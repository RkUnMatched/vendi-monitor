package com.vendixxx.monitor.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;

/**
 * @author liuzheng
 * @date 2021-01-12
 *
 * 构造URL地址. 用法举例：
 * <pre>
 *
 * URIBuilder build =  new URIBuilder("http://127.0.0.1:8080");
 * build.addPath("/purchase/");
 * build.addPath("index");
 * build.addPath("/query").addParameter("name", "maelk");
 * Assert.assertEquals("http://127.0.0.1:8080/purchase/index/query?name=maelk", build.build().toString());
 *
 * </pre>
 */
@Slf4j
public class PathURIBuilder {

    private URIBuilder builder;

    public PathURIBuilder(String baseUrl)  {
        try {
            builder = new URIBuilder(baseUrl);
        } catch (URISyntaxException e) {
            log.error("url is not well formed", e);
        }
    }

    public PathURIBuilder addPath(String subPath) {
        if (subPath == null || subPath.isEmpty() || "/".equals(subPath)) {
            return this;
        }

        if(builder != null) {
            builder.setPath(this.appendSegmentToPath(builder.getPath(), subPath));
        }
        return this;
    }

    public PathURIBuilder addParameter(String key, String value) {
        if(builder != null) {
            builder.addParameter(key,value);
        }
        return this;
    }

    public String build(){
        try {
            if(builder != null) {
                return this.builder.build().toString();
            }
        } catch (URISyntaxException e) {
            log.error("url is not well formed.", e);
        }
        return null;
    }

    private String appendSegmentToPath(String path, String segment) {
        if (path == null || path.isEmpty()) {
            path = "/";
        }

        if (path.charAt(path.length() - 1) == '/' || segment.startsWith("/")) {
            return path + segment;
        }

        return path + "/" + segment;
    }
}
