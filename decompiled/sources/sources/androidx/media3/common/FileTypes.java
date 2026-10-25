package androidx.media3.common;

import android.net.Uri;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class FileTypes {
    public static int inferFileTypeFromMimeType(String r24) {
        if (r24 != null) goto L5;
        return -1;
    L5:
        String r1 = MimeTypes.normalizeMimeType(r24);
        r1.getClass();
        switch(r1.hashCode()) {
            case -2123537834: goto L133;
            case -1662384011: goto L129;
            case -1662384007: goto L125;
            case -1662095187: goto L121;
            case -1606874997: goto L117;
            case -1487656890: goto L113;
            case -1487464693: goto L109;
            case -1487464690: goto L105;
            case -1487394660: goto L101;
            case -1487018032: goto L97;
            case -1248337486: goto L93;
            case -1079884372: goto L89;
            case -1004728940: goto L85;
            case -879272239: goto L81;
            case -879258763: goto L77;
            case -387023398: goto L73;
            case -43467528: goto L69;
            case 13915911: goto L65;
            case 187078296: goto L61;
            case 187078297: goto L57;
            case 187078669: goto L53;
            case 187090232: goto L49;
            case 187091926: goto L45;
            case 187099443: goto L41;
            case 1331848029: goto L37;
            case 1503095341: goto L33;
            case 1504578661: goto L29;
            case 1504619009: goto L25;
            case 1504824762: goto L21;
            case 1504831518: goto L17;
            case 1505118770: goto L13;
            case 2039520277: goto L9;
            default: goto L7;
        };
    L7:
        char r12 = 65535;
    L136:
        switch(r12) {
            case 0: goto L158;
            case 1: goto L157;
            case 2: goto L156;
            case 3: goto L155;
            case 4: goto L154;
            case 5: goto L153;
            case 6: goto L152;
            case 7: goto L152;
            case 8: goto L151;
            case 9: goto L150;
            case 10: goto L149;
            case 11: goto L148;
            case 12: goto L147;
            case 13: goto L146;
            case 14: goto L145;
            case 15: goto L155;
            case 16: goto L155;
            case 17: goto L144;
            case 18: goto L158;
            case 19: goto L143;
            case 20: goto L154;
            case 21: goto L149;
            case 22: goto L142;
            case 23: goto L141;
            case 24: goto L149;
            case 25: goto L154;
            case 26: goto L158;
            case 27: goto L140;
            case 28: goto L139;
            case 29: goto L138;
            case 30: goto L155;
            case 31: goto L155;
            default: goto L137;
        };
    L137:
        return -1;
    L138:
        return 7;
    L139:
        return 15;
    L140:
        return 4;
    L141:
        return 12;
    L142:
        return 9;
    L143:
        return 1;
    L144:
        return 5;
    L145:
        return 17;
    L146:
        return 19;
    L147:
        return 13;
    L148:
        return 16;
    L149:
        return 8;
    L150:
        return 18;
    L151:
        return 14;
    L152:
        return 20;
    L153:
        return 21;
    L154:
        return 3;
    L155:
        return 6;
    L156:
        return 11;
    L157:
        return 10;
    L158:
        return 0;
    L9:
        if (r1.equals("video/x-matroska") == false) goto L7;
        r12 = 31;
        goto L136
    L13:
        if (r1.equals("audio/webm") == false) goto L7;
        r12 = 30;
        goto L136
    L17:
        if (r1.equals("audio/mpeg") == false) goto L7;
        r12 = 29;
        goto L136
    L21:
        if (r1.equals("audio/midi") == false) goto L7;
        r12 = 28;
        goto L136
    L25:
        if (r1.equals("audio/flac") == false) goto L7;
        r12 = 27;
        goto L136
    L29:
        if (r1.equals("audio/eac3") == false) goto L7;
        r12 = 26;
        goto L136
    L33:
        if (r1.equals("audio/3gpp") == false) goto L7;
        r12 = 25;
        goto L136
    L37:
        if (r1.equals("video/mp4") == false) goto L7;
        r12 = 24;
        goto L136
    L41:
        if (r1.equals("audio/wav") == false) goto L7;
        r12 = 23;
        goto L136
    L45:
        if (r1.equals("audio/ogg") == false) goto L7;
        r12 = 22;
        goto L136
    L49:
        if (r1.equals("audio/mp4") == false) goto L7;
        r12 = 21;
        goto L136
    L53:
        if (r1.equals("audio/amr") == false) goto L7;
        r12 = 20;
        goto L136
    L57:
        if (r1.equals("audio/ac4") == false) goto L7;
        r12 = 19;
        goto L136
    L61:
        if (r1.equals("audio/ac3") == false) goto L7;
        r12 = 18;
        goto L136
    L65:
        if (r1.equals("video/x-flv") == false) goto L7;
        r12 = 17;
        goto L136
    L69:
        if (r1.equals("application/webm") == false) goto L7;
        r12 = 16;
        goto L136
    L73:
        if (r1.equals("audio/x-matroska") == false) goto L7;
        r12 = 15;
        goto L136
    L77:
        if (r1.equals("image/png") == false) goto L7;
        r12 = 14;
        goto L136
    L81:
        if (r1.equals("image/bmp") == false) goto L7;
        r12 = '\r';
        goto L136
    L85:
        if (r1.equals("text/vtt") == false) goto L7;
        r12 = '\f';
        goto L136
    L89:
        if (r1.equals("video/x-msvideo") == false) goto L7;
        r12 = 11;
        goto L136
    L93:
        if (r1.equals("application/mp4") == false) goto L7;
        r12 = '\n';
        goto L136
    L97:
        if (r1.equals("image/webp") == false) goto L7;
        r12 = '\t';
        goto L136
    L101:
        if (r1.equals("image/jpeg") == false) goto L7;
        r12 = '\b';
        goto L136
    L105:
        if (r1.equals("image/heif") == false) goto L7;
        r12 = 7;
        goto L136
    L109:
        if (r1.equals("image/heic") == false) goto L7;
        r12 = 6;
        goto L136
    L113:
        if (r1.equals("image/avif") == false) goto L7;
        r12 = 5;
        goto L136
    L117:
        if (r1.equals("audio/amr-wb") == false) goto L7;
        r12 = 4;
        goto L136
    L121:
        if (r1.equals("video/webm") == false) goto L7;
        r12 = 3;
        goto L136
    L125:
        if (r1.equals("video/mp2t") == false) goto L7;
        r12 = 2;
        goto L136
    L129:
        if (r1.equals("video/mp2p") == false) goto L7;
        r12 = 1;
        goto L136
    L133:
        if (r1.equals("audio/eac3-joc") == false) goto L7;
        r12 = 0;
        goto L136
    }

    public static int inferFileTypeFromResponseHeaders(Map<String, List<String>> r1) {
        List<String> r12 = r1.get("Content-Type");
        if (r12 != null) goto L5;
    L8:
        String r13 = null;
    L10:
        return inferFileTypeFromMimeType(r13);
    L5:
        if (r12.isEmpty() == true) goto L8;
        r13 = r12.get(0);
        goto L10
    }

    public static int inferFileTypeFromUri(Uri r6) {
        String r62 = r6.getLastPathSegment();
        if (r62 != null) goto L6;
        return -1;
    L6:
        if (r62.endsWith(".ac3") == false) goto L8;
        return 0;
    L8:
        if (r62.endsWith(".ec3") == false) goto L11;
        return 0;
    L11:
        if (r62.endsWith(".ac4") == false) goto L15;
        return 1;
    L15:
        if (r62.endsWith(".adts") == false) goto L17;
        return 2;
    L17:
        if (r62.endsWith(".aac") == false) goto L20;
        return 2;
    L20:
        if (r62.endsWith(".amr") == false) goto L24;
        return 3;
    L24:
        if (r62.endsWith(".flac") == false) goto L27;
        return 4;
    L27:
        if (r62.endsWith(".flv") == false) goto L30;
        return 5;
    L30:
        if (r62.endsWith(".mid") == false) goto L32;
        return 15;
    L32:
        if (r62.endsWith(".midi") == false) goto L34;
        return 15;
    L34:
        if (r62.endsWith(".smf") == false) goto L37;
        return 15;
    L37:
        if (r62.startsWith(".mk", r62.length() - 4) == false) goto L39;
        return 6;
    L39:
        if (r62.endsWith(".webm") == false) goto L42;
        return 6;
    L42:
        if (r62.endsWith(".mp3") == false) goto L46;
        return 7;
    L46:
        if (r62.endsWith(".mp4") == false) goto L48;
        return 8;
    L48:
        if (r62.startsWith(".m4", r62.length() - 4) == false) goto L50;
        return 8;
    L50:
        if (r62.startsWith(".mp4", r62.length() - 5) == false) goto L52;
        return 8;
    L52:
        if (r62.startsWith(".cmf", r62.length() - 5) == false) goto L55;
        return 8;
    L55:
        if (r62.startsWith(".og", r62.length() - 4) == false) goto L57;
        return 9;
    L57:
        if (r62.endsWith(".opus") == false) goto L60;
        return 9;
    L60:
        if (r62.endsWith(".ps") == false) goto L62;
        return 10;
    L62:
        if (r62.endsWith(".mpeg") == false) goto L64;
        return 10;
    L64:
        if (r62.endsWith(".mpg") == false) goto L66;
        return 10;
    L66:
        if (r62.endsWith(".m2p") == false) goto L69;
        return 10;
    L69:
        if (r62.endsWith(".ts") == false) goto L71;
        return 11;
    L71:
        if (r62.startsWith(".ts", r62.length() - 4) == false) goto L74;
        return 11;
    L74:
        if (r62.endsWith(".wav") == false) goto L76;
        return 12;
    L76:
        if (r62.endsWith(".wave") == false) goto L79;
        return 12;
    L79:
        if (r62.endsWith(".vtt") == false) goto L81;
        return 13;
    L81:
        if (r62.endsWith(".webvtt") == false) goto L84;
        return 13;
    L84:
        if (r62.endsWith(".jpg") == false) goto L86;
        return 14;
    L86:
        if (r62.endsWith(".jpeg") == false) goto L89;
        return 14;
    L89:
        if (r62.endsWith(".avi") == false) goto L93;
        return 16;
    L93:
        if (r62.endsWith(".png") == false) goto L97;
        return 17;
    L97:
        if (r62.endsWith(".webp") == false) goto L101;
        return 18;
    L101:
        if (r62.endsWith(".bmp") == false) goto L103;
        return 19;
    L103:
        if (r62.endsWith(".dib") == false) goto L106;
        return 19;
    L106:
        if (r62.endsWith(".heic") == false) goto L108;
        return 20;
    L108:
        if (r62.endsWith(".heif") == false) goto L111;
        return 20;
    L111:
        if (r62.endsWith(".avif") == false) goto L114;
        return 21;
    L114:
        return -1;
    }
}
