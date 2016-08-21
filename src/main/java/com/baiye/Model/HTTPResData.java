package com.baiye.Model;

/**
 * Created by Baiye on 8/21/16.
 */
public class HTTPResData {


    public HTTPResData() {
    }

    public HTTPResData(String head, String content, int contentLength)
    {
        this.head = head;
        this.content = content;
        this.contentLength = contentLength;
    }

    private String head;
    private String content;
    private int contentLength;


    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }
}
