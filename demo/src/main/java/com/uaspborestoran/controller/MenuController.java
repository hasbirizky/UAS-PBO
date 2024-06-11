package com.uaspborestoran.controller;

import com.uaspborestoran.data.Menu;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuController {
    @FXML
    private Label lblHarga;

    @FXML
    private Label lblJenis;

    @FXML
    private Label lblNama;

    @FXML
    private ImageView menuPhoto;

    public void setData(Menu menu) {
        Image image = new Image(getClass().getResource("/com/uaspborestoran/img/" + menu.getPhoto()).toExternalForm());
        menuPhoto.setImage(image);
        lblHarga.setText("Rp. " + menu.getHarga());
        lblJenis.setText(menu.getKategori());
        lblNama.setText(menu.getNama());
    }
}
