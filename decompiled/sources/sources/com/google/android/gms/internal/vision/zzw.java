package com.google.android.gms.internal.vision;

import android.graphics.Bitmap;
import android.graphics.Color;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public abstract class zzw {
    public static ByteBuffer zza(Bitmap r11, boolean r12) {
        int r122 = r11.getWidth();
        int r0 = r11.getHeight();
        int r1 = r122 * r0;
        ByteBuffer r02 = ByteBuffer.allocateDirect(((((r122 + 1) / 2) * ((r0 + 1) / 2)) << 1) + r1);
        int r2 = 0;
        int r3 = r1;
    L3:
        if (r2 >= r1) goto L10;
        int r4 = r2 % r122;
        int r5 = r2 / r122;
        int r6 = r11.getPixel(r4, r5);
        int r7 = Color.red(r6);
        float r72 = r7;
        float r8 = Color.green(r6);
        float r62 = Color.blue(r6);
        r02.put(r2, (byte) (((0.299f * r72) + (0.587f * r8)) + (0.114f * r62)));
        if ((r5 % 2) != 0) goto L9;
        if ((r4 % 2) != 0) goto L9;
        int r52 = r3 + 1;
        r02.put(r3, (byte) (((((-0.169f) * r72) + ((-0.331f) * r8)) + (r62 * 0.5f)) + 128.0f));
        r3 = r3 + 2;
        r02.put(r52, (byte) ((((r72 * 0.5f) + (r8 * (-0.419f))) + (r62 * (-0.081f))) + 128.0f));
    L9:
        r2 = r2 + 1;
        goto L3
    L10:
        return r02;
    }
}
