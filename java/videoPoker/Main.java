import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Semente: ");
        Scanner leitura = new Scanner(System.in);
        int semente = leitura.nextInt(); //pegamos a seed
        System.out.print("Saldo inicial: ");
        int saldo = leitura.nextInt(); //pegamos o saldo inicial
        leitura.nextLine(); //limpeza do buffer
        int valorDaAposta = 0;
        MaoVP novaMao = new MaoVP(semente, saldo);
        while (true) { //loop do jogo, so termina quando o usuario escolher sair ou seu saldo zerar
            if (saldo == 0) {
                System.out.println("Seu saldo acabou. Tente jogar outra vez.");
                return;
            }
            while (true) {
                System.out.println("Saldo atual: $"+saldo);
                System.out.print("Digite o valor da aposta of 'F' para terminar ==> ");
                String comando = leitura.nextLine().trim(); //uso do trim para limpar possíveis espaços nas pontas da leitura
                if (comando.equals("F")) {
                    System.out.println("Terminando o jogo... Parabéns você ainda tem saldo de $" +saldo);
                    return; 
                }
                else {
                    valorDaAposta = Integer.parseInt(comando);
                }
                if (valorDaAposta > saldo) {
                    System.out.println("Saldo insuficiente. Tecle enter para continuar");
                    leitura.nextLine();
                }
                else {
                    break;
                }
            }
            novaMao.gerarMao();
            semente = 0;
            System.out.println("");
            novaMao.imprimirMao();
            for (int n=0; n < 2; n++) {  //loop das rodadas

                System.out.println("Digite o número das cartas que você deseja trocar, separados por espaços: ");
                String linhaTroca = leitura.nextLine().trim(); //lemos a linha inteira de cartas trocadas
                if (!linhaTroca.isEmpty()) {
                    String[] leituraTrocadas = linhaTroca.split("\\s+");  //partimos a linha inteira usando split
                    int[] numTrocadas = new int [leituraTrocadas.length];  //criamos um vetor com os numeros lidos
                    for (int i = 0; i < leituraTrocadas.length; i++) {     
                        numTrocadas[i] = Integer.parseInt(leituraTrocadas[i]); //transformamos os numeros em int
                    }
                    Arrays.sort(numTrocadas);
                    for (int i=0; i < numTrocadas.length; i++) { //loop para a troca de cartas
                        novaMao.trocarCarta(numTrocadas[i] - 1);
                    }
                }
                novaMao.imprimirMao();

            }
            int combo = novaMao.verificarCombo();
            novaMao.modificarDinheiro(valorDaAposta); //modificamos o saldo
            saldo = novaMao.getDinheiro(); 
            if (combo == 0) {
                System.out.println("Peninha... não ganhou nada nessa rodada");
            } else  {
                int lucro = novaMao.getLucro(valorDaAposta);
                System.out.println("Parabéns. Você acrescentou $"+ lucro +" ao seu saldo");
            }
            novaMao.recarregarBaralho();
            novaMao.embaralhar();
            System.out.println("Tecle enter para continuar");
            leitura.nextLine();
        }
    }
}