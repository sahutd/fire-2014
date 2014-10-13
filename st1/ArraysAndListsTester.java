package abe.testing.helper;

import java.util.List;

public class ArraysAndListsTester {
	public static void printArray(Object a[]){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
	}
	public static void printList(List a){
		for(int i=0;i<a.size();i++){
			System.out.print(a+" ");
		}
	}
}
