package com.springapp.gui;

import com.springapp.adapterPattern.MikeLogging;
import com.springapp.adapterPattern.MikeLoggingAdapter;
import com.springapp.article.Article;
import com.springapp.article.dao.ArticleDaoImpl;
import com.springapp.author.Author;
import com.springapp.author.dao.AuthorDaoImpl;
import com.springapp.calculators;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main extends JFrame {

    public double NEWwords;
    public double NEWprepos;
    public double NEWsentences;

    public double getNEWwords() {
        return NEWwords;
    }

    public void setNEWwords(double NEWwords) {
        this.NEWwords = NEWwords;
    }

    public double getNEWprepos() {
        return NEWprepos;
    }

    public void setNEWprepos(double NEWprepos) {
        this.NEWprepos = NEWprepos;
    }

    public double getNEWsentences() {
        return NEWsentences;
    }

    public void setNEWsentences(double NEWsentences) {
        this.NEWsentences = NEWsentences;
    }

    public void calculate_param (String string)
    {
        int wordCount = 0;
        boolean word = false;
        int endOfLine = string.length() - 1;
        for (int i = 0; i < string.length(); i++) {
            if (Character.isLetter(string.charAt(i)) && i != endOfLine) {
                word = true;
            } else if (!Character.isLetter(string.charAt(i)) && word) {
                wordCount++;
                word = false;
            } else if (Character.isLetter(string.charAt(i)) && i == endOfLine) {
                wordCount++;
            }
        }
        setNEWwords(wordCount);
        int count = string.split("[!?.:]+").length;
        setNEWsentences(count);
        setNEWprepos(countPrepositions(string));
    }
    public static int countPrepositions(String input) {
        int total = 0;
        Matcher matches = Pattern.compile("((?i)of|until|about|at|in)").matcher(input);
        while (matches.find()) {
            total++;
        }
        return total;}


    private JTable table;
    private DefaultTableModel tableModel;
    ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
    AuthorDaoImpl dao = ctx.getBean("AuthorDaoImpl", AuthorDaoImpl.class);

    private main() {
        createGUI();
    }
    private void articleFrame(){
        JFrame frame = new JFrame("Articles");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JScrollPane pane = new JScrollPane();
        table = new JTable();
        pane.setViewportView(table);
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        JButton btnAllArticles = new JButton("Show all Articles");
        JButton btnCalcParams = new JButton("Calc Params");
        eastPanel.add(btnAllArticles);
        eastPanel.add(btnCalcParams);
        JPanel northPanel = new JPanel();
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(eastPanel, BorderLayout.EAST);
        frame.add(pane, BorderLayout.CENTER);
        tableModel = new DefaultTableModel(new Object[]{"Article ID", "content", "words", "preposition", "sentences"},0);
        table.setModel(tableModel);

        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        btnAllArticles.addActionListener(e -> {
            ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
            ArticleDaoImpl dao = ctx.getBean("ArticleDaoImpl", ArticleDaoImpl.class);

            List<Article> k = dao.getAllArticles();
            tableModel.setRowCount(0);
            for (Article f : k) {
                tableModel.addRow(new Object[]{f.getId(), f.getContent(), f.getWords(),
                        f.getPrepos(), f.getSentences()});

            }
        });
        btnCalcParams.addActionListener(e -> {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
                ArticleDaoImpl dao = ctx.getBean("ArticleDaoImpl", ArticleDaoImpl.class);
                List<Article> k = dao.getAllArticles();
                for (Article f : k) {
                    calculate_param(f.getContent());
                //TODO Transact script
                    dao.calcParams(f.getId(), getNEWwords(), getNEWprepos(), getNEWsentences());
                }
        });

    }
    private void removeSelectedRows(JTable table){
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        int[] rows = table.getSelectedRows();
        for(int i=0;i<rows.length;i++){
            model.removeRow(rows[i]-i);
        }
    }
    private void crudFrame() {

        JFrame frame = new JFrame("Authors");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JScrollPane pane = new JScrollPane();
        table = new JTable();
        pane.setViewportView(table);
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        JButton btnAllAuthors = new JButton("Show all authors");
        JButton btnDeleteAuthors = new JButton("Delete author");
        JButton btnSearchAuthors = new JButton("Search author");
        JButton btnAddAuthors = new JButton("Add author");
        JButton btnUpdateAuthors = new JButton("Update authors");
        JButton btnClearTable = new JButton("Clear table");
        JButton btnCalcAVGparams = new JButton("Calc AVG params");

        JTextField textSearch = new JTextField();
        textSearch.getPreferredSize();

        eastPanel.add(btnAllAuthors);
        eastPanel.add(btnDeleteAuthors);
        eastPanel.add(btnAddAuthors);
        eastPanel.add(btnSearchAuthors);
        eastPanel.add(btnUpdateAuthors);
        eastPanel.add(btnClearTable);
        eastPanel.add(btnCalcAVGparams);
        eastPanel.add(textSearch);
        JPanel northPanel = new JPanel();

        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(eastPanel, BorderLayout.EAST);
        frame.add(pane, BorderLayout.CENTER);
        tableModel = new DefaultTableModel(new Object[]{"id","first name", "last name", "nationality"},0);
        table.setModel(tableModel);
        tableModel.getColumnName(1);
        frame.setLocationByPlatform(true);

        frame.pack();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        btnCalcAVGparams.addActionListener(e ->  {
                ArticleDaoImpl daoArticle = ctx.getBean("ArticleDaoImpl", ArticleDaoImpl.class);
                double k[][] = daoArticle.getParams();
                System.out.println(k.length);
//            for(int i = 0; i < k.length; i++)
//            {
//                dao.updateAuthorsAVG((int) k[i][0], k[i][3], k[i][2], k[i][1]);
//            }
            for (double[] aK : k) {
                dao.updateAuthorsAVG((int) aK[0], aK[3], aK[2], aK[1]);
            }

        });
        btnUpdateAuthors.addActionListener(e ->  {
                System.out.println(table.getRowCount());
                for (int x = 0; x < table.getRowCount(); x += 1){
                    System.out.println(table.getValueAt(x, 1) + " " + table.getValueAt(x, 2) + " "
                            + table.getValueAt(x, 3));
                    dao.updateAuthors((Integer) table.getValueAt(x, 0),
                            table.getValueAt(x, 1).toString(),
                            table.getValueAt(x, 2).toString(),
                            table.getValueAt(x, 3).toString());
            }
        });
        btnDeleteAuthors.addActionListener(e ->  {
                dao.deleteAuthorById((Integer) table.getValueAt(table.getSelectedRow(), 0));
                removeSelectedRows(table);
        });

        btnAddAuthors.addActionListener(e ->  {
                Author temp = new Author();
                temp.setFirstname(tableModel.getValueAt(0, 1).toString());
                temp.setLastname(tableModel.getValueAt(0, 2).toString());
                temp.setNationality(tableModel.getValueAt(0, 3).toString());
                dao.create(temp);

        });

        btnClearTable.addActionListener(e ->  {
                tableModel.setRowCount(1);
                tableModel.setValueAt("", 0, 0);
                tableModel.setValueAt("", 0, 1);
                tableModel.setValueAt("", 0, 2);


        });
        btnAllAuthors.addActionListener( e ->  {
                List<Author> k = dao.getAllAuthors();
                tableModel.setRowCount(0);
                k.forEach(f -> tableModel.addRow(new Object[]{f.getId(), f.getFirstname(), f.getLastname(), f.getNationality()}));
        });

        btnSearchAuthors.addActionListener(e ->  {
                tableModel.setRowCount(0);
//                List<Author> k = dao.getAuthorByLastName(textSearch.getText());
        });
    }
    private void createGUI() {
        JPanel westPanel = new JPanel();
        JPanel centralPanel = new JPanel();
        JScrollPane pane = new JScrollPane();
        JButton btnAuthorsCRUD = new JButton("Authors CRUD");
        JButton btnTexts = new JButton("Articles");
        JButton btnRecognize = new JButton("Recognize author by piece of text");
        JButton btnReminder = new JButton("Days before deadline");
        JTextArea display = new JTextArea(16, 58);
        JScrollPane scroll = new JScrollPane(display);
        table = new JTable();


        setLayout(new BorderLayout());
        pane.setViewportView(table);
        centralPanel.setBorder(new TitledBorder(new EtchedBorder(), "Piece of text here"));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        westPanel.add(btnAuthorsCRUD);
        westPanel.add(btnTexts);
        westPanel.add(btnRecognize);
        westPanel.add(btnReminder);
        centralPanel.add(scroll);

        add(westPanel, BorderLayout.WEST);
        add(centralPanel, BorderLayout.CENTER);
        tableModel = new DefaultTableModel(new Object[]{"id","first name","last name"},0);
        table.setModel(tableModel);
        btnReminder.addActionListener(e -> {
            MikeLogging mikeLogging = new MikeLogging();
            MikeLoggingAdapter mikeLoggingAdapter = new MikeLoggingAdapter(mikeLogging);
            mikeLoggingAdapter.store_info();
        });

        btnAuthorsCRUD.addActionListener(e -> crudFrame());
        btnTexts.addActionListener(e -> articleFrame());

        btnRecognize.addActionListener(e -> {
            String temp = "";
            calculators param = new calculators();
            param.calculate_param(display.getText());
            System.out.println("param words: " + param.getWords());
            List <Author> authors = dao.getAllAuthors();
            for (Author f: authors){
                if (f.getAvg_words() <= param.getWords() &&
                        (f.getAvg_words()*4 >= param.getWords())
                        && f.getAvg_words() != 0
                        ){
                    System.out.println(" avg words" + f.getAvg_words() +
                            " avg prepo " + f.getAvg_prepos() +
                            " avg sent" + f.getAvg_sentences());
                    temp += f.getFirstname() + " " + f.getLastname() + " ";
                }
            }
            if (temp.length() == 0) {
                temp = " - ";
            }
            String textpane = "Possible authors is: " + temp;
            JOptionPane.showMessageDialog(null, textpane);

        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            main frm = new main();
            frm.setLocationByPlatform(true);
            frm.pack();
//            frm.setDefaultCloseOperation();
            frm.setVisible(true);
        });
    }
}
