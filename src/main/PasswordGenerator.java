package main;

import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        System.out.println("Password Generator v1.1");
        do{
            System.out.println("How many characters?");
            int len = new Scanner(System.in).nextInt();
            boolean[] scope = selection();
            do{
                System.out.println(generator(len, scope));
                System.out.println("Enter 1 to regenerate, any other key to finish.");
            }while(new Scanner(System.in).next().equals("1"));
            System.out.println("Enter 1 to generate new code, any other key to exit.");
        }while(new Scanner(System.in).next().equals("1"));
    }

    private static boolean[] selection() {
        boolean[] scope = new boolean[4];
        String[] scopeType = {"Uppercase", "Lowercase", "Numbers", "Symbols"};
        for(int i=0; i<scope.length; i++){
            String choice;
            do{
                System.out.println(scopeType[i]+"? (Y/N)");
                choice = new Scanner(System.in).next().trim().toLowerCase();
            }while(!choice.equals("y")&&!choice.equals("n"));
            scope[i] = choice.equals("y");
        }
        return scope;
    }

    private static String generator(int len, boolean[] scope) {
        String arrScope = "", code = "";
        int start, end;
        for (int s=0; s<scope.length; s++){
            if (scope[s]==true) {
                start = getParams(s)[0]; end = getParams(s)[1];
                for(int a=start; a<=end; a++){
                    if(s==3&&a==48){a=58;}
                    if(s==3&&a==65){a=91;}
                    if(s==3&&a==97){a=123;}
                    arrScope += (char)a;
                }
            }
        }
        for(int f=0; f<len; f++){
            code +=  arrScope.charAt(new Random().nextInt(arrScope.length()));
        }
        return code;
    }

    private static int[] getParams(int s) {
        int[] nums = new int[2];
        switch(s){
            case 0:
                nums[0] = 65;
                nums[1] = 90;
                break;
            case 1:
                nums[0] = 97;
                nums[1] = 122;
                break;
            case 2:
                nums[0] = 48;
                nums[1] = 57;
                break;
            case 3:
                nums[0] = 33;
                nums[1] = 126;
                break;
            default:
                throw new UnknownError("OOPS! Something went wrong.");
        }
        return nums;
    }
}
