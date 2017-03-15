package com.hongru.search.impl;

import com.hongru.search.SearchQuery;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 多条件不分词
 * Created by chenhongyu on 2016/10/12.
 */
public class DefaultTermBooleanQuery implements SearchQuery {

    @Override
    public Query createQuery(Analyzer analyzer, String search_query) {
        Map<String,  BooleanClause.Occur> searchFields = new HashMap<>();
        searchFields.put("metaTitle", BooleanClause.Occur.MUST);
        searchFields.put("metaKeyword", BooleanClause.Occur.SHOULD);
        searchFields.put("metaDescription", BooleanClause.Occur.SHOULD);

        //条件构造
        org.apache.lucene.search.BooleanQuery.Builder booleanQueryBuilder = new org.apache.lucene.search.BooleanQuery.Builder();
        String[] keywords = search_query.split("\\s");
        for (String keyword: keywords
                ) {

            //多字段搜索
            Set<Map.Entry<String,  BooleanClause.Occur>> entrySet = searchFields.entrySet();
            for (Map.Entry<String,  BooleanClause.Occur> entry : entrySet) {
                String key = entry.getKey();

                Term term = new Term(key, keyword);
                TermQuery tQuery = new TermQuery(term);

                BooleanClause clause = new BooleanClause(tQuery, searchFields.get(key));
                booleanQueryBuilder.add(clause);
            }

        }

        org.apache.lucene.search.BooleanQuery query = booleanQueryBuilder.build();

        return query;
    }
}
