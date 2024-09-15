package src.objetos;

public enum Danos {
    CalculadoraDoInfinito(10),
    CanhaoCosmico(20),
    DepuradorQuantico(17);

    private int ataque;

    Danos(int ataque) {
        this.ataque = ataque;
    }

    public int getAtaque() {
        return ataque;
    }

    public void aumentarAtaque(double incremento) {
        this.ataque += incremento;
    }
}
