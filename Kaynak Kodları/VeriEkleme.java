package bilgisayarMimarisiProje01;

import java.util.ArrayList;

public class VeriEkleme {

	//tum siniflar icinde erisilebilmesi icin statik degiskenler
	static String dataS;
	static String storedDataS;
	static int sendromCode;
	static String sendromKelimesi;

	static ArrayList<String> data = new ArrayList<String>();
	static ArrayList<String> storedData = new ArrayList<String>();

	public static void main(String[] args) {
		//kullanicinin sectigi karakter uzunluguna gore arrayliste atama
		if(Menu.text.length()==16) {
			data.add(Character.toString(Menu.text.charAt(15)));
			data.add(Character.toString(Menu.text.charAt(14)));
			data.add(Character.toString(Menu.text.charAt(13)));
			data.add(Character.toString(Menu.text.charAt(12)));
			data.add(Character.toString(Menu.text.charAt(11)));
			data.add(Character.toString(Menu.text.charAt(10)));
			data.add(Character.toString(Menu.text.charAt(9)));
			data.add(Character.toString(Menu.text.charAt(8)));
			data.add(Character.toString(Menu.text.charAt(7)));
			data.add(Character.toString(Menu.text.charAt(6)));
			data.add(Character.toString(Menu.text.charAt(5)));
			data.add(Character.toString(Menu.text.charAt(4)));
			data.add(Character.toString(Menu.text.charAt(3)));
			data.add(Character.toString(Menu.text.charAt(2)));
			data.add(Character.toString(Menu.text.charAt(1)));
			data.add(Character.toString(Menu.text.charAt(0)));
		}
		else if(Menu.text.length()==8) {
			data.add(Character.toString(Menu.text.charAt(7)));
			data.add(Character.toString(Menu.text.charAt(6)));
			data.add(Character.toString(Menu.text.charAt(5)));
			data.add(Character.toString(Menu.text.charAt(4)));
			data.add(Character.toString(Menu.text.charAt(3)));
			data.add(Character.toString(Menu.text.charAt(2)));
			data.add(Character.toString(Menu.text.charAt(1)));
			data.add(Character.toString(Menu.text.charAt(0)));
		}
		else if(Menu.text.length()==4) {
			data.add(Character.toString(Menu.text.charAt(3)));
			data.add(Character.toString(Menu.text.charAt(2)));
			data.add(Character.toString(Menu.text.charAt(1)));
			data.add(Character.toString(Menu.text.charAt(0)));
		}
		
		//sendrom kelimesi uzunlugu bulma
		sendromCode = Functions.hammingCodeBitLengthFinder(data);
		
		//depolanmis hamming code degerleri olmadan gecici bit ekleme
		storedData = Functions.hammingCodeBitAdder(data, sendromCode);
		
		//gecici olarak eklenen bitlerin degerini bulma
		int check = Functions.hammingCodeBitFinder(storedData, sendromCode);
		sendromKelimesi = String.format("%0"+sendromCode+"d", Functions.find(check));
		
		//bulunan check bitlerini depolanmis veriye ekleme
		Functions.findStoredData(storedData, sendromKelimesi);
		
		//ekrana yazdirabilmek icin ters cevrilmis data ve storedData
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

		VeriEkleme.dataS = String.join("" , dataReverse);
		VeriEkleme.storedDataS = String.join("" , storedDataReverse);	
	}
}
