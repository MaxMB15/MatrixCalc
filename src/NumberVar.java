import java.util.ArrayList;
import java.util.Collections;

public class NumberVar {
	ArrayList<Character> varLets = new ArrayList<Character>();
	int quantity = 1;
	ArrayList<Multiplicity> multiplicities = null;
	public NumberVar(String s){
		//Make sure only one number
		String num = "";
		Boolean oneNum = true;
		//Analyze through s
		char[] data = s.toCharArray();
		for(int i = 0;i<data.length;i++){
			if(Character.isAlphabetic(data[i])){
				if(!num.equals("")){
					oneNum = false;
				}
				varLets.add(data[i]);
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
					multiplicities.add(new Multiplicity(t,varLets.get(varLets.size()-1)));
				}
				else{
					
				}
			}
			//Improper Char
			else{
				System.out.println("ERROR! This character is invalid: "+data[i]+"!");
				System.exit(1);
			}
		}
		
		Collections.sort(varLets);
	}
}
