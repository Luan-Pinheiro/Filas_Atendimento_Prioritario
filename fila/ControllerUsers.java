
/* Discentes
 * Luan Pinheiro Azevedo
 * Carlos Gil Martins
 */
import java.util.ArrayList;

public class ControllerUsers {
  int numAtendimento =0;
  private User usuario;
  private FilaEncadeada<User> filaPreferencial = new FilaEncadeada<>();
  private FilaEncadeada<User> filaNaoPreferencial = new FilaEncadeada<>();
  private int serviceTime = 1;

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
    while(filaPreferencial.size() != 0 && filaNaoPreferencial.size() != 0){
      if(filaPreferencial.getFirst().getInitTime().equals(filaNaoPreferencial.getFirst().getInitTime()))
        atendidos += atendimentoPreferencial() + "\n";
      else{
        int auxPref = Integer.parseInt(filaPreferencial.getFirst().getInitTime().replaceAll(";",""));
        int auxPrad = Integer.parseInt(filaNaoPreferencial.getFirst().getInitTime().replaceAll(";",""));

        if(auxPref < auxPrad){
          atendidos += atendimentoPreferencial() + "\n";
        }else
          atendidos += atendimentoPadrao() + "\n";
      } 
    }
    while(filaPreferencial.size() != 0 || filaNaoPreferencial.size() != 0){
      if(filaNaoPreferencial.size()!=0)
        atendidos += atendimentoPadrao() + "\n";
      else 
        atendidos += atendimentoPreferencial() + "\n";
    } 
    return "\n" + atendidos;
  }

  public String atendimentoPadrao(){
    if(filaNaoPreferencial.size() == 0)
      return"";

    int tempoChegada = Integer.parseInt(this.filaNaoPreferencial.getFirst().getInitTime().replaceAll(";",""));
    serviceTime += Integer.parseInt(this.filaNaoPreferencial.getFirst().getServiceTime());
    int tempoReal = serviceTime-tempoChegada;
    String atendimento = "Atendimento [ " + (numAtendimento+1) + " ] <===> Cliente [" + this.filaNaoPreferencial.getFirst().getClienteId().replaceAll(";","")  + "] atendido na Fila Padrão com o Tempo Total de [ " + tempoReal + " ]";
    try {
      uData.writeFinalArchive(this.filaNaoPreferencial.getFirst().getClienteId(), String.valueOf(tempoReal)+"\n");
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.filaNaoPreferencial.dequeue();
    numAtendimento++;
    return atendimento;
  }

  public String atendimentoPreferencial(){
    if(filaPreferencial.size() == 0)
      return"";
    int tempoChegada = Integer.parseInt(this.filaPreferencial.getFirst().getInitTime().replaceAll(";",""));
    serviceTime += Integer.parseInt(this.filaPreferencial.getFirst().getServiceTime());
    int tempoReal = serviceTime-tempoChegada;
    String atendimento = "Atendimento [ " + (numAtendimento+1) + " ] <===> Cliente [" + this.filaPreferencial.getFirst().getClienteId().replaceAll(";","") + "] atendido na Fila Preferencial com o Tempo Total de [ " + tempoReal + " ]";
    try {
      uData.writeFinalArchive(this.filaPreferencial.getFirst().getClienteId(), String.valueOf(tempoReal)+"\n");
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.filaPreferencial.dequeue();
    numAtendimento++;
    return atendimento;
  }
}
