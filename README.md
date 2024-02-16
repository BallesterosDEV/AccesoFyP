# Proyecto Acceso a Datos:
- Este proyecto está realizado en parejas, en nuestro caso, nuestra pareja está formada por Francisco Solano y Pablo Ballesteros, en conjunto, hemos realizado un proyecto en base al enunciado especificado. Antes de comenzar, decir que estamos usando la versión 20 de Java, y la versión 6 de Spring.

### Enunciado:
El trabajo consiste en crear una red social. Para ello debemos tener un
usuario que tenga un username, un email, una contraseña, una
descripción, una fecha de creación. Tanto el username como el email
deben ser únicos y no pueden repetirse. Un usuario podrá publicar
muchas publicaciones. Las publicaciones tendrán un autor, un texto, una
fecha de creación y una fecha de edición. Cómo es una red social, un
usuario puede seguir a muchos usuarios y un usuario puede ser seguido
por otros muchos usuarios.
Para aprobar, el alumno deberá cumplir los siguientes puntos:
- Tener el proyecto estructurado de manera apropiada.
- Crear una base de datos MySQL consistente que resuelva el
problema planteado. La contraseña del usuario debe estar cifrada
con Bcrypt.
- Desarrollar entidades para todas las tablas con las anotaciones
adecuadas, así como definir sus repositorios con los métodos
necesarios.
- Desarrollar los DTO que sean necesarios para evitar enviar
información sensible. La exposición de la contraseña implica
suspenso directo.
- Definir los servicios para cada entidad, desarrollando los métodos
que implementan la lógica de tu aplicación.
- Definir el/los controlador/es necesarios para tu API REST,
siguiendo una estructura de endpoints adecuada.

- Implementar Spring Security con JWT para evitar que usen la API
si no están logueados.
Y tener como mínimo los siguientes endpoints en su API:
- Registro (público)
- Login (público)
- Editar descripción (privado)
- Buscar usuario por username (público)
- Obtener todos los usuarios que sigue un usuario (privado)
- Obtener todos los usuarios que siguen a un usuario (privado)
- Obtener todas las publicaciones (privado)
- Obtener todas las publicaciones de un usuario (público)
- Obtener todas las publicaciones de los usuarios que sigues
(privado)
- Insertar publicación (privado)
- Editar publicación (privado)
- Borrar publicación (privado)

### Contexto General:
Hemos realizado un proyecto con Spring, dividiendo nuestra arquitectura por capas, de esta forma, cada capa se encargará de una función distinta. Todas las dependencias que hemos utilizado en el proyecto las puedes encontrar en el archivo `pom.xml`. Las propiedades que hemos establecido para controlar como se usa la bbdd se ven reflejada en el `application.properties` A través de las relaciones de Hibernate, las comodidades que ofrece Spring y los datos de nuestra BBDD MySQL hemos logrado completar este proyecto.


### Persistencia:
- La persistencia es la capa más baja de nuestro proyecto, se encargar de coger los datos de nuestra BBDD y pasarlos a Java, la persistencia está compuesta por los `Modelos` (Los modelos representan las entitdades de nuestra base de datos, en este caso `Publication`, `User` y `Rol`) y los `Repositorios` (Los repositorios contienen los métodos necesarios para realizar las diferentes peticiones y métodos CRUD).
- Para los modelos, utilizamos diferentes anotaciones para hacerle entender a Spring cómo queremos nuestras tablas y columnas, así como establecer las relaciones pertinentes:
![image](https://github.com/BallesterosDEV/AccesoFyP/assets/118269269/f21faa74-6668-42d5-bba5-d1f38670b5ec)

![image](https://github.com/BallesterosDEV/AccesoFyP/assets/118269269/e2f23814-5797-47ca-aa2c-f93860e6254a)

![image](https://github.com/BallesterosDEV/AccesoFyP/assets/118269269/e77a79f9-7936-419b-849d-1789b967c756)

- Para los repositorios, utilizamos Spring Data JPA, que automáticamente ya nos inyecta los métodos en nuestros repositorios, a su vez, también podemos definir métodos personalizados si cumplimos con la nomenclatura.

![image](https://github.com/BallesterosDEV/AccesoFyP/assets/118269269/94f8cb53-2f71-486b-9e5c-99d7f9b5bcbe)

## Servicios:
- Los servicios implementan toda la lógica de negocio, al aplicarle la anotación `@Autowired` a nuestro objeto de su respectiva clase `Repository`, obtenemos todos sus métodos (es lo que se conoce como inyección de dependencias), todo ello para obtener un bajo acoplamiento Por buenas prácticas, creamos una interfaz definiendo los métodos que posteriormente vaya a implementar nuestro servicio.

![image](https://github.com/BallesterosDEV/AccesoFyP/assets/118269269/6dfaedd6-d6a8-42ca-a1a2-411eb227940c)


![image](https://github.com/BallesterosDEV/AccesoFyP/assets/118269269/e7399d33-da0f-4047-943e-39a7bc14047e)

![image](https://github.com/BallesterosDEV/AccesoFyP/assets/118269269/b1e7139a-f163-4a05-9019-b555fcf567b0)

### Controladores:
- Los controladores literalmente controlan la respuesta a la petición http, devolviendo un status diferente según el éxito o no de la petición, funcionan como filtro y en ellos se definen las rutas por las que hacer las peticiones, así como los endpoints en general.

![image](https://github.com/BallesterosDEV/AccesoFyP/assets/118269269/a9862bd4-b7f7-47f5-8648-50fc8a2cb743)

### Data Transfer Objects
- Los DTO son clases javas que pretenden imitar a una entidad de nuestra base de datos, mostrando solo los datos que nos interesan. Un usuario puede tener un nombre, apellidos, correo y contraseña, pero si no queremos que se muestren todos esos datos sensibles, podríamos hacer un `UserDTO` para mostrar solo aquello que deseamos.

  ![image](https://github.com/BallesterosDEV/AccesoFyP/assets/118269269/c83ef2d9-3c7a-4a8a-b2df-54be38eaec73)

### Security

- Spring Security proporciona una seguridad adicional a nuestro proyecto, siendo necesario un token para loguearte en nuestra aplicación, clasificando las peticiones como públicas o privadas. Concretamente, la mayoría de peticiones requieren que el usuario esté logueado, todo eso lo configuramos en el `FilterChain`:

![image](https://github.com/BallesterosDEV/AccesoFyP/assets/118269269/a91926cb-d2bb-44e7-9e2c-6e8931e8f314)

- El token se obtiene gracias a `JWT`, lo que nos permite ingresar a las peticiones privadas, reforzando así la seguridad:
![image](https://github.com/BallesterosDEV/AccesoFyP/assets/118269269/20c4bd10-b132-4cd1-8e7b-4eea40f5774b)







