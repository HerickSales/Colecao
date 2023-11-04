/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import start.projetopadrao.App;

/**
 * FXML Controller class
 *
 * @author lefoly
 */
public class PrincipalController implements Initializable {

    @FXML
    private TextField txtFiltro;
    @FXML
    private Button btnLimpar;
    @FXML
    private Button btnIncluir;
    @FXML
    private TableColumn<?, ?> tblNome;
    @FXML
    private TableColumn<?, ?> tblStatus;
    @FXML
    private TableColumn<?, ?> tblPlaca;
    @FXML
    private ImageView imgCarro;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Label lblKm;
    @FXML
    private Label lblAno;
    @FXML
    private Label lblObs;
    @FXML
    private Label lblDisponives;
    @FXML
    private Label lblAlugados;
    @FXML
    private Label lblManutencao;
    @FXML
    private Label lblTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnIncluirOnAction(ActionEvent event) throws IOException {
        App.setRoot("Formulario");
    }

    @FXML
    private void btnEditarOnAction(ActionEvent event) throws IOException {
        App.setRoot("Formulario");
    }
    
}
