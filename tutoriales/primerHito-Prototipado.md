# Prototipado

### Datos básicos

Víctor Moreno Sola y Alejandro Sánchez Castillo

---

### Competidores

Nuestro proyecto consiste en el desarrollo de una página web que permita la reserva de sitios de acampada. Tras investigar webs con funcionalidad similar, hemos escogido [airbnb.com](http://airbnb.com) y [thedyrt.com](https://thedyrt.com/) **como competidores potenciales. Destacamos dos fortalezas y dos debilidades en cada uno de los competidores:

|  | Airbnb | TheDyrt |
| --- | --- | --- |
| Fortalezas | Uso de tarjetas y sencillez | Página de inicio y mapa de ubicación |
| Debilidades | Hay que hacer mucho scroll hasta las fechas libres y hay muchos iconos en la barra superior | No muestra fotos de primera en los lugares, menú desplegable con ciudades mas buscadas muy grande. |

---

### Design system

![branding.png](Prototipado%20bbb28d91b9d445f9855790064dae9fd7/branding.png)

En cuanto al *design system,* nos centraremos en tres elementos: el la fuente del texto, la gama de colores y las tarjetas con imágenes. Utilizamos figma como herramienta para el prototipado.
En lo referente a los textos, hemos escogido Helvetica por ser sencilla y perfectamente legible en cualquier tamaño. Para títulos y textos importantes usaremos Helvetica bold, con la intención de que destaque sobre el resto de párrafos tanto en tamaño como en forma. Mantendremos el formato a lo largo de todas las páginas de la web.
El segundo elemento clave es la gama de colores. Escogemos tonalidades que recuerdan a la naturaleza y todo lo referente al ámbito rural. Corríamos el riesgo de que la web fuera poco llamativa en algunas ramas con marrones claros y verdes, así que escogimos una que también incluyera el naranja (FF7B54) para que también fuera ciertamente luminosa. El resultado puede comprobarse en la captura.

Por último, utilizaremos tarjetas inspiradas en la web [airbnb.com](http://airbnb.com) para mostrar imágenes sobre los lugares en los que acampar. Evidentemente, las tarjetas servirán como botón para acceder a los distintos sitios y poder realizar las reservas. Utilizaremos tarjetas porque lucen bien en la web, y son una forma simple de mostrar imágenes y un poco texto conjuntamente. Además, podremos navegar entre las fotos para mostrar varias fotografías en el mismo espacio. También sirven como botón para desplazarse a la página donde se realice la reserva, así que evitaremos colocar botones “clásicos” que pueden resultar menos estéticos.

---

### Estructura de la web

![index.html](Prototipado%20bbb28d91b9d445f9855790064dae9fd7/index.png)

index.html

El *header* y el *footer* se mantendrán en todas las páginas de la web para mantener la uniformidad de la página. El usuario podrá escribir en “¿Dónde vamos?” y pulsar enter para acceder a la página de salas (salas.html), donde podrá ver los espacios de acampada disponibles en ese lugar. En el footer también se encuentra el botón “Lista de lugares” que lleva a la página de salas. 

En cuanto a la estética, buscamos que sea simple y que tenga poco botones, para evitar el exceso de profundidad horizontal. Evitamos lo máximo posible la abundancia de texto, y colocamos la descripción principal de la web arriba para que sea lo primero que lean los usuarios.

![salas.html](Prototipado%20bbb28d91b9d445f9855790064dae9fd7/listaSitios.png)

salas.html

La página *salas.html* contendrá, principalmente, las tarjetas con los posibles lugares donde realizar una acampada. Servirán como botones para acceder a la página *reserva.html*, donde se lleva a cabo la reserva en una fecha determinada. Hemos decidido que el fondo sea blanco para lograr un buen contraste con las tarjetas, además de lograr un aspecto discreto que haga que la web parezca sencilla de manejar y entender. Los lugares cercanos estarán próximos entre sí y las fotografías son la información más importante que le llega al usuario. Por ello ocupan el cuerpo de la página al completo. Las tarjetas de los extremos estarán alineadas con los botones del header y del footer para cumplir con el principio de linealidad en toda la web y, por supuesto, todas las tarjetas tendrán el mismo tamaño, la misma fuente en el texto y la misma alineación horizontal y vertical.

![reserva.html](Prototipado%20bbb28d91b9d445f9855790064dae9fd7/reservaSitio.png)

reserva.html

Una vez el usuario decida un lugar donde realizar la acampada, accederá con un sólo click a la página *reserva.html*. De nuevo, las imágenes se mantienen con una gran importancia, aunque en este caso añadiremos un poco de información sobre el lugar en cuestión para que la gente tenga claro que desea realizar la reserva. Utilizaremos un formato de calendario para seleccionar el mes y el día, porque creemos que es la forma más intuitiva de realizar una reserva y ver qué días se encuentran disponibles y cuáles no lo están. El botón de *¡Reserva ya!* Se muestra en el primer golpe de vista, para que el usuario no se pierda en ningún momento en información menos importante. La disposición por columnas de las imágenes y el texto con el calendario hacen que la web sea refinada y los textos fáciles de leer. Nos surge la idea de colocar un carrusel de imágenes para que se puedan ver todas las fotografías de nuevo sin perjudicar la claridad de la página. Con este diseño, todo puede verse sin ni siquiera tener que navegar con la rueda del ratón. Los bordes son redondeados durante toda la web y las imágenes son igual de cuadradas para cumplir con el principio de repetición. El alineamiento se mantiene constante.

En total, pretendemos que el usuario quede a cuatro clicks (seleccionar ubicación, seleccionar lugar de acampada, seleccionar días disponibles y ¡Reserva ya!) de realizar una reserva.

![X.html, en nuestro caso un formulario de contacto](Prototipado%20bbb28d91b9d445f9855790064dae9fd7/contactanos.png)

X.html, en nuestro caso un formulario de contacto

Nuestra página opcional será *contacto.html*, una página en la que los usuarios podrán contactar con nosotros vía correo electrónico para resolver cualquier duda relacionada con la web. Creemos que basta con un dibujo y las cabeceras de los tres inputs para que el usuario entienda qué debe hacer. No es necesario añadir demasiado texto ni animaciones que distraigan al usuario, ni en esta página ni en las demás. La uniformidad en formas y colores se mantiene una vez más. Nos aseguraremos de que el color naranja de los placeholders sea lo suficientemente intenso como para que haya contraste y sea legible.

![X.html, en nuestro caso un “sobre nosotros”](Prototipado%20bbb28d91b9d445f9855790064dae9fd7/about.png)

X.html, en nuestro caso un “sobre nosotros”

Elegimos como página opcional *sobrenosotros.html,* una página con información de nosotros, los creadores de la web. Así podremos darnos a conocer y mostrar la información que queramos compartir sobre por qué realizamos el proyecto de la web. Las tarjetas serán similares a las de los lugares de acampada, pero un poco más grandes. Contendrán una breve descripción sobre nosotros y lo que pensamos sobre el proyecto.