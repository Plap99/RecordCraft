# RecordCraft para Fabric 26.3

Esta copia del proyecto se migró desde Minecraft/Fabric 26.2 a 26.3.

## Cambios realizados

- Minecraft: `26.3`.
- Fabric Loader: `0.19.5` (sin cambios).
- Fabric API: `0.164.0+26.3`.
- Fabric Loom: `1.18.4`.
- Gradle Wrapper: `9.7.1`.
- `fabric.mod.json`: dependencia de Minecraft actualizada a `~26.3`.
- Eliminación de los codecs de bloques, retirados en Minecraft 26.3.
- Actualización de `Player.drop` con `Prediction.SERVER_ONLY`.
- Actualización de las llamadas de renderizado `PoseStack` y `submitModel`.
- Migración de F6 y F7 de GLFW a `InputConstants` (SDL).

Se conservaron el identificador `vinylcraft`, los paquetes Java y la versión interna
`1.0.0` para no alterar las referencias existentes de mundos y partidas. El nombre
visible del mod continúa siendo **RecordCraft**.

## Verificación

La tarea `gradlew build` terminó con **BUILD SUCCESSFUL** utilizando JDK 25.
Esta verificación cubre la compilación de código común y de cliente y la creación
del JAR. No equivale a una prueba interactiva dentro de Minecraft.

## Cómo probar en Windows

1. Crear o abrir un perfil de Minecraft **26.3** con Fabric Loader **0.19.5**.
2. Instalar Fabric API **0.164.0+26.3** en la carpeta `mods` del perfil.
3. Copiar el JAR `recordcraft-1.0.0.jar` generado por el proyecto a `mods`.
4. Quitar del mismo perfil cualquier JAR anterior de RecordCraft/VinylCraft para evitar duplicados.
5. Probar la carga del mundo y los bloques; abrir biblioteca (F7), opciones (F6),
   el grabador, tocadiscos y Discman; escuchar una pista y comprobar la portada.

Para recompilar el proyecto en Windows con JDK 25 instalado:

```powershell
.\gradlew.bat clean build
```

El resultado está en `build/libs/recordcraft-1.0.0.jar`.

No se publicaron cambios en GitHub, CurseForge ni Modrinth.
