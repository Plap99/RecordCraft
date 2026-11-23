# RecordCraft 1.0.0 - Complemento álamo (Minecraft 26.3)

Minecraft 26.3 incorpora el conjunto de madera de **álamo (poplar)**. Los tres colores de hojas pertenecen a la misma madera.

- Nuevos IDs: `vinylcraft:poplar_vinyl_player` y `vinylcraft:poplar_vinyl_recorder`.
- Registros de bloques/items, entidades de bloque, pestaña creativa y minería con hacha.
- Modelos, estados, recetas, avances de recetas en formato 26.3, botín y traducciones en-US/es-MX.
- Ambas variantes usan `minecraft:block/poplar_planks`.
- Versión del mod conservada en 1.0.0; no se alteraron los sistemas de música/portadas.

## Comprobación pendiente

Se validó la sintaxis de todos los JSON, pero no se pudo compilar Minecraft con Java 25 en este entorno.

En tu VS Code, ejecutar:

```powershell
.\gradlew.bat clean build
.\gradlew.bat runClient
```

Comprobar tocadiscos de álamo (abrir/cerrar y audio), grabador de álamo (REC), recetas con tablones de álamo y aparición en el inventario creativo.

**Importante:** Si ya se envió a CurseForge el JAR anterior, ese JAR NO contiene estas variantes. Se debe compilar un JAR actualizado y subirlo como un nuevo archivo o sustituirlo solo si la plataforma lo permite. Evita borrar etiquetas Git previas.
