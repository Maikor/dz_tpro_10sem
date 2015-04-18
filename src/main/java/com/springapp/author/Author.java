package com.springapp.author;

/**
 * Created by mkorshun on 4/8/2015.
 */
public class Author {

    private int id;
    private String firstname;
    private String lastname;
    private String nationality;
    private double avg_words;
    private double avg_prepos;
    private double avg_sentences;


    public double getAvg_words() {
        return avg_words;
    }

    public void setAvg_words(double avg_words) {
        this.avg_words = avg_words;
    }

    public double getAvg_prepos() {
        return avg_prepos;
    }

    public void setAvg_prepos(double avg_prepos) {
        this.avg_prepos = avg_prepos;
    }

    public double getAvg_sentences() {
        return avg_sentences;
    }

    public void setAvg_sentences(double avg_sentences) {
        this.avg_sentences = avg_sentences;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
