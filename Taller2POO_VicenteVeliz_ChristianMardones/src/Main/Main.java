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

        System.out.println("¡Bienvenido al juego de Pokémon!");
        System.out.println("1) Continuar");
        System.out.println("2) Nueva Partida");
        System.out.println("3) Salir");

        System.out.print("Elige una opción: ");
        int opcion = scanner.nextInt();

       switch (opcion) {
       		case 1:
       			leerRegistros();
				mostrarMenuJugador();
       			
       		case 2:
       			System.out.println("dou");
       	
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
	
	public static void mostrarMenuJugador() {
		Scanner scanner = new Scanner(System.in);

        System.out.println("Menu de opciones:");
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
        System.out.println("llego");
        
        switch (opcion) {
        	case 1:
        		System.out.println("dou");
        	case 2:
        	case 3:
        	case 4:
        	case 5:
        	case 6:
        	case 7:
        	case 8:
        		
    		default: 
    			System.out.println("opcion invalida");
                mostrarMenuJugador();
                break;

        	
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
	
	public static void leerRegistros() throws FileNotFoundException {
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
