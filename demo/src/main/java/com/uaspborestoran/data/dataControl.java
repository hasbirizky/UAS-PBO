package com.uaspborestoran.data;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.uaspborestoran.App;
import com.uaspborestoran.service.connection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class dataControl {

    protected Boolean createMenu(Menu menu) throws Exception {
        String query = "INSERT INTO menu (nama, harga, deskripsi, kategori, ukuran, photo) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.conn.prepareStatement(query)) {
            preparedStatement.setString(1, menu.getNama());
            preparedStatement.setDouble(2, menu.getHarga());
            preparedStatement.setString(3, menu.getDeskripsi());
            preparedStatement.setString(4, menu.getKategori());
            preparedStatement.setString(5, menu.getUkuran());
            preparedStatement.setString(6, menu.getPhoto());
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
            return false;
        }
    }

    protected ObservableList<Menu> getMenuList() throws SQLException {
        if (connection.conn == null) {
            connection.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbuasrestoran", "root", "");
        }
        ObservableList<Menu> menus = FXCollections.observableArrayList();
        String query = "SELECT * FROM menu";
        try (Statement stmt = connection.conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                menus.add(new Menu(rs.getInt("id"),
                                rs.getString("nama"),
                                rs.getString("deskripsi"),
                                rs.getString("ukuran"),
                                rs.getDouble("harga"),
                                rs.getString("kategori"),
                                rs.getString("photo")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }

    protected void updateMenu(Menu menu) throws Exception {
        String query = "UPDATE menu SET nama = ?, harga = ?, deskripsi = ?, kategori = ?, ukuran = ?, photo = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.conn.prepareStatement(query)) {
            preparedStatement.setString(1, menu.getNama());
            preparedStatement.setDouble(2, menu.getHarga());
            preparedStatement.setString(3, menu.getDeskripsi());
            preparedStatement.setString(4, menu.getKategori());
            preparedStatement.setString(5, menu.getUkuran());
            preparedStatement.setString(6, menu.getPhoto());
            preparedStatement.setInt(7, menu.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    protected void delMenu (int id) throws Exception {
        String query = "DELETE FROM menu WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    protected int createUser(User user) throws Exception {
        String query = "SELECT * FROM user WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.conn.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsn());
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return 1;
                }
            }
        }
        query = "INSERT INTO user (name, username, password) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.conn.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsn());
            preparedStatement.setString(3, user.getPw());
            preparedStatement.executeUpdate();
            // getUser(user.getUsn(), user.getPw());
            return 0;
        } catch (SQLException e) {
            System.err.println(e);
            e.printStackTrace();
            return 2;
        }
    }

    protected User getUser(String usn) throws Exception {
        User u = new User(-1, null, null, null);
        String query = "SELECT * FROM user WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.conn.prepareStatement(query)) {
            preparedStatement.setString(1, usn);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    u = new User(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"));
                    return u;
                }
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        }
        return u;
    }

    protected Boolean getUserLogin(String usn, String pw) throws Exception {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.conn.prepareStatement(query)) {
            preparedStatement.setString(1, usn);
            preparedStatement.setString(2, pw);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        }
        return false;
    }

    protected ArrayList<Menu> getMenus() {
        ArrayList<Menu> menus = new ArrayList<>();
        String query = "SELECT * FROM menu";
        try (PreparedStatement preparedStatement = connection.conn.prepareStatement(query)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    App.menu = new Menu(rs.getInt("id"), rs.getString("nama"), rs.getString("deskripsi"), rs.getString("ukuran"), rs.getDouble("harga"), rs.getString("kategori"), rs.getString("photo"));
                    // menus.add(new Menu(rs.getInt("id"), rs.getString("nama"), rs.getString("deskripsi"), rs.getString("ukuran"), rs.getDouble("harga"), rs.getString("kategori"), rs.getString("photo")));
                    menus.add(App.menu);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menus;
    }

    protected void pesanMenu(Order order) throws SQLException {
        String query = "INSERT INTO `order` (iduser, idmenu, total) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.conn.prepareStatement(query)) {
            preparedStatement.setInt(1, order.getIduser());
            preparedStatement.setInt(2, order.getIdmenu());
            preparedStatement.setDouble(3, order.getTotal());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected int getIdUser(String usn) throws SQLException {
        int id = -1;
        String query = "SELECT id FROM user WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.conn.prepareStatement(query)) {
            preparedStatement.setString(1, usn);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        }
        return id;
    }

    protected Menu getMenu(int id) throws SQLException {
        String query = "SELECT * FROM menu WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    App.menu = new Menu(rs.getInt("id"),
                                        rs.getString("nama"),
                                        rs.getString("deskripsi"),
                                        rs.getString("ukuran"),
                                        rs.getDouble("harga"),
                                        rs.getString("kategori"),
                                        rs.getString("photo"));
                }
            }
        }
        return App.menu;
    }
}
