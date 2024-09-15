package src.objetos;

import src.objetos.interfaces.Pessoa;

import java.util.Random;

public class Inimigos implements Pessoa {

    private int vida;
    private int dano;

    @Override
    public int getVida() {
        return this.vida;
    }

    @Override
    public void setVida(int vidaNova) {
        this.vida = vidaNova;
    }

    @Override
    public int getDamage() {
        return this.dano;
    }

    @Override
    public void setDamage(int danoNovo) {
        this.dano = danoNovo;
    }

    public Perguntas Perguntar(Pessoa p) {
        Perguntas[] perguntas = Perguntas.values();
        int perguntaAleatoria = new Random().nextInt(perguntas.length);
        return perguntas[perguntaAleatoria];
        // System.out.println(perguntas[perguntaAleatoria].getPergunta());

    }

}
