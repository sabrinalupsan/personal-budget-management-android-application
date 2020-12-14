//package com.example.s4.BudgetApp;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.s4.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static android.content.ContentValues.TAG;
//import static android.widget.Toast.*;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link ListFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class ListFragment extends Fragment {
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//    private ArrayList<CreditEntry> creditEntries = new ArrayList<>();
//    private ListView lv;
//
//    public ListFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ListFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static ListFragment newInstance(String param1, String param2) {
//        ListFragment fragment = new ListFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//
//        }
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//       // GetElementsForList();
//      //  return inflater.inflate(R.layout.fragment_list, container, false);
//
//
//         View rootView = inflater.inflate(R.layout.fragment_list, container, false);
//        lv = (ListView)rootView.findViewById(R.id.lv_creditList);
////      //  GetElementsForList();
//
//
//        CreditEntry p200 = (CreditEntry)getArguments().getSerializable("p200");
//     //   return inflater.inflate(R.layout.fragment_list, container, false);
//
//        return rootView;
//
//    }
//
//
//    private OnFragmentInteraction listener;
//
//    public interface OnFragmentInteraction {
//        void onViewClick(String p1, String p2);
//    }
//
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
////        if (context instanceof OnFragmentInteraction)
////            listener = (OnFragmentInteraction) context;
////        else
////            throw new ClassCastException(context.toString() + " must implement ListFragment.OnFragmentInteraction");
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        listener = null;
//    }
//
//    private void AddEntry() {
//        ArrayAdapter<CreditEntry> adapter = new ArrayAdapter<>(this.getContext(),
//                android.R.layout.simple_list_item_1,
//                creditEntries);
//        lv.setAdapter(adapter);
//    }
//
//    public void GetElementsForList() {
//
//        Bundle extras = new Bundle();
//      //  Log.d(TAG, "------------GetElementsForList method called------------- ");
//      //  Bundle bundle = new Bundle();
//      //  Log.d(TAG, "------------Bundle created------------- ");
//        CreditEntry p200 = (CreditEntry)extras.getSerializable("p200");
//     //   Log.d(TAG, "------------Object received the info------------- ");
//        creditEntries.add(p200);
//     //   Log.d(TAG, "------------Info added------------- ");
//
//
//
//        AddEntry();
//       // CreditEntry p2 = (CreditEntry) extras.getSerializable("p2");
////        if (p2 != null) {
////            makeText(this.getContext(),
////                    p2.toString(),
////                    LENGTH_LONG).show();
////
////            creditEntries.add(p2);
//
//            ArrayAdapter adapter2 = (ArrayAdapter) lv.getAdapter();
//            adapter2.notifyDataSetChanged();
//
//    }
//}