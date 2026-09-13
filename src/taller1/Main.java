//Millaray Zepeda - 22.063.994-0 - ICCI
//Silvana Campillay -21.862.853-2 - ICI

package taller1;

import java.io.File;
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
		while(entrar) {
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
			String opcion = sc.nextLine();
			
			switch(opcion) {
			case "1":
				try {
					System.out.println();
					leerAlumnos(alumnosC1, rutC1, rutC2, alumnosC2);
					leerSolicitudes(solicitudes);
				} catch(IOException e) {
					System.out.println("no hay archivos txt");
				}
				break;
			case "2":
				if (contador1 == 0 && contador2 == 0) {
					System.out.println("[AVISO] No hay alumnos cargados en el sistema. Cargue los archivos primero (Opcion 1).");
				} else if(solicitudes[0] == null) {
					System.out.println("Sin solicitudes");
				} else {
					int aceptados = 0;
					int rechazados = 0;
			
					for(int p = 0; p < solicitudes.length; p++) {
						if(solicitudes[p] != null) {
							boolean encontrado = false;
							
							for(int k = 0; k < alumnosC1.length; k++) {
								if(solicitudes[p].equals(alumnosC1[k])) {
									String[] partesAceptadosC1 = solicitudes[p].split("-");
									System.out.println("[OK]       " + partesAceptadosC1[0] + " " + partesAceptadosC1[1] + " en C1");
									
									boolean yaEnGrupo = false;
									for(int g = 0; g < cantGrupoC1; g++) {
										if(grupoC1[g] != null && grupoC1[g].equals(alumnosC1[k])) {
											yaEnGrupo = true;
											break;
										}
									}
									if(!yaEnGrupo && cantGrupoC1 < grupoC1.length) {
										grupoC1[cantGrupoC1] = alumnosC1[k];
										rutGrupoC1[cantGrupoC1] = rutC1[k];
										cantGrupoC1++;
									}
									
									aceptados++;
									encontrado = true;
									break;
								}
							}
							if(encontrado == false) {
								for(int k = 0; k < alumnosC2.length; k++) {
									if(solicitudes[p].equals(alumnosC2[k])) {
										String[] partesAceptadosC2 = solicitudes[p].split("-");
										System.out.println("[OK]       " + partesAceptadosC2[0] + " " + partesAceptadosC2[1] + " en C2");
										
										boolean yaEnGrupo = false;
										for(int g = 0; g < cantGrupoC2; g++) {
											if(grupoC2[g] != null && grupoC2[g].equals(alumnosC2[k])) {
												yaEnGrupo = true;
												break;
											}
										}
										if(!yaEnGrupo && cantGrupoC2 < grupoC2.length) {
											grupoC2[cantGrupoC2] = alumnosC2[k];
											rutGrupoC2[cantGrupoC2] = rutC2[k];
											cantGrupoC2++;
										}
										
										aceptados++;
										encontrado = true;
										break;
									}
								}
							}
							
							if(encontrado == false) {
								System.out.println("[RECHAZADOS] " + solicitudes[p] + " no encontrado");
								rechazados++;
								if(cantRechazados < registroRechazados.length) {
									registroRechazados[cantRechazados] = solicitudes[p].replace("-", " ") + " - No pertenece a ningun paralelo del curso";
									cantRechazados++;
								}
							}
						}
					}
					System.out.println("Total procesados: " + aceptados);
					System.out.println("Total Rechazados: " + rechazados);
				}
				break;
			case "7":
				entrar = false;
				break;
			default:
				System.out.println("Opcion en desarrollo...");
				break;
			}
		}
		sc.close();
	}

	private static void leerAlumnos(String[] alumnosC1, String[] rutC1, String[] rutC2, String[] alumnosC2) throws java.io.IOException {
		File arch1 = new File("Alumnos.txt");
		Scanner lineaAlumnos = new Scanner(arch1);
		contador1 = 0;
		contador2 = 0;
		
		while(lineaAlumnos.hasNextLine()) {
			String alumno = lineaAlumnos.nextLine();
			String[] partes1 = alumno.split(";");
			if(partes1.length >= 4) {
				String nombre = partes1[0];
				String apellido = partes1[1];
				String rut = partes1[2];
				String paralelo = partes1[3];
				
				if(paralelo.equals("C1")) {
					alumnosC1[contador1] = nombre + "-" + apellido;
					rutC1[contador1] = rut;
					contador1++;
				} else if(paralelo.equals("C2")) {
					alumnosC2[contador2] = nombre + "-" + apellido;
					rutC2[contador2] = rut;
					contador2++; 
				}
			}
		}
		System.out.println("- "+(contador1+contador2) + " alumnos en lista");
		lineaAlumnos.close();
	}

	private static void leerSolicitudes(String[] solicitudes) throws java.io.IOException {
		File arch2 = new File("Solicitudes.txt");
		Scanner lineaSolicitudes = new Scanner(arch2);
		String[] unicos = new String[100]; 
		int contador = 0;
		
		while(lineaSolicitudes.hasNextLine()) {
			String solicitud = lineaSolicitudes.nextLine();
			if(!solicitud.isEmpty()) {
				boolean yaExiste = false;
				for (int i = 0; i < contador; i++) {
					if(solicitud.equals(unicos[i])) {
						yaExiste = true;
						break;
					}
				}
				if(!yaExiste && contador < unicos.length) {
					unicos[contador] = solicitud;
					contador++;
				}
			}
		}
		lineaSolicitudes.close();
		
		for (int j = 0; j < contador; j++) {
			solicitudes[j] = unicos[j];
		}
		System.out.println("- "+contador+" solicitudes de ingreso");
	}
}