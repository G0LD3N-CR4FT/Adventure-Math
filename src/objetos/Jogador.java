package src.objetos;

import src.colors.ConsoleColors;
import src.objetos.interfaces.Pessoa;

import java.util.Random;
import java.util.Scanner;

public class Jogador implements Pessoa {

    private int vida = 0;
    private int danoBasico = 0;
    private Armas armas;
    private Classes tipoClasse;
    private int ondas = 1;



    @Override
    public int getDamage() {
        return danoBasico;
    }

    @Override
    public void setDamage(int damage) {
        this.danoBasico = damage;
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

    public boolean setClasses(int escolha){
        try {
            Classes[] classes = Classes.values();
            this.tipoClasse = classes[escolha-1];
            this.vida +=  this.tipoClasse.getBonusVida();
            this.danoBasico += this.tipoClasse.getBonusDano();
            return true;
        } catch (ArrayIndexOutOfBoundsException error){
            System.out.println("Essa classe digitada não existe, escolha uma classe valida");
            return false;
        }
    }

    public void escolhaArma(Scanner teclado, Jogador jogador) throws InterruptedException {
        boolean sucessoArma = false;
        // Selecionando armas do personagem
        jogador.mostrarArmas();
        int escolhaArmas = teclado.nextInt();
        teclado.nextLine();

        // Realizando a escolha de Classe
        while(true){
            sucessoArma  = jogador.setArmas(escolhaArmas);
            if(sucessoArma){
                System.out.println("\nA Arma escolhida foi \n"+ jogador.getClasse());
                System.out.println("Press Enter para continuar -> ");
                teclado.nextLine();
                break;
            } else {
                Thread.sleep(800);
                // Selecionando a classe do personagem
                jogador.mostrarArmas();
                escolhaArmas = teclado.nextInt();
                teclado.nextLine(); // Limpando o buffer
            }
        }
    }

    public void escolhaClasse(Scanner teclado, Jogador jogador) throws InterruptedException {
        boolean sucessoClasse = false;
        jogador.mostrarClasses();
        int escolhaClasse = teclado.nextInt();
        teclado.nextLine();
        // Realizando a escolha de Classe
        while(true){
            sucessoClasse  = jogador.setClasses(escolhaClasse);
            if(sucessoClasse){
                System.out.println("\nA classe escolhida foi \n"+ jogador.getClasse());
                System.out.println("Press Enter para continuar -> ");
                teclado.nextLine();
                break;
            } else {
                Thread.sleep(800);
                // Selecionando a classe do personagem
                jogador.mostrarClasses();
                escolhaClasse = teclado.nextInt();
                teclado.nextLine(); // Limpando o buffer
            }
        }
    }

    // Metodos para mostrar e escolher as armas

    public void mostrarArmas(){
        System.out.println(ConsoleColors.GREEN + "---------------DIGITE O NÚMERO DA ARMA DESEJADA---------------" + ConsoleColors.RESET + "\n");
        Armas[] armas = Armas.values();
        for (int i = 0; i < armas.length ; i++) {
            System.out.println(String.format("%s%d - %s%s", ConsoleColors.CYAN_BOLD, i + 1, ConsoleColors.RESET, armas[i].toString()));
        }
    }

    public boolean setArmas(int escolha){
        try {
            Armas[] armas = Armas.values();
            this.armas = armas[escolha - 1];
            this.danoBasico += this.armas.getAtaque();
            return true;
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println("Essa classe digitada não existe, escolha uma classe valida");
            return false;
        }
    }


    public Armas getArmas() {
        return armas;
    }

    public Classes getClasse(){
        return tipoClasse;
    }

    public int getOndas(){
        return ondas;
    }

    public void setOndas(int ondas){
        this.ondas = ondas;
    }

    public int getVida() {
        return vida;
    }

    @Override
    public void setVida(int vidaNova) {
        this.vida = vidaNova;
    }

    public void status() {
        System.out.println("\n" + ConsoleColors.ORANGE_BOLD + "-----------------------STATUS DO JOGADOR------------------------" + ConsoleColors.RESET + "\n" + "\n" +
                ConsoleColors.GREEN_BOLD + "VIDA TOTAL--------------------------------------------" + ConsoleColors.RESET +  ConsoleColors.GREEN + vida + ConsoleColors.RESET + "\n" +
                ConsoleColors.RED_BOLD + "DANO TOTAL--------------------------------------------" + ConsoleColors.RESET +  ConsoleColors.RED + danoBasico + ConsoleColors.RESET +"\n" +
                ConsoleColors.CYAN_BOLD + "ARMA: " + ConsoleColors.RESET + armas +
                "CLASSE: " + tipoClasse + "\n");
         ;
    }
}
