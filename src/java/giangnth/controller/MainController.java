/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangnth.controller;

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
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    static Logger log = Logger.getLogger(MainController.class.getName());
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (action.equals("login")) {
                url = "LoginController";
            }else if (action.equals("register")) {
               url = "RegisterController" ;
            }else if (action.equals("search")) {
                url = "SearchController";
            }else if (action.equals("edit")) {
                url = "EditController";
            }else if (action.equals("delete")) {
                url = "DeleteController";
            }else if (action.equals("insert")) {
                url = "InsertController";
            }else if (action.equals("Search History")) {
                url = "SearchHistoryController";
            }else if (action.equals("View Quiz")) {
                url = "ViewQuizController";
            }else if (action.equals("Take Quiz")) {
                url = "TakeQuizController";
            }else if (action.equals("SUBMIT QUIZ")) {
                url = "SaveQuizController";
            }
            else{
               request.setAttribute("ERROR", "Can not find !");
            }
        } catch (Exception e) {
            log.debug("Error at MainController: "+ e.getMessage());
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
