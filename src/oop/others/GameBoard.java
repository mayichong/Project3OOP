package oop.others;
import java.util.ArrayList;
public class GameBoard extends TwoDArray{


    ArrayList<Integer> x = new ArrayList<Integer>();
    ArrayList<Integer> y = new ArrayList<Integer>();

    public GameBoard(int row, int col, String defaultVal){
        super(row, col, defaultVal);
    }

    public int checkWin(String userMarker,String compMarker) {
        int count = 0;

        if (newArray[0][0].equals(newArray[0][1]) && newArray[0][1].equals(newArray[0][2]) && newArray[0][0].equals(userMarker)) {
            return 1;
        } else if (newArray[1][0].equals(newArray[1][1]) && newArray[1][1].equals(newArray[1][2]) && newArray[1][0].equals(userMarker)) {
            return 1;
        } else if (newArray[2][0].equals(newArray[2][1]) && newArray[2][1].equals(newArray[2][2]) && newArray[2][0].equals(userMarker)) {
            return 1;
        } else if (newArray[0][0].equals(newArray[1][0]) && newArray[1][0].equals(newArray[2][0]) && newArray[0][0].equals(userMarker)) {
            return 1;
        } else if (newArray[0][1].equals(newArray[1][1]) && newArray[1][1].equals(newArray[2][1]) && newArray[0][1].equals(userMarker)) {
            return 1;
        } else if (newArray[0][2].equals(newArray[1][2]) && newArray[1][2].equals(newArray[2][2]) && newArray[0][2].equals(userMarker)) {
            return 1;
        } else if (newArray[0][0].equals(newArray[1][1]) && newArray[1][1].equals(newArray[2][2]) && newArray[0][0].equals(userMarker)) {
            return 1;
        } else if (newArray[0][2].equals(newArray[1][1]) && newArray[1][1].equals(newArray[2][0]) && newArray[0][2].equals(userMarker)) {
            return 1;
        } else if (newArray[0][0].equals(newArray[0][1]) && newArray[0][1].equals(newArray[0][2]) && newArray[0][0].equals(compMarker)) {
            return 2;
        } else if (newArray[1][0].equals(newArray[1][1]) && newArray[1][1].equals(newArray[1][2]) && newArray[1][0].equals(compMarker)) {
            return 2;
        } else if (newArray[2][0].equals(newArray[2][1]) && newArray[2][1].equals(newArray[2][2]) && newArray[2][0].equals(compMarker)) {
            return 2;
        } else if (newArray[0][0].equals(newArray[1][0]) && newArray[1][0].equals(newArray[2][0]) && newArray[0][0].equals(compMarker)) {
            return 2;
        } else if (newArray[0][1].equals(newArray[1][1]) && newArray[1][1].equals(newArray[2][1]) && newArray[0][1].equals(compMarker)) {
            return 2;
        } else if (newArray[0][2].equals(newArray[1][2]) && newArray[1][2].equals(newArray[2][2]) && newArray[0][2].equals(compMarker)) {
            return 2;
        } else if (newArray[0][0].equals(newArray[1][1]) && newArray[1][1].equals(newArray[2][2]) && newArray[0][0].equals(compMarker)) {
            return 2;
        } else if (newArray[0][2].equals(newArray[1][1]) && newArray[1][1].equals(newArray[2][0]) && newArray[0][2].equals(compMarker)) {
            return 2;
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (newArray[i][j] == "0") {
                        count++;
                        if (count == 3){
                            return 0;
                        }
                        return 3;

                    }
                }
            }
            return 0;
        }

    }

    public String avaX(){

        for (int i =0;i<3;i++){
            for (int j =0;j<3;j++){
                if (newArray[i][j] == "0"){
                    x.add(i);
                    y.add(j);
                }
            }
        }

        int index = 0;
        String coord = x.get(index) + "" + y.get(index);
        x.clear();
        y.clear();
        return coord;
    }

    public String boardDisplay(String marker){
        String dis = " ";
        for (int i = 0;i<3;i++){

            dis = dis + "\n";
            for (int j = 0;j<3;j++){
                if (newArray[i][j].equals("1")){
                    newArray[i][j] = "1";
                }

                if (newArray[i][j].equals("2")){
                    newArray[i][j] = "2";
                }

                dis = dis + newArray [i][j] + "\t";
            }
        }
    return dis;
    }

    public boolean checkFull(){
        int count = 0;
        for (int i =0;i<3;i++){
            for (int j = 0;j<3;j++){
                if (!getInt(i,j).equals("0")){
                    count++;
                }
            }
        }
        if (count == 9){
            return true;
        }
        else
            return false;

    }

}

