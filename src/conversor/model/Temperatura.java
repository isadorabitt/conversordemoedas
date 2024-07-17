/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conversor.model;

/**
 *
 * @author evandro
 */
public class Temperatura {
    
    private int id; 
    String escala;
    
    public Temperatura(int id, String escala) {
        this.id = id;
        this.escala = escala;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getEscala() {
        return this.escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }
    
    @Override
    public String toString() {
        return getEscala();
    }
    
}
