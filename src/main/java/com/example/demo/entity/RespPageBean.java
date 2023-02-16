package com.example.demo.entity;

import java.util.List;

public class RespPageBean {
    private Long total;
    private List<?> data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public RespPageBean(){
    }

    public RespPageBean(Long total, List<?> data){
        this.total = total;
        this.data = data;
    }
}
