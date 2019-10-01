# HexInverter

A simple Hex Inverter! It takes a list of either raw bytes, or user input, and inverts them! It then outputs the inverted bytes to console, and a .dat file
(This is mostly just me messing around with git, so it's a pretty simple project. if you can even call it a "project")

### Prerequisites

Java 7 Minimum

### Installing
Clone the repository, and Build using javac in the command line
```
javac HexInverter.java
```

## Running the project

Open a local shell in the repo, and run using java in the command line
```
java HexInverter -h FF FF FF FF ...
java HexInverter -f [File In] [File Out]
java HexInverter -table
```
-h will take a list of bytes, and invert them. it must be in format of "01 02 03 04 05" with maximum byte of "FF"
-f will take an input file, and an output file, and invert the bytes

Both -h and -f will print the input list, and the output list in the console, and write to a binary file. -h will write to HexInverted.dat

-table will print a simple ascii table to the screen


## Authors

* **D. C** - *Initial work* - [Carbolemons](https://github.com/Carbolemons) - Discord Carbolemons#0001
