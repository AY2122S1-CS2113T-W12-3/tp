package seedu.duke.storage;

import seedu.duke.items.Event;
import seedu.duke.items.mainlists.EventCatalog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StorageFile {

    private static final String DEFAULT_FILE_PATH = "data/slamData.txt";
    private static final String DEFAULT_DIRECTORY = "data";

    public void save(ArrayList<Event> eventsList) {

        File saveFile = new File(DEFAULT_FILE_PATH);
        checkFileIsValid(saveFile);
        List<String> encodedEventsList = EventEncoder.encodeEventsList(eventsList);
        try {
            writeToFile(saveFile, encodedEventsList);
        } catch (IOException e) {
            System.out.println("Error writing to file at path: " + DEFAULT_FILE_PATH);
        }
    }

    private static void checkFileIsValid(File saveFile) {

        try {
            if (!saveFile.exists()) {
                Files.createDirectories(Path.of(DEFAULT_DIRECTORY));
                Files.createFile(Path.of(DEFAULT_FILE_PATH));
            }
        } catch (IOException e) {
            System.out.println("Error with default directory/file_path.");
        }
    }

    private void writeToFile(File saveFile, List<String> encodedEventsList)
            throws IOException {

        FileWriter eventsWriter = new FileWriter(saveFile);
        for (String item : encodedEventsList) {
            eventsWriter.write(item + "\n");
        }
        eventsWriter.close();
    }

    public void load(EventCatalog eventCatalog) {
        File saveFile = new File(DEFAULT_FILE_PATH);
        try {
            List<String> encodedItems = getStringsFromFile(saveFile);
            assert encodedItems.get(0).startsWith("e") : "First String in list should be an Event";
            for (String item : encodedItems) {
                if (item.startsWith("e")) {
                    eventCatalog.add(EventDecoder.decodeEventFromString(item));
                }
                if (item.startsWith("t")) {
                    eventCatalog.get(eventCatalog.size() - 1)
                            .addToTaskList(TaskDecoder.decodeTaskFromString(item));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oooh a new user!");
        }
    }

    private List<String> getStringsFromFile(File saveFile) throws FileNotFoundException {
        List<String> encodedItems = new ArrayList<>();
        Scanner myScanner = new Scanner(saveFile);
        while (myScanner.hasNextLine()) {
            String data = myScanner.nextLine();
            encodedItems.add(data);
        }

        return encodedItems;
    }
}
