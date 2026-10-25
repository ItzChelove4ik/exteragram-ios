package org.telegram.ui.Components.blur3.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.NinePatchDrawable;
import androidx.core.math.MathUtils;
import c.f$$ExternalSyntheticBUOutline1;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.telegram.messenger.ApplicationLoader;

/* loaded from: classes7.dex */
public abstract class NinePatchBuilder {

    public interface NinePathRenderer {
        void draw(Canvas r1, RectF r2, float[] r3);
    }

    public static /* synthetic */ void $r8$lambda$86sFOu1PZqKXvzaMlCbF6VL6Vy8(int r2, float r3, float r4, float r5, int r6, Canvas r7, RectF r8, float[] r9) {
        Path r0 = new Path();
        r0.addRoundRect(r8, r9, Path.Direction.CW);
        Paint r82 = new Paint(1);
        r82.setStyle(Paint.Style.FILL);
        r82.setColor(r2);
        if (r3 <= 0.0f) goto L5;
        r82.setShadowLayer(r3, r4, r5, r6);
    L5:
        r7.drawPath(r0, r82);
        if (r3 <= 0.0f) goto L9;
        r82.clearShadowLayer();
        r7.drawPath(r0, r82);
        return;
    }

    public static NinePatchDrawable createNinePatch(Bitmap r10, Rect r11, int r12, int r13) {
        if (r10 != null) goto L5;
        f$$ExternalSyntheticBUOutline1.m("bitmap == null");
        return null;
    L5:
        if (r10.isRecycled() == true) goto L16;
        if (r12 < 0) goto L15;
        if (r12 >= r10.getWidth()) goto L15;
        if (r13 < 0) goto L15;
        if (r13 >= r10.getHeight()) goto L15;
        return new NinePatchDrawable(ApplicationLoader.applicationContext.getResources(), r10, createNinePatchChunk(r12, r12 + 1, r13, r13 + 1, r11.left, r11.top, r11.right, r11.bottom, r10.getPixel(r12, r13)).array(), r11, null);
    L15:
        throw new IllegalArgumentException("center pixel is outside bitmap: (" + r12 + ", " + r13 + ") for " + r10.getWidth() + "x" + r10.getHeight());
    L16:
        f$$ExternalSyntheticBUOutline1.m("bitmap is recycled");
        return null;
    }

    public static NinePatchDrawable createNinePatch(Bitmap[] r6, int r7, float[] r8, float r9, int r10, float r11, float r12, int r13) {
        return createNinePatch(r6, r8, r9, r11, r12, r13, new NinePatchBuilder$$ExternalSyntheticLambda0(r7, r9, r11, r12, r10));
    }

    public static NinePatchDrawable createNinePatch(Bitmap[] r34, float[] r35, float r36, float r37, float r38, int r39, NinePathRenderer r40) {
        if (r35 != null) goto L5;
    L34:
        f$$ExternalSyntheticBUOutline1.m("radii must have 8 values: TLx,TLy, TRx,TRy, BRx,BRy, BLx,BLy");
        return null;
    L5:
        if (r35.length != 8) goto L34;
        float r7 = Math.max(0.0f, r35[0]);
        float r10 = Math.max(0.0f, r35[1]);
        float r12 = Math.max(0.0f, r35[2]);
        float r14 = Math.max(0.0f, r35[3]);
        float r4 = Math.max(0.0f, r35[4]);
        float r11 = Math.max(0.0f, r35[5]);
        float r13 = Math.max(0.0f, r35[6]);
        float r1 = Math.max(0.0f, r35[7]);
        int r6 = (int) Math.ceil(r36 * 2.0f);
        int r62 = r6 + ((int) Math.ceil(Math.max(0.0f, -r37)));
        int r31 = r6 + ((int) Math.ceil(Math.max(0.0f, r37)));
        int r2 = r6 + ((int) Math.ceil(Math.max(0.0f, -r38)));
        int r32 = r6 + ((int) Math.ceil(Math.max(0.0f, r38)));
        float r3 = Math.max(r7 + r12, r13 + r4);
        float r8 = Math.max(r10 + r1, r14 + r11);
        int r33 = ((int) Math.ceil(r3 + 2.0f)) + r62;
        int r9 = r33 + r31;
        int r82 = ((int) Math.ceil(r8 + 2.0f)) + r2;
        int r102 = r82 + r32;
        if (r34 == null) goto L11;
        int r362 = 0;
        if (r34.length != 1) goto L12;
        int r22 = 1;
    L13:
        if (r22 == 0) goto L27;
        Bitmap r15 = r34[r362];
        if (r15 == null) goto L27;
        if (r15.isRecycled() == true) goto L27;
        if (r15.isMutable() == false) goto L27;
        if (r15.getWidth() != r9) goto L27;
        if (r15.getHeight() != r102) goto L27;
        if (r15.getConfig() != Bitmap.Config.ARGB_8888) goto L27;
        r15.eraseColor(r362);
        Bitmap r0 = r15;
    L28:
        if (r0 != null) goto L30;
        r0 = Bitmap.createBitmap(r9, r102, Bitmap.Config.ARGB_8888);
    L30:
        if (r22 == 0) goto L32;
        r34[0] = r0;
    L32:
        Bitmap r372 = r0;
        r40.draw(new Canvas(r0), new RectF(r62, r2, r33, r82), new float[]{r7, r10, r12, r14, r4, r11, r13, r1});
        float r02 = Math.max(r7, r13);
        float r310 = Math.max(r12, r4);
        float r42 = Math.max(r10, r14);
        float r16 = Math.max(r1, r11);
        int r25 = MathUtils.clamp(((int) Math.ceil(r02)) + r62, 1, r9 - 2);
        int r26 = MathUtils.clamp((r9 - r31) - ((int) Math.ceil(r310)), r25 + 1, r9 - 1);
        int r27 = MathUtils.clamp(((int) Math.ceil(r42)) + r2, 1, r102 - 2);
        return new NinePatchDrawable(ApplicationLoader.applicationContext.getResources(), r372, createNinePatchChunk(r25, r26, r27, MathUtils.clamp((r102 - r32) - ((int) Math.ceil(r16)), r27 + 1, r102 - 1), r62, r2, r31, r32, r39).array(), new Rect(r62, r2, r31, r32), null);
    L27:
        r0 = null;
    L12:
        r22 = r362;
        goto L13
    L11:
        r362 = 0;
        goto L12
    }

    public static ByteBuffer createNinePatchChunk(int r3, int r4, int r5, int r6, int r7, int r8, int r9, int r10, int r11) {
        ByteBuffer r0 = ByteBuffer.allocate(84).order(ByteOrder.nativeOrder());
        r0.put((byte) 1);
        r0.put((byte) 2);
        r0.put((byte) 2);
        r0.put((byte) 9);
        r0.putInt(0);
        r0.putInt(0);
        r0.putInt(r7);
        r0.putInt(r9);
        r0.putInt(r8);
        r0.putInt(r10);
        r0.putInt(0);
        r0.putInt(r3);
        r0.putInt(r4);
        r0.putInt(r5);
        r0.putInt(r6);
        r0.putInt(1);
        r0.putInt(1);
        r0.putInt(1);
        r0.putInt(1);
        r0.putInt(r11);
        r0.putInt(1);
        r0.putInt(1);
        r0.putInt(1);
        r0.putInt(1);
        return r0;
    }
}
