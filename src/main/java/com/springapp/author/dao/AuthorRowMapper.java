package com.springapp.author.dao;

/**
 * Created by mkorshun on 4/8/2015.
 */

import com.springapp.author.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AuthorRowMapper implements RowMapper<Author> {


    @Override
    public Author mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getInt("id"));
        author.setFirstname(resultSet.getString("firstname"));
        author.setLastname(resultSet.getString("lastname"));
        author.setNationality(resultSet.getString("nationality"));
        author.setAvg_words(resultSet.getDouble("avg_words"));
        author.setAvg_prepos(resultSet.getDouble("avg_prepos"));
        author.setAvg_sentences(resultSet.getDouble("avg_sentences"));

        return author;
    }
}
