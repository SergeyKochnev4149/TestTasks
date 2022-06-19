import java.util.ArrayList;
import java.util.List;

/*
    This program finds the cheapest way from one city to another
 */

public class Task2 {

    static ArrayList<City> cities = new ArrayList<City>();
    static ArrayList<ArrayList<Integer>> routMap = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> mapOfTripCost = new ArrayList<>();


    public static void getMinTripCostCity1toCity2(String startCity, String finishCity) throws CloneNotSupportedException {

        if (chekCitiesInList(startCity, finishCity) != null) {
            buildRoutMap(startCity, finishCity);

            int minCost = 0, sum = 0;
            for (Integer cost : mapOfTripCost.get(0))
                minCost += cost;

            for (int i = 1; i < mapOfTripCost.size(); i++) {
                for (Integer cost : mapOfTripCost.get(i))
                    sum += cost;

                if (minCost > sum)
                    minCost = sum;

                sum = 0;
            }

            System.out.printf("Минимальная стоимость поездки %s -> %s = %d\n", startCity, finishCity, minCost);
        } else {
            System.out.println("Неверно введены города");
        }
    }


    public static void buildRoutMap(String start, String finish) throws CloneNotSupportedException {
        routMap.clear();
        mapOfTripCost.clear();

        int[] indexOfStartAndFinish = ClassForTestTask2.chekCitiesInList(start, finish);
        Integer startIndex = null, endIndex = null;

        if (indexOfStartAndFinish == null) {
            System.out.println("Неверно введены города");
            return;
        } else {
            startIndex = indexOfStartAndFinish[0];
            endIndex = indexOfStartAndFinish[1];
        }

        /*
        The copy is needed not to damage the original,
        because when searching for the path to the finishCity,
        the elements from the list city.routs and city.cost will be deleted
         */

        var copyOfCities = new ArrayList<City>();
        for (City city : cities) {
            copyOfCities.add(new City(city));
        }

        // To avoid an endless cycle
        for (City city : copyOfCities) {

            if (city.id == endIndex) {
                city.routs.clear();
                city.cost.clear();
            }

            // remove the startIndex in cities of List
            if (city.routs != null && city.routs.contains(startIndex)) {
                int removeIndex = city.routs.indexOf(startIndex);
                city.routs.remove(removeIndex);
                city.cost.remove(removeIndex);
            }
        }


        City startCity = new City(copyOfCities.get(startIndex - 1));
        City currentCity = startCity;

        ArrayList<City> copy = new ArrayList<>();
        copy.add(startCity);

        ArrayList<Integer> thisRout = new ArrayList<>();
        thisRout.add(startIndex);

        ArrayList<Integer> thisCost = new ArrayList<>();

        while (!startCity.routs.isEmpty()) {

            //return to the previous element in this route
            if (currentCity.routs.isEmpty()) {
                copy.remove(currentCity);
                currentCity = copy.get(copy.size() - 1);
                currentCity.routs.remove(0);
                currentCity.cost.remove(0);
                thisRout.remove(thisRout.size() - 1);
                thisCost.remove(thisCost.size() - 1);

            } else {

                //condition to prevent returning to elements that already exist in this rout
                if (thisRout.contains(currentCity.routs.get(0))) {
                    currentCity.routs.remove(0);
                    currentCity.cost.remove(0);


                } else {
                    // build this rout and adding to the general list of routes,
                    // when matching the final city
                    thisRout.add(currentCity.routs.get(0));
                    thisCost.add(currentCity.cost.get(0));

                    currentCity = new City(copyOfCities.get(currentCity.routs.get(0) - 1));
                    copy.add(currentCity);

                    if (currentCity.id == endIndex) {
                        routMap.add(new ArrayList<>(thisRout));
                        mapOfTripCost.add(new ArrayList<>(thisCost));
                    }
                }
            }
        }
    }


    static class City {
        final String name;
        final int id;
        int[][] nextCityIndexAndTripCost;
        List<Integer> routs = new ArrayList<>();
        List<Integer> cost = new ArrayList<>();

        public City(String name) {
            this.name = name;
            id = cities.size() + 1;
        }

        // constructor for create the copy of City object
        // !!! with the fields used by the method buildRoutMap() !!!
        public City(City original) {
            this.name = new String(original.name);
            this.id = original.id;
            this.routs = new ArrayList<>(original.routs);
            this.cost = new ArrayList<>(original.cost);
        }
    }

    public static void sortArrayOfNeighboringCitiesByIndex() {

        for (City city : cities) {
            int arrLength = city.nextCityIndexAndTripCost.length;
            if (arrLength > 1) {
                for (int j = 0; j + 1 < arrLength; j++) {
                    int currentElementInArr = city.nextCityIndexAndTripCost[j][0];
                    int nextElementInArr = city.nextCityIndexAndTripCost[j + 1][0];

                    if (currentElementInArr > nextElementInArr) {
                        int[] element = city.nextCityIndexAndTripCost[j + 1];
                        city.nextCityIndexAndTripCost[j + 1] = city.nextCityIndexAndTripCost[j];
                        city.nextCityIndexAndTripCost[j] = element;
                    }
                }
            }
        }
    }


    public static int[] chekCitiesInList(String start, String finish) {
        int indexOfStart = -1;
        int indexOfFinish = -1;
        boolean chekStart = false;
        boolean chekFinish = false;

        for (City city : cities) {
            if (city.name.equalsIgnoreCase(start)) {
                indexOfStart = city.id;
                chekStart = true;
            } else if (city.name.equalsIgnoreCase(finish)) {
                indexOfFinish = city.id;
                chekFinish = true;
            }
        }

        if (!chekStart || !chekFinish) return null;

        if (indexOfStart > indexOfFinish) {
            int index = indexOfStart;
            indexOfStart = indexOfFinish;
            indexOfFinish = index;
        }

        return new int[]{indexOfStart, indexOfFinish};
    }


}



