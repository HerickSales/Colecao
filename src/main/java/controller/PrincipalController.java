package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Carro;
import model.dao.CarroDaoJdbc;
import model.dao.DaoFactory;
import start.projetopadrao.App;

public class PrincipalController implements Initializable {

    @FXML
    private TextField txtFiltro;
    @FXML
    private Button btnIncluir;
    @FXML
    private TableView<Carro> tblCarros;
    @FXML
    private TableColumn<Carro, String> tblNome;
    @FXML
    private TableColumn<Carro, String> tblStatus;
    @FXML
    private TableColumn<Carro, String> tblPlaca;
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
    
    private List<Carro> listaCarros;
    private ObservableList<Carro> observableListCarros;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarCarros("");
    }    

    @FXML
    private void btnIncluirOnAction(ActionEvent event) throws IOException {
        App.setRoot("Formulario");
    }

    @FXML
    private void btnEditarOnAction(ActionEvent event) throws IOException {
        Carro carroSelecionado= tblCarros.selectionModelProperty().getValue().getSelectedItem();
        if(carroSelecionado != null){
            FormularioController.setCarroSelecionado(carroSelecionado);
            App.setRoot("Formulario");
        }else{
            Alert alerta= new Alert(AlertType.WARNING);
            alerta.setTitle("Erro");
            alerta.setContentText("Nenhum item selecionado");
        }
    }


    @FXML
    private void btnExcluirOnAction(ActionEvent event) throws Exception {
        Alert alerta= new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("ATENÇÃO");
        alerta.setContentText("Tem certeza que deseja excluir este Item? ");
        alerta.showAndWait();
        if(alerta.getResult()== ButtonType.OK){
            Carro carroSelecionado=  tblCarros.selectionModelProperty().getValue().getSelectedItem();
            CarroDaoJdbc dao= DaoFactory.novoCarroDao();
            dao.excluir(carroSelecionado);
            limpaCampos();
            carregarCarros("");
        }   
    }
    
    public void carregarCarros(String param) {
        tblNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tblStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        tblPlaca.setCellValueFactory(new PropertyValueFactory<>("Placa"));
        
        try {
            CarroDaoJdbc dao = DaoFactory.novoCarroDao();
            listaCarros = dao.listar(param);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        observableListCarros = FXCollections.observableArrayList(listaCarros);
        tblCarros.setItems(observableListCarros);
    }   

    @FXML
    private void tblCarrosOnClick(MouseEvent event) {
        var carroSelecionado = tblCarros.selectionModelProperty().getValue().getSelectedItem();
        lblKm.setText(String.valueOf(carroSelecionado.getKilometragem()));
        lblAno.setText(String.valueOf(carroSelecionado.getAno()));
        lblObs.setText(String.valueOf(carroSelecionado.getObservacao()));
        Image image = new Image(carroSelecionado.getFoto());
        imgCarro.setImage(image);
    }


    @FXML
    private void onTyped(KeyEvent event) throws Exception {
        carregarCarros(txtFiltro.getText());   
    }
    
    private void limpaCampos(){
        lblKm.setText("");
        lblAno.setText("");
        lblObs.setText("");
        imgCarro.setImage(null);
    
    }

    @FXML
    private void btnEstatisticasOnClick(ActionEvent event) throws IOException {
        App.setRoot("Estatistica");
    }
}
