package abe.fire.transliterator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import abe.testing.helper.PermutationIterator;
/**
 * Entire class assumes that all passed Strings are in lowercase.
 * TODO Sanitising strings to lower case
 * @author Abraham
 *
 */
public class Transliterator {
	/*public static String[] possibilitiesAt(String s, int index){
		char c = s.charAt(index);
		if(isVowel(c)){
			return getVowelPossibilities(s, index);
		}
		else if(isConsonant(c)){
			return null;//TODO
		}
		return null;//TODO
	}*/
	/**
	 * works for a vowel block
	 * @param s
	 * @param index
	 * @return
	 */
	public static String[] getVowelBlockPossibilities(String whole[], int index){
		String block = whole[index];
		ArrayList<String> al = new ArrayList<String>();//holds possibilities
		if(index==0){//starting block
			if(block.length()==1){
				switch((char)block.charAt(0)){
				case 'a':
					al.add("A"); //TODO Consider al.add("AA");
					break;
				case 'e':
					al.add("E");//TODO Consider eh, ehhh, ai sounds
					break;
				case 'i':
					al.add("I"); //TODO Consider II
					break;
				case 'o':
					al.add("O");//TODO Consider fully
					break;
				case 'u':
					al.add("U");//TODO Consider UU
					break;
				}
			}
			else if(block.length()==2){
				if(block.equals("aa")){
					al.add("AA");
				}
				else if(block.equals("ai")){
					al.add("EE");
				}
				else if(block.equals("au")){
					al.add("OO");
				}
				else if(block.equals("ea")){//TODO check actually
					al.add("I");
					al.add("II");
				}
				else if(block.equals("ee")){
					al.add("II");//TODO Consider I
				}
				else if(block.equals("ei")){
					al.add("EEE");//TODO consider E, EE
					al.add("E");//TODO unsure
				}
				//
				else if(block.equals("eu")){//TODO as part of experiment where consonants replace vowels
					al.add("y#uu");
					al.add("y#u");
				}
				//TODO ia, ie, ii, io, iu
				//TODO oa, oe, oi
				else if(block.equals("ou")){
					al.add("OO");
				}
				else if(block.equals("oo")){
					al.add("U");
					al.add("UU");
				}
				else if(block.equals("uu")){
					al.add("UU");
					al.add("U");
				}
			}
		}
		
		else if(index==whole.length-1){//last block of sentence
			if(block.length()==1){
				switch((char)block.charAt(0)){
				case 'a':
					al.add("a"); //TODO Consider al.add("AA");
					break;
				case 'e':
					al.add("e");//TODO Consider eh, ehhh, ai sounds
					al.add("ee");
					break;
				case 'i':
					al.add("ii"); //TODO Consider which has greater possibility, ii, or i?
					al.add("i");
					break;
				case 'o':
					al.add("o");//TODO Consider ou later (2 length block)
					break;
				case 'u':
					al.add("u");
					al.add("uu");//TODO very unsure
					break;
				}
			}
			else if(block.length()==2){
				if(block.equals("aa")){
					al.add("a");
				}
				else if(block.equals("ai")){
					al.add("a#II");//as in bhai
					al.add("e");//hai as in H#e
					al.add("ee");
					al.add("a#Y");//consonants added! gai as in cow. Low priority
				}
				else if(block.equals("au")){
					al.add("oo");
				}
				else if(block.equals("ea")){//TODO check actually
					System.out.println("LOG: END ea");
					al.add("i");
					al.add("ii");
				}
				else if(block.equals("ee")){
					al.add("ii");//TODO Consider I
				}
				else if(block.equals("ei")){
					al.add("eee");//TODO consider E, EE
					al.add("e");//TODO unsure
					//mein
				}
				//
				else if(block.equals("eu")){//TODO as part of experiment where consonants replace vowels
					System.out.println("LOG: END eu");
					al.add("y#uu");
					al.add("y#u");
				}
				else if(block.equals("ia")){
					al.add("i#Y#a");//consonants added!
					al.add("ii#Y#a");//MUCH lower priority
				}
				else if(block.equals("ie")){
					al.add("i");//no clue
				}
				else if(block.equals("ii")){
					al.add("ii");
				}
				else if(block.equals("io")){
					al.add("i#Y#o");
					al.add("ii#Y#o");
				}
				else if(block.equals("iu")){
					al.add("i#Y#u");
					al.add("i#Y#uu");
					al.add("ii#Y#u");
					al.add("ii#Y#uu");
				}
				//TODO oe, oi
				else if(block.equals("oa")){
					al.add("o#A");
					al.add("o#AA");
				}
				else if(block.equals("oi")){
					al.add("o#Y");
				}
				else if(block.equals("ou")){
					al.add("oo");
				}
				else if(block.equals("oo")){
					al.add("u");
					al.add("uu");
				}
				else if(block.equals("uu")){
					al.add("uu");
					al.add("u");
				}
			}
		}
		else{//in the middle
			if(block.length()==1){
				switch((char)block.charAt(0)){
				case 'a':
					al.add("!");//! means nothing i.e gap between consonants 
					al.add("a"); //TODO Consider al.add("AA");
					break;
				case 'e':
					al.add("e");//TODO Consider eh, ehhh, ai sounds
					break;
				case 'i':
					al.add("i"); //TODO Consider which has greater possibility, ii, or i?
					al.add("ii");//In the middle of sentence, isn't it i?
					break;
				case 'o':
					al.add("o");//TODO Consider ou later (2 length block)
					break;
				case 'u'://TODO check ordering
					al.add("!");//! means nothing
					al.add("u");
					al.add("uu");//TODO very unsure
					break;
				}
			}
			else if(block.length()==2){
				if(block.equals("aa")){
					al.add("a");
				}
				else if(block.equals("ai")){
					al.add("eee");//bhai...
					al.add("a#I");//bhaijaan
					al.add("a#II");//bhaijaan
					al.add("e");//low priority
				}
				else if(block.equals("au")){
					al.add("oo");
				}
				else if(block.equals("ea")){//TODO check actually
					al.add("i");
					al.add("ii");
				}
				else if(block.equals("ee")){
					al.add("ii");//TODO Consider I
				}
				else if(block.equals("ei")){
					al.add("eee");//TODO mein
					al.add("e");//TODO mein
					//mein
				}
				//
				else if(block.equals("eu")){//TODO as part of experiment where consonants replace vowels
					System.out.println("LOG: END eu");
					al.add("y#uu");
					al.add("y#u");
				}
				else if(block.equals("ia")){
					al.add("i#Y#a");//consonants added!
					al.add("a#Y#a");//diameter, diaphragm, mainly english words
					al.add("ii#Y#a");//MUCH lower priority
				}
				else if(block.equals("ie")){
					al.add("i");//no clue
					al.add("ii");//low
				}
				else if(block.equals("ii")){
					al.add("ii");
				}
				else if(block.equals("io")){
					al.add("i#Y#o");
					al.add("ii#Y#o");//consider ii#o (o replaces Y)
				}
				else if(block.equals("iu")){
					al.add("i#Y#u");
					al.add("i#Y#uu");
					al.add("ii#Y#u");//unsure of necessity
					al.add("ii#Y#uu");//unsure of necessity
				}
				//TODO oe, oi
				else if(block.equals("oi")){
					al.add("o#i");//join
					al.add("o#ii");//join
				}
				else if(block.equals("oa")){
					al.add("o#A");
					al.add("o#AA");
				}
				else if(block.equals("oi")){
					al.add("o#Y");
				}
				else if(block.equals("ou")){
					al.add("oo");
				}
				else if(block.equals("oo")){
					al.add("u");
					al.add("uu");
				}
				else if(block.equals("uu")){
					al.add("uu");
					al.add("u");
				}
			}
		}
		return al.toArray(new String[0]);
	}
	/**
	 * in consonant blocks, position is not as important as in vowels, main determinant is consonant used
	 * hence position checks are performed later not at the start
	 * P.S. In vowels position checks determine sound and whether or not sign or letter form should be used
	 * 
	 * Things to add: starting and ending specialities, besides z in zindagi
	 * @param whole
	 * @param index
	 * @return
	 */
	public static String[] getConsonantBlockPosibilities(String[] whole, int index){
		String block = whole[index];
		ArrayList<String> al = new ArrayList<String>();
		if(block.length()==1){
			char c = block.charAt(0);
			switch(c){
			case 'b':
				al.add("B");
				al.add("BB");//low priority
				break;
			case 'c':
			case 'k':
				al.add("K");//KK can be added as low priority
				break;
			case 'd':
				al.add("D");//da sound
				al.add("D2");//dha sound
				al.add("DD2");//ddha sound
				break;
			case 'f':
				al.add("PP");
				break;
			case 'g':
				al.add("G");
				al.add("GG");
				break;
			case 'h':
				al.add("H");
				break;
			case 'j':
				al.add("J");
				al.add("JJ");//very low priority
				break;
			case 'l':
				al.add("L");
				break;
			case 'm':
				al.add("M");
				break;
			case 'n':
				al.add("N");
				break;
			case 'p':
				al.add("P");
				break;
			case 'q'://TODO check order of this. Eg- Qureshi
				al.add("K");
				al.add("KK");
				break;
			case 'r':
				al.add("R");
				break;
			case 's':
				al.add("S");
				break;
			case 't':
				al.add("T");
				al.add("TH");//close competition
				al.add("TT");
				break;
			case 'v':
				al.add("V");
				break;
			case 'w':
				al.add("W");
				break;
			case 'y':
				al.add("Y");
				break;
			case 'z':
				al.add("J#DU");//DU - dot under
				break;
				//Generally, maybe dot under should be applied wherever d, dd is seen to test
			}
		}
		else if(block.length()==2){
			
			if(block.equals("sh")){
				al.add("SH");
				al.add("SHH");
			}
			//ending with h rules. TODO should be applied at the end of removeDoubleConsonants Eg: zindhagi
			else if(block.endsWith("H")){
				char c = block.charAt(0);
				switch(c){
				case 'b':
					al.add("BB");
					break;
				case 'c':
					al.add("C");
					al.add("CH");
					break;
				case 'd':
					al.add("DD2");
					al.add("DD");
					al.add("D2");
					al.add("D");
					break;
				case 'f':
					al.add("PP");
					break;
				case 'g':
					al.add("GG");//not considered g
					break;
				case 'h':
					al.add("H");
					break;
				case 'j':
					al.add("JJ");//not considered j
					break;
				case 'k':
					al.add("KK");//not considered k
					break;
				case 'l':
					al.add("L");//should make space for ':' symbol
					break;
				case 'm':
					al.add("M");
					break;
				case 'n':
					al.add("N");
					break;
				case 'p':
					al.add("PP");
					break;
				case 'q':
					al.add("KK");
					al.add("K");
					break;
				case 'r':
					al.add("R");
					break;
				case 't':
					al.add("TH");
					al.add("THH");
					break;
				case 'v':
					al.add("V");
					break;
				case 'w':
					al.add("V");
					break;
				case 'y':
					al.add("Y");
					break;
				case 'z':
					al.add("J#DU");//DU dot under
					break;
				}
			}
		}
		if(al.size()==0){//not been able to find any match
			block = removeDoubleConsonants(block);
			/*if(block.contains("sh")){ //TODO handle sh in the middle
				int sInd = block.indexOf("sh");				
			}*/
			String prefix = "";
			if(block.startsWith("n")){
				prefix+="DT#";//DT dot top
				block = block.replaceFirst("n", "");
			}
			//String result = prefix;
			ArrayList<String[]> resultsEachLetter = new ArrayList<String[]>();
			for(int i=0;i<block.length();i++){//TODO IMP! Accomodate getUnitConstantPossibilities giving multiple values
				//replacing with ! right now while we consider whether or not it is a possibility
				//right now only first result from getUnitConstantPossibilities being considered
				String unitResults[] =getUnitConstantPossibilities(whole, index, block.charAt(i));
				resultsEachLetter.add(unitResults);
				//spaces to be replaced # symbols. Used just so that we can easily remove trailing # with trim()
				//if(i!=block.length()-1)
					//result+="!"+" ";//remove this line to ignore possibility of hamare being written as hamre
				
				//before enabling '!', you need to write function to produce without ! also
				//TEMPORARY CODE UNTIL ALL RESULTS CAN BE PROCESSED				
				//END OF TEMP CODE
			}
			String temp=prefix;
			//TODO take care of half-letters and non-half letters
			String tResults[][] = resultsEachLetter.toArray(new String[0][0]);
			PermutationIterator<String> pm = new PermutationIterator<String>(tResults);
			while(pm.hasNext())
				al.add(prefix+pm.getNextPermutation());
			//FILTERING OUT EXTRA HASHES
			//CORRECT WAY to be done here and in each function, not implicitly when array is split. #justencapsulationthings
			//CHEAP WAY used later
			//The code below was originally used to take care of half letters too
			//al.add(temp);
			//result = result.trim();
			//result = result.replace(' ' , '#');
			//String result2 = result.replace("!#", "");
			//al.add(result);
			//al.add(result2);
		}
		//handle any special character cases which mean something like starting with n and so on
		//reduce and try for above two block lengths if possible
		return al.toArray(new String[0]);
	}
	public static String[] getUnitConstantPossibilities(String whole[], int index, char c){
			ArrayList<String> al = new ArrayList<String>();
			//char c = block.charAt(0);
			switch(c){
			case 'b':
				al.add("B");
				al.add("BB");//low priority
				break;
			case 'c':
			case 'k':
				al.add("K");//KK can be added as low priority
				al.add("KK");//IMP TODO add system to provide stress to double consonants if reduced. Eg:- ikkees requires KK not K
				//possible implementation could involve an integer array being passed saying how many letters of the same type were present/a measure of stress to be applied
				break;
			case 'd':
				al.add("D");//da sound
				al.add("D2");//dha sound
				al.add("DD2");//ddha sound
				break;
			case 'f':
				al.add("PP");
				break;
			case 'g':
				al.add("G");
				al.add("GG");
				break;
			case 'h':
				al.add("H");
				break;
			case 'j':
				al.add("J");
				al.add("JJ");//very low priority
				break;
			case 'l':
				al.add("L");
				break;
			case 'm':
				al.add("M");
				break;
			case 'n':
				al.add("N");
				break;
			case 'p':
				al.add("P");
				break;
			case 'q'://TODO check order of this. Eg- Qureshi
				al.add("K");
				al.add("KK");
				break;
			case 'r':
				al.add("R");
				break;
			case 's':
				al.add("S");
				break;
			case 't':
				al.add("T");
				al.add("TH");//close competition
				al.add("TT");
				break;
			case 'v':
				al.add("V");
				break;
			case 'w':
				al.add("W");
				break;
			case 'y':
				al.add("Y");
				break;
			case 'z':
				al.add("J#DU");//DU - dot under
				break;
				//Generally, maybe dot under should be applied wherever d, dd is seen to test
			}
		return al.toArray(new String[0]);
	}
	public static String removeDoubleConsonants(String s){
		for(int i=0;i<s.length()-1;i++){
			if(s.charAt(i)==s.charAt(i+1)){
				s = s.replaceFirst(""+s.charAt(i),"!#");
			}
		}
		s = s.replaceAll("!#", "");
		return s;
	}
	public static void main(String sa[]){

		//File testFile = new File("test.htm");
		//testFile.delete();
		sa = new String[]{"hamare"};
		for(String s:sa){
			String blocks[] = getConsonantVowelBlocks(s);
			String result[][] = new String[blocks.length][];
			
			for(int i =0;i<blocks.length;i++){
				if(isVowel(blocks[i].charAt(0))){
					result[i]=getVowelBlockPossibilities(blocks, i);
				}
				else{
					result[i]=getConsonantBlockPosibilities(blocks, i);
				}
			}
			/*for(String test[]: result){
				for(String t: test){
					System.out.print(t);
				}
				System.out.println();
			}*/
			PermutationIterator<String> pi = new PermutationIterator<String>(result);
			while(pi.hasNext()){
				String t = pi.getNextPermutation();
				t=t.replace("##","#");//CHEAP WAY Ctrl+F for correct way. Possibly make each function handle the hashes rather than add it ourselves
				//System.out.println(t);
				String mapped = CharacterMapper.getMapped(t);
				System.out.println(mapped);
				//writeToFile(t,testFile);
				//writeToFile(mapped, testFile);
			}
			System.out.println();
		}
	}
	public static void writeToFile(String s, File f){
		try {
			f.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		PrintWriter pw=null;
		try {
			FileOutputStream fos = new FileOutputStream(f,true);
			pw = new PrintWriter(new OutputStreamWriter(fos,"UTF16"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.println(s);
		pw.close();
	}
	/**
	 * returns array representing consonant and vowel blocks
	 * @param s
	 * @return
	 */
	public static String[] getConsonantVowelBlocks(String s){
		ArrayList<String> al = new ArrayList<String>();
		int beg = 0;
		boolean lastVowel = isVowel(s.charAt(0));
		for(int i=1;i<s.length();i++){
			char c = s.charAt(i);
			if(isVowel(c)^lastVowel){//if this is a vowel but last wasn't and vice versa
				al.add(s.substring(beg,i));
				beg = i;
				lastVowel = !lastVowel;//if break occured, that means this char was not a vowel if last was a vowel and vice versa				
			}
		}
		al.add(s.substring(beg, s.length()));//whatever is remaining i.e last block
		return al.toArray(new String[0]);
	}
	public static boolean isBeginning(String s, int index){
		return index==0;
	}
	public static boolean isEnd(String s, int index){
		return s.length()-1==index;
	}
	public static boolean isVowel(char c){
		boolean result = false;
		switch(c){
		case 'a':
		case 'e':
		case 'i':
		case 'o':
		case 'u':
			result=true;
		}
		return result;
	}
	public static boolean isConsonant(char c){
		return !isVowel(c);
	}
	public static boolean vowelsEitherSideOfChar(String s, int index){
		if(index==0||index==s.length()-1){
			return false;
		}
		else
			return isVowel(s.charAt(index-1))&&isVowel(s.charAt(index+1));
	}
}
