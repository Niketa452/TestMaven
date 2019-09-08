public class Conversion {
public static int convertinttoint(String num){
int convertint = Integer.parseInt(num);
return convertint;
}
public static double converttodouble (String doub){
    double convertdouble = Double.parseDouble(doub);
return convertdouble;
}

    public static void main(String[] args) {
        int a = convertinttoint("10");
        System.out.println("String value is converted to int " + a);
        double b =converttodouble("10.20");
        System.out.println("String value is converted to double "+ b);
    }

}
