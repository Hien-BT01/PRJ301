/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shopping.helper.RandomString;
import shopping.order.OrderDTO;
import shopping.order.OrderDetailedDTO;
import shopping.product.CartDTO;
import shopping.product.VegetableDAO;
import shopping.product.VegetableDTO;
import shopping.user.UserDTO;

/**
 *
 * @author HP
 */
public class Checkout extends HttpServlet {

    private static final String VIEW_CART = "view_cart.jsp";
    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "ShowProducts";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        boolean check = true;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("LOGIN_USER") != null) {
                VegetableDAO dao = new VegetableDAO();
                CartDTO cart = (CartDTO) session.getAttribute("CART");
                for (String key : cart.getKetSet()) {
                    VegetableDTO product = dao.getProduct(key);
                    if (cart.getCart().get(key).getQuantity() > product.getQuantity()) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    String orderId = RandomString.randomString();
                    UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
                    float total = cart.getTotalPrice();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
                    LocalDateTime now = LocalDateTime.now();
                    String date = dtf.format(now);
                    OrderDTO order = new OrderDTO(orderId, user.getUsername(), date, total);
                    boolean flag1 = dao.createOrder(order);
                    if (flag1) {
                        for (String key : cart.getKetSet()) {
                            String detailID = RandomString.randomString();
                            String productID = cart.getCart().get(key).getId();
                            float price = cart.getCart().get(key).getPrice();
                            int productCartQuatity = cart.getCart().get(key).getQuantity();
                            int updateQuantity = dao.getProduct(key).getQuantity() - productCartQuatity;
                            dao.updateQuantityProduct(key, updateQuantity);
                            OrderDetailedDTO orderDetailed = new OrderDetailedDTO(detailID, orderId, productID, price, productCartQuatity);
                            boolean flag2 = dao.createOrderDetailed(orderDetailed);
                            if (!flag2) {
                                break;
                            }
                        }
                        session.removeAttribute("CART");
                        request.setAttribute("BUY_MESS", "Thanks for buying product from us! Have a nice day");
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("BUY_ERROR", "Your quantity is out of range our quantity! please select again!");
                    url = VIEW_CART;
                }
            }

        } catch (Exception event) {
            log("Erorr at CheckoutController: " + event.toString());
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
