public class Formula1Driver extends Driver{
    private int timesInTheFirstPlace;
    private int timesInTheSecondPlace;
    private int timesInTheThirdPlace;
    private int pointsGathered;
    private int numberOfRacesTakenPart;

    public Formula1Driver(String name, String nationality, String team) {
        super(name, nationality, team);
        this.timesInTheFirstPlace = 0;
        this.timesInTheSecondPlace = 0;
        this.timesInTheThirdPlace = 0;
        this.pointsGathered = 0;
        this.numberOfRacesTakenPart = 0;
    }

    public Formula1Driver(String name, String nationality, String team, int timesInTheFirstPlace, int timesInTheSecondPlace, int timesInTheThirdPlace, int pointsGathered, int numberOfRacesTakenPart) {
        super(name, nationality, team);
        this.timesInTheFirstPlace = timesInTheFirstPlace;
        this.timesInTheSecondPlace = timesInTheSecondPlace;
        this.timesInTheThirdPlace = timesInTheThirdPlace;
        this.pointsGathered = pointsGathered;
        this.numberOfRacesTakenPart = numberOfRacesTakenPart;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nTimes In The First Place:" + timesInTheFirstPlace +
                "\nTimes In The Second Place:" + timesInTheSecondPlace +
                "\nTimes In The Third Place:" + timesInTheThirdPlace +
                "\nPoints Gathered:" + pointsGathered +
                "\nNumber Of Races Taken Part:" + numberOfRacesTakenPart;
    }

    public String toFile(){
        return super.toFile()+timesInTheFirstPlace+"~"+timesInTheSecondPlace+"~"+timesInTheThirdPlace+"~"+pointsGathered+"~"+numberOfRacesTakenPart;
    }

    public int getTimesInTheFirstPlace() {
        return timesInTheFirstPlace;
    }

    public int getTimesInTheSecondPlace() {
        return timesInTheSecondPlace;
    }

    public int getTimesInTheThirdPlace() {
        return timesInTheThirdPlace;
    }

    public int getPointsGathered() {
        return pointsGathered;
    }

    public int getNumberOfRacesTakenPart() {
        return numberOfRacesTakenPart;
    }

    public void setTimesInTheFirstPlace(int timesInTheFirstPlace) {
        this.timesInTheFirstPlace = timesInTheFirstPlace;
    }

    public void setTimesInTheSecondPlace(int timesInTheSecondPlace) {
        this.timesInTheSecondPlace = timesInTheSecondPlace;
    }

    public void setTimesInTheThirdPlace(int timesInTheThirdPlace) {
        this.timesInTheThirdPlace = timesInTheThirdPlace;
    }

    public void setPointsGathered(int pointsGathered) {
        this.pointsGathered = pointsGathered;
    }

    public void setNumberOfRacesTakenPart(int numberOfRacesTakenPart) {
        this.numberOfRacesTakenPart = numberOfRacesTakenPart;
    }

}
