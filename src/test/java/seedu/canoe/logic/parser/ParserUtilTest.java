package seedu.canoe.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.canoe.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.canoe.testutil.Assert.assertThrows;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.canoe.logic.parser.exceptions.ParseException;
import seedu.canoe.model.student.Email;
import seedu.canoe.model.student.Id;
import seedu.canoe.model.student.Name;
import seedu.canoe.model.student.Phone;
import seedu.canoe.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_ID_LEADING_ZERO = "001";
    private static final String INVALID_ID_ALPHABET = "1a";
    private static final String INVALID_ID_NEGATIVE = "-1";
    private static final String[] INVALID_MULTIPLE_IDS = {"4", "b"};

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_ID = "1";
    private static final String[] VALID_MULTIPLE_IDS = {"2", "3"};

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_STUDENT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseIdNullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIdValue((String) null));
    }

    @Test
    public void parseIdInvalidValueThrowsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIdValue(INVALID_ID_LEADING_ZERO));
        assertThrows(ParseException.class, () -> ParserUtil.parseIdValue(INVALID_ID_ALPHABET));
        assertThrows(ParseException.class, () -> ParserUtil.parseIdValue(INVALID_ID_NEGATIVE));
    }

    @Test
    public void parseIdValidValueWithoutWhitespaceReturnsId() throws Exception {
        String expectedId = new Id(VALID_ID).getValue();
        assertEquals(expectedId, ParserUtil.parseIdValue(VALID_ID));
    }

    @Test
    public void parseIdValidValueWithWhitespaceReturnsTrimmedId() throws Exception {
        String idWithWhitespace = WHITESPACE + VALID_ID + WHITESPACE;
        String expectedId = new Id(VALID_ID).getValue();
        assertEquals(expectedId, ParserUtil.parseIdValue(idWithWhitespace));
    }

    @Test
    public void parseMultipleIdsNullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMultipleIds(null));
    }

    @Test
    public void parseMultipleIdsContainsInvalidValueThrowsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMultipleIds(INVALID_MULTIPLE_IDS));
    }

    @Test
    public void parseMultipleIdsValidValuesWithoutWhitespaceReturnsIds() throws Exception {
        List<String> expectedIdList = List.of("2", "3");
        assertEquals(expectedIdList, ParserUtil.parseMultipleIds(VALID_MULTIPLE_IDS));
    }

    @Test
    public void parseMultipleIdsValidValuesWithWhitespaceReturnsTrimmedIds() throws Exception {
        String idWithWhitespace = WHITESPACE + VALID_ID + WHITESPACE;
        String[] validIdListWithWhitespace = {idWithWhitespace, "2"};
        List<String> expectedIdList = List.of("1", "2");
        assertEquals(expectedIdList, ParserUtil.parseMultipleIds(validIdListWithWhitespace));
    }
}
