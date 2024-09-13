package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public abstract class CalculadoraTarifas {
	
	public final double IMPUESTO=0.28;
	
	protected abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente);
	
	protected abstract double calcularPorcentajeDescuento(Cliente cliente);
	
	protected int calcularDistanciaVuelo(Ruta ruta)
	{
		int distancia;
		Aeropuerto Origen = ruta.getOrigen();
		Aeropuerto Destino = ruta.getDestino();
		
		distancia=Aeropuerto.calcularDistancia(Origen, Destino);
		
		return distancia;
	}
	
	protected int calcularValorImpuestos(int costoBase)
	{
		int impuesto;
		
		impuesto=(int) (costoBase*IMPUESTO);
		
		return impuesto;
	}
	
	public int calcularTarifa(Vuelo vuelo, Cliente cliente)
	{
		int tarifa;
		int costoBase = calcularCostoBase(vuelo, cliente);
		
		tarifa=(int) (costoBase-(costoBase*calcularPorcentajeDescuento(cliente))+ calcularValorImpuestos(costoBase));
				
		return tarifa;
	}

}
