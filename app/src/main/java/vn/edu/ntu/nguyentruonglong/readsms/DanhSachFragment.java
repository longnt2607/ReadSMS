package vn.edu.ntu.nguyentruonglong.readsms;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DanhSachFragment extends Fragment {

    ListView lvDS_SMS;
    ArrayList<String> dsSMS;
    ArrayAdapter<String> smsAdater;
    Boolean readSMS;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_danhsach, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        readSMS = ((MainActivity)getActivity()).readSMS;
        lvDS_SMS = view.findViewById(R.id.lvSMS);
        smsAdater = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_activated_1);
    }

    private void HienThiSMS() {
        Bundle data = new Bundle();
        String strFromDate = data.getString(LuaChonFragment.KEY_FROM_DATE);
        String strToDate = data.getString(LuaChonFragment.KEY_TO_DATE);
        Date fromDate = Date.valueOf(strFromDate);
        Date toDate = Date.valueOf(strToDate);
        dsSMS.clear();
        Uri uriSMS = Telephony.Sms.Inbox.CONTENT_URI;
        ContentResolver resolver = getActivity().getContentResolver();
        Cursor cursor = resolver.query(uriSMS, null, null, null, null);
        while (cursor.moveToNext()) {
            int addressCol = cursor.getColumnIndex("address");
            int dateCol = cursor.getColumnIndex("date");
            int bodyCol = cursor.getColumnIndex("body");
            String dateStr = cursor.getString(dateCol);
            long s = Long.parseLong(dateStr);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        }
    }
}
