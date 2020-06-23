package pl.pszczolkowski.newsapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DdConfiguration {

    private DataSource dataSource;

    @Autowired
    public DdConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JdbcTemplate getJdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("USE mojabaza");
        return jdbcTemplate;
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void createNewsTable(){
//        getJdbcTemplate().update("CREATE table news_api(news_id int NOT NULL AUTO_INCREMENT, title varchar(500), description varchar(1000), url varchar(255), url_image varchar(255), publishedAt varchar(100), content varchar(3000), PRIMARY KEY (news_id))");
//    }
}
