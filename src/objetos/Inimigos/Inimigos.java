package src.objetos.Inimigos;

import src.colors.ConsoleColors;
import src.objetos.Jogador;
import src.objetos.Perguntas;
import src.objetos.interfaces.Pessoa;

import java.util.*;

public class Inimigos {

    private TipoMonstro monstro;
    private static List<Perguntas> perguntasRestantes = new ArrayList<Perguntas>();
    private static List<TipoMonstro> monstrosRestantes = new ArrayList<TipoMonstro>();
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
    public void convocarMonstro(Jogador jogador){
        if(monstrosRestantes.isEmpty()){
            // Pegando todos os objetos do Enum Perguntas
            TipoMonstro[] tipoMonstro = TipoMonstro.values();

            // Selecionando as perguntas por dificuldade
            List<TipoMonstro> perguntasDificuldade = Arrays.stream(tipoMonstro)
                    .filter(a -> a.getDificuldade() <= jogador.getOndas())
                    .toList();
            monstrosRestantes.addAll(perguntasDificuldade);
        }

        int mostroAleatorio = new Random().nextInt(monstrosRestantes.size());
        this.setMonstro(monstrosRestantes.get(mostroAleatorio));
        this.vida = monstrosRestantes.get(mostroAleatorio).getVida();
        this.dano = monstrosRestantes.get(mostroAleatorio).getDamage();
    }

    // Convocar o Boss de forma Aleatoria
    public void convocarBoss(Jogador p){
        TipoMonstro[] tipoMonstro = TipoMonstro.values();

        List<TipoMonstro> listaTipoMonstroHabilitados = Arrays.stream(tipoMonstro)
                .filter(a -> a.getDificuldade() >= 10)
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

    // Evitar o inimigo ficar com vida negativa
    public void danoTomado(int dano, TipoMonstro monstro){
        int vidaRestante = this.getVida();
            if(dano > vidaRestante){
                vidaRestante = 0;
                monstrosRestantes.remove(monstro);
            } else {
                vidaRestante -= dano;
            }
        setVida(vidaRestante);
    }
    // Função de batalha do jogo
    public void perguntar(Jogador jogador, Scanner entrada) {
        if(this.monstro.getVida() > 0){

            Perguntas Questao = gerarQuestao(jogador);

            System.out.println("\n" + Questao.getPergunta());
            System.out.println(Questao.getAlternativa());
            // Mostrar a resposta para facilitar
            System.out.println("A Resposta correta é: " + Questao.getResposta());

            String respostaCorretaDificuldade = Questao.getResposta();

            // Tentando Limpar o Buffer (Devido ao NextInt, porém foi removido provisoriamente)
            // entrada.nextLine();

            System.out.println("\n"+ ConsoleColors.YELLOW_BOLD + "DIGITE SUA RESPOSTA:" + ConsoleColors.RESET);
            String resposta = entrada.nextLine();

            if (resposta.equalsIgnoreCase(respostaCorretaDificuldade)) {
                System.out.println(ConsoleColors.GREEN_BOLD +"ACERTOU ✅" + ConsoleColors.RESET + "\n");
                System.out.println(ConsoleColors.CYAN_BOLD +"VOCÊ ATACA SEU INIMIGO COM A/O "+ jogador.getArmas().name() +", INFRINGINDO "+ jogador.getDamage() +
                        " DE DANO" + ConsoleColors.RESET);
                // Removendo Perguntas Repetidas
                perguntasRestantes.remove(Questao);
                this.danoTomado(jogador.getDamage(), this.getMonstro());
            } else {
                System.out.println(ConsoleColors.RED_BOLD +"ERROU ❌" + ConsoleColors.RESET + "\n");
                System.out.println(ConsoleColors.RED_BRIGHT +"O INIMIGO SE ENFURECEU COM SUA RESPOSTA. ELE TE ATACA CAUSANDO "+ this.monstro.getDamage() + " DE DANO NA SUA BARRA DE VIDA" +
                ConsoleColors.RESET);
                jogador.setVida(jogador.getVida()-this.monstro.getDamage());
            }
        }
    }

    public void statusMonstro() {
        System.out.println("\n Nome: " + this.monstro.getNome());
        System.out.println(this.monstro.getFotoMonstro());
        System.out.println(ConsoleColors.RED_BOLD + "Vida: " + this.getVida() + "\uD83D\uDDA4 | Dano: " + this.getDano() + "\uD83D\uDD2A" + ConsoleColors.RESET);

        // Criar barra de vida
        int barLength = 40; // Comprimento total da barra
        int filledLength = (int) ((double) this.getVida() / this.monstro.getVida() * barLength);
        StringBuilder bar = new StringBuilder(ConsoleColors.BLACK_BOLD +"["+ ConsoleColors.RESET);
    
        for (int i = 0; i < barLength; i++) {
            if (i < filledLength) {
                bar.append(ConsoleColors.RED_BOLD + "█"+ ConsoleColors.RESET); // Parte preenchida da barra
            } else {
                bar.append(" "); // Parte vazia da barra
            }
        }
    
        bar.append(ConsoleColors.BLACK_BOLD +"] "+ ConsoleColors.RESET + this.getVida() + "/" + this.monstro.getVida());
        System.out.println("Barra de Vida: " + bar.toString());
    }
    

}
