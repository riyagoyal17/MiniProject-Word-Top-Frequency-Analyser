# MiniProject-Word-Top-Frequency-Analyser
Using data structures  find the frequency of the words in the text file "Indian National Anthem". Read the given file by creating Runnable based class that has a constructor to get the file path.

TopKeywordAnalyzer.java: 
This class implements Runnable. It gets the filepath using constructor. It uses FileUtility to read the the given text file as list. It then using hashmap and sorting sorts the data in descending order of their count.

KeywordCount.java:
This class is used to define the data form i.e Keyword:count

FileUtility.java:
This file implements various methods to read, write, append and readfileasList for a file.

TaskManager.java:
This class enables us to manage the number of threads that are being used at a particular time.
