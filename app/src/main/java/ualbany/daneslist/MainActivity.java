package ualbany.daneslist;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //implements fragment_thing_detail.OnThingChanged {

    protected MyApplication myApp;
    //DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the application instance
        myApp = (MyApplication)getApplication();
        //db = new DBHelper(this);
        //ArrayList<String> categoryList = myApp.db.getAllCategories();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.root_layout,
                            RandomFragment.newInstance())
                            //fragment_thing_detail.newInstance(getString(R.string.defaultName), true, getString(R.string.defaultNote)))
                            //fragment_thing_detail.newInstance())
                    .commit()
            ;
        }
    }

    /*@Override
    public void onThingChanged(Thing newThing) {
        final fragment_thing_detail detailsFragment =
                fragment_thing_detail.newInstance(newThing);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.root_layout, detailsFragment)
                .addToBackStack(null)
                .commit();
    }*/

}
