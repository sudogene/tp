---
layout: page
title: Wei Heng's Project Portfolio Page
---

## Project: CanoE-Coach

CanoE-COACH is a desktop app for managing training schedules for secondary school canoe teams, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical Interface (GUI). If you can type fast, CanoE-COACH can get your training scheduling done faster than traditional GUI apps.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=whleee&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=Whleee&tabRepo=AY2021S1-CS2103-F10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **New Feature**: Created the Training Class.
  * What it does: Allows Students to interact with this Training class so that various scheduling features such as adding or deleting students, or marking of student attendances can be implemented in the future.
  * Justification: This is a key class in the CanoE-Coach application as many future scheduling features depend on this class to be well implemented and extensible without bugs.
  * Highlights: This class is the cornerstone for most of the other features of the Canoe-Coach application, hence much consideration and excellent understanding of the entire application is required to implement the class well for future use.
  * Credits: The other team members all gave input for the implementation of this class, and assisted with bugfixes when bugs were found.

* **New Feature**: Added the command to add and delete Training Sessions.
  * What it does: Allows the user to add and delete Training Sessions at a user specified date and time.
  * Justification: This is a core feature in the CanoE-Coach application that allows users to schedule training sessions.
  * Highlights: This additional feature adds on to existing commands and is made extensible to commands in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: The other team members gave valuable input on how this feature should be implemented, and their input were taken into consideration when implementing this feature.

* **New Feature**: Added the command to add Student to Training Sessions.
  * What it does: Allows the user to add existing Students to the user specified Training Session.
  * Justification: This is another core feature in the CanoE-Coach application that allows users to add Students from their Student List into the Training Session of their choice, so users can keep track of who and how many students are in each training session.
  * Highlights: This additional feature adds on to existing commands, and the sensitive nature of this command means that careful consideration and excellent understanding of the system is required to ensure that this feature is implemented bug-free.
  * Credits: The other team members helped to vet pull requests and point out areas that are bug prone, allowing the feature to be implemented more carefully and bug-free.
  
* **Testing**: Added Unit Tests for testing of classes concerning the addition/deletion of Training Sessions as well as the addition of Students to Training Sessions.
  * Wrote tests for `training` and `delete-training` features [\#71](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/71).
  * Wrote tests for `ts-add` and `ts-delete` features which increased coverage from 65% to 70% [\#83](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/83).
  
* **Bug Fixes**: Fixed some of the bugs regarding the creation and deletion of Trainings, as well as the interaction between Students and Trainings.
  * Fixed the bug where Training Sessions are not stored when the application exits, and improved the specificity of error messages: [\#73](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/73).
  * Fixed the bug where the command to add Students to Training Session or delete Students from Training Session is still partially executed despite some student Ids being invalid. [\#92](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/92).
  * Fixed the bug where invalid user input DateTimes were being rounded off to the nearest valid DateTime [\#209](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/209). (Code combined with Pull Request of another team member)

* **Project Management**:
  * Managed issues 14 issues which includes feature implementation, documentation, and bug fixes: [Link to Issues](https://github.com/AY2021S1-CS2103-F10-1/tp/issues?q=is%3Aissue+assignee%3AWhleee+is%3Aclosed).
  
* **Documentation**:
  * User Guide:
    * Added documentation for the feature `ts-add`: [\#124](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/124).
  * Developer Guide:
    * Added implementation details of the `ts-add` feature: [\#129](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/129).

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#23](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/23), [\#73](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/73), [\#123](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/123)
  * Reported bugs and gave non-trivial suggestions to other teams in the class: [Link to Issues](https://github.com/Whleee/ped/issues).
 
