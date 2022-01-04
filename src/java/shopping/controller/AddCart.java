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
import javax.servlet.http.HttpSession;
import shopping.product.CartDTO;
import shopping.product.VegetableDTO;

/**
 *
 * @author HP
 */
public class AddCart extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "index.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("LOGIN_USER") != null) {
                request.setCharacterEncoding("UTF-8");
                String productId = request.getParameter("productId");
                byte[] bytes = request.getParameter("productName").getBytes("ISO-8859-1");
                String productName = new String(bytes,"UTF-8");
                String productImage = request.getParameter("productImage");
                float productPrice = Float.parseFloat(request.getParameter("productPrice"));
                int productQuantity = Integer.parseInt(request.getParameter("quantity"));
                CartDTO cart = (CartDTO) session.getAttribute("CART");
                if (cart == null) {
                    cart = new CartDTO();
                }
                VegetableDTO vegetable = new VegetableDTO(productId, productName, productImage, productPrice, productQuantity);
                boolean statusAdd = cart.add(vegetable);
                if (statusAdd) {
                    url = SUCCESS;
                    session.setAttribute("CART", cart);
                    request.setAttribute("MESSAGE", "Sản phẩm đã được thêm vào giỏ hàng.");
                }
            }
        } catch (Exception event) {
            log("Error at AddCartController: " + event.toString());
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
