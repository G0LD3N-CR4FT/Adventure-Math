package src.objetos.Inimigos;

import src.objetos.interfaces.Pessoa;

public enum TipoMonstro implements Pessoa {
            MOSTRO1("Ogro Matematico",
                    "       ,      ,\n" +
                            "      /(.-\"\"-.)\\\n" +
                            " |\\   \\/      \\/ /|\n" +
                            " | \\ / =.  .= \\ /  |\n" +
                            "  \\( \\  o\\/o  / ) /\n" +
                            "   \\_, '-/  \\-'  ,_/\n" +
                            "     /   \\__/     \\\n" +
                            "     \\ \\__/\\__/ /\n" +
                            "   ___\\ \\|--|/ /___\n" +
                            " /`     \\       /   `\\\n" +
                            "/        '----'        \\",
                    "Um Ogro Matematico Furioso aproxima de voce, o que voce faz ?",
                    1 ,20, 80);


    private String nome;
    private String fotoMostro;
    private String encontro;
    private int dificuldade;
    private int dano;
    private int vida;

    TipoMonstro(String nome, String foto, String encontro, int dificuldade, int dano, int vida) {
        this.nome = nome;
        this.fotoMostro = foto;
        this.encontro = encontro;
        this.dificuldade = dificuldade;
        this.dano = dano;
        this.vida = vida;
    }

    @Override
    public int getVida() {
        return vida;
    }

    @Override
    public void setVida(int vidaNova) {
        this.vida = vidaNova;
    }

    @Override
    public int getDamage() {
        return dano;
    }

    @Override
    public void setDamage(int danoNovo) {
        this.dano = danoNovo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFotoMostro() {
        return fotoMostro;
    }

    public void setFotoMostro(String fotoMostro) {
        this.fotoMostro = fotoMostro;
    }

    public String getEncontro() {
        return encontro;
    }

    public void setEncontro(String encontro) {
        this.encontro = encontro;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }
}
