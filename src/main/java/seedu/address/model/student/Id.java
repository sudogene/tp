package seedu.address.model.student;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Id {

    public static final String MESSAGE_CONSTRAINTS = "The Id must be unique.";

    // list of all used Id values to ensure uniqueness of each Id.
    private static final Set<String> usedIds = new HashSet<>();

    public final String value;

    /**
     * Constructs an {@code Id}.
     *
     * @param id A valid unique id.
     */
    public Id(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        Id.usedIds.add(id);
        value = id;
    }

    public static boolean isValidId(String test) {
        return !Id.usedIds.contains(test);
    }



}
