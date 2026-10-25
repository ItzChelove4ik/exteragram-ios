package org.telegram.messenger.pip;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes.dex */
class PipActivityContentLayout extends FrameLayout {
    private final Activity activity;
    private boolean isViewInPip;
    private int originalHeight;
    private int originalWidth;

    public PipActivityContentLayout(Activity r1) {
        super(r1);
        this.activity = r1;
    }

    public boolean isViewInPip() {
        return this.isViewInPip;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        int r22 = View.MeasureSpec.getSize(r2);
        int r32 = View.MeasureSpec.getSize(r3);
        boolean r0 = AndroidUtilities.isInPictureInPictureMode(this.activity);
        if (r0 == true) goto L5;
        this.originalWidth = r22;
        this.originalHeight = r32;
    L5:
        if (r0 == true) goto L7;
    L11:
        boolean r02 = false;
    L12:
        this.isViewInPip = r02;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(r22, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r32, TLObject.FLAG_30));
        return;
    L7:
        if (r22 >= this.originalWidth) goto L11;
        if (r32 >= this.originalHeight) goto L11;
        r02 = true;
        goto L12
    }
}
