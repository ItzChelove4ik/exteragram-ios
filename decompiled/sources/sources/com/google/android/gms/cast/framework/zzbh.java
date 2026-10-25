package com.google.android.gms.cast.framework;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* loaded from: classes4.dex */
public final class zzbh extends zzay {

    @NotOnlyInitialized
    private final SessionManagerListener zza;
    private final Class zzb;

    public zzbh(SessionManagerListener r1, Class r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.cast.framework.zzaz
    public final IObjectWrapper zzb() {
        return ObjectWrapper.wrap(this.zza);
    }

    @Override // com.google.android.gms.cast.framework.zzaz
    public final void zzc(IObjectWrapper r3) {
        Session r32 = (Session) ObjectWrapper.unwrap(r3);
        Class r0 = this.zzb;
        if (r0.isInstance(r32) == false) goto L8;
        SessionManagerListener r2 = this.zza;
        if (r2 == null) goto L9;
        r2.onSessionStarting((Session) r0.cast(r32));
        return;
    L9:
        return;
    }

    @Override // com.google.android.gms.cast.framework.zzaz
    public final void zzd(IObjectWrapper r3, String r4) {
        Session r32 = (Session) ObjectWrapper.unwrap(r3);
        Class r0 = this.zzb;
        if (r0.isInstance(r32) == false) goto L8;
        SessionManagerListener r2 = this.zza;
        if (r2 == null) goto L9;
        r2.onSessionStarted((Session) r0.cast(r32), r4);
        return;
    L9:
        return;
    }

    @Override // com.google.android.gms.cast.framework.zzaz
    public final void zze(IObjectWrapper r3, int r4) {
        Session r32 = (Session) ObjectWrapper.unwrap(r3);
        Class r0 = this.zzb;
        if (r0.isInstance(r32) == false) goto L8;
        SessionManagerListener r2 = this.zza;
        if (r2 == null) goto L9;
        r2.onSessionStartFailed((Session) r0.cast(r32), r4);
        return;
    L9:
        return;
    }

    @Override // com.google.android.gms.cast.framework.zzaz
    public final void zzf(IObjectWrapper r3) {
        Session r32 = (Session) ObjectWrapper.unwrap(r3);
        Class r0 = this.zzb;
        if (r0.isInstance(r32) == false) goto L8;
        SessionManagerListener r2 = this.zza;
        if (r2 == null) goto L9;
        r2.onSessionEnding((Session) r0.cast(r32));
        return;
    L9:
        return;
    }

    @Override // com.google.android.gms.cast.framework.zzaz
    public final void zzg(IObjectWrapper r3, int r4) {
        Session r32 = (Session) ObjectWrapper.unwrap(r3);
        Class r0 = this.zzb;
        if (r0.isInstance(r32) == false) goto L8;
        SessionManagerListener r2 = this.zza;
        if (r2 == null) goto L9;
        r2.onSessionEnded((Session) r0.cast(r32), r4);
        return;
    L9:
        return;
    }

    @Override // com.google.android.gms.cast.framework.zzaz
    public final void zzh(IObjectWrapper r3, String r4) {
        Session r32 = (Session) ObjectWrapper.unwrap(r3);
        Class r0 = this.zzb;
        if (r0.isInstance(r32) == false) goto L8;
        SessionManagerListener r2 = this.zza;
        if (r2 == null) goto L9;
        r2.onSessionResuming((Session) r0.cast(r32), r4);
        return;
    L9:
        return;
    }

    @Override // com.google.android.gms.cast.framework.zzaz
    public final void zzi(IObjectWrapper r3, boolean r4) {
        Session r32 = (Session) ObjectWrapper.unwrap(r3);
        Class r0 = this.zzb;
        if (r0.isInstance(r32) == false) goto L8;
        SessionManagerListener r2 = this.zza;
        if (r2 == null) goto L9;
        r2.onSessionResumed((Session) r0.cast(r32), r4);
        return;
    L9:
        return;
    }

    @Override // com.google.android.gms.cast.framework.zzaz
    public final void zzj(IObjectWrapper r3, int r4) {
        Session r32 = (Session) ObjectWrapper.unwrap(r3);
        Class r0 = this.zzb;
        if (r0.isInstance(r32) == false) goto L8;
        SessionManagerListener r2 = this.zza;
        if (r2 == null) goto L9;
        r2.onSessionResumeFailed((Session) r0.cast(r32), r4);
        return;
    L9:
        return;
    }

    @Override // com.google.android.gms.cast.framework.zzaz
    public final void zzk(IObjectWrapper r3, int r4) {
        Session r32 = (Session) ObjectWrapper.unwrap(r3);
        Class r0 = this.zzb;
        if (r0.isInstance(r32) == false) goto L8;
        SessionManagerListener r2 = this.zza;
        if (r2 == null) goto L9;
        r2.onSessionSuspended((Session) r0.cast(r32), r4);
        return;
    L9:
        return;
    }
}
