# HTML, CSS, JavaScript y accesibilidad

En esta parte del tutorial haremos hincapié en las tecnologías empleadas en las diferentes páginas que componen Vivac, centrándonos en los 2 aspectos principales, HTML y CSS y explicando que utilidad hemos encontrado para JavaScript así como ha sido el trabajo con respecto a la accesibilidad.

---

### index.html

Esta página es la principal del sitio web en la que encontramos un banner principal animándonos a interactuar con el con el objetivo de generar interés por la página.

**HTML**

Nos encontramos con un *header* en la parte superior en el que mostramos el logo de la página y tras el que observamos un elemento *hr* para crear un espacio diferente en el que encontraremos un banner compuesto de un un par de elementos de tipo bloque seguidos de un boton que nos envía a **salas.html**. Si continuamos hacia abajo encontramos un footer compuesto en su parte superior por el logo de la página seguido de un logo de *copyright* a la izquierda de dos imágenes de descarga de la APP en las plataformas mas conocidas pues sabemos que posteriormente programaremos con Kotlin y por ello queríamos incluirlo en la página. Debajo de esta primera parte encontramos otro elemento *hr* que nos divide lo superior de lo inferior, un elemento tipo *ul* mostrado de forma horizontal que nos lleva a otros lugares de la página de forma mas rapida.

**CSS**

Con respecto a la estructura del CSS empleamos *media queries* para crear diferentes estilos dependiendo de la plataforma aunque es cierto que no sería necesario al menos en nuestro caso pues prácticamente todos los elementos se adaptan bien gracias al uso de *flexbox.* Las *media queries* se encargan de mover los márgenes de algunos elementos con el objetivo de que todo sea igual de legible que en las versiones para mayores resoluciones así como un compactamiento en el menú inferior que se mantiene en todos los *footers* de la web.

Encontramos en algunos casos la etiqueta *text-decoration: none* pues empleamos elementos tipo *a* para crear enlaces pero no deseamos que esten subrayados. 

---

### salas.html

**HTML**

Para mantener la uniformidad durante toda la web, mantenemos el mismo header y footer que en el index. Esta vez, introducimos un <a> con el título “lista de sitios” para indicar que nos encontramos ante una lista de los lugares en lo que se puede reservar una acampada. mantenemos la línea haciendo uso de <hr> por estética. Justo después, comienzan a aparecer las tarjetas con imágenes y descripciones de los lugares. Tuvimos que envolver todas las tarjetas en un nuevo div para manejar mejor su posición en la web y su alineación entre ellas. Además, cada tarjeta se envuelve en un <a> para que tanto el texto de cada tarjeta como su imagen sirva como botón de enlace a su respectiva página de reserva. 

**CSS**

El CSS supuso el gran reto de esta página, aunque su apariencia sea sencilla. Para empezar, heredamos clases de bootstrap para poder utilizar unas tarjetas ya diseñadas en las que indicar los lugares de acampada. Esto generó un problema de inmediato: Algunos elementos del css heredado de bootstrap se solapan con los creados por nosotros hasta ahora. Para solucionar el problema, utilizamos la herramienta para desarrolladores del navegador de chrome cuando consultábamos el estado de la web en localhost, para obtener el css concrecto de las tarjetas. Creamos el archivo salas.css e introducimos sólo las clases necesarias de bootstrap. Además, así podíamos modificar las tarjetas para personalizarlas con más detalle. Al final, bootstrap fue una guía inicial para crear nuestra propia tarjeta. 

La siguiente gran dificultad fue conseguir el alineamiento horizontal y vertical en la web, un principio fundamental en el diseño. Utilizamos max-width en header y footer, y propiedades como display:flex y justify-content: space-between en el div que envuelve a todas las tarjetas. Para terminar, controlamos el espacio vacío entre tarjetas con margin-right. además, las tarjetas tienen un display: flex para que se agrupen según el tamaño de página, y el tamaño de las tarjetas se controlan desde la clase card-img con height y width.

Por otro lado, el footer se encuentra en modo sticky en esta página. Añadimos un margin-bottom en el div que envuelve a las tarjetas para que cuando se llegue en la navegación al final de la página, el footer no solape el contenido más abajo de las tarjetas.

Por último, utilizamos una media query para la ubicación de las tarjetas de manera responsiva. Las tarjetas ya eran capaces de reubicarse en función a el ancho de la web, aunque el tamaño de las mismas se mantenía igual y surgía un problema con la ubicación de la primera tarjeta cuando la web se hacía más estrecha. 

Cuando la web se encuentra en su ancho máximo, utilizamos justify-content: space-between para alinear verticalmente las tarjetas con el header y footer. Esto generaba un problema cuando sólo caben dos o una tarjeta porque el ancho cambie. La primera tarjeta quedaba siempre en el lado izquierdo de la web, y la segunda al final. Para solucionar esto, utilizamos una media query que, a partir del ancho en el que sólo caben dos tarjetas, centrara las mismas con justify-content: space-around. También aprovechamos para agrandar el tamaño del texto y las imágenes en sí, para que destacasen más y ocuparan más espacio cuando fueran menos.

---

### reserva.html

Esta página servirá como una plantilla para los diferentes sitios que se mostrarán en **salas.html**. En esta encontrará el usuario todo lo necesario para realizar una reserva del sitio que desee.

**HTML**

Al igual que en el resto de las páginas, encontraremos un *header* y un *footer* que se mantendrá, agregando cohesión y sentido a la página. Sin embargo en esta concretamente tendremos una gran foto a la izquierda y a la derecha información y algunos formularios. Centrándonos en el lado derecho, encontramos el nombre del sitio, seguido de una pequeña descripción y unos formularios. En primer lugar encontramos un selector de la fecha, a continuación dos selectores, de hora inicial y hora final y un *slider* para el número de personas.

**CSS**

El CSS en este caso nos ayuda a colocar los diferentes elementos en los espacios que deseamos al emplear *flexbox* así como a establecer los colores corporativos en los componentes como son los formularios o los botones. Así mismo, nos ayuda a que el tamaño de los formularios y el *slider* sea el del contenedor padre. ****Las *media queries* juegan un papel importante para que el texto del elemento *aside* se muestre de una forma mas estilosa al centrarse justo debajo de la foto en el momento en el que la pantalla ve reducido su tamaño.

**JavaScript**

Con respecto al JavaScript, encontramos algunos que nos ayudan a establecer los límites de los formularios, ayudándonos a que el usuario no pueda elegir fechas anterior a la del sistema así como comprobar que la hora inicial no sea superior a la hora final. Además, por estética hemos establecido que el slider solo muestre el número del selector cuando sea actualizado, añadiendo una funcionalidad más, aunque mínima, a la página.

---

### conocenos.html

**HTML**

El conocenos es la página más simple de la web. De nuevo mantenemos el header y el footer para la uniformidad, y de nuevo utilizamos tarjetas para colocar la información sobre nosotros. Eso sí, las tarjetas poseen características de estilo diferentes a las que utilizamos para los lugares de acampada. Desde el prototipado lo decidimos así.

**CSS**

Partimos de la base de las demás hojas de CSS, pero las tarjetas si fueron modificadas. Decidimos que la tarjeta completa tuviera un fondo y contorno que envuelve texto e imagen, a diferencia de en *salas.html.* En salas, nos interesaba que las imágenes fueran más aún el centro de atención. Aquí nos interesa resaltar también el texto, que habla sobre nosotros. Por supuesto, utilizamos un naranja acorde con la gama de colores de la web. Las tarjetas siguen siendo responsivas, permanecen centradas en la web pero se alargan para el que texto se mantenga dentro. La imagen se estrecha dejando el centro de la misma siempre visible.

---

### Accesibilidad

Con respecto a la accesibilidad el criterio que hemos escogido ha sido el siguiente:

[https://www.w3.org/WAI/WCAG21/Understanding/language-of-parts.html](https://www.w3.org/WAI/WCAG21/Understanding/language-of-parts.html)

Este criterio se basa en proporcionar una forma identificativa de establecer el idioma de la página web y lo hemos hecho a través de la etiqueta *lang* del elemento *head* de los archivos *html.* El criterio a su vez exige que no se cambie de forma repentina el idioma a lo largo de la página web y es algo que conseguimos sin lugar a duda.

Con respecto a la accesibilidad en general hemos optado por textos grandes, con gran contraste con el resto de elementos así como el uso de una estructura sencilla y fácilmente navegable por las diferentes herramientas que pudieran usarse durante el recorrido de la página.

Mas allá de estos cambios también hemos realizado otros como son envolver los elementos *li* en uno *ul,* colocar texto alternativo a las imágenes, etiquetas a los distintos formularios, en nuestro caso hemos empleado *aria-labels,* además de establecer un correcto uso de *headers* sin obviar niveles.

También hicimos uso del análisis lighthouse de google para evaluar nuestra página web. Obtuvimos una calificación de 70 puntos en algunas páginas con el primer análisis. Hemos sido capaces de elevar la calificación hasta 91 puntos o más, según la página, logrando 100 puntos en accesibilidad.