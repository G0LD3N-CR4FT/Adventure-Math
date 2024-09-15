package src.objetos;

public class Jogador implements Pessoa {
    private Long vida;
    private Armas tipoArma;
    private Classes tipoClasse;
    private Danos dano;
    private int onda;
    private int contadorRespostas = 10;

    public Jogador(Long vida, int pontos, Armas tipoArma, Classes tipoClasse, Danos dano, int onda) {
        this.vida = vida;
        this.tipoArma = tipoArma;
        this.tipoClasse = tipoClasse;
        this.dano = dano;
        this.onda = onda;
    }

    @Override
    public String getTipoClasse() {
        return tipoClasse.name();
    }

    @Override
    public int getDamage() {
        return dano.getAtaque();
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

    public Long getVida() {
        return vida;
    }

    public void setVida(Long vida) {
        this.vida = vida;
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
