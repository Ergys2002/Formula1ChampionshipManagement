import java.util.List;

public class Race {
    private final List<Formula1Driver> driversList;
    private final String dateOfRace;
    public List<Formula1Driver> getDriversList(){
        return driversList;
    }
    public Race(List<Formula1Driver> driversList, String date){
        this.driversList = driversList;
        this.dateOfRace = date;
    }

    public String toFile(){
        String infoRaceFormatToFile = "";
        for(Formula1Driver driver: driversList){
            infoRaceFormatToFile = infoRaceFormatToFile.concat(driver.toFile()).concat("~");
        }
        return dateOfRace+"~"+infoRaceFormatToFile;
    }

    @Override
    public String toString() {
        return  "\n"+ dateOfRace + driversList;
    }
}
