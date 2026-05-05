package Main;

import java.util.ArrayList;

public class Gimnasio {

	private int numeroGimnasio;
	private String lider;
	private String estado;
	private ArrayList<Pokemon> pokemons;

	public Gimnasio(int numeroGimnasio, String lider, String estado, ArrayList<Pokemon> pokemons) {
		this.numeroGimnasio = numeroGimnasio;
		this.lider = lider;
		this.estado = estado;
		this.pokemons = pokemons;
	}

	public int getNumeroGimnasio() {
		return numeroGimnasio;
	}

	public String getLider() {
		return lider;
	}

	public String getEstado() {
		return estado;
	}

	public ArrayList<Pokemon> getPokemons() {
		return pokemons;
	}
	
	public void setEstado(String estado) {
        this.estado = estado;
    }
	
	public void agregarPokemon(Pokemon p) {
        this.pokemons.add(p);
    }
	
	public void mostrarGimnasio() {
        System.out.println("Gimnasio N°: " + numeroGimnasio);
        System.out.println("Líder: " + lider);
        System.out.println("Estado: " + estado);
        System.out.println("Pokémon:");
        for (Pokemon p : pokemons) {
            System.out.println("- " + p.getNombrePokemon() + " | " + p.getTipo());
        }
    }

}
