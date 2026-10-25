package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@KeepName
/* loaded from: classes4.dex */
public abstract class BasePendingResult<R extends Result> extends PendingResult<R> {
    static final ThreadLocal zaa = null;
    public static final /* synthetic */ int zad = 0;

    @KeepName
    private zar resultGuardian;
    protected final CallbackHandler zab;
    protected final WeakReference zac;
    private final Object zae;
    private final CountDownLatch zaf;
    private final ArrayList zag;
    private ResultCallback zah;
    private final AtomicReference zai;
    private Result zaj;
    private Status zak;
    private volatile boolean zal;
    private boolean zam;
    private boolean zan;
    private ICancelToken zao;
    private volatile zacs zap;
    private boolean zaq;

    public static class CallbackHandler<R extends Result> extends com.google.android.gms.internal.base.zao {
        public CallbackHandler(Looper r1) {
            super(r1);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public final void handleMessage(Message r2) {
            int r1 = r2.what;
            if (r1 != 1) goto L5;
            Pair r12 = (Pair) r2.obj;
            ResultCallback r22 = (ResultCallback) r12.first;
            Result r13 = (Result) r12.second;
            r22.onResult(r13);     // Catch: RuntimeException -> L13
            return;
        L13:
            e = move-exception;
            BasePendingResult.zal(r13);
            throw e;
        L5:
            if (r1 == 2) goto L8;
            StringBuilder r0 = new StringBuilder(String.valueOf(r1).length() + 34);
            r0.append("Don't know how to handle message: ");
            r0.append(r1);
            Log.wtf("BasePendingResult", r0.toString(), new Exception());
            return;
        L8:
            ((BasePendingResult) r2.obj).forceFailureUnlessReady(Status.RESULT_TIMEOUT);
        }

        public final void zaa(ResultCallback r2, Result r3) {
            int r0 = BasePendingResult.zad;
            sendMessage(obtainMessage(1, new Pair((ResultCallback) Preconditions.checkNotNull(r2), r3)));
        }
    }

    static {
        zaa = new zaq();
    }

    @Deprecated
    public BasePendingResult(Looper r3) {
        this.zae = new Object();
        this.zaf = new CountDownLatch(1);
        this.zag = new ArrayList();
        this.zai = new AtomicReference();
        this.zaq = false;
        this.zab = new CallbackHandler(r3);
        this.zac = new WeakReference(null);
    }

    public BasePendingResult(GoogleApiClient r3) {
        this.zae = new Object();
        this.zaf = new CountDownLatch(1);
        this.zag = new ArrayList();
        this.zai = new AtomicReference();
        this.zaq = false;
        if (r3 == null) goto L5;
        Looper r0 = r3.getLooper();
    L6:
        this.zab = new CallbackHandler(r0);
        this.zac = new WeakReference(r3);
        return;
    L5:
        r0 = Looper.getMainLooper();
        goto L6
    }

    private final Result zaa() {
        Object r0 = this.zae;
        monitor-enter(r0);
        Preconditions.checkState(!this.zal, "Result has already been consumed.");     // Catch: Throwable -> L11
        Preconditions.checkState(isReady(), "Result is not ready.");     // Catch: Throwable -> L11
        Result r1 = this.zaj;     // Catch: Throwable -> L11
        this.zaj = null;     // Catch: Throwable -> L11
        this.zah = null;     // Catch: Throwable -> L11
        this.zal = true;     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        zact r02 = (zact) this.zai.getAndSet(null);
        if (r02 == null) goto L10;
        r02.zaa.zab.remove(this);
    L10:
        return (Result) Preconditions.checkNotNull(r1);
    L11:
        th = move-exception;
        throw th;
    }

    private final void zab(Result r5) {
        this.zaj = r5;
        this.zak = r5.getStatus();
        this.zaf.countDown();
        if (this.zam == false) goto L5;
        this.zah = null;
    L11:
        ArrayList r52 = this.zag;
        int r0 = r52.size();
        int r1 = 0;
    L12:
        if (r1 >= r0) goto L14;
        ((PendingResult.StatusListener) r52.get(r1)).onComplete(this.zak);
        r1 = r1 + 1;
        goto L12
    L14:
        r52.clear();
        return;
    L5:
        ResultCallback r53 = this.zah;
        if (r53 == null) goto L8;
        CallbackHandler r02 = this.zab;
        r02.removeMessages(2);
        r02.zaa(r53, zaa());
        goto L11
    L8:
        if ((this.zaj instanceof Releasable) == false) goto L11;
        this.resultGuardian = new zar(this, null);
        goto L11
    }

    public static void zal(Result r3) {
        if ((r3 instanceof Releasable) == false) goto L11;
        ((Releasable) r3).release();     // Catch: RuntimeException -> L6
        return;
    L6:
        e = move-exception;
        Log.w("BasePendingResult", "Unable to release ".concat(String.valueOf(r3)), e);
        return;
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void addStatusListener(PendingResult.StatusListener r3) {
        if (r3 == null) goto L4;
        boolean r0 = true;
    L5:
        Preconditions.checkArgument(r0, "Callback cannot be null.");
        Object r02 = this.zae;
        monitor-enter(r02);
    L10:
        th = move-exception;
        throw th;
    L8:
        if (isReady() == false) goto L12;
        r3.onComplete(this.zak);     // Catch: Throwable -> L10
    L13:
        monitor-exit(r02);     // Catch: Throwable -> L10
        return;
    L12:
        this.zag.add(r3);     // Catch: Throwable -> L10
        goto L13
    L4:
        r0 = false;
        goto L5
    }

    public final R await() {
        Preconditions.checkNotMainThread("await must not be called on the UI thread");
        boolean r1 = true;
        Preconditions.checkState(!this.zal, "Result has already been consumed");
        if (this.zap == null) goto L6;
        r1 = false;
    L6:
        Preconditions.checkState(r1, "Cannot await if then() has been called.");
        this.zaf.await();     // Catch: InterruptedException -> L9
    L10:
        Preconditions.checkState(isReady(), "Result is not ready.");
        return (R) zaa();
    L9:
        forceFailureUnlessReady(Status.RESULT_INTERRUPTED);
        goto L10
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final R await(long r4, TimeUnit r6) {
        if (r4 <= 0) goto L5;
        Preconditions.checkNotMainThread("await must not be called on the UI thread when time is greater than zero.");
    L5:
        boolean r1 = true;
        Preconditions.checkState(!this.zal, "Result has already been consumed.");
        if (this.zap == null) goto L9;
        r1 = false;
    L9:
        Preconditions.checkState(r1, "Cannot await if then() has been called.");
    L14:
        forceFailureUnlessReady(Status.RESULT_INTERRUPTED);
    L15:
        Preconditions.checkState(isReady(), "Result is not ready.");
        return (R) zaa();
    L11:
        if (this.zaf.await(r4, r6) == true) goto L15;
        forceFailureUnlessReady(Status.RESULT_TIMEOUT);     // Catch: InterruptedException -> L14
        goto L15
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public void cancel() {
        Object r0 = this.zae;
        monitor-enter(r0);
    L12:
        th = move-exception;
        throw th;
    L5:
        if (this.zam == false) goto L7;
    L14:
        monitor-exit(r0);     // Catch: Throwable -> L12
        return;
    L7:
        if (this.zal == true) goto L14;
        zal(this.zaj);     // Catch: Throwable -> L12
        this.zam = true;     // Catch: Throwable -> L12
        zab(createFailedResult(Status.RESULT_CANCELED));     // Catch: Throwable -> L12
        monitor-exit(r0);     // Catch: Throwable -> L12
    }

    public abstract R createFailedResult(Status r1);

    @Deprecated
    public final void forceFailureUnlessReady(Status r3) {
        Object r0 = this.zae;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (isReady() == true) goto L9;
        setResult(createFailedResult(r3));     // Catch: Throwable -> L7
        this.zan = true;     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
    }

    public final boolean isCanceled() {
        Object r0 = this.zae;
        monitor-enter(r0);
        boolean r1 = this.zam;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public final boolean isReady() {
        if (this.zaf.getCount() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final void setCancelToken(ICancelToken r1) {
        Object r0 = this.zae;
        monitor-enter(r0);
        monitor-exit(r0);     // Catch: Throwable -> L6
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public final void setResult(R r4) {
        Object r0 = this.zae;
        monitor-enter(r0);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (this.zan == false) goto L7;
    L13:
        zal(r4);     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        return;
    L7:
        if (this.zam == true) goto L13;
        isReady();     // Catch: Throwable -> L11
        Preconditions.checkState(!isReady(), "Results have already been set");     // Catch: Throwable -> L11
        Preconditions.checkState(!this.zal, "Result has already been consumed");     // Catch: Throwable -> L11
        zab(r4);     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
    }

    @Override // com.google.android.gms.common.api.PendingResult
    public final void setResultCallback(ResultCallback<? super R> r5) {
        Object r0 = this.zae;
        monitor-enter(r0);
        if (r5 == null) goto L6;
        boolean r2 = true;
        Preconditions.checkState(!this.zal, "Result has already been consumed.");     // Catch: Throwable -> L9
        if (this.zap == null) goto L15;
        r2 = false;
    L15:
        Preconditions.checkState(r2, "Cannot set callbacks if then() has been called.");     // Catch: Throwable -> L9
        if (isCanceled() == false) goto L20;
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L20:
        if (isReady() == false) goto L22;
        this.zab.zaa(r5, zaa());     // Catch: Throwable -> L9
    L23:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L22:
        this.zah = r5;     // Catch: Throwable -> L9
        goto L23
    L6:
        this.zah = null;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public final void setResultCallback(ResultCallback<? super R> r5, long r6, TimeUnit r8) {
        Object r0 = this.zae;
        monitor-enter(r0);
        if (r5 == null) goto L6;
        boolean r2 = true;
        Preconditions.checkState(!this.zal, "Result has already been consumed.");     // Catch: Throwable -> L9
        if (this.zap == null) goto L15;
        r2 = false;
    L15:
        Preconditions.checkState(r2, "Cannot set callbacks if then() has been called.");     // Catch: Throwable -> L9
        if (isCanceled() == false) goto L20;
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L20:
        if (isReady() == false) goto L22;
        this.zab.zaa(r5, zaa());     // Catch: Throwable -> L9
    L23:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L22:
        this.zah = r5;     // Catch: Throwable -> L9
        CallbackHandler r52 = this.zab;     // Catch: Throwable -> L9
        r52.sendMessageDelayed(r52.obtainMessage(2, this), r8.toMillis(r6));     // Catch: Throwable -> L9
        goto L23
    L6:
        this.zah = null;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public final <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> r6) {
        Preconditions.checkState(!this.zal, "Result has already been consumed.");
        Object r0 = this.zae;
        monitor-enter(r0);
        boolean r3 = false;
        if (this.zap != null) goto L7;
        boolean r2 = true;
    L8:
        Preconditions.checkState(r2, "Cannot call then() twice.");     // Catch: Throwable -> L14
        if (this.zah != null) goto L11;
        r3 = true;
    L11:
        Preconditions.checkState(r3, "Cannot call then() if callbacks are set.");     // Catch: Throwable -> L14
        Preconditions.checkState(!this.zam, "Cannot call then() if result was canceled.");     // Catch: Throwable -> L14
        this.zaq = true;     // Catch: Throwable -> L14
        this.zap = new zacs(this.zac);     // Catch: Throwable -> L14
        TransformedResult<S> r62 = this.zap.then(r6);     // Catch: Throwable -> L14
        if (isReady() == false) goto L16;
        this.zab.zaa(this.zap, zaa());     // Catch: Throwable -> L14
    L17:
        monitor-exit(r0);     // Catch: Throwable -> L14
        return r62;
    L16:
        this.zah = this.zap;     // Catch: Throwable -> L14
        goto L17
    L7:
        r2 = false;
    L14:
        th = move-exception;
        throw th;
    }

    public final boolean zaj() {
        Object r0 = this.zae;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (((GoogleApiClient) this.zac.get()) != null) goto L7;
    L11:
        cancel();     // Catch: Throwable -> L9
    L12:
        boolean r2 = isCanceled();     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r2;
    L7:
        if (this.zaq == true) goto L12;
        goto L12
    }

    public final void zak() {
        boolean r1 = true;
        if (this.zaq == false) goto L5;
    L8:
        this.zaq = r1;
        return;
    L5:
        if (((Boolean) zaa.get()).booleanValue() == true) goto L8;
        r1 = false;
        goto L8
    }

    public final /* synthetic */ Result zam() {
        return this.zaj;
    }

    public final void zan(zact r1) {
        this.zai.set(r1);
    }
}
