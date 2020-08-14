/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangnth.controller;

import giangnth.dtos.QuestionDTO;
import giangnth.model.UserBean;
import java.io.IOException;
import java.sql.Timestamp;
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
public class TakeQuizController extends HttpServlet {

    Logger log = Logger.getLogger(TakeQuizController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String idSubject = request.getParameter("idSubject");
            int numQuestion = Integer.parseInt(request.getParameter("numQuestion"));
            UserBean bean = new UserBean();
            HttpSession session = request.getSession();
            ArrayList<QuestionDTO> questionList = (ArrayList<QuestionDTO>) session.getAttribute("QUESTIONLIST");
            if (questionList == null) {
                questionList = bean.takeQuiz(idSubject, numQuestion);
                session.setAttribute("QUESTIONLIST", questionList);
            }

            // lấy page num
            String currentNumString = request.getParameter("currentNum");
            int currentNum;
            String action = request.getParameter("action");
            if (action.equals("next") || action.equals("Take Quiz")) {
                if (currentNumString == null) {
                    currentNum = 0;
                } else {
                    currentNum = Integer.parseInt(currentNumString);
                    String chooseAnswer = request.getParameter("quiz");
                    QuestionDTO dto = questionList.get(currentNum - 1);
                    dto.setChooseAnswer(chooseAnswer);
                }
                request.setAttribute("currentNum", currentNum);
                request.setAttribute("QUESTIONDTO", questionList.get(currentNum));
            } else if (action.equals("back")) {
                if (currentNumString == null) {
                    currentNum = 0;
                } else {
                    currentNum = Integer.parseInt(currentNumString);
                    String chooseAnswer = request.getParameter("quiz");
                    QuestionDTO dto = questionList.get(currentNum - 1);
                    dto.setChooseAnswer(chooseAnswer);
                    currentNum -= 2;
                    // currentNum: index câu tiếp theo, currentNum -1: câu hiện tại, currentNum -2 : câu quá khứ
                }
                request.setAttribute("currentNum", currentNum);
                request.setAttribute("QUESTIONDTO", questionList.get(currentNum));
            }

            request.setAttribute("numQuestion", numQuestion);

            // làm thanh thời gian:
            String check = request.getParameter("TIMER");
            if (check == null) {
                String time = request.getParameter("time");
                String[] format = time.split(":");
                String durationInSecondOfQuizConfigString = format[1];
                int durationInSecondOfQuizConfig = Integer.parseInt(durationInSecondOfQuizConfigString)*60*1000;
                java.util.Date now = new java.util.Date();
                Timestamp startedAt = new Timestamp(now.getTime());
                Timestamp finishedAt = new Timestamp(now.getTime() + durationInSecondOfQuizConfig);
                session.setAttribute("TIMER", finishedAt);
            }

        } catch (Exception e) {
            log.debug("Error at TakeQuizController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("quiz.jsp").forward(request, response);
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
