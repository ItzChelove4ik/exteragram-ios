package org.telegram.ui.Components;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.SizeNotifierFrameLayout;

/* loaded from: classes7.dex */
public abstract class SizeNotifierFrameLayoutPhoto extends SizeNotifierFrameLayout {
    private Activity activity;
    private int keyboardHeight;
    private Rect rect;
    private boolean useSmoothKeyboard;
    private boolean withoutWindow;

    public static /* synthetic */ void $r8$lambda$PK0LNRjtzZL7xBqBKUrmwu6Env8(SizeNotifierFrameLayoutPhoto r0, boolean r1) {
        r0.lambda$notifyHeightChanged$0(r1);
    }

    public SizeNotifierFrameLayoutPhoto(Context r1, Activity r2, boolean r3) {
        super(r1);
        this.rect = new Rect();
        setActivity(r2);
        this.useSmoothKeyboard = r3;
    }

    private /* synthetic */ void lambda$notifyHeightChanged$0(boolean r4) {
        SizeNotifierFrameLayout.SizeNotifierFrameLayoutDelegate r0 = this.delegate;
        if (r0 == null) goto L5;
        r0.onSizeChanged(this.keyboardHeight, r4);
    L5:
        int r02 = 0;
    L7:
        if (r02 >= this.delegates.size()) goto L9;
        this.delegates.get(r02).onSizeChanged(this.keyboardHeight, r4);
        r02 = r02 + 1;
        goto L7
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
    public int getKeyboardHeight() {
        return this.keyboardHeight;
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
    public int measureKeyboardHeight() {
        View r0 = getRootView();
        getWindowVisibleDisplayFrame(this.rect);
        int r2 = 0;
        if (this.withoutWindow == false) goto L9;
        int r1 = r0.getHeight();
        if (this.rect.top == 0) goto L7;
        r2 = AndroidUtilities.statusBarHeight;
    L7:
        int r12 = (r1 - r2) - AndroidUtilities.getViewInset(r0);
        Rect r4 = this.rect;
        return r12 - (r4.bottom - r4.top);
    L9:
        int r42 = (this.activity.getWindow().getDecorView().getHeight() - AndroidUtilities.getViewInset(r0)) - r0.getBottom();
        if (r42 > Math.max(AndroidUtilities.dp(10.0f), AndroidUtilities.statusBarHeight)) goto L12;
        return 0;
    L12:
        return r42;
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
    public void notifyHeightChanged() {
        if (this.delegate == null) goto L5;
    L8:
        this.keyboardHeight = measureKeyboardHeight();
        Point r0 = AndroidUtilities.displaySize;
        if (r0.x <= r0.y) goto L11;
        boolean r02 = true;
    L12:
        post(new SizeNotifierFrameLayoutPhoto$$ExternalSyntheticLambda0(this, r02));
        return;
    L11:
        r02 = false;
        goto L12
    L5:
        if (this.delegates.isEmpty() == false) goto L8;
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        notifyHeightChanged();
    }

    public void setActivity(Activity r1) {
        this.activity = r1;
    }

    public void setWithoutWindow(boolean r1) {
        this.withoutWindow = r1;
    }
}
