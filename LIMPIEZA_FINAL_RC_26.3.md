# Limpieza final de advertencias — VinylCraft 26.3 RC

A partir de la RC con ajustes seguros previamente compartida:

- Se eliminaron **7 campos de botones** nunca consultados después de crearse. Los widgets se siguen registrando con `addRenderableWidget` y mantienen exactamente sus callbacks.
- Se conservaron los métodos de prueba manual `testMusicLibraryScanner()` y `testAudioMetadata()`. Se anotaron individualmente con `@SuppressWarnings("unused")`, dado que son herramientas de depuración intencionalmente no invocadas en producción. No se ha desactivado globalmente la detección de código sin usar.
- La configuración `.vscode/settings.json` incluye el análisis extra de nulidad desactivado, igual que en la última captura; esto **no corrige por sí mismo riesgos de nulos** ni modifica Gradle.

En el panel Problems de la última captura había 9 advertencias y 3 informativos. Con las correcciones anteriores, las 9 advertencias específicas deberían desaparecer tras refrescar el proyecto. Los 3 mensajes informativos son un comentario TODO y dos avisos de sincronizar Gradle; no son errores.

**Validación pendiente:** Compilar en Windows con el JDK 25 configurado y probar `runClient` antes de etiquetar/publicar. Este paquete no se recompiló aquí (el entorno disponible tiene Java 21).
