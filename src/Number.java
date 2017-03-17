
public class Number {
	private NumberVar exponent = null;
	private int num = 0;
	
	//Number with no exponent
	public Number(int Num){
		num = Num;
	}
	public Number(int Num, NumberVar exp){
		//Number with exponent
		num = Num;
		exponent = exp;
	}
	
	//get exponent
	public NumberVar getExponent(){
		return exponent;
	}
	//print
	public String toString(){
		if(exponent==null){
			return ""+num;
		}
		else{
			//If there is just one num/variable
			if(exponent.getSize()==1&&exponent.getQuantity()==null){
				//One var, no num
				return num+"^"+exponent.getVars().get(0).toString();
			}
			//No var, one num
			else if(exponent.getSize()==0&&exponent.getQuantity()!=null){
				return num+"^"+exponent.getQuantity().toString();
			}
			//If multiple num/vars
			else{
				//Check if there are only vars
				if(exponent.getQuantity()==null){
					String temp = "";
					for(Variable v : exponent.getVars()){
						temp+=v.toString();
					}
					return num+"^("+temp+")";
				}
				//If there are also nums
				else{
					String temp = "";
					for(Variable v : exponent.getVars()){
						temp+=v.toString();
					}
					return num+"^("+exponent.getQuantity().toString()+temp+")";
				}
			}
		}
		
	}
}
