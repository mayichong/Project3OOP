package oop.others;

/**
 * You must implement the following methods to accept the expected 
 * parameters and return accurate results.
 * You are allowed and expected to add any class attributes and methods 
 * needed to complete this assignment. 
 * 
 *
 */
import java.util.HashMap;
import java.util.Map;

public class TwoDArray {
	String [][] newArray;
    int globalRow;
    int globalCol;
    String globalDefaultValue;
    int countOfDefault;
    public TwoDArray(int rows, int cols, String defaultVal){
		/*TODO - Create a 2D integer array consisting of 
		 * the number of rows and columns given. Initialize 
		 * the array by setting each int to be the defaulVal. 
		 * */
		newArray = new String[rows][cols];
        globalRow = rows;
        globalCol = cols;
        globalDefaultValue = defaultVal;
		for (int i = 0;i<rows;i++){
			for (int j = 0;j<cols;j++){
				newArray [i][j] = defaultVal;

			}
		}

	}
	
	public void initArray(String defaultVal) {
        for (int i = 0;i<globalRow;i++){
            for (int j = 0;j<globalCol;j++){
                newArray [i][j] = defaultVal;
            }
        }
	}
	
    public boolean insertInt(int row, int col, String val) {
		/*TODO - "Insert" based on the following conditions:
		 * 1. The location [row][col] is still set to the default value
		 * 		-return "Success! (val) was inserted."
		 * 
		 * 2. The location [row][col] is no longer the default value
		 * 		-return "Failure: (row), (col) is not empty."
		 * 
		 * 3. val is the default value; 
		 * 		-return "Failure: (val) is not allowed."
		 * 
		 * Note: Print the int value in place of (). 
		 * e.g., replace (val) with val.
		 */
        
        if (val.equals(globalDefaultValue)){
            return false;
            
        }else if (!newArray[row][col].equals(globalDefaultValue)){
            return false;
        }else if (newArray[row][col].equals(globalDefaultValue)){
            newArray[row][col] = val;
            return true;
        }
		return false;
	}
	
	public String getInt(int row, int col) {
		/*TODO - Return the value at the specified row, col
		 * 
		 */
		
		return newArray [row][col];
	}
	
	public String getArrayDisplay() {
        String dis = " ";
		for (int i = 0;i<globalRow;i++){
            dis = dis + "\n";
            for (int j = 0;j<globalCol;j++){

                dis = dis + newArray [i][j] + "\t";
            }
        }

		return dis;
	}
	
	public String getArrayDetails() {
		/*TODO - List the following:
		 * # rows
		 * # columns
		 * How many unique values (in the above example, this would be 2
		 * Value and count of each (e.g. 
		 * 			value:1 count:5
		 * 			value:0 count:4
		 * 
		 * 			)
		 * 
		 */
        String detail = "\n# rows: " + globalRow + "\n# columns: " + globalCol + "\n" + "Value and count of each:\n";
        
		String converted = "";
		for (int i = 0;i<globalRow;i++){
			for (int j = 0;j<globalCol;j++){
				converted = converted + newArray[i][j];
			}
		}
		Map<Character, Integer> charMapCount = new HashMap<>();

		for (Character c : converted.toCharArray()){
			if (charMapCount.containsKey(c)){
				charMapCount.put(c,charMapCount.get(c) + 1);
			}else{
				charMapCount.put(c,1);
			}
		}

		String test = "\n";
		for (Character value: charMapCount.keySet()){
			test = test + "value: " + value + " count: " + charMapCount.get(value) + "\n";
		}

		test = detail + test;
		return test;	
	}



}