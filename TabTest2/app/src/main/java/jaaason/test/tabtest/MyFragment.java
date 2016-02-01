package jaaason.test.tabtest;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by JAAASON on 2016/1/31.
 */
public class MyFragment extends Fragment{

    Button bt_EAT;
    Button bt_DRINK;
    TextView tv;

    public static MyFragment newInstance(){
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    public MyFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceStae){
        View rootView = inflater.inflate(R.layout.my_fragment, container, false);
        bt_EAT = (Button)rootView.findViewById(R.id.bt_eat);
        bt_DRINK = (Button)rootView.findViewById(R.id.bt_drink);
        tv = (TextView) rootView.findViewById(R.id.question1);
        bt_EAT.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                tv.setText("OKAY, LET'S EAT!");
            }
        });
        bt_DRINK.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                tv.setText("OKAY, LET'S DRINK!");
            }
        });
        return rootView;
    }

}//End of MyFragment
