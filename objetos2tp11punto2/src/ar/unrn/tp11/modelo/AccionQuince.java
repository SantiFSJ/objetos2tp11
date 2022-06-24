package ar.unrn.tp11.modelo;

import fernandezsanjuan.framework.Accion;

public class AccionQuince implements Accion{

	@Override
	public String descripcionItemMenu() {
		return "Hace la tarea numero 15";
	}

	@Override
	public void ejecutar() {
		System.out.println("Beep Boop estoy ejecutando la tarea Numero 15");
		
	}

	@Override
	public String nombreItemMenu() {
		return "Accion Quince";
	}

}
