package com.example.daochang;

public class Post {
    private int id;
    private String content;
    private String portrait;
    private String name;
    private int praise;
    private int discouragement;
    private String title;
    private String images;
    private String publishDate;
    private int postId;
    private String recentAnswerDate;
    private int answerCount;
    private boolean is_praise;
    private boolean is_discouragement;
    private boolean is_favorite;

    public Post(int id, String content, String portrait, String name, int praise, int discouragement, String title, String images, String publishDate, int postId, String recentAnswerDate, int answerCount, boolean is_praise, boolean is_discouragement, boolean is_favorite) {
        this.id = id;
        this.content = content;
        this.portrait = portrait;
        this.name = name;
        this.praise = praise;
        this.discouragement = discouragement;
        this.title = title;
        this.images = images;
        this.publishDate = publishDate;
        this.postId = postId;
        this.recentAnswerDate = recentAnswerDate;
        this.answerCount = answerCount;
        this.is_praise = is_praise;
        this.is_discouragement = is_discouragement;
        this.is_favorite = is_favorite;
    }

    public int getID() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getPortrait() {
        return portrait;
    }

    public String getName() {
        return name;
    }

    public int getPraise() {
        return praise;
    }

    public int getDiscouragement() {
        return discouragement;
    }

    public String getTitle() {
        return title;
    }

    public String getImages() {
        return images;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public int getPostId() {
        return postId;
    }

    public String getRecentAnswerDate() {
        return recentAnswerDate;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public boolean isIs_praise() {
        return is_praise;
    }

    public boolean isIs_discouragement() {
        return is_discouragement;
    }

    public boolean isIs_favorite() {
        return is_favorite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public void setDiscouragement(int discouragement) {
        this.discouragement = discouragement;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setRecentAnswerDate(String recentAnswerDate) {
        this.recentAnswerDate = recentAnswerDate;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public void setIs_praise(boolean is_praise) {
        this.is_praise = is_praise;
    }

    public void setIs_discouragement(boolean is_discouragement) {
        this.is_discouragement = is_discouragement;
    }

    public void setIs_favorite(boolean is_favorite) {
        this.is_favorite = is_favorite;
    }
}

