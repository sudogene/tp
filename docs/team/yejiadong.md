---
layout: page
title: Jiadong Ye's Project Portfolio Page
---

## Project: CanoE-Coach

CanoE-COACH is a desktop app for managing training schedules for secondary school canoe teams, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical Interface (GUI). If you can type fast, CanoE-COACH can get your training scheduling done faster than traditional GUI apps.

Given below are my contributions to the project.

* **Refactoring AB3 Code**: Refactoring the person class to the student class
  * What it does: Ensures that individuals stored in our contact list are "students" rather than "persons"
  * Justification: This feature aligns the app with our objective of building a canoe training app designed for usage with secondary school students.
  * Highlights: Intensive and comprehensive replacements were required to remove all viable instances of "person"

* **New Entity**: Implemented the dismissal time fields in the student class
  * What it does: Allows every student to store his/her dismissal times for the five weekdays
  * Justification: The core feature of CanoE-Coach requires the scheduling of trainings, which had to be done without coming in conflict with the various dismissal times of students. Hence, each student field needed to contain dismissal time references.
  * Highlights: This feature requires careful integration with the existing student class and commands. Since there were five weekdays, multiple approaches were considered and trialed before proceeding with the current implementation. This approach allowed for the most seamless integration with existing design patterns and commands. The implementation was challenging as it required changes to existing commands.

* **New Feature**: Added the find-training command to allow for searches of students and trainings by id and datetime
  * What it does: Enables users to be able to search for trainings by their datetimes and display all of the corresponding scheduled students, and also the converse, which is to search for specific students and display his/her corresponding trainings
  * Justification: This is a crucial addition to allow users to efficiently perform operations on specific Trainings or students. Before this, users had to scroll through the training panel to identify the corresponding training index for their commands. With a find command that works based on datetime, as well as student id, this would allow users to more conveniently identify training and student indexes, as well as provide them a bird-eye view of all of a student's trainings or all of the students scheduled for a training.

  * Highlights: This feature requires a strong understanding in the filtering of the student and training lists, as well as bug-free cross-interaction with other implemented commands. As optional parameters were allowed (i.e. user can either specify student index or training datetime or both), the parser has to recognise all three variations and process them differently.

* **New Feature**: Implemented the initial cross-interaction between the training and student panel GUIs
  * What it does: Enables changes made on one panel to be propagated to the other at the same time.
  * Justification: In order for information displayed to be streamlined and tally on both the training and student panels, GUI cross-interaction was critical as details of students were displayed on both panels. Hence, this would permit CRUD functions on the student panels, while ensuring changes reflect on the other, eliminating user confusion and potential bugs further down the pipeline.

  * Highlights: This feature required detailed understanding in the minute details of the implementations of all of the pre-existing commands. A minor difference in the student objects in both training and student model lists would lead to catastrophic bugs later in the pipeline. Hence, modification of almost all of the existing commands were necessary to support cross-interaction (i.e. edit, delete student commands). The feature was also heavily user-tested for bugs.

* **GUI**: Modified the initial GUI layout
  * What it does: Supports display of additional critical student and training fields in a structured and readable manner. Colour was also added to indicate differences in attendance status.
  * Justification: Additional fields were added that needed to be displayed in an organised manner (i.e dismissal times, training schedules) and colour was added to attendance statuses for better readability.

  * Highlights: This feature required detailed understanding in javafx's bind function, to allow for dynamic changes to label colours stemming from internal property states.

* **Project management**:
	 *   Managed all releases  `v1.1`  -  `v1.3`  (4 releases) on GitHub
	 *  Milestone management (creation and closing of milestones)
	 * Managed 41 issues ranging from bugs to user stories and practical exam dry run. [Link to issues](https://github.com/AY2021S1-CS2103-F10-1/tp/issues?q=is%3Aissue+author%3Ayejiadong+is%3Aclosed).

* **Enhancements to existing features**:
	-   Updated the GUI color scheme (Pull requests  [#144](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/144))
	-   Wrote additional tests for existing features to increase coverage (Pull requests  [#75](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/75))

* **Code Quality Improvement**: Shorten lengthy code to be more concise and clearer, Abstracted blocks of reused codes.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=yejiadong&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `find-training` and fixed documentation bugs in other commands: [#80](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/80), [#122](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/122), [#154](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/154), [#157](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/157)
    * Update UI image: [#96](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/96)
  * Developer Guide:
    * Added implementation details of the `find-training` feature: [#122](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/122)

* **Community**:
  * PRs reviewed: [Link to list of PRs](https://github.com/AY2021S1-CS2103-F10-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me)
  * Reported bugs and suggestions for other teams in the class: [Link to issues](https://github.com/yejiadong/ped/issues)
