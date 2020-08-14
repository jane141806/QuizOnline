/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangnth.dtos;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author Dell
 */
public class QuizDetailDTO {
    private String email , idSubject ;
    private double score ;
    private String doingDate ;
    private int idQuiz ;

    public QuizDetailDTO() {
    }
    

    public QuizDetailDTO(String email, int idQuiz, String idSubject, double score,Timestamp doingDate) {
        this.email = email;
        this.idQuiz = idQuiz;
        this.idSubject = idSubject;
        this.score = score;
        this.doingDate = doingDate.toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(String idSubject) {
        this.idSubject = idSubject;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }


    public String getDoingDate() {
        return doingDate;
    }

    public void setDoingDate(String doingDate) {
        this.doingDate = doingDate;
    }

    
    public String toString(Timestamp date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(date);
    }
    
    
}
