// Repositorio: es la capa que se encarga de comunicarse con la base de datos. Spring Boot usa Spring Data JPA para hacer esto más fácil. El Repository permite:
// Guardar, actualizar, eliminar y buscar datos en la base de datos.
// Evitar escribir código SQL manualmente.
// Usar métodos listos para CRUD (Create, Read, Update, Delete).

// RELACION CONTROLADOR, SERVICIO Y RESPOSITORIO

// 1️⃣ Controller → Recibe las solicitudes del usuario (API o web).
// 2️⃣ Service → Contiene la lógica de negocio (procesa datos, valida, etc.).
// 3️⃣ Repository → Se encarga de acceder a la base de datos.

// Imagina que tu aplicación es un restaurante:
// 1️⃣ El cliente (usuario) hace un pedido → Controller
// 2️⃣ El mesero (Service) toma el pedido y lo envía a la cocina → Service
// 3️⃣ El cocinero (Repository) prepara la comida (datos en la BD) y la entrega → Repository
// 4️⃣ El mesero recoge la comida y se la da al cliente → Controller responde al usuario.

// ANOTACIONES DEL REPOSITORY

// @Repository	Marca esta interfaz como un repositorio de Spring.

// JpaRepository es una interfaz de Spring Data JPA que proporciona métodos listos para usar como:
// save() → Guarda o actualiza una entidad.
// findById(id) → Busca un registro por su ID.
// deleteById(id) → Borra un registro por ID.
// findAll() → Obtiene todos los registros.
// <Autoridad, Integer> → Le indica a JpaRepository: Que trabajará con la entidad Autoridad. Que la clave primaria (id) es de tipo Integer.

// @Transactional (Explicado en el service)

// @Modifying → Indica que el Query modifica datos. Se usa cuando el @Query modifica datos en la base de datos (ejemplo: UPDATE, DELETE). Sin @Modifying, Spring pensaría que este query solo recupera datos, pero aquí estamos actualizando la BD. Spring sabe que debe ejecutar este query como una modificación de datos.

// @Query Permite definir consultas personalizadas en JPQL (Java Persistence Query Language). 
// Diferencia con SQL: JPQL usa nombres de entidades y atributos (Autoridad, autEstado). SQL nativo usa nombres de tablas y columnas de la BD.
// En JPQL: UPDATE Autoridad u SET u.autEstado. Esto es mas parecido a como se trabaja en java
// En SQL: UPDATE tbl_autoridad SET aut_estado

// En :id, los dos puntos (:) indican que es un valor dinámico que se pasará cuando se ejecute el método. Spring sustituye :id por el valor real que reciba como parámetro en el método borrarLogico(Integer id).


// EJEMPLO

// @Repository

// public interface AutoridadRepository extends JpaRepository<Autoridad, Integer>{
    
//     List<Autoridad> findByAutEstadoTrue();

//     //Borrado lógico: Cambia usr_estado a false en la BD
//     @Transactional
//     @Modifying
//     @Query("UPDATE Autoridad u SET u.autEstado = false WHERE u.aut_id = :id")
//     void borrarLogico(Integer id);
// }
