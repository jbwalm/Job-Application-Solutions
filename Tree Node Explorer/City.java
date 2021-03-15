import java.util.Collection;
import java.util.ArrayList;

public class City {
    private Collection<City> adjacentCities;
    private String name;
    private boolean beenHere = false;

    public City(String name) {
        this.name = name;
        this.adjacentCities = new ArrayList<City>();
    }

    /**
     * Name of the city, can be assumed to be unique
     */
    public String getName() {
        return name;
    }

    /**
     * Adjacent city are connected to this city directly by road
     */
    public Collection<City> getAdjacentCities() {
        return adjacentCities;
    }

    public void addAdjacentCity(City city) {
        adjacentCities.add(city);
        city.getAdjacentCities().add(this);
    }

    public boolean canDriveTo(City city) {
        if (this.beenHere){
            return false;
        }else{
            this.beenHere = true;
        }
        for (City temp : adjacentCities){
            if (temp.getName().equals(city.getName())){
                return true;
            }

            boolean outcome = temp.canDriveTo(city);
            if (outcome){
                    return outcome;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        City a = new City("A");
        City b = new City("B");
        City c = new City("C");
        City d = new City("D");
        City e = new City("E");
        City p = new City("P");


        a.addAdjacentCity(b);
        a.addAdjacentCity(c);
        a.addAdjacentCity(d);
        b.addAdjacentCity(c);
        c.addAdjacentCity(e);
        d.addAdjacentCity(p);

        System.out.println(a.canDriveTo(p));
    }
}