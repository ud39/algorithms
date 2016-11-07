
package test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import de.msiggi.sportsdata.webservices.ArrayOfMatchdata;
import de.msiggi.sportsdata.webservices.Matchdata;
import de.msiggi.sportsdata.webservices.Sport;
import de.msiggi.sportsdata.webservices.Sportsdata;
import de.msiggi.sportsdata.webservices.SportsdataSoap;

public class readingFromWeb2 {
	public static void main (String[] args) {
		int resultT1= -1;
		int resultT2= -1;
		String text;
		ArrayList<String> arrString = new ArrayList<String>();
		Sportsdata sportsdata = new Sportsdata();
		SportsdataSoap service = sportsdata.getSportsdataSoap();
		ArrayOfMatchdata arrMatchdata = service.getMatchdataByLeagueSaison("bl1", "2016");

		//Hole Matchdaten
		List<Matchdata> mdata = arrMatchdata.getMatchdata();

		for(Matchdata mdata2: mdata) { //Lese Matches aus
			/*
			  System.out.println("Liga: " + mdata2.getLeagueName() + " " + mdata2.getLeagueSaison() +
										" Tag " + mdata2.getGroupOrderID() +
										" Team1: " 	+ mdata2.getIdTeam1() + " " + mdata2.getNameTeam1() +
										" Team2: " 	+ mdata2.getIdTeam2() + " " + mdata2.getNameTeam2());

			*/
			if(mdata2.isMatchIsFinished()) { //Endergebnis vorhanden
				//System.out.println(" Ergebnis "	+ mdata2.getMatchResults().getMatchResult().get(1).getPointsTeam1()+ ":" + mdata2.getMatchResults().getMatchResult().get(1).getPointsTeam2());
				resultT1 = mdata2.getMatchResults().getMatchResult().get(1).getPointsTeam1();
				resultT2 = mdata2.getMatchResults().getMatchResult().get(1).getPointsTeam2();

			} else { //Kein Endergebnis
				//System.out.println(" Ergebnis "	+ mdata2.getPointsTeam1() + ":" + mdata2.getPointsTeam2());
				resultT1= -1;
				resultT2= -1;
			}

			//Formatiert Spieltag, Team1 (Name), Team2 (Name), Ergebnis wie besprochen
			//getrennt durch Komma
			text = mdata2.getGroupOrderID() + ":" + mdata2.getNameTeam1() + ":" + mdata2.getNameTeam2() + ":";

			if(resultT1 < 0 || resultT2 < 0) { // Kein Ergebnis
	    		text = text + "?\n";
	    	} else if(resultT1 > resultT2) { // Sieg
	    		text = text + "S\n";
			} else if (resultT1 < resultT2) { // Niederlage
				text = text + "N\n";
			} else { // Unentschieden
				text = text + "U\n";
			}

			arrString.add(text); //Speicher fürs schreiben in die Datei
		}


		Writer writer = null;
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("C:\\Users\\World\\Desktop\\Tabelle2.txt")));
		    for(String s1: arrString) {
		    	writer.write(s1);
		    }
		} catch (IOException ex) {
		  //ignore
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
	}
}


