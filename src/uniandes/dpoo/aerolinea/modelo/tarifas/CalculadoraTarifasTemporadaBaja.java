package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas{

	protected final int COSTO_POR_KM_NATURAL = 600;
	protected final int COSTO_POR_KM_CORPORATIVO= 900;
	protected final double DESCUENTO_PEQ=0.02;
	protected final double DESCUENTO_MEDIANAS=0.1;
	protected final double DESCUENTO_GRANDES=0.2;
	
	@Override
	public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		// TODO Auto-generated method stub
		int costoBase;
		
		if (cliente.getTipoCliente().equals("Natural"))
			costoBase=calcularDistanciaVuelo(vuelo.getRuta())*COSTO_POR_KM_NATURAL;
		else
			costoBase=calcularDistanciaVuelo(vuelo.getRuta())*COSTO_POR_KM_CORPORATIVO;
		
		return costoBase;
	}

	@Override
	public double calcularPorcentajeDescuento(Cliente cliente) {
		// TODO Auto-generated method stub
		double porcentaje;
		ClienteCorporativo ClienteCorp;
		
		if (cliente.getTipoCliente().equals("Natural"))
			porcentaje=0;
		else
		{
			ClienteCorp=(ClienteCorporativo)(cliente);
			if (ClienteCorp.getTamanoEmpresa()==1)
				porcentaje=DESCUENTO_GRANDES;
			else if (ClienteCorp.getTamanoEmpresa()==2)
				porcentaje=DESCUENTO_MEDIANAS;
			else
				porcentaje=DESCUENTO_PEQ;
		}
		
		return porcentaje;
	}
	

}
