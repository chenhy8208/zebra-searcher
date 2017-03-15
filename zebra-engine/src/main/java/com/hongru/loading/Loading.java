package com.hongru.loading;

import com.hongru.domain.WebHtml;
import edu.uci.ics.crawler4j.crawler.Page;

/**
 * Created by chenhongyu on 16/9/28.
 */
public interface Loading {
    public WebHtml loading(Page page);
}
