package com.codesimple.bookstore.common;

import org.springframework.data.domain.Page;

public class PaginationMeta {
    //total count 100
    //page size 10
    //total pages 10
    //page number = 1(1-10),2(11-20)...10(91-100)
    //isLast = true
    //isFirst = true

    private Long totalCount;
    private Integer pageSize;
    private Integer totalPage;
    private Integer pageNumber;
    private Boolean isLast;
    private Boolean isFirst;

    public PaginationMeta(Long totalCount, Integer pageSize, Integer totalPage, Integer pageNumber, Boolean isLast, Boolean isFirst) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.pageNumber = pageNumber;
        this.isLast = isLast;
        this.isFirst = isFirst;
    }

    public PaginationMeta() {

    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Boolean getLast() {
        return isLast;
    }

    public void setLast(Boolean last) {
        isLast = last;
    }

    public Boolean getFirst() {
        return isFirst;
    }

    public void setFirst(Boolean first) {
        isFirst = first;
    }

    public static <T> PaginationMeta createPagination(Page<T> page){
        PaginationMeta paginationMeta = new PaginationMeta();

        paginationMeta.setFirst(page.isFirst());
        paginationMeta.setLast(page.isLast());
        paginationMeta.setPageNumber(page.getNumber());
        paginationMeta.setPageSize(page.getSize());
        paginationMeta.setTotalCount(page.getTotalElements());
        paginationMeta.setTotalPage(page.getTotalPages());

        return paginationMeta;
    }
}
