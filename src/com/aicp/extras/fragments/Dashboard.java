/*
 * Copyright (C) 2017-2018 AICP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aicp.extras.fragments;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aicp.extras.BaseSettingsFragment;
import com.aicp.extras.Constants;
import com.aicp.extras.PreferenceMultiClickHandler;
import com.aicp.extras.R;
import com.aicp.extras.utils.Util;

public class Dashboard extends BaseSettingsFragment {

    private static final String PREF_AICP_OTA = "aicp_ota";
    private static final String PREF_LOG_IT = "log_it";
    private static final String PREF_WEATHER = "weather_option";

    private static final Intent INTENT_OTA = new Intent().setComponent(new ComponentName(
            Constants.AICP_OTA_PACKAGE, Constants.AICP_OTA_ACTIVITY));

    private Preference mAicpOTA;
    private Preference mWeatherOption;

    @Override
    protected int getPreferenceResource() {
        return R.xml.dashboard;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PackageManager pm = getActivity().getPackageManager();

        mWeatherOption = findPreference(PREF_WEATHER);
        if (!Util.isPackageInstalled(Constants.WEATHER_SERVICE_PACKAGE, pm)) {
            mWeatherOption.getParent().removePreference(mWeatherOption);
        }

        mAicpOTA = findPreference(PREF_AICP_OTA);
        if (!Util.isPackageEnabled(Constants.AICP_OTA_PACKAGE, pm)) {
            mAicpOTA.getParent().removePreference(mAicpOTA);
        }

        Preference logIt = findPreference(PREF_LOG_IT);
        Util.requireRoot(getActivity(), logIt);
    }
}
