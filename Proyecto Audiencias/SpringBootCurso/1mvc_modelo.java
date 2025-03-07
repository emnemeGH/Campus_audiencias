// QUE ES MVC

// En el contexto de Spring Boot, el patrón MVC (Modelo-Vista-Controlador) se utiliza para organizar las aplicaciones web, permitiendo separar la lógica de negocio, la interfaz de usuario y el control de las peticiones de manera estructurada. Ahora bien, en Spring Boot, aunque el patrón MVC sigue siendo el mismo en cuanto a su estructura, existen conceptos adicionales como Repository y Service que, aunque no forman parte del patrón MVC en sí, son fundamentales para la arquitectura de la aplicación. 

// QUE ES EL MODELO

// Modelo: El Modelo es la representación de los datos de la aplicación. En Spring Boot, las clases que representan entidades de datos generalmente se definen como entidades JPA (con anotaciones como @Entity), y estas clases se gestionan para la persistencia de datos en la base de datos. El Modelo puede tener atributos como los datos que se almacenan (por ejemplo, un User con name, email, etc.). Estas entidades de Modelo son las que se manipulan en los Repositories.

// ETIQUETAS DEL MODELO

// @Entity Esta anotación indica que la clase es una entidad JPA, es decir, una clase que representa una tabla en la base de datos. Spring Data JPA o cualquier otro framework JPA manejará esta clase para persistir los datos en la base de datos. Una clase anotada con @Entity está vinculada a una tabla de base de datos, y sus atributos corresponden a las columnas de esa tabla.

// @Id marca el campo que actúa como clave primaria en la tabla de base de datos.

// @GeneratedValue indica cómo se generará el valor de la clave primaria automáticamente. Esta anotación requiere que se especifique cómo se va a generar el valor de la clave primaria. El parámetro más común es strategy, y puedes usar valores como: GenerationType.IDENTITY: La base de datos se encarga de generar el valor, generalmente usando un autoincremento.

// @Data es una anotación de Lombok que genera automáticamente los métodos getters, setters, toString(), equals(), y hashCode() para los campos de la clase. Esto es útil porque evita tener que escribir manualmente estos métodos, lo que reduce el código repetitivo y hace que la clase sea más limpia. Tambien existen las anotaciones: @Getter y @Setter si no queres usar las otras que vienen con data

// @Column se usa para especificar detalles adicionales sobre las columnas de la tabla. Si no se proporciona, JPA utiliza los nombres de los atributos como nombres de las columnas de forma predeterminada. Sin embargo, puedes usar @Column para personalizar el nombre de la columna, su longitud, etc.

// @Temporal Si necesitas mapear tipos de datos como Date o Calendar en lugar de LocalDate, se usa la anotación @Temporal para especificar si quieres que JPA lo almacene como DATE, TIME, o TIMESTAMP.

// @Temporal(TemporalType.DATE)
// private Date eau_fecins;


// EJEMPLO

// @Entity
// @Data

// public class Audiencia_ext {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer eau_id;

//     @Column(name = "nombre_completo", length = 100))
//     private String eau_nombre;
//     private String eau_usrins;
//     private LocalDate eau_fecins;
//     private Integer eau_usrmod;
//     private LocalDate eau_fecmod;
//     private Boolean eau_estado;

//     @ManyToOne
//     @JoinColumn(name="aut_id")
//     private Autoridad aut_id;

//     @ManyToOne
//     @JoinColumn(name="aud_id")
//     private Audiencia aud_id;

// }



