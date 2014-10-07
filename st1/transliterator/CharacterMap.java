//package abe.fire.transliterator;

import java.util.HashMap;

/**
 * Serves as a map between used character convention and UNICODE hindi characters
 * Reference font: Arial Unicode MS
 * @author Abraham
 *
 */
@SuppressWarnings("serial")
public class CharacterMap extends HashMap<String,String>{
	public CharacterMap(){
		put("A", "\u0905");
		put("AA", "\u0906");
		put("I","\u0907");
		put("II","\u0908");
		put("III","\u0908");
		put("U","\u0909");
		put("UU","\u090A");
		put("E","\u090F");
		put("EE","\u0910");
		put("EEE","\u0911");
		put("O","\u0913");
		put("OO","\u0914");
		//Do vowels
		put("a", "\u093E");
		put("i","\u093F");
		put("ii","\u0940");
		put("iii","\u0940");
		put("u","\u0941");
		put("uu","\u0942");
		put("e","\u0947");
		put("ee","\u0948");
		put("eee","\u0948");
		put("o","\u094B");
		put("oo","\u094C");

		put("K","\u0915");
		put("KK","\u0916");
		put("G","\u0917");
		put("GG","\u0918");
		put("C","\u091A");
		put("CH","\u091B");
		put("J","\u091C");
		put("JJ","\u091D");
		put("T","\u091F");
		put("TT","\u0920");
		put("D","\u0921");
		put("DD","\u0922");
		put("TH","\u0924");
		put("THH","\u0925");
		put("D2","\u0926");
		put("DD2","\u0927");
		put("N","\u0928");
		put("P","\u092A");
		put("PP","\u092B");
		put("B","\u092C");
		put("BB","\u092D");
		put("M","\u092E");
		put("Y","\u092F");
		put("R","\u0930");
		put("L","\u0932");
		put("W","\u0935");
		put("SH","\u0936");
		put("SHH","\u0937");
		put("S","\u0938");
		put("H","\u0939");

		put("HALF","\u094D");
		put("DU","\u093C");
		put("DT","\u0902");

	}
}
