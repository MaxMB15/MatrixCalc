import java.util.ArrayList;

public class NumberVar {
	private ArrayList<Variable> variables = new ArrayList<Variable>();
	private Number quantity = null;
	
	private Expression exponent = null;
	
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
			//If there is an exponent
			if(sChars[i] == '^'){
				if(sChars[i+1] == '('){
					//to get first char in parenthesis 
					i+=2;
					int depth = 0;
					String data = "";
					while(!(sChars[i] == ')' && depth == 0)){
						//update depth so parenthesis match
						if(sChars[i] == '('){
							depth++;
						}
						else if(sChars[i] == ')'){
							depth--;
						}
						//copy
						data += sChars[i];
						
						//if cannot find ending parenthesis
						if(i<s.length()){
							try {
								throw new Exception("Exponent formating error!!!");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						i++;
					}
					//If no errors, program should get here:
					
					break;
				}
				else{
					exponent = new NumberVar(""+sChars[i+1]);
					break;
				}
			}
			else if(Character.isDigit(sChars[i]) || (sChars[i] == '-' && i == 0)){
				qntNumb += sChars[i];
			}
			//The beginning of vars
			else if(Character.isAlphabetic(sChars[i])){
				quantity = new Number(Integer.parseInt(qntNumb));
				break;
			}
			//Unrecognized char
			else{
				try {
					throw new Exception("Formating Error!!!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//Add vars to variables list
		for(;i<s.length();i++){
			if(Character.isAlphabetic(sChars[i])){
				variables.add(new Variable(sChars[i]));
			}
			//Unrecognized char
			else{
				try {
					throw new Exception("Formating Error!!!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	//get the vars in the ArrayList
	public ArrayList<Variable> getVars(){
		return variables;
	}
	//get the quantity
	public Number getQuantity(){
		return quantity;
	}
	//Print
	public String toString(){
		String temp = "";
		for(Variable v : variables){
			temp+=v.toString();
		}
		return quantity.toString()+temp;
	}
}
