package Main;

public class Pokemon {

	private String nombrePokemon;
	private String habitat;
	private double aparicion;
	private int vida;
	private int ataque;
	private int defensa;
	private int ataqueEspecial;
	private int defensaEspecial;
	private int velocidad;
	private String tipo;
	private String estado = null;


	public Pokemon(String nombrePokemon, String habitat, double aparicion, int vida, int ataque, int defensa,int ataqueEspecial, int defensaEspecial, int velocidad, String tipo) {
		this.nombrePokemon = nombrePokemon;
		this.habitat = habitat;
		this.aparicion = aparicion;
		this.vida = vida;
		this.ataque = ataque;
		this.defensa = defensa;
		this.ataqueEspecial = ataqueEspecial;
		this.defensaEspecial = defensaEspecial;
		this.velocidad = velocidad;
		this.tipo = tipo;
		
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getNombrePokemon() {
		return nombrePokemon;
	}


	public String getHabitat() {
		return habitat;
	}


	public double getAparicion() {
		return aparicion;
	}


	public int getVida() {
		return vida;
	}


	public int getAtaque() {
		return ataque;
	}


	public int getDefensa() {
		return defensa;
	}


	public int getAtaqueEspecial() {
		return ataqueEspecial;
	}


	public int getDefensaEspecial() {
		return defensaEspecial;
	}


	public int getVelocidad() {
		return velocidad;
	}


	public String getTipo() {
		return tipo;
	}
	
	
	

}
