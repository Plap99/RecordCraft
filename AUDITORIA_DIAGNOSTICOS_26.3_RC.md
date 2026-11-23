# Auditoría de diagnósticos de VS Code — VinylCraft 26.3 RC

Origen: archivo de diagnósticos compartido por el usuario. Estado: revisión estática + cambios conservadores; **compilación pendiente en Java 25**.

**476 diagnósticos**: 468 advertencias (severity 4), 8 informativos (severity 2). 66 archivos distintos. Estos son conteos del export anterior a las correcciones; **no** representan un nuevo escaneo.

## Distribución por código

| Código Eclipse JDT | Total | Descripción |
|---|---:|---|
| `536871898` | 196 | Conversión de valores a tipos no nulos en APIs externas |
| `67109781` | 120 | Parámetros de métodos heredados sin anotación explícita @NonNull |
| `536871867` | 58 | Conversión de genéricos con anotaciones de nulidad |
| `67109778` | 23 | Tipos de retorno de métodos heredados y anotaciones de nulidad |
| `536871864` | 15 | Acceso potencial a valor nulo |
| `67109822` | 14 | Referencias de método y contratos de nulidad |
| `536871868` | 14 | Incompatibilidad de anotaciones de nulidad en genéricos |
| `570425421` | 7 | Campos asignados pero no leídos |
| `67109824` | 6 | Referencias de método y retornos no nulos |
| `536871061` | 4 | Código considerado inalcanzable por el analizador |
| `67109782` | 3 | Parámetro heredado sin anotación explícita @Nullable |
| `536871865` | 3 | Expresión anulable donde se espera no nula |
| `536871895` | 3 | Anotaciones de nulidad en colecciones |
| `16778197` | 2 | Inferencia de nulidad al deserializar con Gson |
| `603979894` | 2 | Métodos de pruebas locales sin referencias |
| `0` | 2 | Gradle requiere sincronización |
| `536871362` | 1 | Marca TODO |
| `536870973` | 1 | Variable local sin uso |
| `16778127` | 1 | Identificador posiblemente nulo |
| `536871831` | 1 | getLevel() puede devolver null |

## Qué fue corregido

Se revisaron y corrigieron con cambios mínimos algunas lecturas repetidas de `Minecraft.player` y `BlockEntity.level`, donde el analizador pierde la garantía de no-nulidad, y una variable local sin uso; se reforzó la condición que asegura una portada no nula. No se cambiaron mecánicas, recetas, sonido ni renderizado.

- `src/main/java/com/radig/vinylcraft/block/entity/AlbumFrameBlockEntity.java`: Guardar referencia comprobada al mundo del mural.
- `src/main/java/com/radig/vinylcraft/block/entity/AlbumFrameBlockEntity.java`: Evitar acceso potencialmente nulo en actualización del mural.
- `src/main/java/com/radig/vinylcraft/block/entity/VinylPlayerBlockEntity.java`: Referencia local segura al retirar un vinilo.
- `src/main/java/com/radig/vinylcraft/block/entity/VinylPlayerBlockEntity.java`: Evitar acceso potencialmente nulo en actualización del reproductor.
- `src/main/java/com/radig/vinylcraft/block/entity/VinylRecorderBlockEntity.java`: Referencia local segura al retirar el vinilo del grabador.
- `src/main/java/com/radig/vinylcraft/block/entity/VinylRecorderBlockEntity.java`: Evitar mundo nulo al soltar vinilo del grabador.
- `src/main/java/com/radig/vinylcraft/block/entity/VinylRecorderBlockEntity.java`: Evitar acceso potencialmente nulo en actualización del grabador.
- `src/client/java/com/radig/vinylcraft/client/sound/VinylPlayerSoundManager.java`: Guardar referencia comprobada al jugador para distancia de audio.
- `src/client/java/com/radig/vinylcraft/client/render/PlaybackHudManager.java`: Comprobar jugador antes de generar fuentes HUD.
- `src/client/java/com/radig/vinylcraft/client/render/PlaybackHudManager.java`: Usar referencia comprobada del jugador en HUD.
- `src/client/java/com/radig/vinylcraft/client/recorder/VinylAlbumInfoScreen.java`: Referencia comprobada al mundo en pantalla de álbum.
- `src/client/java/com/radig/vinylcraft/client/discman/DiscmanScreen.java`: Referencia comprobada al jugador del Discman.
- `src/client/java/com/radig/vinylcraft/client/discman/DiscmanScreen.java`: Referencia comprobada al inventario del Discman.
- `src/client/java/com/radig/vinylcraft/client/library/MusicLibraryScreen.java`: Eliminar variable local sin uso.

## Pendientes que NO deben ignorarse a ciegas

- `MusicLibraryScreen.java:699`: reforzada la comprobación de `coverTexture` antes de dibujar; verificar portada grande y compacta dentro del juego.
- Comprobar posibles nulos en `BlockEntity` y casos de audio tras ejecutar en Minecraft.
- Revisar los dos `Gson.fromJson(...)`: el resultado puede ser `null`; **mantener** las comprobaciones de null aunque el analizador avise `Dead code`.
- Revisión futura de `@NonNull` heredados (120), conversiones de nulidad de librerías externas y genéricos. No recomendamos suprimir globalmente el análisis de null.
- Botones asignados y no consultados (7): podría ser legítimo porque se agregan a la GUI; no eliminarlos sin inspección funcional.

## Cómo validar

En VS Code con Java 25, ejecutar `./gradlew.bat clean build` desde PowerShell. Si se completa, `./gradlew.bat runClient` y verificar el HUD, Discman, mural, grabador y cambios de pantalla. Exportar nuevamente los diagnósticos para comparar cantidades; un número bajo no garantiza ausencia de bugs.

Si el panel muestra errores de Gradle adicionales, usar `Java: Clean Java Language Server Workspace` y recargar la configuración; no modificar el Java global de los demás mods.
