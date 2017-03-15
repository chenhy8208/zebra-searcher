package com.hongru;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.lang3.StringUtils;

/**
 * Unit test for simple AppInit.
 */
public class AppInitTest
    extends TestCase
{
    
    public void testContains() {
        System.out.println("StringUtils.contains(\"网站建设大王\", \"网站2建设\") = " + StringUtils.contains("网站建设大王", "网站2建设"));
    }
    
    public void testGetWebSiteData() {
//
//        String keyword = "网站";
//        DirectoryReader directoryReader = null;
//        IndexSearcher indexSearcher = null;
//        try {
//            // 2、创建IndexReader
//            directoryReader = DirectoryReader.open(ConfigManager.getDirectory());
//            indexSearcher = new IndexSearcher(directoryReader);
//
//            Analyzer analyzer = ConfigManager.getAnalyzer();
//            //QueryParser queryParser = new MultiFieldQueryParser(new String[]{"metaTitle","html"}, analyzer);
//            QueryParser queryParser = new QueryParser("html", analyzer);
//            Query query = queryParser.parse(keyword);
//
//            TopDocs topDocs = indexSearcher.search(query, 10);
//            System.out.println("查找到的文档总共有："+topDocs.totalHits);
//            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
//
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//            for (ScoreDoc scoreDoc: scoreDocs
//                    ) {
//
//                int docId = scoreDoc.doc;
//                Document doc = indexSearcher.doc(docId);
//
//                WebHtml webHtml = HTMLDocumentUtils.documentToHtml(doc);
//
//                System.out.println("url = " + webHtml.getUrl());
//                System.out.println("crawlTime = " + sdf.format(webHtml.getCrawlTime()));
//                System.out.println("pageUpdateTime = " + sdf.format(webHtml.getPageUpdateTime()));
//                System.out.println("metaTitle = " + webHtml.getMetaTitle());
//                System.out.println("metaKeyword = " + webHtml.getMetaKeyword());
//                System.out.println("metaDescription = " + webHtml.getMetaDescription());
//                //System.out.println("html = " + webHtml.getHtml());
//                //System.out.println("text = " + webHtml.getText());
//                System.out.println("statusCode = " + webHtml.getStatusCode());
//                System.out.println("htmlLength = " + webHtml.getHtmlLength());
//                System.out.println("numberOfOutgoingLinks = " + webHtml.getNumberOfOutgoingLinks());
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


        System.out.println("over = " + true);
    }

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppInitTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppInitTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
