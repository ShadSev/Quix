package se.quiz;


import java.sql.Date;

/**
 * Created by Administrator on 2016-09-28.
 */
public class Quiz {
    public int quizID;
    public String title;
    public String descr;
    public Date createdDate;

    public Quiz(int quizID, String title, String descr, Date createdDate){
        this.quizID=quizID;
        this.title=title;
        this.descr=descr;
        this.createdDate=createdDate;
    }
}
