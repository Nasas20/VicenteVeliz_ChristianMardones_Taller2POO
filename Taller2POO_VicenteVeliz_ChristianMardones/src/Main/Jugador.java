package Main;

import java.util.ArrayList;
import java.util.Scanner;


public class Jugador {
	private String nombreCuenta;
	private int cantMedallas;
	private ArrayList<Pokemon> pokemones = new ArrayList<>();
	private ArrayList<Pokemon> pc = new ArrayList<>();
	
	
	public Jugador(String nombreCuenta, int cantMedallas) {
		this.nombreCuenta = nombreCuenta;
		this.cantMedallas = cantMedallas;
	}
	
	
	public void agregarPokemones(Pokemon p) {
		if (pokemones.size() != 6) {
			pokemones.add(p);
			System.out.println("¡Pokemon capturado!, se ha añadido a su equipo.");
			System.out.println();
		}
		else {
			p.setEstado("pc");
			pc.add(p);
			System.out.println("¡Pokemon capturado!, se ha añadido al pc.");
			System.out.println();
		}
	}


	public String getNombreCuenta() {
		return nombreCuenta;
	}


	public int getCantMedallas() {
		return cantMedallas;
	}


	public ArrayList<Pokemon> getPokemonesEquipo() {
		return pokemones;
	}
	
	public ArrayList<Pokemon> getPokemonesGeneral() {
		ArrayList<Pokemon> pokemonGeneral = pokemones;
		pokemonGeneral.addAll(pc);
		
		return pokemonGeneral;
		
		
	}
	
	public void mostrarEquipo() {
		if (pokemones.isEmpty()) {
			System.out.println("No hay pokemones. ");
		}
		else {
			System.out.println("Equipo:");
			int contador = 1;
			for (Pokemon pokemon : pokemones) {
				System.out.println(contador + ") " + pokemon.getNombrePokemon() + "|" + pokemon.getTipo() + "|Stats totales: " + pokemon.getEstadisticas() + "|" + pokemon.getEstado());
				contador++;
			}
			System.out.println();
		}
	}
	
	public void mostrarPC() {
		System.out.println("PC: ");
		int contador = 1;
		for (Pokemon pokemon : pc) {
			System.out.println(contador + ") " + pokemon.getNombrePokemon() + "|" + pokemon.getTipo() + "|Stats totales: " + pokemon.getEstadisticas());
			contador++;
		}
		System.out.println();
	}
	
	public void accederPC() {
		Scanner input = new Scanner(System.in);
		int eleccion = 0;
		
		if (pokemones.isEmpty()) {
			System.out.println("No tienes pokemones en tu equipo.");
		}
		else if (pc.isEmpty()) {
			System.out.println("No tienes pokemons en tu pc.");
		}
		else {
			boolean seguirViendo = true;
			while (seguirViendo) {
				mostrarEquipo();
				mostrarPC();
				
				System.out.println("¿Que deseas hacer?");
				System.out.println("1) Intercambiar pokemon.");
				System.out.println("2) Salir.");
				System.out.print("¿Que deseas hacer?: ");
				eleccion = input.nextInt();
				input.nextLine();
				
				//Implementar control de error aqui
				
				switch (eleccion) {
				case 1:
					mostrarEquipo();
					System.out.print("¿Que pokemon desea mover a la caja?:");
					int pokemonPalPc = input.nextInt();
					input.nextLine();
					
					//Implementar control de error aqui.
					
					mostrarPC();
					System.out.print("¿Que pokemon desea mover para el equipo?:");
					int pokemonPalEquipo = input.nextInt();
					input.nextLine();
					
					//Implementar control de error aqui.
					
					Pokemon pokemonEquipo = pokemones.get(pokemonPalPc - 1); //Obtengo el pokemon que quiero mandar para el pc.
					Pokemon pokemonPC = pc.get(pokemonPalEquipo - 1); //Obtengo el pokemon que quiero mandar para el equipo.
					
					pokemonEquipo.setEstado("pc");  //Cambio sus estados.
					pokemonPC.setEstado("Vivo");
					
					pokemones.set(pokemonPalPc - 1, pokemonPC); //Hago el intercambio entre el pc y el equipo.
					pc.set(pokemonPalEquipo - 1, pokemonEquipo);
					
					System.out.println("¡Intercambio hecho de manera correcta!");
					System.out.println();
					
					
					break;
				
				case 2:
					seguirViendo = false;
					System.out.println();

				default:
					break;
				}
			}
		}
	}

}
