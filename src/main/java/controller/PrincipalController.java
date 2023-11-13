package controller;

import java.io.File;
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
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private AnchorPane apPainel;
    @FXML
    private ImageView iconLupa;
    
    @FXML
    private Label lblErro;

    
    private List<Carro> listaCarros;
    private ObservableList<Carro> observableListCarros;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarCarros("");
        File f=new File("./Files/icons/lupa.png");
        String iconPath= f.toURI().toString();
        Image icon= new Image(iconPath);
        iconLupa.setImage(icon);
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
            lblErro.setText("Por favor escolha um item para ser selecionado");
        }
    }


    @FXML
    private void btnExcluirOnAction(ActionEvent event) throws Exception {
        Alert alerta= new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("ATENÇÃO");
        alerta.setContentText("Tem certeza que deseja excluir este Item? ");
        alerta.showAndWait();
        if(alerta.getResult()== ButtonType.OK){
            try{
                Carro carroSelecionado=  tblCarros.selectionModelProperty().getValue().getSelectedItem();
                CarroDaoJdbc dao= DaoFactory.novoCarroDao();
                dao.excluir(carroSelecionado);
                limpaCampos();
                carregarCarros("");
            }catch(NullPointerException e){
                lblErro.setText("Por favor escolha um item para ser excluido");
            }catch(Exception e){
                lblErro.setText("ocorreu um erro");
            }
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
            lblErro.setText("Erro carregar dados");
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
        lblErro.setText("");
        imgCarro.setImage(null);
    
    }

    @FXML
    private void btnEstatisticasOnClick(ActionEvent event) throws IOException {
        App.setRoot("Estatistica");
    }

    @FXML
    private void painelOnClick(MouseEvent event) {
        tblCarros.getSelectionModel().clearSelection();
        limpaCampos();
    }
}
