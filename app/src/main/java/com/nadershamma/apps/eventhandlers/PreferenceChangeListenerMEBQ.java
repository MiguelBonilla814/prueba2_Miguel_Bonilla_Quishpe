package com.nadershamma.apps.eventhandlers;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.widget.Toast;

import com.nadershamma.apps.androidfunwithflags.MainActivityMEBQ;
import com.nadershamma.apps.androidfunwithflags.R;

import java.util.Set;

public class PreferenceChangeListenerMEBQ implements OnSharedPreferenceChangeListener {
    private MainActivityMEBQ mainActivityMEBQ;

    public PreferenceChangeListenerMEBQ(MainActivityMEBQ mainActivityMEBQ) {
        this.mainActivityMEBQ = mainActivityMEBQ;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        this.mainActivityMEBQ.setPreferencesChanged(true);

        if (key.equals(this.mainActivityMEBQ.getREGIONS())) {
            this.mainActivityMEBQ.getQuizViewModel().setGuessRows(sharedPreferences.getString(
                    MainActivityMEBQ.CHOICES, null));
            this.mainActivityMEBQ.getQuizFragment().resetQuiz();
        } else if (key.equals(this.mainActivityMEBQ.getCHOICES())) {
            Set<String> regions = sharedPreferences.getStringSet(this.mainActivityMEBQ.getREGIONS(),
                    null);
            if (regions != null && regions.size() > 0) {
                this.mainActivityMEBQ.getQuizViewModel().setRegionsSet(regions);
                this.mainActivityMEBQ.getQuizFragment().resetQuiz();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                regions.add(this.mainActivityMEBQ.getString(R.string.default_region));
                editor.putStringSet(this.mainActivityMEBQ.getREGIONS(), regions);
                editor.apply();
                Toast.makeText(this.mainActivityMEBQ, R.string.default_region_message,
                        Toast.LENGTH_LONG).show();
            }
        }

        Toast.makeText(this.mainActivityMEBQ, R.string.restarting_quiz,
                Toast.LENGTH_SHORT).show();
    }
}
