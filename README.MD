# ATHON

Athon is a basic program that allow to modify an image applying a filter or a specific color palette.

![image](athon.jpg)

Current available filters :

- Greyscale
- Negative
- Sepia

Current available palettes :

 - Software
    - MS/IBM 16 colors
    - MS 20 colors
    - Apple Macintosh
    - Risc OS

- Apple II
    - Low res
    - High res

- CGA
    - Palette 0 Low
    - Palette 0 High
    - Palette 1 Low
    - Palette 1 High
    - Mode 5 Low
    - Mode 5 High
    
- Commodore
    - Vic 20 High res
    - Vic 20 Multicolor
    - C64"

- Game Boy
    - Original
    - Pocket
    
- Game Boy Color
    - $12
    - $B0
    - $79
    - $B8
    - $AD
    - $16
    - $17
    - $07
    - $BA
    - $05
    - $7C
    - $13
    
- Mattel
    - Aquarius
    - Intellivision
    
- Thomson TO7/70
    - Palette 1
    - Palette 2
    - Palette 3
    
- Others
    - Epoch Super Cassette Vision
    - Fairchild Channel F
    - Magnavox Odissey 2
    - Msx
    - Sinclair QL
    - Teletext
    - Thomson MO5
    
Current supported file formats :

- BMP
- GIF
- JPEG
- PNG
- TIFF
- WBMP

## Custom palette
Palettes are located in the file __"palettes.json"__ under the folder __"LIB"__ , you can edit the current palettes or add new ones.

To add a new entry insert a block like the following :
```
{
    "Name"      : "Test palette",
    "Palette"   : ["FF0000","00FF00","0000FF"]
}
```

To group one or more entries add the __"Section"__ parameter :
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
To create a jar file with the included __"MANIFEST.MF"__ file use :
```
jar cvfm athon.jar MANIFEST.MF -C BUILD . 
```
To run the jar file use :
```
java --add-opens=java.desktop/sun.awt.shell=ALL-UNNAMED -jar athon.jar
```

## Info
The program is written in Java by __atomfighter__ <theatomfighter@gmail.com> and __honneamise__ <honneamise@hotmail.com>.

To apply a palette to an image the program retrieve the closest color using the euclidean distance.

If the __"Dither"__ option is enabled the Floyd Steinberg dithering algo will be applied.


