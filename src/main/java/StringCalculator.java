

import java.util.ArrayList;

public class StringCalculator {
    public int add(String values) throws Exception { //"1,1"
        if(values.length() > 0) {
            int temp;
            String delimiter = null;
            try {
                temp = Integer.parseInt("" + values.charAt(0));
            } catch(Exception e) {
                if(("" + values.charAt(0)) == "-") {
                    delimiter = null;
                } else {
                    if(("" + values.charAt(0)).equals("[")) {
                        delimiter = "/";
                    }else{
                        delimiter = "" + values.charAt(0);}
                }
            }
            String[] splittedList2=values.split("");
            String hola="";
            boolean aux=true;
            for(int i=0;i<splittedList2.length;i++){
                if (aux){
                    if (!splittedList2[i].equals("[")){
                        hola+=splittedList2[i];
                    }else{
                        hola+="/";
                        aux=false;
                    }
                }else{
                    if (splittedList2[i].equals("]")){
                        aux=true;
                    }
                }
            }

            String[] splittedList = null;
            if(delimiter != null) {
                splittedList = hola.substring(1, hola.length()).split(delimiter);
            } else {
                splittedList = hola.split("[,|\n|#|/]");
            }

            System.out.println(splittedList);

            ArrayList<Integer> numberList = new ArrayList<Integer>();
            int accumulator = 0;
            for(String element: splittedList) {
                int tempValue = Integer.parseInt(element);
                if(tempValue < 0) {
                    throw new Exception("NegativeNumberException");
                }
                if(tempValue > 1000) {
                    continue;
                }
                numberList.add(tempValue);
            }
            for(Integer number: numberList) {
                accumulator += number;
            }
            return accumulator;
        }
        return 0;
    }
}