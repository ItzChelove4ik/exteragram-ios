package androidx.media3.extractor.text.ttml;

import android.text.Layout;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ColorParser;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.common.util.XmlPullParserUtil;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.LegacySubtitleUtil;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleDecoderException;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.base.Ascii;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes4.dex */
public final class TtmlParser implements SubtitleParser {
    private static final Pattern CELL_RESOLUTION = null;
    private static final Pattern CLOCK_TIME = null;
    private static final FrameAndTickRate DEFAULT_FRAME_AND_TICK_RATE = null;
    private static final Pattern FONT_SIZE = null;
    private static final Pattern OFFSET_TIME = null;
    static final Pattern PERCENTAGE_COORDINATES = null;
    private static final Pattern PIXEL_COORDINATES = null;
    static final Pattern SIGNED_PERCENTAGE = null;
    private final XmlPullParserFactory xmlParserFactory;

    public static final class FrameAndTickRate {
        final float effectiveFrameRate;
        final int subFrameRate;
        final int tickRate;

        public FrameAndTickRate(float r1, int r2, int r3) {
            this.effectiveFrameRate = r1;
            this.subFrameRate = r2;
            this.tickRate = r3;
        }
    }

    public static final class TtsExtent {
        final int height;
        final int width;

        public TtsExtent(int r1, int r2) {
            this.width = r1;
            this.height = r2;
        }
    }

    static {
        CLOCK_TIME = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
        OFFSET_TIME = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
        FONT_SIZE = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
        SIGNED_PERCENTAGE = Pattern.compile("^([-+]?\\d+\\.?\\d*?)%$");
        PERCENTAGE_COORDINATES = Pattern.compile("^([-+]?\\d+\\.?\\d*?)% ([-+]?\\d+\\.?\\d*?)%$");
        PIXEL_COORDINATES = Pattern.compile("^([-+]?\\d+\\.?\\d*?)px ([-+]?\\d+\\.?\\d*?)px$");
        CELL_RESOLUTION = Pattern.compile("^(\\d+) (\\d+)$");
        DEFAULT_FRAME_AND_TICK_RATE = new FrameAndTickRate(30.0f, 1, 1);
    }

    public TtmlParser() {
        XmlPullParserFactory r0 = XmlPullParserFactory.newInstance();     // Catch: XmlPullParserException -> L5
        this.xmlParserFactory = r0;     // Catch: XmlPullParserException -> L5
        r0.setNamespaceAware(true);     // Catch: XmlPullParserException -> L5
        return;
    L5:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Couldn't create XmlPullParserFactory instance", e);
        throw null;
    }

    private static TtmlStyle createIfNull(TtmlStyle r0) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        return new TtmlStyle();
    }

    private static boolean isSupportedTag(String r1) {
        if (r1.equals("tt") == false) goto L5;
        return true;
    L5:
        if (r1.equals("head") == false) goto L7;
        return true;
    L7:
        if (r1.equals("body") == false) goto L9;
        return true;
    L9:
        if (r1.equals("div") == false) goto L11;
        return true;
    L11:
        if (r1.equals("p") == false) goto L13;
        return true;
    L13:
        if (r1.equals("span") == false) goto L15;
        return true;
    L15:
        if (r1.equals("br") == false) goto L17;
        return true;
    L17:
        if (r1.equals("style") == false) goto L19;
        return true;
    L19:
        if (r1.equals("styling") == false) goto L21;
        return true;
    L21:
        if (r1.equals("layout") == false) goto L23;
        return true;
    L23:
        if (r1.equals("region") == false) goto L25;
        return true;
    L25:
        if (r1.equals("metadata") == false) goto L27;
        return true;
    L27:
        if (r1.equals("image") == false) goto L29;
        return true;
    L29:
        if (r1.equals("data") == false) goto L31;
        return true;
    L31:
        if (r1.equals("information") == true) goto L50;
        return false;
    L50:
        return true;
    }

    private static Layout.Alignment parseAlignment(String r2) {
        String r22 = Ascii.toLowerCase(r2);
        r22.getClass();
        char r1 = 65535;
        switch(r22.hashCode()) {
            case -1364013995: goto L22;
            case 100571: goto L18;
            case 3317767: goto L14;
            case 108511772: goto L10;
            case 109757538: goto L6;
            default: goto L25;
        };
    L25:
        switch(r1) {
            case 0: goto L33;
            case 1: goto L31;
            case 2: goto L29;
            case 3: goto L31;
            case 4: goto L29;
            default: goto L26;
        };
    L26:
        return null;
    L29:
        return Layout.Alignment.ALIGN_NORMAL;
    L31:
        return Layout.Alignment.ALIGN_OPPOSITE;
    L33:
        return Layout.Alignment.ALIGN_CENTER;
    L6:
        if (r22.equals("start") == false) goto L25;
        r1 = 4;
        goto L25
    L10:
        if (r22.equals("right") == false) goto L25;
        r1 = 3;
        goto L25
    L14:
        if (r22.equals("left") == false) goto L25;
        r1 = 2;
        goto L25
    L18:
        if (r22.equals("end") == false) goto L25;
        r1 = 1;
        goto L25
    L22:
        if (r22.equals("center") == false) goto L25;
        r1 = 0;
        goto L25
    }

    private static int parseCellRows(XmlPullParser r7, int r8) {
        String r72 = r7.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "cellResolution");
        if (r72 != null) goto L5;
        return r8;
    L5:
        Matcher r1 = CELL_RESOLUTION.matcher(r72);
        if (r1.matches() == true) goto L9;
        Log.w("TtmlParser", "Ignoring malformed cell resolution: ".concat(r72));
        return r8;
    L9:
        boolean r2 = true;
        int r5 = Integer.parseInt((String) Assertions.checkNotNull(r1.group(1)));     // Catch: NumberFormatException -> L17
        int r12 = Integer.parseInt((String) Assertions.checkNotNull(r1.group(2)));     // Catch: NumberFormatException -> L17
        if (r5 == 0) goto L14;
        if (r12 == 0) goto L14;
    L15:
        Assertions.checkArgument(r2, "Invalid cell resolution " + r5 + " " + r12);     // Catch: NumberFormatException -> L17
        return r12;
    L14:
        r2 = false;
    L17:
        Log.w("TtmlParser", "Ignoring malformed cell resolution: ".concat(r72));
        return r8;
    }

    private static void parseFontSize(String r7, TtmlStyle r8) {
        String[] r0 = Util.split(r7, "\\s+");
        if (r0.length != 1) goto L6;
        Matcher r02 = FONT_SIZE.matcher(r7);
    L9:
        if (r02.matches() == false) goto L34;
        String r1 = (String) Assertions.checkNotNull(r02.group(3));
        r1.getClass();
        char r6 = 65535;
        switch(r1.hashCode()) {
            case 37: goto L22;
            case 3240: goto L18;
            case 3592: goto L14;
            default: goto L25;
        };
    L25:
        switch(r6) {
            case 0: goto L30;
            case 1: goto L29;
            case 2: goto L28;
            default: goto L27;
        };
    L28:
        r8.setFontSizeUnit(1);
    L31:
        r8.setFontSize(Float.parseFloat((String) Assertions.checkNotNull(r02.group(1))));
        return;
    L29:
        r8.setFontSizeUnit(2);
        goto L31
    L30:
        r8.setFontSizeUnit(3);
        goto L31
    L27:
        throw new SubtitleDecoderException("Invalid unit for fontSize: '" + r1 + "'.");
    L14:
        if (r1.equals("px") == false) goto L25;
        r6 = 2;
        goto L25
    L18:
        if (r1.equals("em") == false) goto L25;
        r6 = 1;
        goto L25
    L22:
        if (r1.equals("%") == false) goto L25;
        r6 = 0;
        goto L25
    L34:
        throw new SubtitleDecoderException("Invalid expression for fontSize: '" + r7 + "'.");
    L6:
        if (r0.length != 2) goto L36;
        r02 = FONT_SIZE.matcher(r0[1]);
        Log.w("TtmlParser", "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first.");
        goto L9
    L36:
        throw new SubtitleDecoderException("Invalid number of entries for fontSize: " + r0.length + ".");
    }

    private static FrameAndTickRate parseFrameAndTickRates(XmlPullParser r7) {
        String r0 = r7.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "frameRate");
        if (r0 == null) goto L5;
        int r02 = Integer.parseInt(r0);
    L6:
        String r2 = r7.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "frameRateMultiplier");
        if (r2 != null) goto L9;
        float r3 = 1.0f;
    L14:
        FrameAndTickRate r22 = DEFAULT_FRAME_AND_TICK_RATE;
        int r4 = r22.subFrameRate;
        String r5 = r7.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "subFrameRate");
        if (r5 == null) goto L17;
        r4 = Integer.parseInt(r5);
    L17:
        int r23 = r22.tickRate;
        String r72 = r7.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "tickRate");
        if (r72 == null) goto L21;
        r23 = Integer.parseInt(r72);
    L21:
        return new FrameAndTickRate(r02 * r3, r4, r23);
    L9:
        if (Util.split(r2, " ").length != 2) goto L11;
        boolean r32 = true;
    L12:
        Assertions.checkArgument(r32, "frameRateMultiplier doesn't have 2 parts");
        r3 = Integer.parseInt(r2[0]) / Integer.parseInt(r2[1]);
        goto L14
    L11:
        r32 = false;
        goto L12
    L5:
        r02 = 30;
        goto L6
    }

    private static Map<String, TtmlStyle> parseHeader(XmlPullParser r5, Map<String, TtmlStyle> r6, int r7, TtsExtent r8, Map<String, TtmlRegion> r9, Map<String, String> r10) {
    L2:
        r5.next();
        if (XmlPullParserUtil.isStartTag(r5, "style") == false) goto L13;
        String r0 = XmlPullParserUtil.getAttributeValue(r5, "style");
        TtmlStyle r1 = parseStyleAttributes(r5, new TtmlStyle());
        if (r0 == null) goto L9;
        String[] r02 = parseStyleIds(r0);
        int r2 = r02.length;
        int r3 = 0;
    L7:
        if (r3 >= r2) goto L9;
        r1.chain(r6.get(r02[r3]));
        r3 = r3 + 1;
    L9:
        String r03 = r1.getId();
        if (r03 == null) goto L21;
        r6.put(r03, r1);
    L21:
        if (XmlPullParserUtil.isEndTag(r5, "head") == false) goto L2;
        return r6;
    L13:
        if (XmlPullParserUtil.isStartTag(r5, "region") == false) goto L18;
        TtmlRegion r04 = parseRegionAttributes(r5, r7, r8, r6);
        if (r04 == null) goto L21;
        r9.put(r04.id, r04);
        goto L21
    L18:
        if (XmlPullParserUtil.isStartTag(r5, "metadata") == false) goto L21;
        parseMetadata(r5, r10);
        goto L21
    }

    private static void parseMetadata(XmlPullParser r2, Map<String, String> r3) {
    L2:
        r2.next();
        if (XmlPullParserUtil.isStartTag(r2, "image") == false) goto L8;
        String r0 = XmlPullParserUtil.getAttributeValue(r2, "id");
        if (r0 == null) goto L8;
        r3.put(r0, r2.nextText());
    L8:
        if (XmlPullParserUtil.isEndTag(r2, "metadata") == false) goto L2;
    }

    private static TtmlNode parseNode(XmlPullParser r21, TtmlNode r22, Map<String, TtmlRegion> r23, FrameAndTickRate r24) {
        XmlPullParser r0 = r21;
        int r2 = r0.getAttributeCount();
        String r3 = null;
        TtmlStyle r5 = parseStyleAttributes(r0, null);
        long r10 = -9223372036854775807L;
        long r12 = -9223372036854775807L;
        long r14 = -9223372036854775807L;
        String[] r6 = null;
        String r7 = _UrlKt.FRAGMENT_ENCODE_SET;
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L47;
        String r8 = r0.getAttributeName(r4);
        int r18 = r2;
        String r25 = r0.getAttributeValue(r4);
        r8.getClass();
        char r20 = 65535;
        switch(r8.hashCode()) {
            case -934795532: goto L28;
            case 99841: goto L24;
            case 100571: goto L20;
            case 93616297: goto L16;
            case 109780401: goto L12;
            case 1292595405: goto L8;
            default: goto L31;
        };
    L31:
        switch(r20) {
            case 0: goto L44;
            case 1: goto L42;
            case 2: goto L41;
            case 3: goto L40;
            case 4: goto L37;
            case 5: goto L34;
            default: goto L46;
        };
    L37:
        String[] r02 = parseStyleIds(r25);
        if (r02.length <= 0) goto L46;
        r6 = r02;
        goto L46
    L40:
        r10 = parseTimeExpression(r25, r24);
        goto L46
    L41:
        r12 = parseTimeExpression(r25, r24);
        goto L46
    L42:
        r14 = parseTimeExpression(r25, r24);
        goto L46
    L34:
        if (r25.startsWith("#") == false) goto L46;
        r3 = r25.substring(1);
        goto L46
    L44:
        if (r23.containsKey(r25) == false) goto L46;
        r7 = r25;
    L46:
        r4 = r4 + 1;
        r0 = r21;
        r2 = r18;
        goto L3
    L8:
        if (r8.equals("backgroundImage") == false) goto L31;
        r20 = 5;
        goto L31
    L12:
        if (r8.equals("style") == false) goto L31;
        r20 = 4;
        goto L31
    L16:
        if (r8.equals("begin") == false) goto L31;
        r20 = 3;
        goto L31
    L20:
        if (r8.equals("end") == false) goto L31;
        r20 = 2;
        goto L31
    L24:
        if (r8.equals("dur") == false) goto L31;
        r20 = 1;
        goto L31
    L28:
        if (r8.equals("region") == false) goto L31;
        r20 = 0;
        goto L31
    L47:
        if (r22 == null) goto L56;
        long r03 = r22.startTimeUs;
        if (r03 == (-9223372036854775807L)) goto L56;
        if (r10 == (-9223372036854775807L)) goto L54;
        r10 = r10 + r03;
    L54:
        if (r12 == (-9223372036854775807L)) goto L56;
        r12 = r12 + r03;
    L56:
        long r1 = r10;
        if (r12 != (-9223372036854775807L)) goto L66;
        if (r14 == (-9223372036854775807L)) goto L61;
        r12 = r1 + r14;
        goto L66
    L61:
        if (r22 == null) goto L66;
        long r102 = r22.endTimeUs;
        if (r102 == (-9223372036854775807L)) goto L66;
        r12 = r102;
    L66:
        return TtmlNode.buildNode(r21.getName(), r1, r12, r5, r6, r7, r3, r22);
    }

    private static TtmlRegion parseRegionAttributes(XmlPullParser r17, int r18, TtsExtent r19, Map<String, TtmlStyle> r20) {
        String r5 = XmlPullParserUtil.getAttributeValue(r17, "id");
        if (r5 != null) goto L5;
        return null;
    L5:
        String r4 = XmlPullParserUtil.getAttributeValue(r17, "origin");
        if (r4 != null) goto L12;
        String r7 = XmlPullParserUtil.getAttributeValue(r17, "style");
        if (r7 == null) goto L12;
        TtmlStyle r72 = r20.get(r7);
        if (r72 == null) goto L12;
        r4 = r72.getOrigin();
    L12:
        int r9 = 2;
        if (r4 == null) goto L31;
        Matcher r12 = PERCENTAGE_COORDINATES.matcher(r4);
        Matcher r13 = PIXEL_COORDINATES.matcher(r4);
        if (r12.matches() == false) goto L21;
        float r132 = Float.parseFloat((String) Assertions.checkNotNull(r12.group(1))) / 100.0f;     // Catch: NumberFormatException -> L18
        float r122 = Float.parseFloat((String) Assertions.checkNotNull(r12.group(2))) / 100.0f;
    L32:
        String r14 = XmlPullParserUtil.getAttributeValue(r17, "extent");
        if (r14 != null) goto L39;
        String r6 = XmlPullParserUtil.getAttributeValue(r17, "style");
        if (r6 == null) goto L39;
        TtmlStyle r2 = r20.get(r6);
        if (r2 == null) goto L39;
        r14 = r2.getExtent();
    L39:
        if (r14 == null) goto L58;
        Matcher r62 = PERCENTAGE_COORDINATES.matcher(r14);
        Matcher r142 = PIXEL_COORDINATES.matcher(r14);
        float r202 = 1.0f;
        if (r62.matches() == false) goto L47;
        float r1 = Float.parseFloat((String) Assertions.checkNotNull(r62.group(1))) / 100.0f;     // Catch: NumberFormatException -> L44
        float r22 = Float.parseFloat((String) Assertions.checkNotNull(r62.group(2))) / 100.0f;
    L53:
        float r11 = r22;
    L59:
        String r23 = XmlPullParserUtil.getAttributeValue(r17, "displayAlign");
        int r3 = 0;
        if (r23 == null) goto L69;
        String r24 = Ascii.toLowerCase(r23);
        r24.getClass();
        if (r24.equals("center") == false) goto L64;
        float r123 = r122 + (r11 / 2.0f);
        int r25 = r18;
        int r42 = 2;
        r9 = 1;
    L67:
        float r73 = r123;
    L70:
        float r26 = r202 / r25;
        String r0 = XmlPullParserUtil.getAttributeValue(r17, "writingMode");
        if (r0 == null) goto L90;
        String r02 = Ascii.toLowerCase(r0);
        r02.getClass();
        switch(r02.hashCode()) {
            case 3694: goto L84;
            case 3553396: goto L80;
            case 3553576: goto L76;
            default: goto L74;
        };
    L74:
        r3 = -1;
    L86:
        switch(r3) {
            case 0: goto L89;
            case 1: goto L89;
            case 2: goto L88;
            default: goto L90;
        };
    L88:
        int r143 = 1;
    L92:
        return new TtmlRegion(r5, r132, r73, 0, r9, r1, r11, 1, r26, r143);
    L89:
        r143 = r42;
        goto L92
    L76:
        if (r02.equals("tbrl") == false) goto L74;
        r3 = r42;
        goto L86
    L80:
        if (r02.equals("tblr") == false) goto L74;
        r3 = 1;
        goto L86
    L84:
        if (r02.equals("tb") == true) goto L86;
    L90:
        r143 = Integer.MIN_VALUE;
        goto L92
    L64:
        if (r24.equals("after") == false) goto L69;
        r123 = r122 + r11;
        r25 = r18;
        r42 = 2;
    L69:
        r25 = r18;
        r42 = 2;
        r73 = r122;
        r9 = 0;
    L44:
        Log.w("TtmlParser", "Ignoring region with malformed extent: " + r4);
        return null;
    L47:
        if (r142.matches() == false) goto L56;
        if (r19 != null) goto L95;
        Log.w("TtmlParser", "Ignoring region with missing tts:extent: " + r4);
        return null;
    L95:
        int r63 = Integer.parseInt((String) Assertions.checkNotNull(r142.group(1)));     // Catch: NumberFormatException -> L54
        float r64 = r63 / r19.width;     // Catch: NumberFormatException -> L54
        r22 = Integer.parseInt((String) Assertions.checkNotNull(r142.group(2))) / r19.height;
        r1 = r64;
    L54:
        Log.w("TtmlParser", "Ignoring region with malformed extent: " + r4);
        return null;
    L56:
        Log.w("TtmlParser", "Ignoring region with unsupported extent: " + r4);
        return null;
    L58:
        r202 = 1.0f;
        r1 = 1.0f;
        r11 = 1.0f;
    L18:
        Log.w("TtmlParser", "Ignoring region with malformed origin: ".concat(r4));
        return null;
    L21:
        if (r13.matches() == false) goto L29;
        if (r19 != null) goto L97;
        Log.w("TtmlParser", "Ignoring region with missing tts:extent: ".concat(r4));
        return null;
    L97:
        int r124 = Integer.parseInt((String) Assertions.checkNotNull(r13.group(1)));     // Catch: NumberFormatException -> L27
        float r125 = r124 / r19.width;     // Catch: NumberFormatException -> L27
        float r133 = Integer.parseInt((String) Assertions.checkNotNull(r13.group(2))) / r19.height;
        r132 = r125;
        r122 = r133;
    L27:
        Log.w("TtmlParser", "Ignoring region with malformed origin: ".concat(r4));
        return null;
    L29:
        Log.w("TtmlParser", "Ignoring region with unsupported origin: ".concat(r4));
        return null;
    L31:
        r122 = 0.0f;
        r132 = 0.0f;
        goto L32
    }

    private static float parseShear(String r5) {
        Matcher r0 = SIGNED_PERCENTAGE.matcher(r5);
        if (r0.matches() == true) goto L12;
        Log.w("TtmlParser", "Invalid value for shear: " + r5);
        return Float.MAX_VALUE;
    L12:
        return Math.min(100.0f, Math.max(-100.0f, Float.parseFloat((String) Assertions.checkNotNull(r0.group(1)))));
    L9:
        e = move-exception;
        Log.w("TtmlParser", "Failed to parse shear: " + r5, e);
        return Float.MAX_VALUE;
    }

    private static TtmlStyle parseStyleAttributes(XmlPullParser r12, TtmlStyle r13) {
        int r0 = r12.getAttributeCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L173;
        String r3 = r12.getAttributeValue(r2);
        String r4 = r12.getAttributeName(r2);
        r4.getClass();
        char r6 = 5;
        char r8 = 65535;
        switch(r4.hashCode()) {
            case -1550943582: goto L72;
            case -1289044182: goto L68;
            case -1224696685: goto L64;
            case -1065511464: goto L60;
            case -1008619738: goto L56;
            case -879295043: goto L52;
            case -734428249: goto L48;
            case 3355: goto L44;
            case 3511770: goto L40;
            case 94842723: goto L36;
            case 109403361: goto L32;
            case 110138194: goto L28;
            case 365601008: goto L24;
            case 921125321: goto L20;
            case 1115953443: goto L16;
            case 1287124693: goto L12;
            case 1754920356: goto L8;
            default: goto L6;
        };
    L6:
        char r42 = 65535;
    L76:
        switch(r42) {
            case 0: goto L171;
            case 1: goto L170;
            case 2: goto L169;
            case 3: goto L168;
            case 4: goto L167;
            case 5: goto L142;
            case 6: goto L141;
            case 7: goto L139;
            case 8: goto L106;
            case 9: goto L102;
            case 10: goto L101;
            case 11: goto L94;
            case 12: goto L174;
            case 13: goto L90;
            case 14: goto L83;
            case 15: goto L79;
            case 16: goto L78;
            default: goto L172;
        };
    L78:
        r13 = createIfNull(r13).setMultiRowAlign(parseAlignment(r3));
        goto L172
    L79:
        r13 = createIfNull(r13);
        r13.setBackgroundColor(ColorParser.parseTtmlColor(r3));     // Catch: IllegalArgumentException -> L82
    L82:
        Log.w("TtmlParser", "Failed parsing background value: " + r3);
        goto L172
    L83:
        String r32 = Ascii.toLowerCase(r3);
        r32.getClass();
        if (r32.equals("before") == false) goto L86;
        r13 = createIfNull(r13).setRubyPosition(1);
        goto L172
    L86:
        if (r32.equals("after") == false) goto L172;
        r13 = createIfNull(r13).setRubyPosition(2);
        goto L172
    L90:
        r13 = createIfNull(r13).setTextEmphasis(TextEmphasis.parse(r3));
        goto L172
    L94:
        String r33 = Ascii.toLowerCase(r3);
        r33.getClass();
        if (r33.equals("all") == false) goto L97;
        r13 = createIfNull(r13).setTextCombine(true);
        goto L172
    L97:
        if (r33.equals("none") == false) goto L172;
        r13 = createIfNull(r13).setTextCombine(false);
        goto L172
    L101:
        r13 = createIfNull(r13).setShearPercentage(parseShear(r3));
        goto L172
    L102:
        r13 = createIfNull(r13);
        r13.setFontColor(ColorParser.parseTtmlColor(r3));     // Catch: IllegalArgumentException -> L105
    L105:
        Log.w("TtmlParser", "Failed parsing color value: " + r3);
        goto L172
    L106:
        String r34 = Ascii.toLowerCase(r3);
        r34.getClass();
        switch(r34.hashCode()) {
            case -618561360: goto L129;
            case -410956671: goto L125;
            case -250518009: goto L121;
            case -136074796: goto L117;
            case 3016401: goto L113;
            case 3556653: goto L110;
            default: goto L108;
        };
    L108:
        r6 = 65535;
    L132:
        switch(r6) {
            case 0: goto L137;
            case 1: goto L136;
            case 2: goto L135;
            case 3: goto L134;
            case 4: goto L137;
            case 5: goto L134;
            default: goto L172;
        };
    L134:
        r13 = createIfNull(r13).setRubyType(3);
        goto L172
    L135:
        r13 = createIfNull(r13).setRubyType(4);
        goto L172
    L136:
        r13 = createIfNull(r13).setRubyType(1);
        goto L172
    L137:
        r13 = createIfNull(r13).setRubyType(2);
        goto L172
    L110:
        if (r34.equals("text") == true) goto L132;
    L113:
        if (r34.equals("base") == false) goto L108;
        r6 = 4;
        goto L132
    L117:
        if (r34.equals("textContainer") == false) goto L108;
        r6 = 3;
        goto L132
    L121:
        if (r34.equals("delimiter") == false) goto L108;
        r6 = 2;
        goto L132
    L125:
        if (r34.equals("container") == false) goto L108;
        r6 = 1;
        goto L132
    L129:
        if (r34.equals("baseContainer") == false) goto L108;
        r6 = 0;
        goto L132
    L141:
        r13 = createIfNull(r13).setBold("bold".equalsIgnoreCase(r3));
        goto L172
    L142:
        String r35 = Ascii.toLowerCase(r3);
        r35.getClass();
        switch(r35.hashCode()) {
            case -1461280213: goto L158;
            case -1026963764: goto L154;
            case 913457136: goto L150;
            case 1679736913: goto L146;
            default: goto L161;
        };
    L161:
        switch(r8) {
            case 0: goto L166;
            case 1: goto L165;
            case 2: goto L164;
            case 3: goto L163;
            default: goto L172;
        };
    L163:
        r13 = createIfNull(r13).setLinethrough(true);
        goto L172
    L164:
        r13 = createIfNull(r13).setLinethrough(false);
        goto L172
    L165:
        r13 = createIfNull(r13).setUnderline(true);
        goto L172
    L166:
        r13 = createIfNull(r13).setUnderline(false);
        goto L172
    L146:
        if (r35.equals("linethrough") == false) goto L161;
        r8 = 3;
        goto L161
    L150:
        if (r35.equals("nolinethrough") == false) goto L161;
        r8 = 2;
        goto L161
    L154:
        if (r35.equals("underline") == false) goto L161;
        r8 = 1;
        goto L161
    L158:
        if (r35.equals("nounderline") == false) goto L161;
        r8 = 0;
        goto L161
    L167:
        r13 = createIfNull(r13).setOrigin(r3);
        goto L172
    L168:
        r13 = createIfNull(r13).setTextAlign(parseAlignment(r3));
        goto L172
    L169:
        r13 = createIfNull(r13).setFontFamily(r3);
        goto L172
    L170:
        r13 = createIfNull(r13).setExtent(r3);
        goto L172
    L171:
        r13 = createIfNull(r13).setItalic("italic".equalsIgnoreCase(r3));
        goto L172
    L174:
        r13 = createIfNull(r13);     // Catch: SubtitleDecoderException -> L93
        parseFontSize(r3, r13);     // Catch: SubtitleDecoderException -> L93
    L93:
        Log.w("TtmlParser", "Failed parsing fontSize value: " + r3);
        goto L172
    L139:
        if ("style".equals(r12.getName()) == false) goto L172;
        r13 = createIfNull(r13).setId(r3);
    L172:
        r2 = r2 + 1;
        goto L3
    L8:
        if (r4.equals("multiRowAlign") == false) goto L6;
        r42 = 16;
        goto L76
    L12:
        if (r4.equals("backgroundColor") == false) goto L6;
        r42 = 15;
        goto L76
    L16:
        if (r4.equals("rubyPosition") == false) goto L6;
        r42 = 14;
        goto L76
    L20:
        if (r4.equals("textEmphasis") == false) goto L6;
        r42 = '\r';
        goto L76
    L24:
        if (r4.equals("fontSize") == false) goto L6;
        r42 = '\f';
        goto L76
    L28:
        if (r4.equals("textCombine") == false) goto L6;
        r42 = 11;
        goto L76
    L32:
        if (r4.equals("shear") == false) goto L6;
        r42 = '\n';
        goto L76
    L36:
        if (r4.equals("color") == false) goto L6;
        r42 = '\t';
        goto L76
    L40:
        if (r4.equals("ruby") == false) goto L6;
        r42 = '\b';
        goto L76
    L44:
        if (r4.equals("id") == false) goto L6;
        r42 = 7;
        goto L76
    L48:
        if (r4.equals("fontWeight") == false) goto L6;
        r42 = 6;
        goto L76
    L52:
        if (r4.equals("textDecoration") == false) goto L6;
        r42 = 5;
        goto L76
    L56:
        if (r4.equals("origin") == false) goto L6;
        r42 = 4;
        goto L76
    L60:
        if (r4.equals("textAlign") == false) goto L6;
        r42 = 3;
        goto L76
    L64:
        if (r4.equals("fontFamily") == false) goto L6;
        r42 = 2;
        goto L76
    L68:
        if (r4.equals("extent") == false) goto L6;
        r42 = 1;
        goto L76
    L72:
        if (r4.equals("fontStyle") == false) goto L6;
        r42 = 0;
        goto L76
    L173:
        return r13;
    }

    private static String[] parseStyleIds(String r1) {
        String r12 = r1.trim();
        if (r12.isEmpty() == false) goto L7;
        return new String[0];
    L7:
        return Util.split(r12, "\\s+");
    }

    private static long parseTimeExpression(String r13, FrameAndTickRate r14) {
        Matcher r0 = CLOCK_TIME.matcher(r13);
        char r2 = 4;
        if (r0.matches() == false) goto L17;
        double r7 = ((Long.parseLong((String) Assertions.checkNotNull(r0.group(1))) * 3600) + (Long.parseLong((String) Assertions.checkNotNull(r0.group(2))) * 60)) + Long.parseLong((String) Assertions.checkNotNull(r0.group(3)));
        String r132 = r0.group(4);
        double r1 = 0.0d;
        if (r132 == null) goto L7;
        double r9 = Double.parseDouble(r132);
    L8:
        double r72 = r7 + r9;
        if (r0.group(5) == null) goto L11;
        double r92 = Long.parseLong(r13) / r14.effectiveFrameRate;
    L12:
        double r73 = r72 + r92;
        if (r0.group(6) == null) goto L16;
        r1 = (Long.parseLong(r13) / r14.subFrameRate) / r14.effectiveFrameRate;
    L16:
        return (long) ((r73 + r1) * 1000000.0d);
    L11:
        r92 = 0.0d;
        goto L12
    L7:
        r9 = 0.0d;
        goto L8
    L17:
        Matcher r02 = OFFSET_TIME.matcher(r13);
        if (r02.matches() == false) goto L53;
        double r8 = Double.parseDouble((String) Assertions.checkNotNull(r02.group(1)));
        String r133 = (String) Assertions.checkNotNull(r02.group(2));
        r133.getClass();
        switch(r133.hashCode()) {
            case 102: goto L38;
            case 104: goto L34;
            case 109: goto L30;
            case 116: goto L26;
            case 3494: goto L23;
            default: goto L21;
        };
    L21:
        r2 = 65535;
    L41:
        switch(r2) {
            case 0: goto L49;
            case 1: goto L48;
            case 2: goto L46;
            case 3: goto L45;
            case 4: goto L43;
            default: goto L51;
        };
    L43:
        double r134 = 1000.0d;
    L44:
        r8 = r8 / r134;
        goto L51
    L45:
        r134 = r14.tickRate;
        goto L44
    L46:
        double r135 = 60.0d;
    L47:
        r8 = r8 * r135;
        goto L51
    L48:
        r135 = 3600.0d;
        goto L47
    L49:
        r134 = r14.effectiveFrameRate;
    L51:
        return (long) (r8 * 1000000.0d);
    L23:
        if (r133.equals("ms") == true) goto L41;
    L26:
        if (r133.equals("t") == false) goto L21;
        r2 = 3;
        goto L41
    L30:
        if (r133.equals("m") == false) goto L21;
        r2 = 2;
        goto L41
    L34:
        if (r133.equals("h") == false) goto L21;
        r2 = 1;
        goto L41
    L38:
        if (r133.equals("f") == false) goto L21;
        r2 = 0;
        goto L41
    L53:
        throw new SubtitleDecoderException("Malformed time expression: " + r13);
    }

    private static TtsExtent parseTtsExtent(XmlPullParser r5) {
        String r52 = XmlPullParserUtil.getAttributeValue(r5, "extent");
        if (r52 != null) goto L5;
        return null;
    L5:
        Matcher r1 = PIXEL_COORDINATES.matcher(r52);
        if (r1.matches() == true) goto L14;
        Log.w("TtmlParser", "Ignoring non-pixel tts extent: ".concat(r52));
        return null;
    L14:
        return new TtsExtent(Integer.parseInt((String) Assertions.checkNotNull(r1.group(1))), Integer.parseInt((String) Assertions.checkNotNull(r1.group(2))));
    L12:
        Log.w("TtmlParser", "Ignoring malformed tts extent: ".concat(r52));
        return null;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public int getCueReplacementBehavior() {
        return 1;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public void parse(byte[] r1, int r2, int r3, SubtitleParser.OutputOptions r4, Consumer<CuesWithTiming> r5) {
        LegacySubtitleUtil.toCuesWithTiming(parseToLegacySubtitle(r1, r2, r3), r4, r5);
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public Subtitle parseToLegacySubtitle(byte[] r19, int r20, int r21) {
        Subtitle r1 = null;
        XmlPullParser r3 = this.xmlParserFactory.newPullParser();     // Catch: IOException -> L53 XmlPullParserException -> L55
        HashMap r4 = new HashMap();     // Catch: IOException -> L53 XmlPullParserException -> L55
        HashMap r7 = new HashMap();     // Catch: IOException -> L53 XmlPullParserException -> L55
        HashMap r8 = new HashMap();     // Catch: IOException -> L53 XmlPullParserException -> L55
        r7.put(_UrlKt.FRAGMENT_ENCODE_SET, new TtmlRegion(_UrlKt.FRAGMENT_ENCODE_SET));     // Catch: IOException -> L53 XmlPullParserException -> L55
        r3.setInput(new ByteArrayInputStream(r19, r20, r21), null);     // Catch: IOException -> L53 XmlPullParserException -> L55
        ArrayDeque r2 = new ArrayDeque();     // Catch: IOException -> L53 XmlPullParserException -> L55
        int r0 = r3.getEventType();     // Catch: IOException -> L53 XmlPullParserException -> L55
        FrameAndTickRate r5 = DEFAULT_FRAME_AND_TICK_RATE;     // Catch: IOException -> L53 XmlPullParserException -> L55
        TtmlSubtitle r11 = null;
        TtsExtent r12 = null;
        int r10 = 0;
        int r6 = 15;
    L5:
        if (r0 == 1) goto L57;
        TtmlNode r13 = (TtmlNode) r2.peek();     // Catch: IOException -> L53 XmlPullParserException -> L55
    L8:
        if (r10 != 0) goto L46;
        Subtitle r16 = r1;
        String r14 = r3.getName();     // Catch: IOException -> L17 XmlPullParserException -> L19
        if (r0 != 2) goto L38;
        if ("tt".equals(r14) == false) goto L16;
        r5 = parseFrameAndTickRates(r3);     // Catch: IOException -> L17 XmlPullParserException -> L19
        r6 = parseCellRows(r3, 15);     // Catch: IOException -> L17 XmlPullParserException -> L19
        r12 = parseTtsExtent(r3);     // Catch: IOException -> L17 XmlPullParserException -> L19
    L16:
        TtsExtent r17 = r12;
        FrameAndTickRate r122 = r5;
        int r52 = r6;
        if (isSupportedTag(r14) == true) goto L28;
        Log.i("TtmlParser", "Ignoring unsupported tag: " + r3.getName());     // Catch: IOException -> L17 XmlPullParserException -> L19
    L25:
        r10 = r10 + 1;     // Catch: IOException -> L17 XmlPullParserException -> L19
    L26:
        r6 = r52;
        r5 = r122;
        r12 = r17;
    L52:
        r3.next();     // Catch: IOException -> L17 XmlPullParserException -> L19
        r0 = r3.getEventType();     // Catch: IOException -> L17 XmlPullParserException -> L19
        r1 = r16;
        goto L5
    L28:
        if ("head".equals(r14) == false) goto L63;
        parseHeader(r3, r4, r52, r17, r7, r8);     // Catch: IOException -> L17 XmlPullParserException -> L19
        goto L26
    L63:
        TtmlNode r02 = parseNode(r3, r13, r7, r122);     // Catch: IOException -> L17 XmlPullParserException -> L19 SubtitleDecoderException -> L35
        r2.push(r02);     // Catch: IOException -> L17 XmlPullParserException -> L19 SubtitleDecoderException -> L35
        if (r13 == null) goto L26;
        r13.addChild(r02);     // Catch: IOException -> L17 XmlPullParserException -> L19 SubtitleDecoderException -> L35
        goto L26
    L35:
        e = move-exception;
        Log.w("TtmlParser", "Suppressing parser error", e);     // Catch: IOException -> L17 XmlPullParserException -> L19
        goto L25
    L38:
        if (r0 != 4) goto L41;
        ((TtmlNode) Assertions.checkNotNull(r13)).addChild(TtmlNode.buildTextNode(r3.getText()));     // Catch: IOException -> L17 XmlPullParserException -> L19
        goto L52
    L41:
        if (r0 != 3) goto L52;
        if (r3.getName().equals("tt") == false) goto L45;
        r11 = new TtmlSubtitle((TtmlNode) Assertions.checkNotNull((TtmlNode) r2.peek()), r4, r7, r8);     // Catch: IOException -> L17 XmlPullParserException -> L19
    L45:
        r2.pop();     // Catch: IOException -> L17 XmlPullParserException -> L19
    L17:
        e = e;
    L59:
        Constants$$ExternalSyntheticBUOutline0.m("Unexpected error when reading input.", e);
        return r16;
    L19:
        e = e;
    L61:
        Constants$$ExternalSyntheticBUOutline0.m("Unable to decode source", e);
        return r16;
    L46:
        r16 = r1;
        if (r0 != 2) goto L50;
        r10 = r10 + 1;     // Catch: IOException -> L17 XmlPullParserException -> L19
        goto L52
    L50:
        if (r0 != 3) goto L52;
        r10 = r10 - 1;
        goto L52
    L57:
        r16 = r1;
        return (Subtitle) Assertions.checkNotNull(r11);
    L53:
        e = e;
        r16 = r1;
    L55:
        e = e;
        r16 = r1;
        goto L61
    }
}
