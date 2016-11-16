package test;

import java.util.List;

import javax.xml.ws.Service;

import de.msiggi.sportsdata.webservices.ArrayOfMatchdata;
import de.msiggi.sportsdata.webservices.Fussballdaten;
import de.msiggi.sportsdata.webservices.Matchdata;
import de.msiggi.sportsdata.webservices.Sportsdata;
import de.msiggi.sportsdata.webservices.SportsdataSoap;

public class readingFromWeb {
	public static void main (String[] args) {
		// Verbindung zum Webservice herstellen
		Sportsdata sportsdata = new Sportsdata();
		// Serviceobject erstellen um die Daten abzufragen
		SportsdataSoap service = sportsdata.getSportsdataSoap();
		//Hole Daten
		ArrayOfMatchdata arrMatchdata = service.getMatchdataByLeagueSaison("bl1", "2016");
	
		List<Matchdata> mdata = arrMatchdata.getMatchdata();
		
		for(Matchdata mdata2: mdata) {
			if(mdata2.isMatchIsFinished() == true){
			System.out.println("Liga: " + mdata2.getLeagueName() + " " + mdata2.getLeagueSaison() +
										" Tag " + mdata2.getMatchDateTime() +
										" Team1: " 	+ mdata2.getIdTeam1() + " " + mdata2.getNameTeam1() +
										" Team2: " 	+ mdata2.getIdTeam2() + " " + mdata2.getNameTeam2() +
										" MatchID "	+ mdata2.getMatchResults().getMatchResult().get(1).getPointsTeam1() + ":" + mdata2.getMatchResults().getMatchResult().get(1).getPointsTeam2() + "\n");
			}
		}
	}
}
