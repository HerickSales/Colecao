/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
