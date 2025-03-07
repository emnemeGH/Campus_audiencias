// QUE ES Spring Boot 

// Spring Boot es un framework de desarrollo que facilita la creación de aplicaciones Java con una configuración mínima

// Spring Data JPA es el módulo de Spring que se basa en JPA y proporciona abstracciones para trabajar con bases de datos de manera fácil

// QUE SON LAS DEPENDENCIAS

// Las dependencias en Java son bibliotecas o recursos externos que tu proyecto necesita para funcionar correctamente. Estas se especifican en el archivo de configuración del proyecto (como el pom.xml)

// QUE ES MAVEN

// Maven es una herramienta gestor de dependencias y una herramienta de construcción para proyectos Java. Con Maven, gestionas las dependencias necesarias para tu proyecto (como Spring Boot, JPA, y Hibernate). Dependencias en pom.xml: Maven te permite añadir dependencias como spring-boot-starter-data-jpa y otras bibliotecas necesarias (como el conector de la base de datos y Hibernate). Descargar dependencias: Cuando ejecutas comandos como mvn clean install, Maven descargará automáticamente las dependencias especificadas en tu pom.xml (por ejemplo, Hibernate, Spring Data JPA, etc.). Maven también se encarga de compilar el código fuente y empaquetarlo en un formato específico

// QUE ES JPA

// JPA (Java Persistence API, persistence porque permite alamcenar datos persistentemente en una base) es una especificación* de Java te ayuda a gestionar datos en bases de datos utilizando objetos Java, eliminando la necesidad de escribir SQL manualmente, y haciendo que el trabajo con bases de datos sea mucho más fácil y limpio.

//*Especificacion: define un conjunto de interfaces, anotaciones y comportamientos que los desarrolladores deben seguir para trabajar con bases de datos de manera consistente en Java.

// QUE ES HIBERNATE

// JPA define el contrato, es decir, las reglas sobre cómo debe funcionar la persistencia en Java. Hibernate es una implementación de JPA que se encarga de hacer las operaciones reales. Es decir, convierte tus operaciones de objetos Java en consultas SQL, maneja las conexiones con la base de datos, y realiza automáticamente las operaciones CRUD sin necesidad de que escribas SQL manualmente.

// Ejemplo práctico de JPA:
// JPA Define el Contrato:

// Usas JPA para definir entidades, como Producto, y sus relaciones con la base de datos.
// JPA te permite definir cómo interactuar con esas entidades a través de anotaciones como @Entity, @Id, etc.
// Código de entidad con JPA:

// @Entity
// public class Producto {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     @Column(name = "nombre")
//     private String nombre;
    
//     @Column(name = "precio")
//     private Double precio;

    // Getters y setters
// }
// Hibernate (o cualquier implementación JPA) hace el trabajo real:

// Hibernate o la implementación que estés usando se encarga de leer las clases de entidad, crear las tablas en la base de datos (si se configura así) y ejecutar las consultas SQL necesarias para realizar las operaciones CRUD sobre esas entidades.
// Si quieres guardar un objeto en la base de datos, Hibernate es el que va a generar el SQL correspondiente (INSERT INTO productos...) y ejecutarlo.
// Código de uso con JPA:

// Producto producto = new Producto();
// producto.setNombre("Laptop");
// producto.setPrecio(1000.0);

// entityManager.persist(producto);  // Hibernate genera el SQL INSERT