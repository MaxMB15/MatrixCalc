//This class handles variables by themselves
public class Variable {
	private NumberVar exponent = null;
	private char variable;
	
	//Var with no exponent
	public Variable(char var){
		variable = var;
	}
	//Var with exponent
	public Variable(char var, NumberVar exp){
		variable = var;
		exponent = exp;
	}
	public String toString(){
		if(exponent==null){
			return ""+variable;
		}
		else{
			return variable + "^" + exponent.toString();
		}
	}
	
	//get var
	public String getVar(){
		return ""+variable;
	}
}
