package carpenterScott;

import java.io.File;
import java.io.FileNotFoundException;

public class MainCarpenter01 {

	public static void main(String[] args) throws FileNotFoundException {
		
		if(args.length ==0) {
			System.out.print("Failed to run, no file given.");
			System.exit(1);
		}
		else
		{
			new SudokuToSatReducerCarpenter01(new File(args[0]));
		}
	}

}
