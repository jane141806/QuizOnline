/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangnth.model;

import giangnth.dao.DAO;
import giangnth.dtos.QuestionDTO;
import giangnth.dtos.QuizDetailDTO;
import giangnth.dtos.SubjectDTO;
import giangnth.dtos.UserDTO;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class UserBean {
    private UserDTO userDTO ;
    private QuestionDTO questionDTO;
    private DAO dao ;

    public UserBean() {
        dao = new DAO();
    }
    
    // getter and setter
    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public QuestionDTO getQuestionDTO() {
        return questionDTO;
    }

    public void setQuestionDTO(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }
    
    
     // method
    
    public UserDTO checkLogin(UserDTO userDTO) throws Exception{
        return dao.checkLogin(userDTO);
    }
    
    public boolean insertUser(UserDTO userDTO) throws Exception{
        return dao.insertUser(userDTO);
    }
    
    // change status after login
    public boolean changeStatus(String email) throws Exception{
        return dao.changeStatus(email);
    }
    
    // search question
    public ArrayList<QuestionDTO> searchQuestionByName(String search, int startNum) throws Exception{
        return dao.searchQuestionByName(search, startNum);
    }
    public ArrayList<QuestionDTO> searchQuestionBySubject(String search, int startNum) throws Exception{
        return dao.searchQuestionBySubject(search, startNum);
    }
    public ArrayList<QuestionDTO> searchQuestionByStatus(String search, int startNum) throws Exception{
        return dao.searchQuestionByStatus(search, startNum);
    }
    
    // count question page
    public double countSearchQuestionPage(String search) throws Exception{
        return dao.countSearchQuestionPage(search);
    }
    public double countSearchSubjectPage(String search) throws Exception{
        return dao.countSearchSubjectPage(search);
    }
    public double countSearchStatusPage(String search) throws Exception{
        return dao.countSearchStatusPage(search);
    }
    
    //CRUD question
    public boolean deleteQuestion(String idQuestion) throws Exception{
        return dao.deleteQuestion(idQuestion);
    }
    public boolean editQuestion(QuestionDTO dto) throws Exception{
        return dao.editQuestion(dto);
    }
    public boolean insertQuestion(QuestionDTO dto) throws Exception{
        return dao.insertQuestion(dto);
    }
    
    // search History
    public double countSearchHistoryPage(String search, String email) throws Exception{
        return dao.countSearchHistoryPage(search, email);
    }
    
    public ArrayList<QuizDetailDTO> searchHistory(String email, String search , int currentPage) throws Exception{
        return dao.searchHistory(search, email, currentPage);
    }
    
    // view quiz 
    public ArrayList<SubjectDTO> viewQuiz(String idSubject) throws Exception{
        return dao.viewQuiz(idSubject);
    }
    
    // take quiz
    public ArrayList<QuestionDTO> takeQuiz(String idSubject, int numQuestion) throws Exception{
        return dao.takeQuiz(idSubject, numQuestion);
    }
    
    // save quiz
    public boolean saveQuiz(String email, double score , String idSubject) throws Exception{
        return dao.saveQuiz(email, score, idSubject);
    }
}

