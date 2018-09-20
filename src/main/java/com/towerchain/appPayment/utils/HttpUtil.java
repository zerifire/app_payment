package com.towerchain.appPayment.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation")
public class HttpUtil {
    private static final String APPLICATION_JSON = "application/json";

    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }

            }).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

            return HttpClients.custom().setSSLSocketFactory(sslsf).build();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return HttpClients.createDefault();
    }


    public static String doGet(String url, Map<String, Object> param, Map<String, String> headerMap) throws Exception {
        CloseableHttpClient httpclient = null;
        if (url.startsWith("https")) {
            httpclient = createSSLClientDefault();
        } else {
            httpclient = HttpClients.createDefault();
        }


        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key).toString());
                }
            }
            URI uri = builder.build();

            HttpGet httpGet = new HttpGet(uri);
            if (headerMap != null && headerMap.size() > 0) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }

            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url, Map<String, String> headerMap) throws Exception {
        return doGet(url, null, headerMap);
    }

    public static String doGet(String url) throws Exception {
        return doGet(url, null, null);
    }

    public static String doPost(String url, Map<String, Object> param, Map<String, String> headerMap) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            HttpPost httpPost = new HttpPost(url);

            if (headerMap != null && headerMap.size() > 0) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }

            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key).toString()));
                }
                /*
                 * UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
				 * entity.setContentType("application/json");
				 * entity.setContentEncoding("UTF-8"); httpPost.setEntity(entity);
				 */
                httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);

                StringEntity se = new StringEntity(URLEncoder.encode(JsonUtility.obj2Json(param), "UTF-8"));
                se.setContentType(CONTENT_TYPE_TEXT_JSON);
                se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
                httpPost.setEntity(se);
            }
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doPost(String url, Map<String, String> headerMap) throws Exception {
        return doPost(url, null, headerMap);
    }

    public static String doPostJson(String url, String json, Map<String, String> headerMap) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            if (headerMap != null && headerMap.size() > 0) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (response != null)
                    response.close();
                httpClient.close();
            } catch (IOException e) {
            }
        }
        return resultString;
    }

    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }

    public static byte[] doByteGet(String url, Map<String, Object> param, Map<String, String> headerMap) {

        CloseableHttpClient httpclient = null;
        if (url.startsWith("https")) {
            httpclient = createSSLClientDefault();
        } else {
            httpclient = HttpClients.createDefault();
        }


        byte[] resultString = null;
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key).toString());
                }
            }
            URI uri = builder.build();

            HttpGet httpGet = new HttpGet(uri);
            if (headerMap != null && headerMap.size() > 0) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }

            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                // resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
                resultString = EntityUtils.toByteArray(response.getEntity());
            }
        } catch (Exception e) {
            // throw e;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 参数为xml形式，POST请求
     *
     * @param url
     * @param xml
     * @return
     * @throws IOException
     */
    public static String doPostXml(String url, String xml) throws IOException {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = null;
        try {
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-Type", "application/xml;charset=UTF-8");
            httpClient = createSSLClientDefault();
            StringEntity entity = new StringEntity(xml, "utf-8");
            post.setEntity(entity);
            response = httpClient.execute(post);
            Header[] headers = response.getHeaders("Digest");

            result = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }
        return result;
    }

    /**
     * 获取请求的ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}