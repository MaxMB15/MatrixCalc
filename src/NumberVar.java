import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class NumberVar {
	private ArrayList<Variable> vars = new ArrayList<Variable>();
	private Number quantity = null;
	
	public NumberVar(String s){
		//Make sure only one number
		String num = "";
		Boolean oneNum = true;
		char prevChar = 0;
		//Analyze through s
		char[] data = s.toCharArray();
		for(int i = 0;i<data.length;i++){
			if(Character.isAlphabetic(data[i])){
				if(!num.equals("")){
					oneNum = false;
					if(quantity==null){
						quantity = new Number(Integer.parseInt(num));						
					}
				}
				vars.add(new Variable(data[i]));
			}
			else if(Character.isDigit(data[i])){
				//Check if can append number
				if(oneNum){
					num+=data[i];
				}
				else{
					System.out.println("ERROR! Multiple Numbers in one var!");
					System.exit(1);
				}
			}
			//Check if negative
			else if(data[i]=='-'){
				//Make sure "-" format is correct
				if(!num.equals("")){
					num+=data[i];
				}
				else{
					System.out.println("ERROR! \"-\" format is incorrect!");
					System.exit(1);
				}
			}
			//Multiplicity
			else if(data[i]=='^'){
				//Make another NumberVar
				//get whatever was before{
				if(Character.isDigit(data[i-1])){
					prevChar = 0;
					//default with number
				}
				else if(Character.isAlphabetic(data[i-1])){
					prevChar = data[i-1];
				}
				else{
					System.out.println("ERROR! Previous Char is not valid!");
					System.exit(1);
				}
				
				//if there are parentheses, take what ever is inside
				i++;
				if(data[i]=='('){
					//Make a new Sting to give to NumberVar again
					i++;
					String t = "";
					while(data[i]!=')'){
						t+=data[i];
						i++;
					}
					//if number
					if(prevChar==0){
						quantity = new Number(Integer.parseInt(num), new NumberVar(t));
					}
					//if var
					else{
						vars.remove(vars.size()-1);
						vars.add(new Variable(prevChar,new NumberVar(t)));
					}
				}
				//no parentheses
				else{
					//if number
					if(prevChar==0){
						quantity = new Number(Integer.parseInt(num), new NumberVar(""+data[i]));
					}
					//if var
					else{
						vars.remove(vars.size()-1);
						vars.add(new Variable(prevChar,new NumberVar(""+data[i])));
					}
				}
			}
			//Improper Char
			else{
				System.out.println("ERROR! This character is invalid: "+data[i]+"!");
				System.exit(1);
			}
		}
		
		//if no vars, push number
		if(quantity==null&&!num.equals("")){
			quantity = new Number(Integer.parseInt(num));
		}
		Collections.sort(vars, new Comparator<Variable>(){
			@Override
			public int compare(Variable o1, Variable o2) {
				return o1.getVar().compareToIgnoreCase(o2.getVar());
			}
			
		});
	}
	//Get size of how
	public int getSize(){
		return vars.size();
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
