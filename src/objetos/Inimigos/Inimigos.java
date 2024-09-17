package src.objetos.Inimigos;

import src.objetos.Jogador;
import src.objetos.Perguntas;
import src.objetos.interfaces.Pessoa;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Inimigos {

    private TipoMonstro monstro;
    private int vida;

    public TipoMonstro getMonstro() {
        return this.monstro;
    }
    public void setMonstro(TipoMonstro monstro) {
        this.monstro = monstro;
    }

    public int getVida() {
        return this.vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    // Convocar um tipo de Inimigo de forma Aleatoria
    public void convocarMonstro(Jogador p){
        TipoMonstro[] tipoMonstro = TipoMonstro.values();

        List<TipoMonstro> listaTipoMonstroHabilitados = Arrays.stream(tipoMonstro)
                .filter(a -> a.getDificuldade() <= p.getOndas())
                .toList();

        int mostroAleatorio = new Random().nextInt(listaTipoMonstroHabilitados.size());
        this.setMonstro(tipoMonstro[mostroAleatorio]);
        this.vida = tipoMonstro[mostroAleatorio].getVida();
    }

    // Gerando uma pergunta baseado na onda do Jogador
    public static Perguntas gerarQuestao(Jogador jogador){
        // Pegando todos os objetos do Enum Perguntas
        Perguntas[] perguntas = Perguntas.values();

        // Selecionando as perguntas por dificuldade
        List<Perguntas> perguntasDificuldade = Arrays.stream(perguntas)
                .filter(a -> a.getDificuldade() <= jogador.getOndas())
                .toList();

        // Sorteando uma pergunta da lista
        int perguntaAleatoriaDificuldade = new Random().nextInt(perguntasDificuldade.size());
        return perguntasDificuldade.get(perguntaAleatoriaDificuldade);
    }

    // Função de batalha do jogo
    public void perguntar(Jogador jogador, Scanner entrada) {
        if(this.monstro.getVida() > 0){

            Perguntas Questao = gerarQuestao(jogador);

            System.out.println(Questao.getPergunta());
            System.out.println(Questao.getAlternativa());
            // Mostrar a resposta para facilitar
            System.out.println("A Resposta correta é: " + Questao.getResposta());

            String respostaCorretaDificuldade = Questao.getResposta();

            // Tentando Limpar o Buffer
            entrada.nextLine();

            System.out.println("\n"+ "Digite sua resposta:");
            String resposta = entrada.nextLine();

            if (resposta.equalsIgnoreCase(respostaCorretaDificuldade)) {
                System.out.println("Acertou");
                System.out.println("Voce carrega seu ataque para acertar seu inimigo com a/o "+ jogador.getArmas().name() +", tirando "+ jogador.getDamage() +
                        " de dano ao seu inimigo");
                this.setVida(this.getVida() - jogador.getDamage());
            } else {
                System.out.println("Errou\n");
                System.out.println("O Inimigo se infureceu com sua resposta. Ele te ataca causando "+ this.monstro.getDamage() + " na sua barra de vida" +
                        "Press enter para continuar -->");
                jogador.setVida(jogador.getVida()-this.monstro.getDamage());
            }
        }
    }

}
