package Servidor;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorTarefas {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("---- Iniciando Servidor ----");
		
		// 12345 é a porta
		ServerSocket servidor = new ServerSocket(12345);
		
		// já vai começar automaticamente 2 threads
		// ExecutorService poolDeThread = Executors.newFixedThreadPool(2);
		ExecutorService poolDeThread = Executors.newCachedThreadPool();
		
		// aceitando requisição
		//servidor.accept();
		
		while(true) {
			Socket socket = servidor.accept();
			System.out.println("Aceitando novo cliente na porta: " + socket.getPort());
			DistribuiTarefas distribuiTarefas = new DistribuiTarefas(socket);
			// já cria a fila de espera e não preciso mais iniciailzar na mão
			poolDeThread.execute(distribuiTarefas);
			//new Thread(distribuiTarefas).start();
		}
		

	}

}
