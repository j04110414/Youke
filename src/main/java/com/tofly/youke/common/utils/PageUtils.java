package com.tofly.youke.common.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author lyrics
 * @date 2017-12-28
 */
public class PageUtils {
    /**
     * 默认分页偏移量
     **/
    public static int DEFUALT_PAGENUM = 0;

    /**
     * 默认分页每页数量
     **/
    public static int DEFUALT_PAGESIZE = 10;

    public static int DEFUALT_DRAW = 1;

    public static Page<Object> getPagerParams(HttpServletRequest request) {

        String pageNum = request.getParameter("start");
        String pageSize = request.getParameter("length");

        int pageNumInt = DEFUALT_PAGENUM;
        int pageSizeInt = DEFUALT_PAGESIZE;

        if (StringUtils.hasText(pageNum)) {
            pageNumInt = Integer.parseInt(pageNum);
        }

        if (StringUtils.hasText(pageSize)) {
            pageSizeInt = Integer.parseInt(pageSize);
        }

        return PageHelper.startPage(pageNumInt, pageSizeInt);
    }

    public static DataTablesPager pageHelperToDataTablesPager(Page page, HttpServletRequest request) {

        String draw = request.getParameter("draw");

        DataTablesPager dataTablesPager = new DataTablesPager();

        dataTablesPager.setData(page.getResult());
        dataTablesPager.setRecordsTotal(page.getTotal());
        dataTablesPager.setRecordsFiltered(page.getTotal());
        dataTablesPager.setDraw(org.apache.commons.lang3.StringUtils.isNotBlank(draw) ? Integer.parseInt(draw) : DEFUALT_DRAW);

        return dataTablesPager;
    }
}
