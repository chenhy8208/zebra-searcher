package com.hongru.search;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.search.Query;

/**
 * Created by chenhongyu on 2016/10/12.
 */
public interface SearchQuery {
    public Query createQuery(Analyzer analyzer, String search_query);
}
