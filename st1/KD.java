//package abe.fire.transliterator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * issues - kapoor becomes kapor. like. wat.
 * @author USER
 *
 */
public class KD {
	public static String NOT_FOUND="not found";
	public static String compress(String s){
		s = removeDoubleVowels(s);
		s = removeUselessH(s);
		s = removeDoubleConsonants(s);
		return s;
	}
	public static String removeDoubleVowels(String s){
		StringBuilder sb = new StringBuilder(s);
		for(int i=0;i<sb.length()-1;i++)
			while(isVowel(sb.charAt(i))&&i+1<sb.length()&&(sb.charAt(i)==sb.charAt(i+1)))
				sb.deleteCharAt(i+1);
		return sb.toString();
	}
	public static String removeDoubleConsonants(String s){
		StringBuilder sb = new StringBuilder(s);
		for(int i=0;i<sb.length()-1;i++)
			while(!isVowel(sb.charAt(i))&&i+1<sb.length()&&(sb.charAt(i)==sb.charAt(i+1)))
				sb.deleteCharAt(i+1);
		return sb.toString();
	}
	public static String removeUselessH(String s){
		StringBuilder sb = new StringBuilder(s);
		for(int i=1;i<sb.length();i++){//we don't care about starting h
			while(sb.charAt(i)=='h'&&validatesRemovingHAfterwards(sb.charAt(i-1)))
				sb.deleteCharAt(i);

		}
		return sb.toString();
	}
	public static boolean validatesRemovingHAfterwards(char c){
		if(isVowel(c))
			return false;
		switch(c){
		case 's':
		case 'c':
		case 'p':
			//IMP t? can't add as training data may have tamasa instead of thamasa
			return false;
		}
		return true;
	}
	//add leniency for sh and s interconversion, maybe another stage
	public static boolean isVowel(char c){
		c = Character.toLowerCase(c);
		switch(c){
		case 'a':
		case 'e':
		case 'i':
		case 'o':
		case 'u':
			return true;
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		//System.out.println(compress("ddhanyavaaad bhaaai jaaanuuu aaaaooo naaa shashhwhaat shashi kapoor"));
		generateTraining(new File("data"));
	}
	public static void generateTraining(File f)throws Exception{
		Scanner sc = null;
		FileWriter fw = null;
			File outF = new File("testing");
			outF.createNewFile();
			fw = new FileWriter(outF,true);
			FileInputStream fr = new FileInputStream(f);
			FileOutputStream fo = new FileOutputStream(outF);
			byte[] b= new byte[1];
			StringBuilder sb = new StringBuilder();

			while(fr.read(b)!=-1){
				/*sb = new StringBuilder();
				String data = new String(b,"UTF-8");
				sb.append(data);
				while(fr.read(b)!=-1&&!(data=new String(b,"UTF-8")).equals("\n")){
					sb.append(data);
				}*/
				String s = sb.toString();
				System.out.println(s);
				fo.write(s.getBytes("UTF-8"));
				//String parts[] = s.split(" ");
				//String compressed = compress(parts[0]);
				//fw.write(compressed+" ");
				//fw.write(parts[1]+"\n");
				//fw.flush();
			}
			fo.close();
			fr.close();
			fw.close();
	}
	/*public static String getCorresponding(File f){
	}*/
}
