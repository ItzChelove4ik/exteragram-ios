package com.google.android.gms.cast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzff;
import com.google.android.gms.internal.cast.zzfk;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

@SuppressLint({"UseSparseArrays"})
/* loaded from: classes4.dex */
public final class zzbm extends GoogleApi implements zzq {
    public static final /* synthetic */ int zzf = 0;
    private static final Logger zzg = null;
    private static final Api.AbstractClientBuilder zzh = null;
    private static final Api zzi = null;
    final zzbl zza;
    TaskCompletionSource zzb;
    TaskCompletionSource zzc;
    final Map zzd;
    final Map zze;
    private Handler zzj;
    private boolean zzk;
    private boolean zzl;
    private final AtomicLong zzm;
    private final Object zzn;
    private final Object zzo;
    private ApplicationMetadata zzp;
    private String zzq;
    private double zzr;
    private boolean zzs;
    private int zzt;
    private int zzu;
    private zzao zzv;
    private final CastDevice zzw;
    private final Cast.Listener zzx;
    private final List zzy;
    private int zzz;

    static {
        zzg = new Logger("CastClient");
        zzar r0 = new zzar();
        zzh = r0;
        zzi = new Api("Cast.API_CXLESS", r0, com.google.android.gms.cast.internal.zzal.zzb);
    }

    public zzbm(Context r3, Cast.CastOptions r4) {
        super(r3, zzi, r4, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.zza = new zzbl(this);
        this.zzn = new Object();
        this.zzo = new Object();
        this.zzy = Collections.synchronizedList(new ArrayList());
        Preconditions.checkNotNull(r3, "context cannot be null");
        Preconditions.checkNotNull(r4, "CastOptions cannot be null");
        this.zzx = r4.zzb;
        this.zzw = r4.zza;
        this.zzd = new HashMap();
        this.zze = new HashMap();
        this.zzm = new AtomicLong(0);
        this.zzz = 1;
        zzt();
    }

    public static /* synthetic */ Logger zzM() {
        return zzg;
    }

    private final Task zzV(com.google.android.gms.cast.internal.zzaj r2) {
        return doUnregisterEventListener((ListenerHolder.ListenerKey) Preconditions.checkNotNull(registerListener(r2, "castDeviceControllerListenerKey").getListenerKey(), "Key must not be null"), 8415);
    }

    private final void zzW() {
        zzg.d("removing all MessageReceivedCallbacks", new Object[0]);
        Map r3 = this.zze;
        monitor-enter(r3);
        r3.clear();     // Catch: Throwable -> L7
        monitor-exit(r3);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private final void zzX() {
        boolean r0 = true;
        if (this.zzz != 1) goto L6;
        r0 = false;
    L6:
        Preconditions.checkState(r0, "Not active connection");
    }

    private final void zzY() {
        Preconditions.checkState(zza(), "Not connected to device");
    }

    private final void zzZ(TaskCompletionSource r3) {
        Object r0 = this.zzn;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.zzb == null) goto L9;
        zzaa(2477);     // Catch: Throwable -> L7
    L9:
        this.zzb = r3;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
    }

    private final void zzaa(int r3) {
        Object r0 = this.zzn;
        monitor-enter(r0);
        TaskCompletionSource r1 = this.zzb;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.setException(zzab(r3));     // Catch: Throwable -> L7
    L9:
        this.zzb = null;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private static ApiException zzab(int r1) {
        return ApiExceptionUtil.fromStatus(new Status(r1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ void zzA(String r2, Cast.MessageReceivedCallback r3, com.google.android.gms.cast.internal.zzy r4, TaskCompletionSource r5) {
        zzX();
        ApiMetadata r1 = zzff.zza(r4.getContext());
        ((com.google.android.gms.cast.internal.zzah) r4.getService()).zzm(r2, r1);
        if (r3 == null) goto L5;
        ((com.google.android.gms.cast.internal.zzah) r4.getService()).zzl(r2, r1);
    L5:
        r5.setResult(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ void zzB(Cast.MessageReceivedCallback r1, String r2, com.google.android.gms.cast.internal.zzy r3, TaskCompletionSource r4) {
        zzX();
        if (r1 == null) goto L5;
        ((com.google.android.gms.cast.internal.zzah) r3.getService()).zzm(r2, zzff.zza(r3.getContext()));
    L5:
        r4.setResult(null);
    }

    public final /* synthetic */ Task zzC(com.google.android.gms.cast.internal.zzaj r1) {
        return zzV(r1);
    }

    public final /* synthetic */ void zzD() {
        zzW();
    }

    public final /* synthetic */ void zzE(com.google.android.gms.cast.internal.zzac r10) {
        ApplicationMetadata r0 = r10.zze();
        if (CastUtils.zza(r0, this.zzp) == true) goto L5;
        this.zzp = r0;
        this.zzx.onApplicationMetadataChanged(r0);
    L5:
        double r02 = r10.zza();
        boolean r3 = true;
        if (Double.isNaN(r02) == false) goto L8;
    L10:
        boolean r03 = false;
    L11:
        boolean r1 = r10.zzb();
        if (r1 == this.zzs) goto L14;
        this.zzs = r1;
        r03 = true;
    L14:
        Logger r12 = zzg;
        r12.d("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", new Object[]{Boolean.valueOf(r03), Boolean.valueOf(this.zzk)});
        Cast.Listener r2 = this.zzx;
        if (r2 == null) goto L20;
        if (r03 == false) goto L18;
    L19:
        r2.onVolumeChanged();
        goto L20
    L18:
        if (this.zzk == true) goto L19;
    L20:
        Double.isNaN(r10.zzg());
        int r04 = r10.zzc();
        if (r04 == this.zzt) goto L23;
        this.zzt = r04;
        boolean r05 = true;
    L24:
        r12.d("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", new Object[]{Boolean.valueOf(r05), Boolean.valueOf(this.zzk)});
        if (r2 == null) goto L30;
        if (r05 == false) goto L28;
    L29:
        r2.onActiveInputStateChanged(this.zzt);
        goto L30
    L28:
        if (this.zzk == true) goto L29;
    L30:
        int r06 = r10.zzd();
        if (r06 == this.zzu) goto L33;
        this.zzu = r06;
    L34:
        r12.d("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", new Object[]{Boolean.valueOf(r3), Boolean.valueOf(this.zzk)});
        if (r2 == null) goto L41;
        if (r3 == false) goto L38;
    L39:
        r2.onStandbyStateChanged(this.zzu);
        goto L41
    L38:
        if (this.zzk == true) goto L39;
    L41:
        if (CastUtils.zza(this.zzv, r10.zzf()) == true) goto L43;
        this.zzv = r10.zzf();
    L43:
        this.zzk = false;
        return;
    L33:
        r3 = false;
        goto L34
    L23:
        r05 = false;
        goto L24
    L8:
        if (Math.abs(r02 - this.zzr) <= 1.0E-7d) goto L10;
        this.zzr = r02;
        r03 = true;
        goto L11
    }

    public final /* synthetic */ void zzF(com.google.android.gms.cast.internal.zza r5) {
        String r52 = r5.zza();
        if (CastUtils.zza(r52, this.zzq) == true) goto L5;
        this.zzq = r52;
        boolean r53 = true;
    L6:
        zzg.d("hasChanged=%b, mFirstApplicationStatusUpdate=%b", new Object[]{Boolean.valueOf(r53), Boolean.valueOf(this.zzl)});
        Cast.Listener r0 = this.zzx;
        if (r0 == null) goto L12;
        if (r53 == false) goto L10;
    L11:
        r0.onApplicationStatusChanged();
        goto L12
    L10:
        if (this.zzl == true) goto L11;
    L12:
        this.zzl = false;
        return;
    L5:
        r53 = false;
        goto L6
    }

    public final /* synthetic */ void zzG(Cast.ApplicationConnectionResult r3) {
        Object r0 = this.zzn;
        monitor-enter(r0);
        TaskCompletionSource r1 = this.zzb;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.setResult(r3);     // Catch: Throwable -> L7
    L9:
        this.zzb = null;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ void zzH(int r1) {
        zzaa(r1);
    }

    public final /* synthetic */ void zzI(int r4) {
        Object r0 = this.zzo;
        monitor-enter(r0);
        TaskCompletionSource r1 = this.zzc;     // Catch: Throwable -> L8
        if (r1 != null) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        if (r4 != 0) goto L12;
        r1.setResult(new Status(0));     // Catch: Throwable -> L8
    L13:
        this.zzc = null;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L12:
        r1.setException(zzab(r4));     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ void zzJ(long r1, int r3) {
        Map r0 = this.zzd;
        monitor-enter(r0);
        Long r12 = Long.valueOf(r1);     // Catch: Throwable -> L12
        TaskCompletionSource r2 = (TaskCompletionSource) r0.get(r12);     // Catch: Throwable -> L12
        r0.remove(r12);     // Catch: Throwable -> L12
        monitor-exit(r0);     // Catch: Throwable -> L12
        if (r2 == null) goto L17;
        if (r3 != 0) goto L10;
        r2.setResult(null);
        return;
    L10:
        r2.setException(zzab(r3));
        return;
    L17:
        return;
    L12:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ Handler zzK() {
        if (this.zzj != null) goto L6;
        this.zzj = new zzfk(getLooper());
    L6:
        return this.zzj;
    }

    public final /* synthetic */ void zzL() {
        this.zzt = -1;
        this.zzu = -1;
        this.zzp = null;
        this.zzq = null;
        this.zzr = 0.0d;
        zzt();
        this.zzs = false;
        this.zzv = null;
    }

    public final /* synthetic */ void zzN(boolean r1) {
        this.zzk = true;
    }

    public final /* synthetic */ void zzO(boolean r1) {
        this.zzl = true;
    }

    public final /* synthetic */ void zzP(ApplicationMetadata r1) {
        this.zzp = r1;
    }

    public final /* synthetic */ void zzQ(String r1) {
        this.zzq = r1;
    }

    public final /* synthetic */ CastDevice zzR() {
        return this.zzw;
    }

    public final /* synthetic */ Cast.Listener zzS() {
        return this.zzx;
    }

    public final /* synthetic */ List zzT() {
        return this.zzy;
    }

    public final /* synthetic */ void zzU(int r1) {
        this.zzz = r1;
    }

    @Override // com.google.android.gms.cast.zzq
    public final boolean zza() {
        if (this.zzz != 3) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.gms.cast.zzq
    public final void zzb(zzp r1) {
        Preconditions.checkNotNull(r1);
        this.zzy.add(r1);
    }

    @Override // com.google.android.gms.cast.zzq
    public final Task zzc() {
        ListenerHolder r0 = registerListener(this.zza, "castDeviceControllerListenerKey");
        RegistrationMethods.Builder r1 = RegistrationMethods.builder();
        zzbd r2 = new zzbd(this);
        zzas r3 = zzas.zza;
        this.zzz = 2;
        return doRegisterEventListener(r1.withHolder(r0).register(r2).unregister(r3).setFeatures(new Feature[]{zzaq.zzb}).setMethodKey(8428).build());
    }

    @Override // com.google.android.gms.cast.zzq
    public final Task zzd() {
        Task r0 = doWrite(TaskApiCall.builder().run(zzau.zza).setMethodKey(8403).build());
        zzW();
        zzV(this.zza);
        return r0;
    }

    @Override // com.google.android.gms.cast.zzq
    public final Task zze() {
        return doWrite(TaskApiCall.builder().run(zzav.zza).setMethodKey(8404).build());
    }

    @Override // com.google.android.gms.cast.zzq
    public final Task zzf(String r4, String r5) {
        CastUtils.throwIfInvalidNamespace(r4);
        if (TextUtils.isEmpty(r5) == false) goto L5;
        f$$ExternalSyntheticBUOutline1.m("The message payload cannot be null or empty");
        return null;
    L5:
        if (r5.length() <= 524288) goto L7;
        zzg.w("Message send failed. Message exceeds maximum size", new Object[0]);
        f$$ExternalSyntheticBUOutline1.m("Message exceeds maximum size524288");
        return null;
    L7:
        return doWrite(TaskApiCall.builder().run(new zzaw(this, null, r4, r5)).setMethodKey(8405).build());
    }

    @Override // com.google.android.gms.cast.zzq
    public final Task zzg(String r3, LaunchOptions r4) {
        return doWrite(TaskApiCall.builder().run(new zzax(this, r3, r4)).setMethodKey(8406).build());
    }

    @Override // com.google.android.gms.cast.zzq
    public final Task zzh(String r3) {
        return doWrite(TaskApiCall.builder().run(new zzaz(this, r3)).setMethodKey(8409).build());
    }

    @Override // com.google.android.gms.cast.zzq
    public final Task zzi(double r3) {
        if (Double.isInfinite(r3) == false) goto L5;
    L8:
        StringBuilder r1 = new StringBuilder(String.valueOf(r3).length() + 17);
        r1.append("Volume cannot be ");
        r1.append(r3);
        throw new IllegalArgumentException(r1.toString());
    L5:
        if (Double.isNaN(r3) == true) goto L8;
        return doWrite(TaskApiCall.builder().run(new zzba(this, r3)).setMethodKey(8411).build());
    }

    @Override // com.google.android.gms.cast.zzq
    public final double zzj() {
        zzY();
        return this.zzr;
    }

    @Override // com.google.android.gms.cast.zzq
    public final Task zzk(boolean r3) {
        return doWrite(TaskApiCall.builder().run(new zzbb(this, r3)).setMethodKey(8412).build());
    }

    @Override // com.google.android.gms.cast.zzq
    public final boolean zzl() {
        zzY();
        return this.zzs;
    }

    @Override // com.google.android.gms.cast.zzq
    public final int zzm() {
        zzY();
        return this.zzt;
    }

    @Override // com.google.android.gms.cast.zzq
    public final int zzn() {
        zzY();
        return this.zzu;
    }

    @Override // com.google.android.gms.cast.zzq
    public final ApplicationMetadata zzo() {
        zzY();
        return this.zzp;
    }

    @Override // com.google.android.gms.cast.zzq
    public final String zzp() {
        zzY();
        return this.zzq;
    }

    @Override // com.google.android.gms.cast.zzq
    public final Task zzq(String r3, Cast.MessageReceivedCallback r4) {
        CastUtils.throwIfInvalidNamespace(r3);
        if (r4 == null) goto L13;
        Map r0 = this.zze;
        monitor-enter(r0);
        r0.put(r3, r4);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    L13:
        return doWrite(TaskApiCall.builder().run(new zzbc(this, r3, r4)).setMethodKey(8413).build());
    }

    @Override // com.google.android.gms.cast.zzq
    public final Task zzr(String r4) {
        if (TextUtils.isEmpty(r4) == true) goto L13;
        Map r0 = this.zze;
        monitor-enter(r0);
        Cast.MessageReceivedCallback r1 = (Cast.MessageReceivedCallback) r0.remove(r4);     // Catch: Throwable -> L10
        monitor-exit(r0);     // Catch: Throwable -> L10
        return doWrite(TaskApiCall.builder().run(new zzat(this, r1, r4)).setMethodKey(8414).build());
    L10:
        th = move-exception;
        throw th;
    L13:
        f$$ExternalSyntheticBUOutline1.m("Channel namespace cannot be null or empty");
        return null;
    }

    public final Task zzs(String r3, String r4, zzbn r5) {
        return doWrite(TaskApiCall.builder().run(new zzay(this, r3, r4, null)).setMethodKey(8407).build());
    }

    @RequiresNonNull({"device"})
    public final double zzt() {
        CastDevice r5 = this.zzw;
        if (r5.hasCapability(2048) == false) goto L6;
        return 0.02d;
    L6:
        if (r5.hasCapability(4) == true) goto L8;
    L13:
        return 0.05d;
    L8:
        if (r5.hasCapability(1) == true) goto L13;
        if ("Chromecast Audio".equals(r5.getModelName()) == false) goto L12;
        return 0.05d;
    L12:
        return 0.02d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ void zzu(String r7, String r8, String r9, com.google.android.gms.cast.internal.zzy r10, TaskCompletionSource r11) {
        long r3 = this.zzm.incrementAndGet();
        zzY();
        this.zzd.put(Long.valueOf(r3), r11);     // Catch: RemoteException -> L5
        ApiMetadata r5 = zzff.zza(r10.getContext());     // Catch: RemoteException -> L5
        ((com.google.android.gms.cast.internal.zzah) r10.getService()).zzk(r8, r9, r3, r5);     // Catch: RemoteException -> L5
        return;
    L5:
        e = move-exception;
        this.zzd.remove(Long.valueOf(r3));
        r11.setException(e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ void zzv(String r2, LaunchOptions r3, com.google.android.gms.cast.internal.zzy r4, TaskCompletionSource r5) {
        zzY();
        ((com.google.android.gms.cast.internal.zzah) r4.getService()).zzn(r2, r3, zzff.zza(r4.getContext()));
        zzZ(r5);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ void zzw(String r2, String r3, zzbn r4, com.google.android.gms.cast.internal.zzy r5, TaskCompletionSource r6) {
        zzY();
        ((com.google.android.gms.cast.internal.zzah) r5.getService()).zzo(r2, r3, null, zzff.zza(r5.getContext()));
        zzZ(r6);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ void zzx(String r2, com.google.android.gms.cast.internal.zzy r3, TaskCompletionSource r4) {
        zzY();
        ((com.google.android.gms.cast.internal.zzah) r3.getService()).zzg(r2, zzff.zza(r3.getContext()));
        Object r22 = this.zzo;
        monitor-enter(r22);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.zzc == null) goto L11;
        r4.setException(zzab(CastStatusCodes.INVALID_REQUEST));     // Catch: Throwable -> L9
        monitor-exit(r22);     // Catch: Throwable -> L9
        return;
    L11:
        this.zzc = r4;     // Catch: Throwable -> L9
        monitor-exit(r22);     // Catch: Throwable -> L9
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ void zzy(double r9, com.google.android.gms.cast.internal.zzy r11, TaskCompletionSource r12) {
        ((com.google.android.gms.cast.internal.zzah) r11.getService()).zzi(r9, this.zzr, this.zzs, zzff.zza(r11.getContext()));
        r12.setResult(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ void zzz(boolean r8, com.google.android.gms.cast.internal.zzy r9, TaskCompletionSource r10) {
        ((com.google.android.gms.cast.internal.zzah) r9.getService()).zzj(r8, this.zzr, this.zzs, zzff.zza(r9.getContext()));
        r10.setResult(null);
    }
}
