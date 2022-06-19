import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ClassForTestTask2 extends Task2 {

    public static void main(String[] args) throws CloneNotSupportedException {
        addCities();
        addTrips();

        for (String[] trip : tripMap) {
            getMinTripCostCity1toCity2(trip[0], trip[1]);
        }
    }


    static ArrayList<String[]> tripMap = new ArrayList<>();

    public static void addTrips() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите количество маршрутов, которые хотите добавить");
        int countOfTrips = sc.nextInt();

        for (int i = 0; i < countOfTrips; i++) {
            String start = null;
            String finish = null;

            System.out.println("Введите начальный город");
            start = sc.next();

            System.out.println();

            System.out.println("Введите конечный город");
            finish = sc.next();

            tripMap.add(new String[]{start, finish});
            System.out.println("\n");
        }

        sc.close();
    }


    public static void addCities() {
        cities.add(new Task2.City("Слав"));
        cities.get(0).nextCityIndexAndTripCost = new int[][]{
                {2, 1},
                {3, 3},
                {4, 4}};

        cities.add(new Task2.City("Чер"));
        cities.get(1).nextCityIndexAndTripCost = new int[][]{
                {1, 1},
                {3, 1},
                {4, 2},
                {5, 4}};

        cities.add(new Task2.City("Нежин"));
        cities.get(2).nextCityIndexAndTripCost = new int[][]{
                {1, 2},
                {2, 1},
                {4, 2},
                {7, 1}};

        cities.add(new Task2.City("Киев"));
        cities.get(3).nextCityIndexAndTripCost = new int[][]{
                {1, 4},
                {2, 2},
                {3, 2},
                {5, 1},
                {6, 1},
                {7, 1}};

        cities.add(new Task2.City("Борисполь"));
        cities.get(4).nextCityIndexAndTripCost = new int[][]{{4, 1}};

        cities.add(new Task2.City("БЦ"));
        cities.get(5).nextCityIndexAndTripCost = new int[][]{
                {4, 1},
                {7, 3}};

        cities.add(new Task2.City("Бровары"));
        cities.get(6).nextCityIndexAndTripCost = new int[][]{
                {3, 1},
                {4, 1},
                {6, 3}};

        sortArrayOfNeighboringCitiesByIndex();

        for (City city : cities) {
            for (int i = 0; i < city.nextCityIndexAndTripCost.length; i++) {
                city.routs.add(city.nextCityIndexAndTripCost[i][0]);
                city.cost.add(city.nextCityIndexAndTripCost[i][1]);
            }
        }

    }


    public static void printArrayOfNeighboringCities() {
        for (City city : cities) {
            if (city.nextCityIndexAndTripCost == null) continue;
            for (int[] arr : city.nextCityIndexAndTripCost)
                System.out.println(Arrays.toString(arr));
            System.out.println();
        }
    }

}

