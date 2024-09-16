package src;

import src.colors.ConsoleColors;
import src.objetos.Inimigos;
import src.objetos.Jogador;
import src.objetos.interfaces.Pessoa;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("\n" + ConsoleColors.CYAN_BOLD + "███╗   ███╗ █████╗ ████████╗██╗  ██╗     █████╗ ██████╗ ██╗   ██╗███████╗███╗   ██╗████████╗██╗   ██╗██████╗ ███████╗\r\n" + //
                "████╗ ████║██╔══██╗╚══██╔══╝██║  ██║    ██╔══██╗██╔══██╗██║   ██║██╔════╝████╗  ██║╚══██╔══╝██║   ██║██╔══██╗██╔════╝\r\n" + //
                "██╔████╔██║███████║   ██║   ███████║    ███████║██║  ██║██║   ██║█████╗  ██╔██╗ ██║   ██║   ██║   ██║██████╔╝█████╗  \r\n" + //
                "██║╚██╔╝██║██╔══██║   ██║   ██╔══██║    ██╔══██║██║  ██║╚██╗ ██╔╝██╔══╝  ██║╚██╗██║   ██║   ██║   ██║██╔══██╗██╔══╝  \r\n" + //
                "██║ ╚═╝ ██║██║  ██║   ██║   ██║  ██║    ██║  ██║██████╔╝ ╚████╔╝ ███████╗██║ ╚████║   ██║   ╚██████╔╝██║  ██║███████╗\r\n" + //
                "╚═╝     ╚═╝╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝    ╚═╝  ╚═╝╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═══╝   ╚═╝    ╚═════╝ ╚═╝  ╚═╝╚══════╝" + ConsoleColors.RESET);
        Scanner teclado = new Scanner(System.in);

        System.out.println("\nBem vindo ao " + ConsoleColors.RED_BOLD +"ADVENTURE MATH"+ ConsoleColors.RESET + " Pressione Enter para continuar ->" );
        teclado.nextLine();
        System.out.println("Escolha uma classe abaixo para começar o jogo \n");
        Jogador jogadores = new Jogador();
        // Pausa Dramatica
        Thread.sleep(1000);
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

        Inimigos monstro = new Inimigos();
        monstro.Perguntar(jogadores,teclado);
        teclado.nextLine();
        System.out.println(jogadores.toString());

    }
}
