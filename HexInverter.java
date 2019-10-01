import java.io.*;
import java.nio.file.*;

/*
*
* Simple Hex Inverter, 
* By: Carbolemons#0001 Discord
* https://github.com/Carbolemons
* 01.10.2019
*
*/

public class HexInverter {
	
	public static byte[] int_to_bytes(int data) { //convert integer into byte, shouldn't really even be larger than 255, or FF, but, go off?
			return new byte[] {(byte)(data & 0xFF)};
		}
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
		
		if(args.length != 0){	// if there are command line arguments
			System.out.println("Recieved Argument: " + args[0]);
			switch(args[0]){
				case "-h": //interpret hex list entered by command line, flip, and then print
					if(args.length == 2) { //invalid args, there would be no bytes in argument if there is only 2.
						System.out.println("No Bytes Detected. Aborting.");
						break;
					}
					System.out.println("Output File: " + args[1]);
					File hfile = new File(args[1]); //this is to delete pre-existing files with same name.
					hfile.delete();
					OutputStream os = new FileOutputStream(new File(args[1]), true); //create new file to output towards
					//blank strings to prepare to be concat'd
					String new_hex = "";
					String new_hex_console = "";
					String user_input_list = "";
					System.out.printf("Raw Input ASCII: ");
					for (int i = 2; i < args.length; i++) { //if a byte in the list is larger than 255, or FF, abort.
						if(Integer.valueOf(args[i], 16) > 0xFF) {
							System.out.printf("\nInvalid Byte Recieved: %x, Aborting. Element %d in list.\n", Integer.valueOf(args[i], 16) , i);
							break;
						};
						user_input_list = user_input_list + args[i] + " "; //concat user inputted byte into a string to show the command line
						int inverted_hex_as_decimal = 255 - Integer.valueOf(args[i], 16); //invert the byte by subtracting itself from 255, or FF
						System.out.printf("%s", (char) Integer.parseInt(args[i], 16)); //this is appended to "Raw Input ASCII: " on the console log
						new_hex = new_hex + Integer.toHexString(inverted_hex_as_decimal) + " "; //this is concat'd to show the inverted list on command screen
						new_hex_console = new_hex_console + (char)inverted_hex_as_decimal; // and this shows the new ASCII characters
						os.write(HexInverter.int_to_bytes(inverted_hex_as_decimal), 0, 1); //and finally, append new byte to new file
					}
					os.close(); //close output file
					System.out.println("\nRaw Input Hexadecimal List: " + user_input_list);
					System.out.println("Raw Output ASCII: " + new_hex_console);
					System.out.println("Inverted Hexadecimal List: " + new_hex);
				break;
				case "-f": //interpret hex from binary file
				
					System.out.println("Input  File: " + args[1]);
					System.out.println("Output File: " + args[2]);
					try{ //try for FileNotFoundException, incase the mysterious input file is a wrong path
					File ifile = new File(args[2]); //this is to delete pre-existing files with same name.
					ifile.delete();
					OutputStream fos = new FileOutputStream(new File(args[2]), true); //create new file to output towards
					FileInputStream file = null;
					//blank strings to prepare to be concat'd
					String new_console_hex = "";
					String new_console_ascii = "";
					String file_input_list = "";
					file = new FileInputStream(args[1]); //open the file as an input stream
					System.out.printf("Raw Input ASCII: ");
					for(int i = 0; i< new File(args[1]).length(); i++){
						int in_byte = (int)file.read(); //next byte in file, casted to type integer
						file_input_list = file_input_list +  Integer.toHexString(in_byte) + " "; //converts this integer to a hexdecimal string
						System.out.printf("%s", (char) in_byte); //this is appended to "Raw Input ASCII: " on the console log
						int inverted_raw_byte = 255 - in_byte; //invert the byte by subtracting itself from 255, or FF
						new_console_hex = new_console_hex + Integer.toHexString(inverted_raw_byte) + " "; //this is concat'd to show the inverted list on command screen
						new_console_ascii = new_console_ascii + (char)inverted_raw_byte;// and this shows the new ASCII characters
						fos.write(HexInverter.int_to_bytes(inverted_raw_byte), 0, 1); //and finally, append new byte to new file
					}
					if (file != null) { //close input file
						file.close();
					}
					fos.close(); //close output file
					System.out.println("\nRaw Input Hexadecimal List: " + file_input_list);
					System.out.println("Raw Output ASCII: " + new_console_ascii);
					System.out.println("Inverted Hexadecimal List: " + new_console_hex);
					} catch (FileNotFoundException e){
						System.out.println("Input File does not exist! Aborting... Maybe you spelled it wrong?");
					}
				break;
				case "-table":
					System.out.printf( //Command characters that do not show up on command line, in plain text
					"- NUL : 00\n"+
					"- SOH : 01\n"+
					"- STX : 02\n"+
					"- ETX : 03\n"+
					"- EOT : 04\n"+
					"- ENQ : 05\n"+
					"- ACK : 06\n"+
					"- BEL : 07\n"+
					"- BS  : 08\n"+
					"- HT  : 09\n"+
					"- LF  : 0A\n"+
					"- VT  : 0B\n"+
					"- FF  : 0C\n"+
					"- CR  : 0D\n"+
					"- SO  : 0E\n"+
					"- SI  : 0F\n"+
					"- DLE : 10\n"+
					"- DC1 : 11\n"+
					"- DC2 : 12\n"+
					"- DC3 : 13\n"+
					"- DC4 : 14\n"+
					"- NAK : 15\n"+
					"- SYN : 16\n"+
					"- ETB : 17\n"+
					"- CAN : 18\n"+
					"- EM  : 19\n"+
					"- SUB : 1A\n"+
					"- ESC : 1B\n"+
					"- FS  : 1C\n"+
					"- GS  : 1D\n"+
					"- RS  : 1E\n"+
					"- US  : 1F\n");
					for (int i = 32; i < 127; i++){ //loops and displays hex, and corrisponding ascii char up to DEL on ASCII table
						System.out.printf("- %c   : %s \n", (char)i, Integer.toHexString(i));
					}
					System.out.println("- DEL : 7f");
				break;
				default: //no proper argument found, it'll show this
					System.out.println("This is a Command Line Java Class, please use proper arguments");
					System.out.println("-f [input file] [output file] //file input mode, will still print list of inverted bytes in console");
					System.out.println("-h [output file] 00 01 02 03 ... //raw hex mode, paste list of bytes with spaces in between, maximum 1 byte per input, FF FF, not FFFF.");
					System.out.println("-table //shows the ascii table, if you want to do this by hand, you crazy person you");
				break;
			}
		} else { //no arguments detected whatsoever
			System.out.println("This is a Command Line Java Class, please use proper arguments");
			System.out.println("-f [input file] [output file] //file input mode, will still print list of inverted bytes in console");
			System.out.println("-h [output file] 00 01 02 03 ... //raw hex mode, paste list of bytes with spaces in between, maximum 1 byte per input, FF FF, not FFFF.");
			System.out.println("-table //shows the ascii table, if you want to do this by hand, you crazy person you");
		}
	}
}