//package abe.fire.transliterator;


public class ProbString implements Comparable<ProbString>{
	String mString;
	int mProb;

	public void add(String s, int p){
		mString = s;
		mProb = p;
	}

	@Override
	public int compareTo(ProbString o) {
		return this.mProb-o.mProb;
	}
}
