package Entity;

import Entity.Estados;
import Entity.Vuelos;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-02-22T22:11:40", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Ciudades.class)
public class Ciudades_ { 

    public static volatile SingularAttribute<Ciudades, Estados> estado;
    public static volatile ListAttribute<Ciudades, Vuelos> listaDestinos;
    public static volatile SingularAttribute<Ciudades, Long> id;
    public static volatile ListAttribute<Ciudades, Vuelos> listaOrigenes;
    public static volatile SingularAttribute<Ciudades, String> nombre;

}