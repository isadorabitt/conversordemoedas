package conversor;


import conversor.model.Moeda;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author evandro
 */
public class ConversorMoeda {
    public static double converterValor(double valorDeEntrada, Moeda valorOrigem, Moeda valorDestino) {
      double cotacaoOrigem =  Double.parseDouble(valorOrigem.getCotacao());
      double cotacaoDestino = Double.parseDouble(valorDestino.getCotacao());
      double valorConvertido = 0;
      if(valorOrigem.getId() == 1) {
        valorConvertido = valorDeEntrada / cotacaoDestino;
      }else if(valorDestino.getId() == 1) {
        valorConvertido = valorDeEntrada * cotacaoOrigem;
      }
      return valorConvertido;
    }
    
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
     
    public static void exibirAlertaDeErro(String mensagem){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Título");
        alert.setHeaderText("Erro!");
        alert.setContentText(mensagem);
        alert.show();
    }
    
    public static String formatarValorMonetario(double valor, Locale locale) {
        NumberFormat formatoMonetario = NumberFormat.getCurrencyInstance(locale);
        return formatoMonetario.format(valor);
    }
    
    public static void fecharConversorMoeda () {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mensagem");
        alert.setHeaderText("");
        alert.setContentText("Deseja Sair da Aplicação?!"); 
        ButtonType botaoSim = new ButtonType("Sim");
        ButtonType botaoNao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);  
        alert.getButtonTypes().setAll(botaoSim, botaoNao);  
        ButtonType resultado = alert.showAndWait().orElse(ButtonType.CANCEL); 
        if(resultado == botaoSim) {
            Platform.exit();
        }
    }
}
