package pl.pszczolkowski.newsapi.dao;


import pl.pszczolkowski.newsapi.model.Article;
import pl.pszczolkowski.newsapi.model.ArticleCuted;

import java.util.List;

public interface NewsDao {
    List<ArticleCuted> getArticlesDb();
    void saveNewsDb();
//    void editNews(Article article);
}
