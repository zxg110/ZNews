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

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
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
