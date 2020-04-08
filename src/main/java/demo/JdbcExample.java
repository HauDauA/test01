package demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class JdbcExample {
    public static final String DATABASE_URL = "jdbc:mysql://localhost/t1908m_demo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=utf-8";
    public static final String DATABASE_USER = "root";
    public static final String DATABASE_PWD = "";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Article> articlesArrayList = loadArticles();

        if (articlesArrayList.size() == 0) {
            System.out.println("hiện tại không có bản ghi nào");
            return;

        }
        printListArticle(articlesArrayList);
        System.out.println("Nhập stt tin bạn muốn xem");
        int index = new Scanner(System.in).nextInt();
        Article article = articlesArrayList.get(index - 1);
        Article fullArticle = loadArticleDetail(article.getUrl());
        if (fullArticle == null) {
            System.out.println("tin này đã bị xóa");
            return;
        }
        System.out.println(fullArticle.getTitle());
        System.out.println(fullArticle.getContent());
    }

    private static void printListArticle(ArrayList<Article> articlesArrayList) {
        System.out.println("Danh sách bài viết");
        System.out.println("------------------------------");
        for (int i = 0; i < articlesArrayList.size(); i++) {
            System.out.println(String.format("%d-%s", i + 1, articlesArrayList.get(i).getTitle()));

        }
    }

    public static Article loadArticleDetail(String url) {
        try {
            Connection cnn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PWD);
            String cmd = "select* from  articles where url=?";
            PreparedStatement preparedStatement = cnn.prepareStatement(cmd);
            preparedStatement.setString(1, url);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String content = resultSet.getString("content");
                String thumnail = resultSet.getString("thumnail");
                Article article = new Article(url, title, description, thumnail, content);
                return article;
            }
        } catch (Exception ex) {
            System.err.println("ko thể lấy dữ liệu"+ex.getMessage());
        }
        return null;
    }

    public static ArrayList<Article> loadArticles() {
        ArrayList<Article> articleArrayList = new ArrayList<Article>();
        try {
            Connection cnn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PWD);
            String cmd = "select url, title from articles limit 10";
            PreparedStatement preparedStatement = cnn.prepareStatement(cmd);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String url = resultSet.getString("url");
                String title = resultSet.getString("title");
                Article article = new Article(url, title);
                articleArrayList.add(article);

            }
        } catch (Exception ex) {
            System.err.println("Khong thể lấy dữ liệu"+ex.getMessage());

        }
        return articleArrayList;


    }

    //        VnexpressCrawler vnexpressCrawler=new VnexpressCrawler();
//        Article article= vnexpressCrawler.crawl("https://vnexpress.net/suc-khoe/so-ca-nhiem-ncov-len-222-4078321.html");
//        saveArticle(article);
//        saveArticleVerson2(article);
    public static void saveArticle(Article article) {
        try {
            Connection cnn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PWD);
            Statement stt = cnn.createStatement();
            String sqlcmd = String.format("insert into articles (url,title,description,content,thumnail)values ('%s','%s','%s','%s','%s')",
                    article.getUrl(), article.getTitle(), article.getContent(), article.getDescription(), article.getThumbnail());
            stt.execute(sqlcmd);
            System.out.println("access success");
        } catch (SQLException e) {
            System.out.println("lỗi kết nối database");
            e.printStackTrace();
        }
    }
    public static void saveArticleVerson2(Article article) {
        try {

            Connection cnn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PWD);
            Statement stt = cnn.createStatement();
            PreparedStatement preparedStatement =
                    cnn.prepareStatement("insert into articles (url,title,description,content,thumnail)values (?,?,?,?,?)");
            preparedStatement.setString(1, article.getUrl());
            preparedStatement.setString(2, article.getTitle());
            preparedStatement.setString(3, article.getDescription());
            preparedStatement.setString(4, article.getContent());
            preparedStatement.setString(5, article.getThumbnail());
            preparedStatement.execute();
            System.out.println("access success");
        } catch (SQLException e) {
            System.out.println("lỗi kết nối database");

        }

    }


}



