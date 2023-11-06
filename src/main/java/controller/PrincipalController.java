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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.Carro;
import model.dao.CarroDaoJdbc;
import model.dao.DaoFactory;
import start.projetopadrao.App;

public class PrincipalController implements Initializable {

    @FXML
    private TextField txtFiltro;
    @FXML
    private Button btnLimpar;
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
    private Label lblDisponives;
    @FXML
    private Label lblAlugados;
    @FXML
    private Label lblManutencao;
    @FXML
    private Label lblTotal;
    
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
        App.setRoot("Formulario");
    }

    @FXML
    private void btnLimparOnAction(ActionEvent event) {
    }

    @FXML
    private void btnExcluirOnAction(ActionEvent event) {
    }
    
    private void carregarCarros(String param) {
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
    
}
