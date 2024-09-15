package src.objetos;

import src.objetos.interfaces.Pessoa;

public enum Armas {
    CalculadoraDoInfinito(10),
    CanhaoCosmico(20),
    DepuradorQuantico(17);

    private int ataque;

    Armas(int ataque, Pessoa jogador) {
        this.ataque = ataque;
    }

    public int getAtaque() {
        return ataque;
    }

    public void aumentarAtaque(double incremento) {
        this.ataque += incremento;
    }
}
