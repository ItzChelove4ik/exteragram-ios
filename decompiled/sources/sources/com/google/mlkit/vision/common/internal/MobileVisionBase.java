package com.google.mlkit.vision.common.internal;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_common.zzlx;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MLTask;
import com.google.mlkit.vision.common.InputImage;
import java.io.Closeable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public abstract class MobileVisionBase<DetectionResultT> implements Closeable, LifecycleObserver {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final GmsLogger zzb = null;
    private final AtomicBoolean zzc;
    private final MLTask zzd;
    private final CancellationTokenSource zze;
    private final Executor zzf;
    private final Task zzg;

    static {
        zzb = new GmsLogger("MobileVisionBase", _UrlKt.FRAGMENT_ENCODE_SET);
    }

    public MobileVisionBase(MLTask<DetectionResultT, InputImage> r3, Executor r4) {
        this.zzc = new AtomicBoolean(false);
        this.zzd = r3;
        CancellationTokenSource r0 = new CancellationTokenSource();
        this.zze = r0;
        this.zzf = r4;
        r3.pin();
        this.zzg = r3.callAfterLoad(r4, zzb.zza, r0.getToken()).addOnFailureListener(zzc.zza);
    }

    public static /* synthetic */ void zzc(Exception r3) {
        zzb.e("MobileVisionBase", "Error preloading model resource", r3);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public synchronized void close() {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (this.zzc.getAndSet(true) == true) goto L10;
        this.zze.cancel();     // Catch: Throwable -> L8
        this.zzd.unpin(this.zzf);     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L10:
        monitor-exit(this);
    }

    public synchronized Task<DetectionResultT> processBase(InputImage r4) {
        monitor-enter(this);
        Preconditions.checkNotNull(r4, "InputImage can not be null");     // Catch: Throwable -> L8
        if (this.zzc.get() == false) goto L11;
        Task<DetectionResultT> r42 = Tasks.forException(new MlKitException("This detector is already closed!", 14));     // Catch: Throwable -> L8
        monitor-exit(this);
        return r42;
    L11:
        if (r4.getWidth() >= 32) goto L13;
    L17:
        Task<DetectionResultT> r43 = Tasks.forException(new MlKitException("InputImage width and height should be at least 32!", 3));     // Catch: Throwable -> L8
        monitor-exit(this);
        return r43;
    L13:
        if (r4.getHeight() < 32) goto L17;
        Task<DetectionResultT> r44 = this.zzd.callAfterLoad(this.zzf, new zza(this, r4), this.zze.getToken());     // Catch: Throwable -> L8
        monitor-exit(this);
        return r44;
    L8:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ Object zza(InputImage r4) {
        zzlx r1 = zzlx.zze("detectorTaskWithResource#run");
        r1.zzb();
        Object r3 = this.zzd.run(r4);     // Catch: Throwable -> L6
        r1.close();
        return r3;
    L6:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L9
    L11:
        throw th;
    L9:
        th = move-exception;
        Throwable.class.getDeclaredMethod("addSuppressed", new Class[]{Throwable.class}).invoke(th, new Object[]{th});     // Catch: Exception -> L12
        goto L11
    }
}
