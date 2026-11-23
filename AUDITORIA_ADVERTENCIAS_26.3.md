# Auditoría inicial de advertencias — RecordCraft / VinylCraft 26.3

**Base:** archivo `VinylCraft 26_3 RC.zip` proporcionado por el autor.
**Alcance:** revisión estática conservadora; no se cambia la lógica del juego.

## Resultados confirmados

- **104 archivos Java** inspeccionados.
- **244 archivos JSON** del directorio `src` validados sintácticamente sin errores.
- **4 imports no usados** identificados por referencia textual y eliminados:
  - `AlbumFrameBlock.java`: `ModBlockEntities`
  - `VinylCraftClient.java`: `DiscmanData`
  - `VinylRecorderScreen.java`: `ArrayList`
  - `LocalTrackAudioPlayer.java`: `IOException`
- Búsqueda orientativa de campos `private` declarados pero no referenciados: **sin resultados evidentes**. Esta comprobación no sustituye al analizador Java.

## Advertencias de VS Code que aún requieren diagnóstico

El recuento de **472 advertencias** mostrado anteriormente en VS Code no viaja en el ZIP: el panel **Problems** se genera al analizar el proyecto en el equipo del autor. Sin su contenido no es posible clasificar ni corregir cada advertencia de manera rigurosa. Algunas podrían corresponder a la configuración de análisis de nulidad (`java.compile.nullAnalysis.mode = automatic`), pero eso **no está confirmado**.

**No** se desactivó el análisis de nulidad, ni se añadieron anotaciones `@SuppressWarnings` para esconder los mensajes.

## Compilación

No se ejecutó una compilación nueva de este paquete: el entorno de auditoría dispone de **JDK 21**, mientras que el proyecto requiere **JDK 25**, y no tiene salida de red para instalarlo. El mod original de esta RC ya fue probado en Minecraft por el autor; estas cuatro eliminaciones de imports no alteran su comportamiento.

En la PC con Java 25:

```powershell
.\gradlew.bat clean build --warning-mode all
.\gradlew.bat runClient
```

## Cómo obtener el diagnóstico exacto de VS Code

1. Abrir **View → Problems** (`Ctrl+Shift+M`).
2. Filtrar por advertencias de Java si hay otros tipos de diagnósticos.
3. Enfocar la lista, seleccionar todas las advertencias (`Ctrl+A`) y copiarlas (`Ctrl+C`); pegar el contenido en un archivo `.txt` y compartirlo. Si la interfaz no permite copiar todo, enviar capturas de los grupos expandidos o exportar el registro del servidor de Java.
4. Con esa lista es posible agrupar los mensajes repetidos, separar falsos positivos de defectos reales y corregirlos por lotes con cambios verificables.

## Alcance del paquete entregado

Contiene código fuente, recursos, configuración de Gradle y carpeta `.git` original para conservar el historial; omite cachés `.gradle`, salida de compilación `build`, directorio de desarrollo `run` y `bin` para evitar datos temporales y mundos de prueba.
