import java.util.Arrays;
import java.util.ArrayList;

public class MaoVP {
    private int dinheiro;
    private Baralho baralho;
    private CartasVP[] cartasNaMao;
    private ArrayList<CartasVP> descarte;

    public MaoVP(int seed, int saldoInicial) {
        this.dinheiro = saldoInicial;   
        this.cartasNaMao = new CartasVP[5];
        this.baralho = new Baralho(seed);
        this.baralho.embaralhar();
        this.descarte = new ArrayList<>();
    }

    public void gerarMao() {
        for (int i=0; i < 5; i++) {
            this.cartasNaMao[i] = this.baralho.puxarCarta();
        }
    }

    public void trocarCarta(int num) {
        this.descarte.add(this.cartasNaMao[num]);
        this.cartasNaMao[num] = this.baralho.puxarCarta();
    }

    public int getDinheiro() {
        return this.dinheiro;
    }

    public void recarregarBaralho() {
        for (int i = 0; i < 5; i++) { //movemos a mao para o fim do baralho
            this.baralho.add(this.cartasNaMao[i]);
        }
        for (CartasVP carta : this.descarte) { //move a pilha de descarte para o fim do baralho
            this.baralho.add(carta);
        }
        this.descarte.clear(); //limpamos a pilha de descarte, ja que movemos todas as suas cartas para o baralho
    }

    public void embaralhar() {
        this.baralho.embaralhar();
    }

    private boolean temDoisPares () {
        int pares = 0;
        for (int i = 0; i< 5; i++) {
            for(int j=i+1; j< 5; j++) {
                if (this.cartasNaMao[i].getValor() == this.cartasNaMao[j].getValor()) pares++;
                if (pares == 2) return true;  
            }
        }
        return false;
    }

    private boolean temTrinca() {
        for (int i=0; i<5; i++) {
            for (int j = i + 1; j<5; j++) {
                for(int k = j+1; k < 5; k++) {
                    if (this.cartasNaMao[i].getValor() == this.cartasNaMao[j].getValor() && this.cartasNaMao[i].getValor() == this.cartasNaMao[k].getValor()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean temStraight() {
        int[] valoresDasCartas = new int[5]; //vamos guardar os valores das cartas em um vetor separado
        for (int i=0; i<5; i++) {
            valoresDasCartas[i] = this.cartasNaMao[i].getValor();
        }
        Arrays.sort(valoresDasCartas); //ordenamos os valores das cartas em ordem crescente
        if (valoresDasCartas[4] == valoresDasCartas[3] +1 && valoresDasCartas[3] == valoresDasCartas[2] +1 && valoresDasCartas[2] == valoresDasCartas[1] +1 && valoresDasCartas[1] == valoresDasCartas[0] +1) {
            return true; 
        }
        return false;
    }

    private boolean temFlush() {
        if (this.cartasNaMao[0].getNaipe() == this.cartasNaMao[1].getNaipe() && this.cartasNaMao[0].getNaipe() == this.cartasNaMao[2].getNaipe() && this.cartasNaMao[0].getNaipe() == this.cartasNaMao[3].getNaipe() && this.cartasNaMao[0].getNaipe() == this.cartasNaMao[4].getNaipe()) {
            return true; 
        }
        return false;
    }
        
    private boolean temFullHouse() {
        int[] valoresDasCartas = new int[5]; //vamos guardar os valores das cartas em um vetor separado
        for (int i=0; i<5; i++) {
            valoresDasCartas[i] = this.cartasNaMao[i].getValor();
        }
        Arrays.sort(valoresDasCartas); //ordenamos os valores das cartas em ordem crescente
        if ((valoresDasCartas[0] == valoresDasCartas[1] && valoresDasCartas[0] == valoresDasCartas[2] && valoresDasCartas[3] == valoresDasCartas[4]) || (valoresDasCartas[0] == valoresDasCartas[1] && valoresDasCartas[2] == valoresDasCartas[3] && valoresDasCartas[3] == valoresDasCartas[4])) {
            return true;
        }
        return false;
    }

    private boolean temQuadra() {
        int[] valoresDasCartas = new int[5]; //vamos guardar os valores das cartas em um vetor separado
        for (int i=0; i<5; i++) {
            valoresDasCartas[i] = this.cartasNaMao[i].getValor();
        }   
        Arrays.sort(valoresDasCartas); //ordenamos os valores das cartas em ordem crescente
        if ((valoresDasCartas[0] == valoresDasCartas[1] && valoresDasCartas[0] == valoresDasCartas[2] && valoresDasCartas[0] == valoresDasCartas[3]) || (valoresDasCartas[1] == valoresDasCartas[2] &&valoresDasCartas[1] == valoresDasCartas[3] &&valoresDasCartas[1] == valoresDasCartas[4])) {
            return true;
        }
        return false;
    }

    private boolean temStraightFlush() {
        if (temStraight() && temFlush()) return true;
        return false;
    }

    private boolean temRoyalFlush() {
        if (temFlush() == false) return false;
        int[] valoresDasCartas = new int[5]; //vamos guardar os valores das cartas em um vetor separado
        for (int i=0; i<5; i++) {
            valoresDasCartas[i] = this.cartasNaMao[i].getValor();
        }   
        Arrays.sort(valoresDasCartas); //ordenamos os valores das cartas em ordem crescente
        if (valoresDasCartas[0] == 10 && valoresDasCartas[1] == 11 && valoresDasCartas[2] == 12 && valoresDasCartas[3] == 13 && valoresDasCartas[4] == 14) {
            return true;
        }
        return false;
    }

    public int verificarCombo() {
        if (temRoyalFlush()) return 200;
        if (temStraightFlush()) return 100;
        if (temQuadra()) return 50;
        if (temFullHouse()) return 20;
        if (temFlush()) return 10;
        if (temStraight()) return 5;
        if (temTrinca()) return 2;
        if (temDoisPares()) return 1;
        return 0;
    }

    public int getLucro(int aposta) {
        return (this.verificarCombo() * aposta);
    }

    public void modificarDinheiro(int aposta) {
        this.dinheiro -= aposta;
        this.dinheiro += this.verificarCombo() * aposta;
    }

    public void imprimirMao() {
        System.out.println("+-----+ +-----+ +-----+ +-----+ +-----+");
        System.out.println("|     | |     | |     | |     | |     |");
        for (int i=0; i<5; i++) {
            String valor; //11 = J; 12 = Q; 13 = K; 14 = As
            if (this.cartasNaMao[i].getValor() == 11) valor = "J ";
            else if (this.cartasNaMao[i].getValor() == 12) valor = "Q ";
            else if (this.cartasNaMao[i].getValor() == 13) valor = "K ";
            else if (this.cartasNaMao[i].getValor() == 14) valor = "A ";
            else if (this.cartasNaMao[i].getValor() == 10) valor = "10";
            else valor = this.cartasNaMao[i].getValor() + " "; //a adição transformaa o int em string
            char naipe = '0'; // 1 = ♣ ; 2 = ♥; 3 = ♠; 4 = ♦     
            if (this.cartasNaMao[i].getNaipe() == 1) naipe = '♣';
            else if (this.cartasNaMao[i].getNaipe() == 2) naipe = '♥';
            else if (this.cartasNaMao[i].getNaipe() == 3) naipe = '♠';
            else if (this.cartasNaMao[i].getNaipe() == 4) naipe = '♦';
            System.out.print("| " + valor + naipe + " | "); //print normal para nao pular linha a cada loop
        }
        System.out.println("");
        System.out.println("|     | |     | |     | |     | |     |");
        System.out.println("+-----+ +-----+ +-----+ +-----+ +-----+ ");
        System.out.println("  (1)     (2)     (3)     (4)     (5)");
    }

}