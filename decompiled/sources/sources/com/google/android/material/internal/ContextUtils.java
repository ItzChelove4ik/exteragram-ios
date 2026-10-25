package com.google.android.material.internal;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* loaded from: classes5.dex */
public class ContextUtils {
    public ContextUtils() {
    }

    public static Activity getActivity(Context r1) {
    L3:
        if ((r1 instanceof ContextWrapper) == false) goto L9;
        if ((r1 instanceof Activity) == true) goto L7;
        r1 = ((ContextWrapper) r1).getBaseContext();
        goto L3
    L7:
        return (Activity) r1;
    L9:
        return null;
    }
}
