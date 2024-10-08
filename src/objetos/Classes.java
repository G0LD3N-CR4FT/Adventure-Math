package src.objetos;

import src.colors.ConsoleColors;
import src.objetos.Inimigos.Inimigos;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public enum Classes {
    MATEMATICO(5,10, ConsoleColors.GREEN_BRIGHT + "SOMA➤ " +
                                                        ConsoleColors.GREEN + "Ao acertar uma pergunta de maior dificuldade, você aumenta seu dano base em 5." +
                                                        ConsoleColors.RESET
){

        private static List<Perguntas> perguntasRestantes = new ArrayList<Perguntas>();
        private int turnosPassados = 0; // Contador de turnos passados
        private boolean vez = true;
        // Vai pegar uma pergunta de 2 Ondas acima para responder
        // TODO  VOU TER QUE REVISAR, ESTA DANDO LOOP INFINITO NA PARTE DO MENU.
        public void aplicarBuff(Jogador jogador, Inimigos monstro) {
            if (turnosPassados == 0 ) {
                if(vez) {
                    Scanner entrada = new Scanner(System.in);

                    // Importar o metodo statico não vai adiantar, pois vamos pegar dificuldades maiores
                    Perguntas Questao = gerarPeruntaMatematico(jogador, monstro);
                    pausaTexto();
                    System.out.println("\n" + Questao.getPergunta());
                    System.out.println(Questao.getAlternativa());
                    // Mostrar a resposta para facilitar
                    System.out.println("A Resposta correta é: " + Questao.getResposta());

                    String respostaCorretaDificuldade = Questao.getResposta();

                    System.out.println("\n" + ConsoleColors.YELLOW_BOLD + "DIGITE SUA RESPOSTA:" + ConsoleColors.RESET);
                    String resposta = entrada.nextLine();

                    if (resposta.equalsIgnoreCase(respostaCorretaDificuldade)) {
                        System.out.println(ConsoleColors.GREEN_BOLD + "ACERTOU ✅" + ConsoleColors.RESET + "\n");
                        System.out.println(ConsoleColors.CYAN_BOLD + "VOCE ACERTOU O CALCULO, GANHANDO 5 DE DANO" + ConsoleColors.RESET);
                        // Removendo Perguntas Repetidas
                        perguntasRestantes.remove(Questao);
                        jogador.setDamage(jogador.getDamage() + 5);
                        cancelarBuff();
                    } else {
                        System.out.println(ConsoleColors.RED_BOLD + "ERROU ❌" + ConsoleColors.RESET + "\n");
                        System.out.println(ConsoleColors.RED_BRIGHT + "VOCE ERROU O CALCULO, PERDENDO 5 DE DANO " +
                                ConsoleColors.RESET);
                        jogador.setDamage(jogador.getDamage() - 5);
                        cancelarBuff();

                    }

                }
            } else {
                if (turnosPassados != 0){
                    System.out.println("Ainda não é possivel ativar a habilidade SOMA, faltam " + turnosPassados + " turnos.");
                }
            }

        }

        private static void pausaTexto() {
            try{
                System.out.println("A HABILIDADE FOI ATIVADA, ACERTA A QUESTAO PARA GANHAR 5 DE DANO");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        public Perguntas gerarPeruntaMatematico(Jogador jogador, Inimigos monstro){
            if(perguntasRestantes.isEmpty()){
                jogador.setOndas(jogador.getOndas()+2);
                // Pegando todos os objetos do Enum Perguntas
                Perguntas[] perguntas = Perguntas.values();

                // Selecionando as perguntas por dificuldade
                List<Perguntas> perguntasDificuldade = Arrays.stream(perguntas)
                        .filter(a -> a.getDificuldade() <= jogador.getOndas())
                        .toList();
                perguntasRestantes.addAll(perguntasDificuldade);
            }
            jogador.setOndas(jogador.getOndas()-2);
            // Sorteando uma pergunta da lista
            int perguntaAleatoriaDificuldade = new Random().nextInt(perguntasRestantes.size());
            return perguntasRestantes.get(perguntaAleatoriaDificuldade);
        }

        @Override
        public void registrarTurno() {
            vez = true;

            if(turnosPassados > 0){
                turnosPassados--;
                String mgs = turnosPassados != 0 ? ConsoleColors.PURPLE_BOLD +"Faltam " + turnosPassados + " turnos para usar de novo" : "" + ConsoleColors.RESET;
                System.out.println(mgs);
            }
            // Se 3 turnos passaram, pode recuperar a habilidade
            if (turnosPassados == 0) {
                System.out.println(ConsoleColors.PURPLE_BOLD +"A habilidade SOMA pode ser utilizada novamente!"  + ConsoleColors.RESET);
            }

        }

        private void cancelarBuff() {
            turnosPassados = 5;
            vez = true; // Permite a execução no próximo turno
        }
    },

    FISICO(10, 10, "QUANTUM➤ Cria uma reacao em cadeia recuperando 10 de vida durante 6 turnos"){

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

                System.out.println(ConsoleColors.PURPLE_BOLD + "Habilidade QUANTUM ativada! A cada turno sua vida recuperará 10 pontos de vida." + ConsoleColors.RESET);



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
                            boolean vidaCheia = jogador.getClasse().getBonusVida() + 100 == jogador.getVida();
                            if(!vidaCheia){
                                jogador.setVida(jogador.getVida() + vidaPorTurno);
                                System.out.println(ConsoleColors.PURPLE_BOLD +"Voce recuperou 10 de vida" + ConsoleColors.RESET);
                            } else {
                                System.out.println(ConsoleColors.PURPLE_BOLD + "A vida ja esta cheia" + ConsoleColors.RESET);
                            }
                        } else if (aplicado >= limiteAplicado){
                            System.out.println(ConsoleColors.PURPLE_BOLD +"O tempo da habilidade QUANTUM acabou, necessário esperar " + (5 - turnosPassados) + " turnos para usar de novo"  + ConsoleColors.RESET);
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
                    System.out.println(ConsoleColors.PURPLE_BOLD +"Habilidade já está ativa!"+ ConsoleColors.RESET);
                }
                if (turnosPassados != 0){
                    System.out.println(ConsoleColors.PURPLE_BOLD +"Ainda não é possivel ativar a habilidade QUANTUM, faltam " + turnosPassados + " turnos." + ConsoleColors.RESET);
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
                    System.out.println(ConsoleColors.PURPLE_BOLD +"A habilidade QUANTUM pode ser reaplicada!"+ ConsoleColors.RESET);
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

    PROGRAMADOR(1000, 5, "BUG➤ Infecta os Inimigos com um Bug para tirar 6 de vida durante 6 turnos"){

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

                System.out.println(ConsoleColors.PURPLE_BOLD + "Habilidade BUG ativado! Causando dano ao inimigo a cada turno."+ ConsoleColors.RESET);



                // Agendar a tarefa de causar dano
                Runnable tarefa = () -> {
                    if (monstro.getVida() <= 0 && jogador.getVida() > 0){
                        ativo = true;
                        cancelarBuff();
                        System.out.println(ConsoleColors.PURPLE_BOLD +"O inimigo foi derrotado! O BUG foi Desfeito"+ ConsoleColors.RESET);
                    }
                    if (vez) {
                        vez = false;
                        if (aplicado <= limiteAplicado && monstro.getVida() > 0) {
                            aplicado++; // Incrementa o contador de execuções
                            int danoPorTurno = 6; // Dano a ser causado
                            // System.out.println("Causando " + danoPorTurno + " de dano no Inimigo.");
                            monstro.danoTomado(danoPorTurno);
                            System.out.println(ConsoleColors.PURPLE_BOLD + "O inimigo tomou 6 de dano, agora ele esta com : " + monstro.getVida() + ConsoleColors.RESET);

                        } else if (aplicado >= limiteAplicado){
                            System.out.println(ConsoleColors.PURPLE_BOLD + "O tempo da habilidade BUG acabou, necessário esperar " + (3 - turnosPassados) + " turno para usar de novo"+ ConsoleColors.RESET);
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
                    System.out.println(ConsoleColors.PURPLE_BOLD + "Habilidade já está ativa!"+ ConsoleColors.RESET);
                }
                if (turnosPassados != 0){
                    System.out.println(ConsoleColors.PURPLE_BOLD + "Ainda não é possivel ativar a habilidade BUG, faltam " + turnosPassados + " turnos."+ ConsoleColors.RESET);
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
                    System.out.println(ConsoleColors.PURPLE_BOLD +"A habilidade BUG pode ser reaplicada!"+ ConsoleColors.RESET);
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
        return   ConsoleColors.BLACK_BOLD +  this.name() + ConsoleColors.RESET + "\n" +
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

