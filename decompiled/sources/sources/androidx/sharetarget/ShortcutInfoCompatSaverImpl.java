package androidx.sharetarget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.concurrent.futures.ResolvableFuture;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutInfoCompatSaver;
import androidx.core.graphics.drawable.IconCompat;
import androidx.sharetarget.ShortcutsInfoSerialization;
import c.a$$ExternalSyntheticBUOutline0;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class ShortcutInfoCompatSaverImpl extends ShortcutInfoCompatSaver<ListenableFuture<Void>> {
    private static final Object GET_INSTANCE_LOCK = null;
    private static volatile ShortcutInfoCompatSaverImpl sInstance;
    final File mBitmapsDir;
    final ExecutorService mCacheUpdateService;
    final Context mContext;
    private final ExecutorService mDiskIoService;
    final Map<String, ListenableFuture<?>> mScheduledBitmapTasks;
    final Map<String, ShortcutsInfoSerialization.ShortcutContainer> mShortcutsMap;
    final File mTargetsXmlFile;

    /* renamed from: androidx.sharetarget.ShortcutInfoCompatSaverImpl$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ ShortcutInfoCompatSaverImpl this$0;
        final /* synthetic */ File val$workingDirectory;

        public AnonymousClass1(ShortcutInfoCompatSaverImpl r1, File r2) {
            this.this$0 = r1;
            this.val$workingDirectory = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            ShortcutInfoCompatSaverImpl.ensureDir(this.val$workingDirectory);     // Catch: Exception -> L4
            ShortcutInfoCompatSaverImpl.ensureDir(this.this$0.mBitmapsDir);     // Catch: Exception -> L4
            ShortcutInfoCompatSaverImpl r0 = this.this$0;     // Catch: Exception -> L4
            r0.mShortcutsMap.putAll(ShortcutsInfoSerialization.loadFromXml(r0.mTargetsXmlFile, r0.mContext));     // Catch: Exception -> L4
            this.this$0.deleteDanglingBitmaps(new ArrayList(this.this$0.mShortcutsMap.values()));     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            Log.w("ShortcutInfoCompatSaver", "ShortcutInfoCompatSaver started with an exceptions ", e);
        }
    }

    /* renamed from: androidx.sharetarget.ShortcutInfoCompatSaverImpl$10, reason: invalid class name */
    public class AnonymousClass10 implements Runnable {
        final /* synthetic */ ShortcutInfoCompatSaverImpl this$0;
        final /* synthetic */ List val$containers;

        public AnonymousClass10(ShortcutInfoCompatSaverImpl r1, List r2) {
            this.this$0 = r1;
            this.val$containers = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.deleteDanglingBitmaps(this.val$containers);
            ShortcutsInfoSerialization.saveAsXml(this.val$containers, this.this$0.mTargetsXmlFile);
        }
    }

    /* renamed from: androidx.sharetarget.ShortcutInfoCompatSaverImpl$11, reason: invalid class name */
    public class AnonymousClass11 implements Runnable {
        final /* synthetic */ ShortcutInfoCompatSaverImpl this$0;
        final /* synthetic */ ListenableFuture val$future;
        final /* synthetic */ ResolvableFuture val$output;

        public AnonymousClass11(ShortcutInfoCompatSaverImpl r1, ListenableFuture r2, ResolvableFuture r3) {
            this.this$0 = r1;
            this.val$future = r2;
            this.val$output = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.val$future.get();     // Catch: Exception -> L4
            this.val$output.set(null);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            this.val$output.setException(e);
        }
    }

    /* renamed from: androidx.sharetarget.ShortcutInfoCompatSaverImpl$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ ShortcutInfoCompatSaverImpl this$0;
        final /* synthetic */ List val$idList;
        final /* synthetic */ ResolvableFuture val$result;

        public AnonymousClass2(ShortcutInfoCompatSaverImpl r1, List r2, ResolvableFuture r3) {
            this.this$0 = r1;
            this.val$idList = r2;
            this.val$result = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator r0 = this.val$idList.iterator();
        L4:
            if (r0.hasNext() == false) goto L8;
            String r1 = (String) r0.next();
            this.this$0.mShortcutsMap.remove(r1);
            ListenableFuture<?> r12 = this.this$0.mScheduledBitmapTasks.remove(r1);
            if (r12 == null) goto L4;
            r12.cancel(false);
            goto L4
        L8:
            this.this$0.scheduleSyncCurrentState(this.val$result);
        }
    }

    /* renamed from: androidx.sharetarget.ShortcutInfoCompatSaverImpl$3, reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ ShortcutInfoCompatSaverImpl this$0;
        final /* synthetic */ ResolvableFuture val$result;

        public AnonymousClass3(ShortcutInfoCompatSaverImpl r1, ResolvableFuture r2) {
            this.this$0 = r1;
            this.val$result = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.mShortcutsMap.clear();
            Iterator<ListenableFuture<?>> r0 = this.this$0.mScheduledBitmapTasks.values().iterator();
        L4:
            if (r0.hasNext() == false) goto L6;
            r0.next().cancel(false);
            goto L4
        L6:
            this.this$0.mScheduledBitmapTasks.clear();
            this.this$0.scheduleSyncCurrentState(this.val$result);
        }
    }

    /* renamed from: androidx.sharetarget.ShortcutInfoCompatSaverImpl$4, reason: invalid class name */
    public class AnonymousClass4 implements Callable<ArrayList<ShortcutInfoCompat>> {
        final /* synthetic */ ShortcutInfoCompatSaverImpl this$0;

        public AnonymousClass4(ShortcutInfoCompatSaverImpl r1) {
            this.this$0 = r1;
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ ArrayList<ShortcutInfoCompat> call() {
            return call2();
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: call, reason: avoid collision after fix types in other method */
        public ArrayList<ShortcutInfoCompat> call2() {
            ArrayList<ShortcutInfoCompat> r0 = new ArrayList();
            Iterator<ShortcutsInfoSerialization.ShortcutContainer> r3 = this.this$0.mShortcutsMap.values().iterator();
        L4:
            if (r3.hasNext() == false) goto L6;
            r0.add(new ShortcutInfoCompat.Builder(r3.next().mShortcutInfo).build());
            goto L4
        L6:
            return r0;
        }
    }

    /* renamed from: androidx.sharetarget.ShortcutInfoCompatSaverImpl$5, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass5 implements Callable<ShortcutsInfoSerialization.ShortcutContainer> {
        final /* synthetic */ ShortcutInfoCompatSaverImpl this$0;
        final /* synthetic */ String val$shortcutId;

        public AnonymousClass5(ShortcutInfoCompatSaverImpl r1, String r2) {
            this.this$0 = r1;
            this.val$shortcutId = r2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public ShortcutsInfoSerialization.ShortcutContainer call() {
            return this.this$0.mShortcutsMap.get(this.val$shortcutId);
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ ShortcutsInfoSerialization.ShortcutContainer call() {
            return call();
        }
    }

    /* renamed from: androidx.sharetarget.ShortcutInfoCompatSaverImpl$6, reason: invalid class name */
    /* loaded from: classes4.dex */
    public class AnonymousClass6 implements Callable<Bitmap> {
        final /* synthetic */ ShortcutInfoCompatSaverImpl this$0;
        final /* synthetic */ ShortcutsInfoSerialization.ShortcutContainer val$container;

        public AnonymousClass6(ShortcutInfoCompatSaverImpl r1, ShortcutsInfoSerialization.ShortcutContainer r2) {
            this.this$0 = r1;
            this.val$container = r2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Bitmap call() {
            return BitmapFactory.decodeFile(this.val$container.mBitmapPath);
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ Bitmap call() {
            return call();
        }
    }

    /* renamed from: androidx.sharetarget.ShortcutInfoCompatSaverImpl$7, reason: invalid class name */
    public class AnonymousClass7 implements Runnable {
        final /* synthetic */ ShortcutInfoCompatSaverImpl this$0;
        final /* synthetic */ List val$copy;
        final /* synthetic */ ResolvableFuture val$result;

        /* renamed from: androidx.sharetarget.ShortcutInfoCompatSaverImpl$7$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ AnonymousClass7 this$1;
            final /* synthetic */ ListenableFuture val$future;
            final /* synthetic */ String val$id;

            public AnonymousClass1(AnonymousClass7 r1, String r2, ListenableFuture r3) {
                this.this$1 = r1;
                this.val$id = r2;
                this.val$future = r3;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.this$1.this$0.mScheduledBitmapTasks.remove(this.val$id);
                if (this.val$future.isCancelled() == false) goto L10;
                return;
            L10:
                this.val$future.get();     // Catch: Exception -> L7
                return;
            L7:
                e = move-exception;
                this.this$1.val$result.setException(e);
            }
        }

        public AnonymousClass7(ShortcutInfoCompatSaverImpl r1, List r2, ResolvableFuture r3) {
            this.this$0 = r1;
            this.val$copy = r2;
            this.val$result = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator r0 = this.val$copy.iterator();
        L4:
            if (r0.hasNext() == false) goto L20;
            ShortcutInfoCompat r1 = (ShortcutInfoCompat) r0.next();
            Set<String> r2 = r1.getCategories();
            if (r2 == null) goto L4;
            if (r2.isEmpty() == true) goto L4;
            ShortcutsInfoSerialization.ShortcutContainer r22 = this.this$0.containerFrom(r1);
            IconCompat r3 = r1.getIcon();
            if (r22.mBitmapPath == null) goto L13;
            Bitmap r32 = r3.getBitmap();
        L14:
            String r12 = r1.getId();
            this.this$0.mShortcutsMap.put(r12, r22);
            if (r32 == null) goto L4;
            ListenableFuture<Void> r23 = this.this$0.scheduleBitmapSaving(r32, r22.mBitmapPath);
            ListenableFuture<?> r33 = this.this$0.mScheduledBitmapTasks.put(r12, r23);
            if (r33 == null) goto L19;
            r33.cancel(false);
        L19:
            r23.addListener(new AnonymousClass1(this, r12, r23), this.this$0.mCacheUpdateService);
            goto L4
        L13:
            r32 = null;
            goto L14
        L20:
            this.this$0.scheduleSyncCurrentState(this.val$result);
        }
    }

    /* renamed from: androidx.sharetarget.ShortcutInfoCompatSaverImpl$8, reason: invalid class name */
    public class AnonymousClass8 implements Runnable {
        final /* synthetic */ ShortcutInfoCompatSaverImpl this$0;
        final /* synthetic */ Bitmap val$bitmap;
        final /* synthetic */ String val$path;

        public AnonymousClass8(ShortcutInfoCompatSaverImpl r1, Bitmap r2, String r3) {
            this.this$0 = r1;
            this.val$bitmap = r2;
            this.val$path = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.saveBitmap(this.val$bitmap, this.val$path);
        }
    }

    /* renamed from: androidx.sharetarget.ShortcutInfoCompatSaverImpl$9, reason: invalid class name */
    public class AnonymousClass9 implements Runnable {
        final /* synthetic */ ShortcutInfoCompatSaverImpl this$0;
        final /* synthetic */ ResolvableFuture val$result;
        final /* synthetic */ Runnable val$runnable;

        public AnonymousClass9(ShortcutInfoCompatSaverImpl r1, ResolvableFuture r2, Runnable r3) {
            this.this$0 = r1;
            this.val$result = r2;
            this.val$runnable = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.val$result.isCancelled() == false) goto L10;
            return;
        L10:
            this.val$runnable.run();     // Catch: Exception -> L7
            this.val$result.set(null);     // Catch: Exception -> L7
            return;
        L7:
            e = move-exception;
            this.val$result.setException(e);
        }
    }

    static {
        GET_INSTANCE_LOCK = new Object();
    }

    public ShortcutInfoCompatSaverImpl(Context r2, ExecutorService r3, ExecutorService r4) {
        this.mShortcutsMap = new ArrayMap();
        this.mScheduledBitmapTasks = new ArrayMap();
        this.mContext = r2.getApplicationContext();
        this.mCacheUpdateService = r3;
        this.mDiskIoService = r4;
        File r42 = new File(r2.getFilesDir(), "ShortcutInfoCompatSaver_share_targets");
        this.mBitmapsDir = new File(r42, "ShortcutInfoCompatSaver_share_targets_bitmaps");
        this.mTargetsXmlFile = new File(r42, "targets.xml");
        r3.submit(new AnonymousClass1(this, r42));
    }

    public static ExecutorService createExecutorService() {
        LinkedBlockingQueue r6 = new LinkedBlockingQueue();
        return new ThreadPoolExecutor(0, 1, 20, TimeUnit.SECONDS, r6);
    }

    public static boolean ensureDir(File r1) {
        if (r1.exists() == false) goto L11;
        if (r1.isDirectory() == true) goto L11;
        if (r1.delete() == true) goto L11;
        return false;
    L11:
        if (r1.exists() == false) goto L13;
        return true;
    L13:
        return r1.mkdirs();
    }

    public static ShortcutInfoCompatSaverImpl getInstance(Context r4) {
        if (sInstance != null) goto L16;
        Object r0 = GET_INSTANCE_LOCK;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L7:
        if (sInstance != null) goto L11;
        sInstance = new ShortcutInfoCompatSaverImpl(r4, createExecutorService(), createExecutorService());     // Catch: Throwable -> L9
    L11:
        monitor-exit(r0);     // Catch: Throwable -> L9
    L16:
        return sInstance;
    }

    private ListenableFuture<Void> submitDiskOperation(Runnable r4) {
        ResolvableFuture r0 = ResolvableFuture.create();
        this.mDiskIoService.submit(new AnonymousClass9(this, r0, r4));
        return r0;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
    public ListenableFuture<Void> addShortcuts(List<ShortcutInfoCompat> r4) {
        ArrayList r0 = new ArrayList(r4.size());
        Iterator<ShortcutInfoCompat> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L6;
        r0.add(new ShortcutInfoCompat.Builder(r42.next()).build());
        goto L4
    L6:
        ResolvableFuture r43 = ResolvableFuture.create();
        this.mCacheUpdateService.submit(new AnonymousClass7(this, r0, r43));
        return r43;
    }

    @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
    public /* bridge */ /* synthetic */ ListenableFuture<Void> addShortcuts(List r1) {
        return addShortcuts(r1);
    }

    public ShortcutsInfoSerialization.ShortcutContainer containerFrom(ShortcutInfoCompat r5) {
        IconCompat r0 = r5.getIcon();
        if (r0 == null) goto L13;
        int r2 = r0.getType();
        if (r2 != 1) goto L7;
    L12:
        String r02 = new File(this.mBitmapsDir, UUID.randomUUID().toString()).getAbsolutePath();
        String r4 = null;
    L15:
        return new ShortcutsInfoSerialization.ShortcutContainer(new ShortcutInfoCompat.Builder(r5).setIcon(null).build(), r4, r02);
    L7:
        if (r2 != 2) goto L9;
        r4 = this.mContext.getResources().getResourceName(r0.getResId());
        r02 = null;
        goto L15
    L9:
        if (r2 == 5) goto L12;
    L13:
        r4 = null;
        r02 = null;
        goto L15
    }

    public void deleteDanglingBitmaps(List<ShortcutsInfoSerialization.ShortcutContainer> r5) {
        ArrayList r0 = new ArrayList();
        Iterator<ShortcutsInfoSerialization.ShortcutContainer> r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L8;
        ShortcutsInfoSerialization.ShortcutContainer r1 = r52.next();
        if (TextUtils.isEmpty(r1.mBitmapPath) == true) goto L4;
        r0.add(r1.mBitmapPath);
        goto L4
    L8:
        File[] r4 = this.mBitmapsDir.listFiles();
        int r53 = r4.length;
        int r12 = 0;
    L9:
        if (r12 >= r53) goto L14;
        File r2 = r4[r12];
        if (r0.contains(r2.getAbsolutePath()) == true) goto L13;
        r2.delete();
    L13:
        r12 = r12 + 1;
        goto L9
    }

    public IconCompat getShortcutIcon(String r4) {
        ShortcutsInfoSerialization.ShortcutContainer r42 = (ShortcutsInfoSerialization.ShortcutContainer) this.mCacheUpdateService.submit(new AnonymousClass5(this, r4)).get();
        if (r42 != null) goto L6;
        return null;
    L6:
        if (TextUtils.isEmpty(r42.mResourceName) == true) goto L14;
        int r1 = this.mContext.getResources().getIdentifier(r42.mResourceName, null, null);     // Catch: Exception -> L9
    L10:
        if (r1 == 0) goto L14;
        return IconCompat.createWithResource(this.mContext, r1);
    L9:
        r1 = 0;
    L14:
        if (TextUtils.isEmpty(r42.mBitmapPath) == true) goto L21;
        Bitmap r3 = (Bitmap) this.mDiskIoService.submit(new AnonymousClass6(this, r42)).get();
        if (r3 != null) goto L18;
        return null;
    L18:
        return IconCompat.createWithBitmap(r3);
    L21:
        return null;
    }

    @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
    public List<ShortcutInfoCompat> getShortcuts() {
        return (List) this.mCacheUpdateService.submit(new AnonymousClass4(this)).get();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
    public ListenableFuture<Void> removeAllShortcuts() {
        ResolvableFuture r0 = ResolvableFuture.create();
        this.mCacheUpdateService.submit(new AnonymousClass3(this, r0));
        return r0;
    }

    @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
    public /* bridge */ /* synthetic */ ListenableFuture<Void> removeAllShortcuts() {
        return removeAllShortcuts();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
    public ListenableFuture<Void> removeShortcuts(List<String> r4) {
        ArrayList r0 = new ArrayList(r4);
        ResolvableFuture r42 = ResolvableFuture.create();
        this.mCacheUpdateService.submit(new AnonymousClass2(this, r0, r42));
        return r42;
    }

    @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
    public /* bridge */ /* synthetic */ ListenableFuture<Void> removeShortcuts(List r1) {
        return removeShortcuts(r1);
    }

    public void saveBitmap(Bitmap r5, String r6) {
        if (r5 != null) goto L5;
        f$$ExternalSyntheticBUOutline1.m("bitmap is null");
        return;
    L5:
        if (TextUtils.isEmpty(r6) == false) goto L35;
        f$$ExternalSyntheticBUOutline1.m("path is empty");
        return;
    L35:
        FileOutputStream r1 = new FileOutputStream(new File(r6));     // Catch: OutOfMemoryError -> L11 RuntimeException -> L13 Throwable -> L15
    L19:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L22
    L24:
        throw th;     // Catch: OutOfMemoryError -> L11 RuntimeException -> L13 Throwable -> L15
    L22:
        th = move-exception;
        th.addSuppressed(th);     // Catch: OutOfMemoryError -> L11 RuntimeException -> L13 Throwable -> L15
        goto L24
    L8:
        if (r5.compress(Bitmap.CompressFormat.PNG, 100, r1) == false) goto L17;
        r1.close();     // Catch: OutOfMemoryError -> L11 RuntimeException -> L13 Throwable -> L15
        return;
    L17:
        Log.wtf("ShortcutInfoCompatSaver", "Unable to compress bitmap");     // Catch: Throwable -> L19
        throw new RuntimeException("Unable to compress bitmap for saving " + r6);     // Catch: Throwable -> L19
    L15:
        e = move-exception;
        Log.wtf("ShortcutInfoCompatSaver", "Unable to write bitmap to file", e);
        a$$ExternalSyntheticBUOutline0.m("Unable to write bitmap to file ", r6, e);
    }

    public ListenableFuture<Void> scheduleBitmapSaving(Bitmap r2, String r3) {
        return submitDiskOperation(new AnonymousClass8(this, r2, r3));
    }

    public void scheduleSyncCurrentState(ResolvableFuture<Void> r3) {
        ListenableFuture<Void> r0 = submitDiskOperation(new AnonymousClass10(this, new ArrayList(this.mShortcutsMap.values())));
        r0.addListener(new AnonymousClass11(this, r0, r3), this.mCacheUpdateService);
    }
}
