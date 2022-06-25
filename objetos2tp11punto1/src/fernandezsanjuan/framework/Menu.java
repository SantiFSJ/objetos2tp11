package fernandezsanjuan.framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import fernandezsanjuan.utilizacion.AccionDos;
import fernandezsanjuan.utilizacion.AccionUno;

public class Menu {
	private String pathConfig;
	private List<Accion> acciones; //properties.get(accion)
	
	public Menu(String pathConfig) {
		this.acciones = new ArrayList<Accion>();
		this.pathConfig = pathConfig;
		this.acciones = leerAccionesDelProperties();
	}
	
	public void run() {
		imprimirMenu();
	}
	
	private List<Accion> leerAccionesDelProperties() {
		Properties prop = new Properties();
		ArrayList<Accion> listaDeAcciones = new ArrayList<Accion>();
		try (InputStream configFile = getClass().getResourceAsStream(this.pathConfig);){		
			prop.load(configFile);
			String clase = prop.getProperty("clase");
			
			String[] clases = clase.split(";");
			
			for(int i=0;i<clases.length;i++){
				Accion accionTemp = (Accion) Class.forName(clases[i]).getDeclaredConstructor().newInstance();
				listaDeAcciones.add(accionTemp);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Se Produjo un Error al Instanciar una Clase del Config: " +e.getMessage());
		}
		
		return listaDeAcciones;
	}
	
	private void imprimirMenu() {	
		int i = 1;
		System.out.println("\nBienvenido, estas son sus opciones: ");
		for(Accion a: acciones) {
			System.out.println(i+". " +a.nombreItemMenu() +" ("+a.descripcionItemMenu()+")");
			i++;
		}
		System.out.println("0. Salir");
		System.out.println("Ingrese su opcion: ");
		
		leerAccionDeConsola();
	}
	
	private void leerAccionDeConsola() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String opcion;
		try {
			opcion = reader.readLine();
			if(!opcion.equals("0")) {
				try {
				this.acciones.get(Integer.valueOf(opcion)-1).ejecutar();;
				imprimirMenu();
				}catch(Exception e) {
					System.out.println("Por Favor, Ingrese una opcion valida");
					imprimirMenu();
				}
			}else 
				System.out.println("Empacando Todo!!!");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}

}
