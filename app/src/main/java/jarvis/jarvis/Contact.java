package jarvis.jarvis;

public class Contact {

    //private variables
    int _id;
    String _name;
    String _phone_number;
    String _email_id;
    // Empty constructor
    public Contact(){

    }
    // constructor
    public Contact(int id, String name, String _phone_number, String _email_id){
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
        this._email_id = _email_id;
    }

    // constructor
    public Contact(String name, String _phone_number, String _email_id){
        this._name = name;
        this._phone_number = _phone_number;
        this._email_id = _email_id;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getName(){
        return this._name;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }

    // getting phone number
    public String getPhoneNumber(){
        return this._phone_number;
    }

    // setting phone number
    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }

    // getting phone number
    public String getEmailId(){
        return this._email_id;
    }

    // setting phone number
    public void setEmailId(String email_id){
        this._email_id = email_id;
    }
}