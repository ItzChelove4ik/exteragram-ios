package com.exteragram.messenger.debug;

import android.app.Activity;
import android.os.Debug;
import android.os.SystemClock;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.concurrent.ThreadsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ForegroundDetector;
import org.telegram.ui.LaunchActivity;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001DB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0005\u0010\u0003J\u001f\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ#\u0010\u000f\u001a\u00020\u000e2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00040\u000bH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J3\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00040\u000bH\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0015\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001e\u001a\u00020\f¢\u0006\u0004\b\u001e\u0010\u001fJ\r\u0010 \u001a\u00020\f¢\u0006\u0004\b \u0010\u001fJ\u0015\u0010\"\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\f¢\u0006\u0004\b\"\u0010#J\r\u0010$\u001a\u00020\u0006¢\u0006\u0004\b$\u0010%J\u000f\u0010&\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b&\u0010'J\r\u0010(\u001a\u00020\u0006¢\u0006\u0004\b(\u0010%J\u001b\u0010+\u001a\u00020\u00042\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00040)¢\u0006\u0004\b+\u0010,J\u0015\u0010-\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b-\u0010.J\u001f\u00101\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00152\b\u00100\u001a\u0004\u0018\u00010/¢\u0006\u0004\b1\u00102J\u000f\u00104\u001a\u00020\u0004H\u0000¢\u0006\u0004\b3\u0010\u0003R\u0018\u00106\u001a\u0004\u0018\u0001058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u00107R\u0016\u00108\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b8\u00109R\u001e\u0010<\u001a\n\u0012\u0004\u0012\u00020;\u0018\u00010:8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b<\u0010=R\u001b\u0010A\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b>\u0010?\u001a\u0004\b@\u0010'R\u0011\u0010C\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\bB\u0010%¨\u0006E"}, d2 = {"Lcom/exteragram/messenger/debug/HeapMonitor;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "init", _UrlKt.FRAGMENT_ENCODE_SET, "used", "limit", "showLimitAlert", "(JJ)V", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "onProgress", "Ljava/io/File;", "dumpHeap", "(Lkotlin/jvm/functions/Function1;)Ljava/io/File;", "source", "target", "compress", "(Ljava/io/File;Ljava/io/File;Lkotlin/jvm/functions/Function1;)V", "Landroid/app/Activity;", "activity", "file", "share", "(Landroid/app/Activity;Ljava/io/File;)V", _UrlKt.FRAGMENT_ENCODE_SET, "enabled", "setEnabled", "(Z)V", "getLimitMb", "()I", "getMaxLimitMb", "limitMb", "setLimitMb", "(I)V", "getUsedBytes", "()J", "getLastDump", "()Ljava/io/File;", "getDumpsSize", "Lkotlin/Function0;", "onDone", "deleteDumps", "(Lkotlin/jvm/functions/Function0;)V", "shareLastDump", "(Landroid/app/Activity;)V", "Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;", "resourcesProvider", "dumpAndShare", "(Landroid/app/Activity;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V", "collectGarbage$TMessagesProj", "collectGarbage", "Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;", "watcher", "Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;", "dumping", "Z", "Ljava/lang/ref/WeakReference;", "Lorg/telegram/ui/ActionBar/AlertDialog;", "alertRef", "Ljava/lang/ref/WeakReference;", "dumpsDir$delegate", "Lkotlin/Lazy;", "getDumpsDir", "dumpsDir", "getPromptStepBytes", "promptStepBytes", "LimitWatcher", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nHeapMonitor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HeapMonitor.kt\ncom/exteragram/messenger/debug/HeapMonitor\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,308:1\n3938#2:309\n4474#2,2:310\n14048#2,2:327\n14048#2,2:329\n2045#3,14:312\n1#4:326\n*S KotlinDebug\n*F\n+ 1 HeapMonitor.kt\ncom/exteragram/messenger/debug/HeapMonitor\n*L\n96#1:309\n96#1:310,2\n200#1:327,2\n104#1:329,2\n97#1:312,14\n*E\n"})
/* loaded from: classes4.dex */
public final class HeapMonitor {
    public static final HeapMonitor INSTANCE = null;
    private static WeakReference<AlertDialog> alertRef;
    private static volatile boolean dumping;
    private static final Lazy dumpsDir$delegate = null;
    private static volatile LimitWatcher watcher;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u0006\u0010\u0010\u001a\u00020\u000eJ\u0006\u0010\u0011\u001a\u00020\u000eJ\u000e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000bJ\b\u0010\u0014\u001a\u00020\u000eH\u0002J\b\u0010\u0015\u001a\u00020\u000eH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/exteragram/messenger/debug/HeapMonitor$LimitWatcher;", "Lorg/telegram/ui/Components/ForegroundDetector$Listener;", "<init>", "()V", "queue", "Lorg/telegram/messenger/DispatchQueue;", "checkRunnable", "Ljava/lang/Runnable;", "running", _UrlKt.FRAGMENT_ENCODE_SET, "nextPromptBytes", _UrlKt.FRAGMENT_ENCODE_SET, "lastCollectTime", "onBecameForeground", _UrlKt.FRAGMENT_ENCODE_SET, "onBecameBackground", "stop", "resetPrompt", "postponePrompt", "usedBytes", "updateRunning", "check", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class LimitWatcher implements ForegroundDetector.Listener {
        private final Runnable checkRunnable;
        private long lastCollectTime;
        private long nextPromptBytes;
        private final DispatchQueue queue;
        private volatile boolean running;

        public static void $r8$lambda$1eef_Xx9_DcRVhqCQaqT1pw9pr8(LimitWatcher r0) {
            r0.check();
        }

        /* renamed from: $r8$lambda$Glvfehde9YHk6gQtUreR-r-Mb9g, reason: not valid java name */
        public static void m1246$r8$lambda$Glvfehde9YHk6gQtUreRrMb9g(long r1, long r3) {
            HeapMonitor.access$showLimitAlert(HeapMonitor.INSTANCE, r1, r3);
        }

        /* renamed from: $r8$lambda$TOO-MnxGNhTNPbLmNjNZLVNYFM8, reason: not valid java name */
        public static void m1247$r8$lambda$TOOMnxGNhTNPbLmNjNZLVNYFM8(LimitWatcher r3) {
            r3.queue.cancelRunnable(r3.checkRunnable);
            if (r3.running == false) goto L6;
            r3.queue.postRunnable(r3.checkRunnable, 5000);
            return;
        }

        /* renamed from: $r8$lambda$i0uVHpCZaV6c-3J4CwvOvyrTDcQ, reason: not valid java name */
        public static void m1248$r8$lambda$i0uVHpCZaV6c3J4CwvOvyrTDcQ(LimitWatcher r4, long r5) {
            r4.nextPromptBytes = Math.max(r4.nextPromptBytes, r5 + HeapMonitor.INSTANCE.getPromptStepBytes());
        }

        public static void $r8$lambda$sR_UlDrroxxv8iXPrMFg1erNAWw(LimitWatcher r2) {
            r2.nextPromptBytes = 0;
            r2.lastCollectTime = 0;
        }

        public LimitWatcher() {
            this.queue = new DispatchQueue("heapMonitorQueue");
            this.checkRunnable = new HeapMonitor$LimitWatcher$$ExternalSyntheticLambda0(this);
            ForegroundDetector r0 = ForegroundDetector.getInstance();
            if (r0 == null) goto L5;
            r0.addListener(this);
        L5:
            updateRunning();
        }

        private final void check() {
            if (this.running == false) goto L24;
            HeapMonitor r0 = HeapMonitor.INSTANCE;
            long r1 = r0.getLimitMb() << 20;
            long r3 = r0.getUsedBytes();
            if (r3 >= r1) goto L9;
            this.nextPromptBytes = 0;
        L20:
            if (this.running == false) goto L23;
            this.queue.postRunnable(this.checkRunnable, 5000);
            return;
        L23:
            return;
        L9:
            if (r3 < this.nextPromptBytes) goto L20;
            if (HeapMonitor.access$getDumping$p() == true) goto L20;
            if (ApplicationLoader.mainInterfacePaused == true) goto L20;
            if ((SystemClock.elapsedRealtime() - this.lastCollectTime) < NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) goto L20;
            this.lastCollectTime = SystemClock.elapsedRealtime();
            r0.collectGarbage$TMessagesProj();
            long r32 = r0.getUsedBytes();
            if (r32 < Math.max(r1, this.nextPromptBytes)) goto L20;
            this.nextPromptBytes = r0.getPromptStepBytes() + r32;
            AndroidUtilities.runOnUIThread(new HeapMonitor$LimitWatcher$$ExternalSyntheticLambda3(r32, r1));
            goto L20
        }

        private final void updateRunning() {
            ForegroundDetector r0 = ForegroundDetector.getInstance();
            boolean r1 = false;
            if (r0 == null) goto L8;
            if (r0.isForeground() != true) goto L8;
            r1 = true;
        L8:
            if (this.running != r1) goto L10;
            return;
        L10:
            this.running = r1;
            this.queue.postRunnable(new HeapMonitor$LimitWatcher$$ExternalSyntheticLambda1(this));
        }

        @Override // org.telegram.ui.Components.ForegroundDetector.Listener
        public void onBecameBackground() {
            updateRunning();
        }

        @Override // org.telegram.ui.Components.ForegroundDetector.Listener
        public void onBecameForeground() {
            updateRunning();
        }

        public final void postponePrompt(long r3) {
            this.queue.postRunnable(new HeapMonitor$LimitWatcher$$ExternalSyntheticLambda4(this, r3));
        }

        public final void resetPrompt() {
            this.queue.postRunnable(new HeapMonitor$LimitWatcher$$ExternalSyntheticLambda2(this));
        }

        public final void stop() {
            ForegroundDetector r0 = ForegroundDetector.getInstance();
            if (r0 == null) goto L5;
            r0.removeListener(this);
        L5:
            this.running = false;
            this.queue.cleanupQueue();
            this.queue.recycle();
        }
    }

    public static Unit $r8$lambda$4bQhVXYDNdN6H_VjUQxdVfUhGBo(AlertDialog r2, Activity r3) {
        SystemClock.sleep(300);
        HeapMonitor r0 = INSTANCE;
        Result.Companion r1 = Result.Companion;     // Catch: Throwable -> L5
        Object r02 = Result.m2018constructorimpl(r0.dumpHeap(new HeapMonitor$$ExternalSyntheticLambda4(r2)));     // Catch: Throwable -> L5
    L7:
        AndroidUtilities.runOnUIThread(new HeapMonitor$$ExternalSyntheticLambda5(r2, r02, r3));
        return Unit.INSTANCE;
    L5:
        th = move-exception;
        Result.Companion r12 = Result.Companion;
        r02 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L7
    }

    public static void $r8$lambda$5zrQMeJT7ZjO2ogkU3UeAGxPFBY(int r1, AlertDialog r2) {
        if (r1 != 0) goto L4;
        r2.setMessage("Compressing…");
    L4:
        r2.setProgress(r1);
    }

    public static Unit $r8$lambda$ONRMsNbfuk3xDA6TyEiuMcLOfCY(AlertDialog r1, int r2) {
        AndroidUtilities.runOnUIThread(new HeapMonitor$$ExternalSyntheticLambda8(r2, r1));
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$R6tXhA8smJy6OzCZSJ7-sboifr4, reason: not valid java name */
    public static void m1243$r8$lambda$R6tXhA8smJy6OzCZSJ7sboifr4(Function0 r4) {
        File[] r0 = INSTANCE.getDumpsDir().listFiles();
        if (r0 == null) goto L7;
        int r1 = r0.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L7;
        r0[r2].delete();
        r2 = r2 + 1;
    L7:
        AndroidUtilities.runOnUIThread(new HeapMonitor$$ExternalSyntheticLambda3(r4));
    }

    public static File $r8$lambda$SuKPShm5yaocICP1T8WTyS_VAZ4() {
        return DebugFilesKt.debugFilesDir("heapdumps");
    }

    public static void $r8$lambda$_SL_LURkOvouHUUv8lRwaIOGDz4(LaunchActivity r0, AlertDialog r1, int r2) {
        INSTANCE.dumpAndShare(r0, null);
    }

    public static void $r8$lambda$euY017UpxHSPivzCOWIKbGRnYdQ(AlertDialog r2, Object r3, Activity r4) {
        dumping = false;
        r2.dismiss();     // Catch: Exception -> L5
    L8:
        if (Result.m2025isSuccessimpl(r3) == false) goto L10;
        INSTANCE.share(r4, (File) r3);
    L10:
        Throwable r22 = Result.m2021exceptionOrNullimpl(r3);
        if (r22 == null) goto L16;
        FileLog.e(r22);
        BulletinFactory.global().createSimpleBulletin(R.raw.error, "Heap dump failed: " + r22).show();
        return;
    L16:
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    /* renamed from: $r8$lambda$hztOoxM3SZHZ7Awcb7ppNqu-12s, reason: not valid java name */
    public static void m1244$r8$lambda$hztOoxM3SZHZ7Awcb7ppNqu12s(AlertDialog r0, int r1) {
        INSTANCE.setEnabled(false);
    }

    /* renamed from: $r8$lambda$lHyX6_s-ZCLcuzAxtweoZ59QQ5M, reason: not valid java name */
    public static void m1245$r8$lambda$lHyX6_sZCLcuzAxtweoZ59QQ5M(Function0 r0) {
        r0.invoke();
    }

    static {
        INSTANCE = new HeapMonitor();
        dumpsDir$delegate = LazyKt.lazy(new HeapMonitor$$ExternalSyntheticLambda0());
    }

    private HeapMonitor() {
    }

    public static final /* synthetic */ boolean access$getDumping$p() {
        return dumping;
    }

    public static final /* synthetic */ void access$showLimitAlert(HeapMonitor r0, long r1, long r3) {
        r0.showLimitAlert(r1, r3);
    }

    private final void compress(File r9, File r10, Function1<? super Integer, Unit> r11) {
        long r0 = Math.max(1, r9.length());
        r11.invoke(0);
        ZipOutputStream r2 = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(r10), 65536));
        r2.setLevel(1);     // Catch: Throwable -> L17
        r2.putNextEntry(new ZipEntry(r9.getName()));     // Catch: Throwable -> L17
        FileInputStream r3 = new FileInputStream(r9);     // Catch: Throwable -> L17
        byte[] r92 = new byte[65536];     // Catch: Throwable -> L10
        long r4 = 0;
        int r102 = 0;
    L5:
        int r6 = r3.read(r92);     // Catch: Throwable -> L10
        if (r6 < 0) goto L12;
        r2.write(r92, 0, r6);     // Catch: Throwable -> L10
        r4 = r4 + r6;     // Catch: Throwable -> L10
        int r62 = (int) ((100 * r4) / r0);     // Catch: Throwable -> L10
        if (r62 == r102) goto L5;
        r11.invoke(Integer.valueOf(r62));     // Catch: Throwable -> L10
        r102 = r62;
        goto L5
    L12:
        Unit r8 = Unit.INSTANCE;     // Catch: Throwable -> L10
    L14:
        CloseableKt.closeFinally(r3, null);     // Catch: Throwable -> L17
        r2.closeEntry();     // Catch: Throwable -> L17
        CloseableKt.closeFinally(r2, null);
        return;
    L10:
        th = move-exception;
        throw th;     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        CloseableKt.closeFinally(r3, th);     // Catch: Throwable -> L17
        throw th;     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        throw th;     // Catch: Throwable -> L24
    L24:
        th = move-exception;
        CloseableKt.closeFinally(r2, th);
        throw th;
    }

    private final File dumpHeap(Function1<? super Integer, Unit> r8) {
        getDumpsDir().mkdirs();
        String r1 = "heap_" + BuildVars.BUILD_VERSION_STRING + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        File r2 = new File(getDumpsDir(), r1.concat(".hprof"));
        File r3 = new File(getDumpsDir(), r1.concat(".zip.part"));
        File r4 = new File(getDumpsDir(), r1.concat(".zip"));
        collectGarbage$TMessagesProj();     // Catch: Throwable -> L6
        LimitWatcher r12 = watcher;     // Catch: Throwable -> L6
        if (r12 == null) goto L8;
        r12.postponePrompt(getUsedBytes());     // Catch: Throwable -> L6
    L8:
        Debug.dumpHprofData(r2.getAbsolutePath());     // Catch: Throwable -> L6
        compress(r2, r3, r8);     // Catch: Throwable -> L6
        if (r3.renameTo(r4) == false) goto L20;
        r2.delete();
        r3.delete();
        File[] r7 = getDumpsDir().listFiles();
        if (r7 == null) goto L18;
        int r82 = r7.length;
        int r0 = 0;
    L13:
        if (r0 >= r82) goto L18;
        File r13 = r7[r0];
        if (Intrinsics.areEqual(r13, r4) == true) goto L17;
        r13.delete();
    L17:
        r0 = r0 + 1;
    L18:
        return r4;
    L20:
        throw new IOException("Can't rename " + r3.getName());     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        r2.delete();
        r3.delete();
        throw th;
    }

    @JvmStatic
    public static final void init() {
        if (DebugConfig.getHeapMonitorEnabled() == false) goto L6;
        watcher = new LimitWatcher();
        return;
    }

    private final void share(Activity r2, File r3) {
        DebugFilesKt.shareDebugFile(r2, r3, "application/zip", "Share heap dump");
    }

    private final void showLimitAlert(long r6, long r8) {
        LaunchActivity r5 = LaunchActivity.instance;
        if (watcher != null) goto L5;
        return;
    L5:
        if (dumping == true) goto L25;
        if (r5 != null) goto L8;
        return;
    L8:
        if (r5.isFinishing() == true) goto L27;
        WeakReference<AlertDialog> r0 = alertRef;
        if (r0 == null) goto L16;
        AlertDialog r02 = r0.get();
        if (r02 == null) goto L16;
        if (r02.isShowing() != true) goto L16;
        return;
    L16:
        long r2 = Runtime.getRuntime().maxMemory();
        AlertDialog r52 = new AlertDialog.Builder(r5).setTitle("Heap limit exceeded").setMessage("Java heap is at " + AndroidUtilities.formatFileSize(r6, true, false) + " of " + AndroidUtilities.formatFileSize(r2, true, false) + " after GC, over the " + AndroidUtilities.formatFileSize(r8, true, false) + " limit.\n\nThe app freezes while dumping. The dump holds everything in memory, so share it only with people you trust.").setPositiveButton("Dump & share", new HeapMonitor$$ExternalSyntheticLambda6(r5)).setNeutralButton("Turn off", new HeapMonitor$$ExternalSyntheticLambda7()).setNegativeButton("Later", null).create();
        alertRef = new WeakReference(r52);
        r52.show();     // Catch: Exception -> L19
        return;
    L19:
        e = move-exception;
        FileLog.e(e);
        return;
    L27:
        return;
    }

    public final void collectGarbage$TMessagesProj() {
        Runtime r0 = Runtime.getRuntime();
        r0.gc();
        r0.runFinalization();
        r0.gc();
    }

    public final void deleteDumps(Function0<Unit> r2) {
        if (dumping == false) goto L5;
        return;
    L5:
        Utilities.globalQueue.postRunnable(new HeapMonitor$$ExternalSyntheticLambda1(r2));
    }

    public final void dumpAndShare(Activity r9, Theme.ResourcesProvider r10) {
        if (dumping == false) goto L5;
        return;
    L5:
        dumping = true;
        AlertDialog r8 = new AlertDialog(r9, 2, r10);
        r8.setMessage("Dumping heap…");
        r8.setCanceledOnTouchOutside(false);
        r8.setCancelable(false);
        r8.show();
        ThreadsKt.thread$default(false, false, null, "heapDump", 0, new HeapMonitor$$ExternalSyntheticLambda2(r8, r9), 23, null);
    }

    public final File getDumpsDir() {
        return (File) dumpsDir$delegate.getValue();
    }

    public final long getDumpsSize() {
        File[] r6 = getDumpsDir().listFiles();
        long r0 = 0;
        if (r6 == null) goto L7;
        int r2 = r6.length;
        int r3 = 0;
    L5:
        if (r3 >= r2) goto L7;
        r0 = r0 + r6[r3].length();
        r3 = r3 + 1;
    L7:
        return r0;
    }

    public final File getLastDump() {
        File[] r9 = getDumpsDir().listFiles();
        Object r0 = null;
        if (r9 == null) goto L29;
        ArrayList r1 = new ArrayList();
        int r2 = r9.length;
        int r4 = 0;
    L5:
        if (r4 >= r2) goto L10;
        File r5 = r9[r4];
        if (StringsKt.endsWith$default(r5.getName(), ".zip", false, 2, null) == false) goto L9;
        r1.add(r5);
    L9:
        r4 = r4 + 1;
        goto L5
    L10:
        Iterator r92 = r1.iterator();
        if (r92.hasNext() == false) goto L23;
        r0 = r92.next();
        if (r92.hasNext() == false) goto L23;
        long r12 = ((File) r0).lastModified();
    L17:
        Object r3 = r92.next();
        long r42 = ((File) r3).lastModified();
        if (r12 >= r42) goto L21;
        r0 = r3;
        r12 = r42;
    L21:
        if (r92.hasNext() == true) goto L17;
    L23:
        return (File) r0;
    L29:
        return null;
    }

    public final int getLimitMb() {
        return RangesKt.coerceIn(DebugConfig.getHeapMonitorLimitMb(), 64, getMaxLimitMb());
    }

    public final int getMaxLimitMb() {
        return Math.max(64, ((((int) (Runtime.getRuntime().maxMemory() >> 20)) / 16) - 1) * 16);
    }

    public final long getPromptStepBytes() {
        return Runtime.getRuntime().maxMemory() / 8;
    }

    public final long getUsedBytes() {
        Runtime r4 = Runtime.getRuntime();
        return r4.totalMemory() - r4.freeMemory();
    }

    public final void setEnabled(boolean r1) {
        if (DebugConfig.getHeapMonitorEnabled() != r1) goto L5;
        return;
    L5:
        DebugConfig.setHeapMonitorEnabled(r1);
        LimitWatcher r0 = watcher;
        if (r0 == null) goto L8;
        r0.stop();
    L8:
        if (r1 == false) goto L10;
        LimitWatcher r02 = new LimitWatcher();
    L11:
        watcher = r02;
        return;
    L10:
        r02 = null;
        goto L11
    }

    public final void setLimitMb(int r1) {
        DebugConfig.setHeapMonitorLimitMb(r1);
        LimitWatcher r0 = watcher;
        if (r0 == null) goto L6;
        r0.resetPrompt();
        return;
    }

    public final void shareLastDump(Activity r2) {
        File r1 = getLastDump();
        if (r1 == null) goto L6;
        INSTANCE.share(r2, r1);
        return;
    }
}
