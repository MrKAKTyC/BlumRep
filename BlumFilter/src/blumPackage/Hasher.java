package blumPackage;

public class Hasher {

	private int hashFuncCount;


	public Hasher(int hashFuncCount) {
		this.hashFuncCount = hashFuncCount;
	}
	/**
	 * 
	 * @param Value	String which will be hashed
	 * @param SizeOfSet Amount of needed hash functions
	 * @return	Positions where change state
	 */
	public int[] hashar(String Value, int SizeOfSet) {
		int[] posInSet = new int[hashFuncCount];
		int j = 0;
		for (int i = 0; i < posInSet.length; i++) {
		j++;
			posInSet[i] = ((FirstHash(Value, i, SizeOfSet) * j * SecondHash(Value, i, SizeOfSet)) % SizeOfSet
					+ SizeOfSet) % SizeOfSet;
//			System.out.printf("%d * %d * %d = ", FirstHash(Value, i, SizeOfSet), j, SecondHash(Value, i, SizeOfSet));
//			System.out.println(posInSet[i]);
		}
//		System.out.println();
		return posInSet;
	}

	private int FirstHash(String Value, int Iteration, int SizeOfSet) {
		return ((int) (((Value).hashCode() * 37 * (Iteration + 17)) % SizeOfSet));
	}

	private int SecondHash(String Value, int Iteration, int SizeOfSet) {
		int hash = 0;
		for (int i = 0; i <= SizeOfSet; i++) {
			hash = (31 * hash) + Value.hashCode();
		}
		return hash;
	}

	public int getHashFuncCount() {
		return hashFuncCount;
	}
}
