/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangnth.dtos;

/**
 *
 * @author Dell
 */
public class QuestionDTO {
    private String idQuestion, question , answer1 , answer2 , answer3 , answer4 , correctAnswer;
    private String idSubject , quesStatus ;
    private String chooseAnswer ;
    
    public QuestionDTO() {
    }

    // for search questions (admin)
    public QuestionDTO(String idQuestion, String question, String answer1, String answer2, String answer3, String answer4, String correctAnswer, String idSubject, String quesStatus) {
        this.idQuestion = idQuestion;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
        this.idSubject = idSubject;
        this.quesStatus = quesStatus;
    }
    
    //for take quiz 
    public QuestionDTO(String idQuestion, String question, String answer1, String answer2, String answer3, String answer4, String correctAnswer, String idSubject) {
        this.idQuestion = idQuestion;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
        this.idSubject = idSubject;
    }

    public String getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(String idSubject) {
        this.idSubject = idSubject;
    }

    public String getQuesStatus() {
        return quesStatus;
    }

    public void setQuesStatus(String quesStatus) {
        this.quesStatus = quesStatus;
    }

    public String getChooseAnswer() {
        return chooseAnswer;
    }

    public void setChooseAnswer(String chooseAnswer) {
        this.chooseAnswer = chooseAnswer;
    }
    
    
}
