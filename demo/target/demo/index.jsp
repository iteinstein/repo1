<%--
  Created by IntelliJ IDEA.
  User: lichuan
  Date: 2019/10/20
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js"></script>
    <link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
    <!-- 引入bootbox弹框插件的css文件和js文件 -->
    <script src="<%=request.getContextPath()%>/js/bootbox/bootbox.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootbox/bootbox.locales.min.js"></script>
    <link href="<%=request.getContextPath()%>/js/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
    <script  src="<%=request.getContextPath()%>/js/zTree/js/jquery.ztree.core.min.js"></script>
    <script>


        var option = {
            data: {
                simpleData: {
                    enable: true,
                    idKey:"id",
                    pIdKey:"pid"
                }
            },
            view:{
                //禁用多选节点
                selectedMulti:false
            }
        };

        var buttons = {
            confirm:{
                label:"确认"
            },
            cancel:{
                label:"取消",
                className:"btn btn-danger"
            }
        };

        var treeObj;

        $(function(){
            backupDivHTML();
            initCategoryTree();
        });

        var addCategoryDivHTML = "";
        var updateCategoryDivHTML = "";
        function backupDivHTML(){
            addCategoryDivHTML = $("#addCategoryDiv").html();
            updateCategoryDivHTML = $("#updateCategoryDiv").html();
        }

        function initCategoryTree(){
            $.ajax({
                url:"<%=request.getContextPath()%>/CategoryController/queryCategoryList",
                type:"post",
                dataType:"json",
                data:{},
                success:function(result){
                    if(result.code==200){
                        alert(result.data);
                        treeObj = $.fn.zTree.init($("#zTree"), option, result.data);
                        treeObj.expandAll(true);
                    }else{
                        showErrorAlert("初始化分类树失败!");
                    }
                },
                error:function(){
                    showErrorAlert("初始化分类树失败!");
                }
            });
        }

        function showAddCategoryDialog(){
            var selectedNodes = treeObj.getSelectedNodes();
            if(selectedNodes.length > 0 ) {
                var selectedNode = treeObj.getSelectedNodes()[0];
                $("#addParentName").val(selectedNode.name);
                bootbox.confirm({
                    title: "新增分类",
                    message: $("#addCategoryDiv form"),
                    buttons: buttons,
                    callback: function (result) {
                        alert(result);
                        var addName = $("#addName").val();
                        if (result) {
                            $.ajax({
                                url: "<%=request.getContextPath()%>/CategoryController/addCategory",
                                dataType: "json",
                                data: {
                                    "name": addName,
                                    "pid": selectedNode.id
                                },
                                success: function (result) {
                                    if(result.code==200){
                                        var newNode = {"id": result.data, "pid": selectedNode.id, "name": addName};
                                        treeObj.addNodes(selectedNode, -1, newNode, false);
                                    }else{
                                        showErrorAlert("新增分类失败!");
                                    }
                                },
                                error: function () {
                                    showErrorAlert("新增分类失败!");
                                }
                            });
                        }
                        $("#addCategoryDiv").html(addCategoryDivHTML);
                    }
                });
            }else{
                showErrorAlert("请先选择父分类!");
            }
        }

        function showUpdateCategoryDialog(){
            var selectedNodes = treeObj.getSelectedNodes();
            if(selectedNodes.length > 0 ) {
                var selectedNode = treeObj.getSelectedNodes()[0];
                var parentNode = selectedNode.getParentNode();
                $.ajax({
                    url: "<%=request.getContextPath()%>/CategoryController/getCategoryById",
                    type: "post",
                    data: {
                        "id": selectedNode.id
                    },
                    dataType:"json",
                    success: function (result) {
                        if(result.code==200){
                            $("#updateParentName").val(parentNode.name);
                            $("#updateName").val(result.data.name);
                            bootbox.confirm({
                                title: "修改分类",
                                message: $("#updateCategoryDiv form"),
                                buttons: buttons,
                                callback: function (result) {
                                    var updateName = $("#updateName").val();
                                    if (result) {
                                        $.ajax({
                                            url: "<%=request.getContextPath()%>/CategoryController/updateCategory",
                                            type: "post",
                                            dataType: "json",
                                            data: {
                                                "name": updateName,
                                                "id": selectedNode.id,
                                                "pid": parentNode.id
                                            },
                                            success: function (result) {
                                                if(result.code==200){
                                                    selectedNode.name = updateName;
                                                    treeObj.updateNode(selectedNode);
                                                }else{
                                                    showErrorAlert("修改分类失败!");
                                                }
                                            },
                                            error: function () {
                                                showErrorAlert("修改分类失败!");
                                            }
                                        });
                                    }
                                    $("#updateCategoryDiv").html(updateCategoryDivHTML);
                                }
                            });
                        }
                    },
                    error: function () {
                        showErrorAlert("获取分类失败!");
                    }
                });
            }else{
                showErrorAlert("请先选择要修改的分类!");
            }
        }

        function showErrorAlert(message){
            bootbox.alert({
                title:"<span style='color:red;font-weight:bold'><span class='glyphicon glyphicon-remove'></span>&nbsp;错误提示</span>",
                message:message,
                buttons:{
                    ok:{
                        label:"确定"
                    }
                }
            });
        }

        function deleteCategory(){
            //获取被选中的节点的数组
            var selectedNodes = treeObj.getSelectedNodes();
            if(selectedNodes.length > 0 ) {
                //索取包含节点自身在内的所有子孙后代的数组
                var arr = treeObj.transformToArray(selectedNodes[0]);
                var ids = [];
                for(var i = 0 ; i < arr.length ; i ++){
                    ids.push(arr[i].id);
                }
                $.ajax({
                    url:"<%=request.getContextPath()%>/CategoryController/deleteCategory",
                    type:"post",
                    dataType:"json",
                    data:{
                        "ids":ids
                    },
                    success:function(result){
                        if(result.code==200){
                            treeObj.removeNode(selectedNodes[0]);
                        }else{
                            showErrorAlert("删除分类失败!");
                        }
                    },
                    error:function(){
                        showErrorAlert("删除分类失败!");
                    }
                });
            }else{
                showErrorAlert("请先选择要进行删除的分类!");
            }
        }
    </script>
</head>
<body>

    <!--新增分类div-->
    <div id="addCategoryDiv" style="display: none">
        <form id="addPhoneForm" class="form-horizontal" style="">
            <div class="form-group">
                <label class="col-sm-2 control-label">父类名称:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" readonly id="addParentName" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">分类名称:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="addName" />
                </div>
            </div>
        </form>
    </div>

    <!--修改分类div-->
    <div id="updateCategoryDiv" style="display: none">
        <form id="updateCategoryForm" class="form-horizontal" style="">
            <div class="form-group">
                <label class="col-sm-2 control-label">父类名称:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" readonly id="updateParentName" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">分类名称:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="updateName" />
                </div>
            </div>
        </form>
    </div>

    <button class="btn btn-primary btn-sm" onclick="showAddCategoryDialog()">
        <span class="glyphicon glyphicon-plus"></span>
        新增分类
    </button>
    <button class="btn btn-primary btn-sm" onclick="showUpdateCategoryDialog()">
        <span class="glyphicon glyphicon-pencil"></span>
        修改分类
    </button>
    <button class="btn btn-danger btn-sm" onclick="deleteCategory()">
        <span class="glyphicon glyphicon-trash"></span>
        删除分类
    </button>
    <ul id="zTree" class="ztree"></ul>
</body>
</html>
