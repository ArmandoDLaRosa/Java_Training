package Entity;

import Entity.Vuelos;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-02-22T22:11:40", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Aviones.class)
public class Aviones_ { 

    public static volatile ListAttribute<Aviones, Vuelos> listaVuelos;
    public static volatile SingularAttribute<Aviones, String> aerolinea;
    public static volatile SingularAttribute<Aviones, Integer> capacidadDePasajeros;
    public static volatile SingularAttribute<Aviones, String> numeroDeAvion;
    public static volatile SingularAttribute<Aviones, Long> id;
    public static volatile SingularAttribute<Aviones, String> modelo;

}