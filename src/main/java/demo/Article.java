package demo;

public class Article {
    String url;
    String description;
    String title;
    String content;
    String thumbnail;

    public Article() {
    }

    public Article(String url, String description, String title, String content, String thumbnail) {
        this.url = url;
        this.description = description;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
    }

    public Article(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", Thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
