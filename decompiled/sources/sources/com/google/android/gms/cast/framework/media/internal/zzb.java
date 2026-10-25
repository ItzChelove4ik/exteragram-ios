package com.google.android.gms.cast.framework.media.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes4.dex */
public final class zzb {
    private final Context zza;
    private final ImageHints zzb;
    private Uri zzc;
    private zzd zzd;
    private Bitmap zze;
    private boolean zzf;
    private zza zzg;

    public zzb(Context r4) {
        this(r4, new ImageHints(-1, 0, 0));
    }

    public zzb(Context r1, ImageHints r2) {
        this.zza = r1;
        this.zzb = r2;
        zze();
    }

    private final void zze() {
        zzd r0 = this.zzd;
        if (r0 == null) goto L5;
        r0.cancel(true);
        this.zzd = null;
    L5:
        this.zzc = null;
        this.zze = null;
        this.zzf = false;
    }

    public final void zza(zza r1) {
        this.zzg = r1;
    }

    public final boolean zzb(Uri r13) {
        if (r13 != null) goto L7;
        zze();
        return true;
    L7:
        if (r13.equals(this.zzc) == true) goto L18;
        zze();
        this.zzc = r13;
        ImageHints r0 = this.zzb;
        if (r0.getWidthInPixels() != 0) goto L11;
    L14:
        this.zzd = new zzd(this.zza, 0, 0, false, 2097152, 5, 333, 10000, this);
    L15:
        zzd r02 = (zzd) Preconditions.checkNotNull(this.zzd);
        Uri r1 = (Uri) Preconditions.checkNotNull(this.zzc);
        r02.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Uri[]{r1});
        return false;
    L11:
        if (r0.getHeightInPixels() == 0) goto L14;
        this.zzd = new zzd(this.zza, r0.getWidthInPixels(), r0.getHeightInPixels(), false, 2097152, 5, 333, 10000, this);
        goto L15
    L18:
        if (this.zzf == false) goto L20;
        return true;
    L20:
        return false;
    }

    public final void zzc() {
        zze();
        this.zzg = null;
    }

    public final void zzd(Bitmap r2) {
        this.zze = r2;
        this.zzf = true;
        zza r0 = this.zzg;
        if (r0 == null) goto L5;
        r0.zza(r2);
    L5:
        this.zzd = null;
    }
}
