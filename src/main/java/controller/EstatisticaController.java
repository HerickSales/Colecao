/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Carro;
import model.dao.CarroDaoJdbc;
import model.dao.DaoFactory;
import start.projetopadrao.App;

public class EstatisticaController implements Initializable {

    @FXML
    private PieChart pcEstatistica;
    
    private List<Carro> listaCarros;
    @FXML
    private Button btnVoltar;
    @FXML
    private Label lblAlugados;
    @FXML
    private Label lblDisponiveis;
    @FXML
    private Label lblManutencao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            CarroDaoJdbc dao = DaoFactory.novoCarroDao();
            listaCarros = dao.listar("");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            var alugadosCount = sortearPorStatus("Alugado");
            var disponiveisCount = sortearPorStatus("Disponivel");
            var manutencaoCount = sortearPorStatus("Manutenção");
            
            PieChart.Data alugados = new PieChart.Data("Alugados", alugadosCount);
            PieChart.Data disponiveis = new PieChart.Data("Disponiveis", disponiveisCount);
            PieChart.Data manutencao = new PieChart.Data("Em Manutenção", manutencaoCount);
            
            lblAlugados.setText(String.valueOf(alugadosCount));
            lblDisponiveis.setText(String.valueOf(disponiveisCount));
            lblManutencao.setText(String.valueOf(manutencaoCount));
            
            pcEstatistica.getData().addAll(alugados, disponiveis, manutencao);
        }
    } 
    
    private int sortearPorStatus(String status) {
        int counter = 0;
        
        for(int i=0; i<listaCarros.size(); i++) {
            if(listaCarros.get(i).getStatus().equals(status)) {
                counter++;
            }
        }
            
        return counter;
    }

    @FXML
    private void btnVoltarOnAction(ActionEvent event) throws Exception {
        App.setRoot("Principal");
    }
}
