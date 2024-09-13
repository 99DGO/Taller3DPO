package uniandes.dpoo.aerolinea.modelo.cliente;

public class ClienteNatural extends Cliente
{
	public final static String NATURAL="Natural";
	private String nombre;
	
	public ClienteNatural(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public String getTipoCliente()
	{
		return NATURAL;
	}


	public String getIdentificador() {
		return nombre;
	}
	

}