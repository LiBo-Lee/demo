package com.example.demo.entity;

import java.util.List;

/**
 * @Description: 分页
 * @Author: LB
 * @Date: 2023年2月17日
 */
public class RespPageBean {
    private Long totalCount;
    private Long leftCount;
    private List<?> data;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(Long leftCount) {
        this.leftCount = leftCount;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public RespPageBean(){
    }

    public RespPageBean(Long leftCount, Long totalCount, List<?> data){
        this.leftCount = leftCount;
        this.totalCount = totalCount;
        this.data = data;
    }
}
