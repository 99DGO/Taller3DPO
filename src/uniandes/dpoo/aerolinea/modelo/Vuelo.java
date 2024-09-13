package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Vuelo {
	private String fecha;
	private Avion avion;
	private Ruta ruta;
	private Map<String, Tiquete> tiquetes;
	
	
	public Vuelo(Ruta ruta, String fecha, Avion avion) {
		this.fecha = fecha;
		this.avion = avion;
		this.ruta = ruta;
	}
	
	public String getFecha() {
		return fecha;
	}
	public Avion getAvion() {
		return avion;
	}
	public Ruta getRuta() {
		return ruta;
	}
	public Collection<Tiquete> getTiquetes() {
		return tiquetes.values();
	}
	
	public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad)
	{
		int total=0;
		
		for (int i=0; i<=cantidad; i++)
		{
			int tarifa=calculadora.calcularTarifa(this, cliente);
			
			Tiquete tiquete= GeneradorTiquetes.generarTiquete(this, cliente, tarifa);
			
			this.tiquetes.put(tiquete.getCodigo(), tiquete);
			
			cliente.agregarTiquete(tiquete);
			
			total+=tarifa;
			
		}
		
		return total;
	}
	
	public boolean equals (Object obj)
	{
		if (obj instanceof Vuelo )
		{
			boolean igual = false;
			Vuelo otroVuelo = (Vuelo) obj;
			
			if (otroVuelo.getFecha().equals(this.fecha))
			{
				if (otroVuelo.getRuta().equals(this.ruta))
				{
					if (otroVuelo.getAvion().equals(this.avion))
					{
						igual=true;
					}
				}
			}
			
			return igual;
			
		}
		else
		{
			return obj.equals(this);
		}
	}
	

}
