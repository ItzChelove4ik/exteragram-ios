package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class zzm implements Cast.CastApi {
    public zzm() {
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final int getActiveInputState(GoogleApiClient r1) {
        return ((com.google.android.gms.cast.internal.zzx) r1.getClient(com.google.android.gms.cast.internal.zzal.zza)).zzz();
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final ApplicationMetadata getApplicationMetadata(GoogleApiClient r1) {
        return ((com.google.android.gms.cast.internal.zzx) r1.getClient(com.google.android.gms.cast.internal.zzal.zza)).zzD();
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final String getApplicationStatus(GoogleApiClient r1) {
        return ((com.google.android.gms.cast.internal.zzx) r1.getClient(com.google.android.gms.cast.internal.zzal.zza)).zzE();
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final int getStandbyState(GoogleApiClient r1) {
        return ((com.google.android.gms.cast.internal.zzx) r1.getClient(com.google.android.gms.cast.internal.zzal.zza)).zzA();
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final double getVolume(GoogleApiClient r1) {
        return ((com.google.android.gms.cast.internal.zzx) r1.getClient(com.google.android.gms.cast.internal.zzal.zza)).zzx();
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final boolean isMute(GoogleApiClient r1) {
        return ((com.google.android.gms.cast.internal.zzx) r1.getClient(com.google.android.gms.cast.internal.zzal.zza)).zzy();
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final PendingResult<Cast.ApplicationConnectionResult> joinApplication(GoogleApiClient r2) {
        return zza(r2, null, null, null);
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final PendingResult<Cast.ApplicationConnectionResult> joinApplication(GoogleApiClient r2, String r3) {
        return zza(r2, r3, null, null);
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final PendingResult<Cast.ApplicationConnectionResult> joinApplication(GoogleApiClient r2, String r3, String r4) {
        return zza(r2, r3, r4, null);
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final PendingResult<Cast.ApplicationConnectionResult> launchApplication(GoogleApiClient r2, String r3) {
        return r2.execute(new zzg(this, r2, r3));
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final PendingResult<Cast.ApplicationConnectionResult> launchApplication(GoogleApiClient r2, String r3, LaunchOptions r4) {
        return r2.execute(new zzh(this, r2, r3, r4));
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    @Deprecated
    public final PendingResult<Cast.ApplicationConnectionResult> launchApplication(GoogleApiClient r2, String r3, boolean r4) {
        LaunchOptions.Builder r0 = new LaunchOptions.Builder();
        r0.setRelaunchIfRunning(r4);
        return r2.execute(new zzh(this, r2, r3, r0.build()));
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final PendingResult<Status> leaveApplication(GoogleApiClient r2) {
        return r2.execute(new zzj(this, r2));
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final void removeMessageReceivedCallbacks(GoogleApiClient r1, String r2) {
        ((com.google.android.gms.cast.internal.zzx) r1.getClient(com.google.android.gms.cast.internal.zzal.zza)).zzC(r2);     // Catch: RemoteException -> L4
        return;
    L4:
        Model$$ExternalSyntheticBUOutline0.m("service error");
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final void requestStatus(GoogleApiClient r1) {
        ((com.google.android.gms.cast.internal.zzx) r1.getClient(com.google.android.gms.cast.internal.zzal.zza)).zzu();     // Catch: RemoteException -> L4
        return;
    L4:
        Model$$ExternalSyntheticBUOutline0.m("service error");
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final PendingResult<Status> sendMessage(GoogleApiClient r2, String r3, String r4) {
        return r2.execute(new zzf(this, r2, r3, r4));
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final void setMessageReceivedCallbacks(GoogleApiClient r1, String r2, Cast.MessageReceivedCallback r3) {
        ((com.google.android.gms.cast.internal.zzx) r1.getClient(com.google.android.gms.cast.internal.zzal.zza)).zzB(r2, r3);     // Catch: RemoteException -> L4
        return;
    L4:
        Model$$ExternalSyntheticBUOutline0.m("service error");
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final void setMute(GoogleApiClient r1, boolean r2) {
        ((com.google.android.gms.cast.internal.zzx) r1.getClient(com.google.android.gms.cast.internal.zzal.zza)).zzw(r2);     // Catch: RemoteException -> L4
        return;
    L4:
        Model$$ExternalSyntheticBUOutline0.m("service error");
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final void setVolume(GoogleApiClient r1, double r2) {
        ((com.google.android.gms.cast.internal.zzx) r1.getClient(com.google.android.gms.cast.internal.zzal.zza)).zzv(r2);     // Catch: RemoteException -> L4
        return;
    L4:
        Model$$ExternalSyntheticBUOutline0.m("service error");
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final PendingResult<Status> stopApplication(GoogleApiClient r2) {
        return r2.execute(new zzk(this, r2));
    }

    @Override // com.google.android.gms.cast.Cast.CastApi
    public final PendingResult<Status> stopApplication(GoogleApiClient r2, String r3) {
        return r2.execute(new zzl(this, r2, r3));
    }

    public final PendingResult zza(GoogleApiClient r7, String r8, String r9, zzbn r10) {
        return r7.execute(new zzi(this, r7, r8, r9, null));
    }
}
