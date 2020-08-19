import java.io.*;
import java.util.Stack;

public class LispMain 
{
	public static void main(String[] args) throws IOException {
		
		String [] tests={
					"(*3(+(+5 5)(/10 2))))",
					"(*3(+(+5 5))(/10 2)))",
					"(*3(+(+5 5)(/10 2)))",
					"(+3(+(+5.5 5)(/10 2)))",
					"(*3(+( + 1(-1 2)22)(+5 0)(/10 2)))"};
		// test des expréssions
		System.out.println("Tests de parenthèses:\n ");
		for(int t=0;t<tests.length;t++){
			System.out.println(tests[t]+"\t: "+ Lisp.isEquilibre(tests[t]));
		}
		
		//test des expréssions
		System.out.println("\nTests de résolution:\n");
		for(int t=0;t<tests.length;t++){
			if(Lisp.isEquilibre(tests[t])==true){
				System.out.println(tests[t]+"\t= "+ Lisp.solve(tests[t]));
			}	
		}
	}
	
}	

	
	
	

