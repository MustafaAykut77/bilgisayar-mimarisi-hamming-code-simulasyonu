package bilgisayarMimarisiProje01;

import java.util.ArrayList;
import java.util.Collections;

public class Functions {
	//HAMMING CODE BIT UZUNLUGU BULMA
	public static int hammingCodeBitLengthFinder(ArrayList<String> data){
		for(int k=0; k<data.size(); k++) {
			if( (Math.pow(2,k)-1) >= data.size()+k ) {
				return k;
			}
		}
		return 0;
	}

	//CHECK BITLERI EKLEME
	public static ArrayList<String> hammingCodeBitAdder(ArrayList<String> data,int k) {
		ArrayList<String> storedData = new ArrayList<>(data);
		Collections.copy(storedData, data);	
		for(int i=0; i<k; i++) {
			storedData.add((int)(Math.pow(2, i) - 1), "2");
		}		
		return storedData;
	}

	//HAMMING CODE BULMA
	public static int hammingCodeBitFinder(ArrayList<String> data, int necessaryBitCount){
		ArrayList<Integer> hammingCode = new ArrayList<Integer>();
		int check = 0;
		for(int i=0; i<data.size(); i++) {
			if(data.get(i).equals("1")) {
				hammingCode.add(i+1);
			}
		}
		while(true) {
			int i = 0;
			if(hammingCode.size() == 1){
				check = hammingCode.get(i);
				break;
			}
			else {
				check = hammingCode.get(i) ^ hammingCode.get(i+1);
				hammingCode.set(i+1, check);
				hammingCode.remove(i);				
			}
		}
		return check;
	}

	//DECIMALI BINARYE CEVIRME
	public static int find(int decimal_number)
	{
		if (decimal_number == 0) 
			return 0; 
		else
			return (decimal_number % 2 + 10 * find(decimal_number / 2));
	}

	//DEPOLANMIS VERIYI BUL
	public static void findStoredData(ArrayList<String> StoredData, String HammingCode){
		int sayac = 0;
		for(int i=0; i<StoredData.size(); i++) {
			if(sayac == HammingCode.length()) {
				break;
			}
			else if(StoredData.get(i) == "2") {
				StoredData.set(i, Character.toString(HammingCode.charAt(HammingCode.length() - sayac - 1)));
				sayac++;
			}
		}
	}

	//HATAYI BUL
	public static int errorDetection(ArrayList<String> corruptedData, ArrayList<String> corruptedStoredData,int k) {	
		int hammingCode1 = 0;
		int hammingCode2 = 0;
		for(int i=0; i<k; i++) {
			hammingCode1 += Math.pow(2, i) * Integer.parseInt(corruptedStoredData.get((int)(Math.pow(2, i))-1));
		}
		ArrayList<String> s = hammingCodeBitAdder(corruptedData, k);
		hammingCode2 = hammingCodeBitFinder(s, k);
		int errorCode = hammingCode1 ^ hammingCode2;
		return errorCode;
	}
}
