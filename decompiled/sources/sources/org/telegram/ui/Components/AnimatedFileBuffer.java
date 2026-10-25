package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import java.util.Arrays;

/* loaded from: classes7.dex */
public class AnimatedFileBuffer {
    public final Bitmap bitmap;
    public final int height;
    public boolean opaque;
    private final BitmapShader[] shader;
    public int time;
    public final int width;

    private AnimatedFileBuffer(Bitmap r2) {
        this.shader = new BitmapShader[3];
        this.bitmap = r2;
        this.width = r2.getWidth();
        this.height = r2.getHeight();
    }

    public static AnimatedFileBuffer of(int r2, int r3) {
        return new AnimatedFileBuffer(Bitmap.createBitmap(r2, r3, Bitmap.Config.ARGB_8888));
    }

    public static AnimatedFileBuffer of(Bitmap r1) {
        return new AnimatedFileBuffer(r1);
    }

    public BitmapShader getShader(int r5) {
        BitmapShader[] r0 = this.shader;
        if (r0[r5] != null) goto L6;
        Bitmap r2 = this.bitmap;
        Shader.TileMode r3 = Shader.TileMode.CLAMP;
        r0[r5] = new BitmapShader(r2, r3, r3);
    L6:
        return this.shader[r5];
    }

    public void recycle() {
        this.bitmap.recycle();
        Arrays.fill(this.shader, null);
    }
}
