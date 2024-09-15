package src.objetos;

public enum Perguntas {

    QUESTAO1("Calcule o limite lim x —> 2, (3x + 1).",
            "A) 5  \n" +
            "B) 6  \n" +
            "C) 7  \n" +
            "D) 8  \n" +
            "E) 9  ",
            "C",
            5),
    QUESTAO2("Calcule o limite lim x —> 2, (3x + 1).",
            "A) 5  \n" +
                    "B) 6  \n" +
                    "C) 7  \n" +
                    "D) 8  \n" +
                    "E) 9  ",
            "C",
            5);

    private final String pergunta;
    private final String alternativa;
    private final String resposta;
    private final int dificuldade;

    Perguntas(String s, String alternativas, String resposta, int i) {
        this.pergunta = s;
        this.alternativa = alternativas;
        this.resposta = resposta;
        this.dificuldade = i;
    }

    // Metodos
    public String getPergunta() {
        return pergunta;
    }

    public String getAlternativa() {
        return alternativa;
    }

    public String getResposta() {
        return resposta;
    }

    public int getDificuldade() {
        return dificuldade;
    }

}
