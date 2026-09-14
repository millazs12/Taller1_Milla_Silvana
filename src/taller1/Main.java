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
			case "3": 
				if (contador1 == 0 && contador2 == 0) {
					System.out.println("[AVISO] No hay alumnos cargados en el sistema. Cargue los archivos primero (Opcion 1).");
					break;
				}
				
				System.out.println("Como desea inscribir a la persona?");
				System.out.println("1) Por nombre completo");
				System.out.println("2) Por RUT");
				System.out.print("Ingrese opcion: ");
				String tipoInscripcion = sc.nextLine();
				
				if(tipoInscripcion.equals("1")) {
					System.out.print("Ingrese nombre completo: ");
					String nombreBuscado = sc.nextLine().replace(" ", "-");
					boolean encontrado = false;
					
					for(int k = 0; k < alumnosC1.length; k++) {
						if(alumnosC1[k] != null && alumnosC1[k].equalsIgnoreCase(nombreBuscado)) {
							System.out.println("El alumno " + alumnosC1[k].replace("-", " ") + " pertenece al paralelo C1.");
							
							boolean yaEnGrupo = false;
							for(int g = 0; g < cantGrupoC1; g++) {
								if(grupoC1[g] != null && grupoC1[g].equalsIgnoreCase(alumnosC1[k])) {
									yaEnGrupo = true;
									break;
								}
							}
							if(!yaEnGrupo && cantGrupoC1 < grupoC1.length) {
								grupoC1[cantGrupoC1] = alumnosC1[k];
								rutGrupoC1[cantGrupoC1] = rutC1[k];
								cantGrupoC1++;
							}
							encontrado = true;
							break;
						}
					}
					
					if(!encontrado) {
						for(int k = 0; k < alumnosC2.length; k++) {
							if(alumnosC2[k] != null && alumnosC2[k].equalsIgnoreCase(nombreBuscado)) {
								System.out.println("El alumno " + alumnosC2[k].replace("-", " ") + " pertenece al paralelo C2.");
								
								boolean yaEnGrupo = false;
								for(int g = 0; g < cantGrupoC2; g++) {
									if(grupoC2[g] != null && grupoC2[g].equalsIgnoreCase(alumnosC2[k])) {
										yaEnGrupo = true;
										break;
									}
								}
								if(!yaEnGrupo && cantGrupoC2 < grupoC2.length) {
									grupoC2[cantGrupoC2] = alumnosC2[k];
									rutGrupoC2[cantGrupoC2] = rutC2[k];
									cantGrupoC2++;
								}
								encontrado = true;
								break;
							}
						}
					}
					
					if(!encontrado) {
						System.out.println("El nombre no pertenece al curso.");
						System.out.println("Se registrara su nombre en los rechazados.");
						if(cantRechazados < registroRechazados.length) {
							registroRechazados[cantRechazados] = nombreBuscado.replace("-", " ") + " - No pertenece a ningun paralelo del curso";
							cantRechazados++;
						}
					}
					
				} else if(tipoInscripcion.equals("2")) {
					System.out.print("Ingrese RUT: ");
					String rutBuscado = sc.nextLine();
					boolean encontrado = false;
					
					for(int k = 0; k < rutC1.length; k++) {
						if(rutC1[k] != null && rutC1[k].equals(rutBuscado)) {
							String nombreEncontrado = alumnosC1[k] != null ? alumnosC1[k].replace("-", " ") : "";
							System.out.println("El RUT " + rutBuscado + " (" + nombreEncontrado + ") pertenece al paralelo C1.");
							
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
							encontrado = true;
							break;
						}
					}
					
					if(!encontrado) {
						for(int k = 0; k < rutC2.length; k++) {
							if(rutC2[k] != null && rutC2[k].equals(rutBuscado)) {
								String nombreEncontrado = alumnosC2[k] != null ? alumnosC2[k].replace("-", " ") : "";
								System.out.println("El RUT " + rutBuscado + " (" + nombreEncontrado + ") pertenece al paralelo C2.");
								
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
								encontrado = true;
								break;
							}
						}
					}
					
					if(!encontrado) {
						System.out.println("El RUT " + rutBuscado + " no pertenece al curso.");
						System.out.println("No tenemos su nombre, por lo que se registrara solo el RUT en los rechazados.");
						if(cantRechazados < registroRechazados.length) {
							registroRechazados[cantRechazados] = "Sin nombre registrado, RUT: " + rutBuscado;
							cantRechazados++;
						}
					}
				} else {
					System.out.println("Opcion invalida.");
				}
				break;
			case "4":
				if (contador1 == 0 && contador2 == 0) {
					System.out.println("[AVISO] No hay alumnos cargados en el sistema. Cargue los archivos primero (Opcion 1).");
					break;
				}

				System.out.println("--- Administracion del curso ---");
				System.out.println("1) Cambiar el paralelo de un alumno (C1 <-> C2)");
				System.out.println("2) Eliminar un alumno del curso");
				System.out.println("3) Inscribir un alumno nuevo al curso");
				System.out.print("Ingrese opcion: ");
				String opcionAdmin = sc.nextLine();

				if (opcionAdmin.equals("1")) {
					System.out.print("Ingrese el RUT del alumno a cambiar de paralelo: ");
					String rutCambio = sc.nextLine();
					boolean cambiado = false;

					for (int i = 0; i < contador1; i++) {
						if (rutC1[i] != null && rutC1[i].equals(rutCambio)) {
							String alumnoMovido = alumnosC1[i];
							
							for (int j = i; j < contador1 - 1; j++) {
								alumnosC1[j] = alumnosC1[j + 1];
								rutC1[j] = rutC1[j + 1];
							}
							alumnosC1[contador1 - 1] = null;
							rutC1[contador1 - 1] = null;
							contador1--;

							alumnosC2[contador2] = alumnoMovido;
							rutC2[contador2] = rutCambio;
							contador2++;

							for(int g = 0; g < cantGrupoC1; g++) {
								if(grupoC1[g] != null && grupoC1[g].equals(alumnoMovido)) {
									for(int gj = g; gj < cantGrupoC1 - 1; gj++) {
										grupoC1[gj] = grupoC1[gj + 1];
										rutGrupoC1[gj] = rutGrupoC1[gj + 1];
									}
									grupoC1[cantGrupoC1 - 1] = null;
									rutGrupoC1[cantGrupoC1 - 1] = null;
									cantGrupoC1--;

									if(cantGrupoC2 < grupoC2.length) {
										grupoC2[cantGrupoC2] = alumnoMovido;
										rutGrupoC2[cantGrupoC2] = rutCambio;
										cantGrupoC2++;
									}
									break;
								}
							}

							actualizarArchivoAlumnos(alumnosC1, rutC1, alumnosC2, rutC2);
							System.out.println("[EXITO] Alumno cambiado de C1 a C2 exitosamente.");
							cambiado = true;
							break;
						}
					}

					if (!cambiado) {
						for (int i = 0; i < contador2; i++) {
							if (rutC2[i] != null && rutC2[i].equals(rutCambio)) {
								String alumnoMovido = alumnosC2[i];

								for (int j = i; j < contador2 - 1; j++) {
									alumnosC2[j] = alumnosC2[j + 1];
									rutC2[j] = rutC2[j + 1];
								}
								alumnosC2[contador2 - 1] = null;
								rutC2[contador2 - 1] = null;
								contador2--;

								alumnosC1[contador1] = alumnoMovido;
								rutC1[contador1] = rutCambio;
								contador1++;

								for(int g = 0; g < cantGrupoC2; g++) {
									if(grupoC2[g] != null && grupoC2[g].equals(alumnoMovido)) {
										for(int gj = g; gj < cantGrupoC2 - 1; gj++) {
											grupoC2[gj] = grupoC2[gj + 1];
											rutGrupoC2[gj] = rutGrupoC2[gj + 1];
										}
										grupoC2[cantGrupoC2 - 1] = null;
										rutGrupoC2[cantGrupoC2 - 1] = null;
										cantGrupoC2--;

										if(cantGrupoC1 < grupoC1.length) {
											grupoC1[cantGrupoC1] = alumnoMovido;
											rutGrupoC1[cantGrupoC1] = rutCambio;
											cantGrupoC1++;
										}
										break;
									}
								}

								actualizarArchivoAlumnos(alumnosC1, rutC1, alumnosC2, rutC2);
								System.out.println("[EXITO] Alumno cambiado de C2 a C1 exitosamente.");
								cambiado = true;
								break;
							}
						}
					}

					if (!cambiado) {
						System.out.println("[ERROR] No se encontro un alumno con ese RUT en ningun paralelo.");
					}

				} else if (opcionAdmin.equals("2")) {
					System.out.print("Ingrese el RUT del alumno a eliminar: ");
					String rutEliminar = sc.nextLine();
					boolean eliminado = false;

					for (int i = 0; i < contador1; i++) {
						if (rutC1[i] != null && rutC1[i].equals(rutEliminar)) {
							String alumnoEliminado = alumnosC1[i];
							for (int j = i; j < contador1 - 1; j++) {
								alumnosC1[j] = alumnosC1[j + 1];
								rutC1[j] = rutC1[j + 1];
							}
							alumnosC1[contador1 - 1] = null;
							rutC1[contador1 - 1] = null;
							contador1--;

							for(int g = 0; g < cantGrupoC1; g++) {
								if(grupoC1[g] != null && grupoC1[g].equals(alumnoEliminado)) {
									for(int gj = g; gj < cantGrupoC1 - 1; gj++) {
										grupoC1[gj] = grupoC1[gj + 1];
										rutGrupoC1[gj] = rutGrupoC1[gj + 1];
									}
									grupoC1[cantGrupoC1 - 1] = null;
									rutGrupoC1[cantGrupoC1 - 1] = null;
									cantGrupoC1--;
									break;
								}
							}

							actualizarArchivoAlumnos(alumnosC1, rutC1, alumnosC2, rutC2);
							System.out.println("[EXITO] Alumno eliminado del curso correctamente.");
							eliminado = true;
							break;
						}
					}

					if (!eliminado) {
						for (int i = 0; i < contador2; i++) {
							if (rutC2[i] != null && rutC2[i].equals(rutEliminar)) {
								String alumnoEliminado = alumnosC2[i];
								for (int j = i; j < contador2 - 1; j++) {
									alumnosC2[j] = alumnosC2[j + 1];
									rutC2[j] = rutC2[j + 1];
								}
								alumnosC2[contador2 - 1] = null;
								rutC2[contador2 - 1] = null;
								contador2--;

								for(int g = 0; g < cantGrupoC2; g++) {
									if(grupoC2[g] != null && grupoC2[g].equals(alumnoEliminado)) {
										for(int gj = g; gj < cantGrupoC2 - 1; gj++) {
											grupoC2[gj] = grupoC2[gj + 1];
											rutGrupoC2[gj] = rutGrupoC2[gj + 1];
										}
										grupoC2[cantGrupoC2 - 1] = null;
										rutGrupoC2[cantGrupoC2 - 1] = null;
										cantGrupoC2--;
										break;
									}
								}

								actualizarArchivoAlumnos(alumnosC1, rutC1, alumnosC2, rutC2);
								System.out.println("[EXITO] Alumno eliminado del curso correctamente.");
								eliminado = true;
								break;
							}
						}
					}

					if (!eliminado) {
						System.out.println("[ERROR] No se encontro ningun alumno con ese RUT.");
					}

				} else if (opcionAdmin.equals("3")) {
					System.out.print("Ingrese nombre: ");
					String nuevoNombre = sc.nextLine();
					System.out.print("Ingrese apellido: ");
					String nuevoApellido = sc.nextLine();
					System.out.print("Ingrese RUT: ");
					String nuevoRut = sc.nextLine();
					System.out.print("Ingrese paralelo (C1 o C2): ");
					String nuevoParalelo = sc.nextLine().toUpperCase();

					if (nuevoParalelo.equals("C1")) {
						alumnosC1[contador1] = nuevoNombre + "-" + nuevoApellido;
						rutC1[contador1] = nuevoRut;
						contador1++;
						actualizarArchivoAlumnos(alumnosC1, rutC1, alumnosC2, rutC2);
						System.out.println("[EXITO] Alumno inscrito nuevo en C1.");
					} else if (nuevoParalelo.equals("C2")) {
						alumnosC2[contador2] = nuevoNombre + "-" + nuevoApellido;
						rutC2[contador2] = nuevoRut;
						contador2++;
						actualizarArchivoAlumnos(alumnosC1, rutC1, alumnosC2, rutC2);
						System.out.println("[EXITO] Alumno inscrito nuevo en C2.");
					} else {
						System.out.println("[ERROR] Paralelo invalido. Debe ser C1 o C2.");
					}
				} else {
					System.out.println("Opcion de administracion invalida.");
				}
				break;
			//caso 5
			case "5":
				System.out.println("--- Generar Reportes ---");
				System.out.println("1) Reporte Paralelo C1");
				System.out.println("2) Reporte Paralelo C2");
				System.out.println("3) Reporte de Rechazados");
				System.out.print("Ingrese opcion de reporte: ");
				String opReporte = sc.nextLine();

				File dirReportes = new File("Reportes");
				if (!dirReportes.exists()) {
					dirReportes.mkdir();
				}

				if (opReporte.equals("1")) {
					versionC1++;
					String nombreArchivo = "Reportes/ReporteC1-V" + versionC1 + ".txt";
					try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
						bw.write("=== Miembros del grupo - Paralelo C1 ===");
						bw.newLine();
						for (int i = 0; i < cantGrupoC1; i++) {
							if (grupoC1[i] != null && rutGrupoC1[i] != null) {
								bw.write(grupoC1[i].replace("-", " ") + " - " + rutGrupoC1[i]);
								bw.newLine();
							}
						}
						System.out.println("[EXITO] Reporte generado exitosamente: " + nombreArchivo);
					} catch (IOException e) {
						System.out.println("[ERROR] No se pudo generar el reporte C1.");
					}
				} else if (opReporte.equals("2")) {
					versionC2++;
					String nombreArchivo = "Reportes/ReporteC2-V" + versionC2 + ".txt";
					try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
						bw.write("=== Miembros del grupo - Paralelo C2 ===");
						bw.newLine();
						for (int i = 0; i < cantGrupoC2; i++) {
							if (grupoC2[i] != null && rutGrupoC2[i] != null) {
								bw.write(grupoC2[i].replace("-", " ") + " - " + rutGrupoC2[i]);
								bw.newLine();
							}
						}
						System.out.println("[EXITO] Reporte generado exitosamente: " + nombreArchivo);
					} catch (IOException e) {
						System.out.println("[ERROR] No se pudo generar el reporte C2.");
					}
				} else if (opReporte.equals("3")) {
					versionRech++;
					String nombreArchivo = "Reportes/Rechazados-V" + versionRech + ".txt";
					try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
						bw.write("=== Solicitudes rechazadas ===");
						bw.newLine();
						for (int i = 0; i < cantRechazados; i++) {
							if (registroRechazados[i] != null) {
								bw.write(registroRechazados[i]);
								bw.newLine();
							}
						}
						System.out.println("[EXITO] Reporte generado exitosamente: " + nombreArchivo);
					} catch (IOException e) {
						System.out.println("[ERROR] No se pudo generar el reporte de rechazados.");
					}
				} else {
					System.out.println("Opcion de reporte invalida.");
				}
				break;
			//caso 6
			case "6":
				entrar = false;
				break;
			default:
				System.out.println("OPCION INVALIDA");
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
		private static void actualizarArchivoAlumnos(String[] alumnosC1, String[] rutC1, String[] alumnosC2, String[] rutC2) {
			try (BufferedWriter bw = new BufferedWriter(new FileWriter("Alumnos.txt"))) {
				for (int i = 0; i < contador1; i++) {
					if (alumnosC1[i] != null && rutC1[i] != null) {
						String[] partesNombre = alumnosC1[i].split("-");
						String nombre = partesNombre[0];
						String apellido = partesNombre.length > 1 ? partesNombre[1] : "";
						bw.write(nombre + ";" + apellido + ";" + rutC1[i] + ";C1");
						bw.newLine();
					}
				}
				for (int i = 0; i < contador2; i++) {
					if (alumnosC2[i] != null && rutC2[i] != null) {
						String[] partesNombre = alumnosC2[i].split("-");
						String nombre = partesNombre[0];
						String apellido = partesNombre.length > 1 ? partesNombre[1] : "";
						bw.write(nombre + ";" + apellido + ";" + rutC2[i] + ";C2");
						bw.newLine();
					}
				}
			} catch (IOException e) {
				System.out.println("Error al guardar los cambios en Alumnos.txt");
			}
		}
	}
}