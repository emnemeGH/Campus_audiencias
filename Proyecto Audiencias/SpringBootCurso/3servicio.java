// Servicio: El Service se encarga de implementar la lógica de negocio de la aplicación. Esta capa intermedia entre el Controlador y el Repository es fundamental en aplicaciones más complejas, ya que permite manejar transacciones, validaciones y otras reglas de negocio sin sobrecargar el Controlador. En términos de Spring Boot, el Service contiene los métodos que realizan operaciones en los Repositories y proporcionan los datos procesados al Controlador.

// ANOTACIONES DEL SERVICE

// @Service Se usa para marcar una clase como un servicio dentro de la arquitectura de Spring.

// @Autowired Se usa para inyectar dependencias de forma automática en un bean* gestionado por Spring.
// *Bean: Un bean es simplemente un objeto que Spring Boot crea y administra por ti. En lugar de que tú tengas que hacer new AutoridadService() en todas partes, Spring se encarga de crear y reutilizar ese objeto cuando lo necesites.
// Ejemplo: Imagínate que AutoridadService necesita usar AutoridadRepository. En lugar de hacer esto manualmente: AutoridadRepository autoridadRepository = new AutoridadRepository(); Spring lo hace por ti con @Autowired: 
// @Autowired  
// private AutoridadRepository autoridadRepository;
// Así, Spring ya sabe que debe darle un objeto listo para usar cuando lo necesites.

// @Transactional Se usa para manejar transacciones en métodos que interactúan con la base de datos. le dice a Spring Boot: "Asegúrate de que este método se ejecute completamente o no se haga nada si hay un error."


// EJEMPLO

// @Service
// public class UsuarioService extends BaseServiceImpl<Usuario, Integer>{
    
//     private final UsuarioRepository usuarioRepository;

//     @Autowired
//     public UsuarioService(UsuarioRepository usuarioRepository) {
//         this.usuarioRepository = usuarioRepository;
//     }

//     @Transactional
//     public void borradoLogico(Integer id) {
//         usuarioRepository.borrarLogico(id);  // Llama al repositorio para actualizar usr_estado
//     }
// }