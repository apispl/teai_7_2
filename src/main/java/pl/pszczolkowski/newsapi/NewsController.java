package pl.pszczolkowski.newsapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pszczolkowski.newsapi.dao.NewsDao;
import pl.pszczolkowski.newsapi.model.ArticleCuted;
import java.util.List;


@RestController
@CrossOrigin("*")
public class NewsController {

    private NewsDao newsDao;
    private List<ArticleCuted> listToDisplay;

    @Autowired
    public NewsController(NewsDao newsDao) {
        this.newsDao = newsDao;
        listToDisplay = newsDao.getArticlesDb();
        newsDao.saveNewsDb();
    }

    @GetMapping("/fillDatabase")
    public String fillDatabase(){
        listToDisplay = newsDao.getArticlesDb();
        newsDao.saveNewsDb();
        return "SAVED";
    }

    @GetMapping
    public List<ArticleCuted> getArticlesWb(){
        return listToDisplay;
    }
}
