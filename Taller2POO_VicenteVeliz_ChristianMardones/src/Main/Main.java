package Main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static ArrayList<Pokemon> pokedex = new ArrayList<>();
	public static ArrayList<Habitat> habitats = new ArrayList<>();
	public static ArrayList<Gimnasio> gimnasios = new ArrayList<>();
	public static ArrayList<AltoMando> altosMandos = new ArrayList<>();



	public static void main(String[] args) throws FileNotFoundException {
		leerPokedex();
        leerHabitat();
        leerGimnasios();
        leerAltoMando();
        
        mostrarMenuInicio();


	}
	
	private static void mostrarMenuInicio() throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);

       
        boolean verInicio = true;
        
        while (verInicio) {
        	
    	    System.out.println("¡Bienvenido al juego de Pokémon!");
            System.out.println("1) Continuar");
            System.out.println("2) Nueva Partida");
            System.out.println("3) Salir");

            System.out.print("Elige una opción: ");
        	int opcion = scanner.nextInt();

            switch (opcion) {
         		case 1:
         			Jugador jugador = leerRegistros();
      				mostrarMenuJugador(jugador);
      				break;
             			
         		case 2:
         			System.out.println("dou");
         			nuevoProgreso();
         			break;
             			
     			default:
     				System.out.println("corre");
     				break;
             	
             }
        }
        
       
		
	}
	
	
	
	public static void salirACapturar(Jugador jugador) {
	    Scanner scanner = new Scanner(System.in);

	    System.out.println("Dónde deseas ir a explorar");
	    System.out.println("Zonas disponibles:");
	    System.out.println("1) Lago");
	    System.out.println("2) Cueva");
	    System.out.println("3) Montaña");
	    System.out.println("4) Bosque");
	    System.out.println("5) Prado");
	    System.out.println("6) Mar");
	    System.out.println("7) Volver al menú");

	    System.out.print("\nElige una zona: ");
	    int zona = scanner.nextInt();

	    switch (zona) {
	        case 1:
	            capturarPokemonEnZona(jugador, "Lago");
	            break;
	        case 2:
	            capturarPokemonEnZona(jugador, "Cueva");
	            break;
	        case 3:
	            capturarPokemonEnZona(jugador, "Montaña");
	            break;
	        case 4:
	            capturarPokemonEnZona(jugador, "Bosque");
	            break;
	        case 5:
	            capturarPokemonEnZona(jugador, "Prado");
	            break;
	        case 6:
	            capturarPokemonEnZona(jugador, "Mar");
	            break;
	        case 7:
	            System.out.println("Volviendo al menú...");
	            mostrarMenuJugador(jugador); 
	            break;
	        default:
	            System.out.println("Opción no válida.");
	            break;
	    }
	}
	
	public static void capturarPokemonEnZona(Jugador jugador, String zona) {
	    System.out.println("Explorando la zona de: " + zona);

	    Pokemon pokemonCapturado = capturarPokemonPorZona(zona);
	    
	    if (pokemonCapturado != null) {
	        System.out.println("aparecio un " + pokemonCapturado.getNombrePokemon() );
	        
	        System.out.println("¿Qué deseas hacer?");
	        System.out.println("1) Capturar");
	        System.out.println("2) Huir");

	        System.out.print("\nElige una opción: ");
	        Scanner scanner = new Scanner(System.in);
	        int opcion = scanner.nextInt();

	        if (opcion == 1) {
	        	
	        	if (jugador.getPokemonesEquipo().contains(pokemonCapturado)) {
	        		System.out.println("Ya posees a este pokemon!");
	        	}
	        	else {
	        		jugador.agregarPokemones(pokemonCapturado);
	        	}
	        	
	        } else if (opcion == 2) {
	            System.out.println("Has huido de la batalla.");
	        } else {
	            System.out.println("Opción no válida. Has huido de la batalla.");
	        }
	    } else {
	        System.out.println("No lograste capturar ningún Pokémon en esta zona.");
	    }
	}
	
	public static Pokemon capturarPokemonPorZona(String zona) {
	    ArrayList<Pokemon> pokemonsPorZona = new ArrayList<>();

	    for (Pokemon p : pokedex) {
	        if (p.getHabitat().equalsIgnoreCase(zona)) {
	            pokemonsPorZona.add(p);
	        }
	    }

	    if (pokemonsPorZona.isEmpty()) {
	        System.out.println("No hay Pokémon en esta zona.");
	        return null;
	    }

	    int index = (int) (Math.random() * pokemonsPorZona.size());
	    Pokemon pokemonSeleccionado = pokemonsPorZona.get(index);

	    double probabilidad = Math.random();
	    if (probabilidad <= pokemonSeleccionado.getAparicion()) {
	        return pokemonSeleccionado;  
	    } else {
	        return null;  
	    }
	}
	
	
	public static void nuevoProgreso() {
		Scanner scan = new Scanner(System.in);
	    System.out.print("Ingresa tu nombre de jugador: ");
	    String nombreJugador = scan.nextLine();
	    
	    Jugador nuevoJugador = new Jugador(nombreJugador, 0);
	    
	    guardarProgreso(nuevoJugador);
	    
	    System.out.println("Bienvenido " + nombreJugador);
	    mostrarMenuJugador(nuevoJugador);

	}
	
	public static void guardarProgreso(Jugador jugador) {
		try {
	        BufferedWriter writer = new BufferedWriter(new FileWriter("archivos/registros.txt", false));
	        writer.write(jugador.getNombreCuenta() + ";" + jugador.getCantMedallas());
	        writer.newLine();
	        
	        for (Pokemon p : jugador.getPokemonesGeneral()) {
	            writer.write(p.getNombrePokemon() + ";" + p.getEstado());  
	            writer.newLine();
	        }
	        
	        writer.close();
	        
	        System.out.println("Progreso guardado exitosamente.");
	    } catch (IOException e) {
	        System.out.println("Error al guardar el progreso: " + e.getMessage());
	    }
	}
	


	public static void leerAltoMando() throws FileNotFoundException {
	    File arch = new File("archivos/altosmandos.txt");
	    Scanner scan = new Scanner(arch);

	    while(scan.hasNextLine()) {
	        String linea = scan.nextLine();
	        String[] partes = linea.split(";");
	        
	        int numeroAltoMando = Integer.parseInt(partes[0]);
	        String nombre = partes[1];
	        
	        ArrayList<Pokemon> pokemons = new ArrayList<>();
	        
	        for (int i = 2; i < partes.length; i++) {
	            String nombrePokemon = partes[i];
	          
	            for (Pokemon poke : pokedex) {
	                if (poke.getNombrePokemon().equals(nombrePokemon)) {
	                    pokemons.add(poke);  
	                    break;
	                }
	            }
	        }

	        AltoMando altoMando = new AltoMando(numeroAltoMando, nombre, pokemons);
	        altosMandos.add(altoMando);
	    }
	    scan.close();
	}
	
	public static void mostrarMenuJugador(Jugador j) {
		Scanner scanner = new Scanner(System.in);
		boolean ver = true;
		
		while (ver) {
			
			System.out.println(j.getNombreCuenta() + ", que desea hacer?");
			System.out.println();
		     System.out.println("1) Revisar equipo");
		     System.out.println("2) Salir a capturar");
		     System.out.println("3) Acceder al PC (cambiar Pokémon)");
	         System.out.println("4) Retar un gimnasio");
	         System.out.println("5) Desafío al Alto Mando");
	         System.out.println("6) Curar Pokémon");
	         System.out.println("7) Guardar");
	         System.out.println("8) Guardar y Salir");

	         System.out.print("Elige una opción: ");
	         int opcion = scanner.nextInt();
		        
	         switch (opcion) {
	        	case 1:
	        		System.out.println("mostrando equipo");
	        		j.mostrarEquipo();
	        		
	        		break;
	        	case 2:
	        		System.out.println("eligiendo zona");
	        		salirACapturar(j);
	        		break;
	        	case 3:
	        		j.accederPC();
	        		break;
	        	case 4:
	        		System.out.println("desafiando un gimnasio"); 
	        		break;
	        	case 5:
	        		System.out.println("desafiando alto mando");
	        		break;
	        	case 6:
	        		System.out.println("curando pokemones");
	        		break;
	        	case 7:
	        		System.out.println("guardando progreso");
	        		guardarProgreso(j);
	        		break;
	        	case 8:
	        		System.out.println("guardando progreso y saliendo");
	        		guardarProgreso(j);
	        		ver = false;
	        		break;
	        		
	        		
	    		default: 
	    			System.out.println("opcion invalida");
	                mostrarMenuJugador(j);
	                break;

	        	
	        }
		}
		
		

       

	}
	
	
	
	public static void leerGimnasios() throws FileNotFoundException {
		File arch = new File("archivos/gimnasios.txt");
		Scanner scan = new Scanner(arch);
		
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
	        String[] partes = linea.split(";");
	        
	        int numeroGimnasio = Integer.parseInt(partes[0]);
	        String lider = partes[1];
	        String estado = partes[2];
	        int cantidadPokemons = Integer.parseInt(partes[3]);
	        ArrayList<Pokemon> pokemons = new ArrayList<>();
	        
	        for (int i = 4; i < 4 + cantidadPokemons; i++) {
	            String nombrePokemon = partes[i];
	            for (Pokemon poke : pokedex) {
	                if (poke.getNombrePokemon().equals(nombrePokemon)) {
	                    pokemons.add(poke);  
	                    break;
	                }
	            }
	        }

	        

	        Gimnasio g = new Gimnasio(numeroGimnasio, lider, estado, pokemons);
	        gimnasios.add(g);
			
		}
	}
	
	public static Jugador leerRegistros() throws FileNotFoundException {
		File arch = new File("archivos/registros.txt");
		Scanner scan = new Scanner(arch);
		String primera = scan.nextLine();
		String[] user = primera.split(";");
		String nombre = user[0];
		int medallas = Integer.valueOf(user[1]);
		
		Jugador j = new Jugador(nombre, medallas);

		
		while (scan.hasNext()) {
			String linea = scan.nextLine();
			String[] partes = linea.split(";");
			String nombrePokemon = partes[0];
			String estado = partes[1];
			
			Pokemon p = null;
			for (Pokemon poke : pokedex) {
				if (poke.getNombrePokemon().equalsIgnoreCase(nombrePokemon)) {
					p = poke;
					break;
				}
			}
			
			if (p != null) {
	            Pokemon copia = new Pokemon(p.getNombrePokemon(), p.getHabitat(), p.getAparicion(), p.getVida(), p.getAtaque(), p.getDefensa(), p.getAtaqueEspecial(), p.getDefensaEspecial(), p.getVelocidad(), p.getTipo());
	            copia.setEstado(estado);
	            j.agregarPokemones(copia);
	        } else {
	            System.out.println("No se encontró el Pokémon '" + nombrePokemon + "' en la Pokedex");
	        }
			
		}
		scan.close();
		return j;
		
		
	}
	
	public static void leerHabitat() throws FileNotFoundException {
		File arch = new File("archivos/habitats.txt");
		Scanner scan = new Scanner(arch);
		
		while(scan.hasNextLine()) {
			String nombre = scan.nextLine();
			
			Habitat h = new Habitat(nombre);
			habitats.add(h);
		}
		scan.close();
	}
	
	public static void leerPokedex() throws FileNotFoundException {
		File arch = new File("archivos/Pokedex.txt");
		Scanner scan = new Scanner(arch);
		
		while(scan.hasNextLine()) {
			String linea = scan.nextLine();
			String[] partes = linea.split(";");
			String nombrePokemon = partes[0];
			String habitat = partes[1];
			double aparicion = Double.valueOf(partes[2]);
			int vida = Integer.valueOf(partes[3]);
			int ataque = Integer.valueOf(partes[4]);
			int defensa = Integer.valueOf(partes[5]);
			int ataqueEspecial = Integer.valueOf(partes[6]);
			int defensaEspecial = Integer.valueOf(partes[7]);
			int velocidad = Integer.valueOf(partes[8]);
			String tipo = partes[9];
			
			Pokemon p = new Pokemon(nombrePokemon,habitat,aparicion,vida,ataque,defensa,ataqueEspecial,defensaEspecial,velocidad,tipo);
			pokedex.add(p);
						
			
			
		}	
		scan.close();
	}

}
