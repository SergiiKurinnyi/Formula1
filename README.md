# Formula1

There are 2 log files start.log and end.log that contain start and end data of the best lap for each racer of Formula 1 - Monaco 2018 Racing. (Start and end times are fictional, but the best lap times are true). Data contains only the first 20 minutes that refers to the first stage of the qualification.
Q1: For the first 20 minutes (Q1), all cars together on the track try to set the fastest time. The slowest seven cars are eliminated, earning the bottom grid positions. Drivers are allowed to complete as many laps as they want during this short space of time.
Top 15 cars are going to the Q2 stage. If you are so curious, you can read the rules here https://www.thoughtco.com/developing-saga-of-formula1-qualifying-1347189
The third file abbreviations.txt contains abbreviation explanations.

Parse hint:
SVF2018-05-24_12:02:58.917
SVF - racer abbreviation
2018-05-24 - date
12:02:58.917 - time
Your task is to read data from 2 files, order racers by time and print report that shows the top 15 racers and the rest after underline, for example:

Daniel Ricciardo      | RED BULL RACING TAG HEUER     | 1:12.013
Sebastian Vettel      | FERRARI                                         | 1:12.415
...



Brendon Hartley   | SCUDERIA TORO ROSSO HONDA | 1:13.179
Marcus Ericsson    | SAUBER FERRARI                           | 1:13.265

Use Java 8 Stream API where appropriate.

# Functional requirements

We received clarified data(see attachments). Actually some racers did not finish even single lap, some had their engines blow on a second. Those problems should be addresed during data parsing.
abbreviations.txtstart.logend.log

# Tables

Business require you to develop robust solution, so there additional tables that should be displayed based based on provided data:

Table Name          | Columns                       | Default sorting
--------------------------------------------------------------------------------
Racers best lap     | No, Name, Team, Best Lap Time | Ascending by best lap time

Racers names        | No, Name                      | Alphabetically by Name

Racers lap count    | No, Name, Team, Lap Count     | Descending by Lap Count

Racers avg lap time | No, Name, Team, Avg Lap Time  | Ascending by avg lap time


***
Type of table should be asked from user in console menu
Table should be sorted by default column, but allow user to select column and sorting.
