package com.nzgreens.common.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author:helizheng
 * @Date: Created in 2017/8/20 20:29
 */
@Configuration
@PropertySource(value="classpath:properties/pay.properties")
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
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getQueryUrl() {
        return queryUrl;
    }

    public void setQueryUrl(String queryUrl) {
        this.queryUrl = queryUrl;
    }

    public String getCertificateUrl() {
        return certificateUrl;
    }

    public void setCertificateUrl(String certificateUrl) {
        this.certificateUrl = certificateUrl;
    }

    public String getCertificateTestUrl() {
        return certificateTestUrl;
    }

    public void setCertificateTestUrl(String certificateTestUrl) {
        this.certificateTestUrl = certificateTestUrl;
    }
}
