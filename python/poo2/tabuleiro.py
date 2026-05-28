class Tabuleiro:
    def __init__(self, dimensoes, numeros):
        self.linha_zero = -1 #devemos guardar a linha e a coluna do 0
        self.coluna_zero = -1
        self.dimensoes = dimensoes
        self.matriz = []
        self.criar_tabuleiro(numeros)

    
    def criar_tabuleiro(self, numeros):
        for i in range(self.dimensoes):
            linha = []                  #criação de linha vazia
            for j in range(self.dimensoes):
                linha.append(0)             #preenchemos a linha vazia com zeros
            self.matriz.append(linha)            #adicionamos a linha com 0s na matriz
        indice = 0      #vamos usar isso para saber quantos elementos ja inserimos na matriz
        for i in range(self.dimensoes):
            for j in range(self.dimensoes) :
                valor_atual = numeros[indice]
                self.matriz[i][j] = valor_atual #usamos o vetor numeros para preencher a matriz de acordo com o indice 
                if valor_atual == 0: #se estivermos lidando com o 0, devemos guardar a sua posicao de linha e coluna para poder move-lo depois
                    self.linha_zero = i
                    self.coluna_zero = j
                indice += 1

    def printar_tabuleiro(self):
        linha_separadora = "+" + "------+" * self.dimensoes #a linha separadora sempre começa com + e, para cada dimensao da matriz, adicionamos mais um "-----+"
        print(linha_separadora)
        for linha in self.matriz:
            linha_nums = "|"    #a linha de numero sempre começa com |
            for numero in linha:
                if numero != 0:
                    linha_nums += f"{numero:>4}  |"  #centralizacao correta
                else:
                    linha_nums += "      |"
            print(linha_nums)
            print(linha_separadora)
        print("")

    def movimentar(self, movimento):
        novaLinha = self.linha_zero 
        novaColuna = self.coluna_zero
        match movimento:
            case "u": 
                novaLinha += 1                
            case "d":
                novaLinha -= 1
            case "l":
                novaColuna += 1
            case "r":
                novaColuna -= 1
        
        if (0 <= novaLinha < self.dimensoes) and (0 <= novaColuna < self.dimensoes): #nao podemos deixar o 0 ir para fora do tabuleiro
            self.matriz[self.linha_zero][self.coluna_zero] = self.matriz[novaLinha][novaColuna]
            self.matriz[novaLinha][novaColuna] = 0
            self.linha_zero = novaLinha
            self.coluna_zero = novaColuna
            
    def verificar_ordem(self):
        resultado = "True"
        for i in range(self.dimensoes):
            for j in range(self.dimensoes -1):
                if (i*self.dimensoes) + j != self.matriz[i][j]:
                    resultado = "False"
        print(f"Posicao final: {resultado}")
                
            