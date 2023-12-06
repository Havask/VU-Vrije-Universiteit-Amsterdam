![alt](https://assets.vu.nl/d8b6f1f5-816c-005b-1dc1-e363dd7ce9a5/03b18476-b98e-43c2-8b3c-1d7d50026c89/VU_logo_RGB-01.jpg)

# Software-Engineering-Processes

**Academic year: 2022/2023**  
> Bachelor program in Computer Science  

**_List of contributors:_**  
Eros Masarin [ersmax], Tommaso Ravedoni [travedoni],  
Oscar Chen [Ouscqr], Havard Skjærstein [havask]  

## Introduction  
  
This work is organised in two different sections according to the repository.  
The goal of this work is to explore 2 repositories that were given by our instructors and  
study them by answering the questions below. 

## Repository analysis 
In this section we are going to present the analysis for the two repositories: Jedis and Javacord.

### Repository 1: Jedis
> repository available at: https://github.com/redis/jedis  

### Q1) Describe, with your own words, what the project is about. Also, include in such a description the history of the project in terms of age, number of commits in the main branch, and number of collaborators.
The Jedis is a repository that has the following features:
* It is open-source;
* It is a remote dictionary – a sort of in-memory data structure server;
* Performance and user-friendly are the most important features;
* It is a Java client for Redis, which means that it eases the communication from a java
application to the database.

Regarding the history and the main characteristics of the repository, it can be seen that just under 200 developers have committed more than 2,000 commits in over 12 years. The latest version (4.4.3) counts over 84 releases. There is also a clear participation in the community, with roughly 50 issues that have been raised and 1,600 closed issues.

### Q2) What are the practices in terms of commit messages (consider only commits on the main branch)?
The commits of the Jedis repository can be described as complete and neat. For one, there is a short and clear summary that highlights the repository changes over time. For another, there are no redundant or equivocal information.

The following practices are applied:
* The commit starts with a verb that describes the action, namely “FIXED bug #id123”;
* Every single contribution is being associated with a unique commit;
* There could be references to specific issues or pull requests. These are included in
brackets and allow to link a discussion to a specific resolution or implementation. “Shutdown ExecutorService in multi node pipelines 3467” is an example of such practice;
* There is a clear reference to the part of the project that the commit affects. “Bump maven-surefire-plugin from 3.1.0 to 3.1.2” is an example of this practice. The important contribution of this measure is that it allows to understand how different components evolve in time and the how they affect the overall system build.
  
In short, these good practices enhance clarity in the description of the developer’s contribution and improve readiness to external contributors and readers.

### Q3) How are the issues organized?
Of course, it goes without saying that developers make use of GitHub issues to handle problems that need to be resolved. It can be seen that more than 120 issues are open, while just above 1,500 issues were closed. There are both benefits and drawbacks that we highlight below.

There is a clear description for each issue and occasionally the use of labels to choose the right category/topic. Nonetheless, there are some concerns we would like to address. Firstly, the structure could be improved since there is a poor consistency. Secondly, labels should be used more frequently since their use is sporadic in the whole project development.

We noticed a good pattern in the description of each issue. In particular, there is a section related to the “Expected behaviour” and another section related to the “Actual behaviour”. This methodology certainly helps contributors in the process of identifying and solving issues quickly. Some improvements, however, could be made. Sometimes general comments or remarks related to the whole project describe an issue, which therefore misses a detailed problem statement.

Finally, there seems to be issues that ask for attention and are related to further improvements and enhancements of new features in the Jedis project. In other words, these issues are related to the future project development. “Mark jedis test cases based on compatible Redis versions”, “Add support for triggered functions”, “Add support for JSON.MSET” are examples in this matter.

### Q4) Are there instructions on how to contribute to the project? If yes, explain them.
The README file contains all the necessary instructions for the contributors to add their work through a pull request to the Jedis repository. It is also possible to create new issues to address bugs.

It is interesting to notice the recommended procedure that the repository suggests to potential and current contributors. Firstly, one need to fork the repository on GitHub in order to create a copy of Jedis folders and file on the personal space of the developer. Secondly, a new branch must be created in the new forked repository so as to start working without creating conflicts with other contributors. Creating a branch is easy with the git command git checkout -b new_branch. Thirdly, the developer adds the changed files to the local staging area with the command git add and proceeds with a commit to their own local branch, through git commit. If the developer is happy with the change, they can push the changes to the master through the command git push origin new_branch. Finally, the developer creates a pull request in the Jedis repository on GitHub. They should take extra care and add extra details, namely what changes have been made, their reason and contribution to the project.

In order to validate new contributions, a test environment is provided on top of guidelines and conventions that help maintaining clarity and readable code. There are also particular warnings to contributors. On the one hand, they ought to take care that their changes include and pass some unit tests. The reason is that such test helps validating the new added functionality. On the other hand, a “project maintainer” provides useful and insightful feedback or requests of changes with a proper review on new pull requests. These two good practices prevent code degradation.

### Q5) What automated checks do exist on a commit pushed to the main branch?  
The automated checks in the Jedis repository are organised as a series of unit tests that run and automatically verify the submitted code before the commit. The purpose of these tests is to check if the submitted changes do not affect negatively the overall functionality of the project code and therefore, they assert the correctness of the changes. Either individual components or code units are being tested in order to check their intended functionality.

There are also automated tests for commits that are being pushed to the main branch. In fact, we have identified some event triggers. For one, a workflow that is enabled through a push to the master. For another, pull requests that being directed to the master. Moreover, we noticed a weekly schedule through a specific cron instruction (31 4 * * 4, which stands for every Thursday at 4:31).

Finally, the repository also makes use of “jobs”. Indeed, the workflow uses an analyse job that basically runs in every recent release of Ubuntu. In details, the workflow code in the repository is being checked in a preliminary “repository checkout". The second step is the “CodeQL initialization”, where CodeQL tools are being prepared for the scan. As a side note, he language to be examined here is Java. Moving on to the third step (“the auto build step”), there is an attempt to automatically build any compiled languages in the repository, namely C, C++, Java. There is also a “back-up plan”. In other words, if the auto build step fails, then contributors can use parts of the code shared in the repository to manually build the code themselves. As a last step, there is the CodeQL analysis: it is possible to perform a CodeQL analysis on the codebase through the initialised tools.  

### Q6) In the context of pull requests, what automated checks are done (consider checks on commits and comments posted in the pull requests by automated tools and bots)?
In order to ensure the quality of the code and also keep the codebase integrity, there are different automated checks in the context of pull requests. These are mainly performed through tools and bots. In details, we have identified seven different automated checks.

Firstly, we have to consider the code quality and style. In this regard, automated tools assess the changes made to the code through a pull request in order to verify the coherence to the coding style conventions and the good practices, taking into account also the complexity of the code. Secondly, there are units tests and test coverage. The goals of automated checks in this case are double checking that the proposed changes are supported by relevant unit tests and ensuring that these tests have an overall coverage and do not result in an adverse impact on the whole. Thirdly, automated tools can successfully perform integration tests so as to avoid compatibility issues or unnecessary regressions as a consequence of detrimental proposed changes. A fourth check we identified is the use of a static code analysis. Such activity is done so as to seek bugs, security risks or performance issues in the changes proposed with a new pull request. To tackle these issues, tools have been adopted to perform a static code analysis.

The prior four automated checks play a significant role in identifying and preventing issues, yet there are other measures in place to ensure a neat and smooth integration as well as to ease a reliable deployment of proposed changes following a pull request. For instance, there is a documentation check, which is done automatically by tools. These ensure that new documents are aligned with specific standards and adhere to the specific formatting. There are also security scans in place – automated security tools that prevent new changes to break the build. These tools perform a security check on the new code submitted so as to detect those risks that pose a threat to the security. They also detect vulnerabilities and unsafe practices on the code submitted. Finally, there is a sensible use of automated workflows for review and approval for some projects. This is done in order to check that the new pull request follows the requirements before it is merged in the master branch. This process is automated through a specific workflow, which enforces a review and approval process.  

### Q7) How are the release notes organized?
There is a specific tool that is used to organise and create release notes that goes under the name of “Release drafter”. In the Jedis repository there is a specific workflow file that explains the configuration for the generation process of release notes. In details, there is an event “trigger” that is used to push to the master and to set the workflow. In addition, there is a job in the workflow – “update release draft” – and the Release drafter tool is being used for this single step job.

In order to start the Release Drafter tool, the release-drafter action is used. With the indication of the configuration files, the tool enables the customisation of the release note generation process. Additionally, there is a configuration file that is optional (the specific file is called release-drafter-config) and it is described in the workflow.yml, located in the .github/ directory. Documents that are shared do no specify the content of such configuration file.  

### Q8) What is the license of the project? Explain if it’s permissive or restrictive.
The license for the Redis is the MIT license, an open-source license that allows users to freely use, change, distribute, sub-license the software upon observing some conditions. Users are let free to change the code under the MIT license, to integrate the code with their personal projects and they can also proceed with a commercial distribution. In this sense, the license is permissive. Nonetheless there is a disclaimer of liability, which states that the software is provided “as is” with no warranty. Finally, it is said that all copies of the software must follow the license mandate and contain the original copyright and disclaimer.  

### Repository 2: Javacord
> repository available at: https://github.com/Javacord/Javacord  

### Q1) Describe, with your own words, what the project is about. Also, include in such a description the history of the project in terms of age, number of commits in the main branch, and number of collaborators.
The Javacord repository is related to an open-source project that has the goal of providing Java library in order to create bots for Discord and applications with a simplified API. In addition, the project aims to give Java developers necessary tools and interfaces to interact with the Discord API so as to develop bots and applications. The project has received over the years regular updates. The current release is the v.3.8.0. Back to 2015, there was a rewrite of the project.

In the master branch it can be seen just under 2,500 commits and the repository counts almost 85 releases. These figures suggest that the project is actively maintained with regular updates and has an active community of contributors, with 56 members actively participating in the development.  

### Q2) What are the practices in terms of commit messages (consider only commits on the main branch)?
Short, descriptive and concise are the adjectives that best describe the commit messages of the Javacord repository. Indeed, an action verb is put in front of each commit message in order to convey the reason and the scope of new changes. Arguably, the approach enhances the clarity of the reason behind the commit.

A good practice used in the Javacord repository is the association between the commit with each contribution. This allows for a better organised history, which allows an external user to understand the steps that were followed. Another upside of associating a task to each commit is a better tracking of versions and releases in the history. This makes it easier than ever to keep track of changes after a period of inactivity of a contributor. Furthermore, there is a consistency in the format of commit messages in the repository that have been followed in the course of the years.

There is undoubtedly a clear commitment of contributors to ensure readability in the commit history. Indeed, clear guidelines for new commits can be found in the Javacord repository. We list some of them below to give the reader a general outlook of them:
* Separate subject from the body with a blank line;
* No more than 50 characters per subject line;
* Subject lines should start with capital letters;
* Subject lines should not end with a period;
* Use of the imperative in subject lines;
* Bodies should be contained to 72 characters;
* Use of the body to explain what, why, how.
  
Other good practices are followed. Generally speaking, each commit message is clear to the reader. We comment some of them below.

For instance, a functionality for voice message support was added in the past. The functionality can be traced with a commit message that describes it (“Voice message support has been added”). Another feature that is being implemented is related to the addition of server scheduled events. The related commit message suggest this with a neat message (“Add server scheduled events”). There is also a commit message that remarks a change in the codebase for a better clarity. In other words, it suggests changing the id name from “channel_id” to “channel” for interaction needs.

Yet, another example is the commit message related to the change of the “EventDispatcherBase” class (“Check that the remaining tasks are present in EventDispatcherBase”). Here the purpose is clear. Other example of commit message that start with an active verb, use the imperative and follow all the other good practice are: “Add missing UserFlag enum values”, “Add missing StickerFormatType enum values”, “Rename existing values in the PermissionType enumeration”.  

### Q3) How are the issues organized?
Contributors make use of GitHub issues to add, fix or improve the project in the repository, which counts roughly 70 open issues and 300 close issues. Issues are described with a short sentence that sum up a problem or resolution. Sometimes labels are used to describe requests or a fix with short description names. “Bug fix” and “feature request” are example in this matter.

There is also a practical section in the description related to open suggestions. The section “What feature are you proposing” contains many different issues or features that have been added over time in the repository. By and large, the issues description shows to readers and contributors a clear overview and an understanding of the scope and purpose of the proposed changes.

We identified some issues that may be related to how usernames are handled: “Replacement/simple removal and deprecation of getDiscriminatedName”. Other issues handle additional reaction functionalities: “add super reaction” is an example of this. Interestingly, there are some issues that reports errors or problems in handling packets of type “VOICE_STATE_UPDATE”, which are mainly related to enhancing the voice functionality. Related to errors, there are also some issues that deal with audio connection problems, when a command is given through a text channel. Yet, there are some issues that suggest the insertion of flags or attributes related to server members: “Add server member flags”.  

### Q4) Are there instructions on how to contribute to the project? If yes, explain them.
One can contribute to the project in a number of different ways: it is possible to create issues, submit new pull requests or enhance the Javacord Wiki. There is a list of “good issues”, which is recommended for newcomers and current contributors, but one could also contribute with new pull requests. The user is let free to choose the improvement they would like to work on, and they can always give their contribution by creating a pull request to tackle some of the issues listed in the “issues page”.

Despite the freedom that contributors have, there is a clear recommendation. It is said that before submitting pull requests, it is of the utmost importance to test the changes. Else, the pull request ought to be marked as a draft if it has not been tested yet. However, testing the changes is easy by using Grandle to create a composite build, or by adding test file to the repository.

By and large, the Javacord project follows the Google Java Style Guide. We discuss in this paragraph some of the good practices followed. For example, the coding conventions seem to be followed (use of braces, block indentation, column limits) and the same goes for the type selection (Set, List, Collection). It is possible to use collections to create a copy instead of returning collections. While the use of unusual acronyms is avoided, camel case seems to be the preferred choice of style. Moreover, single-letter variable names are avoided in favour of a descriptive naming, while catch blocks that ignore exceptions on purpose use the suggestive name “ignored”. Finally, there is the good practice of naming methods with meaningful and self-explanatory names instead of relying on documentation, which is always good so as to reduce overheads in an agile-fashion way (code over formal documentation).

### Q5) What automated checks do exist on a commit pushed to the main branch?
There are a number of different automated checks on a commit pushed to the main branch. For one, there is “build and test”, which is a check on the codebase overall integrity that ensures that the code is compiled properly. For another, there is a check on the “coding style”. In details, the checkstyle_job runs to make sure the adherence of the code to the styling code conventions. This job performs “checkstyle” tasks on the Javacord core and API modules. Additionally, there is the “integration with Reviewdog”, which is being used to assess the checkstyle report and gives useful feedbacks that help identifying styling violations. 

Through these automated checks and feedbacks, rule violations can be corrected and consistency of code can be kept before contributors proceed with a merge to the master branch. Perhaps this is one of the reasons why the there is a high consistency in the format of commit messages, a topic we have addressed in question Q2).
  
### Q6) In the context of pull requests, what automated checks are done (consider checks on commits and comments posted in the pull requests by automated tools and bots)?
To ensure the code quality and to ensure the integrity of the codebase, there are different automated checks. These are crucial because they preserve the code quality, facilitate the integration with the rest of the code and ensure a continuous integration.

One of these is the “Continuous Integration build and tests”, which runs the build procedure and performs tests on the changes related to the pull request. The main goal is ensuring that tests run with no errors and changes can therefore get the approval. Another automated check is the “Code Style check” – an automated tools similar to “checkstyle”, which we have already discussed by addressing question Q5). Basically, code style check assesses the changes made to the code in the pull request and compares them to the coding style standard. If the changes do not comply, proper recommendation are made on naming, indentation, and other style features.

Next, we have “Static Code Analysis” – an automated check that perform a code analysis in order to seek security issues, bugs and code smells. Tools are provided to enhance the quality of the code as well as analysing the changes. “Test coverage analysis” is another tool that can be used to check the changes to the code and measuring the lines of code being tested.

There are also automated tools that examine documentation and comments. One of these is “Comment Linters” – a tool that allows to analyse comments added to pull requests and discover issues and violations. Omitted issue reference and improper formatting are examples of problems that the automated tool can spot. Other automated tools that work on the documentation ensure that changes made through pull requests follow the project documentation standards and the recommended format.

Finally, there are some templates that help contributors in adding further details in their pull request, namely a reference to documentation, the nature of the issues, etc. What is important to notice here is the existence of automated checks that perform an assessment of the template description accuracy and the compliance to the repository guidelines.
  
### Q7) How are the release notes organized?
Release notes are organised through the “Release and update” automated workflow that works as follows. Firstly, the project current version is read from Grandle and property files trigger the workflow. Then a changelog for the release is generated by the workflow by executing the generateChangelog task with ./grandle. Relevant updates and changes are being extract from the codebase by executing the task and then are consequently formatted into the changelog. Finally, a new release is created by using the actions/create-release action if the version does not end in “snapshot”. If that is the case, the created release is identified as non-snapshot release. The generated changelog is being included in the release note together with the associated version number that identifies it. This allows for a better tracking of the release changes and enhance their clarity to external readers.

On this basis, we can conclude that release notes are generated dynamically through an automated workflow. We notice that release notes in the repository have important information related to bug fixes, new features and the likes that can be added through the create-release action and the changelog generation task.
  
### Q8) What is the license of the project? Explain if it’s permissive or restrictive.
The Javacord license is a permissive and opensource license, in particular it is the Apache license v2.0. As we have already discussed by answering question Q4), users are given freedom to choose the improvement they would like to work. Under the Apache license, they are let free to use, change, distribute and also sublicense the software. Although there are few restrictions on the use and distribution of the software, users are allowed to use the software for personal and also commercial used. In the second case, however, they should clearly state that the software is provided “as is” with no warranty. Finally, it is important to notice how the Apache licence is particularly convenient for small developers that work as freelancer since the license offers defence against a patent infringement claims.

## Usage analysis 

Plese find below some of the good practices that were followed by the members.  
1. Each commit is an atomic change, meaning that it addresses an issue or makes a change;  
1. Each commit message is clear, follows a pattern, and is traceable  (the group tried to follow the pattern: commit message that starts with an active verb, uses the imperative, closes an open issue);  
1. Issues should be created to split the work among the group’s members, and assignments should be done;  
1. Every issue should be closed once it’s addressed through a pull request;  
1. Every commit on the main branch should only be done through the merge of a pull request;  
1. Every pull request should be merged by a different group member than who submitted it.  

## Individual contributions

Please find below the contacts of the group members.  
Please note that the username of github of each team members are put in [square brackets]  
next to the full name in the section member names below.   

**_Group: 75_**  
Member names:  
Eros Masarin [ersmax], Tommaso Ravedoni [travedoni],  
Oscar Chen [Ouscqr], Havard Skjærstein [havask]  
  
Emails: e.masarin@student.vu.nl, t.ravedoni@student.vu.nl,  
o.b.x.chen@student.vu.nl, h.skjaerstein@student.vu.nl  
  
VUnetIDs: emn256, tra214, och208, hsk202  
Bachelor program in Computer Science  

All team members analysed the repository together before the start of the project.  
Two team members answered and reviewed one another the questions for one repository.
The other two members answered and reviewed the questions for the other repository. 
In addition, each team member worked in their own branch.  
When a question (issue) was addressed, a team member reviewed the work that has been done  
and proceeeded with merging the branch with the master.  
Isssues were opened at the start of the project so as to organise and spilt the work  
amongst team members equally.  


In details, the two repositories were analysed by all team members.
Then, the questions were edited (E), reviewed and proofread (PR) in the following manner:  
(Please refer to the section team members for the full names of each user indicated below)  
  
1. Question 1
    1. repository 1:     (E): [havask].      (PR): [travedoni].
    1. repository 2:     (E): [ersmax].      (PR): [Ouscqr].

1. Question 2
    1. repository 1:     (E):  [havask].     (PR): [travedoni].
    1. repository 2:   (E):  [ersmax].       (PR): [Ouscqr].  

1. Question 3
    1. repository 1:  (E):  [havask].     (PR): [travedoni].  
    2. repository 2:  (E):  [ersmax].     (PR): [Ouscqr].  

1. Question 4
    1. repository 1:  (E):  [havask].     (PR): [travedoni].  
    2. repository 2:  (E):  [ersmax].     (PR): [Ouscqr].  

1. Question 5
    1. repository 1:    (E):  [travedoni].  (PR): [havask].  
    2. repository 2:    (E):  [Ouscqr].     (PR): [ersmax].

1. Question 6
    1. repository 1:    (E):  [travedoni].  (PR): [havask].  
    2. repository 2:    (E):  [Ouscqr].     (PR): [ersmax].

1. Question 7
    1. repository 1:    (E):  [travedoni].  (PR): [havask].  
    2. repository 2:    (E):  [Ouscqr].     (PR): [ersmax].

1. Question 8
    1. repository 1:    (E):  [travedoni].  (PR): [havask].  
    2. repository 2:    (E):  [Ouscqr].     (PR): [ersmax].


