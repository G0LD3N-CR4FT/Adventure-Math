package src;

import src.colors.ConsoleColors;
import src.objetos.Inimigos.Inimigos;
import src.objetos.Jogador;

import java.util.ArrayList;
import java.util.List;
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
        historia();
        System.out.println("\nEscolha uma classe abaixo para começar o jogo \n");

        Jogador jogador = new Jogador();
        boolean sucessoClasse = false;


        // Pausa Dramatica
        Thread.sleep(500);

        // Selecionando a classe do personagem
        jogador.escolhaClasse(teclado,jogador);
        // Fim de seleção de classe do personagem

        System.out.println("\n");

        // Selecionando armas do personagem
        jogador.escolhaArma(teclado,jogador);
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
                // Foto Monstro
                // System.out.println("\n" + monstro.getMonstro().getFotoMostro());

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
                        monstro.statusMonstro();
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
                jogador.setOndas(jogador.getOndas()+1);
                System.out.println("\n\nVocê derrotou o monstro!, porém voce ainda, não fechou a portal, se prepare eles estão vindo...");
                Thread.sleep(500);
            }

        }
    }

    public static void historia() throws InterruptedException {
        List<String> historia = new ArrayList<String>();

        historia.add("No ano de 2050, a tecnologia atingiu níveis inimagináveis de forma rápida e exponencial, mas algo deu errado.");
        historia.add("Um experimento de Inteligência Artificial, criado para otimizar cálculos e resolver os maiores desafios matemáticos da humanidade, saiu do controle.");
        historia.add("A IA 'M.A. I' (Mathematical Artificial Intelligence) rompeu as barreiras entre o mundo virtual e o real, criando portais matemáticos que começaram a aparecer em várias cidades.");
        historia.add("Desses portais, criaturas formadas por equações, teoremas e problemas matemáticos ganharam vida.");
        historia.add("Esses Monstros da Matemática atacam indiscriminadamente, e a única forma de derrotá-los é resolvendo os problemas matemáticos que eles representam.");
        historia.add("As três principais áreas do conhecimento – programação, matemática e física – uniram forças para combater essa ameaça.");
        historia.add("Agora, três heróis surgem: o Programador, que domina algoritmos e estruturas de dados; o Matemático, que compreende a lógica profunda por trás de cada equação; e o Físico, que aplica as leis do universo para resolver problemas complexos.");
        historia.add("Juntos, eles devem fechar os portais e derrotar os chefes que controlam esses monstros.");

        for(String key : historia){
            System.out.println(key);
            Thread.sleep(6000);
        }
    }
}
