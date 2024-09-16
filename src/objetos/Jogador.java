package src.objetos;

import src.colors.ConsoleColors;
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
        System.out.println(ConsoleColors.BLUE_BOLD + "---------------DIGITE O NÚMERO DA CLASSE DESEJADA---------------" + ConsoleColors.RESET + "\n");
        Classes[] classes = Classes.values();
        for (int i = 0; i < classes.length ; i++) {
            System.out.println(String.format("%s%d - %s%s", ConsoleColors.BLACK_BOLD, i + 1, ConsoleColors.RESET, classes[i].toString()));
        }
    }

    public void setClasses(int escolha){
        Classes[] classes = Classes.values();
        this.tipoClasse = classes[escolha-1];
        this.vida +=  this.tipoClasse.getBonusVida();
        this.danoBasico += this.tipoClasse.getBonusDano();
    }

    // Metodos para mostrar e escolher as armas

    public void mostrarArmas(){
        System.out.println(ConsoleColors.GREEN + "---------------DIGITE O NÚMERO DA ARMA DESEJADA---------------" + ConsoleColors.RESET + "\n");
        Armas[] armas = Armas.values();
        for (int i = 0; i < armas.length ; i++) {
            System.out.println(String.format("%s%d - %s%s", ConsoleColors.CYAN_BOLD, i + 1, ConsoleColors.RESET, armas[i].toString()));
        }
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
        return "\n" + ConsoleColors.ORANGE_BOLD + "-----------------------STATUS DO JOGADOR------------------------" + ConsoleColors.RESET + "\n" + "\n" +
                ConsoleColors.GREEN_BOLD + "VIDA TOTAL--------------------------------------------" + ConsoleColors.RESET +  ConsoleColors.GREEN + vida + ConsoleColors.RESET + "\n" +
                ConsoleColors.RED_BOLD + "DANO TOTAL--------------------------------------------" + ConsoleColors.RESET +  ConsoleColors.RED + danoBasico + ConsoleColors.RESET +"\n" +
                ConsoleColors.CYAN_BOLD + "ARMA: " + ConsoleColors.RESET + armas +
                "CLASSE: " + tipoClasse + "\n";
    }
}
