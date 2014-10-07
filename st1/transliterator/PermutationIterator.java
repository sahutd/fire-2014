//package abe.testing.helper;

import java.util.Iterator;

public class PermutationIterator<E> implements Iterator<E>{

	E arr[][];
	int p[];
	boolean mHasNext = true;//not safe for empty array TODO
	/**
	 * first index (rows) of array represent total possible positions
	 * each column represents possibilities for that row
	 */
	public PermutationIterator(E arr[][]){
		this.arr=arr;
		p = new int[arr.length];
	}
	public int numberOfPermutations(){
		int sum = arr[0].length;
		for(int i=1;i<arr.length;i++){
			sum*=arr[i].length;
		}
		return sum;
	}
	@Override
	public boolean hasNext() {
		return mHasNext;
		/*int maxSum=0, currSum=0;
		for(int i=0;i<arr.length;i++){
			maxSum+=arr[i].length;
			currSum+=p[i];
		}
		if(maxSum<currSum)
			return false;
		return true;*/
	}
	/**
	 * uses current p array, then increments it
	 * @return
	 */
	public String getNextPermutation(){
		if(hasNext()){
			String result = "";
			for(int i=0;i<arr.length;i++){
				result+=arr[i][p[i]]+"#";
			}
			boolean reset = true;
			for(int i=0;reset&&i<arr.length;i++){
				p[i]++;
				reset = p[i]>=arr[i].length;
				if(reset)
					p[i]=0;
			}
			if(reset)//if last element (MSB) gets reset reset will be set to true after loop. This means we have exceeded allowable permutations
				mHasNext=false;
			return result;
		}
		return null;
	}
	@Override
	public E next() {//TODO
		if(hasNext()){
			E r=null;
			for(int i=0;i<arr.length;i++){
	//			r+=p[i];
			}
			return r;
		}
		return null;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}
}
