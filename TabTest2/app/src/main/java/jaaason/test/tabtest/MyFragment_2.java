package jaaason.test.tabtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ymno1 on 2/1/2016.
 */
public class MyFragment_2 extends Fragment {

    Button bt_BURGER;
    Button bt_PANDA;
    TextView tv;

    public static MyFragment_2 newInstance(){
        MyFragment_2 fragment = new MyFragment_2();
        return fragment;
    }

    public MyFragment_2(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceStae){
        View rootView = inflater.inflate(R.layout.my_fragment_2, container, false);
        bt_BURGER = (Button)rootView.findViewById(R.id.bt_burger);
        bt_PANDA = (Button)rootView.findViewById(R.id.bt_panda);
        tv = (TextView) rootView.findViewById(R.id.question2);
        bt_BURGER.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                tv.setText("OKAY, LET'S GO TO BURGER KING!");
            }
        });
        bt_PANDA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                tv.setText("OKAY, LET'S GO TO PANDA EXPRESS!");
            }
        });
        return rootView;
    }

}//End of MyFragment
