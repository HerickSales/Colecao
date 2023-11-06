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
import model.Carro;
import model.dao.CarroDaoJdbc;
import model.dao.DaoFactory;
import start.projetopadrao.App;

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
    private Button btnCancelar;
    @FXML
    private AnchorPane paneCadastro;
    @FXML
    private ToolBar toolbarCadastro;
    
    private static Carro carroSelecionado;

    public static Carro getCarroSelecionado() {
        return carroSelecionado;
    }

    public static void setCarroSelecionado(Carro carroSelecionado) {
        FormularioController.carroSelecionado = carroSelecionado;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(carroSelecionado != null) {
            txtNome.setText(carroSelecionado.getNome());
            txtPlaca.setText(carroSelecionado.getPlaca());
            txtKm.setText(String.valueOf(carroSelecionado.getKilometragem()));
            txtAno.setText(carroSelecionado.getAno());
            txtObs.setText(carroSelecionado.getObservacao());
        }
    }    

    @FXML
    private void btnEscolherFotoOnAction(ActionEvent event) {
    }

    @FXML
    private void btnSalvarOnAction(ActionEvent event) throws Exception {
        Carro carro = new Carro();
        carro.setNome(txtNome.getText());
        carro.setPlaca(txtPlaca.getText());
        carro.setKilometragem(Integer.getInteger(txtPlaca.getText()));
        carro.setAno(txtAno.getText());
        carro.setObservacao(txtObs.getText());

        CarroDaoJdbc dao = DaoFactory.novoCarroDao();
        if (carroSelecionado == null) {
            dao.incluir(carro);
        } else {
            carro.setId(carroSelecionado.getId());
            dao.editar(carro);
            carroSelecionado = null;
        }

        App.setRoot("Principal");
    }

    @FXML
    private void btnCancelarOnAction(ActionEvent event) throws Exception {
        App.setRoot("Principal");
    }
    
}
