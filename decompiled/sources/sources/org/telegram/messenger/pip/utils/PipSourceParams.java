package org.telegram.messenger.pip.utils;

import android.app.PictureInPictureParams;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.Rational;
import org.telegram.messenger.AndroidUtilities$$ExternalSyntheticApiModelOutline0;

/* loaded from: classes5.dex */
public class PipSourceParams {
    private final Rect position;
    private final Point ratio;

    public PipSourceParams() {
        this.position = new Rect();
        this.ratio = new Point();
    }

    public PictureInPictureParams.Builder build() {
        PictureInPictureParams.Builder r0 = AndroidUtilities$$ExternalSyntheticApiModelOutline0.m();
        Point r1 = this.ratio;
        int r2 = r1.x;
        if (r2 <= 0) goto L16;
        int r12 = r1.y;
        if (r12 <= 0) goto L16;
        double r13 = r2 / r12;
        if (r13 >= 0.45d) goto L10;
        Rational r14 = new Rational(45, 100);
    L13:
        r0.setAspectRatio(r14);
        if (Build.VERSION.SDK_INT < 33) goto L20;
        r0.setExpandedAspectRatio(r14);
    L20:
        if (this.position.isEmpty() == true) goto L23;
        r0.setSourceRectHint(this.position);
        return r0;
    L23:
        r0.setSourceRectHint(null);
        return r0;
    L10:
        if (r13 <= 2.35d) goto L12;
        r14 = new Rational(235, 100);
        goto L13
    L12:
        Point r22 = this.ratio;
        r14 = new Rational(r22.x, r22.y);
    L16:
        r0.setAspectRatio(null);
        if (Build.VERSION.SDK_INT < 33) goto L20;
        r0.setExpandedAspectRatio(null);
        goto L20
    }

    public int getHeight() {
        return this.position.height();
    }

    public void getPosition(Rect r1) {
        r1.set(this.position);
    }

    public int getWidth() {
        return this.position.width();
    }

    public boolean isValid() {
        if (this.position.isEmpty() == true) goto L10;
        Point r1 = this.ratio;
        if (r1.x > 0) goto L7;
        return false;
    L7:
        if (r1.y <= 0) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public boolean setPosition(Rect r2) {
        if (this.position.equals(r2) == true) goto L6;
        this.position.set(r2);
        return true;
    L6:
        return false;
    }

    public boolean setRatio(int r2, int r3) {
        Point r1 = this.ratio;
        if (r1.x == r2) goto L5;
    L9:
        r1.set(r2, r3);
        return true;
    L5:
        if (r1.y != r3) goto L9;
        return false;
    }
}
