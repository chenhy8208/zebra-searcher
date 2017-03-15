package com.hongru.common.lucene;

import com.hongru.domain.WebHtml;
import com.hongru.loading.impl.CrawlerLoading;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.james.mime4j.dom.field.DateTimeField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.document.*;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.util.NumericUtils;
import org.apache.poi.util.LongField;

import java.lang.reflect.*;
import java.util.Date;

public class HTMLDocumentUtils {

	/**
	 * 把webhtml中的null去掉,因为lucene中会报错
	 * @param html
     */
	private static void updateWebHtmlNullToEmpty(WebHtml html) {

		if (html == null) {
			logger.warn("updateWebHtmlNullToEmpty fault,Because html = " + html);
			return;
		}

		//得到类对象
		Class userCla = (Class) html.getClass();

       /*
        * 得到类中的所有属性集合
        */
		java.lang.reflect.Field[] fs = userCla.getDeclaredFields();
		for(int i = 0 ; i < fs.length; i++){
			java.lang.reflect.Field f = fs[i];
			f.setAccessible(true); //设置些属性是可以访问的
			Object val = null;//得到此属性的值
			try {
				val = f.get(html);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				logger.warn("updateWebHtmlNullToEmpty fault,Because = " + e.getMessage());
			}

			if (f.getType() == java.lang.String.class) {
				try {
					f.set(html, StringUtils.stripToEmpty(val+"").replace("null", "")) ;        //给属性设值
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					logger.warn("updateWebHtmlNullToEmpty set value fault,Because = " + e.getMessage());
				}
			}


		}
	}

	private static void addNumberFieldSortAndStored(Document doc, String name, long value, boolean sortable) {
		Field field = null;
		if (sortable) {
			field = new NumericDocValuesField(name, value);
			doc.add(field);
		}

		field = new StoredField(name, value);
		doc.add(field);
	}

	private static void addNumberField(Document doc, String name, long value, boolean sortable) {
		Field field = new LongPoint(name, value);
		doc.add(field);
		addNumberFieldSortAndStored(doc,name,value,sortable);
	}

	private static void addNumberField(Document doc, String name, int value, boolean sortable) {
		Field field = new IntPoint(name, value);
		doc.add(field);
		addNumberFieldSortAndStored(doc,name,value,sortable);
	}

	public static Document htmlToDocument(WebHtml html) {

		if (html == null) {
			logger.warn("htmlToDocument fault,Because html = " + html);
			return null;
		}
		updateWebHtmlNullToEmpty(html);  //将null替换成空
		Document doc = new Document();

		doc.add(new Field("url", html.getUrl(), StringField.TYPE_STORED));
		doc.add(new Field("encoding", html.getEncoding(), StringField.TYPE_STORED));
		doc.add(new Field("contentType", html.getContentType(), StringField.TYPE_STORED));
		doc.add(new Field("author", html.getAuthor(), TextField.TYPE_STORED));
		doc.add(new Field("metaTitle", html.getMetaTitle(), TextField.TYPE_STORED));
		doc.add(new Field("metaKeyword", html.getMetaKeyword(), TextField.TYPE_STORED));
		doc.add(new Field("metaDescription", html.getMetaDescription(), TextField.TYPE_STORED));
		doc.add(new Field("html", html.getHtml(), TextField.TYPE_STORED));
		doc.add(new Field("text", html.getText(), TextField.TYPE_STORED));

		addNumberField(doc, "crawlTime", html.getCrawlTime().getTime(), true);
		addNumberField(doc, "pageUpdateTime", html.getPageUpdateTime().getTime(), true);
		addNumberField(doc, "pageLastModified", html.getPageLastModified().getTime(), true);
		addNumberField(doc, "statusCode", html.getStatusCode(), false);
		addNumberField(doc, "htmlLength", html.getHtmlLength(), true);
		addNumberField(doc, "numberOfOutgoingLinks", html.getNumberOfOutgoingLinks(), true);
		addNumberField(doc, "docId", html.getDocId(), true);

		return doc;
	}

	public static WebHtml documentToHtml(Document doc) {

		if (doc == null) {
			logger.warn("documentToHtml fault,Because html = " + doc);
			return null;
		}

		WebHtml html = new WebHtml();
		html.setDocId(NumberUtils.toInt(doc.get("docId")));
		html.setPageUpdateTime(new Date(NumberUtils.toLong(doc.get("pageUpdateTime"))));
		html.setCrawlTime(new Date(NumberUtils.toLong(doc.get("crawlTime"))));
		html.setPageLastModified(new Date(NumberUtils.toLong(doc.get("pageLastModified"))));
		html.setStatusCode(NumberUtils.toInt(doc.get("statusCode")));
		html.setHtmlLength(NumberUtils.toInt(doc.get("htmlLength")));
		html.setNumberOfOutgoingLinks(NumberUtils.toInt(doc.get("numberOfOutgoingLinks")));
		html.setHtml(doc.get("html"));
		html.setText(doc.get("text"));
		html.setMetaTitle(doc.get("metaTitle"));
		html.setMetaKeyword(doc.get("metaKeyword"));
		html.setMetaDescription(doc.get("metaDescription"));
		html.setUrl(doc.get("url"));
		html.setEncoding(doc.get("encoding"));
		html.setContentType(doc.get("contentType"));
		html.setAuthor(doc.get("author"));

		updateWebHtmlNullToEmpty(html);

		return html;
	}

	private static final Logger logger = LogManager.getLogger(HTMLDocumentUtils.class);
}
