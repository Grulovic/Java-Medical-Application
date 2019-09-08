/* ------------------------------------------------------------------ *
 * Stefan Grulovic 
 * DATE: 11/02/2017
 * TITLE: Patient management system for a general medical practice
 * DESCRIPTION: All the information in the word file that comes with this.
 * COPYRIGHT: Releases as open source code under the GNU public license agreement.
 * CONTACT: 20150280@student.act.edu 
 * ------------------------------------------------------------------ */

// Importing all the libraries needed
import java.io.*;                   
import java.util.*;
public class patient_management_APP implements Serializable{
    public static void main(String[] args) throws IOException // Declares that an error (exception) may occur
    {
        Scanner sc = new Scanner(System.in);          //reads input from different sources (used mainly for error trapping)
        //String inData;                              // Text variable to receive the user's input

        //DECLARIZATION AND INITIALIZATION
        //menu choices
        int choice;
        int id;
        int empty_array=0;

        //temporary storing of the variables
        String name,lname,date, comment;
        char sex;
        int age;
        double height, weight;

        user_input_check user_input_check = new user_input_check(); //object that check user input
        ArrayList<patient_record> record = new ArrayList<patient_record>(); //array list where patient record objects are stored

        //PROGRAM STARTS HERE
        do{//the following is a menu that displays and asks the user what choice they want
            System.out.println();
            System.out.println("________________________________________");
            System.out.println(" MENU: ");
            System.out.println("________________________________________");
            System.out.println("Enter one of the following commands:\n");
            System.out.println("1 - Insert new patient data. ");
            System.out.println("2 - Retrieve existing patient data. ");
            System.out.println("3 - Modify existing patient data. ");
            System.out.println("4 - Delete existing patient data. ");
            System.out.println("5 - Export patient data to file. ");
            System.out.println("6 - Import patient data from file. ");
            System.out.println("7 - EXIT.");
            System.out.println();
            System.out.println("Enter \"1\", \"2\", \"3\", \"4\", \"5\", \"6\" or \"7\".");
            System.out.println("________________________________________");
            choice = user_input_check.ask_int("Choice: ",0,8);

            if (choice < 1 || choice > 6) {//if else if statements that act as a menu
            }
            else if(choice == 1) {
                int patient_input_num = 0;
                patient_input_num = user_input_check.ask_int("\nPlease enter how many patients you want to enter: ",0,1000000);

                for(int i=0; i<patient_input_num; i++){
                    System.out.print("\n\n");
                    empty_array = record.size();
                    record.add( new patient_record( empty_array,"","","",'s',0,0,0,"",0));

                    //the following options asks and store data using methods from patient_record as well as error traping for wrong user input from user_input_check
                    System.out.println();
                    System.out.println("________________________________________");
                    System.out.println(" NEW PATIENT: ");
                    System.out.println("________________________________________");
                    System.out.println("\nPatient ID:  " + empty_array + "\n");

                    record.get(empty_array).name_set(user_input_check.ask_string("Please enter name: "));

                    record.get(empty_array).lname_set(user_input_check.ask_string("Please enter last name: "));

                    record.get(empty_array).date_set(user_input_check.ask_string("Please enter last day of visit: "));

                    String[] character_input_restrictions = {"m","M","male","Male","MALE","f","F", "female", "Female","FEMALE"};
                    record.get(empty_array).sex_set(user_input_check.ask_char("Please enter sex: ", character_input_restrictions));

                    record.get(empty_array).age_set(user_input_check.ask_int("Please enter age: ",0,150));

                    record.get(empty_array).height_set(user_input_check.ask_double("Please enter height: ",0.0,3.0));

                    record.get(empty_array).weight_set(user_input_check.ask_double("Please enter weight: ",0.0,700.0));

                    record.get(empty_array).comment_set(user_input_check.ask_string("Please enter a comment: "));

                    record.get(empty_array).bmi_set();

                    System.out.println("________________________________________");
                    System.out.println("");
                    System.out.println("Patient ID("+empty_array+") has been successfuly made. \n");

                    empty_array=0;
                }
            }

            else if(choice == 2) {//following is a program that displays all the data from a specific patient that the user chose to see, as well as erorr traping for incorect input 
                if( record.isEmpty()){System.out.println("\n_________________________________\nPatient record is empty.\n_________________________________\n"); continue; }
                id=user_input_check.ask_int("\nPlease enter the id of the patient you want retreive: ", -1, record.size());

                if(record.get(id) == null){ System.out.println("Patient with ID("+id+") does not exist."); continue; }

                System.out.println();
                System.out.println("________________________________________");
                System.out.println(" PATIENT ID: " + id);
                System.out.println("________________________________________");
                System.out.println( "Name: " + record.get(id).name_return() );
                System.out.println( "Last Name: " + record.get(id).lname_return() );
                System.out.println( "Last Visit Date: " + record.get(id).date_return() );
                System.out.println( "Sex: " + record.get(id).sex_return() );
                System.out.println( "Age: " + record.get(id).age_return() );
                System.out.println( "Height: " + record.get(id).height_return()+"m"  );
                System.out.println( "Weight: " + record.get(id).weight_return()+"kg" );
                System.out.println( "Comment: " + record.get(id).comment_return() );
                System.out.println( "BMI: " + record.get(id).bmi_return() );
                if( record.get(id).bmi_return() <18){ System.out.println( "     Patient is underweight. "); }
                else if( 18 < record.get(id).bmi_return() && record.get(id).bmi_return() < 28){System.out.println( "     Patient is normal. ");}
                else if( 28 < record.get(id).bmi_return()  && record.get(id).bmi_return() < 30){System.out.println( "     Patient is over-weight. ");}
                else if( 30< record.get(id).bmi_return() ){System.out.println( "     Patient is obese. ");}
                System.out.println("________________________________________\n");
            }

            else if(choice == 3) {//folowing ask for a id of a patient the user wants to modify after which is presented with a sub menu with options to change specific data

                if( record.isEmpty()){System.out.println("\n_________________________________\nPatient record is empty.\n_________________________________\n"); continue; }

                id=user_input_check.ask_int("\nPlease enter the id of the patient you want to modify: ", -1, record.size());

                if(id >= record.size() ){ System.out.println("Patient with ID("+id+") does not exist.\n"); continue; }

                do{
                    System.out.println("\n________________________________________");
                    System.out.println(" PATIENT ID("+ id + ") DATA MODIFICATION: ");
                    System.out.println("________________________________________");
                    System.out.println("Enter one of the following commands:");
                    System.out.println();
                    System.out.println("1  - Name. ");
                    System.out.println("2  - Last Name. ");
                    System.out.println("3  - Last Visit Date. ");
                    System.out.println("4  - Sex. ");
                    System.out.println("5  - Age. ");
                    System.out.println("6  - Height. ");
                    System.out.println("7  - Weight. ");
                    System.out.println("8  - Comment. ");
                    System.out.println("9  <- GO BACK! ");
                    System.out.println();
                    System.out.println("Enter \"1\", \"2\", \"3\", \"4\", \"5\", \"6\", \"7\", \"8\" or \"9\".");
                    System.out.println("________________________________________");
                    choice = user_input_check.ask_int("Command: ", 0, 10);

                    if (choice < 1 || choice > 9) {
                    }
                    else if(choice == 1) {
                        System.out.print("\nCurrent name: " + record.get(id).name_return() + "\n");

                        record.get(id).name_set(user_input_check.ask_string("Please enter new name: "));

                        System.out.println("Patient ID("+id+") name has been changed to: " + record.get(id).name_return() + ". \n");
                    }
                    else if(choice == 2) {
                        System.out.print("\nCurrent last name: " + record.get(id).lname_return() + "\n");

                        record.get(id).lname_set(user_input_check.ask_string("Please enter new last name: "));

                        System.out.println("Patient ID("+id+") last name has been changed to: " + record.get(id).lname_return() + ". \n");
                    }

                    else if(choice == 3) {
                        System.out.print("\nCurrent date: " + record.get(id).date_return() + "\n");

                        record.get(id).date_set(user_input_check.ask_string("Please enter new last day of visit: "));

                        System.out.println("Patient ID("+id+") last visit date has been changed to: " + record.get(id).date_return() + ". \n");
                    }
                    else if(choice == 4) {
                        System.out.print("\nCurrent sex: " + record.get(id).sex_return() + "\n");

                        String[] character_input_restrictions = {"m","M","male","Male","MALE","f","F", "female", "Female","FEMALE"};
                        record.get(id).sex_set(user_input_check.ask_char("Please enter new sex: ", character_input_restrictions));

                        System.out.println("Patient ID("+id+") sex has been changed to: " + record.get(id).sex_return() + ". \n");
                    }
                    else if(choice == 5) {
                        System.out.print("\nCurrent age: " + record.get(id).age_return() + "\n");

                        record.get(id).age_set(user_input_check.ask_int("Please enter new age: ",0,150));

                        System.out.println("Patient ID("+id+") age has been changed to: " + record.get(id).age_return() + ". \n");
                    }
                    else if(choice == 6) {
                        System.out.print("\nCurrent height: " + record.get(id).height_return() + "\n");

                        record.get(id).height_set(user_input_check.ask_double("Please enter new height: ",0.0,3.0));

                        System.out.println("Patient ID("+id+") height has been changed to: " + record.get(id).height_return() + "m. \n");
                    }
                    else if(choice == 7) {
                        System.out.print("\nCurrent weight: " + record.get(id).weight_return() + "\n");

                        record.get(id).weight_set(user_input_check.ask_double("Please enter new weight: ",0.0,700.0));

                        System.out.println("Patient ID("+id+") weight has been changed to: " + record.get(id).weight_return() + "kg. \n");
                    }
                    else if(choice == 8) {
                        System.out.println("\nCurrent comment: " + record.get(id).comment_return() + "\n");

                        record.get(id).comment_set(user_input_check.ask_string("Please enter a comment: "));

                        System.out.println("Patient ID("+id+") comment has been changed to: " + record.get(id).comment_return() + ". \n");
                    }
                    else if(choice == 9) {
                        System.out.print("\n");
                    }
                }  while (choice != 9);
                System.out.println();
            }
            else if(choice == 4) {//following aks for an id of a patient which the user wants to delete
                System.out.println("\n________________________________________");
                System.out.println(" PATIENT DELETION: ");
                System.out.println("________________________________________\n");

                if( record.isEmpty()){System.out.println("Patient record is empty."); continue; }//exits the option if there is nothing to delete
                id = user_input_check.ask_int("Please enter the ID of the patient you want to delete: ", -1, record.size());
                if(id >= record.size()){ System.out.println("\nPatient with ID("+id+") does not exist.\n"); continue; }
                record.remove(id);

                System.out.println("\nPatient ID(" + id +") has been successfuly deleted. ");
                System.out.println("________________________________________\n");
            }

            else if(choice == 5) {//following is a program that exports all the array list data in a specific format that can be read by the user as well as in the next option to be imported,includes error trapping for possible errors
                if( record.isEmpty()){System.out.println("\nPatient record is empty."); continue; } //exits the option if there is nothing to export
                BufferedWriter bw = null;
                String export_file_name;
                File export_file;

                export_file_name = user_input_check.ask_string("Please enter the name and the extension of the file and directory where you want to export: ");
                export_file = new File(export_file_name);

                try {
                    String output="";
                    for(int i=0; i<record.size();i++){
                        if(i >= record.size()){break;}
                        output += "//ID: \n" + record.get(i).id_return()+ "\n" +"//Name: \n" + record.get(i).name_return() + "\n" + "//Last Name: \n" + record.get(i).lname_return() + "\n" + "//Last visit date: \n"  + record.get(i).date_return() + "\n"
                        + "//Sex: \n" + record.get(i).sex_return() + "\n"+"//Age: \n"  + record.get(i).age_return() + "\n" + "//Height: \n"  + record.get(i).height_return() + "\n" +
                        "//Weight: \n" + record.get(i).weight_return() + "\n" + "//Comment: \n"  + record.get(i).comment_return() + "\n" + "//BMI: \n"  + record.get(i).bmi_return() + "\n" + "____________________________" + "\n";
                    }

                    FileWriter fw = new FileWriter(export_file);
                    bw = new BufferedWriter(fw);
                    bw.write("\n");
                    bw.write(output);
                    System.out.println("\nExport file: " + export_file);
                    System.out.println("\nExporting array data SUCCESSFUL!");

                } catch (IOException ioe) {ioe.printStackTrace();}
                finally{ 
                    try{if(bw!=null)bw.close();}
                    catch(Exception ex){System.out.println("\nError in closing the BufferedWriter"+ex);}
                }
            }
            else if(choice == 6) {//following is a program that imports all the array data from a text file and stores it in a appropriate array
                System.out.println("\n________________________________________");
                System.out.println(" PATIENT DATA IMPORT: ");
                System.out.println("________________________________________\n");

                String import_file_name;
                boolean exists;
                File import_file;
                do{
                    import_file_name = user_input_check.ask_string("Please enter the directory, name and the extension of the file: ");
                    import_file = new File(import_file_name);
                    exists = import_file.exists();
                    if( !import_file.exists() && !import_file.isFile() ){
                        System.out.println("\nFile inptuted either doesnt exist or it is not a file!\n");
                    }
                }while( !import_file.exists() && !import_file.isFile() );
                BufferedReader b = new BufferedReader(new FileReader(import_file_name));
                String line =null;

                int line_count=0;
                Scanner s1= new Scanner(new File(import_file_name));
                while(s1.hasNextLine()){
                    line_count ++;
                    s1.nextLine();
                }

                try{//following is a specific way the file is being read in order to store the data into objects
                    char import_choice;
                    int import_start = 0, import_count = 0;

                    String[] character_input_restrictions = {"y","Y","yes","YES","Yes","n","N", "NO", "no","No"};
                    import_choice = user_input_check.ask_char("Do you want to overwrite existing data (yes or no): ", character_input_restrictions);

                    if( import_choice == 'y' || import_choice == 'Y'){ 
                        import_start = 0;
                        for(int i=0; i<record.size(); i++){
                            record.remove(i);
                        }
                    }
                    else if(import_choice == 'n' || import_choice == 'N')
                    { import_start = record.size();}
                    while((line = b.readLine())!=null){
                        for(int i=0; i<line_count/21; i++){
                            empty_array=import_start + i;
                            record.add( new patient_record( empty_array,"","","",'s',0,0,0,"",0));

                            b.readLine();
                            b.readLine();
                            record.get(empty_array).id_set(empty_array);
                            b.readLine();
                            record.get(empty_array).name_set(b.readLine());
                            b.readLine();
                            record.get(empty_array).lname_set(b.readLine());
                            b.readLine();
                            record.get(empty_array).date_set(b.readLine());
                            b.readLine();
                            record.get(empty_array).sex_set(b.readLine().charAt(0));
                            b.readLine();
                            record.get(empty_array).age_set(Integer.parseInt(b.readLine()));
                            b.readLine();
                            record.get(empty_array).height_set(Double.parseDouble(b.readLine()));
                            b.readLine();
                            record.get(empty_array).weight_set(Double.parseDouble(b.readLine()));
                            b.readLine();
                            record.get(empty_array).comment_set(b.readLine());
                            b.readLine();
                            b.readLine();
                            b.readLine();
                            record.get(empty_array).bmi_set();

                            import_count++;
                        }
                    }
                    System.out.println("\nImport file: \""+ import_file_name +"\"");
                    if(record.isEmpty()){
                        System.out.println("\nImporting array data NOT successful!");
                    }else{
                        System.out.println("\nImporting array data SUCCESSFUL!");
                        System.out.println("Number of imported records: " + import_count +"\n");
                    }
                    System.out.println("________________________________________\n");
                }catch(IOException e){e.printStackTrace();}
                finally{ 
                    try{if(b!=null)b.close();}
                    catch(Exception ex){System.out.println("\nError in closing the BufferedReader"+ex);}
                }   
            }
            else if(choice == 7) {
                //Rxit prograE
                System.out.println("\nProgram will now Exit. ");
                System.out.println("GOODBYE!");
                System.exit(0);
            }
        }  while (choice != 7);
    }   
}