# M6-Ac2
# Gestió d'Encàrrecs

Aquest programa en Java permet gestionar encàrrecs de clients, oferint la possibilitat de generar i mostrar fitxers amb la informació dels encàrrecs en diferents formats (albarà, CSV o binari).

## Funcionalitats principals

1. **Generar un nou encàrrec**:
   - L'usuari introdueix les dades del client (nom, telèfon i data).
   - Es permet afegir diversos articles a l'encàrrec, especificant la quantitat, unitats i el nom de cada article.
   - L'encàrrec es pot guardar en un fitxer amb un dels següents formats:
     - **Albarà (TXT)**: Un fitxer de text amb les dades del client i la llista d'articles.
     - **CSV**: Un fitxer CSV amb la informació separada per punts i coma.
     - **Binari**: Un fitxer binari amb les dades codificades.

2. **Mostrar un encàrrec existent**:
   - L'usuari pot seleccionar un fitxer guardat en format CSV o binari per visualitzar el seu contingut.
   - El programa mostrarà les dades del client i els articles associats.

3. **Sortir del programa**: Finalitza l'execució de l'aplicació.

## Estructura del programa

- **Classe `Sessio`**: Controla el flux principal del programa, permetent seleccionar accions (generar, mostrar encàrrecs o sortir).
- **Classe `encarrec`**: Gestiona la creació d'encàrrecs, l'emmagatzematge en fitxers i la visualització del contingut.
- **Classe `Article`**: Representa els articles que formen part d'un encàrrec, incloent la quantitat, unitat i nom.
## Autor

**Daniel Merida Cordero**, **DAM2A**.
