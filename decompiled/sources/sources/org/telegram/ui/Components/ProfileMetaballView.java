package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import org.telegram.messenger.DispatchQueue;

/* loaded from: classes7.dex */
public abstract class ProfileMetaballView extends View {
    public static final DispatchQueue profileBlurQueue = null;

    public static class BlurBitmapHolder {
        Bitmap bitmap;
        Canvas canvas;
        boolean destroyed;
        boolean destroying;
        boolean hasContent;
        boolean isBusy;
        int key;

        public BlurBitmapHolder(int r2, int r3) {
            this.key = 0;
            this.bitmap = Bitmap.createBitmap(r2, r3, Bitmap.Config.ARGB_8888);
            this.canvas = new Canvas(this.bitmap);
        }

        public BlurBitmapHolder(BlurBitmapHolder r3) {
            this.key = 0;
            this.bitmap = Bitmap.createBitmap(r3.bitmap.getWidth(), r3.bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            this.canvas = new Canvas(this.bitmap);
        }

        public boolean canUse(int r3, int r4) {
            if (this.destroyed == false) goto L6;
            return false;
        L6:
            if (this.bitmap.getWidth() == r3) goto L8;
        L11:
            return false;
        L8:
            if (this.bitmap.getHeight() != r4) goto L11;
            return true;
        }

        public boolean canUse(BlurBitmapHolder r4) {
            if (this.destroyed == false) goto L6;
            return false;
        L6:
            if (this.bitmap.getWidth() == r4.bitmap.getWidth()) goto L8;
        L11:
            return false;
        L8:
            if (this.bitmap.getHeight() != r4.bitmap.getHeight()) goto L11;
            return true;
        }

        public void clear() {
            if (this.destroyed == false) goto L5;
            return;
        L5:
            this.hasContent = false;
            this.bitmap.eraseColor(0);
        }

        public void lock() {
            this.isBusy = true;
        }

        public void ready() {
            this.hasContent = true;
            this.key++;
        }

        public void recycle() {
            this.destroying = true;
            if (this.isBusy == true) goto L6;
            this.destroyed = true;
            this.bitmap.recycle();
            return;
        }

        public void unlock() {
            this.isBusy = false;
            if (this.destroyed == false) goto L5;
            return;
        L5:
            if (this.destroying == false) goto L9;
            this.destroyed = true;
            this.bitmap.recycle();
            return;
        }
    }

    static {
        profileBlurQueue = new DispatchQueue("profileBlurQueue");
    }
}
