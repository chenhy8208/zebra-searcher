package com.hongru.storage;

import com.hongru.domain.WebHtml;

/**
 * Created by chenhongyu on 16/9/28.
 */
public interface HtmlPersistence {
    public boolean filterAndSave(WebHtml webHtml);
}
