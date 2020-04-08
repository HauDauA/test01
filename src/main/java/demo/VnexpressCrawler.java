package demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class VnexpressCrawler extends Thread implements HelloCrawler {
    private String url;

    public VnexpressCrawler() {
    }

    public VnexpressCrawler(String url) {
        this.url = url;
    }
    @Override
    public void run() {
        System.out.println("Đang lấy tin, vui lòng chờ");
        Article article = null;
        try {
            article = new Article();
            article.setUrl(url);
            Document document = Jsoup.connect(url).get();
            article.setTitle(document.select("h1.title_news_detail").text());
            article.setDescription(document.select("p.descriptiom").text());
            article.setContent(document.select("article.content_detail").text());
            Elements els = document.select("article img");
            if (els.size() > 0) {
                article.setThumbnail(els.get(0).attr("src"));
                System.out.println("Lấy tin thành công");
                System.out.println(article.toString());

            }

        } catch (Exception e) {
            article = null;
            System.err.println(String.format("Lỗi khi lấy dữ liệu từ link: %s", e.getMessage()));

        }

    }

    public Article crawl(String url) {
        System.out.println("Đang lấy tin vui lòng chờ");
        Article article = null;
        try {
            article = new Article();
            article.setUrl(url);
            Document document = Jsoup.connect(url).get();
            article.setTitle(document.select("h1.title_news_detail").text());
            article.setDescription(document.select("p.description").text());
            article.setContent(document.select("article.content_detail").text());
            Elements els = document.select("article img");
            if (els.size() > 0) {
                article.setThumbnail(els.get(0).attr("src"));
            }
            System.out.println("Lấy tin thành công!");


        } catch (Exception e) {
            article = null;
            System.err.println(String.format("Lỗi khi lấy dữ liệu từ link: %s", e.getMessage()));
        }
        return article;
    }
    public Article crawler() {
        return null;
    }
    public ArrayList<String> getLink() {
        return null;
    }
}
