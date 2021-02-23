package Entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-02-22T22:11:40", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile SingularAttribute<Usuarios, Long> id;
    public static volatile SingularAttribute<Usuarios, String> nombre;
    public static volatile SingularAttribute<Usuarios, String> email;
    public static volatile SingularAttribute<Usuarios, String> contraseña;
    public static volatile SingularAttribute<Usuarios, Integer> perfil;

}