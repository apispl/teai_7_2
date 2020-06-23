package pl.pszczolkowski.newsapi.client;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.pszczolkowski.newsapi.model.Article;
import pl.pszczolkowski.newsapi.model.Example;


import java.util.List;
import java.util.Objects;

@RestController
public class NewsClient {

    private RestTemplate restTemplate = new RestTemplate();

    public List<Article> getArticleList(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        String baseUrl = "https://newsapi.org/v2/top-headlines";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("country", "pl")
                .queryParam("apiKey","6f3fc1bd6f5a4d9abf6fe0957b120e5b")
                .queryParam("pageSize", "10");

        HttpEntity<Example> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Example.class
        );

        return Objects.requireNonNull(response.getBody()).getArticles();
    }


}
