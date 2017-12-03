package blumPackage;

public class Hasher {

	private int hashFuncCount;
	private int[] posInSet;
	private int SizeOfSet;

	public Hasher(int hashFuncCount, int SizeOfSet) {
		this.hashFuncCount = hashFuncCount;
		this.posInSet = new int[hashFuncCount];
		this.SizeOfSet = SizeOfSet;

	}

	/**
	 * 
	 * @param Value
	 *            String which will be hashed
	 * @param SizeOfSet
	 *            Amount of needed hash functions
	 * @return Positions where change state
	 */
	public int[] hashar(String Value) {
		int j = hashFuncCount;
		RSHash(Value, j);
		return this.posInSet;
	}

	public void RSHash(String str, int Num) {
		final int num = 0;
		if (num == Num) {
			return;
		}
		int b = 378551;
		int a = 63689;
		long hash = 0;

		for (int i = 0; i < str.length(); i++) {
			hash = hash * a + str.charAt(i);
			a = a * b;
		}
		posInSet[num] = ((int) hash % SizeOfSet + SizeOfSet) % SizeOfSet;
		JSHash(str, Num++);
	}

	public void JSHash(String str, int Num) {
		final int num = 1;
		if (num == Num) {
			return;
		}
		long hash = 1315423911;

		for (int i = 0; i < str.length(); i++) {
			hash ^= ((hash << 5) + str.charAt(i) + (hash >> 2));
		}
		posInSet[num] = ((int) hash % SizeOfSet + SizeOfSet) % SizeOfSet;
		PJWHash(str, Num++);
	}

	public void PJWHash(String str, int Num) {
		final int num = 2;
		if (num == Num) {
			return;
		}
		long BitsInUnsignedInt = (long) (4 * 8);
		long ThreeQuarters = (long) ((BitsInUnsignedInt * 3) / 4);
		long OneEighth = (long) (BitsInUnsignedInt / 8);
		long HighBits = (long) (0xFFFFFFFF) << (BitsInUnsignedInt - OneEighth);
		long hash = 0;
		long test = 0;

		for (int i = 0; i < str.length(); i++) {
			hash = (hash << OneEighth) + str.charAt(i);

			if ((test = hash & HighBits) != 0) {
				hash = ((hash ^ (test >> ThreeQuarters)) & (~HighBits));
			}
		}
		posInSet[num] = ((int) hash % SizeOfSet + SizeOfSet) % SizeOfSet;
		ELFHash(str, Num++);
	}
	public void ELFHash(String str, int Num) {
		final int num = 3;
		if (num == Num) {
			return;
		}
		long hash = 0;
		long x = 0;

		for (int i = 0; i < str.length(); i++) {
			hash = (hash << 4) + str.charAt(i);

			if ((x = hash & 0xF0000000L) != 0) {
				hash ^= (x >> 24);
			}
			hash &= ~x;
		}
		posInSet[num] = ((int) hash % SizeOfSet + SizeOfSet) % SizeOfSet;
		BKDRHash(str, Num++);
	}

	public void BKDRHash(String str, int Num) {
		final int num = 4;
		if (num == Num) {
			return;
		}
		long seed = 131; // 31 131 1313 13131 131313 etc..
		long hash = 0;

		for (int i = 0; i < str.length(); i++) {
			hash = (hash * seed) + str.charAt(i);
		}
		posInSet[num] = ((int) hash % SizeOfSet + SizeOfSet) % SizeOfSet;
		SDBMHash(str, Num++);
	}

	public void SDBMHash(String str, int Num) {
		final int num = 5;
		if (num == Num) {
			return;
		}
		long hash = 0;

		for (int i = 0; i < str.length(); i++) {
			hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash;
		}
		posInSet[num] = ((int) hash % SizeOfSet + SizeOfSet) % SizeOfSet;
		DJBHash(str, Num++);
	}

	public void DJBHash(String str, int Num) {
		final int num = 6;
		if (num == Num) {
			return;
		}
		long hash = 5381;

		for (int i = 0; i < str.length(); i++) {
			hash = ((hash << 5) + hash) + str.charAt(i);
		}
		posInSet[num] = ((int) hash % SizeOfSet + SizeOfSet) % SizeOfSet;
		DEKHash(str, Num++);
	}

	public void DEKHash(String str, int Num) {
		final int num = 7;
		if (num == Num) {
			return;
		}
		long hash = str.length();

		for (int i = 0; i < str.length(); i++) {
			hash = ((hash << 5) ^ (hash >> 27)) ^ str.charAt(i);
		}
		posInSet[num] = ((int) hash % SizeOfSet + SizeOfSet) % SizeOfSet;
		BPHash(str, Num++);
	}

	public void BPHash(String str, int Num) {
		final int num = 8;
		if (num == Num) {
			return;
		}
		long hash = 0;

		for (int i = 0; i < str.length(); i++) {
			hash = hash << 7 ^ str.charAt(i);
		}
		posInSet[num] = ((int) hash % SizeOfSet + SizeOfSet) % SizeOfSet;
		FNVHash(str, Num++);
	}

	public void FNVHash(String str, int Num) {
		final int num = 9;
		if (num == Num) {
			return;
		}
		long fnv_prime = 0x811C9DC5;
		long hash = 0;

		for (int i = 0; i < str.length(); i++) {
			hash *= fnv_prime;
			hash ^= str.charAt(i);
		}
		posInSet[0] = ((int) hash % SizeOfSet + SizeOfSet) % SizeOfSet;
		APHash(str, Num++);
	}

	public void APHash(String str, int Num) {
		final int num = 10;
		if (num == Num) {
			return;
		}
		long hash = 0xAAAAAAAA;

		for (int i = 0; i < str.length(); i++) {
			if ((i & 1) == 0) {
				hash ^= ((hash << 7) ^ str.charAt(i) * (hash >> 3));
			} else {
				hash ^= (~((hash << 11) + str.charAt(i) ^ (hash >> 5)));
			}
		}
		posInSet[num] = ((int) hash % SizeOfSet + SizeOfSet) % SizeOfSet;
	}

	public int getHashFuncCount() {
		return hashFuncCount;
	}
}
