package io.full.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by user on 06/04/17.
 */

public class ControlFragment extends Fragment implements View.OnClickListener {

    OnButtonClickListener mCallback;


    public interface OnButtonClickListener {
        void onButtonClicked(int id);
    }

    public ControlFragment() {
        // Required empty public constructor

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mCallback = (OnButtonClickListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()+" must implement the OnButtonClickListener Interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View lRootView = inflater.inflate(R.layout.fragment_control_panel, container, false);

        setOnClickListeners(lRootView);

        return lRootView;
    }


    @Override
    public void onClick(View pView) {

        int id = pView.getId();
        mCallback.onButtonClicked(id);

    }

    public void setOnClickListeners(View pView){

        ((TextView)pView.findViewById(R.id.number_0)).setOnClickListener(this);

        ((TextView)pView.findViewById(R.id.number_1)).setOnClickListener(this);

        ((TextView)pView.findViewById(R.id.number_2)).setOnClickListener(this);

        ((TextView)pView.findViewById(R.id.number_3)).setOnClickListener(this);

        ((TextView)pView.findViewById(R.id.number_4)).setOnClickListener(this);

        ((TextView)pView.findViewById(R.id.number_5)).setOnClickListener(this);

        ((TextView)pView.findViewById(R.id.number_6)).setOnClickListener(this);

        ((TextView)pView.findViewById(R.id.number_7)).setOnClickListener(this);

        ((TextView)pView.findViewById(R.id.number_8)).setOnClickListener(this);

        ((TextView)pView.findViewById(R.id.number_9)).setOnClickListener(this);

        ((TextView)pView.findViewById(R.id.decimalPoint)).setOnClickListener(this);

        ((TextView)pView.findViewById(R.id.plus_or_minus)).setOnClickListener(this);

        ((TextView)pView.findViewById(R.id.operation_add)).setOnClickListener(this);

        ((TextView)pView.findViewById(R.id.operation_subtract)).setOnClickListener(this);

        ((TextView)pView.findViewById(R.id.operation_multiply)).setOnClickListener(this);

        ((TextView)pView.findViewById(R.id.operation_division)).setOnClickListener(this);

        ((TextView)pView.findViewById(R.id.operation_modulo)).setOnClickListener(this);

        ((TextView)pView.findViewById(R.id.operation_delete)).setOnClickListener(this);

        ((TextView)pView.findViewById(R.id.operation_clear)).setOnClickListener(this);

        ((TextView)pView.findViewById(R.id.operation_equalTo)).setOnClickListener(this);

    }

}

