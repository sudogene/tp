package seedu.canoe.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.canoe.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Id(null));
    }

    @Test
    public void isValidId() {
        // null Id
        assertThrows(NullPointerException.class, () -> Id.isValidId(null));

        // invalid Id values
        assertFalse(Id.isValidId("")); // empty string
        assertFalse(Id.isValidId(" ")); // spaces only
        assertFalse(Id.isValidId("one")); // non-numeric
        assertFalse(Id.isValidId("1a2")); // alphabets within digits
        assertFalse(Id.isValidId("1 2")); // spaces within digits

        // valid Id values
        assertTrue(Id.isValidId("234"));
        assertTrue(Id.isValidId("209"));
        assertTrue(Id.isValidId("202"));
    }

    @Test
    public void isUsedId() {
        // NOTE: Tests on uniqueness of Id values will be trickier due to mutability
        new Id("210");
        assertFalse(Id.isUsedId("300"));
    }

    @Test
    public void getPlaceHolderId() {
        assertEquals(Id.getPlaceHolderId(), new Id(Id.PLACEHOLDER_VALUE));
    }

    @Test
    public void getLastUsedId() {
        // Last used Id is based on last successfully created Student, not query Id.
        int queryId = 300;
        new Id(String.valueOf(queryId)); // Query Id
        assertNotEquals(queryId, Id.getLastUsedId());
    }
}
