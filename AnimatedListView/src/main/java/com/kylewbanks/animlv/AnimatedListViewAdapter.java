package com.kylewbanks.animlv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by kylewbanks on 2013-10-11.
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