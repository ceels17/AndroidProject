
package ualbany.daneslist;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RandomFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RandomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RandomFragment extends Fragment{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CATEGORY = "category";
    private static final String ARG_LIST = "list";
    private static final String ARG_NAME = "name";
    private static final String ARG_ONOFF = "onOff";
    private static final String ARG_NOTE = "note";
    private static final String ARG_PRICE = "price";
    private static final String ARG_PHONE = "phone";
    private static final String ARG_EMAIL = "email";


    protected MyApplication myApp;

    //private Thing theThing;
    // not sure that I need these if they're stored in the bundle
    /*private int categoryIndex;
    private int thingIndex;*/

    private Button randButton;

    //private OnFragmentInteractionListener mListener;
    ThingChangeListener listener;
    Fragment thingDetail = new ThingDetailFragment();

    public interface ThingChangeListener {
        public void onThingChange(DataModel.Thing newThing);
    }




    public RandomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */
    public static RandomFragment newInstance() {
        // instantiate new fragment
        //fragment_thing_detail fragment = new fragment_thing_detail();
        //fragment.myApp = (MyApplication)getContext().getApplicationContext();

        RandomFragment fragment = new RandomFragment();
        Bundle args = new Bundle();
        //args.putInt(ARG_CATEGORY, category);
        //args.putInt(ARG_THING, thing);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApp = (MyApplication)getContext().getApplicationContext();

        // based on:
        // http://developer.android.com/about/versions/android-4.2.html#NestedFragments
        // this makes an extra entry. It doesn't do anything else.
        //Fragment thingDetail = new fragment_thing_detail();
        /*FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.thingDetailFragment, this.thingDetail).commit();*/

        /*if (getArguments() != null) {
            this.theThing = myApp.getCatList().get(
                    getArguments().getInt(ARG_CATEGORY)).getThingList().get(
                    getArguments().getInt(ARG_THING));
            /*this.categoryIndex = getArguments().getInt(ARG_CATEGORY);
            this.thingIndex = getArguments().getInt(ARG_THING);
        }
        else {
            this.theThing = myApp.getThing();
        }*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_random, container, false);
        // based on:
        // http://stackoverflow.com/questions/31823337/android-how-to-send-data-from-parent-fragment-to-child-fragment
        final ThingDetailFragment thingDetail = (ThingDetailFragment) getChildFragmentManager().findFragmentById(R.id.thingDetailFragment);
        // link properties with UI objects to detect/use new user input
        // this happens after onCreate
        this.randButton = (Button) view.findViewById(R.id.randButton);

        this.randButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myApp.getRandThing();
                thingDetail.updateThing();


                /*Log.d("randomClicked", "Listener: " + listener);
                //TODO: figure out why listener is null!!!
                if (null != listener) {
                    listener.onThingChange(myApp.getThing());
                }
                Log.d("randomClicked", myApp.getThing().getName());*/

                //Thing newThing = myApp.getThing();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }



    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onThingChange(DataModel.Thing newThing);
        void updateThing();
        //void onFragmentInteraction(Uri uri);
    }
    // for between-fragment communication
    // from:
    // http://stackoverflow.com/questions/15447804/fragment-fragment-communication-in-android

}
