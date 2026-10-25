package androidx.media3.common;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.google.common.base.Ascii;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.checkerframework.dataflow.qual.Pure;
import org.telegram.messenger.MediaController;
import org.telegram.tgnet.TLObject;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes3.dex */
public abstract class MimeTypes {
    private static final Pattern MP4A_RFC_6381_CODEC_PATTERN = null;
    private static final ArrayList<Object> customMimeTypes = null;

    public static final class Mp4aObjectType {
        public final int audioObjectTypeIndication;
        public final int objectTypeIndication;

        public Mp4aObjectType(int r1, int r2) {
            this.objectTypeIndication = r1;
            this.audioObjectTypeIndication = r2;
        }

        public int getEncoding() {
            int r1 = this.audioObjectTypeIndication;
            if (r1 != 2) goto L5;
            return 10;
        L5:
            if (r1 != 5) goto L7;
            return 11;
        L7:
            if (r1 != 29) goto L9;
            return 12;
        L9:
            if (r1 != 42) goto L11;
            return 16;
        L11:
            if (r1 != 22) goto L13;
            return TLObject.FLAG_30;
        L13:
            if (r1 == 23) goto L16;
            return 0;
        L16:
            return 15;
        }
    }

    static {
        customMimeTypes = new ArrayList();
        MP4A_RFC_6381_CODEC_PATTERN = Pattern.compile("^mp4a\\.([a-zA-Z0-9]{2})(?:\\.([0-9]{1,2}))?$");
    }

    public static boolean allSamplesAreSyncSamples(String r4, String r5) {
        if (r4 != null) goto L5;
        return false;
    L5:
        char r3 = 65535;
        switch(r4.hashCode()) {
            case -2123537834: goto L49;
            case -432837260: goto L45;
            case -432837259: goto L41;
            case -53558318: goto L37;
            case 187078296: goto L33;
            case 187094639: goto L29;
            case 1504578661: goto L25;
            case 1504619009: goto L21;
            case 1504831518: goto L17;
            case 1903231877: goto L13;
            case 1903589369: goto L9;
            default: goto L52;
        };
    L52:
        switch(r3) {
            case 0: goto L65;
            case 1: goto L65;
            case 2: goto L65;
            case 3: goto L54;
            case 4: goto L65;
            case 5: goto L65;
            case 6: goto L65;
            case 7: goto L65;
            case 8: goto L65;
            case 9: goto L65;
            case 10: goto L65;
            default: goto L53;
        };
    L53:
        return false;
    L54:
        if (r5 != null) goto L56;
        return false;
    L56:
        Mp4aObjectType r42 = getObjectTypeFromMp4aRFC6381CodecString(r5);
        if (r42 != null) goto L59;
        return false;
    L59:
        int r43 = r42.getEncoding();
        if (r43 != 0) goto L62;
    L64:
        return false;
    L62:
        if (r43 == 16) goto L64;
        return true;
    L65:
        return true;
    L9:
        if (r4.equals("audio/g711-mlaw") == false) goto L52;
        r3 = '\n';
        goto L52
    L13:
        if (r4.equals("audio/g711-alaw") == false) goto L52;
        r3 = '\t';
        goto L52
    L17:
        if (r4.equals("audio/mpeg") == false) goto L52;
        r3 = '\b';
        goto L52
    L21:
        if (r4.equals("audio/flac") == false) goto L52;
        r3 = 7;
        goto L52
    L25:
        if (r4.equals("audio/eac3") == false) goto L52;
        r3 = 6;
        goto L52
    L29:
        if (r4.equals("audio/raw") == false) goto L52;
        r3 = 5;
        goto L52
    L33:
        if (r4.equals("audio/ac3") == false) goto L52;
        r3 = 4;
        goto L52
    L37:
        if (r4.equals(MediaController.AUDIO_MIME_TYPE) == false) goto L52;
        r3 = 3;
        goto L52
    L41:
        if (r4.equals("audio/mpeg-L2") == false) goto L52;
        r3 = 2;
        goto L52
    L45:
        if (r4.equals("audio/mpeg-L1") == false) goto L52;
        r3 = 1;
        goto L52
    L49:
        if (r4.equals("audio/eac3-joc") == false) goto L52;
        r3 = 0;
        goto L52
    }

    public static boolean containsCodecsCorrespondingToMimeType(String r0, String r1) {
        if (getCodecsCorrespondingToMimeType(r0, r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    public static String getAudioMediaMimeType(String r5) {
        if (r5 != null) goto L5;
        return null;
    L5:
        String[] r52 = Util.splitCodecs(r5);
        int r1 = r52.length;
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L13;
        String r3 = getMediaMimeType(r52[r2]);
        if (r3 == null) goto L12;
        if (isAudio(r3) == false) goto L12;
        return r3;
    L12:
        r2 = r2 + 1;
        goto L6
    L13:
        return null;
    }

    public static String getCodecsCorrespondingToMimeType(String r6, String r7) {
        if (r6 == null) goto L19;
        if (r7 == null) goto L19;
        String[] r62 = Util.splitCodecs(r6);
        StringBuilder r1 = new StringBuilder();
        int r2 = r62.length;
        int r3 = 0;
    L7:
        if (r3 >= r2) goto L16;
        String r4 = r62[r3];
        if (r7.equals(getMediaMimeType(r4)) == false) goto L14;
        if (r1.length() <= 0) goto L13;
        r1.append(",");
    L13:
        r1.append(r4);
    L14:
        r3 = r3 + 1;
        goto L7
    L16:
        if (r1.length() <= 0) goto L19;
        return r1.toString();
    L19:
        return null;
    }

    private static String getCustomMimeTypeForCodec(String r2) {
        ArrayList<Object> r22 = customMimeTypes;
        if (r22.size() > 0) goto L5;
        return null;
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r22.get(0));
        throw null;
    }

    public static int getEncoding(String r8, String r9) {
        r8.getClass();
        char r7 = 65535;
        switch(r8.hashCode()) {
            case -2123537834: goto L50;
            case -1365340241: goto L46;
            case -1095064472: goto L42;
            case -53558318: goto L38;
            case 187078296: goto L34;
            case 187078297: goto L30;
            case 550520934: goto L26;
            case 1504578661: goto L22;
            case 1504831518: goto L18;
            case 1504891608: goto L14;
            case 1505942594: goto L10;
            case 1556697186: goto L6;
            default: goto L53;
        };
    L53:
        switch(r7) {
            case 0: goto L76;
            case 1: goto L75;
            case 2: goto L74;
            case 3: goto L67;
            case 4: goto L66;
            case 5: goto L64;
            case 6: goto L62;
            case 7: goto L61;
            case 8: goto L60;
            case 9: goto L58;
            case 10: goto L57;
            case 11: goto L55;
            default: goto L54;
        };
    L54:
        return 0;
    L55:
        return 14;
    L57:
        return 8;
    L58:
        return 20;
    L60:
        return 9;
    L61:
        return 6;
    L62:
        return 30;
    L64:
        return 17;
    L66:
        return 5;
    L67:
        if (r9 != null) goto L69;
        return 0;
    L69:
        Mp4aObjectType r82 = getObjectTypeFromMp4aRFC6381CodecString(r9);
        if (r82 != null) goto L73;
        return 0;
    L73:
        return r82.getEncoding();
    L74:
        return 7;
    L75:
        return 8;
    L76:
        return 18;
    L6:
        if (r8.equals("audio/true-hd") == false) goto L53;
        r7 = 11;
        goto L53
    L10:
        if (r8.equals("audio/vnd.dts.hd") == false) goto L53;
        r7 = '\n';
        goto L53
    L14:
        if (r8.equals("audio/opus") == false) goto L53;
        r7 = '\t';
        goto L53
    L18:
        if (r8.equals("audio/mpeg") == false) goto L53;
        r7 = '\b';
        goto L53
    L22:
        if (r8.equals("audio/eac3") == false) goto L53;
        r7 = 7;
        goto L53
    L26:
        if (r8.equals("audio/vnd.dts.uhd;profile=p2") == false) goto L53;
        r7 = 6;
        goto L53
    L30:
        if (r8.equals("audio/ac4") == false) goto L53;
        r7 = 5;
        goto L53
    L34:
        if (r8.equals("audio/ac3") == false) goto L53;
        r7 = 4;
        goto L53
    L38:
        if (r8.equals(MediaController.AUDIO_MIME_TYPE) == false) goto L53;
        r7 = 3;
        goto L53
    L42:
        if (r8.equals("audio/vnd.dts") == false) goto L53;
        r7 = 2;
        goto L53
    L46:
        if (r8.equals("audio/vnd.dts.hd;profile=lbr") == false) goto L53;
        r7 = 1;
        goto L53
    L50:
        if (r8.equals("audio/eac3-joc") == false) goto L53;
        r7 = 0;
        goto L53
    }

    public static String getMediaMimeType(String r2) {
        String r0 = null;
        if (r2 != null) goto L5;
        return null;
    L5:
        String r22 = Ascii.toLowerCase(r2.trim());
        if (r22.startsWith("avc1") == false) goto L8;
        return MediaController.VIDEO_MIME_TYPE;
    L8:
        if (r22.startsWith("avc3") == false) goto L11;
        return MediaController.VIDEO_MIME_TYPE;
    L11:
        if (r22.startsWith("hev1") == false) goto L13;
        return "video/hevc";
    L13:
        if (r22.startsWith("hvc1") == false) goto L16;
        return "video/hevc";
    L16:
        if (r22.startsWith("dvav") == false) goto L18;
        return "video/dolby-vision";
    L18:
        if (r22.startsWith("dva1") == false) goto L20;
        return "video/dolby-vision";
    L20:
        if (r22.startsWith("dvhe") == false) goto L22;
        return "video/dolby-vision";
    L22:
        if (r22.startsWith("dvh1") == false) goto L25;
        return "video/dolby-vision";
    L25:
        if (r22.startsWith("av01") == false) goto L29;
        return "video/av01";
    L29:
        if (r22.startsWith("vp9") == false) goto L31;
        return "video/x-vnd.on2.vp9";
    L31:
        if (r22.startsWith("vp09") == false) goto L34;
        return "video/x-vnd.on2.vp9";
    L34:
        if (r22.startsWith("vp8") == false) goto L36;
        return "video/x-vnd.on2.vp8";
    L36:
        if (r22.startsWith("vp08") == false) goto L39;
        return "video/x-vnd.on2.vp8";
    L39:
        if (r22.startsWith("mp4a") == false) goto L50;
        if (r22.startsWith("mp4a.") == false) goto L45;
        Mp4aObjectType r23 = getObjectTypeFromMp4aRFC6381CodecString(r22);
        if (r23 == null) goto L45;
        r0 = getMimeTypeFromMp4ObjectType(r23.objectTypeIndication);
    L45:
        if (r0 != null) goto L48;
        return MediaController.AUDIO_MIME_TYPE;
    L48:
        return r0;
    L50:
        if (r22.startsWith("mha1") == false) goto L54;
        return "audio/mha1";
    L54:
        if (r22.startsWith("mhm1") == false) goto L58;
        return "audio/mhm1";
    L58:
        if (r22.startsWith("ac-3") == false) goto L60;
        return "audio/ac3";
    L60:
        if (r22.startsWith("dac3") == false) goto L63;
        return "audio/ac3";
    L63:
        if (r22.startsWith("ec-3") == false) goto L65;
        return "audio/eac3";
    L65:
        if (r22.startsWith("dec3") == false) goto L68;
        return "audio/eac3";
    L68:
        if (r22.startsWith("ec+3") == false) goto L72;
        return "audio/eac3-joc";
    L72:
        if (r22.startsWith("ac-4") == false) goto L74;
        return "audio/ac4";
    L74:
        if (r22.startsWith("dac4") == false) goto L77;
        return "audio/ac4";
    L77:
        if (r22.startsWith("dtsc") == false) goto L81;
        return "audio/vnd.dts";
    L81:
        if (r22.startsWith("dtse") == false) goto L85;
        return "audio/vnd.dts.hd;profile=lbr";
    L85:
        if (r22.startsWith("dtsh") == false) goto L87;
        return "audio/vnd.dts.hd";
    L87:
        if (r22.startsWith("dtsl") == false) goto L90;
        return "audio/vnd.dts.hd";
    L90:
        if (r22.startsWith("dtsx") == false) goto L94;
        return "audio/vnd.dts.uhd;profile=p2";
    L94:
        if (r22.startsWith("opus") == false) goto L98;
        return "audio/opus";
    L98:
        if (r22.startsWith("vorbis") == false) goto L102;
        return "audio/vorbis";
    L102:
        if (r22.startsWith("flac") == false) goto L106;
        return "audio/flac";
    L106:
        if (r22.startsWith("stpp") == false) goto L110;
        return "application/ttml+xml";
    L110:
        if (r22.startsWith("wvtt") == false) goto L114;
        return "text/vtt";
    L114:
        if (r22.contains("cea708") == false) goto L118;
        return "application/cea-708";
    L118:
        if (r22.contains("eia608") == false) goto L120;
        return "application/cea-608";
    L120:
        if (r22.contains("cea608") == false) goto L123;
        return "application/cea-608";
    L123:
        return getCustomMimeTypeForCodec(r22);
    }

    public static String getMimeTypeFromMp4ObjectType(int r1) {
        if (r1 != 32) goto L5;
        return "video/mp4v-es";
    L5:
        if (r1 != 33) goto L7;
        return MediaController.VIDEO_MIME_TYPE;
    L7:
        if (r1 != 35) goto L9;
        return "video/hevc";
    L9:
        if (r1 != 64) goto L11;
        return MediaController.AUDIO_MIME_TYPE;
    L11:
        if (r1 != 163) goto L13;
        return "video/wvc1";
    L13:
        if (r1 != 177) goto L15;
        return "video/x-vnd.on2.vp9";
    L15:
        if (r1 != 221) goto L17;
        return "audio/vorbis";
    L17:
        if (r1 != 165) goto L19;
        return "audio/ac3";
    L19:
        if (r1 == 166) goto L40;
        switch(r1) {
            case 96: goto L38;
            case 97: goto L38;
            case 98: goto L38;
            case 99: goto L38;
            case 100: goto L38;
            case 101: goto L38;
            case 102: goto L58;
            case 103: goto L58;
            case 104: goto L58;
            case 105: goto L36;
            case 106: goto L34;
            case 107: goto L36;
            case 108: goto L32;
            default: goto L21;
        };
    L21:
        switch(r1) {
            case 169: goto L30;
            case 170: goto L28;
            case 171: goto L28;
            case 172: goto L30;
            case 173: goto L26;
            case 174: goto L24;
            default: goto L22;
        };
    L22:
        return null;
    L24:
        return "audio/ac4";
    L26:
        return "audio/opus";
    L28:
        return "audio/vnd.dts.hd";
    L30:
        return "audio/vnd.dts";
    L32:
        return "image/jpeg";
    L34:
        return "video/mpeg";
    L36:
        return "audio/mpeg";
    L38:
        return "video/mpeg2";
    L58:
        return MediaController.AUDIO_MIME_TYPE;
    L40:
        return "audio/eac3";
    }

    public static Mp4aObjectType getObjectTypeFromMp4aRFC6381CodecString(String r3) {
        Matcher r32 = MP4A_RFC_6381_CODEC_PATTERN.matcher(r3);
        if (r32.matches() == true) goto L5;
        return null;
    L5:
        String r0 = (String) Assertions.checkNotNull(r32.group(1));
        String r33 = r32.group(2);
        int r02 = Integer.parseInt(r0, 16);     // Catch: NumberFormatException -> L13
        if (r33 == null) goto L10;
        int r34 = Integer.parseInt(r33);     // Catch: NumberFormatException -> L13
    L12:
        return new Mp4aObjectType(r02, r34);
    L10:
        r34 = 0;
    L16:
        return null;
    }

    private static String getTopLevelType(String r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        int r1 = r3.indexOf(47);
        if (r1 != (-1)) goto L9;
        return null;
    L9:
        return r3.substring(0, r1);
    }

    public static int getTrackType(String r1) {
        if (TextUtils.isEmpty(r1) == false) goto L7;
        return -1;
    L7:
        if (isAudio(r1) == false) goto L11;
        return 1;
    L11:
        if (isVideo(r1) == false) goto L15;
        return 2;
    L15:
        if (isText(r1) == false) goto L19;
        return 3;
    L19:
        if (isImage(r1) == false) goto L23;
        return 4;
    L23:
        if ("application/id3".equals(r1) == false) goto L25;
        return 5;
    L25:
        if ("application/x-emsg".equals(r1) == false) goto L27;
        return 5;
    L27:
        if ("application/x-scte35".equals(r1) == false) goto L29;
        return 5;
    L29:
        if ("application/x-icy".equals(r1) == false) goto L31;
        return 5;
    L31:
        if ("application/vnd.dvb.ait".equals(r1) == false) goto L34;
        return 5;
    L34:
        if ("application/x-camera-motion".equals(r1) == false) goto L38;
        return 6;
    L38:
        return getTrackTypeForCustomMimeType(r1);
    }

    private static int getTrackTypeForCustomMimeType(String r1) {
        ArrayList<Object> r12 = customMimeTypes;
        if (r12.size() > 0) goto L6;
        return -1;
    L6:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r12.get(0));
        throw null;
    }

    public static int getTrackTypeOfCodec(String r0) {
        return getTrackType(getMediaMimeType(r0));
    }

    public static String getVideoMediaMimeType(String r5) {
        if (r5 != null) goto L5;
        return null;
    L5:
        String[] r52 = Util.splitCodecs(r5);
        int r1 = r52.length;
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L13;
        String r3 = getMediaMimeType(r52[r2]);
        if (r3 == null) goto L12;
        if (isVideo(r3) == false) goto L12;
        return r3;
    L12:
        r2 = r2 + 1;
        goto L6
    L13:
        return null;
    }

    public static boolean isAudio(String r1) {
        return MediaStreamTrack.AUDIO_TRACK_KIND.equals(getTopLevelType(r1));
    }

    public static boolean isDolbyVisionCodec(String r5, String r6) {
        if (r5 != null) goto L6;
        return false;
    L6:
        if (r5.startsWith("dvhe") == false) goto L8;
    L34:
        return true;
    L8:
        if (r5.startsWith("dvh1") == true) goto L34;
        if (r6 != null) goto L13;
        return false;
    L13:
        if (r6.startsWith("dvhe") == false) goto L17;
        if (r5.startsWith("hev1") == false) goto L17;
    L32:
        return true;
    L17:
        if (r6.startsWith("dvh1") == false) goto L21;
        if (r5.startsWith("hvc1") == true) goto L32;
    L21:
        if (r6.startsWith("dvav") == false) goto L25;
        if (r5.startsWith("avc3") == true) goto L32;
    L25:
        if (r6.startsWith("dva1") == false) goto L29;
        if (r5.startsWith("avc1") == true) goto L32;
    L29:
        if (r6.startsWith("dav1") == true) goto L31;
    L33:
        return false;
    L31:
        if (r5.startsWith("av01") == false) goto L33;
        goto L32
    }

    public static boolean isImage(String r2) {
        if ("image".equals(getTopLevelType(r2)) == false) goto L5;
        return true;
    L5:
        if ("application/x-image-uri".equals(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isMatroska(String r2) {
        if (r2 != null) goto L6;
        return false;
    L6:
        if (r2.startsWith("video/webm") == false) goto L8;
        return true;
    L8:
        if (r2.startsWith("audio/webm") == false) goto L10;
        return true;
    L10:
        if (r2.startsWith("application/webm") == false) goto L12;
        return true;
    L12:
        if (r2.startsWith("video/x-matroska") == false) goto L14;
        return true;
    L14:
        if (r2.startsWith("audio/x-matroska") == false) goto L16;
        return true;
    L16:
        if (r2.startsWith("application/x-matroska") == true) goto L25;
        return false;
    L25:
        return true;
    }

    @Pure
    public static boolean isText(String r2) {
        if ("text".equals(getTopLevelType(r2)) == false) goto L5;
        return true;
    L5:
        if ("application/x-media3-cues".equals(r2) == false) goto L7;
        return true;
    L7:
        if ("application/cea-608".equals(r2) == false) goto L9;
        return true;
    L9:
        if ("application/cea-708".equals(r2) == false) goto L11;
        return true;
    L11:
        if ("application/x-mp4-cea-608".equals(r2) == false) goto L13;
        return true;
    L13:
        if ("application/x-subrip".equals(r2) == false) goto L15;
        return true;
    L15:
        if ("application/ttml+xml".equals(r2) == false) goto L17;
        return true;
    L17:
        if ("application/x-quicktime-tx3g".equals(r2) == false) goto L19;
        return true;
    L19:
        if ("application/x-mp4-vtt".equals(r2) == false) goto L21;
        return true;
    L21:
        if ("application/x-rawcc".equals(r2) == false) goto L23;
        return true;
    L23:
        if ("application/vobsub".equals(r2) == false) goto L25;
        return true;
    L25:
        if ("application/pgs".equals(r2) == false) goto L27;
        return true;
    L27:
        if ("application/dvbsubs".equals(r2) == true) goto L44;
        return false;
    L44:
        return true;
    }

    public static boolean isVideo(String r1) {
        return MediaStreamTrack.VIDEO_TRACK_KIND.equals(getTopLevelType(r1));
    }

    public static String normalizeMimeType(String r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        String r22 = Ascii.toLowerCase(r2);
        r22.getClass();
        char r1 = 65535;
        switch(r22.hashCode()) {
            case -1833600100: goto L33;
            case -1007807498: goto L29;
            case -979095690: goto L25;
            case -586683234: goto L21;
            case -432836268: goto L17;
            case -432836267: goto L13;
            case 187090231: goto L9;
            default: goto L36;
        };
    L36:
        switch(r1) {
            case 0: goto L50;
            case 1: goto L48;
            case 2: goto L46;
            case 3: goto L44;
            case 4: goto L42;
            case 5: goto L40;
            case 6: goto L38;
            default: goto L37;
        };
    L37:
        return r22;
    L38:
        return "audio/mpeg";
    L40:
        return "audio/mpeg-L2";
    L42:
        return "audio/mpeg-L1";
    L44:
        return "audio/wav";
    L46:
        return "application/x-mpegURL";
    L48:
        return "audio/flac";
    L50:
        return "video/mv-hevc";
    L9:
        if (r22.equals("audio/mp3") == false) goto L36;
        r1 = 6;
        goto L36
    L13:
        if (r22.equals("audio/mpeg-l2") == false) goto L36;
        r1 = 5;
        goto L36
    L17:
        if (r22.equals("audio/mpeg-l1") == false) goto L36;
        r1 = 4;
        goto L36
    L21:
        if (r22.equals("audio/x-wav") == false) goto L36;
        r1 = 3;
        goto L36
    L25:
        if (r22.equals("application/x-mpegurl") == false) goto L36;
        r1 = 2;
        goto L36
    L29:
        if (r22.equals("audio/x-flac") == false) goto L36;
        r1 = 1;
        goto L36
    L33:
        if (r22.equals("video/x-mvhevc") == false) goto L36;
        r1 = 0;
        goto L36
    }
}
