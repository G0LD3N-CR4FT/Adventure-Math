package src;

import src.colors.ConsoleColors;
import src.objetos.Jogador;
import src.objetos.interfaces.Pessoa;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        System.out.println("\nBem vindo ao " + ConsoleColors.RED +"Adventure Math"+ ConsoleColors.RESET +", escolha uma classe abaixo \n");
        Jogador jogadores = new Jogador();

        // Selecionando a classe do personagem
        jogadores.mostrarClasses();

        int escolhaClasse = teclado.nextInt();
        jogadores.setClasses(escolhaClasse);
        // Fim de seleção de classe do personagem

        System.out.println("\n");
        // Selecionando armas do personagem
        jogadores.mostrarArmas();

        int escolhaArmas = teclado.nextInt();
        jogadores.setArmas(escolhaArmas);
        // Fim de seleção de armas do personagem

        System.out.println(jogadores.toString());
    }
}
