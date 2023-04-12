/* Discentes
 * Luan Pinheiro Azevedo
 * Carlos Gil Martins
 */

import java.io.*;
import java.util.ArrayList;

 public class UserData implements Serializable{
  //Lista que irá armazenar os usuários
  private ArrayList<User> usuario = new ArrayList<>();
  private ArrayList<User> userRead;

  public ArrayList<User> getUserRead() {
    return userRead;
  }
  public ArrayList<User> getUserList(String arquivo){
    //criação de lista de usuarios para serem lidos 
    //Instanciando objetos para realizar a serialização e escrição/leitura do arquivo
    FileInputStream fluxo = null;
    ObjectInputStream obs = null;
    try{
      userRead = new ArrayList<>();
      //localizando arquivo para leitura
      fluxo = new FileInputStream(arquivo);
      while (fluxo.available() > 0) {
        obs = new ObjectInputStream(fluxo);
        //casting e armazenando objeto
        User usuario = (User)obs.readObject();
        if (!userRead.contains(usuario)) {
          userRead.add(usuario);
        }
      }
    //mensagens para erros localizados
    }catch (ClassNotFoundException x) {
      System.out.println(x.getMessage());
    }catch (FileNotFoundException e) {  
      System.out.println("Arquivo não localizado");
    }catch (IOException x) {
      System.out.println(x.getMessage());
    }catch (NullPointerException r) {
      System.out.println(r.getMessage());
    }finally{
      if(obs != null)
      try{
        //fechamento do arquivo
        obs.close();
      }catch(IOException e){
        System.out.println(e.getMessage());
      }
    }
    return userRead;
  }
  public ArrayList<User> userInsert(User user) {
    //Instanciando objetos para realizar a serialização e escrição/leitura do arquivo
    FileOutputStream fluxo = null;
    ObjectOutputStream obs = null;
    try {
      //definindo nome do arquivo gerado e permitindo indexação ao fim do arquivo sem apaga-lo
      fluxo = new FileOutputStream("Users.ser", true);
      obs = new ObjectOutputStream(fluxo);
      //adicionando o usuário na lista de usuários 
      usuario.add(user);
      //escrevendo usuário no arquivo
      obs.writeObject(user);
      //mensagens em caso de erros localizados
    } catch (FileNotFoundException e) {
      System.out.println("Arquivo não localizado");
    } catch (IOException x) {
      System.out.println(x.getMessage());
    }catch (NullPointerException r) {
      System.out.println(r.getMessage());
    } finally{
      if(obs != null)
        try{
          //fechando o arquivo
          obs.close();
        }catch(IOException e){
          System.out.println(e.getMessage());
        }
    }
    return usuario;
 }
}