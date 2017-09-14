import java.util.ArrayList;

public class NumberVar {
	private ArrayList<Variable> vars = new ArrayList<Variable>();
	private Number quantity = null;
	
	ArrayList<Character> operands = new ArrayList<Character>();
	public NumberVar(String s){
		//cases I should look out for
		//* -4ac
		//* 5a
		//* ac
		//First couple of chars should always be numeric or else quantity is null
		int i = 0;
		char[] sChars = s.toCharArray();
		String qntNumb = "";
		//go through all chars till alpha char is present
		for(;i<s.length();i++){
			if(Character.isDigit(sChars[i]) || (sChars[i] == '-' && i == 0)){
				qntNumb += sChars[i];
			}
			//The beginning of vars
			else if(Character.isAlphabetic(sChars[i])){
				break;
			}
			//Unrecognized char
			else{
				
			}
		}
		
	}
	//get the vars in the ArrayList
	public ArrayList<Variable> getVars(){
		return vars;
	}
	//get the quantity
	public Number getQuantity(){
		return quantity;
	}
	//Print
	public String toString(){
		String temp = "";
		for(Variable v : vars){
			temp+=v.toString();
		}
		return quantity.toString()+temp;
	}
}
