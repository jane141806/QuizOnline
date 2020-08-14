/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangnth.dao;

import giangnth.db.MyConnection;
import giangnth.dtos.QuestionDTO;
import giangnth.dtos.QuizDetailDTO;
import giangnth.dtos.SubjectDTO;
import giangnth.dtos.UserDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class DAO implements Serializable {

    private Connection cnn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public DAO() {
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (cnn != null) {
            cnn.close();
        }
    }

    //login
    public UserDTO checkLogin(UserDTO user) throws Exception {
        UserDTO dto = null;
        String role, name, status;
        try {
            String sql = "Select Name, Status, Role from QuizUser where Email = ? and Password = ?";
            cnn = MyConnection.getConnection();
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, user.getEmail());
            preStm.setString(2, user.getPassword());
            rs = preStm.executeQuery();
            if (rs.next()) {
                name = rs.getString("Name");
                status = rs.getString("Status");
                role = rs.getString("Role");
                dto = new UserDTO(user.getEmail(), role, name, status);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    // register
    public boolean insertUser(UserDTO user) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert into QuizUser(Email,Name,Password,Status,Role) values (?,?,?,?,?)";
            cnn = MyConnection.getConnection();
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, user.getEmail());
            preStm.setString(2, user.getName());
            preStm.setString(3, user.getPassword());
            preStm.setString(4, "new");
            preStm.setString(5, "student");
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    // change from New to Active
    public boolean changeStatus(String email) throws Exception {
        boolean check = false;
        try {
            String sql = "Update QuizUser set Status = ? where Email = ?";
            cnn = MyConnection.getConnection();
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "active");
            preStm.setString(2, email);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    // search question by name
    public ArrayList<QuestionDTO> searchQuestionByName(String search, int startNum) throws Exception {
        ArrayList<QuestionDTO> questionList = new ArrayList<QuestionDTO>();
        try {
            String sql = "SELECT TOP(20) q.IdQuestion, q.Question , q.Answer1 , "
                    + "q.Answer2, q.Answer3, q.Answer4, q.CorrectAnswer , q.IdSubject, "
                    + "q.QuesStatus from Question as q WHERE q.Question LIKE ? and "
                    + "q.IdQuestion NOT IN (SELECT TOP(?) Question.IdQuestion FROM Question "
                    + " WHERE Question.Question LIKE ? order by Question.Question) order by q.Question";
            // top20 -> 21--40
            //top 40 --> 41-60
            cnn = MyConnection.getConnection();
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            preStm.setInt(2, (startNum - 1) * 20);
            preStm.setString(3, "%" + search + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                QuestionDTO dto = new QuestionDTO();
                dto.setIdQuestion(rs.getString("IdQuestion"));
                dto.setQuestion(rs.getString("Question"));
                dto.setAnswer1(rs.getString("Answer1"));
                dto.setAnswer2(rs.getString("Answer2"));
                dto.setAnswer3(rs.getString("Answer3"));
                dto.setAnswer4(rs.getString("Answer4"));
                dto.setCorrectAnswer(rs.getString("CorrectAnswer"));
                dto.setIdSubject(rs.getString("IdSubject"));
                dto.setQuesStatus(rs.getString("QuesStatus"));
                questionList.add(dto);
            }
        } finally {
            closeConnection();
        }
        return questionList;
    }

    // search question by status
    public ArrayList<QuestionDTO> searchQuestionByStatus(String search, int startNum) throws Exception {
        ArrayList<QuestionDTO> questionList = new ArrayList<QuestionDTO>();
        try {
            String sql = "SELECT TOP(20) q.IdQuestion, q.Question , q.Answer1 , "
                    + "q.Answer2, q.Answer3, q.Answer4, q.CorrectAnswer , q.IdSubject, "
                    + "q.QuesStatus from Question as q WHERE q.QuesStatus LIKE ? and "
                    + "q.IdQuestion NOT IN (SELECT TOP(?) Question.IdQuestion FROM Question "
                    + " WHERE Question.QuesStatus LIKE ? order by Question.Question) order by q.Question";
            cnn = MyConnection.getConnection();
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            preStm.setInt(2, (startNum - 1) * 20);
            preStm.setString(3, "%" + search + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                QuestionDTO dto = new QuestionDTO();
                dto.setIdQuestion(rs.getString(1));
                dto.setQuestion(rs.getString(2));
                dto.setAnswer1(rs.getString(3));
                dto.setAnswer2(rs.getString(4));
                dto.setAnswer3(rs.getString(5));
                dto.setAnswer4(rs.getString(6));
                dto.setCorrectAnswer(rs.getString(7));
                dto.setIdSubject(rs.getString(8));
                dto.setQuesStatus(rs.getString(9));
                questionList.add(dto);
            }
        } finally {
            closeConnection();
        }
        return questionList;
    }

    // search question by id Subject
    public ArrayList<QuestionDTO> searchQuestionBySubject(String search, int startNum) throws Exception {
        ArrayList<QuestionDTO> questionList = new ArrayList<QuestionDTO>();
        try {
            String sql = "SELECT TOP(20) q.IdQuestion, q.Question , q.Answer1 , "
                    + "q.Answer2, q.Answer3, q.Answer4, q.CorrectAnswer , q.IdSubject, "
                    + "q.QuesStatus from Question as q WHERE q.IdSubject LIKE ? and "
                    + "q.IdQuestion NOT IN (SELECT TOP(?) Question.IdQuestion FROM Question "
                    + " WHERE Question.IdSubject LIKE ? order by Question.Question) order by q.Question";
            cnn = MyConnection.getConnection();
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            preStm.setInt(2, (startNum - 1) * 20);
            preStm.setString(3, "%" + search + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                QuestionDTO dto = new QuestionDTO();
                dto.setIdQuestion(rs.getString(1));
                dto.setQuestion(rs.getString(2));
                dto.setAnswer1(rs.getString(3));
                dto.setAnswer2(rs.getString(4));
                dto.setAnswer3(rs.getString(5));
                dto.setAnswer4(rs.getString(6));
                dto.setCorrectAnswer(rs.getString(7));
                dto.setIdSubject(rs.getString(8));
                dto.setQuesStatus(rs.getString(9));
                questionList.add(dto);
            }
        } finally {
            closeConnection();
        }
        return questionList;
    }

    // count total page for question search
    public double countSearchQuestionPage(String search) throws Exception {
        double num = 0;
        try {
            String sql = "Select count(IdQuestion) from Question Where Question LIKE ?";
            cnn = MyConnection.getConnection();
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            if (rs.next()) {
                num = Math.ceil(rs.getDouble(1) / 20);
            }
        } finally {
            closeConnection();
        }
        return num;
    }

    public double countSearchSubjectPage(String search) throws Exception {
        double num = 0;
        try {
            String sql = "Select count(IdQuestion) from Question Where IdSubject LIKE ?";
            cnn = MyConnection.getConnection();
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            if (rs.next()) {
                num = Math.ceil(rs.getDouble(1) / 20);
            }
        } finally {
            closeConnection();
        }
        return num;
    }

    public double countSearchStatusPage(String search) throws Exception {
        double num = 0;
        try {
            String sql = "Select count(IdQuestion) from Question Where QuesStatus LIKE ?";
            cnn = MyConnection.getConnection();
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            if (rs.next()) {
                num = Math.ceil(rs.getDouble(1) / 20);
            }
        } finally {
            closeConnection();
        }
        return num;
    }

    //delete question
    public boolean deleteQuestion(String idQuestion) throws Exception {
        boolean check = false;
        try {
            String sql = "Update Question Set QuesStatus = ? Where IdQuestion = ?";
            cnn = MyConnection.getConnection();
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "deactive");
            preStm.setString(2, idQuestion);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    // edit question
    public boolean editQuestion(QuestionDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Update Question Set Question = ?, Answer1 = ?, Answer2 = ?,"
                    + "Answer3 = ?, Answer4 = ?, CorrectAnswer = ?, IdSubject = ? ,"
                    + "QuesStatus = ? Where IdQuestion = ?";
            cnn = MyConnection.getConnection();
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, dto.getQuestion());
            preStm.setString(2, dto.getAnswer1());
            preStm.setString(3, dto.getAnswer2());
            preStm.setString(4, dto.getAnswer3());
            preStm.setString(5, dto.getAnswer4());
            preStm.setString(6, dto.getCorrectAnswer());
            preStm.setString(7, dto.getIdSubject());
            preStm.setString(8, dto.getQuesStatus());
            preStm.setString(9, dto.getIdQuestion());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    //insert question
    public boolean insertQuestion(QuestionDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert into Question(IdQuestion,Question,Answer1,Answer2,"
                    + "Answer3,Answer4,CorrectAnswer,IdSubject,QuesStatus) values "
                    + "(?,?,?,?,?,?,?,?,?)";
            cnn = MyConnection.getConnection();
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, dto.getIdQuestion());
            preStm.setString(2, dto.getQuestion());
            preStm.setString(3, dto.getAnswer1());
            preStm.setString(4, dto.getAnswer2());
            preStm.setString(5, dto.getAnswer3());
            preStm.setString(6, dto.getAnswer4());
            preStm.setString(7, dto.getCorrectAnswer());
            preStm.setString(8, dto.getIdSubject());
            preStm.setString(9, dto.getQuesStatus());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    //count page for history 
    public double countSearchHistoryPage(String search, String email) throws Exception {
        double num = 0;
        try {
            String sql = "Select count(Email) from QuizDetail Where IdSubject LIKE ? and Email = ?";
            cnn = MyConnection.getConnection();
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            preStm.setString(2, email);
            rs = preStm.executeQuery();
            if (rs.next()) {
                num = Math.ceil(rs.getDouble(1) / 20);
            }
        } finally {
            closeConnection();
        }
        return num;
    }

    public ArrayList<QuizDetailDTO> searchHistory(String search, String email, int currentPage) throws Exception {
        QuizDetailDTO dto;
        ArrayList<QuizDetailDTO> list = new ArrayList<QuizDetailDTO>();
        double score;
        String idSubject;
        Timestamp doingDate;
        int idQuiz;
        try {
            String sql = "Select Top(20) q.Score, q.IdQuiz, q.DoingDate, "
                    + " q.IdSubject from QuizDetail as q where q.Email = ? and q.IdSubject LIKE  "
                    + " ? and q.IdQuiz NOT IN (Select TOP(?) QuizDetail.IdQuiz from QuizDetail "
                    + " where QuizDetail.Email = ? and QuizDetail.IdSubject LIKE ?)";
            cnn = MyConnection.getConnection();
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, email);
            preStm.setString(2, "%" + search + "%");
            preStm.setInt(3, (currentPage - 1) * 20);
            preStm.setString(4, email);
            preStm.setString(5, "%" + search + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                score = rs.getDouble("Score");
                idQuiz = rs.getInt("IdQuiz");
                doingDate = rs.getTimestamp("DoingDate");
                idSubject = rs.getString("IdSubject");
                dto = new QuizDetailDTO(email, idQuiz, idSubject, score, doingDate);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    // view quiz
    public ArrayList<SubjectDTO> viewQuiz(String idSubject) throws Exception {
        ArrayList<SubjectDTO> list = new ArrayList<SubjectDTO>();
        SubjectDTO dto;
        try {
            String sql = "Select NumQuestion,Time from Subject "
                    + "where IdSubject = ? and SubStatus = ?";
            cnn = MyConnection.getConnection();
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, idSubject);
            preStm.setString(2, "active");
            rs = preStm.executeQuery();
            if (rs.next()) {
                dto = new SubjectDTO();
                dto.setIdSubject(idSubject);
                dto.setNumQuestion(rs.getInt("NumQuestion"));
                dto.setTime(rs.getTime("Time"));
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    //take quiz
    public ArrayList<QuestionDTO> takeQuiz(String idSubject ,int numQuestion) throws Exception{
        ArrayList<QuestionDTO> questionList = new ArrayList<QuestionDTO>();
        QuestionDTO dto;
        try {
            String sql = "Select TOP(?) IdQuestion, Question, Answer1, Answer2, "
                    + "Answer3, Answer4, CorrectAnswer From Question Where IdSubject = ? and "
                    + "QuesStatus = ? ORDER BY NEWID()";
            cnn = MyConnection.getConnection();
            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, numQuestion);
            preStm.setString(2, idSubject);
            preStm.setString(3, "active");
            rs = preStm.executeQuery();
            while (rs.next()) { 
                String idQuestion = rs.getString("IdQuestion");
                String question = rs.getString("Question");
                String answer1 = rs.getString("Answer1");
                String answer2 = rs.getString("Answer2");
                String answer3 = rs.getString("Answer3");
                String answer4 = rs.getString("Answer4");
                String correctAnswer = rs.getString("CorrectAnswer");
                dto = new QuestionDTO(idQuestion, question, answer1, answer2, answer3, answer4, correctAnswer, idSubject);
                questionList.add(dto);
            }
        } finally{
            closeConnection();
        }
        return questionList;
    }
    
    // saveQuiz 
    public boolean saveQuiz(String email, double score , String idSubject) throws Exception{
        boolean check = false;
        try {
            String sql = "Insert into QuizDetail(Email,Score,IdSubject) values(?,?,?)";
            cnn = MyConnection.getConnection();
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, email);
            preStm.setDouble(2, score);
            preStm.setString(3, idSubject);
            check = preStm.executeUpdate() > 0 ;
        } finally{
            closeConnection();
        }
        return check;
    }
}
