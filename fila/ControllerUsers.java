/* Discentes
 * Luan Pinheiro Azevedo
 * Carlos Gil Martins
 */
import java.util.ArrayList;

public class ControllerUsers{
  private User usuario;
  private FilaEncadeada<User> filaPreferencial = new FilaEncadeada<>();
  private FilaEncadeada<User> filaNaoPreferencial = new FilaEncadeada<>();

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

  public void insertUserFile(User user){
    //verificação para caso os dados de usuário estajm vazios
    if(!(user.getInitTime().isEmpty() && user.getQueueType().isEmpty() && user.getServiceTime().isEmpty()) ){
      //em caso negativo o usuário é escrito no arquivo atraves do insertUser() 
      userInsert(user);
      //validação de dados do usuário
    }else{
      //em caso da validação ser negativa por usuário/senha incorretos ou campos não preenchidos, uma mensagem de erro é exibida
      System.out.println("Erro no cadastro!");
    }
  }
  public ArrayList<User> userInsert(User usuario) {
    return uData.userInsert(usuario);
  }
  public ArrayList<User> getUserList(String arquivo){
    return uData.getUserList(arquivo);
  }

  public void insertUserRead(String arquivo){
    for(int i = 0; i < this.getUserList(arquivo).size(); i++){
      if(this.getUserList(arquivo).get(i).getQueueType().equals("1;")){
        initTime = this.getUserList(arquivo).get(i).getInitTime();
        queueType = this.getUserList(arquivo).get(i).getQueueType();
        totalTime = this.getUserList(arquivo).get(i).getServiceTime();
        usuario = new User(initTime, queueType, totalTime);
        filaPreferencial.queue(usuario);
      }else{
        initTime = this.getUserList(arquivo).get(i).getInitTime();
        queueType = this.getUserList(arquivo).get(i).getQueueType();
        totalTime = this.getUserList(arquivo).get(i).getServiceTime();
        usuario = new User(initTime, queueType, totalTime);
        filaNaoPreferencial.queue(usuario);
        
      }
    }
  }

  public String atendeOrdem(){
    int sizeFP = filaPreferencial.size();
    int sizeFNP = filaNaoPreferencial.size();
    int tempoAtendimento = 0;
    String aux = "";
    try{
      if(sizeFNP <= sizeFP){
        if(sizeFNP >0){
          for(int i=0; i < sizeFNP; i++){  
            if( sizeFP > 0){
              for(int j=0; j < sizeFP; j++){
                if(Integer.parseInt(this.filaNaoPreferencial.getIndex(i).getInitTime().substring(0, this.filaNaoPreferencial.getIndex(i).getInitTime().length() - 1)) <= Integer.parseInt(this.filaPreferencial.getIndex(j).getInitTime().substring(0, this.filaPreferencial.getIndex(j).getInitTime().length() - 1))){            
                  tempoAtendimento += atendementoPreferencial(j);
                  aux += "Idoso da posicao " + (j+1) + " da Fila preferencial foi atendido\n |-> Tempo de atendimento: " + tempoAtendimento + "\n";
                }else{
                  tempoAtendimento += atendimentoPadrao(i);
                  aux += "Adulto da posicao " + (i+1) + " da Fila padrão foi atendido\n |-> Tempo de atendimento: " + tempoAtendimento + "\n";
                }
               }
            }else{
              tempoAtendimento += atendimentoPadrao(i);
              aux += "Adulto da posicao " + (i+1) + " da Fila padrão foi atendido\n |-> Tempo de atendimento: " + tempoAtendimento + "\n";
            }
          }
        }
      }
      else{
        if(sizeFP >0){
          for(int i=0; i < sizeFP; i++){  
            if( sizeFNP > 0){
              for(int j=0; j < sizeFNP; j++){
                if(Integer.parseInt(this.filaPreferencial.getIndex(i).getInitTime().substring(0, this.filaPreferencial.getIndex(i).getInitTime().length() - 1)) <= Integer.parseInt(this.filaNaoPreferencial.getIndex(j).getInitTime().substring(0, this.filaNaoPreferencial.getIndex(j).getInitTime().length() - 1))){            
                  tempoAtendimento += atendementoPreferencial(i);
                  aux += "Idoso da posicao " + (j+1) + " da Fila preferencial foi atendido\n |-> Tempo de atendimento: " + tempoAtendimento + "\n";
                }else{
                  tempoAtendimento += atendimentoPadrao(j);
                  aux += "Adulto da posicao " + (i+1) + " da Fila padrão foi atendido\n |-> Tempo de atendimento: " + tempoAtendimento + "\n";
                }
               }
            }else{
              tempoAtendimento += atendimentoPadrao(i);
              aux += "Adulto da posicao " + (i+1) + " da Fila padrão foi atendido\n |-> Tempo de atendimento: " + tempoAtendimento + "\n";
            }
          }
        }
      } 
    }catch(NullPointerException e){
      e.printStackTrace();
    }
    return aux;
  }


  public int atendimentoPadrao(int i){
    int tempoAtendimento = 0;
    tempoAtendimento += Integer.parseInt(this.filaNaoPreferencial.getIndex(i).getInitTime().substring(0, this.filaNaoPreferencial.getIndex(i).getInitTime().length() - 1)) + Integer.parseInt(this.filaNaoPreferencial.getIndex(i).getServiceTime());
    this.filaNaoPreferencial.dequeue();
    return tempoAtendimento;
  }

  public int atendementoPreferencial(int i){
    int tempoAtendimento = 0;
    tempoAtendimento +=  Integer.parseInt(this.filaPreferencial.getIndex(i).getInitTime().substring(0, this.filaPreferencial.getIndex(i).getInitTime().length() - 1)) + Integer.parseInt(this.filaPreferencial.getIndex(i).getServiceTime());
    this.filaPreferencial.dequeue();
    
    return tempoAtendimento;
  }
}