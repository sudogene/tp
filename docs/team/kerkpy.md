---
layout: page
title: Kerk Pei Yong's Project Portfolio Page
---

## Project: CanoE-Coach

CanoE-COACH is a desktop app for managing training schedules for secondary school canoe teams, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical Interface (GUI).
If you can type fast, CanoE-COACH can get your training scheduling done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Aided the implementation of dismissal time fields for the Student class.
Responsible for the test cases and improvements in Code Quality.
  * What it does: Tracks the dismissal time of the Student for different days.
  * Justification: There is a need for the user (the coach) to know the dismissal times of the students of the different days.
  * Highlights: There is a need for an understanding of the existing code base to ensure no regression.

* **New Feature**: Modified the GUI for the Training, and to match the theme of the project.
  * What it does: Created a separate panel for the display of Training, and storage of training.
  * Justification: Essential for the display of trainings in GUI
  * Highlights: Created this in parallel with the implementation of Training and its associated methods with Wei Heng (Whleee).
  There is a need to communicate how the GUI works and how the data of training would be displayed.

* **New Feature**: Added the Attendance class and associated functions.
  * What it does: Creates an Attendance class that records the training session a student is attending.
  * Justification: This class is necessary to prevent any forms of cyclic dependency between Training and Student.
  * Highlights: In implementing this class, there were several design considerations. The ease of implementation in the relative short time, need to prevent cyclic dependency and the extendability of the Attendance class was being considered.

* **Bug Fixes**: Fixed some bugs regarding Training, Mark/Unmark attendance. Below are the bugs fixed:
  * When creating a Training, it was possible to create a Training for a date that has already passed. ([#81](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/81))
  * When adding or deleting a Student from a Training, the parser was parsing by index rather than prefixes. ([#101](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/101))
  * It was previously possible to mark the Attendance for a training that has not passed. ([#214](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/214))

* **Code Quality Improvement**:
  * Refactor `Attend` to `Attendance`to replace a verb to a noun for class name. [#143](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/143)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=kerkpy&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed 12 issues ranging from user stories for implementation to bug fixes and code quality. [Link to issues](https://github.com/AY2021S1-CS2103-F10-1/tp/issues?q=is%3Aissue+is%3Aclosed+assignee%3Asudogene).

* **Documentation**:
  * User Guide:
    * Added documentation for `unmark-command`.
    * Glossary for the app.
    * Updated the existing commands in the User Guide for the app as of v1.2b. [#82](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/82)
  * Developer Guide:
    * Added implementation details of the `Attendance` class: [#126](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/126)
    * Added Use Cases, User Stories as of v1.2b: [#97](https://github.com/AY2021S1-CS2103-F10-1/tp/pull/97)

* **Community**:
  * PRs reviewed: [Link to list of PRs](https://github.com/AY2021S1-CS2103-F10-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Akerkpy+)
  * Reported bugs and suggestions for other teams in the class: [Link to issues](https://github.com/kerkpy/ped/issues)
