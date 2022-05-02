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
To run this application, first pull the docker image from my docker hub
with the following command:
```
docker pull dvdhyh22/grep
```
Then run the docker container:
```
docker run --rm -v `pwd`INPUT_DIRECTORY:INPUT_DIRECTORY -v `pwd`OUTPUT_DIRECTORY:OUTPUT_DIRECTORY dvdhyh22/grep REGEX INPUT_DIRECTORY PATH_TO_OUTPUT_FILE
```
For example:
```
docker run --rm -v `pwd`/data:/data -v `pwd`/log:/log dvdhyh22/grep .*Romeo.*Juliet.* /data /log/grep.out
```

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
For sample data, I used a shakespeare text file that contains over
900000 words. I tested the application by running the app in IntelliJ
and changing the input arguments to check lines that have the Romeo
followed by Juliet anywhere else after in the same line. The lines
that matched are saved in a temporary out file that I opened to confirm
that each line matched the REGEX.

# Deployment
To dockerize the Grep app, I first created a new Docker hub account.
Then, I create a new Dockerfile that will be used later.
After packaging the java app, I built the docker image locally by
running the dockerfile.
Lastly, I pushed the image to my Docker Hub which can now be pulled
and used by others.
How you dockerize your app for easier distribution?

# Improvement
Three Improvements:
1. The app can read in any characters (Some characters are unreadable)
2. The app can separate the matched lines into separate files
3. The app can find matching sentences instead of matching lines.