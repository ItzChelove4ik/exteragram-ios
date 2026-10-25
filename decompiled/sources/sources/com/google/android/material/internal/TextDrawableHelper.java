package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.resources.TextAppearanceFontCallback;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class TextDrawableHelper {
    private WeakReference<TextDrawableDelegate> delegate;
    private final TextAppearanceFontCallback fontCallback;
    private TextAppearance textAppearance;
    private float textHeight;
    private final TextPaint textPaint;
    private boolean textSizeDirty;
    private float textWidth;

    /* renamed from: com.google.android.material.internal.TextDrawableHelper$1, reason: invalid class name */
    public class AnonymousClass1 extends TextAppearanceFontCallback {
        final /* synthetic */ TextDrawableHelper this$0;

        public AnonymousClass1(TextDrawableHelper r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.material.resources.TextAppearanceFontCallback
        public void onFontRetrievalFailed(int r2) {
            TextDrawableHelper.access$002(this.this$0, true);
            TextDrawableDelegate r1 = (TextDrawableDelegate) TextDrawableHelper.access$100(this.this$0).get();
            if (r1 == null) goto L6;
            r1.onTextSizeChange();
            return;
        }

        @Override // com.google.android.material.resources.TextAppearanceFontCallback
        public void onFontRetrieved(Typeface r1, boolean r2) {
            if (r2 == true) goto L9;
            TextDrawableHelper.access$002(this.this$0, true);
            TextDrawableDelegate r0 = (TextDrawableDelegate) TextDrawableHelper.access$100(this.this$0).get();
            if (r0 == null) goto L8;
            r0.onTextSizeChange();
            return;
        L8:
            return;
        }
    }

    public interface TextDrawableDelegate {
        int[] getState();

        boolean onStateChange(int[] r1);

        void onTextSizeChange();
    }

    public TextDrawableHelper(TextDrawableDelegate r3) {
        this.textPaint = new TextPaint(1);
        this.fontCallback = new AnonymousClass1(this);
        this.textSizeDirty = true;
        this.delegate = new WeakReference(null);
        setDelegate(r3);
    }

    public static /* synthetic */ boolean access$002(TextDrawableHelper r0, boolean r1) {
        r0.textSizeDirty = r1;
        return r1;
    }

    public static /* synthetic */ WeakReference access$100(TextDrawableHelper r0) {
        return r0.delegate;
    }

    private float calculateTextHeight(String r1) {
        if (r1 != null) goto L6;
        return 0.0f;
    L6:
        return Math.abs(this.textPaint.getFontMetrics().ascent);
    }

    private float calculateTextWidth(CharSequence r3) {
        if (r3 != null) goto L6;
        return 0.0f;
    L6:
        return this.textPaint.measureText(r3, 0, r3.length());
    }

    private void refreshTextDimens(String r2) {
        this.textWidth = calculateTextWidth(r2);
        this.textHeight = calculateTextHeight(r2);
        this.textSizeDirty = false;
    }

    public TextAppearance getTextAppearance() {
        return this.textAppearance;
    }

    public float getTextHeight(String r2) {
        if (this.textSizeDirty == false) goto L5;
        refreshTextDimens(r2);
        return this.textHeight;
    L5:
        return this.textHeight;
    }

    public TextPaint getTextPaint() {
        return this.textPaint;
    }

    public float getTextWidth(String r2) {
        if (this.textSizeDirty == false) goto L5;
        refreshTextDimens(r2);
        return this.textWidth;
    L5:
        return this.textWidth;
    }

    public boolean isTextWidthDirty() {
        return this.textSizeDirty;
    }

    public void setDelegate(TextDrawableDelegate r2) {
        this.delegate = new WeakReference(r2);
    }

    public void setTextAppearance(TextAppearance r3, Context r4) {
        if (this.textAppearance == r3) goto L14;
        this.textAppearance = r3;
        if (r3 == null) goto L10;
        r3.updateMeasureState(r4, this.textPaint, this.fontCallback);
        TextDrawableDelegate r0 = this.delegate.get();
        if (r0 == null) goto L9;
        TextPaint r1 = this.textPaint;
        r1.drawableState = r0.getState();
    L9:
        r3.updateDrawState(r4, this.textPaint, this.fontCallback);
        this.textSizeDirty = true;
    L10:
        TextDrawableDelegate r2 = this.delegate.get();
        if (r2 == null) goto L15;
        r2.onTextSizeChange();
        r2.onStateChange(r2.getState());
        return;
    L15:
        return;
    }

    public void setTextSizeDirty(boolean r1) {
        this.textSizeDirty = r1;
    }

    public void setTextWidthDirty(boolean r1) {
        this.textSizeDirty = r1;
    }

    public void updateTextPaintDrawState(Context r3) {
        this.textAppearance.updateDrawState(r3, this.textPaint, this.fontCallback);
    }
}
