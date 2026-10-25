package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.HashMap;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class zzsh {
    private static final zzai zzb = null;
    private final String zzc;
    private final String zzd;
    private final zzrz zze;
    private final SharedPrefManager zzf;
    private final Task zzg;
    private final Task zzh;
    private final String zzi;
    private final int zzj;

    static {
        zzb = zzai.zzc("optional-module-barcode", "com.google.android.gms.vision.barcode");
    }

    public zzsh(Context r2, SharedPrefManager r3, zzrz r4, String r5) {
        new HashMap();
        new HashMap();
        this.zzc = r2.getPackageName();
        this.zzd = CommonUtils.getAppVersion(r2);
        this.zzf = r3;
        this.zze = r4;
        zzsv.zza();
        this.zzi = r5;
        this.zzg = MLTaskExecutor.getInstance().scheduleCallable(new zzse(this));
        MLTaskExecutor r42 = MLTaskExecutor.getInstance();
        Objects.requireNonNull(r3);
        this.zzh = r42.scheduleCallable(new zzsf(r3));
        zzai r32 = zzb;
        if (r32.containsKey(r5) == false) goto L5;
        int r22 = DynamiteModule.getRemoteVersion(r2, (String) r32.get(r5));
    L6:
        this.zzj = r22;
        return;
    L5:
        r22 = -1;
        goto L6
    }

    public final /* synthetic */ String zza() {
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }
}
