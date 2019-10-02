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
	public static boolean silent = false;
	public static boolean verbose = false;
	public static void main(String[] args) throws FileNotFoundException, IOException{
		
		if(args.length != 0){	// if there are command line arguments
			System.out.println("Recieved Argument: " + args[0]);
			switch(args[0]){
				case "-sh":
				case "-vh":
				case "-svh":
				case "-h": //interpret hex list entered by command line, flip, and then print
					if(args[0].equals("-sh")) HexInverter.silent = true;
					if(args[0].equals("-vh")) HexInverter.verbose = true;
					if(args[0].equals("-svh")){ HexInverter.verbose = true; HexInverter.silent = true;}
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
					String real_temp = "";
					if(!HexInverter.silent) System.out.printf("Raw Input ASCII: ");
					for (int i = 2; i < args.length; i++) { //if a byte in the list is larger than 255, or FF, abort.
						if(Integer.valueOf(args[i], 16) > 0xFF) {
							System.out.printf("\nInvalid Byte Recieved: %x, Aborting. Element %d in list.\n", Integer.valueOf(args[i], 16) , i);
							break;
						};
						user_input_list = user_input_list + args[i] + " "; //concat user inputted byte into a string to show the command line
						int inverted_hex_as_decimal = 255 - Integer.valueOf(args[i], 16); //invert the byte by subtracting itself from 255, or FF
						if(!HexInverter.silent) System.out.printf("%s", (char) Integer.parseInt(args[i], 16)); //this is appended to "Raw Input ASCII: " on the console log
						new_hex = new_hex + Integer.toHexString(inverted_hex_as_decimal) + " "; //this is concat'd to show the inverted list on command screen
						new_hex_console = new_hex_console + (char)inverted_hex_as_decimal; // and this shows the new ASCII characters
						if(HexInverter.verbose){
							real_temp = Integer.toHexString(inverted_hex_as_decimal) + " ";
							os.write(real_temp.getBytes(), 0, 3); //and finally, append new string to file.
						} else {
							os.write(HexInverter.int_to_bytes(inverted_hex_as_decimal), 0, 1); //and finally, append new byte to new file
						}
					}
					os.close(); //close output file
					if(!HexInverter.silent) System.out.println("\nRaw Input Hexadecimal List: " + user_input_list);
					if(!HexInverter.silent) System.out.println("Raw Output ASCII: " + new_hex_console);
					if(!HexInverter.silent) System.out.println("Inverted Hexadecimal List: " + new_hex);
					System.out.println("// Task Complete.");
				break;
				case "-sf": //silent
				case "-vf": //verbose
				case "-svf":
				case "-f": //interpret hex from binary file
					if(args[0].equals("-sf")) HexInverter.silent = true;
					if(args[0].equals("-vf")) HexInverter.verbose = true;
					if(args[0].equals("-svf")){ HexInverter.verbose = true; HexInverter.silent = true;}
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
					String freal_temp = "";
					file = new FileInputStream(args[1]); //open the file as an input stream
					if(!HexInverter.silent) System.out.printf("Raw Input ASCII: ");
					for(int i = 0; i< new File(args[1]).length(); i++){
						int in_byte = (int)file.read(); //next byte in file, casted to type integer
						file_input_list = file_input_list +  Integer.toHexString(in_byte) + " "; //converts this integer to a hexdecimal string
						if(!HexInverter.silent) System.out.printf("%s", (char) in_byte); //this is appended to "Raw Input ASCII: " on the console log
						int inverted_raw_byte = 255 - in_byte; //invert the byte by subtracting itself from 255, or FF
						new_console_hex = new_console_hex + Integer.toHexString(inverted_raw_byte) + " "; //this is concat'd to show the inverted list on command screen
						new_console_ascii = new_console_ascii + (char)inverted_raw_byte;// and this shows the new ASCII characters
						if(HexInverter.verbose){
							freal_temp = Integer.toHexString(in_byte) + " ";
							fos.write(freal_temp.getBytes(), 0, 3);
						} else {
							fos.write(HexInverter.int_to_bytes(inverted_raw_byte), 0, 1); //and finally, append new byte to new file
						}
					}
					if (file != null) { //close input file
						file.close();
					}
					fos.close(); //close output file
					if(!HexInverter.silent) System.out.println("\nRaw Input Hexadecimal List: " + file_input_list);
					if(!HexInverter.silent) System.out.println("Raw Output ASCII: " + new_console_ascii);
					if(!HexInverter.silent) System.out.println("Inverted Hexadecimal List: " + new_console_hex);
					System.out.println("// Task Complete.");
					} catch (FileNotFoundException e){
						System.out.println("Input File does not exist! Aborting... Maybe you spelled it wrong?");
					}
				break;
				case "-direct": //directly convert a string to ascii characters
					String input_string = "";
					String console_out = "";
					for(int i = 1; i < args.length ; i++){
						input_string = input_string + args[i] + " ";
					}
					input_string = input_string.substring(0, input_string.length() - 1);
					System.out.println("Input String: " + input_string);
					
					for(int i = 0; i < input_string.length(); i++){
						console_out = console_out + Integer.toHexString((int)input_string.charAt(i)) + " ";
					}
					System.out.println("Hex Out: " + console_out);
					
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
					System.out.println("-f [File In] [File Out] //file input mode, will still print list of inverted bytes in console");
					System.out.println("-sf [File In] [File Out] //silent file mode, same as above with little console output");
					System.out.println("-vf [File In] [File Out] //verbose file mode, print to txtfile as list instead of bytes");
					System.out.println("-svf [File In] [File Out] //silent verbose file mode, does what you think it does");
					System.out.println("-h [File Out] FF FE FB FC ... //raw hex mode, paste list of bytes with spaces in between");
					System.out.println("-sh [File Out] 0F 2F 3F BB ... //silent hex mode, same as above with little console output");
					System.out.println("-vh [File Out] FE FA 03 5F ... //verbose hex mode, print to txtfile as list instead of bytes");
					System.out.println("-svh [File In] [File Out] //silent verbose hex mode, does what you think it does");
					System.out.println("-direct [Input String] //directly convert input string to hex list! ");
					System.out.println("-table //shows the ascii table, if you want to do this by hand, you crazy person you");
				break;
			}
		} else { //no arguments detected whatsoever
					System.out.println("This is a Command Line Java Class, please use proper arguments");
					System.out.println("-f [File In] [File Out] //file input mode, will still print list of inverted bytes in console");
					System.out.println("-sf [File In] [File Out] //silent file mode, same as above with little console output");
					System.out.println("-vf [File In] [File Out] //verbose file mode, print to txtfile as list instead of bytes");
					System.out.println("-svf [File In] [File Out] //silent verbose file mode, does what you think it does");
					System.out.println("-h [File Out] FF FE FB FC ... //raw hex mode, paste list of bytes with spaces in between");
					System.out.println("-sh [File Out] 0F 2F 3F BB ... //silent hex mode, same as above with little console output");
					System.out.println("-vh [File Out] FE FA 03 5F ... //verbose hex mode, print to txtfile as list instead of bytes");
					System.out.println("-svh [File In] [File Out] //silent verbose hex mode, does what you think it does");
					System.out.println("-direct [Input String] //directly convert input string to hex list! ");
					System.out.println("-table //shows the ascii table, if you want to do this by hand, you crazy person you");
		}
	}
}