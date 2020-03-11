import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorTCP {

	public static void main(String[] args) {
		ServidorTCP S = new ServidorTCP(9000);
		S.iniciar();
	}

	ServerSocket sServidor;
	Socket sCliente;
	int puerto;
	PrintStream salida;
	Scanner entrada;
	String mensajeSolicitud = "";
	String mensajeRespuesta = "";
	Scanner leer = new Scanner(System.in);

	public ServidorTCP(int p) {
		puerto = p;
	}

	String respuesta = "";
	int res = 0;

	public void iniciar() {
		try {
			sServidor = new ServerSocket(puerto);
			System.out.println("- SERVIDOR TCP INICIADO -");
			System.out.println("- Esperando Cliente -");
			while (true) {
				sCliente = sServidor.accept();
				entrada = new Scanner(sCliente.getInputStream());
				salida = new PrintStream(sCliente.getOutputStream());

				mensajeSolicitud = entrada.nextLine();
				System.out.println(mensajeSolicitud);

				System.out.print("SERVIDOR : ");
				respuesta = leer.nextLine();
				mensajeRespuesta = "SERVIDOR : " + respuesta;
				salida.println(mensajeRespuesta);
			}
		} catch (Exception e) {
			e.printStackTrace();
			finalizar();
		} finally {
			finalizar();
		}
	}

	public void finalizar() {
		try {
			salida.close();
			entrada.close();
			sServidor.close();
			sCliente.close();
			System.out.println("Conexion Finalizada...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
