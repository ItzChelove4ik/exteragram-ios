package com.google.android.material.resources;

import android.graphics.Typeface;

/* loaded from: classes5.dex */
public abstract class TextAppearanceFontCallback {
    public TextAppearanceFontCallback() {
    }

    public abstract void onFontRetrievalFailed(int r1);

    public abstract void onFontRetrieved(Typeface r1, boolean r2);
}
