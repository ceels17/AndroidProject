package ualbany.daneslist;


import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;


/*
 A simple {@link Fragment} subclass.
 Activities that contain this fragment must implement the
  {@link ThingDetailFragment.OnFragmentInteractionListener} interface
 to handle interaction events.
  Use the {@link ThingDetailFragment#newInstance} factory method to
  create an instance of this fragment.
 */
public class ThingDetailFragment extends Fragment //implements RandomFragment.OnFragmentInteractionListener
{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    // these are keys to store things in bundle if fragment despawns/respawns?
    private static final String ARG_NAME = "name";
    private static final String ARG_ONOFF = "onOff";
    private static final String ARG_NOTE = "note";
    private static final String ARG_PRICE = "price";
    private static final String ARG_PHONE = "phone";
    private static final String ARG_EMAIL = "email";

    // UI elements
    private EditText newName, newNote, newPrice, newPhone, newEmail;
    private Switch newOnOff;

    //other  parameters
    protected MyApplication myApp;

    // this was generated by android studio and doesn't seem to do anything:
    //private OnFragmentInteractionListener mListener;


    public ThingDetailFragment() {
        // Required empty public constructor
    }


    public ThingDetailFragment newInstance() {

        // instantiate new fragment
        ThingDetailFragment fragment = new ThingDetailFragment();

        // according to:
        // http://stackoverflow.com/questions/16825524/interface-nullpointerexception-in-fragment-onitemclicklistener-from-gridview
        // you need these to refer to the fragment you created so you don't get null button from making a new new fragment
        // but in this example this happens in onCreate; I think we need to use a constructor in newInstance
        //FragmentManager fm = getFragmentManager();
        //fragment = (fragment_thing_detail)fm.findFragmentById(R.id.???)


        fragment.myApp = (MyApplication)getContext().getApplicationContext();
        /*fragment.theThing = newThing;
        fragment.myApp.setThing(newThing);*/

        // create bundle with current information in case of despawn/respawn
        // this won't work because i think we're returning the cursor and not parsing it
        Bundle args = new Bundle();
        /*args.putString(ARG_NAME, fragment.myApp.db.getThingName(fragment.myApp.thing));
        args.putBoolean(ARG_ONOFF, Boolean.valueOf(fragment.myApp.db.getThingOnOff(fragment.myApp.thing)));
        args.putString(ARG_NOTE, fragment.myApp.db.getThingNote(fragment.myApp.thing));*/
        args.putString(ARG_NAME, fragment.myApp.thingList.get(fragment.myApp.thing).getName());
        args.putBoolean(ARG_ONOFF, Boolean.valueOf(fragment.myApp.thingList.get(fragment.myApp.thing).getOnOff()));
        args.putString(ARG_NOTE, fragment.myApp.thingList.get(fragment.myApp.thing).getNote());
        args.putString(ARG_PRICE, fragment.myApp.thingList.get(fragment.myApp.thing).getPrice());
        args.putString(ARG_PHONE, fragment.myApp.thingList.get(fragment.myApp.thing).getPhone());
        args.putString(ARG_EMAIL, fragment.myApp.thingList.get(fragment.myApp.thing).getEmail());


        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // onCreate can assign variables, get Intent extras, and anything that doesn't involve view.
        // onCreate happens before view is created
        super.onCreate(savedInstanceState);

        myApp = (MyApplication)getContext().getApplicationContext();

        Log.v("fragment_thing_detail", "onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // onCreateView happens after onCreate.
        // onCreateView can assign view variables and do any graphic initializations.
        // must return a View or null (if fragment involves no view)
        // onCreateView is called before onActivityCreated

        // allegedly in a fragment this is what we need instead of setContentView:
        // http://stackoverflow.com/questions/12108370/how-to-setcontentview-in-a-fragment
        View view = inflater.inflate(R.layout.fragment_thing_detail, container, false);
        // and then this stuff should work. and it does.

        Log.v("fragment_thing_detail", "onCreateView() " + view);



        // link properties with UI objects to detect/use new user input
        // this happens after onCreate
        this.newName = (EditText) view.findViewById(R.id.nameText);
        this.newNote = (EditText) view.findViewById(R.id.noteText);
        this.newOnOff = (Switch) view.findViewById(R.id.onOffSwitch);

        // if there's a bundle, get those variables
        if (getArguments() != null) {
            this.newName.setText(getArguments().getString(ARG_NAME));
            this.newOnOff.setChecked(getArguments().getBoolean(ARG_ONOFF));
            this.newNote.setText(getArguments().getString(ARG_NOTE));
            this.newPrice.setText(getArguments().getString(ARG_PRICE));
            this.newPhone.setText(getArguments().getString(ARG_PHONE));
            this.newEmail.setText(getArguments().getString(ARG_EMAIL));

        }
        else { // otherwise use the database
            //Log.d("onCreateView:else", this.myApp.thingList.get(this.myApp.thing).getName());
            this.newName.setText(this.myApp.thingList.get(this.myApp.thing).getName());
            this.newOnOff.setChecked(Boolean.valueOf(this.myApp.thingList.get(this.myApp.thing).getOnOff()));
            this.newNote.setText(this.myApp.thingList.get(this.myApp.thing).getNote());
            this.newPrice.setText(this.myApp.thingList.get(this.myApp.thing).getPrice());
            this.newPhone.setText(this.myApp.thingList.get(this.myApp.thing).getPhone());
            this.newEmail.setText(this.myApp.thingList.get(this.myApp.thing).getEmail());


        }

        // from http://stackoverflow.com/questions/7397391/event-for-handling-the-focus-of-the-edittext
        // Toast is for little popup messages to help debugging.
        // testing for losing hasFocus because that tells us when the field is not being edited.
        this.newName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    //theThing.setName(newName.getText().toString());
                    //myApp.getThing().setName(newName.getText().toString());
                    Log.d("onCreateView", newName.getText().toString());
                }
            }
        });

        this.newNote.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    //theThing.setNote(newNote.getText().toString());
                    //myApp.getThing().setNote(newNote.getText().toString());
                    Log.d("onCreateView", newNote.getText().toString());
                }
            }
        });

        this.newPrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    //theThing.setNote(newNote.getText().toString());
                    //myApp.getThing().setNote(newNote.getText().toString());
                    Log.d("onCreateView", newPrice.getText().toString());
                }
            }
        });

        this.newPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    //theThing.setNote(newNote.getText().toString());
                    //myApp.getThing().setNote(newNote.getText().toString());
                    Log.d("onCreateView", newPhone.getText().toString());
                }
            }
        });

        this.newEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    //theThing.setNote(newNote.getText().toString());
                    //myApp.getThing().setNote(newNote.getText().toString());
                    Log.d("onCreateView", newEmail.getText().toString());
                }
            }
        });
        this.newOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //theThing.setOnOff(newOnOff.isChecked());
                Log.d("onCreateView", String.valueOf(newOnOff.isChecked()));
            }
        });

        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_thing_detail, container, false);
        return view;
    }

    // from
    // http://www.intertech.com/Blog/saving-and-retrieving-android-instance-state-part-1/
    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putString(ARG_NAME, this.newName.getText().toString());
        state.putString(ARG_NOTE, this.newNote.getText().toString());
        state.putString(ARG_PRICE, this.newPrice.getText().toString());
        state.putString(ARG_PHONE, this.newPhone.getText().toString());
        state.putString(ARG_EMAIL, this.newEmail.getText().toString());
        state.putBoolean(ARG_ONOFF, this.newOnOff.isChecked());
    }

    public void updateThing() {
        this.newName.setText(myApp.getThing().getName());
        this.newOnOff.setChecked(myApp.getThing().getOnOff());
        this.newNote.setText(myApp.getThing().getNote());
        this.newPrice.setText(myApp.getThing().getPrice());
        this.newPhone.setText(myApp.getThing().getPhone());
        this.newEmail.setText(myApp.getThing().getEmail());


    }


    /*@Override
    public void onClick(View v) {
        myApp.getRandThing();
        this.newName.setText(this.myApp.thingList.get(this.myApp.thing).getName());
        this.newOnOff.setChecked(Boolean.valueOf(this.myApp.thingList.get(this.myApp.thing).getOnOff()));
        this.newNote.setText(this.myApp.thingList.get(this.myApp.thing).getNote());
    }*/

    // TO DO: Rename method, update argument and hook method into UI event
    // ?not sure if I need this because I did this above in onCreateView?
    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    /*@Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    // According to:
    // http://stackoverflow.com/questions/19210187/how-to-create-an-interface-to-get-info-from-a-fragment-to-an-android-activity
    // This should let the activity get things out of the fragment:

    /*public Thing getThing() {
        return this.theThing;
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    // This isn't reserved; You can call it whatever makes sense.
    // I suspect I don't need it if I'm just displaying the fragment?





   /* public interface OnThingDetailInteractionListener {
        void onThingDetailInteraction();
    }*/
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
    /*public interface OnThingDetailInteractionListener{
        void on
    }*/

}
