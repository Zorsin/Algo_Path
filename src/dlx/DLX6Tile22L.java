package dlx;
import java.util.LinkedList;

import javax.swing.JOptionPane;

/**
 * Algorithmen und Datenstrukturen Übung 6
 * 
 * 
 * @author Matthias Bauer, Marko Radocaj, Cagdas Bektas
 *
 *Tabelle:
 *Es gibt für t(2) insgesamt 5 Lösungen
 *Es gibt für t(3) insgesamt 8 Lösungen
 *Es gibt für t(4) insgesamt 37 Lösungen
 *Es gibt für t(5) insgesamt 136 Lösungen
 *Es gibt für t(6) insgesamt 545 Lösungen
 *Es gibt für t(7) insgesamt 2376 Lösungen
 *Es gibt für t(8) insgesamt 10534 Lösungen
 *Es gibt für t(9) insgesamt 46824 Lösungen
 *Es gibt für t(10) insgesamt 212926 Lösungen
 *Es gibt für t(11) insgesamt 961552 Lösungen
 *Es gibt für t(12) insgesamt 4374949 Lösungen
 *Es gibt für t(13) insgesamt 19888832 Lösungen
 *Es gibt für t(14) insgesamt 90570555 Lösungen
 */


public class DLX6Tile22L {
	public static int cnt = 0;
	public static DLXNode h = new DLXNode();
	private static LinkedList<int[]> kombinationen;//Enthält alle Teilmengen
	 public static DLXNode[][] matrix;//Matrix um die Nodes zu verknüpfen
	 
//////////////CODE VON HERRN HEINZ///////////////////////////////////////////////////////////////////////////////////
	  static class DLXNode{
		// represents 1 element or header
		DLXNode C; // reference to column-header
		DLXNode L, R, U, D; // left, right, up, down references
		DLXNode(){
			C=L=R=U=D=this; 
			} // supports circular lists
		}
	  
	  public static void search (int k){ // finds & counts solutions
		  if (h.R == h)
		  {
			  cnt++;
		      return;
		  } // if empty: count & done
		           DLXNode c = h.R; // choose next column c
		            cover(c); // remove c from columns
		  for (DLXNode r=c.D; r!=c; r=r.D) 
		  { // forall rows with 1 in c
		  for (DLXNode j=r.R; j!=r; j=j.R) // forall 1-elements in row
		       cover(j.C); // remove column
		       search(k+1); // recursion
		  for (DLXNode j=r.L; j!=r; j=j.L) // forall 1-elements in row
		         uncover(j.C); // backtrack: un-remove
		         }
		  uncover(c); // un-remove c to columns
		  }
	  
	  public static void cover (DLXNode c){ // remove column c
		  c.R.L = c.L ; // remove header
		  c.L.R = c.R ; // .. from row list
		  for (DLXNode i=c.D; i!=c; i=i.D) // forall rows with 1
		  for (DLXNode j=i.R; i!=j; j=j.R)
		  { // forall elem in row
		        j.D.U = j.U ; // remove row element
		      j.U.D = j.D ; // .. from column list
		  }
		  }
	  
		  public static void uncover (DLXNode c){//undo remove col c
		  for (DLXNode i=c.U; i!=c; i=i.U){ 
				// forall rows with 1
		  for (DLXNode j=i.L; i!=j; j=j.L){ // forall elem in row
		      j.D.U = j ; // un-remove row elem
		      j.U.D = j ; // .. to column list
		  }}
		  c.R.L = c ; // un-remove header
		  c.L.R = c ; // .. to row list
		  }
			
 
///////////Eigener Code////////////////////////////////////////////////////////////////////////////////////////
		  
		  /**
		   * Methode, die uns die Knoten mit Hilfe einer Matrix verknüpft.
		   * 
		   * @param feldgroße
		   * @param anker
		   * @param ausgabe
		   */
			public static void matrixErstellen(int feldgroße, DLXNode anker,int ausgabe)
			{
				boolean erstesElement =true;
				DLXNode[][] matrix = new DLXNode[feldgroße][kombinationen.size() + 1 ];
				
				DLXNode letzterSpaltenkopf = new DLXNode();//Referenz auf letzten Spaltenkopf
			
				for(int i = 0;i<=feldgroße-1;i++)//Spaltenköpfe erstellen und verknüpfen
				{
					matrix[i][0] = new DLXNode();
					matrix[i][0].C = matrix[i][0];
					//Wenn i>0 dann ist letzter Spaltenkopf bereits bekannt
					if(i>0){
						letzterSpaltenkopf.R = matrix[i][0];
					matrix[i][0].L = letzterSpaltenkopf;
					}
					letzterSpaltenkopf = matrix[i][0];
				}
				//Anker verknüpfen
				letzterSpaltenkopf.R = anker;
				anker.L = letzterSpaltenkopf;
				anker.R = matrix[0][0];
				matrix[0][0].L = anker;
				
				
				for(int i = 0; i<=kombinationen.size()-1;i++)//Zeilen erstellen + verknüpfen
				{
					DLXNode ersterZeilenEintrag = new DLXNode();//erster Zeileneintrag
					DLXNode letzterZeilenEintrag = new DLXNode();//letzter Zeileneintrag
					int[] reihe = kombinationen.get(i);
					//zugehörige Spalte bestimmen
					for(int ii = 0; ii<=reihe.length-1;ii++)
					{
						int spalte = reihe[ii] -1;//-1 da spalten von 0 bis felgröße -1 gehen
						matrix[spalte][i+1] = new DLXNode();
						matrix[spalte][i+1].C = matrix[spalte][0];//Stimmt das?
						//verknüpfen
						if(ii>0)//Wenn i>0 dann ist letzterZeilenEintrag bereits bekannt
						{
						matrix[spalte][i+1].L = letzterZeilenEintrag;
						letzterZeilenEintrag.R=matrix[spalte][i+1];
						}
						else {
							ersterZeilenEintrag = matrix[spalte][i+1];
						}
								
						letzterZeilenEintrag = matrix[spalte][i+1];
					}
					//erste und letzte Zeile verknüpfen
					ersterZeilenEintrag.L = letzterZeilenEintrag;
					letzterZeilenEintrag.R = ersterZeilenEintrag;
					}
				
				
				for(int i =0 ;i<=feldgroße-1;i++)//Spalten verküpfen
				{
					erstesElement = true;
					DLXNode letzterSpaltenEintrag = new DLXNode();
					for(int ii = 0;ii<=kombinationen.size()-1;ii++)//Alle Elemene einer Spalte durchgehen
					{
						if(matrix[i][ii+1] instanceof DLXNode)//Wenn Element in der Matrix vom Typ DLX Node ist
						{
							if(erstesElement)//und erstes Element
							{
								matrix[i][0].D = matrix[i][ii+1];
								 matrix[i][ii+1].U = matrix[i][0];
								erstesElement = false;
								letzterSpaltenEintrag = matrix[i][ii+1];
							}
							else{
								letzterSpaltenEintrag.D = matrix[i][ii+1];
								matrix[i][ii+1].U = letzterSpaltenEintrag;
								letzterSpaltenEintrag = matrix[i][ii+1];
							}
							matrix[i][ii+1].C = matrix[i][0];//Referenz auf Spaltenkopf
						}

				    }
					letzterSpaltenEintrag.D = matrix[i][0];
					matrix[i][0].U = letzterSpaltenEintrag;
			}
			    if(ausgabe == JOptionPane.YES_OPTION)//Wenn Matrix ausgegeben werden soll
			    ma(feldgroße,matrix);
			}

			
			/**
			 * Methode zur Ausgabe der Matrix, falls erwünscht.
			 * 
			 * @param feldgröße
			 * @param matrix
			 */
			public static void ma(int feldgröße, DLXNode[][] matrix)
			{
	             String spalten2 = "";
				for (int i = 1; i<= feldgröße;i++)//Beschriftet die Spalten
				{
					if(i==feldgröße/2)
					{
						spalten2 = spalten2 +"Spaltenzahl = " + feldgröße;
					}
					else{
						spalten2 = spalten2 + "----"; 
					}
				}
				System.out.println(spalten2);
				for(int ii = 0;ii<=kombinationen.size(); ii++)//Für jede Zeile
				{
					String zeile = "| ";
					for (int i = 0;i<=feldgröße-1;i++)//gehe die Spalten durch
					{
	
						if (matrix[i][ii]!=null)//Setze eine 1 wenn die Matrix an dieser Stelle schon einen Node hat
						{
							zeile = zeile + 1 + " | ";
						}
						else //Ansonsten 0
						{
							zeile = zeile + 0 + " | ";
						}
						
					}
					if(ii==0)//1 Reihe wird beschriftet
					{
						System.out.println(zeile + "- " + ii + " Zeilen = Anzahl Teilmengen");
					}
					else 
					{
						System.out.println(zeile + "- " + ii + "");
					}
	
				}
			}
			
			
			/**
			 * Erstellt alle Teilmengen
			 * @param n
			 * @param feldgröße
			 */
			public static void teilmengenErstellen(int n,int feldgröße){
				//2x2 quadrate erstellen
				int zähler = 1;
				kombinationen = new LinkedList<int[]>();
				for(int i = 1; i<=feldgröße-3;i++)
				{
					if(i%n!=0&&zähler<6)//solange noch ein Quadrat möglich ist
					{
					int a = i;
					int b = i+1;
					int c = b + (n-1);
					int d = c+1;
					kombinationen.add(new int[]{a,b,c,d});//füge 2x2 Quadrat der Liste hinzu
					}
					else//ansonsten erhöhe Zähler damit man sieht, ob man schon am Ende der Liste ist
					{
						zähler++;
					}
				}
				//L-Trominos erstellen
				int kombiGröße = kombinationen.size();
				for (int i = 0;i<=kombiGröße-1;i++)//Hole nächstes Quadrate aus den Kombinationen
				{
					int [] temp = kombinationen.get(i);
					for(int ii = 0;ii<=temp.length;ii++)//Entferne jeweils eine Stelle des Quadrats und füge es den Kombinationen hinzu
					{
						switch (ii){
						
						case 0:  kombinationen.add(new int[]{temp[1],temp[2],temp[3]});
						        break;
						case 1:   kombinationen.add(new int[]{temp[0],temp[2],temp[3]});
				                  break;
						case 2: kombinationen.add(new int[]{temp[0],temp[1],temp[3]});
		                          break;
						case 3: kombinationen.add(new int[]{temp[0],temp[1],temp[2]});
                              break;
						}
					}
				 }
			
				}
				
				
			
			  
			 public static void main(String[] args){ 
				 
				
				 String nEingabe = (String)JOptionPane.showInputDialog(null,"Bitte geben Sie ein n ein","n eingeben",JOptionPane.PLAIN_MESSAGE,null,null, null);
				 int nEingabeInt = Integer.parseInt(nEingabe);

				   if ((int)nEingabeInt==1)//Wenn n=1 kann es keine Lösung geben
				   {
					   System.out.println("Es gibt für t(1) insgesamt 0 Lösungen");
				   }
				   if ((int)nEingabeInt==0)//Wenn n= 0 kann es nur eine Lösung geben
				   {
					   System.out.println("Es gibt für t(0) insgesamt 1 Lösung");
				   }
				   if ((int)nEingabeInt>1)//für n>1 berechne Lösungen
				   {
					   int matrixAusgeben = JOptionPane.showConfirmDialog(null, "Wollen Sie die Matrix mit ausgeben?", "Matrix ausgeben?", JOptionPane.YES_NO_OPTION);
					   int feldgröße = 6 * nEingabeInt;
				   teilmengenErstellen(nEingabeInt,feldgröße);
				    matrixErstellen(feldgröße,h,matrixAusgeben);
				    search(0);
				   System.out.println("Es gibt für t(" + nEingabeInt + ") insgesamt " + cnt + " Lösungen");
			 }
			
				   

				   
	  
}}
