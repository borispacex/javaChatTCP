import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCP {

	public static void main(String[] args) {
		ClienteTCP C = new ClienteTCP("localhost", 9000);
		C.iniciar();
	}

	Socket sCliente;
	Scanner entrada;
	PrintStream salida;
	String direccion;
	int puerto;
	String mensajeSolicitud = "";
	String mensajeRespuesta = "";

	public ClienteTCP(String d, int p) {
		direccion = d;
		puerto = p;
	}

	public void iniciar() {
		int sw = 0;
		try {
			do {
				// Estableciendo conexion con el servidor
				sCliente = new Socket(direccion, puerto);
				if (sw == 0) {
					sw = 1;
					System.out.println("********CONEXION INICIADA********");
					// Mostramos el ip y el puerto que nos ayudaran a la conexion
					System.out.println("Conectado a: " + sCliente.getRemoteSocketAddress());
				}
				// Obtengo una regerencia a los flujos de datos de entrada y salida
				salida = new PrintStream(sCliente.getOutputStream());
				entrada = new Scanner(sCliente.getInputStream());
				//////////////////////////////////////////////////////////////////
				// Este bloque de codigo se encarga de enviar mensajes al servidor
				Scanner lectura = new Scanner(System.in);
				// System.out.print("\nCual es tu solicitud :");
				System.out.print("CLIENTE : ");
				mensajeSolicitud = lectura.nextLine();
				salida.println("CLIENTE : " + mensajeSolicitud);
				// System.out.println("CLIENTE : "+mensajeSolicitud);
				mensajeRespuesta = entrada.nextLine();
				System.out.println(mensajeRespuesta);
			} while (true);

		} catch (Exception e) {
			e.printStackTrace();
			finalizar();
		}
	}

	public void finalizar() {
		try {
			salida.close();
			entrada.close();
			sCliente.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
