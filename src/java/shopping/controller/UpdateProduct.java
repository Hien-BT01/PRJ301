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
import shopping.product.VegetableDAO;
import shopping.product.VegetableDTO;

/**
 *
 * @author HP
 */
public class UpdateProduct extends HttpServlet {

    private static final String isLogin = "login.jsp";
    private static final String ERROR = "product_form.jsp";
    private static final String SUCCESS = "ShowProducts";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = isLogin;
        boolean flag = true;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("LOGIN_USER") != null) {
                String productID = request.getParameter("productId");
                byte[] bytes = request.getParameter("productName").getBytes("ISO-8859-1");
                String productName = new String(bytes, "UTF-8");
                String productImg = request.getParameter("productImage").trim();
                float productPrice = Float.parseFloat(request.getParameter("productPrice"));
                int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
                String productCategory = request.getParameter("categoryProduct");
                if (productName.trim().length() == 0 || productName.trim().length() <= 2) {
                    request.setAttribute("NAME_ERROR", "Please dont let empty String and input at lease 2 characters");
                    request.setAttribute("REMAIN_INFOR", new VegetableDTO(productID, "", productImg, productPrice, productQuantity, productCategory));
                    flag = false;
                    url = ERROR;
                }
                if (flag) {
                    VegetableDTO product = new VegetableDTO(productID, productName, productImg, productPrice, productQuantity, productCategory);
                    VegetableDAO dao = new VegetableDAO();
                    boolean check = dao.updateProduct(product);
                    if (check) {
                        url = SUCCESS;
                    }
                }
            }
        } catch (Exception event) {
            log("Erro at UpdateProductController: " + event.toString());
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
