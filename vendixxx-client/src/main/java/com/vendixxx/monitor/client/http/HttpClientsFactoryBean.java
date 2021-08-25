package com.vendixxx.monitor.client.http;

import com.vendixxx.monitor.common.config.MonitorCommonProperties;
import com.vendixxx.monitor.common.config.MonitorHttpProperties;
import com.vendixxx.monitor.registry.listener.BeanInit;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 *
 *  HTTPClient的工厂类。 可以接收https请求
 *
 *  http clients support http
 */
@Slf4j
public class HttpClientsFactoryBean implements BeanInit {

    public static volatile CloseableHttpClient closeableHttpClient;

    @Override
    public void processBeforeInitialization(MonitorCommonProperties monitorCommonProperties) throws Exception {

    }

    @Override
    public Class<CloseableHttpClient> getClassType() {
        return null;
    }

    @Override
    public void init(MonitorCommonProperties monitorCommonProperties,String ... packages) throws Exception {
        MonitorHttpProperties monitorHttpProperties =monitorCommonProperties.getMonitorHttpProperties();
        if (closeableHttpClient == null) {
            synchronized (HttpClientsFactoryBean.class) {
                if (closeableHttpClient == null) {
                    HttpClientBuilder b = HttpClientBuilder.create();
                    // setup a Trust Strategy that allows all certificates.
                    SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null,
                            new TrustStrategy() {
                                @Override
                                public boolean isTrusted(X509Certificate[] arg0, String arg1)
                                        throws CertificateException {
                                    return true;
                                }
                            }).build();
                    b.setSSLContext(sslContext);

                    // don't check Hostnames, either.
                    // -- use SSLConnectionSocketFactory.getDefaultHostnameVerifier(), if
                    // you don't want to weaken
                    HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;

                    // here's the special part:
                    // -- need to create an SSL Socket Factory, to use our weakened
                    // "trust strategy";
                    // -- and create a Registry, to register it.
                    //
                    SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(
                            sslContext, hostnameVerifier);
                    Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                            .<ConnectionSocketFactory> create()
                            .register("com/vendixxx/monitor/client/http",
                                    PlainConnectionSocketFactory.getSocketFactory())
                            .register("https", sslSocketFactory).build();

                    // now, we create connection-manager using our Registry.
                    // -- allows multi-threaded use
                    PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(
                            socketFactoryRegistry);
                    connMgr.setMaxTotal(monitorHttpProperties.getMaxTotal());
                    connMgr.setDefaultMaxPerRoute(monitorHttpProperties.getDefaultMaxPerRoute());
                    b.setConnectionManager(connMgr);

                    //request config
                    RequestConfig requestConfig = RequestConfig.custom()
                            .setConnectionRequestTimeout(monitorHttpProperties.getConnectionRequestTimeout())
                            .setConnectTimeout(monitorHttpProperties.getConnectionTimeout())
                            .setSocketTimeout(monitorHttpProperties.getSocketTimeout())
                            .build();
                    b.setDefaultRequestConfig(requestConfig);

                    // finally, build the HttpClient;
                    // -- done!
                    closeableHttpClient = b.build();
                }
            }
        }
        return;
    }

    @Override
    public void procesAfterInitialization(MonitorCommonProperties monitorCommonProperties) throws Exception {

    }

    @Override
    public void destroy() throws Exception {

    }

}