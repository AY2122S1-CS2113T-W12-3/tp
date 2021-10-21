package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.Parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import static seedu.duke.Duke.eventCatalog;

public class AddCommandTest {

    @Test
    public void addCommand_validData_correctlyConstructed() {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in1 = new ByteArrayInputStream("Birthday barbeque".getBytes());
        System.setIn(in1);

        Command command1 = Parser.parseCommand("add -e n/Barbeque d/31-12-2022 1800 v/Home Grill b/100");
        command1.execute();
        assertEquals("[E][ ] Barbeque (at: 31 Dec 2022 - 18:00)", eventCatalog.get(0).toString());
        System.setIn(sysInBackup);

        ByteArrayInputStream in2 = new ByteArrayInputStream("Y".getBytes());
        System.setIn(in2);

        Command command2 = Parser.parseCommand("delete all");
        command2.execute();
        System.setIn(sysInBackup);
    }
}
