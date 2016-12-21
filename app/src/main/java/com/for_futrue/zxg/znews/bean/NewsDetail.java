package com.for_futrue.zxg.znews.bean;

import java.util.List;

/**
 * Description : 新闻详情实体类
 * Created by zxg on 2016/12/21.
 */
public class NewsDetail {
    /**
     * docid
     */
    private String docid;
    /**
     * title
     */
    private String title;
    /**
     * source
     */
    private String source;
    /**
     * body
     */
    private String body;
    /**
     * ptime
     */
    private String ptime;
    /**
     * cover
     */
    private String cover;
    /**
     * 图片列表
     */
    private List<String> imgList;

    /**
     * 分享链接
     */
    private String shareLink;

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    @Override
    public String toString() {
        return "NewsDetail{" +
                "docid='" + docid + '\'' +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", body='" + body + '\'' +
                ", ptime='" + ptime + '\'' +
                ", cover='" + cover + '\'' +
                ", imgList=" + imgList +
                ", shareLink='" + shareLink + '\'' +
                '}';
    }
}
