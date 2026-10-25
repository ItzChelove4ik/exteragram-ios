package org.telegram.ui.Components;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes7.dex */
public abstract class SlideView extends LinearLayout {
    public SlideView(Context r1) {
        super(r1);
    }

    public String getHeaderName() {
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public boolean hasCustomKeyboard() {
        return false;
    }

    public boolean needBackButton() {
        return false;
    }

    public boolean onBackPressed(boolean r1) {
        return true;
    }

    public void onCancelPressed() {
    }

    public void onDestroyActivity() {
    }

    public void onHide() {
    }

    public void onNextPressed(String r1) {
    }

    public void onResume() {
    }

    public void onShow() {
    }

    public void restoreStateParams(Bundle r1) {
    }

    public void saveStateParams(Bundle r1) {
    }

    public void setParams(Bundle r1, boolean r2) {
    }

    public void updateColors() {
    }
}
