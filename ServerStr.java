import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerStr {

    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoIlClient;
    ArrayList<String> lista = new ArrayList(); //lista


    public Socket attendi(){

        try
        {
            System.out.println("Server in esecuzione");
            //creo un server sulla porta 6789
            server = new ServerSocket(6789);
            //rimane in attesa del client
            client  = server.accept();
            //chiudo il server per inibire altri client
            server.close();
            //associo 2 oggetti al socket del client per effettuare scrittura e lettura
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoIlClient = new DataOutputStream(client.getOutputStream());
            server.close();
        }   
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("errore nell'istanza del server");
            System.exit(1);
        }
        return client;
    }

    
    public void comunica(){
        try
        {
            //rimango in attesa della riga trasmessa al client
            System.out.println("Inserisci la nota da memorizzare o digita LISTA per visualizzare le note salvate");
            stringaRicevuta = inDalClient.readLine();
            lista.add(stringaRicevuta);
            //invio
            outVersoIlClient.writeBytes(stringaRicevuta);



        }   
        catch (Exception e)
        {
            
        }
    }
    //MAIN DEL server
    public static void main(String args[]){
        ServerStr servente = new ServerStr();
        servente.attendi();
        servente.comunica();
    }

}
