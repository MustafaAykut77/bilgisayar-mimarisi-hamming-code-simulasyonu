package bilgisayarMimarisiProje01;

import java.util.ArrayList;
import java.util.Collections;

public class VeriBozma {

	//diger siniflarca kullanilabilmesi icin statik degiskenler
	static String sendromKelimesi;
	static ArrayList<String> data = new ArrayList<>(VeriEkleme.data);
	static ArrayList<String> storedData = new ArrayList<>(VeriEkleme.storedData);
	static String dataS;
	static String storedDataS;

	public static void main(String[] args) {

		//bozulmus verinin kullanici girisi olan text2'den alinmasi
		int corruptedBitLocation = Integer.parseInt(Menu.text2);
		//sayac ile kullanicinin sectigi konumdan once kac tane check biti oldugunun bulunmasi
		int sayac = 0;
		for(int i=0; i<Integer.parseInt(Menu.text2); i++) {
			if(Math.pow(2, i)<Integer.parseInt(Menu.text2)) {
				sayac++;
			}
		}
		
		//bozulacak verinin orijinal verinin ustune yazilmamasi icin kopyalarin uretilmesi
		Collections.copy(data, VeriEkleme.data);		
		Collections.copy(storedData, VeriEkleme.storedData);	

		//Veriyi bozma islemi
		//secilen konum check bitleriyse depolanmis veri uzerinde oyna
		if((corruptedBitLocation != 0) && ((corruptedBitLocation & (corruptedBitLocation - 1)) == 0)) {
			storedData.set(corruptedBitLocation -1, Menu.text3);
		}
		//secilen bit check bitine denk gelmiyorsa hem veriyi hem depolanmis veriyi guncelle
		else {
			data.set(corruptedBitLocation - 1 - sayac, Menu.text3);
			storedData.set(corruptedBitLocation -1, Menu.text3);
		}
		
		//olusturulan hatali verilerle orijinal verinin karsilastirilmasi
		int errorCode = Functions.errorDetection(data, storedData, VeriEkleme.sendromCode);
		sendromKelimesi = String.format("%0"+VeriEkleme.sendromCode+"d", Functions.find(errorCode));

		//olusturulan hatali verinin ekrana yazdirilabilmesi icin tersine cevrilmesi
		ArrayList<String> dataCopy = new ArrayList<>(data);
		String str1 = String.join(" " , dataCopy);
		StringBuilder dataReverse = new StringBuilder();
		dataReverse.append(str1);
		dataReverse.reverse();

		ArrayList<String> storedDataCopy = new ArrayList<>(storedData);
		String str2 = String.join(" " , storedDataCopy);
		StringBuilder storedDataReverse = new StringBuilder();
		storedDataReverse.append(str2);
		storedDataReverse.reverse();
		
		VeriBozma.dataS = String.join("" , dataReverse);
		VeriBozma.storedDataS = String.join("" , storedDataReverse);
	}
}
