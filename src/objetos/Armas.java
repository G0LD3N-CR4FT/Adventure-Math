package src.objetos;

import src.colors.ConsoleColors;
import src.objetos.Inimigos.Inimigos;
import src.objetos.Inimigos.TipoMonstro;
import src.objetos.interfaces.Pessoa;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public enum Armas {
    CALCULADORA_DO_INFINITO(10, "O INFINITO: Acerte 10 questoes seguidas para conquistar o infinito"){
        private static List<Perguntas> perguntasRestantes = new ArrayList<Perguntas>();
        private int turnosPassados = 0; // Contador de turnos passados
        private boolean vez = true;
        private int acertos = 10;
        private boolean finalizado = false;
        private boolean mgsAplicado = true;

        // Vai pegar uma pergunta de 2 Ondas acima para responder
        @Override
        public void aplicarBuff(Jogador jogador, Inimigos monstro) {
            if (turnosPassados == 0 ) {
                mgsAplicado = false;
                if(vez && !finalizado) {
                    Scanner entrada = new Scanner(System.in);

                    Perguntas Questao = gerarPeruntaMatematico(jogador, monstro);
                    pausaTexto();
                    System.out.println("\n" + Questao.getPergunta());
                    System.out.println(Questao.getAlternativa());
                    // Mostrar a resposta para facilitar
                    System.out.println("Responda com a letra da alternativa (A, B, C, D)");

                    String respostaCorretaDificuldade = Questao.getResposta();

                    System.out.println("\n" + ConsoleColors.YELLOW_BOLD + "DIGITE SUA RESPOSTA:" + ConsoleColors.RESET);
                    String resposta = entrada.nextLine();

                    if (resposta.equalsIgnoreCase(respostaCorretaDificuldade)) {
                        System.out.println(ConsoleColors.GREEN_BOLD + "ACERTOU ✅" + ConsoleColors.RESET + "\n");
                        System.out.println(ConsoleColors.CYAN_BOLD + "VOCE ACERTOU O CALCULO, RELIZANDO O INFINITO" + ConsoleColors.RESET);
                        // Removendo Perguntas Repetidas
                        perguntasRestantes.remove(Questao);
                        if (this.acertos == 0) {
                            System.out.println("Voce alcançou o infinito");
                            finalizado = true;
                            jogador.setDamage(Integer.MAX_VALUE);
                        } else {
                            int danoAntigo = this.getAtaque();
                            this.aumentarAtaque( this.getAtaque() / this.acertos);
                            jogador.setDamage(this.getAtaque() - danoAntigo);
                            this.acertos--;
                        }
                        cancelarBuff();
                    } else {
                        System.out.println(ConsoleColors.RED_BOLD + "ERROU ❌" + ConsoleColors.RESET + "\n");
                        System.out.println(ConsoleColors.RED_BRIGHT + "VOCE ERROU O CALCULO, CALCELANDO INFINITO" +
                                ConsoleColors.RESET);
                        this.aumentarAtaque( 10);
                        jogador.setDamage(this.getAtaque());
                        this.acertos = 10;
                        cancelarBuff();

                    }

                }
            } else {
                if (turnosPassados != 0){
                    System.out.println("Ainda não é possivel ativar a habilidade CALCULO INFINITO, faltam " + turnosPassados + " turnos.");
                }
                if(finalizado){
                    System.out.println("Você ja atingiu o infinito");
                }
            }

        }

        private static void pausaTexto() {
            try{
                System.out.println("A HABILIDADE FOI ATIVADA, ACERTA A QUESTAO PARA INICIAR O CALCULO INFINITO");
                Thread.sleep(1000);
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
                String mgs = turnosPassados != 0 ? ConsoleColors.PURPLE_BOLD +"Faltam " + turnosPassados + " turnos para usar de novo a habilidade " + this.name() : ConsoleColors.RESET;
                System.out.println(mgs);
            }
            // Se 3 turnos passaram, pode recuperar a habilidade
            if (turnosPassados == 0 && !mgsAplicado) {
                mgsAplicado = true;
                System.out.println(ConsoleColors.PURPLE_BOLD +"A habilidade CALCULO INFINITO pode ser utilizada novamente!"  + ConsoleColors.RESET);
            }

        }

        private void cancelarBuff() {
            turnosPassados = 2;
            vez = true; // Permite a execução no próximo turno
        }
    },
    CANHAO_COSMICO(15, "ROLLBACK: Que tudo volte a origem, durante 4 turnos o dano do inimigo e ignorado") {

        private int aplicado = 0; // Contador de vezes que a vida foi recuperada
        private final int limiteAplicado = 4; // Limite de vezes que o vida pode ser recuperada
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

                System.out.println(ConsoleColors.PURPLE_BOLD + "Habilidade CANHAO COSMICO foi ativada! Durante 4 turnos voce pode dar ROLLBACK na sua vida" + ConsoleColors.RESET);



                // Agendar a tarefa de causar dano
                Runnable tarefa = () -> {
                    if (vez) {
                        vez = false;
                        if (aplicado <= limiteAplicado && jogador.getVida() > 0) {
                             // Incrementa o contador de execuções
                            int vidaPorTurno = monstro.getDano(); // Dano a ser causado
                            // System.out.println("Causando " + danoPorTurno + " de dano no Inimigo.");
                            int vidatotal = jogador.getClasse().getBonusVida() +100;
                            int vidaResto = jogador.getVida();
                            boolean vidaCheia = jogador.getClasse().getBonusVida() + 100 == jogador.getVida();
                            if(!vidaCheia){
                                aplicado++;
                                jogador.setVida(jogador.getVida() + vidaPorTurno);
                                System.out.println(ConsoleColors.PURPLE_BOLD +"Voce deu ROLLBACK e sua vida foi restaurada" + ConsoleColors.RESET);
                            } else {
                                // System.out.println(ConsoleColors.PURPLE_BOLD + "A vida ja esta cheia para dar ROLLBACK" + ConsoleColors.RESET);
                            }
                        } else if (aplicado >= limiteAplicado){
                            System.out.println(ConsoleColors.PURPLE_BOLD +"O tempo da habilidade ROLLBACK acabou, necessário esperar " + (3 - turnosPassados) + " turnos para usar de novo"  + ConsoleColors.RESET);
                            System.out.println(ConsoleColors.PURPLE_BOLD +"A habilidade ROLLBACK finalizado apos ser executado  " + (limiteAplicado ) + ConsoleColors.RESET);
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
                if (turnosPassados == 0 && !mgsAplicado) {
                    mgsAplicado = true;
                    System.out.println(ConsoleColors.PURPLE_BOLD +"Ainda não é possivel ativar a habilidade ROLLBACK, faltam " + turnosPassados + " turnos." + ConsoleColors.RESET);
                }

            }
        }

        @Override
        public void registrarTurno() {
            vez = true;
            if (!ativo) {
                if(turnosPassados > 0){
                    turnosPassados--;
                    String mgs = turnosPassados != 0 ? "Faltam " + turnosPassados + " turnos para usar de novo " + this.name() : ConsoleColors.RESET;
                    System.out.println(mgs);
                }
                // Se 3 turnos passaram, pode recuperar a habilidade
                if (turnosPassados == 0 && !ativo && !mgsAplicado) {
                    mgsAplicado = true;
                    System.out.println(ConsoleColors.PURPLE_BOLD +"A habilidade ROLLBACK pode ser reaplicada!"+ ConsoleColors.RESET);
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
    },
    DEPURADOR_QUANTICO(8, "ATOMICO: Agora eu me tornei a Morte, o destruidor de mundos ") {

        private static List<Perguntas> perguntasRestantes = new ArrayList<Perguntas>();
        private int turnosPassados = 0; // Contador de turnos passados
        private boolean vez = true;
        private boolean mgsAplicado = true;

        // Vai pegar uma pergunta de 2 Ondas acima para responder
        @Override
        public void aplicarBuff(Jogador jogador, Inimigos monstro) {
            if (turnosPassados == 0 ) {
                mgsAplicado = false;
                if(vez) {
                    Scanner entrada = new Scanner(System.in);

                    Perguntas Questao = gerarPeruntaMatematico(jogador, monstro);
                    pausaTexto();
                    System.out.println("\n" + Questao.getPergunta());
                    System.out.println(Questao.getAlternativa());
                    // Mostrar a resposta para facilitar
                    System.out.println("Responda com a letra da alternativa (A, B, C, D)");

                    String respostaCorretaDificuldade = Questao.getResposta();

                    System.out.println("\n" + ConsoleColors.YELLOW_BOLD + "DIGITE SUA RESPOSTA:" + ConsoleColors.RESET);
                    String resposta = entrada.nextLine();

                    if (resposta.equalsIgnoreCase(respostaCorretaDificuldade)) {
                        System.out.println(ConsoleColors.GREEN_BOLD + "ACERTOU ✅" + ConsoleColors.RESET + "\n");
                        // System.out.println(ConsoleColors.CYAN_BOLD + "VOCE ACERTOU O CALCULO, RELIZANDO O INFINITO" + ConsoleColors.RESET);
                        // Removendo Perguntas Repetidas
                        perguntasRestantes.remove(Questao);
                        // Dando o dano insta kill
                        if(monstro.getMonstro() != TipoMonstro.BOSS){
                            pausaMorte();
                            int danoAntigo = monstro.getVida();
                            monstro.danoTomado(danoAntigo, monstro.getMonstro());
                            System.out.println(ConsoleColors.RED_BOLD + "O INIMIGO FOI VAPORIZADO" + ConsoleColors.RESET);
                        } else {
                            System.out.println(ConsoleColors.DARK_RED + "ESSA ARMA NÃO PODE COMPREENDER A MAGNITUDE DO IMAGINARIO");
                        }
                        cancelarBuff();
                    } else {
                        System.out.println(ConsoleColors.RED_BOLD + "ERROU ❌" + ConsoleColors.RESET + "\n");
                        System.out.println(ConsoleColors.RED_BRIGHT + "VOCE ERROU O CALCULO, A MORTE NÃO VIRA A SEUs INIMIGOs" +
                                ConsoleColors.RESET);
                        cancelarBuff();
                    }

                }
            } else {
                if (turnosPassados != 0){
                    System.out.println("Ainda não é possivel ativar a habilidade DEPURADOR QUANTICO, faltam " + turnosPassados + " turnos.");
                }
            }

        }

        private static void pausaTexto() {
            try{
                System.out.println("A HABILIDADE FOI ATIVADA, ACERTA A QUESTAO PARA INICIAR O DEPURADOR QUANTICO");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        private static void pausaMorte() {
            try{
                System.out.println("A HABILIDADE FOI ATIVADA, SEUS INIMIGOS SERAO VAPORIZADOS");
                Thread.sleep(1000);
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
                String mgs = turnosPassados != 0 ? ConsoleColors.PURPLE_BOLD +"Faltam " + turnosPassados + " turnos para usar de novo a habilidade " + this.name() : ConsoleColors.RESET;
                System.out.println(mgs);
            }
            // Se 3 turnos passaram, pode recuperar a habilidade
            if (turnosPassados == 0 && !mgsAplicado) {
                mgsAplicado = true;
                System.out.println(ConsoleColors.PURPLE_BOLD +"A habilidade DEPURADOR QUANTICO pode ser utilizada novamente!"  + ConsoleColors.RESET);
            }

        }

        private void cancelarBuff() {
            turnosPassados = 10;
            vez = true; // Permite a execução no próximo turno
        }
    };

    private int ataque;
    private String descricaoHabilidade;

    public String toString() {
        return    ConsoleColors.CYAN_BOLD + this.name() + ConsoleColors.RESET + "\n" +
                ConsoleColors.RED_UNDERLINED + "ATAQUE DA ARMA: " + ataque + ConsoleColors.RESET + "💥\n" +
                ConsoleColors.GREEN_BOLD + "HABILIDADE DA ARMA: " + descricaoHabilidade + ConsoleColors.RESET + "⚔️\n" +
                "\n";
    }

    Armas(int ataque, String texto) {
        this.ataque = ataque;
        this.descricaoHabilidade = texto;
    }

    public int getAtaque() {
        return ataque;
    }

    public void aumentarAtaque(int incremento) {
        this.ataque += incremento;
    }

    public abstract void aplicarBuff(Jogador jogador, Inimigos monstro);

    public abstract void registrarTurno();
}
