package taller1;
//Millaray Zepeda - 22.063.994-0 - ICCI
//Silvana Campillay -21.862.853-2 - ICI
import java.io.BufferedWinter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Main {
	//Un par de variables
	  //contadores
	static int contador1 = 0;
	static int contador2 = 0;
	  //listas por paralelo
	static String[] grupoC1 = new String[100];
	static String[] rutGrupoC1 = new String[100];
	static int cantGrupoC1 = 0;
	static String[] grupoC2 = new String[100];
	static String[] rutGrupoC2 = new String[100];
	static int cantGrupoC2 = 0;

	static String[] registroRechazados = new String[200];
	static int cantRechazados = 0;

	static int versionC1 = 0;
	static int versionC2 = 0;
	static int versionRech = 0;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		boolean entrar = true;
		
		String[] alumnosC1 = new String[100];
		String[] rutC1 = new String[100];
		String[] alumnosC2 = new String[100];
		String[] rutC2 = new String[100];
		String[] solicitudes = new String[100];
		//El while
		while (entrar) {
			//el menu
			System.out.println("===== Sistema de Control del Grupo POO =====");
			System.out.println("1) Cargar archivos (Alumnos y Solicitudes)");
			System.out.println("2) Procesar solicitudes (Filtrado automatico)");
			System.out.println("3) Inscripcion manual al grupo");
			System.out.println("4) Administracion del curso");
			System.out.println("5) Generar reportes");
			System.out.println("6) Analisis estadistico");
			System.out.println("7) Salir");
			System.out.println("");
			System.out.print("Ingrese opcion: ");
			String opcion = cs.nextLine();
			
			switch (opcion) {
			case "7":
				entrar = false;
				break;
			default:
				System.out.println("Opciones en desarrollo...");
				break;
				
				
			}
		}
		sc.close();

	}

}
