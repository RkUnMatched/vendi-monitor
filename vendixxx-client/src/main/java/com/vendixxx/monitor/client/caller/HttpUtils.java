package com.vendixxx.monitor.client.caller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vendixxx.monitor.common.exception.VendixxxServiceException;
import com.vendixxx.monitor.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * RestTemplate util
 */
@Slf4j
public class HttpUtils {

    /**
     * HTTP POST
     * @param restTemplate  restTemplate
     * @param path  请求路径
     * @param request  请求消息体
     * @param responseType 响应类型
     * @param <T>  响应类型
     * @param extraHeaders 额外的请求头
     * @return  响应结果
     * @throws Exception
     */
    public static <T> T post(RestTemplate restTemplate, String path, Object request, Class<T> responseType, Map<String, String> extraHeaders) throws Exception {

        log.debug("begin to do http post. path:{}, request :{}", path, request);

        MultiValueMap<String, String> headers = convert(extraHeaders);
        HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);

        T t = restTemplate.postForObject(path, requestEntity, responseType);

        log.debug("end to do http get. path:{}, request params:{}. response body: {}", path, request, t);

        return t;
    }


    /**
     * HTTP POST
     * @param restTemplate  restTemplate
     * @param path  请求路径
     * @param request  请求消息体
     * @param responseType 响应类型
     * @param <T>  响应类型
     * @return  响应结果
     * @throws Exception
     */
    public static <T> T post(RestTemplate restTemplate, String path, Object request, Class<T> responseType) throws Exception {
        return post(restTemplate, path, request, responseType, null);
    }


    /**
     * HTTP GET
     * @param restTemplate  restTemplate
     * @param path  请求路径
     * @param request 请求体，object，如果是个map，会自动转化为url参数
     * @param responseType 响应类型
     * @param <T> 响应类型
     * @param extraHeaders 额外的请求头
     * @return  结果
     * @throws Exception
     */
    public static <T> T get(RestTemplate restTemplate, String path, Object request, Class<T> responseType, Map<String, String> extraHeaders) throws Exception {

        log.debug("begin to do http get. path:{}, request params:{}", path, request);

        //add accept json
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        //添加额外的请求头
        if(MapUtils.isNotEmpty(extraHeaders)){
            for(Map.Entry<String,String> entry: extraHeaders.entrySet()){
                headers.set(entry.getKey(), entry.getValue());
            }
        }

        HttpEntity<?> entity = new HttpEntity<>(headers);

        //url
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(path);

        //添加参数
        Map<String, ?> params = (Map<String, ?>) request;
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, ?> urlParam : params.entrySet()) {
                builder.queryParam(urlParam.getKey(), urlParam.getValue());
            }
        }
        final URI getURI = builder.build().encode().toUri();

        HttpEntity<T> response = restTemplate.exchange(getURI, HttpMethod.GET, entity, responseType);

        log.debug("end to do http get. path:{}, request params:{}. response body: {}", path, request, response.getBody());

        return response.getBody();
    }


    /**
     * HTTP GET
     * @param restTemplate  restTemplate
     * @param path  请求路径
     * @param request 请求体，object，如果是个map，会自动转化为url参数
     * @param responseType 响应类型
     * @param <T> 响应类型
     * @return  结果
     * @throws Exception
     */
    public static <T> T get(RestTemplate restTemplate, String path, Object request, Class<T> responseType) throws Exception {
        return get(restTemplate,path,request,responseType,null);
    }


    private final static MultiValueMap<String, String>  convert(Map<String,String> extraHeaders){

        MultiValueMap<String, String> headers = new LinkedMultiValueMap();

        if(MapUtils.isNotEmpty(extraHeaders)){
            for(Map.Entry<String,String> entry: extraHeaders.entrySet()){
                headers.add(entry.getKey(), entry.getValue());
            }
        }

        return  headers;
    }


    public static String doGet(HttpClient httpClient, String url) {
        return doGet(httpClient,url, Collections.EMPTY_MAP, Collections.EMPTY_MAP);
    }

    public static String doGet(HttpClient httpClient,String url, Map<String, Object> params) {
        return doGet(httpClient,url, Collections.EMPTY_MAP, params);
    }

    public static String doGet(HttpClient httpClient, String url, Map<String, String> headers, Map<String, Object> params) {

        // 构建GET请求头
        String apiUrl = getUrlWithParams(url, params);
        HttpGet httpGet = new HttpGet(apiUrl);

        // 设置header信息
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpGet.addHeader(entry.getKey(), entry.getValue());
            }
        }
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response == null || response.getStatusLine() == null) {
                return null;
            }

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                org.apache.http.HttpEntity entityRes = response.getEntity();
                if (entityRes != null) {
                    return EntityUtils.toString(entityRes, "UTF-8");
                }
            }
            return null;
        } catch (IOException e) {
        } finally {
            if (response != null && response instanceof CloseableHttpResponse) {
                try {
                    ((CloseableHttpResponse)response).close();
                } catch (IOException e) {

                }
            }
        }
        return null;
    }

    public static String doPost(HttpClient httpClient,String apiUrl, String requestBody) {
        return doPost(httpClient,apiUrl, Collections.EMPTY_MAP, requestBody);
    }

    public static String doPost(HttpClient httpClient,String apiUrl, Map<String, String> headers, String requestBody) {

        HttpPost httpPost = new HttpPost(apiUrl);
        //  配置请求headers
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }

        httpPost.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));


        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            if (response == null || response.getStatusLine() == null) {
                return null;
            }

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                org.apache.http.HttpEntity entityRes = response.getEntity();
                if (entityRes != null) {
                    return EntityUtils.toString(entityRes, "UTF-8");
                }
            }
            if(statusCode == HttpStatus.SC_MOVED_TEMPORARILY){
                return null;
            }
            return null;
        } catch (IOException e) {
            log.info("httpClient fail reason is {}",e);
            return null;
        } finally {
            if (response != null && response instanceof CloseableHttpResponse) {
                try {
                    ((CloseableHttpResponse)response).close();
                } catch (IOException e) {
                    return null;
                }
            }
        }
    }

    private static org.apache.http.HttpEntity getUrlEncodedFormEntity(Map<String, Object> params) {
        List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
                    .getValue().toString());
            pairList.add(pair);
        }
        return new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8"));
    }

    private static String getUrlWithParams(String url, Map<String, Object> params) {
        boolean first = true;
        StringBuilder sb = new StringBuilder(url);
        for (String key : params.keySet()) {
            char ch = '&';
            if (first == true) {
                ch = '?';
                first = false;
            }
            String value = params.get(key).toString();
            try {
                String sval = URLEncoder.encode(value, "UTF-8");
                sb.append(ch).append(key).append("=").append(sval);
            } catch (UnsupportedEncodingException e) {
            }
        }
        return sb.toString();
    }

    public static <T> T clientPost(HttpClient httpClient, String path, Object request, Class<T> responseType, Map<String, String> extraHeaders){
        String requestBody;
        if(request == null){
            requestBody = "";
        }else{
            requestBody = JSON.toJSONString(request);
        }
        String result = doPost(httpClient,path,extraHeaders,requestBody);
        if(StringUtils.isEmpty(result)){
            throw new VendixxxServiceException("refer http failed");
        }
        return JSON.parseObject(result,responseType);
    }

    public static <T> T clientGet(HttpClient httpClient, String path, Object request, Class<T> responseType, Map<String, String> extraHeaders) throws Exception {
        JSONObject jsonObject;
        if(request == null){
            jsonObject = new JSONObject();
        }else{
            jsonObject = JSONObject.parseObject(JSON.toJSONString(request));
        }
        String result = doGet(httpClient,path,extraHeaders,jsonObject);
        if(StringUtils.isEmpty(result)){
            throw new VendixxxServiceException("refer http failed");
        }
        return JSON.parseObject(result,responseType);
    }

}
