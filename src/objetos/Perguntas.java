package src.objetos;

public enum Perguntas {

    QUESTAO1("10 + 2","12", 3),
    QUESTAO2("10 - 2","12", 3);

    private String pergunta;
    private String resposta;
    private int dificuldade;

    Perguntas(String s, String number, int i) {
        this.pergunta = s;
        this.resposta = number;
        this.dificuldade = i;
    }

    // Metodos
    public String getPergunta() {
        return pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public int getDificuldade() {
        return dificuldade;
    }

}
