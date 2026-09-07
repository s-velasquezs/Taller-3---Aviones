package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteNatural;

/**
 * Esta clase se utiliza para calcular las tarifas en temporada baja.
 * 
 * En temporada baja, los clientes que son personas naturales tienen una tarifa base diferente a la de los clientes corporativos.
 * 
 * Adicionalmente, los clientes corporativos tienen un descuento diferente según el tamaño (los clientes naturales no tienen descuento).
 */
public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas
{
    /**
     * El costo por kilómetro en temporada baja para personas naturales
     */
    protected final int COSTO_POR_KM_NATURAL = 600;

    /**
     * El costo por kilómetro en temporada baja para clientes corporativos
     */
    protected final int COSTO_POR_KM_CORPORATIVO = 900;

    /**
     * El descuento que se le puede aplicar a empresas pequeñas
     */
    protected final double DESCUENTO_PEQ = 0.02;

    /**
     * El descuento que se le puede aplicar a empresas medianas
     */
    protected final double DESCUENTO_MEDIANAS = 0.1;

    /**
     * El descuento que se le puede aplicar a empresas grandes
     */
    protected final double DESCUENTO_GRANDES = 0.2;

    /**
     * Calcula el costo base como COSTO_POR_KM x distancia.
     */
    @Override
    public int calcularCostoBase( Vuelo vuelo, Cliente cliente )
    {
        int distancia = calcularDistanciaVuelo( vuelo.getRuta( ) );
        int costoPorKm = ClienteNatural.NATURAL.equals( cliente.getTipoCliente( ) ) ? COSTO_POR_KM_NATURAL : COSTO_POR_KM_CORPORATIVO;
        return distancia * costoPorKm;
    }

    /**
     * Calcula el porcentaje de descuento que se le debería dar a un cliente dado su tipo y/o su historia.
     */
    @Override
    public double calcularPorcentajeDescuento( Cliente cliente )
    {
        if( cliente instanceof ClienteCorporativo )
        {
            ClienteCorporativo clienteCorporativo = ( ClienteCorporativo )cliente;
            switch( clienteCorporativo.getTamanoEmpresa( ) )
            {
                case ClienteCorporativo.PEQUENA:
                    return DESCUENTO_PEQ;
                case ClienteCorporativo.MEDIANA:
                    return DESCUENTO_MEDIANAS;
                case ClienteCorporativo.GRANDE:
                    return DESCUENTO_GRANDES;
            }
        }
        return 0;
    }

}
