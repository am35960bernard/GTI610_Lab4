package serveur;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.ServerSocket;

public class Server {

	public static void main(String[] zero){

		ServerSocket socket;
		
		try {
		socket = new ServerSocket(2222);
		Thread t = new Thread(new Accepter_clients(socket));
		t.start();
		System.out.println("Bring it on 2222!");
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Accepter_clients implements Runnable {
		
	private int MAX = 256;
	private ServerSocket socketserver;
	private Socket socket;
	private int nbrclient = 1;
	private byte[] buff = new byte[MAX];
	
	private String messageRetour = "There will be no peace" + "\n";
	 
	public Accepter_clients(ServerSocket s){
		socketserver = s;
	}
		
		public void run() {
	        
			try {
	        	while(true){
	        		//accepter la demande de connexion du client 
	        		socket = socketserver.accept(); // Un client se connecte on l'accepte
	                System.out.println("Le client numéro "+nbrclient+ " est connecté !");
	                nbrclient++;
	                
	                //recevoir le message du client
	                InputStream is = socket.getInputStream();
	                InputStreamReader isr = new InputStreamReader(is);
	                BufferedReader br = new BufferedReader(isr);
	                String message = br.readLine();
	                System.out.println("Message reçu du client: "+message);
	        		
	           
	                //envoyer le message de retour au client.
	                OutputStream os = socket.getOutputStream();
	                OutputStreamWriter osw = new OutputStreamWriter(os);
	                BufferedWriter bw = new BufferedWriter(osw);
	                bw.write(messageRetour);
	                System.out.println("Message retourner au client: "+messageRetour);
	                bw.flush();
	                
	                socket.close();
	        	}
	        
	        } catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
