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

    QUESTAO2("Encontre o limite lim x —> -1, (x^2 + 3x + 2).",
            "A) -2  \n" +
                    "B) 0  \n" +
                    "C) 1  \n" +
                    "D) 2  \n" +
                    "E) 4  ",
            "B",
            5),

    QUESTAO3("Dada a função  f(x) = 3x - 5 ), qual é o valor de f(2)?",
            "A) 1  \n" +
                    "B) 3  \n" +
                    "C)-1  \n" +
                    "D)-3  \n" +
                    "E) 5  ",
            "A",
            2),

    QUESTAO4("A função g(x) = -2x + 4 intercepta o eixo y em qual ponto?", 
            "A) (0, -4)  \n" +
                    "B) (2, 0)  \n" +
                    "C) (0, 4)  \n" +
                    "D) (-2, 0)  \n" +
                    "E) (4, 0)  ",
            "C",
            2),

    QUESTAO5("Se a função h(x) = 5x + k passa pelo ponto (1, 7), qual é o valor de k?", 
            "A) 1  \n" +
                    "B) 2  \n" +
                    "C) 3  \n" +
                    "D) 4  \n" +
                    "E) 5  ",
            "B",
            2),

    QUESTAO6("Qual é a raiz da função f(x) = 4x - 8?", 
            "A) 1  \n" +
                    "B) 2  \n" +
                    "C) -1  \n" +
                    "D) -2  \n" +
                    "E) 0  ",
            "B",
            2),

    QUESTAO7("Dada a função f(x) = 2x + 3, qual o valor de f(2)?", 
            "A) 5  \n" +
                    "B) 7  \n" +
                    "C) 8  \n" +
                    "D) 9  ",
            "B",
            2),

    QUESTAO8("Qual é o valor de f(0), para a função f(x) = x^2 - 4x?", 
            "A) 0  \n" +
                    "B) -4  \n" +
                    "C) 4  \n" +
                    "D) -8  ",
            "A",
            2),

    QUESTAO9("Se f(x) = 3x - 5, qual o valor de x quando f(x) = 4?", 
            "A) 3  \n" +
                    "B) 4  \n" +
                    "C) 5  \n" +
                    "D) 6  ",
            "C",
            2),

    QUESTAO10("Dada a função f(x) = x^2 + 2x + 1, qual o valor de f(-2)?", 
            "A) 0  \n" +
                    "B) -1  \n" +
                    "C) 4  \n" +
                    "D) 9  ",
            "A",
            2),

    QUESTAO11("Se f(x) = 2x^2 - 4x + 3, qual o valor de x quando f(x) = 3?", 
            "A) 0  \n" +
                    "B) 1  \n" +
                    "C) 2  \n" +
                    "D) 3  ",
            "B",
            2),

    QUESTAO12("Qual o valor de sin(30º)?", 
            "A) 1  \n" +
                    "B) 0.5  \n" +
                    "C) 0  \n" +
                    "D) 0.866  ",
            "B",
            3),

    QUESTAO13("Qual o valor de cos(90º)?", 
            "A) 1  \n" +
                    "B) 0.5  \n" +
                    "C) 0  \n" +
                    "D) 0.866  ",
            "C",
            3),

    QUESTAO14("Qual o valor de tan(45º)?", 
            "A) 0  \n" +
                    "B) 1  \n" +
                    "C) √3  \n" +
                    "D) 0.5  ",
            "B",
            3),

    QUESTAO15("Se sin(x) = cos(30º), qual o valor de (x)?", 
            "A) 30º  \n" +
                    "B) 60º  \n" +
                    "C) 45º  \n" +
                    "D) 90º  ",
            "B",
            3),

    QUESTAO16("Qual o valor de tan(60º)?", 
            "A) √3  \n" +
                    "B) 1  \n" +
                    "C) 0.5  \n" +
                    "D) √2  ",
            "A",
            3),

    QUESTAO17("Qual o valor de (x) na equação (2x + 4 = 10)?", 
            "A) 1  \n" +
                    "B) 2  \n" +
                    "C) 3  \n" +
                    "D) 4  ",
            "C",
            2),

    QUESTAO18("Se x + 3 = 7, qual o valor de (x)?", 
            "A) 2  \n" +
                    "B) 3  \n" +
                    "C) 4  \n" +
                    "D) 5  ",
            "D",
            2),

    QUESTAO19("Qual o valor de (x) em (3x = 9)?", 
            "A) 2  \n" +
                    "B) 3  \n" +
                    "C) 4  \n" +
                    "D) 6  ",
            "B",
            2),

    QUESTAO20("Se 2x - 3 = 4x + 1, qual o valor de (x)?", 
            "A) -2  \n" +
                    "B) 0  \n" +
                    "C) 1  \n" +
                    "D) 2  ",
            "A",
            2),

    QUESTAO21("Qual o valor de (x) na equação (4x - 5 = 2x + 3)?", 
            "A) 1  \n" +
                    "B) 2  \n" +
                    "C) 3  \n" +
                    "D) 4  ",
            "D",
            2);



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
