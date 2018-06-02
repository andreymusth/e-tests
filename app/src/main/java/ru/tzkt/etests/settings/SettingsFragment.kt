package ru.tzkt.etests.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v14.preference.SwitchPreference
import android.support.v7.preference.EditTextPreference
import android.support.v7.preference.ListPreference
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import ru.tzkt.etests.R

/**
 * Created by andrey on 19/04/2018.
 */
class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.app_preferences)

        val sharedPreferences = preferenceScreen.sharedPreferences
        val prefScreen = preferenceScreen

        for (i in 0 until prefScreen.preferenceCount) {

            val p = prefScreen.getPreference(i)
            if (p !is SwitchPreference) {
                val value = sharedPreferences.getString(p.key, "")
                setPreferenceSummary(p, value)
            }
        }
    }


    private fun setPreferenceSummary(pref: Preference, value: String) {

        if (pref is ListPreference) {
            val prefIndex = pref.findIndexOfValue(value)
            if (prefIndex >= 0) {
                pref.summary = pref.entries[prefIndex]
            }
        }

        if (pref is EditTextPreference) {
            pref.setSummary(pref.text)
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {

        val p = findPreference(key)

        if (p != null) {
            if (p !is SwitchPreference) {

                val value = sharedPreferences?.getString(p.key, "") ?: ""
                setPreferenceSummary(p, value)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        preferenceManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

}