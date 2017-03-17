import java.util.ArrayList;

public class Variable {
	private NumberVar exponent = null;
	private char variable;
	
	//Var with no exponent
	public Variable(char var){
		variable = var;
	}
	//Var with exponent
	public Variable(char var, NumberVar numvar){
		variable = var;
		exponent = numvar;
	}
	public String toString(){
		if(exponent==null){
			return ""+variable;
		}
		else{
			//If there is just one num/variable
			if(exponent.getSize()==1&&exponent.getQuantity()==null){
				//One var, no num
				return variable+"^"+exponent.getVars().get(0).toString();
			}
			//No var, one num
			else if(exponent.getSize()==0&&exponent.getQuantity()!=null){
				return variable+"^"+exponent.getQuantity().toString();
			}
			//If multiple num/vars
			else{
				//Check if there are only vars
				if(exponent.getQuantity()==null){
					String temp = "";
					for(Variable v : exponent.getVars()){
						temp+=v.toString();
					}
					return variable+"^("+temp+")";
				}
				//If there are also nums
				else{
					String temp = "";
					for(Variable v : exponent.getVars()){
						temp+=v.toString();
					}
					return variable+"^("+exponent.getQuantity().toString()+temp+")";
				}
			}
		}
		
	}
	
	//get var
	public String getVar(){
		return ""+variable;
	}
}
