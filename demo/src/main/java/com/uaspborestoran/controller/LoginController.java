package com.uaspborestoran.controller;

import java.io.IOException;

import com.uaspborestoran.App;
import com.uaspborestoran.data.User;
import com.uaspborestoran.data.dataControl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends dataControl {
    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField tbPwd;

    @FXML
    private TextField tbUsn;

    @FXML
    private Button btnReg;

    @FXML
    private TextField tbName;

    @FXML
    private PasswordField tbPwdR;

    @FXML
    private TextField tbUsnR;

    @FXML
    private Label lblNotif;

    @FXML
    void login(ActionEvent event) throws Exception {
        if (tbUsn.getText().equals("admin") && tbPwd.getText().equals("123")) {
            App.setScene("dashboard");
        } else if (getUserLogin(tbUsn.getText(), tbPwd.getText())) {
            MainMenuController.username = tbUsn.getText();
            App.setScene("mainmenu");
        } else{
            Alert alert = new Alert(AlertType.INFORMATION, "Username atau Password anda salah!", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                App.setScene("login");
            }
        }
    }

    @FXML
    void linkLogin(ActionEvent event) throws IOException {
        App.setRoot("login");
    }
    
    @FXML
    void linkRegis(ActionEvent event) throws IOException {
        App.setRoot("regis");
    }

    @FXML
    void registration(ActionEvent event) throws Exception {
        switch (createUser(new User(-1, tbName.getText(), tbUsnR.getText(), tbPwdR.getText()))) {
            case 0:
                App.setRoot("login");
                break;
            case 1:
                lblNotif.setText("Username tidak tersedia");
                lblNotif.setVisible(true);
                break;
            case 2:
                lblNotif.setText("Registrasi gagal");
                lblNotif.setVisible(true);
                break;
            default:
                lblNotif.setText("Something went wrong");
                lblNotif.setVisible(true);
                break;
        }
    }
}
