//package

/// Imports \\\
import java.util.*;

public class MineSweeper {

    private int[][] fieldVisible = new int[10][10];
    private int[][] fieldHidden = new int [10][10];

    public static void main(String[] args){
        MineSweeper MS = new MineSweeper();
        MS.startGame();
    }

    public void startGame(){
        System.out.println("\n\n================ Welcome to Super Ultimate X-Treme MineSweeper !==============\n");
        setupField(1);

        boolean flag = true;
        while(flag){
            displayVisible();
            flag = playMove();
            if(checkWin()){
                displayHidden();
                System.out.println("\n ========== Tubular! you are the Most Super Ultimate X-Treme! ==========");
                break;
            }
        }

    }

    public void setupField(int diff){
        int var=0;
        while(var!=10){
            Random random = new Random();
            int x = random.nextInt(10); //Randomizes the X co-ordinate for the bomb placement
            int y = random.nextInt(10); //Randomizes the Y co-ordinate for the bomb placement

            // Debugging \\
            System.out.print("Random int a:" + x + " , Random int b:" + y);
            // Debug End \\

            fieldHidden[x][y] = 100;

            var++;
        }
        buildHidden();
    }

    public void buildHidden(){   ///// Builds the Hidden Matrix
        for(int i = 0; i<10; i++){
            for(int i2 = 0; i2<10; i2++){
                int count=0;
                if(fieldHidden[i][i2] != 100){
                    if (i != 0){
                        if(fieldHidden[i-1][i2] == 100) count++;
                        if(i2 != 0){
                            if(fieldHidden[i-1][i2-1] == 100) count++;
                        }
                    }
                    if(i != 9){
                        if(fieldHidden[i+1][i2] == 100) count++;
                        if(i2 != 9){
                            if(fieldHidden[i+1][i2+1] == 100) count++;
                        }
                    }
                    if (i2 != 0){
                        if(fieldHidden[i][i2-1] == 100) count++;
                        if(i != 9){
                            if(fieldHidden[i+1][i2 - 1] == 100) count++;
                        }
                    }
                    if(i2 != 9){
                        if(fieldHidden[i][i2+1] == 100) count++;
                        if(i != 0){
                            if(fieldHidden[i-1][i2+1] == 100) count++;
                        }
                    }

                    fieldHidden[i][i2] = count;
                }
            }
        }
    }

    public void displayVisible(){
        System.out.print("\t");
        for (int i=0; i<10; i++){
            System.out.print("  "+ i + "  ");
        }
        System.out.print("\n");
        for(int i = 0; i<10; i++){
            System.out.print(i + "\t| ");
            for(int i2 = 0; i2<10; i2++){
                if(fieldVisible[i][i2]==0){
                    System.out.print("?");
                }
                else if(fieldVisible[i][i2]==50){
                    System.out.print("  ");
                }
                else {
                    System.out.print(fieldVisible[i][i2]);
                }
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
    }

public boolean playMove(){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Row (x) No: ");
        int RowNo = sc.nextInt();
        System.out.print("\nEnter Column (y) No: ");
        int ColNo = sc.nextInt();

        if(RowNo < 0 || RowNo > 9 || ColNo < 0 || ColNo > 9 || fieldVisible[RowNo][ColNo] != 0){
            System.out.print("\nIncorrect Input!");
            return true;
        }

        if(fieldHidden[RowNo][ColNo] == 100){
            displayHidden();
            System.out.print("Thats Not Rad! you stepped on a mine bro! and has you totally exploaded!");
            return false;
        }
        else if(fieldHidden[RowNo][ColNo] == 0){
            fixVisible(RowNo, ColNo);
        }
        else{
            fixNeighbours(RowNo,ColNo);
        }

        return true;
}

public void fixVisible(int i, int i2){
        fieldVisible[i][i2] = 50;
                if(i!=0){
                    fieldVisible[i-1][i2] = fieldHidden [i-1][i2];
                    if(fieldVisible[i-1][i2]==0) fieldVisible[i-1][i2] = 50;
                    if(i2!=0){
                        fieldVisible[i-1][i2-1] = fieldHidden[i-1][i2-1];
                        if(fieldVisible[i-1][i2-1]==0) fieldVisible[i-1][i2-1]=50;

                    }
                }
                if(i!=9){
                    fieldVisible[i+1][i2]=fieldHidden[i+1][i2];
                    if(fieldVisible[i+1][i2]==0) fieldVisible[i+1][i2]=50;
                    if(i2!=9)
                    {
                        fieldVisible[i+1][i2+1]= fieldHidden[i+1][i2+1];
                        if(fieldVisible[i+1][i2+1]==0) fieldVisible[i+1][i2+1] = 50;
                    }
                }
    if(i2!=0)
    {
        fieldVisible[i][i2-1]=fieldHidden[i][i2-1];
        if(fieldVisible[i][i2-1]==0) fieldVisible[i][i2-1] = 50;
        if(i!=9)
        {
            fieldVisible[i+1][i2-1]=fieldHidden[i+1][i2-1];
            if(fieldVisible[i+1][i2-1]==0) fieldVisible[i+1][i2-1] = 50;
        }
    }
    if(i2!=9)
    {
        fieldVisible[i][i2+1]=fieldHidden[i][i2+1];
        if(fieldVisible[i][i2+1]==0) fieldVisible[i][i2+1] = 50;
        if(i!=0)
        {
            fieldVisible[i-1][i2+1]=fieldHidden[i-1][i2+1];
            if(fieldVisible[i-1][i2+1]==0) fieldVisible[i-1][i2+1] = 50;
        }
    }
}

public void fixNeighbours(int i, int i2)
    {
        Random random = new Random();
        int x = random.nextInt()%4;

        fieldVisible[i][i2] = fieldHidden[i][i2];

        if(x==0)
        {
            if(i!=0)
            {
                if(fieldHidden[i-1][i2]!=100)
                {
                    fieldVisible[i-1][i2] = fieldHidden[i-1][i2];
                    if(fieldVisible[i-1][i2]==0)  fieldVisible[i-1][i2] = 50;
                }
            }
            if(i2!=0)
            {
                if(fieldHidden[i][i2-1]!=100)
                {
                    fieldVisible[i][i2-1] = fieldHidden[i][i2-1];
                    if(fieldVisible[i][i2-1]==0)  fieldVisible[i][i2-1] = 50;
                }

            }
            if(i!=0 && i2!=0)
            {
                if(fieldHidden[i-1][i2-1]!=100)
                {
                    fieldVisible[i-1][i2-1] = fieldHidden[i-1][i2-1];
                    if(fieldVisible[i-1][i2-1]==0)  fieldVisible[i-1][i2-1] = 50;
                }

            }
        }
        else if(x==1)
        {
            if(i!=0)
            {
                if(fieldHidden[i-1][i2]!=100)
                {
                    fieldVisible[i-1][i2] = fieldHidden[i-1][i2];
                    if(fieldVisible[i-1][i2]==0)  fieldVisible[i-1][i2] = 50;
                }
            }
            if(i2!=9)
            {
                if(fieldHidden[i][i2+1]!=100)
                {
                    fieldVisible[i][i2+1] = fieldHidden[i][i2+1];
                    if(fieldVisible[i][i2+1]==0)  fieldVisible[i][i2+1] = 50;
                }

            }
            if(i!=0 && i2!=9)
            {
                if(fieldHidden[i-1][i2+1]!=100)
                {
                    fieldVisible[i-1][i2+1] = fieldHidden[i-1][i2+1];
                    if(fieldVisible[i-1][i2+1]==0)  fieldVisible[i-1][i2+1] = 50;
                }
            }
        }
        else if(x==2)
        {
            if(i!=9)
            {
                if(fieldHidden[i+1][i2]!=100)
                {
                    fieldVisible[i+1][i2] = fieldHidden[i+1][i2];
                    if(fieldVisible[i+1][i2]==0)  fieldVisible[i+1][i2] = 50;
                }
            }
            if(i2!=9)
            {
                if(fieldHidden[i][i2+1]!=100)
                {
                    fieldVisible[i][i2+1] = fieldHidden[i][i2+1];
                    if(fieldVisible[i][i2+1]==0)  fieldVisible[i][i2+1] = 50;
                }

            }
            if(i!=9 && i2!=9)
            {
                if(fieldHidden[i+1][i2+1]!=100)
                {
                    fieldVisible[i+1][i2+1] = fieldHidden[i+1][i2+1];
                    if(fieldVisible[i+1][i2+1]==0)  fieldVisible[i+1][i2+1] = 50;
                }
            }
        }
        else
        {
            if(i!=9)
            {
                if(fieldHidden[i+1][i2]!=100)
                {
                    fieldVisible[i+1][i2] = fieldHidden[i+1][i2];
                    if(fieldVisible[i+1][i2]==0)  fieldVisible[i+1][i2] = 50;
                }
            }
            if(i2!=0)
            {
                if(fieldHidden[i][i2-1]!=100)
                {
                    fieldVisible[i][i2-1] = fieldHidden[i][i2-1];
                    if(fieldVisible[i][i2-1]==0)  fieldVisible[i][i2-1] = 50;
                }

            }
            if(i!=9 && i2!=0)
            {
                if(fieldHidden[i+1][i2-1]!=100)
                {
                    fieldVisible[i+1][i2-1] = fieldHidden[i+1][i2-1];
                    if(fieldVisible[i+1][i2-1]==0)  fieldVisible[i+1][i2-1] = 50;
                }
            }
        }
    }
public boolean checkWin(){
    for(int i=0; i<10; i++)
    {
        for(int i2=0; i2<10; i2++)
        {
            if(fieldVisible[i][i2]==0)
            {
                if(fieldHidden[i][i2]!=100)
                {
                    return false;
                }
            }
        }
    }
    return true;
}

    public void displayHidden()
    {
        System.out.print("\t ");
        for(int i=0; i<10; i++)
        {
            System.out.print(" " + i + " ");
        }
        System.out.print("\n");
        for(int i=0; i<10; i++)
        {
            System.out.print(i + "\t| ");
            for(int i2=0; i2<10; i2++)
            {
                if(fieldHidden[i][i2]==0)
                {
                    System.out.print(" ");
                }
                else if(fieldHidden[i][i2]==100)
                {
                    System.out.print("X");
                }
                else
                {
                    System.out.print(fieldHidden[i][i2]);
                }
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
    }

}










