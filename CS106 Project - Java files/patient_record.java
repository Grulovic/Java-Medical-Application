/* ------------------------------------------------------------------ *
 * Stefan Grulovic 
 * DATE: 11/02/2017
 * TITLE: Patient record object 
 * DESCRIPTION: Object for patient management system (patient_management_APP) for a general medical practice
 * COPYRIGHT: Releases as open source code under the GNU public license agreement.
 * CONTACT: 20150280@student.act.edu 
 * ------------------------------------------------------------------ */
public class patient_record{
    //Variable Declaration
    int id;

    String name;
    String lname;
    String date;
    char sex;
    int age;
    double height;
    double weight;
    String comment;
    double bmi;

    //Constructor
    patient_record(int patient_record_id, String patient_record_name, String patient_record_lname
                , String patient_record_date, char patient_record_sex,int patient_record_age, double patient_record_height
                , double patient_record_weight, String patient_record_comment, double patient_record_bmi)
    {
        this.id=patient_record_id;
        this.name=patient_record_name;
        this.lname=patient_record_lname;
        this.date=patient_record_date;
        this.sex=patient_record_sex;
        this.age=patient_record_age;
        this.height=patient_record_height;
        this.weight=patient_record_weight;
        this.comment=patient_record_comment;
        this.bmi=patient_record_bmi;
    }

    //set methods
    void id_set(int patient_record_id){
        id=patient_record_id;
    }
    void name_set(String new_name){
        name=new_name;
    }
    void lname_set(String new_lname){
        lname=new_lname;
    }
    void date_set(String new_date){
        date=new_date;
    }
    void sex_set(char new_sex){
        sex=new_sex;
    }
    void age_set(int new_age){
        age=new_age;
    }
    void height_set(double new_height){
        height=new_height;
    }
    void weight_set(double new_weight){
        weight=new_weight;
    }
    void comment_set(String new_comment){
        comment=new_comment;
    }
    void bmi_set(){
        bmi=weight/(height * height);
    }

    //Return methods
    int id_return(){
        return id;
    }
    String name_return(){
        return name;
    }
    String lname_return(){
        return lname;
    }
    String date_return(){
        return date;
    }
    char sex_return(){
        return sex;
    }
    int age_return(){
        return age;
    }
    double height_return(){
        return height;
    }
    double weight_return(){
        return weight;
    }
    String comment_return(){
        return comment;
    }
    double bmi_return(){
        return bmi;
    }
}