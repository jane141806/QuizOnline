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
public class EditController extends HttpServlet {

    static Logger log = Logger.getLogger(EditController.class.getName());
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       String url = "SearchController";
        try {
            String idQuestion = request.getParameter("idQuestion").trim();
            String question = request.getParameter("question").trim();
            String answer1 = request.getParameter("answer1").trim();
            String answer2 = request.getParameter("answer2").trim();
            String answer3 = request.getParameter("answer3").trim();
            String answer4 = request.getParameter("answer4").trim();
            String correctAnswer = request.getParameter("correctAnswer").trim();
            String idSubject = request.getParameter("idSubject").trim().toUpperCase();
            String quesStatus = request.getParameter("quesStatus").trim().toLowerCase();
            QuestionDTO dto = new QuestionDTO(idQuestion, question, answer1, answer2, answer3, answer4, correctAnswer, idSubject, quesStatus);
            UserBean bean = new UserBean();
            boolean check = bean.editQuestion(dto);
            if (check) {
                request.setAttribute("SUCCESS", "Edit Successful !");
            }else{
                request.setAttribute("ERROR", "Oops! Something went wrong. Please try again !");
            }
        } catch (Exception e) {
            log.debug("Error at EditController: " + e.getMessage());
            request.setAttribute("ERROR", "Oops! Something went wrong. Please try again !");
        } finally{
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
