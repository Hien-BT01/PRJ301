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

/**
 *
 * @author HP
 */
public class MainController extends HttpServlet {

    private static final String LOGIN = "Login";
    private static final String DEFAULT = "StartUpApp";
    private static final String LOGOUT = "Logout";
    private static final String SHOW_LOGIN = "LoginForm";
    private static final String SHOW_SIGNUP = "SignUpForm";
    private static final String SHOW_PRODUCT = "ShowProducts";
    private static final String SEARCH_PRODUCTS = "Search";
    private static final String ADD_TO_CART = "AddCart";
    private static final String UPDATE_ITEM_CART = "UpdateItemCart";
    private static final String DELETE_ITEM_CART = "DeleteProductCart";
    private static final String SHOW_ADD_FORM = "ShowAddForm";
    private static final String ADD_PRODUCT = "AddProduct";
    private static final String UPDATE_PRODUCT = "UpdateProduct";
    private static final String SHOW_UPDATE_FORM = "ShowUpdateForm";
    private static final String DELETE_PRODUCT = "DeleteProduct";
    private static final String CHECK_OUT = "Checkout";
    private static final String REGISTRATION = "Registration";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = DEFAULT;
        try {
            String action = request.getParameter("action");
            if (action.equals("Login")) {
                url = LOGIN;
            }else if(action.equals("Logout")){
                url = LOGOUT;
            }else if(action.equals("ShowLogin")){
                url = SHOW_LOGIN;
            }else if(action.equals("ShowSignup")){
                url = SHOW_SIGNUP;
            }else if(action.equals("listProduct")){
                url = SHOW_PRODUCT;
            }else if(action.equals(("Search"))){
                url = SEARCH_PRODUCTS;
            }else if(action.equals("goHome")){
                url = SHOW_PRODUCT;
            }else if(action.equals("Add To Cart")){
                url = ADD_TO_CART;
            }else if(action.equals("Save")){
                url = UPDATE_ITEM_CART;
            }else if(action.equals("DeleteProductInCart")){
                url = DELETE_ITEM_CART;
            }else if(action.equals("showAddForm")){
                url = SHOW_ADD_FORM;
            }else if(action.equals("AddProduct")){
                url = ADD_PRODUCT;
            }else if(action.equals("Update")){
                url = UPDATE_PRODUCT;
            }else if(action.equals("showUpdateForm")){
                url = SHOW_UPDATE_FORM;
            }else if(action.equals("deleteProduct")){
                url = DELETE_PRODUCT;
            }else if(action.equals("Checkout")){
                url = CHECK_OUT;
            }else if(action.equals("Register")){
                url = REGISTRATION;
            }
        } catch (Exception event) {
            log("Error at MainController: " + event.toString());
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
