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
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Dell
 */
public class LoginController extends HttpServlet {

    static Logger log = Logger.getLogger(LoginController.class);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "error.jsp";
        try {
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            password = DigestUtils.sha256Hex(password);
            UserBean bean = new UserBean();
            UserDTO userDTO = new UserDTO(email,password);
            UserDTO user = bean.checkLogin(userDTO);
            if (user != null) {
                if (user.getStatus().equals("new")) {
                    if (bean.changeStatus(user.getEmail())) {
                        user.setStatus("active");
                    }
                }
                HttpSession session = request.getSession();
                session.setAttribute("USER", user);
                if (user.getRole().equals("admin")) {
                    url = "admin.jsp";
                }else if (user.getRole().equals("student")) {
                    url = "user.jsp";
                }else{
                     request.setAttribute("ERROR", "Can not recognize the role");
                }
            }else{
                request.setAttribute("ERROR", "Can not find the account ! Please Register");
            }
        } catch (Exception e) {
            log.debug("Error at LoginController: " + e.getMessage());
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
