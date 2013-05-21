
public class Test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(method(7));
	}
//	public static int method(int n){
//		if(n == 1)
//			return 1;
//		else
//			return n*method(n-1);			
//	}
	
	public static int method(int n){
		int s=0,s1=1,s2=1;
		if(n<=2){
			return 1;
		}
		else{
			for(int i=3;i<=n;i++){
				s = s1+s2;
				s1 = s2;
				s2 = s;					
			}
			return s;
		}		
	}
}
