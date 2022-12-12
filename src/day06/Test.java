package day06;

public class Test {
    public static void main(String[] args) {
        String randomString = generateRandoms("10", "100");  
        System.out.println(randomString); 
    }

    public static String generateRandoms(String inputN, String inputMax) {
        Integer n = Integer.parseInt(inputN);
        Integer min = 0;
        Integer max = Integer.parseInt(inputMax);

        String  randomList = "";
        
        // creating random numbers and adding to arraylist
        for (int i = 0; i < n; i++) {
            Integer randomNumber = (int) Math.floor(Math.random()*(max - min + 1) + min);
            randomList = randomList + " " + randomNumber;
        }

        return randomList;
    }
}
