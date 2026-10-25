package com.google.android.material.listitem;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes5.dex */
public interface RevealableListItem {
    public static final int PRIMARY_ACTION_SWIPE_DIRECT = 2;
    public static final int PRIMARY_ACTION_SWIPE_DISABLED = 0;
    public static final int PRIMARY_ACTION_SWIPE_INDIRECT = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PrimaryActionSwipeMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RevealGravity {
    }

    int getIntrinsicWidth();

    int getPrimaryActionSwipeMode();

    void setPrimaryActionSwipeMode(int r1);

    void setRevealedWidth(int r1);
}
