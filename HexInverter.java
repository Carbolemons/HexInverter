import java.io.*;
import java.nio.*;

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
			return new byte[] {
				(byte)(data & 0x000000FF),
				(byte)((data & 0x0000FF00) >> 8),
				(byte)((data & 0x00FF0000) >> 16),
				(byte)((data & 0xFF000000) >> 24)
			};
		}
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
		
		if(args.length != 0){	
			System.out.println("Recieved Argument: " + args[0]);
			switch(args[0]){
				case "-h": //interpret hex list, flip, and then print
					if(args.length == 1) {
						System.out.println("No Bytes Detected. Aborting.");
						break;
					}
					File file = new File("HexInverted.dat");
					file.delete();
					OutputStream os = new FileOutputStream(new File("HexInverted.dat"), true);
					String new_hex = "";
					for (int i = 1; i < args.length; i++) {
						if(Integer.valueOf(args[i], 16) > 0xFF) {
							System.out.printf("Invalid Byte Recieved: %x, Aborting. Element %d in list.\n", Integer.valueOf(args[i], 16) , i);
							break;
						};
						int inverted_hex_as_decimal = 255 - Integer.valueOf(args[i], 16);
						new_hex = new_hex + Integer.toHexString(inverted_hex_as_decimal) + " ";
						os.write(HexInverter.int_to_bytes(inverted_hex_as_decimal), 0, 1);
					}
					os.close();
					System.out.println("Inverted Hexadecimal List: " + new_hex);
				break;
				case "-f":
					System.out.println("Wow, this should be a file input and not a list of bytes");
				break;
				case "-table":
					System.out.printf(
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
					for (int i = 32; i < 127; i++){
						System.out.printf("- %c   : %s \n", (char)i, Integer.toHexString(i));
					}
					System.out.println("- DEL : 7f");
				break;
				default: //no proper argument found
					System.out.println("This is a Command Line Java Class, please use proper arguments");
					System.out.println("-f [input file] [output file] //file input mode, will still print list of inverted bytes in console");
					System.out.println("-h 00 01 02 03 ... //raw hex mode, paste list of bytes with spaces in between, maximum 1 byte per input, FF FF, not FFFF.");
					System.out.println("-table //shows the ascii table, if you want to do this by hand, you crazy person you");
				break;
			}
		} else { //no arguments detected
			System.out.println("This is a Command Line Java Class, please use proper arguments");
			System.out.println("-f [input file] [output file] //file input mode, will still print list of inverted bytes in console");
			System.out.println("-h 00 01 02 03 ... //raw hex mode, paste list of bytes with spaces in between, maximum 1 byte per input, FF FF, not FFFF.");
			System.out.println("-table //shows the ascii table, if you want to do this by hand, you crazy person you");
		}
	}
}