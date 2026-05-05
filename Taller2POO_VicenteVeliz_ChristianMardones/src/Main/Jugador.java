package Main;

import java.util.ArrayList;

public class Jugador {
	private String nombreCuenta;
	private int cantMedallas;
	private ArrayList<Pokemon> pokemones = new ArrayList<>();
	
	
	public Jugador(String nombreCuenta, int cantMedallas) {
		this.nombreCuenta = nombreCuenta;
		this.cantMedallas = cantMedallas;
	}
	
	
	public void agregarPokemones(Pokemon p) {
		pokemones.add(p);
	}

}
