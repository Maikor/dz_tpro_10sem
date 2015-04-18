package com.springapp.article.dao;

import com.springapp.article.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mkorshun on 4/18/2015.
 */
public interface ArticleRowMapperAdapter extends RowMapper<Article> {

    public Article mapRow(ResultSet resultSet, int rowNum) throws SQLException;
}
