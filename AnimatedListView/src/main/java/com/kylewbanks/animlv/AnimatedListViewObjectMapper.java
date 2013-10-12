package com.kylewbanks.animlv;

import android.view.View;

/**
 * Called to populate a View with data from an Object.
 */
public interface AnimatedListViewObjectMapper {

    public void bindObjectToView(Object object, View view);

}
