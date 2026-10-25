package org.telegram.ui.Components;

import android.graphics.drawable.Drawable;

/* loaded from: classes3.dex */
public abstract class RecyclableDrawable extends Drawable {
    public RecyclableDrawable() {
    }

    public abstract void recycle();
}
