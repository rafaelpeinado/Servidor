package Servidor;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorTarefas {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("---- Iniciando Servidor ----");
		
		// 12345 � a porta
		ServerSocket servidor = new ServerSocket(12345);
		
		// j� vai come�ar automaticamente 2 threads
		// ExecutorService poolDeThread = Executors.newFixedThreadPool(2);
		ExecutorService poolDeThread = Executors.newCachedThreadPool();
		
		// aceitando requisi��o
		//servidor.accept();
		
		while(true) {
			Socket socket = servidor.accept();
			System.out.println("Aceitando novo cliente na porta: " + socket.getPort());
			DistribuiTarefas distribuiTarefas = new DistribuiTarefas(socket);
			// j� cria a fila de espera e n�o preciso mais iniciailzar na m�o
			poolDeThread.execute(distribuiTarefas);
			//new Thread(distribuiTarefas).start();
		}
		

	}

}
