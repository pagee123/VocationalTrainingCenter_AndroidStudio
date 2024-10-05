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
public class FirstFragment extends Fragment {

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first, container, false);

        Button btnNextPage = v.findViewById(R.id.btnNextPage);
        btnNextPage.setOnClickListener(btnNextPageOnClick);

        return v;
    }

    private View.OnClickListener btnNextPageOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 呼叫主程式檔的方法顯示第二頁
            MainActivity activity = (MainActivity) getActivity();
            activity.showSecondFragment();
        }
    };
}
