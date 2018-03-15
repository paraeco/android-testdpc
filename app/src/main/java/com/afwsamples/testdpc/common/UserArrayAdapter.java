/*
 * Copyright (C) 2017 The Android Open Source Project
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

package com.afwsamples.testdpc.common;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.UserHandle;
import android.os.UserManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.afwsamples.testdpc.R;

import java.util.List;

/**
 * A simple adapter which takes a list of user serial numbers in a listview.
 */
@TargetApi(Build.VERSION_CODES.M)
public class UserArrayAdapter extends ArrayAdapter<UserHandle> {
    private static final String TAG = "UserArrayAdapter";

    private UserManager mUserManager;
    private Resources mResources;

    public UserArrayAdapter(Context context, int resource, List<UserHandle> userHandleList) {
        super(context, resource, userHandleList);
        mUserManager = context.getSystemService(UserManager.class);
        mResources = context.getResources();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_row, parent,
                    false);
        }

        final TextView userNameTextView = convertView.findViewById(R.id.user_name);
        userNameTextView.setText(
                mResources.getString(
                        R.string.user_string,
                        mUserManager.getSerialNumberForUser(getItem(position))));
        return convertView;
    }
}
