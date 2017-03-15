package com.hongru.common.lucene;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;

public class LuceneUtils {

	public static void closeIndexWriter(IndexWriter indexWriter) {
		if (indexWriter == null) {
			return;
		}

		try {
			indexWriter.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void closeIndexSearcher(IndexSearcher indexSearcher) {
		if (indexSearcher == null) {
			return;
		}

		//关闭indexSearcher
	}
}
