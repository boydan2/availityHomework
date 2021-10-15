package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    //1. My proudest professional achievement: I got all positive mentions in my first employee evaluation

    //2. Something I read recently that I'd recommend: I read a very long blog post detailing genocides in the
    // last 100 years, very informative about propaganda and social conditioning used in these horrific instances

    //3. Explaining what Availity does to my grandmother: They create computer programs to allow doctors,
    //          and insurance companies to do business and secure sensitive information

    //4. a method that checks if a sting has properly nested parenthesis
    public boolean properlyNested(String lisp) {
        int countOpen = 0;
        int countClosed = 0;
        boolean properNesting = false;

        //convert string to charArray
        char[] array = lisp.toCharArray();

        //loop through each char and count how many open/closed parenthesis exist
        for(int i = 0; i < array.length; i++)
        {
            if (array[i] == '(')
                countOpen++;
            else if (array[i] == ')')
                countClosed++;
        }

        //make sure the string starts with ( and ends with ) as well as having an equal number of closing/opening parenthesis
        if (array[0] == '(' && array[array.length -1] == ')' && countOpen == countClosed)
            properNesting = true;

        return properNesting;
    }//end properlyNested
    //
    //

    //5. a method that reads in a comma separated list, and puts each row in a new file sorted by insurance company
    //file looks like this User Id (string), First Name (string), Last Name (string), Version (integer), Insurance Company (string)
    public void sortingCSV(File myCSVfile) throws IOException {
        Scanner sc = new Scanner(myCSVfile);
        sc.useDelimiter(",");   //sets the delimiter
        //vars
        String currentUserId = "";
        String currentFirstName = "";
        String currentLastName = "";
        Integer currentVersion = 0;
        String currentInsuranceCompany = "";

        //loop through each line,
        while (sc.hasNextLine())
        {
            currentUserId = sc.next();//goes to next token
            currentFirstName = sc.next();//goes to next token
            currentLastName = sc.next();//goes to next token
            currentVersion = (Integer)sc.next();//goes to next token
            currentInsuranceCompany = sc.next();//goes to last token in the line




            //if insurance company file not found, create new insurance company file
            File myObj = new File(currentInsuranceCompany + ".txt");//attempting to create a new file
            if (myObj.createNewFile()) {
                //File created
            } else {
                //File already exists
                //if there are duplicate user ids for the same insurance company, use the one with the higher version number
                BufferedReader reader = new BufferedReader(new FileReader(currentInsuranceCompany + ".txt"));
                ArrayList<String> str = new ArrayList<>();
                String line = "";
                while((line=reader.readLine())!=null){
                    str.add(line);
                }
                reader.close();
                str.search(currentUserId);//try to find if we have duplicate userIDs???????????????
                //TODO: replace userID with newest version...
            }

            //add line to insurance company file, regardless of if we created the file or if it exists
            String filename= currentInsuranceCompany + ".txt";
            FileWriter fw = new FileWriter(filename,true); //true to append
            fw.write(currentLastName + ", " + currentFirstName + ", " + currentUserId + ", " + currentVersion + "\n");//appends the user to the INSURANCE file
            fw.close();

            //sort file by last and first name
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            ArrayList<String> str = new ArrayList<>();
            String line = "";
            while((line=reader.readLine())!=null){
                str.add(line);//read each line in
            }
            reader.close();

            Collections.sort(str);//sort the file
            FileWriter fw2 = new FileWriter(filename,false); //false to overwrite, need to overwrite the file as we are sorting it.
            for(String s: str){
                fw2.write(s);
                fw2.write("\r\n");
            }
            fw2.close();


        }
        sc.close();
    }//end sortingCSV


}
