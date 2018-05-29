package baas;
import java.sql.*;
import java.io.*;
public class Tabelid{
	public static void main(String[] arg) throws Exception{
		BufferedReader sisse=new BufferedReader(new InputStreamReader(System.in));
		Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/if17_kukltane?user=if17&password=if17");
		
		System.out.println("Sisesta toiming (tabeli_loomine/tabeli_kustutamine/lisamine/kustutamine/muutmine/n2itamine/"+
		"n2itamine_filtriga/mitme_tabeli_yhendamine):");
		String toiming = sisse.readLine();
		
		if(toiming.equals("tabeli_loomine")){
			System.out.println("Sisesta tabeli nimi:");
			String tabeli_nimi = sisse.readLine();
			System.out.println("Mitme lahtriga tabelit luua soovite?");
			String Lahtrite_arv = sisse.readLine();
			int lahtrite_arv =Integer.parseInt(Lahtrite_arv);
			
			String lahtrite_nimed[]=new String[lahtrite_arv];
			String lahtrite_tyybid[]=new String[lahtrite_arv];
			
			for(int i=1; i<lahtrite_arv+1; i++){
				System.out.println("Sisesta "+i+". lahtri nimi:");
				String lahtri_nimi = sisse.readLine();
				lahtrite_nimed[i-1] = lahtri_nimi;
				System.out.println("Sisesta "+i+". lahtri tyyp (VARCHAR(size)/INT/DOUBLE):");
				String lahtri_tyyp = sisse.readLine();
				lahtrite_tyybid[i-1] = lahtri_tyyp;
			}
			
			String tabeli_loomise_k2sk = "CREATE TABLE "+tabeli_nimi+"(";

			for(int i=0; i<lahtrite_arv; i++){
				if(i!=lahtrite_arv-1){
					tabeli_loomise_k2sk = tabeli_loomise_k2sk+lahtrite_nimed[i]+" "+lahtrite_tyybid[i]+",";
				}else{
					tabeli_loomise_k2sk = tabeli_loomise_k2sk+lahtrite_nimed[i]+" "+lahtrite_tyybid[i]+");";
				}
			}
			
			PreparedStatement st1=cn.prepareStatement(tabeli_loomise_k2sk);
			st1.executeUpdate();
		}
		
		if(toiming.equals("tabeli_kustutamine")){
			System.out.println("Millist tabelit kustutada soovid?");
			String tabeli_nimi1 = sisse.readLine();
			
			String tabeli_kustutamise_k2sk = "DROP TABLE "+tabeli_nimi1+";";
			
			PreparedStatement st2=cn.prepareStatement(tabeli_kustutamise_k2sk);
			st2.executeUpdate();
		}
		
		if(toiming.equals("lisamine")){
			System.out.println("Millisesse tabelisse soovite andmeid lisada?");
			String tabeli_nimi2 = sisse.readLine();
			System.out.println("Mitme lahtriga tabelit t2ita soovite?");
			String Lahtrite_arv1 = sisse.readLine();
			int lahtrite_arv1 =Integer.parseInt(Lahtrite_arv1);
			
			String tabeli_t2itmise_k2sk = "INSERT INTO "+tabeli_nimi2+" VALUES (";
			
			for(int i=0; i<lahtrite_arv1; i++){
				if(i!=lahtrite_arv1-1){
					tabeli_t2itmise_k2sk = tabeli_t2itmise_k2sk+"?,";
				}else{
					tabeli_t2itmise_k2sk = tabeli_t2itmise_k2sk+"?";
				}
			}
			
			tabeli_t2itmise_k2sk = tabeli_t2itmise_k2sk+");";
			
			PreparedStatement st2=cn.prepareStatement(tabeli_t2itmise_k2sk);
			
			String v22rtus;
			int v22rtus1;
			double v22rtus2;
			
			for(int i=1; i<lahtrite_arv1+1; i++){
				System.out.println("Sisesta "+i+". lahtri tyyp (STRING/INT/DOUBLE):");
				String lahtri_tyyp1 = sisse.readLine();
				
				if(lahtri_tyyp1.equals("STRING")){
					System.out.println("Sisesta "+i+". lahtri sisestatav v22rtus:");
					v22rtus = sisse.readLine();
					st2.setString(i, v22rtus);
				}
				if(lahtri_tyyp1.equals("INT")){
					System.out.println("Sisesta "+i+". lahtri sisestatav v22rtus:");
					v22rtus = sisse.readLine();
					v22rtus1 =Integer.parseInt(v22rtus);
					st2.setInt(i, v22rtus1);
				}
				if(lahtri_tyyp1.equals("DOUBLE")){
					System.out.println("Sisesta "+i+". lahtri sisestatav v22rtus:");
					v22rtus = sisse.readLine();
					v22rtus2 = Double.parseDouble(v22rtus);
					st2.setDouble(i, v22rtus2);
				}
			}
			st2.executeUpdate();
		}
		
		if(toiming.equals("kustutamine")){
			System.out.println("Millisest tabelist veergu kustutada soovid?");
			String tabeli_nimi3 = sisse.readLine();
			System.out.println("Millise muutuja j2rgi kustutada soovid?");
			String muutuja = sisse.readLine();
			System.out.println("Millist v22rtuse j2rgi kustutada soovid?");
			String v22rtus3 = sisse.readLine();
			System.out.println("Mis tyypi andme j2rgi kustutad (arvuline/mittearvuline)?");
			String tyyp = sisse.readLine();
			
			if(tyyp.equals("arvuline")){
				String kustutamise_k2sk = "DELETE FROM "+tabeli_nimi3+" WHERE "+muutuja+"="+v22rtus3+";";
				PreparedStatement st3=cn.prepareStatement(kustutamise_k2sk);
				st3.executeUpdate();
			}
			if(tyyp.equals("mittearvuline")){
				String kustutamise_k2sk = "DELETE FROM "+tabeli_nimi3+" WHERE "+muutuja+"='"+v22rtus3+"';";
				PreparedStatement st3=cn.prepareStatement(kustutamise_k2sk);
				st3.executeUpdate();
			}
		}
		
		if(toiming.equals("muutmine")){
			System.out.println("Millist tabelit muuta soovid?");
			String tabeli_nimi4 = sisse.readLine();
			System.out.println("Millist lahtrit muuta soovid?");
			String muudetav_lahter = sisse.readLine();			
			System.out.println("Millise v22rtuse lahtrisse kirjutada soovid?");
			String muudetav_v22rtus = sisse.readLine();	
			System.out.println("Mis on muudetava lahtri tyyp (arvuline/mittearvuline)?");
			String muudetav_tyyp = sisse.readLine();	
			System.out.println("Millise lahtri j2rgi muuta soovid?");
			String otsitav_lahter = sisse.readLine();			
			System.out.println("Millise v22rtuse j2rgi muuta soovid?");
			String otsitav_v22rtus = sisse.readLine();
			System.out.println("Mis on otsitava lahtri tyyp (arvuline/mittearvuline)?");
			String otsitav_tyyp = sisse.readLine();
			
			if(muudetav_tyyp.equals("arvuline") && otsitav_tyyp.equals("arvuline")){
				String muutmise_k2sk = "UPDATE "+tabeli_nimi4+" SET "+muudetav_lahter+"="+muudetav_v22rtus+" WHERE "+otsitav_lahter+"="+otsitav_v22rtus+";";
				PreparedStatement st4=cn.prepareStatement(muutmise_k2sk);
				st4.executeUpdate();
			}
			if(muudetav_tyyp.equals("arvuline") && otsitav_tyyp.equals("mittearvuline")){
				String muutmise_k2sk = "UPDATE "+tabeli_nimi4+" SET "+muudetav_lahter+"="+muudetav_v22rtus+" WHERE "+otsitav_lahter+"='"+otsitav_v22rtus+"';";
				PreparedStatement st4=cn.prepareStatement(muutmise_k2sk);
				st4.executeUpdate();
			}
			if(muudetav_tyyp.equals("mittearvuline") && otsitav_tyyp.equals("arvuline")){
				String muutmise_k2sk = "UPDATE "+tabeli_nimi4+" SET "+muudetav_lahter+"='"+muudetav_v22rtus+"' WHERE "+otsitav_lahter+"="+otsitav_v22rtus+";";
				PreparedStatement st4=cn.prepareStatement(muutmise_k2sk);
				st4.executeUpdate();
			}
			if(muudetav_tyyp.equals("mittearvuline") && otsitav_tyyp.equals("mittearvuline")){
				String muutmise_k2sk = "UPDATE "+tabeli_nimi4+" SET "+muudetav_lahter+"='"+muudetav_v22rtus+"' WHERE "+otsitav_lahter+"='"+otsitav_v22rtus+"';";
				PreparedStatement st4=cn.prepareStatement(muutmise_k2sk);
				st4.executeUpdate();
			}
		}
		
		if(toiming.equals("n2itamine")){
			System.out.println("Millist tabelit n2idata soovid?");
			String tabeli_nimi5 = sisse.readLine();
			System.out.println("Mitut veergu n2ha soovite (1...10)?");
			String Veergude_arv = sisse.readLine();
			int veergude_arv =Integer.parseInt(Veergude_arv);
			
			String lahtrite_nimed1[]=new String[veergude_arv];
			String lahtri_nimi2;
			int koht;
			
			for(int i=0; i<veergude_arv; i++){
				koht = i+1;
				System.out.println("Sisesta "+koht+". tabeliveerg, mida tahad kuvada:");
				lahtri_nimi2 = sisse.readLine();
				lahtrite_nimed1[i] = lahtri_nimi2;
			}
			
			String n2itamise_k2sk = "SELECT * FROM "+tabeli_nimi5+";";
			
			Statement st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st1.executeQuery(n2itamise_k2sk);
			
			if(veergude_arv==1){
				while(rs.next()){
					System.out.println(rs.getString(lahtrite_nimed1[0]));
				}
			}
			if(veergude_arv==2){
				while(rs.next()){
					System.out.println(rs.getString(lahtrite_nimed1[0])+" "+rs.getString(lahtrite_nimed1[1]));
				}
			}
			if(veergude_arv==3){
				while(rs.next()){
					System.out.println(rs.getString(lahtrite_nimed1[0])+" "+rs.getString(lahtrite_nimed1[1])
					+" "+rs.getString(lahtrite_nimed1[2]));
				}
			}
			if(veergude_arv==4){
				while(rs.next()){
					System.out.println(rs.getString(lahtrite_nimed1[0])+" "+rs.getString(lahtrite_nimed1[1])
					+" "+rs.getString(lahtrite_nimed1[2])+" "+rs.getString(lahtrite_nimed1[3]));
				}
			}
			if(veergude_arv==5){
				while(rs.next()){
					System.out.println(rs.getString(lahtrite_nimed1[0])+" "+rs.getString(lahtrite_nimed1[1])
					+" "+rs.getString(lahtrite_nimed1[2])+" "+rs.getString(lahtrite_nimed1[3])
					+" "+rs.getString(lahtrite_nimed1[4]));
				}
			}
			if(veergude_arv==6){
				while(rs.next()){
					System.out.println(rs.getString(lahtrite_nimed1[0])+" "+rs.getString(lahtrite_nimed1[1])
					+" "+rs.getString(lahtrite_nimed1[2])+" "+rs.getString(lahtrite_nimed1[3])
					+" "+rs.getString(lahtrite_nimed1[4])+" "+rs.getString(lahtrite_nimed1[5]));
				}
			}
			if(veergude_arv==7){
				while(rs.next()){
					System.out.println(rs.getString(lahtrite_nimed1[0])+" "+rs.getString(lahtrite_nimed1[1])
					+" "+rs.getString(lahtrite_nimed1[2])+" "+rs.getString(lahtrite_nimed1[3])
					+" "+rs.getString(lahtrite_nimed1[4])+" "+rs.getString(lahtrite_nimed1[5])
					+" "+rs.getString(lahtrite_nimed1[6]));
				}
			}
			if(veergude_arv==8){
				while(rs.next()){
					System.out.println(rs.getString(lahtrite_nimed1[0])+" "+rs.getString(lahtrite_nimed1[1])
					+" "+rs.getString(lahtrite_nimed1[2])+" "+rs.getString(lahtrite_nimed1[3])
					+" "+rs.getString(lahtrite_nimed1[4])+" "+rs.getString(lahtrite_nimed1[5])
					+" "+rs.getString(lahtrite_nimed1[6])+" "+rs.getString(lahtrite_nimed1[7]));
				}
			}
			if(veergude_arv==9){
				while(rs.next()){
					System.out.println(rs.getString(lahtrite_nimed1[0])+" "+rs.getString(lahtrite_nimed1[1])
					+" "+rs.getString(lahtrite_nimed1[2])+" "+rs.getString(lahtrite_nimed1[3])
					+" "+rs.getString(lahtrite_nimed1[4])+" "+rs.getString(lahtrite_nimed1[5])
					+" "+rs.getString(lahtrite_nimed1[6])+" "+rs.getString(lahtrite_nimed1[7])
					+" "+rs.getString(lahtrite_nimed1[8]));
				}
			}
			if(veergude_arv==10){
				while(rs.next()){
					System.out.println(rs.getString(lahtrite_nimed1[0])+" "+rs.getString(lahtrite_nimed1[1])
					+" "+rs.getString(lahtrite_nimed1[2])+" "+rs.getString(lahtrite_nimed1[3])
					+" "+rs.getString(lahtrite_nimed1[4])+" "+rs.getString(lahtrite_nimed1[5])
					+" "+rs.getString(lahtrite_nimed1[6])+" "+rs.getString(lahtrite_nimed1[7])
					+" "+rs.getString(lahtrite_nimed1[8])+" "+rs.getString(lahtrite_nimed1[9]));
				}
			}
		}
		
		if(toiming.equals("n2itamine_filtriga")){
			System.out.println("Millist tabelit n2idata soovid?");
			String tabeli_nimi6 = sisse.readLine();
			System.out.println("Millise muutuja j2rgi filtreerida soovid?");
			String muutuja1 = sisse.readLine();
			System.out.println("Kuidas filtreerida soovid (v22rtuse_j2rgi/piirkondade_abil)?");
			String filtreerimise_liik = sisse.readLine();
			
			if(filtreerimise_liik.equals("v22rtuse_j2rgi")){
				System.out.println("Millise muutuja v22rtuse j2rgi filtreerida soovid?");
				String v22rtus4 = sisse.readLine();
				System.out.println("Milline on muutuja tyyp (arvuline/mittearvuline)?");
				String tyyp1 = sisse.readLine();
				
				if(tyyp1.equals("arvuline")){
					String filtreerimisega_n2itamise_k2sk = "SELECT * FROM "+tabeli_nimi6+" WHERE "+muutuja1+"="+v22rtus4+";";
					
					System.out.println("Mitut veergu n2ha soovite (1...10)?");
					String Veergude_arv1 = sisse.readLine();
					int veergude_arv1 =Integer.parseInt(Veergude_arv1);
					
					String lahtrite_nimed2[]=new String[veergude_arv1];
					String lahtri_nimi3;
					int koht1;
					
					for(int i=0; i<veergude_arv1; i++){
						koht1 = i+1;
						System.out.println("Sisesta "+koht1+". tabeliveerg, mida tahad kuvada:");
						lahtri_nimi3 = sisse.readLine();
						lahtrite_nimed2[i] = lahtri_nimi3;
					}
					
					Statement st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs=st1.executeQuery(filtreerimisega_n2itamise_k2sk);
					
					if(veergude_arv1==1){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed2[0]));
						}
					}
					if(veergude_arv1==2){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed2[0])+" "+rs.getString(lahtrite_nimed2[1]));
						}
					}
					if(veergude_arv1==3){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed2[0])+" "+rs.getString(lahtrite_nimed2[1])
							+" "+rs.getString(lahtrite_nimed2[2]));
						}
					}
					if(veergude_arv1==4){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed2[0])+" "+rs.getString(lahtrite_nimed2[1])
							+" "+rs.getString(lahtrite_nimed2[2])+" "+rs.getString(lahtrite_nimed2[3]));
						}
					}
					if(veergude_arv1==5){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed2[0])+" "+rs.getString(lahtrite_nimed2[1])
							+" "+rs.getString(lahtrite_nimed2[2])+" "+rs.getString(lahtrite_nimed2[3])
							+" "+rs.getString(lahtrite_nimed2[4]));
						}
					}
					if(veergude_arv1==6){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed2[0])+" "+rs.getString(lahtrite_nimed2[1])
							+" "+rs.getString(lahtrite_nimed2[2])+" "+rs.getString(lahtrite_nimed2[3])
							+" "+rs.getString(lahtrite_nimed2[4])+" "+rs.getString(lahtrite_nimed2[5]));
						}
					}
					if(veergude_arv1==7){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed2[0])+" "+rs.getString(lahtrite_nimed2[1])
							+" "+rs.getString(lahtrite_nimed2[2])+" "+rs.getString(lahtrite_nimed2[3])
							+" "+rs.getString(lahtrite_nimed2[4])+" "+rs.getString(lahtrite_nimed2[5])
							+" "+rs.getString(lahtrite_nimed2[6]));
						}
					}
					if(veergude_arv1==8){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed2[0])+" "+rs.getString(lahtrite_nimed2[1])
							+" "+rs.getString(lahtrite_nimed2[2])+" "+rs.getString(lahtrite_nimed2[3])
							+" "+rs.getString(lahtrite_nimed2[4])+" "+rs.getString(lahtrite_nimed2[5])
							+" "+rs.getString(lahtrite_nimed2[6])+" "+rs.getString(lahtrite_nimed2[7]));
						}
					}
					if(veergude_arv1==9){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed2[0])+" "+rs.getString(lahtrite_nimed2[1])
							+" "+rs.getString(lahtrite_nimed2[2])+" "+rs.getString(lahtrite_nimed2[3])
							+" "+rs.getString(lahtrite_nimed2[4])+" "+rs.getString(lahtrite_nimed2[5])
							+" "+rs.getString(lahtrite_nimed2[6])+" "+rs.getString(lahtrite_nimed2[7])
							+" "+rs.getString(lahtrite_nimed2[8]));
						}
					}
					if(veergude_arv1==10){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed2[0])+" "+rs.getString(lahtrite_nimed2[1])
							+" "+rs.getString(lahtrite_nimed2[2])+" "+rs.getString(lahtrite_nimed2[3])
							+" "+rs.getString(lahtrite_nimed2[4])+" "+rs.getString(lahtrite_nimed2[5])
							+" "+rs.getString(lahtrite_nimed2[6])+" "+rs.getString(lahtrite_nimed2[7])
							+" "+rs.getString(lahtrite_nimed2[8])+" "+rs.getString(lahtrite_nimed2[9]));
						}
					}
				}
				
				if(tyyp1.equals("mittearvuline")){
					String filtreerimisega_n2itamise_k2sk = "SELECT * FROM "+tabeli_nimi6+" WHERE "+muutuja1+"='"+v22rtus4+"';";
					
					System.out.println("Mitut veergu n2ha soovite (1...10)?");
					String Veergude_arv2 = sisse.readLine();
					int veergude_arv2 =Integer.parseInt(Veergude_arv2);
					
					String lahtrite_nimed3[]=new String[veergude_arv2];
					String lahtri_nimi4;
					int koht2;
					
					for(int i=0; i<veergude_arv2; i++){
						koht2 = i+1;
						System.out.println("Sisesta "+koht2+". tabeliveerg, mida tahad kuvada:");
						lahtri_nimi4 = sisse.readLine();
						lahtrite_nimed3[i] = lahtri_nimi4;
					}
					
					Statement st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs=st1.executeQuery(filtreerimisega_n2itamise_k2sk);
					
					if(veergude_arv2==1){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed3[0]));
						}
					}
					if(veergude_arv2==2){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed3[0])+" "+rs.getString(lahtrite_nimed3[1]));
						}
					}
					if(veergude_arv2==3){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed3[0])+" "+rs.getString(lahtrite_nimed3[1])
							+" "+rs.getString(lahtrite_nimed3[2]));
						}
					}
					if(veergude_arv2==4){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed3[0])+" "+rs.getString(lahtrite_nimed3[1])
							+" "+rs.getString(lahtrite_nimed3[2])+" "+rs.getString(lahtrite_nimed3[3]));
						}
					}
					if(veergude_arv2==5){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed3[0])+" "+rs.getString(lahtrite_nimed3[1])
							+" "+rs.getString(lahtrite_nimed3[2])+" "+rs.getString(lahtrite_nimed3[3])
							+" "+rs.getString(lahtrite_nimed3[4]));
						}
					}
					if(veergude_arv2==6){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed3[0])+" "+rs.getString(lahtrite_nimed3[1])
							+" "+rs.getString(lahtrite_nimed3[2])+" "+rs.getString(lahtrite_nimed3[3])
							+" "+rs.getString(lahtrite_nimed3[4])+" "+rs.getString(lahtrite_nimed3[5]));
						}
					}
					if(veergude_arv2==7){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed3[0])+" "+rs.getString(lahtrite_nimed3[1])
							+" "+rs.getString(lahtrite_nimed3[2])+" "+rs.getString(lahtrite_nimed3[3])
							+" "+rs.getString(lahtrite_nimed3[4])+" "+rs.getString(lahtrite_nimed3[5])
							+" "+rs.getString(lahtrite_nimed3[6]));
						}
					}
					if(veergude_arv2==8){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed3[0])+" "+rs.getString(lahtrite_nimed3[1])
							+" "+rs.getString(lahtrite_nimed3[2])+" "+rs.getString(lahtrite_nimed3[3])
							+" "+rs.getString(lahtrite_nimed3[4])+" "+rs.getString(lahtrite_nimed3[5])
							+" "+rs.getString(lahtrite_nimed3[6])+" "+rs.getString(lahtrite_nimed3[7]));
						}
					}
					if(veergude_arv2==9){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed3[0])+" "+rs.getString(lahtrite_nimed3[1])
							+" "+rs.getString(lahtrite_nimed3[2])+" "+rs.getString(lahtrite_nimed3[3])
							+" "+rs.getString(lahtrite_nimed3[4])+" "+rs.getString(lahtrite_nimed3[5])
							+" "+rs.getString(lahtrite_nimed3[6])+" "+rs.getString(lahtrite_nimed3[7])
							+" "+rs.getString(lahtrite_nimed3[8]));
						}
					}
					if(veergude_arv2==10){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed3[0])+" "+rs.getString(lahtrite_nimed3[1])
							+" "+rs.getString(lahtrite_nimed3[2])+" "+rs.getString(lahtrite_nimed3[3])
							+" "+rs.getString(lahtrite_nimed3[4])+" "+rs.getString(lahtrite_nimed3[5])
							+" "+rs.getString(lahtrite_nimed3[6])+" "+rs.getString(lahtrite_nimed3[7])
							+" "+rs.getString(lahtrite_nimed3[8])+" "+rs.getString(lahtrite_nimed3[9]));
						}
					}
				}
			}
			
			if(filtreerimise_liik.equals("piirkondade_abil")){
				System.out.println("Kuidas soovid piirkonda m22ratleda (sisesta tegevuse number):"); 
				System.out.println("1) Suurem mingist v22rtusest.");
				System.out.println("2) Suurem v6i v6rdne mingist v22rtusest.");
				System.out.println("3) V2iksem mingist v22rtusest.");
				System.out.println("4) V2iksem v6i v6rdne mingist v22rtusest.");
				System.out.println("5) Suurem v22rtusest1 ja v2iksem v22rtusest2.");
				System.out.println("6) Suurem v6i v6rdne v22rtusest1 ja v2iksem v22rtusest2.");
				System.out.println("7) Suurem v22rtusest1 ja v2iksem v6i v6rdne v22rtusest2.");
				System.out.println("8) Suurem v6i v6rdne v22rtusest1 ja v2iksem v6i v6rdne v22rtusest2.");
				System.out.println("9) V2iksem v22rtusest1 v6i suurem v22rtusest2.");
				System.out.println("10) V2iksem v6i v6rdne v22rtusest1 v6i suurem v22rtusest2.");
				System.out.println("11) V2iksem v22rtusest1 v6i suurem v6i v6rdne v22rtusest2.");
				System.out.println("12) V2iksem v6i v6rdne v22rtusest1 v6i suurem v6i v6rdne v22rtusest2.");
				
				String valik = sisse.readLine();
				
				if(valik.equals("1")){
					System.out.println("Sisesta v6rreldav v22rtus:");
					String v22rtus5 = sisse.readLine();
					
					String filtreerimisega_n2itamise_k2sk1 = "SELECT * FROM "+tabeli_nimi6+" WHERE "+muutuja1+">"+v22rtus5+";";
					
					System.out.println("Mitut veergu n2ha soovite (1...10)?");
					String Veergude_arv3 = sisse.readLine();
					int veergude_arv3 =Integer.parseInt(Veergude_arv3);
					
					String lahtrite_nimed4[]=new String[veergude_arv3];
					String lahtri_nimi5;
					int koht3;
					
					for(int i=0; i<veergude_arv3; i++){
						koht3 = i+1;
						System.out.println("Sisesta "+koht3+". tabeliveerg, mida tahad kuvada:");
						lahtri_nimi5 = sisse.readLine();
						lahtrite_nimed4[i] = lahtri_nimi5;
					}
					
					Statement st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs=st1.executeQuery(filtreerimisega_n2itamise_k2sk1);
					
					if(veergude_arv3==1){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed4[0]));
						}
					}
					if(veergude_arv3==2){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed4[0])+" "+rs.getString(lahtrite_nimed4[1]));
						}
					}
					if(veergude_arv3==3){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed4[0])+" "+rs.getString(lahtrite_nimed4[1])
							+" "+rs.getString(lahtrite_nimed4[2]));
						}
					}
					if(veergude_arv3==4){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed4[0])+" "+rs.getString(lahtrite_nimed4[1])
							+" "+rs.getString(lahtrite_nimed4[2])+" "+rs.getString(lahtrite_nimed4[3]));
						}
					}
					if(veergude_arv3==5){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed4[0])+" "+rs.getString(lahtrite_nimed4[1])
							+" "+rs.getString(lahtrite_nimed4[2])+" "+rs.getString(lahtrite_nimed4[3])
							+" "+rs.getString(lahtrite_nimed4[4]));
						}
					}
					if(veergude_arv3==6){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed4[0])+" "+rs.getString(lahtrite_nimed4[1])
							+" "+rs.getString(lahtrite_nimed4[2])+" "+rs.getString(lahtrite_nimed4[3])
							+" "+rs.getString(lahtrite_nimed4[4])+" "+rs.getString(lahtrite_nimed4[5]));
						}
					}
					if(veergude_arv3==7){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed4[0])+" "+rs.getString(lahtrite_nimed4[1])
							+" "+rs.getString(lahtrite_nimed4[2])+" "+rs.getString(lahtrite_nimed4[3])
							+" "+rs.getString(lahtrite_nimed4[4])+" "+rs.getString(lahtrite_nimed4[5])
							+" "+rs.getString(lahtrite_nimed4[6]));
						}
					}
					if(veergude_arv3==8){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed4[0])+" "+rs.getString(lahtrite_nimed4[1])
							+" "+rs.getString(lahtrite_nimed4[2])+" "+rs.getString(lahtrite_nimed4[3])
							+" "+rs.getString(lahtrite_nimed4[4])+" "+rs.getString(lahtrite_nimed4[5])
							+" "+rs.getString(lahtrite_nimed4[6])+" "+rs.getString(lahtrite_nimed4[7]));
						}
					}
					if(veergude_arv3==9){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed4[0])+" "+rs.getString(lahtrite_nimed4[1])
							+" "+rs.getString(lahtrite_nimed4[2])+" "+rs.getString(lahtrite_nimed4[3])
							+" "+rs.getString(lahtrite_nimed4[4])+" "+rs.getString(lahtrite_nimed4[5])
							+" "+rs.getString(lahtrite_nimed4[6])+" "+rs.getString(lahtrite_nimed4[7])
							+" "+rs.getString(lahtrite_nimed4[8]));
						}
					}
					if(veergude_arv3==10){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed4[0])+" "+rs.getString(lahtrite_nimed4[1])
							+" "+rs.getString(lahtrite_nimed4[2])+" "+rs.getString(lahtrite_nimed4[3])
							+" "+rs.getString(lahtrite_nimed4[4])+" "+rs.getString(lahtrite_nimed4[5])
							+" "+rs.getString(lahtrite_nimed4[6])+" "+rs.getString(lahtrite_nimed4[7])
							+" "+rs.getString(lahtrite_nimed4[8])+" "+rs.getString(lahtrite_nimed4[9]));
						}
					}
				}
				
				if(valik.equals("2")){
					System.out.println("Sisesta v6rreldav v22rtus:");
					String v22rtus6 = sisse.readLine();
					
					String filtreerimisega_n2itamise_k2sk1 = "SELECT * FROM "+tabeli_nimi6+" WHERE "+muutuja1+">="+v22rtus6+";";
					
					System.out.println("Mitut veergu n2ha soovite (1...10)?");
					String Veergude_arv4 = sisse.readLine();
					int veergude_arv4 =Integer.parseInt(Veergude_arv4);
					
					String lahtrite_nimed5[]=new String[veergude_arv4];
					String lahtri_nimi6;
					int koht4;
					
					for(int i=0; i<veergude_arv4; i++){
						koht4 = i+1;
						System.out.println("Sisesta "+koht4+". tabeliveerg, mida tahad kuvada:");
						lahtri_nimi6 = sisse.readLine();
						lahtrite_nimed5[i] = lahtri_nimi6;
					}
					
					Statement st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs=st1.executeQuery(filtreerimisega_n2itamise_k2sk1);
					
					if(veergude_arv4==1){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed5[0]));
						}
					}
					if(veergude_arv4==2){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed5[0])+" "+rs.getString(lahtrite_nimed5[1]));
						}
					}
					if(veergude_arv4==3){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed5[0])+" "+rs.getString(lahtrite_nimed5[1])
							+" "+rs.getString(lahtrite_nimed5[2]));
						}
					}
					if(veergude_arv4==4){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed5[0])+" "+rs.getString(lahtrite_nimed5[1])
							+" "+rs.getString(lahtrite_nimed5[2])+" "+rs.getString(lahtrite_nimed5[3]));
						}
					}
					if(veergude_arv4==5){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed5[0])+" "+rs.getString(lahtrite_nimed5[1])
							+" "+rs.getString(lahtrite_nimed5[2])+" "+rs.getString(lahtrite_nimed5[3])
							+" "+rs.getString(lahtrite_nimed5[4]));
						}
					}
					if(veergude_arv4==6){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed5[0])+" "+rs.getString(lahtrite_nimed5[1])
							+" "+rs.getString(lahtrite_nimed5[2])+" "+rs.getString(lahtrite_nimed5[3])
							+" "+rs.getString(lahtrite_nimed5[4])+" "+rs.getString(lahtrite_nimed5[5]));
						}
					}
					if(veergude_arv4==7){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed5[0])+" "+rs.getString(lahtrite_nimed5[1])
							+" "+rs.getString(lahtrite_nimed5[2])+" "+rs.getString(lahtrite_nimed5[3])
							+" "+rs.getString(lahtrite_nimed5[4])+" "+rs.getString(lahtrite_nimed5[5])
							+" "+rs.getString(lahtrite_nimed5[6]));
						}
					}
					if(veergude_arv4==8){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed5[0])+" "+rs.getString(lahtrite_nimed5[1])
							+" "+rs.getString(lahtrite_nimed5[2])+" "+rs.getString(lahtrite_nimed5[3])
							+" "+rs.getString(lahtrite_nimed5[4])+" "+rs.getString(lahtrite_nimed5[5])
							+" "+rs.getString(lahtrite_nimed5[6])+" "+rs.getString(lahtrite_nimed5[7]));
						}
					}
					if(veergude_arv4==9){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed5[0])+" "+rs.getString(lahtrite_nimed5[1])
							+" "+rs.getString(lahtrite_nimed5[2])+" "+rs.getString(lahtrite_nimed5[3])
							+" "+rs.getString(lahtrite_nimed5[4])+" "+rs.getString(lahtrite_nimed5[5])
							+" "+rs.getString(lahtrite_nimed5[6])+" "+rs.getString(lahtrite_nimed5[7])
							+" "+rs.getString(lahtrite_nimed5[8]));
						}
					}
					if(veergude_arv4==10){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed5[0])+" "+rs.getString(lahtrite_nimed5[1])
							+" "+rs.getString(lahtrite_nimed5[2])+" "+rs.getString(lahtrite_nimed5[3])
							+" "+rs.getString(lahtrite_nimed5[4])+" "+rs.getString(lahtrite_nimed5[5])
							+" "+rs.getString(lahtrite_nimed5[6])+" "+rs.getString(lahtrite_nimed5[7])
							+" "+rs.getString(lahtrite_nimed5[8])+" "+rs.getString(lahtrite_nimed5[9]));
						}
					}
				}
				
				if(valik.equals("3")){
					System.out.println("Sisesta v6rreldav v22rtus:");
					String v22rtus7 = sisse.readLine();
					
					String filtreerimisega_n2itamise_k2sk2 = "SELECT * FROM "+tabeli_nimi6+" WHERE "+muutuja1+"<"+v22rtus7+";";
					
					System.out.println("Mitut veergu n2ha soovite (1...10)?");
					String Veergude_arv5 = sisse.readLine();
					int veergude_arv5 =Integer.parseInt(Veergude_arv5);
					
					String lahtrite_nimed6[]=new String[veergude_arv5];
					String lahtri_nimi7;
					int koht5;
					
					for(int i=0; i<veergude_arv5; i++){
						koht5 = i+1;
						System.out.println("Sisesta "+koht5+". tabeliveerg, mida tahad kuvada:");
						lahtri_nimi7 = sisse.readLine();
						lahtrite_nimed6[i] = lahtri_nimi7;
					}
					
					Statement st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs=st1.executeQuery(filtreerimisega_n2itamise_k2sk2);
					
					if(veergude_arv5==1){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed6[0]));
						}
					}
					if(veergude_arv5==2){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed6[0])+" "+rs.getString(lahtrite_nimed6[1]));
						}
					}
					if(veergude_arv5==3){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed6[0])+" "+rs.getString(lahtrite_nimed6[1])
							+" "+rs.getString(lahtrite_nimed6[2]));
						}
					}
					if(veergude_arv5==4){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed6[0])+" "+rs.getString(lahtrite_nimed6[1])
							+" "+rs.getString(lahtrite_nimed6[2])+" "+rs.getString(lahtrite_nimed6[3]));
						}
					}
					if(veergude_arv5==5){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed6[0])+" "+rs.getString(lahtrite_nimed6[1])
							+" "+rs.getString(lahtrite_nimed6[2])+" "+rs.getString(lahtrite_nimed6[3])
							+" "+rs.getString(lahtrite_nimed6[4]));
						}
					}
					if(veergude_arv5==6){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed6[0])+" "+rs.getString(lahtrite_nimed6[1])
							+" "+rs.getString(lahtrite_nimed6[2])+" "+rs.getString(lahtrite_nimed6[3])
							+" "+rs.getString(lahtrite_nimed6[4])+" "+rs.getString(lahtrite_nimed6[5]));
						}
					}
					if(veergude_arv5==7){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed6[0])+" "+rs.getString(lahtrite_nimed6[1])
							+" "+rs.getString(lahtrite_nimed6[2])+" "+rs.getString(lahtrite_nimed6[3])
							+" "+rs.getString(lahtrite_nimed6[4])+" "+rs.getString(lahtrite_nimed6[5])
							+" "+rs.getString(lahtrite_nimed6[6]));
						}
					}
					if(veergude_arv5==8){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed6[0])+" "+rs.getString(lahtrite_nimed6[1])
							+" "+rs.getString(lahtrite_nimed6[2])+" "+rs.getString(lahtrite_nimed6[3])
							+" "+rs.getString(lahtrite_nimed6[4])+" "+rs.getString(lahtrite_nimed6[5])
							+" "+rs.getString(lahtrite_nimed6[6])+" "+rs.getString(lahtrite_nimed6[7]));
						}
					}
					if(veergude_arv5==9){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed6[0])+" "+rs.getString(lahtrite_nimed6[1])
							+" "+rs.getString(lahtrite_nimed6[2])+" "+rs.getString(lahtrite_nimed6[3])
							+" "+rs.getString(lahtrite_nimed6[4])+" "+rs.getString(lahtrite_nimed6[5])
							+" "+rs.getString(lahtrite_nimed6[6])+" "+rs.getString(lahtrite_nimed6[7])
							+" "+rs.getString(lahtrite_nimed6[8]));
						}
					}
					if(veergude_arv5==10){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed6[0])+" "+rs.getString(lahtrite_nimed6[1])
							+" "+rs.getString(lahtrite_nimed6[2])+" "+rs.getString(lahtrite_nimed6[3])
							+" "+rs.getString(lahtrite_nimed6[4])+" "+rs.getString(lahtrite_nimed6[5])
							+" "+rs.getString(lahtrite_nimed6[6])+" "+rs.getString(lahtrite_nimed6[7])
							+" "+rs.getString(lahtrite_nimed6[8])+" "+rs.getString(lahtrite_nimed6[9]));
						}
					}
				}
				
				if(valik.equals("4")){
					System.out.println("Sisesta v6rreldav v22rtus:");
					String v22rtus8 = sisse.readLine();
					
					String filtreerimisega_n2itamise_k2sk3 = "SELECT * FROM "+tabeli_nimi6+" WHERE "+muutuja1+"<="+v22rtus8+";";
					
					System.out.println("Mitut veergu n2ha soovite (1...10)?");
					String Veergude_arv6 = sisse.readLine();
					int veergude_arv6 =Integer.parseInt(Veergude_arv6);
					
					String lahtrite_nimed7[]=new String[veergude_arv6];
					String lahtri_nimi8;
					int koht6;
					
					for(int i=0; i<veergude_arv6; i++){
						koht6 = i+1;
						System.out.println("Sisesta "+koht6+". tabeliveerg, mida tahad kuvada:");
						lahtri_nimi8 = sisse.readLine();
						lahtrite_nimed7[i] = lahtri_nimi8;
					}
					
					Statement st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs=st1.executeQuery(filtreerimisega_n2itamise_k2sk3);
					
					if(veergude_arv6==1){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed7[0]));
						}
					}
					if(veergude_arv6==2){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed7[0])+" "+rs.getString(lahtrite_nimed7[1]));
						}
					}
					if(veergude_arv6==3){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed7[0])+" "+rs.getString(lahtrite_nimed7[1])
							+" "+rs.getString(lahtrite_nimed7[2]));
						}
					}
					if(veergude_arv6==4){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed7[0])+" "+rs.getString(lahtrite_nimed7[1])
							+" "+rs.getString(lahtrite_nimed7[2])+" "+rs.getString(lahtrite_nimed7[3]));
						}
					}
					if(veergude_arv6==5){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed7[0])+" "+rs.getString(lahtrite_nimed7[1])
							+" "+rs.getString(lahtrite_nimed7[2])+" "+rs.getString(lahtrite_nimed7[3])
							+" "+rs.getString(lahtrite_nimed7[4]));
						}
					}
					if(veergude_arv6==6){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed7[0])+" "+rs.getString(lahtrite_nimed7[1])
							+" "+rs.getString(lahtrite_nimed7[2])+" "+rs.getString(lahtrite_nimed7[3])
							+" "+rs.getString(lahtrite_nimed7[4])+" "+rs.getString(lahtrite_nimed7[5]));
						}
					}
					if(veergude_arv6==7){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed7[0])+" "+rs.getString(lahtrite_nimed7[1])
							+" "+rs.getString(lahtrite_nimed7[2])+" "+rs.getString(lahtrite_nimed7[3])
							+" "+rs.getString(lahtrite_nimed7[4])+" "+rs.getString(lahtrite_nimed7[5])
							+" "+rs.getString(lahtrite_nimed7[6]));
						}
					}
					if(veergude_arv6==8){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed7[0])+" "+rs.getString(lahtrite_nimed7[1])
							+" "+rs.getString(lahtrite_nimed7[2])+" "+rs.getString(lahtrite_nimed7[3])
							+" "+rs.getString(lahtrite_nimed7[4])+" "+rs.getString(lahtrite_nimed7[5])
							+" "+rs.getString(lahtrite_nimed7[6])+" "+rs.getString(lahtrite_nimed7[7]));
						}
					}
					if(veergude_arv6==9){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed7[0])+" "+rs.getString(lahtrite_nimed7[1])
							+" "+rs.getString(lahtrite_nimed7[2])+" "+rs.getString(lahtrite_nimed7[3])
							+" "+rs.getString(lahtrite_nimed7[4])+" "+rs.getString(lahtrite_nimed7[5])
							+" "+rs.getString(lahtrite_nimed7[6])+" "+rs.getString(lahtrite_nimed7[7])
							+" "+rs.getString(lahtrite_nimed7[8]));
						}
					}
					if(veergude_arv6==10){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed7[0])+" "+rs.getString(lahtrite_nimed7[1])
							+" "+rs.getString(lahtrite_nimed7[2])+" "+rs.getString(lahtrite_nimed7[3])
							+" "+rs.getString(lahtrite_nimed7[4])+" "+rs.getString(lahtrite_nimed7[5])
							+" "+rs.getString(lahtrite_nimed7[6])+" "+rs.getString(lahtrite_nimed7[7])
							+" "+rs.getString(lahtrite_nimed7[8])+" "+rs.getString(lahtrite_nimed7[9]));
						}
					}
				}
				
				if(valik.equals("5")){
					System.out.println("Sisesta v6rreldav v22rtus1:");
					String v22rtus9 = sisse.readLine();
					System.out.println("Sisesta v6rreldav v22rtus2:");
					String v22rtus10 = sisse.readLine();
					
					String filtreerimisega_n2itamise_k2sk4 = "SELECT * FROM "+tabeli_nimi6+" WHERE "+muutuja1+">"+v22rtus9+" AND "+muutuja1+"<"+v22rtus10+";";
					
					System.out.println("Mitut veergu n2ha soovite (1...10)?");
					String Veergude_arv7 = sisse.readLine();
					int veergude_arv7 =Integer.parseInt(Veergude_arv7);
					
					String lahtrite_nimed8[]=new String[veergude_arv7];
					String lahtri_nimi9;
					int koht7;
					
					for(int i=0; i<veergude_arv7; i++){
						koht7 = i+1;
						System.out.println("Sisesta "+koht7+". tabeliveerg, mida tahad kuvada:");
						lahtri_nimi9 = sisse.readLine();
						lahtrite_nimed8[i] = lahtri_nimi9;
					}
					
					Statement st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs=st1.executeQuery(filtreerimisega_n2itamise_k2sk4);
					
					if(veergude_arv7==1){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed8[0]));
						}
					}
					if(veergude_arv7==2){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed8[0])+" "+rs.getString(lahtrite_nimed8[1]));
						}
					}
					if(veergude_arv7==3){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed8[0])+" "+rs.getString(lahtrite_nimed8[1])
							+" "+rs.getString(lahtrite_nimed8[2]));
						}
					}
					if(veergude_arv7==4){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed8[0])+" "+rs.getString(lahtrite_nimed8[1])
							+" "+rs.getString(lahtrite_nimed8[2])+" "+rs.getString(lahtrite_nimed8[3]));
						}
					}
					if(veergude_arv7==5){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed8[0])+" "+rs.getString(lahtrite_nimed8[1])
							+" "+rs.getString(lahtrite_nimed8[2])+" "+rs.getString(lahtrite_nimed8[3])
							+" "+rs.getString(lahtrite_nimed8[4]));
						}
					}
					if(veergude_arv7==6){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed8[0])+" "+rs.getString(lahtrite_nimed8[1])
							+" "+rs.getString(lahtrite_nimed8[2])+" "+rs.getString(lahtrite_nimed8[3])
							+" "+rs.getString(lahtrite_nimed8[4])+" "+rs.getString(lahtrite_nimed8[5]));
						}
					}
					if(veergude_arv7==7){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed8[0])+" "+rs.getString(lahtrite_nimed8[1])
							+" "+rs.getString(lahtrite_nimed8[2])+" "+rs.getString(lahtrite_nimed8[3])
							+" "+rs.getString(lahtrite_nimed8[4])+" "+rs.getString(lahtrite_nimed8[5])
							+" "+rs.getString(lahtrite_nimed8[6]));
						}
					}
					if(veergude_arv7==8){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed8[0])+" "+rs.getString(lahtrite_nimed8[1])
							+" "+rs.getString(lahtrite_nimed8[2])+" "+rs.getString(lahtrite_nimed8[3])
							+" "+rs.getString(lahtrite_nimed8[4])+" "+rs.getString(lahtrite_nimed8[5])
							+" "+rs.getString(lahtrite_nimed8[6])+" "+rs.getString(lahtrite_nimed8[7]));
						}
					}
					if(veergude_arv7==9){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed8[0])+" "+rs.getString(lahtrite_nimed8[1])
							+" "+rs.getString(lahtrite_nimed8[2])+" "+rs.getString(lahtrite_nimed8[3])
							+" "+rs.getString(lahtrite_nimed8[4])+" "+rs.getString(lahtrite_nimed8[5])
							+" "+rs.getString(lahtrite_nimed8[6])+" "+rs.getString(lahtrite_nimed8[7])
							+" "+rs.getString(lahtrite_nimed8[8]));
						}
					}
					if(veergude_arv7==10){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed8[0])+" "+rs.getString(lahtrite_nimed8[1])
							+" "+rs.getString(lahtrite_nimed8[2])+" "+rs.getString(lahtrite_nimed8[3])
							+" "+rs.getString(lahtrite_nimed8[4])+" "+rs.getString(lahtrite_nimed8[5])
							+" "+rs.getString(lahtrite_nimed8[6])+" "+rs.getString(lahtrite_nimed8[7])
							+" "+rs.getString(lahtrite_nimed8[8])+" "+rs.getString(lahtrite_nimed8[9]));
						}
					}
				}
				
				if(valik.equals("6")){
					System.out.println("Sisesta v6rreldav v22rtus1:");
					String v22rtus10 = sisse.readLine();
					System.out.println("Sisesta v6rreldav v22rtus2:");
					String v22rtus11 = sisse.readLine();
					
					String filtreerimisega_n2itamise_k2sk5 = "SELECT * FROM "+tabeli_nimi6+" WHERE "+muutuja1+">="+v22rtus10+" AND "+muutuja1+"<"+v22rtus11+";";
					
					System.out.println("Mitut veergu n2ha soovite (1...10)?");
					String Veergude_arv8 = sisse.readLine();
					int veergude_arv8 =Integer.parseInt(Veergude_arv8);
					
					String lahtrite_nimed9[]=new String[veergude_arv8];
					String lahtri_nimi10;
					int koht8;
					
					for(int i=0; i<veergude_arv8; i++){
						koht8 = i+1;
						System.out.println("Sisesta "+koht8+". tabeliveerg, mida tahad kuvada:");
						lahtri_nimi10 = sisse.readLine();
						lahtrite_nimed9[i] = lahtri_nimi10;
					}
					
					Statement st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs=st1.executeQuery(filtreerimisega_n2itamise_k2sk5);
					
					if(veergude_arv8==1){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed9[0]));
						}
					}
					if(veergude_arv8==2){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed9[0])+" "+rs.getString(lahtrite_nimed9[1]));
						}
					}
					if(veergude_arv8==3){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed9[0])+" "+rs.getString(lahtrite_nimed9[1])
							+" "+rs.getString(lahtrite_nimed9[2]));
						}
					}
					if(veergude_arv8==4){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed9[0])+" "+rs.getString(lahtrite_nimed9[1])
							+" "+rs.getString(lahtrite_nimed9[2])+" "+rs.getString(lahtrite_nimed9[3]));
						}
					}
					if(veergude_arv8==5){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed9[0])+" "+rs.getString(lahtrite_nimed9[1])
							+" "+rs.getString(lahtrite_nimed9[2])+" "+rs.getString(lahtrite_nimed9[3])
							+" "+rs.getString(lahtrite_nimed9[4]));
						}
					}
					if(veergude_arv8==6){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed9[0])+" "+rs.getString(lahtrite_nimed9[1])
							+" "+rs.getString(lahtrite_nimed9[2])+" "+rs.getString(lahtrite_nimed9[3])
							+" "+rs.getString(lahtrite_nimed9[4])+" "+rs.getString(lahtrite_nimed9[5]));
						}
					}
					if(veergude_arv8==7){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed9[0])+" "+rs.getString(lahtrite_nimed9[1])
							+" "+rs.getString(lahtrite_nimed9[2])+" "+rs.getString(lahtrite_nimed9[3])
							+" "+rs.getString(lahtrite_nimed9[4])+" "+rs.getString(lahtrite_nimed9[5])
							+" "+rs.getString(lahtrite_nimed9[6]));
						}
					}
					if(veergude_arv8==8){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed9[0])+" "+rs.getString(lahtrite_nimed9[1])
							+" "+rs.getString(lahtrite_nimed9[2])+" "+rs.getString(lahtrite_nimed9[3])
							+" "+rs.getString(lahtrite_nimed9[4])+" "+rs.getString(lahtrite_nimed9[5])
							+" "+rs.getString(lahtrite_nimed9[6])+" "+rs.getString(lahtrite_nimed9[7]));
						}
					}
					if(veergude_arv8==9){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed9[0])+" "+rs.getString(lahtrite_nimed9[1])
							+" "+rs.getString(lahtrite_nimed9[2])+" "+rs.getString(lahtrite_nimed9[3])
							+" "+rs.getString(lahtrite_nimed9[4])+" "+rs.getString(lahtrite_nimed9[5])
							+" "+rs.getString(lahtrite_nimed9[6])+" "+rs.getString(lahtrite_nimed9[7])
							+" "+rs.getString(lahtrite_nimed9[8]));
						}
					}
					if(veergude_arv8==10){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed9[0])+" "+rs.getString(lahtrite_nimed9[1])
							+" "+rs.getString(lahtrite_nimed9[2])+" "+rs.getString(lahtrite_nimed9[3])
							+" "+rs.getString(lahtrite_nimed9[4])+" "+rs.getString(lahtrite_nimed9[5])
							+" "+rs.getString(lahtrite_nimed9[6])+" "+rs.getString(lahtrite_nimed9[7])
							+" "+rs.getString(lahtrite_nimed9[8])+" "+rs.getString(lahtrite_nimed9[9]));
						}
					}
				}
				
				if(valik.equals("7")){
					System.out.println("Sisesta v6rreldav v22rtus1:");
					String v22rtus12 = sisse.readLine();
					System.out.println("Sisesta v6rreldav v22rtus2:");
					String v22rtus13 = sisse.readLine();
					
					String filtreerimisega_n2itamise_k2sk6 = "SELECT * FROM "+tabeli_nimi6+" WHERE "+muutuja1+">"+v22rtus12+" AND "+muutuja1+"<="+v22rtus13+";";
					
					System.out.println("Mitut veergu n2ha soovite (1...10)?");
					String Veergude_arv9 = sisse.readLine();
					int veergude_arv9 =Integer.parseInt(Veergude_arv9);
					
					String lahtrite_nimed10[]=new String[veergude_arv9];
					String lahtri_nimi11;
					int koht9;
					
					for(int i=0; i<veergude_arv9; i++){
						koht9 = i+1;
						System.out.println("Sisesta "+koht9+". tabeliveerg, mida tahad kuvada:");
						lahtri_nimi11 = sisse.readLine();
						lahtrite_nimed10[i] = lahtri_nimi11;
					}
					
					Statement st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs=st1.executeQuery(filtreerimisega_n2itamise_k2sk6);
					
					if(veergude_arv9==1){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed10[0]));
						}
					}
					if(veergude_arv9==2){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed10[0])+" "+rs.getString(lahtrite_nimed10[1]));
						}
					}
					if(veergude_arv9==3){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed10[0])+" "+rs.getString(lahtrite_nimed10[1])
							+" "+rs.getString(lahtrite_nimed10[2]));
						}
					}
					if(veergude_arv9==4){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed10[0])+" "+rs.getString(lahtrite_nimed10[1])
							+" "+rs.getString(lahtrite_nimed10[2])+" "+rs.getString(lahtrite_nimed10[3]));
						}
					}
					if(veergude_arv9==5){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed10[0])+" "+rs.getString(lahtrite_nimed10[1])
							+" "+rs.getString(lahtrite_nimed10[2])+" "+rs.getString(lahtrite_nimed10[3])
							+" "+rs.getString(lahtrite_nimed10[4]));
						}
					}
					if(veergude_arv9==6){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed10[0])+" "+rs.getString(lahtrite_nimed10[1])
							+" "+rs.getString(lahtrite_nimed10[2])+" "+rs.getString(lahtrite_nimed10[3])
							+" "+rs.getString(lahtrite_nimed10[4])+" "+rs.getString(lahtrite_nimed10[5]));
						}
					}
					if(veergude_arv9==7){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed10[0])+" "+rs.getString(lahtrite_nimed10[1])
							+" "+rs.getString(lahtrite_nimed10[2])+" "+rs.getString(lahtrite_nimed10[3])
							+" "+rs.getString(lahtrite_nimed10[4])+" "+rs.getString(lahtrite_nimed10[5])
							+" "+rs.getString(lahtrite_nimed10[6]));
						}
					}
					if(veergude_arv9==8){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed10[0])+" "+rs.getString(lahtrite_nimed10[1])
							+" "+rs.getString(lahtrite_nimed10[2])+" "+rs.getString(lahtrite_nimed10[3])
							+" "+rs.getString(lahtrite_nimed10[4])+" "+rs.getString(lahtrite_nimed10[5])
							+" "+rs.getString(lahtrite_nimed10[6])+" "+rs.getString(lahtrite_nimed10[7]));
						}
					}
					if(veergude_arv9==9){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed10[0])+" "+rs.getString(lahtrite_nimed10[1])
							+" "+rs.getString(lahtrite_nimed10[2])+" "+rs.getString(lahtrite_nimed10[3])
							+" "+rs.getString(lahtrite_nimed10[4])+" "+rs.getString(lahtrite_nimed10[5])
							+" "+rs.getString(lahtrite_nimed10[6])+" "+rs.getString(lahtrite_nimed10[7])
							+" "+rs.getString(lahtrite_nimed10[8]));
						}
					}
					if(veergude_arv9==10){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed10[0])+" "+rs.getString(lahtrite_nimed10[1])
							+" "+rs.getString(lahtrite_nimed10[2])+" "+rs.getString(lahtrite_nimed10[3])
							+" "+rs.getString(lahtrite_nimed10[4])+" "+rs.getString(lahtrite_nimed10[5])
							+" "+rs.getString(lahtrite_nimed10[6])+" "+rs.getString(lahtrite_nimed10[7])
							+" "+rs.getString(lahtrite_nimed10[8])+" "+rs.getString(lahtrite_nimed10[9]));
						}
					}
				}
				
				if(valik.equals("8")){
					System.out.println("Sisesta v6rreldav v22rtus1:");
					String v22rtus13 = sisse.readLine();
					System.out.println("Sisesta v6rreldav v22rtus2:");
					String v22rtus14 = sisse.readLine();
					
					String filtreerimisega_n2itamise_k2sk7 = "SELECT * FROM "+tabeli_nimi6+" WHERE "+muutuja1+">="+v22rtus13+" AND "+muutuja1+"<="+v22rtus14+";";
					
					System.out.println("Mitut veergu n2ha soovite (1...10)?");
					String Veergude_arv10 = sisse.readLine();
					int veergude_arv10 =Integer.parseInt(Veergude_arv10);
					
					String lahtrite_nimed11[]=new String[veergude_arv10];
					String lahtri_nimi12;
					int koht10;
					
					for(int i=0; i<veergude_arv10; i++){
						koht10 = i+1;
						System.out.println("Sisesta "+koht10+". tabeliveerg, mida tahad kuvada:");
						lahtri_nimi12 = sisse.readLine();
						lahtrite_nimed11[i] = lahtri_nimi12;
					}
					
					Statement st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs=st1.executeQuery(filtreerimisega_n2itamise_k2sk7);
					
					if(veergude_arv10==1){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed11[0]));
						}
					}
					if(veergude_arv10==2){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed11[0])+" "+rs.getString(lahtrite_nimed11[1]));
						}
					}
					if(veergude_arv10==3){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed11[0])+" "+rs.getString(lahtrite_nimed11[1])
							+" "+rs.getString(lahtrite_nimed11[2]));
						}
					}
					if(veergude_arv10==4){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed11[0])+" "+rs.getString(lahtrite_nimed11[1])
							+" "+rs.getString(lahtrite_nimed11[2])+" "+rs.getString(lahtrite_nimed11[3]));
						}
					}
					if(veergude_arv10==5){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed11[0])+" "+rs.getString(lahtrite_nimed11[1])
							+" "+rs.getString(lahtrite_nimed11[2])+" "+rs.getString(lahtrite_nimed11[3])
							+" "+rs.getString(lahtrite_nimed11[4]));
						}
					}
					if(veergude_arv10==6){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed11[0])+" "+rs.getString(lahtrite_nimed11[1])
							+" "+rs.getString(lahtrite_nimed11[2])+" "+rs.getString(lahtrite_nimed11[3])
							+" "+rs.getString(lahtrite_nimed11[4])+" "+rs.getString(lahtrite_nimed11[5]));
						}
					}
					if(veergude_arv10==7){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed11[0])+" "+rs.getString(lahtrite_nimed11[1])
							+" "+rs.getString(lahtrite_nimed11[2])+" "+rs.getString(lahtrite_nimed11[3])
							+" "+rs.getString(lahtrite_nimed11[4])+" "+rs.getString(lahtrite_nimed11[5])
							+" "+rs.getString(lahtrite_nimed11[6]));
						}
					}
					if(veergude_arv10==8){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed11[0])+" "+rs.getString(lahtrite_nimed11[1])
							+" "+rs.getString(lahtrite_nimed11[2])+" "+rs.getString(lahtrite_nimed11[3])
							+" "+rs.getString(lahtrite_nimed11[4])+" "+rs.getString(lahtrite_nimed11[5])
							+" "+rs.getString(lahtrite_nimed11[6])+" "+rs.getString(lahtrite_nimed11[7]));
						}
					}
					if(veergude_arv10==9){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed11[0])+" "+rs.getString(lahtrite_nimed11[1])
							+" "+rs.getString(lahtrite_nimed11[2])+" "+rs.getString(lahtrite_nimed11[3])
							+" "+rs.getString(lahtrite_nimed11[4])+" "+rs.getString(lahtrite_nimed11[5])
							+" "+rs.getString(lahtrite_nimed11[6])+" "+rs.getString(lahtrite_nimed11[7])
							+" "+rs.getString(lahtrite_nimed11[8]));
						}
					}
					if(veergude_arv10==10){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed11[0])+" "+rs.getString(lahtrite_nimed11[1])
							+" "+rs.getString(lahtrite_nimed11[2])+" "+rs.getString(lahtrite_nimed11[3])
							+" "+rs.getString(lahtrite_nimed11[4])+" "+rs.getString(lahtrite_nimed11[5])
							+" "+rs.getString(lahtrite_nimed11[6])+" "+rs.getString(lahtrite_nimed11[7])
							+" "+rs.getString(lahtrite_nimed11[8])+" "+rs.getString(lahtrite_nimed11[9]));
						}
					}
				}
				
				if(valik.equals("9")){
					System.out.println("Sisesta v6rreldav v22rtus1:");
					String v22rtus15 = sisse.readLine();
					System.out.println("Sisesta v6rreldav v22rtus2:");
					String v22rtus16 = sisse.readLine();
					
					String filtreerimisega_n2itamise_k2sk8 = "SELECT * FROM "+tabeli_nimi6+" WHERE "+muutuja1+"<"+v22rtus15+" OR "+muutuja1+">"+v22rtus16+";";
					
					System.out.println("Mitut veergu n2ha soovite (1...10)?");
					String Veergude_arv11 = sisse.readLine();
					int veergude_arv11 =Integer.parseInt(Veergude_arv11);
					
					String lahtrite_nimed12[]=new String[veergude_arv11];
					String lahtri_nimi13;
					int koht11;
					
					for(int i=0; i<veergude_arv11; i++){
						koht11 = i+1;
						System.out.println("Sisesta "+koht11+". tabeliveerg, mida tahad kuvada:");
						lahtri_nimi13 = sisse.readLine();
						lahtrite_nimed12[i] = lahtri_nimi13;
					}
					
					Statement st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs=st1.executeQuery(filtreerimisega_n2itamise_k2sk8);
					
					if(veergude_arv11==1){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed12[0]));
						}
					}
					if(veergude_arv11==2){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed12[0])+" "+rs.getString(lahtrite_nimed12[1]));
						}
					}
					if(veergude_arv11==3){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed12[0])+" "+rs.getString(lahtrite_nimed12[1])
							+" "+rs.getString(lahtrite_nimed12[2]));
						}
					}
					if(veergude_arv11==4){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed12[0])+" "+rs.getString(lahtrite_nimed12[1])
							+" "+rs.getString(lahtrite_nimed12[2])+" "+rs.getString(lahtrite_nimed12[3]));
						}
					}
					if(veergude_arv11==5){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed12[0])+" "+rs.getString(lahtrite_nimed12[1])
							+" "+rs.getString(lahtrite_nimed12[2])+" "+rs.getString(lahtrite_nimed12[3])
							+" "+rs.getString(lahtrite_nimed12[4]));
						}
					}
					if(veergude_arv11==6){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed12[0])+" "+rs.getString(lahtrite_nimed12[1])
							+" "+rs.getString(lahtrite_nimed12[2])+" "+rs.getString(lahtrite_nimed12[3])
							+" "+rs.getString(lahtrite_nimed12[4])+" "+rs.getString(lahtrite_nimed12[5]));
						}
					}
					if(veergude_arv11==7){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed12[0])+" "+rs.getString(lahtrite_nimed12[1])
							+" "+rs.getString(lahtrite_nimed12[2])+" "+rs.getString(lahtrite_nimed12[3])
							+" "+rs.getString(lahtrite_nimed12[4])+" "+rs.getString(lahtrite_nimed12[5])
							+" "+rs.getString(lahtrite_nimed12[6]));
						}
					}
					if(veergude_arv11==8){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed12[0])+" "+rs.getString(lahtrite_nimed12[1])
							+" "+rs.getString(lahtrite_nimed12[2])+" "+rs.getString(lahtrite_nimed12[3])
							+" "+rs.getString(lahtrite_nimed12[4])+" "+rs.getString(lahtrite_nimed12[5])
							+" "+rs.getString(lahtrite_nimed12[6])+" "+rs.getString(lahtrite_nimed12[7]));
						}
					}
					if(veergude_arv11==9){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed12[0])+" "+rs.getString(lahtrite_nimed12[1])
							+" "+rs.getString(lahtrite_nimed12[2])+" "+rs.getString(lahtrite_nimed12[3])
							+" "+rs.getString(lahtrite_nimed12[4])+" "+rs.getString(lahtrite_nimed12[5])
							+" "+rs.getString(lahtrite_nimed12[6])+" "+rs.getString(lahtrite_nimed12[7])
							+" "+rs.getString(lahtrite_nimed12[8]));
						}
					}
					if(veergude_arv11==10){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed12[0])+" "+rs.getString(lahtrite_nimed12[1])
							+" "+rs.getString(lahtrite_nimed12[2])+" "+rs.getString(lahtrite_nimed12[3])
							+" "+rs.getString(lahtrite_nimed12[4])+" "+rs.getString(lahtrite_nimed12[5])
							+" "+rs.getString(lahtrite_nimed12[6])+" "+rs.getString(lahtrite_nimed12[7])
							+" "+rs.getString(lahtrite_nimed12[8])+" "+rs.getString(lahtrite_nimed12[9]));
						}
					}
				}
				
				if(valik.equals("10")){
					System.out.println("Sisesta v6rreldav v22rtus1:");
					String v22rtus17 = sisse.readLine();
					System.out.println("Sisesta v6rreldav v22rtus2:");
					String v22rtus18 = sisse.readLine();
					
					String filtreerimisega_n2itamise_k2sk9 = "SELECT * FROM "+tabeli_nimi6+" WHERE "+muutuja1+"<="+v22rtus17+" OR "+muutuja1+">"+v22rtus18+";";
					
					System.out.println("Mitut veergu n2ha soovite (1...10)?");
					String Veergude_arv12 = sisse.readLine();
					int veergude_arv12 =Integer.parseInt(Veergude_arv12);
					
					String lahtrite_nimed13[]=new String[veergude_arv12];
					String lahtri_nimi14;
					int koht12;
					
					for(int i=0; i<veergude_arv12; i++){
						koht12 = i+1;
						System.out.println("Sisesta "+koht12+". tabeliveerg, mida tahad kuvada:");
						lahtri_nimi14 = sisse.readLine();
						lahtrite_nimed13[i] = lahtri_nimi14;
					}
					
					Statement st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs=st1.executeQuery(filtreerimisega_n2itamise_k2sk9);
					
					if(veergude_arv12==1){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed13[0]));
						}
					}
					if(veergude_arv12==2){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed13[0])+" "+rs.getString(lahtrite_nimed13[1]));
						}
					}
					if(veergude_arv12==3){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed13[0])+" "+rs.getString(lahtrite_nimed13[1])
							+" "+rs.getString(lahtrite_nimed13[2]));
						}
					}
					if(veergude_arv12==4){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed13[0])+" "+rs.getString(lahtrite_nimed13[1])
							+" "+rs.getString(lahtrite_nimed13[2])+" "+rs.getString(lahtrite_nimed13[3]));
						}
					}
					if(veergude_arv12==5){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed13[0])+" "+rs.getString(lahtrite_nimed13[1])
							+" "+rs.getString(lahtrite_nimed13[2])+" "+rs.getString(lahtrite_nimed13[3])
							+" "+rs.getString(lahtrite_nimed13[4]));
						}
					}
					if(veergude_arv12==6){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed13[0])+" "+rs.getString(lahtrite_nimed13[1])
							+" "+rs.getString(lahtrite_nimed13[2])+" "+rs.getString(lahtrite_nimed13[3])
							+" "+rs.getString(lahtrite_nimed13[4])+" "+rs.getString(lahtrite_nimed13[5]));
						}
					}
					if(veergude_arv12==7){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed13[0])+" "+rs.getString(lahtrite_nimed13[1])
							+" "+rs.getString(lahtrite_nimed13[2])+" "+rs.getString(lahtrite_nimed13[3])
							+" "+rs.getString(lahtrite_nimed13[4])+" "+rs.getString(lahtrite_nimed13[5])
							+" "+rs.getString(lahtrite_nimed13[6]));
						}
					}
					if(veergude_arv12==8){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed13[0])+" "+rs.getString(lahtrite_nimed13[1])
							+" "+rs.getString(lahtrite_nimed13[2])+" "+rs.getString(lahtrite_nimed13[3])
							+" "+rs.getString(lahtrite_nimed13[4])+" "+rs.getString(lahtrite_nimed13[5])
							+" "+rs.getString(lahtrite_nimed13[6])+" "+rs.getString(lahtrite_nimed13[7]));
						}
					}
					if(veergude_arv12==9){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed13[0])+" "+rs.getString(lahtrite_nimed13[1])
							+" "+rs.getString(lahtrite_nimed13[2])+" "+rs.getString(lahtrite_nimed13[3])
							+" "+rs.getString(lahtrite_nimed13[4])+" "+rs.getString(lahtrite_nimed13[5])
							+" "+rs.getString(lahtrite_nimed13[6])+" "+rs.getString(lahtrite_nimed13[7])
							+" "+rs.getString(lahtrite_nimed13[8]));
						}
					}
					if(veergude_arv12==10){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed13[0])+" "+rs.getString(lahtrite_nimed13[1])
							+" "+rs.getString(lahtrite_nimed13[2])+" "+rs.getString(lahtrite_nimed13[3])
							+" "+rs.getString(lahtrite_nimed13[4])+" "+rs.getString(lahtrite_nimed13[5])
							+" "+rs.getString(lahtrite_nimed13[6])+" "+rs.getString(lahtrite_nimed13[7])
							+" "+rs.getString(lahtrite_nimed13[8])+" "+rs.getString(lahtrite_nimed13[9]));
						}
					}
				}
				
				if(valik.equals("11")){
					System.out.println("Sisesta v6rreldav v22rtus1:");
					String v22rtus19 = sisse.readLine();
					System.out.println("Sisesta v6rreldav v22rtus2:");
					String v22rtus20 = sisse.readLine();
					
					String filtreerimisega_n2itamise_k2sk10 = "SELECT * FROM "+tabeli_nimi6+" WHERE "+muutuja1+"<"+v22rtus19+" OR "+muutuja1+">="+v22rtus20+";";
					
					System.out.println("Mitut veergu n2ha soovite (1...10)?");
					String Veergude_arv13 = sisse.readLine();
					int veergude_arv13 =Integer.parseInt(Veergude_arv13);
					
					String lahtrite_nimed14[]=new String[veergude_arv13];
					String lahtri_nimi15;
					int koht13;
					
					for(int i=0; i<veergude_arv13; i++){
						koht13 = i+1;
						System.out.println("Sisesta "+koht13+". tabeliveerg, mida tahad kuvada:");
						lahtri_nimi15 = sisse.readLine();
						lahtrite_nimed14[i] = lahtri_nimi15;
					}
					
					Statement st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs=st1.executeQuery(filtreerimisega_n2itamise_k2sk10);
					
					if(veergude_arv13==1){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed14[0]));
						}
					}
					if(veergude_arv13==2){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed14[0])+" "+rs.getString(lahtrite_nimed14[1]));
						}
					}
					if(veergude_arv13==3){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed14[0])+" "+rs.getString(lahtrite_nimed14[1])
							+" "+rs.getString(lahtrite_nimed14[2]));
						}
					}
					if(veergude_arv13==4){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed14[0])+" "+rs.getString(lahtrite_nimed14[1])
							+" "+rs.getString(lahtrite_nimed14[2])+" "+rs.getString(lahtrite_nimed14[3]));
						}
					}
					if(veergude_arv13==5){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed14[0])+" "+rs.getString(lahtrite_nimed14[1])
							+" "+rs.getString(lahtrite_nimed14[2])+" "+rs.getString(lahtrite_nimed14[3])
							+" "+rs.getString(lahtrite_nimed14[4]));
						}
					}
					if(veergude_arv13==6){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed14[0])+" "+rs.getString(lahtrite_nimed14[1])
							+" "+rs.getString(lahtrite_nimed14[2])+" "+rs.getString(lahtrite_nimed14[3])
							+" "+rs.getString(lahtrite_nimed14[4])+" "+rs.getString(lahtrite_nimed14[5]));
						}
					}
					if(veergude_arv13==7){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed14[0])+" "+rs.getString(lahtrite_nimed14[1])
							+" "+rs.getString(lahtrite_nimed14[2])+" "+rs.getString(lahtrite_nimed14[3])
							+" "+rs.getString(lahtrite_nimed14[4])+" "+rs.getString(lahtrite_nimed14[5])
							+" "+rs.getString(lahtrite_nimed14[6]));
						}
					}
					if(veergude_arv13==8){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed14[0])+" "+rs.getString(lahtrite_nimed14[1])
							+" "+rs.getString(lahtrite_nimed14[2])+" "+rs.getString(lahtrite_nimed14[3])
							+" "+rs.getString(lahtrite_nimed14[4])+" "+rs.getString(lahtrite_nimed14[5])
							+" "+rs.getString(lahtrite_nimed14[6])+" "+rs.getString(lahtrite_nimed14[7]));
						}
					}
					if(veergude_arv13==9){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed14[0])+" "+rs.getString(lahtrite_nimed14[1])
							+" "+rs.getString(lahtrite_nimed14[2])+" "+rs.getString(lahtrite_nimed14[3])
							+" "+rs.getString(lahtrite_nimed14[4])+" "+rs.getString(lahtrite_nimed14[5])
							+" "+rs.getString(lahtrite_nimed14[6])+" "+rs.getString(lahtrite_nimed14[7])
							+" "+rs.getString(lahtrite_nimed14[8]));
						}
					}
					if(veergude_arv13==10){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed14[0])+" "+rs.getString(lahtrite_nimed14[1])
							+" "+rs.getString(lahtrite_nimed14[2])+" "+rs.getString(lahtrite_nimed14[3])
							+" "+rs.getString(lahtrite_nimed14[4])+" "+rs.getString(lahtrite_nimed14[5])
							+" "+rs.getString(lahtrite_nimed14[6])+" "+rs.getString(lahtrite_nimed14[7])
							+" "+rs.getString(lahtrite_nimed14[8])+" "+rs.getString(lahtrite_nimed14[9]));
						}
					}
				}
				
				if(valik.equals("12")){
					System.out.println("Sisesta v6rreldav v22rtus1:");
					String v22rtus21 = sisse.readLine();
					System.out.println("Sisesta v6rreldav v22rtus2:");
					String v22rtus22 = sisse.readLine();
					
					String filtreerimisega_n2itamise_k2sk11 = "SELECT * FROM "+tabeli_nimi6+" WHERE "+muutuja1+"<="+v22rtus21+" OR "+muutuja1+">="+v22rtus22+";";
					
					System.out.println("Mitut veergu n2ha soovite (1...10)?");
					String Veergude_arv14 = sisse.readLine();
					int veergude_arv14 =Integer.parseInt(Veergude_arv14);
					
					String lahtrite_nimed15[]=new String[veergude_arv14];
					String lahtri_nimi16;
					int koht14;
					
					for(int i=0; i<veergude_arv14; i++){
						koht14 = i+1;
						System.out.println("Sisesta "+koht14+". tabeliveerg, mida tahad kuvada:");
						lahtri_nimi16 = sisse.readLine();
						lahtrite_nimed15[i] = lahtri_nimi16;
					}
					
					Statement st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rs=st1.executeQuery(filtreerimisega_n2itamise_k2sk11);
					
					if(veergude_arv14==1){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed15[0]));
						}
					}
					if(veergude_arv14==2){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed15[0])+" "+rs.getString(lahtrite_nimed15[1]));
						}
					}
					if(veergude_arv14==3){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed15[0])+" "+rs.getString(lahtrite_nimed15[1])
							+" "+rs.getString(lahtrite_nimed15[2]));
						}
					}
					if(veergude_arv14==4){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed15[0])+" "+rs.getString(lahtrite_nimed15[1])
							+" "+rs.getString(lahtrite_nimed15[2])+" "+rs.getString(lahtrite_nimed15[3]));
						}
					}
					if(veergude_arv14==5){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed15[0])+" "+rs.getString(lahtrite_nimed15[1])
							+" "+rs.getString(lahtrite_nimed15[2])+" "+rs.getString(lahtrite_nimed15[3])
							+" "+rs.getString(lahtrite_nimed15[4]));
						}
					}
					if(veergude_arv14==6){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed15[0])+" "+rs.getString(lahtrite_nimed15[1])
							+" "+rs.getString(lahtrite_nimed15[2])+" "+rs.getString(lahtrite_nimed15[3])
							+" "+rs.getString(lahtrite_nimed15[4])+" "+rs.getString(lahtrite_nimed15[5]));
						}
					}
					if(veergude_arv14==7){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed15[0])+" "+rs.getString(lahtrite_nimed15[1])
							+" "+rs.getString(lahtrite_nimed15[2])+" "+rs.getString(lahtrite_nimed15[3])
							+" "+rs.getString(lahtrite_nimed15[4])+" "+rs.getString(lahtrite_nimed15[5])
							+" "+rs.getString(lahtrite_nimed15[6]));
						}
					}
					if(veergude_arv14==8){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed15[0])+" "+rs.getString(lahtrite_nimed15[1])
							+" "+rs.getString(lahtrite_nimed15[2])+" "+rs.getString(lahtrite_nimed15[3])
							+" "+rs.getString(lahtrite_nimed15[4])+" "+rs.getString(lahtrite_nimed15[5])
							+" "+rs.getString(lahtrite_nimed15[6])+" "+rs.getString(lahtrite_nimed15[7]));
						}
					}
					if(veergude_arv14==9){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed15[0])+" "+rs.getString(lahtrite_nimed15[1])
							+" "+rs.getString(lahtrite_nimed15[2])+" "+rs.getString(lahtrite_nimed15[3])
							+" "+rs.getString(lahtrite_nimed15[4])+" "+rs.getString(lahtrite_nimed15[5])
							+" "+rs.getString(lahtrite_nimed15[6])+" "+rs.getString(lahtrite_nimed15[7])
							+" "+rs.getString(lahtrite_nimed15[8]));
						}
					}
					if(veergude_arv14==10){
						while(rs.next()){
							System.out.println(rs.getString(lahtrite_nimed15[0])+" "+rs.getString(lahtrite_nimed15[1])
							+" "+rs.getString(lahtrite_nimed15[2])+" "+rs.getString(lahtrite_nimed15[3])
							+" "+rs.getString(lahtrite_nimed15[4])+" "+rs.getString(lahtrite_nimed15[5])
							+" "+rs.getString(lahtrite_nimed15[6])+" "+rs.getString(lahtrite_nimed15[7])
							+" "+rs.getString(lahtrite_nimed15[8])+" "+rs.getString(lahtrite_nimed15[9]));
						}
					}
				}
			}
		}
		
		if(toiming.equals("mitme_tabeli_yhendamine")){
			System.out.println("Mitut tabelit yhendada soovite?");
			String Tabelite_arv = sisse.readLine();
			int tabelite_arv =Integer.parseInt(Tabelite_arv);
			
			String tabelite_nimed[]=new String[tabelite_arv];
			
			for(int i=1; i<tabelite_arv+1; i++){
				System.out.println("Sisesta "+i+". tabeli nimi:");
				String tabeli_nimi = sisse.readLine();
				tabelite_nimed[i-1] = tabeli_nimi;
			}
			
			System.out.println("Millise veeru j2rgi tabeleid yhendada soovite?");
			String yhendav_veerg = sisse.readLine();
			
			String yhendamise_k2sk = "SELECT * FROM ";
			
			for(int i=0; i<tabelite_arv; i++){
				if(i!=tabelite_arv-1){
					yhendamise_k2sk = yhendamise_k2sk + tabelite_nimed[i]+", ";
				}else{
					yhendamise_k2sk = yhendamise_k2sk + tabelite_nimed[i]+" WHERE ";
				}
			}
			
			for(int i=1; i<tabelite_arv; i++){
				if(i!=tabelite_arv-1){
					yhendamise_k2sk = yhendamise_k2sk+tabelite_nimed[0]+"."+yhendav_veerg+"="+ tabelite_nimed[i]+"."+yhendav_veerg+" AND ";
				}else{
					yhendamise_k2sk = yhendamise_k2sk+tabelite_nimed[0]+"."+yhendav_veerg+"="+ tabelite_nimed[i]+"."+yhendav_veerg+";";
				}
			}
			
			System.out.println("Mitut veergu peale yhendava veeru n2ha soovite (1...9)?");
			String Veergude_arv15 = sisse.readLine();
			int veergude_arv15 =Integer.parseInt(Veergude_arv15);
					
			String lahtrite_nimed16[]=new String[veergude_arv15+1];
			String lahtri_nimi17;
			int koht15;
			
			lahtrite_nimed16[0] = tabelite_nimed[0]+"."+yhendav_veerg;
					
			for(int i=1; i<veergude_arv15+1; i++){
				koht15 = i+1;
				System.out.println("Sisesta "+koht15+". tabeliveerg, mida tahad kuvada:");
				lahtri_nimi17 = sisse.readLine();
				lahtrite_nimed16[i] = lahtri_nimi17;
			}
					
			Statement st1=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st1.executeQuery(yhendamise_k2sk);
			
			if(veergude_arv15==1){
				while(rs.next()){
					System.out.println(rs.getString(lahtrite_nimed16[0])+" "+rs.getString(lahtrite_nimed16[1]));
				}
			}
			if(veergude_arv15==2){
				while(rs.next()){
					System.out.println(rs.getString(lahtrite_nimed16[0])+" "+rs.getString(lahtrite_nimed16[1])+
					" "+rs.getString(lahtrite_nimed16[2]));
				}
			}
			if(veergude_arv15==3){
				while(rs.next()){
					System.out.println(rs.getString(lahtrite_nimed16[0])+" "+rs.getString(lahtrite_nimed16[1])
					+" "+rs.getString(lahtrite_nimed16[2])+" "+rs.getString(lahtrite_nimed16[3]));
				}
			}
			if(veergude_arv15==4){
				while(rs.next()){
					System.out.println(rs.getString(lahtrite_nimed16[0])+" "+rs.getString(lahtrite_nimed16[1])
					+" "+rs.getString(lahtrite_nimed16[2])+" "+rs.getString(lahtrite_nimed16[3])
					+" "+rs.getString(lahtrite_nimed16[4]));
				}
			}
			if(veergude_arv15==5){
				while(rs.next()){
					System.out.println(rs.getString(lahtrite_nimed16[0])+" "+rs.getString(lahtrite_nimed16[1])
					+" "+rs.getString(lahtrite_nimed16[2])+" "+rs.getString(lahtrite_nimed16[3])
					+" "+rs.getString(lahtrite_nimed16[4])+" "+rs.getString(lahtrite_nimed16[5]));
				}
			}
			if(veergude_arv15==6){
				while(rs.next()){
					System.out.println(rs.getString(lahtrite_nimed16[0])+" "+rs.getString(lahtrite_nimed16[1])
					+" "+rs.getString(lahtrite_nimed16[2])+" "+rs.getString(lahtrite_nimed16[3])
					+" "+rs.getString(lahtrite_nimed16[4])+" "+rs.getString(lahtrite_nimed16[5])
					+" "+rs.getString(lahtrite_nimed16[6]));
				}
			}
			if(veergude_arv15==7){
				while(rs.next()){
					System.out.println(rs.getString(lahtrite_nimed16[0])+" "+rs.getString(lahtrite_nimed16[1])
					+" "+rs.getString(lahtrite_nimed16[2])+" "+rs.getString(lahtrite_nimed16[3])
					+" "+rs.getString(lahtrite_nimed16[4])+" "+rs.getString(lahtrite_nimed16[5])
					+" "+rs.getString(lahtrite_nimed16[6])+" "+rs.getString(lahtrite_nimed16[7]));
				}
			}
			if(veergude_arv15==8){
				while(rs.next()){
					System.out.println(rs.getString(lahtrite_nimed16[0])+" "+rs.getString(lahtrite_nimed16[1])
					+" "+rs.getString(lahtrite_nimed16[2])+" "+rs.getString(lahtrite_nimed16[3])
					+" "+rs.getString(lahtrite_nimed16[4])+" "+rs.getString(lahtrite_nimed16[5])
					+" "+rs.getString(lahtrite_nimed16[6])+" "+rs.getString(lahtrite_nimed16[7])
					+" "+rs.getString(lahtrite_nimed16[8]));
				}
			}
			if(veergude_arv15==9){
				while(rs.next()){
					System.out.println(rs.getString(lahtrite_nimed16[0])+" "+rs.getString(lahtrite_nimed16[1])
					+" "+rs.getString(lahtrite_nimed16[2])+" "+rs.getString(lahtrite_nimed16[3])
					+" "+rs.getString(lahtrite_nimed16[4])+" "+rs.getString(lahtrite_nimed16[5])
					+" "+rs.getString(lahtrite_nimed16[6])+" "+rs.getString(lahtrite_nimed16[7])
					+" "+rs.getString(lahtrite_nimed16[8])+" "+rs.getString(lahtrite_nimed16[9]));
				}
			}
		}
	}
}

//java -jar target/boot3-1.0-SNAPSHOT.jar baas.Tabelid
//mysql -uif17 -pif17 if17_kukltane
//mvn package