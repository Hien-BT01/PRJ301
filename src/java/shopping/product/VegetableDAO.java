/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import shopping.helper.utils;
import shopping.order.OrderDTO;
import shopping.order.OrderDetailedDTO;

/**
 *
 * @author HP
 */
public class VegetableDAO {

    private static final String SELECT_PRODUCTS = "SELECT id, productName, image, price, quantity, categoryID FROM Product WHERE categoryID =?";
    private static final String SEARCH_PRODUCTS = "SELECT id, productName, image, price, quantity, categoryID FROM Product WHERE productName LIKE ?";
    private static final String GET_PRODUCT = "SELECT id, productName, image, price, quantity, categoryID FROM Product WHERE id =?";
    private static final String ADD_PRODUCT = "INSERT INTO Product(id, productName, image, price, quantity, categoryID) VALUES(?,?,?,?,?,?)";
    private static final String UPDATE_PRODUCT = "UPDATE Product SET productName =?, image =?, price =?, quantity =?, categoryID =? WHERE id =?";
    private static final String DELETE_PRODUCT = "DELETE FROM Product WHERE id =?";
    private static final String CREATE_ORDER = "INSERT INTO OrderProduct(orderID, userName, date, total) VALUES(?,?,?,?)";
    private static final String CREATE_ORDER_DETAIL = "INSERT INTO OrderDetail(detailID, orderID, productID, price, quantity) VALUES(?,?,?,?,?)";
    private static final String UPDATE_QUANTITY = "UPDATE Product SET quantity =? WHERE id=?";

    public void updateQuantityProduct(String id, int quantity) throws SQLException {
        Connection connection = null;
        PreparedStatement pstm = null;
        try {
            connection = utils.getConnection();
            if (connection != null) {
                pstm = connection.prepareStatement(UPDATE_QUANTITY);
                pstm.setInt(1, quantity);
                pstm.setNString(2, id);
                pstm.executeUpdate();
            }
        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public boolean createOrderDetailed(OrderDetailedDTO orderDetailed) throws SQLException {
        boolean rowEffected = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        try {
            connection = utils.getConnection();
            if (connection != null) {
                pstm = connection.prepareStatement(CREATE_ORDER_DETAIL);
                pstm.setString(1, orderDetailed.getDetailID());
                pstm.setString(2, orderDetailed.getOrderID());
                pstm.setNString(3, orderDetailed.getProductID());
                pstm.setFloat(4, orderDetailed.getPrice());
                pstm.setInt(5, orderDetailed.getQuantity());
                rowEffected = pstm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return rowEffected;
    }

    public boolean createOrder(OrderDTO order) throws SQLException {
        boolean rowEffected = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        try {
            connection = utils.getConnection();
            if (connection != null) {
                pstm = connection.prepareStatement(CREATE_ORDER);
                pstm.setString(1, order.getOrderID());
                pstm.setNString(2, order.getUserName());
                pstm.setString(3, order.getDate());
                pstm.setFloat(4, order.getTotal());
                rowEffected = pstm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return rowEffected;
    }

    public boolean deleteProduct(String id) throws SQLException {
        boolean rowAffected = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        try {
            connection = utils.getConnection();
            if (connection != null) {
                pstm = connection.prepareStatement(DELETE_PRODUCT);
                pstm.setNString(1, id);
                rowAffected = pstm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return rowAffected;
    }

    public boolean updateProduct(VegetableDTO product) throws SQLException {
        boolean rowAffected = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        try {
            connection = utils.getConnection();
            if (connection != null) {
                pstm = connection.prepareStatement(UPDATE_PRODUCT);
                pstm.setNString(1, product.getName());
                pstm.setNString(2, product.getImage());
                pstm.setFloat(3, product.getPrice());
                pstm.setInt(4, product.getQuantity());
                pstm.setNString(5, product.getCategory());
                pstm.setNString(6, product.getId());
                rowAffected = pstm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return rowAffected;

    }

    public boolean addProduct(VegetableDTO product) throws SQLException {
        boolean rowAffected = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        try {
            connection = utils.getConnection();
            if (connection != null) {
                pstm = connection.prepareStatement(ADD_PRODUCT);
                pstm.setNString(1, product.getId());
                pstm.setNString(2, product.getName());
                pstm.setNString(3, product.getImage());
                pstm.setFloat(4, product.getPrice());
                pstm.setInt(5, product.getQuantity());
                pstm.setNString(6, product.getCategory());
                rowAffected = pstm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return rowAffected;
    }

    public VegetableDTO getProduct(String id) throws SQLException {
        VegetableDTO product = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet result = null;
        try {
            connection = utils.getConnection();
            if (connection != null) {
                pstm = connection.prepareStatement(GET_PRODUCT);
                pstm.setNString(1, id);
                result = pstm.executeQuery();
                if (result.next()) {
                    String productID = result.getNString("id");
                    String name = result.getNString("productName");
                    String image = result.getNString("image");
                    float price = result.getFloat("price");
                    int quantity = result.getInt("quantity");
                    String category = result.getNString("categoryID");
                    product = new VegetableDTO(id, name, image, price, quantity, category);
                }
            }
        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            if (result != null) {
                result.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return product;
    }

    public List<VegetableDTO> getList(String categoryID) throws SQLException {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet result = null;
        List<VegetableDTO> list = null;
        try {
            connection = utils.getConnection();
            if (connection != null) {
                list = new ArrayList<>();
                pstm = connection.prepareStatement(SELECT_PRODUCTS);
                pstm.setNString(1, categoryID);
                result = pstm.executeQuery();
                while (result.next()) {
                    String id = result.getNString("id");
                    String name = result.getNString("productName");
                    String image = result.getNString("image");
                    float price = result.getFloat("price");
                    int quantity = result.getInt("quantity");
                    String category = result.getNString("categoryID");
                    VegetableDTO vegetable = new VegetableDTO(id, name, image, price, quantity, category);
                    list.add(vegetable);
                }
            }
        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            if (result != null) {
                result.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public List<VegetableDTO> searchProducts(String search) throws SQLException {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet result = null;
        List<VegetableDTO> list = null;
        try {
            connection = utils.getConnection();
            if (connection != null) {
                list = new ArrayList<>();
                pstm = connection.prepareStatement(SEARCH_PRODUCTS);
                pstm.setNString(1, "%" + search + "%");
                result = pstm.executeQuery();
                while (result.next()) {
                    String id = result.getNString("id");
                    String name = result.getNString("productName");
                    String image = result.getNString("image");
                    float price = result.getFloat("price");
                    int quantity = result.getInt("quantity");
                    String category = result.getNString("categoryID");
                    VegetableDTO vegetable = new VegetableDTO(id, name, image, price, quantity, category);
                    list.add(vegetable);
                }
            }
        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            if (result != null) {
                result.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }
}
