package src.objetos;

import src.colors.ConsoleColors;

public enum Classes {
    MATEMATICO(5,10){
        @Override
        public void aplicarBuff(Jogador jogador) {
            /* Essa habilidade vai funcionar assim: se a pessoa acertar uma quantidade de questões ela atinge o dano infinito e além disso vai incrementando o dano que ela possui, seguindo tal formula:
             *
             * sequenciaderespostascertas = 15 (isso é só um exemplo de 15 questões)
             * while (sequenciaderespostascertas >= 0){
             *  ataque = ataque + ataque/sequenciaderespostascertas;
             *
             * if (sequenciaderespostascertas = 0){
             * ataque = ataque/0.00000000001;
             * }
             * sequenciaderespostascertas--;
             * }
             */
        }

        @Override
        public String descricaoBuff() {
            return  "To the infinity: A cada pergunta respondida corretamente o dano é aumentado exponencialmente até chegar em infinito";
        }
    },
    FISICO(10, 10){
        @Override
        public void aplicarBuff(Jogador jogador) {


        }
        @Override
        public String descricaoBuff(){
            return "Even light can't escape: Elimina instantaneamente o inimigo independentemente de qual seja";
        }
    },
    PROGRAMADOR(0, 5){
        @Override
        public void aplicarBuff(Jogador jogador) {


        }

        @Override
        public String  descricaoBuff() {
            return "Firewall Implacável: Torna o jogador invencível ";
        }

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

    public abstract void aplicarBuff(Jogador jogador);

    public abstract String descricaoBuff();

    // Getter para bonusVida
    public int getBonusVida() {
        return bonusVida;
    }

    // Getter para bonusDano
    public int getBonusDano() {
        return bonusDano;
    }
}

