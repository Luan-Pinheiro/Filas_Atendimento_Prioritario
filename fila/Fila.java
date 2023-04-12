/* Discentes
 * Luan Pinheiro Azevedo
 * Carlos Gil Martins
 */

public interface Fila<T> {
  void clear();
  Object getFirst();
  String toPrint(User user);
  Object getLast();
  boolean estaVazia();
  void queue(T value);
  void dequeue();
  void print();
  T getIndex(int i);
  int size();
}
