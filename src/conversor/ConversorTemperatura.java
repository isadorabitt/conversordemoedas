package conversor;

import conversor.model.Temperatura;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author evandro
 */
public class ConversorTemperatura {
    
    public static double validarValorDeEntradaDoUsuario(String valor) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(); 
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat formatoNumerico = new DecimalFormat("000.00", symbols);
        formatoNumerico.setDecimalFormatSymbols(symbols);
        try {
            Number numeroFormatado = formatoNumerico.parse(valor);
            return numeroFormatado.doubleValue(); 
        } catch(ParseException e) {
            throw new IllegalArgumentException("Valor não é numérico ou está no formato incorreto (use a vírgula como separador decimal).");
        }
    }
    
    public static void exibirAlertaDeErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Mensagem");
        alert.setHeaderText("Erro!");
        alert.setContentText("Valor não é numérico, varifique!");
        alert.show();
    }
    
    public static double converterterValor(double valorDeEntrada, Temperatura tempOrigem, Temperatura tempDestino) {
       
        double valorConvertido = 0;
        
        if(tempOrigem.getId() == 1 && tempDestino.getId() == 2) {
            valorConvertido = (valorDeEntrada * 1.8) + 32;
            
        }else if(tempOrigem.getId() == 2 && tempDestino.getId() == 1) {
            valorConvertido = (valorDeEntrada - 32) / 1.8;
            
        }else if(tempOrigem.getId() == 1 && tempDestino.getId() == 3) {
            valorConvertido = valorDeEntrada + 273.15;
            
        }else if(tempOrigem.getId() == 3 && tempDestino.getId() == 1) {
            valorConvertido = valorDeEntrada - 273.15;
            
        }else if(tempOrigem.getId() == 2 && tempDestino.getId() == 3) { 
            valorConvertido = (valorDeEntrada + 459.67) / 1.8;
            
        }else if (tempOrigem.getId() == 3 && tempDestino.getId() == 2) {
            valorConvertido = (valorDeEntrada * 1.8) - 459.67;
                  
        }else {
            valorConvertido = valorDeEntrada;
        }
        
        return valorConvertido;
    }
    
    
    public static String formatarValorTemperatura(double valor) {
        
        DecimalFormat formatoTemperatura = new DecimalFormat("0.0 °C");
        String valorTemperatura = formatoTemperatura.format(valor);
        return valorTemperatura;
        
    }
    
    public static void fecharConversorTemperatura () {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mensagem");
        alert.setHeaderText("");
        alert.setContentText("Deseja Sair da Aplicação?!");
                
        ButtonType botaoSim = new ButtonType("Sim");
        ButtonType botaoNao = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
                
        alert.getButtonTypes().setAll(botaoSim, botaoNao);
                
        ButtonType resultado = alert.showAndWait().orElse(ButtonType.CANCEL);
                
        if(resultado == botaoSim) {
            Platform.exit();
        }
    }
}
