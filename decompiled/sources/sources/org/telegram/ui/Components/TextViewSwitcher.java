package org.telegram.ui.Components;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes7.dex */
public class TextViewSwitcher extends ViewSwitcher {
    public TextViewSwitcher(Context r1) {
        super(r1);
    }

    @Override // android.widget.ViewSwitcher, android.widget.ViewAnimator, android.view.ViewGroup
    public void addView(View r2, int r3, ViewGroup.LayoutParams r4) {
        if ((r2 instanceof TextView) == false) goto L6;
        super.addView(r2, r3, r4);
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
    }

    @Override // android.widget.ViewAnimator
    public /* bridge */ /* synthetic */ View getCurrentView() {
        return getCurrentView();
    }

    @Override // android.widget.ViewAnimator
    public TextView getCurrentView() {
        return (TextView) super.getCurrentView();
    }

    @Override // android.widget.ViewSwitcher
    public /* bridge */ /* synthetic */ View getNextView() {
        return getNextView();
    }

    @Override // android.widget.ViewSwitcher
    public TextView getNextView() {
        return (TextView) super.getNextView();
    }

    public void invalidateViews() {
        getCurrentView().invalidate();
        getNextView().invalidate();
    }

    public void setText(CharSequence r2) {
        setText(r2, true);
    }

    public void setText(CharSequence r2, boolean r3) {
        setText(r2, r3, false);
    }

    public boolean setText(CharSequence r1, boolean r2, boolean r3) {
        if (r3 == false) goto L4;
    L5:
        if (r2 == false) goto L8;
        getNextView().setText(r1);
        showNext();
        return true;
    L8:
        getCurrentView().setText(r1);
        return false;
    L4:
        if (TextUtils.equals(r1, getCurrentView().getText()) == false) goto L5;
        return false;
    }
}
