package org.springframework.social.pinterest.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajaboopathy Vijay on 10/27/15.
 */
public class CounterList<T> extends ArrayList<T> {

    private final Integer totalCount;

    public CounterList(List<T> unpagedList, Integer totalCount) {
        super(unpagedList);
        this.totalCount = totalCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }
}
