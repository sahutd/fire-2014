package abe.fire.transliterator;

public class CharacterMapper {
	/**
	 * parses sentences and returns their devanagiri script equivalent
	 * @param s
	 * @return
	 */
	public static String getMapped(String s){
		CharacterMap cMap = new CharacterMap();
		String split[] = s.split("#");
		StringBuffer result = new StringBuffer(split.length);
		for(String t:split){
			if(!t.equals("!"))//TODO add condition to put half letters in 
				result.append(""+cMap.get(t));
		}
		return result.toString();
	}
}
