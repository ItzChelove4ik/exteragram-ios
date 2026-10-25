package org.telegram.ui.Components.Paint;

import android.graphics.RectF;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

/* loaded from: classes7.dex */
public abstract class Utils {
    public static void HasGLError() {
        int r0 = GLES20.glGetError();
        if (r0 == 0) goto L6;
        Log.d("Paint", GLUtils.getEGLErrorString(r0));
        return;
    }

    public static void RectFIntegral(RectF r2) {
        r2.left = (int) Math.floor(r2.left);
        r2.top = (int) Math.floor(r2.top);
        r2.right = (int) Math.ceil(r2.right);
        r2.bottom = (int) Math.ceil(r2.bottom);
    }
}
