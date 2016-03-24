package ualbany.daneslist;

import java.util.ArrayList;

/**
 * Created by cmagnus on 3/5/16.
 * Hoping I can make all these in one file;
 * otherwise it will be annoying
 * or i can make one generic thing and the note and category fields can be empty on categories
 */
public class DataModel {

    public class Item {
        private String name;
        private boolean onOff;
        private int id;

        // Constructors:

        // default
        public Item() {
            this.name = "New Item";
            this.onOff = true;
            this.id = -1;   // negative means it's not in db yet
        }

        // takes name; default to On
        /*public Item(String name) {
            this.name = name;
            this.onOff = true;
        }*/

        // takes all values
        public Item(int id, String name, boolean onOff) {
            this.name = name;
            this.onOff = onOff;
            this.id = id;
        }

        // Methods:

        public String toString() {
            return "" + this.id + "name: " + this.name +
                    "onOff: " + String.valueOf(this.onOff);
        }

        // getters

        public String getName() {
            return this.name;
        }

        public boolean getOnOff() {
            return this.onOff;
        }

        public int getId() {
            return this.id;
        }

        // setters

        public void setName(String newName) {
            this.name = newName;
        }

        public void setOnOff(boolean newOnOff) {
            this.onOff = newOnOff;
        }

        public void setOnOff(int newOnOff) {
            if (newOnOff == 0) this.onOff = false;
            else this.onOff = true;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public class Thing extends Item {
        private String note = "Add a note.";
        private String price = "$";
        private String phone = "(xxx)-xxx-xxxx";
        private String email = "example@gamil.com";
        private int parent = 0;

        // constructors
        // default
        public Thing() {
            super();
            this.parent = 0;
            this.note = "Add a note.";
            this.price = "$";
            this.phone = "(xxx)-xxx-xxxx";
            this.email = "example@gamil.com";
        }

        /*public Thing(String name, String note) {
            super(name, true);
            this.note = note;
            this.parent = 0;
        }*/

        public Thing(int id, String name, boolean onOff, String note, String price, String phone, String email, int parent) {
            super(id, name, onOff);
            this.note = note;
            this.price = price;
            this.phone = phone;
            this.email = email;
            this.parent = parent;
        }

        /*public Thing (String name, boolean onOff, String note, int parent) {
            super(name, onOff);
            this.note = note;
            this.parent = parent;
        }*/

        // Methods:

        /*public String toString() {
            return super.toString() + "note: " + this.note;
        }*/

        // getters

        public String getNote() {return this.note;}

        public String getPrice() {return this.price;}

        public String getPhone() {return this.phone;}

        public String getEmail() {return this.email;}

        public int getParent() {
            return this.parent;
        }

        // setters
        public void setNote(String newNote) {this.note = newNote;}

        public void setPrice(String newPrice) {this.note = newPrice;}

        public void setPhone(String newPhone) {
            this.note = newPhone;
        }

        public void setEmail(String newEmail) {
            this.note = newEmail;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }
    }

    // this is completely inert right now, but I think it makes sense to use it instead of item.
    public class Category extends Item{

        // Properties
/*
        private ArrayList<Thing> thingList = null;  // list of things
        private int onThings = 0;                   // number of things in list switched on

        // Constructors

        public Category() {
            super();
            this.onThings = 0;
            //this.thingList = null;
        }

        public Category(String name, ArrayList<Thing> thingList) {
            super(name, true);
            this.thingList = thingList;

            // count the onThings
            this.onThings = 0;
            this.countOnThings();
            this.setAllParents();
        }

        public Category(String name, boolean onOff, ArrayList<Thing> thingList) {
            super(name, onOff);
            this.thingList = thingList;
            this.onThings = 0;
            this.countOnThings();
            this.setAllParents();
        }

        // Methods

        // counts switched-on things in list
        public void countOnThings() {
            for (int i = 0; i < this.thingList.size(); i++)
                if (this.thingList.get(i).getOnOff()) this.onThings++;
        }

        public void setAllParents() {
            for (int i = 0; i < this.thingList.size(); i++)
                this.thingList.get(i).setParent(this);
        }

        // returns random thing from thingList
        public Thing randThing() {
            // select random thing from selected category
            int index = (int)(Math.random()*this.onThings);
            // account for off switches
            for (int i = 0; i < index; i++) {
                if (!this.thingList.get(i).getOnOff()) index++;
                // might want to break if thingList = i??? for now no
            }
            return this.thingList.get(index);
        }

        public String toString() {
            String newString = super.toString() +"\n";
            for (int i = 0; i < this.thingList.size(); i++)
                newString += this.thingList.get(i).toString() + " ";
            return newString;
        }

        // Getters
        public ArrayList<Thing> getThingList() {
            return this.thingList;
        }

        // Setters
        // not making setThingList because will require lots of bookkeeping and I don't know that I need it.
        public void setThingList() {}
*/
    }

}

