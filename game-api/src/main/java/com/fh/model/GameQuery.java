package com.fh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class GameQuery extends DataTablePageBean{

    private String gname;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date minShowTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date maxShowTime;

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Date getMinShowTime() {
        return minShowTime;
    }

    public void setMinShowTime(Date minShowTime) {
        this.minShowTime = minShowTime;
    }

    public Date getMaxShowTime() {
        return maxShowTime;
    }

    public void setMaxShowTime(Date maxShowTime) {
        this.maxShowTime = maxShowTime;
    }
}
