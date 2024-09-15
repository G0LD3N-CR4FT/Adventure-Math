package src.objetos;

import src.objetos.interfaces.Pessoa;

public class Jogador implements Pessoa {

    private int vida;
    private Danos dano;
    private Classes tipoClasse;
    private int onda;
    private int contadorRespostas = 10;

    public Jogador(Long vida, int pontos, Classes tipoClasse, Danos dano, int onda) {


        this.tipoClasse = tipoClasse;
        this.dano = dano;
        this.onda = onda;
    }



    @Override
    public int getDamage() {
        return dano.getAtaque();
    }

    @Override
    public void setDamage(int damage) {

    }

    public void aplicarBuff() {
        tipoClasse.aplicarBuff(this);
    }

    public void responderPerguntaCorretamente() {
        if (tipoClasse == Classes.MATEMATICO && contadorRespostas > 0) {
            dano.aumentarAtaque(dano.getAtaque() / contadorRespostas);
            contadorRespostas--;
        }
        if (tipoClasse == Classes.MATEMATICO && contadorRespostas == 0) {
            dano.aumentarAtaque(dano.getAtaque() / 0.0000001);
        }
    }

    public int getVida() {
        return vida;
    }

    @Override
    public void setVida(int vidaNova) {
        this.vida = vidaNova;
    }

    public int onda() {
        return onda;
    }

    public void setOnda(int onda) {
        this.onda = onda;
    }

    public void eliminarInimigo() {
        if (tipoClasse == Classes.FISICO) {
            System.out.println("Inimigo espaguetificado!");
        }
    }

    public void tornarInvencivel() {
        if (tipoClasse == Classes.PROGRAMADOR) {
            // Implementar a lógica para tornar o jogador invencível
            System.out.println("Jogador é agora invencível!");
        }
    }
}
