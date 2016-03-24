package ualbany.daneslist;


import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import static ualbany.daneslist.DataModel.*;

/**
 * Created by cmagnus on 2/24/16.
 *
 * According to these sites this should be used to hold global info
 * https://androidcookbook.com/Recipe.seam?recipeId=1218
 * https://www.mobomo.com/2011/05/how-to-use-application-object-of-android/
 *
 */
public class MyApplication extends Application {
    private static MyApplication singleton;
    DBHelper db;
    ArrayList<DataModel.Category> categoryList = new ArrayList<>();
    ArrayList<DataModel.Category> categoryListOn = new ArrayList<>();
    ArrayList<DataModel.Thing> thingList = new ArrayList<>();
    ArrayList<DataModel.Thing> thingListOn = new ArrayList<>();
    int thing = 0;
    int category = 0;


    public MyApplication getInstance() {
        return singleton;
    }

    // Called by the system when the device configuration changes while your component is running.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // Called when the application is starting, before any other application objects have been
    // created.
    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("MyApplication", "onCreate");
        // open database & load categories/things
        this.db = new DBHelper(this);
        this.categoryList = db.getAllCategories(); // load all categories

        Log.d("MyApplication: onCreate", this.categoryList.get(this.category).getName());

        // choose a random category
        this.getRandThing();
        Log.d("MyApplication: onCreate", String.valueOf(this.thing));
        singleton = this;
    }

    // This is called when the overall system is running low on memory, and would like actively
    // running processes to tighten their belts.
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    // This method is for use in emulated process environments. It will never be called on a
    // production Android device, where processes are removed by simply killing them;
    // no user code (including this callback) is executed when doing so.
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    // updates this.category and this.thing to select valid random entries
    public void getRandThing() {
        // assume db already opened in onCreate
        // assume db categories already imported into this.categoryList
        Log.d("getRandThing", "start");

        this.getOnCategories(); // updates categoryListOn

        // choose random category and find it's index in the original category list
        this.category = this.categoryList.indexOf(this.categoryListOn.get(this.randInt(this.categoryListOn.size())));

        // load all things in category
        this.thingList = db.getAllThingsInCategory(this.category);

        this.getOnThings(); // updates thingListOn

        // choose any random thing and find it's index in the original category list
        this.thing = this.thingList.indexOf(this.thingListOn.get(this.randInt(this.thingListOn.size())));
    }

    public void getOnCategories() {
        // empty list
        this.categoryListOn = new ArrayList<>();
        // go through master list & add categories to CategoryOnList if they are switched to on
        for (int i = 0; i < this.categoryList.size(); i++) {
            Log.d("getOnCategories", this.categoryList.get(i).getName() + " " + this.categoryList.get(i).getOnOff());
            if (this.categoryList.get(i).getOnOff()) this.categoryListOn.add(this.categoryList.get(i));
        }
    }

    public void getOnThings() {
        // empty list
        this.thingListOn = new ArrayList<>();
        // go through master list & add categories to CategoryOnList if they are switched to on
        for (int i = 0; i < this.thingList.size(); i++) {
            Log.d("getOnThings", this.thingList.get(i).getName() + " " + this.thingList.get(i).getOnOff());
            if (this.thingList.get(i).getOnOff()) this.thingListOn.add(this.thingList.get(i));
        }
    }

    public int randInt(double size) {
        return (int) Math.floor(Math.random() * size);
    }

    public DataModel.Thing getThing() {
        return this.thingList.get(this.thing);
    }

    public DataModel.Category getCategory() {
        return this.categoryList.get(this.category);
    }
}

