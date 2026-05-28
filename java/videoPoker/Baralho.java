import java.util.Collections;
import java.util.Random;
import java.util.ArrayList; 

public class Baralho {
    private ArrayList<CartasVP> cartas; //vetor que vai guardar todas as cartas
    private Random gerador;

    public Baralho(int seed) {
        this.cartas = new ArrayList<>();
        if (seed == 0) {
            this.gerador = new Random();
        }
        else {
            this.gerador = new Random(seed);
        }
        this.construirBaralho();
        this.embaralhar();
    }

    private void construirBaralho() {
        for (int i =1; i <5; i++) { //loop para os naipes (1 a 4) 1 = ♣ ; 2 = ♥; 3 = ♠; 4 = ♦     
            for (int j =2; j<15; j++) {    //loop para os valores (2 a 14)
                CartasVP novaCarta = new CartasVP(i, j);
                this.cartas.add(novaCarta); //adicionamos a nova carta no baralho
            }
        }
    }

    public CartasVP puxarCarta() {
        return this.cartas.remove(0); //puxamos uma carta do baralho
    }

    public void add(CartasVP carta) {
        this.cartas.add(carta);
}

    public void embaralhar() {
        Collections.shuffle(this.cartas, gerador);
    }

}