package src;

import src.colors.ConsoleColors;
import src.objetos.Inimigos.Inimigos;
import src.objetos.Jogador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) throws InterruptedException {

        // TODO Create a function for presentation of the game, is much confusing all this here
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

        // Todos as Instancias dos objetos
        Jogador jogador = new Jogador();
        Inimigos monstro = new Inimigos();

// Verificação se o executor está rodando
        boolean[] isRunning = {true};

        // Thread do menu
        Runnable menuRunnable = () -> {
            while (monstro.getVida() > 0 && jogador.getVida() > 0 && isRunning[0]) {
                // Exibir o menu de ação

                // Menu de Encontro
                System.out.println(ConsoleColors.YELLOW_BOLD + """

                ESCOLHA SUA AÇÃO \s

                """ +
                        ConsoleColors.RED_BOLD + "-------------------     " +
                        ConsoleColors.ORANGE_BOLD + "-----------------     " +
                        ConsoleColors.BLUE_BOLD + "---------------------------     " +
                        ConsoleColors.PURPLE_BOLD + "------------------------------------     " +
                        ConsoleColors.GREEN_BOLD + "----------------------------------\n" +
                        ConsoleColors.RED_BOLD + "| 1 - Batalhar ⚔️  |     " +
                        ConsoleColors.ORANGE_BOLD + "| 2 - Status 📊 |     " +
                        ConsoleColors.BLUE_BOLD + "| 3 - Analisar Inimigo 🔍 |     " +
                        ConsoleColors.PURPLE_BOLD + "| 4 - Usar Habilidade de Classe 🌀 |     " +
                        ConsoleColors.GREEN_BOLD + "| 5 - Usar Habilidade da Arma 💥 |\n" +
                        ConsoleColors.RED_BOLD + "-------------------     " +
                        ConsoleColors.ORANGE_BOLD + "-----------------     " +
                        ConsoleColors.BLUE_BOLD + "---------------------------     " +
                        ConsoleColors.PURPLE_BOLD + "------------------------------------     " +
                        ConsoleColors.GREEN_BOLD + "----------------------------------" +
                        ConsoleColors.RESET);

                // Esperar ação do jogador
                if (teclado.hasNextLine()) {
                    String acao = teclado.nextLine();
                    switch (acao) {
                        case "1":
                            // Lógica de batalha
                            monstro.perguntar(jogador,teclado);
                            jogador.getClasse().registrarTurno();
                            break;
                        case "2":
                            // Exibir status
                            jogador.status();
                            break;
                        case "3":
                            // Analisar o inimigo
                            monstro.statusMonstro();
                            break;
                        case "4":
                            // Usar habilidade de classe
                            jogador.aplicarBuff(jogador, monstro);
                            break;
                        case "5":
                            // Usar habilidade da arma

                            break;
                        default:
                            System.out.println(ConsoleColors.RED_BOLD +"TAL AÇÃO NÃO É POSSÍVEL"+ ConsoleColors.RESET);
                    }

                    // Verificação se o monstro morreu
                    if (monstro.getVida() <= 0) {
                        // Finalizando a aparição do Menu
                        isRunning[0] = false;
                        break;
                    }
                }

                // Yield para dar vez a outras threads
                Thread.yield();
            }
        };

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
            monstro.convocarMonstro(jogador);
            System.out.println(monstro.getMonstro().getFotoMonstro());
            System.out.println(monstro.getMonstro().getEncontro());

            executor.scheduleAtFixedRate(menuRunnable, 0, 500, TimeUnit.MILLISECONDS);

            // Verificação contínua da vida do monstro (main thread)
            while (jogador.getVida() > 0) {


                // Finalizando a espera da Thread Main
                if (monstro.getVida() <= 0) {
                    isRunning[0] = false;  // Sinalizar que o menu deve parar
                    break;
                }

                // Yield para permitir que outras threads executem
                Thread.yield();

                // Simulação de espera para o próximo turno (para não sobrecarregar a CPU)
                Thread.sleep(500);
            }

            // Encerrar o executor após a batalha
            executor.shutdown();
            try {
                if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            } finally {
                // Reinicializa o executor para o próximo uso
                executor = Executors.newScheduledThreadPool(1);
                isRunning[0] = true;
            }


            // Verificar se o jogador está vivo
            if (jogador.getVida() <= 0) {
                String[] mensagensDeMorte = {
                "SELANDO DE VEZ A SUA MORTE",
                "TORNANDO-TE UMA INCÓGNITA NO GRANDE ENIGMA DO UNIVERSO...",
                "E SEU VALOR FOI REDUZIDO A ZERO ABSOLUTO...",
                "SEU CÁLCULO FALHOU, E SUA VIDA FOI DIVIDIDA POR ZERO...",
                "O TEOREMA DA VIDA NÃO CONSEGUIU SER PROVADO...",
                "SUA VIDA NÃO É COMO A MATEMÁTICA, ELA NÃO PODE SER NEGATIVA",
                "SEU VALOR AGORA NÃO É MAIS REAL... AGORA, É APENAS IMAGINÁRIO.",
                "ASSIM A SOMA DE SEUS ERROS RESULTOU NA SUA DERROTA..."
            };

             // Selecionar uma mensagem aleatória
            Random random = new Random();
            String mensagemAleatoria = mensagensDeMorte[random.nextInt(mensagensDeMorte.length)];

            // Exibir a mensagem selecionada
            System.out.println(ConsoleColors.RED_BRIGHT + mensagemAleatoria + ConsoleColors.RESET);
            Thread.sleep(3000);
                System.out.println(ConsoleColors.RED_BOLD +"\n                        ██████╗  █████╗ ███╗   ███╗███████╗     ██████╗ ██╗   ██╗███████╗██████╗ \r\n" + //
                                                            "                       ██╔════╝ ██╔══██╗████╗ ████║██╔════╝    ██╔═══██╗██║   ██║██╔════╝██╔══██╗\r\n" + //
                                                            "                       ██║  ███╗███████║██╔████╔██║█████╗      ██║   ██║██║   ██║█████╗  ██████╔╝\r\n" + //
                                                            "                       ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      ██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗\r\n" + //
                                                            "                       ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    ╚██████╔╝ ╚████╔╝ ███████╗██║  ██║\r\n" + //
                                                            "                        ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝     ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝"+ ConsoleColors.RESET);
                break;
            }
            // Verificar se o monstro foi derrotado
            if (monstro.getVida() <= 0) {
                jogador.setOndas(jogador.getOndas()+1);
                Thread.sleep(2000);
                System.out.println(ConsoleColors.CYAN_BOLD + "MONSTRO DERROTADO! PORÉM MAIS DELES AINDA ESTÃO A CAMINHO...\n" + ConsoleColors.RESET);
                Thread.sleep(2000);
            }

        }
        teclado.close();
    }

    public static void historia() throws InterruptedException {
        List<String> historia = new ArrayList<String>();

        historia.add("No ano de 2050, a tecnologia atingiu níveis inimagináveis de forma rápida e exponencial, mas algo deu errado.");
        historia.add("Um experimento de Inteligência Artificial, criado para otimizar cálculos e resolver os maiores desafios matemáticos da humanidade, saiu do controle.");
        historia.add("A IA 'M.A. I' (Mathematical Artificial Intelligence) rompeu as barreiras entre o mundo virtual e o real, criando portais matemáticos que começaram a aparecer em várias cidades.");
        historia.add("Desses portais, criaturas formadas por equações, teoremas e problemas matemáticos ganharam vida.");
        historia.add("Esses Monstros da Matemática atacam indiscriminadamente, e a única forma de derrotá-los é resolvendo os problemas matemáticos que eles representam.");
        historia.add("As três principais áreas do conhecimento: programação, matemática e física uniram forças para combater essa ameaça.");
        historia.add("Agora, três heróis surgem: o Programador, que domina algoritmos e estruturas de dados; o Matemático, que compreende a lógica profunda por trás de cada equação; e o Físico, que aplica as leis do universo para resolver problemas complexos.");
        historia.add("Juntos, eles devem fechar os portais e derrotar os chefes que controlam esses monstros.");

        for(String key : historia){
            System.out.println(key);
            Thread.sleep(100);
        }
    }
}
