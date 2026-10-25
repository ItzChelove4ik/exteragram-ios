package com.google.android.material.sidesheet;

import android.view.View;

/* loaded from: classes5.dex */
public abstract class SideSheetCallback implements SheetCallback {
    public SideSheetCallback() {
    }

    public void onLayout(View r1) {
    }

    @Override // com.google.android.material.sidesheet.SheetCallback
    public abstract void onSlide(View r1, float r2);

    @Override // com.google.android.material.sidesheet.SheetCallback
    public abstract void onStateChanged(View r1, int r2);
}
