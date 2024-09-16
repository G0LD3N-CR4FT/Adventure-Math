package src.objetos;

import src.colors.ConsoleColors;
import src.objetos.interfaces.Pessoa;

public enum Armas {
    CALCULADORA_DO_INFINITO(10),
    CANHAO_COSMICO(20),
    DEPURADOR_QUANTICO(17);

    private int ataque;

    public String toString() {
        return    ConsoleColors.CYAN_BOLD + this.name() + ConsoleColors.RESET + "\n" +
                ConsoleColors.RED_UNDERLINED + "ATAQUE DA ARMA: " + ataque + ConsoleColors.RESET + "\n" + "\n";
    }

    Armas(int ataque) {
        this.ataque = ataque;
    }

    public int getAtaque() {
        return ataque;
    }

    public void aumentarAtaque(double incremento) {
        this.ataque += incremento;
    }
}
