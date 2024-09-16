package src.objetos;

import src.objetos.interfaces.Pessoa;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Inimigos implements Pessoa {

    private int vida = 80;
    private int dano = 10;
    private String foto;

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

    public void Perguntar(Jogador p, Scanner entrada) {
        Perguntas[] perguntas = Perguntas.values();
        // Selecionando as perguntas por dificuldade
        List<Perguntas> perguntasDificuldade = Arrays.stream(perguntas)
                .filter(a -> a.getDificuldade() <= p.getOndas())
                .toList();
        // Sorteando uma pergunta da lista
        int perguntaAleatoriaDificuldade = new Random().nextInt(perguntasDificuldade.size());

        // int perguntaAleatoria = new Random().nextInt(perguntas.length);

        System.out.println(perguntasDificuldade.get(perguntaAleatoriaDificuldade).getPergunta());
        System.out.println(perguntasDificuldade.get(perguntaAleatoriaDificuldade).getAlternativa());
        // System.out.println(perguntas[perguntaAleatoria].getResposta());
        // String respostaCorreta = perguntas[perguntaAleatoria].getResposta();

        String respostaCorretaDificuldade = perguntasDificuldade.get(perguntaAleatoriaDificuldade).getResposta();

        // Tentando Limpar o Buffer
        entrada.nextLine();  // Descomente se houver um nextInt() ou similar antes dessa função.

        System.out.println("\n "+ "Digite sua resposta:");
        String resposta = entrada.nextLine();

        if (resposta.equalsIgnoreCase(respostaCorretaDificuldade)) {
            System.out.println("Acertou");
            System.out.println("Voce carrega seu ataque para acertar seu inimigo com a/o "+ p.getArmas().name() +", tirando "+ p.getDamage() +
                    " de dano ao seu inimigo");
            this.setVida(this.getVida() - p.getDamage());
        } else {
            System.out.println("Errou\n");
            System.out.println("O Inimigo se infureceu com sua resposta. Ele te ataca causando "+ this.dano + " na sua barra de vida" +
                    "Press enter para continuar -->");
            p.setVida(p.getVida()-this.dano);
        }
    }

}
