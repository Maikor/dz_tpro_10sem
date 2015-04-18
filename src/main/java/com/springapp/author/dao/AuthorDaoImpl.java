package com.springapp.author.dao;

import com.springapp.author.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkorshun on 4/8/2015.
 */
@Repository
public class AuthorDaoImpl implements AuthorDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void create(Author author) {
        String sql = "insert into authors (firstname, lastname, nationality) values (?, ?, ?)";
        jdbcTemplate.update(sql, author.getFirstname(), author.getLastname(), author.getNationality());

        System.out.println("created record: " +
                author.getFirstname() +
                author.getLastname() +
                author.getNationality() );
    }

    public void updateAuthors (Integer id, String firstname, String lastname, String nationality) {
        String sql = "update authors set firstname=?, lastname=?, nationality=? where id=? ";
        jdbcTemplate.update(sql, firstname, lastname, nationality, id);
    }
    public void updateAuthorsAVG (Integer id, double avg_words, double avg_prepos, double avg_sentences){
        String sql = "update authors set avg_words=?, avg_prepos=?, avg_sentences=? where id=?";
        jdbcTemplate.update(sql, id, avg_words, avg_prepos, avg_sentences);

    }

    @Override
    public Author getAuthorById(Integer id) {
        String sql = "select * from authors where id = ?";
        Author author = jdbcTemplate.queryForObject(sql, new Object[]{id}, new AuthorRowMapper());
        return author;
    }
    @Override
    public void deleteAuthorById(Integer id) {
        System.out.println("row is deleted with ID " + id);
        String sql = "delete from authors where id = ?";
        jdbcTemplate.update(sql, new Object[] {id});
    }

    public List<Author> getAuthorByLastName(String lastname) {
        String sql = "select * from authors where lastname = ?";
//        System.out.println(author.getFirstname() + "  " + author.getLastname());
        return jdbcTemplate.query(sql, new Object[]{lastname}, new AuthorRowMapper());
    }



    public List<Author> getAllAuthors() {
        String sql = " select * from authors";
        return jdbcTemplate.query(sql, new AuthorRowMapper());
    }


}
