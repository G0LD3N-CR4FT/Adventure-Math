package src.objetos.Inimigos;

import src.objetos.interfaces.Pessoa;

public enum TipoMonstro implements Pessoa {
            MONSTRO1("Ogro Matematico",
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
                    1 ,20, 80),

            MONSTRO2("Abissal Geométrico",
                    "@@                                    ##                                          ..                    \r\n" + //
                        "++                          MM      @@##                            --    --##    @@      @@        \r\n" + //
                        "  ++                    ++..++mm    ####                      ..      ##MM..MM::  ##    ++          \r\n" + //
                        "##                  MMMM  MM++mmmm  ##@@                        @@----++----mm++mm##MM  ##          \r\n" + //
                        "::++##@@        --::::----@@@@mmMM####@@++                      mm--MM####@@##++@@++::              \r\n" + //
                        "@@mm@@MM        ####  MMmm@@##++--####mm##    ++##        ::::++mmMM############MM##MM##########    \r\n" + //
                        "  ::::##..::  @@++    MM@@  ::  ##########++MM##          ::++MMmm#             ++##::::@@####      \r\n" + //
                        "  mm--####  ++####  ##::  --  ####@@############          ::mm                    --##::@@######    \r\n" + //
                        "  ##MM::::    ##  mm##--@@::############  ######          MM@@                      ....MM++####::++\r\n" + //
                        "      --##  ##@@mm##MM##mm########################MM  mm++mm                 ####   #::##--MMMM--:: \r\n" + //
                        "    MM@@..##++    ##MM++##########################@@    ::#                     #++  ..  ++####     \r\n" + //
                        "    MM##MM########::################++########::######::MM@@                      ####..##@@####    \r\n" + //
                        "    ::  ##  ++mm::@@########@@################--####::--mm@@@@                     @##    mm@@##    \r\n" + //
                        "    MM####++::####################--##################..MM@@                       #@@  ####--::::MM\r\n" + //
                        "      ++  ++--##++mm--########++--####  --############MMmm@@                        ##  ##--::####  \r\n" + //
                        "          @@..mmMM    MM@@##      ####################--++@@@@#                     ## ..mm######## \r\n" + //
                        "        --::MM####MMMM######    --##@@################@@++mm@@                    ##  ####MM++####  \r\n" + //
                        "      ++mm@@@@############        MM++mm################MM++@@@@                  ##  ..MMmmMM::++@@\r\n" + //
                        "      ##@@@@mm############        ##  ##MMMM############mm..MM++@@              ##  ..::##::mm##@@##\r\n" + //
                        "            mm########              MM##@@  --######++##@@::--MMMM@@          ##--  ##mm########    \r\n" + //
                        "          ############          @@  ::######@@##########    ++++MMMM####@@##@@  ::##  ##MM######    \r\n" + //
                        "        mmmm##########            --  @@##mm..############    ++++++mm--    mm..mm  --MM######      \r\n" + //
                        "          mm########              ++@@@@--##############@@        ..      mmMMMMMM######mmMM@@      \r\n" + //
                        "          ##@@####                ##mm####@@##############      mmmmmm  @@##MM####MM########--::++  \r\n" + //
                        "            ####                  MM@@####MM##############  ..  ::####mmMM####mmmm@@################\r\n" + //
                        "              ##                ++  ######..MM@@######@@##mm  ..  --mmmm@@++mmMM##@@########        \r\n" + //
                        "                                @@  ####@@##--MMmm########@@  mm######@@##@@####@@########          \r\n" + //
                        "                                  ##..mm####@@::############++--MM++########MM##########            \r\n" + //
                        "                              ++::@@MM      ##############mm##MM--::mm##################            \r\n" + //
                        "              ++##++              MM################################--MM##########mm::      ##      \r\n" + //
                        "              ..mm@@          mm  --######mm####################mm################  ##    ++##mm##  \r\n" + //
                        "                ####mm    mm  ##  ########  ++@@####MM########..mm    mm@@######  ####  mm##MM####  \r\n" + //
                        "          ::####MM####@@  ++mm  ##--####@@############mm########@@############  MM##mm##########++##\r\n" + //
                        "              ##########@@  MM@@##############mm@@######@@mmmm@@  ##::--MM  ++MM########..      ####\r\n" + //
                        "        ############################@@::##@@::########@@##########  ##  ##@@############::##      ##\r\n" + //
                        "        ::MM####      ##################..  ################mm####++--++############  ######        \r\n" + //
                        "      ########          ####################@@##########--MM##########..MM@@######        ######    \r\n" + //
                        "    MM######            ::##########################################MM  ..@@                ##      \r\n" + //
                        "    ..####              ++##        ################################  @@MM##                ##MM    \r\n" + //
                        "    ++##                  ##              ####################          mm                          \r\n" + //
                        "    ::##                  ##                ####                        ##                          \r\n" + //
                        "      ##                                                                ++                          \r\n" + //
                        "      ##                                                                MM                          \r\n" + //
                        "                                                                        ++",
                    "Uma criatura forte aparece?",
                    3 ,60, 140),

            MONSTRO3("Espectro Poligonal",
                       "                                          MM##  ####                                                \r\n" + //
                       "                                          ####..########                                            \r\n" + //
                       "                                        ::        ##########                                        \r\n" + //
                       "                                      --        ##  ######  ##                                      \r\n" + //
                       "                                    ####    ######@@@@##mm  ##                                      \r\n" + //
                       "                                  ######  ::########  ##  mm####                              @@    \r\n" + //
                       "              MM##                      ##            ::::++####                              mm    \r\n" + //
                       "            ::######              ####::  ..############@@  ####                                    \r\n" + //
                       "            --######              ####--    ########  ######--####MM                                \r\n" + //
                       "              ######                ######++########--##############                        ##      \r\n" + //
                       "                                    ##############MM##############@@##                      ##      \r\n" + //
                       "      ++                              ##MM########++##############++######mm                        \r\n" + //
                       "                                        ####  @@####################    MM####@@@@                  \r\n" + //
                       "                                    ##..##  MM########MM##@@##  ####          MM####@@    ##        \r\n" + //
                       "          ##  ##    ##              ####    ####@@mm####@@  ####mmmm              ##MM##  ##        \r\n" + //
                       "      ##  MM  ##                  ######      ####mmMM####MM##@@##..                  ##  ##        \r\n" + //
                       "        mm    ##  MM                ##        ##++MMmmmm####MM##MM##                  ####          \r\n" + //
                       "          ######                ####            @@@@@@##MM##@@######                  ####          \r\n" + //
                       "            ########                              mm        ##MMMMmm                  ####          \r\n" + //
                       "                ####          MM##                          ####                      ++##          \r\n" + //
                       "                  mmmm      ##                      ##      ####        ##                          \r\n" + //
                       "                      @@##  ####                    ####    ######    ##              ##            \r\n" + //
                       "                        MM##++                    ########--####  ######            @@##            \r\n" + //
                       "                          ##MM                  ######  ##MM############            ++##            \r\n" + //
                       "                                          ##########  --##@@mm######--                ##            \r\n" + //
                       "                                        MM##########      ####  ######                ##            \r\n" + //
                       "                                    ##@@######MM          ++  ##    ##              MM@@            \r\n" + //
                       "                                  ########                        mmmm          ######              \r\n" + //
                       "                              ########                            ##        ##      @@##            \r\n" + //
                       "                            ######                              ####                ##              \r\n" + //
                       "                              MM                                ####                ######          \r\n" + //
                       "                            ++##++                            ##++##                  ::##          \r\n" + //
                       "                                ####                          MM####                                \r\n" + //
                       "                                    ####                        ####MM                              \r\n" + //
                       "                                      ####--                      --####            ##  ##          \r\n" + //
                       "                                        MM##mm                        mm##..                        \r\n" + //
                       "                                          ##mmmm                          ##@@                      \r\n" + //
                       "                                        ####                                ####                    \r\n" + //
                       "                                        ##                                  ####                    \r\n" + //
                       "                                      ::                                    ##                      \r\n" + //
                       "                                  @@--                                      ##                      \r\n" + //
                       "                                ##::                                      ++##                      \r\n" + //
                       "                              ##::                                        ##                        \r\n" + //
                       "                            --++                                          ##                        \r\n" + //
                       "                                                                          ##                        \r\n" + //
                       "                                                                        ##++                        \r\n" + //
                       "                                                                      ------                        \r\n" + //
                        "                                                                       ####   ",
                    "Uma criatura forte aparece?",
                    2 ,40, 100);                    

    private String nome;
    private String fotoMonstro;
    private String encontro;
    private int dificuldade;
    private int dano;
    private int vida;

    TipoMonstro(String nome, String foto, String encontro, int dificuldade, int dano, int vida) {
        this.nome = nome;
        this.fotoMonstro = foto;
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

    public String getFotoMonstro() {
        return fotoMonstro;
    }

    public void setFotoMonstro(String fotoMonstro) {
        this.fotoMonstro = fotoMonstro;
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
