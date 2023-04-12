/* Discentes
* Luan Pinheiro Azevedo
* Carlos Gil Martins
*/
import java.io.Serializable;

public class User implements Serializable{
  private String clienteId;
  private static int contador = 1;
  private String initTime;
  private String queueType;
  private String serviceTime;

  public void setInitTime(String tempoChegada) {
    this.initTime = tempoChegada;
  }

  public void setQueueType(String tipoFila) {
    this.queueType = tipoFila;
  }

  public void setServiceTime(String tempoTotalMinutos) {
    this.serviceTime = tempoTotalMinutos;
  }

  public User(String tempC, String tFila, String tempTot){
    this.clienteId = String.valueOf(contador++) + ";";
    this.initTime = tempC;
    this.queueType = tFila;
    this.serviceTime = tempTot;
  }
  public String getClienteId() {
    return clienteId;
  }
  public String getInitTime() {
    return initTime;
  }
  public String getQueueType() {
    return queueType;
  }
  public String getServiceTime() {
    return serviceTime;
  }

  public String getUsuario(){
    return getClienteId() + getInitTime() + getQueueType() + getServiceTime();
  }

}
