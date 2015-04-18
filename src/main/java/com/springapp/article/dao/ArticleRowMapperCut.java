package com.springapp.article.dao;

import com.springapp.article.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mkorshun on 4/13/2015.
 */
public class ArticleRowMapperCut implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Article article = new Article();

        article.setId_author(resultSet.getInt("id_author"));
        article.setPrepos(resultSet.getDouble("prepos"));
        article.setWords(resultSet.getDouble("words"));
        article.setSentences(resultSet.getDouble("sentences"));

        return article;
    }
}
