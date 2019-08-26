package com.nzgreens.common.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:properties/pay.properties"})
public class PayConfig {
    @Value("${wx.pay.appId:}")
    private String appId;
    @Value("${wx.pay.mchId:}")
    private String mchId;
    @Value("${wx.pay.key:}")
    private String key;
    @Value("${wx.pay.url:}")
    private String url;
    @Value("${wx.pay.notify:}")
    private String notify;
    @Value("${wx.pay.queryUrl:}")
    private String queryUrl;
    @Value("${ios.pay.certificate.url:}")
    private String certificateUrl;
    @Value("${ios.pay.certificate.test.url:}")
    private String certificateTestUrl;

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    public String getMchId()
    {
        return this.mchId;
    }

    public void setMchId(String mchId)
    {
        this.mchId = mchId;
    }

    public String getKey()
    {
        return this.key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getUrl()
    {
        return this.url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getNotify()
    {
        return this.notify;
    }

    public void setNotify(String notify)
    {
        this.notify = notify;
    }

    public String getQueryUrl()
    {
        return this.queryUrl;
    }

    public void setQueryUrl(String queryUrl)
    {
        this.queryUrl = queryUrl;
    }

    public String getCertificateUrl()
    {
        return this.certificateUrl;
    }

    public void setCertificateUrl(String certificateUrl)
    {
        this.certificateUrl = certificateUrl;
    }

    public String getCertificateTestUrl()
    {
        return this.certificateTestUrl;
    }

    public void setCertificateTestUrl(String certificateTestUrl)
    {
        this.certificateTestUrl = certificateTestUrl;
    }
}
