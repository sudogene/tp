---
layout: page
title: User Guide
---

CanoE-COACH is a **desktop app for managing training schedules for secondary school canoe teams, optimized for use via
 a Command Line Interface** (CLI) while still having the benefits of a Graphical Interface (GUI). If you can type fast
, CanoE-COACH can get your training scheduling done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `CanoE-COACH.jar` from [here](https://github.com/AY2021S1-CS2103-F10-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your CanoE-COACH.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all students.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com ay/2` : Adds a student named
    `John Doe` to the student list.

   * **`delete`**`3` : Deletes the 3rd student shown in the student list.

   * **`clear`** : Deletes all students.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

### Viewing help: `help`
Shows a summarised list of available commands.

Format: `help`

### Adding a student: `add`
Adds a student to the student list. The student's Id is auto-assigned.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL ay/ACADEMIC_YEAR [d1/HHmm d2/HHmm d3/HHmm d4/HHmm d5/HHmm]`

<center>

| Prefix   |      Representation      |
|:----------:|:-------------:|
| `d1/` |  **Monday's** Dismissal Time|
| `d2/` |    **Tuesday's** Dismissal Time   |
| `d3/` | **Wednesday's** Dismissal Time |
| `d4/` | **Thursday's** Dismissal Time |
| `d5/` | **Friday's** Dismissal Time |

</center>

* Academic year ranges from `1` to `5`.
* `d1/ d2/ d3/ d4/ d5/` corresponds to the dismissal times on different days. They are optional fields.
* The default dismissal time is 1500. If the user inputs `d1/1700 d5/1200`, then the record will be equivalent to `d1/1700 d2/1500 d3/1500 d4/1500 d5/1200`

Examples:
- `add n/Benjamin p/12345678 e/littleicemaiden@rocket.com ay/3 d1/1330 d2/1730 d3/1600 d4/1200 d5/1100`
- `add n/Sarah p/56781234 e/sa.rah@googoo.com ay/1 d1/1700 d3/1430`

### Editing a student : `edit`

Edits an existing student in the student list.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [ay/ACADEMIC_YEAR] [d1/HHmm d2/HHmm d3/HHmm d4/HHmm d5/HHmm] [t/TAG]…​`

* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.
* You can remove all the student’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com d1/1600` Edits the phone number and email address of the 1st student to be `91234567` and `johndoe@example.com` respectively. Also changes Monday's dismissal time to 1600.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd student to be `Betsy Crower` and clears all existing tags.


### Delete student: `delete`
Deletes the specified student from the nominal roll.

Format: `delete INDEX`
- Deletes the student at the specified `INDEX`.
- The index refers to the index number shown in the displayed student list.
- The index must be an unsigned integer 1, 2, 3, …

Examples:
- `delete 2` deletes the 2nd student in the student list.

### Find : `find`
Find students based on specified fields.

Format: `find [n/KEYWORDS] [p/PHONE_VALUE] [ay/ACADEMIC_YEAR] [e/EMAIL] [d1/HHmm d2/HHmm d3/HHmm d4/HHmm d5/HHmm] [id/ID]`

- At least one field needs to be filled
- Name
    - The search is case-insensitive. e.g `hans` will match `Hans`
    - The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
    - Only full words will be matched e.g. `Han` will not match `Hans`
    - Students matching at least one keyword will be returned (i.e. OR search). e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

- Phone Number
    - Value will match exactly. e.g. `98765432` will not match `987654` but will match `98765432`
    - Only one phone number can be searched for at any one time

- Email
    - Students with emails containing the search word will be returned. e.g. `meow` will match `meow@domain.com`, `emailer@meow.com`
    - Only one email address can be searched for at any one time

- Academic Year
    - Value will match exactly. e.g. `2` will match `2` but not `1`

- Dismissal Time (d1 to d5)
    - Students with dismissal times equal OR before the query time will be matched

      e.g. `d1/1500` will match `1500` and `1200`, but not `1530` on Monday

- Id
    - Student with the same Id value will be matched.
    - Due to the nature of Id being unique, only one student should be matched.

- Searching by more than one field
    - Find command will return student(s) that matches exactly with all the fields provided. e.g. `n/Alex p/123` will return `Alex Yeoh` only if his phone number matches `123`
    - Order in which fields are written does not matter. e.g. `find n/alex e/meow.com` is the same as `find e/meow.com n/alex`

Examples:
- `find n/alex david` returns `Alex Yeoh`, `David Li`
- `find n/alex david p/123` returns `Alex Yeoh`
- `find e/alexyeoh@example.com p/123` returns `Alex Yeoh`
- `find n/Alex d2/1600` returns `Alex Yeoh`, provided his dismissal time on Tuesday falls before `1600`
- `find e/alexyeoh@example.com p/456` returns an empty list

### Clearing all entries: `clear`
Clears the terminal of all previous commands.

Format: `clear`

### Exiting the program: `exit`
Exits the program.

Format: `exit`


## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CanoE-COACH home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL ay/ACADEMIC_YEAR [t/TAG]… [d1/MONDAY] [d2/TUESDAY] [d3/WEDNESDAY] [d4/THURSDAY] [d5/FRIDAY]​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com ay/1 t/friend`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG]… [d1/MONDAY] [d2/TUESDAY] [d3/WEDNESDAY] [d4/THURSDAY] [d5/FRIDAY] ​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find [n/KEYWORDS] [p/PHONE_NUMBER] [ay/ACADEMIC_YEAR] [e/EMAIL] [d1/HHmm d2/HHmm d3/HHmm d4/HHmm d5/HHmm] [id/ID]`<br> e.g., `find n/James Jake ay/2`
**List** | `list`
**Help** | `help`
