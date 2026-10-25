package com.google.android.gms.cast.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.text.TextUtils;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzbn;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.internal.BinderWrapper;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzff;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes4.dex */
public final class zzx extends GmsClient {
    private static final Object zzD = null;
    private static final Object zzE = null;
    public static final /* synthetic */ int zze = 0;
    private static final Logger zzf = null;
    private final Map zzA;
    private BaseImplementation$ResultHolder zzB;
    private BaseImplementation$ResultHolder zzC;
    private ApplicationMetadata zzg;
    private final CastDevice zzh;
    private final Cast.Listener zzi;
    private final Map zzj;
    private final long zzk;
    private final Bundle zzl;
    private zzw zzm;
    private String zzn;
    private boolean zzo;
    private boolean zzp;
    private boolean zzq;
    private boolean zzr;
    private double zzs;
    private com.google.android.gms.cast.zzao zzt;
    private int zzu;
    private int zzv;
    private final AtomicLong zzw;
    private String zzx;
    private String zzy;
    private Bundle zzz;

    static {
        zzf = new Logger("CastClientImpl");
        zzD = new Object();
        zzE = new Object();
    }

    public zzx(Context r8, Looper r9, ClientSettings r10, CastDevice r11, long r12, Cast.Listener r14, Bundle r15, GoogleApiClient.ConnectionCallbacks r16, GoogleApiClient.OnConnectionFailedListener r17) {
        super(r8, r9, 10, r10, r16, r17);
        this.zzh = r11;
        this.zzi = r14;
        this.zzk = r12;
        this.zzl = r15;
        this.zzj = new HashMap();
        this.zzw = new AtomicLong(0);
        this.zzA = new HashMap();
        zzY();
        zzG();
    }

    public static /* synthetic */ Logger zzN() {
        return zzf;
    }

    public static /* synthetic */ Object zzX() {
        return zzD;
    }

    private final void zzY() {
        this.zzr = false;
        this.zzu = -1;
        this.zzv = -1;
        this.zzg = null;
        this.zzn = null;
        this.zzs = 0.0d;
        zzG();
        this.zzo = false;
        this.zzt = null;
    }

    private final void zzZ(BaseImplementation$ResultHolder r9) {
        Object r1 = zzD;
        monitor-enter(r1);
        BaseImplementation$ResultHolder r0 = this.zzB;     // Catch: Throwable -> L7
        if (r0 == null) goto L9;
        r0.setResult(new zzr(new Status(2477), null, null, null, false));     // Catch: Throwable -> L7
    L9:
        this.zzB = r9;     // Catch: Throwable -> L7
        monitor-exit(r1);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private final void zzaa(BaseImplementation$ResultHolder r3) {
        Object r0 = zzE;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.zzC == null) goto L11;
        r3.setResult(new Status(CastStatusCodes.INVALID_REQUEST));     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L11:
        this.zzC = r3;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
    }

    private final void zzab() {
        zzf.d("removing all MessageReceivedCallbacks", new Object[0]);
        Map r3 = this.zzj;
        monitor-enter(r3);
        r3.clear();     // Catch: Throwable -> L7
        monitor-exit(r3);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private final void zzac(long r1, int r3) {
        Map r0 = this.zzA;
        monitor-enter(r0);
        BaseImplementation$ResultHolder r12 = (BaseImplementation$ResultHolder) r0.remove(Long.valueOf(r1));     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        if (r12 == null) goto L14;
        r12.setResult(new Status(r3));
        return;
    L14:
        return;
    L9:
        th = move-exception;
        throw th;
    }

    private final void zzad(int r4) {
        Object r0 = zzE;
        monitor-enter(r0);
        BaseImplementation$ResultHolder r1 = this.zzC;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.setResult(new Status(r4));     // Catch: Throwable -> L7
        this.zzC = null;     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r1 = r2.queryLocalInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        if ((r1 instanceof zzah) == false) goto L10;
        return (zzah) r1;
    L10:
        return new zzah(r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final void disconnect() {
        Logger r0 = zzf;
        r0.d("disconnect(); ServiceListener=%s, isConnected=%b", new Object[]{this.zzm, Boolean.valueOf(isConnected())});
        zzw r1 = this.zzm;
        this.zzm = null;
        if (r1 != null) goto L5;
    L20:
        r0.d("already disposed, so short-circuiting", new Object[0]);
        return;
    L5:
        if (r1.zzq() == null) goto L20;
        zzab();
        ((zzah) getService()).zze(zzff.zza(getContext()));     // Catch: Throwable -> L10 IllegalStateException -> L12 Throwable -> L14
    L16:
        super.disconnect();
        return;
    L10:
        th = move-exception;
        super.disconnect();
        throw th;
    L14:
        e = move-exception;
        zzf.d(e, "Error while disconnecting the controller interface", new Object[0]);     // Catch: Throwable -> L10
        goto L16
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Bundle getConnectionHint() {
        Bundle r0 = this.zzz;
        if (r0 == null) goto L7;
        this.zzz = null;
        return r0;
    L7:
        return super.getConnectionHint();
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Bundle getGetServiceRequestExtraArgs() {
        Bundle r0 = new Bundle();
        zzf.d("getRemoteService(): mLastApplicationId=%s, mLastSessionId=%s", new Object[]{this.zzx, this.zzy});
        this.zzh.putInBundle(r0);
        r0.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.zzk);
        Bundle r1 = this.zzl;
        if (r1 == null) goto L5;
        r0.putAll(r1);
    L5:
        this.zzm = new zzw(this);
        r0.putParcelable("listener", new BinderWrapper(this.zzm));
        String r12 = this.zzx;
        if (r12 == null) goto L10;
        r0.putString("last_application_id", r12);
        String r4 = this.zzy;
        if (r4 == null) goto L10;
        r0.putString("last_session_id", r4);
    L10:
        return r0;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 12800000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final void onConnectionFailed(ConnectionResult r1) {
        super.onConnectionFailed(r1);
        zzab();
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final void onPostInitHandler(int r4, IBinder r5, Bundle r6, int r7) {
        zzf.d("in onPostInitHandler; statusCode=%d", new Object[]{Integer.valueOf(r4)});
        if (r4 == 0) goto L7;
        if (r4 == 2300) goto L7;
        this.zzr = false;
    L8:
        if (r4 != 2300) goto L10;
        Bundle r42 = new Bundle();
        this.zzz = r42;
        r42.putBoolean(Cast.EXTRA_APP_NO_LONGER_RUNNING, true);
        r4 = 0;
    L10:
        super.onPostInitHandler(r4, r5, r6, r7);
        return;
    L7:
        this.zzr = true;
        this.zzp = true;
        this.zzq = true;
        goto L8
    }

    public final int zzA() {
        checkConnected();
        return this.zzv;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzB(String r2, Cast.MessageReceivedCallback r3) {
        CastUtils.throwIfInvalidNamespace(r2);
        zzC(r2);
        if (r3 == null) goto L15;
        Map r0 = this.zzj;
        monitor-enter(r0);
        r0.put(r2, r3);     // Catch: Throwable -> L12
        monitor-exit(r0);     // Catch: Throwable -> L12
        zzah r32 = (zzah) getService();
        if (zzF() == false) goto L18;
        r32.zzl(r2, zzff.zza(getContext()));
        return;
    L18:
        return;
    L12:
        th = move-exception;
        throw th;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzC(String r3) {
        if (TextUtils.isEmpty(r3) == true) goto L17;
        Map r0 = this.zzj;
        monitor-enter(r0);
        Cast.MessageReceivedCallback r1 = (Cast.MessageReceivedCallback) r0.remove(r3);     // Catch: Throwable -> L14
        monitor-exit(r0);     // Catch: Throwable -> L14
        if (r1 == null) goto L23;
        ((zzah) getService()).zzm(r3, zzff.zza(getContext()));     // Catch: IllegalStateException -> L11
        return;
    L11:
        e = move-exception;
        zzf.d(e, "Error unregistering namespace (%s)", new Object[]{r3});
        return;
    L23:
        return;
    L14:
        th = move-exception;
        throw th;
    L17:
        f$$ExternalSyntheticBUOutline1.m("Channel namespace cannot be null or empty");
    }

    public final ApplicationMetadata zzD() {
        checkConnected();
        return this.zzg;
    }

    public final String zzE() {
        checkConnected();
        return this.zzn;
    }

    public final boolean zzF() {
        if (this.zzr == false) goto L10;
        zzw r1 = this.zzm;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.zzr() == true) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public final double zzG() {
        CastDevice r5 = this.zzh;
        Preconditions.checkNotNull(r5, "device should not be null");
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

    public final void zzH(int r9) {
        Object r1 = zzD;
        monitor-enter(r1);
        BaseImplementation$ResultHolder r0 = this.zzB;     // Catch: Throwable -> L7
        if (r0 == null) goto L9;
        r0.setResult(new zzr(new Status(r9), null, null, null, false));     // Catch: Throwable -> L7
        this.zzB = null;     // Catch: Throwable -> L7
    L9:
        monitor-exit(r1);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ void zzI() {
        zzY();
    }

    public final /* synthetic */ void zzJ(zzac r10) {
        ApplicationMetadata r0 = r10.zze();
        if (CastUtils.zza(r0, this.zzg) == true) goto L5;
        this.zzg = r0;
        this.zzi.onApplicationMetadataChanged(r0);
    L5:
        double r02 = r10.zza();
        boolean r3 = true;
        if (Double.isNaN(r02) == false) goto L8;
    L10:
        boolean r03 = false;
    L11:
        boolean r1 = r10.zzb();
        if (r1 == this.zzo) goto L14;
        this.zzo = r1;
        r03 = true;
    L14:
        Double.isNaN(r10.zzg());
        Logger r12 = zzf;
        r12.d("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", new Object[]{Boolean.valueOf(r03), Boolean.valueOf(this.zzq)});
        Cast.Listener r2 = this.zzi;
        if (r2 == null) goto L20;
        if (r03 == false) goto L18;
    L19:
        r2.onVolumeChanged();
        goto L20
    L18:
        if (this.zzq == true) goto L19;
    L20:
        int r04 = r10.zzc();
        if (r04 == this.zzu) goto L23;
        this.zzu = r04;
        boolean r05 = true;
    L24:
        r12.d("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", new Object[]{Boolean.valueOf(r05), Boolean.valueOf(this.zzq)});
        if (r2 == null) goto L30;
        if (r05 == false) goto L28;
    L29:
        r2.onActiveInputStateChanged(this.zzu);
        goto L30
    L28:
        if (this.zzq == true) goto L29;
    L30:
        int r06 = r10.zzd();
        if (r06 == this.zzv) goto L33;
        this.zzv = r06;
    L34:
        r12.d("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", new Object[]{Boolean.valueOf(r3), Boolean.valueOf(this.zzq)});
        if (r2 == null) goto L41;
        if (r3 == false) goto L38;
    L39:
        r2.onStandbyStateChanged(this.zzv);
        goto L41
    L38:
        if (this.zzq == true) goto L39;
    L41:
        if (CastUtils.zza(this.zzt, r10.zzf()) == true) goto L43;
        this.zzt = r10.zzf();
    L43:
        this.zzq = false;
        return;
    L33:
        r3 = false;
        goto L34
    L23:
        r05 = false;
        goto L24
    L8:
        if (Math.abs(r02 - this.zzs) <= 1.0E-7d) goto L10;
        this.zzs = r02;
        r03 = true;
        goto L11
    }

    public final /* synthetic */ void zzK(zza r5) {
        String r52 = r5.zza();
        if (CastUtils.zza(r52, this.zzn) == true) goto L5;
        this.zzn = r52;
        boolean r53 = true;
    L6:
        zzf.d("hasChanged=%b, mFirstApplicationStatusUpdate=%b", new Object[]{Boolean.valueOf(r53), Boolean.valueOf(this.zzp)});
        Cast.Listener r0 = this.zzi;
        if (r0 == null) goto L12;
        if (r53 == false) goto L10;
    L11:
        r0.onApplicationStatusChanged();
        goto L12
    L10:
        if (this.zzp == true) goto L11;
    L12:
        this.zzp = false;
        return;
    L5:
        r53 = false;
        goto L6
    }

    public final /* synthetic */ void zzL(long r1, int r3) {
        zzac(r1, r3);
    }

    public final /* synthetic */ void zzM(int r1) {
        zzad(r1);
    }

    public final /* synthetic */ void zzO(ApplicationMetadata r1) {
        this.zzg = r1;
    }

    public final /* synthetic */ CastDevice zzP() {
        return this.zzh;
    }

    public final /* synthetic */ Cast.Listener zzQ() {
        return this.zzi;
    }

    public final /* synthetic */ Map zzR() {
        return this.zzj;
    }

    public final /* synthetic */ void zzS(String r1) {
        this.zzn = r1;
    }

    public final /* synthetic */ void zzT(String r1) {
        this.zzx = r1;
    }

    public final /* synthetic */ void zzU(String r1) {
        this.zzy = r1;
    }

    public final /* synthetic */ BaseImplementation$ResultHolder zzV() {
        return this.zzB;
    }

    public final /* synthetic */ void zzW(BaseImplementation$ResultHolder r1) {
        this.zzB = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzp(String r8, String r9, BaseImplementation$ResultHolder r10) {
        if (TextUtils.isEmpty(r9) == false) goto L5;
        f$$ExternalSyntheticBUOutline1.m("The message payload cannot be null or empty");
        return;
    L5:
        if (r9.length() > 524288) goto L17;
        CastUtils.throwIfInvalidNamespace(r8);
        long r4 = this.zzw.incrementAndGet();
        this.zzA.put(Long.valueOf(r4), r10);     // Catch: Throwable -> L11
        zzah r1 = (zzah) getService();     // Catch: Throwable -> L11
        if (zzF() == false) goto L13;
        r1.zzk(r8, r9, r4, zzff.zza(getContext()));     // Catch: Throwable -> L11
        return;
    L13:
        zzac(r4, CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);     // Catch: Throwable -> L11
        return;
    L11:
        th = move-exception;
        this.zzA.remove(Long.valueOf(r4));
        throw th;
    L17:
        zzf.w("Message send failed. Message exceeds maximum size", new Object[0]);
        f$$ExternalSyntheticBUOutline1.m("Message exceeds maximum size");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzq(String r2, LaunchOptions r3, BaseImplementation$ResultHolder r4) {
        zzZ(r4);
        zzah r42 = (zzah) getService();
        if (zzF() == false) goto L6;
        r42.zzn(r2, r3, zzff.zza(getContext()));
        return;
    L6:
        zzH(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzr(String r2, String r3, zzbn r4, BaseImplementation$ResultHolder r5) {
        zzZ(r5);
        zzbn r42 = new zzbn();
        zzah r52 = (zzah) getService();
        if (zzF() == false) goto L6;
        r52.zzo(r2, r3, r42, zzff.zza(getContext()));
        return;
    L6:
        zzH(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzs(BaseImplementation$ResultHolder r2) {
        zzaa(r2);
        zzah r22 = (zzah) getService();
        if (zzF() == false) goto L6;
        r22.zzf(zzff.zza(getContext()));
        return;
    L6:
        zzad(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzt(String r2, BaseImplementation$ResultHolder r3) {
        zzaa(r3);
        zzah r32 = (zzah) getService();
        if (zzF() == false) goto L6;
        r32.zzg(r2, zzff.zza(getContext()));
        return;
    L6:
        zzad(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzu() {
        zzah r0 = (zzah) getService();
        if (zzF() == false) goto L6;
        r0.zzh(zzff.zza(getContext()));
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzv(double r9) {
        if (Double.isInfinite(r9) == false) goto L5;
    L10:
        StringBuilder r10 = new StringBuilder(String.valueOf(r9).length() + 17);
        r10.append("Volume cannot be ");
        r10.append(r9);
        throw new IllegalArgumentException(r10.toString());
    L5:
        if (Double.isNaN(r9) == true) goto L10;
        zzah r1 = (zzah) getService();
        if (zzF() == false) goto L12;
        r1.zzi(r9, this.zzs, this.zzo, zzff.zza(getContext()));
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzw(boolean r8) {
        zzah r1 = (zzah) getService();
        if (zzF() == false) goto L6;
        r1.zzj(r8, this.zzs, this.zzo, zzff.zza(getContext()));
        return;
    }

    public final double zzx() {
        checkConnected();
        return this.zzs;
    }

    public final boolean zzy() {
        checkConnected();
        return this.zzo;
    }

    public final int zzz() {
        checkConnected();
        return this.zzu;
    }
}
