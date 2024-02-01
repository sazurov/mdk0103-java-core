package base.delivery;

class Solution {
    public void fillMatrix (int[][] matrix) {
        int temp = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int floor = i + 1;
                matrix[i][j] = temp++;
                System.out.println(floor + " этаж | квартира №" + (temp - 1));
            }
        }
    }
}

public class Delivery {
    public static void main(String[] args) {
        int[][] building = new int[10][5];
        Solution solution = new Solution();

        solution.fillMatrix(building);
    }
}
