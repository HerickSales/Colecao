/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
    
    private String pathImage;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtPlaca;
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
    @FXML
    private RadioButton radDisponivel;
    @FXML
    private RadioButton radAlugado;
    @FXML
    private RadioButton radManutencao;
    @FXML
    private ToggleGroup statusGroup;
    
    private static Carro carroSelecionado;
    private static Carro carroNovo;

    public static Carro getCarroSelecionado() {
        return carroSelecionado;
    }

    public static void setCarroSelecionado(Carro carroSelecionado) {
        FormularioController.carroSelecionado = carroSelecionado;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carroNovo = new Carro();
        radDisponivel.setToggleGroup(statusGroup);
        radAlugado.setToggleGroup(statusGroup);
        radManutencao.setToggleGroup(statusGroup);
        
        statusGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == radDisponivel) {
                carroNovo.setStatus("Disponivel");
            } else if(newValue == radAlugado) {
                carroNovo.setStatus("Alugado");
            } else if(newValue == radManutencao) {
                carroNovo.setStatus("Manutenção");
            }
        }); 
        
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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione uma imagem");
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG (.jpeg)", ".jpg"),
                new FileChooser.ExtensionFilter("PNG (.png)", "*png"), new FileChooser.ExtensionFilter("All images", "*jpg",".png"));
        File selectedFile = fileChooser.showOpenDialog(btnFoto.getScene().getWindow());
        if(selectedFile != null){
            pathImage=selectedFile.toURI().toString();
            System.out.println(pathImage);
            Image image = new Image(pathImage);
            imgCarro.setImage(image);
            
        }else{
            System.out.println("Nenhum arquivo foi selecionado");
        }
        
    }

    @FXML
    private void btnSalvarOnAction(ActionEvent event) throws Exception {
        carroNovo.setNome(txtNome.getText());
        carroNovo.setPlaca(txtPlaca.getText());
        carroNovo.setKilometragem(Integer.parseInt(txtKm.getText()));
        carroNovo.setAno(txtAno.getText());
        carroNovo.setObservacao(txtObs.getText());
        if(pathImage==null){
            Alert alert= new Alert(AlertType.WARNING);
            alert.setTitle("ATENÇÃO");
            alert.setContentText("IMAGEM NAO FOI ESCOLHIDA");
            alert.showAndWait();
            return;
        }
        carroNovo.setFoto(pathImage);
        
        CarroDaoJdbc dao = DaoFactory.novoCarroDao();
        if (carroSelecionado == null) {
            dao.incluir(carroNovo);
        } else {
            carroNovo.setId(carroSelecionado.getId());
            dao.editar(carroNovo);
            carroSelecionado = null;
        }

        App.setRoot("Principal");
    }

    @FXML
    private void btnCancelarOnAction(ActionEvent event) throws Exception {
        App.setRoot("Principal");
    }
    
}
