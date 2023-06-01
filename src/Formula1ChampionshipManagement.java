import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.Timer;


public class Formula1ChampionshipManagement {
    BufferedWriter updatedListWriter;
    BufferedWriter saveTofile;
    private final List<Formula1Driver> drivers = new ArrayList<>();
    private final List<Race> races = new ArrayList<>();

    public void createNewDriver(JTextArea jte){
        jte.setText("");
        if(drivers == null){
            Formula1Driver newDriver = getDriverData();
            drivers.add(newDriver);
            saveDriverToFile(newDriver);
        }
        if(drivers.size()==10){
            MsgFrame msgFrame = new MsgFrame("Create new driver","Drivers list for this championship is completed!");
            msgFrame.setVisible(true);
            msgFrame.setLocationRelativeTo(null);
            jte.append("Drivers list for this championship is completed! +\n");
        }else {
            Formula1Driver newDriver = getDriverData();

            if(drivers.stream().anyMatch(driver -> driver.getTeam().equals(newDriver.getTeam()))){
                MsgFrame msgFrame = new MsgFrame("Create new driver","Team already exists.");
                msgFrame.setVisible(true);
                msgFrame.setLocationRelativeTo(null);
                jte.append("Team already exists.\n");
            }else{
                drivers.add(newDriver);
                jte.append("Driver created succesfully");
                saveDriverToFile(newDriver);
            }
        }

    }
    private Formula1Driver getDriverData(){
        String driverName = JOptionPane.showInputDialog("Please enter driver name: ");
        String driverNationality = JOptionPane.showInputDialog("Please enter driver Nationality: ");
        String driverTeam = JOptionPane.showInputDialog("Please enter driver Team: ");
        return new Formula1Driver(driverName,driverNationality,driverTeam);
    }

    public void removeExistingDriver(JTextArea jte){
        jte.setText("");
        if(drivers.size() == 0){
            MsgFrame msgFrame = new MsgFrame("Driver size == 0","There are no drivers left");
            msgFrame.setVisible(true);
            msgFrame.setLocationRelativeTo(null);
            jte.append("There are no drivers left \n");
        }else {
            Driver driverToBeRemoved = getDriverData();
            if (!drivers.contains(driverToBeRemoved)) {
                MsgFrame msgFrame = new MsgFrame("Data for the driver you want to remove...","Driver to be removed doesn't exist.");
                msgFrame.setVisible(true);
                msgFrame.setLocationRelativeTo(null);
                jte.append("Driver to be removed doesn't exist.\n");
            } else {
                drivers.remove(driverToBeRemoved);
                jte.append("Driver removed succesfully");
                saveUpdatedListOfDriversToFile();
            }
        }
    }

    public void changeDriverForExistingTeam(JTextArea jte){
        jte.setText("");
        if(drivers.size() == 0){
            MsgFrame msgFrame = new MsgFrame("ChangeDriverForExistingTeam","There are no drivers left");
            msgFrame.setVisible(true);
            msgFrame.setLocationRelativeTo(null);
            jte.append("There are no drivers left \n");
        }else {
            String team = JOptionPane.showInputDialog("Enter the team which you want to change the driver: ");
            if (drivers.stream().noneMatch(driver -> driver.getTeam().equals(team))) {
                MsgFrame msgFrame = new MsgFrame("ChangeDriverForExistingTeam","Team doesn't exist.");
                msgFrame.setVisible(true);
                msgFrame.setLocationRelativeTo(null);
                jte.append("Team doesn't exist.\n");
            } else {
                    Formula1Driver driverForChanging;
                String newDriverName = JOptionPane.showInputDialog("Enter new driver name: " );
                String newDriverNationality = JOptionPane.showInputDialog("Enter new driver nationality: ");
                driverForChanging = new Formula1Driver(newDriverName, newDriverNationality, team);

                for (Formula1Driver driverToBeChanged : drivers) {
                    if (driverToBeChanged.getTeam().equals(team)) {
                        driverToBeChanged.setName(driverForChanging.getName());
                        driverToBeChanged.setNationality(driverForChanging.getNationality());
                        MsgFrame msgFrame = new MsgFrame("ChangeDriverForExistingTeam","Driver of teamm changed succesfully " );
                        msgFrame.setVisible(true);
                        msgFrame.setLocationRelativeTo(null);
                        jte.append("Driver of teamm changed succesfully !");
                        break;
                    }
                }
                saveUpdatedListOfDriversToFile();
            }
        }
    }

    public void getStatisticsForTheSpecifiedDriver(JTextArea jte){
        jte.setText("");
        if(drivers.size() == 0){
            MsgFrame msgFrame = new MsgFrame("GetStatisticsForTheSpecifiedDriver","No driver exists.");
            msgFrame.setVisible(true);
            msgFrame.setLocationRelativeTo(null);
            jte.append("No driver exists.\n");
        }else {
            String nameToBeSearched = JOptionPane.showInputDialog("Enter driver name:");
            if (drivers.stream().noneMatch(driver -> driver.getName().equals(nameToBeSearched))) {
                MsgFrame msgFrame = new MsgFrame("GetStatisticsForTheSpecifiedDriver","The driver you searched for doesn't exist.");
                msgFrame.setVisible(true);
                msgFrame.setLocationRelativeTo(null);
                jte.append("The driver you searched for doesn't exist.\n");
            } else {
                String x = String.valueOf(drivers.stream().filter(driver -> driver.getName().equals(nameToBeSearched)).findFirst());
                jte.append(x + "\n");
            }
        }
    }

    public void printOrderedListOfDrivers(JTextArea jte){
        jte.setText("");
        if(drivers.size() == 0){
            MsgFrame msgFrame = new MsgFrame("PrintOrderedListOfDrivers","No drivers in the table");
            msgFrame.setVisible(true);
            msgFrame.setLocationRelativeTo(null);
            jte.append("No drivers in the table");
        }else {
            List<Formula1Driver> sortedListOfDrivers = drivers.stream().sorted(Comparator
                    .comparing(Formula1Driver::getPointsGathered)
                    .thenComparing(Formula1Driver::getTimesInTheFirstPlace)
                    .thenComparing(Formula1Driver::getTimesInTheSecondPlace)
                    .thenComparing(Formula1Driver::getTimesInTheThirdPlace)).toList();

            List<Formula1Driver> reversedList = new ArrayList<>(sortedListOfDrivers);
            Collections.reverse(reversedList);

           Formula1Driver[] array = reversedList.toArray(new Formula1Driver[reversedList.size()]);
            for (Formula1Driver driver: array) {
                jte.append(driver.toString() + "\n");
            }
        }
    }
    public void createRace(JTextArea jte){
        if(drivers.size() != 10){
            MsgFrame msgFrame = new MsgFrame("Race Creation Error","No enough drivers to start the race");
            msgFrame.setVisible(true);
            msgFrame.setLocationRelativeTo(null);
            jte.append("No enough drivers to start the race \n");
        }else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime date = LocalDateTime.now();
            List<Formula1Driver> newOrderOfRace = drivers;
            Collections.shuffle(newOrderOfRace);
            Formula1Driver[] helpArray = new Formula1Driver[drivers.size()];
            Race raceCreated = new Race(newOrderOfRace, dtf.format(date));
            for (int i = 0; i < drivers.size(); i++) {
                helpArray[i] = newOrderOfRace.get(i);
            }
            for (Formula1Driver driverToBeChangedStats : drivers) {
                if (driverToBeChangedStats.equals(helpArray[0])) {
                    driverToBeChangedStats.setPointsGathered(driverToBeChangedStats.getPointsGathered() + 25);
                    driverToBeChangedStats.setNumberOfRacesTakenPart(driverToBeChangedStats.getNumberOfRacesTakenPart() + 1);
                    driverToBeChangedStats.setTimesInTheFirstPlace(driverToBeChangedStats.getTimesInTheFirstPlace() + 1);
                } else if (driverToBeChangedStats.equals(helpArray[1])) {
                    driverToBeChangedStats.setPointsGathered(driverToBeChangedStats.getPointsGathered() + 18);
                    driverToBeChangedStats.setNumberOfRacesTakenPart(driverToBeChangedStats.getNumberOfRacesTakenPart() + 1);
                    driverToBeChangedStats.setTimesInTheSecondPlace(driverToBeChangedStats.getTimesInTheSecondPlace() + 1);
                } else if (driverToBeChangedStats.equals(helpArray[2])) {
                    driverToBeChangedStats.setPointsGathered(driverToBeChangedStats.getPointsGathered() + 15);
                    driverToBeChangedStats.setNumberOfRacesTakenPart(driverToBeChangedStats.getNumberOfRacesTakenPart() + 1);
                    driverToBeChangedStats.setTimesInTheThirdPlace(driverToBeChangedStats.getTimesInTheThirdPlace() + 1);
                } else if (driverToBeChangedStats.equals(helpArray[3])) {
                    driverToBeChangedStats.setPointsGathered(driverToBeChangedStats.getPointsGathered() + 12);
                    driverToBeChangedStats.setNumberOfRacesTakenPart(driverToBeChangedStats.getNumberOfRacesTakenPart() + 1);
                } else if (driverToBeChangedStats.equals(helpArray[4])) {
                    driverToBeChangedStats.setPointsGathered(driverToBeChangedStats.getPointsGathered() + 10);
                    driverToBeChangedStats.setNumberOfRacesTakenPart(driverToBeChangedStats.getNumberOfRacesTakenPart() + 1);
                } else if (driverToBeChangedStats.equals(helpArray[5])) {
                    driverToBeChangedStats.setPointsGathered(driverToBeChangedStats.getPointsGathered() + 8);
                    driverToBeChangedStats.setNumberOfRacesTakenPart(driverToBeChangedStats.getNumberOfRacesTakenPart() + 1);
                } else if (driverToBeChangedStats.equals(helpArray[6])) {
                    driverToBeChangedStats.setPointsGathered(driverToBeChangedStats.getPointsGathered() + 6);
                    driverToBeChangedStats.setNumberOfRacesTakenPart(driverToBeChangedStats.getNumberOfRacesTakenPart() + 1);
                } else if (driverToBeChangedStats.equals(helpArray[7])) {
                    driverToBeChangedStats.setPointsGathered(driverToBeChangedStats.getPointsGathered() + 4);
                    driverToBeChangedStats.setNumberOfRacesTakenPart(driverToBeChangedStats.getNumberOfRacesTakenPart() + 1);
                } else if (driverToBeChangedStats.equals(helpArray[8])) {
                    driverToBeChangedStats.setPointsGathered(driverToBeChangedStats.getPointsGathered() + 2);
                    driverToBeChangedStats.setNumberOfRacesTakenPart(driverToBeChangedStats.getNumberOfRacesTakenPart() + 1);
                } else if (driverToBeChangedStats.equals(helpArray[9])) {
                    driverToBeChangedStats.setPointsGathered(driverToBeChangedStats.getPointsGathered() + 1);
                    driverToBeChangedStats.setNumberOfRacesTakenPart(driverToBeChangedStats.getNumberOfRacesTakenPart() + 1);
                }
            }
            saveUpdatedListOfDriversToFile();
            printRaceStats(newOrderOfRace, dtf.format(date),jte);
            MsgFrame msgFrame = new MsgFrame("Created Race","Race started...");
            msgFrame.setVisible(true);
            msgFrame.setLocationRelativeTo(null);

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    msgFrame.dispose();
                }
            }, 2000);
            races.add(raceCreated);
            saveRaceToFile(raceCreated);
        }
    }

    private void printRaceStats(List<Formula1Driver> finishedRaceStats, String date,JTextArea jte){
        jte.setText("");

        String string0 = "Date of race:" + date;
        String string1 = "THE 1-ST PLACE WINNER!!!";
        String string2 = "Name:"+finishedRaceStats.get(0).getName()+ " | Team:" + finishedRaceStats.get(0).getTeam() + " | Nationality:" + finishedRaceStats.get(0).getNationality();
        String string3 = "THE 2-ND PLACE WINNER!!!";
        String string4 = "Name:"+finishedRaceStats.get(1).getName()+ " | Team:" + finishedRaceStats.get(1).getTeam() + " | Nationality:" + finishedRaceStats.get(1).getNationality();
        String string5 = "THE 3-RD PLACE WINNER!!!";
        String string6 = "Name:"+finishedRaceStats.get(2).getName()+ " | Team:" + finishedRaceStats.get(2).getTeam() + " | Nationality:" + finishedRaceStats.get(2).getNationality();

        ArrayList<String> str = new ArrayList<>();
        str.add(string0);
        str.add(string1);
        str.add(string2);
        str.add(string3);
        str.add(string4);
        str.add(string5);
        str.add(string6);

        for(int i = 4;i <= drivers.size();i++){
            str.add("THE " + i +"-TH PLACE WINNER!!!");
            str.add("Name:"+finishedRaceStats.get(i-1).getName()+
                    " | Team:" + finishedRaceStats.get(i-1).getTeam() +
                    " | Nationality:" + finishedRaceStats.get(i-1).getNationality());
        }
        String[] castArray = str.toArray(new String[str.size()]);

        for (String s : castArray) {
            jte.append(s + "\n");
        }
    }
    public void saveUpdatedListOfDriversToFile(){
        try {
            updatedListWriter = new BufferedWriter(new FileWriter("Files/DriversFile.txt"));
            for(Formula1Driver driver: drivers){
                updatedListWriter.write(driver.toFile());
                updatedListWriter.newLine();
                updatedListWriter.flush();
            }
            updatedListWriter.close();
        } catch (IOException e) {
            MsgFrame msgFrame = new MsgFrame("saveUpdatedListOfDriversToFile","Problem with DriversFile!");
            msgFrame.setVisible(true);
            msgFrame.setLocationRelativeTo(null);
        }
    }

    public void saveDriverToFile(Formula1Driver driverToBeSaved){
        try {
            saveTofile = new BufferedWriter(new FileWriter("Files/DriversFile.txt",true));
            saveTofile.write(driverToBeSaved.toFile());
            saveTofile.newLine();
            saveTofile.flush();
            saveTofile.close();

        }catch (Exception e){
            MsgFrame msgFrame = new MsgFrame("saveDriverToFile","File not found");
            msgFrame.setVisible(true);
            msgFrame.setLocationRelativeTo(null);
        }
    }

    public void readDriversFromFile(){
        try {
            BufferedReader readDrivers = new BufferedReader(new FileReader("Files/DriversFile.txt"));
            String lineReadFromFile;
            while((lineReadFromFile = readDrivers.readLine()) != null){
                String[] driverData = lineReadFromFile.split("~");
                Formula1Driver tempDriver = new Formula1Driver(driverData[0], driverData[1], driverData[2], Integer.parseInt(driverData[3]),Integer.parseInt(driverData[4]), Integer.parseInt(driverData[5]),Integer.parseInt(driverData[6]),Integer.parseInt(driverData[7]));
                this.drivers.add(tempDriver);
            }
        } catch (IOException e) {
            MsgFrame msgFrame = new MsgFrame("readDriversFromFile","File not found");
            msgFrame.setVisible(true);
            msgFrame.setLocationRelativeTo(null);
        }

    }

    public void saveRaceToFile(Race race){
        try {
            BufferedWriter raceWriter = new BufferedWriter(new FileWriter("Files/RacesFile.txt", true));
            raceWriter.write(race.toFile());
            raceWriter.newLine();
            raceWriter.flush();
        } catch (IOException e) {
            MsgFrame msgFrame = new MsgFrame("saveRaceToFile","File not found");
            msgFrame.setVisible(true);
            msgFrame.setLocationRelativeTo(null);
        }
    }

    public void uploadRacesFromFile(){
        try {
            BufferedReader readRaceFromFile = new BufferedReader(new FileReader("Files/RacesFile.txt"));
            String lineReadFromRacesFile;
            while((lineReadFromRacesFile = readRaceFromFile.readLine()) != null){
                String[] helpArray = lineReadFromRacesFile.split("~");
                String date = helpArray[0];
                List<Formula1Driver> driversList = new ArrayList<>();
                for(int i=1;i<drivers.size()*8;i+=8) {
                    driversList.add(new Formula1Driver(
                            helpArray[i],
                            helpArray[i+1],
                            helpArray[i+2],
                            Integer.parseInt(helpArray[i+3]),
                            Integer.parseInt(helpArray[i+4]),
                            Integer.parseInt(helpArray[i+5]),
                            Integer.parseInt(helpArray[i+6]),
                            Integer.parseInt(helpArray[i+7])));
                }
                races.add(new Race(driversList, date));
            }
        } catch (Exception e) {
            MsgFrame msgFrame = new MsgFrame("uploadRacesFromFile","File not found");
            msgFrame.setVisible(true);
            msgFrame.setLocationRelativeTo(null);
            System.out.println(e.getMessage());
        }
    }

    public void printOrderedListOfDriversByFirstPlace(JTextArea jte) {
        jte.setText("");
        if (drivers.size() == 0) {
            jte.append("No drivers in the table\n");
        } else {
            List<Formula1Driver> sortedListOfDrivers = drivers.stream().sorted(Comparator
                    .comparing(Formula1Driver::getTimesInTheFirstPlace)
                    .thenComparing(Formula1Driver::getTimesInTheSecondPlace)
                    .thenComparing(Formula1Driver::getTimesInTheThirdPlace)).toList();

            List<Formula1Driver> reversedList = new ArrayList<>(sortedListOfDrivers);
            Collections.reverse(reversedList);
            Formula1Driver[] array = reversedList.toArray(new Formula1Driver[reversedList.size()]);
            for (Formula1Driver driver: array) {
                jte.append(driver.toString() + "\n");
            }

        }
    }

    public void printAllRaces(JTextArea jte){
        jte.setText("");
        String driverName = JOptionPane.showInputDialog("Enter the driver which you want to see the races: ");
        for(Race race: races){
            for(Formula1Driver driver: race.getDriversList()){
                if(driver.getName().equals(driverName)){
                    jte.append(race + "");
                }
            }
        }
    }
}

