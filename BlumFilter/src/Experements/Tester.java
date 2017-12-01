package Experements;

import blumPackage.Hasher;

public class Tester {

	public static void main(String[] args) {
		int HashC = 10;
		int Size = 100000;
		int Adds = 100000;
		
		Hasher hash = new Hasher(HashC, Size);
		int[] test = new int[Size];
		for(int i = 0; i<Adds;i++){
			changer(test,hash.hashar(new Integer(i).toString().toString()));
		}
		FileOutputer.GenCSVFile(test, "test");
//		System.out.println(ZerosCount(test));
		System.out.println("Done");
	}
	private static void changer(int[] Source, int[] Pos){
		for(int i = 0; i<Pos.length;i++){
			Source[Pos[i]]++;
		}
	}
	private static int ZerosCount(int[] array){
		int Zeros = 0;
		for(int i = 0; i < array.length; i++){
			if(array[i]==0)
				Zeros++;
		}
		return Zeros;
	}

}
