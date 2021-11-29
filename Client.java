import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;



public class Client extends Thread{

    Socket mioSocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outversoServer;
    BufferedReader inDalServer;
    

    public void comunica(){
        try
        {
            stringaUtente = tastiera.readLine();
            //la spedisco al server

            outversoServer.writeBytes(stringaUtente+'\n');
            //leggo la risposta dal server

            stringaRicevutaDalServer = inDalServer.readLine();
            //messaggio inoltrato correttamente al server

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("errore nella comunicazione con il server");
            System.exit(1);

        }
    }

    //connessione al server
    public Socket connetti(){

        try
        {
            
            tastiera = (new BufferedReader(new InputStreamReader(System.in)));
            mioSocket = new Socket("localhost", 6789);
            outversoServer = new DataOutputStream(mioSocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("errore nella connessione");
            System.exit(1);
        }
        return mioSocket;        //ritorna il socket
    }


    //MAIN DEL CLIENT
    public static void main(String args[]){
        Client cliente = new Client();
        cliente.connetti();
        cliente.comunica();
    }

}
