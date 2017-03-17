import java.awt.Point;
import java.util.ArrayList;

public class Matrix {
	//PROPERTIES
	private String[][] matrix = null;
	
	
	//CONSTRUCTORS
	public Matrix(String s){
		//from webwork logic
		//get everything into an arraylist
		ArrayList<ArrayList<String>> out = new ArrayList<ArrayList<String>>();
		out.add(new ArrayList<String>());
		//analyze by char
		char[] data = s.toCharArray();
		String input = "";
		int i = 0;
		int width = 0;
		//get matrix width
		while(i<data.length){
			//check if negative
			if(data[i] == '-'){
				input += data[i];
			}
			//if numeric
			else if(data[i]>='0'&&data[i]<='9'){
				input += data[i];
			}
			//if alpha
			else if(Character.isAlphabetic(data[i])){
				input += data[i];
			}
			//if space then end input stream
			else if((data[i]==' '||data[i]=='\t')&&(!input.equals(""))){
				out.get(0).add(new String(input));
				input = "";
				width++;
			}
			//if char telling first row is done
			else if(data[i] == '⎥'){
				out.add(new ArrayList<String>());
				break;
			}
			i++;
		}
		//fill all the other numbers
		int x = 0;
		while(i<data.length){
			//check if negative
			if(data[i] == '-'){
				input += data[i];
			}
			//if numeric
			else if(data[i]>='0'&&data[i]<='9'){
				input += data[i];
				
			}
			else if(data[i]>='0'&&data[i]<='9'){
				input += data[i];
			}
			//if alpha
			else if(Character.isAlphabetic(data[i])){
				input += data[i];
			}
			//if space then end input stream
			else if((data[i]==' '||data[i]=='\t')&&(!input.equals(""))){
				out.get(out.size()-1).add(new String(input));
				input = "";
				x++;
				//if reached the end of the row
				if(x==width){
					x = 0;
					out.add(new ArrayList<String>());
				}
			}
			i++;
		}
		out.get(out.size()-1).add(new String(input));
		matrixFromArrayList(out);
	}
	public Matrix(ArrayList<ArrayList<String>> a){
		//from arraylists
		matrixFromArrayList(a);
		
	}
	public Matrix(String[][] a){
		//from arrays
		matrix = a;	
	}
	public Matrix(int Row, int Col){
		//default with zeros everywhere
		//init
		matrix = new String[Row][Col];
		//set
		for(int x = 0; x<Row; x++){
			for(int y = 0; y<Col; y++){
				matrix[x][y] = "0";
			}
		}
	}
	public Matrix(int Row, int Col, int defVal){
		//default with defVal everywhere
		//init
		matrix = new String[Row][Col];
		//set
		for(int x = 0; x<Row; x++){
			for(int y = 0; y<Col; y++){
				matrix[x][y] = ""+defVal;
			}
		}
	}
	
	
	//MAIN METHODS
	public int getWidth(){
		return matrix[0].length;
	}
	public int getHeight(){
		return matrix.length;
	}
	public void print(){
		//Print matrix
		for(int y = 0;y<getHeight();y++){
			for(int x = 0;x<getWidth();x++){
				System.out.print(matrix[y][x]+"\t");
			}
			System.out.println();
		}
		System.out.println();
	}
	public void matrixFromArrayList(ArrayList<ArrayList<String>> a){
		matrix = new String[a.size()][a.get(0).size()];
		//set
		for(int x = 0; x<a.size(); x++){
			for(int y = 0; y<a.get(0).size(); y++){
				matrix[x][y] = ""+a.get(x).get(y);
			}
		}
		//make sure matrix is correct by checking lengths
		for(int z = 1;z<a.size();z++){
			if(a.get(z).size()!=a.get(0).size()){
				System.out.println("ERROR: Matrix is inbalanced!\nRow "+(z+1)+" only has "+a.get(z).size()+" columns when it should have "+ a.get(0).size()+".");
				System.exit(1);
			}
		}
	}
	public String getData(int row, int col){
		return matrix[row][col];
	}
	public void setData(int row, int col, String val){
		matrix[row][col] = val;
	}
	
	
	//FUCTION METHODS
	public String derivative(){
		return correctSigns(detLogic(this));
	}
	private String detLogic(Matrix m){
		String output = "";
		int addsub = 1;
		if(m.getWidth()==1){
			return m.getData(0, 0);
		}
		output += "(";
		for(int i = 0;i<m.getWidth();i++){
			if(addsub>0){
				output += "+"+m.getData(0,i)+"*"+detLogic(m.remRC(new Point(i,0)));
			}
			else{
				output += "-"+m.getData(0,i)+"*"+detLogic(m.remRC(new Point(i,0)));
			}
			addsub*=-1;
		}
		output += ")";
		return output;
	}
	public Matrix remRC(Point p){
		//black output matrix
		Matrix output = new Matrix(getHeight()-1,getWidth()-1);
		//fill accounting for the offset
		int offsety = 0;
		int offsetx = 0;
		for(int y = 0; y<getHeight();y++){
			//avoid filling y point
			if(y==p.y){
				offsety++;
			}
			//fill
			else{
				offsetx = 0;
				for(int x = 0; x<getWidth();x++){
					//make sure to go around x point
					if(x!=p.x){
						//set data
						output.setData(y-offsety,x-offsetx,getData(y,x));
					}
					else{
						offsetx++;
					}
				}
			}
		}
		return output;
	}
	public String correctSigns(String s){
		//output
		String output = "";
		//all chars
		char[] data = s.toCharArray();
		char lastChar = data[0];
		//check if double signs
		for(int i = 1;i<data.length;i++){
			if(lastChar=='+'){
				if(data[i]=='+'){
					output+='+';
					i++;
				}
				else if(data[i]=='-'){
					output+='-';
					i++;
				}
				else{
					output+=lastChar;
				}
			}
			else if(lastChar=='-'){
				if(data[i]=='+'){
					output+='-';
					i++;
				}
				else if(data[i]=='-'){
					output+='+';
					i++;
				}
				else{
					output+=lastChar;
				}
			}
			else{
				output+=lastChar;
			}
			lastChar = data[i];
		}
		output+=lastChar;
		
		//get rid of + before numbers
		data = output.toCharArray();
		output = "";
		if(data[0]!='+'){
			output+=data[0];
		}
		for(int i = 1;i<data.length;i++){
			lastChar = data[i-1];
			if(!(!(Character.isAlphabetic(lastChar)||Character.isDigit(lastChar)||lastChar==')')&&data[i]=='+')){
				output+=data[i];
			}
		}
		return output;
	}
	private String simplifyExpression(String s){
		return s;
	}
	
	
}
