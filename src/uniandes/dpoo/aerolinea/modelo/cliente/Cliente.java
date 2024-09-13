package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente {
	private List<Tiquete> tiquetesSinUsar;
	private List<Tiquete> tiquetesUsados;
	
	public Cliente() {
		
	}
	
	public String getTipoCliente()
	{
		return "";
	}
	
	public String getIdentificador()
	{
		return "";
	}
	
	public void agregarTiquete(Tiquete tiquete)
	{
		this.tiquetesSinUsar.add(tiquete);
	}
	
	public int calcularValorTotalTiquetes()
	{
		int total=0;
		Iterator<Tiquete> it = this.tiquetesSinUsar.iterator();
		
		while (it.hasNext())
		{
			total+= it.next().getTarifa();
		}
		
		return total;
	}
	
	public void usarTiquetes(Vuelo vuelo)
	{
		int indx=0;
		Iterator<Tiquete> it1 = this.tiquetesSinUsar.iterator();
		
		while (it1.hasNext())
		{
			if (it1.next().getVuelo().equals(vuelo))
			{
				it1.next().marcarComoUsado();
				this.tiquetesUsados.add(it1.next());
				this.tiquetesSinUsar.remove(indx);
			}
			indx++;
		}
		
		
	}
}
