package com.fenxi.analyse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
public class Analyse {
	private InputStreamReader isr;	
	private BufferedWriter bw;
	private char[] ch = new char[1];
	private int k = 0;
//	private String index = null;
	private String[][] strIndex = 
	{{"begin","1"},{"end","2"},{"if","3"},{"then","4"},{"else","5"},
		{"while","6"},{"do","7"},{"char","8"},{"+","9"},{"-","10"},{"*","11"},
		{"/","12"},{"=","13"},{"<","14"},{">","15"},{"<=","16"},{">=","17"},
		{"{","18"},{"}","19"},{"[","20"},{"]","21"},{";","22"},{",","23"},{".","24"},
		{"(","25"},{")","26"},{":","27"}};
	public Analyse(InputStreamReader isr, BufferedWriter bw){
		this.isr = isr;
		this.bw = bw;
	}
	public void Test() throws IOException{
		String s = "";
		while(isr.read(ch) != -1){
			if(ch[0]==' ' || ch[0]=='\t' || ch[0]=='\n'){
				continue;
			} else if(Character.isLetter(ch[0])) {
				while(Character.isLetter(ch[0]) || Character.isDigit(ch[0])){
					s = s + ch[0];
					k = isr.read(ch);
					if(k == -1 || ch[0]==' ' || ch[0]=='\t' || ch[0]=='\n'){
						break;
					}
				}
				if(isKey(s)){
//					System.out.println(s + "   关键字");
					bw.write("("+this.hasIndex(s)+"\t"+s+")");
					bw.newLine();
					s = "";
				} else {
//					System.out.println(s + "   标识符");
					bw.write("("+this.hasIndex(s)+"\t"+s+")");
					bw.newLine();
					s = "";
				}
				if(!Character.isLetter(ch[0]) && !Character.isDigit(ch[0])){
					this.isOperator(ch[0]);
				}
			} else if(Character.isDigit(ch[0])){
				s = "";
				while(Character.isDigit(ch[0]) || ch[0]=='.' ){
					s = s + ch[0];
					k = isr.read(ch);
					if(k == -1 || ch[0]==' ' || ch[0]=='\t' || ch[0]=='\n'){
						break;
					}
				}
//				System.out.println(s + "   无符号实数");
				bw.write("("+this.hasIndex(s)+"\t"+s+")");
				bw.newLine();
				s = "";
				if(!Character.isLetter(ch[0]) && !Character.isDigit(ch[0])){
					this.isOperator(ch[0]);
				}
			} else {
				this.isOperator(ch[0]);
			}
		}
	}
	public boolean isKey(String s){
		String[] key={"begin","end","if","then","else","while","do", "char"};
		int i = 0;
		while(i<key.length){
			if(s.equals(key[i])){
				return true;
			} else {
				i++;
			}
		}
		return false;
	}
	public void isOperator(char c) throws IOException{

		switch(c){
		case '+' :{	bw.write("("+this.hasIndex(c+"")+"\t"+c+")");
					bw.newLine();
					} break;
        case '-' :{	bw.write("("+this.hasIndex(c+"")+"\t"+c+")");
					bw.newLine();
					} break;
        case '*' :{	bw.write("("+this.hasIndex(c+"")+"\t"+c+")");
        			bw.newLine();
					} break;
        case '=' :{	bw.write("("+this.hasIndex(c+"")+"\t"+c+")");
					bw.newLine();
					} break;
        case '/' :{	bw.write("("+this.hasIndex(c+"")+"\t"+c+")");
					bw.newLine();
					} break;
        case '(' :{	bw.write("("+this.hasIndex(c+"")+"\t"+c+")");
					bw.newLine();
					} break;
        case ')' :{	bw.write("("+this.hasIndex(c+"")+"\t"+c+")");
					bw.newLine();
					} break;
        case '[' :{	bw.write("("+this.hasIndex(c+"")+"\t"+c+")");
					bw.newLine();
					} break;
        case ']' :{	bw.write("("+this.hasIndex(c+"")+"\t"+c+")");
					bw.newLine();
					} break;               
        case ';' :{	bw.write("("+this.hasIndex(c+"")+"\t"+c+")");
					bw.newLine();
					} break;
        case '.' :{	bw.write("("+this.hasIndex(c+"")+"\t"+c+")");
					bw.newLine();
					} break;
        case ',' :{	bw.write("("+this.hasIndex(c+"")+"\t"+c+")");
					bw.newLine();
					} break;
        case '{' :{	bw.write("("+this.hasIndex(c+"")+"\t"+c+")");
					bw.newLine();
					} break;
        case '}' :{	bw.write("("+this.hasIndex(c+"")+"\t"+c+")");
        			bw.newLine();
					} break;
        case '>' :{	k=isr.read(ch);
	        		if(k != -1 && ch[0]=='='){
	        			bw.write("("+this.hasIndex(">=")+"\t"+">=)");
	        			bw.newLine();
	        		} else {
	        			bw.write("("+this.hasIndex(">")+"\t"+">)");
	        			bw.newLine();
	        		}	
        		  }	break;
        case '<' :{	k=isr.read(ch);
				    if(k != -1 && ch[0]=='='){
						bw.write("("+this.hasIndex("<=")+"\t"+"<=)");
						bw.newLine();
					} else {
						bw.write("("+this.hasIndex("<")+"\t"+"<)");
						bw.newLine();
					}
        		  } break;
		}
	}
	public String hasIndex(String s){
		for(int i=0; i<strIndex.length; i++){
			for(int j=0; j<strIndex[i].length-1; j++){
				if(s.equals(strIndex[i][j])){
					return strIndex[i][j+1];
				}
			}
		}
		return "28";
	}
}
