package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.AttributionSource;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.wrappers.AttributionSourceWrapper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class BaseGmsClient<T extends IInterface> {
    public static final int CONNECT_STATE_CONNECTED = 4;
    public static final int CONNECT_STATE_DISCONNECTED = 1;
    public static final int CONNECT_STATE_DISCONNECTING = 5;
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES = null;
    public static final String KEY_PENDING_INTENT = "pendingIntent";
    private static final Feature[] zze = null;
    private volatile String zzA;
    private volatile AttributionSourceWrapper zzB;
    private ConnectionResult zzC;
    private boolean zzD;
    private volatile zzj zzE;
    zzs zza;
    final Handler zzb;
    protected ConnectionProgressReportCallbacks zzc;
    protected AtomicInteger zzd;
    private int zzf;
    private long zzg;
    private long zzh;
    private int zzi;
    private long zzj;
    private volatile String zzk;
    private final Context zzl;
    private final Looper zzm;
    private final GmsClientSupervisor zzn;
    private final GoogleApiAvailabilityLight zzo;
    private final Object zzp;
    private final Object zzq;
    private IGmsServiceBroker zzr;
    private IInterface zzs;
    private final ArrayList zzt;
    private zze zzu;
    private int zzv;
    private final BaseConnectionCallbacks zzw;
    private final BaseOnConnectionFailedListener zzx;
    private final int zzy;
    private final String zzz;

    public interface BaseConnectionCallbacks {
        void onConnected(Bundle r1);

        void onConnectionSuspended(int r1);
    }

    public interface BaseOnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult r1);
    }

    public interface ConnectionProgressReportCallbacks {
        void onReportServiceBinding(ConnectionResult r1);
    }

    public class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
        final /* synthetic */ BaseGmsClient zza;

        public LegacyClientCallbackAdapter(BaseGmsClient r1) {
            java.util.Objects.requireNonNull(r1);
            this.zza = r1;
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
        public final void onReportServiceBinding(ConnectionResult r2) {
            boolean r0 = r2.isSuccess();
            BaseGmsClient r1 = this.zza;
            if (r0 == false) goto L7;
            r1.getRemoteService(null, r1.getScopes());
            return;
        L7:
            if (r1.zzl() == null) goto L10;
            r1.zzl().onConnectionFailed(r2);
            return;
        }
    }

    public interface SignOutCallbacks {
        void onSignOutComplete();
    }

    static {
        zze = new Feature[0];
        GOOGLE_PLUS_REQUIRED_FEATURES = new String[]{"service_esmobile", "service_googleme"};
    }

    public BaseGmsClient(Context r10, Looper r11, int r12, BaseConnectionCallbacks r13, BaseOnConnectionFailedListener r14, String r15) {
        GmsClientSupervisor r3 = GmsClientSupervisor.getInstance(r10);
        GoogleApiAvailabilityLight r4 = GoogleApiAvailabilityLight.getInstance();
        Preconditions.checkNotNull(r13);
        Preconditions.checkNotNull(r14);
        this(r10, r11, r3, r4, r12, r13, r14, r15);
    }

    public BaseGmsClient(Context r3, Looper r4, GmsClientSupervisor r5, GoogleApiAvailabilityLight r6, int r7, BaseConnectionCallbacks r8, BaseOnConnectionFailedListener r9, String r10) {
        this.zzk = null;
        this.zzp = new Object();
        this.zzq = new Object();
        this.zzt = new ArrayList();
        this.zzv = 1;
        this.zzC = null;
        this.zzD = false;
        this.zzE = null;
        this.zzd = new AtomicInteger(0);
        Preconditions.checkNotNull(r3, "Context must not be null");
        this.zzl = r3;
        Preconditions.checkNotNull(r4, "Looper must not be null");
        this.zzm = r4;
        Preconditions.checkNotNull(r5, "Supervisor must not be null");
        this.zzn = r5;
        Preconditions.checkNotNull(r6, "API availability must not be null");
        this.zzo = r6;
        this.zzb = new zzb(this, r4);
        this.zzy = r7;
        this.zzw = r8;
        this.zzx = r9;
        this.zzz = r10;
    }

    private final void zzp(int r13, IInterface r14) {
        boolean r0 = false;
        if (r13 == 4) goto L5;
        boolean r3 = false;
    L6:
        if (r14 != null) goto L8;
        boolean r4 = false;
    L9:
        if (r3 != r4) goto L11;
        r0 = true;
    L11:
        Preconditions.checkArgument(r0);
        Object r32 = this.zzp;
        monitor-enter(r32);
        this.zzv = r13;     // Catch: Throwable -> L21
        this.zzs = r14;     // Catch: Throwable -> L21
        Bundle r02 = null;
        if (r13 != 1) goto L16;
        zze r8 = this.zzu;     // Catch: Throwable -> L21
        if (r8 == null) goto L54;
        GmsClientSupervisor r42 = this.zzn;     // Catch: Throwable -> L21
        String r5 = this.zza.zza();     // Catch: Throwable -> L21
        Preconditions.checkNotNull(r5);     // Catch: Throwable -> L21
        r42.zzb(r5, this.zza.zzb(), 4225, r8, zza(), this.zza.zzc());     // Catch: Throwable -> L21
        this.zzu = null;     // Catch: Throwable -> L21
    L54:
        monitor-exit(r32);     // Catch: Throwable -> L21
        return;
    L16:
        if (r13 == 2) goto L23;
        if (r13 == 3) goto L23;
        if (r13 != 4) goto L54;
        Preconditions.checkNotNull(r14);     // Catch: Throwable -> L21
        onConnectedLocked(r14);     // Catch: Throwable -> L21
    L23:
        zze r9 = this.zzu;     // Catch: Throwable -> L21
        if (r9 == null) goto L28;
        zzs r52 = this.zza;     // Catch: Throwable -> L21
        if (r52 == null) goto L28;
        String r7 = r52.zza();     // Catch: Throwable -> L21
        String r53 = r52.zzb();     // Catch: Throwable -> L21
        StringBuilder r10 = new StringBuilder((String.valueOf(r7).length() + 70) + String.valueOf(r53).length());     // Catch: Throwable -> L21
        r10.append("Calling connect() while still connected, missing disconnect() for ");     // Catch: Throwable -> L21
        r10.append(r7);     // Catch: Throwable -> L21
        r10.append(" on ");     // Catch: Throwable -> L21
        r10.append(r53);     // Catch: Throwable -> L21
        Log.e("GmsClient", r10.toString());     // Catch: Throwable -> L21
        GmsClientSupervisor r54 = this.zzn;     // Catch: Throwable -> L21
        String r6 = this.zza.zza();     // Catch: Throwable -> L21
        Preconditions.checkNotNull(r6);     // Catch: Throwable -> L21
        r54.zzb(r6, this.zza.zzb(), 4225, r9, zza(), this.zza.zzc());     // Catch: Throwable -> L21
        this.zzd.incrementAndGet();     // Catch: Throwable -> L21
    L28:
        zze r132 = new zze(this, this.zzd.get());     // Catch: Throwable -> L21
        this.zzu = r132;     // Catch: Throwable -> L21
        if (this.zzv == 3) goto L31;
    L33:
        zzs r55 = new zzs(getStartServicePackage(), getStartServiceAction(), false, 4225, getUseDynamicLookup());     // Catch: Throwable -> L21
    L34:
        this.zza = r55;     // Catch: Throwable -> L21
        if (r55.zzc() == true) goto L37;
    L41:
        GmsClientSupervisor r1 = this.zzn;     // Catch: Throwable -> L21
        String r43 = this.zza.zza();     // Catch: Throwable -> L21
        Preconditions.checkNotNull(r43);     // Catch: Throwable -> L21
        String r56 = this.zza.zzb();     // Catch: Throwable -> L21
        String r62 = zza();     // Catch: Throwable -> L21
        boolean r72 = this.zza.zzc();     // Catch: Throwable -> L21
        ConnectionResult r133 = r1.zza(new zzn(r43, r56, 4225, r72), r132, r62, getBindServiceExecutor());     // Catch: Throwable -> L21
        if (r133.isSuccess() == true) goto L54;
        String r44 = this.zza.zza();     // Catch: Throwable -> L21
        String r57 = this.zza.zzb();     // Catch: Throwable -> L21
        StringBuilder r73 = new StringBuilder((String.valueOf(r44).length() + 34) + String.valueOf(r57).length());     // Catch: Throwable -> L21
        r73.append("unable to connect to service: ");     // Catch: Throwable -> L21
        r73.append(r44);     // Catch: Throwable -> L21
        r73.append(" on ");     // Catch: Throwable -> L21
        r73.append(r57);     // Catch: Throwable -> L21
        Log.w("GmsClient", r73.toString());     // Catch: Throwable -> L21
        if (r133.getErrorCode() != (-1)) goto L46;
        int r142 = 16;
    L48:
        if (r133.getResolution() == null) goto L50;
        r02 = new Bundle();     // Catch: Throwable -> L21
        r02.putParcelable(KEY_PENDING_INTENT, r133.getResolution());     // Catch: Throwable -> L21
    L50:
        zzb(r142, r02, this.zzd.get());     // Catch: Throwable -> L21
        goto L54
    L46:
        r142 = r133.getErrorCode();     // Catch: Throwable -> L21
        goto L48
    L37:
        if (getMinApkVersion() >= 17895000) goto L41;
        throw new IllegalStateException("Internal Error, the minimum apk version of this BaseGmsClient is too low to support dynamic lookup. Start service action: ".concat(String.valueOf(this.zza.zza())));     // Catch: Throwable -> L21
    L31:
        if (getLocalStartServiceAction() == null) goto L33;
        r55 = new zzs(getContext().getPackageName(), getLocalStartServiceAction(), true, 4225, false);     // Catch: Throwable -> L21
    L21:
        th = move-exception;
        throw th;
    L8:
        r4 = true;
        goto L9
    L5:
        r3 = true;
        goto L6
    }

    public void checkAvailabilityAndConnect() {
        int r0 = this.zzo.isGooglePlayServicesAvailable(this.zzl, getMinApkVersion());
        if (r0 == 0) goto L6;
        zzp(1, null);
        triggerNotAvailable(new LegacyClientCallbackAdapter(this), r0, null);
        return;
    L6:
        connect(new LegacyClientCallbackAdapter(this));
    }

    public final void checkConnected() {
        if (isConnected() == false) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Not connected. Call connect() and wait for onConnected() to be called.");
    }

    public void connect(ConnectionProgressReportCallbacks r2) {
        Preconditions.checkNotNull(r2, "Connection progress callbacks cannot be null.");
        this.zzc = r2;
        zzp(2, null);
    }

    public abstract T createServiceInterface(IBinder r1);

    public void disconnect() {
        this.zzd.incrementAndGet();
        ArrayList r0 = this.zzt;
        monitor-enter(r0);
        int r1 = r0.size();     // Catch: Throwable -> L7
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L9;
        ((zzc) r0.get(r2)).zzf();     // Catch: Throwable -> L7
        r2 = r2 + 1;     // Catch: Throwable -> L7
        goto L5
    L9:
        r0.clear();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        Object r12 = this.zzq;
        monitor-enter(r12);
        this.zzr = null;     // Catch: Throwable -> L18
        monitor-exit(r12);     // Catch: Throwable -> L18
        zzp(1, null);
        return;
    L18:
        th = move-exception;
        throw th;
    L7:
        th = move-exception;
        throw th;
    }

    public void disconnect(String r1) {
        this.zzk = r1;
        disconnect();
    }

    public void dump(String r19, FileDescriptor r20, PrintWriter r21, String[] r22) {
        Object r3 = this.zzp;
        monitor-enter(r3);
        int r4 = this.zzv;     // Catch: Throwable -> L54
        IInterface r5 = this.zzs;     // Catch: Throwable -> L54
        monitor-exit(r3);     // Catch: Throwable -> L54
        Object r6 = this.zzq;
        monitor-enter(r6);
        IGmsServiceBroker r32 = this.zzr;     // Catch: Throwable -> L51
        monitor-exit(r6);     // Catch: Throwable -> L51
        r21.append(r19).append("mConnectState=");
        if (r4 == 1) goto L23;
        if (r4 == 2) goto L22;
        if (r4 != 3) goto L15;
        r21.print("LOCAL_CONNECTING");
    L24:
        r21.append(" mService=");
        if (r5 != null) goto L27;
        r21.append("null");
    L28:
        r21.append(" mServiceBroker=");
        if (r32 != null) goto L31;
        r21.println("null");
    L32:
        SimpleDateFormat r33 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzh <= 0) goto L35;
        PrintWriter r42 = r21.append(r19).append("lastConnectedTime=");
        long r11 = this.zzh;
        String r13 = r33.format(new Date(r11));
        long r16 = 0;
        StringBuilder r9 = new StringBuilder((String.valueOf(r11).length() + 1) + String.valueOf(r13).length());
        r9.append(r11);
        r9.append(" ");
        r9.append(r13);
        r42.println(r9.toString());
    L37:
        if (this.zzg <= r16) goto L48;
        r21.append(r19).append("lastSuspendedCause=");
        int r43 = this.zzf;
        if (r43 == 1) goto L45;
        if (r43 == 2) goto L44;
        if (r43 == 3) goto L43;
        r21.append(String.valueOf(r43));
    L46:
        PrintWriter r44 = r21.append(" lastSuspendedTime=");
        long r62 = this.zzg;
        String r92 = r33.format(new Date(r62));
        StringBuilder r12 = new StringBuilder((String.valueOf(r62).length() + 1) + String.valueOf(r92).length());
        r12.append(r62);
        r12.append(" ");
        r12.append(r92);
        r44.println(r12.toString());
        goto L48
    L43:
        r21.append("CAUSE_DEAD_OBJECT_EXCEPTION");
        goto L46
    L44:
        r21.append("CAUSE_NETWORK_LOST");
        goto L46
    L45:
        r21.append("CAUSE_SERVICE_DISCONNECTED");
    L48:
        if (this.zzj <= r16) goto L61;
        r21.append(r19).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzi));
        PrintWriter r1 = r21.append(" lastFailedTime=");
        long r63 = this.zzj;
        String r0 = r33.format(new Date(r63));
        StringBuilder r45 = new StringBuilder((String.valueOf(r63).length() + 1) + String.valueOf(r0).length());
        r45.append(r63);
        r45.append(" ");
        r45.append(r0);
        r1.println(r45.toString());
        return;
    L61:
        return;
    L35:
        r16 = 0;
        goto L37
    L31:
        r21.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(r32.asBinder())));
        goto L32
    L27:
        r21.append(getServiceDescriptor()).append("@").append(Integer.toHexString(System.identityHashCode(r5.asBinder())));
        goto L28
    L15:
        if (r4 != 4) goto L17;
        r21.print("CONNECTED");
        goto L24
    L17:
        if (r4 == 5) goto L19;
        r21.print("UNKNOWN");
        goto L24
    L19:
        r21.print("DISCONNECTING");
        goto L24
    L22:
        r21.print("REMOTE_CONNECTING");
        goto L24
    L23:
        r21.print("DISCONNECTED");
    L51:
        th = move-exception;
        throw th;
    L54:
        th = move-exception;
        throw th;
    }

    public boolean enableLocalFallback() {
        return false;
    }

    public Account getAccount() {
        return null;
    }

    public Feature[] getApiFeatures() {
        return zze;
    }

    public AttributionSourceWrapper getAttributionSourceWrapper() {
        return this.zzB;
    }

    public final Feature[] getAvailableFeatures() {
        zzj r0 = this.zzE;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.zzb;
    }

    public Executor getBindServiceExecutor() {
        return null;
    }

    public Bundle getConnectionHint() {
        return null;
    }

    public final Context getContext() {
        return this.zzl;
    }

    public String getEndpointPackageName() {
        if (isConnected() == false) goto L8;
        zzs r1 = this.zza;
        if (r1 == null) goto L8;
        return r1.zzb();
    L8:
        GlShader$$ExternalSyntheticBUOutline1.m("Failed to connect when checking package");
        return null;
    }

    public int getGCoreServiceId() {
        return this.zzy;
    }

    public Bundle getGetServiceRequestExtraArgs() {
        return new Bundle();
    }

    public String getLastDisconnectMessage() {
        return this.zzk;
    }

    public String getLocalStartServiceAction() {
        return null;
    }

    public final Looper getLooper() {
        return this.zzm;
    }

    public abstract int getMinApkVersion();

    public void getRemoteService(IAccountAccessor r19, Set<Scope> r20) {
        Bundle r2 = getGetServiceRequestExtraArgs();
        if (Build.VERSION.SDK_INT >= 31) goto L7;
        String r4 = this.zzA;
    L5:
        String r17 = r4;
        int r5 = this.zzy;
        int r6 = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        Scope[] r9 = GetServiceRequest.zza;
        Bundle r10 = new Bundle();
        Feature[] r12 = GetServiceRequest.zzb;
        GetServiceRequest r3 = new GetServiceRequest(6, r5, r6, null, null, r9, r10, null, r12, r12, true, 0, false, r17);
        r3.zzf = this.zzl.getPackageName();
        r3.zzi = r2;
        if (r20 == null) goto L20;
        r3.zzh = (Scope[]) r20.toArray(new Scope[0]);
    L20:
        if (requiresSignIn() == false) goto L28;
        Account r0 = getAccount();
        if (r0 != null) goto L24;
        r0 = new Account(DEFAULT_ACCOUNT, "com.google");
    L24:
        r3.zzj = r0;
        if (r19 == null) goto L30;
        r3.zzg = r19.asBinder();
    L30:
        r3.zzk = zze;
        r3.zzl = getApiFeatures();
        if (usesClientTelemetry() == false) goto L59;
        r3.zzo = true;
    L59:
        Object r22 = this.zzq;     // Catch: RuntimeException -> L45 RemoteException -> L47 DeadObjectException -> L49 SecurityException -> L53
        monitor-enter(r22);     // Catch: RuntimeException -> L45 RemoteException -> L47 DeadObjectException -> L49 SecurityException -> L53
        IGmsServiceBroker r02 = this.zzr;     // Catch: Throwable -> L38
        if (r02 == null) goto L40;
        r02.getService(new zzd(this, this.zzd.get()), r3);     // Catch: Throwable -> L38
    L41:
        monitor-exit(r22);     // Catch: Throwable -> L38
        return;
    L40:
        Log.w("GmsClient", "mServiceBroker is null, client disconnected");     // Catch: Throwable -> L38
    L38:
        th = move-exception;
        throw th;     // Catch: RuntimeException -> L45 RemoteException -> L47 DeadObjectException -> L49 SecurityException -> L53
    L49:
        e = move-exception;
        Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
        triggerConnectionSuspended(3);
        return;
    L47:
        e = e;
    L51:
        Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
        onPostInitHandler(8, null, null, this.zzd.get());
        return;
    L53:
        e = move-exception;
        throw e;
    L45:
        e = e;
        goto L51
    L28:
        if (requiresAccount() == false) goto L30;
        r3.zzj = getAccount();
        goto L30
    L7:
        if (this.zzB != null) goto L9;
        r4 = this.zzA;
        goto L5
    L9:
        AttributionSource r42 = this.zzB.getAttributionSource();
        if (r42 != null) goto L13;
        r4 = this.zzA;
        goto L5
    L13:
        if (r42.getAttributionTag() != null) goto L15;
        r4 = this.zzA;
        goto L5
    L15:
        r4 = r42.getAttributionTag();
        goto L5
    }

    public Set<Scope> getScopes() {
        return Collections.EMPTY_SET;
    }

    public final T getService() {
        Object r0 = this.zzp;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.zzv == 5) goto L12;
        checkConnected();     // Catch: Throwable -> L9
        IInterface r3 = this.zzs;     // Catch: Throwable -> L9
        Preconditions.checkNotNull(r3, "Client is connected but service is null");     // Catch: Throwable -> L9
        T r32 = (T) r3;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r32;
    L12:
        throw new DeadObjectException();     // Catch: Throwable -> L9
    }

    public IBinder getServiceBrokerBinder() {
        Object r0 = this.zzq;
        monitor-enter(r0);
        IGmsServiceBroker r1 = this.zzr;     // Catch: Throwable -> L9
        if (r1 != null) goto L11;
        monitor-exit(r0);     // Catch: Throwable -> L9
        return null;
    L11:
        IBinder r12 = r1.asBinder();     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r12;
    L9:
        th = move-exception;
        throw th;
    }

    public abstract String getServiceDescriptor();

    public Intent getSignInIntent() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    public abstract String getStartServiceAction();

    public String getStartServicePackage() {
        return "com.google.android.gms";
    }

    public ConnectionTelemetryConfiguration getTelemetryConfiguration() {
        zzj r0 = this.zzE;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.zzd;
    }

    public boolean getUseDynamicLookup() {
        if (getMinApkVersion() < 211700000) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean hasConnectionInfo() {
        if (this.zzE == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isConnected() {
        Object r0 = this.zzp;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (this.zzv != 4) goto L7;
        boolean r2 = true;
    L8:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return r2;
    L7:
        r2 = false;
        goto L8
    }

    public boolean isConnecting() {
        Object r0 = this.zzp;
        monitor-enter(r0);
        int r3 = this.zzv;     // Catch: Throwable -> L12
        boolean r2 = true;
        if (r3 != 2) goto L7;
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L12
        return r2;
    L7:
        if (r3 == 3) goto L10;
        r2 = false;
    L12:
        th = move-exception;
        throw th;
    }

    public void onConnectedLocked(T r3) {
        this.zzh = System.currentTimeMillis();
    }

    public void onConnectionFailed(ConnectionResult r3) {
        this.zzi = r3.getErrorCode();
        this.zzj = System.currentTimeMillis();
    }

    public void onConnectionSuspended(int r3) {
        this.zzf = r3;
        this.zzg = System.currentTimeMillis();
    }

    public void onPostInitHandler(int r2, IBinder r3, Bundle r4, int r5) {
        zzf r0 = new zzf(this, r2, r3, r4);
        Handler r1 = this.zzb;
        r1.sendMessage(r1.obtainMessage(1, r5, -1, r0));
    }

    public void onUserSignOut(SignOutCallbacks r1) {
        r1.onSignOutComplete();
    }

    public boolean providesSignIn() {
        return false;
    }

    public boolean requiresAccount() {
        return false;
    }

    public boolean requiresGooglePlayServices() {
        return true;
    }

    public boolean requiresSignIn() {
        return false;
    }

    public void setAttributionSourceWrapper(AttributionSourceWrapper r1) {
        this.zzB = r1;
    }

    public void setAttributionTag(String r1) {
        this.zzA = r1;
    }

    public void triggerConnectionSuspended(int r3) {
        int r0 = this.zzd.get();
        Handler r2 = this.zzb;
        r2.sendMessage(r2.obtainMessage(6, r0, r3));
    }

    public void triggerNotAvailable(ConnectionProgressReportCallbacks r2, int r3, PendingIntent r4) {
        Preconditions.checkNotNull(r2, "Connection progress callbacks cannot be null.");
        this.zzc = r2;
        int r22 = this.zzd.get();
        Handler r1 = this.zzb;
        r1.sendMessage(r1.obtainMessage(3, r22, r3, r4));
    }

    public boolean usesClientTelemetry() {
        return false;
    }

    public final String zza() {
        String r0 = this.zzz;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return this.zzl.getClass().getName();
    }

    public final void zzb(int r2, Bundle r3, int r4) {
        zzg r0 = new zzg(this, r2, r3);
        Handler r1 = this.zzb;
        r1.sendMessage(r1.obtainMessage(7, r4, -1, r0));
    }

    public final /* synthetic */ void zzc(zzj r1) {
        this.zzE = r1;
        if (usesClientTelemetry() == false) goto L10;
        ConnectionTelemetryConfiguration r0 = r1.zzd;
        RootTelemetryConfigManager r12 = RootTelemetryConfigManager.getInstance();
        if (r0 != null) goto L7;
        RootTelemetryConfiguration r02 = null;
    L8:
        r12.zza(r02);
        return;
    L7:
        r02 = r0.zza();
        goto L8
    }

    public final /* synthetic */ void zzd(int r1, IInterface r2) {
        zzp(r1, null);
    }

    public final /* synthetic */ boolean zze(int r3, int r4, IInterface r5) {
        Object r0 = this.zzp;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.zzv == r3) goto L11;
        monitor-exit(r0);     // Catch: Throwable -> L9
        return false;
    L11:
        zzp(r4, r5);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return true;
    }

    public final /* synthetic */ void zzf(int r3) {
        Object r32 = this.zzp;
        monitor-enter(r32);
        int r0 = this.zzv;     // Catch: Throwable -> L12
        monitor-exit(r32);     // Catch: Throwable -> L12
        if (r0 != 3) goto L9;
        this.zzD = true;
        int r33 = 5;
    L10:
        Handler r02 = this.zzb;
        r02.sendMessage(r02.obtainMessage(r33, this.zzd.get(), 16));
        return;
    L9:
        r33 = 4;
    L12:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ boolean zzg() {
        if (this.zzD == false) goto L6;
        return false;
    L6:
        if (TextUtils.isEmpty(getServiceDescriptor()) == false) goto L9;
        return false;
    L9:
        if (TextUtils.isEmpty(getLocalStartServiceAction()) == false) goto L15;
        return false;
    L15:
        Class.forName(getServiceDescriptor());     // Catch: ClassNotFoundException -> L14
        return true;
    L14:
        return false;
    }

    public final /* synthetic */ Object zzh() {
        return this.zzq;
    }

    public final /* synthetic */ void zzi(IGmsServiceBroker r1) {
        this.zzr = r1;
    }

    public final /* synthetic */ ArrayList zzj() {
        return this.zzt;
    }

    public final /* synthetic */ BaseConnectionCallbacks zzk() {
        return this.zzw;
    }

    public final /* synthetic */ BaseOnConnectionFailedListener zzl() {
        return this.zzx;
    }

    public final /* synthetic */ ConnectionResult zzm() {
        return this.zzC;
    }

    public final /* synthetic */ void zzn(ConnectionResult r1) {
        this.zzC = r1;
    }

    public final /* synthetic */ boolean zzo() {
        return this.zzD;
    }
}
