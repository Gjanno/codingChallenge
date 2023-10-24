import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Solution

{

    /*
        Rules:
        - Grid size: n*m (rows and columns)
        - Valid if it starts from the middle cell and
        has odd number of accompanying cells
        - Smallest valid size is 1
        - Return product of the two largest plus (plus1 and plus2)
        - B for bad cells, G for good cells
        - Two "+" symbols can't overlap
        - When encountering G, expand in all 4 directions, one
        cell at a time, tracking the expansion area
        - Mark every visited cell to avoid overlap
*/

    public static int twoPluses(List<String> grid)
    {
        //Get the number of rows and column from the grid

        int numRows = grid.size();
        int numCols = grid.get(0).length();

        //Set initial area of both plus 1 and plus 2 to zero

        int plus1Area = 0;
        int plus2Area = 0;
        //Create an array of same size as original grid

        char[][] arrayUsed = new char[numRows][numCols];

        //Create an array of same size as original grid

        for (int i = 0; i < numRows; i++) {
            for (int k = 0; k < numCols; k++) {
                arrayUsed[i][k] = grid.get(i).charAt(k);
            }
        }

        //We iterate over the grid to find max 2 plus
        for (int i = 0; i < numRows ; i++)
        {
            for (int j = 0; j < numCols; j++)
            {
                //we would try to try if we can expand from a cell in four direction by one
                int radiusExpansion = 1;
                int result = 0;

                char[][] originalArr = new char[numRows][numCols];

                //Copy the current array into originalArr
                for (int x = 0; x < numRows; x++)
                {
                    for (int y = 0; y < numCols; y++)
                    {
                        originalArr[x][y] = arrayUsed[x][y];
                    }
                }

                //if the current visited cell is G, we would try to expand in four direction
                if(arrayUsed[i][j]=='G')
                {   // Check to ensure row and column is within bounds
                    while (i - radiusExpansion >= 0 && i + radiusExpansion < numRows &&
                            j - radiusExpansion >= 0 && j + radiusExpansion < numCols &&
                            arrayUsed[i - radiusExpansion][j] == 'G' &&//Tries to expand up
                            arrayUsed[i][j - radiusExpansion] == 'G' &&//Tries to expand to left
                            arrayUsed[i][j + radiusExpansion] == 'G' &&//Tries to expand to right
                            arrayUsed[i + radiusExpansion][j] == 'G') {//tries to expand down
                        //Set all the visited cell as D so we wouldn't count the overlap cross
                        arrayUsed[i][j] = 'D';
                        arrayUsed[i - radiusExpansion][j] = 'D';
                        arrayUsed[i][j - radiusExpansion] = 'D';
                        arrayUsed[i][j + radiusExpansion] = 'D';
                        arrayUsed[i + radiusExpansion][j] = 'D';
                        //We add 4 as we can successfully expand in four direction
                        result += 4;
                        //We try to expand one position more if possible
                        radiusExpansion += 1;
                    }
                    //To ensure center is counted we add result by one, we don't add result+=5 inside the while loop as we would overcount it everytime we expand
                    result += 1;

                }

                //Checks to ensure plus1Area and plus2Area is the max
                if (result >= plus1Area && plus1Area >= plus2Area) {
                    plus2Area = plus1Area;
                    plus1Area = result;
                } else if (plus1Area >= result && result >= plus2Area) {
                    plus2Area = result;
                } else {
                    for (int x = 0; x < numRows; x++) {
                        for (int y = 0; y < numCols; y++) {
                            arrayUsed[x][y] = originalArr[x][y];
                        }
                    }
                }
            }
        }
        //return product of two areas
        return plus1Area * plus2Area;
    }

}

public class TwoPlus {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<String> grid = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = scanner.next();
            grid.add(line);
        }

        int result = Solution.twoPluses(grid);
        System.out.println(result);
    }
}
