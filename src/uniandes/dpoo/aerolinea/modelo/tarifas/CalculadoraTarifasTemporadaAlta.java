package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas
{
	public final int COSTO_POR_KM=1000;
	
	@Override
	public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		// TODO Auto-generated method stub
		int costoBase;
		costoBase=calcularDistanciaVuelo(vuelo.getRuta())*COSTO_POR_KM;
		
		return costoBase;
	}

	@Override
	public double calcularPorcentajeDescuento(Cliente cliente) {
		// TODO Auto-generated method stub
		return 0;
	}
	


}
