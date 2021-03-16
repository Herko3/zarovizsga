package hu.nive.ujratervezes.zarovizsga.workhours;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class WorkHours {

    public String minWork(String file){
        Path path = Path.of(file);
        List<WorkSheet> workData = readFile(path);

        int minHour = getMinHour(workData);
        WorkSheet result = getWorkSheetFromWorkHour(workData,minHour);

        return result.getName() + ": " + result.getDate();
    }

    private List<WorkSheet> readFile(Path path){
        List<WorkSheet> workData = new ArrayList<>();
        try(BufferedReader reader = Files.newBufferedReader(path)){

            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                String name = parts[0];
                int hour = Integer.parseInt(parts[1]);
                LocalDate date = LocalDate.parse(parts[2]);
                workData.add(new WorkSheet(name,hour,date));
            }

        } catch (IOException ioe){
            throw new IllegalStateException("Cannot read file", ioe);
        } catch (NumberFormatException nfe){
            throw new IllegalArgumentException("Workhour is not a number", nfe);
        }

        return workData;
    }

    private int getMinHour(List<WorkSheet> workData){
        OptionalInt result = workData.stream()
        .mapToInt(WorkSheet::getHours).min();

        if(result.isPresent()){
            return result.getAsInt();
        }
        throw new IllegalArgumentException("No value to compare");
    }

    private WorkSheet getWorkSheetFromWorkHour(List<WorkSheet> workData,int hour){
        Optional<WorkSheet> result = workData.stream()
                .filter(workSheet -> workSheet.getHours() == hour).findAny();

        if(result.isPresent()){
            return result.get();
        }
        throw new IllegalArgumentException("No data with that filter");
    }
}
