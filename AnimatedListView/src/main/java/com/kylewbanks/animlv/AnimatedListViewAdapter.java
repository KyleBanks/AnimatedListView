/*
 * Copyright (c) 2013 Kyle W. Banks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kylewbanks.animlv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Manages a List of objects to display in the AnimatedListView. When a new view needs to be displayed, it will
 * request that the AnimatedListViewObjectMapper populate the view, and then animated it.
 */
public class AnimatedListViewAdapter extends ArrayAdapter {

    private LayoutInflater _inflater;

    private int _resourceId;
    private List _objects;
    private AnimatedListViewObjectMapper _objectMapper;

    private int lastPosition = -1;

    public AnimatedListViewAdapter(Context context, int resourceId, List objects, AnimatedListViewObjectMapper objectMapper) {
        super(context, resourceId, objects);

        this._inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this._resourceId = resourceId;
        this._objects = objects;
        this._objectMapper = objectMapper;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position > _objects.size()) {
            return null;
        }

        if (convertView == null) {
            convertView = _inflater.inflate(_resourceId, parent, false);
        }

        Object object = _objects.get(position);
        _objectMapper.bindObjectToView(object, convertView);

        if(convertView != null) {
            Animation animation = new TranslateAnimation(0, 0, (position > lastPosition) ? 100 : -100, 0);
            animation.setDuration(400);
            convertView.startAnimation(animation);
        }
        lastPosition = position;

        return convertView;
    }
}