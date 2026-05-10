package Main;

import java.util.ArrayList;
import java.util.Scanner;


public class Jugador {
	private String nombreCuenta;
	private String[] cantMedallas;
	private ArrayList<Pokemon> pokemones = new ArrayList<>();
	private ArrayList<Pokemon> pc = new ArrayList<>();
	
	
	public Jugador(String nombreCuenta, String[] cantMedallas) {
		this.nombreCuenta = nombreCuenta;
		this.cantMedallas = cantMedallas;
	}
	
	
	public void agregarPokemones(Pokemon p) {
		if (pokemones.size() != 6) {
			pokemones.add(p);
		}
		else {
			p.setEstado("pc");
			pc.add(p);
		}
	}


	public String getNombreCuenta() {
		return nombreCuenta;
	}


	public String getCantMedallas() {
		String medallas = "";
		for (String a : cantMedallas) {
			if (!(a == null)) {
				medallas +=a + ";";
			}
		}
		
		return medallas;
	}
	
	public int getIntCantMedallas() {
		int medallas = 0;
		for (String a : cantMedallas) {
			if (!(a == null)) {
				medallas ++;
			}
		}
		
		return medallas;
	}
	
	public String[] getCantMedallasLista() {
		return this.cantMedallas;
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
				eleccion = 0;
				
				try {
	        		eleccion = input.nextInt();
				} catch (Exception e) {
					System.out.println();
					input.nextLine();
				}
				
				//Implementar control de error aqui
				
				System.out.println();
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
					System.out.println("Escoge un valor valido.");
					break;
				}
			}
		}
	}
	
	public Pokemon combate(Pokemon pokemonJugador, Pokemon pokemonEnemigo) {
		String tipoJugador = pokemonJugador.getTipo();
		String tipoEnemigo = pokemonEnemigo.getTipo();
		
		double[][] tablaTipos = TablaTipos.getEfectividad();
		
		int posicionJugador = TablaTipos.retornarPosicionTipo(tipoJugador);
		int posicionEnemigo = TablaTipos.retornarPosicionTipo(tipoEnemigo);
		
		//Printear combate
		System.out.println(pokemonJugador.getNombrePokemon() + "-> " + pokemonJugador.getEstadisticas() + " puntos");
		System.out.println(pokemonEnemigo.getNombrePokemon() + "-> " + pokemonEnemigo.getEstadisticas() + " puntos");
		
		//Calcuar estadisticas ATCANTE
		if (tablaTipos[posicionJugador][posicionEnemigo] == 1.0) { //Neutral
			System.out.println("neutral");
			if (pokemonJugador.getEstadisticas() >= pokemonEnemigo.getEstadisticas()) {
				System.out.println("Ha ganado " + pokemonJugador.getNombrePokemon() + "! " + pokemonEnemigo.getNombrePokemon() + " ha sido derrotado...");
				System.out.println();
				return pokemonJugador;
			}
			else {
				System.out.println("Ha ganado " + pokemonEnemigo.getNombrePokemon() + "! " + pokemonJugador.getNombrePokemon() + " ha sido derrotado...");
				System.out.println();
				return pokemonEnemigo;
			}
		} else if (tablaTipos[posicionJugador][posicionEnemigo] == 2.0) { //Efectivo
			System.out.println("efectivo");
			if (pokemonJugador.getEstadisticas()*2 >= pokemonEnemigo.getEstadisticas()) {
				System.out.println("Ha ganado " + pokemonJugador.getNombrePokemon() + "! " + pokemonEnemigo.getNombrePokemon() + " ha sido derrotado...");
				System.out.println();
				
				return pokemonJugador;
			}
			else {
				System.out.println("Ha ganado " + pokemonEnemigo.getNombrePokemon() + "! " + pokemonJugador.getNombrePokemon() + " ha sido derrotado...");
				System.out.println();
				return pokemonEnemigo;
			}
		} else if (tablaTipos[posicionJugador][posicionEnemigo] == 0.5) {
			System.out.println("poco efectivo");
			if (pokemonJugador.getEstadisticas()*0.5 >= pokemonEnemigo.getEstadisticas()) {
				System.out.println("Ha ganado " + pokemonJugador.getNombrePokemon() + "! " + pokemonEnemigo.getNombrePokemon() + " ha sido derrotado...");
				System.out.println();
				return pokemonJugador;
			}
			else {
				System.out.println("Ha ganado " + pokemonEnemigo.getNombrePokemon() + "! " + pokemonJugador.getNombrePokemon() + " ha sido derrotado...");
				System.out.println();
				return pokemonEnemigo;
			}
		} else {
			System.out.println("cero efectivo");
			if (pokemonJugador.getEstadisticas()*0.0 >= pokemonEnemigo.getEstadisticas()) {
				System.out.println("Ha ganado " + pokemonJugador.getNombrePokemon() + "! " + pokemonEnemigo.getNombrePokemon() + " ha sido derrotado...");
				System.out.println();
				return pokemonJugador;
			}
			else {
				System.out.println("Ha ganado " + pokemonEnemigo.getNombrePokemon() + "! " + pokemonJugador.getNombrePokemon() + " ha sido derrotado...");
				System.out.println();
				return pokemonEnemigo;
			}
		}
	}
	
	public boolean combateGimnasio(Gimnasio lider) {
		ArrayList<Pokemon> liderGimnasio = lider.getPokemons();
		boolean seguirViendo = true;
		int eleccion = 0;
		int pokemonActual = 0;
		
		if (pokemones.get(pokemonActual).getEstado().equals("Derrotado")) {
			for (Pokemon p : this.pokemones) {
				if (p.getEstado().equals("Vivo")) {
					int index = this.pokemones.indexOf(p);
					pokemonActual = index;
					break;
				}
			}
		}
		
		Pokemon pokemonActualLider = null;
		Scanner input = new Scanner(System.in);
		
		System.out.println();
		System.out.println("Desafiando a " + lider.getLider() + "!!");
		System.out.println();
		
		while (seguirViendo) {
			for (Pokemon p : liderGimnasio) {
				if (p.getEstado().equals("Vivo")) {
					pokemonActualLider = p;
				}
			}
			
			if (pokemonActualLider == null) {
				System.out.println("Has ganado a " + lider.getLider() + "!");
				return true;
			}
			
			
			System.out.println(lider.getLider() + " saca a " + pokemonActualLider.getNombrePokemon() + "!");
			System.out.println(getNombreCuenta() + " saca a " + pokemones.get(pokemonActual).getNombrePokemon() + "!");
			System.out.println();
			
			System.out.print("Que deseas hacer?\r\n"
					+ "1) Atacar\r\n"
					+ "2) Cambiar de pokemon\r\n"
					+ "3) Rendirse\r\n"
					+ "Ingrese Opcion:");
			eleccion = input.nextInt();
			
			
			//Agregar control de error aca
			
			System.out.println();
			
			switch (eleccion) {
			case 1:
				Pokemon pokemonGanador = combate(pokemones.get(pokemonActual), pokemonActualLider);
				
				if (pokemonGanador.equals(pokemones.get(pokemonActual))) {
					int indexPokemonLider = liderGimnasio.indexOf(pokemonActualLider);
					pokemonActualLider.setEstado("Derrotado");
					liderGimnasio.set(indexPokemonLider, pokemonActualLider);
					pokemonActualLider = null;
				} else {
					Pokemon pokemonEntrenador = pokemones.get(pokemonActual);
					pokemonEntrenador.setEstado("Derrotado");
					pokemones.set(pokemonActual, pokemonEntrenador);
					
					int contadorVivos = 0;
					
					for (Pokemon p : pokemones) {
						if (p.getEstado().equals("Vivo")) {
							contadorVivos++;
						}
					}
					
					if (contadorVivos > 0) {
						for (Pokemon p : pokemones) {
							if (p.getEstado().equals("Vivo")) {
								pokemonActual = pokemones.indexOf(p);
							}
						}
					} else {
						System.out.println("Te has quedado sin pokemones en tu equipo!");
						System.out.println("Volviendo al menu...");
						return false;
					}
					
				}
				
				break;
			
			case 2:
				mostrarEquipo();
				System.out.print("¿A que pokemon quieres cambiar?: ");
				pokemonActual = input.nextInt()-1;
				
				//Agregar control de error aqui.
				
				while (this.pokemones.get(pokemonActual).getEstado().equals("Derrotado")) {
					System.out.println();
					System.out.println("El pokemon elegido, ya ha sido derrotado, por favor escoge otro.");
					System.out.println();
					
					mostrarEquipo();
					System.out.print("¿A que pokemon quieres cambiar?: ");
					pokemonActual = input.nextInt()-1;
				}
				
				break;
			
			case 3:
				System.out.println("Te has rendido, por lo que " + lider.getLider() + " ha ganado el combate.");
				System.out.println("Volviendo al menu...");
				return false;

			default:
				break;
			}
			
		}
		return false;
	}
	
	public boolean combateAltoMando(AltoMando lider) {     //Reutilize el codigo de combate de gimnasios para el combate contra el Alto Mando.
		ArrayList<Pokemon> liderGimnasio = lider.getPokemons();
		boolean seguirViendo = true;
		int eleccion = 0;
		int pokemonActual = 0;
		
		if (pokemones.get(pokemonActual).getEstado().equals("Derrotado")) {
			for (Pokemon p : this.pokemones) {
				if (p.getEstado().equals("Vivo")) {
					int index = this.pokemones.indexOf(p);
					pokemonActual = index;
					break;
				}
			}
		}
		
		Pokemon pokemonActualLider = null;
		Scanner input = new Scanner(System.in);
		
		System.out.println();
		System.out.println("Desafiando a " + lider.getNombre() + "!!");
		System.out.println();
		
		while (seguirViendo) {
			for (Pokemon p : liderGimnasio) {
				if (p.getEstado().equals("Vivo")) {
					pokemonActualLider = p;
				}
			}
			
			if (pokemonActualLider == null) {
				System.out.println("Has ganado a " + lider.getNombre() + "!");
				return true;
			}
			
			
			System.out.println(lider.getNombre() + " saca a " + pokemonActualLider.getNombrePokemon() + "!");
			System.out.println(getNombreCuenta() + " saca a " + pokemones.get(pokemonActual).getNombrePokemon() + "!");
			System.out.println();
			
			System.out.print("Que deseas hacer?\r\n"
					+ "1) Atacar\r\n"
					+ "2) Cambiar de pokemon\r\n"
					+ "3) Rendirse\r\n"
					+ "Ingrese Opcion:");
			eleccion = input.nextInt();
			
			//Agregar control de error aca
			
			System.out.println();
			
			switch (eleccion) {
			case 1:
				Pokemon pokemonGanador = combate(pokemones.get(pokemonActual), pokemonActualLider);
				
				if (pokemonGanador.equals(pokemones.get(pokemonActual))) {
					int indexPokemonLider = liderGimnasio.indexOf(pokemonActualLider);
					pokemonActualLider.setEstado("Derrotado");
					liderGimnasio.set(indexPokemonLider, pokemonActualLider);
					pokemonActualLider = null;
				} else {
					Pokemon pokemonEntrenador = pokemones.get(pokemonActual);
					pokemonEntrenador.setEstado("Derrotado");
					pokemones.set(pokemonActual, pokemonEntrenador);
					
					int contadorVivos = 0;
					
					for (Pokemon p : pokemones) {
						if (p.getEstado().equals("Vivo")) {
							contadorVivos++;
						}
					}
					
					if (contadorVivos > 0) {
						for (Pokemon p : pokemones) {
							if (p.getEstado().equals("Vivo")) {
								pokemonActual = pokemones.indexOf(p);
							}
						}
					} else {
						System.out.println("Te has quedado sin pokemones en tu equipo!");
						System.out.println("Volviendo al menu...");
						return false;
					}
					
				}
				
				break;
			
			case 2:
				mostrarEquipo();
				System.out.print("¿A que pokemon quieres cambiar?: ");
				pokemonActual = input.nextInt()-1;
				
				//Agregar control de error aqui.
				
				while (this.pokemones.get(pokemonActual).getEstado().equals("Derrotado")) {
					System.out.println();
					System.out.println("El pokemon elegido, ya ha sido derrotado, por favor escoge otro.");
					System.out.println();
					
					mostrarEquipo();
					System.out.print("¿A que pokemon quieres cambiar?: ");
					pokemonActual = input.nextInt()-1;
				}
				break;
			
			case 3:
				System.out.println("Te has rendido, por lo que " + lider.getNombre() + " ha ganado el combate.");
				System.out.println("Volviendo al menu...");
				return false;

			default:
				break;
			}
			
		}
		return false;
	}


	public void agregarMedallas(String lider) {
		int contador = 0;
		for (String a : cantMedallas) {
			if (!(a == null)) {
				contador++;
			}
		}
		
		cantMedallas[contador] = lider;
		
	}
	
	public void curarPokemones() {
		for (Pokemon p : pokemones) {
			if (p.getEstado().equals("Derrotado")) {
				int index = pokemones.indexOf(p);
				p.setEstado("Vivo");
				pokemones.set(index, p);
			}
		}
		System.out.println("¡Pokemones curados!");
	}
	
	public boolean chequearPokemonVivo() { //Chequea si en el equipo hay pokemones vivos
		int contador = 0;
		
		
		for (Pokemon p : this.pokemones) {
			if (p.getEstado().equalsIgnoreCase("Vivo")) {
				contador++;
			}
		}
		
		if (contador > 0) {
			return true;
		} else {
			return false;
		}
	}
}
