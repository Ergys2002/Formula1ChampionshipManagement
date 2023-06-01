import java.util.Objects;

public abstract class Driver {
    private String name;
    private String nationality;
    private final String team;

    public Driver(String name, String nationality, String team) {
        this.name = name;
        this.nationality = nationality;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getTeam() {
        return team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Driver driver = (Driver) o;
        return Objects.equals(name, driver.name) && Objects.equals(nationality, driver.nationality) && Objects.equals(team, driver.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nationality, team);
    }

    @Override
    public String toString() {
        return  "\nName:" + name +
                "\nNationality:" + nationality +
                "\nTeam:" + team;
    }
    public String toFile(){
        return name+"~"+nationality+"~"+team+"~";
    }
}
