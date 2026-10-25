package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.cast.framework.R;
import org.telegram.tgnet.TLObject;

/* loaded from: classes4.dex */
final class zzi {
    private final Rect zza;
    private final int zzb;
    private final int zzc;
    private final int zzd;
    private final int zze;
    private final zzh zzf;

    public zzi(zzh r2) {
        this.zza = new Rect();
        this.zzf = r2;
        Resources r22 = r2.getResources();
        this.zzb = r22.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_inner_radius);
        this.zzc = r22.getDimensionPixelOffset(R.dimen.cast_libraries_material_featurehighlight_inner_margin);
        this.zzd = r22.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_text_max_width);
        this.zze = r22.getDimensionPixelSize(R.dimen.cast_libraries_material_featurehighlight_text_horizontal_offset);
    }

    private final int zzb(View r4, int r5, int r6, int r7, int r8) {
        ViewGroup.MarginLayoutParams r42 = (ViewGroup.MarginLayoutParams) r4.getLayoutParams();
        int r0 = r8 - r5;
        int r1 = r6 - r8;
        int r82 = r8 - (r7 / 2);
        int r3 = this.zze;
        if (r0 > r1) goto L5;
        int r83 = r82 + r3;
    L6:
        int r32 = r42.leftMargin;
        if ((r83 - r32) < r5) goto L9;
        int r43 = r42.rightMargin;
        if (((r83 + r7) + r43) > r6) goto L13;
        return r83;
    L13:
        return (r6 - r7) - r43;
    L9:
        return r5 + r32;
    L5:
        r83 = r82 - r3;
        goto L6
    }

    private final void zzc(View r3, int r4, int r5) {
        ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) r3.getLayoutParams();
        r3.measure(View.MeasureSpec.makeMeasureSpec(Math.min((r4 - r0.leftMargin) - r0.rightMargin, this.zzd), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r5, Integer.MIN_VALUE));
    }

    public final void zza(Rect r9, Rect r10) {
        zzh r0 = this.zzf;
        View r2 = r0.zzg();
        if (r9.isEmpty() == false) goto L5;
    L6:
        zzi r1 = this;
        r2.layout(0, 0, 0, 0);
    L12:
        Rect r8 = r1.zza;
        r8.set(r2.getLeft(), r2.getTop(), r2.getRight(), r2.getBottom());
        r0.zzh().zzc(r9, r8);
        r0.zzi().zza(r9);
        return;
    L5:
        if (r10.isEmpty() == true) goto L6;
        int r12 = r9.centerY();
        int r6 = r9.centerX();
        int r3 = r10.centerY();
        int r4 = r9.height();
        int r5 = this.zzb;
        int r42 = Math.max(r5 + r5, r4) / 2;
        int r52 = r12 + r42;
        int r7 = this.zzc;
        if (r12 >= r3) goto L10;
        int r72 = r7 + r52;
        int r13 = r10.bottom - r72;
        zzc(r2, r10.width(), r13);
        int r82 = zzb(r2, r10.left, r10.right, r2.getMeasuredWidth(), r6);
        r2.layout(r82, r72, r2.getMeasuredWidth() + r82, r2.getMeasuredHeight() + r72);
        r1 = this;
        r2 = r2;
        goto L12
    L10:
        int r73 = (r12 - r42) - r7;
        int r83 = r73 - r10.top;
        zzc(r2, r10.width(), r83);
        r1 = this;
        r2 = r2;
        int r84 = r1.zzb(r2, r10.left, r10.right, r2.getMeasuredWidth(), r6);
        r2.layout(r84, r73 - r2.getMeasuredHeight(), r2.getMeasuredWidth() + r84, r73);
        goto L12
    }
}
