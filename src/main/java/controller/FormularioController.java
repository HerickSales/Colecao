/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author heric
 */
public class FormularioController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtPlaca;
    @FXML
    private TextField txtStatus;
    @FXML
    private TextField txtKm;
    @FXML
    private TextField txtAno;
    @FXML
    private TextField txtObs;
    @FXML
    private ImageView imgCarro;
    @FXML
    private Button btnFoto;
    @FXML
    private Button btnSalvar;
    @FXML
    private AnchorPane paneCadastro;
    @FXML
    private ToolBar toolbarCadastro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnEscolherFotoOnAction(ActionEvent event) {
    }

    @FXML
    private void btnSalvarOnAction(ActionEvent event) {
    }
    
}
