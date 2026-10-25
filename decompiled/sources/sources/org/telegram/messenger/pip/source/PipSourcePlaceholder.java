package org.telegram.messenger.pip.source;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

/* loaded from: classes5.dex */
class PipSourcePlaceholder {
    private Bitmap placeholder;
    private Drawable placeholderActivityDrawable;
    private final View placeholderActivityView;
    private Drawable placeholderSourceDrawable;
    private final View placeholderSourceView;

    public static class PlaceholderDrawable extends Drawable {
        private final Bitmap bitmap;
        private final Rect rect;

        private PlaceholderDrawable(Bitmap r2) {
            this.rect = new Rect();
            this.bitmap = r2;
        }

        public /* synthetic */ PlaceholderDrawable(Bitmap r1, PipSourcePlaceholderIA r2) {
            this(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r3) {
            if (this.bitmap.isRecycled() == false) goto L5;
            return;
        L5:
            r3.drawBitmap(this.bitmap, null, this.rect, null);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setBounds(int r5, int r6, int r7, int r8) {
            super.setBounds(r5, r6, r7, r8);
            Bitmap r0 = this.bitmap;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.isRecycled() == true) goto L10;
            int r72 = r7 - r5;
            int r82 = r8 - r6;
            float r02 = this.bitmap.getWidth();
            float r1 = this.bitmap.getHeight();
            float r2 = Math.min(r72 / r02, r82 / r1);
            int r03 = Math.round(r02 * r2);
            int r12 = Math.round(r1 * r2);
            int r52 = r5 + ((r72 - r03) / 2);
            int r62 = r6 + ((r82 - r12) / 2);
            this.rect.set(r52, r62, r03 + r52, r12 + r62);
            return;
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    public PipSourcePlaceholder(View r1, View r2) {
        this.placeholderActivityView = r1;
        this.placeholderSourceView = r2;
    }

    private void maybeClearPlaceholder() {
        if (this.placeholderSourceDrawable == null) goto L5;
        return;
    L5:
        if (this.placeholderActivityDrawable != null) goto L11;
        Bitmap r0 = this.placeholder;
        if (r0 == null) goto L12;
        r0.recycle();
        this.placeholder = null;
        return;
    L12:
        return;
    }

    public void clear() {
        stopPlaceholderForActivity();
        stopPlaceholderForSource();
    }

    public void setPlaceholder(Bitmap r3) {
        if (this.placeholder == r3) goto L10;
        clear();
        this.placeholder = r3;
        PlaceholderDrawable r32 = new PlaceholderDrawable(this.placeholder, null);
        this.placeholderActivityDrawable = r32;
        this.placeholderActivityView.setBackground(r32);
        if (this.placeholderSourceView == null) goto L9;
        PlaceholderDrawable r33 = new PlaceholderDrawable(this.placeholder, null);
        this.placeholderSourceDrawable = r33;
        this.placeholderSourceView.setBackground(r33);
        return;
    L9:
        return;
    }

    public void stopPlaceholderForActivity() {
        if (this.placeholderActivityDrawable == null) goto L5;
        this.placeholderActivityView.setBackground(null);
        this.placeholderActivityDrawable = null;
    L5:
        maybeClearPlaceholder();
    }

    public void stopPlaceholderForSource() {
        if (this.placeholderSourceDrawable == null) goto L7;
        this.placeholderSourceDrawable = null;
        View r1 = this.placeholderSourceView;
        if (r1 == null) goto L7;
        r1.setBackground(null);
    L7:
        maybeClearPlaceholder();
    }
}
