/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.controller;

import java.io.IOException;
import java.util.Random;
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
public class AddProduct extends HttpServlet {
    private static final String isLogin = "login.jsp";
    private static final String ERROR = "product_form.jsp";
    private static final String SUCCESS = "ShowProducts";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean check = true;
        String url = isLogin;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("LOGIN_USER") != null) {
                byte[] bytes = request.getParameter("productName").getBytes("ISO-8859-1");
                String productName = new String(bytes, "UTF-8");
                String productImg = request.getParameter("productImage").trim();
                float productPrice = Float.parseFloat(request.getParameter("productPrice"));
                int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
                String productCategory = request.getParameter("categoryProduct");
                if (productName.trim().length() == 0 || productName.trim().length() <= 2) {
                    request.setAttribute("NAME_ERROR", "Please dont let empty String and input at lease 2 characters and without special character");
                    check = false;
                    request.setAttribute("REMAIN_INFOR", new VegetableDTO("", productImg, productPrice, productQuantity, productCategory));
                    url = ERROR;
                }
                if (check) {
                    VegetableDAO dao = new VegetableDAO();
                    Random random = new Random();
                    String randomString = new String();
                    String randomNumber = new String();
                    boolean flag = true;
                    String textString = "QWERTYUIOPASDFGHJKLZXCVBNMzxcvbnmasdfghjklqwertyuiop";
                    String textNum = "0123456789";
                    while (flag) {
                        randomString = "";
                        randomNumber = "";
                        for (int index = 0; index < 10; index++) {
                            int temp = random.nextInt(textString.length());
                            int temp2 = random.nextInt(textNum.length());
                            char randomCharString = textString.charAt(temp);
                            char randomCharNum = textNum.charAt(temp2);
                            randomString += randomCharString;
                            randomNumber += randomCharNum;
                        }
                        String newId = productCategory + randomString + randomNumber;
                        VegetableDTO tempVegetable = dao.getProduct(newId);
                        if (tempVegetable == null) {
                            flag = false;
                        }
                    }
                    VegetableDTO product = new VegetableDTO(productCategory + randomString + randomNumber, productName, productImg, productPrice, productQuantity, productCategory);
                    boolean checkAdd = dao.addProduct(product);
                    if (checkAdd) {
                        url = SUCCESS;
                    }
                }
            }
        } catch (Exception event) {
            log("Erro at AddProductController: " + event.toString());
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
