/* Discentes
 * Luan Pinheiro Azevedo
 * Carlos Gil Martins
 */

public class ListaEncadeada<T> {
  private Elemento<T> primeiroElemento;
  private Elemento<T> ultimoElemento;
  private int tamanho;

  public ListaEncadeada(){
    this.tamanho = 0;
  }
  public int getTamanho() {
    return tamanho;
  }

  public T getPrimeiroElemento() {
    return  primeiroElemento.getValor();
  }
  
  public T getUltimoElemento() {
    return  ultimoElemento.getValor();
  }
  
  public void esvaziaLista(){
    this.primeiroElemento = null;
    this.ultimoElemento = null;
  }

  public void adicionaInicio(T valor) {
    Elemento<T> termo = new Elemento<T>(valor);
    if(this.primeiroElemento == null && this.ultimoElemento==null){
      primeiroElemento = termo;
      ultimoElemento = termo;
      tamanho++;
    }else{
      termo.setProximo(primeiroElemento);
      primeiroElemento = termo;
      tamanho++;     
    }
  }

  public void adicionaFim(T valor) {
    Elemento<T> termo = new Elemento<T>(valor);
    if(this.primeiroElemento == null && this.ultimoElemento==null){
      primeiroElemento = termo;
      ultimoElemento = termo;
      tamanho++;
    }else{
      ultimoElemento.setProximo(termo);
      ultimoElemento = termo;
      tamanho++;     
    }
  }
  
  public void adiciona(T valor){
    Elemento<T> novoElemento = new Elemento<>(valor);
    if(this.primeiroElemento == null && this.ultimoElemento == null){
      primeiroElemento =  novoElemento;
      ultimoElemento =  novoElemento;
      tamanho++;
    }else{
      ultimoElemento.setProximo(novoElemento);
      ultimoElemento = novoElemento;
      tamanho++;
    }
  }

  public T getIndex(int posicao){
    Elemento<T> atual = this.primeiroElemento;
    for(int i = 0; i < posicao; i++){
      if(atual.getProximo() != null){
        atual = atual.getProximo();
      }
    }
    return atual.getValor();
  }

  public void remover(T termo){
    Elemento<T> atual = this.primeiroElemento;
    Elemento<T> anterior = new Elemento<>(null);
    for(int i = 0; i < this.getTamanho(); i++){
      if((atual.getValor()).equals(termo)){
        if(tamanho == 1){
          primeiroElemento = null;
          ultimoElemento = null;
        }else if(atual == this.primeiroElemento){
          primeiroElemento = atual.getProximo();
          atual = null;
        }else if(atual == this.ultimoElemento){
          this.ultimoElemento = anterior;
          atual = null;
          ultimoElemento.setProximo(null);
        }else{
          anterior.setProximo(atual.getProximo());
          atual = null;
        }
        tamanho--;
        break;
      }
      anterior = atual;
      atual = atual.getProximo();
    }
  }

  public String toPrint(User user){
    return user.getUsuario();
  }

  public void print(){
    Elemento<T> pointer = this.primeiroElemento;
    System.out.print(" [ ");
    while (pointer != null) {
      System.out.print(pointer.getValor() + " ");
      pointer = pointer.getProximo();
    }
    System.out.println("]");
  }
}
