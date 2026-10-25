package org.telegram.ui.Stories.recorder;

import android.graphics.Bitmap;
import android.graphics.Color;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public abstract class DominantColors {
    private static float[] tempHsv;

    private static int adapt(int r4, boolean r5) {
        if (tempHsv != null) goto L5;
        tempHsv = new float[3];
    L5:
        Color.colorToHSV(r4, tempHsv);
        float[] r42 = tempHsv;
        float r1 = r42[2];
        if (r5 == false) goto L8;
        float r52 = -0.05f;
    L9:
        r42[2] = Utilities.clamp(r1 + r52, 0.85f, 0.15f);
        float[] r43 = tempHsv;
        float r0 = r43[1];
        if (r0 <= 0.1f) goto L20;
        if (r0 > 0.95f) goto L20;
        if (r0 > 0.5f) goto L17;
        r43[1] = Utilities.clamp(r0 + 0.2f, 1.0f, 0.0f);
        goto L20
    L17:
        if (r0 <= 0.8f) goto L20;
        r43[1] = Utilities.clamp(r0 - 0.4f, 1.0f, 0.0f);
    L20:
        return Color.HSVToColor(tempHsv);
    L8:
        r52 = 0.07f;
        goto L9
    }

    public static void getColors(boolean r0, Bitmap r1, boolean r2, Utilities.Callback<int[]> r3) {
        if (r3 != null) goto L5;
        return;
    L5:
        if (r1 != null) goto L8;
        r3.run(new int[]{0, 0});
        return;
    L8:
        r3.run(getColorsSync(false, r1, r2));
    }

    public static int[] getColorsSync(boolean r3, Bitmap r4, boolean r5) {
        return new int[]{adapt(r4.getPixel(r4.getWidth() / 2, (int) (r4.getHeight() * 0.1f)), r5), adapt(r4.getPixel(r4.getWidth() / 2, (int) (r4.getHeight() * 0.9f)), r5)};
    }
}
