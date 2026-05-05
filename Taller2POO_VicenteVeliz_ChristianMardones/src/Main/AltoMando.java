package Main;

import java.util.ArrayList;

public class AltoMando {
	private int numeroAltoMando;
	private String nombre;
	private ArrayList<Pokemon> pokemons;

	public AltoMando(int numeroAltoMando, String nombre, ArrayList<Pokemon> pokemons) {
		this.numeroAltoMando = numeroAltoMando;
		this.nombre = nombre;
		this.pokemons = pokemons;
	}

	public int getNumeroAltoMando() {
		return numeroAltoMando;
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<Pokemon> getPokemons() {
		return pokemons;
	}
	
	

}
