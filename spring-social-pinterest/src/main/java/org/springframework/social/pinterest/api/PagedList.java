package org.springframework.social.pinterest.api;

import java.util.List;

/**
 * Created by Rajaboopathy Vijay on 10/27/15.
 */
public class PagedList<T> extends CounterList<T> {

    private final PageParameters pageParameters;

    public PagedList(List<T> unpagedList, Integer totalCount) {
        this(unpagedList, totalCount, null);
    }

    public PagedList(List<T> unpagedList, Integer totalCount, PageParameters pageParameters) {
        super(unpagedList, totalCount);
        this.pageParameters = pageParameters;
    }

    public PageParameters getPageParameters() {
        return pageParameters;
    }
}
