package blumPackage;

import java.util.BitSet;

public class Filter {

	private BitSet BArray;
	private int SetSize;
	private int Insertion;
	private Hasher hash;

	public Filter(int SizeOfFilter, int HashCount) {
		this.BArray = new BitSet(SizeOfFilter);
		this.SetSize = SizeOfFilter;
		this.Insertion = 0;
		this.hash = new Hasher(HashCount, this.SetSize);
	}

	public String addToSet(String Value) {
		try {
			int posSet[] = hash.hashar(Value);
			
			for (int i : posSet) {
				assert (posSet[i] > SetSize);
				if(!this.BArray.get(i)){
					this.Insertion++;
					this.BArray.set(i);
				} else {
					this.BArray.set(i);
				}
			}
			return "Успішно додано";
		} catch (Exception e) {
			return e.getLocalizedMessage();
		}
	}

	public String findInSet(String Value) {
		boolean contain;
		int i = 0;
		int posSet[] = hash.hashar(Value);
		do {
			contain = this.BArray.get(posSet[i]);
			i++;
		} while ((contain) && (i < posSet.length));
		if (contain)
			return "Можливо містить";
		else
			return "Не містить";
	}
	
	public String removeFromSet(String Value){
		try{
			int posSet[] = hash.hashar(Value);
			
			for (int i : posSet) {
				assert (posSet[i] > SetSize);
				if(this.BArray.get(i)){
					this.Insertion--;
					this.BArray.clear(i);
				} else {
					this.BArray.clear(i);
				}
			}			
			return "Успішно видалено";
		} catch(Exception e) {
			return e.getMessage();
		}
	}
	
	public double CalculateFalsePositv(){
		double chance = 0.0;
		chance = Math.pow( ( 1-Math.pow((1-(1.0/this.SetSize)), hash.getHashFuncCount()*this.Insertion) ), hash.getHashFuncCount());
		return chance;
		
	}
	
	public int getSetSize(){
		return this.SetSize;
	}
	public Hasher getHasher(){
		return this.hash;
	}
}
