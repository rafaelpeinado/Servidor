package Servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class DistribuiTarefas implements Runnable {

	private Socket socket;
	
	
	
	public DistribuiTarefas(Socket socket) {
		this.socket = socket;
	}



	@Override
	public void run() {
		try {
			System.out.println("Distribuindo as tarefas para o cliente: " + socket);
			
			Scanner entradaCliente = new Scanner(socket.getInputStream());
			
			PrintStream saidaCliente = new PrintStream(socket.getOutputStream());
			
			while(entradaCliente.hasNextLine()) {
				String comando = entradaCliente.nextLine();
				System.out.println("Comando: " + comando);
				
				switch (comando) {
				case "ola":
					saidaCliente.println(" mundo!");
					break;
				default:
					saidaCliente.println("Comando não encontrado!");
						
				}
			}
			saidaCliente.close();
			entradaCliente.close();
			
			//Thread.sleep(20000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	
}
