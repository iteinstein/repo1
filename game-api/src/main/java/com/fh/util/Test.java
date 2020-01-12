package com.fh.util;

import com.alibaba.fastjson.JSON;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Test {

    public static void main(String[] args) {
        Map<String,Object> member = new HashMap<>();
        member.put("uuid", UUID.randomUUID().toString());
        member.put("username","zhangsan");
        member.put("id",UUID.randomUUID().toString());
        String string = JSON.toJSONString(member);
        String encode = Base64.getEncoder().encodeToString(string.getBytes());
        System.out.println(encode);
        String key = "123456";
        String hex = MD5Util.md5Hex(encode + key);
        String string1 = Base64.getEncoder().encodeToString(hex.getBytes());
        String str = encode + "." + string1;
        System.out.println(str);

        String[] split = str.split("\\.");
        String memberBase64Json = split[0];
        System.out.println(memberBase64Json);
        String sign = split[1];
        String s = MD5Util.md5Hex(memberBase64Json+key);

        String string2 = Base64.getEncoder().encodeToString(s.getBytes());
        System.out.println(sign);
        if(!string2.equals(sign)){
            System.out.println("签名无效");
        }
    }

}
