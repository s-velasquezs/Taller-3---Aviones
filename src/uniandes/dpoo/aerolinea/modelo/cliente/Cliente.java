package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

/**
 * Esta clase abstracta define e implementa los aspectos que son comunes para todos los tipos de clientes de la Aerolínea
 * 
 * Cada cliente, sin importar su tipo, tiene una lista de tiquetes usados y sin usar.
 */
public abstract class Cliente
{
    /**
     * La lista de tiquetes sin usar del cliente
     */
    private List<Tiquete> tiquetesSinUsar;

    /**
     * La lista de tiquetes usados del cliente
     */
    private List<Tiquete> tiquetesUsados;

    /**
     * Inicializa las listas de tiquetes del cliente.
     */
    public Cliente( )
    {
        tiquetesSinUsar = new ArrayList<Tiquete>( );
        tiquetesUsados = new ArrayList<Tiquete>( );
    }

    /**
     * Retorna el tipo del cliente.
     * @return Una cadena que identifica al tipo de cliente
     */
    public abstract String getTipoCliente( );

    /**
     * Retorna el identificador del cliente
     * @return Identificador del cliente que debería ser único
     */
    public abstract String getIdentificador( );

    /**
     * Agrega un nuevo tiquete a la lista de tiquetes (sin usar) que ha comprado el cleinte
     * @param tiquete El nuevo tiquete que se va a agregar
     */
    public void agregarTiquete( Tiquete tiquete )
    {
        this.tiquetesSinUsar.add( tiquete );
    }

    /**
     * Calcula el valor total de los tiquetes que ha comprado un cliente y todavía no ha usado
     * @return
     */
    public int calcularValorTotalTiquetes( )
    {
        int total = 0;
        for( Tiquete tiquete : tiquetesSinUsar )
            // Se verifica el estado del tiquete como una protección adicional: en el flujo normal de venta, la lista de tiquetes
            // sin usar sólo contiene tiquetes que no han sido usados, pero un tiquete cargado desde un archivo podría haber
            // quedado marcado como usado sin haberse movido explícitamente de lista.
            if( !tiquete.esUsado( ) )
                total += tiquete.getTarifa( );
        return total;
    }

    /**
     * Marca como usados todos los tiquetes del cliente qus se hayan realizado en el vuelo que llega por parámetro, moviéndolos de la lista de tiquetes sin usar a la lista de
     * tiquetes usados
     * @param vuelo El vuelo del que se usaron los tiquetes
     */
    public void usarTiquetes( Vuelo vuelo )
    {
        Iterator<Tiquete> iterador = tiquetesSinUsar.iterator( );
        while( iterador.hasNext( ) )
        {
            Tiquete tiquete = iterador.next( );
            if( tiquete.getVuelo( ).equals( vuelo ) )
            {
                tiquete.marcarComoUsado( );
                tiquetesUsados.add( tiquete );
                iterador.remove( );
            }
        }
    }

}
