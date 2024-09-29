package src.objetos;

import src.colors.ConsoleColors;
import src.objetos.Inimigos.Inimigos;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public enum Classes {
    MATEMATICO(5,10, ConsoleColors.GREEN_BRIGHT + "SOMA➤ " +
                                                         ConsoleColors.GREEN + "Ao acertar uma pergunta de maior dificuldade, você aumenta seu dano base em 5." +
                                                         ConsoleColors.RESET
){

        public void aplicarBuff(Jogador jogador, Inimigos monstro) {


        }

        @Override
        public void registrarTurno() {

        }
    },

    FISICO(10, 10, ConsoleColors.PURPLE_BRIGHT + "QUANTUM➤ " +
                                                       ConsoleColors.PURPLE + "Inicia uma reação em cadeia, restaurando 10 pontos de vida a cada turno, durante 6 turnos consecutivos" +
                                                       ConsoleColors.RESET
){

        private int aplicado = 0; // Contador de vezes que a vida foi recuperada
        private final int limiteAplicado = 6; // Limite de vezes que o vida pode ser recuperada
        private boolean ativo = false; // Controle para saber se a habilidade está ativa
        private int turnosPassados = 0; // Contador de turnos passados
        private boolean vez = true;
        private boolean mgsAplicado = true;
        private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        @Override
        public void aplicarBuff(Jogador jogador, Inimigos monstro) {

            //
            if (!ativo && turnosPassados == 0 ) {
                ativo = true;
                mgsAplicado = false; // A mgs ja foi aplicada, então pode aplicar depois
                aplicado = 0; // Reseta o contador de aplicações

                System.out.println("Buff QUANTUM ativado! A cada turno vai restaurar 10 de vida .");



                // Agendar a tarefa de causar dano
                Runnable tarefa = () -> {
                    if (vez) {
                        vez = false;
                        if (aplicado <= limiteAplicado && jogador.getVida() > 0) {
                            aplicado++; // Incrementa o contador de execuções
                            int vidaPorTurno = 10; // Dano a ser causado
                            // System.out.println("Causando " + danoPorTurno + " de dano no Inimigo.");
                            int vidatotal = jogador.getClasse().getBonusVida() +100;
                            int vidaResto = jogador.getVida();
                            boolean vidaCheia = jogador.getClasse().getBonusVida() + 100 >= jogador.getVida();
                            if(!vidaCheia){
                                jogador.setVida(jogador.getVida() + vidaPorTurno);
                                System.out.println("Voce recuperou 10 de vida");
                            } else {
                                System.out.println("A vida ja esta cheia");
                            }
                        } else if (aplicado >= limiteAplicado){
                            System.out.println("Buff QUANTUM foi finalizado execuções, espera " + (5 - turnosPassados) + " turno para usar de novo");
                            System.out.println("Buff QUANTUM finalizado apos ser executado  " + (limiteAplicado ) );
                            cancelarBuff(); // Reseta o estado do buff
                        }
                    }
                };

                // Talvez diminuir a tempo para não atrapalhar o fluxo do jogo
                executor.scheduleAtFixedRate(tarefa, 0, 1, TimeUnit.SECONDS);
                // Finalizando Thread
                if(jogador.getVida() <= 0) {
                    executor.shutdown();
                }
            } else {
                if (ativo){
                    System.out.println("Buff já está ativo!");
                }
                if (turnosPassados != 0){
                    System.out.println("Ainda não é possivel ativar o Buff BUG, faltam " + turnosPassados + " turnos.");
                }

            }
        }

        @Override
        public void registrarTurno() {
            vez = true;
            if (!ativo) {
                if(turnosPassados > 0){
                    turnosPassados--;
                    String mgs = turnosPassados != 0 ? "Faltam " + turnosPassados + " turnos para usar de novo" : "";
                    System.out.println(mgs);
                }
                // Se 3 turnos passaram, pode recuperar a habilidade
                if (turnosPassados == 0 && !ativo && !mgsAplicado) {
                    mgsAplicado = true;
                    System.out.println("A habilidade QUANTUM pode ser reaplicada!");
                }
            }
        }


        private void cancelarBuff() {
            try {
                ativo = false; // Desativa o buff
                aplicado = 0; // Reseta o contador de execuções
                turnosPassados = 5;
                vez = true; // Permite a execução no próximo turno
                executor.shutdown(); // Finaliza o executor para liberar recursos

                // Reinicializa o executor para o próximo uso
                executor = Executors.newScheduledThreadPool(1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    },

    PROGRAMADOR(1000, 5,
            ConsoleColors.RED_BRIGHT     + "G" +
                             ConsoleColors.YELLOW_BRIGHT + "L" +
                             ConsoleColors.GREEN_BRIGHT  + "I" +
                             ConsoleColors.CYAN_BRIGHT   + "T" +
                             ConsoleColors.BLUE_BRIGHT   + "C" +
                             ConsoleColors.PURPLE_BRIGHT + "H" +
                             ConsoleColors.RED_BRIGHT    + "E" +
                             ConsoleColors.YELLOW_BRIGHT + "D" +
                             ConsoleColors.CYAN + "➤  Lança um bug que infecta os inimigos, causando 6 de dano por turno durante 6 turnos" + ConsoleColors.RESET){

        private int aplicado = 0; // Contador de vezes que o dano foi aplicado
        private final int limiteAplicado = 6; // Limite de vezes que o dano pode ser aplicado
        private boolean ativo = false; // Controle para saber se a habilidade está ativa
        private int turnosPassados = 0; // Contador de turnos passados
        private boolean vez = true;
        private boolean mgsAplicado = true;
        private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        @Override
        public void aplicarBuff(Jogador jogador, Inimigos monstro) {

            //
            if (!ativo && turnosPassados == 0 ) {
                ativo = true;
                mgsAplicado = false; // A mgs ja foi aplicada, então pode aplicar depois
                aplicado = 0; // Reseta o contador de aplicações

                System.out.println("Buff BUG ativado! Causando dano ao inimigo a cada turno.");



                // Agendar a tarefa de causar dano
                Runnable tarefa = () -> {
                    if (monstro.getVida() <= 0 && jogador.getVida() > 0){
                        ativo = true;
                        cancelarBuff();
                        System.out.println("O inimigo foi derrotado! O BUG foi Desfeito");
                    }
                    if (vez) {
                        vez = false;
                        if (aplicado <= limiteAplicado && monstro.getVida() > 0) {
                            aplicado++; // Incrementa o contador de execuções
                            int danoPorTurno = 6; // Dano a ser causado
                            // System.out.println("Causando " + danoPorTurno + " de dano no Inimigo.");
                            monstro.danoTomado(danoPorTurno);
                            System.out.println("o inimigo tomou 6 de dano, agora ele esta com : " + monstro.getVida());

                        } else if (aplicado >= limiteAplicado){
                            System.out.println("Buff BUG finalizado execuções, espera " + (3 - turnosPassados) + " turno para usar de novo");
                            System.out.println("Buff BUG finalizado apos ser executado  " + (limiteAplicado ) );
                            cancelarBuff(); // Reseta o estado do buff
                        }
                    }
                };

                // Talvez diminuir a tempo para não atrapalhar o fluxo do jogo
                executor.scheduleAtFixedRate(tarefa, 0, 1, TimeUnit.SECONDS);
                // Finalizando Thread
                if(jogador.getVida() <= 0) {
                    executor.shutdown();
                }
            } else {
                if (ativo){
                    System.out.println("Buff já está ativo!");
                }
                if (turnosPassados != 0){
                    System.out.println("Ainda não é possivel ativar o Buff BUG, faltam " + turnosPassados + " turnos.");
                }

            }
        }

        @Override
        public void registrarTurno() {
            vez = true;
            if (!ativo) {
                if(turnosPassados > 0){
                    turnosPassados--;
                    String mgs = turnosPassados != 0 ? "Faltam " + turnosPassados + " turnos para usar de novo" : "";
                    System.out.println(mgs);
                }
                // Se 3 turnos passaram, pode recuperar a habilidade
                if (turnosPassados == 0 && !ativo && !mgsAplicado) {
                    mgsAplicado = true;
                    System.out.println("A habilidade BUG pode ser reaplicada!");
                }
            }
        }


        private void cancelarBuff() {
            try {
                ativo = false; // Desativa o buff
                aplicado = 0; // Reseta o contador de execuções
                turnosPassados = 3;
                vez = true; // Permite a execução no próximo turno
                executor.shutdown(); // Finaliza o executor para liberar recursos

                // Reinicializa o executor para o próximo uso
                executor = Executors.newScheduledThreadPool(1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    };

    private int bonusVida;
    private int bonusDano;
    private String descricaoHabilidade;

    Classes(int bonusVida, int bonusDano, String descricaoHabilidade){

        this.bonusVida = bonusVida;
        this.bonusDano = bonusDano;
        this.descricaoHabilidade = descricaoHabilidade;
    }

    @Override
    public String toString() {
        return   ConsoleColors.WHITE_BRIGHT +  this.name() + ConsoleColors.RESET + "\n" +
                ConsoleColors.GREEN_BOLD + "BONUS DE VIDA: " + bonusVida + ConsoleColors.RESET + "💚\n" +
                ConsoleColors.RED_BOLD + "BONUS DE DANO: " + bonusDano + ConsoleColors.RESET + "🥊\n" +
                ConsoleColors.CYAN_BOLD + "HABILIDADE: " + descricaoHabilidade + ConsoleColors.RESET + "🧙\n" +
                "\n";
    }

    public abstract void aplicarBuff(Jogador jogador, Inimigos monstro);

    public abstract void registrarTurno();

    // Getter para bonusVida
    public int getBonusVida() {
        return bonusVida;
    }

    // Getter para bonusDano
    public int getBonusDano() {
        return bonusDano;
    }
}

