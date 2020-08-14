/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangnth.controller;

import giangnth.dtos.QuestionDTO;
import giangnth.model.UserBean;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Dell
 */
public class SaveQuizController extends HttpServlet {

    Logger log = Logger.getLogger(SaveQuizController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "error.jsp";
        try {
            String email = request.getParameter("email");
            String idSubject = request.getParameter("idSubject");
            HttpSession session = request.getSession();
            ArrayList<QuestionDTO> questionList = (ArrayList<QuestionDTO>) session.getAttribute("QUESTIONLIST");
            int numCorrectAnswer = 0;
            for (int i = 0; i < questionList.size(); i++) {
                QuestionDTO dto = questionList.get(i);
                if (dto.getChooseAnswer() != null) {
                    if (dto.getChooseAnswer().equals(dto.getCorrectAnswer())) {
                        numCorrectAnswer++;
                    }
                }
            }
            double score = (10 / questionList.size()) * numCorrectAnswer;
            UserBean bean = new UserBean();
            boolean isSuccess = bean.saveQuiz(email, score, idSubject);
            if (isSuccess) {
                url = "user.jsp";
                request.setAttribute("idSubject", idSubject);
                request.setAttribute("numCorrectAnswer", numCorrectAnswer);
                request.setAttribute("totalQuestion", questionList.size());
                request.setAttribute("score", score);
                request.setAttribute("QUIZSUCCESS", "Your result: ");
            } else {
                request.setAttribute("ERROR", "Something went wrong ! Please take quiz again");
            }
        } catch (Exception e) {
            request.setAttribute("ERROR", "Something went wrong ! Please take quiz again");
            log.debug("Error at SaveQuizController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
