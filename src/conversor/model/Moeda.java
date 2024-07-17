/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conversor.model;


import java.util.Locale;

/**
 *
 * @author evandro
 */
public class Moeda {
   
   private int id; 
   private String nome;
   private String codigoMoeda;
   private String cotacao;
   private String local;
   
   public Moeda(int id, String nome, String codigoMoeda, String cotacao, String local)  {
       
       this.id = id;
       this.nome = nome;
       this.codigoMoeda = codigoMoeda;
       this.cotacao = cotacao;
       this.local = local;
       
   }

    public int getId() {
        return this.id;
    }

  
    public String getNome() {
        return this.nome;
    }

    public String getCodigoMoeda() {
        return this.codigoMoeda;
    }

    public String getCotacao() {
        return this.cotacao;
    }

    public String getLocal() {
        return this.local;
    }
    
    @Override
    public String toString() {
        return getNome();
    }
    
}
