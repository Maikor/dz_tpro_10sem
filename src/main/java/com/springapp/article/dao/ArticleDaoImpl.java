package com.springapp.article.dao;

import com.springapp.article.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mkorshun on 4/13/2015.
 */
@Repository
public class ArticleDaoImpl implements ArticleDao {


    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Article> getAllArticles() {

        String sql = " select * from Articles";
//        ArticleRowMapper k = new ArticleRowMapperAdapter();

        return jdbcTemplate.query(sql, new ArticleRowMapper());
    }
    public double[][] getParams() {
        String sql = "SELECT id_author, SUM(words)/COUNT(*) as \"words\", SUM(prepos)/COUNT(*) as \"prepos\"," +
                " SUM(sentences)/COUNT(*) as \"sentences\" FROM articles GROUP BY id_author";
        List<Article> articles = new ArrayList<Article>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        int i = rows.size();
        double arr[][] = new double[i][4];
        int l = 0;
        for (Map row : rows){
            Article article = new Article();
            System.out.println(row.get("sentences"));
            System.out.println(row.get("prepos"));
            arr[l][0] =(Integer) row.get("id_author");
            arr[l][1] =Double.valueOf(row.get("sentences").toString());
            arr[l][2] =Double.valueOf(row.get("prepos").toString());
            arr [l][3] = Double.valueOf(row.get("words").toString());
            l ++;
//            Double s = Double.valueOf(row.get("sentences").toString());
//            article.setId_author((Integer) row.get("id_author"));
//            article.setSentences(Double.valueOf(row.get("sentences").toString()));
//            article.setPrepos((Double.valueOf(row.get("prepos").toString())));
//            article.setWords((Double.valueOf(row.get("words").toString())));
        }
//        return jdbcTemplate.queryForObject(sql, new Article[]);
        return arr;
        }


    public void calcParams (Integer id, double words, double prepos, double sentences) {
//        String sql = "insert into articles (words, prepos, sentences) values (?, ?, ?) where id = ?";
        String sql = "update articles set words=?, prepos=?, sentences=? where id = ?";
        jdbcTemplate.update(sql, words, prepos, sentences, id );
        System.out.println("updated record: " +
                id +
                words +
                prepos + sentences );
    }

    }


