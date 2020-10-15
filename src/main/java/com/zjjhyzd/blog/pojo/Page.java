package com.zjjhyzd.blog.pojo;

import com.baomidou.mybatisplus.core.metadata.IPage;

public class Page<T> extends com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> implements IPage<T> {
    private Long page;
    private Long limit;

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        super.current = page;
        this.page = page;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        super.size = limit;
        this.limit = limit;
    }


    public Page() {
        super();
        this.page = super.current;
        this.limit = super.size;
    }

    public Page(long current, long size) {
        super(current, size);
        this.page = current;
        this.limit = size;
    }

    @Override
    public String toString() {
        return "Page{" +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }
}
