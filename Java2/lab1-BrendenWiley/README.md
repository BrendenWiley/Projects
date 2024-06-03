# Lab 1: Test-Driven Review

Welcome to CS 2334!
For our first lab, we will complete the following tasks:

1. Install JDK and Eclipse.
2. Download the starter code from this GitHub repo.
3. Import the code into Eclipse.
4. Write methods that pass a set of provided unit tests.
5. Commit the code to GitHub.
6. Upload the source file to zyLabs.

## Install Oracle JDK and Eclipse

To compile and run Java programs, you need to install the Java Development Kit (JDK).
To write Java programs, you can use anything from a simple text editor to an integrated development environment (IDE), such as Eclipse, IntelliJ, or NetBeans.
We don't require that you use a particular editor or IDE, but many of the assignments will include instructions for Eclipse (including this one).

If you completed CS 1323/4 last semester, you probably have the JDK and Eclipse installed already.
You don't have to install the latest version of Eclipse, but we ask that you at least install JDK 11.

### Install JDK

1. Uninstall any version of the JDK that is currently on your computer.
2. Download the installer for JDK 11 from [this site](https://secretlab.page/misc/jdk11/).
3. Run the installer with the default settings.
4. Delete the installer after the installation is complete.

### Clean Up the System Path on Windows 

If you uninstalled an older version of JDK on Windows, the system path *might* still include the destination of the deleted bin folder.
Follow these steps to remove it:

1. Right-click the Start button and select "System" from the context menu.
2. In the window that opens, scroll down to "Related settings" and click "System info".
3. A second window will open.
Click "Advanced system settings" from the options on the left.
4. A third window will open.
Click the "Environment Variables..." button at the bottom.
5. A fourth window will open, which contains two lists: user variables and system variables.
In the bottom list (system variables), select "Path" and then click the "Edit..." button.
6. A fifth window will open with a list of folder paths.
Look for the entry "C:\Program Files\Java\jdk-xxxx\bin", where "xxxx" is some version number.
Delete the entry and click the "OK" button.
7. Close out of windows three and four by clicking their respective "OK" buttons.

### Install Eclipse

1. The latest version of Eclipse can be downloaded on [this site](https://www.eclipse.org/downloads/packages/).
Please do not download the installer at the top of the page.
Instead, scroll down to "Eclipse IDE for Java Developers" (*not* "Enterprise Java Developers"), and download the file correspond to your operating system.
2. macOS users: Open the .dmg file from step 1, and drag the Eclipse folder to the Applications folder.
If you would like to add an Eclipse shortcut to the dock, open the Eclipse folder inside the Application folder, and drag the Eclipse launcher to the dock.
3. Windows users: Right-click the .zip file from step 1 and select "Extract All".
The unzipped folder can be moved to any convenient location.
If you would like to add an Eclipse shortcut to your Start menu or taskbar, open the Eclipse folder, right-click the eclipse.exe application, and select "Pin to Start" or "Pin to taskbar".
4. Delete the .dmg or .zip file after completing step 2 or 3.

## Download the Starter Code

If you're reading this document, you've created your very own GitHub repository (or "repo" for short).
This repo contains an online copy of the starter code for Lab 1.

At the top of the page is a file browser you can use to view the contents of the repo.
For instance, the text you're reading is stored in the file README.md.
(The file extension "md" stands for "markdown", but it's just a plain text file that you can open in any editor.)
GitHub automatically displays the contents of this file on the home page of the repo.

If you click on a file, GitHub will show you its contents in an online text editor.
You can use this editor to make changes, but we'll ignore this functionality for now.
Instead, let's make a local copy of the files.

1. If you're not on the home page of the repo, click the "<> Code" link at the top-left of the page.
2. Click the "Code" button at the top of the file browser.
3. Select "Download ZIP" from the menu and save the file.
4. Unzip the file from step 3 and move the resulting folder to a convient location on your computer.
(We recommend creating a folder to store all of the programming assignments in this course.)
5. The name of the folder will match the name of your GitHub repo (lab1-username).
You can change the name to something simple like "Lab1".
6. Delete the .zip file from step 3.

## Import the Starter Code

The starter code comes preconfigured to run in Eclipse.
Import it with the following steps:

1. Click "File" in the menu bar at the top-left of the Eclipse window.
2. In the drop-down menu, select "Import...", which will open a new window.
3. Click the arrow next to "General", select "Existing Projects into Workspace", and then click the "Next" button.
4. Select the radio button next to "Select root directory" and click the "Browse..." button.
5. Navigate to the folder that contains the starter code and click the "Select Folder" button.
(Select the folder that contains the src folder, not the src folder itself.)
6. Check that the Projects pane contains a single item (which should not be named "src") and click the "Finish" button.
7. A folder named "Lab1" will appear in the Package Explorer on the left side of the Eclipse window.
8. Click the arrow next to Lab1 to reveal the src folder.
9. Click the arrow next to src to reveal the default package.
(Java projects with multiple source files can be organized into different packages to avoid namespace conflicts.
The starter code, however, only uses the default package.)
10. Click the arrow next to the package to reveal the source files: Java1Review.java and JUnitTests.java.
Double-click the files to open them in the editor.

If you accidentally import the wrong folder, delete it from the Package Explorer and try again.
Before reimporting, check that Eclipse didn't create any extra hidden files in the project folder.
(Hidden files have names that begin with a period, and, by default, can't be seen in a file browser.)
To view the hidden files, follow these steps:

* Windows: Open File Explorer, select "View" from the menu bar, and check the box next to "Hidden items".
* macOS: Open Finder and type the keyboard shortcut Command + Shift + Period.

Compare the root of the project folder and the src subfolder to those in your GitHub repo.
Delete any files on your computer that are not in the repo and then follow the import instructions above.

## Run the Unit Tests

Take a look at the class JUnitTests.
This is a JUnit test class.
Notice that each method is prefixed with the annotation `@Test`.

Right-click the file JUnitTests.java in the Package Explorer and select "Run As" > "JUnit Test" from the context menu.
You will receive a warning that errors exist in the project, but click the "Proceed" button anyway.

A new tab will open next to the Package Explorer named "JUnit".
The tab includes a list of all the methods with the `@Test` annotation.
Each of these methods is a test, and they're all failing!

The goal of this assignment is to add code to the class Java1Review so that all of the tests pass.
(Don't change the code in JUnitTests.)
You'll know you're making progress when some of those red X's turn into green check marks.

## Write the Java1Review Class

Let's take a closer look at the first test in JUnitTests, which I'll duplicate below.

```java
@Test
void testFloatingPointDivision() {
    assertEquals(4.0 / 4.0, Java1Review.divide(4.0, 4.0));
    assertEquals(4.0 / 3.0, Java1Review.divide(4.0, 3.0));
    assertEquals(4.0 / 2.0, Java1Review.divide(4.0, 2.0));
    assertEquals(4.0 / 1.0, Java1Review.divide(4.0, 1.0));
    assertEquals(Double.POSITIVE_INFINITY, Java1Review.divide(4.0, 0.0));
}
```

The test, which is named "testFloatingPointDivision", calls the assertEquals method five times.
(If you look at the other tests, you'll see that they also call methods with names that begin with "assert".)
This is a special JUnit method that defines the test criteria.
To pass the test, `assertEquals` must be given two equal inputs every time it is called.
If `assertEquals` is called anywhere in `testFloatingPointDivision` with *unequal* inputs, the test fails.

Now notice that the second input to `assertEquals` is always a call to a method named "divide" in the class Java1Review.
This implies that `testFloatingPointDivision` is testing the Java1Review divide method.
Each time `assertEquals` is called, it checks the output of `divide`.
By looking at the inputs to `divide` and the first input to `assertEquals`, we can see that `divide` should divide its first argument by its second and return the result.

Each method in JUnitTests tests a different method in Java1Review.
By studying the tests, you can figure out how each Java1Review method should work.
Your job is to write the Java1Review methods so that they pass the tests.

### Getting Started: Write Method Stubs

The starter code for Java1Review includes only the class declaration and the main method.
A good way to start this assignment is to write [method stubs](https://en.wikipedia.org/wiki/Method_stub).
A stub consists of a method declaration and an arbitrary return value.
For instance, if you know a method returns an integer, the body of the stub could be `return 0;`.

For each test in JUnitTests, write a stub for the Java1Review method being tested.
Think carefully about the method declarations.
In particular, ask yourself these questions:

* What is the data type of each input?
* What is the return type?
* Should the method be static?
* Should the method be public or private?

You'll know a stub is correct if the method is no longer underlined in JUnitTests.

### Debugging: Use the JUnit Tab

When you run the unit tests in Eclipse, a tab opens next to the Package Explorer that contains two panes.
The top pane shows a list of the tests.
If you click on a failed test in the list, its failure trace is shown in the bottom pane.
The information in these panes can be extremely useful for debugging your code.
In particular, note the following:

* If a test is red, your code produced an error.
(Check the method signature and return statement.)
* If a test is blue, one of the assert methods failed.
That is, the output of your code did not match the expected output.
* For blue tests, the top line of the failure trace shows the inputs to the failed assert method.
You can view this information in a separate window by right-clicking the line and selecting "Compare Result".
The output of your code is shown in the "Actual" pane; the correct output is shown in the "Expected" pane.
* The second line in the failure trace shows the line number of the failed assert method.
Right-click the line and select "Go to File" to highlight the assert method in the text editor.

## Commit to GitHub

It's good practice to commit your code to GitHub after you pass each unit test.
Below are instructions on how to do this with a web browser.

1. Navigate to the home page of your repo.

2. In the file browser at the top of the page, click the text "src" next to the folder icon.
This will open a new page that shows the contents of the folder: JUnitTests.java and Java1Review.java.
We want to replace the file Java1Review.java with the version on your computer that contains your code.

3. Click the "Add file" button in the top-right corner and select "Upload files" from the drop-down menu.

4. A new page will open with a rectangle that contains the text "Drag additional files here to add them to your repository Or choose your files".
Click the text "choose your files" and navigate to the location of Java1Review.java on your computer.

5. Scroll down the page to the rectangle that says "Commit changes".
Type a message in the text box that says "Add files via upload".
The message should be a [concise description](https://chris.beams.io/posts/git-commit/) of the changes to the repo.
Something like "Implement methodName" (where "methodName" is the method that passed the test) is a good choice.
Notice that it's written in the imperative mood and does not end with a period.

6. Make sure the radio button is set to "Commit directly to the main branch" and then click the green "Commit changes" button.

7. Open Java1Review.java in the GitHub file browser to check that your changes were added.

Regularly committing your code may seem like a hassle, but there are some good reasons to develop this habit:

* If your computer fails, you have an online backup of your code.
* If you accidentally break your program, you can revert to an earlier version.
* If you get stuck on an assignment, we can view the code in your repo and give you feedback.

## Upload to zyLabs

After your code passes the unit tests, upload it to zyLabs.
(A link to zyLabs can be found on the Canvas page for this assignment.)
Your grade will be equal to the number of zyLab tests that you pass before the deadline.
The zyLab tests are similar to the JUnit tests, but they have slightly different inputs and outputs.
