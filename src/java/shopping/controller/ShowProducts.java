/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shopping.product.VegetableDAO;
import shopping.product.VegetableDTO;
import shopping.user.UserDTO;

/**
 *
 * @author HP
 */
public class ShowProducts extends HttpServlet {

    private static final String USER = "index.jsp";
    private static final String ADMIN = "admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String defaultCategory = "RAU";
        String url = USER;
        try {
            String categoryID = request.getParameter("category");
            if (categoryID != null) {
                defaultCategory = categoryID;
            }
            List<VegetableDTO> list = new ArrayList<>();
            VegetableDAO dao = new VegetableDAO();
            list = dao.getList(defaultCategory);
            HttpSession session = request.getSession();
            session.setAttribute("LIST_PRODUCT", list);
            session.setAttribute("CATEGORY", defaultCategory);
            UserDTO account = (UserDTO) session.getAttribute("LOGIN_USER");
            if (account != null) {
                if (account.getRoleID().equals("AD")) {
                    url = ADMIN;
                }
            }
        } catch (Exception event) {
            log("Error at ShowListController: " + event.toString());
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
