package com.uaspborestoran.controller;

import com.uaspborestoran.data.Menu;
import com.uaspborestoran.data.dataControl;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

// import com.mysql.cj.jdbc.exceptions.SQLError;
import com.uaspborestoran.App;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DashboardController extends dataControl {

    @FXML
    private Button btnBatal;

    @FXML
    private Button btnHapus;

    @FXML
    private Button btnSimpan;

    @FXML
    private Button btnUbah;

    @FXML
    private ComboBox<String> cbUkuran;

    @FXML
    private RadioButton rb1;

    @FXML
    private RadioButton rb2;
    
    @FXML
    private RadioButton rb3;

    @FXML
    private TextField tbDeskripsi;

    @FXML
    private TextField tbHarga;

    @FXML
    private TextField tbNama;

    @FXML
    private TableView<Menu> tableMenu;

    @FXML
    private Button btnlogout;
    
    @FXML
    private ImageView cover;

    ToggleGroup group = new ToggleGroup();
    Menu selectedList;
    int idMenu;
    String coverFileName;

    public void initialize() throws SQLException {
        cbUkuran.getItems().addAll("Kecil", "Sedang", "Besar");
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        rb3.setToggleGroup(group);

        TableColumn<Menu, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Menu, String> naColumn = new TableColumn<>("Nama");
        naColumn.setCellValueFactory(new PropertyValueFactory<>("nama"));

        TableColumn<Menu, Double> harColumn = new TableColumn<>("Harga");
        harColumn.setCellValueFactory(new PropertyValueFactory<>("harga"));

        TableColumn<Menu, String> desColumn = new TableColumn<>("Deskripsi");
        desColumn.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));

        TableColumn<Menu, String> ukColumn = new TableColumn<>("Ukuran");
        ukColumn.setCellValueFactory(new PropertyValueFactory<>("ukuran"));

        TableColumn<Menu, String> katColumn = new TableColumn<>("Kategori");
        katColumn.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        
        TableColumn<Menu, String> photoColumn = new TableColumn<>("Photo");
        photoColumn.setCellValueFactory(new PropertyValueFactory<>("photo"));

        tableMenu.getColumns().add(idColumn);
        tableMenu.getColumns().add(naColumn);
        tableMenu.getColumns().add(harColumn);
        tableMenu.getColumns().add(desColumn);
        tableMenu.getColumns().add(ukColumn);
        tableMenu.getColumns().add(katColumn);
        tableMenu.getColumns().add(photoColumn);

        tableMenu.setItems(getMenuList());
    }

    @FXML
    void batal(ActionEvent event) throws IOException {
        App.setRoot("dashboard");
    }

    @FXML
    void hapusMenu(ActionEvent event) throws Exception {
        if (idMenu >= 0) {
            delMenu(idMenu);
            idMenu = -1;
            App.setRoot("dashboard");

            Alert alert = new Alert(AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                App.setRoot("mainmenu");
            }
        }
    }

    @FXML
    void simpanMenu(ActionEvent event) throws Exception {
        String kategori = "";
        if (rb1.isSelected()) {
            kategori = "Makanan";
        } else if (rb2.isSelected()) {
            kategori = "Minuman";
        } else if (rb3.isSelected()) {
            kategori = "Snack";
        }

        if (createMenu(new Menu(-1, tbNama.getText(), tbDeskripsi.getText(), cbUkuran.getValue(), Double.parseDouble(tbHarga.getText()), kategori, coverFileName))) {
            App.setRoot("dashboard");

        Alert alert = new Alert(AlertType.INFORMATION, "Data berhasil ditambah", ButtonType.OK);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            App.setRoot("dashboard");   
            }
        } 
    }

    @FXML
    void ubahMenu(ActionEvent event) throws Exception {
        String kategori;
        if (rb1.isSelected()) {
            kategori = "Makanan";
        } else if (rb2.isSelected()) {
            kategori = "Minuman";
        } else {
            kategori = "Snack";
        }
        updateMenu(new Menu(idMenu, tbNama.getText(), tbDeskripsi.getText(), cbUkuran.getValue(), Double.parseDouble(tbHarga.getText()), kategori, coverFileName));
        idMenu = -1;
        App.setRoot("dashboard");

        Alert alert = new Alert(AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            App.setRoot("dashboard");
        }
    }

    @FXML
    void tbMenuSelected(MouseEvent event) {
        if (tableMenu.getSelectionModel().getSelectedItem() != null) {
            btnUbah.setDisable(false);
            btnHapus.setDisable(false);
            Menu selectedList = tableMenu.getSelectionModel().getSelectedItem();
            // setMenuData(selectedList);
            idMenu = selectedList.getId();
            tbNama.setText(selectedList.getNama());
            tbHarga.setText(String.valueOf(selectedList.getHarga()));
            tbDeskripsi.setText(selectedList.getDeskripsi());
            cbUkuran.setValue(selectedList.getUkuran());

            coverFileName = selectedList.getPhoto();
            Image image = new Image(getClass().getResource("/com/uaspborestoran/img/" + selectedList.getPhoto()).toExternalForm());
            cover.setImage(image);
    
            if (selectedList.getKategori().equals(rb1.getText())) {
                rb1.setSelected(true);
            } else if (selectedList.getKategori().equals(rb2.getText())) {
                rb2.setSelected(true);
            } else {
                rb3.setSelected(true);
            }
        }
    }

    @FXML
    void browsepic(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(
                        "Image Files", "*.png", "*.jpg", "*.jpeg"));
        java.io.File file = fileChooser.showOpenDialog((Stage) App.scene.getWindow());
        if (file != null) {
            try {
                Image image = new Image(new FileInputStream(file));
                cover.setImage(image);
                coverFileName = file.getName();

                Path currentRelativePath = Paths.get("");
                String projectDir = currentRelativePath.toAbsolutePath().toString();

                Path targetDirectory = Paths.get(projectDir, "src", "main", "resources", "com", "uaspborestoran", "img");
                Path targetFile = targetDirectory.resolve(coverFileName);

                Files.copy(file.toPath(), targetFile);
            } catch (Exception e) {
                System.err.println("File not found: " + e.getMessage());
            }
        }
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        App.setScene("login");
    }
}