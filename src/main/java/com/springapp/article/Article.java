package com.springapp.article;

/**
 * Created by mkorshun on 4/8/2015.
 */
public class Article {


    private int id;
    private String content;
    private int id_author;
    private double words;
    private double prepos;
    private double sentences;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public double getWords() {
        return words;
    }

    public void setWords(double words) {
        this.words = words;
    }

    public double getPrepos() {
        return prepos;
    }

    public void setPrepos(double prepos) {
        this.prepos = prepos;
    }

    public double getSentences() {
        return sentences;
    }

    public void setSentences(double sentences) {
        this.sentences = sentences;
    }
}
