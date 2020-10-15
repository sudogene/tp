---
layout: page
title: User Guide
---

CanoE-COACH is a **desktop app for managing training schedules for secondary school canoe teams, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical Interface (GUI). If you can type fast, CanoE-COACH can get your training scheduling done faster than traditional GUI apps.

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

   * **`add`**`n/Steven Soo p/98665432 e/stev@example.com ay/2` : Adds a student named
    `Steven Soo` to the student list.

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
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/treasurer` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/treasurer`, `t/treasurer t/EXCO` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

### Viewing help: `help`
Shows a summarised list of available commands.

Format: `help`

### Adding a student: `add`
Adds a student to the student list. The student will be auto-assigned a unique `id`.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL ay/ACADEMIC_YEAR [d1/HHmm d2/HHmm d3/HHmm d4/HHmm d5/HHmm] [t/TAG]`

<center>

| Prefix   |      Representation      |
|:----------:|:-------------:|
| `d1/` |  **Monday's** Dismissal Time|
| `d2/` |    **Tuesday's** Dismissal Time   |
| `d3/` | **Wednesday's** Dismissal Time |
| `d4/` | **Thursday's** Dismissal Time |
| `d5/` | **Friday's** Dismissal Time |

</center>

* Academic year ranges from `1` to `5` and represents Secondary 1 to 5.
* `d1/ d2/ d3/ d4/ d5/` corresponds to the dismissal times on different days. They are optional fields.
* The default dismissal time is 1500. If the user inputs `d1/1700 d5/1200`, then the record will be equivalent to `d1/1700 d2/1500 d3/1500 d4/1500 d5/1200`

> Be careful of adding duplicates:
> * Student names can be the same, but they should not share the same contact number **AND** email address

Examples:
- `add n/Benjamin p/12345678 e/littleicemaiden@rocket.com ay/3 d1/1330 d2/1730 d3/1600 d4/1200 d5/1100`
- `add n/Sarah p/56781234 e/sa.rah@googoo.com ay/1 d1/1700 d3/1430`

### Editing a student : `edit`

Edits an existing student in the student list.

Format: `edit STUDENT_INDEX [n/NAME] [p/PHONE] [e/EMAIL] [ay/ACADEMIC_YEAR] [d1/HHmm d2/HHmm d3/HHmm d4/HHmm d5/HHmm] [t/TAG]…​`

* Edits the student at the specified `STUDENT_INDEX`. The student index refers to the index number shown in the displayed student list. The student index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.
* You can remove all of the student’s tags by typing `t/` without specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com d1/1600` Edits the phone number and email address of the 1st student in the displayed student list to be `91234567` and `johndoe@example.com` respectively. This also changes his Monday's dismissal time to 1600.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd student to be `Betsy Crower` and clears all existing tags.


### Delete student: `delete`
Deletes the specified student from the nominal roll.

Format: `delete STUDENT_INDEX`
- Deletes the student at the specified `STUDENT_INDEX`.
- The student index refers to the index number shown in the displayed student list.
- The index must be an unsigned integer 1, 2, 3, …

Examples:
- `delete 2` deletes the 2nd student in the displayed student list.

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

- Dismissal Time (`d1` to `d5`)
    - Students with dismissal times equal OR before the query time will be matched

      e.g. `d1/1500` will match `1500` and `1200`, but not `1530` on Monday

- Id
    - Student with the same `id` value will be matched.
    - Due to the nature of id being unique, only one student should be matched.

- Searching by more than one field
    - Find command will return student(s) that matches exactly with all the fields provided. e.g. `n/Alex p/123` will return `Alex Yeoh` only if his phone number matches `123`
    - Order in which fields are written does not matter. e.g. `find n/alex e/meow.com` is the same as `find e/meow.com n/alex`

Examples:
- `find n/alex david` returns `Alex Yeoh`, `David Li`
- `find n/alex david p/123` returns `Alex Yeoh`
- `find e/alexyeoh@example.com p/123` returns `Alex Yeoh`
- `find n/Alex d2/1600` returns `Alex Yeoh`, provided his dismissal time on Tuesday falls at or before `1600`
- `find e/alexyeoh@example.com p/456` returns an empty list, if such an email **AND** contact number is not present in the student list

### Common Time : `commonTime`
Returns the latest dismissal times on all days for all of the students in the specified subgroup. This would be the earliest
time to conduct training for all in the sub group.

Format: `commonTime [n/KEYWORDS] [ay/ACADEMIC_YEAR]`

- At least one field needs to be filled
- Name
    - The search is case-insensitive. e.g `hans` will match `Hans`
    - The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
    - Only full words will be matched e.g. `Han` will not match `Hans`
    - Students matching at least one keyword will be checked (i.e. OR search). e.g. `Hans Bo` will match `Hans Gruber`, `Bo Yang`

- Academic Year
    - Value will match exactly. e.g. `2` will match `2` but not `1`

- Searching by more than one field
    - Common Time command will return student(s) that matches any of the fields provided.

Examples:
- `commonTime n/alex ay/1` returns the latest dismissal times for any student with names containing the whole word `alex`, **OR** who are in Academic Year 1.
- `commonTime ay/1` returns the latest dismissal times for all the students in the student list who are in Academic Year 1.
- `commonTime n/Alex Jane Mary` returns the latest dismissal times for any student with names Alex, Jane or Mary. Take note the rules above for matching applies.

### Create Training : 'training'
Creates a new training at the specified date and time.

Format: `training yyyy-MM-dd HHmm`

Examples:
- `training 2020-10-10 1800`

### Delete Training : 'delete-training'
Deletes an existing training based on the training index.

* Training index refers to the index of the training in the displayed training list.
* All students inside of the training to be deleted will be cleared.
* Only one training index can be specified at a time.

Format: `delete-training TRAINING_INDEX`

Examples:
- `delete-training 2` removes the training with index 2 in the displayed training list. 

### Add Student to Training : 'ts-add'
Adds students to a training.

* Training index refers to the index of the training in the displayed training list.
* Student index refers to the index of the student in the displayed student list.
* Multiple students can be added with the same command by inputing multiple student indexes separated with a comma.
* Only one training index can be specified at a time.

> Note: Training schedules can also be viewed on the student list panel and they will update as you add students to trainings.

Format: `ts-add TRAINING_INDEX STUDENT_INDEX...`

Examples:
- `ts-add 2 1,2,3` adds students with indexes 1,2,3 to training 1.

### Delete Student from Training : 'ts-delete'
Deletes students from a training.

* Training index refers to the index of the training in the displayed training list.
* Student index refers to the index of the student in the displayed student list.
* Multiple students can be deleted with the same command by inputing multiple student indexes separated with a comma.
* Only one training index can be specified at a time.

> Note: Training schedules can also be viewed on the student list panel and they will update as you delete students from trainings.

Format: `ts-delete TRAINING_INDEX STUDENT_INDEX...`

Examples:
- `ts-delete 2 1,2,3` deletes students with indexes 1,2,3 from training 1.

### Clearing all entries: `clear`
Clears the student and training list of all existing students and trainings.

> Note: Be careful, this deletes all data stored inside of the program, including student and training records. All sample data will be cleared too.

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
**Delete** | `delete STUDENT_INDEX`<br> e.g., `delete 3`
**Edit** | `edit STUDENT_INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG]… [d1/MONDAY] [d2/TUESDAY] [d3/WEDNESDAY] [d4/THURSDAY] [d5/FRIDAY] ​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find [n/KEYWORDS] [p/PHONE_NUMBER] [ay/ACADEMIC_YEAR] [e/EMAIL] [d1/HHmm d2/HHmm d3/HHmm d4/HHmm d5/HHmm] [id/ID]`<br> e.g., `find n/James Jake ay/2`
**commonTime** | `commonTime [n/KEYWORDS] [ay/ACADEMIC_YEAR]`<br> e.g., `commonTime n/alex ay/1`
**training** | `training yyyy-MM-dd HHmm`<br> e.g., `training 2021-01-20 1800`
**delete-training** | `delete-training TRAINING_INDEX`<br> e.g., `delete-training 1`
**ts-add** | `ts-add TRAINING_INDEX STUDENT_INDEX...`<br> e.g., `ts-add 1 1,2,3`
**ts-delete** | `ts-delete TRAINING_INDEX STUDENT_INDEX...`<br> e.g., `ts-delete 1 1,2,3`
**List** | `list`
**Help** | `help`
