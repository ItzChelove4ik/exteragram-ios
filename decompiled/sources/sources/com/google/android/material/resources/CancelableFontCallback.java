package com.google.android.material.resources;

import android.graphics.Typeface;

/* loaded from: classes5.dex */
public final class CancelableFontCallback extends TextAppearanceFontCallback {
    private final ApplyFont applyFont;
    private boolean cancelled;
    private final Typeface fallbackFont;

    public interface ApplyFont {
        void apply(Typeface r1);
    }

    public CancelableFontCallback(ApplyFont r1, Typeface r2) {
        this.fallbackFont = r2;
        this.applyFont = r1;
    }

    private void updateIfNotCancelled(Typeface r2) {
        if (this.cancelled == true) goto L6;
        this.applyFont.apply(r2);
        return;
    }

    public void cancel() {
        this.cancelled = true;
    }

    @Override // com.google.android.material.resources.TextAppearanceFontCallback
    public void onFontRetrievalFailed(int r1) {
        updateIfNotCancelled(this.fallbackFont);
    }

    @Override // com.google.android.material.resources.TextAppearanceFontCallback
    public void onFontRetrieved(Typeface r1, boolean r2) {
        updateIfNotCancelled(r1);
    }
}
