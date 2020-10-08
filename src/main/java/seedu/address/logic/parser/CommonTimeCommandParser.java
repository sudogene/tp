package seedu.address.logic.parser;

import seedu.address.logic.commands.CommonTimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class CommonTimeCommandParser implements Parser<CommonTimeCommand> {

    public CommonTimeCommand parse(String args) throws ParseException {
        
        return new CommonTimeCommand();
    }
}
