package bilgisayarMimarisiProje01;

import javax.swing.*;

import java.awt.Desktop;
import java.awt.event.*;
import java.net.URL;

public class Menu {
	
	//Uygulama ilk acildiginda ekranda yazan yazi her yerden erisilebilmesi icin statik
	static JLabel label1 = new JLabel("<html><font size='9'>Hamming Error-Correcting Code"
			+ "<br>Simülatörüne hoş geldiniz..."
			+ "<br>Sol üstteki seçeneklerden simülasyonu"
			+ "<br>kullanmaya başlayabilirsiniz."
			+ "</font></html>", SwingConstants.CENTER);	
	
	//Diger siniflarin da ulasabilmesi icin textArea'larin textleri
	static String text;
	static String text2;
	static String text3;
	
	//Main fonksiyonu
	public static void main(String[] args) {
		//ana ekran
		JFrame frame = new JFrame("Bilgisayar Mimarisi Proje");
	    
		//ekran boyutları ve konumu
	    frame.add(label1);
	    frame.setLocation(448, 150);
	    frame.setSize(1024, 800);
	    
	    //ekran icindeki elemanlarin olusturulmasi
	    JMenuBar menuBar = new JMenuBar();
	    JLabel imza = new JLabel("<html><font size='5'>Mustafa AYKUT 22360859028</font></html>", SwingConstants.CENTER);
	    JButton github = new JButton("GitHub");
	    JMenu optionsMenu = new JMenu("<html><font size='5'>Seçenekler</font></html>");
	    JMenuItem veriEkleme = new JMenuItem("<html><font size='5'>Veri Ekleme</font></html>");
	    JMenuItem VeriBoz = new JMenuItem("<html><font size='5'>Veriyi Bozma</font></html>");
	    
	    //secenekler menusundeki seceneklere actionlistener atama
	    veriEkleme.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		veriEklemeEkraniGoster(frame);
	    	}
	    });
	    VeriBoz.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	if(VeriEkleme.data.isEmpty()) {
	        		JOptionPane.showMessageDialog(null, "Lütfen önce veri oluşturun!");
	        	}
	        	else {
	        		veriBozmaEkraniGoster(frame);
	        	}
	        }
	    });
	    
	    github.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URL("https://github.com/MustafaAykut77/bilgisayar-mimarisi-hamming-code-simulasyonu").toURI());
				}
				catch (Exception a) {
					// TODO: handle exception
				}
				
			}
		});
	    
	    //olusturulan elemanlarin ekrana eklenmesi
	    optionsMenu.add(veriEkleme);
	    optionsMenu.add(VeriBoz);
	    menuBar.add(optionsMenu);
	    menuBar.add(imza); 
	    menuBar.add(github);
	    frame.setJMenuBar(menuBar);
	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	}
	
	//VERI BOZMA EKRANINI GOSTERME FONKSIYONU
	public static void veriBozmaEkraniGoster(JFrame parentFrame) {
		//gerekli tuslarin ve elemanlarin olusturulmasi
		JFrame secenekler = new JFrame();
		secenekler.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel contentPanel = new JPanel();
		
		JLabel label = new JLabel("<html><font size='5'>Bozulacak bitin pozisyonunu girin:</font></html>");
		contentPanel.add(label);
		JTextArea textArea1 = new JTextArea(5,30);
		contentPanel.add(textArea1);
		
		JLabel label2 = new JLabel("<html><font size='5'>Bozulacak bitin değerini girin:</font></html>");
		contentPanel.add(label2);
		JTextArea textArea2 = new JTextArea(5,30);
		contentPanel.add(textArea2);
		
		JButton submitButton = new JButton("<html><font size='5'>Kaydet</font></html>");
		JButton cancelButton = new JButton("<html><font size='5'>Geri dön</font></html>");
		contentPanel.add(submitButton);
		contentPanel.add(cancelButton);
		
		//kaydetme tusuna actionlistener eklenmesi
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//textArealara yazilan textlerin kaydedilmesi
				text2 = textArea1.getText();
			    text3 = textArea2.getText();
			    //textArea icindeki veri 0 veya 1den olusuyor mu kontrolu
			    if(text3.matches("^[01]+$")) {
			    	secenekler.dispose();
			    	//veri bozma sinifinin cagirilmasi
			    	VeriBozma.main(null);
			    	//bozulduktan sonra eski ve bozulan verinin bilgilerinin ekrana yazdirilmasi
			    	label1.setText("<html><font size='7'>Orijinal veriniz: "+
			    			"<br>"+VeriEkleme.dataS+
			    			"<br>"+"Orijinal depolanmış Veriniz: "+
			    			"<br>"+VeriEkleme.storedDataS+
			    			"<br>Hamming kodu: "+
			    			"<br>"+VeriEkleme.sendromKelimesi+
			    			"<br><br>"+
			    			Menu.text2 + ". bit bozuldu"+
			    			"<br>"+
			    			"<br>Bozulmuş veri: "+
			    			"<br>"+VeriBozma.dataS+
			    			"<br>Bozulmuş depolanmış veri: "+
			    			"<br>"+VeriBozma.storedDataS+
			    			"<br>Sendrom Kelimesi: "+
			    			"<br>"+VeriBozma.sendromKelimesi+
			    			"</font></html");

			    }
			    //0 veya 1den olusmuyorsa uyari verilmesi
			    else if(!text3.matches("^[01]+$")) {
			    	JOptionPane.showMessageDialog(null, "Lütfen 0 veya 1 girin!");
			    }
		  }
			  
		});
		
		//iptal tusuna actionlistener eklenmesi
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//menunun kapanmasi
				secenekler.dispose();
			}
		});
		
		//gerekli gorsel duzenlemelerin yapilmasi
		secenekler.setSize(400,400);
		secenekler.setContentPane(contentPanel);
		secenekler.setLocationRelativeTo(parentFrame);  // Position relative to main frame
		secenekler.setVisible(true);
	}
	
	//VERI EKLEME EKRANI GOSTERME FONKSIYONU
	public static void veriEklemeEkraniGoster(JFrame parentFrame) {
		//gerekli butonlarin ve menulerin atanmasi
		JFrame secenekler = new JFrame();
		JButton _4bitButton = new JButton("<html><font size='9'>4 Bit</font></html>");
		JButton _8bitButton = new JButton("<html><font size='9'>8 Bit</font></html>");
		JButton _16bitButton = new JButton("<html><font size='9'>16 Bit</font></html>");
		secenekler.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel contentPanel = new JPanel();
		contentPanel.add(_4bitButton);
		contentPanel.add(_8bitButton);
		contentPanel.add(_16bitButton);
		secenekler.setContentPane(contentPanel);
		secenekler.pack();
		secenekler.setLocationRelativeTo(parentFrame);  // Position relative to main frame
		secenekler.setVisible(true);

		//Butonlarin ne yapacagina karar verilmesi ve actionlistener eklenmesi
		//4 bit tusu
		_4bitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//gerekli tuslarin ve textArea boyutlarinin ayarlanmasi
				JTextArea textArea = new JTextArea(5, 20);
				JButton submitButton = new JButton("<html><font size='5'>Kaydet</font></html>");
				JButton cancelButton = new JButton("<html><font size='5'>Geri dön</font></html>");

				//textArea icin pencere olusturulmasi ve elemanlarin eklenmesi
				JFrame textAreaWindow = new JFrame("Text Area Input");
				textAreaWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				JPanel contentPanel = new JPanel();
				contentPanel.add(textArea);
				contentPanel.add(submitButton);
				contentPanel.add(cancelButton);
				textAreaWindow.setContentPane(contentPanel);
				textAreaWindow.pack();

				//Kaydetme tusuna actionlistener atama
				submitButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						text = textArea.getText();
						//bilgiler istenilen sekilde girildi mi kontrolu
						if(text.length()!=4) {
							JOptionPane.showMessageDialog(null, "Lütfen 4 bit girin!");
						}
						else if (!text.matches("^[01]+$")) {
							JOptionPane.showMessageDialog(null, "Lütfen 0 veya 1 girin!");
						}
						else {
							//veriekleme sinifinin cagirilmasi
							VeriEkleme.main(null);
							//gerekli bilginin ekrana yazdirilmasi
							label1.setText("<html><font size='9'>Veriniz: "+
									"<br>"+VeriEkleme.dataS+
									"<br><br>Depolanmış Veriniz: "+
									"<br>"+VeriEkleme.storedDataS+
									"<br><br>Hamming kodu: "+
									"<br>"+VeriEkleme.sendromKelimesi+
									"</font></html");
							textAreaWindow.dispose();
							secenekler.dispose();
						}
					}
				});
				
				//iptal tusuna actionlistener atamasi
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						textAreaWindow.dispose();
					}
				});

				textAreaWindow.setLocationRelativeTo(parentFrame);
				textAreaWindow.setVisible(true);
			}
		});

		//Butonlarin ne yapacagina karar verilmesi ve actionlistener eklenmesi
		//8 bit tusu
		_8bitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//gerekli tuslarin ve textArea boyutlarinin ayarlanmasi
				JTextArea textArea = new JTextArea(5, 20);
				JButton submitButton = new JButton("<html><font size='5'>Kaydet</font></html>");
				JButton cancelButton = new JButton("<html><font size='5'>Geri dön</font></html>");

				//textArea icin pencere olusturulmasi ve elemanlarin eklenmesi
				JFrame textAreaWindow = new JFrame("Text Area Input");
				textAreaWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				JPanel contentPanel = new JPanel();
				contentPanel.add(textArea);
				contentPanel.add(submitButton);
				contentPanel.add(cancelButton);
				textAreaWindow.setContentPane(contentPanel);
				textAreaWindow.pack();

				//Kaydetme tusuna actionlistener atama
				submitButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						text = textArea.getText();
						//bilgiler istenilen sekilde girildi mi kontrolu
						if(text.length()!=8) {
							JOptionPane.showMessageDialog(null, "Lütfen 8 bit girin!");
						}
						else if (!text.matches("^[01]+$")) {
							JOptionPane.showMessageDialog(null, "Lütfen 0 veya 1 girin!");
						}
						else {
							//veriekleme sinifinin cagirilmasi
							VeriEkleme.main(null);
							//gerekli bilginin ekrana yazdirilmasi
							label1.setText("<html><font size='9'>Veriniz: "+
									"<br>"+VeriEkleme.dataS+
									"<br><br>Depolanmış Veriniz: "+
									"<br>"+VeriEkleme.storedDataS+
									"<br><br>Hamming kodu: "+
									"<br>"+VeriEkleme.sendromKelimesi+
									"</font></html");
							textAreaWindow.dispose();
							secenekler.dispose();
						}
					}
				});
				
				//iptal tusuna actionlistener atamasi
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						textAreaWindow.dispose();
					}
				});

				textAreaWindow.setLocationRelativeTo(parentFrame);
				textAreaWindow.setVisible(true);
			}
		});  

		//Butonlarin ne yapacagina karar verilmesi ve actionlistener eklenmesi
		//16 bit tusu
		_16bitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//gerekli tuslarin ve textArea boyutlarinin ayarlanmasi
				JTextArea textArea = new JTextArea(5, 20);
				JButton submitButton = new JButton("<html><font size='5'>Kaydet</font></html>");
				JButton cancelButton = new JButton("<html><font size='5'>Geri dön</font></html>");

				//textArea icin pencere olusturulmasi ve elemanlarin eklenmesi
				JFrame textAreaWindow = new JFrame("Text Area Input");
				textAreaWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				JPanel contentPanel = new JPanel();
				contentPanel.add(textArea);
				contentPanel.add(submitButton);
				contentPanel.add(cancelButton);
				textAreaWindow.setContentPane(contentPanel);
				textAreaWindow.pack();

				//Kaydetme tusuna actionlistener atama
				submitButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						text = textArea.getText();
						//bilgiler istenilen sekilde girildi mi kontrolu
						if(text.length()!=16) {
							JOptionPane.showMessageDialog(null, "Lütfen 16 bit girin!");
						}
						else if (!text.matches("^[01]+$")) {
							JOptionPane.showMessageDialog(null, "Lütfen 0 veya 1 girin!");
						}
						else {
							//veriekleme sinifinin cagirilmasi
							VeriEkleme.main(null);
							//gerekli bilginin ekrana yazdirilmasi
							label1.setText("<html><font size='9'>Veriniz: "+
									"<br>"+VeriEkleme.dataS+
									"<br><br>Depolanmış Veriniz: "+
									"<br>"+VeriEkleme.storedDataS+
									"<br><br>Hamming kodu: "+
									"<br>"+VeriEkleme.sendromKelimesi+
									"</font></html");
							textAreaWindow.dispose();
							secenekler.dispose();
						}
					}
				});

				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						textAreaWindow.dispose(); 
					}
				});

				textAreaWindow.setLocationRelativeTo(parentFrame);
				textAreaWindow.setVisible(true);
			}
		});
	}
}

