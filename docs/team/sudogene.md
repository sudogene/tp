---
layout: page
title: Andy Wu's Project Portfolio Page
---

## Project: CanoE-Coach

CanoE-COACH is a desktop app for managing training schedules for secondary school canoe teams, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical Interface (GUI).
If you can type fast, CanoE-COACH can get your training scheduling done faster than traditional GUI apps.

Given below are my contributions to the project.

* **Enhancement To AB3 Feature**: Enhanced the Find command to filter for more parameters.
  * What it does: Allows user to find Students using all fields of the Student class.
  * Justification: The CanoE-Coach user expects to handle a rather large list of around 90 students, and the legacy AB3 Find feature which searches by name is insufficient.
  With this enhancement, the user is able to filter the student list by "batches" using fields like Dismissal Time and Academic Year. This filter feature
  is also designed to work well with downstream commands that pull from the filtered student list.
  * Highlights: This enhancement combines the use of `Predicate` in the legacy Find command and the use of `ArgumentMultimap` and `Prefix` in the legacy Edit command. Hence, it
  requires a strong understanding in the inner-workings of the parsing of prefixes as well as the way AB3 filters the student list using predicates in the `Model`.

* **New Feature**: Added the command to add all Students to a Training Session.
  * What it does: Allows user to add all available and valid Students to a Training Session.
  * Justification: This feature increase convenience for the user to mass add Students to the Training.
  * Highlights: This feature is designed to integrate and chain well with the filter command and requires
  a deep understanding in the Training commands.

* **New Feature**: Added the command to delete Students from a Training Session.
  * What it does: Allows user to delete Students from a Training Session.
  * Justification: This is a core feature of the CanoE-Coach application to remove Students from Trainings if needed.
  * Highlights: This feature requires careful management and handling of multiple entities including Student and Training. Hence,
  an excellent understanding of the system design is required to ensure a bug-free implementation.

* **New Entity**: Added the student Id field.
  * What it does: Enables Students to be uniquely identified instead of their indices in the GUI display.
  * Justification: This is a crucial addition to allow users to easily find, add, remove students from Trainings using their Id values. Before this,
  users had to look at what index a particular Student is tagged to, and this is constantly changing when users use filtering
  commands.
  * Highlights: This feature requires a strong understanding in the parsing and creation of Student objects to implicitly integrate an immutable and unique
  attribute to a rather mutable Student class.

* **Bug Fixes**: Fixed some bugs regarding deleting of Training, Id creation, find command. Below are the bugs fixed:
  * Students' training schedules are not removed when the Training was deleted. ([#90](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/90))
  * Multiple copies of the same Student exist in the Model, and cannot be matched due to different training schedules. ([#93](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/93))
  * Id value increases when new Student fails to be added to the Student list. ([#103](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/103))
  * Id accepting last used value even for values that are used before, causing a "re-roll" effect ([#206](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/206))
  * Find command accepting invalid values for fields Phone, Academic Year, Id ([#210](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/210))
  * Standardization of error messages involving Id inputs ([#223](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/223), [#224](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/224))

* **Code Quality Improvement**: Shorten lengthy code to be more concise and clearer, Abstracted blocks of reused codes.
  * Implemented the `Student::cloneStudent` and `Training::cloneTraining` to allow Command classes to create the respective objects to replace old objects in the Model.
  * Replaced long loops with concise and easy to understand streams.
  * Abstracted re-implemented and re-used methods from multiple Command classes into a new `CommandUtil` class.
  * Abstracted common parsing methods from multiple Parser classes into `ParserUtil` methods.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=sudogene&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=sudogene&tabRepo=AY2021S1-CS2103-F10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Project management**:
  * Managed 18 issues ranging from user stories for implementation to bug fixes and code quality. [Link to issues](https://github.com/AY2021S1-CS2103-F10-1/tp/issues?q=is%3Aissue+is%3Aclosed+assignee%3Asudogene).

* **Enhancements to existing features**:
  * Enhanced the Find command to find Students by Name, Phone, Email, Academic Year, Dismissal Time, Id. ([#26](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/26), [#32](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/32), [#38](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/38), [#52](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/52), [#68](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/68))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `find`, Student Id, and `ts-addall`: [#80](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/80), [#120](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/120), [#153](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/153), [#210](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/210)
  * Developer Guide:
    * Added implementation details of the `ts-addall` feature: [#127](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/127)

* **Community**:
  * PRs reviewed: [Link to list of PRs](https://github.com/AY2021S1-CS2103-F10-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Asudogene)
  * Reported bugs and suggestions for other teams in the class: [Link to issues](https://github.com/sudogene/ped/issues)
