Java Grep
# Introduction
JavaGrep is an app that is similar to how grep on the command-line
works. The user can pass in a regular expression and a directory
and the app will search within each file of the directory for matching
lines of text. These lines will be stored into a file also given
by the user.

The technologies used in this project are the following:
- Core Java for the app
- IntelliJ as the IDE
- Git for repository version control and distribution
- Docker for image deployment
- Java Libraries
- Maven for project management

# Quick Start
How to use your apps?

#Implemenation
## Pseudocode
```
linesWithPattern = []
for file in listFiles(rootDir)
    for line in readLines(file)
        if containsPattern(line)
            linesWithPattern.add(line)
writeToFile(linesWithPattern)
```
## Performance Issue
(30-60 words)
The Java Virtual Machine has a heap memory
space to store data for objects such as lists. For larger size
directories and files, the app can run into errors caused by not
having enough memory.

To solve this, I created another implementation of the app that
uses Java Streams which are objects that can process data coming
in without storing.

# Test
How did you test your application manually? (e.g. prepare sample data, run some test cases manually, compare result)

# Deployment
To dockerize the Grep app, I first created a new Docker hub account.
Then, I create a new Dockerfile that will be used later.
After packaging the java app, I built the docker image locally by
running the dockerfile.
Lastly, I pushed the image to my Docker Hub which can now be pulled
and used by others.
How you dockerize your app for easier distribution?

# Improvement
List three things you can improve in this project.