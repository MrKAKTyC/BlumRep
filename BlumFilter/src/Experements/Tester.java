package Experements;

import java.util.Iterator;
import java.util.LinkedList;

import blumPackage.Filter;

public class Tester {

	public static void main(String[] args) {
		int HashC = 2;
		int Size = 100_000;
		int Adds = 25_000;

		// Hasher hash = new Hasher(HashC, Size);
		// int[] test = new int[Size];
		// for(int i = 0; i<Adds;i++){
		// changer(test,hash.hashar(new Integer(i).toString().toString()));
		// }
		// FileOutputer.GenCSVFile(test, "test");
		// System.out.println(ZerosCount(test));
		// for (int i = 0; i <= 3; i++) {
		// System.out.println("Size = " + Size / Math.pow(10, i));
		// for (int j = 0; j <= 3; j++) {
		// System.out.print(emulation(HashC, (int) (Size / Math.pow(10, i)),
		// (int) (Adds / Math.pow(10, j)))+ " ");
		// }
		// System.out.println();
		// }
		// System.out.println(emulation(HashC, Size/1, Adds/1));
		System.out.println(Delet_emulation(HashC, Size, Adds, (int) (Adds * 0.01)));
		System.out.println("Done");
	}

	private static void changer(int[] Source, int[] Pos) {
		for (int i = 0; i < Pos.length; i++) {
			Source[Pos[i]]++;
		}
	}

	private static int ZerosCount(int[] array) {
		int Zeros = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == 0)
				Zeros++;
		}
		return Zeros;
	}

	private static int emulation(int HCount, int Size, int Adds) {
		System.out.printf("HC = %d Size = %d Adds = %d%n", HCount, Size, Adds);
		Filter filtr = new Filter(Size, HCount);
		LinkedList<Integer> elements = new LinkedList<>();
		for (int i = 0; i < Adds; i++) {
			int Val = (int) (Math.random() * Size);
			Integer InVal = new Integer(Val);
			elements.add(InVal);
			filtr.addToSet(InVal.toString());

		}
		int FalsePositivs = 0;
		for (int i = 0; i < Adds;) {
			Integer InVal = new Integer((int) (Math.random() * Size));
			if (elements.contains(InVal)) {
				continue;
			} else {
				if (filtr.findInSet(InVal.toString()).equals("Можливо містить"))
					FalsePositivs++;
				i++;
			}
		}
		return FalsePositivs;
	}

	private static int Delet_emulation(int HCount, int Size, int Adds, int Deleting) {
		System.out.printf("HC = %d Size = %d Adds = %d%n", HCount, Size, Adds);
		Filter filtr = new Filter(Size, HCount);
		LinkedList<Integer> elements = new LinkedList<>();
		for (int i = 0; i < Adds; i++) { // Заповнення фыльтру
			int Val = (int) (Math.random() * Size);
			Integer InVal = new Integer(Val);
			elements.add(InVal);
			filtr.addToSet(InVal.toString());

		}
		int WrongFind = 0;
		System.out.println(elements.size());
		for (int i = 0; i < Deleting;i++) {
			Integer InVal = elements.get((int) Math.random() * elements.size());
			filtr.removeFromSet(InVal.toString());
			elements.remove(InVal);
		}
		System.out.println(elements.size());
		for (Iterator<Integer> itr = elements.iterator(); itr.hasNext();) {
			Integer integer = (Integer) itr.next();
			if (!filtr.findInSet(integer.toString()).equals("Можливо містить"))
				WrongFind++;
		}
		return WrongFind;
	}

}
