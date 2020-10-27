package seedu.canoe.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.canoe.commons.core.GuiSettings;
import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.logic.commands.Command;
import seedu.canoe.logic.commands.CommandResult;
import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.logic.parser.CanoeCoachParser;
import seedu.canoe.logic.parser.exceptions.ParseException;
import seedu.canoe.model.Model;
import seedu.canoe.model.ReadOnlyCanoeCoach;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.training.Training;
import seedu.canoe.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final CanoeCoachParser canoeCoachParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        canoeCoachParser = new CanoeCoachParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = canoeCoachParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveCanoeCoach(model.getCanoeCoach());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyCanoeCoach getCanoeCoach() {
        return model.getCanoeCoach();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public ObservableList<Training> getFilteredTrainingList() {
        return model.getFilteredTrainingList();
    }

    @Override
    public Path getCanoeCoachFilePath() {
        return model.getCanoeCoachFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
