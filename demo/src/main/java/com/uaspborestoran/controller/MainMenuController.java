package com.uaspborestoran.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.uaspborestoran.data.dataControl;
import com.uaspborestoran.App;
import com.uaspborestoran.data.Menu;
import com.uaspborestoran.data.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
// import javafx.scene.control.Menu;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainMenuController extends dataControl implements Initializable {
    private ArrayList<Menu> menus;
    static String username = "";
    int id = -1;

    @FXML
    private Label lblWelcome;

    @FXML
    private GridPane menuContainer;

    @FXML
    private VBox mainVBox;

    @FXML
    private ScrollPane sPane;

    public void initialize(URL location, ResourceBundle resources) {
        try {
            User u = getUser(username);
            if (u.getId() > 0) {
                lblWelcome.setText("Halo, " + u.getName() + "!");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        menus = getMenus();
        int col = 0;
        int row = 1;
        try {
            for (Menu m : menus) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(App.class.getResource("menu.fxml"));
                VBox menuBox = fxmlLoader.load();
                MenuController menuController = fxmlLoader.getController();
                menuController.setData(m);

                if (col == 3) {
                    col = 0;
                    ++row;
                }

                menuContainer.add(menuBox, col++, row);
                GridPane.setMargin(menuBox, new Insets(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        username = null;
        App.setScene("login");
    }

    @FXML
    void btnPesan(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/uaspborestoran/pesanmenu.fxml"));
            HBox pesanMenu = fxmlLoader.load();
            PesanMenuController pesanMenuController = fxmlLoader.getController();
            pesanMenuController.setUsername(username);

            sPane.setVisible(false);

            mainVBox.getChildren().addAll(pesanMenu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnMenu(ActionEvent event) throws IOException {
        App.setScene("mainmenu");
    }
}
