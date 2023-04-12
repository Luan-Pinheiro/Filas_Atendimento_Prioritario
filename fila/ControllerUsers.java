
/* Discentes
 * Luan Pinheiro Azevedo
 * Carlos Gil Martins
 */
import java.util.ArrayList;

public class ControllerUsers {
  private User usuario;
  private FilaEncadeada<User> filaPreferencial = new FilaEncadeada<>();
  private FilaEncadeada<User> filaNaoPreferencial = new FilaEncadeada<>();
  private int tempoTotal = 0;

  public FilaEncadeada<User> getFilaPreferencial() {
    return filaPreferencial;
  }

  public FilaEncadeada<User> getFilaNaoPreferencial() {
    return filaNaoPreferencial;
  }

  private UserData uData = new UserData();
  private String initTime;
  private String queueType;
  private String totalTime;

  public void insertUserFile(User user) {
    // verificação para caso os dados de usuário estajm vazios
    if (!(user.getInitTime().isEmpty() && user.getQueueType().isEmpty() && user.getServiceTime().isEmpty())) {
      // em caso negativo o usuário é escrito no arquivo atraves do insertUser()
      userInsert(user);
      // validação de dados do usuário
    } else {
      // em caso da validação ser negativa por usuário/senha incorretos ou campos não
      // preenchidos, uma mensagem de erro é exibida
      System.out.println("Erro no cadastro!");
    }
  }

  public ArrayList<User> userInsert(User usuario) {
    return uData.userInsert(usuario);
  }

  public ArrayList<User> getUserList(String arquivo) {
    return uData.getUserList(arquivo);
  }

  public void insertUserRead(String arquivo) {
    for (int i = 0; i < this.getUserList(arquivo).size(); i++) {
      if (this.getUserList(arquivo).get(i).getQueueType().equals("1;")) {
        initTime = this.getUserList(arquivo).get(i).getInitTime();
        queueType = this.getUserList(arquivo).get(i).getQueueType();
        totalTime = this.getUserList(arquivo).get(i).getServiceTime();
        usuario = new User(initTime, queueType, totalTime);
        filaPreferencial.queue(usuario);
      } else {
        initTime = this.getUserList(arquivo).get(i).getInitTime();
        queueType = this.getUserList(arquivo).get(i).getQueueType();
        totalTime = this.getUserList(arquivo).get(i).getServiceTime();
        usuario = new User(initTime, queueType, totalTime);
        filaNaoPreferencial.queue(usuario);

      }
    }
  }

  public String atendeOrdem() {
    String atendidos = "";
  
    while (filaPreferencial.size() !=0 && filaNaoPreferencial.size() != 0) {
      String aux1 = "";
      String aux2 = "";

      if (filaNaoPreferencial.estaVazia() == false) {
        aux1 = filaNaoPreferencial.getFirst().getInitTime();
        aux1 = aux1.replaceAll(";", "");
      }

      if (filaPreferencial.estaVazia() == false) {
        aux2 = filaPreferencial.getFirst().getInitTime();
        aux2 = aux2.replaceAll(";", "");
      }

      if (aux1.equals(null)) {
        atendidos += atendimentoPadrao() + "\n";
      }
      if (aux2.equals(null)) {
        atendidos += atendimentoPreferencial() + "\n";
      }

      if (aux1 != null && aux2 != null) {
        if (aux1.equals(aux2)) {
          atendidos += atendimentoPreferencial() + "\n";
        } else {
          atendidos += atendimentoPadrao() + "\n";
        }
      }
    }
    return atendidos;
  }

  public String atendimentoPadrao() {
    tempoTotal += Integer.parseInt(this.filaNaoPreferencial.getFirst().getServiceTime());
    String atendimento = "Cliente [" + this.filaNaoPreferencial.getFirst().getClienteId()+ "] da Fila Padrão foi atendido com o tempo de " + tempoTotal;
    this.filaNaoPreferencial.dequeue();

    return atendimento;
  }

  public String atendimentoPreferencial() {
    tempoTotal += Integer.parseInt(this.filaPreferencial.getFirst().getServiceTime());
    String atendimento = "Cliente [" + this.filaPreferencial.getFirst().getClienteId()+ "] da Fila Preferencial foi atendido com o tempo de " + tempoTotal;
    this.filaPreferencial.dequeue();

    return atendimento;
  }
}
