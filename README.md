# Wonka Staff 
 
## Descripción
Prueba técnica realizada para una empresa [Test.pdf](https://github.com/jorgesanme/Wonka_Staff/blob/main/images/Napptillus_Android%20Developer.pdf)

Wonka Staff es una app que consume los datos desde la API  [Willy Wonka Factory](https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/oompa-loompas). Donde se disponen de 20 paginas de datos. Cada pagina dispone de los datos de 25 trabajadores identificados con un id unico.


## Explicación de la app:
Esta app muestra los datos más relevantes de los trabajadores de Willy Wonka en un RecycleView. Los mismos se pueden filtrar por genero y por la profesión.

<img src="https://github.com/jorgesanme/Wonka_Staff/blob/main/images/top_bar.png" width="400" height="50" />

El usuario dispone de un selector de paginas para navegar por cada una de ellas, extraer sus datos y mostrarlos en un RecycleView.  Desde este último se lanza una vista de detalles del trabajador. 


Para ello se ha usado:

- **Okhttp3**  => inicio de las llamadas a red
- **Retrofit**  => llamadas a red para descargar datos más simplificadas
- **Binding** => para vincular las view
- **Glide**  => carga las imagenes desde http
- **Coroutines** => Trabajar en Async para no bloquear el hilo main de la UI.
- **Moshi** => parceador de archivos JSon a modelos de red.
- **Lifecycle** => Crea más facilmente el viewModel  


## Imagenes de muestra
### Paginador   | Filtrar por genero |  Filtrar por profesion  | Detalles del trabajador|
<img src="https://github.com/jorgesanme/Wonka_Staff/blob/main/images/Wonka_Staff_1.gif" width="160" height="300" />|
<img src="https://github.com/jorgesanme/Wonka_Staff/blob/main/images/Wonka_Staff_2.gif" width="160" height="300" />|
<img src="https://github.com/jorgesanme/Wonka_Staff/blob/main/images/Wonka_Staff_3.gif" width="160" height="300" />|
<img src="https://github.com/jorgesanme/Wonka_Staff/blob/main/images/Wonka_Staff_4.gif" width="160" height="300" />

