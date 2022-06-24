package ar.unrn.tp11.modelo;

import fernandezsanjuan.framework.Accion;

public class AccionTreinta implements Accion{

	@Override
	public String descripcionItemMenu() {
		return "Te Canta Feliz Cumple!!";
	}

	@Override
	public void ejecutar() {
		System.out.println("Beep Boop  Que los Cumplas Feliz, que los Cumplas Feliz!!!!");
	}

	@Override
	public String nombreItemMenu() {
		return "Accion Treinta";
	}

}
