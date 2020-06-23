package pl.pszczolkowski.newsapi.model;

public class ArticleCuted {
    int id;
    String title;
    String description;
    String url;
    String urlImage;
    String publishedAt;
    String content;

    public ArticleCuted(int id, String title, String description, String url, String urlImage, String publishedAt, String content) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlImage = urlImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
