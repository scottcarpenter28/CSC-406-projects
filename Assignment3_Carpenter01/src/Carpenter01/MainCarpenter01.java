package Carpenter01;

import java.io.FileNotFoundException;

public class MainCarpenter01 {

	public static void main(String[] args) throws FileNotFoundException {
		if(args.length==0) {
			System.out.println("No file found");
			System.exit(1);
		}
		else 
			new KruskalMSTCarpenter01(args[0]);
	}

}
