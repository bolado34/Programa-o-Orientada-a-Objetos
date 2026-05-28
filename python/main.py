from tabuleiro import Tabuleiro

inicial = input() #usuario insere os valores iniciais do tabuleiro
valores = inicial.split() #dividimos esse numero em um vetor
numeros = [int(x) for x in valores] #convertemos esses numeros, que no momento sao strings, em int
num_de_elementos = len(numeros) #num_de_elementos vai guardar o tamanho do vetor
raiz = int (num_de_elementos ** 0.5) #elevamos a 0,5 e para obter a raíz
tabuleiro = Tabuleiro(raiz, numeros)
x = input()  #vamos armazenar os movimentos em x
movimentos = list(x)   #separamos os movimentos em uma lista para analisa-los individulamente
num_de_movs = len(movimentos)
tabuleiro.printar_tabuleiro()
for i in range(num_de_movs):
    tabuleiro.movimentar(movimentos[i])
    tabuleiro.printar_tabuleiro()
tabuleiro.verificar_ordem()

