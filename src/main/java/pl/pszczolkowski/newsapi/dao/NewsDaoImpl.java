package pl.pszczolkowski.newsapi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pl.pszczolkowski.newsapi.client.NewsClient;
import pl.pszczolkowski.newsapi.model.Article;
import pl.pszczolkowski.newsapi.model.ArticleCuted;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Repository
public class NewsDaoImpl implements NewsDao {

    private JdbcTemplate jdbcTemplate;
    private NewsClient newsClient;
    private List<ArticleCuted> articleList = new ArrayList<>();

    @Autowired
    public NewsDaoImpl(JdbcTemplate jdbcTemplate, NewsClient newsClient) {
        this.jdbcTemplate = jdbcTemplate;
        this.newsClient = newsClient;
    }

    @Override
    public List<ArticleCuted> getArticlesDb() throws NullPointerException {
        jdbcTemplate.update("USE mojabaza");
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM mojabaza.news_api");

        maps.forEach(ar -> articleList.add(new ArticleCuted(
                Integer.parseInt(String.valueOf(ar.get("news_id"))),
                String.valueOf(ar.get("title")),
                String.valueOf(ar.get("description")),
                String.valueOf(ar.get("url")),
                String.valueOf(ar.get("url_image")),
                String.valueOf(ar.get("publishedAt")),
                String.valueOf(ar.get("content")))));

        System.out.println(articleList.size());
        return articleList;
    }


    @Override
    public void saveNewsDb() {
        List<Article> articleListClient = newsClient.getArticleList();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        System.out.println("Articles: " + articleListClient.size());
        String sql = "INSERT INTO news_api VALUES(?,?, ?, ?, ?, ?, ?)";
        articleListClient.forEach(ar -> {
            jdbcTemplate.update("USE mojabaza");
            jdbcTemplate.update(sql,
                    keyHolder.getKey(),
                    ar.getTitle(),
                    ar.getDescription(),
                    ar.getUrl(),
                    ar.getUrlToImage(),
                    ar.getPublishedAt(),
                    ar.getContent());
        });
    }

//    @Override
//    public void editNews(Article article) {
//        String sql = "UPDATE news_api SET news_api.title=?, news_api.description=?, news_api.publishedAt=?, news_api.content=?, WHERE news_api.news_id=?";
//        jdbcTemplate.update(sql, article.getTitle(), article.getDescription(),article.getPublishedAt(),article.getContent(),article.getNews_id());
//    }
}
