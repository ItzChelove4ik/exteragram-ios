package com.google.android.gms.cloudmessaging;

import android.app.BroadcastOptions;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import com.chaquo.python.internal.Common;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class Rpc {
    private static int zza;
    private static PendingIntent zzb;
    private static final Executor zzc = null;
    private static final Pattern zzk = null;
    private final SimpleArrayMap zzd;
    private final Context zze;
    private final zzw zzf;
    private final ScheduledExecutorService zzg;
    private final Messenger zzh;
    private Messenger zzi;
    private zzd zzj;

    static {
        zzc = zzaf.zza;
        zzk = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)");
    }

    public Rpc(Context r5) {
        this.zzd = new SimpleArrayMap();
        this.zze = r5;
        this.zzf = new zzw(r5);
        this.zzh = new Messenger(new zzy(this, Looper.getMainLooper()));
        ScheduledThreadPoolExecutor r52 = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("fcm-rpc-timeout-executor"));
        r52.setKeepAliveTime(60, TimeUnit.SECONDS);
        r52.allowCoreThreadTimeOut(true);
        this.zzg = r52;
    }

    public static /* synthetic */ Task zzc(Bundle r1) {
        if (zzg(r1) == false) goto L7;
        return Tasks.forResult(null);
    L7:
        return Tasks.forResult(r1);
    }

    private static synchronized void zze(Context r4, Intent r5) {
        monitor-enter(Rpc.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zzb != null) goto L9;
        Intent r1 = new Intent();     // Catch: Throwable -> L7
        r1.setPackage("com.google.example.invalidpackage");     // Catch: Throwable -> L7
        zzb = com.google.android.gms.internal.cloudmessaging.zzr.zza(r4, 0, r1, com.google.android.gms.internal.cloudmessaging.zzr.zza);     // Catch: Throwable -> L7
    L9:
        r5.putExtra(Common.ASSET_APP, zzb);     // Catch: Throwable -> L7
        monitor-exit(Rpc.class);
    }

    private final void zzf(String r4, Bundle r5) {
        SimpleArrayMap r3 = this.zzd;
        monitor-enter(r3);
        TaskCompletionSource r1 = (TaskCompletionSource) r3.remove(r4);     // Catch: Throwable -> L9
        if (r1 != null) goto L11;
        StringBuilder r2 = new StringBuilder(String.valueOf(r4).length() + 21);     // Catch: Throwable -> L9
        r2.append("Missing callback for ");     // Catch: Throwable -> L9
        r2.append(r4);     // Catch: Throwable -> L9
        Log.w("Rpc", r2.toString());     // Catch: Throwable -> L9
        monitor-exit(r3);     // Catch: Throwable -> L9
        return;
    L11:
        r1.setResult(r5);     // Catch: Throwable -> L9
        monitor-exit(r3);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        throw th;
    }

    private static boolean zzg(Bundle r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.containsKey("google.messenger") == false) goto L9;
        return true;
    L9:
        return false;
    }

    private static synchronized String zzh() {
        monitor-enter(Rpc.class);
        int r1 = zza;     // Catch: Throwable -> L7
        zza = r1 + 1;     // Catch: Throwable -> L7
        String r12 = Integer.toString(r1);     // Catch: Throwable -> L7
        monitor-exit(Rpc.class);
        return r12;
    L7:
        th = move-exception;
        throw th;
    }

    private final Task zzi(Bundle r8) {
        String r0 = zzh();
        TaskCompletionSource r1 = new TaskCompletionSource();
        SimpleArrayMap r2 = this.zzd;
        monitor-enter(r2);
        r2.put(r0, r1);     // Catch: Throwable -> L35
        monitor-exit(r2);     // Catch: Throwable -> L35
        Intent r22 = new Intent();
        r22.setPackage("com.google.android.gms");
        if (this.zzf.zza() != 2) goto L9;
        r22.setAction("com.google.iid.TOKEN_REQUEST");
    L10:
        r22.putExtras(r8);
        zze(this.zze, r22);
        StringBuilder r3 = new StringBuilder(String.valueOf(r0).length() + 5);
        r3.append("|ID|");
        r3.append(r0);
        r3.append("|");
        r22.putExtra("kid", r3.toString());
        if (Log.isLoggable("Rpc", 3) == false) goto L13;
        Log.d("Rpc", "Sending ".concat(String.valueOf(r22.getExtras())));
    L13:
        r22.putExtra("google.messenger", this.zzh);
        if (this.zzi == null) goto L16;
    L17:
        Message r82 = Message.obtain();
        r82.obj = r22;
        Messenger r5 = this.zzi;     // Catch: RemoteException -> L23
        if (r5 == null) goto L21;
        r5.send(r82);     // Catch: RemoteException -> L23
    L38:
    L33:
        ScheduledFuture<?> r83 = this.zzg.schedule(new zzaa(r1), 30, TimeUnit.SECONDS);
        r1.getTask().addOnCompleteListener(zzc, new zzab(this, r0, r83));
        return r1.getTask();
    L21:
        this.zzj.zza.send(r82);     // Catch: RemoteException -> L23
    L24:
        if (Log.isLoggable("Rpc", 3) == false) goto L26;
        Log.d("Rpc", "Messenger failed, fallback to startService");
    L26:
        int r84 = this.zzf.zza();
        Context r32 = this.zze;
        if (r84 == 2) goto L29;
        r32.startService(r22);
        goto L33
    L29:
        if (Build.VERSION.SDK_INT >= 34) goto L31;
        r32.sendBroadcast(r22);
        goto L33
    L31:
        r32.sendBroadcast(r22, null, BroadcastOptions.makeBasic().setShareIdentityEnabled(true).toBundle());
        goto L33
    L16:
        if (this.zzj == null) goto L26;
    L9:
        r22.setAction("com.google.android.c2dm.intent.REGISTER");
    L35:
        th = move-exception;
        throw th;
    }

    public Task<CloudMessage> getProxiedNotificationData() {
        if (this.zzf.zzb() < 241100000) goto L7;
        return zzv.zza(this.zze).zzc(5, Bundle.EMPTY).continueWith(zzc, zzac.zza);
    L7:
        return Tasks.forException(new IOException("SERVICE_NOT_AVAILABLE"));
    }

    public Task<Void> messageHandled(CloudMessage r4) {
        if (this.zzf.zzb() < 233700000) goto L10;
        Bundle r0 = new Bundle();
        r0.putString("google.message_id", r4.getMessageId());
        Integer r42 = r4.zza();
        if (r42 == null) goto L8;
        r0.putInt("google.product_id", r42.intValue());
    L8:
        return zzv.zza(this.zze).zzb(3, r0);
    L10:
        return Tasks.forException(new IOException("SERVICE_NOT_AVAILABLE"));
    }

    public Task<Bundle> send(Bundle r4) {
        zzw r0 = this.zzf;
        if (r0.zzb() >= 12000000) goto L11;
        if (r0.zza() == 0) goto L9;
        return zzi(r4).continueWithTask(zzc, new zzz(this, r4));
    L9:
        return Tasks.forException(new IOException("MISSING_INSTANCEID_SERVICE"));
    L11:
        return zzv.zza(this.zze).zzc(1, r4).continueWith(zzc, zzae.zza);
    }

    public Task<Void> setRetainProxiedNotifications(boolean r3) {
        if (this.zzf.zzb() < 241100000) goto L7;
        Bundle r0 = new Bundle();
        r0.putBoolean("proxy_retention", r3);
        return zzv.zza(this.zze).zzb(4, r0);
    L7:
        return Tasks.forException(new IOException("SERVICE_NOT_AVAILABLE"));
    }

    public final /* synthetic */ Task zza(Bundle r2, Task r3) {
        if (r3.isSuccessful() == true) goto L6;
    L9:
        return r3;
    L6:
        if (zzg((Bundle) r3.getResult()) == false) goto L9;
        return zzi(r2).onSuccessTask(zzc, zzad.zza);
    }

    public final /* synthetic */ void zzb(String r1, ScheduledFuture r2, Task r3) {
        SimpleArrayMap r0 = this.zzd;
        monitor-enter(r0);
        r0.remove(r1);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        r2.cancel(false);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ void zzd(Message r8) {
        if (r8 == null) goto L67;
        Object r0 = r8.obj;
        if ((r0 instanceof Intent) == false) goto L67;
        Intent r02 = (Intent) r0;
        r02.setExtrasClassLoader(new zzc());
        if (r02.hasExtra("google.messenger") == false) goto L13;
        Parcelable r03 = r02.getParcelableExtra("google.messenger");
        if ((r03 instanceof zzd) == false) goto L11;
        this.zzj = (zzd) r03;
    L11:
        if ((r03 instanceof Messenger) == false) goto L13;
        this.zzi = (Messenger) r03;
    L13:
        Intent r82 = (Intent) r8.obj;
        String r04 = r82.getAction();
        if (Objects.equals(r04, "com.google.android.c2dm.intent.REGISTRATION") == false) goto L16;
        String r05 = r82.getStringExtra("registration_id");
        if (r05 != null) goto L23;
        r05 = r82.getStringExtra("unregistered");
    L23:
        if (r05 != null) goto L57;
        String r06 = r82.getStringExtra("error");
        if (r06 != null) goto L29;
        Log.w("Rpc", "Unexpected response, no error or registration id ".concat(String.valueOf(r82.getExtras())));
        return;
    L29:
        if (Log.isLoggable("Rpc", 3) == false) goto L32;
        Log.d("Rpc", "Received InstanceID error ".concat(r06));
    L32:
        if (r06.startsWith("|") == false) goto L45;
        String[] r4 = r06.split("\\|");
        if (r4.length > 2) goto L36;
    L43:
        Log.w("Rpc", "Unexpected structured response ".concat(r06));
        return;
    L36:
        if (Objects.equals(r4[1], "ID") == false) goto L43;
        String r07 = r4[2];
        String r1 = r4[3];
        if (r1.startsWith(":") == false) goto L41;
        r1 = r1.substring(1);
    L41:
        zzf(r07, r82.putExtra("error", r1).getExtras());
        return;
    L45:
        SimpleArrayMap r42 = this.zzd;
        monitor-enter(r42);
        int r08 = 0;
    L69:
    L51:
        th = move-exception;
        throw th;
    L49:
        if (r08 >= r42.size()) goto L53;
        zzf((String) r42.keyAt(r08), r82.getExtras());     // Catch: Throwable -> L51
        r08 = r08 + 1;     // Catch: Throwable -> L51
        goto L69
    L53:
        monitor-exit(r42);     // Catch: Throwable -> L51
        return;
    L57:
        Matcher r43 = zzk.matcher(r05);
        if (r43.matches() == false) goto L60;
        String r09 = r43.group(1);
        String r12 = r43.group(2);
        if (r09 == null) goto L74;
        Bundle r83 = r82.getExtras();
        r83.putString("registration_id", r12);
        zzf(r09, r83);
        return;
    L74:
        return;
    L60:
        if (Log.isLoggable("Rpc", 3) == false) goto L73;
        Log.d("Rpc", "Unexpected response string: ".concat(r05));
        return;
    L73:
        return;
    L16:
        if (Log.isLoggable("Rpc", 3) == false) goto L72;
        Log.d("Rpc", "Unexpected response action: ".concat(String.valueOf(r04)));
        return;
    L72:
        return;
    L67:
        Log.w("Rpc", "Dropping invalid message");
    }
}
