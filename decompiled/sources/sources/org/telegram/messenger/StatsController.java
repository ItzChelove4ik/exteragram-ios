package org.telegram.messenger;

import android.content.SharedPreferences;
import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import kotlin.UByte;
import org.telegram.messenger.utils.ImmutableByteArrayOutputStream;

/* loaded from: classes.dex */
public class StatsController extends BaseController {
    private static volatile StatsController[] Instance = null;
    private static final int OLD_TYPES_COUNT = 7;
    private static final int TYPES_COUNT = 8;
    public static final int TYPE_AUDIOS = 3;
    public static final int TYPE_CALLS = 0;
    public static final int TYPE_FILES = 5;
    public static final int TYPE_MESSAGES = 1;
    public static final int TYPE_MOBILE = 0;
    public static final int TYPE_MUSIC = 7;
    public static final int TYPE_PHOTOS = 4;
    public static final int TYPE_ROAMING = 2;
    public static final int TYPE_TOTAL = 6;
    public static final int TYPE_VIDEOS = 2;
    public static final int TYPE_WIFI = 1;
    private static final ThreadLocal<Long> lastStatsSaveTime = null;
    private static DispatchQueue statsSaveQueue;
    private byte[] buffer;
    ImmutableByteArrayOutputStream byteArrayOutputStream;
    private int[] callsTotalTime;
    private long lastInternalStatsSaveTime;
    private long[][] receivedBytes;
    private int[][] receivedItems;
    private long[] resetStatsDate;
    private Runnable saveRunnable;
    private long[][] sentBytes;
    private int[][] sentItems;
    private RandomAccessFile statsFile;

    /* renamed from: org.telegram.messenger.StatsController$1, reason: invalid class name */
    public class AnonymousClass1 extends ThreadLocal<Long> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public Long initialValue() {
            return Long.valueOf(System.currentTimeMillis() - 1000);
        }

        @Override // java.lang.ThreadLocal
        public /* bridge */ /* synthetic */ Long initialValue() {
            return initialValue();
        }
    }

    /* renamed from: org.telegram.messenger.StatsController$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ StatsController this$0;

        public AnonymousClass2(StatsController r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            long r0 = System.currentTimeMillis();
            if (Math.abs(r0 - StatsController.m4973$$Nest$fgetlastInternalStatsSaveTime(this.this$0)) < 2000) goto L28;
            StatsController.m4980$$Nest$fputlastInternalStatsSaveTime(this.this$0, r0);
            this.this$0.byteArrayOutputStream.reset();     // Catch: Exception -> L22
            int r1 = 0;
        L9:
            if (r1 >= 3) goto L16;
            int r3 = 0;
        L11:
            StatsController r6 = this.this$0;
            if (r3 >= 7) goto L14;
            r6.byteArrayOutputStream.write(StatsController.m4982$$Nest$mlongToBytes(r6, StatsController.m4977$$Nest$fgetsentBytes(r6)[r1][r3]), 0, 8);     // Catch: Exception -> L22
            StatsController r62 = this.this$0;     // Catch: Exception -> L22
            r62.byteArrayOutputStream.write(StatsController.m4982$$Nest$mlongToBytes(r62, StatsController.m4974$$Nest$fgetreceivedBytes(r62)[r1][r3]), 0, 8);     // Catch: Exception -> L22
            StatsController r63 = this.this$0;     // Catch: Exception -> L22
            r63.byteArrayOutputStream.write(StatsController.m4981$$Nest$mintToBytes(r63, StatsController.m4978$$Nest$fgetsentItems(r63)[r1][r3]), 0, 4);     // Catch: Exception -> L22
            StatsController r64 = this.this$0;     // Catch: Exception -> L22
            r64.byteArrayOutputStream.write(StatsController.m4981$$Nest$mintToBytes(r64, StatsController.m4975$$Nest$fgetreceivedItems(r64)[r1][r3]), 0, 4);     // Catch: Exception -> L22
            r3 = r3 + 1;     // Catch: Exception -> L22
            goto L11
        L14:
            r6.byteArrayOutputStream.write(StatsController.m4981$$Nest$mintToBytes(r6, StatsController.m4972$$Nest$fgetcallsTotalTime(r6)[r1]), 0, 4);     // Catch: Exception -> L22
            StatsController r2 = this.this$0;     // Catch: Exception -> L22
            r2.byteArrayOutputStream.write(StatsController.m4982$$Nest$mlongToBytes(r2, StatsController.m4976$$Nest$fgetresetStatsDate(r2)[r1]), 0, 8);     // Catch: Exception -> L22
            r1 = r1 + 1;
            goto L9
        L16:
            int r12 = 0;
        L17:
            StatsController r65 = this.this$0;
            if (r12 >= 3) goto L20;
            r65.byteArrayOutputStream.write(StatsController.m4982$$Nest$mlongToBytes(r65, StatsController.m4977$$Nest$fgetsentBytes(r65)[r12][7]), 0, 8);     // Catch: Exception -> L22
            StatsController r66 = this.this$0;     // Catch: Exception -> L22
            r66.byteArrayOutputStream.write(StatsController.m4982$$Nest$mlongToBytes(r66, StatsController.m4974$$Nest$fgetreceivedBytes(r66)[r12][7]), 0, 8);     // Catch: Exception -> L22
            StatsController r67 = this.this$0;     // Catch: Exception -> L22
            r67.byteArrayOutputStream.write(StatsController.m4981$$Nest$mintToBytes(r67, StatsController.m4978$$Nest$fgetsentItems(r67)[r12][7]), 0, 4);     // Catch: Exception -> L22
            StatsController r68 = this.this$0;     // Catch: Exception -> L22
            r68.byteArrayOutputStream.write(StatsController.m4981$$Nest$mintToBytes(r68, StatsController.m4975$$Nest$fgetreceivedItems(r68)[r12][7]), 0, 4);     // Catch: Exception -> L22
            r12 = r12 + 1;     // Catch: Exception -> L22
            goto L17
        L20:
            StatsController.m4979$$Nest$fgetstatsFile(r65).seek(0);     // Catch: Exception -> L22
            RandomAccessFile r13 = StatsController.m4979$$Nest$fgetstatsFile(this.this$0);     // Catch: Exception -> L22
            ImmutableByteArrayOutputStream r22 = this.this$0.byteArrayOutputStream;     // Catch: Exception -> L22
            r13.write(r22.buf, 0, r22.count());     // Catch: Exception -> L22
            StatsController.m4979$$Nest$fgetstatsFile(this.this$0).getFD().sync();     // Catch: Exception -> L22
            return;
        L29:
            return;
        }
    }

    /* renamed from: -$$Nest$fgetcallsTotalTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m4972$$Nest$fgetcallsTotalTime(StatsController r0) {
        return r0.callsTotalTime;
    }

    /* renamed from: -$$Nest$fgetlastInternalStatsSaveTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m4973$$Nest$fgetlastInternalStatsSaveTime(StatsController r2) {
        return r2.lastInternalStatsSaveTime;
    }

    /* renamed from: -$$Nest$fgetreceivedBytes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long[][] m4974$$Nest$fgetreceivedBytes(StatsController r0) {
        return r0.receivedBytes;
    }

    /* renamed from: -$$Nest$fgetreceivedItems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[][] m4975$$Nest$fgetreceivedItems(StatsController r0) {
        return r0.receivedItems;
    }

    /* renamed from: -$$Nest$fgetresetStatsDate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long[] m4976$$Nest$fgetresetStatsDate(StatsController r0) {
        return r0.resetStatsDate;
    }

    /* renamed from: -$$Nest$fgetsentBytes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long[][] m4977$$Nest$fgetsentBytes(StatsController r0) {
        return r0.sentBytes;
    }

    /* renamed from: -$$Nest$fgetsentItems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[][] m4978$$Nest$fgetsentItems(StatsController r0) {
        return r0.sentItems;
    }

    /* renamed from: -$$Nest$fgetstatsFile, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RandomAccessFile m4979$$Nest$fgetstatsFile(StatsController r0) {
        return r0.statsFile;
    }

    /* renamed from: -$$Nest$fputlastInternalStatsSaveTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4980$$Nest$fputlastInternalStatsSaveTime(StatsController r0, long r1) {
        r0.lastInternalStatsSaveTime = r1;
    }

    /* renamed from: -$$Nest$mintToBytes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ byte[] m4981$$Nest$mintToBytes(StatsController r0, int r1) {
        return r0.intToBytes(r1);
    }

    /* renamed from: -$$Nest$mlongToBytes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ byte[] m4982$$Nest$mlongToBytes(StatsController r0, long r1) {
        return r0.longToBytes(r1);
    }

    static {
        statsSaveQueue = new DispatchQueue("statsSaveQueue");
        lastStatsSaveTime = new AnonymousClass1();
        Instance = new StatsController[16];
    }

    private StatsController(int r15) {
        super(r15);
        this.buffer = new byte[8];
        Class r6 = Long.TYPE;
        this.sentBytes = (long[][]) Array.newInstance(r6, new int[]{3, 8});
        this.receivedBytes = (long[][]) Array.newInstance(r6, new int[]{3, 8});
        Class r62 = Integer.TYPE;
        this.sentItems = (int[][]) Array.newInstance(r62, new int[]{3, 8});
        this.receivedItems = (int[][]) Array.newInstance(r62, new int[]{3, 8});
        this.resetStatsDate = new long[3];
        this.callsTotalTime = new int[3];
        this.byteArrayOutputStream = new ImmutableByteArrayOutputStream();
        this.saveRunnable = new AnonymousClass2(this);
        File r1 = ApplicationLoader.getFilesDirFixed();
        if (r15 == 0) goto L43;
        r1 = new File(ApplicationLoader.getFilesDirFixed(), "account" + r15 + "/");
        r1.mkdirs();
    L43:
        RandomAccessFile r2 = new RandomAccessFile(new File(r1, "stats2.dat"), "rw");     // Catch: Exception -> L42
        this.statsFile = r2;     // Catch: Exception -> L42
        if (r2.length() <= 0) goto L27;
        int r12 = 0;
        boolean r22 = false;
    L11:
        if (r12 >= 3) goto L20;
        int r10 = 0;
    L13:
        RandomAccessFile r11 = this.statsFile;
        if (r10 >= 7) goto L16;
        r11.readFully(this.buffer, 0, 8);     // Catch: Exception -> L42
        this.sentBytes[r12][r10] = bytesToLong(this.buffer);     // Catch: Exception -> L42
        this.statsFile.readFully(this.buffer, 0, 8);     // Catch: Exception -> L42
        this.receivedBytes[r12][r10] = bytesToLong(this.buffer);     // Catch: Exception -> L42
        this.statsFile.readFully(this.buffer, 0, 4);     // Catch: Exception -> L42
        this.sentItems[r12][r10] = bytesToInt(this.buffer);     // Catch: Exception -> L42
        this.statsFile.readFully(this.buffer, 0, 4);     // Catch: Exception -> L42
        this.receivedItems[r12][r10] = bytesToInt(this.buffer);     // Catch: Exception -> L42
        r10 = r10 + 1;     // Catch: Exception -> L42
        goto L13
    L16:
        r11.readFully(this.buffer, 0, 4);     // Catch: Exception -> L42
        this.callsTotalTime[r12] = bytesToInt(this.buffer);     // Catch: Exception -> L42
        this.statsFile.readFully(this.buffer, 0, 8);     // Catch: Exception -> L42
        this.resetStatsDate[r12] = bytesToLong(this.buffer);     // Catch: Exception -> L42
        long[] r8 = this.resetStatsDate;     // Catch: Exception -> L42
        if (r8[r12] != 0) goto L19;
        r8[r12] = System.currentTimeMillis();     // Catch: Exception -> L42
        r22 = true;
    L19:
        r12 = r12 + 1;     // Catch: Exception -> L42
        goto L11
    L20:
        int r13 = 0;
    L21:
        if (r13 >= 3) goto L23;
        this.statsFile.readFully(this.buffer, 0, 8);     // Catch: Exception -> L42
        this.sentBytes[r13][7] = bytesToLong(this.buffer);     // Catch: Exception -> L42
        this.statsFile.readFully(this.buffer, 0, 8);     // Catch: Exception -> L42
        this.receivedBytes[r13][7] = bytesToLong(this.buffer);     // Catch: Exception -> L42
        this.statsFile.readFully(this.buffer, 0, 4);     // Catch: Exception -> L42
        this.sentItems[r13][7] = bytesToInt(this.buffer);     // Catch: Exception -> L42
        this.statsFile.readFully(this.buffer, 0, 4);     // Catch: Exception -> L42
        this.receivedItems[r13][7] = bytesToInt(this.buffer);     // Catch: Exception -> L42
        r13 = r13 + 1;     // Catch: Exception -> L42
        goto L21
    L23:
        if (r22 == false) goto L54;
        saveStats();     // Catch: Exception -> L42
        return;
    L54:
        return;
    L27:
        if (r15 != 0) goto L29;
        SharedPreferences r152 = ApplicationLoader.applicationContext.getSharedPreferences("stats", 0);
    L30:
        int r14 = 0;
        boolean r23 = false;
    L31:
        if (r14 >= 3) goto L39;
        this.callsTotalTime[r14] = r152.getInt("callsTotalTime" + r14, 0);
        this.resetStatsDate[r14] = r152.getLong("resetStatsDate" + r14, 0);
        int r82 = 0;
    L33:
        if (r82 >= 8) goto L35;
        this.sentBytes[r14][r82] = r152.getLong("sentBytes" + r14 + "_" + r82, 0);
        this.receivedBytes[r14][r82] = r152.getLong("receivedBytes" + r14 + "_" + r82, 0);
        this.sentItems[r14][r82] = r152.getInt("sentItems" + r14 + "_" + r82, 0);
        this.receivedItems[r14][r82] = r152.getInt("receivedItems" + r14 + "_" + r82, 0);
        r82 = r82 + 1;
        goto L33
    L35:
        long[] r83 = this.resetStatsDate;
        if (r83[r14] != 0) goto L38;
        r83[r14] = System.currentTimeMillis();
        r23 = true;
    L38:
        r14 = r14 + 1;
        goto L31
    L39:
        if (r23 == false) goto L55;
        saveStats();
        return;
    L55:
        return;
    L29:
        r152 = ApplicationLoader.applicationContext.getSharedPreferences("stats" + r15, 0);
        goto L30
    }

    private int bytesToInt(byte[] r2) {
        return (((r2[0] << 24) | ((r2[1] & UByte.MAX_VALUE) << 16)) | ((r2[2] & UByte.MAX_VALUE) << 8)) | (r2[3] & UByte.MAX_VALUE);
    }

    private long bytesToLong(byte[] r7) {
        return (r7[7] & 255) | ((((((((r7[0] & 255) << 56) | ((r7[1] & 255) << 48)) | ((r7[2] & 255) << 40)) | ((r7[3] & 255) << 32)) | ((r7[4] & 255) << 24)) | ((r7[5] & 255) << 16)) | ((r7[6] & 255) << 8));
    }

    public static StatsController getInstance(int r3) {
        StatsController r0 = Instance[r3];
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(StatsController.class);
        StatsController r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        StatsController[] r03 = Instance;     // Catch: Throwable -> L9
        StatsController r2 = new StatsController(r3);     // Catch: Throwable -> L9
        r03[r3] = r2;     // Catch: Throwable -> L9
        r02 = r2;
    L11:
        monitor-exit(StatsController.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    private byte[] intToBytes(int r3) {
        byte[] r2 = this.buffer;
        r2[0] = (byte) (r3 >>> 24);
        r2[1] = (byte) (r3 >>> 16);
        r2[2] = (byte) (r3 >>> 8);
        r2[3] = (byte) r3;
        return r2;
    }

    private byte[] longToBytes(long r3) {
        byte[] r2 = this.buffer;
        r2[0] = (byte) (r3 >>> 56);
        r2[1] = (byte) (r3 >>> 48);
        r2[2] = (byte) (r3 >>> 40);
        r2[3] = (byte) (r3 >>> 32);
        r2[4] = (byte) (r3 >>> 24);
        r2[5] = (byte) (r3 >>> 16);
        r2[6] = (byte) (r3 >>> 8);
        r2[7] = (byte) r3;
        return r2;
    }

    private void saveStats() {
        long r0 = System.currentTimeMillis();
        ThreadLocal<Long> r2 = lastStatsSaveTime;
        if (Math.abs(r0 - r2.get().longValue()) < 2000) goto L6;
        r2.set(Long.valueOf(r0));
        statsSaveQueue.cancelRunnable(this.saveRunnable);
        statsSaveQueue.postRunnable(this.saveRunnable);
        return;
    }

    public int getCallsTotalTime(int r1) {
        return this.callsTotalTime[r1];
    }

    public long getReceivedBytesCount(int r3, int r4) {
        long[][] r2 = this.receivedBytes;
        if (r4 != 1) goto L7;
        long[] r22 = r2[r3];
        return ((((r22[6] - r22[5]) - r22[3]) - r22[2]) - r22[4]) - r22[7];
    L7:
        return r2[r3][r4];
    }

    public int getRecivedItemsCount(int r1, int r2) {
        return this.receivedItems[r1][r2];
    }

    public long getResetStatsDate(int r3) {
        return this.resetStatsDate[r3];
    }

    public long getSentBytesCount(int r3, int r4) {
        long[][] r2 = this.sentBytes;
        if (r4 != 1) goto L7;
        long[] r22 = r2[r3];
        return ((((r22[6] - r22[5]) - r22[3]) - r22[2]) - r22[4]) - r22[7];
    L7:
        return r2[r3][r4];
    }

    public int getSentItemsCount(int r1, int r2) {
        return this.sentItems[r1][r2];
    }

    public void incrementReceivedBytesCount(int r3, int r4, long r5) {
        long[] r32 = this.receivedBytes[r3];
        r32[r4] = r32[r4] + r5;
        saveStats();
    }

    public void incrementReceivedItemsCount(int r2, int r3, int r4) {
        int[] r22 = this.receivedItems[r2];
        r22[r3] = r22[r3] + r4;
        saveStats();
    }

    public void incrementSentBytesCount(int r3, int r4, long r5) {
        long[] r32 = this.sentBytes[r3];
        r32[r4] = r32[r4] + r5;
        saveStats();
    }

    public void incrementSentItemsCount(int r2, int r3, int r4) {
        int[] r22 = this.sentItems[r2];
        r22[r3] = r22[r3] + r4;
        saveStats();
    }

    public void incrementTotalCallsTime(int r3, int r4) {
        int[] r0 = this.callsTotalTime;
        r0[r3] = r0[r3] + r4;
        saveStats();
    }

    public void resetStats(int r6) {
        this.resetStatsDate[r6] = System.currentTimeMillis();
        int r1 = 0;
    L4:
        if (r1 >= 8) goto L6;
        this.sentBytes[r6][r1] = 0;
        this.receivedBytes[r6][r1] = 0;
        this.sentItems[r6][r1] = 0;
        this.receivedItems[r6][r1] = 0;
        r1 = r1 + 1;
        goto L4
    L6:
        this.callsTotalTime[r6] = 0;
        saveStats();
    }
}
