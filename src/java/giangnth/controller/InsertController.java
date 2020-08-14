/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangnth.controller;

import giangnth.dtos.QuestionDTO;
import giangnth.model.UserBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Dell
 */
public class InsertController extends HttpServlet {

    Logger log = Logger.getLogger(InsertController.class.getName());
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "insert.jsp";
        QuestionDTO dto = null;
        try {
            String idQuestion = request.getParameter("idQuestion").trim();
            String question = request.getParameter("question").trim();
            String answer1 = request.getParameter("answer1").trim();
            String answer2 = request.getParameter("answer2").trim();
            String answer3 = request.getParameter("answer3").trim();
            String answer4 = request.getParameter("answer4").trim();
            String correctAnswer = request.getParameter("correctAnswer").trim().toLowerCase();
            if (correctAnswer.equals("answer1")) {
                correctAnswer = answer1;
            }else if(correctAnswer.equals("answer2")) {
                correctAnswer = answer2;
            }else if(correctAnswer.equals("answer3")) {
                correctAnswer = answer3;
            }else if(correctAnswer.equals("answer4")) {
                correctAnswer = answer4;
            }
            String idSubject = request.getParameter("idSubject").trim().toUpperCase();
            
            dto = new QuestionDTO(idQuestion, question, answer1, answer2, answer3, answer4, correctAnswer, idSubject, "active");
            UserBean bean = new UserBean();
            boolean check = bean.insertQuestion(dto);
            if (check) {
                request.setAttribute("SUCCESS", "Insert Successful! Do you wanna continue ?");
            }else{
                request.setAttribute("ERROR", "Oops! Something went wrong. Please try again !");
            }
        } catch (Exception e) {
            log.debug("Error at InsertController: " + e.getMessage());
            if (e.toString().contains("duplicate")) {
                request.setAttribute("ERROR", "Duplicate IdQuestion. Please try again !");
                request.setAttribute("DTO", dto );
            }
        }finally{
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
