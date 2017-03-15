package com.hongru.common.lucene.conf;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.StringReader;

/**
 * Created by chenhongyu on 16/10/6.
 */
public class TestConfigManager {

    //TODO IKAnalyer的分词效果没有出来
//    @Test
//    public void testGetAnalyzer() {
//        String text="北京新鸿儒基于java语言开发的轻量级的中文分词工具包";
//        try {
//            showAnalyzerResult(ConfigManager.getAnalyzer(), text);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void showAnalyzerResult(IKAnalyzer analyzer, String str) throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("\n" + analyzer.getClass().getSimpleName());
        StringReader reader = new StringReader(str);
        TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(str));
        CharTermAttribute term= tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset();

        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("耗时：" + time + "ms");

        while (tokenStream.incrementToken()) {
            System.out.print(term.toString() + "|");
        }
    }
}
