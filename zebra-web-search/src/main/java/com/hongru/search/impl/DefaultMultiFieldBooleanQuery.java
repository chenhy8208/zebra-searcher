package com.hongru.search.impl;

import com.hongru.search.SearchQuery;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 多条件分词搜索
 * Created by chenhongyu on 2016/10/12.
 */
public class DefaultMultiFieldBooleanQuery implements SearchQuery {

    @Override
    public Query createQuery(Analyzer analyzer, String search_query) {
        String[] searchFields={"metaTitle","metaKeyword","metaDescription"};
        //条件构造
        org.apache.lucene.search.BooleanQuery.Builder booleanQueryBuilder = new org.apache.lucene.search.BooleanQuery.Builder();
        String[] keywords = search_query.split("\\s");
        for (String keyword: keywords
                ) {
            QueryParser parser= new MultiFieldQueryParser(searchFields, analyzer);

            try {
                Query query=parser.parse(keyword);

                BooleanClause clause = new BooleanClause(query, BooleanClause.Occur.MUST);
                booleanQueryBuilder.add(clause);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        org.apache.lucene.search.BooleanQuery query = booleanQueryBuilder.build();

        return query;
    }
}
