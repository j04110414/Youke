package com.tofly.youke.common.utils;

import com.tofly.youke.common.domain.BackResult;

/**
 * DataTablesPager分页对象
 * Created by cremin on 2017/8/9.
 */
public class DataTablesPager<T> extends BackResult {


    private long recordsTotal;

    private long recordsFiltered;

    private int draw;

    public DataTablesPager() {

    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }
}
