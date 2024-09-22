package src.objetos.Inimigos;

import src.colors.ConsoleColors;
import src.objetos.Jogador;
import src.objetos.Perguntas;
import src.objetos.interfaces.Pessoa;

import java.util.*;

public class Inimigos {

    private TipoMonstro monstro;
    private static List<Perguntas> perguntasRestantes = new ArrayList<Perguntas>();
    private int vida;
    private int dano;

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

    public int getDano() {
        return this.dano;
    }
    public void setDano(int dano) {
        this.dano = dano;
    }

    // Convocar um tipo de Inimigo de forma Aleatoria
    public void convocarMonstro(Jogador p){
        TipoMonstro[] tipoMonstro = TipoMonstro.values();

        List<TipoMonstro> listaTipoMonstroHabilitados = Arrays.stream(tipoMonstro)
                .filter(a -> a.getDificuldade() <= p.getOndas())
                .toList();

        int mostroAleatorio = new Random().nextInt(listaTipoMonstroHabilitados.size());
        this.setMonstro(listaTipoMonstroHabilitados.get(mostroAleatorio));
        this.vida = listaTipoMonstroHabilitados.get(mostroAleatorio).getVida();
        this.dano = listaTipoMonstroHabilitados.get(mostroAleatorio).getDamage();
    }

    // Gerando uma pergunta baseado na onda do Jogador
    public static Perguntas gerarQuestao(Jogador jogador){

        if(perguntasRestantes.isEmpty()){
            // Pegando todos os objetos do Enum Perguntas
            Perguntas[] perguntas = Perguntas.values();

            // Selecionando as perguntas por dificuldade
            List<Perguntas> perguntasDificuldade = Arrays.stream(perguntas)
                    .filter(a -> a.getDificuldade() <= jogador.getOndas())
                    .toList();
            perguntasRestantes.addAll(perguntasDificuldade);
        }

        // Sorteando uma pergunta da lista
        int perguntaAleatoriaDificuldade = new Random().nextInt(perguntasRestantes.size());
        return perguntasRestantes.get(perguntaAleatoriaDificuldade);
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
                System.out.println("ACERTOU ✅");
                System.out.println("Voce carrega seu ataque para acertar seu inimigo com a/o "+ jogador.getArmas().name() +", tirando "+ jogador.getDamage() +
                        " de dano ao seu inimigo");
                // Removendo Perguntas Repetidas
                perguntasRestantes.remove(Questao);
                this.setVida(this.getVida() - jogador.getDamage());
            } else {
                System.out.println("ERROU ❌\n");
                System.out.println("O Inimigo se infureceu com sua resposta. Ele te ataca causando "+ this.monstro.getDamage() + " na sua barra de vida" +
                        "\n Press enter para continuar -->");
                jogador.setVida(jogador.getVida()-this.monstro.getDamage());
            }
        }
    }

    public void statusMonstro() {
        System.out.println("Nome: " + this.monstro.getNome());
        System.out.println(this.monstro.getFotoMonstro());
        System.out.println("Vida: " + this.getVida() + " | Dano: " + this.getDano());
    
        // Criar barra de vida
        int barLength = 40; // Comprimento total da barra
        int filledLength = (int) ((double) this.getVida() / this.monstro.getVida() * barLength);
        StringBuilder bar = new StringBuilder(ConsoleColors.BLACK_BOLD +"["+ ConsoleColors.RESET);
    
        for (int i = 0; i < barLength; i++) {
            if (i < filledLength) {
                bar.append(ConsoleColors.RED_BOLD +"█"+ ConsoleColors.RESET); // Parte preenchida da barra
            } else {
                bar.append(" "); // Parte vazia da barra
            }
        }
    
        bar.append(ConsoleColors.BLACK_BOLD +"] "+ ConsoleColors.RESET + this.getVida() + "/" + this.monstro.getVida());
        System.out.println("Barra de Vida: " + bar.toString());
    }
    

}
