package com.uaspborestoran.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.uaspborestoran.App;
import com.uaspborestoran.data.Menu;
import com.uaspborestoran.data.Order;
import com.uaspborestoran.data.dataControl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PesanMenuController extends dataControl {

    @FXML
    private Label hargaMenu;

    @FXML
    private ImageView imgMenu;

    @FXML
    private Label namaMenu;

    @FXML
    private TableView<Menu> tbMenu;

    @FXML
    private Label lblUsn, lblIdMenu;

    String username = "";

    public void initialize() throws SQLException {
        lblUsn.setVisible(false);
        lblIdMenu.setVisible(false);

        TableColumn<Menu, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Menu, Integer> nama = new TableColumn<>("Menu");
        nama.setCellValueFactory(new PropertyValueFactory<>("nama"));

        tbMenu.getColumns().add(idColumn);
        tbMenu.getColumns().add(nama);

        tbMenu.setItems(getMenuList());
    }

    @FXML
    void btnPesan(ActionEvent event) throws SQLException, IOException {
        int iduser = getIdUser(lblUsn.getText());
        Menu menu = getMenu(Integer.parseInt(lblIdMenu.getText()));
        if (iduser >= 0) {
            pesanMenu(new Order(-1, iduser, Integer.parseInt(lblIdMenu.getText()), menu.getHarga()));

            Alert alert = new Alert(AlertType.INFORMATION, "Pesan Menu Berhasil!", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                App.setScene("mainmenu");
            }
        }
    }

    public void setUsername(String usn) {
        lblUsn.setText(usn);
    }

    @FXML
    void tbMenuSelected(MouseEvent event) throws SQLException {
        if (tbMenu.getSelectionModel().getSelectedItem() != null) {
            Menu selectedList = tbMenu.getSelectionModel().getSelectedItem();
            Menu selectedMenu = getMenu(selectedList.getId());

            lblIdMenu.setText(String.valueOf(selectedMenu.getId()));
            namaMenu.setText(selectedMenu.getNama());
            hargaMenu.setText("Rp" + selectedMenu.getHarga());
            Image img = new Image(getClass().getResource("/com/uaspborestoran/img/" + selectedMenu.getPhoto()).toExternalForm());
            imgMenu.setImage(img);
        }
    }

}
