/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giangnth.controller;

import giangnth.dtos.UserDTO;
import giangnth.model.UserBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Dell
 */
public class RegisterController extends HttpServlet {

    static Logger log = Logger.getLogger(LoginController.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "error.jsp";
        try {
            String password = request.getParameter("txtPassword");
            password = DigestUtils.sha256Hex(password);
            String name = request.getParameter("txtName");
            String email = request.getParameter("txtEmail");
            UserDTO dto = new UserDTO(email, password, name);
            UserBean bean = new UserBean();
            boolean check = bean.insertUser(dto);
            if (check == true) {
                url = "login.jsp";
                request.setAttribute("SUCCESS", "Register successfully! Please login !");
            } else {
                request.setAttribute("ERROR", "Something went wrong, please register another account !");
            }
        } catch (Exception e) {
            log.debug("Error at RegisterController: "+e.getMessage());
            request.setAttribute("ERROR", "Something went wrong, please register another account !");
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
