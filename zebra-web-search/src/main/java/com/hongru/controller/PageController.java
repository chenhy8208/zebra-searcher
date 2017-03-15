package com.hongru.controller;

import com.hongru.common.lucene.HTMLDocumentUtils;
import com.hongru.common.lucene.config.ConfigManager;
import com.hongru.common.util.PageVo;
import com.hongru.common.util.StrUtils;
import com.hongru.config.AppConfig;
import com.hongru.domain.FindResult;
import com.hongru.domain.WebHtml;
import com.hongru.search.SearchQuery;
import com.hongru.search.impl.DefaultMultiFieldBooleanQuery;
import com.hongru.search.impl.DefaultTermBooleanQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringReader;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chenhongyu on 2016/10/8.
 */
@Controller
public class PageController {

    @Autowired
    private ConfigManager configManager;

    @Autowired
    private AppConfig appConfig;

    @RequestMapping("/index")
    public void index(ModelMap model) {

    }

    @RequestMapping("/results")
    public void results(HttpServletRequest request, ModelMap model) {

        //计时
        long start = System.currentTimeMillis();

        //初始化
        List<FindResult<WebHtml>> results = new ArrayList<>();
        model.put("findTotalResults", 0);
        model.put("findTotalPagesizes", 0);
        model.put("findSeconds", 0);
        model.put("results", results);

        String search_query  = StrUtils.getString(request, "search_query");
        int pageNum = StrUtils.getInt(request, "pageindex", 1);

        //搜索词
        model.put("search_query", search_query);

        //没有用的搜索词直接返回
        if (StringUtils.isBlank(search_query))
            return;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        IndexSearcher indexSearcher = null;
        Query query = null;
        TopDocs topDocs = null;

        PageVo<FindResult<WebHtml>> pageVo = new PageVo<FindResult<WebHtml>>(pageNum);
        pageVo.setRows(appConfig.getSearchResultPagesize());

        try {
            // 创建IndexReader
            indexSearcher = configManager.getIndexSearcher();
            SearchQuery searchQuery = new DefaultMultiFieldBooleanQuery();
            query = searchQuery.createQuery(configManager.getAnalyzer(), search_query);

            //更新时间倒排序
            SortField sortF =new SortField("pageUpdateTime", SortField.Type.LONG, true);
            Sort sort =new Sort(sortF);

            //Sort.RELEVANCE 相关度排序

            //分页取纪录
            topDocs = indexSearcher.search(query, pageVo.getOffset() + pageVo.getRows(), Sort.RELEVANCE);

            model.put("findTotalResults", topDocs.totalHits);

            ScoreDoc[] scoreDocs = topDocs.scoreDocs;

            for (int i = pageVo.getOffset(); i < scoreDocs.length; i ++) {
                ScoreDoc scoreDoc = scoreDocs[i];
                int docId = scoreDoc.doc;
                Document doc = indexSearcher.doc(docId);

                WebHtml webHtml = HTMLDocumentUtils.documentToHtml(doc);
                FindResult<WebHtml> findResult = new FindResult<>();
                findResult.setWebHtml(webHtml);
                markRelativity(scoreDoc, findResult);
                results.add(findResult);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(results != null){
            markHighlighter(query, results);

            pageVo.setList(results);
            Map<String,String> map = new HashMap<String, String>();
            map.put("search_query", search_query);
            pageVo.setArgs(map);
            pageVo.setCount(topDocs.totalHits);
        }

        model.put("pageVo", pageVo);
        model.put("findTotalPagesizes", pageVo.getPageCount());
        model.put("results", results);
        model.put("findSeconds", (System.currentTimeMillis()-start)/1000f);
    }

    /**
     * 标记相关度
     * @param scoreDoc
     * @param findResult
     */
    private void markRelativity(ScoreDoc scoreDoc, FindResult<WebHtml> findResult) {
        Float fpercent = scoreDoc.score;
        //转百分数
//        NumberFormat percentFormat = NumberFormat.getPercentInstance();
//        percentFormat.setMaximumFractionDigits(2); //最大小数位数

        findResult.setRelativity(fpercent);
//        findResult.setRelativityStr(percentFormat.format(fpercent));
    }

    /**
     * 高亮
     * @param query
     * @param results
     */
    private void markHighlighter(Query query, List<FindResult<WebHtml>> results) {
        //高亮
        QueryScorer scorer=new QueryScorer(query);
        Fragmenter fragmenter=new SimpleSpanFragmenter(scorer, 30);
        SimpleHTMLFormatter simpleHTMLFormatter=new SimpleHTMLFormatter("<font class='keyword'>", "</font>");
        Highlighter highlighterTitle=new Highlighter(simpleHTMLFormatter, scorer);
        highlighterTitle.setTextFragmenter(fragmenter);

        //高亮简介
        Fragmenter fragmenterIntro=new SimpleSpanFragmenter(scorer, 200);
        Highlighter highlighterIntro=new Highlighter(simpleHTMLFormatter, scorer);
        highlighterIntro.setTextFragmenter(fragmenterIntro);

        for (FindResult<WebHtml> findResult: results
             ) {
            WebHtml webHtml = findResult.getWebHtml();

            //高亮
            try {
                String metaTitle=webHtml.getMetaTitle();
                if(metaTitle!=null){
                    TokenStream tokenStream = configManager.getAnalyzer().tokenStream("metaTitle", new StringReader(metaTitle));
                    metaTitle = highlighterTitle.getBestFragment(tokenStream, metaTitle);
                    if (StringUtils.isNoneBlank(metaTitle)) {
                        webHtml.setMetaTitle(metaTitle);
                    }
                }

                String metaDescription=webHtml.getMetaDescription();
                if(metaDescription!=null){
                    TokenStream tokenStream = configManager.getAnalyzer().tokenStream("metaDescription", new StringReader(metaDescription));
                    metaDescription = highlighterIntro.getBestFragment(tokenStream, metaDescription);
                    if (StringUtils.isNoneBlank(metaDescription)) {
                        webHtml.setMetaDescription(metaDescription);
                    }
                }

            } catch (InvalidTokenOffsetsException e) {
                logger.error("markHighlighter fault:" + e.getMessage());
            } catch (IOException e) {
                logger.error("markHighlighter fault:" + e.getMessage());
            }
        }
    }

    private static final Logger logger = LogManager.getLogger(PageController.class);
}
