package androidx.media3.decoder.ffmpeg;

import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.util.Log;
import com.google.android.gms.cast.HlsSegmentFormat;
import kotlin.text.Typography;
import org.telegram.messenger.MediaController;

/* loaded from: classes3.dex */
public final class FfmpegLibrary {
    private static String av1DecoderName;
    private static int inputBufferPaddingSize;
    private static String version;

    static {
        MediaLibraryInfo.registerModule("media3.decoder.ffmpeg");
        inputBufferPaddingSize = -1;
    }

    private static native String ffmpegGetAv1DecoderName();

    private static native int ffmpegGetInputBufferPaddingSize();

    private static native String ffmpegGetVersion();

    private static native boolean ffmpegHasDecoder(String r0);

    public static String getCodecName(String r2) {
        r2.getClass();
        char r1 = 65535;
        switch(r2.hashCode()) {
            case -2123537834: goto L154;
            case -1851077871: goto L150;
            case -1664118616: goto L146;
            case -1662735862: goto L142;
            case -1662656741: goto L138;
            case -1662541442: goto L134;
            case -1662384011: goto L130;
            case -1662384010: goto L126;
            case -1662382439: goto L122;
            case -1662078879: goto L118;
            case -1606874997: goto L114;
            case -1365340241: goto L110;
            case -1095064472: goto L106;
            case -1079884372: goto L102;
            case -1003765268: goto L98;
            case -432837260: goto L94;
            case -432837259: goto L90;
            case -53558318: goto L86;
            case 5583809: goto L82;
            case 5751993: goto L78;
            case 13915911: goto L74;
            case 187078296: goto L70;
            case 266356455: goto L66;
            case 1187890754: goto L62;
            case 1331836730: goto L58;
            case 1331849723: goto L54;
            case 1503095341: goto L50;
            case 1504470054: goto L46;
            case 1504578661: goto L42;
            case 1504619009: goto L38;
            case 1504831518: goto L34;
            case 1504891608: goto L30;
            case 1505942594: goto L26;
            case 1556697186: goto L22;
            case 1599127256: goto L18;
            case 1599127257: goto L14;
            case 1903231877: goto L10;
            case 1903589369: goto L6;
            default: goto L158;
        };
    L6:
        if (r2.equals("audio/g711-mlaw") == false) goto L158;
        r1 = '%';
        goto L158
    L10:
        if (r2.equals("audio/g711-alaw") == false) goto L158;
        r1 = Typography.dollar;
        goto L158
    L14:
        if (r2.equals("video/x-vnd.on2.vp9") == false) goto L158;
        r1 = '#';
        goto L158
    L18:
        if (r2.equals("video/x-vnd.on2.vp8") == false) goto L158;
        r1 = Typography.quote;
        goto L158
    L22:
        if (r2.equals("audio/true-hd") == false) goto L158;
        r1 = '!';
        goto L158
    L26:
        if (r2.equals("audio/vnd.dts.hd") == false) goto L158;
        r1 = ' ';
        goto L158
    L30:
        if (r2.equals("audio/opus") == false) goto L158;
        r1 = 31;
        goto L158
    L34:
        if (r2.equals("audio/mpeg") == false) goto L158;
        r1 = 30;
        goto L158
    L38:
        if (r2.equals("audio/flac") == false) goto L158;
        r1 = 29;
        goto L158
    L42:
        if (r2.equals("audio/eac3") == false) goto L158;
        r1 = 28;
        goto L158
    L46:
        if (r2.equals("audio/alac") == false) goto L158;
        r1 = 27;
        goto L158
    L50:
        if (r2.equals("audio/3gpp") == false) goto L158;
        r1 = 26;
        goto L158
    L54:
        if (r2.equals("video/ogg") == false) goto L158;
        r1 = 25;
        goto L158
    L58:
        if (r2.equals(MediaController.VIDEO_MIME_TYPE) == false) goto L158;
        r1 = 24;
        goto L158
    L62:
        if (r2.equals("video/mp4v-es") == false) goto L158;
        r1 = 23;
        goto L158
    L66:
        if (r2.equals("video/prores") == false) goto L158;
        r1 = 22;
        goto L158
    L70:
        if (r2.equals("audio/ac3") == false) goto L158;
        r1 = 21;
        goto L158
    L74:
        if (r2.equals("video/x-flv") == false) goto L158;
        r1 = 20;
        goto L158
    L78:
        if (r2.equals("video/mpeg2") == false) goto L158;
        r1 = 19;
        goto L158
    L82:
        if (r2.equals("video/mjpeg") == false) goto L158;
        r1 = 18;
        goto L158
    L86:
        if (r2.equals(MediaController.AUDIO_MIME_TYPE) == false) goto L158;
        r1 = 17;
        goto L158
    L90:
        if (r2.equals("audio/mpeg-L2") == false) goto L158;
        r1 = 16;
        goto L158
    L94:
        if (r2.equals("audio/mpeg-L1") == false) goto L158;
        r1 = 15;
        goto L158
    L98:
        if (r2.equals("audio/vorbis") == false) goto L158;
        r1 = 14;
        goto L158
    L102:
        if (r2.equals("video/x-msvideo") == false) goto L158;
        r1 = '\r';
        goto L158
    L106:
        if (r2.equals("audio/vnd.dts") == false) goto L158;
        r1 = '\f';
        goto L158
    L110:
        if (r2.equals("audio/vnd.dts.hd;profile=lbr") == false) goto L158;
        r1 = 11;
        goto L158
    L114:
        if (r2.equals("audio/amr-wb") == false) goto L158;
        r1 = '\n';
        goto L158
    L118:
        if (r2.equals("video/wvc1") == false) goto L158;
        r1 = '\t';
        goto L158
    L122:
        if (r2.equals("video/mpeg") == false) goto L158;
        r1 = '\b';
        goto L158
    L126:
        if (r2.equals("video/mp43") == false) goto L158;
        r1 = 7;
        goto L158
    L130:
        if (r2.equals("video/mp42") == false) goto L158;
        r1 = 6;
        goto L158
    L134:
        if (r2.equals("video/hevc") == false) goto L158;
        r1 = 5;
        goto L158
    L138:
        if (r2.equals("video/divx") == false) goto L158;
        r1 = 4;
        goto L158
    L142:
        if (r2.equals("video/av01") == false) goto L158;
        r1 = 3;
        goto L158
    L146:
        if (r2.equals("video/3gpp") == false) goto L158;
        r1 = 2;
        goto L158
    L150:
        if (r2.equals("video/dolby-vision") == false) goto L158;
        r1 = 1;
        goto L158
    L154:
        if (r2.equals("audio/eac3-joc") == false) goto L158;
        r1 = 0;
    L158:
        switch(r1) {
            case 0: goto L218;
            case 1: goto L216;
            case 2: goto L214;
            case 3: goto L210;
            case 4: goto L221;
            case 5: goto L216;
            case 6: goto L207;
            case 7: goto L205;
            case 8: goto L204;
            case 9: goto L202;
            case 10: goto L200;
            case 11: goto L198;
            case 12: goto L198;
            case 13: goto L220;
            case 14: goto L196;
            case 15: goto L194;
            case 16: goto L194;
            case 17: goto L192;
            case 18: goto L190;
            case 19: goto L189;
            case 20: goto L187;
            case 21: goto L185;
            case 22: goto L183;
            case 23: goto L221;
            case 24: goto L181;
            case 25: goto L179;
            case 26: goto L177;
            case 27: goto L175;
            case 28: goto L218;
            case 29: goto L173;
            case 30: goto L194;
            case 31: goto L171;
            case 32: goto L198;
            case 33: goto L169;
            case 34: goto L167;
            case 35: goto L165;
            case 36: goto L163;
            case 37: goto L161;
            default: goto L159;
        };
    L159:
        return null;
    L161:
        return "pcm_mulaw";
    L163:
        return "pcm_alaw";
    L165:
        return "vp9";
    L167:
        return "vp8";
    L169:
        return "truehd";
    L171:
        return "opus";
    L173:
        return "flac";
    L175:
        return "alac";
    L177:
        return "amrnb";
    L179:
        return "theora";
    L181:
        return "h264";
    L183:
        return "prores";
    L185:
        return HlsSegmentFormat.AC3;
    L187:
        return "flv1";
    L189:
        return "mpeg2video";
    L190:
        return "mjpeg";
    L192:
        return HlsSegmentFormat.AAC;
    L194:
        return HlsSegmentFormat.MP3;
    L196:
        return "vorbis";
    L198:
        return "dca";
    L200:
        return "amrwb";
    L202:
        return "vc1";
    L204:
        return "mpeg2video";
    L205:
        return "msmpeg4";
    L207:
        return "msmpeg4v2";
    L214:
        return "h263";
    L216:
        return "hevc";
    L218:
        return "eac3";
    L220:
        return "mpeg4";
    L221:
        return "mpeg4";
    L210:
        if (av1DecoderName != null) goto L213;
        av1DecoderName = ffmpegGetAv1DecoderName();
    L213:
        return av1DecoderName;
    }

    public static int getInputBufferPaddingSize() {
        if (isAvailable() == true) goto L6;
        return -1;
    L6:
        if (inputBufferPaddingSize != (-1)) goto L9;
        inputBufferPaddingSize = ffmpegGetInputBufferPaddingSize();
    L9:
        return inputBufferPaddingSize;
    }

    public static String getVersion() {
        if (isAvailable() == true) goto L7;
        return null;
    L7:
        if (version != null) goto L10;
        version = ffmpegGetVersion();
    L10:
        return version;
    }

    public static boolean isAvailable() {
        return true;
    }

    public static boolean supportsFormat(String r3) {
        if (isAvailable() == true) goto L5;
        return false;
    L5:
        if (r3 != null) goto L7;
        return false;
    L7:
        String r32 = getCodecName(r3);
        if (r32 != null) goto L11;
        return false;
    L11:
        if (ffmpegHasDecoder(r32) == true) goto L14;
        Log.w("FfmpegLibrary", "No " + r32 + " decoder available. Check the FFmpeg build configuration.");
        return false;
    L14:
        return true;
    }
}
