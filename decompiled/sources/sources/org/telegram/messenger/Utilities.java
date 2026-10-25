package org.telegram.messenger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.Uri;
import com.android.dx.AppDataDirGuesser;
import com.carrotsearch.randomizedtesting.Xoroshiro128PlusRandom;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.UByte;
import okhttp3.internal.url._UrlKt;
import org.telegram.tgnet.ConnectionsManager;

/* loaded from: classes.dex */
public class Utilities {
    private static final String RANDOM_STRING_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static volatile DispatchQueue cacheClearQueue;
    public static volatile DispatchQueue externalNetworkQueue;
    public static Random fastRandom;
    public static volatile DispatchQueue globalQueue;
    protected static final char[] hexArray = null;
    public static Pattern pattern;
    public static volatile DispatchQueue phoneBookQueue;
    public static volatile DispatchQueue pluginsQueue;
    public static SecureRandom random;
    public static volatile DispatchQueue searchQueue;
    public static volatile DispatchQueue stageQueue;
    public static volatile DispatchQueue themeQueue;
    public static volatile DispatchQueue videoPlayerQueue;

    public interface Callback<T> {
        void run(T r1);
    }

    public interface Callback0Return<ReturnType> {
        ReturnType run();
    }

    public interface Callback2<T, T2> {
        void run(T r1, T2 r2);
    }

    public interface Callback2Return<T1, T2, ReturnType> {
        ReturnType run(T1 r1, T2 r2);
    }

    public interface Callback3<T, T2, T3> {
        void run(T r1, T2 r2, T3 r3);
    }

    public interface Callback3Return<T1, T2, T3, ReturnType> {
        ReturnType run(T1 r1, T2 r2, T3 r3);
    }

    public interface Callback4<T, T2, T3, T4> {
        void run(T r1, T2 r2, T3 r3, T4 r4);
    }

    public interface Callback4Return<T, T2, T3, T4, ReturnType> {
        ReturnType run(T r1, T2 r2, T3 r3, T4 r4);
    }

    public interface Callback5<T, T2, T3, T4, T5> {
        void run(T r1, T2 r2, T3 r3, T4 r4, T5 r5);
    }

    public interface Callback5Return<T, T2, T3, T4, T5, ReturnType> {
        ReturnType run(T r1, T2 r2, T3 r3, T4 r4, T5 r5);
    }

    public interface CallbackReturn<Arg, ReturnType> {
        ReturnType run(Arg r1);
    }

    public interface CallbackVoidReturn<ReturnType> {
        ReturnType run();
    }

    public interface IndexedConsumer<T> {
        void accept(T r1, int r2);
    }

    public enum libyuv_ScaleFilter extends Enum<libyuv_ScaleFilter> {
        private static final /* synthetic */ libyuv_ScaleFilter[] $VALUES = null;
        public static final libyuv_ScaleFilter Bilinear = null;
        public static final libyuv_ScaleFilter Box = null;
        public static final libyuv_ScaleFilter Linear = null;
        public static final libyuv_ScaleFilter None = null;

        private static /* synthetic */ libyuv_ScaleFilter[] $values() {
            return new libyuv_ScaleFilter[]{None, Linear, Bilinear, Box};
        }

        static {
            None = new libyuv_ScaleFilter("None", 0);
            Linear = new libyuv_ScaleFilter("Linear", 1);
            Bilinear = new libyuv_ScaleFilter("Bilinear", 2);
            Box = new libyuv_ScaleFilter("Box", 3);
            $VALUES = $values();
        }

        libyuv_ScaleFilter(String r1, int r2) {
        }

        public static libyuv_ScaleFilter valueOf(String r1) {
            return (libyuv_ScaleFilter) Enum.valueOf(libyuv_ScaleFilter.class, r1);
        }

        public static libyuv_ScaleFilter[] values() {
            return (libyuv_ScaleFilter[]) $VALUES.clone();
        }
    }

    public static /* synthetic */ void $r8$lambda$7LYngopHvLcfmuoeB4pq0gKlpg4(int[] r2, Callback[] r3, Runnable r4) {
        int r1 = r2[0] + 1;
        r2[0] = r1;
        if (r1 != r3.length) goto L7;
        if (r4 == null) goto L8;
        r4.run();
        return;
    L8:
        return;
    }

    /* renamed from: $r8$lambda$LHYJzz-yENfnvJAEigmT9zWlizY, reason: not valid java name */
    public static /* synthetic */ void m5049$r8$lambda$LHYJzzyENfnvJAEigmT9zWlizY(int r0, Callback[] r1) {
        doCallbacks(r0 + 1, r1);
    }

    static {
        pattern = Pattern.compile("[\\-0-9]+");
        random = new SecureRandom();
        fastRandom = new Xoroshiro128PlusRandom(random.nextLong());
        stageQueue = new DispatchQueue("stageQueue");
        globalQueue = new DispatchQueue("globalQueue");
        cacheClearQueue = new DispatchQueue("cacheClearQueue");
        searchQueue = new DispatchQueue("searchQueue");
        phoneBookQueue = new DispatchQueue("phoneBookQueue");
        themeQueue = new DispatchQueue("themeQueue");
        externalNetworkQueue = new DispatchQueue("externalNetworkQueue");
        pluginsQueue = new DispatchQueue("pluginsQueue");
        hexArray = "0123456789ABCDEF".toCharArray();
        FileInputStream r1 = new FileInputStream(new File("/dev/urandom"));     // Catch: Exception -> L5
        byte[] r0 = new byte[1024];     // Catch: Exception -> L5
        r1.read(r0);     // Catch: Exception -> L5
        r1.close();     // Catch: Exception -> L5
        random.setSeed(r0);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    public Utilities() {
    }

    public static String MD5(String r7) {
        if (r7 != null) goto L14;
        return null;
    L14:
        byte[] r72 = MessageDigest.getInstance("MD5").digest(AndroidUtilities.getStringBytes(r7));     // Catch: NoSuchAlgorithmException -> L8
        StringBuilder r1 = new StringBuilder();     // Catch: NoSuchAlgorithmException -> L8
        int r2 = r72.length;     // Catch: NoSuchAlgorithmException -> L8
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L10;
        r1.append(Integer.toHexString((r72[r3] & UByte.MAX_VALUE) | 256).substring(1, 3));     // Catch: NoSuchAlgorithmException -> L8
        r3 = r3 + 1;     // Catch: NoSuchAlgorithmException -> L8
        goto L6
    L10:
        return r1.toString();
    L8:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static String SHA256(String r6) {
        if (r6 != null) goto L15;
        return null;
    L15:
        byte[] r62 = MessageDigest.getInstance("SHA-256").digest(AndroidUtilities.getStringBytes(r6));     // Catch: NoSuchAlgorithmException -> L9
        StringBuilder r1 = new StringBuilder();     // Catch: NoSuchAlgorithmException -> L9
        int r2 = 0;
    L7:
        if (r2 >= r62.length) goto L11;
        r1.append(Integer.toHexString((r62[r2] & UByte.MAX_VALUE) | 256).substring(1, 3));     // Catch: NoSuchAlgorithmException -> L9
        r2 = r2 + 1;     // Catch: NoSuchAlgorithmException -> L9
        goto L7
    L11:
        return r1.toString();
    L9:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static native void aesCbcEncryption(ByteBuffer r0, byte[] r1, byte[] r2, int r3, int r4, int r5);

    private static native void aesCbcEncryptionByteArray(byte[] r0, byte[] r1, byte[] r2, int r3, int r4, int r5, int r6);

    public static void aesCbcEncryptionByteArraySafe(byte[] r7, byte[] r8, byte[] r9, int r10, int r11, int r12, int r13) {
        aesCbcEncryptionByteArray(r7, r8, (byte[]) r9.clone(), r10, r11, r12, r13);
    }

    public static native void aesCtrDecryption(ByteBuffer r0, byte[] r1, byte[] r2, int r3, int r4);

    public static native void aesCtrDecryptionByteArray(byte[] r0, byte[] r1, byte[] r2, int r3, long r4, int r6);

    private static native void aesIgeEncryption(ByteBuffer r0, byte[] r1, byte[] r2, boolean r3, int r4, int r5);

    public static void aesIgeEncryption(ByteBuffer r6, byte[] r7, byte[] r8, boolean r9, boolean r10, int r11, int r12) {
        if (r10 == true) goto L5;
        r8 = (byte[]) r8.clone();
    L5:
        aesIgeEncryption(r6, r7, r8, r9, r11, r12);
    }

    private static native void aesIgeEncryptionByteArray(byte[] r0, byte[] r1, byte[] r2, boolean r3, int r4, int r5);

    public static void aesIgeEncryptionByteArray(byte[] r6, byte[] r7, byte[] r8, boolean r9, boolean r10, int r11, int r12) {
        if (r10 == true) goto L5;
        r8 = (byte[]) r8.clone();
    L5:
        aesIgeEncryptionByteArray(r6, r7, r8, r9, r11, r12);
    }

    public static native boolean applyAlphaInvert(Bitmap r0, Bitmap r1, int r2);

    public static native boolean applySoftLight(Bitmap r0, Bitmap r1, int r2);

    public static boolean arraysEquals(byte[] r4, int r5, byte[] r6, int r7) {
        if (r4 == null) goto L23;
        if (r6 == null) goto L23;
        if (r5 < 0) goto L23;
        if (r7 < 0) goto L23;
        if ((r4.length - r5) > (r6.length - r7)) goto L23;
        if ((r4.length - r5) < 0) goto L23;
        if ((r6.length - r7) < 0) goto L23;
        int r1 = r5;
    L16:
        if (r1 >= r4.length) goto L21;
        if (r4[r1 + r5] != r6[r1 + r7]) goto L19;
        r1 = r1 + 1;
        goto L16
    L19:
        return false;
    L21:
        return true;
    L23:
        return false;
    }

    public static native int averageBitmapColor(Bitmap r0, int r1, int r2, int r3, int r4);

    public static native void blurBitmap(Object r0, int r1);

    public static Bitmap blurWallpaper(Bitmap r7) {
        if (r7 != null) goto L6;
        return null;
    L6:
        if (r7.getHeight() <= r7.getWidth()) goto L8;
        Bitmap r1 = Bitmap.createBitmap(Math.round((r7.getWidth() * 450.0f) / r7.getHeight()), 450, Bitmap.Config.ARGB_8888);
    L9:
        Paint r2 = new Paint(2);
        new Canvas(r1).drawBitmap(r7, null, new Rect(0, 0, r1.getWidth(), r1.getHeight()), r2);
        stackBlurBitmap(r1, 12);
        return r1;
    L8:
        r1 = Bitmap.createBitmap(450, Math.round((r7.getHeight() * 450.0f) / r7.getWidth()), Bitmap.Config.ARGB_8888);
        goto L9
    }

    public static String bytesToHex(byte[] r6) {
        if (r6 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        char[] r0 = new char[r6.length * 2];
        int r1 = 0;
    L7:
        if (r1 >= r6.length) goto L10;
        byte r2 = r6[r1];
        int r3 = r2 & UByte.MAX_VALUE;
        int r4 = r1 * 2;
        char[] r5 = hexArray;
        r0[r4] = r5[r3 >>> 4];
        r0[r4 + 1] = r5[r2 & 15];
        r1 = r1 + 1;
        goto L7
    L10:
        return new String(r0);
    }

    public static int bytesToInt(byte[] r2) {
        return ((((r2[3] & UByte.MAX_VALUE) << 24) + ((r2[2] & UByte.MAX_VALUE) << 16)) + ((r2[1] & UByte.MAX_VALUE) << 8)) + (r2[0] & UByte.MAX_VALUE);
    }

    public static long bytesToLong(byte[] r7) {
        return (((((((r7[7] << 56) + ((r7[6] & 255) << 48)) + ((r7[5] & 255) << 40)) + ((r7[4] & 255) << 32)) + ((r7[3] & 255) << 24)) + ((r7[2] & 255) << 16)) + ((r7[1] & 255) << 8)) + (r7[0] & 255);
    }

    public static native void calcCDT(ByteBuffer r0, int r1, int r2, ByteBuffer r3, ByteBuffer r4);

    public static double clamp(double r1, double r3, double r5) {
        if (Double.isNaN(r1) == false) goto L6;
        return r5;
    L6:
        if (Double.isInfinite(r1) == false) goto L9;
        return r3;
    L9:
        return Math.max(Math.min(r1, r3), r5);
    }

    public static float clamp(float r1, float r2, float r3) {
        if (Float.isNaN(r1) == false) goto L6;
        return r3;
    L6:
        if (Float.isInfinite(r1) == false) goto L9;
        return r2;
    L9:
        return Math.max(Math.min(r1, r2), r3);
    }

    public static int clamp(int r0, int r1, int r2) {
        return Math.max(Math.min(r0, r1), r2);
    }

    public static long clamp(long r0, long r2, long r4) {
        return Math.max(Math.min(r0, r2), r4);
    }

    public static float clamp01(float r2) {
        return clamp(r2, 1.0f, 0.0f);
    }

    public static native void clearDir(String r0, int r1, long r2, boolean r4);

    public static byte[] computePBKDF2(byte[] r2, byte[] r3) {
        byte[] r0 = new byte[64];
        pbkdf2(r2, r3, r0, AppDataDirGuesser.PER_USER_RANGE);
        return r0;
    }

    public static byte[] computeSHA1(ByteBuffer r2) {
        return computeSHA1(r2, 0, r2.limit());
    }

    public static byte[] computeSHA1(ByteBuffer r3, int r4, int r5) {
        int r0 = r3.position();
        int r1 = r3.limit();
        MessageDigest r2 = MessageDigest.getInstance("SHA-1");     // Catch: Throwable -> L6 Exception -> L8
        r3.position(r4);     // Catch: Throwable -> L6 Exception -> L8
        r3.limit(r5);     // Catch: Throwable -> L6 Exception -> L8
        r2.update(r3);     // Catch: Throwable -> L6 Exception -> L8
        byte[] r42 = r2.digest();     // Catch: Throwable -> L6 Exception -> L8
        r3.limit(r1);
        r3.position(r0);
        return r42;
    L6:
        th = move-exception;
        r3.limit(r1);
        r3.position(r0);
        throw th;
    L8:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L6
        r3.limit(r1);
        r3.position(r0);
        return new byte[20];
    }

    public static byte[] computeSHA1(byte[] r2) {
        return computeSHA1(r2, 0, r2.length);
    }

    public static byte[] computeSHA1(byte[] r1, int r2, int r3) {
        MessageDigest r0 = MessageDigest.getInstance("SHA-1");     // Catch: Exception -> L4
        r0.update(r1, r2, r3);     // Catch: Exception -> L4
        return r0.digest();
    L4:
        e = move-exception;
        FileLog.e(e);
        return new byte[20];
    }

    public static byte[] computeSHA256(byte[] r3) {
        return computeSHA256(r3, 0, r3.length);
    }

    public static byte[] computeSHA256(byte[] r3, int r4, int r5, ByteBuffer r6, int r7, int r8) {
        int r0 = r6.position();
        int r1 = r6.limit();
        MessageDigest r2 = MessageDigest.getInstance("SHA-256");     // Catch: Throwable -> L6 Exception -> L8
        r2.update(r3, r4, r5);     // Catch: Throwable -> L6 Exception -> L8
        r6.position(r7);     // Catch: Throwable -> L6 Exception -> L8
        r6.limit(r8);     // Catch: Throwable -> L6 Exception -> L8
        r2.update(r6);     // Catch: Throwable -> L6 Exception -> L8
        byte[] r32 = r2.digest();     // Catch: Throwable -> L6 Exception -> L8
        r6.limit(r1);
        r6.position(r0);
        return r32;
    L6:
        th = move-exception;
        r6.limit(r1);
        r6.position(r0);
        throw th;
    L8:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L6
        r6.limit(r1);
        r6.position(r0);
        return new byte[32];
    }

    public static byte[] computeSHA256(byte[] r1, int r2, long r3) {
        MessageDigest r0 = MessageDigest.getInstance("SHA-256");     // Catch: Exception -> L4
        r0.update(r1, r2, (int) r3);     // Catch: Exception -> L4
        return r0.digest();
    L4:
        e = move-exception;
        FileLog.e(e);
        return new byte[32];
    }

    public static byte[] computeSHA256(byte[]... r6) {
        MessageDigest r0 = MessageDigest.getInstance("SHA-256");     // Catch: Exception -> L7
        int r1 = r6.length;     // Catch: Exception -> L7
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        byte[] r4 = r6[r3];     // Catch: Exception -> L7
        r0.update(r4, 0, r4.length);     // Catch: Exception -> L7
        r3 = r3 + 1;     // Catch: Exception -> L7
        goto L3
    L5:
        return r0.digest();
    L7:
        e = move-exception;
        FileLog.e(e);
        return new byte[32];
    }

    public static byte[] computeSHA512(byte[] r3) {
        MessageDigest r0 = MessageDigest.getInstance("SHA-512");     // Catch: Exception -> L4
        r0.update(r3, 0, r3.length);     // Catch: Exception -> L4
        return r0.digest();
    L4:
        e = move-exception;
        FileLog.e(e);
        return new byte[64];
    }

    public static byte[] computeSHA512(byte[] r3, byte[] r4) {
        MessageDigest r0 = MessageDigest.getInstance("SHA-512");     // Catch: Exception -> L4
        r0.update(r3, 0, r3.length);     // Catch: Exception -> L4
        r0.update(r4, 0, r4.length);     // Catch: Exception -> L4
        return r0.digest();
    L4:
        e = move-exception;
        FileLog.e(e);
        return new byte[64];
    }

    public static byte[] computeSHA512(byte[] r3, byte[] r4, byte[] r5) {
        MessageDigest r0 = MessageDigest.getInstance("SHA-512");     // Catch: Exception -> L4
        r0.update(r3, 0, r3.length);     // Catch: Exception -> L4
        r0.update(r4, 0, r4.length);     // Catch: Exception -> L4
        r0.update(r5, 0, r5.length);     // Catch: Exception -> L4
        return r0.digest();
    L4:
        e = move-exception;
        FileLog.e(e);
        return new byte[64];
    }

    public static native boolean copyBitmaps(Bitmap r0, Bitmap r1);

    public static float dist(float r0, float r1, float r2, float r3) {
        float r02 = r0 - r2;
        float r12 = r1 - r3;
        return (float) Math.sqrt((r02 * r02) + (r12 * r12));
    }

    public static int divCeil(int r0, int r1) {
        return ((r0 + r1) - 1) / r1;
    }

    private static void doCallbacks(int r2, Callback<Runnable>... r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.length <= r2) goto L8;
        r3[r2].run(new Utilities$$ExternalSyntheticLambda0(r2, r3));
        return;
    }

    public static void doCallbacks(Callback<Runnable>... r1) {
        doCallbacks(0, r1);
    }

    public static native boolean drawDitheredGradient(Bitmap r0, int[] r1, int r2, int r3, int r4, int r5);

    public static native boolean drawReplyLinePattern(Bitmap r0, int r1, int r2, int r3, int r4, boolean r5);

    public static native boolean expandAlphaToBlack(Bitmap r0, Bitmap r1);

    public static native boolean extractAlpha(Bitmap r0, Bitmap r1);

    public static native void generateGradient(Bitmap r0, int r1, float r2, int[] r3);

    public static String generateRandomString() {
        return generateRandomString(16);
    }

    public static String generateRandomString(int r4) {
        StringBuilder r0 = new StringBuilder();
        int r1 = 0;
    L3:
        if (r1 >= r4) goto L6;
        r0.append(RANDOM_STRING_CHARS.charAt(fastRandom.nextInt(62)));
        r1 = r1 + 1;
        goto L3
    L6:
        return r0.toString();
    }

    public static native long getDirSize(String r0, int r1, boolean r2);

    public static String getExtension(String r3) {
        int r0 = r3.lastIndexOf(46);
        if (r0 == (-1)) goto L5;
        String r32 = r3.substring(r0 + 1);
    L6:
        if (r32 != null) goto L9;
        return null;
    L9:
        return r32.toUpperCase();
    L5:
        r32 = null;
        goto L6
    }

    public static native long getLastUsageFileTime(String r0);

    public static DispatchQueue getOrCreatePlayerQueue() {
        if (videoPlayerQueue != null) goto L6;
        videoPlayerQueue = new DispatchQueue("playerQueue");
    L6:
        return videoPlayerQueue;
    }

    public static <Key, Value> Value getOrDefault(HashMap<Key, Value> r0, Key r1, Value r2) {
        Value r02 = r0.get(r1);
        if (r02 != null) goto L5;
        return r2;
    L5:
        return r02;
    }

    public static byte[] hexToBytes(String r7) {
        if (r7 != null) goto L5;
        return null;
    L5:
        int r0 = r7.length();
        byte[] r1 = new byte[r0 / 2];
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L8;
        r1[r2 / 2] = (byte) ((Character.digit(r7.charAt(r2), 16) << 4) + Character.digit(r7.charAt(r2 + 1), 16));
        r2 = r2 + 2;
        goto L6
    L8:
        return r1;
    }

    public static byte[] intToBytes(int r5) {
        return new byte[]{(byte) (r5 >>> 24), (byte) (r5 >>> 16), (byte) (r5 >>> 8), (byte) r5};
    }

    public static boolean isGoodGaAndGb(BigInteger r3, BigInteger r4) {
        if (r3.compareTo(BigInteger.valueOf(1)) > 0) goto L5;
        return false;
    L5:
        if (r3.compareTo(r4.subtract(BigInteger.valueOf(1))) >= 0) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isGoodPrime(byte[] r0, int r1) {
        return ConnectionsManager.native_isGoodPrime(r0, r1);
    }

    public static boolean isNullOrEmpty(Collection<?> r0) {
        if (r0 != null) goto L4;
        return true;
    L4:
        if (r0.isEmpty() == true) goto L10;
        return false;
    L10:
        return true;
    }

    public static boolean libyuvARGBSaleBitmap(Bitmap r0, Bitmap r1, libyuv_ScaleFilter r2) {
        return nLibyuvARGBSaleBitmap(r0, r1, r2.ordinal());
    }

    private static native boolean nLibyuvARGBSaleBitmap(Bitmap r0, Bitmap r1, int r2);

    public static native int needInvert(Object r0);

    public static int parseInt(CharSequence r13) {
        int r0 = 0;
        if (r13 != null) goto L5;
        return 0;
    L5:
        int r1 = r13.length();
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L21;
        char r5 = r13.charAt(r2);
        if (r5 < '0') goto L13;
        if (r5 <= '9') goto L21;
    L13:
        if (r5 != '-') goto L20;
        int r52 = r2 + 1;
        if (r52 >= r1) goto L20;
        char r6 = r13.charAt(r52);
        if (r6 < '0') goto L20;
        if (r6 > '9') goto L20;
        boolean r53 = true;
        r2 = r52;
    L22:
        if (r2 < r1) goto L25;
        return 0;
    L25:
        if (r53 == false) goto L27;
        int r7 = Integer.MIN_VALUE;
    L28:
        int r8 = r7 / 10;
    L29:
        if (r2 >= r1) goto L45;
        char r9 = r13.charAt(r2);
        if (r9 < '0') goto L45;
        if (r9 > '9') goto L45;
        int r92 = r9 - '0';
        if (r0 < r8) goto L36;
        int r02 = r0 * 10;
        if (r02 < (r7 + r92)) goto L41;
        r0 = r02 - r92;
        r2 = r2 + 1;
        goto L29
    L41:
        if (r53 == false) goto L43;
        return Integer.MIN_VALUE;
    L43:
        return Integer.MAX_VALUE;
    L36:
        if (r53 == false) goto L38;
        return Integer.MIN_VALUE;
    L38:
        return Integer.MAX_VALUE;
    L45:
        if (r53 == false) goto L48;
        return r0;
    L48:
        return -r0;
    L27:
        r7 = -2147483647;
    L20:
        r2 = r2 + 1;
    L21:
        r53 = false;
        goto L22
    }

    private static Integer parseInt(String r5) {
        int r0 = r5.length();
        int r1 = 0;
        char r2 = r5.charAt(0);
        int r4 = 1;
        if (r2 != '-') goto L5;
        boolean r22 = false;
    L6:
        if (r4 >= r0) goto L8;
        r1 = ((r1 * 10) + 48) - r5.charAt(r4);
        r4 = r4 + 1;
        goto L6
    L8:
        if (r22 == false) goto L11;
        r1 = -r1;
    L11:
        return Integer.valueOf(r1);
    L5:
        r1 = '0' - r2;
        r22 = true;
        goto L6
    }

    public static String parseIntToString(String r1) {
        Matcher r12 = pattern.matcher(r1);
        if (r12.find() == true) goto L5;
        return null;
    L5:
        return r12.group(0);
    }

    public static Long parseLong(String r3) {
        long r0 = 0;
        if (r3 == null) goto L5;
        Matcher r32 = pattern.matcher(r3);     // Catch: Exception -> L11
        if (r32.find() == false) goto L10;
        r0 = Long.parseLong(r32.group(0));     // Catch: Exception -> L11
    L10:
        return Long.valueOf(r0);
    L5:
        return 0L;
    }

    private static native int pbkdf2(byte[] r0, byte[] r1, byte[] r2, int r3);

    public static void raceCallbacks(Runnable r3, Callback<Runnable>... r4) {
        if (r4 != null) goto L4;
    L10:
        if (r3 == null) goto L14;
        r3.run();
        return;
    L14:
        return;
    L4:
        if (r4.length == 0) goto L10;
        int r0 = 0;
        Utilities$$ExternalSyntheticLambda1 r2 = new Utilities$$ExternalSyntheticLambda1(new int[]{0}, r4, r3);
    L8:
        if (r0 >= r4.length) goto L15;
        r4[r0].run(r2);
        r0 = r0 + 1;
        goto L8
    }

    public static native String readlink(String r0);

    public static native String readlinkFd(int r0);

    public static native void setupNativeCrashesListener(String r0);

    public static native void stackBlurBitmap(Bitmap r0, int r1);

    public static Bitmap stackBlurBitmapMax(Bitmap r1) {
        return stackBlurBitmapMax(r1, false);
    }

    public static Bitmap stackBlurBitmapMax(Bitmap r9, boolean r10) {
        int r1 = AndroidUtilities.dp(20.0f);
        int r0 = (int) ((AndroidUtilities.dp(20.0f) * r9.getHeight()) / r9.getWidth());
        Bitmap r2 = Bitmap.createBitmap(r1, r0, Bitmap.Config.ARGB_8888);
        Canvas r3 = new Canvas(r2);
        r3.save();
        r3.scale(r2.getWidth() / r9.getWidth(), r2.getHeight() / r9.getHeight());
        if (r10 == false) goto L5;
        Path r102 = new Path();
        r102.addCircle(r9.getWidth() / 2.0f, r9.getHeight() / 2.0f, (Math.min(r9.getWidth(), r9.getHeight()) / 2.0f) - 1.0f, Path.Direction.CW);
        r3.clipPath(r102);
    L5:
        r3.drawBitmap(r9, 0.0f, 0.0f, null);
        r3.restore();
        stackBlurBitmap(r2, Math.max(10, Math.max(r1, r0) / 150));
        return r2;
    }

    public static Bitmap stackBlurBitmapWithScaleFactor(Bitmap r6, float r7) {
        int r1 = (int) Math.max(AndroidUtilities.dp(20.0f), r6.getWidth() / r7);
        int r72 = (int) Math.max((AndroidUtilities.dp(20.0f) * r6.getHeight()) / r6.getWidth(), r6.getHeight() / r7);
        Bitmap r0 = Bitmap.createBitmap(r1, r72, Bitmap.Config.ARGB_8888);
        Canvas r2 = new Canvas(r0);
        r2.save();
        r2.scale(r0.getWidth() / r6.getWidth(), r0.getHeight() / r6.getHeight());
        r2.drawBitmap(r6, 0.0f, 0.0f, null);
        r2.restore();
        stackBlurBitmap(r0, Math.max(10, Math.max(r1, r72) / 150));
        return r0;
    }

    public static <T> void swapItems(List<T> r2, int r3, int r4) {
        T r0 = r2.get(r3);
        r2.set(r3, r2.get(r4));
        r2.set(r4, r0);
    }

    public static long tryParseLong(String r0, long r1) {
        return Long.parseLong(r0);
    L4:
        return r1;
    }

    public static Uri uriParseSafe(String r0) {
        return Uri.parse(r0);
    L4:
        return null;
    }
}
