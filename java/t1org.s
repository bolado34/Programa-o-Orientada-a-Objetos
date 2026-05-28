.globl main
	
.text
main:
    #vamos começar imprimindo o menu
    li a7, 4            # serviço 4 do a7 imprime uma string
    la a0, menu      # como o endereço usado será o a0, mandamos a string "menu" para o a0
    ecall               # system call






    # 2. Sair do programa
    li a7, 10           # serviço 10 do a7 encerra o programa
    ecall

.data
    menu: .asciz "1- Adicionar vagão no início\n2- Adicionar vagão no final\n3- Remover vagão por ID\n4- Listar trem\n5- Buscar vagão\n6- Sair"
