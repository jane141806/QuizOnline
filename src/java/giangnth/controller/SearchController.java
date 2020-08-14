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
import org.apache.log4j.Logger;

/**
 *
 * @author Dell
 */
public class SearchController extends HttpServlet {

    static Logger log = Logger.getLogger(SearchController.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String search = request.getParameter("txtSearch").trim();
            String category = request.getParameter("chooseBox");
            UserBean bean = new UserBean();
            ArrayList<QuestionDTO> questionList = new ArrayList<QuestionDTO>();
            String currentNumString = request.getParameter("currentNum");
            String pageNumString = request.getParameter("pageNum");
            
            int currentNum = 0;
            int pageNum = 0;
            // nếu có truyền currentNum
            if (currentNumString != null) {
                currentNum = Integer.parseInt(currentNumString);
            }
            // nếu có truyền pageNum thì lấy ra, không thì tìm pageNum
            if (pageNumString != null) {
                pageNum = Integer.parseInt(pageNumString);
            } else if (category.equals("Question Name")) {
                pageNum = (int) bean.countSearchQuestionPage(search);
            } else if (category.equals("Id Subject(ENG/PRJ)")) {
                pageNum = (int) bean.countSearchSubjectPage(search);
            } else if (category.equals("Status(active/deactive)")) {
                pageNum = (int) bean.countSearchStatusPage(search);
            }

            // nếu có currentNum
            if (currentNum != 0) {
                if (category.equals("Question Name")) {
                    questionList = bean.searchQuestionByName(search, currentNum);
                } else if (category.equals("Id Subject(ENG/PRJ)")) {
                    questionList = bean.searchQuestionBySubject(search, currentNum);
                } else if (category.equals("Status(active/deactive)")) {
                    questionList = bean.searchQuestionByStatus(search, currentNum);
                }
                 request.setAttribute("currentNum", currentNum);
            }else if (currentNum == 0 && pageNum > 0) {
                // trường hợp chưa có currentNum nhưng có kết quả trả về : pageNum > 0
                if (category.equals("Question Name")) {
                    questionList = bean.searchQuestionByName(search, 1);
                } else if (category.equals("Id Subject(ENG/PRJ)")) {
                    questionList = bean.searchQuestionBySubject(search, 1);
                } else if (category.equals("Status(active/deactive)")) {
                    questionList = bean.searchQuestionByStatus(search, 1);
                }
                request.setAttribute("currentNum", 1);
            }
            request.setAttribute("pageNum", pageNum);
            request.setAttribute("QUESTION", questionList);
        } catch (Exception e) {
            log.debug("Error at SearchController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("admin.jsp").forward(request, response);
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
