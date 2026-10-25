package com.google.android.gms.cast.framework.media.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.internal.cast.zzay;

/* loaded from: classes4.dex */
public final class zzd extends AsyncTask {
    private static final Logger zza = null;
    private final zzg zzb;
    private final zzb zzc;

    static {
        zza = new Logger("FetchBitmapTask");
    }

    public zzd(Context r2, int r3, int r4, boolean r5, long r6, int r8, int r9, int r10, zzb r11) {
        this.zzc = r11;
        this.zzb = zzay.zze(r2.getApplicationContext(), this, new zzc(this, null), r3, r4, false, 2097152, 5, 333, 10000);
    }

    public static /* synthetic */ void zza(zzd r0, Object[] r1) {
        r0.publishProgress(r1);
    }

    @Override // android.os.AsyncTask
    public final /* bridge */ /* synthetic */ Object doInBackground(Object[] r4) {
        Uri[] r42 = (Uri[]) r4;
        if (r42.length != 1) goto L14;
        Uri r43 = r42[0];
        if (r43 != null) goto L7;
        return null;
    L7:
        zzg r3 = this.zzb;
        if (r3 != null) goto L15;
        return null;
    L15:
        return r3.zze(r43);
    L12:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"doFetch", zzg.class.getSimpleName()});
    L14:
        return null;
    }

    @Override // android.os.AsyncTask
    public final /* bridge */ /* synthetic */ void onPostExecute(Object r1) {
        zzb r0 = this.zzc;
        Bitmap r12 = (Bitmap) r1;
        if (r0 == null) goto L6;
        r0.zzd(r12);
        return;
    }
}
