package uniandes.dpoo.aerolinea.persistencia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;


public class PersistenciaAerolineaJson implements IPersistenciaAerolinea{

	public void salvarAerolinea(String archivo, Aerolinea aerolinea)
	{

		String jsonCompleto;
		try {
			jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		 IPersistenciaTiquetes pTiqJson;
			try {
				pTiqJson = CentralPersistencia.getPersistenciaTiquetes("JSON");
			} catch (TipoInvalidoException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			
		JSONArray jVuelos= new JSONArray( );
		JSONObject jobject = new JSONObject(jsonCompleto);
		
		for (Vuelo vuelo: aerolinea.getVuelos())
		{
			Ruta ruta=vuelo.getRuta();
			
            JSONObject jVuelo = new JSONObject( );
            
            jVuelo.put("Fecha", vuelo.getFecha());
            jVuelo.put("Avion", vuelo.getAvion().getNombre());
            jVuelo.put( "CodigoRuta", ruta.getCodigoRuta());
            jVuelo.put( "destino", ruta.getDestino().getNombre());
            jVuelo.put( "origen", ruta.getOrigen().getNombre());
            jVuelos.put( jVuelo );
	         
	        
		}
		
        jobject.put( "Vuelos", jVuelos );
        
        try {
			pTiqJson.salvarTiquetes(archivo, aerolinea);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
	}

	public void cargarAerolinea(String archivo, Aerolinea aerolinea)  
	{
		String jsonCompleto;
		JSONObject raiz;
        Aerolinea nuevaAerolinea = new Aerolinea( );
        
        IPersistenciaTiquetes pTiqJson;
		try {
			pTiqJson = CentralPersistencia.getPersistenciaTiquetes("JSON");
		} catch (TipoInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		try {
			jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
			raiz = new JSONObject( jsonCompleto );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		JSONArray jVuelos = raiz.getJSONArray("vuelos");
		        
        int numVuelos = jVuelos.length( );

        for (int i = 0; i < numVuelos; i++ )
        {
        	JSONObject vuelo = jVuelos.getJSONObject( i );
        	
            String codigoRuta = vuelo.getString( "CodigoRuta" );
            String fechaVuelo = vuelo.getString("Fecha" );

            nuevaAerolinea.registrarVueloRealizado(fechaVuelo, codigoRuta);
        }
        
        try {
			pTiqJson.cargarTiquetes(archivo, aerolinea);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InformacionInconsistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
     

       
	}

}
