package project;
import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

public class MagicP {

	public static void main(String[] args) {
	
		IloNumExpr current_points = null; 
		
	try {
	
		//Model für Berechnung von p*
		IloCplex cplex = new IloCplex();
		
		//Variable für Bedingung
		IloNumVar z = cplex.numVar(0, Double.MAX_VALUE, "z");
		//Intervall von den Variablen {0,1}
		double[] lb = {0.0,0.0,0.0};
		double[] ub = {1.0,1.0,1.0};
		
		// Ein Array welches die Variablen abspeicher;
		IloNumVar[] x  = cplex.numVarArray(3,lb, ub);
		// Eine Funktion, die p* berechnet ??
		
		/** Koeffizient ??
		 *double[] objvals= {3.0,1.0,3.0};
		 */
		//Funktion ??
		
		IloLinearNumExpr obj = cplex.linearNumExpr();
		IloLinearNumExpr points = cplex.linearNumExpr(40);
		//Summe der Ergebnisse  der Heimspiele
		for(int i = 0; i <= 18 ; i++)
		{
			for(int j = 0; j <= home_games ; j++)
			{
			obj.addTerm(x[0],3.0);
			}
		//Summe der Ergebnisse der Auswärtsspiele
			for(int j = 0; j <= away_games ; j++)
			{
			obj.addTerm(x[1],3);	
			}
		//Summe der Ergebnisse aller unentschiedene Spiele
			for(int j = 0; j <= (away_games+home_games); j++)
			{
			obj.addTerm(x[2], 1);	
			}
			obj.add(points);
		}
		// Bedingungen der Funktion
		cplex.addLe(obj, z);
		
		
		cplex.addMinimize(cplex.scalProd(x,objvals));
		// >= z
		cplex.addLe(cplex.sum(cplex.prod(3.0,x[0]), cplex.prod(1.0,x[1]), cplex.prod(3.0,x[2])),40);
		cplex.addEq(cplex.sum(x[0],x[1],x[2]),1);
		
		System.out.println("Test");
	
			if(cplex.solve()){
				System.out.println(("Solution status=" + cplex.getObjValue()));
				System.out.println(("Solution value= " + cplex.getStatus())); 

			}else{
			System.out.println("not possible");
			}
		
		/*	IloCplex cplex = new IloCplex();
		
		IloNumVar[] x = cplex.numVarArray(3, 0.0, 1.0);
	
		double[] objvals = {3.0, 1.0, 3.0};
		cplex.addMinimize(cplex.scalProd(x, objvals));
		
		
		cplex.addLe(cplex.sum(cplex.prod(3.0, x[0]),
				cplex.prod( 1.0, x[1]),
				cplex.prod( 3.0, x[2])), z);
		
		*/
	// 	Summe: CplexModeler.Sum Method (IIntExpr[], Int32, Int32)	
	//	Creates and returns an integer expression that is the sum of a number of expressions, 
	//	starting from the specified index, of an array of integer expressions. 
		
	//	x[0] + 2*x[1] + 3*x[2]                     IloNumExpr expr = cplex.sum(x[0],cplex.prod(2.0, x[1]),cplex.prod(3.0, x[2]));
		
		
	
		
		
		
		
	} catch (IloException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
}
