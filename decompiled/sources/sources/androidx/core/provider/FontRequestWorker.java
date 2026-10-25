package androidx.core.provider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import androidx.camera.core.ImageCapture$$ExternalSyntheticBackport0;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.provider.FontsContractCompat;
import androidx.core.util.Consumer;
import androidx.tracing.Trace;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* loaded from: classes3.dex */
abstract class FontRequestWorker {
    private static final ExecutorService DEFAULT_EXECUTOR_SERVICE = null;
    static final Object LOCK = null;
    static final SimpleArrayMap<String, ArrayList<Consumer<TypefaceResult>>> PENDING_REPLIES = null;
    static final LruCache<String, Typeface> sTypefaceCache = null;

    /* renamed from: androidx.core.provider.FontRequestWorker$1, reason: invalid class name */
    public class AnonymousClass1 implements Callable<TypefaceResult> {
        final /* synthetic */ Context val$context;
        final /* synthetic */ String val$id;
        final /* synthetic */ FontRequest val$request;
        final /* synthetic */ int val$style;

        public AnonymousClass1(String r1, Context r2, FontRequest r3, int r4) {
            this.val$id = r1;
            this.val$context = r2;
            this.val$request = r3;
            this.val$style = r4;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public TypefaceResult call() {
            return FontRequestWorker.getFontSync(this.val$id, this.val$context, ImageCapture$$ExternalSyntheticBackport0.m(this.val$request), this.val$style);
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ TypefaceResult call() {
            return call();
        }
    }

    /* renamed from: androidx.core.provider.FontRequestWorker$2, reason: invalid class name */
    public class AnonymousClass2 implements Consumer<TypefaceResult> {
        final /* synthetic */ CallbackWrapper val$callback;

        public AnonymousClass2(CallbackWrapper r1) {
            this.val$callback = r1;
        }

        /* renamed from: accept, reason: avoid collision after fix types in other method */
        public void accept2(TypefaceResult r2) {
            if (r2 != null) goto L4;
            r2 = new TypefaceResult(-3);
        L4:
            this.val$callback.onTypefaceResult(r2);
        }

        @Override // androidx.core.util.Consumer
        public /* bridge */ /* synthetic */ void accept(TypefaceResult r1) {
            accept2(r1);
        }
    }

    /* renamed from: androidx.core.provider.FontRequestWorker$3, reason: invalid class name */
    public class AnonymousClass3 implements Callable<TypefaceResult> {
        final /* synthetic */ Context val$context;
        final /* synthetic */ String val$id;
        final /* synthetic */ List val$requests;
        final /* synthetic */ int val$style;

        public AnonymousClass3(String r1, Context r2, List r3, int r4) {
            this.val$id = r1;
            this.val$context = r2;
            this.val$requests = r3;
            this.val$style = r4;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public TypefaceResult call() {
            return FontRequestWorker.getFontSync(this.val$id, this.val$context, this.val$requests, this.val$style);
        L5:
            return new TypefaceResult(-3);
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ TypefaceResult call() {
            return call();
        }
    }

    /* renamed from: androidx.core.provider.FontRequestWorker$4, reason: invalid class name */
    public class AnonymousClass4 implements Consumer<TypefaceResult> {
        final /* synthetic */ String val$id;

        public AnonymousClass4(String r1) {
            this.val$id = r1;
        }

        /* renamed from: accept, reason: avoid collision after fix types in other method */
        public void accept2(TypefaceResult r4) {
            Object r0 = FontRequestWorker.LOCK;
            monitor-enter(r0);
            SimpleArrayMap<String, ArrayList<Consumer<TypefaceResult>>> r1 = FontRequestWorker.PENDING_REPLIES;     // Catch: Throwable -> L8
            ArrayList<Consumer<TypefaceResult>> r2 = r1.get(this.val$id);     // Catch: Throwable -> L8
            if (r2 != null) goto L10;
            monitor-exit(r0);     // Catch: Throwable -> L8
            return;
        L10:
            r1.remove(this.val$id);     // Catch: Throwable -> L8
            monitor-exit(r0);     // Catch: Throwable -> L8
            int r3 = 0;
        L14:
            if (r3 >= r2.size()) goto L16;
            r2.get(r3).accept(r4);
            r3 = r3 + 1;
            goto L14
        L16:
            return;
        L8:
            th = move-exception;
            throw th;
        }

        @Override // androidx.core.util.Consumer
        public /* bridge */ /* synthetic */ void accept(TypefaceResult r1) {
            accept2(r1);
        }
    }

    public static final class TypefaceResult {
        final int mResult;
        final Typeface mTypeface;

        public TypefaceResult(int r2) {
            this.mTypeface = null;
            this.mResult = r2;
        }

        @SuppressLint({"WrongConstant"})
        public TypefaceResult(Typeface r1) {
            this.mTypeface = r1;
            this.mResult = 0;
        }

        @SuppressLint({"WrongConstant"})
        public boolean isSuccess() {
            if (this.mResult != 0) goto L6;
            return true;
        L6:
            return false;
        }
    }

    static {
        sTypefaceCache = new LruCache(16);
        DEFAULT_EXECUTOR_SERVICE = RequestExecutor.createDefaultExecutor("fonts-androidx", 10, 10000);
        LOCK = new Object();
        PENDING_REPLIES = new SimpleArrayMap();
    }

    private static String createCacheId(List<FontRequest> r3, int r4) {
        StringBuilder r0 = new StringBuilder();
        int r1 = 0;
    L4:
        if (r1 >= r3.size()) goto L10;
        r0.append(r3.get(r1).getId());
        r0.append("-");
        r0.append(r4);
        if (r1 >= (r3.size() - 1)) goto L8;
        r0.append(";");
    L8:
        r1 = r1 + 1;
        goto L4
    L10:
        return r0.toString();
    }

    @SuppressLint({"WrongConstant"})
    private static int getFontFamilyResultStatus(FontsContractCompat.FontFamilyResult r5) {
        int r2 = 1;
        if (r5.getStatusCode() != 0) goto L5;
        FontsContractCompat.FontInfo[] r52 = r5.getFonts();
        if (r52 != null) goto L12;
    L22:
        return r2;
    L12:
        if (r52.length == 0) goto L22;
        int r0 = r52.length;
        r2 = 0;
        int r3 = 0;
    L15:
        if (r3 >= r0) goto L22;
        int r4 = r52[r3].getResultCode();
        if (r4 != 0) goto L18;
        r3 = r3 + 1;
        goto L15
    L18:
        if (r4 >= 0) goto L20;
        return -3;
    L20:
        return r4;
    L5:
        if (r5.getStatusCode() == 1) goto L7;
        return -3;
    L7:
        return -2;
    }

    public static TypefaceResult getFontSync(String r4, Context r5, List<FontRequest> r6, int r7) {
        Trace.beginSection("getFontSync");
        LruCache<String, Typeface> r0 = sTypefaceCache;     // Catch: Throwable -> L31
        Typeface r1 = r0.get(r4);     // Catch: Throwable -> L31
        if (r1 == null) goto L34;
        TypefaceResult r42 = new TypefaceResult(r1);     // Catch: Throwable -> L31
        Trace.endSection();
        return r42;
    L34:
        FontsContractCompat.FontFamilyResult r62 = FontProvider.getFontFamilyResult(r5, r6, null);     // Catch: PackageManager.NameNotFoundException -> L28 Throwable -> L31
        int r2 = getFontFamilyResultStatus(r62);     // Catch: Throwable -> L31
        if (r2 == 0) goto L16;
        TypefaceResult r43 = new TypefaceResult(r2);     // Catch: Throwable -> L31
        Trace.endSection();
        return r43;
    L16:
        if (r62.hasFallback() == true) goto L18;
    L20:
        Typeface r52 = TypefaceCompat.createFromFontInfo(r5, null, r62.getFonts(), r7);     // Catch: Throwable -> L31
    L21:
        if (r52 == null) goto L25;
        r0.put(r4, r52);     // Catch: Throwable -> L31
        TypefaceResult r44 = new TypefaceResult(r52);     // Catch: Throwable -> L31
        Trace.endSection();
        return r44;
    L25:
        TypefaceResult r45 = new TypefaceResult(-3);     // Catch: Throwable -> L31
        Trace.endSection();
        return r45;
    L18:
        if (Build.VERSION.SDK_INT < 29) goto L20;
        r52 = TypefaceCompat.createFromFontInfoWithFallback(r5, null, r62.getFontsWithFallbacks(), r7);     // Catch: Throwable -> L31
        goto L21
    L28:
        TypefaceResult r46 = new TypefaceResult(-1);     // Catch: Throwable -> L31
        Trace.endSection();
        return r46;
    L31:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    public static Typeface requestFontAsync(Context r5, List<FontRequest> r6, int r7, Executor r8, CallbackWrapper r9) {
        String r0 = createCacheId(r6, r7);
        Typeface r1 = sTypefaceCache.get(r0);
        if (r1 == null) goto L6;
        r9.onTypefaceResult(new TypefaceResult(r1));
        return r1;
    L6:
        AnonymousClass2 r12 = new AnonymousClass2(r9);
        Object r92 = LOCK;
        monitor-enter(r92);
        SimpleArrayMap<String, ArrayList<Consumer<TypefaceResult>>> r2 = PENDING_REPLIES;     // Catch: Throwable -> L13
        ArrayList<Consumer<TypefaceResult>> r3 = r2.get(r0);     // Catch: Throwable -> L13
        if (r3 == null) goto L15;
        r3.add(r12);     // Catch: Throwable -> L13
        monitor-exit(r92);     // Catch: Throwable -> L13
        return null;
    L15:
        ArrayList<Consumer<TypefaceResult>> r32 = new ArrayList();     // Catch: Throwable -> L13
        r32.add(r12);     // Catch: Throwable -> L13
        r2.put(r0, r32);     // Catch: Throwable -> L13
        monitor-exit(r92);     // Catch: Throwable -> L13
        AnonymousClass3 r93 = new AnonymousClass3(r0, r5, r6, r7);
        if (r8 != null) goto L20;
        r8 = DEFAULT_EXECUTOR_SERVICE;
    L20:
        RequestExecutor.execute(r8, r93, new AnonymousClass4(r0));
        return null;
    L13:
        th = move-exception;
        throw th;
    }

    public static Typeface requestFontSync(Context r2, FontRequest r3, CallbackWrapper r4, int r5, int r6) {
        String r0 = createCacheId(ImageCapture$$ExternalSyntheticBackport0.m(r3), r5);
        Typeface r1 = sTypefaceCache.get(r0);
        if (r1 == null) goto L7;
        r4.onTypefaceResult(new TypefaceResult(r1));
        return r1;
    L7:
        if (r6 != (-1)) goto L10;
        TypefaceResult r22 = getFontSync(r0, r2, ImageCapture$$ExternalSyntheticBackport0.m(r3), r5);
        r4.onTypefaceResult(r22);
        return r22.mTypeface;
    L10:
        AnonymousClass1 r12 = new AnonymousClass1(r0, r2, r3, r5);
        TypefaceResult r23 = (TypefaceResult) RequestExecutor.submit(DEFAULT_EXECUTOR_SERVICE, r12, r6);     // Catch: InterruptedException -> L13
        r4.onTypefaceResult(r23);     // Catch: InterruptedException -> L13
        return r23.mTypeface;
    L13:
        r4.onTypefaceResult(new TypefaceResult(-3));
        return null;
    }
}
