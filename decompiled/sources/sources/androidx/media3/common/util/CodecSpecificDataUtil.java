package androidx.media3.common.util;

import android.annotation.SuppressLint;
import android.util.Pair;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.mediarouter.media.GlobalMediaRouter;
import com.google.common.collect.ImmutableList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.MediaController;
import org.telegram.tgnet.TLObject;

@SuppressLint({"InlinedApi"})
/* loaded from: classes3.dex */
public abstract class CodecSpecificDataUtil {
    private static final String[] HEVC_GENERAL_PROFILE_SPACE_STRINGS = null;
    private static final byte[] NAL_START_CODE = null;
    private static final Pattern PROFILE_PATTERN = null;

    static {
        NAL_START_CODE = new byte[]{0, 0, 0, 1};
        HEVC_GENERAL_PROFILE_SPACE_STRINGS = new String[]{_UrlKt.FRAGMENT_ENCODE_SET, "A", "B", "C"};
        PROFILE_PATTERN = Pattern.compile("^\\D?(\\d+)$");
    }

    private static int ac4BitstreamAndPresentationVersionsToProfileConst(int r2, int r3) {
        if (r2 != 0) goto L4;
        if (r3 != 0) goto L26;
        return GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ADDED;
    L26:
        return -1;
    L4:
        if (r2 != 1) goto L6;
        if (r3 != 0) goto L17;
        return GlobalMediaRouter.CallbackHandler.MSG_PROVIDER_ADDED;
    L17:
        if (r3 != 1) goto L25;
        return GlobalMediaRouter.CallbackHandler.MSG_PROVIDER_REMOVED;
    L25:
        return -1;
    L6:
        if (r2 != 2) goto L27;
        if (r3 != 1) goto L11;
        return 1026;
    L11:
        if (r3 != 2) goto L23;
        return 1028;
    L23:
        return -1;
    L27:
        return -1;
    }

    private static int ac4LevelNumberToConst(int r2) {
        if (r2 != 0) goto L5;
        return 1;
    L5:
        if (r2 != 1) goto L7;
        return 2;
    L7:
        if (r2 != 2) goto L9;
        return 4;
    L9:
        if (r2 == 3) goto L15;
        if (r2 == 4) goto L13;
        return -1;
    L13:
        return 16;
    L15:
        return 8;
    }

    private static int av1LevelNumberToConst(int r0) {
        switch(r0) {
            case 0: goto L51;
            case 1: goto L49;
            case 2: goto L47;
            case 3: goto L45;
            case 4: goto L43;
            case 5: goto L41;
            case 6: goto L39;
            case 7: goto L37;
            case 8: goto L35;
            case 9: goto L33;
            case 10: goto L31;
            case 11: goto L29;
            case 12: goto L27;
            case 13: goto L25;
            case 14: goto L23;
            case 15: goto L21;
            case 16: goto L19;
            case 17: goto L17;
            case 18: goto L15;
            case 19: goto L13;
            case 20: goto L11;
            case 21: goto L9;
            case 22: goto L7;
            case 23: goto L5;
            default: goto L3;
        };
    L3:
        return -1;
    L5:
        return 8388608;
    L7:
        return 4194304;
    L9:
        return TLObject.FLAG_21;
    L11:
        return 1048576;
    L13:
        return 524288;
    L15:
        return 262144;
    L17:
        return 131072;
    L19:
        return 65536;
    L21:
        return 32768;
    L23:
        return 16384;
    L25:
        return 8192;
    L27:
        return 4096;
    L29:
        return 2048;
    L31:
        return 1024;
    L33:
        return 512;
    L35:
        return 256;
    L37:
        return 128;
    L39:
        return 64;
    L41:
        return 32;
    L43:
        return 16;
    L45:
        return 8;
    L47:
        return 4;
    L49:
        return 2;
    L51:
        return 1;
    }

    private static int avcLevelNumberToConst(int r0) {
        switch(r0) {
            case 10: goto L39;
            case 11: goto L37;
            case 12: goto L35;
            case 13: goto L33;
            default: goto L3;
        };
    L3:
        switch(r0) {
            case 20: goto L31;
            case 21: goto L29;
            case 22: goto L27;
            default: goto L4;
        };
    L4:
        switch(r0) {
            case 30: goto L25;
            case 31: goto L23;
            case 32: goto L21;
            default: goto L5;
        };
    L5:
        switch(r0) {
            case 40: goto L19;
            case 41: goto L17;
            case 42: goto L15;
            default: goto L6;
        };
    L6:
        switch(r0) {
            case 50: goto L13;
            case 51: goto L11;
            case 52: goto L9;
            default: goto L7;
        };
    L7:
        return -1;
    L9:
        return 65536;
    L11:
        return 32768;
    L13:
        return 16384;
    L15:
        return 8192;
    L17:
        return 4096;
    L19:
        return 2048;
    L21:
        return 1024;
    L23:
        return 512;
    L25:
        return 256;
    L27:
        return 128;
    L29:
        return 64;
    L31:
        return 32;
    L33:
        return 16;
    L35:
        return 8;
    L37:
        return 4;
    L39:
        return 1;
    }

    private static int avcProfileNumberToConst(int r1) {
        if (r1 != 66) goto L5;
        return 1;
    L5:
        if (r1 != 77) goto L7;
        return 2;
    L7:
        if (r1 != 88) goto L9;
        return 4;
    L9:
        if (r1 != 100) goto L11;
        return 8;
    L11:
        if (r1 != 110) goto L13;
        return 16;
    L13:
        if (r1 != 122) goto L15;
        return 32;
    L15:
        if (r1 == 244) goto L18;
        return -1;
    L18:
        return 64;
    }

    public static String buildAvcCodecString(int r0, int r1, int r2) {
        return String.format("avc1.%02X%02X%02X", new Object[]{Integer.valueOf(r0), Integer.valueOf(r1), Integer.valueOf(r2)});
    }

    public static List<byte[]> buildCea708InitializationData(boolean r2) {
        if (r2 == false) goto L5;
        byte[] r22 = {1};
    L7:
        return Collections.singletonList(r22);
    L5:
        r22 = new byte[]{0};
        goto L7
    }

    public static String buildHevcCodecString(int r2, boolean r3, int r4, int r5, int[] r6, int r7) {
        String r22 = HEVC_GENERAL_PROFILE_SPACE_STRINGS[r2];
        Integer r42 = Integer.valueOf(r4);
        Integer r52 = Integer.valueOf(r5);
        if (r3 == false) goto L5;
        char r32 = 'H';
    L6:
        StringBuilder r0 = new StringBuilder(Util.formatInvariant("hvc1.%s%d.%X.%c%d", new Object[]{r22, r42, r52, Character.valueOf(r32), Integer.valueOf(r7)}));
        int r23 = r6.length;
    L7:
        if (r23 <= 0) goto L11;
        if (r6[r23 - 1] != 0) goto L11;
        r23 = r23 - 1;
    L11:
        int r33 = 0;
    L12:
        if (r33 >= r23) goto L15;
        r0.append(String.format(".%02X", new Object[]{Integer.valueOf(r6[r33])}));
        r33 = r33 + 1;
        goto L12
    L15:
        return r0.toString();
    L5:
        r32 = 'L';
        goto L6
    }

    public static String buildIamfCodecString(byte[] r5) {
        ParsableByteArray r0 = new ParsableByteArray(r5);
        r0.skipLeb128();
        r0.skipBytes(4);
        int r1 = r0.readUnsignedByte();
        int r2 = r0.readUnsignedByte();
        r0.skipBytes(1);
        r0.skipLeb128();
        r0.skipLeb128();
        String r52 = r0.readString(4);
        if (r52.equals("mp4a") == false) goto L9;
        r0.skipLeb128();
        r0.skipBytes(2);
        ParsableBitArray r3 = new ParsableBitArray();
        r3.reset(r0);
        int r02 = r3.readBits(5);
        if (r02 != 31) goto L7;
        r02 = r3.readBits(6) + 32;
    L7:
        r52 = r52 + ".40." + r02;
    L9:
        return Util.formatInvariant("iamf.%03X.%03X.%s", new Object[]{Integer.valueOf(r1), Integer.valueOf(r2), r52});
    }

    public static byte[] buildNalUnit(byte[] r4, int r5, int r6) {
        byte[] r0 = NAL_START_CODE;
        byte[] r1 = new byte[r0.length + r6];
        System.arraycopy(r0, 0, r1, 0, r0.length);
        System.arraycopy(r4, r5, r1, r0.length, r6);
        return r1;
    }

    public static ImmutableList<byte[]> buildVp9CodecPrivateInitializationData(byte r4, byte r5, byte r6, byte r7) {
        return ImmutableList.of(new byte[]{1, 1, r4, 2, 1, r5, 3, 1, r6, 4, 1, r7});
    }

    private static Integer dolbyVisionStringToLevel(String r7) {
        if (r7 != null) goto L5;
        return null;
    L5:
        char r6 = 65535;
        switch(r7.hashCode()) {
            case 1537: goto L57;
            case 1538: goto L53;
            case 1539: goto L49;
            case 1540: goto L45;
            case 1541: goto L41;
            case 1542: goto L37;
            case 1543: goto L33;
            case 1544: goto L29;
            case 1545: goto L25;
            case 1567: goto L21;
            case 1568: goto L17;
            case 1569: goto L13;
            case 1570: goto L9;
            default: goto L60;
        };
    L60:
        switch(r6) {
            case 0: goto L87;
            case 1: goto L85;
            case 2: goto L83;
            case 3: goto L81;
            case 4: goto L79;
            case 5: goto L77;
            case 6: goto L75;
            case 7: goto L73;
            case 8: goto L71;
            case 9: goto L69;
            case 10: goto L67;
            case 11: goto L65;
            case 12: goto L63;
            default: goto L61;
        };
    L61:
        return null;
    L63:
        return 4096;
    L65:
        return 2048;
    L67:
        return 1024;
    L69:
        return 512;
    L71:
        return 256;
    L73:
        return 128;
    L75:
        return 64;
    L77:
        return 32;
    L79:
        return 16;
    L81:
        return 8;
    L83:
        return 4;
    L85:
        return 2;
    L87:
        return 1;
    L9:
        if (r7.equals("13") == false) goto L60;
        r6 = '\f';
        goto L60
    L13:
        if (r7.equals("12") == false) goto L60;
        r6 = 11;
        goto L60
    L17:
        if (r7.equals("11") == false) goto L60;
        r6 = '\n';
        goto L60
    L21:
        if (r7.equals("10") == false) goto L60;
        r6 = '\t';
        goto L60
    L25:
        if (r7.equals("09") == false) goto L60;
        r6 = '\b';
        goto L60
    L29:
        if (r7.equals("08") == false) goto L60;
        r6 = 7;
        goto L60
    L33:
        if (r7.equals("07") == false) goto L60;
        r6 = 6;
        goto L60
    L37:
        if (r7.equals("06") == false) goto L60;
        r6 = 5;
        goto L60
    L41:
        if (r7.equals("05") == false) goto L60;
        r6 = 4;
        goto L60
    L45:
        if (r7.equals("04") == false) goto L60;
        r6 = 3;
        goto L60
    L49:
        if (r7.equals("03") == false) goto L60;
        r6 = 2;
        goto L60
    L53:
        if (r7.equals("02") == false) goto L60;
        r6 = 1;
        goto L60
    L57:
        if (r7.equals("01") == false) goto L60;
        r6 = 0;
        goto L60
    }

    private static Integer dolbyVisionStringToProfile(String r7) {
        if (r7 != null) goto L5;
        return null;
    L5:
        char r6 = 65535;
        switch(r7.hashCode()) {
            case 1536: goto L49;
            case 1537: goto L45;
            case 1538: goto L41;
            case 1539: goto L37;
            case 1540: goto L33;
            case 1541: goto L29;
            case 1542: goto L25;
            case 1543: goto L21;
            case 1544: goto L17;
            case 1545: goto L13;
            case 1567: goto L9;
            default: goto L52;
        };
    L52:
        switch(r6) {
            case 0: goto L75;
            case 1: goto L73;
            case 2: goto L71;
            case 3: goto L69;
            case 4: goto L67;
            case 5: goto L65;
            case 6: goto L63;
            case 7: goto L61;
            case 8: goto L59;
            case 9: goto L57;
            case 10: goto L55;
            default: goto L53;
        };
    L53:
        return null;
    L55:
        return 1024;
    L57:
        return 512;
    L59:
        return 256;
    L61:
        return 128;
    L63:
        return 64;
    L65:
        return 32;
    L67:
        return 16;
    L69:
        return 8;
    L71:
        return 4;
    L73:
        return 2;
    L75:
        return 1;
    L9:
        if (r7.equals("10") == false) goto L52;
        r6 = '\n';
        goto L52
    L13:
        if (r7.equals("09") == false) goto L52;
        r6 = '\t';
        goto L52
    L17:
        if (r7.equals("08") == false) goto L52;
        r6 = '\b';
        goto L52
    L21:
        if (r7.equals("07") == false) goto L52;
        r6 = 7;
        goto L52
    L25:
        if (r7.equals("06") == false) goto L52;
        r6 = 6;
        goto L52
    L29:
        if (r7.equals("05") == false) goto L52;
        r6 = 5;
        goto L52
    L33:
        if (r7.equals("04") == false) goto L52;
        r6 = 4;
        goto L52
    L37:
        if (r7.equals("03") == false) goto L52;
        r6 = 3;
        goto L52
    L41:
        if (r7.equals("02") == false) goto L52;
        r6 = 2;
        goto L52
    L45:
        if (r7.equals("01") == false) goto L52;
        r6 = 1;
        goto L52
    L49:
        if (r7.equals("00") == false) goto L52;
        r6 = 0;
        goto L52
    }

    private static Pair<Integer, Integer> getAacCodecProfileAndLevel(String r5, String[] r6) {
        if (r6.length == 3) goto L15;
        Log.w("CodecSpecificDataUtil", "Ignoring malformed MP4A codec string: " + r5);
        return null;
    L15:
    L13:
        Log.w("CodecSpecificDataUtil", "Ignoring malformed MP4A codec string: " + r5);
    L14:
        return null;
    L8:
        if (MediaController.AUDIO_MIME_TYPE.equals(MimeTypes.getMimeTypeFromMp4ObjectType(Integer.parseInt(r6[1], 16))) == false) goto L14;
        int r62 = mp4aAudioObjectTypeToProfile(Integer.parseInt(r6[2]));     // Catch: NumberFormatException -> L13
        if (r62 == (-1)) goto L14;
        return new Pair(Integer.valueOf(r62), 0);
    }

    private static Pair<Integer, Integer> getAc4CodecProfileAndLevel(String r6, String[] r7) {
        if (r7.length == 4) goto L20;
        Log.w("CodecSpecificDataUtil", "Ignoring malformed AC-4 codec string: " + r6);
        return null;
    L20:
        int r0 = Integer.parseInt(r7[1]);     // Catch: NumberFormatException -> L18
        int r1 = Integer.parseInt(r7[2]);     // Catch: NumberFormatException -> L18
        int r62 = Integer.parseInt(r7[3]);     // Catch: NumberFormatException -> L18
        int r72 = ac4BitstreamAndPresentationVersionsToProfileConst(r0, r1);
        if (r72 != (-1)) goto L12;
        Log.w("CodecSpecificDataUtil", "Unknown AC-4 profile: " + r0 + "." + r1);
        return null;
    L12:
        int r02 = ac4LevelNumberToConst(r62);
        if (r02 != (-1)) goto L17;
        Log.w("CodecSpecificDataUtil", "Unknown AC-4 level: " + r62);
        return null;
    L17:
        return new Pair(Integer.valueOf(r72), Integer.valueOf(r02));
    L18:
        Log.w("CodecSpecificDataUtil", "Ignoring malformed AC-4 codec string: " + r6);
        return null;
    }

    private static Pair<Integer, Integer> getAv1ProfileAndLevel(String r8, String[] r9, ColorInfo r10) {
        if (r9.length >= 4) goto L6;
        Log.w("CodecSpecificDataUtil", "Ignoring malformed AV1 codec string: " + r8);
        return null;
    L6:
        int r0 = 1;
        int r1 = Integer.parseInt(r9[1]);     // Catch: NumberFormatException -> L34
        int r6 = Integer.parseInt(r9[2].substring(0, 2));     // Catch: NumberFormatException -> L34
        int r82 = Integer.parseInt(r9[3]);     // Catch: NumberFormatException -> L34
        if (r1 == 0) goto L12;
        Log.w("CodecSpecificDataUtil", "Unknown AV1 profile: " + r1);
        return null;
    L12:
        if (r82 != 8) goto L14;
    L17:
        if (r82 == 8) goto L28;
        if (r10 != null) goto L21;
    L27:
        r0 = 2;
        goto L28
    L21:
        if (r10.hdrStaticInfo != null) goto L26;
        int r83 = r10.colorTransfer;
        if (r83 == 7) goto L26;
        if (r83 != 6) goto L27;
    L26:
        r0 = 4096;
    L28:
        int r84 = av1LevelNumberToConst(r6);
        if (r84 != (-1)) goto L33;
        Log.w("CodecSpecificDataUtil", "Unknown AV1 level: " + r6);
        return null;
    L33:
        return new Pair(Integer.valueOf(r0), Integer.valueOf(r84));
    L14:
        if (r82 == 10) goto L17;
        Log.w("CodecSpecificDataUtil", "Unknown AV1 bit depth: " + r82);
        return null;
    L34:
        Log.w("CodecSpecificDataUtil", "Ignoring malformed AV1 codec string: " + r8);
        return null;
    }

    private static Pair<Integer, Integer> getAvcProfileAndLevel(String r7, String[] r8) {
        if (r8.length >= 2) goto L28;
        Log.w("CodecSpecificDataUtil", "Ignoring malformed AVC codec string: " + r7);
        return null;
    L28:
    L26:
        Log.w("CodecSpecificDataUtil", "Ignoring malformed AVC codec string: " + r7);
        return null;
    L8:
        if (r8[1].length() != 6) goto L11;
        int r4 = Integer.parseInt(r8[1].substring(0, 2), 16);     // Catch: NumberFormatException -> L26
        int r72 = Integer.parseInt(r8[1].substring(4), 16);     // Catch: NumberFormatException -> L26
    L14:
        int r82 = avcProfileNumberToConst(r4);
        if (r82 != (-1)) goto L18;
        Log.w("CodecSpecificDataUtil", "Unknown AVC profile: " + r4);
        return null;
    L18:
        int r3 = avcLevelNumberToConst(r72);
        if (r3 != (-1)) goto L23;
        Log.w("CodecSpecificDataUtil", "Unknown AVC level: " + r72);
        return null;
    L23:
        return new Pair(Integer.valueOf(r82), Integer.valueOf(r3));
    L11:
        if (r8.length < 3) goto L24;
        int r0 = Integer.parseInt(r8[1]);     // Catch: NumberFormatException -> L26
        r72 = Integer.parseInt(r8[2]);     // Catch: NumberFormatException -> L26
        r4 = r0;
        goto L14
    L24:
        Log.w("CodecSpecificDataUtil", "Ignoring malformed AVC codec string: " + r7);     // Catch: NumberFormatException -> L26
        return null;
    }

    public static Pair<Integer, Integer> getCodecProfileAndLevel(Format r6) {
        String r0 = r6.codecs;
        if (r0 != null) goto L5;
        return null;
    L5:
        String[] r02 = r0.split("\\.");
        if ("video/dolby-vision".equals(r6.sampleMimeType) == true) goto L8;
        char r2 = 0;
        String r3 = r02[0];
        r3.getClass();
        switch(r3.hashCode()) {
            case 2986313: goto L49;
            case 3004662: goto L45;
            case 3006243: goto L41;
            case 3006244: goto L37;
            case 3199032: goto L33;
            case 3214780: goto L29;
            case 3224753: goto L25;
            case 3356560: goto L21;
            case 3475740: goto L17;
            case 3624515: goto L13;
            default: goto L11;
        };
    L11:
        r2 = 65535;
    L51:
        switch(r2) {
            case 0: goto L68;
            case 1: goto L66;
            case 2: goto L64;
            case 3: goto L64;
            case 4: goto L62;
            case 5: goto L62;
            case 6: goto L60;
            case 7: goto L58;
            case 8: goto L56;
            case 9: goto L54;
            default: goto L52;
        };
    L52:
        return null;
    L54:
        return getVp9ProfileAndLevel(r6.codecs, r02);
    L56:
        return getH263ProfileAndLevel(r6.codecs, r02);
    L58:
        return getAacCodecProfileAndLevel(r6.codecs, r02);
    L60:
        return getIamfCodecProfileAndLevel(r6.codecs, r02);
    L62:
        return getHevcProfileAndLevel(r6.codecs, r02, r6.colorInfo);
    L64:
        return getAvcProfileAndLevel(r6.codecs, r02);
    L66:
        return getAv1ProfileAndLevel(r6.codecs, r02, r6.colorInfo);
    L68:
        return getAc4CodecProfileAndLevel(r6.codecs, r02);
    L13:
        if (r3.equals("vp09") == false) goto L11;
        r2 = '\t';
        goto L51
    L17:
        if (r3.equals("s263") == false) goto L11;
        r2 = '\b';
        goto L51
    L21:
        if (r3.equals("mp4a") == false) goto L11;
        r2 = 7;
        goto L51
    L25:
        if (r3.equals("iamf") == false) goto L11;
        r2 = 6;
        goto L51
    L29:
        if (r3.equals("hvc1") == false) goto L11;
        r2 = 5;
        goto L51
    L33:
        if (r3.equals("hev1") == false) goto L11;
        r2 = 4;
        goto L51
    L37:
        if (r3.equals("avc2") == false) goto L11;
        r2 = 3;
        goto L51
    L41:
        if (r3.equals("avc1") == false) goto L11;
        r2 = 2;
        goto L51
    L45:
        if (r3.equals("av01") == false) goto L11;
        r2 = 1;
        goto L51
    L49:
        if (r3.equals("ac-4") == true) goto L51;
    L8:
        return getDolbyVisionProfileAndLevel(r6.codecs, r02);
    }

    private static Pair<Integer, Integer> getDolbyVisionProfileAndLevel(String r6, String[] r7) {
        if (r7.length >= 3) goto L6;
        Log.w("CodecSpecificDataUtil", "Ignoring malformed Dolby Vision codec string: " + r6);
        return null;
    L6:
        Matcher r0 = PROFILE_PATTERN.matcher(r7[1]);
        if (r0.matches() == true) goto L10;
        Log.w("CodecSpecificDataUtil", "Ignoring malformed Dolby Vision codec string: " + r6);
        return null;
    L10:
        String r62 = r0.group(1);
        Integer r02 = dolbyVisionStringToProfile(r62);
        if (r02 != null) goto L14;
        Log.w("CodecSpecificDataUtil", "Unknown Dolby Vision profile string: " + r62);
        return null;
    L14:
        String r63 = r7[2];
        Integer r72 = dolbyVisionStringToLevel(r63);
        if (r72 != null) goto L19;
        Log.w("CodecSpecificDataUtil", "Unknown Dolby Vision level string: " + r63);
        return null;
    L19:
        return new Pair(r02, r72);
    }

    private static Pair<Integer, Integer> getH263ProfileAndLevel(String r6, String[] r7) {
        Pair<Integer, Integer> r0 = new Pair(1, 1);
        if (r7.length >= 3) goto L10;
        Log.w("CodecSpecificDataUtil", "Ignoring malformed H263 codec string: " + r6);
        return r0;
    L10:
        return new Pair(Integer.valueOf(Integer.parseInt(r7[1])), Integer.valueOf(Integer.parseInt(r7[2])));
    L8:
        Log.w("CodecSpecificDataUtil", "Ignoring malformed H263 codec string: " + r6);
        return r0;
    }

    public static Pair<Integer, Integer> getHevcProfileAndLevel(String r6, String[] r7, ColorInfo r8) {
        if (r7.length >= 4) goto L6;
        Log.w("CodecSpecificDataUtil", "Ignoring malformed HEVC codec string: " + r6);
        return null;
    L6:
        int r1 = 1;
        Matcher r0 = PROFILE_PATTERN.matcher(r7[1]);
        if (r0.matches() == true) goto L10;
        Log.w("CodecSpecificDataUtil", "Ignoring malformed HEVC codec string: " + r6);
        return null;
    L10:
        String r62 = r0.group(1);
        if ("1".equals(r62) == true) goto L22;
        r1 = 6;
        if ("2".equals(r62) == false) goto L21;
        if (r8 != null) goto L17;
    L19:
        r1 = 2;
        goto L22
    L17:
        if (r8.colorTransfer != 6) goto L19;
        r1 = 4096;
        goto L22
    L21:
        if ("6".equals(r62) == true) goto L22;
        Log.w("CodecSpecificDataUtil", "Unknown HEVC profile string: " + r62);
        return null;
    L22:
        String r63 = r7[3];
        Integer r72 = hevcCodecStringToProfileLevel(r63);
        if (r72 != null) goto L27;
        Log.w("CodecSpecificDataUtil", "Unknown HEVC level string: " + r63);
        return null;
    L27:
        return new Pair(Integer.valueOf(r1), r72);
    }

    private static Pair<Integer, Integer> getIamfCodecProfileAndLevel(String r10, String[] r11) {
        int r3 = 4;
        if (r11.length >= 4) goto L38;
        Log.w("CodecSpecificDataUtil", "Ignoring malformed IAMF codec string: " + r10);
        return null;
    L38:
        int r0 = 1 << (Integer.parseInt(r11[1]) + 16);
        String r5 = r11[3];
        r5.getClass();
        char r9 = 65535;
        switch(r5.hashCode()) {
            case 2464863: goto L24;
            case 3114792: goto L20;
            case 3238865: goto L16;
            case 3356560: goto L12;
            default: goto L27;
        };
    L27:
        switch(r9) {
            case 0: goto L32;
            case 1: goto L34;
            case 2: goto L31;
            case 3: goto L30;
            default: goto L28;
        };
    L28:
        Log.w("CodecSpecificDataUtil", "Ignoring unknown codec identifier for IAMF auxiliary profile: " + r11[3]);
        return null;
    L30:
        r3 = 2;
        goto L34
    L31:
        r3 = 8;
        goto L34
    L32:
        r3 = 1;
    L34:
        return new Pair(Integer.valueOf((16777216 | r0) | r3), 0);
    L12:
        if (r5.equals("mp4a") == false) goto L27;
        r9 = 3;
        goto L27
    L16:
        if (r5.equals("ipcm") == false) goto L27;
        r9 = 2;
        goto L27
    L20:
        if (r5.equals("fLaC") == false) goto L27;
        r9 = 1;
        goto L27
    L24:
        if (r5.equals("Opus") == false) goto L27;
        r9 = 0;
    L35:
        e = move-exception;
        Log.w("CodecSpecificDataUtil", "Ignoring malformed primary profile in IAMF codec string: " + r11[1], e);
        return null;
    }

    private static Pair<Integer, Integer> getVp9ProfileAndLevel(String r5, String[] r6) {
        if (r6.length >= 3) goto L20;
        Log.w("CodecSpecificDataUtil", "Ignoring malformed VP9 codec string: " + r5);
        return null;
    L20:
        int r0 = Integer.parseInt(r6[1]);     // Catch: NumberFormatException -> L18
        int r52 = Integer.parseInt(r6[2]);     // Catch: NumberFormatException -> L18
        int r62 = vp9ProfileNumberToConst(r0);
        if (r62 != (-1)) goto L12;
        Log.w("CodecSpecificDataUtil", "Unknown VP9 profile: " + r0);
        return null;
    L12:
        int r02 = vp9LevelNumberToConst(r52);
        if (r02 != (-1)) goto L17;
        Log.w("CodecSpecificDataUtil", "Unknown VP9 level: " + r52);
        return null;
    L17:
        return new Pair(Integer.valueOf(r62), Integer.valueOf(r02));
    L18:
        Log.w("CodecSpecificDataUtil", "Ignoring malformed VP9 codec string: " + r5);
        return null;
    }

    private static Integer hevcCodecStringToProfileLevel(String r8) {
        if (r8 != null) goto L5;
        return null;
    L5:
        char r7 = 65535;
        switch(r8.hashCode()) {
            case 70821: goto L109;
            case 70914: goto L105;
            case 70917: goto L101;
            case 71007: goto L97;
            case 71010: goto L93;
            case 74665: goto L89;
            case 74758: goto L85;
            case 74761: goto L81;
            case 74851: goto L77;
            case 74854: goto L73;
            case 2193639: goto L69;
            case 2193642: goto L65;
            case 2193732: goto L61;
            case 2193735: goto L57;
            case 2193738: goto L53;
            case 2193825: goto L49;
            case 2193828: goto L45;
            case 2193831: goto L41;
            case 2312803: goto L37;
            case 2312806: goto L33;
            case 2312896: goto L29;
            case 2312899: goto L25;
            case 2312902: goto L21;
            case 2312989: goto L17;
            case 2312992: goto L13;
            case 2312995: goto L9;
            default: goto L112;
        };
    L112:
        switch(r7) {
            case 0: goto L165;
            case 1: goto L163;
            case 2: goto L161;
            case 3: goto L159;
            case 4: goto L157;
            case 5: goto L155;
            case 6: goto L153;
            case 7: goto L151;
            case 8: goto L149;
            case 9: goto L147;
            case 10: goto L145;
            case 11: goto L143;
            case 12: goto L141;
            case 13: goto L139;
            case 14: goto L137;
            case 15: goto L135;
            case 16: goto L133;
            case 17: goto L131;
            case 18: goto L129;
            case 19: goto L127;
            case 20: goto L125;
            case 21: goto L123;
            case 22: goto L121;
            case 23: goto L119;
            case 24: goto L117;
            case 25: goto L115;
            default: goto L113;
        };
    L113:
        return null;
    L115:
        return 16777216;
    L117:
        return 4194304;
    L119:
        return 1048576;
    L121:
        return 262144;
    L123:
        return 65536;
    L125:
        return 16384;
    L127:
        return 4096;
    L129:
        return 1024;
    L131:
        return 33554432;
    L133:
        return 8388608;
    L135:
        return Integer.valueOf(TLObject.FLAG_21);
    L137:
        return 524288;
    L139:
        return 131072;
    L141:
        return 32768;
    L143:
        return 8192;
    L145:
        return 2048;
    L147:
        return 256;
    L149:
        return 64;
    L151:
        return 16;
    L153:
        return 4;
    L155:
        return 1;
    L157:
        return 512;
    L159:
        return 128;
    L161:
        return 32;
    L163:
        return 8;
    L165:
        return 2;
    L9:
        if (r8.equals("L186") == false) goto L112;
        r7 = 25;
        goto L112
    L13:
        if (r8.equals("L183") == false) goto L112;
        r7 = 24;
        goto L112
    L17:
        if (r8.equals("L180") == false) goto L112;
        r7 = 23;
        goto L112
    L21:
        if (r8.equals("L156") == false) goto L112;
        r7 = 22;
        goto L112
    L25:
        if (r8.equals("L153") == false) goto L112;
        r7 = 21;
        goto L112
    L29:
        if (r8.equals("L150") == false) goto L112;
        r7 = 20;
        goto L112
    L33:
        if (r8.equals("L123") == false) goto L112;
        r7 = 19;
        goto L112
    L37:
        if (r8.equals("L120") == false) goto L112;
        r7 = 18;
        goto L112
    L41:
        if (r8.equals("H186") == false) goto L112;
        r7 = 17;
        goto L112
    L45:
        if (r8.equals("H183") == false) goto L112;
        r7 = 16;
        goto L112
    L49:
        if (r8.equals("H180") == false) goto L112;
        r7 = 15;
        goto L112
    L53:
        if (r8.equals("H156") == false) goto L112;
        r7 = 14;
        goto L112
    L57:
        if (r8.equals("H153") == false) goto L112;
        r7 = '\r';
        goto L112
    L61:
        if (r8.equals("H150") == false) goto L112;
        r7 = '\f';
        goto L112
    L65:
        if (r8.equals("H123") == false) goto L112;
        r7 = 11;
        goto L112
    L69:
        if (r8.equals("H120") == false) goto L112;
        r7 = '\n';
        goto L112
    L73:
        if (r8.equals("L93") == false) goto L112;
        r7 = '\t';
        goto L112
    L77:
        if (r8.equals("L90") == false) goto L112;
        r7 = '\b';
        goto L112
    L81:
        if (r8.equals("L63") == false) goto L112;
        r7 = 7;
        goto L112
    L85:
        if (r8.equals("L60") == false) goto L112;
        r7 = 6;
        goto L112
    L89:
        if (r8.equals("L30") == false) goto L112;
        r7 = 5;
        goto L112
    L93:
        if (r8.equals("H93") == false) goto L112;
        r7 = 4;
        goto L112
    L97:
        if (r8.equals("H90") == false) goto L112;
        r7 = 3;
        goto L112
    L101:
        if (r8.equals("H63") == false) goto L112;
        r7 = 2;
        goto L112
    L105:
        if (r8.equals("H60") == false) goto L112;
        r7 = 1;
        goto L112
    L109:
        if (r8.equals("H30") == false) goto L112;
        r7 = 0;
        goto L112
    }

    private static int mp4aAudioObjectTypeToProfile(int r1) {
        int r0 = 17;
        if (r1 == 17) goto L29;
        r0 = 20;
        if (r1 == 20) goto L29;
        r0 = 23;
        if (r1 == 23) goto L29;
        r0 = 29;
        if (r1 == 29) goto L29;
        r0 = 39;
        if (r1 == 39) goto L29;
        r0 = 42;
        if (r1 == 42) goto L29;
        switch(r1) {
            case 1: goto L27;
            case 2: goto L25;
            case 3: goto L23;
            case 4: goto L21;
            case 5: goto L19;
            case 6: goto L17;
            default: goto L15;
        };
    L15:
        return -1;
    L17:
        return 6;
    L19:
        return 5;
    L21:
        return 4;
    L23:
        return 3;
    L25:
        return 2;
    L27:
        return 1;
    L29:
        return r0;
    }

    public static Pair<Integer, Integer> parseAlacAudioSpecificConfig(byte[] r2) {
        ParsableByteArray r0 = new ParsableByteArray(r2);
        r0.setPosition(9);
        int r22 = r0.readUnsignedByte();
        r0.setPosition(20);
        return Pair.create(Integer.valueOf(r0.readUnsignedIntToInt()), Integer.valueOf(r22));
    }

    public static boolean parseCea708InitializationData(List<byte[]> r3) {
        if (r3.size() == 1) goto L5;
    L9:
        return false;
    L5:
        if (r3.get(0).length != 1) goto L9;
        if (r3.get(0)[0] != 1) goto L9;
        return true;
    }

    private static int vp9LevelNumberToConst(int r1) {
        if (r1 != 10) goto L5;
        return 1;
    L5:
        if (r1 != 11) goto L7;
        return 2;
    L7:
        if (r1 != 20) goto L9;
        return 4;
    L9:
        if (r1 != 21) goto L11;
        return 8;
    L11:
        if (r1 != 30) goto L13;
        return 16;
    L13:
        if (r1 != 31) goto L15;
        return 32;
    L15:
        if (r1 != 40) goto L17;
        return 64;
    L17:
        if (r1 != 41) goto L19;
        return 128;
    L19:
        if (r1 != 50) goto L21;
        return 256;
    L21:
        if (r1 == 51) goto L31;
        switch(r1) {
            case 60: goto L29;
            case 61: goto L27;
            case 62: goto L25;
            default: goto L23;
        };
    L23:
        return -1;
    L25:
        return 8192;
    L27:
        return 4096;
    L29:
        return 2048;
    L31:
        return 512;
    }

    private static int vp9ProfileNumberToConst(int r2) {
        if (r2 != 0) goto L5;
        return 1;
    L5:
        if (r2 == 1) goto L15;
        if (r2 != 2) goto L8;
        return 4;
    L8:
        if (r2 == 3) goto L11;
        return -1;
    L11:
        return 8;
    L15:
        return 2;
    }
}
