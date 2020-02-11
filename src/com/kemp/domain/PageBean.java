package com.kemp.domain;

import java.util.List;

/**
 * 分页对象
 */
public class PageBean<T> {
    private int totalCount; //总记录数
    private int rowCount;  //每页显示行数
    private List<T> list; //每页的数据
    private int currentPage; //当前的页面
    private int totalPage; //总页码数

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", rowCount=" + rowCount +
                ", list=" + list +
                ", currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                '}';
    }
}
