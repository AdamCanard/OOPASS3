Welcome to the README. The following are instructions on how to run our WordTracker.jar file.

ASSUMPTIONS:
1. Your have Java installed on your computer.
2. Your computer's PATH includes Java's /bin directory.

How To Run:
Our Jar takes in several arguments. The first argument is the filepath of the file with the words you wish to insert into the BSTree. The second argument is either -pf, -pl, or -po. PF prints all words in alphabetical order along with the corresponding list of files in which the words occur. PL prints all words in alphabetical order along with the corresponding list of files AND line numbers on which the words occur. PO prints all words in alphabetical order, along with the corresponding list of files and line numbers on which the words occurred, AS WELL as the frequency of the occurrence of the words. An OPTIONAL argument is -f <filename> where <filename> is the path of a file in which you wish to store the output of the program.

For example:
$ java -jar WordTracker.jar input.txt -po -f test.txt

You can replace input.txt with the relative filepath for any text document. Upon running the command, you will be shown the output as described above, and the program will serialize and store the tree in res/repository.ser.
