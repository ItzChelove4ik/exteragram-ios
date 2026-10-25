package com.google.android.gms.internal.cast;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.Looper;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.mediarouter.media.MediaRouter;
import androidx.mediarouter.media.RouteListingPreference;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.SessionState;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionTransferCallback;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@TargetApi(30)
/* loaded from: classes4.dex */
public final class zzce {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Logger zzb = null;
    private final CastOptions zzc;
    private final Set zzd;
    private final Handler zze;
    private final Runnable zzf;
    private int zzg;
    private boolean zzh;
    private SessionManager zzi;
    private CallbackToFutureAdapter.Completer zzj;
    private SessionState zzk;

    static {
        zzb = new Logger("SessionTransController");
    }

    public zzce(CastOptions r2) {
        this.zzd = Collections.synchronizedSet(new HashSet());
        this.zzc = r2;
        this.zzg = 0;
        this.zze = new zzfk(Looper.getMainLooper());
        this.zzf = new zzcd(this);
    }

    public static /* synthetic */ Logger zzo() {
        return zzb;
    }

    private final void zzq() {
        ((Handler) Preconditions.checkNotNull(this.zze)).removeCallbacks((Runnable) Preconditions.checkNotNull(this.zzf));
        this.zzg = 0;
        this.zzk = null;
    }

    private final void zzr(int r4) {
        CallbackToFutureAdapter.Completer r0 = this.zzj;
        if (r0 == null) goto L5;
        r0.setCancelled();
    L5:
        zzb.d("notify failed transfer with type = %d, reason = %d", new Object[]{Integer.valueOf(this.zzg), Integer.valueOf(r4)});
        Iterator r02 = new HashSet(this.zzd).iterator();
    L7:
        if (r02.hasNext() == false) goto L9;
        ((SessionTransferCallback) r02.next()).onTransferFailed(this.zzg, r4);
        goto L7
    L9:
        zzq();
    }

    private final RemoteMediaClient zzs() {
        SessionManager r3 = this.zzi;
        if (r3 != null) goto L6;
        zzb.d("skip transferring as SessionManager is null", new Object[0]);
        return null;
    L6:
        CastSession r32 = r3.getCurrentCastSession();
        if (r32 != null) goto L11;
        zzb.d("skip transferring as CastSession is null", new Object[0]);
        return null;
    L11:
        return r32.getRemoteMediaClient();
    }

    public final void zza(SessionManager r2) {
        this.zzi = r2;
        ((Handler) Preconditions.checkNotNull(this.zze)).post(new zzca(this));
    }

    public final void zzb(boolean r1) {
        this.zzh = r1;
    }

    public final void zzc(SessionTransferCallback r4) {
        zzb.d("register callback = %s", new Object[]{r4});
        Preconditions.checkMainThread("Must be called from the main thread.");
        Preconditions.checkNotNull(r4);
        this.zzd.add(r4);
    }

    public final void zzd(SessionTransferCallback r4) {
        zzb.d("unregister callback = %s", new Object[]{r4});
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (r4 == null) goto L6;
        this.zzd.remove(r4);
        return;
    }

    public final void zze(MediaRouter.RouteInfo r7, MediaRouter.RouteInfo r8, CallbackToFutureAdapter.Completer r9) {
        Set r1 = this.zzd;
        if (new HashSet(r1).isEmpty() == false) goto L6;
        zzb.d("No need to prepare transfer without any callback", new Object[0]);
        r9.set(null);
        return;
    L6:
        int r0 = 1;
        if (r7.getPlaybackType() == 1) goto L10;
        zzb.d("No need to prepare transfer when transferring from local", new Object[0]);
        r9.set(null);
        return;
    L10:
        RemoteMediaClient r72 = zzs();
        if (r72 != null) goto L13;
    L28:
        zzb.d("No need to prepare transfer when there is no media session", new Object[0]);
        r9.set(null);
        return;
    L13:
        if (r72.hasMediaSession() == false) goto L28;
        Logger r4 = zzb;
        r4.d("Prepare route transfer for changing endpoint", new Object[0]);
        if (r8.getPlaybackType() != 0) goto L19;
        zzr.zzb(zzpm.zzP);
    L22:
        this.zzg = r0;
        this.zzj = r9;
        r4.d("notify transferring with type = %d", new Object[]{Integer.valueOf(r0)});
        Iterator r82 = new HashSet(r1).iterator();
    L24:
        if (r82.hasNext() == false) goto L26;
        ((SessionTransferCallback) r82.next()).onTransferring(this.zzg);
        goto L24
    L26:
        this.zzk = null;
        r72.zzf(null).addOnSuccessListener(new zzcb(this)).addOnFailureListener(new zzcc(this));
        ((Handler) Preconditions.checkNotNull(this.zze)).postDelayed((Runnable) Preconditions.checkNotNull(this.zzf), 20000);
        return;
    L19:
        if (CastDevice.getFromBundle(r8.getExtras()) != null) goto L21;
        r0 = 3;
        goto L22
    L21:
        r0 = 2;
        goto L22
    }

    public final void zzf(MediaRouter r4) {
        if (zzg() == true) goto L5;
        return;
    L5:
        SessionManager r3 = this.zzi;
        if (r3 == null) goto L8;
        CastSession r32 = r3.getCurrentCastSession();
    L9:
        if (r32 != null) goto L12;
        r4.setRouteListingPreference(null);
        return;
    L12:
        ArrayList r33 = new ArrayList();
        Iterator<MediaRouter.RouteInfo> r0 = r4.getRoutes().iterator();
    L14:
        if (r0.hasNext() == false) goto L18;
        MediaRouter.RouteInfo r1 = r0.next();
        if (CastDevice.getFromBundle(r1.getExtras()) == null) goto L14;
        r33.add(new RouteListingPreference.Item.Builder(r1.getId()).setFlags(0).build());
        goto L14
    L18:
        zzb.d("updateRouteListingPreference with %d available routes", new Object[]{Integer.valueOf(r33.size())});
        r4.setRouteListingPreference(new RouteListingPreference.Builder().setItems(r33).build());
        return;
    L8:
        r32 = null;
        goto L9
    }

    public final boolean zzg() {
        if (this.zzh == true) goto L5;
        return false;
    L5:
        if (this.zzc.zzj() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final /* synthetic */ void zzh() {
        zzb.i("transfer with type = %d has timed out", new Object[]{Integer.valueOf(this.zzg)});
        zzr(101);
    }

    public final /* synthetic */ void zzi() {
        zzbz r0 = new zzbz(this, null);
        ((SessionManager) Preconditions.checkNotNull(this.zzi)).addSessionManagerListener(r0, CastSession.class);
    }

    public final /* synthetic */ void zzj(SessionState r1) {
        this.zzk = r1;
        CallbackToFutureAdapter.Completer r0 = this.zzj;
        if (r0 == null) goto L6;
        r0.set(null);
        return;
    }

    public final /* synthetic */ void zzk(Exception r4) {
        zzb.w(r4, "Fail to store SessionState", new Object[0]);
        zzr(100);
    }

    public final /* synthetic */ void zzl() {
        zzq();
    }

    public final /* synthetic */ void zzm() {
        int r0 = this.zzg;
        if (r0 != 0) goto L6;
        zzb.d("No need to notify transferred if the transfer type is unknown", new Object[0]);
        return;
    L6:
        SessionState r2 = this.zzk;
        if (r2 != null) goto L10;
        zzb.d("No need to notify with null sessionState", new Object[0]);
        return;
    L10:
        zzb.d("notify transferred with type = %d, sessionState = %s", new Object[]{Integer.valueOf(r0), this.zzk});
        Iterator r02 = new HashSet(this.zzd).iterator();
    L12:
        if (r02.hasNext() == false) goto L14;
        ((SessionTransferCallback) r02.next()).onTransferred(this.zzg, r2);
        goto L12
    }

    public final /* synthetic */ void zzn() {
        if (this.zzk != null) goto L6;
        zzb.d("skip restoring session state due to null SessionState", new Object[0]);
        return;
    L6:
        RemoteMediaClient r0 = zzs();
        if (r0 != null) goto L10;
        zzb.d("skip restoring session state due to null RemoteMediaClient", new Object[0]);
        return;
    L10:
        zzb.d("resume SessionState to current session", new Object[0]);
        r0.zzg(this.zzk);
    }

    public final /* synthetic */ int zzp() {
        return this.zzg;
    }
}
