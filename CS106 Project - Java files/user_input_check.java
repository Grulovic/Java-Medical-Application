/* ------------------------------------------------------------------ *
 * Stefan Grulovic 
 * DATE: 05/03/2017
 * TITLE: User input error trapping with parameters/limits.
 * DESCRIPTION: User input object that has 4 methods one for asking for and integer, 
 *              second for asking for a double, and third for asking for a character
 *              and fourth for asking for a string. 
 *              The four methods ask for a message that the user will be presented 
 *              with and asked for a requested data type and a parameters for limiting what the user can input.
 * COPYRIGHT: Releases as open source code under the GNU public license agreement.
 * CONTACT: 20150280@student.act.edu 
 * ------------------------------------------------------------------ */
import java.util.Scanner;
public class user_input_check{
    Scanner sc = new Scanner(System.in);
    String message;

    int user_int;
    double user_double;
    char user_char;
    String user_string;

    public user_input_check(){}

    /* EXAMPLE OF USAGE:
     * integer_num = user_input.ask_int("Please enter your integer: ", 0, 10);
     *   ->The user can only input numbers between 0 <= integer_num <= 10.
     */
    int ask_int(String message, int int_lower_limit, int int_higher_limit){
        do{
            System.out.println(message);
            while (!sc.hasNextInt())
            {
                System.out.println("Invalid input, type in an integer number:");
                sc.next();
            }
            user_int = sc.nextInt();
            if(user_int<=int_lower_limit || user_int >= int_higher_limit){ 
                System.out.println("Not possible. Please try again!"); 
            }
        }while(user_int<=int_lower_limit || user_int >= int_higher_limit);
        return user_int;
    }

    /* EXAMPLE OF USAGE:
     * double_num = user_input.ask_double("Please enter your double: ", 0.4, 10.4);
     *  ->The user can only input numbers between 0.4 <= double_num <= 10.4.
     */
    double ask_double(String message, double double_lower_limit, double double_higher_limit){
        do{
            System.out.println(message); 
            while (!sc.hasNextDouble())
            {
                System.out.println("Invalid input, type in number: ");
                sc.next();
            }
            user_double = sc.nextDouble();
            if(user_double<=double_lower_limit || user_double >= double_higher_limit){ 
                System.out.println("Not possible. Please try again!"); 
            }
        }while(user_double<=double_lower_limit || user_double >= double_higher_limit);
        return user_double;
    }

    /* EXAMPLE OF USAGE:
     * String[] character_input_restrictions = {"a","b","A","B"};
     * character = user_input.ask_char("Please enter your character: ", character_input_restrictions);
     *  ->The user can only input "a", "b", "A" or "B".
     */
    char ask_char(String message, String[] char_restriction ){
        boolean char_restriction_check = false;
        int message_check=0;
        String temp_user_char;

        do{
            if(message_check == 0 ){System.out.println(message);}
            else if(message_check > 0){System.out.println("Not possible. Please try again!"); }
            temp_user_char = sc.next();
            for(int i=0; i<char_restriction.length; i++){
                if( temp_user_char.equals(char_restriction[i])){
                    char_restriction_check = true;
                }
            }
            message_check++;
        }while(char_restriction_check != true);
        user_char = temp_user_char.charAt(0);
        return user_char;
    }

    /* EXAMPLE OF USAGE:
     * string = user_input.ask_string("Please enter name: ");
     *  ->The user needs to enter something it cannot input blank. Also error traps if the scanner accidently picks up a \n or something unusual.
     */
    String ask_string(String message){
        String string_empty_check;
        int message_check = 0;
        do{
            System.out.println(message);
            if(message_check > 0 ){System.out.println("Not possible. Please type in again!");}
            user_string = sc.nextLine();

            if(user_string.equals("") || user_string.equals("\n") || user_string.equals(" ") || user_string.equals("  ")){
                user_string = sc.nextLine();
                message_check++;
            }
        }while(user_string.equals("") || user_string.equals("\n") || user_string.equals(" ") || user_string.equals("  "));
        return user_string;
    }
}