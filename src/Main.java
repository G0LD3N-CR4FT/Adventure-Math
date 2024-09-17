package src;

import src.colors.ConsoleColors;
import src.objetos.Inimigos.Inimigos;
import src.objetos.Jogador;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("\n" + ConsoleColors.CYAN_BOLD +
                "███╗   ███╗ █████╗ ████████╗██╗  ██╗     █████╗ ██████╗ ██╗   ██╗███████╗███╗   ██╗████████╗██╗   ██╗██████╗ ███████╗\r\n" + //
                "████╗ ████║██╔══██╗╚══██╔══╝██║  ██║    ██╔══██╗██╔══██╗██║   ██║██╔════╝████╗  ██║╚══██╔══╝██║   ██║██╔══██╗██╔════╝\r\n" + //
                "██╔████╔██║███████║   ██║   ███████║    ███████║██║  ██║██║   ██║█████╗  ██╔██╗ ██║   ██║   ██║   ██║██████╔╝█████╗  \r\n" + //
                "██║╚██╔╝██║██╔══██║   ██║   ██╔══██║    ██╔══██║██║  ██║╚██╗ ██╔╝██╔══╝  ██║╚██╗██║   ██║   ██║   ██║██╔══██╗██╔══╝  \r\n" + //
                "██║ ╚═╝ ██║██║  ██║   ██║   ██║  ██║    ██║  ██║██████╔╝ ╚████╔╝ ███████╗██║ ╚████║   ██║   ╚██████╔╝██║  ██║███████╗\r\n" + //
                "╚═╝     ╚═╝╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝    ╚═╝  ╚═╝╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═══╝   ╚═╝    ╚═════╝ ╚═╝  ╚═╝╚══════╝" + ConsoleColors.RESET);
        Scanner teclado = new Scanner(System.in);

        System.out.println("\nBem vindo ao " + ConsoleColors.RED_BOLD +"ADVENTURE MATH"+ ConsoleColors.RESET + " Pressione Enter para continuar ->" );
        teclado.nextLine();
        System.out.println("Escolha uma classe abaixo para começar o jogo \n");

        Jogador jogador = new Jogador();


        // Pausa Dramatica
        Thread.sleep(500);

        // Selecionando a classe do personagem
        jogador.mostrarClasses();

        int escolhaClasse = teclado.nextInt();
        jogador.setClasses(escolhaClasse);
        // Fim de seleção de classe do personagem

        System.out.println("\n");
        // Selecionando armas do personagem
        jogador.mostrarArmas();

        int escolhaArmas = teclado.nextInt();
        jogador.setArmas(escolhaArmas);
        // Fim de seleção de armas do personagem


        // Continuar jogo até a vida do Inimigo Zerar
        while(jogador.getVida() > 0){

            // Escolher Novo Inimigo
            Inimigos monstro = new Inimigos();
            monstro.convocarMonstro(jogador);
            System.out.println(monstro.getMonstro().getFotoMostro());
            System.out.println(monstro.getMonstro().getEncontro());

            // Batalhar contra o Inimigo
            while(monstro.getVida() > 0 && jogador.getVida() > 0){
                // Menu de Encontro
                System.out.println("""

                        Escolha sua Ação\s
                        1 - Batalhar, 2 - Status, 3 - Analisar Inimigo""");

                int acao = teclado.nextInt();

                switch (acao){
                    case 1:
                        monstro.perguntar(jogador,teclado);
                        break;
                    case 2:
                        jogador.status();
                        break;
                    case 3:
                        System.out.println(monstro.getVida());
                        break;
                    default:
                        System.out.println("Tal acao não é possivel");
                }
            }

            // Verificar se o jogador está vivo
            if (jogador.getVida() <= 0) {
                System.out.println("Você foi derrotado. Fim de jogo.");
                break;
            }

            // Verificar se o monstro foi derrotado
            if (monstro.getVida() <= 0) {
                System.out.println("\n\nVocê derrotou o monstro!, porém voce ainda, não fechou a portal, se prepare eles estão vindo...");
                Thread.sleep(500);
            }

        }
    }
}
