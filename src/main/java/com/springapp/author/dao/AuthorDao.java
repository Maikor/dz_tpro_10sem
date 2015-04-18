package com.springapp.author.dao;

import com.springapp.author.Author;

import java.util.List;

/**
 * Created by mkorshun on 4/8/2015.
 */
public interface AuthorDao {

    void create (Author author);
    void deleteAuthorById (Integer id);
    Author getAuthorById (Integer id);
    void updateAuthors (Integer id, String firstname, String lastname, String nationality);
    void updateAuthorsAVG (Integer id, double avg_words, double avg_prepos, double avg_sentences);
    List<Author> getAuthorByLastName(String lastname);
    List<Author> getAllAuthors();

}
