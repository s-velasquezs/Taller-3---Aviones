package uniandes.dpoo.aerolinea.consola;

import java.io.IOException;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.persistencia.CentralPersistencia;
import uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException;

public class ConsolaArerolinea extends ConsolaBasica
{
    private Aerolinea unaAerolinea;

    /**
     * Es un método que corre la aplicación y realmente no hace nada interesante: sólo muestra cómo se podría utilizar la clase Aerolínea para hacer pruebas.
     */
    public void correrAplicacion( )
    {
        try
        {
            unaAerolinea = new Aerolinea( );
            // Primero se debe cargar la información base de la aerolínea (aeropuertos, aviones, rutas, vuelos)
            unaAerolinea.cargarAerolinea( "./datos/aerolinea.json", CentralPersistencia.JSON );
            // String archivo = this.pedirCadenaAlUsuario( "Digite el nombre del archivo json con la información de una aerolinea" );
            String archivo = "tiquetes.json"; 
            unaAerolinea.cargarTiquetes( "./datos/" + archivo, CentralPersistencia.JSON );

            // A partir de aquí solo son impresiones de verificación, no hacen parte del taller como tal
            System.out.println( "Carga exitosa. Resumen de la aerolínea:" );
            System.out.println( "  Aeropuertos/rutas: " + unaAerolinea.getRutas( ).size( ) );
            System.out.println( "  Aviones: " + unaAerolinea.getAviones( ).size( ) );
            System.out.println( "  Vuelos: " + unaAerolinea.getVuelos( ).size( ) );
            System.out.println( "  Clientes: " + unaAerolinea.getClientes( ).size( ) );
            System.out.println( "  Tiquetes: " + unaAerolinea.getTiquetes( ).size( ) );
        }
        catch( TipoInvalidoException e )
        {
            e.printStackTrace( );
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
        catch( InformacionInconsistenteException e )
        {
            e.printStackTrace();
        }
    }

    public static void main( String[] args )
    {
        ConsolaArerolinea ca = new ConsolaArerolinea( );
        ca.correrAplicacion( );
    }
}