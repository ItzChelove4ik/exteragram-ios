package com.google.android.gms.internal.cast;

import android.os.IInterface;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

/* loaded from: classes4.dex */
public interface zzbc extends IInterface {
    int zze();

    com.google.android.gms.cast.framework.zzai zzf(IObjectWrapper r1, CastOptions r2, zzbe r3, Map r4);

    com.google.android.gms.cast.framework.zzav zzg(String r1, String r2, com.google.android.gms.cast.framework.zzbd r3);

    com.google.android.gms.cast.framework.zzal zzh(CastOptions r1, IObjectWrapper r2, com.google.android.gms.cast.framework.zzaf r3);

    com.google.android.gms.cast.framework.zzas zzi(IObjectWrapper r1, IObjectWrapper r2, IObjectWrapper r3);

    com.google.android.gms.cast.framework.media.internal.zzg zzj(IObjectWrapper r1, com.google.android.gms.cast.framework.media.internal.zzi r2, int r3, int r4, boolean r5, long r6, int r8, int r9, int r10);

    com.google.android.gms.cast.framework.media.internal.zzg zzk(IObjectWrapper r1, IObjectWrapper r2, com.google.android.gms.cast.framework.media.internal.zzi r3, int r4, int r5, boolean r6, long r7, int r9, int r10, int r11);
}
