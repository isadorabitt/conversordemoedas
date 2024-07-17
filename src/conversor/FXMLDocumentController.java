/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package conversor;


import conversor.model.Moeda;
import conversor.model.Temperatura;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author evandro
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private ComboBox<Moeda> cbmDeMoedas;
    @FXML
    private ComboBox<Moeda> cbmParaMoedas;
    @FXML
    private Label lblMostrarValorAConverter;
    @FXML
    private Label lblValorConvertido;
    @FXML
    private TextField txtValorAConverter;
    @FXML
    private Label lblDeMoeda;
    @FXML
    private Label lblParaMoeda;
    @FXML
    private ComboBox<Temperatura> cbmTempOrigem;
    @FXML
    private ComboBox<Temperatura> cbmTempDestino;
    @FXML
    private TextField txtValorTempAConverter;
    @FXML
    private Label lblTempAConverter;    
    @FXML    private Label lblTempConvertida;   
    @FXML
    private Label lblTempOrigem;    
    @FXML
    private Label lblTempDestino;
    private List<Moeda> moedas = new ArrayList<>();  
    private ObservableList<Moeda> obsMoedas; 
    private List<Temperatura> temperaturas = new ArrayList<>();
    private ObservableList<Temperatura> obsTemperaturas;
    
    public void carregarMoedas() {    
        String[][] dadosMoedas = {
            {"1", "Real", "USD/BRL", "0.100", "pt-br"},
            {"2", "Dólar", "USA/USD","4.80", "en-US" },
            {"3", "Euro", "Euro/EUR", "5.38", "en-EU"},
            {"4", "Libras Esterlinas", "Libra/GBP","6.29", "en-GB"},
            {"5", "Peso argentino", "Peso/ARS", "0.018", "es-AR"},
            {"6", "Peso Chileno", "Peso/CLP", "0.0059", "es-CL"}
        };
        for(String[] dados: dadosMoedas) {
            int id = Integer.parseInt(dados[0]);
            String nome = dados[1];
            String codigoMoeda = dados[2];
            String cotacao = dados[3];
            String local = dados[4];
            Moeda moeda = new Moeda(id, nome, codigoMoeda, cotacao, local);
            moedas.add(moeda);        
        }
        obsMoedas = FXCollections.observableArrayList(moedas);
        cbmDeMoedas.setItems(obsMoedas);
        cbmParaMoedas.setItems(obsMoedas);   
    } 
    public void carregarTemperaturas() {
        String[][] dadosTemperaturas = {
            {"1", "Grau Celsius"},
            {"2", "Grau Fahrenheit"},
            {"3", "Kelvin"}
        };
        for(String[] dados: dadosTemperaturas) {
           int id = Integer.parseInt(dados[0]);
            String escala = dados[1];
            Temperatura temperatura = new Temperatura(id, escala);
            temperaturas.add(temperatura);        
        }
        obsTemperaturas = FXCollections.observableArrayList(temperaturas);
        cbmTempOrigem.setItems(obsTemperaturas);
        cbmTempDestino.setItems(obsTemperaturas);
   }
    
    @FXML
    public void btnConverter(ActionEvent event) {
        String valor = txtValorAConverter.getText();
        try {
            double valorAConverter = ConversorMoeda.validarValorDeEntradaDoUsuario(valor);
            Moeda primeiraMoedaSelecionada = cbmDeMoedas.getSelectionModel().getSelectedItem();
            Moeda segundaMoedaSelecionada = cbmParaMoedas.getSelectionModel().getSelectedItem();
            double valorConvertido = ConversorMoeda.converterValor(valorAConverter, primeiraMoedaSelecionada, segundaMoedaSelecionada);
            lblMostrarValorAConverter.setText(ConversorMoeda.formatarValorMonetario(valorAConverter, Locale.forLanguageTag(primeiraMoedaSelecionada.getLocal())));
            lblDeMoeda.setText(primeiraMoedaSelecionada.getCodigoMoeda());
            lblParaMoeda.setText(segundaMoedaSelecionada.getCodigoMoeda());
            lblValorConvertido.setText(ConversorMoeda.formatarValorMonetario(valorConvertido, Locale.forLanguageTag(segundaMoedaSelecionada.getLocal())));
        } catch (Exception e) {
            ConversorMoeda.exibirAlertaDeErro(e.getMessage());
        }  
    }
    
    @FXML
    public void btnConverterTemperatura(ActionEvent event) {
        
        try {
            String valor = txtValorTempAConverter.getText();
            double valorTempAConverter = ConversorTemperatura.validarValorDeEntradaDoUsuario(valor);
            Temperatura tempOrigem = cbmTempOrigem.getSelectionModel().getSelectedItem();
            Temperatura tempDestino = cbmTempDestino.getSelectionModel().getSelectedItem();
            double valorTempConvertido = ConversorTemperatura.converterterValor(valorTempAConverter, tempOrigem, tempDestino);
            lblTempAConverter.setText(ConversorTemperatura.formatarValorTemperatura(valorTempAConverter));
            lblTempOrigem.setText(tempOrigem.getEscala());
            lblTempDestino.setText(tempDestino.getEscala());
            lblTempConvertida.setText(ConversorTemperatura.formatarValorTemperatura(valorTempConvertido));
        } catch (Exception e) {
            ConversorTemperatura.exibirAlertaDeErro(e.getMessage());
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarMoedas();
        carregarTemperaturas();
    }    

    @FXML
    void btnSairConversorMoeda(ActionEvent event) {
        ConversorMoeda.fecharConversorMoeda();
    }

    @FXML
    void btnSairConversorTemperatura(ActionEvent event) {
        ConversorTemperatura.fecharConversorTemperatura();
    }
}
