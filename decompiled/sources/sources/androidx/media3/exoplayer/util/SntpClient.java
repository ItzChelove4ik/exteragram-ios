package androidx.media3.exoplayer.util;

import android.os.SystemClock;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.upstream.Loader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import kotlin.UByte;
import okio.internal.ZipFilesKt$$ExternalSyntheticBUOutline0;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class SntpClient {
    private static long elapsedRealtimeOffsetMs = 0;
    private static boolean isInitialized = false;
    private static long lastUpdateElapsedRealtime = -9223372036854775807L;
    private static final Object loaderLock = null;
    private static long maxElapsedTimeUntilUpdateMs = -9223372036854775807L;
    private static String ntpHost = "time.android.com";
    private static int timeoutMs = 1000;
    private static final Object valueLock = null;

    /* renamed from: androidx.media3.exoplayer.util.SntpClient$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public interface InitializationCallback {
        void onInitializationFailed(IOException r1);

        void onInitialized();
    }

    public static final class NtpTimeCallback implements Loader.Callback<Loader.Loadable> {
        private final InitializationCallback callback;

        public NtpTimeCallback(InitializationCallback r1) {
            this.callback = r1;
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public void onLoadCanceled(Loader.Loadable r1, long r2, long r4, boolean r6) {
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public void onLoadCompleted(Loader.Loadable r1, long r2, long r4) {
            if (this.callback == null) goto L10;
            boolean r12 = SntpClient.isInitialized();
            InitializationCallback r0 = this.callback;
            if (r12 == true) goto L8;
            r0.onInitializationFailed(new IOException(new ConcurrentModificationException()));
            return;
        L8:
            r0.onInitialized();
            return;
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Callback
        public Loader.LoadErrorAction onLoadError(Loader.Loadable r1, long r2, long r4, IOException r6, int r7) {
            InitializationCallback r0 = this.callback;
            if (r0 == null) goto L6;
            r0.onInitializationFailed(r6);
        L6:
            return Loader.DONT_RETRY;
        }
    }

    public static final class NtpTimeLoadable implements Loader.Loadable {
        private NtpTimeLoadable() {
        }

        public /* synthetic */ NtpTimeLoadable(AnonymousClass1 r1) {
            this();
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
        public void cancelLoad() {
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
        public void load() {
            Object r5 = SntpClient.access$100();
            monitor-enter(r5);
            Object r0 = SntpClient.access$200();     // Catch: Throwable -> L11
            monitor-enter(r0);     // Catch: Throwable -> L11
        L13:
            th = move-exception;
            throw th;     // Catch: Throwable -> L11
        L7:
            if (SntpClient.access$300() == false) goto L15;
            monitor-exit(r0);     // Catch: Throwable -> L13
            monitor-exit(r5);     // Catch: Throwable -> L11
            return;
        L15:
            monitor-exit(r0);     // Catch: Throwable -> L13
            long r02 = SntpClient.access$400();     // Catch: Throwable -> L11
            Object r2 = SntpClient.access$200();     // Catch: Throwable -> L11
            monitor-enter(r2);     // Catch: Throwable -> L11
            SntpClient.access$502(SystemClock.elapsedRealtime());     // Catch: Throwable -> L22
            SntpClient.access$602(r02);     // Catch: Throwable -> L22
            SntpClient.access$302(true);     // Catch: Throwable -> L22
            monitor-exit(r2);     // Catch: Throwable -> L22
            monitor-exit(r5);     // Catch: Throwable -> L11
            return;
        L22:
            th = move-exception;
            throw th;     // Catch: Throwable -> L11
        L11:
            th = move-exception;
            throw th;
        }
    }

    static {
        loaderLock = new Object();
        valueLock = new Object();
    }

    public static /* synthetic */ Object access$100() {
        return loaderLock;
    }

    public static /* synthetic */ Object access$200() {
        return valueLock;
    }

    public static /* synthetic */ boolean access$300() {
        return isInitialized;
    }

    public static /* synthetic */ boolean access$302(boolean r0) {
        isInitialized = r0;
        return r0;
    }

    public static /* synthetic */ long access$400() {
        return loadNtpTimeOffsetMs();
    }

    public static /* synthetic */ long access$502(long r0) {
        lastUpdateElapsedRealtime = r0;
        return r0;
    }

    public static /* synthetic */ long access$602(long r0) {
        elapsedRealtimeOffsetMs = r0;
        return r0;
    }

    private static void checkValidServerReply(byte r1, byte r2, int r3, long r4) {
        if (r1 != 3) goto L5;
        Model$$ExternalSyntheticBUOutline0.m("SNTP: Unsynchronized server");
        return;
    L5:
        if (r2 != 4) goto L7;
    L11:
        if (r3 != 0) goto L13;
    L19:
        ZipFilesKt$$ExternalSyntheticBUOutline0.m("SNTP: Untrusted stratum: ", r3);
        return;
    L13:
        if (r3 > 15) goto L19;
        if (r4 == 0) goto L17;
        return;
    L17:
        Model$$ExternalSyntheticBUOutline0.m("SNTP: Zero transmitTime");
        return;
    L7:
        if (r2 == 5) goto L11;
        ZipFilesKt$$ExternalSyntheticBUOutline0.m("SNTP: Untrusted mode: ", r2);
    }

    public static long getElapsedRealtimeOffsetMs() {
        Object r0 = valueLock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (isInitialized == false) goto L9;
        long r1 = elapsedRealtimeOffsetMs;     // Catch: Throwable -> L7
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L9:
        r1 = -9223372036854775807L;
        goto L10
    }

    public static String getNtpHost() {
        Object r0 = valueLock;
        monitor-enter(r0);
        String r1 = ntpHost;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public static int getTimeoutMs() {
        Object r0 = valueLock;
        monitor-enter(r0);
        int r1 = timeoutMs;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public static void initialize(Loader r2, InitializationCallback r3) {
        if (isInitialized() == false) goto L7;
        if (r3 == null) goto L11;
        r3.onInitialized();
        return;
    L11:
        return;
    L7:
        if (r2 != null) goto L9;
        r2 = new Loader("SntpClient");
    L9:
        r2.startLoading(new NtpTimeLoadable(null), new NtpTimeCallback(r3), 1);
    }

    public static boolean isInitialized() {
        Object r0 = valueLock;
        monitor-enter(r0);
    L13:
        th = move-exception;
        throw th;
    L5:
        if (lastUpdateElapsedRealtime != (-9223372036854775807L)) goto L7;
    L17:
        boolean r1 = isInitialized;     // Catch: Throwable -> L13
        monitor-exit(r0);     // Catch: Throwable -> L13
        return r1;
    L7:
        if (maxElapsedTimeUntilUpdateMs == (-9223372036854775807L)) goto L17;
        long r12 = SystemClock.elapsedRealtime() - lastUpdateElapsedRealtime;     // Catch: Throwable -> L13
        if (isInitialized == true) goto L11;
    L15:
        boolean r13 = false;
    L16:
        isInitialized = r13;     // Catch: Throwable -> L13
        goto L17
    L11:
        if (r12 >= maxElapsedTimeUntilUpdateMs) goto L15;
        r13 = true;
        goto L16
    }

    private static long loadNtpTimeOffsetMs() {
        DatagramSocket r1 = new DatagramSocket();
        r1.setSoTimeout(getTimeoutMs());     // Catch: Throwable -> L10
        InetAddress[] r2 = InetAddress.getAllByName(getNtpHost());     // Catch: Throwable -> L10
        int r3 = r2.length;     // Catch: Throwable -> L10
        SocketTimeoutException r5 = null;
        int r6 = 0;
        int r7 = 0;
    L4:
        if (r6 >= r3) goto L20;
        byte[] r9 = new byte[48];     // Catch: Throwable -> L10
        DatagramPacket r10 = new DatagramPacket(r9, 48, r2[r6], 123);     // Catch: Throwable -> L10
        r9[0] = 27;     // Catch: Throwable -> L10
        long r11 = System.currentTimeMillis();     // Catch: Throwable -> L10
        long r13 = SystemClock.elapsedRealtime();     // Catch: Throwable -> L10
        writeTimestamp(r9, 40, r11);     // Catch: Throwable -> L10
        r1.send(r10);     // Catch: Throwable -> L10
        r1.receive(new DatagramPacket(r9, 48));     // Catch: Throwable -> L10 SocketTimeoutException -> L12
    L7:
        long r22 = SystemClock.elapsedRealtime();     // Catch: Throwable -> L10
        long r112 = r11 + (r22 - r13);     // Catch: Throwable -> L10
        byte r4 = r9[0];     // Catch: Throwable -> L10
        int r62 = r9[1] & UByte.MAX_VALUE;     // Catch: Throwable -> L10
        long r72 = readTimestamp(r9, 24);     // Catch: Throwable -> L10
        long r132 = readTimestamp(r9, 32);     // Catch: Throwable -> L10
        long r92 = readTimestamp(r9, 40);     // Catch: Throwable -> L10
        checkValidServerReply((byte) ((r4 >> 6) & 3), (byte) (r4 & 7), r62, r92);     // Catch: Throwable -> L10
        long r113 = (r112 + (((r132 - r72) + (r92 - r112)) / 2)) - r22;
        r1.close();
        return r113;
    L12:
        e = move-exception;
        if (r5 != null) goto L15;
        r5 = e;
    L16:
        int r0 = r7 + 1;     // Catch: Throwable -> L10
        if (r7 >= 10) goto L20;
        r6 = r6 + 1;     // Catch: Throwable -> L10
        r7 = r0;
        goto L4
    L15:
        r5.addSuppressed(e);     // Catch: Throwable -> L10
    L20:
        throw ((SocketTimeoutException) Assertions.checkNotNull(r5));     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L23
        throw th;
    L23:
        th = move-exception;
        th.addSuppressed(th);
        throw th;
    }

    private static long read32(byte[] r5, int r6) {
        int r0 = r5[r6];
        int r1 = r5[r6 + 1];
        int r2 = r5[r6 + 2];
        int r52 = r5[r6 + 3];
        if ((r0 & 128) != 128) goto L6;
        r0 = (r0 & 127) + 128;
    L6:
        if ((r1 & 128) != 128) goto L9;
        r1 = (r1 & 127) + 128;
    L9:
        if ((r2 & 128) != 128) goto L12;
        r2 = (r2 & 127) + 128;
    L12:
        if ((r52 & 128) != 128) goto L15;
        r52 = (r52 & 127) + 128;
    L15:
        return (((r0 << 24) + (r1 << 16)) + (r2 << 8)) + r52;
    }

    private static long readTimestamp(byte[] r5, int r6) {
        long r0 = read32(r5, r6);
        long r52 = read32(r5, r6 + 4);
        if (r0 != 0) goto L8;
        if (r52 != 0) goto L8;
        return 0;
    L8:
        return ((r0 - 2208988800L) * 1000) + ((r52 * 1000) / 4294967296L);
    }

    private static void writeTimestamp(byte[] r11, int r12, long r13) {
        if (r13 != 0) goto L6;
        Arrays.fill(r11, r12, r12 + 8, (byte) 0);
        return;
    L6:
        long r2 = r13 / 1000;
        long r132 = r13 - (r2 * 1000);
        r11[r12] = (byte) (r2 >> 24);
        r11[r12 + 1] = (byte) (r2 >> 16);
        r11[r12 + 2] = (byte) (r2 >> 8);
        r11[r12 + 3] = (byte) (r2 + 2208988800L);
        long r133 = (r132 * 4294967296L) / 1000;
        r11[r12 + 4] = (byte) (r133 >> 24);
        r11[r12 + 5] = (byte) (r133 >> 16);
        r11[r12 + 6] = (byte) (r133 >> 8);
        r11[r12 + 7] = (byte) (Math.random() * 255.0d);
    }
}
