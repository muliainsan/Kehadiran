package com.project.insan.kehadiran.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import com.project.insan.kehadiran.R;
import com.project.insan.kehadiran.services.reminder.DailyAlarmReceiver;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();

    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            addPreferencesFromResource(R.xml.root_preferences);
            SwitchPreference switchDailyReminder = findPreference("dailyReminder");

            switchDailyReminder.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {


                    String key = preference.getKey();
                    boolean isOn = (boolean) preference.getPreferenceManager().getSharedPreferences().getBoolean("dailyReminder", true);

//                    Toast.makeText(getActivity(), String.valueOf(isOn) + preference.getKey(), Toast.LENGTH_SHORT).show();
                    DailyAlarmReceiver dailyAlarmReceiver = new DailyAlarmReceiver();

                    if (isOn) {
                        Toast.makeText(getActivity(), "alarm on", Toast.LENGTH_SHORT).show();
//                        dailyAlarmReceiver.showAlarmNotification(getActivity(),"asd","asd",101);
                        dailyAlarmReceiver.setRepeatingAlarm(getActivity());
                    } else {
                        Toast.makeText(getActivity(), "alarm off", Toast.LENGTH_SHORT).show();
                        dailyAlarmReceiver.cancelAlarm(getActivity());
                    }


                    return true;
                }
            });
        }


    }
}