# HexInverter

A simple Hex Inverter! It takes a list of either raw bytes, or user input, and inverts them! It then outputs the inverted bytes to console, and a .dat file
(This is mostly just me messing around with git, so it's a pretty simple project. if you can even call it a "project")

### Prerequisites

Java 7 Minimum

### Installing
Clone the repository, open a local shell in your repo and type build, or just use javac to compile the .java file
```
C:/HexInverter> build
C:/HexInverter> javac HexInverter.java
```

## Running the project

Open a local shell in the repo, and run using java in the command line
```
C:/HexInverter> java HexInverter -h [File Out] FF FF FF FF ...
C:/HexInverter> java HexInverter -sh [File Out] FF FF FF FF ...
C:/HexInverter> java HexInverter -vh [File Out] FF FF FF FF ...
C:/HexInverter> java HexInverter -svh [File Out] FF FF FF FF ...
C:/HexInverter> java HexInverter -f [File In] [File Out]
C:/HexInverter> java HexInverter -sf [File In] [File Out]
C:/HexInverter> java HexInverter -vf [File In] [File Out]
C:/HexInverter> java HexInverter -svf [File In] [File Out]
C:/HexInverter> java HexInverter -direct [Input String]
C:/HexInverter> java HexInverter -table
```
-h will take a list of bytes, and invert them. it must be in format of "01 02 03 04 05" with maximum byte of "FF"
-f will take an input file, and an output file, and invert the bytes, it will always treat the input file as ASCII characters. so "7f" in a example.txt will be parsed as "37 66"

Both -h and -f will print the input list, and the output list in the console, and write to a binary file of your choosing.
Appending a 's' to either -h (-sh), or -f (-sf), will put it in silent running, and not output to the console, but only to a file.
Appending a 's' to either -h (-sh), or -f (-sf), will put it in verbose output, and instead of inverting the bytes in file, will print the lists in a text file, "41" won't be printed as "A" in file, but "41" in file

Appending both 's' and 'v' to -h (-svh) or -f (-svf), will result in both a silent, and verbose output.

-direct will show you the exact ascii values of your string. "Hello World!" will be printed in console as "48 65 6c 6c 6f 20 57 6f 72 6c 64 21"

-table will print a simple ascii table to the screen


## Authors

* **D. C** - *Initial work* - [Carbolemons](https://github.com/Carbolemons) - Discord Carbolemons#0001
