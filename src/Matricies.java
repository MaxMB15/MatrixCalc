import java.awt.Point;
import java.util.ArrayList;

public class Matricies {
	//static String input = "-1	0	0	0	⎤⎦⎥⎥⎥⎥⎥⎥⎥] -5	-5	0	0 -9	-3	-3	0 -4	-7	-7	-5";
	//static String input = "-1	0	⎤⎦⎥⎥⎥⎥⎥⎥⎥] -5	-4";
	static String input = "1a	0	0		⎤⎦⎥⎥⎥⎥⎥⎥⎥] -5	-5	0	0 -9	-3";

	//(5,−1),,(−6,−7),(−3,7), and (−14,1)
	public static void main(String[] args){
		Matrix m = new Matrix(input);
		m.print();
		System.out.println(m.derivative());
		System.out.println("");
		String s = "2^e^4+5*2";
		System.out.println(s);
		System.out.println(new NumberVar(s).toString());
		//System.out.println(findDet());
		//Point[] p = {new Point(0,4),new Point(5,-5),new Point(17,-13),new Point(12,-4)};
		//System.out.println(polyArea(p));
	}
	static int polyArea(Point[] p){
		int temp = 0;
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
		matrix.add(new ArrayList<Integer>());
		matrix.add(new ArrayList<Integer>());
		matrix.get(0).add(0);
		matrix.get(0).add(0);
		matrix.get(1).add(0);
		matrix.get(1).add(0);
		for(int i=0;i<p.length;i++){
			matrix.get(0).set(0, p[i].x);
			matrix.get(0).set(1, p[i].y);
			if(i+1>=p.length){
				matrix.get(1).set(0, p[0].x);
				matrix.get(1).set(1, p[0].y);	
			}
			else{
				matrix.get(1).set(0, p[i+1].x);
				matrix.get(1).set(1, p[i+1].y);				
			}
			temp+=detLogic(matrix);
		}
		temp/=2;
		
		return temp;
	}
	static String findDet(){
		String output = "";
		String temp = "";
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
		matrix.add(new ArrayList<Integer>());
		//reformat input
		int i = 0;
		char[] arrayT = input.toCharArray();
		while(i<input.length()){
			if(Character.isDigit(arrayT[i])||arrayT[i] == '-'){
				temp+=arrayT[i];
			}
			else if(arrayT[i]=='⎥'){
				break;
			}
			else{
				if(!temp.equals("")){
					matrix.get(0).add(new Integer(Integer.parseInt(temp)));
					temp = "";
				}
			}
			i++;
		}
		//after the first row
		int cols = matrix.get(0).size();
		int rowNum = 1;
		if(!(i>=input.length()-1)){
			matrix.add(new ArrayList<Integer>());							
		}
		while(i<input.length()){
			if(Character.isDigit(arrayT[i])||arrayT[i] == '-'){
				temp+=arrayT[i];
				if(i==input.length()-1){
					matrix.get(rowNum).add(new Integer(Integer.parseInt(temp)));
					temp = "";
				}
			}
			else{
				if(!temp.equals("")){
					matrix.get(rowNum).add(new Integer(Integer.parseInt(temp)));
					temp = "";
					if(matrix.get(rowNum).size()>=cols){
						rowNum++;
						matrix.add(new ArrayList<Integer>());
					}
				}
			}
			i++;
		}
		
		
		
		
		//determinate logic
		printMatrix(matrix);
		System.out.println();
		System.out.println(detLogic(matrix));
		return "";
	}
	static int detLogic(ArrayList<ArrayList<Integer>> matrix){
		int output = 0;
		int addsub = 1;
		if(matrix.size()==1){
			return matrix.get(0).get(0);
		}
		for(int i = 0;i<matrix.get(0).size();i++){
			output += addsub*matrix.get(0).get(i)*detLogic(remRC(matrix,new Point(i,0)));
			addsub*=-1;
		}
		return output;
	}
	//static ArrayList<ArrayList<String>> toStringMatrix()
	//static String simplify(String s){
		
	//}
	static String detSLogic(ArrayList<ArrayList<String>> matrix){
		String output = "";
		int addsub = 1;
		if(matrix.size()==1){
			return matrix.get(0).get(0);
		}
		for(int i = 0;i<matrix.get(0).size();i++){
			output += "("+addsub+"*"+matrix.get(0).get(i)+"*"+detSLogic(remSRC(matrix,new Point(i,0)))+")";
			addsub*=-1;
		}
		return output;
	}
	static ArrayList<ArrayList<Integer>> remRC(ArrayList<ArrayList<Integer>> matrix,Point p){
		ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();
		int offset = 0;
		for(int y = 0; y<matrix.size();y++){
			
			if(y==p.y){
				offset++;
			}
			else{
				output.add(new ArrayList<Integer>());
				for(int x = 0; x<matrix.get(0).size();x++){
					if(x!=p.x){
						output.get(y-offset).add(matrix.get(y).get(x));
					}
				}
			}
		}
		return output;
	}
	static ArrayList<ArrayList<String>> remSRC(ArrayList<ArrayList<String>> matrix,Point p){
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		int offset = 0;
		for(int y = 0; y<matrix.size();y++){
			
			if(y==p.y){
				offset++;
			}
			else{
				output.add(new ArrayList<String>());
				for(int x = 0; x<matrix.get(0).size();x++){
					if(x!=p.x){
						output.get(y-offset).add(matrix.get(y).get(x));
					}
				}
			}
		}
		return output;
	}
	static void printMatrix(ArrayList<ArrayList<Integer>> matrix){
		//Print matrix
		for(int y = 0;y<matrix.size();y++){
			for(int x = 0;x<matrix.get(0).size();x++){
				System.out.print(matrix.get(y).get(x)+" ");
			}
			System.out.println();
		}
	}
}
