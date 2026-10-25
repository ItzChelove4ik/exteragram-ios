package org.telegram.messenger.utils;

import android.graphics.LinearGradient;
import android.graphics.Shader;

/* loaded from: classes6.dex */
public class ColorShader extends LinearGradient {
    public ColorShader(int r9) {
        super(0.0f, 0.0f, 1.0f, 0.0f, new int[]{r9, r9}, null, Shader.TileMode.CLAMP);
    }
}
