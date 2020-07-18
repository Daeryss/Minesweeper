package minesweeper;


import java.util.*;

public class Main {
    Random random = new Random();


    public static void main(String[] args) {
        Random random = new Random();
        Main app = new Main();
        Scanner s = new Scanner(System.in);
        System.out.print("How many mines do you want on the field? ");
        int count = s.nextInt();
        String[][] current = app.createNewField(0);

        app.show(current);

        String[][] result = app.createNewField(count);
        String[][] field =  new String[9][9];




        for (int i = 0; i < 9; i++) {
            int sum = 0;
            for (int j = 0; j < 9; j++){
                field[i][j] = app.counter(result, i, j);
            }
        }
        app.show(field);


        while (count != 0){
            System.out.println("Set/unset mines marks or claim a cell as free: ");
            String operation = s.nextLine();
            if (operation.equals("")) {
                operation = s.nextLine();
            }
            int b = app.coordinate(operation)[0] - 1;
            int a = app.coordinate(operation)[1] - 1;
            if (app.mineFree(operation)) {
                if (result[a][b].equals("X")){
                    current = app.showMine(current, result);
                    app.show(current);
                    System.out.println("You stepped on a mine and failed!");
                    break;
                } else if (field[a][b].matches("[1-9]")){
                    current[a][b] = field[a][b];
                    app.show(current);
                    count = count;
                } else {
                    //current = app.checkEmpty(current, field, a, b);
                    int x = app.countMine(current);
                    current[a][b] = "/";
                    while (!app.checkingTilda(current)) {
                        current = app.putAnotherOne(current, field);
                    }
                    current = app.replace(current, field);
                    app.show(current);
                    if (app.countDots(current, count)) {
                        count = 0;
                    } else {
                        count = count - (x - app.countMine(current));
                    }

                }
            }
            else {
                if (current[a][b].equals(".")) {
                    current[a][b] = "*";
                    app.show(current);
                    if (result[a][b].equals("X")){
                        count--;
                    } else {
                        count++;
                    }
                } else if (current[a][b].equals("*")) {
                    current[a][b] = ".";
                    app.show(current);
                    if (result[a][b].equals("X")){
                        count++;
                    } else {
                        count--;
                    }
                } else if (current[a][b].equals("/")) {
                    //current[a][b] = "*";
                    //current = app.putMine(current, a, b);
                    app.show(current);
                    count = count++;
                }
                else {
                    app.show(current);
                    count = count;
                }
            }
        }

        if (count == 0) {
            System.out.println("Congratulations! You found all mines!");
        }
    }

    private int countMine(String[][] current){
        int x = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0 ; j < 9; j++) {
                if (current[i][j].equals("*")){
                    x++;
                }
            }
        }
        return x;
    }

    private boolean countDots (String[][] current, int count) {
        int x = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (current[i][j].equals(".")) {
                    x++;
                }
            }
        }
        return x == count;
    }

    private boolean checkingTilda (String[][] current){
        for (int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++) {
                if (current[i][j].equals("/")) {
                    try {
                        if (current[i + 1][j].equals(".")) {
                            return false;
                        }
                    } catch (Exception e) {
                    }
                    try {
                        if (current[i - 1][j].equals(".")) {
                            return false;
                        }
                    } catch (Exception e) {
                    }
                    try {
                        if (current[i][j + 1].equals(".")) {
                            return false;
                        }
                    } catch (Exception e) {
                    }
                    try {
                        if (current[i][j - 1].equals(".")) {
                            return false;
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        //System.out.println("try check");
        return true;
    }

    private String[][] putAnotherOne(String[][] current, String[][] field) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (current[i][j].equals("/")) {
                    try{
                        if (field[i][j + 1].equals(".") || field[i][j + 1].equals("*")){
                            current[i][j + 1] = "/";
                        } else if (field[i][j + 1].matches("[1-9]")) {
                            current[i][j + 1] = field[i][j + 1];
                        }
                    } catch (Exception e) {}
                    try{
                        if (field[i][j - 1].equals(".") || field[i][j - 1].equals("*")){
                            current[i][j - 1] = "/";
                        } else if (field[i][j - 1].matches("[1-9]")) {
                            current[i][j - 1] = field[i][j - 1];
                        }
                    } catch (Exception e) {}
                    try{
                        if (field[i + 1][j].equals(".") || field[i + 1][j].equals("*")){
                            current[i + 1][j] = "/";
                        } else if (field[i + 1][j].matches("[1-9]")) {
                            current[i + 1][j] = field[i + 1][j];
                        }
                    } catch (Exception e) {}
                    try{
                        if (field[i - 1][j].equals(".") || field[i - 1][j].equals("*")){
                            current[i - 1][j] = "/";
                        } else if (field[i - 1][j].matches("[1-9]")) {
                            current[i - 1][j] = field[i - 1][j];
                        }
                    } catch (Exception e) {}
                }
            }
            //System.out.println("tilda");
            //show(current);
        }
        return current;
    }

    private String[][] replace (String[][] current, String[][] field) {
        int x = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (current[i][j].equals(".")) {
                    try {
                        if (current[i + 1][j + 1].equals("/") || current[i + 1][j - 1].equals("/") || current[i - 1][j + 1].equals("/") || current[i - 1][j - 1].equals("/")) {
                            current[i][j] = field[i][j];
                            if (current[i][j].equals(".")){
                                current[i][j] = "0";
                            }
                        }
                    } catch (Exception e) { }
                } else if (current[i][j].equals("*") && (field[i][j].matches("[1-9]") || field.equals("."))){
                    current[i][j] = field[i][j];
                }
            }
        }
        return current;
    }

    private String[][] showMine (String[][] current, String[][] result) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++){
                if (result[i][j].equals("X")){
                    current[i][j] = "X";
                }
            }
        }
        return current;
    }

    private int[] coordinate(String str) {
        String[] split = str.split(" ");
        int a = Integer.parseInt(split[0]);
        int b = Integer.parseInt(split[1]);
        int[] result = {a, b};
        return result;
    }

    private boolean mineFree(String str) {
        String[] s = str.split(" +");
        return s[2].equals("free");
    }

    public String counter(String[][] field, int i, int j) {
        int sum = 0;
        try {
            if (field[i - 1][j - 1].equals("X")) {
                sum++;
            }
        }catch (Exception e){}
        try {
            if (field[i - 1][j].equals("X")) {
                sum++;
            }
        }catch (Exception e){}
        try {
            if (field[i - 1][j + 1].equals("X")) {
                sum++;
            }
        }catch (Exception e){}
        try {
            if (field[i][j - 1].equals("X")) {
                sum++;
            }
        }catch (Exception e){}
        try {
            if (field[i][j + 1].equals("X")) {
                sum++;
            }
        }catch (Exception e){}
        try {
            if (field[i + 1][j - 1].equals("X")) {
                sum++;
            }
        }catch (Exception e){}
        try {
            if (field[i + 1][j].equals("X")) {
                sum++;
            }
        }catch (Exception e){}
        try {
            if (field[i + 1][j + 1].equals("X")) {
                sum++;
            }
        }catch (Exception e){}
        StringBuilder str = new StringBuilder();
        if (field[i][j].equals("X")) {
            str.append(".");
        } else {
            if (sum == 0) {
                str.append(".");
            } else {
                str.append(sum);
            }
        }
        return str.toString();
    }

    private void show(String[][] str) {
        System.out.println();
        System.out.println(" │123456789│");
        System.out.println("—│—————————│");
        for (int i = 0; i< 9; i++) {
            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < 9; j++){
                temp.append(str[i][j]);
            }
            System.out.printf("%d│%s│", (i + 1),temp.toString());
            System.out.println();
        }
        System.out.println("—│—————————│");
    }

    private String[][] createNewField(int count) {
        Set<Integer> num = new LinkedHashSet<>();
        for (int i = 0; i < count; i++) {
            int x = random.nextInt(81);
            while (num.contains(x)) {
                x = random.nextInt(81);
            }
            num.add(x);
        }

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 81; i ++){
            if (num.contains(i)){
                str.append("X");
            } else {
                str.append(".");
            }
        }
        String temp = str.toString();
        //System.out.println(temp);
        int p = 0;
        int y = 0;
        String[][] field = new String[9][9];
        for (int i = 9; i < 82; i += 9){
            //System.out.print(y + " ");
            //System.out.println(temp.substring(p, i));
            field[y] = temp.substring(p, i).split("");
            p = i;
            y++;
        }
        return field;
    }
}
