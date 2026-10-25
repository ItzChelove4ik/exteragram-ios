package org.telegram.messenger.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DispatchQueuePoolBackground;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.RLottieDrawable;

/* loaded from: classes.dex */
public class BitmapsCache {
    private static final int N = 0;
    private static ThreadPoolExecutor bitmapCompressExecutor;
    static volatile boolean cleanupScheduled;
    private static boolean mkdir;
    static final ConcurrentHashMap<Thread, byte[]> sharedBuffers = null;
    private static CacheGeneratorSharedTools sharedTools;
    private static int taskCounter;
    byte[] bufferTmp;
    volatile boolean cacheCreated;
    RandomAccessFile cachedFile;
    public AtomicBoolean cancelled;
    public volatile boolean checked;
    private Runnable cleanupSharedBuffers;
    int compressQuality;
    boolean error;
    final File file;
    volatile boolean fileExist;
    String fileName;
    private int frameIndex;
    ArrayList<FrameOffset> frameOffsets;
    public final AtomicInteger framesProcessed;
    int h;
    private final Object mutex;
    BitmapFactory.Options options;
    volatile boolean recycled;
    private final Cacheable source;
    private Bitmap tmpRgbaBitmap;
    private int tryCount;
    final boolean useSharedBuffers;
    int w;

    /* renamed from: org.telegram.messenger.utils.BitmapsCache$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ BitmapsCache this$0;

        public AnonymousClass1(BitmapsCache r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator<Thread> r0 = BitmapsCache.sharedBuffers.keySet().iterator();
        L4:
            if (r0.hasNext() == false) goto L9;
            Thread r1 = r0.next();
            if (r1.isAlive() == true) goto L4;
            BitmapsCache.sharedBuffers.remove(r1);
            goto L4
        L9:
            if (BitmapsCache.sharedBuffers.isEmpty() == true) goto L12;
            AndroidUtilities.runOnUIThread(BitmapsCache.m5178$$Nest$fgetcleanupSharedBuffers(this.this$0), 5000);
            return;
        L12:
            BitmapsCache.cleanupScheduled = false;
        }
    }

    public static class CacheGeneratorSharedTools {
        private Bitmap[] bitmap;
        ImmutableByteArrayOutputStream[] byteArrayOutputStream;
        private int lastSize;

        public static /* synthetic */ void $r8$lambda$92tmsO31cfirt8xhSYMxR5CoewY(ArrayList r3) {
            int r0 = r3.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            Object r2 = r3.get(r1);
            r1 = r1 + 1;
            ((Bitmap) r2).recycle();
            goto L3
        }

        public static /* synthetic */ void $r8$lambda$bI_R6xrmz7dFswgII6EQKTk5QFU(Bitmap r0) {
            r0.recycle();     // Catch: Exception -> L4
            return;
        }

        /* renamed from: -$$Nest$fgetbitmap, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Bitmap[] m5180$$Nest$fgetbitmap(CacheGeneratorSharedTools r0) {
            return r0.bitmap;
        }

        private CacheGeneratorSharedTools() {
            this.byteArrayOutputStream = new ImmutableByteArrayOutputStream[BitmapsCache.m5179$$Nest$sfgetN()];
            this.bitmap = new Bitmap[BitmapsCache.m5179$$Nest$sfgetN()];
        }

        public /* synthetic */ CacheGeneratorSharedTools(BitmapsCacheIA r1) {
            this();
        }

        public void allocate(int r6, int r7) {
            int r0 = (r7 << 16) + r6;
            int r2 = 0;
            if (this.lastSize == r0) goto L5;
            boolean r1 = true;
        L6:
            this.lastSize = r0;
        L8:
            if (r2 >= BitmapsCache.m5179$$Nest$sfgetN()) goto L20;
            if (r1 == false) goto L11;
        L12:
            Bitmap r02 = this.bitmap[r2];
            if (r02 == null) goto L15;
            Utilities.globalQueue.postRunnable(new BitmapsCache$CacheGeneratorSharedTools$$ExternalSyntheticLambda1(r02));
        L15:
            this.bitmap[r2] = Bitmap.createBitmap(r7, r6, Bitmap.Config.ARGB_8888);
        L16:
            ImmutableByteArrayOutputStream[] r03 = this.byteArrayOutputStream;
            if (r03[r2] != null) goto L19;
            r03[r2] = new ImmutableByteArrayOutputStream((r7 * r6) * 2);
        L19:
            r2 = r2 + 1;
            goto L8
        L11:
            if (this.bitmap[r2] != null) goto L16;
        L20:
            return;
        L5:
            r1 = false;
            goto L6
        }

        public void release() {
            int r1 = 0;
            ArrayList r2 = null;
        L4:
            if (r1 >= BitmapsCache.m5179$$Nest$sfgetN()) goto L12;
            if (this.bitmap[r1] == null) goto L10;
            if (r2 != null) goto L9;
            r2 = new ArrayList();
        L9:
            r2.add(this.bitmap[r1]);
        L10:
            this.bitmap[r1] = null;
            this.byteArrayOutputStream[r1] = null;
            r1 = r1 + 1;
            goto L4
        L12:
            if (r2.isEmpty() == true) goto L18;
            Utilities.globalQueue.postRunnable(new BitmapsCache$CacheGeneratorSharedTools$$ExternalSyntheticLambda0(r2));
            return;
        }
    }

    public static class CacheOptions {
        public int compressQuality;
        public boolean fallback;
        public boolean firstFrame;

        public CacheOptions() {
            this.compressQuality = 100;
            this.fallback = false;
        }
    }

    public interface Cacheable {
        int getNextFrame(Bitmap r1);

        void prepareForGenerateCache();

        void releaseForGenerateCache();
    }

    public class FrameOffset {
        int frameOffset;
        int frameSize;
        final int index;
        final /* synthetic */ BitmapsCache this$0;

        private FrameOffset(BitmapsCache r1, int r2) {
            this.this$0 = r1;
            this.index = r2;
        }

        public /* synthetic */ FrameOffset(BitmapsCache r1, int r2, BitmapsCacheIA r3) {
            this(r1, r2);
        }
    }

    public static class Metadata {
        public int frame;

        public Metadata() {
        }
    }

    public static /* synthetic */ int $r8$lambda$6kVoZXFQyFpZ88pME7DtCof8SlA(FrameOffset r0) {
        return r0.index;
    }

    /* renamed from: $r8$lambda$75FwZaXfPh-7AZ0gZ3tKl5Rqbyg, reason: not valid java name */
    public static /* synthetic */ void m5177$r8$lambda$75FwZaXfPh7AZ0gZ3tKl5Rqbyg() {
        CacheGeneratorSharedTools r0 = sharedTools;
        if (r0 == null) goto L6;
        r0.release();
        sharedTools = null;
        return;
    }

    public static /* synthetic */ void $r8$lambda$FoChbovdHYgidAWB8EndELMo4u4(BitmapsCache r0, AtomicBoolean r1, Bitmap[] r2, int r3, ImmutableByteArrayOutputStream[] r4, int r5, RandomAccessFile r6, ArrayList r7, CountDownLatch[] r8) {
        r0.lambda$createCache$1(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    /* renamed from: -$$Nest$fgetcleanupSharedBuffers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m5178$$Nest$fgetcleanupSharedBuffers(BitmapsCache r0) {
        return r0.cleanupSharedBuffers;
    }

    /* renamed from: -$$Nest$sfgetN, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5179$$Nest$sfgetN() {
        return N;
    }

    static {
        sharedBuffers = new ConcurrentHashMap();
        N = Utilities.clamp(Runtime.getRuntime().availableProcessors() - 2, 6, 1);
    }

    public BitmapsCache(File r9, Cacheable r10, CacheOptions r11, int r12, int r13, boolean r14) {
        this(r9, r10, r11, r12, r13, r14, 0);
    }

    public BitmapsCache(File r14, Cacheable r15, CacheOptions r16, int r17, int r18, boolean r19, int r20) {
        this.framesProcessed = new AtomicInteger(0);
        this.frameOffsets = new ArrayList();
        this.mutex = new Object();
        this.cancelled = new AtomicBoolean(false);
        this.cleanupSharedBuffers = new AnonymousClass1(this);
        this.source = r15;
        this.w = r17;
        this.h = r18;
        this.compressQuality = r16.compressQuality;
        this.fileName = r14.getName();
        if (bitmapCompressExecutor != null) goto L5;
        int r7 = N;
        bitmapCompressExecutor = new ThreadPoolExecutor(r7, r7, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    L5:
        File r142 = new File(FileLoader.checkDirectory(4), "acache");
        if (mkdir == true) goto L8;
        r142.mkdir();
        mkdir = true;
    L8:
        StringBuilder r6 = new StringBuilder();
        r6.append(this.fileName);
        r6.append("_");
        r6.append(r17);
        r6.append("_");
        r6.append(r18);
        if (r19 == false) goto L11;
        String r72 = "_nolimit";
    L12:
        r6.append(r72);
        if (r20 == 0) goto L15;
        String r2 = "_fitz" + r20;
    L16:
        r6.append(r2);
        r6.append(".pcache2");
        File r152 = new File(r142, r6.toString());
        this.file = r152;
        if (r17 < AndroidUtilities.dp(60.0f)) goto L19;
    L21:
        boolean r143 = false;
    L22:
        this.useSharedBuffers = r143;
        if (SharedConfig.getDevicePerformanceClass() < 2) goto L53;
        this.fileExist = r152.exists();
        if (this.fileExist == true) goto L58;
    L51:
        this.checked = true;
        return;
    L58:
        RandomAccessFile r144 = new RandomAccessFile(r152, "r");     // Catch: Throwable -> L42
        this.cacheCreated = r144.readBoolean();     // Catch: Throwable -> L37
        if (this.cacheCreated == true) goto L30;
    L40:
        r144.close();     // Catch: Throwable -> L42
        goto L51
    L30:
        if (this.frameOffsets.isEmpty() == false) goto L40;
        r144.seek(r144.readInt());     // Catch: Throwable -> L37
        int r0 = r144.readInt();     // Catch: Throwable -> L37
        if (r0 <= 10000) goto L34;
        r0 = 0;
    L34:
        fillFrames(r144, r0);     // Catch: Throwable -> L37
        if (this.frameOffsets.isEmpty() == false) goto L39;
        this.cacheCreated = false;     // Catch: Throwable -> L37
        this.fileExist = false;     // Catch: Throwable -> L37
        this.checked = true;     // Catch: Throwable -> L37
        r152.delete();     // Catch: Throwable -> L37
        goto L40
    L39:
        closeCachedFile();     // Catch: Throwable -> L37
        this.cachedFile = new RandomAccessFile(r152, "r");     // Catch: Throwable -> L37
    L37:
        th = move-exception;
        r144.close();     // Catch: Throwable -> L46
    L59:
        throw th;     // Catch: Throwable -> L42
    L46:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L42
        throw th;     // Catch: Throwable -> L42
    L42:
        th = move-exception;
        FileLog.e(th);
        this.file.delete();
        this.fileExist = false;
        this.checked = true;
        goto L51
    L53:
        this.fileExist = false;
        this.cacheCreated = false;
        return;
    L19:
        if (r18 >= AndroidUtilities.dp(60.0f)) goto L21;
        r143 = true;
        goto L22
    L15:
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L16
    L11:
        r72 = " ";
        goto L12
    }

    private void closeCachedFile() {
        RandomAccessFile r0 = this.cachedFile;
        if (r0 == null) goto L11;
        r0.close();     // Catch: IOException -> L6
        return;
    L6:
        e = move-exception;
        e.printStackTrace();
        return;
    }

    public static void decrementTaskCounter() {
        int r0 = taskCounter - 1;
        taskCounter = r0;
        if (r0 > 0) goto L6;
        taskCounter = 0;
        RLottieDrawable.lottieCacheGenerateQueue.postRunnable(new BitmapsCache$$ExternalSyntheticLambda0());
        return;
    }

    private void fillFrames(RandomAccessFile r4, int r5) {
        if (r5 == 0) goto L7;
        byte[] r0 = new byte[r5 * 8];
        r4.read(r0);
        ByteBuffer r42 = ByteBuffer.wrap(r0);
        int r02 = 0;
    L5:
        if (r02 >= r5) goto L9;
        FrameOffset r1 = new FrameOffset(this, r02, null);
        r1.frameOffset = r42.getInt();
        r1.frameSize = r42.getInt();
        this.frameOffsets.add(r1);
        r02 = r02 + 1;
        goto L5
    L9:
        return;
    }

    private byte[] getBuffer(FrameOffset r6) {
        if (this.useSharedBuffers == true) goto L5;
    L7:
        boolean r0 = false;
    L8:
        if (r0 == false) goto L10;
        byte[] r2 = sharedBuffers.get(Thread.currentThread());
    L11:
        if (r2 != null) goto L13;
    L16:
        byte[] r62 = new byte[(int) (r6.frameSize * 1.3f)];
        if (r0 == false) goto L22;
        sharedBuffers.put(Thread.currentThread(), r62);
        if (cleanupScheduled == true) goto L21;
        cleanupScheduled = true;
        AndroidUtilities.runOnUIThread(this.cleanupSharedBuffers, 5000);
    L21:
        return r62;
    L22:
        this.bufferTmp = r62;
        return r62;
    L13:
        if (r2.length < r6.frameSize) goto L16;
        return r2;
    L10:
        r2 = this.bufferTmp;
        goto L11
    L5:
        if (Thread.currentThread().getName().startsWith(DispatchQueuePoolBackground.THREAD_PREFIX) == false) goto L7;
        r0 = true;
        goto L8
    }

    public static void incrementTaskCounter() {
        taskCounter++;
    }

    private /* synthetic */ void lambda$createCache$1(AtomicBoolean r5, Bitmap[] r6, int r7, ImmutableByteArrayOutputStream[] r8, int r9, RandomAccessFile r10, ArrayList r11, CountDownLatch[] r12) {
        if (this.cancelled.get() == false) goto L5;
        return;
    L5:
        if (r5.get() == true) goto L36;
        Bitmap.CompressFormat r0 = Bitmap.CompressFormat.WEBP;
        if (Build.VERSION.SDK_INT > 28) goto L10;
        r0 = Bitmap.CompressFormat.PNG;
    L10:
        r6[r7].compress(r0, this.compressQuality, r8[r7]);
        int r62 = r8[r7].count;
        Object r02 = this.mutex;     // Catch: IOException -> L19
        monitor-enter(r02);     // Catch: IOException -> L19
        FrameOffset r1 = new FrameOffset(this, r9, null);     // Catch: Throwable -> L16
        r1.frameOffset = (int) r10.length();     // Catch: Throwable -> L16
        r11.add(r1);     // Catch: Throwable -> L16
        r10.write(r8[r7].buf, 0, r62);     // Catch: Throwable -> L16
        r1.frameSize = r62;     // Catch: Throwable -> L16
        r8[r7].reset();     // Catch: Throwable -> L16
        monitor-exit(r02);     // Catch: Throwable -> L16
    L26:
        r12[r7].countDown();
        return;
    L16:
        th = move-exception;
        throw th;     // Catch: IOException -> L19
    L19:
        e = move-exception;
        e.printStackTrace();
        r10.close();     // Catch: Exception -> L28 Throwable -> L23
    L23:
        th = move-exception;
        r5.set(true);
        throw th;
    L22:
        r5.set(true);
        goto L26
    }

    public void cancelCreate() {
    }

    public void createCache() {
    L22:
        th = move-exception;
        this.source.releaseForGenerateCache();
        throw th;
    L26:
        e = move-exception;
        e.printStackTrace();     // Catch: Throwable -> L22
    L74:
        this.source.releaseForGenerateCache();
        return;
    L24:
        e = move-exception;
        e.printStackTrace();     // Catch: Throwable -> L22
        goto L74
    L4:
        int r14 = 0;
        if (this.file.exists() == true) goto L122;
    L42:
        RandomAccessFile r7 = new RandomAccessFile(this.file, "rw");     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        if (sharedTools != null) goto L45;
        sharedTools = new CacheGeneratorSharedTools(null);     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
    L45:
        sharedTools.allocate(this.h, this.w);     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        Bitmap[] r3 = CacheGeneratorSharedTools.m5180$$Nest$fgetbitmap(sharedTools);     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        ImmutableByteArrayOutputStream[] r5 = sharedTools.byteArrayOutputStream;     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        CountDownLatch[] r9 = new CountDownLatch[N];     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        ArrayList r8 = new ArrayList();     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        r7.writeBoolean(false);     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        r7.writeInt(0);     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        AtomicBoolean r2 = new AtomicBoolean(false);     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        this.source.prepareForGenerateCache();     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        int r4 = 0;
        int r6 = 0;
    L46:
        CountDownLatch r0 = r9[r4];     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        if (r0 == null) goto L53;
        r0.await();     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26 InterruptedException -> L50
    L50:
        e = move-exception;
        e.printStackTrace();     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
    L53:
        if (this.cancelled.get() == true) goto L80;
        if (r2.get() == true) goto L80;
        if (this.source.getNextFrame(r3[r4]) != 1) goto L59;
        r9[r4] = new CountDownLatch(1);     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        bitmapCompressExecutor.execute(new BitmapsCache$$ExternalSyntheticLambda1(this, r2, r3, r4, r5, r6, r7, r8, r9));     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        r4 = r4 + 1;     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        r6 = r6 + 1;     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        if (r4 < N) goto L78;
        r4 = 0;
    L78:
        this.framesProcessed.set(r6);     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        goto L46
    L59:
        int r32 = 0;
    L61:
        if (r32 >= N) goto L69;
        CountDownLatch r02 = r9[r32];     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        if (r02 == null) goto L68;
        r02.await();     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26 InterruptedException -> L66
    L66:
        e = move-exception;
        e.printStackTrace();     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
    L68:
        r32 = r32 + 1;     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        goto L61
    L69:
        int r03 = (int) r7.length();     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        Collections.sort(r8, Comparator.comparingInt(new BitmapsCache$$ExternalSyntheticLambda2()));     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        r5[0].reset();     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        int r33 = r8.size();     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        r5[0].writeInt(r33);     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        int r42 = 0;
    L71:
        if (r42 >= r8.size()) goto L73;
        r5[0].writeInt(((FrameOffset) r8.get(r42)).frameOffset);     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        r5[0].writeInt(((FrameOffset) r8.get(r42)).frameSize);     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        r42 = r42 + 1;     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        goto L71
    L73:
        r7.write(r5[0].buf, 0, (r33 * 8) + 4);     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        r5[0].reset();     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        r7.seek(0);     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        r7.writeBoolean(true);     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        r7.writeInt(r03);     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        r2.set(true);     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        r7.close();     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        this.frameOffsets.clear();     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        this.frameOffsets.addAll(r8);     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        closeCachedFile();     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        this.cachedFile = new RandomAccessFile(this.file, "r");     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        this.cacheCreated = true;     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        this.fileExist = true;     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        this.checked = true;     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
    L80:
        if (BuildVars.DEBUG_VERSION == false) goto L82;
        FileLog.d("cancelled cache generation");     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
    L82:
        r2.set(true);     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
    L84:
        if (r14 >= N) goto L95;
        CountDownLatch r04 = r9[r14];     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        if (r04 != null) goto L114;
    L91:
        Bitmap r05 = r3[r14];     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        if (r05 == null) goto L94;
        r05.recycle();     // Catch: Throwable -> L22 Exception -> L107
    L94:
        r14 = r14 + 1;
        goto L84
    L114:
        r04.await();     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26 InterruptedException -> L89
    L89:
        e = move-exception;
        e.printStackTrace();     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        goto L91
    L95:
        r7.close();     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
        this.source.releaseForGenerateCache();     // Catch: Throwable -> L22 IOException -> L24 FileNotFoundException -> L26
    L20:
        this.source.releaseForGenerateCache();
        return;
    L122:
        RandomAccessFile r06 = new RandomAccessFile(this.file, "r");     // Catch: Throwable -> L36
        this.cacheCreated = r06.readBoolean();     // Catch: Throwable -> L104
        if (this.cacheCreated == false) goto L30;
        this.frameOffsets.clear();     // Catch: Throwable -> L104
        r06.seek(r06.readInt());     // Catch: Throwable -> L104
        int r34 = r06.readInt();     // Catch: Throwable -> L104
        if (r34 <= 10000) goto L12;
        r34 = 0;
    L12:
        if (r34 <= 0) goto L28;
        fillFrames(r06, r34);     // Catch: Throwable -> L104
        r06.seek(0);     // Catch: Throwable -> L104
        if (this.cachedFile == r06) goto L16;
        closeCachedFile();     // Catch: Throwable -> L104
    L16:
        this.cachedFile = r06;     // Catch: Throwable -> L104
        this.fileExist = true;     // Catch: Throwable -> L104
        this.checked = true;     // Catch: Throwable -> L104
        if (this.cachedFile == r06) goto L20;
        r06.close();     // Catch: Throwable -> L103
        goto L20
    L28:
        this.fileExist = false;     // Catch: Throwable -> L104
        this.cacheCreated = false;     // Catch: Throwable -> L104
        this.checked = true;     // Catch: Throwable -> L104
    L30:
        if (this.cacheCreated == true) goto L33;
        this.file.delete();     // Catch: Throwable -> L104
    L33:
        if (this.cachedFile == r06) goto L42;
    L120:
        r06.close();     // Catch: Throwable -> L106
    L108:
        this.file.delete();     // Catch: Throwable -> L105
    L39:
        if (this.cachedFile == r06) goto L42;
        if (r06 == null) goto L42;
    L36:
        r06 = null;
        goto L108
    }

    public int getFrame(int r11, Bitmap r12) {
        if (this.error == false) goto L5;
        return -1;
    L5:
        RandomAccessFile r0 = null;
    L11:
        th = th;
    L65:
        FileLog.e(th);
        int r112 = this.tryCount + 1;
        this.tryCount = r112;
        if (r112 <= 10) goto L69;
        this.error = true;
        goto L69
    L7:
        if (this.cacheCreated == true) goto L14;
        if (this.fileExist == true) goto L14;
        return -1;
    L14:
        if (this.cacheCreated == false) goto L17;
        RandomAccessFile r3 = this.cachedFile;     // Catch: Throwable -> L11 FileNotFoundException -> L76
        if (r3 == null) goto L17;
    L34:
        if (this.frameOffsets.size() != 0) goto L36;
        return -1;
    L36:
        FrameOffset r113 = this.frameOffsets.get(Utilities.clamp(r11, this.frameOffsets.size() - 1, 0));     // Catch: Throwable -> L23 FileNotFoundException -> L25
        r3.seek(r113.frameOffset);     // Catch: Throwable -> L23 FileNotFoundException -> L25
        byte[] r5 = getBuffer(r113);     // Catch: Throwable -> L23 FileNotFoundException -> L25
        r3.readFully(r5, 0, r113.frameSize);     // Catch: Throwable -> L23 FileNotFoundException -> L25
        if (this.recycled == false) goto L39;
        this.cachedFile = null;     // Catch: Throwable -> L23 FileNotFoundException -> L25
        r3.close();     // Catch: Throwable -> L23 FileNotFoundException -> L25
    L44:
        if (this.options != null) goto L47;
        this.options = new BitmapFactory.Options();     // Catch: Throwable -> L23 FileNotFoundException -> L25
    L47:
        if (r12.getConfig() != Bitmap.Config.ALPHA_8) goto L49;
        boolean r6 = true;
    L50:
        if (r6 == false) goto L59;
        Bitmap r7 = this.tmpRgbaBitmap;     // Catch: Throwable -> L23 FileNotFoundException -> L25
        if (r7 != null) goto L54;
    L57:
        this.tmpRgbaBitmap = Bitmap.createBitmap(r12.getWidth(), r12.getHeight(), Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L23 FileNotFoundException -> L25
    L58:
        this.options.inBitmap = this.tmpRgbaBitmap;     // Catch: Throwable -> L23 FileNotFoundException -> L25
    L60:
        BitmapFactory.decodeByteArray(r5, 0, r113.frameSize, this.options);     // Catch: Throwable -> L23 FileNotFoundException -> L25
        if (r6 == false) goto L63;
        Utilities.extractAlpha(this.tmpRgbaBitmap, r12);     // Catch: Throwable -> L23 FileNotFoundException -> L25
    L63:
        this.options.inBitmap = null;     // Catch: Throwable -> L23 FileNotFoundException -> L25
        return 0;
    L54:
        if (r7.getWidth() != r12.getWidth()) goto L57;
        if (this.tmpRgbaBitmap.getHeight() == r12.getHeight()) goto L58;
    L59:
        this.options.inBitmap = r12;     // Catch: Throwable -> L23 FileNotFoundException -> L25
        goto L60
    L49:
        r6 = false;
        goto L50
    L39:
        if (this.cachedFile == r3) goto L41;
        closeCachedFile();     // Catch: Throwable -> L23 FileNotFoundException -> L25
    L41:
        this.cachedFile = r3;     // Catch: Throwable -> L23 FileNotFoundException -> L25
    L17:
        r3 = new RandomAccessFile(this.file, "r");     // Catch: Throwable -> L11 FileNotFoundException -> L76
        goto L69
    L81:
        this.cacheCreated = r3.readBoolean();     // Catch: Throwable -> L23 FileNotFoundException -> L25
        if (this.cacheCreated == false) goto L27;
        if (this.frameOffsets.isEmpty() == false) goto L27;
        r3.seek(r3.readInt());     // Catch: Throwable -> L23 FileNotFoundException -> L25
        fillFrames(r3, r3.readInt());     // Catch: Throwable -> L23 FileNotFoundException -> L25
    L27:
        if (this.frameOffsets.size() != 0) goto L30;
        this.cacheCreated = false;     // Catch: Throwable -> L23 FileNotFoundException -> L25
        this.checked = true;     // Catch: Throwable -> L23 FileNotFoundException -> L25
    L30:
        if (this.cacheCreated == true) goto L34;
        r3.close();     // Catch: Throwable -> L23 FileNotFoundException -> L25
        return -1;
    L25:
        r0 = r3;
    L23:
        th = th;
        r0 = r3;
    L69:
        if (this.error == false) goto L75;
        if (r0 == null) goto L75;
        r0.close();     // Catch: IOException -> L73
    L73:
        e = move-exception;
        e.printStackTrace();
    L75:
        return -1;
    }

    public int getFrame(Bitmap r2, Metadata r3) {
        int r22 = getFrame(this.frameIndex, r2);
        r3.frame = this.frameIndex;
        if (this.cacheCreated == true) goto L5;
    L9:
        return r22;
    L5:
        if (this.frameOffsets.isEmpty() == true) goto L9;
        int r32 = this.frameIndex + 1;
        this.frameIndex = r32;
        if (r32 < this.frameOffsets.size()) goto L9;
        this.frameIndex = 0;
        goto L9
    }

    public int getFrameCount() {
        return this.frameOffsets.size();
    }

    public boolean needGenCache() {
        if (this.cacheCreated == true) goto L5;
        return true;
    L5:
        if (this.fileExist == false) goto L11;
        return false;
    L11:
        return true;
    }

    public void recycle() {
        RandomAccessFile r0 = this.cachedFile;
        if (r0 != null) goto L11;
    L9:
        this.recycled = true;
        return;
    L11:
        r0.close();     // Catch: IOException -> L6
    L8:
        this.cachedFile = null;
    L6:
        e = move-exception;
        e.printStackTrace();
        goto L8
    }
}
