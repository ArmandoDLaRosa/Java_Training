package Entity;

import Entity.Estados;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-02-22T22:11:40", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Paises.class)
public class Paises_ { 

    public static volatile ListAttribute<Paises, Estados> listaEstados;
    public static volatile SingularAttribute<Paises, Long> id;
    public static volatile SingularAttribute<Paises, String> nombre;

}