package src.objetos;

import src.objetos.interfaces.Pessoa;

import java.util.Random;

public class Jogador implements Pessoa {

    private int vida = 20;
    private int danoBasico = 10;
    private Armas armas;
    private Classes tipoClasse;



    @Override
    public int getDamage() {
        return armas.getAtaque();
    }

    @Override
    public void setDamage(int damage) {

    }

    public void aplicarBuff() {
        tipoClasse.aplicarBuff(this);
    }


    // Metodo para mostrar e escolher as classes do jogo

    public void mostrarClasses(){

        Classes[] classes = Classes.values();
        for (int i = 0; i < classes.length ; i++) {
            System.out.println(i+1 + " - " + classes[i].toString());
        }

        System.out.println("Digite o numero da classe desejada");
    }

    public void setClasses(int escolha){
        Classes[] classes = Classes.values();
        this.tipoClasse = classes[escolha-1];
        this.vida +=  this.tipoClasse.getBonusVida();
        this.danoBasico += this.tipoClasse.getBonusDano();
    }

    // Metodos para mostrar e escolher as armas

    public void mostrarArmas(){

        Armas[] armas = Armas.values();
        for (int i = 0; i < armas.length ; i++) {
            System.out.println(i+1 + " - " + armas[i].toString());
        }

        System.out.println("Digite o numero da arma desejada");
    }

    public void setArmas(int escolha){
        Armas[] armas = Armas.values();
        this.armas = armas[escolha-1];
        this.danoBasico += this.armas.getAtaque();
    }


    public Classes getClasse(){
        return tipoClasse;
    }

    public int getVida() {
        return vida;
    }

    @Override
    public void setVida(int vidaNova) {
        this.vida = vidaNova;
    }

    @Override
    public String toString() {
        return "Jogador {" +
                "vida=" + vida +
                ", danoBasico=" + danoBasico +
                ", armas=" + armas +
                ", tipoClasse=" + tipoClasse +
                '}';
    }
}
