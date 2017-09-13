//This class is just handling numbers by themselves
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
			return num + "^" + exponent.toString();
		}
		
	}
	public String getNumber(){
		return ""+ num;
	}
}
