package org.telegram.messenger;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/* loaded from: classes.dex */
public class ExtendedBitmapDrawable extends BitmapDrawable {
    private int invert;
    private int orientation;

    public ExtendedBitmapDrawable(Bitmap r1, int r2, int r3) {
        super(r1);
        this.invert = r3;
        this.orientation = r2;
    }

    public int getInvert() {
        return this.invert;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public boolean invertHorizontally() {
        if ((this.invert & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public boolean invertVertically() {
        if ((this.invert & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }
}
