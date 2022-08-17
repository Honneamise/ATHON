# ATHON

Athon is a basic program that allow to modify an image applying a specific color palette or filter.

![image](athon.jpg)

Current available palettes :
- CGA
- Others (Gameboy,etc...)

Current available filters :
- Greyscale
- Negative
- Sepia

Current supported file formats :
- BMP
- GIF
- JPEG
- PNG
- TIFF
- WBMP

## Custom palette
Palettes are located in the file __"palettes.json"__ under the folder __"LIB"__ , you can edit current palettes or add new ones.

To create a new palette add an entry like the following:
```
{
    "Section"   : "My palettes",
    "Name"      : "Test palette",
    "Palette"   : ["FF0000","00FF00","0000FF"]
}
```

## Build and run
To build the project use :
```
javac -cp "LIB/*" -d BUILD SRC/*.java
```
To run the project use :
```
java --add-opens=java.desktop/sun.awt.shell=ALL-UNNAMED -cp "BUILD;LIB/*" Athon
```

## Jar file creation
To create a jar file with the included MANIFEST.MF use :
```
jar cvfm athon.jar MANIFEST.MF -C BUILD . 
```
To run the jar file use :
```
java --add-opens=java.desktop/sun.awt.shell=ALL-UNNAMED -jar athon.jar
```

## Source code
The program is written in Java by atomfighter and honny
Included dependencies : 
- flatlaf-2.4.jar
- javax.json-1.0.jar

## Todo
- Add support for Netpbm file format
- Add more filters
- Add palette extractor with K-means

