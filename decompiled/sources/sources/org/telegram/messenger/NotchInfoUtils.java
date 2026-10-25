package org.telegram.messenger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.DisplayMetrics;
import androidx.core.graphics.PathParser;

/* loaded from: classes5.dex */
public class NotchInfoUtils {
    private static final String BOTTOM_MARKER = "@bottom";
    private static final String DP_MARKER = "@dp";
    private static final String LEFT_MARKER = "@left";
    private static final String RIGHT_MARKER = "@right";

    public static final class NotchInfo {
        public RectF bounds;
        public int gravity;
        public boolean isAccurate;
        public boolean isLikelyCircle;
        public Path path;
        public String rawPath;

        public NotchInfo() {
        }
    }

    public NotchInfoUtils() {
    }

    @SuppressLint({"DiscouragedApi"})
    public static NotchInfo getInfo(Context r15) {
        if (Build.VERSION.SDK_INT >= 28) goto L5;
        return null;
    L5:
        NotchInfo r0 = new NotchInfo();
        int r1 = r15.getResources().getIdentifier("config_mainBuiltInDisplayCutout", "string", "android");
        if (r1 == 0) goto L61;
        String r12 = r15.getString(r1);
        if (r12.isEmpty() == false) goto L10;
        return null;
    L10:
        String r13 = r12.trim();
        DisplayMetrics r3 = r15.getResources().getDisplayMetrics();
        int r4 = r3.widthPixels;
        float r32 = r3.density;
        int r8 = 3;
        int r9 = 5;
        boolean r11 = false;
        if (r13.endsWith(RIGHT_MARKER) == false) goto L14;
        float r42 = r4;
        r13 = r13.substring(0, r13.length() - 6).trim();
        int r5 = 5;
    L17:
        boolean r122 = r13.endsWith(DP_MARKER);
        if (r122 == false) goto L21;
        r13 = r13.substring(0, r13.length() - 3);
    L21:
        if (r13.contains(BOTTOM_MARKER) == false) goto L62;
        r13 = r13.split(BOTTOM_MARKER, 2)[0].trim();
    L62:
        PathParser.PathDataNode[] r132 = PathParser.createNodesFromPathData(r13);     // Catch: Throwable -> L59
        Path r14 = new Path();     // Catch: Throwable -> L59
        PathParser.PathDataNode.nodesToPath(r132, r14);     // Catch: Throwable -> L59
        Matrix r2 = new Matrix();
        if (r122 == false) goto L27;
        r2.postScale(r32, r32);
    L27:
        r2.postTranslate(r42, 0.0f);
        r14.transform(r2);
        r0.path = r14;
        RectF r22 = new RectF();
        r14.computeBounds(r22, true);
        r0.bounds = r22;
        DisplayMetrics r152 = r15.getResources().getDisplayMetrics();
        if (r5 == 17) goto L33;
        if (Math.abs(r22.centerX() - (r152.widthPixels / 2.0f)) > AndroidUtilities.dp(2.0f)) goto L33;
        r5 = 17;
    L33:
        if (r5 == 17) goto L35;
    L37:
        r8 = r5;
    L38:
        if (r8 == 17) goto L40;
    L42:
        r9 = r8;
    L43:
        r0.gravity = r9;
        r0.rawPath = r13;
        if (r13.contains("C") == false) goto L46;
    L51:
        boolean r153 = true;
    L52:
        r0.isAccurate = r153;
        if (r22.width() > AndroidUtilities.dp(32.0f)) goto L55;
    L56:
        r11 = true;
    L57:
        r0.isLikelyCircle = r11;
        return r0;
    L55:
        if (r22.width() > r22.height()) goto L57;
    L46:
        if (r13.contains("S") == true) goto L51;
        if (r13.contains("Q") == true) goto L51;
        r153 = false;
        goto L52
    L40:
        if (r22.right <= ((r152.widthPixels / 4.0f) * 3.0f)) goto L42;
    L35:
        if (r22.left >= (r152.widthPixels / 4.0f)) goto L37;
    L59:
        th = move-exception;
        FileLog.e("Failed to parse notch info", th);
        goto L61
    L14:
        if (r13.endsWith(LEFT_MARKER) == false) goto L16;
        r13 = r13.substring(0, r13.length() - 5).trim();
        r42 = 0.0f;
        r5 = 3;
        goto L17
    L16:
        r42 = r4 / 2.0f;
        r5 = 17;
    L61:
        return null;
    }
}
