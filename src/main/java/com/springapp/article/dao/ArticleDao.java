package com.springapp.article.dao;

import com.springapp.article.Article;

import java.util.List;


/**
 * Created by mkorshun on 4/13/2015.
 */
public interface ArticleDao {
    public double[][] getParams();
    List<Article> getAllArticles();
    public void calcParams (Integer id, double words, double prepos, double sentences);
}
