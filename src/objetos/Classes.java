package src.objetos;

import src.colors.ConsoleColors;
import src.objetos.Inimigos.Inimigos;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public enum Classes {
    MATEMATICO(5,10){

        public void aplicarBuff(Jogador jogador, Inimigos monstro) {


        }

        @Override
        public void registrarTurno() {

        }
    },
    FISICO(10, 10){
        @Override
        public void aplicarBuff(Jogador jogador, Inimigos monstro) {


        }

        @Override
        public void registrarTurno() {

        }

    },
    PROGRAMADOR(0, 5){

        private int aplicado = 0; // Contador de vezes que o dano foi aplicado
        private final int limiteAplicado = 6; // Limite de vezes que o dano pode ser aplicado
        private boolean ativo = false; // Controle para saber se a habilidade está ativa
        private int turnosPassados = 0; // Contador de turnos passados
        private boolean vez = true;
        private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        @Override
        public void aplicarBuff(Jogador jogador, Inimigos monstro) {

            //
            if (!ativo && turnosPassados == 0 ) {
                ativo = true;
                aplicado = 0; // Reseta o contador de aplicações

                System.out.println("Buff ativado! Causando dano ao inimigo a cada turno.");



                // Agendar a tarefa de causar dano
                Runnable tarefa = () -> {
                    if (monstro.getVida() <= 0){
                        ativo = true;
                        cancelarBuff();
                        System.out.println("O inimigo foi derrotado pelo BUG! O BUG foi Desfeito");
                    }
                    if (vez) {
                        vez = false;
                        if (aplicado <= limiteAplicado && monstro.getVida() > 0) {
                            aplicado++; // Incrementa o contador de execuções
                            int danoPorTurno = 6; // Dano a ser causado
                            // System.out.println("Causando " + danoPorTurno + " de dano no Inimigo.");
                            monstro.setVida(monstro.getVida() - danoPorTurno);
                            System.out.println("o inimigo tomou 6 de dano, agora ele esta com : " + monstro.getVida());

                        } else if (aplicado >= limiteAplicado){
                            System.out.println("Buff finalizado execuções, espera " + (3 - turnosPassados) + " turno para usar de novo");
                            System.out.println("Buff finalizado. Aguardando " + (limiteAplicado - turnosPassados) + " turnos para usar de novo.");
                            cancelarBuff(); // Reseta o estado do buff
                        }
                    }
                };

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
                if (turnosPassados == 0) {
                    System.out.println("A habilidade de veneno pode ser reaplicada!");
                }
            }
        }


        private void cancelarBuff() {
            try {
                Thread.sleep(500); // Esperar as variaveis atualizar
                ativo = false; // Desativa o buff
                aplicado = 0; // Reseta o contador de execuções
                turnosPassados = 3;
                vez = true; // Permite a execução no próximo turno
                executor.shutdown(); // Finaliza o executor para liberar recursos

                // Reinicializa o executor para o próximo uso
                executor = Executors.newScheduledThreadPool(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Método para registrar turnos e verificar a recuperação do buff


    };

    private int bonusVida;
    private int bonusDano;

    Classes(int bonusVida, int bonusDano){

        this.bonusVida = bonusVida;
        this.bonusDano = bonusDano;
    }

    @Override
    public String toString() {
        return   ConsoleColors.BLACK_BOLD +  this.name() + ConsoleColors.RESET + "\n" +
                ConsoleColors.GREEN_BOLD + "BONUS DE VIDA: " + bonusVida + ConsoleColors.RESET + "💚\n" +
                ConsoleColors.RED_BOLD + "BONUS DE DANO: " + bonusDano + ConsoleColors.RESET + "🥊\n" +
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

