# Wonka Staff 
 
## Descripción
Prueba técnica realizada durante un proceso de selección [Test.pdf](https://github.com/jorgesanme/Wonka_Staff/blob/main/images/Napptillus_Android%20Developer.pdf)

Wonka Staff es una app que consume los datos desde la API  [Willy Wonka's Factory](https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/oompa-loompas). Donde se disponen de 20 paginas de datos en formato Json. Cada pagina dispone de los datos de 25 trabajadores identificados con un [id](https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/oompa-loompas/1)


## Explicación de la app:
Esta app muestra los datos más relevantes de los trabajadores de Willy Wonka en un RecycleView. Los mismos se pueden filtrar por genero y por la profesión.

<img src="https://github.com/jorgesanme/Wonka_Staff/blob/main/images/top_bar.png" width="400" height="50" />

El usuario dispone de un selector de paginas para navegar por cada una de ellas, extraer sus datos y mostrarlos en un RecycleView.  Desde este último se lanza una vista de detalles del trabajador. 


Para ello se ha usado:

- **Okhttp3**  => Punto de inicio de las llamadas a red.
- **Retrofit**  => Llamadas a red para descargar datos más simplificadas. La construción de la url de llamadas es + simple
- **Binding** => Eliminar código repetitivo en la vinculación de código con las views del xml.
- **KodeIn** => Inyeccion de dependecias.
- **Glide**  => Evitar parcear un stream de datos a Bitmap en la descarga de las imagenes desde http. Tiene Cache de imagen y aporta funcionalidad para convertir a Imagen circular. 
- **Coroutines** => Trabajar en Async en background para no bloquear el hilo main de la UI y evitar dependencia de RxJava.
- **Moshi** => Parceador de archivos JSon al modelo de datos. Modificar los nombres de variables que nos llegan desde modelo de red.
- **Lifecycle** => Crea más facilmente el viewModel y con ella desacoplar la lógica de la vista. Flujo de información en una sola dirección.   


## Imagenes de muestra
### Paginador   | Filtrar x genero |  Filtrar x profesión  | Detalles del trabajador|
<img src="https://github.com/jorgesanme/Wonka_Staff/blob/main/images/Wonka_Staff_1.gif" width="160" height="300" />|
<img src="https://github.com/jorgesanme/Wonka_Staff/blob/main/images/Wonka_Staff_2.gif" width="160" height="300" />|
<img src="https://github.com/jorgesanme/Wonka_Staff/blob/main/images/Wonka_Staff_3.gif" width="160" height="300" />|
<img src="https://github.com/jorgesanme/Wonka_Staff/blob/main/images/Wonka_Staff_4.gif" width="160" height="300" />

