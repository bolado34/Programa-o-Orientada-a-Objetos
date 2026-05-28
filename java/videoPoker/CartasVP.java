 public class CartasVP {  

    public int naipe;   //1 = ♣ ; 2 = ♦; 3 = ♥; 4 = ♠
    public int valor;  //11 = J; 12 = Q; 13 = K; 14 = As

    public CartasVP(int naipeSorteado, int valorSorteado) {
        this.naipe = naipeSorteado;
        this.valor = valorSorteado;
    }
    public int getValor() { 
        return this.valor; 
    }
    
    public int getNaipe() { 
        return this.naipe; 
    }

}