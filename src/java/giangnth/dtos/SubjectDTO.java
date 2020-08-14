/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangnth.dtos;

import java.sql.Time;

/**
 *
 * @author Dell
 */
public class SubjectDTO {
    private String idSubject;
    private int numQuestion ;
    private Time time ;

    public SubjectDTO() {
    }

    public SubjectDTO(String idSubject,int numQuestion, Time time) {
        this.idSubject = idSubject;
        this.numQuestion = numQuestion;
        this.time = time;
    }

    public String getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(String idSubject) {
        this.idSubject = idSubject;
    }

    public int getNumQuestion() {
        return numQuestion;
    }

    public void setNumQuestion(int numQuestion) {
        this.numQuestion = numQuestion;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
    
    
}
