package fernandezsanjuan.framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fernandezsanjuan.utilizacion.AccionDos;
import fernandezsanjuan.utilizacion.AccionUno;

public class Menu {
	private String pathConfig;
	private List<Accion> acciones;
	
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
			throw new RuntimeException("Se Produjo un Error al Instanciar una Clase del Config: " +e.getMessage());
		}
		
		return listaDeAcciones;
	}
	
	private void imprimirMenu() {
		JFrame frame = new JFrame("framework");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 451, 229);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        frame.setVisible(true);
        	for(Accion a:this.acciones ) {
        		JButton btnNewButton = new JButton(a.nombreItemMenu());
        		contentPane.add(btnNewButton);
        	
        		btnNewButton.addActionListener((e) -> {
                a.ejecutar();
                 JOptionPane.showMessageDialog(null,  "Se ejecuto la tarea: " + a.nombreItemMenu(),"informacion",
                         JOptionPane.INFORMATION_MESSAGE);
            });
            }
    	frame.setVisible(true);	
	}

}
