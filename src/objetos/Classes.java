/*package src.objetos;

public enum Classes {
    MATEMATICO {
        @Override
        public void aplicarBuff(Jogador jogador) {
            System.out.println("To the infinity: A cada pergunta respondida corretamente o dano é aumentado exponencialmente até chegar em infinito");
            // Possível fórmula this.ataque = ataque + ataque/num--
            // Num vai começar valendo 10 e vai descrementando
            // Dessa forma se o ataque for 10 e a pergunta for acertada corretamente ele vai fazer 10 = 10 + 10/10
            // Ou seja ataque = 11 
            // Quando o descrementador chegar a 0 o ataque vai berar o infinito se tornando a arma definitiva do matemático
        }
    },
    FISICO {
        @Override
        public void aplicarBuff(Jogador jogador) {
            System.out.println("Even light can't escape: Elimina instântaneamente o inimigo indenpendentemente de qual seja");
        }
    },
    PROGRAMADOR {
        @Override
        public void aplicarBuff(Jogador jogador) {
            System.out.println("Firewall Implacavel: Torna o jogador invencível ");
        }
    };

    public abstract void aplicarBuff(Jogador jogador);
}
*/
package src.objetos;

public enum Classes {
    MATEMATICO {
        @Override
        public void aplicarBuff(Jogador jogador) {
            jogador.responderPerguntaCorretamente();
            System.out.println("To the infinity: A cada pergunta respondida corretamente o dano é aumentado exponencialmente até chegar em infinito");
        }
    },
    FISICO {
        @Override
        public void aplicarBuff(Jogador jogador) {
            jogador.eliminarInimigo();
            System.out.println("Even light can't escape: Elimina instantaneamente o inimigo independentemente de qual seja");
        }
    },
    PROGRAMADOR {
        @Override
        public void aplicarBuff(Jogador jogador) {
            jogador.tornarInvencivel();
            System.out.println("Firewall Implacável: Torna o jogador invencível ");
        }
    };

    public abstract void aplicarBuff(Jogador jogador);
}

