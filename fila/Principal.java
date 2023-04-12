/* Discentes
* Luan Pinheiro Azevedo
* Carlos Gil Martins
*/

import java.util.Scanner;

public class Principal {
  public static void main(String[] args) {
    ControllerUsers ctrlUsers = new ControllerUsers();
    User usuario;
    int i = 1;
        System.out.println("\nO que deseja fazer?\n1) Ler um arquivo de clientes existente\n2) Cadastrar um novo arquivo de clientes\n");
        Scanner stdin = new Scanner(System.in);
        String entrada2 = stdin.next();

        switch (entrada2) {
          case "1":
            System.out.println("\nInsira o nome do arquivo a ser Lido [ Obs - Insira com o final [ .ser ] ]");
            Scanner stdin2 = new Scanner(System.in);
            String arquivo = stdin2.next();
            System.out.println("fdpajdspiajdaoisdj");
            System.out.println();
            ctrlUsers.insertUserRead(arquivo);

            System.out.println("<====> FILA PREFERENCIAL <====>");
            System.out.println(ctrlUsers.getFilaPreferencial().printCompleteQueue() + "\n");

            System.out.println("<=======> FILA PADRÃO <=======>");
            System.out.println(ctrlUsers.getFilaNaoPreferencial().printCompleteQueue());
            break;
            // Fim case 01

          case "2":
            String initTime = "";
            System.out.println("<====> CADASTRO <====>");
            String aux = "1";

            while (aux != "-1") {
              System.out.println("\nChegou alguém na fila no tempo: [ " + i + " ] ?\n(1)Sim\n(2)Não");
              String stdinTime = stdin.next();
              int cad = Integer.parseInt(stdinTime);

              if (stdinTime.equals("1")) {
                initTime = String.valueOf(i);
                i++;
              } else {i++;}

              while (cad == 1) {
                System.out.println("\nInsira o tipo de fila que o usuário irá popular\n(1) PREFERENCIAL\n(2) PADRÃO");
                String queueType = stdin.next();

                // Digitação Incorreta, Sair do Laço
                if (Integer.parseInt(queueType)  != 1 && Integer.parseInt(queueType)  != 2){
                  System.out.println("Valor Digitado Incorretamente [ Não existe Fila com Esse valor ]");
                  break;
                }

                System.out.println("\nInsira o tempo total de ATENDIMENTO do usuário");
                String totalTime = stdin.next();
                if (Integer.parseInt(totalTime) <= 0){ // Digitação Incorreta, Sair do Laço
                  System.out.println("[ Tempo de Atendimento Inválido, Impossível ser 0 ou Menor ]");
                  break;
                }

                //Cadastro do Usuário na Fila
                usuario = new User(initTime + ";", queueType + ";", totalTime);
                ctrlUsers.insertUserFile(usuario);

                // Mostrando a Fila Atualizada já com o Usuário Inserido
                switch (usuario.getQueueType()) {
                  case "1;":
                    ctrlUsers.getFilaPreferencial().queue(usuario);
                    System.out.println("Usuário Cadastrado na [ FILA PREFERENCIAL ]");
                    System.out.println(ctrlUsers.getFilaPreferencial().printCompleteQueue());
                    System.out.println();
                    break;
                  case "2;":
                    ctrlUsers.getFilaNaoPreferencial().queue(usuario);
                    System.out.println("Usuário Cadastrado na [ FILA PADRÃO ]");
                    System.out.println(ctrlUsers.getFilaNaoPreferencial().printCompleteQueue());
                    System.out.println();
                    break;
                }
                

                System.out.println("Chegou mais alguém neste tempo? \n(1)Sim\n(2)Não");
                cad = stdin.nextInt();
                if (cad == 1){}

                else if (cad == 2){
                  break;}

                else{
                  System.out.println("[ Digitação Incorreta, Não Existe essa Opção ]");
                  break;
                }
              } // Fim While 02

              //Verificação de Encerramento de Cadastro
              System.out.println("\n[ Deseja Encerrar o Cadastro na Fila de Atendimento ]\n(-1)Encerrar Cadastro\n(1)Continuar");
              aux = stdin.next();
              if (aux.equals("-1")){ // Verificação de Encerramento
                break;}
              
            } // Fim While 01
            break;
            // Fim Case 02

          default:
            System.out.println("\nEntrada inválida");
        } // Fim Switch Principal
        stdin.close();
    }
  }
