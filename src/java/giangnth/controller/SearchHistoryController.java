/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangnth.controller;

import giangnth.dtos.QuizDetailDTO;
import giangnth.dtos.UserDTO;
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
public class SearchHistoryController extends HttpServlet {

    static Logger log = Logger.getLogger(SearchHistoryController.class.getName());
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // take parameter
            String search = request.getParameter("txtSearch").trim();
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            String email = user.getEmail();
            
            UserBean bean = new UserBean();
            ArrayList<QuizDetailDTO> list = new ArrayList<QuizDetailDTO>();
            
            String currentNumString = request.getParameter("currentNum");
            String pageNumString = request.getParameter("pageNum");
            
            int currentNum = 0;
            int pageNum = 0;
            // nếu có truyền currentNum
            if (currentNumString != null) {
                currentNum = Integer.parseInt(currentNumString);
            }
            // nếu có truyền pageNum thì lấy ra, không thì tìm pageNum
            if (pageNumString == null) {
                pageNum = (int) bean.countSearchHistoryPage(search, email);
            }else{
                pageNum = Integer.parseInt(currentNumString);
            }
            
            if (currentNum != 0) {
                list = bean.searchHistory(email, search, currentNum);
                request.setAttribute("currentNum", currentNum);
            }else if (currentNum == 0 && pageNum > 0) {
                list = bean.searchHistory(email, search, 1);
                request.setAttribute("currentNum", 1);
            }
            request.setAttribute("pageNum", pageNum);
            request.setAttribute("LIST", list);
        } catch (Exception e) {
            log.debug("Error at SearchHistoryController: "+e.getMessage());
        } finally{
            request.getRequestDispatcher("user.jsp").forward(request, response);
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
