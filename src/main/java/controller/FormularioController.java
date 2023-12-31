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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private Button btnSalvar;
    @FXML
    private Button btnCancelar;
    @FXML
    private AnchorPane paneCadastro;
    @FXML
    private RadioButton radDisponivel;
    @FXML
    private RadioButton radAlugado;
    @FXML
    private RadioButton radManutencao;
    @FXML
    private ToggleGroup statusGroup;
    @FXML
    private ImageView iconGaleria;
    
    private static Carro carroSelecionado;
    private static Carro carroNovo;

    public static Carro getCarroSelecionado() {
        return carroSelecionado;
    }

    public static void setCarroSelecionado(Carro carroSelecionado) {
        FormularioController.carroSelecionado = carroSelecionado;
    }
    @FXML
    private Label lblErro;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carroNovo = new Carro();
        carroNovo.setStatus("Disponivel");
        File f=new File("./Files/icons/galeria.png");
        String iconPath= f.toURI().toString();
        Image icon= new Image(iconPath);
        iconGaleria.setImage(icon);
        
        radDisponivel.setToggleGroup(statusGroup);
        radAlugado.setToggleGroup(statusGroup);
        radManutencao.setToggleGroup(statusGroup);
     
        statusGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == radDisponivel){
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
            switch (carroSelecionado.getStatus()) {
                case "Disponivel":
                    statusGroup.selectToggle(radDisponivel);
                    break;
                case "Manutenção":
                    statusGroup.selectToggle(radManutencao);
                    break;
                default:
                    statusGroup.selectToggle(radAlugado);
                    break;
            }
            
            pathImage=carroSelecionado.getFoto();
            Image image= new Image(carroSelecionado.getFoto());
            imgCarro.setImage(image);
            
        }
    }    


    @FXML
    private void btnSalvarOnAction(ActionEvent event) throws Exception {
        try{
        carroNovo.setNome(txtNome.getText());
        carroNovo.setPlaca(txtPlaca.getText());
        carroNovo.setKilometragem(Integer.parseInt(txtKm.getText()));
        carroNovo.setAno(txtAno.getText());
        carroNovo.setObservacao(txtObs.getText());
        if(pathImage==null){
            lblErro.setText("Por favor escolha uma foto");
            return;
        }
        carroNovo.setFoto(pathImage);
            CarroDaoJdbc dao = DaoFactory.novoCarroDao();
            
            if (carroSelecionado == null) {
                dao.incluir(carroNovo);
            }else {
                carroNovo.setId(carroSelecionado.getId());
                dao.editar(carroNovo);
                carroSelecionado = null;
            }
        }catch(NumberFormatException e){
            lblErro.setText("Por favor, Confira os campos do Formulario");
            return;
        }catch(Exception e){
            lblErro.setText("Ocorreu um erro.");
            return;
        }            
        App.setRoot("Principal");
    }

    @FXML
    private void btnCancelarOnAction(ActionEvent event) throws Exception {
        App.setRoot("Principal");
    }

    @FXML
    private void galeriaOnClick(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione uma imagem");
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG (.jpeg)", ".jpg"),
                new FileChooser.ExtensionFilter("PNG (.png)", "*png"), new FileChooser.ExtensionFilter("All images", "*jpg",".png"));
        File selectedFile = fileChooser.showOpenDialog(iconGaleria.getScene().getWindow());
        if(selectedFile != null){
            pathImage=selectedFile.toURI().toString();
            System.out.println(pathImage);
            Image image = new Image(pathImage);
            imgCarro.setImage(image);
            
        }else{
            lblErro.setText("Nenhum arquivo Selecionado");
        }
        
    }
    }
    

