package org.chromium.net.impl;

import com.google.android.gms.cast.MediaStatus;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.metrics.ScopedSysTraceEvent;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UploadDataSink;
import org.chromium.net.impl.JavaUrlRequestUtils;
import org.telegram.ui.WearAuthSheet$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class JavaUploadDataSinkBase extends UploadDataSink {
    private ByteBuffer mBuffer;
    private final Executor mExecutor;
    private int mReadCount;
    private final AtomicInteger mSinkState;
    private long mTotalBytes;
    private final VersionSafeCallbacks$UploadDataProviderWrapper mUploadProvider;
    private final Executor mUserUploadExecutor;
    private long mWrittenBytes;

    /* renamed from: org.chromium.net.impl.JavaUploadDataSinkBase$1, reason: invalid class name */
    public class AnonymousClass1 implements Executor {
        final /* synthetic */ JavaUploadDataSinkBase this$0;
        final /* synthetic */ Executor val$userExecutor;

        public AnonymousClass1(JavaUploadDataSinkBase r1, Executor r2) {
            this.val$userExecutor = r2;
            this.this$0 = r1;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable r2) {
            this.val$userExecutor.execute(r2);     // Catch: RejectedExecutionException -> L4
            return;
        L4:
            e = move-exception;
            this.this$0.processUploadError(e);
        }
    }

    /* renamed from: $r8$lambda$A9-kPLqeDYWQiy-PsztmQeDMg3o, reason: not valid java name */
    public static /* synthetic */ void m3804$r8$lambda$A9kPLqeDYWQiyPsztmQeDMg3o(JavaUploadDataSinkBase r0) {
        r0.lambda$startRead$1();
    }

    public static /* synthetic */ void $r8$lambda$DbE3blPi6UGIsevjBlzXKVCuX1Y(JavaUploadDataSinkBase r0, boolean r1) {
        r0.lambda$onReadSucceeded$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$Skz_oWstgCsE5yV2p_g6vrBh_QI(String r2, Runnable r3) {
        ScopedSysTraceEvent r22 = ScopedSysTraceEvent.scoped("JavaUploadDataSinkBase#executeOnExecutor " + r2 + " running callback");
        r3.run();     // Catch: Throwable -> L7
        if (r22 == null) goto L18;
        r22.close();
        return;
    L18:
        return;
    L7:
        th = move-exception;
        if (r22 != null) goto L16;
    L13:
        throw th;
    L16:
        r22.close();     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        th.addSuppressed(th);
        goto L13
    }

    /* renamed from: $r8$lambda$THk-oE5eTe5Y90I_YQZikeVqrI4, reason: not valid java name */
    public static /* synthetic */ void m3805$r8$lambda$THkoE5eTe5Y90I_YQZikeVqrI4(JavaUploadDataSinkBase r0, String r1, JavaUrlRequestUtils.CheckedRunnable r2) {
        r0.lambda$executeOnUploadExecutor$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ZABnqKHUhxos_emnBkBH8Agmjts(JavaUploadDataSinkBase r0) {
        r0.lambda$readFromProvider$3();
    }

    /* renamed from: $r8$lambda$Zs_uXTVqIwC-sg8cGt-9I-Wb3F8, reason: not valid java name */
    public static /* synthetic */ void m3806$r8$lambda$Zs_uXTVqIwCsg8cGt9IWb3F8(JavaUploadDataSinkBase r0, boolean r1) {
        r0.lambda$start$6(r1);
    }

    /* renamed from: $r8$lambda$r9dTVjHf2TChuPiPA7KVdo-6VVo, reason: not valid java name */
    public static /* synthetic */ void m3807$r8$lambda$r9dTVjHf2TChuPiPA7KVdo6VVo(JavaUploadDataSinkBase r0) {
        r0.lambda$readFromProvider$2();
    }

    public JavaUploadDataSinkBase(Executor r3, Executor r4, UploadDataProvider r5) {
        this.mSinkState = new AtomicInteger(3);
        this.mUserUploadExecutor = new AnonymousClass1(this, r3);
        this.mExecutor = r4;
        this.mUploadProvider = new VersionSafeCallbacks$UploadDataProviderWrapper(r5);
    }

    private void executeOnExecutor(Runnable r3, String r4) {
        ScopedSysTraceEvent r0 = ScopedSysTraceEvent.scoped("JavaUploadDataSinkBase#executeOnExecutor " + r4);
        this.mExecutor.execute(new JavaUploadDataSinkBase$$ExternalSyntheticLambda4(r4, r3));     // Catch: Throwable -> L7
        if (r0 == null) goto L18;
        r0.close();
        return;
    L18:
        return;
    L7:
        th = move-exception;
        if (r0 != null) goto L16;
    L13:
        throw th;
    L16:
        r0.close();     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        th.addSuppressed(th);
        goto L13
    }

    private void executeOnUploadExecutor(JavaUrlRequestUtils.CheckedRunnable r4, String r5) {
        ScopedSysTraceEvent r0 = ScopedSysTraceEvent.scoped("Cronet JavaUploadDataSinkBase#executeOnUploadExecutor " + r5);     // Catch: RejectedExecutionException -> L8
        this.mUserUploadExecutor.execute(new JavaUploadDataSinkBase$$ExternalSyntheticLambda2(this, r5, r4));     // Catch: Throwable -> L11
        if (r0 == null) goto L10;
        r0.close();     // Catch: RejectedExecutionException -> L8
        return;
    L10:
        return;
    L11:
        th = move-exception;
        if (r0 != null) goto L22;
    L17:
        throw th;     // Catch: RejectedExecutionException -> L8
    L22:
        r0.close();     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        th.addSuppressed(th);     // Catch: RejectedExecutionException -> L8
    L8:
        e = move-exception;
        processUploadError(e);
    }

    private /* synthetic */ void lambda$executeOnUploadExecutor$5(String r3, JavaUrlRequestUtils.CheckedRunnable r4) {
        ScopedSysTraceEvent r32 = ScopedSysTraceEvent.scoped("Cronet JavaUploadDataSinkBase#executeOnUploadExecutor " + r3 + " running callback");
        getUploadErrorSettingRunnable(r4).run();     // Catch: Throwable -> L7
        if (r32 == null) goto L18;
        r32.close();
        return;
    L18:
        return;
    L7:
        th = move-exception;
        if (r32 != null) goto L16;
    L13:
        throw th;
    L16:
        r32.close();     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        th.addSuppressed(th);
        goto L13
    }

    private /* synthetic */ void lambda$onReadSucceeded$0(boolean r9) {
        ByteBuffer r0 = (ByteBuffer) this.mBuffer.flip();
        long r02 = this.mTotalBytes;
        if (r02 == (-1)) goto L9;
        if ((r02 - this.mWrittenBytes) >= this.mBuffer.remaining()) goto L9;
        processUploadError(new IllegalArgumentException(String.format(Locale.getDefault(), "Read upload data length %d exceeds expected length %d", new Object[]{Long.valueOf(this.mWrittenBytes + this.mBuffer.remaining()), Long.valueOf(this.mTotalBytes)})));
        return;
    L9:
        if (this.mBuffer.remaining() != 0) goto L13;
        if (r9 == true) goto L13;
        processUploadError(new IllegalStateException("Bytes read can't be zero except for last chunk!"));
        return;
    L13:
        long r03 = this.mWrittenBytes + processSuccessfulRead(this.mBuffer);
        this.mWrittenBytes = r03;
        long r6 = this.mTotalBytes;
        if (r03 >= r6) goto L16;
    L29:
        ByteBuffer r92 = (ByteBuffer) this.mBuffer.clear();
        this.mSinkState.set(0);
        readFromProvider();
        return;
    L16:
        if (r6 != (-1)) goto L20;
        if (r9 == false) goto L29;
    L20:
        if (r6 != (-1)) goto L24;
        finish();
        return;
    L24:
        if (r6 != r03) goto L27;
        finish();
        return;
    L27:
        processUploadError(new IllegalArgumentException(String.format(Locale.getDefault(), "Read upload data length %d exceeds expected length %d", new Object[]{Long.valueOf(this.mWrittenBytes), Long.valueOf(this.mTotalBytes)})));
    }

    private /* synthetic */ void lambda$readFromProvider$2() {
        this.mReadCount++;
    }

    private /* synthetic */ void lambda$readFromProvider$3() {
        this.mUploadProvider.read(this, this.mBuffer);
        this.mExecutor.execute(new JavaUploadDataSinkBase$$ExternalSyntheticLambda6(this));
    }

    private /* synthetic */ void lambda$start$6(boolean r7) {
        long r0 = this.mUploadProvider.getLength();
        this.mTotalBytes = r0;
        if (r0 != 0) goto L7;
        finish();
        return;
    L7:
        if (r0 > 0) goto L9;
    L11:
        this.mBuffer = ByteBuffer.allocateDirect(8192);
    L12:
        initializeStart(this.mTotalBytes);
        if (r7 == false) goto L16;
        startRead();
        return;
    L16:
        this.mSinkState.set(1);
        this.mUploadProvider.rewind(this);
        return;
    L9:
        if (r0 >= MediaStatus.COMMAND_PLAYBACK_RATE) goto L11;
        this.mBuffer = ByteBuffer.allocateDirect(((int) r0) + 1);
        goto L12
    }

    private /* synthetic */ void lambda$startRead$1() {
        initializeRead();
        this.mSinkState.set(0);
        readFromProvider();
    }

    private void readFromProvider() {
        executeOnUploadExecutor(new JavaUploadDataSinkBase$$ExternalSyntheticLambda5(this), "readFromProvider");
    }

    private void startRead() {
        executeOnExecutor(getErrorSettingRunnable(new JavaUploadDataSinkBase$$ExternalSyntheticLambda3(this)), "startRead");
    }

    public abstract void finish();

    public abstract Runnable getErrorSettingRunnable(JavaUrlRequestUtils.CheckedRunnable r1);

    public int getReadCount() {
        return this.mReadCount;
    }

    public abstract Runnable getUploadErrorSettingRunnable(JavaUrlRequestUtils.CheckedRunnable r1);

    public abstract void initializeRead();

    public abstract void initializeStart(long r1);

    @Override // org.chromium.net.UploadDataSink
    public void onReadSucceeded(boolean r4) {
        if (this.mSinkState.compareAndSet(0, 2) == false) goto L6;
        executeOnExecutor(getErrorSettingRunnable(new JavaUploadDataSinkBase$$ExternalSyntheticLambda1(this, r4)), "onReadSucceeded");
        return;
    L6:
        WearAuthSheet$$ExternalSyntheticBUOutline0.m("onReadSucceeded() called when not awaiting a read result; in state: ", this.mSinkState.get());
    }

    @Override // org.chromium.net.UploadDataSink
    public void onRewindSucceeded() {
        if (this.mSinkState.compareAndSet(1, 2) == false) goto L6;
        startRead();
        return;
    L6:
        WearAuthSheet$$ExternalSyntheticBUOutline0.m("onRewindSucceeded() called when not awaiting a rewind; in state: ", this.mSinkState.get());
    }

    public abstract int processSuccessfulRead(ByteBuffer r1);

    public abstract void processUploadError(Throwable r1);

    public void start(boolean r2) {
        executeOnUploadExecutor(new JavaUploadDataSinkBase$$ExternalSyntheticLambda0(this, r2), "start");
    }
}
