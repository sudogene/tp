---
layout: page
title: Abdurrahman Faqih's Project Portfolio Page
---

## Project: CanoE-Coach

CanoE-COACH is a desktop app for managing training schedules for secondary school canoe teams, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical Interface (GUI).
If you can type fast, CanoE-COACH can get your training scheduling done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the command to find a common time among all specified Students.
  * What it does: Allows user to know the earliest common available time for a group of specified Students.
  * Justification: This feature increases convenience for the user to know a day and time to create a Training Session with that all the specified Students can attend.
  * Highlights: This feature is designed to integrate and chain well with the filter command and requires
  a deep understanding in the Training commands.

* **New Feature**: Added the command to mark a Student's attendance in a Training Session.
  * What it does: Allows user to mark a Student as attended a Training Session.
  * Justification: This is a core feature of the CanoE-Coach application as coaches need to be able to track a student's attendance for any Training Session.
  * Highlights: This command affects the status of a student as a "bad student" (more than 3 unattended Trainings).

* **New Feature**: Added the command to find all Students with a bad attendance record.
  * What it does: Allows user to find all Students who have missed more than 3 Training Sessions.
  * Justification: This feature allows the user, as a coach, to be aware of any Student that have been consistently missing Training Sessions.
  * Highlights: This command affects future features which will allow the user to send out a warning to these Students regarding their bad attendance record.

* **Bug Fixes**: Fixed bugs regarding editing and deleting of Student and matching of Students in common time command. Below are the bugs fixed:
  * Editing a Student's fields does not change the Student's details in the training panel. ([#98](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/98))
  * Changing one dismissal time to conflict with one training session, will make the other training sessions disappear from the GUI. ([#98](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/98))
  * Changing one dismissal time to affect Student's availability for scheduled Training will remove Student from the Training Session. ([#91](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/91))
  * Deleting a Student does not delete the Student from Training. ([#91](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/91))
  * Common Time Command was not matching all specified Students. Hence, a new PredicateList was created to match all predicates passed to it. ([#84](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/84))

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=abdurrahmanfaqihiskandar&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=abdurrahmanfaqihiskandar&tabRepo=AY2021S1-CS2103-F10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed 16 issues ranging from adding new features to bug fixes and updating documentation. [Link to issues](https://github.com/AY2021S1-CS2103-F10-1/tp/issues?q=is%3Aissue+is%3Aclosed+assignee%3Aabdurrahmanfaqihiskandar).

* **Enhancements to existing features**:
  * Refactored the code base and changed every instance of AddressBook in code and comments to CanoE-COACH. ([#95](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/95))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `common-time`, `mark-attendance`, and `find-bad-students`: [#124](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/124) [#159](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/159)
  * Developer Guide:
    * Added implementation details of the `mark-attendance` feature: [#124](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/124)

* **Community**:
  * PRs reviewed: [Link to list of PRs](https://github.com/AY2021S1-CS2103-F10-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Aabdurrahmanfaqihiskandar)
  * Reported bugs and suggestions for other teams in the class: [Link to issues](https://github.com/abdurrahmanfaqihiskandar/ped/issues)
