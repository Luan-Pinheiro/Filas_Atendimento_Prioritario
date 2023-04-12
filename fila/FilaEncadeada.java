/* Discentes
 * Luan Pinheiro Azevedo
 * Carlos Gil Martins
 */

public class FilaEncadeada<T> implements Fila {
  private ListaEncadeada<T> lista = new ListaEncadeada<>();
  private int count = 0;

  @Override
  public void clear() {
    lista.esvaziaLista();
    count = 0;
  }

  public boolean estaVazia() {
    return lista.estaVazia();
  }

  @Override
  public T getFirst(){
    return lista.getPrimeiroElemento();
  }

  @Override
  public T getLast(){
    return lista.getUltimoElemento();
  }

  @Override
  public void queue(Object value){
    lista.adicionaFim((T)value);
    count++;
  }

  @Override
  public void dequeue(){
    lista.remover((T)lista.getPrimeiroElemento());
    count--;
  }

  @Override
  public void print(){
    lista.print();
  }
  
  @Override
  public T getIndex(int i){
    return lista.getIndex(i);
  }

  @Override
  public int size(){
    return count;
  }
  public String printCompleteQueue(){
    String usuario = "";
    for(int i = 0; i < this.size(); i++){
      if(i == this.size()-1){
        usuario += toPrint((User)this.getIndex(i));
      }
      else
        usuario += toPrint((User)this.getIndex(i)) + "\n";
    }
    return usuario;
  }

  public String toString(User user){
    return user.getClienteId() + user.getInitTime() + ";" + user.getQueueType() + ";" + user.getServiceTime();
  }

  @Override
  public String toPrint(User user){
    return lista.toPrint(user);
  }

}
