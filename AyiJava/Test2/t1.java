public class t1 {
    public static void main(String[] args) {
        Integer a = 5;
        Integer b = 5;
        System.out.println(determinarMayor(a, b));
    }
    public static String determinarMayor(Integer a, Integer b){
        String answer;
        if (a > b){
            answer = a + " es mayor que " + b;
        } else if (a == b) {
            answer = "Son el mismo numero";
        }
        else {
            answer = b + " es mayor que " + a;
        }
        return answer;
    }
}