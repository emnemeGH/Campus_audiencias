// Controlador: El Controlador es la parte de la aplicación que maneja las solicitudes del usuario. Se encarga de:
// Recibir peticiones HTTP (como cuando alguien visita una página o envía un formulario).
// Llamar al Service para procesar la solicitud.
// Devolver una respuesta, que puede ser una vista web (HTML) o datos en formato JSON (para APIs).
// El controlador NO accede a la base de datos directamente → Siempre usa el Service (autoridadService) para hacer operaciones.

// ANOTACIONES DEL CONTROLADOR

// @RestController → Define la clase como un controlador que maneja peticiones web.

// @RequestMapping("/autoridades") → Define la URL base* para las peticiones de este controlador.
// *Url base: las URLs de tu API o aplicación tienen la siguiente estructura: Parte fija (http://localhost:8080/) + URL Base + Endpoint (Ej: /{id})

// @GetMapping("/{id}") → Responde a una petición GET para obtener una autoridad por su id.

// @PostMapping → Responde a una petición POST para crear una nueva autoridad.

// @PathVariable → Parámetros en la URL. Se usa cuando quieres extraer datos directamente de la URL.
// Si el usuario hace una petición a: GET http://localhost:8080/autoridades/5. El valor 5 se extrae de la URL y se pasa como parámetro id. {id} en la URL es una variable de ruta (pathvariable). @PathVariable Integer id toma ese valor y lo usa en el método.

//  @RequestBody → Datos en el cuerpo de la solicitud. Se usa cuando quieres enviar datos en formato JSON en una petición POST o PUT.

// ResponseEntity Es una respuesta HTTP completa que Spring Boot usa para enviar datos desde el backend a quien hizo la petición (por ejemplo, un frontend en Angular). Nos permite: Enviar datos (como una lista de objetos, un mensaje, etc.) y Controlar el código de estado HTTP (como 200 OK, 404 Not Found, etc.).
// Ejemplo: Si no usáramos ResponseEntity, podríamos escribir:
// List<Audiencia_ext> lista = new ArrayList<>();
// return lista;

// Pero con ResponseEntity, lo envolvemos en una respuesta HTTP estructurada:
// return ResponseEntity.ok(lista);

// EJEMPLO

// @RestController
// @RequestMapping("/autoridades") // Ruta base para las peticiones
// public class AutoridadController {

//     private final AutoridadService autoridadService;

//     @Autowired
//     public AutoridadController(AutoridadService autoridadService) {
//         this.autoridadService = autoridadService;
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Autoridad> obtenerAutoridad(@PathVariable Integer id) {
//         Autoridad autoridad = autoridadService.obtenerPorId(id);
//         return ResponseEntity.ok(autoridad); // Devuelve la autoridad en formato JSON
//     }

//     @PostMapping
//     public ResponseEntity<Autoridad> crearAutoridad(@RequestBody Autoridad autoridad) {
//         Autoridad nuevaAutoridad = autoridadService.guardar(autoridad);
//         return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAutoridad);
//     }
// }









