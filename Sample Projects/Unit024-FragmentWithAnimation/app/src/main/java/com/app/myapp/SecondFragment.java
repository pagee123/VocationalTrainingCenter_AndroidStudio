package com.app.myapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_second, container, false);

        Button btnPreviousPage = v.findViewById(R.id.btnPreviousPage);
        btnPreviousPage.setOnClickListener(btnPreviousPageOnClick);

        return v;
    }

    private View.OnClickListener btnPreviousPageOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 呼叫主程式檔的方法隱藏第二頁
            MainActivity activity = (MainActivity) getActivity();
            activity.hideSecondFragment();
        }
    };
}
