package src.objetos;

import src.objetos.interfaces.Pessoa;

public enum Armas {
    CalculadoraDoInfinito(10),
    CanhaoCosmico(20),
    DepuradorQuantico(17);

    private int ataque;

    public String toString() {
        return    this.name() +" {" +
                "Ataque Fisico = " + ataque + "}";
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
