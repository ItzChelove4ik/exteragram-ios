package com.google.android.gms.cast.framework.media.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.gms.cast.internal.Logger;

/* loaded from: classes4.dex */
public final class zzo {
    private static final Logger zza = null;

    static {
        zza = new Logger("WidgetUtil");
    }

    public static Bitmap zza(Context r5, Bitmap r6, float r7, float r8) {
        Logger r72 = zza;
        r72.d("Begin blurring bitmap %s, original width = %d, original height = %d.", new Object[]{r6, Integer.valueOf(r6.getWidth()), Integer.valueOf(r6.getHeight())});
        int r82 = Math.round(r6.getWidth() * 0.25f);
        int r0 = Math.round(r6.getHeight() * 0.25f);
        Bitmap r62 = Bitmap.createScaledBitmap(r6, r82, r0, false);
        Bitmap r1 = Bitmap.createBitmap(r82, r0, r62.getConfig());
        RenderScript r52 = RenderScript.create(r5);
        Allocation r2 = Allocation.createFromBitmap(r52, r62);
        Allocation r3 = Allocation.createTyped(r52, r2.getType());
        ScriptIntrinsicBlur r4 = ScriptIntrinsicBlur.create(r52, r2.getElement());
        r4.setInput(r2);
        r4.setRadius(7.5f);
        r4.forEach(r3);
        r3.copyTo(r1);
        r52.destroy();
        r72.d("End blurring bitmap %s, original width = %d, original height = %d.", new Object[]{r62, Integer.valueOf(r82), Integer.valueOf(r0)});
        return r1;
    }

    public static Drawable zzb(Context r2, int r3, int r4) {
        return zzd(r2, r3, r4, R.attr.colorForeground, 0);
    }

    public static Drawable zzc(Context r2, int r3, int r4) {
        return zzd(r2, r3, r4, 0, R.color.white);
    }

    private static Drawable zzd(Context r1, int r2, int r3, int r4, int r5) {
        Drawable r32 = DrawableCompat.wrap(r1.getResources().getDrawable(r3).mutate());
        DrawableCompat.setTintMode(r32, PorterDuff.Mode.SRC_IN);
        if (r2 == 0) goto L5;
        ColorStateList r12 = ContextCompat.getColorStateList(r1, r2);
    L9:
        DrawableCompat.setTintList(r32, r12);
        return r32;
    L5:
        if (r4 == 0) goto L7;
        TypedArray r13 = r1.obtainStyledAttributes(new int[]{r4});
        int r22 = r13.getColor(0, 0);
        r13.recycle();
    L8:
        int[] r14 = {r22, ColorUtils.setAlphaComponent(r22, 128)};
        r12 = new ColorStateList(new int[][]{new int[]{R.attr.state_enabled}, new int[]{-16842910}}, r14);
        goto L9
    L7:
        r22 = ContextCompat.getColor(r1, r5);
        goto L8
    }
}
