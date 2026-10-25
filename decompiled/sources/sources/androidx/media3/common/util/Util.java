package androidx.media3.common.util;

import android.annotation.SuppressLint;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.media.AudioFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.Display;
import android.view.WindowManager;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.Player;
import androidx.media3.common.audio.AudioManagerCompat;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.MediaTrack;
import com.google.common.base.Ascii;
import com.google.common.io.ByteStreams;
import com.google.common.math.DoubleMath;
import com.google.common.math.LongMath;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedBytes;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import j$.util.DesugarTimeZone;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import kotlin.jvm.internal.LongCompanionObject;
import okio.Segment$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.signature.SignatureVisitor;
import org.telegram.messenger.TranslateController;
import org.telegram.tgnet.TLRPC;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes3.dex */
public abstract class Util {
    private static final int[] CRC16_BYTES_MSBF = null;
    private static final int[] CRC32_BYTES_MSBF = null;
    private static final int[] CRC8_BYTES_MSBF = null;

    @Deprecated
    public static final String DEVICE = null;
    public static final String DEVICE_DEBUG_INFO = null;
    public static final byte[] EMPTY_BYTE_ARRAY = null;
    public static final long[] EMPTY_LONG_ARRAY = null;
    private static final Pattern ESCAPED_CHARACTER_PATTERN = null;
    private static final Pattern ISM_PATH_PATTERN = null;

    @Deprecated
    public static final String MANUFACTURER = null;

    @Deprecated
    public static final String MODEL = null;

    @Deprecated
    public static final int SDK_INT = 0;
    private static final Pattern XS_DATE_TIME_PATTERN = null;
    private static final Pattern XS_DURATION_PATTERN = null;
    private static final String[] additionalIsoLanguageReplacements = null;
    private static final String[] isoLegacyTagReplacements = null;
    private static HashMap<String, String> languageTagReplacementMap;

    public static /* synthetic */ void $r8$lambda$A_PXnw1FVDYyxMydqIC2uPE2SHA(SettableFuture r1, Runnable r2, Object r3) {
    L7:
        th = move-exception;
        r1.setException(th);
        return;
    L3:
        if (r1.isCancelled() == false) goto L5;
        return;
    L5:
        r2.run();     // Catch: Throwable -> L7
        r1.set(r3);     // Catch: Throwable -> L7
    }

    public static /* synthetic */ void $r8$lambda$KzvOxWgSiy5ysENJ2aWOycyi_3s(SettableFuture r0, ListenableFuture r1) {
        if (r0.isCancelled() == false) goto L6;
        r1.cancel(false);
        return;
    }

    /* renamed from: $r8$lambda$c_pOe3I0ViFZgMkkm-3VzBGAMjI, reason: not valid java name */
    public static /* synthetic */ void m759$r8$lambda$c_pOe3I0ViFZgMkkm3VzBGAMjI(ListenableFuture r0, SettableFuture r1, AsyncFunction r2) {
        r1.setFuture(r2.apply(Futures.getDone(r0)));     // Catch: Throwable -> L5
        return;
    L5:
        th = move-exception;
        r1.setException(th);
        return;
    L7:
        e = e;
    L10:
        r1.setException(e);
        return;
    L17:
        r1.cancel(false);
        return;
    L9:
        e = e;
    L11:
        e = e;
        Throwable r22 = e.getCause();
        if (r22 == null) goto L16;
        e = r22;
    L16:
        r1.setException(e);
    }

    public static /* synthetic */ Thread $r8$lambda$hEFlRWzxSAkJ9UgK9TIfr0C9Gic(String r1, Runnable r2) {
        return new Thread(r2, r1);
    }

    public static /* synthetic */ Thread $r8$lambda$wGFACl6UrrkGCKSFfJQ56NYMqbg(String r1, Runnable r2) {
        return new Thread(r2, r1);
    }

    static {
        int r0 = Build.VERSION.SDK_INT;
        SDK_INT = r0;
        String r1 = Build.DEVICE;
        DEVICE = r1;
        String r2 = Build.MANUFACTURER;
        MANUFACTURER = r2;
        String r3 = Build.MODEL;
        MODEL = r3;
        DEVICE_DEBUG_INFO = r1 + ", " + r3 + ", " + r2 + ", " + r0;
        EMPTY_BYTE_ARRAY = new byte[0];
        EMPTY_LONG_ARRAY = new long[0];
        XS_DATE_TIME_PATTERN = Pattern.compile("(\\d\\d\\d\\d)\\-(\\d\\d)\\-(\\d\\d)[Tt](\\d\\d):(\\d\\d):(\\d\\d)([\\.,](\\d+))?([Zz]|((\\+|\\-)(\\d?\\d):?(\\d\\d)))?");
        XS_DURATION_PATTERN = Pattern.compile("^(-)?P(([0-9]*)Y)?(([0-9]*)M)?(([0-9]*)D)?(T(([0-9]*)H)?(([0-9]*)M)?(([0-9.]*)S)?)?$");
        ESCAPED_CHARACTER_PATTERN = Pattern.compile("%([A-Fa-f0-9]{2})");
        ISM_PATH_PATTERN = Pattern.compile("(?:.*\\.)?isml?(?:/(manifest(.*))?)?", 2);
        additionalIsoLanguageReplacements = new String[]{"alb", "sq", "arm", "hy", "baq", "eu", "bur", "my", "tib", "bo", "chi", "zh", "cze", "cs", "dut", "nl", "ger", "de", "gre", "el", "fre", "fr", "geo", "ka", "ice", "is", "mac", "mk", "mao", "mi", "may", "ms", "per", "fa", "rum", "ro", "scc", "hbs-srp", "slo", "sk", "wel", "cy", "id", "ms-ind", "iw", "he", "heb", "he", "ji", "yi", "arb", "ar-arb", "in", "ms-ind", "ind", "ms-ind", "nb", "no-nob", "nob", "no-nob", "nn", "no-nno", "nno", "no-nno", "tw", "ak-twi", "twi", "ak-twi", "bs", "hbs-bos", "bos", "hbs-bos", "hr", "hbs-hrv", "hrv", "hbs-hrv", "sr", "hbs-srp", "srp", "hbs-srp", "cmn", "zh-cmn", "hak", "zh-hak", "nan", "zh-nan", "hsn", "zh-hsn"};
        isoLegacyTagReplacements = new String[]{"i-lux", "lb", "i-hak", "zh-hak", "i-navajo", "nv", "no-bok", "no-nob", "no-nyn", "no-nno", "zh-guoyu", "zh-cmn", "zh-hakka", "zh-hak", "zh-min-nan", "zh-nan", "zh-xiang", "zh-hsn"};
        CRC32_BYTES_MSBF = new int[]{0, 79764919, 159529838, 222504665, 319059676, 398814059, 445009330, 507990021, 638119352, 583659535, 797628118, 726387553, 890018660, 835552979, 1015980042, 944750013, 1276238704, 1221641927, 1167319070, 1095957929, 1595256236, 1540665371, 1452775106, 1381403509, 1780037320, 1859660671, 1671105958, 1733955601, 2031960084, 2111593891, 1889500026, 1952343757, -1742489888, -1662866601, -1851683442, -1788833735, -1960329156, -1880695413, -2103051438, -2040207643, -1104454824, -1159051537, -1213636554, -1284997759, -1389417084, -1444007885, -1532160278, -1603531939, -734892656, -789352409, -575645954, -646886583, -952755380, -1007220997, -827056094, -898286187, -231047128, -151282273, -71779514, -8804623, -515967244, -436212925, -390279782, -327299027, 881225847, 809987520, 1023691545, 969234094, 662832811, 591600412, 771767749, 717299826, 311336399, 374308984, 453813921, 533576470, 25881363, 88864420, 134795389, 214552010, 2023205639, 2086057648, 1897238633, 1976864222, 1804852699, 1867694188, 1645340341, 1724971778, 1587496639, 1516133128, 1461550545, 1406951526, 1302016099, 1230646740, 1142491917, 1087903418, -1398421865, -1469785312, -1524105735, -1578704818, -1079922613, -1151291908, -1239184603, -1293773166, -1968362705, -1905510760, -2094067647, -2014441994, -1716953613, -1654112188, -1876203875, -1796572374, -525066777, -462094256, -382327159, -302564546, -206542021, -143559028, -97365931, -17609246, -960696225, -1031934488, -817968335, -872425850, -709327229, -780559564, -600130067, -654598054, 1762451694, 1842216281, 1619975040, 1682949687, 2047383090, 2127137669, 1938468188, 2001449195, 1325665622, 1271206113, 1183200824, 1111960463, 1543535498, 1489069629, 1434599652, 1363369299, 622672798, 568075817, 748617968, 677256519, 907627842, 853037301, 1067152940, 995781531, 51762726, 131386257, 177728840, 240578815, 269590778, 349224269, 429104020, 491947555, -248556018, -168932423, -122852000, -60002089, -500490030, -420856475, -341238852, -278395381, -685261898, -739858943, -559578920, -630940305, -1004286614, -1058877219, -845023740, -916395085, -1119974018, -1174433591, -1262701040, -1333941337, -1371866206, -1426332139, -1481064244, -1552294533, -1690935098, -1611170447, -1833673816, -1770699233, -2009983462, -1930228819, -2119160460, -2056179517, 1569362073, 1498123566, 1409854455, 1355396672, 1317987909, 1246755826, 1192025387, 1137557660, 2072149281, 2135122070, 1912620623, 1992383480, 1753615357, 1816598090, 1627664531, 1707420964, 295390185, 358241886, 404320391, 483945776, 43990325, 106832002, 186451547, 266083308, 932423249, 861060070, 1041341759, 986742920, 613929101, 542559546, 756411363, 701822548, -978770311, -1050133554, -869589737, -924188512, -693284699, -764654318, -550540341, -605129092, -475935807, -413084042, -366743377, -287118056, -257573603, -194731862, -114850189, -35218492, -1984365303, -1921392450, -2143631769, -2063868976, -1698919467, -1635936670, -1824608069, -1744851700, -1347415887, -1418654458, -1506661409, -1561119128, -1129027987, -1200260134, -1254728445, -1309196108};
        CRC16_BYTES_MSBF = new int[]{0, 4129, 8258, 12387, 16516, 20645, 24774, 28903, 33032, 37161, 41290, 45419, 49548, 53677, 57806, 61935};
        CRC8_BYTES_MSBF = new int[]{0, 7, 14, 9, 28, 27, 18, 21, 56, 63, 54, 49, 36, 35, 42, 45, 112, 119, 126, 121, 108, 107, 98, 101, 72, 79, 70, 65, 84, 83, 90, 93, Opcodes.SHL_INT_LIT8, 231, 238, 233, Opcodes.INVOKE_CUSTOM, Opcodes.INVOKE_POLYMORPHIC_RANGE, 242, 245, Opcodes.ADD_INT_LIT8, Opcodes.XOR_INT_LIT8, Opcodes.OR_INT_LIT16, Opcodes.RSUB_INT, 196, 195, 202, Opcodes.MUL_DOUBLE_2ADDR, 144, 151, 158, 153, 140, 139, 130, 133, 168, 175, 166, 161, 180, 179, 186, 189, 199, 192, 201, Opcodes.DIV_DOUBLE_2ADDR, Opcodes.DIV_INT_LIT8, Opcodes.REM_INT_LIT8, Opcodes.AND_INT_LIT16, Opcodes.MUL_INT_LIT16, 255, 248, 241, 246, 227, 228, 237, 234, 183, 176, 185, 190, 171, 172, 165, 162, 143, 136, 129, 134, 147, 148, 157, 154, 39, 32, 41, 46, 59, 60, 53, 50, 31, 24, 17, 22, 3, 4, 13, 10, 87, 80, 89, 94, 75, 76, 69, 66, 111, 104, 97, 102, 115, 116, 125, 122, 137, 142, 135, 128, 149, 146, 155, 156, 177, 182, 191, 184, 173, 170, 163, 164, 249, Opcodes.CONST_METHOD_HANDLE, 247, 240, TLRPC.LAYER, Opcodes.USHR_INT_LIT8, 235, 236, 193, 198, Opcodes.REM_DOUBLE_2ADDR, 200, Opcodes.AND_INT_LIT8, Opcodes.MUL_INT_LIT8, Opcodes.DIV_INT_LIT16, Opcodes.REM_INT_LIT16, 105, 110, 103, 96, 117, 114, 123, 124, 81, 86, 95, 88, 77, 74, 67, 68, 25, 30, 23, 16, 5, 2, 11, 12, 33, 38, 47, 40, 61, 58, 51, 52, 78, 73, 64, 71, 82, 85, 92, 91, 118, 113, 120, 127, 106, 109, 100, 99, 62, 57, 48, 55, 34, 37, 44, 43, 6, 1, 8, 15, 26, 29, 20, 19, 174, 169, 160, 167, 178, 181, 188, 187, 150, 145, 152, 159, 138, 141, 132, 131, Opcodes.OR_INT_LIT8, Opcodes.RSUB_INT_LIT8, Opcodes.ADD_INT_LIT16, Opcodes.XOR_INT_LIT16, 194, 197, Opcodes.SUB_DOUBLE_2ADDR, 203, 230, Opcodes.SHR_INT_LIT8, 232, 239, 250, Opcodes.INVOKE_CUSTOM_RANGE, 244, 243};
    }

    public static long addWithOverflowDefault(long r2, long r4, long r6) {
        long r0 = r2 + r4;
        if (((r2 ^ r0) & (r4 ^ r0)) >= 0) goto L5;
        return r6;
    L5:
        return r0;
    }

    public static int binarySearchCeil(long[] r4, long r5, boolean r7, boolean r8) {
        int r0 = Arrays.binarySearch(r4, r5);
        if (r0 >= 0) goto L5;
        int r52 = ~r0;
    L13:
        if (r8 == true) goto L15;
        return r52;
    L15:
        return Math.min(r4.length - 1, r52);
    L5:
        int r1 = r0 + 1;
        if (r1 >= r4.length) goto L10;
        if (r4[r1] != r5) goto L10;
        r0 = r1;
    L10:
        if (r7 == false) goto L12;
        r52 = r0;
        goto L13
    L12:
        r52 = r1;
        goto L13
    }

    public static int binarySearchFloor(LongArray r6, long r7, boolean r9, boolean r10) {
        int r0 = r6.size() - 1;
        int r2 = 0;
    L3:
        if (r2 > r0) goto L8;
        int r3 = (r2 + r0) >>> 1;
        if (r6.get(r3) < r7) goto L6;
        r0 = r3 - 1;
        goto L3
    L6:
        r2 = r3 + 1;
        goto L3
    L8:
        if (r9 == false) goto L14;
        int r92 = r0 + 1;
        if (r92 >= r6.size()) goto L14;
        if (r6.get(r92) != r7) goto L14;
        return r92;
    L14:
        if (r10 == true) goto L16;
    L18:
        return r0;
    L16:
        if (r0 != (-1)) goto L18;
        return 0;
    }

    public static <T extends Comparable<? super T>> int binarySearchFloor(List<? extends Comparable<? super T>> r3, T r4, boolean r5, boolean r6) {
        int r0 = Collections.binarySearch(r3, r4);
        if (r0 >= 0) goto L5;
        int r32 = -(r0 + 2);
    L13:
        if (r6 == true) goto L15;
        return r32;
    L15:
        return Math.max(0, r32);
    L5:
        int r1 = r0 - 1;
        if (r1 < 0) goto L10;
        if (r3.get(r1).compareTo(r4) != 0) goto L10;
        r0 = r1;
    L10:
        if (r5 == false) goto L12;
        r32 = r0;
        goto L13
    L12:
        r32 = r1;
        goto L13
    }

    public static int binarySearchFloor(int[] r3, int r4, boolean r5, boolean r6) {
        int r0 = Arrays.binarySearch(r3, r4);
        if (r0 >= 0) goto L5;
        int r32 = -(r0 + 2);
    L13:
        if (r6 == true) goto L15;
        return r32;
    L15:
        return Math.max(0, r32);
    L5:
        int r1 = r0 - 1;
        if (r1 < 0) goto L10;
        if (r3[r1] != r4) goto L10;
        r0 = r1;
    L10:
        if (r5 == false) goto L12;
        r32 = r0;
        goto L13
    L12:
        r32 = r1;
        goto L13
    }

    public static int binarySearchFloor(long[] r4, long r5, boolean r7, boolean r8) {
        int r0 = Arrays.binarySearch(r4, r5);
        if (r0 >= 0) goto L5;
        int r42 = -(r0 + 2);
    L13:
        if (r8 == true) goto L15;
        return r42;
    L15:
        return Math.max(0, r42);
    L5:
        int r1 = r0 - 1;
        if (r1 < 0) goto L10;
        if (r4[r1] != r5) goto L10;
        r0 = r1;
    L10:
        if (r7 == false) goto L12;
        r42 = r0;
        goto L13
    L12:
        r42 = r1;
        goto L13
    }

    @EnsuresNonNull({"#1"})
    public static <T> T castNonNull(T r0) {
        return r0;
    }

    @EnsuresNonNull({"#1"})
    public static <T> T[] castNonNullTypeArray(T[] r0) {
        return r0;
    }

    public static int ceilDivide(int r0, int r1) {
        return ((r0 + r1) - 1) / r1;
    }

    public static long ceilDivide(long r2, long r4) {
        return ((r2 + r4) - 1) / r4;
    }

    public static void closeQuietly(Closeable r0) {
        if (r0 == null) goto L8;
        r0.close();     // Catch: IOException -> L5
        return;
    L9:
        return;
    }

    public static float constrainValue(float r0, float r1, float r2) {
        return Math.max(r1, Math.min(r0, r2));
    }

    public static int constrainValue(int r0, int r1, int r2) {
        return Math.max(r1, Math.min(r0, r2));
    }

    public static long constrainValue(long r0, long r2, long r4) {
        return Math.max(r2, Math.min(r0, r4));
    }

    public static <T> boolean contains(SparseArray<T> r0, int r1) {
        if (r0.indexOfKey(r1) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean contains(Object[] r4, Object r5) {
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (Objects.equals(r4[r2], r5) == true) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    public static <T> boolean contentEquals(SparseArray<T> r6, SparseArray<T> r7) {
        if (r6 != null) goto L7;
        if (r7 != null) goto L6;
        return true;
    L6:
        return false;
    L7:
        if (r7 != null) goto L10;
        return false;
    L10:
        if (Build.VERSION.SDK_INT >= 31) goto L12;
        int r2 = r6.size();
        if (r2 == r7.size()) goto L16;
        return false;
    L16:
        int r3 = 0;
    L17:
        if (r3 >= r2) goto L22;
        if (Objects.equals(r6.valueAt(r3), r7.get(r6.keyAt(r3))) == false) goto L20;
        r3 = r3 + 1;
        goto L17
    L20:
        return false;
    L22:
        return true;
    L12:
        return r6.contentEquals(r7);
    }

    public static <T> int contentHashCode(SparseArray<T> r4) {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        int r0 = 17;
        int r2 = 0;
    L8:
        if (r2 >= r4.size()) goto L10;
        r0 = (((r0 * 31) + r4.keyAt(r2)) * 31) + Objects.hashCode(r4.valueAt(r2));
        r2 = r2 + 1;
        goto L8
    L10:
        return r0;
    L5:
        return r4.contentHashCode();
    }

    public static int crc16(byte[] r2, int r3, int r4, int r5) {
    L2:
        if (r3 >= r4) goto L4;
        int r0 = UnsignedBytes.toInt(r2[r3]);
        int r52 = crc16UpdateFourBits(r0 >> 4, r5);
        r5 = crc16UpdateFourBits(r0 & 15, r52);
        r3 = r3 + 1;
        goto L2
    L4:
        return r5;
    }

    private static int crc16UpdateFourBits(int r2, int r3) {
        int r22 = (r2 ^ ((r3 >> 12) & 255)) & 255;
        int r23 = CRC16_BYTES_MSBF[r22];
        return (r23 ^ ((r3 << 4) & 65535)) & 65535;
    }

    public static int crc32(byte[] r3, int r4, int r5, int r6) {
    L2:
        if (r4 >= r5) goto L4;
        int r0 = r6 << 8;
        r6 = CRC32_BYTES_MSBF[((r6 >>> 24) ^ (r3[r4] & 255)) & 255] ^ r0;
        r4 = r4 + 1;
        goto L2
    L4:
        return r6;
    }

    public static int crc8(byte[] r2, int r3, int r4, int r5) {
    L2:
        if (r3 >= r4) goto L4;
        r5 = CRC8_BYTES_MSBF[r5 ^ (r2[r3] & 255)];
        r3 = r3 + 1;
        goto L2
    L4:
        return r5;
    }

    public static Handler createHandler(Looper r1, Handler.Callback r2) {
        return new Handler(r1, r2);
    }

    public static Handler createHandlerForCurrentLooper() {
        return createHandlerForCurrentLooper(null);
    }

    public static Handler createHandlerForCurrentLooper(Handler.Callback r1) {
        return createHandler((Looper) Assertions.checkStateNotNull(Looper.myLooper()), r1);
    }

    public static Handler createHandlerForCurrentOrMainLooper() {
        return createHandlerForCurrentOrMainLooper(null);
    }

    public static Handler createHandlerForCurrentOrMainLooper(Handler.Callback r1) {
        return createHandler(getCurrentOrMainLooper(), r1);
    }

    private static HashMap<String, String> createIsoLanguageReplacementMap() {
        String[] r0 = Locale.getISOLanguages();
        HashMap<String, String> r1 = new HashMap(r0.length + additionalIsoLanguageReplacements.length);
        int r2 = r0.length;
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L9;
        String r5 = r0[r4];
        String r6 = new Locale(r5).getISO3Language();     // Catch: MissingResourceException -> L13
        if (TextUtils.isEmpty(r6) == true) goto L8;
        r1.put(r6, r5);     // Catch: MissingResourceException -> L13
    L8:
        r4 = r4 + 1;
    L9:
        String[] r02 = additionalIsoLanguageReplacements;
        if (r3 >= r02.length) goto L12;
        r1.put(r02[r3], r02[r3 + 1]);
        r3 = r3 + 2;
        goto L9
    L12:
        return r1;
    }

    public static ByteBuffer createReadOnlyByteBuffer(ByteBuffer r1) {
        return r1.asReadOnlyBuffer().order(r1.order());
    }

    public static long durationUsToSampleCount(long r7, int r9) {
        return scaleLargeValue(r7, r9, 1000000, RoundingMode.UP);
    }

    public static String formatInvariant(String r1, Object... r2) {
        return String.format(Locale.US, r1, r2);
    }

    public static String fromUtf8Bytes(byte[] r2) {
        return new String(r2, StandardCharsets.UTF_8);
    }

    public static String fromUtf8Bytes(byte[] r2, int r3, int r4) {
        return new String(r2, r3, r4, StandardCharsets.UTF_8);
    }

    public static int generateAudioSessionIdV21(Context r0) {
        return AudioManagerCompat.getAudioManager(r0).generateAudioSessionId();
    }

    public static int getApiLevelThatAudioFormatIntroducedAudioEncoding(int r1) {
        if (r1 == 30) goto L22;
        switch(r1) {
            case 2: goto L20;
            case 3: goto L20;
            case 4: goto L18;
            case 5: goto L18;
            case 6: goto L18;
            case 7: goto L16;
            case 8: goto L16;
            case 9: goto L14;
            case 10: goto L14;
            case 11: goto L14;
            case 12: goto L14;
            default: goto L5;
        };
    L5:
        switch(r1) {
            case 14: goto L12;
            case 15: goto L24;
            case 16: goto L24;
            case 17: goto L24;
            case 18: goto L24;
            default: goto L6;
        };
    L6:
        switch(r1) {
            case 20: goto L11;
            case 21: goto L9;
            case 22: goto L9;
            default: goto L7;
        };
    L7:
        return Integer.MAX_VALUE;
    L9:
        return 31;
    L11:
        return 30;
    L12:
        return 25;
    L24:
        return 28;
    L14:
        return 28;
    L16:
        return 23;
    L18:
        return 21;
    L20:
        return 3;
    L22:
        return 34;
    }

    public static AudioFormat getAudioFormat(int r1, int r2, int r3) {
        return new AudioFormat.Builder().setSampleRate(r1).setChannelMask(r2).setEncoding(r3).build();
    }

    @SuppressLint({"InlinedApi"})
    public static int getAudioTrackChannelConfig(int r5) {
        if (r5 == 10) goto L32;
        if (r5 != 12) goto L7;
        return 743676;
    L7:
        if (r5 == 24) goto L25;
        switch(r5) {
            case 1: goto L22;
            case 2: goto L21;
            case 3: goto L19;
            case 4: goto L17;
            case 5: goto L15;
            case 6: goto L13;
            case 7: goto L11;
            case 8: goto L10;
            default: goto L9;
        };
    L9:
        return 0;
    L10:
        return 6396;
    L11:
        return 1276;
    L13:
        return Opcodes.INVOKE_CUSTOM;
    L15:
        return Opcodes.REM_INT_LIT8;
    L17:
        return Opcodes.SUB_DOUBLE_2ADDR;
    L19:
        return 28;
    L21:
        return 12;
    L22:
        return 4;
    L25:
        if (Build.VERSION.SDK_INT < 32) goto L28;
        return 67108860;
    L28:
        return 0;
    L32:
        if (Build.VERSION.SDK_INT < 32) goto L35;
        return 737532;
    L35:
        return 6396;
    }

    public static String getAuxiliaryTrackTypeString(int r1) {
        if (r1 != 0) goto L4;
        return "undefined";
    L4:
        if (r1 != 1) goto L6;
        return "original";
    L6:
        if (r1 != 2) goto L8;
        return "depth-linear";
    L8:
        if (r1 != 3) goto L10;
        return "depth-inverse";
    L10:
        if (r1 != 4) goto L13;
        return "depth metadata";
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("Unsupported auxiliary track type");
        return null;
    }

    public static Player.Commands getAvailableCommands(Player r10, Player.Commands r11) {
        boolean r0 = r10.isPlayingAd();
        boolean r1 = r10.isCurrentMediaItemSeekable();
        boolean r2 = r10.hasPreviousMediaItem();
        boolean r3 = r10.hasNextMediaItem();
        boolean r4 = r10.isCurrentMediaItemLive();
        boolean r5 = r10.isCurrentMediaItemDynamic();
        boolean r102 = r10.getCurrentTimeline().isEmpty();
        Player.Commands.Builder r112 = new Player.Commands.Builder().addAll(r11).addIf(4, !r0);
        boolean r6 = false;
        if (r1 == false) goto L6;
        if (r0 == true) goto L6;
        boolean r8 = true;
    L7:
        Player.Commands.Builder r113 = r112.addIf(5, r8);
        if (r2 == false) goto L11;
        if (r0 == true) goto L11;
        boolean r82 = true;
    L12:
        Player.Commands.Builder r114 = r113.addIf(6, r82);
        if (r102 == true) goto L19;
        if (r2 == true) goto L17;
        if (r4 == false) goto L17;
        if (r1 == false) goto L19;
    L17:
        if (r0 == true) goto L19;
        boolean r22 = true;
    L20:
        Player.Commands.Builder r115 = r114.addIf(7, r22);
        if (r3 == false) goto L24;
        if (r0 == true) goto L24;
        boolean r23 = true;
    L25:
        Player.Commands.Builder r116 = r115.addIf(8, r23);
        if (r102 == true) goto L32;
        if (r3 == true) goto L30;
        if (r4 == false) goto L32;
        if (r5 == false) goto L32;
    L30:
        if (r0 == true) goto L32;
        boolean r103 = true;
    L33:
        Player.Commands.Builder r104 = r116.addIf(9, r103).addIf(10, !r0);
        if (r1 == false) goto L37;
        if (r0 == true) goto L37;
        boolean r117 = true;
    L38:
        Player.Commands.Builder r105 = r104.addIf(11, r117);
        if (r1 == false) goto L43;
        if (r0 == true) goto L43;
        r6 = true;
    L43:
        return r105.addIf(12, r6).build();
    L37:
        r117 = false;
    L32:
        r103 = false;
    L24:
        r23 = false;
    L19:
        r22 = false;
    L11:
        r82 = false;
    L6:
        r8 = false;
        goto L7
    }

    public static int getBigEndianInt(ByteBuffer r1, int r2) {
        int r22 = r1.getInt(r2);
        if (r1.order() != ByteOrder.BIG_ENDIAN) goto L6;
        return r22;
    L6:
        return Integer.reverseBytes(r22);
    }

    public static int getByteDepth(int r4) {
        if (r4 != 2) goto L5;
    L25:
        return 2;
    L5:
        if (r4 != 3) goto L7;
        return 1;
    L7:
        if (r4 != 4) goto L9;
    L22:
        return 4;
    L9:
        if (r4 != 21) goto L11;
    L21:
        return 3;
    L11:
        if (r4 == 22) goto L22;
        if (r4 == 268435456) goto L25;
        if (r4 == 1342177280) goto L21;
        if (r4 == 1610612736) goto L22;
        Segment$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    public static int getCodecCountOfType(String r4, int r5) {
        String[] r42 = splitCodecs(r4);
        int r0 = r42.length;
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L8;
        if (r5 != MimeTypes.getTrackTypeOfCodec(r42[r1])) goto L7;
        r2 = r2 + 1;
    L7:
        r1 = r1 + 1;
        goto L3
    L8:
        return r2;
    }

    public static String getCodecsOfType(String r6, int r7) {
        String[] r62 = splitCodecs(r6);
        if (r62.length != 0) goto L5;
        return null;
    L5:
        StringBuilder r0 = new StringBuilder();
        int r2 = r62.length;
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L15;
        String r4 = r62[r3];
        if (r7 != MimeTypes.getTrackTypeOfCodec(r4)) goto L13;
        if (r0.length() <= 0) goto L12;
        r0.append(",");
    L12:
        r0.append(r4);
    L13:
        r3 = r3 + 1;
        goto L6
    L15:
        if (r0.length() > 0) goto L17;
        return null;
    L17:
        return r0.toString();
    }

    public static String getCodecsWithoutType(String r6, int r7) {
        String[] r62 = splitCodecs(r6);
        if (r62.length != 0) goto L5;
        return null;
    L5:
        StringBuilder r0 = new StringBuilder();
        int r2 = r62.length;
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L15;
        String r4 = r62[r3];
        if (r7 == MimeTypes.getTrackTypeOfCodec(r4)) goto L13;
        if (r0.length() <= 0) goto L12;
        r0.append(",");
    L12:
        r0.append(r4);
    L13:
        r3 = r3 + 1;
        goto L6
    L15:
        if (r0.length() > 0) goto L17;
        return null;
    L17:
        return r0.toString();
    }

    public static String getCountryCode(Context r1) {
        if (r1 == null) goto L10;
        TelephonyManager r12 = (TelephonyManager) r1.getSystemService("phone");
        if (r12 == null) goto L10;
        String r13 = r12.getNetworkCountryIso();
        if (TextUtils.isEmpty(r13) == true) goto L10;
        return Ascii.toUpperCase(r13);
    L10:
        return Ascii.toUpperCase(Locale.getDefault().getCountry());
    }

    public static Point getCurrentDisplayModeSize(Context r2) {
        DisplayManager r0 = (DisplayManager) r2.getSystemService("display");
        if (r0 == null) goto L5;
        Display r02 = r0.getDisplay(0);
    L6:
        if (r02 != null) goto L9;
        r02 = ((WindowManager) Assertions.checkNotNull((WindowManager) r2.getSystemService("window"))).getDefaultDisplay();
    L9:
        return getCurrentDisplayModeSize(r2, r02);
    L5:
        r02 = null;
        goto L6
    }

    public static Point getCurrentDisplayModeSize(Context r4, Display r5) {
        if (r5.getDisplayId() == 0) goto L5;
    L28:
        Point r42 = new Point();
        getDisplaySizeV23(r5, r42);
        return r42;
    L5:
        if (isTv(r4) == false) goto L28;
        if (Build.VERSION.SDK_INT >= 28) goto L9;
        String r0 = getSystemProperty("sys.display-size");
    L11:
        if (TextUtils.isEmpty(r0) == true) goto L21;
        String[] r1 = split(r0.trim(), "x");     // Catch: NumberFormatException -> L30
        if (r1.length != 2) goto L19;
        int r2 = Integer.parseInt(r1[0]);     // Catch: NumberFormatException -> L30
        int r12 = Integer.parseInt(r1[1]);     // Catch: NumberFormatException -> L30
        if (r2 <= 0) goto L19;
        if (r12 <= 0) goto L19;
        return new Point(r2, r12);
    L19:
        Log.e("Util", "Invalid display size: " + r0);
    L21:
        if ("Sony".equals(Build.MANUFACTURER) == false) goto L28;
        if (Build.MODEL.startsWith("BRAVIA") == false) goto L28;
        if (r4.getPackageManager().hasSystemFeature("com.sony.dtv.hardware.panel.qfhd") == false) goto L28;
        return new Point(3840, 2160);
    L9:
        r0 = getSystemProperty("vendor.display-size");
        goto L11
    }

    public static Looper getCurrentOrMainLooper() {
        Looper r0 = Looper.myLooper();
        if (r0 == null) goto L6;
        return r0;
    L6:
        return Looper.getMainLooper();
    }

    private static void getDisplaySizeV23(Display r1, Point r2) {
        Display.Mode r12 = r1.getMode();
        r2.x = r12.getPhysicalWidth();
        r2.y = r12.getPhysicalHeight();
    }

    public static int getErrorCodeForMediaDrmErrorCode(int r1) {
        if (r1 != 2) goto L5;
        return 6005;
    L5:
        if (r1 != 4) goto L7;
        return 6005;
    L7:
        if (r1 != 10) goto L9;
        return 6004;
    L9:
        if (r1 != 7) goto L11;
        return 6005;
    L11:
        if (r1 == 8) goto L18;
        switch(r1) {
            case 15: goto L24;
            case 16: goto L28;
            case 17: goto L25;
            case 18: goto L28;
            case 19: goto L25;
            case 20: goto L25;
            case 21: goto L25;
            case 22: goto L25;
            default: goto L13;
        };
    L13:
        switch(r1) {
            case 24: goto L16;
            case 25: goto L16;
            case 26: goto L16;
            case 27: goto L16;
            case 28: goto L16;
            default: goto L14;
        };
    L14:
        return 6006;
    L16:
        return 6002;
    L24:
        return 6003;
    L25:
        return 6004;
    L28:
        return 6005;
    L18:
        return 6003;
    }

    public static int getErrorCodeFromPlatformDiagnosticsInfo(String r5) {
        int r0 = 0;
        if (r5 != null) goto L5;
        return 0;
    L5:
        String[] r52 = split(r5, "_");
        int r1 = r52.length;
        if (r1 >= 2) goto L8;
        return 0;
    L8:
        String r3 = r52[r1 - 1];
        if (r1 >= 3) goto L11;
    L13:
        boolean r53 = false;
    L20:
        r0 = Integer.parseInt((String) Assertions.checkNotNull(r3));     // Catch: NumberFormatException -> L19
        if (r53 == false) goto L18;
        return -r0;
    L18:
        return r0;
    L11:
        if ("neg".equals(r52[r1 - 2]) == false) goto L13;
        r53 = true;
        goto L20
    }

    public static String getFormatSupportString(int r1) {
        if (r1 != 0) goto L4;
        return "NO";
    L4:
        if (r1 != 1) goto L6;
        return "NO_UNSUPPORTED_TYPE";
    L6:
        if (r1 != 2) goto L8;
        return "NO_UNSUPPORTED_DRM";
    L8:
        if (r1 != 3) goto L10;
        return "NO_EXCEEDS_CAPABILITIES";
    L10:
        if (r1 != 4) goto L13;
        return "YES";
    L13:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    public static int getInt24(ByteBuffer r4, int r5) {
        ByteOrder r0 = r4.order();
        ByteOrder r1 = ByteOrder.BIG_ENDIAN;
        if (r0 != r1) goto L5;
        int r02 = r5;
    L6:
        byte r03 = r4.get(r02);
        byte r2 = r4.get(r5 + 1);
        if (r4.order() != r1) goto L9;
        r5 = r5 + 2;
    L9:
        byte r42 = r4.get(r5);
        int r43 = (r42 << 8) & 65280;
        return (r43 | (((r03 << 24) & (-16777216)) | ((r2 << 16) & 16711680))) >> 8;
    L5:
        r02 = r5 + 2;
        goto L6
    }

    public static String getLocaleLanguageTag(Locale r0) {
        return r0.toLanguageTag();
    }

    public static int getMaxPendingFramesCountForMediaCodecDecoders(Context r0) {
        if (isFrameDropAllowedOnSurfaceInput(r0) == false) goto L6;
        return 1;
    L6:
        return 5;
    }

    public static long getMediaDurationForPlayoutDuration(long r2, float r4) {
        if (r4 != 1.0f) goto L6;
        return r2;
    L6:
        return Math.round(r2 * r4);
    }

    public static long getNowUnixTimeMs(long r2) {
        if (r2 != (-9223372036854775807L)) goto L7;
        return System.currentTimeMillis();
    L7:
        return android.os.SystemClock.elapsedRealtime() + r2;
    }

    public static int getPcmEncoding(int r1) {
        return getPcmEncoding(r1, ByteOrder.LITTLE_ENDIAN);
    }

    public static int getPcmEncoding(int r1, ByteOrder r2) {
        if (r1 != 8) goto L5;
        return 3;
    L5:
        if (r1 == 16) goto L25;
        if (r1 == 24) goto L19;
        if (r1 == 32) goto L13;
        return 0;
    L13:
        if (r2.equals(ByteOrder.LITTLE_ENDIAN) == false) goto L16;
        return 22;
    L16:
        return 1610612736;
    L19:
        if (r2.equals(ByteOrder.LITTLE_ENDIAN) == false) goto L22;
        return 21;
    L22:
        return 1342177280;
    L25:
        if (r2.equals(ByteOrder.LITTLE_ENDIAN) == false) goto L28;
        return 2;
    L28:
        return 268435456;
    }

    public static Format getPcmFormat(int r2, int r3, int r4) {
        return new Format.Builder().setSampleMimeType("audio/raw").setChannelCount(r3).setSampleRate(r4).setPcmEncoding(r2).build();
    }

    public static int getPcmFrameSize(int r0, int r1) {
        return getByteDepth(r0) * r1;
    }

    public static long getPlayoutDurationForMediaDuration(long r2, float r4) {
        if (r4 != 1.0f) goto L6;
        return r2;
    L6:
        return Math.round(r2 / r4);
    }

    public static List<String> getRoleFlagStrings(int r2) {
        ArrayList r0 = new ArrayList();
        if ((r2 & 1) == 0) goto L6;
        r0.add(MediaTrack.ROLE_MAIN);
    L6:
        if ((r2 & 2) == 0) goto L9;
        r0.add("alt");
    L9:
        if ((r2 & 4) == 0) goto L12;
        r0.add(MediaTrack.ROLE_SUPPLEMENTARY);
    L12:
        if ((r2 & 8) == 0) goto L15;
        r0.add(MediaTrack.ROLE_COMMENTARY);
    L15:
        if ((r2 & 16) == 0) goto L18;
        r0.add(MediaTrack.ROLE_DUB);
    L18:
        if ((r2 & 32) == 0) goto L21;
        r0.add(MediaTrack.ROLE_EMERGENCY);
    L21:
        if ((r2 & 64) == 0) goto L24;
        r0.add(MediaTrack.ROLE_CAPTION);
    L24:
        if ((r2 & 128) == 0) goto L27;
        r0.add(MediaTrack.ROLE_SUBTITLE);
    L27:
        if ((r2 & 256) == 0) goto L30;
        r0.add(MediaTrack.ROLE_SIGN);
    L30:
        if ((r2 & 512) == 0) goto L33;
        r0.add("describes-video");
    L33:
        if ((r2 & 1024) == 0) goto L36;
        r0.add("describes-music");
    L36:
        if ((r2 & 2048) == 0) goto L39;
        r0.add("enhanced-intelligibility");
    L39:
        if ((r2 & 4096) == 0) goto L42;
        r0.add("transcribes-dialog");
    L42:
        if ((r2 & 8192) == 0) goto L45;
        r0.add("easy-read");
    L45:
        if ((r2 & 16384) == 0) goto L48;
        r0.add("trick-play");
    L48:
        if ((r2 & 32768) == 0) goto L50;
        r0.add("auxiliary");
    L50:
        return r0;
    }

    public static List<String> getSelectionFlagStrings(int r2) {
        ArrayList r0 = new ArrayList();
        if ((r2 & 4) == 0) goto L6;
        r0.add("auto");
    L6:
        if ((r2 & 1) == 0) goto L9;
        r0.add(PluginsConstants.Settings.DEFAULT);
    L9:
        if ((r2 & 2) == 0) goto L11;
        r0.add("forced");
    L11:
        return r0;
    }

    public static String[] getSystemLanguageCodes() {
        String[] r0 = getSystemLocales();
        int r1 = 0;
    L4:
        if (r1 >= r0.length) goto L6;
        r0[r1] = normalizeLanguageCode(r0[r1]);
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    private static String[] getSystemLocales() {
        return getSystemLocalesV24(Resources.getSystem().getConfiguration());
    }

    private static String[] getSystemLocalesV24(Configuration r1) {
        return split(r1.getLocales().toLanguageTags(), ",");
    }

    private static String getSystemProperty(String r3) {
        Class<?> r0 = Class.forName("android.os.SystemProperties");     // Catch: Exception -> L4
        return (String) r0.getMethod("get", new Class[]{String.class}).invoke(r0, new Object[]{r3});
    L4:
        e = move-exception;
        Log.e("Util", "Failed to read system property " + r3, e);
        return null;
    }

    public static String getTrackTypeString(int r2) {
        switch(r2) {
            case -2: goto L25;
            case -1: goto L23;
            case 0: goto L21;
            case 1: goto L19;
            case 2: goto L17;
            case 3: goto L15;
            case 4: goto L13;
            case 5: goto L11;
            case 6: goto L9;
            default: goto L4;
        };
    L9:
        return "camera motion";
    L11:
        return "metadata";
    L13:
        return "image";
    L15:
        return "text";
    L17:
        return MediaStreamTrack.VIDEO_TRACK_KIND;
    L19:
        return MediaStreamTrack.AUDIO_TRACK_KIND;
    L21:
        return PluginsConstants.Settings.DEFAULT;
    L23:
        return "unknown";
    L25:
        return "none";
    L4:
        if (r2 >= 10000) goto L6;
        return "?";
    L6:
        return "custom (" + r2 + ")";
    }

    public static byte[] getUtf8Bytes(String r1) {
        return r1.getBytes(StandardCharsets.UTF_8);
    }

    public static boolean handlePauseButtonAction(Player r2) {
        if (r2 != null) goto L4;
        return false;
    L4:
        if (r2.isCommandAvailable(1) == false) goto L9;
        r2.pause();
        return true;
    L9:
        return false;
    }

    public static boolean handlePlayButtonAction(Player r4) {
        boolean r0 = false;
        if (r4 != null) goto L5;
        return false;
    L5:
        int r1 = r4.getPlaybackState();
        if (r1 != 1) goto L12;
        if (r4.isCommandAvailable(2) == false) goto L12;
        r4.prepare();
    L10:
        r0 = true;
    L17:
        if (r4.isCommandAvailable(1) == false) goto L20;
        r4.play();
        return true;
    L20:
        return r0;
    L12:
        if (r1 != 4) goto L17;
        if (r4.isCommandAvailable(4) == false) goto L17;
        r4.seekToDefaultPosition();
        goto L10
    }

    public static boolean handlePlayPauseButtonAction(Player r0, boolean r1) {
        if (shouldShowPlayButton(r0, r1) == false) goto L7;
        return handlePlayButtonAction(r0);
    L7:
        return handlePauseButtonAction(r0);
    }

    public static int inferContentType(Uri r4) {
        String r0 = r4.getScheme();
        if (r0 != null) goto L5;
    L10:
        String r02 = r4.getLastPathSegment();
        if (r02 != null) goto L13;
        return 4;
    L13:
        int r2 = r02.lastIndexOf(46);
        if (r2 < 0) goto L18;
        int r03 = inferContentTypeForExtension(r02.substring(r2 + 1));
        if (r03 == 4) goto L18;
        return r03;
    L18:
        Matcher r42 = ISM_PATH_PATTERN.matcher((CharSequence) Assertions.checkNotNull(r4.getPath()));
        if (r42.matches() == false) goto L30;
        String r43 = r42.group(2);
        if (r43 != null) goto L23;
    L29:
        return 1;
    L23:
        if (r43.contains("format=mpd-time-csf") == false) goto L27;
        return 0;
    L27:
        if (r43.contains("format=m3u8-aapl") == false) goto L29;
        return 2;
    L30:
        return 4;
    L5:
        if (Ascii.equalsIgnoreCase("rtsp", r0) == false) goto L7;
        return 3;
    L7:
        if (Ascii.equalsIgnoreCase("rtspt", r0) == false) goto L10;
        return 3;
    }

    public static int inferContentTypeForExtension(String r5) {
        String r52 = Ascii.toLowerCase(r5);
        r52.getClass();
        char r4 = 65535;
        switch(r52.hashCode()) {
            case 104579: goto L18;
            case 108321: goto L14;
            case 3242057: goto L10;
            case 3299913: goto L6;
            default: goto L21;
        };
    L21:
        switch(r4) {
            case 0: goto L26;
            case 1: goto L25;
            case 2: goto L26;
            case 3: goto L24;
            default: goto L22;
        };
    L22:
        return 4;
    L24:
        return 2;
    L25:
        return 0;
    L26:
        return 1;
    L6:
        if (r52.equals("m3u8") == false) goto L21;
        r4 = 3;
        goto L21
    L10:
        if (r52.equals("isml") == false) goto L21;
        r4 = 2;
        goto L21
    L14:
        if (r52.equals("mpd") == false) goto L21;
        r4 = 1;
        goto L21
    L18:
        if (r52.equals("ism") == false) goto L21;
        r4 = 0;
        goto L21
    }

    public static int inferContentTypeForUriAndMimeType(Uri r5, String r6) {
        if (r6 == null) goto L4;
        char r4 = 65535;
        switch(r6.hashCode()) {
            case -979127466: goto L21;
            case -156749520: goto L17;
            case 64194685: goto L13;
            case 1154777587: goto L9;
            default: goto L24;
        };
    L24:
        switch(r4) {
            case 0: goto L30;
            case 1: goto L29;
            case 2: goto L28;
            case 3: goto L27;
            default: goto L25;
        };
    L25:
        return 4;
    L27:
        return 3;
    L28:
        return 0;
    L29:
        return 1;
    L30:
        return 2;
    L9:
        if (r6.equals("application/x-rtsp") == false) goto L24;
        r4 = 3;
        goto L24
    L13:
        if (r6.equals("application/dash+xml") == false) goto L24;
        r4 = 2;
        goto L24
    L17:
        if (r6.equals("application/vnd.ms-sstr+xml") == false) goto L24;
        r4 = 1;
        goto L24
    L21:
        if (r6.equals("application/x-mpegURL") == false) goto L24;
        r4 = 0;
        goto L24
    L4:
        return inferContentType(r5);
    }

    public static boolean inflate(ParsableByteArray r3, ParsableByteArray r4, Inflater r5) {
        if (r3.bytesLeft() != 0) goto L6;
        return false;
    L6:
        if (r4.capacity() >= r3.bytesLeft()) goto L8;
        r4.ensureCapacity(r3.bytesLeft() * 2);
    L8:
        if (r5 != null) goto L10;
        r5 = new Inflater();
    L10:
        r5.setInput(r3.getData(), r3.getPosition(), r3.bytesLeft());
        int r32 = 0;
    L33:
        r32 = r32 + r5.inflate(r4.getData(), r32, r4.capacity() - r32);     // Catch: Throwable -> L16 DataFormatException -> L31
        if (r5.finished() == true) goto L13;
        if (r5.needsDictionary() == true) goto L27;
        if (r5.needsInput() == true) goto L27;
        if (r32 != r4.capacity()) goto L33;
        r4.ensureCapacity(r4.capacity() * 2);     // Catch: Throwable -> L16 DataFormatException -> L31
    L27:
        r5.reset();
        return false;
    L13:
        r4.setLimit(r32);     // Catch: Throwable -> L16 DataFormatException -> L31
        r5.reset();
        return true;
    L31:
        r5.reset();
        return false;
    L16:
        th = move-exception;
        r5.reset();
        throw th;
    }

    public static String intToStringMaxRadix(int r1) {
        return Integer.toString(r1, 36);
    }

    public static boolean isAutomotive(Context r1) {
        return r1.getPackageManager().hasSystemFeature("android.hardware.type.automotive");
    }

    public static boolean isBitmapFactorySupportedMimeType(String r4) {
        r4.getClass();
        char r3 = 65535;
        switch(r4.hashCode()) {
            case -1487656890: goto L30;
            case -1487464693: goto L26;
            case -1487464690: goto L22;
            case -1487394660: goto L18;
            case -1487018032: goto L14;
            case -879272239: goto L10;
            case -879258763: goto L6;
            default: goto L33;
        };
    L33:
        switch(r3) {
            case 0: goto L41;
            case 1: goto L37;
            case 2: goto L37;
            case 3: goto L35;
            case 4: goto L35;
            case 5: goto L35;
            case 6: goto L35;
            default: goto L34;
        };
    L34:
        return false;
    L35:
        return true;
    L37:
        if (Build.VERSION.SDK_INT < 26) goto L39;
        return true;
    L39:
        return false;
    L41:
        if (Build.VERSION.SDK_INT < 34) goto L43;
        return true;
    L43:
        return false;
    L6:
        if (r4.equals("image/png") == false) goto L33;
        r3 = 6;
        goto L33
    L10:
        if (r4.equals("image/bmp") == false) goto L33;
        r3 = 5;
        goto L33
    L14:
        if (r4.equals("image/webp") == false) goto L33;
        r3 = 4;
        goto L33
    L18:
        if (r4.equals("image/jpeg") == false) goto L33;
        r3 = 3;
        goto L33
    L22:
        if (r4.equals("image/heif") == false) goto L33;
        r3 = 2;
        goto L33
    L26:
        if (r4.equals("image/heic") == false) goto L33;
        r3 = 1;
        goto L33
    L30:
        if (r4.equals("image/avif") == false) goto L33;
        r3 = 0;
        goto L33
    }

    public static boolean isEncodingHighResolutionPcm(int r1) {
        if (r1 != 21) goto L5;
        return true;
    L5:
        if (r1 != 1342177280) goto L7;
        return true;
    L7:
        if (r1 != 22) goto L9;
        return true;
    L9:
        if (r1 != 1610612736) goto L11;
        return true;
    L11:
        if (r1 == 4) goto L20;
        return false;
    L20:
        return true;
    }

    public static boolean isEncodingLinearPcm(int r1) {
        if (r1 != 3) goto L5;
        return true;
    L5:
        if (r1 != 2) goto L7;
        return true;
    L7:
        if (r1 != 268435456) goto L9;
        return true;
    L9:
        if (r1 != 21) goto L11;
        return true;
    L11:
        if (r1 != 1342177280) goto L13;
        return true;
    L13:
        if (r1 != 22) goto L15;
        return true;
    L15:
        if (r1 != 1610612736) goto L17;
        return true;
    L17:
        if (r1 == 4) goto L29;
        return false;
    L29:
        return true;
    }

    public static boolean isFrameDropAllowedOnSurfaceInput(Context r2) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 >= 29) goto L5;
        return true;
    L5:
        if (r2.getApplicationInfo().targetSdkVersion >= 29) goto L7;
        return true;
    L7:
        if (r0 != 30) goto L13;
        String r22 = Build.MODEL;
        if (Ascii.equalsIgnoreCase(r22, "moto g(20)") == false) goto L11;
        return true;
    L11:
        if (Ascii.equalsIgnoreCase(r22, "rmx3231") == false) goto L13;
        return true;
    L13:
        if (r0 == 34) goto L15;
        return false;
    L15:
        if (Ascii.equalsIgnoreCase(Build.MODEL, "sm-x200") == true) goto L25;
        return false;
    L25:
        return true;
    }

    public static boolean isLinebreak(int r1) {
        if (r1 != 10) goto L5;
        return true;
    L5:
        if (r1 == 13) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isLocalFileUri(Uri r1) {
        String r12 = r1.getScheme();
        if (TextUtils.isEmpty(r12) == false) goto L5;
        return true;
    L5:
        if (Objects.equals(r12, "file") == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isTv(Context r1) {
        UiModeManager r12 = (UiModeManager) r1.getApplicationContext().getSystemService("uimode");
        if (r12 != null) goto L5;
        return false;
    L5:
        if (r12.getCurrentModeType() != 4) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isWear(Context r1) {
        return r1.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }

    public static int linearSearch(int[] r2, int r3) {
        int r0 = 0;
    L4:
        if (r0 >= r2.length) goto L9;
        if (r2[r0] == r3) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r0;
    L9:
        return -1;
    }

    public static boolean maybeInflate(ParsableByteArray r2, ParsableByteArray r3, Inflater r4) {
        if (r2.bytesLeft() > 0) goto L5;
        return false;
    L5:
        if (r2.peekUnsignedByte() == 120) goto L7;
        return false;
    L7:
        if (inflate(r2, r3, r4) == false) goto L13;
        return true;
    L13:
        return false;
    }

    private static String maybeReplaceLegacyLanguageTags(String r4) {
        int r0 = 0;
    L3:
        String[] r1 = isoLegacyTagReplacements;
        if (r0 >= r1.length) goto L10;
        if (r4.startsWith(r1[r0]) == true) goto L8;
        r0 = r0 + 2;
        goto L3
    L8:
        return r1[r0 + 1] + r4.substring(r1[r0].length());
    L10:
        return r4;
    }

    public static <T> void moveItems(List<T> r2, int r3, int r4, int r5) {
        ArrayDeque r0 = new ArrayDeque();
        int r42 = (r4 - r3) - 1;
    L3:
        if (r42 < 0) goto L5;
        r0.addFirst(r2.remove(r3 + r42));
        r42 = r42 - 1;
        goto L3
    L5:
        r2.addAll(Math.min(r5, r2.size()), r0);
    }

    public static long msToUs(long r2) {
        if (r2 != (-9223372036854775807L)) goto L5;
        return r2;
    L5:
        if (r2 != Long.MIN_VALUE) goto L8;
        return r2;
    L8:
        return r2 * 1000;
    }

    public static ExecutorService newSingleThreadExecutor(String r1) {
        return Executors.newSingleThreadExecutor(new Util$$ExternalSyntheticLambda0(r1));
    }

    public static ScheduledExecutorService newSingleThreadScheduledExecutor(String r1) {
        return Executors.newSingleThreadScheduledExecutor(new Util$$ExternalSyntheticLambda1(r1));
    }

    public static String normalizeLanguageCode(String r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        String r0 = r2.replace('_', SignatureVisitor.SUPER);
        if (r0.isEmpty() == false) goto L8;
    L11:
        String r22 = Ascii.toLowerCase(r2);
        String r02 = splitAtFirst(r22, "-")[0];
        if (languageTagReplacementMap != null) goto L14;
        languageTagReplacementMap = createIsoLanguageReplacementMap();
    L14:
        String r1 = languageTagReplacementMap.get(r02);
        if (r1 == null) goto L18;
        r22 = r1.concat(r22.substring(r02.length()));
        r02 = r1;
    L18:
        if ("no".equals(r02) == true) goto L26;
        if ("i".equals(r02) == true) goto L26;
        if ("zh".equals(r02) == true) goto L26;
        return r22;
    L26:
        return maybeReplaceLegacyLanguageTags(r22);
    L8:
        if (r0.equals(TranslateController.UNKNOWN_LANGUAGE) == true) goto L11;
        r2 = r0;
        goto L11
    }

    public static <T> T[] nullSafeArrayAppend(T[] r1, T r2) {
        Object[] r0 = Arrays.copyOf(r1, r1.length + 1);
        r0[r1.length] = r2;
        return (T[]) castNonNullTypeArray(r0);
    }

    public static <T> T[] nullSafeArrayConcatenation(T[] r3, T[] r4) {
        T[] r0 = (T[]) Arrays.copyOf(r3, r3.length + r4.length);
        System.arraycopy(r4, 0, r0, r3.length, r4.length);
        return r0;
    }

    public static <T> T[] nullSafeArrayCopy(T[] r1, int r2) {
        if (r2 > r1.length) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        return (T[]) Arrays.copyOf(r1, r2);
    L5:
        r0 = false;
        goto L6
    }

    public static <T> T[] nullSafeArrayCopyOfRange(T[] r3, int r4, int r5) {
        boolean r0 = false;
        if (r4 < 0) goto L5;
        boolean r2 = true;
    L6:
        Assertions.checkArgument(r2);
        if (r5 > r3.length) goto L9;
        r0 = true;
    L9:
        Assertions.checkArgument(r0);
        return (T[]) Arrays.copyOfRange(r3, r4, r5);
    L5:
        r2 = false;
        goto L6
    }

    public static long parseXsDateTime(String r10) {
        Matcher r0 = XS_DATE_TIME_PATTERN.matcher(r10);
        if (r0.matches() == false) goto L21;
        int r2 = 0;
        if (r0.group(9) != null) goto L8;
    L13:
        GregorianCalendar r3 = new GregorianCalendar(DesugarTimeZone.getTimeZone("GMT"));
        r3.clear();
        r3.set(Integer.parseInt(r0.group(1)), Integer.parseInt(r0.group(2)) - 1, Integer.parseInt(r0.group(3)), Integer.parseInt(r0.group(4)), Integer.parseInt(r0.group(5)), Integer.parseInt(r0.group(6)));
        if (TextUtils.isEmpty(r0.group(8)) == true) goto L16;
        r3.set(14, new BigDecimal("0." + r0.group(8)).movePointRight(3).intValue());
    L16:
        long r02 = r3.getTimeInMillis();
        if (r2 != 0) goto L19;
        return r02;
    L19:
        return r02 - (r2 * 60000);
    L8:
        if (r0.group(9).equalsIgnoreCase("Z") == true) goto L13;
        r2 = (Integer.parseInt(r0.group(12)) * 60) + Integer.parseInt(r0.group(13));
        if ("-".equals(r0.group(11)) == false) goto L13;
        r2 = r2 * (-1);
        goto L13
    L21:
        throw ParserException.createForMalformedContainer("Invalid date/time format: " + r10, null);
    }

    public static long parseXsDuration(String r14) {
        Matcher r0 = XS_DURATION_PATTERN.matcher(r14);
        if (r0.matches() == false) goto L32;
        boolean r142 = TextUtils.isEmpty(r0.group(1));
        String r1 = r0.group(3);
        double r6 = 0.0d;
        if (r1 == null) goto L7;
        double r8 = Double.parseDouble(r1) * 3.1556908E7d;
    L8:
        String r12 = r0.group(5);
        if (r12 == null) goto L11;
        double r10 = Double.parseDouble(r12) * 2629739.0d;
    L12:
        double r82 = r8 + r10;
        String r13 = r0.group(7);
        if (r13 == null) goto L15;
        double r102 = Double.parseDouble(r13) * 86400.0d;
    L16:
        double r83 = r82 + r102;
        String r15 = r0.group(10);
        if (r15 == null) goto L19;
        double r103 = Double.parseDouble(r15) * 3600.0d;
    L20:
        double r84 = r83 + r103;
        String r16 = r0.group(12);
        if (r16 == null) goto L23;
        double r4 = Double.parseDouble(r16) * 60.0d;
    L24:
        double r85 = r84 + r4;
        String r02 = r0.group(14);
        if (r02 == null) goto L27;
        r6 = Double.parseDouble(r02);
    L27:
        long r03 = (long) ((r85 + r6) * 1000.0d);
        if (r142 == false) goto L30;
        return r03;
    L30:
        return -r03;
    L23:
        r4 = 0.0d;
        goto L24
    L19:
        r103 = 0.0d;
        goto L20
    L15:
        r102 = 0.0d;
        goto L16
    L11:
        r10 = 0.0d;
        goto L12
    L7:
        r8 = 0.0d;
        goto L8
    L32:
        return (long) ((Double.parseDouble(r14) * 3600.0d) * 1000.0d);
    }

    public static int percentInt(long r6, long r8) {
        long r2 = LongMath.saturatedMultiply(r6, 100);
        if (r2 != LongCompanionObject.MAX_VALUE) goto L5;
    L7:
        long r22 = r6 / (r8 / 100);
    L9:
        return Ints.checkedCast(r22);
    L5:
        if (r2 == Long.MIN_VALUE) goto L7;
        r22 = r2 / r8;
        goto L9
    }

    public static boolean postOrRun(Handler r2, Runnable r3) {
        Looper r0 = r2.getLooper();
        if (r0.getThread().isAlive() == true) goto L7;
        return false;
    L7:
        if (r0 != Looper.myLooper()) goto L11;
        r3.run();
        return true;
    L11:
        return r2.post(r3);
    }

    public static <T> ListenableFuture<T> postOrRunWithCompletion(Handler r2, Runnable r3, T r4) {
        SettableFuture r0 = SettableFuture.create();
        postOrRun(r2, new Util$$ExternalSyntheticLambda4(r0, r3, r4));
        return r0;
    }

    public static void putInt24(ByteBuffer r5, int r6) {
        boolean r1 = true;
        if (((-16777216) & r6) != 0) goto L5;
    L8:
        boolean r0 = true;
    L9:
        Assertions.checkArgument(r0, "Value out of range of 24-bit integer: " + Integer.toHexString(r6));
        if (r5.remaining() >= 3) goto L13;
        r1 = false;
    L13:
        Assertions.checkArgument(r1);
        ByteOrder r02 = r5.order();
        ByteOrder r12 = ByteOrder.BIG_ENDIAN;
        if (r02 != r12) goto L17;
        int r03 = (r6 & 16711680) >> 16;
    L16:
        byte r04 = (byte) r03;
        byte r3 = (byte) ((65280 & r6) >> 8);
        if (r5.order() != r12) goto L22;
        int r62 = r6 & 255;
    L23:
        r5.put(r04).put(r3).put((byte) r62);
        return;
    L22:
        r62 = (r6 & 16711680) >> 16;
        goto L23
    L17:
        r03 = r6 & 255;
        goto L16
    L5:
        if ((r6 & (-8388608)) == (-8388608)) goto L8;
        r0 = false;
        goto L9
    }

    public static Intent registerReceiverNotExported(Context r2, BroadcastReceiver r3, IntentFilter r4) {
        if (Build.VERSION.SDK_INT >= 33) goto L7;
        return r2.registerReceiver(r3, r4);
    L7:
        return r2.registerReceiver(r3, r4, 4);
    }

    public static <T> void removeRange(List<T> r1, int r2, int r3) {
        if (r2 >= 0) goto L4;
    L9:
        Segment$$ExternalSyntheticBUOutline0.m();
        return;
    L4:
        if (r3 > r1.size()) goto L9;
        if (r2 > r3) goto L9;
        if (r2 == r3) goto L11;
        r1.subList(r2, r3).clear();
        return;
    }

    public static long sampleCountToDurationUs(long r7, int r9) {
        return scaleLargeValue(r7, 1000000, r9, RoundingMode.DOWN);
    }

    public static long scaleLargeTimestamp(long r7, long r9, long r11) {
        return scaleLargeValue(r7, r9, r11, RoundingMode.DOWN);
    }

    public static void scaleLargeTimestampsInPlace(long[] r6, long r7, long r9) {
        scaleLargeValuesInPlace(r6, r7, r9, RoundingMode.DOWN);
    }

    public static long scaleLargeValue(long r7, long r9, long r11, RoundingMode r13) {
        if (r7 != 0) goto L5;
    L31:
        return 0;
    L5:
        if (r9 == 0) goto L31;
        if (r11 >= r9) goto L10;
    L13:
        if (r11 >= r9) goto L19;
        if ((r9 % r11) != 0) goto L19;
        return LongMath.saturatedMultiply(r7, LongMath.divide(r9, r11, RoundingMode.UNNECESSARY));
    L19:
        if (r11 >= r7) goto L21;
    L24:
        if (r11 >= r7) goto L30;
        if ((r7 % r11) != 0) goto L30;
        return LongMath.saturatedMultiply(r9, LongMath.divide(r7, r11, RoundingMode.UNNECESSARY));
    L30:
        return scaleLargeValueFallback(r7, r9, r11, r13);
    L21:
        if ((r11 % r7) != 0) goto L24;
        return LongMath.divide(r9, LongMath.divide(r11, r7, RoundingMode.UNNECESSARY), r13);
    L10:
        if ((r11 % r9) != 0) goto L13;
        return LongMath.divide(r7, LongMath.divide(r11, r9, RoundingMode.UNNECESSARY), r13);
    }

    private static long scaleLargeValueFallback(long r9, long r11, long r13, RoundingMode r15) {
        long r0 = LongMath.saturatedMultiply(r9, r11);
        if (r0 != LongCompanionObject.MAX_VALUE) goto L5;
    L8:
        long r02 = LongMath.gcd(Math.abs(r11), Math.abs(r13));
        RoundingMode r4 = RoundingMode.UNNECESSARY;
        long r112 = LongMath.divide(r11, r02, r4);
        long r132 = LongMath.divide(r13, r02, r4);
        long r03 = LongMath.gcd(Math.abs(r9), Math.abs(r132));
        long r92 = LongMath.divide(r9, r03, r4);
        long r133 = LongMath.divide(r132, r03, r4);
        long r04 = LongMath.saturatedMultiply(r92, r112);
        if (r04 != LongCompanionObject.MAX_VALUE) goto L11;
    L14:
        double r93 = r92 * (r112 / r133);
        if (r93 <= 9.223372036854776E18d) goto L18;
        return LongCompanionObject.MAX_VALUE;
    L18:
        if (r93 >= (-9.223372036854776E18d)) goto L21;
        return Long.MIN_VALUE;
    L21:
        return DoubleMath.roundToLong(r93, r15);
    L11:
        if (r04 == Long.MIN_VALUE) goto L14;
        return LongMath.divide(r04, r133, r15);
    L5:
        if (r0 == Long.MIN_VALUE) goto L8;
        return LongMath.divide(r0, r13, r15);
    }

    public static void scaleLargeValuesInPlace(long[] r10, long r11, long r13, RoundingMode r15) {
        if (r11 != 0) goto L6;
        Arrays.fill(r10, 0);
        return;
    L6:
        int r1 = 0;
        if (r13 >= r11) goto L9;
    L14:
        if (r13 < r11) goto L16;
    L21:
        int r9 = 0;
    L23:
        if (r9 >= r10.length) goto L48;
        long r0 = r10[r9];
        if (r0 == 0) goto L38;
        if (r13 < r0) goto L33;
        if ((r13 % r0) != 0) goto L33;
        r10[r9] = LongMath.divide(r11, LongMath.divide(r13, r0, RoundingMode.UNNECESSARY), r15);
    L33:
        if (r13 < r0) goto L35;
    L37:
        r10[r9] = scaleLargeValueFallback(r0, r11, r13, r15);
        goto L38
    L35:
        if ((r0 % r13) != 0) goto L37;
        r10[r9] = LongMath.saturatedMultiply(r11, LongMath.divide(r0, r13, RoundingMode.UNNECESSARY));
    L38:
        r9 = r9 + 1;
        goto L23
    L48:
        return;
    L16:
        if ((r11 % r13) != 0) goto L21;
        long r112 = LongMath.divide(r11, r13, RoundingMode.UNNECESSARY);
    L19:
        if (r1 >= r10.length) goto L47;
        r10[r1] = LongMath.saturatedMultiply(r10[r1], r112);
        r1 = r1 + 1;
        goto L19
    L47:
        return;
    L9:
        if ((r13 % r11) != 0) goto L14;
        long r113 = LongMath.divide(r13, r11, RoundingMode.UNNECESSARY);
    L12:
        if (r1 >= r10.length) goto L39;
        r10[r1] = LongMath.divide(r10[r1], r113, r15);
        r1 = r1 + 1;
        goto L12
    }

    @EnsuresNonNullIf(expression = {"#1"}, result = false)
    public static boolean shouldShowPlayButton(Player r3, boolean r4) {
        if (r3 != null) goto L5;
    L18:
        return true;
    L5:
        if (r3.getPlayWhenReady() == false) goto L18;
        if (r3.getPlaybackState() == 1) goto L18;
        if (r3.getPlaybackState() == 4) goto L18;
        if (r4 == true) goto L12;
        return false;
    L12:
        if (r3.getPlaybackSuppressionReason() != 0) goto L14;
        return false;
    L14:
        if (r3.getPlaybackSuppressionReason() != 4) goto L18;
        return false;
    }

    public static String[] split(String r1, String r2) {
        return r1.split(r2, -1);
    }

    public static String[] splitAtFirst(String r1, String r2) {
        return r1.split(r2, 2);
    }

    public static String[] splitCodecs(String r1) {
        if (TextUtils.isEmpty(r1) == false) goto L7;
        return new String[0];
    L7:
        return split(r1.trim(), "(\\s*,\\s*)");
    }

    public static long subtractWithOverflowDefault(long r2, long r4, long r6) {
        long r0 = r2 - r4;
        long r42 = r4 ^ r2;
        if (((r2 ^ r0) & r42) >= 0) goto L5;
        return r6;
    L5:
        return r0;
    }

    @Deprecated
    public static byte[] toByteArray(InputStream r0) {
        return ByteStreams.toByteArray(r0);
    }

    public static String toHexString(byte[] r4) {
        StringBuilder r0 = new StringBuilder(r4.length * 2);
        int r1 = 0;
    L4:
        if (r1 >= r4.length) goto L7;
        r0.append(Character.forDigit((r4[r1] >> 4) & 15, 16));
        r0.append(Character.forDigit(r4[r1] & 15, 16));
        r1 = r1 + 1;
        goto L4
    L7:
        return r0.toString();
    }

    public static long toLong(int r2, int r3) {
        long r0 = toUnsignedLong(r2) << 32;
        return toUnsignedLong(r3) | r0;
    }

    public static long toUnsignedLong(int r4) {
        return r4 & 4294967295L;
    }

    public static <T, U> ListenableFuture<T> transformFutureAsync(ListenableFuture<U> r3, AsyncFunction<U, T> r4) {
        SettableFuture r0 = SettableFuture.create();
        r0.addListener(new Util$$ExternalSyntheticLambda2(r0, r3), MoreExecutors.directExecutor());
        r3.addListener(new Util$$ExternalSyntheticLambda3(r3, r0, r4), MoreExecutors.directExecutor());
        return r0;
    }

    public static long usToMs(long r2) {
        if (r2 != (-9223372036854775807L)) goto L5;
        return r2;
    L5:
        if (r2 != Long.MIN_VALUE) goto L8;
        return r2;
    L8:
        return r2 / 1000;
    }
}
