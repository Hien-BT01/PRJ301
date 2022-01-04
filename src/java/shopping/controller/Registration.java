/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shopping.user.UserDAO;
import shopping.user.UserDTO;
import shopping.user.UserError;

/**
 *
 * @author HP
 */
public class Registration extends HttpServlet {

    private static final String ERROR = "signup.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean flag = true;
        String url = ERROR;
        try {
            UserError error = new UserError();
            String userName = request.getParameter("userName");
            byte[] bytes = request.getParameter("fullName").getBytes("ISO-8859-1");
            String fullName = new String(bytes, "UTF-8");
            String phoneNumber = request.getParameter("phoneNumber");
            byte[] byte2s = request.getParameter("address").getBytes("ISO-8859-1");
            String address = new String(byte2s, "UTF-8");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String gender = request.getParameter("gender");
            if (userName.trim().length() == 0 || userName.trim().length() < 5) {
                error.setUserName("Please dont let empty value or value must be at least 5 characters");
                userName = "";
                flag = false;
            }
            if (fullName.trim().length() == 0 || fullName.trim().length() < 2) {
                error.setFullName("Please dont let empty value or value must be at least 2 characters");
                fullName = "";
                flag = false;
            }
            if (!phoneNumber.trim().matches("0[0-9]{9}")) {
                error.setPhoneNumber("Phone number must start at 0 and 9 numbers after this");
                phoneNumber = "";
                flag = false;
            }
            if (address.trim().length() == 0 || address.trim().length() < 5) {
                error.setAddress("Please dont let empty value or value must be at least 5 characters");
                address = "";
                flag = false;
            }
            if (password.trim().length() == 0 || password.trim().length() < 1) {
                error.setPassword("Please dont let empty value or value must be at least 1 characters");
                password = "";
                flag = false;
            }
            if (!confirm.trim().equals(password.trim())) {
                error.setConfirm("Confirm password musr be valid with password");
                confirm = "";
                flag = false;
            }
            UserDAO dao = new UserDAO();
            UserDTO user = dao.getUser(userName);
            if (user != null) {
                error.setUserName("Your User Name has already existed");
                flag = false;
            }
            if (flag == false) {
                request.setAttribute("USER_ERROR", error);
                request.setAttribute("REMAIN_USER_INFOR", new UserDTO(userName, fullName, address, phoneNumber, password, gender));
            } else {
                boolean check = dao.createUser(new UserDTO(userName, fullName, address, phoneNumber, password, "US", gender));
                if (check) {
                    url = SUCCESS;
                }
            }
        } catch (Exception event) {
            log("Error at RegistrationController: " + event.toString());
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
