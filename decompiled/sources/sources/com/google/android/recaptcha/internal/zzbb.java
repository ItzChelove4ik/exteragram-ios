package com.google.android.recaptcha.internal;

import android.app.Application;
import android.database.Cursor;
import android.view.MotionEvent;
import kotlin.Unit;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
public final class zzbb {
    private final Application zza;
    private final CoroutineScope zzb;
    private final zzav zzc;
    private zzay zzd;
    private boolean zze;
    private final zzas zzf;

    public zzbb(Application r1, CoroutineScope r2, zzav r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        zzas r22 = new zzas(r3);
        this.zzf = r22;
        r1.registerActivityLifecycleCallbacks(r22);
    }

    public static /* synthetic */ Unit zzf(zzbb r6, String r7, MotionEvent r8) {
        zzba r3 = new zzba(r6, r7, MotionEvent.obtain(r8), null);
        BuildersKt.launch$default(r6.zzb, null, null, r3, 3, null);
        return Unit.INSTANCE;
    }

    public final zzay zza() {
        return this.zzd;
    }

    public final boolean zzb() {
        return this.zze;
    }

    public final void zzc(int r9, long r10) {
        if (this.zze == false) goto L5;
        return;
    L5:
        if (this.zzd != null) goto L7;
        this.zzd = new zzay(this.zza.getApplicationContext(), r9, r10, true, 0.2f);
    L7:
        this.zze = true;
        this.zzc.zzb(new zzaz(this));
    }

    public final Cursor zzd(String r1, String[] r2) {
        zzay r0 = this.zzd;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.zzc(r1, r2);
    }

    public final void zze() {
        if (this.zze == false) goto L10;
        this.zzc.zzb(null);
        this.zze = false;
        zzay r2 = this.zzd;
        if (r2 == null) goto L9;
        r2.zzb();
        return;
    L9:
        return;
    }
}
