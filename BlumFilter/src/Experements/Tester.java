package Experements;

import blumPackage.Hasher;

public class Tester {

	public static void main(String[] args) {
		int HashC = 20;
		int Size = 10000;
		
		Hasher hash = new Hasher(HashC);
		int[] test = new int[Size];
		for(int i = 0; i<Size;i++){
			changer(test,hash.hashar(new Integer(i).toString().toString(), Size));
				System.out.println(i);
		}
		FileOutputer.GenCSVFile(test, "test");
		System.out.println("Done");
	}
	private static void changer(int[] Source, int[] Pos){
		for(int i = 0; i<Pos.length;i++){
			Source[Pos[i]]++;
		}
	}

}
