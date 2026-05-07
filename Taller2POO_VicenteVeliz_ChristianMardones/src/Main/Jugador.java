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


	public String getNombreCuenta() {
		return nombreCuenta;
	}


	public int getCantMedallas() {
		return cantMedallas;
	}


	public ArrayList<Pokemon> getPokemones() {
		return pokemones;
	}
	
	public void mostrarEquipo() {
		if (pokemones.isEmpty()) {
			System.out.println("no hay pokemones");
		}
		else {
			System.out.println("equipo de " + this.nombreCuenta);
			for (Pokemon pokemon : pokemones) {
				System.out.println("-" + pokemon.getNombrePokemon() + "estado = " + pokemon.getEstado());
			}
		}
	}

}
