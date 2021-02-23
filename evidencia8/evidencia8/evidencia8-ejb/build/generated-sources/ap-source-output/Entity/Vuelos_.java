package Entity;

import Entity.Aviones;
import Entity.Ciudades;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-02-22T22:11:40", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Vuelos.class)
public class Vuelos_ { 

    public static volatile SingularAttribute<Vuelos, Date> horaInicioDeVuelo;
    public static volatile SingularAttribute<Vuelos, Date> fechaInicioDeVuelo;
    public static volatile SingularAttribute<Vuelos, Integer> numeroDePasajeros;
    public static volatile SingularAttribute<Vuelos, Aviones> numeroDeAvion;
    public static volatile SingularAttribute<Vuelos, String> numeroDeVuelo;
    public static volatile SingularAttribute<Vuelos, Long> id;
    public static volatile SingularAttribute<Vuelos, Ciudades> origen;
    public static volatile SingularAttribute<Vuelos, Ciudades> destino;
    public static volatile SingularAttribute<Vuelos, Date> horaFinDeVuelo;
    public static volatile SingularAttribute<Vuelos, Date> fechaFinDeVuelo;

}