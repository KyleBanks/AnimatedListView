package com.kylewbanks.animlv;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by kylewbanks on 2013-10-11.
 */
public class AnimatedListView extends ListView {

    public AnimatedListView(Context context) {
        this(context, null);
    }

    public AnimatedListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimatedListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        if (adapter instanceof AnimatedListViewAdapter) {
            super.setAdapter(adapter);
        } else {
            throw new UnsupportedOperationException("AnimatedListView requires a ListAdapter of type AnimatedListViewAdapter");
        }
    }
}
