package androidx.media3.extractor.text.webvtt;

import android.graphics.Color;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.HorizontalTextInVerticalContextSpan;
import androidx.media3.common.text.RubySpan;
import androidx.media3.common.text.SpanUtil;
import androidx.media3.common.text.VoiceSpan;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import com.android.dx.rop.code.RegisterSpec;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class WebvttCueParser {
    public static final Pattern CUE_HEADER_PATTERN = null;
    private static final Pattern CUE_SETTING_PATTERN = null;
    private static final Map<String, Integer> DEFAULT_BACKGROUND_COLORS = null;
    private static final Map<String, Integer> DEFAULT_TEXT_COLORS = null;

    /* renamed from: androidx.media3.extractor.text.webvtt.WebvttCueParser$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class Element {
        private static final Comparator<Element> BY_START_POSITION_ASC = null;
        private final int endPosition;
        private final StartTag startTag;

        /* renamed from: $r8$lambda$EzDOl4rWxkZXAh-V62baozGrdqo, reason: not valid java name */
        public static /* synthetic */ int m860$r8$lambda$EzDOl4rWxkZXAhV62baozGrdqo(Element r0, Element r1) {
            return Integer.compare(r0.startTag.position, r1.startTag.position);
        }

        static {
            BY_START_POSITION_ASC = new WebvttCueParser$Element$$ExternalSyntheticLambda0();
        }

        private Element(StartTag r1, int r2) {
            this.startTag = r1;
            this.endPosition = r2;
        }

        public /* synthetic */ Element(StartTag r1, int r2, AnonymousClass1 r3) {
            this(r1, r2);
        }

        public static /* synthetic */ Comparator access$100() {
            return BY_START_POSITION_ASC;
        }

        public static /* synthetic */ StartTag access$200(Element r0) {
            return r0.startTag;
        }

        public static /* synthetic */ int access$300(Element r0) {
            return r0.endPosition;
        }
    }

    public static final class StartTag {
        public final Set<String> classes;
        public final String name;
        public final int position;
        public final String voice;

        private StartTag(String r1, int r2, String r3, Set<String> r4) {
            this.position = r2;
            this.name = r1;
            this.voice = r3;
            this.classes = r4;
        }

        public static StartTag buildStartTag(String r5, int r6) {
            String r52 = r5.trim();
            int r1 = 1;
            Assertions.checkArgument(!r52.isEmpty());
            int r0 = r52.indexOf(" ");
            if (r0 != (-1)) goto L5;
            String r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            String[] r53 = Util.split(r52, "\\.");
            String r2 = r53[0];
            HashSet r3 = new HashSet();
        L8:
            if (r1 >= r53.length) goto L11;
            r3.add(r53[r1]);
            r1 = r1 + 1;
            goto L8
        L11:
            return new StartTag(r2, r6, r02, r3);
        L5:
            String r22 = r52.substring(r0).trim();
            r52 = r52.substring(0, r0);
            r02 = r22;
            goto L6
        }

        public static StartTag buildWholeCueVirtualTag() {
            return new StartTag(_UrlKt.FRAGMENT_ENCODE_SET, 0, _UrlKt.FRAGMENT_ENCODE_SET, Collections.EMPTY_SET);
        }
    }

    public static final class StyleMatch implements Comparable<StyleMatch> {
        public final int score;
        public final WebvttCssStyle style;

        public StyleMatch(int r1, WebvttCssStyle r2) {
            this.score = r1;
            this.style = r2;
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(StyleMatch r1) {
            return Integer.compare(this.score, r1.score);
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(StyleMatch r1) {
            return compareTo2(r1);
        }
    }

    public static final class WebvttCueInfoBuilder {
        public long endTimeUs;
        public float line;
        public int lineAnchor;
        public int lineType;
        public float position;
        public int positionAnchor;
        public float size;
        public long startTimeUs;
        public CharSequence text;
        public int textAlignment;
        public int verticalType;

        public WebvttCueInfoBuilder() {
            this.startTimeUs = 0;
            this.endTimeUs = 0;
            this.textAlignment = 2;
            this.line = -3.4028235E38f;
            this.lineType = 1;
            this.lineAnchor = 0;
            this.position = -3.4028235E38f;
            this.positionAnchor = Integer.MIN_VALUE;
            this.size = 1.0f;
            this.verticalType = Integer.MIN_VALUE;
        }

        private static float computeLine(float r4, int r5) {
            if (r4 == (-3.4028235E38f)) goto L10;
            if (r5 != 0) goto L10;
            if (r4 >= 0.0f) goto L8;
        L9:
            return 1.0f;
        L8:
            if (r4 > 1.0f) goto L9;
        L10:
            if (r4 == (-3.4028235E38f)) goto L12;
            return r4;
        L12:
            if (r5 != 0) goto L14;
            return 1.0f;
        L14:
            return -3.4028235E38f;
        }

        private static Layout.Alignment convertTextAlignment(int r2) {
            if (r2 == 1) goto L19;
            if (r2 == 2) goto L17;
            if (r2 == 3) goto L15;
            if (r2 == 4) goto L19;
            if (r2 == 5) goto L15;
            Log.w("WebvttCueParser", "Unknown textAlignment: " + r2);
            return null;
        L15:
            return Layout.Alignment.ALIGN_OPPOSITE;
        L17:
            return Layout.Alignment.ALIGN_CENTER;
        L19:
            return Layout.Alignment.ALIGN_NORMAL;
        }

        private static float deriveMaxSize(int r2, float r3) {
            if (r2 == 0) goto L18;
            if (r2 == 1) goto L12;
            if (r2 != 2) goto L9;
            return r3;
        L9:
            Segment$$ExternalSyntheticBUOutline1.m(String.valueOf(r2));
            return 0.0f;
        L12:
            if (r3 > 0.5f) goto L16;
            return r3 * 2.0f;
        L16:
            return (1.0f - r3) * 2.0f;
        L18:
            return 1.0f - r3;
        }

        private static float derivePosition(int r1) {
            if (r1 != 4) goto L5;
            return 0.0f;
        L5:
            if (r1 == 5) goto L8;
            return 0.5f;
        L8:
            return 1.0f;
        }

        private static int derivePositionAnchor(int r2) {
            if (r2 != 1) goto L5;
            return 0;
        L5:
            if (r2 != 3) goto L7;
            return 2;
        L7:
            if (r2 != 4) goto L9;
            return 0;
        L9:
            if (r2 == 5) goto L15;
            return 1;
        L15:
            return 2;
        }

        public WebvttCueInfo build() {
            return new WebvttCueInfo(toCueBuilder().build(), this.startTimeUs, this.endTimeUs);
        }

        public Cue.Builder toCueBuilder() {
            float r0 = this.position;
            if (r0 != (-3.4028235E38f)) goto L6;
            r0 = derivePosition(this.textAlignment);
        L6:
            int r1 = this.positionAnchor;
            if (r1 != Integer.MIN_VALUE) goto L10;
            r1 = derivePositionAnchor(this.textAlignment);
        L10:
            Cue.Builder r02 = new Cue.Builder().setTextAlignment(convertTextAlignment(this.textAlignment)).setLine(computeLine(this.line, this.lineType), this.lineType).setLineAnchor(this.lineAnchor).setPosition(r0).setPositionAnchor(r1).setSize(Math.min(this.size, deriveMaxSize(r1, r0))).setVerticalType(this.verticalType);
            CharSequence r5 = this.text;
            if (r5 == null) goto L13;
            r02.setText(r5);
        L13:
            return r02;
        }
    }

    static {
        CUE_HEADER_PATTERN = Pattern.compile("^(\\S+)\\s+-->\\s+(\\S+)((?:.|\\f)*)?$");
        CUE_SETTING_PATTERN = Pattern.compile("(\\S+?):(\\S+)");
        HashMap r0 = new HashMap();
        r0.put("white", Integer.valueOf(Color.rgb(255, 255, 255)));
        r0.put("lime", Integer.valueOf(Color.rgb(0, 255, 0)));
        r0.put("cyan", Integer.valueOf(Color.rgb(0, 255, 255)));
        r0.put(PluginsConstants.Settings.RED, Integer.valueOf(Color.rgb(255, 0, 0)));
        r0.put("yellow", Integer.valueOf(Color.rgb(255, 255, 0)));
        r0.put("magenta", Integer.valueOf(Color.rgb(255, 0, 255)));
        r0.put("blue", Integer.valueOf(Color.rgb(0, 0, 255)));
        r0.put("black", Integer.valueOf(Color.rgb(0, 0, 0)));
        DEFAULT_TEXT_COLORS = Collections.unmodifiableMap(r0);
        HashMap r02 = new HashMap();
        r02.put("bg_white", Integer.valueOf(Color.rgb(255, 255, 255)));
        r02.put("bg_lime", Integer.valueOf(Color.rgb(0, 255, 0)));
        r02.put("bg_cyan", Integer.valueOf(Color.rgb(0, 255, 255)));
        r02.put("bg_red", Integer.valueOf(Color.rgb(255, 0, 0)));
        r02.put("bg_yellow", Integer.valueOf(Color.rgb(255, 255, 0)));
        r02.put("bg_magenta", Integer.valueOf(Color.rgb(255, 0, 255)));
        r02.put("bg_blue", Integer.valueOf(Color.rgb(0, 0, 255)));
        r02.put("bg_black", Integer.valueOf(Color.rgb(0, 0, 0)));
        DEFAULT_BACKGROUND_COLORS = Collections.unmodifiableMap(r02);
    }

    private static void applyDefaultColors(SpannableStringBuilder r4, Set<String> r5, int r6, int r7) {
        Iterator<String> r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L11;
        String r0 = r52.next();
        Map<String, Integer> r1 = DEFAULT_TEXT_COLORS;
        if (r1.containsKey(r0) == true) goto L7;
        Map<String, Integer> r12 = DEFAULT_BACKGROUND_COLORS;
        if (r12.containsKey(r0) == false) goto L4;
        r4.setSpan(new BackgroundColorSpan(r12.get(r0).intValue()), r6, r7, 33);
        goto L4
    L7:
        r4.setSpan(new ForegroundColorSpan(r1.get(r0).intValue()), r6, r7, 33);
        goto L4
    }

    private static void applyEntity(String r2, SpannableStringBuilder r3) {
        r2.getClass();
        char r1 = 65535;
        switch(r2.hashCode()) {
            case 3309: goto L18;
            case 3464: goto L14;
            case 96708: goto L10;
            case 3374865: goto L6;
            default: goto L21;
        };
    L21:
        switch(r1) {
            case 0: goto L30;
            case 1: goto L28;
            case 2: goto L26;
            case 3: goto L24;
            default: goto L22;
        };
    L22:
        Log.w("WebvttCueParser", "ignoring unsupported entity: '&" + r2 + ";'");
        return;
    L24:
        r3.append(' ');
        return;
    L26:
        r3.append(Typography.amp);
        return;
    L28:
        r3.append(Typography.less);
        return;
    L30:
        r3.append(Typography.greater);
        return;
    L6:
        if (r2.equals("nbsp") == false) goto L21;
        r1 = 3;
        goto L21
    L10:
        if (r2.equals("amp") == false) goto L21;
        r1 = 2;
        goto L21
    L14:
        if (r2.equals("lt") == false) goto L21;
        r1 = 1;
        goto L21
    L18:
        if (r2.equals("gt") == false) goto L21;
        r1 = 0;
        goto L21
    }

    private static void applyRubySpans(SpannableStringBuilder r8, String r9, StartTag r10, List<Element> r11, List<WebvttCssStyle> r12) {
        int r0 = getRubyPosition(r12, r9, r10);
        ArrayList r1 = new ArrayList(r11.size());
        r1.addAll(r11);
        Collections.sort(r1, Element.access$100());
        int r102 = r10.position;
        int r112 = 0;
        int r2 = 0;
    L4:
        if (r112 >= r1.size()) goto L10;
        if ("rt".equals(Element.access$200((Element) r1.get(r112)).name) == false) goto L9;
        Element r3 = (Element) r1.get(r112);
        int r4 = firstKnownRubyPosition(getRubyPosition(r12, r9, Element.access$200(r3)), r0, 1);
        int r5 = Element.access$200(r3).position - r2;
        int r32 = Element.access$300(r3) - r2;
        CharSequence r6 = r8.subSequence(r5, r32);
        r8.delete(r5, r32);
        r8.setSpan(new RubySpan(r6.toString(), r4), r102, r5, 33);
        r2 = r2 + r6.length();
        r102 = r5;
    L9:
        r112 = r112 + 1;
        goto L4
    }

    private static void applySpansForTag(String r8, StartTag r9, List<Element> r10, SpannableStringBuilder r11, List<WebvttCssStyle> r12) {
        int r0 = r9.position;
        int r1 = r11.length();
        String r2 = r9.name;
        r2.getClass();
        int r5 = 0;
        char r7 = 65535;
        switch(r2.hashCode()) {
            case 0: goto L34;
            case 98: goto L30;
            case 99: goto L26;
            case 105: goto L22;
            case 117: goto L18;
            case 118: goto L14;
            case 3314158: goto L10;
            case 3511770: goto L6;
            default: goto L38;
        };
    L6:
        if (r2.equals("ruby") == false) goto L38;
        r7 = 7;
        goto L38
    L10:
        if (r2.equals("lang") == false) goto L38;
        r7 = 6;
        goto L38
    L14:
        if (r2.equals(RegisterSpec.PREFIX) == false) goto L38;
        r7 = 5;
        goto L38
    L18:
        if (r2.equals("u") == false) goto L38;
        r7 = 4;
        goto L38
    L22:
        if (r2.equals("i") == false) goto L38;
        r7 = 3;
        goto L38
    L26:
        if (r2.equals("c") == false) goto L38;
        r7 = 2;
        goto L38
    L30:
        if (r2.equals("b") == false) goto L38;
        r7 = 1;
        goto L38
    L34:
        if (r2.equals(_UrlKt.FRAGMENT_ENCODE_SET) == false) goto L38;
        r7 = 0;
    L38:
        switch(r7) {
            case 0: goto L46;
            case 1: goto L45;
            case 2: goto L44;
            case 3: goto L43;
            case 4: goto L42;
            case 5: goto L41;
            case 6: goto L46;
            case 7: goto L40;
            default: goto L50;
        };
    L40:
        applyRubySpans(r11, r8, r9, r10, r12);
        goto L46
    L41:
        applyVoiceSpan(r11, r9.voice, r0, r1);
        goto L46
    L42:
        r11.setSpan(new UnderlineSpan(), r0, r1, 33);
        goto L46
    L43:
        r11.setSpan(new StyleSpan(2), r0, r1, 33);
        goto L46
    L44:
        applyDefaultColors(r11, r9.classes, r0, r1);
        goto L46
    L45:
        r11.setSpan(new StyleSpan(1), r0, r1, 33);
    L46:
        List<StyleMatch> r82 = getApplicableStyles(r12, r8, r9);
    L48:
        if (r5 >= r82.size()) goto L52;
        applyStyleToText(r11, r82.get(r5).style, r0, r1);
        r5 = r5 + 1;
        goto L48
    L52:
        return;
    }

    private static void applyStyleToText(SpannableStringBuilder r4, WebvttCssStyle r5, int r6, int r7) {
        if (r5 != null) goto L5;
        return;
    L5:
        if (r5.getStyle() == (-1)) goto L8;
        SpanUtil.addOrReplaceSpan(r4, new StyleSpan(r5.getStyle()), r6, r7, 33);
    L8:
        if (r5.isLinethrough() == false) goto L11;
        r4.setSpan(new StrikethroughSpan(), r6, r7, 33);
    L11:
        if (r5.isUnderline() == false) goto L14;
        r4.setSpan(new UnderlineSpan(), r6, r7, 33);
    L14:
        if (r5.hasFontColor() == false) goto L17;
        SpanUtil.addOrReplaceSpan(r4, new ForegroundColorSpan(r5.getFontColor()), r6, r7, 33);
    L17:
        if (r5.hasBackgroundColor() == false) goto L20;
        SpanUtil.addOrReplaceSpan(r4, new BackgroundColorSpan(r5.getBackgroundColor()), r6, r7, 33);
    L20:
        if (r5.getFontFamily() == null) goto L22;
        SpanUtil.addOrReplaceSpan(r4, new TypefaceSpan(r5.getFontFamily()), r6, r7, 33);
    L22:
        int r0 = r5.getFontSizeUnit();
        if (r0 != 1) goto L25;
        SpanUtil.addOrReplaceSpan(r4, new AbsoluteSizeSpan((int) r5.getFontSize(), true), r6, r7, 33);
    L33:
        if (r5.getCombineUpright() == false) goto L36;
        r4.setSpan(new HorizontalTextInVerticalContextSpan(), r6, r7, 33);
        return;
    L36:
        return;
    L25:
        if (r0 != 2) goto L27;
        SpanUtil.addOrReplaceSpan(r4, new RelativeSizeSpan(r5.getFontSize()), r6, r7, 33);
        goto L33
    L27:
        if (r0 != 3) goto L33;
        SpanUtil.addOrReplaceSpan(r4, new RelativeSizeSpan(r5.getFontSize() / 100.0f), r6, r7, 33);
        goto L33
    }

    private static void applyVoiceSpan(SpannableStringBuilder r1, String r2, int r3, int r4) {
        r1.setSpan(new VoiceSpan(r2), r3, r4, 33);
    }

    private static int findEndOfTag(String r1, int r2) {
        int r22 = r1.indexOf(62, r2);
        if (r22 != (-1)) goto L7;
        return r1.length();
    L7:
        return r22 + 1;
    }

    private static int firstKnownRubyPosition(int r1, int r2, int r3) {
        if (r1 == (-1)) goto L5;
        return r1;
    L5:
        if (r2 == (-1)) goto L7;
        return r2;
    L7:
        if (r3 == (-1)) goto L9;
        return r3;
    L9:
        Segment$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    private static List<StyleMatch> getApplicableStyles(List<WebvttCssStyle> r6, String r7, StartTag r8) {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r6.size()) goto L9;
        WebvttCssStyle r2 = r6.get(r1);
        int r3 = r2.getSpecificityScore(r7, r8.name, r8.classes, r8.voice);
        if (r3 <= 0) goto L8;
        r0.add(new StyleMatch(r3, r2));
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        Collections.sort(r0);
        return r0;
    }

    private static int getRubyPosition(List<WebvttCssStyle> r2, String r3, StartTag r4) {
        List<StyleMatch> r22 = getApplicableStyles(r2, r3, r4);
        int r32 = 0;
    L4:
        if (r32 >= r22.size()) goto L10;
        WebvttCssStyle r42 = r22.get(r32).style;
        if (r42.getRubyPosition() != (-1)) goto L8;
        r32 = r32 + 1;
        goto L4
    L8:
        return r42.getRubyPosition();
    L10:
        return -1;
    }

    private static String getTagName(String r1) {
        String r12 = r1.trim();
        Assertions.checkArgument(!r12.isEmpty());
        return Util.splitAtFirst(r12, "[ \\.]")[0];
    }

    private static boolean isSupportedTag(String r4) {
        r4.getClass();
        char r3 = 65535;
        switch(r4.hashCode()) {
            case 98: goto L34;
            case 99: goto L30;
            case 105: goto L26;
            case 117: goto L22;
            case 118: goto L18;
            case 3650: goto L14;
            case 3314158: goto L10;
            case 3511770: goto L6;
            default: goto L37;
        };
    L37:
        switch(r3) {
            case 0: goto L39;
            case 1: goto L39;
            case 2: goto L39;
            case 3: goto L39;
            case 4: goto L39;
            case 5: goto L39;
            case 6: goto L39;
            case 7: goto L39;
            default: goto L38;
        };
    L38:
        return false;
    L39:
        return true;
    L6:
        if (r4.equals("ruby") == false) goto L37;
        r3 = 7;
        goto L37
    L10:
        if (r4.equals("lang") == false) goto L37;
        r3 = 6;
        goto L37
    L14:
        if (r4.equals("rt") == false) goto L37;
        r3 = 5;
        goto L37
    L18:
        if (r4.equals(RegisterSpec.PREFIX) == false) goto L37;
        r3 = 4;
        goto L37
    L22:
        if (r4.equals("u") == false) goto L37;
        r3 = 3;
        goto L37
    L26:
        if (r4.equals("i") == false) goto L37;
        r3 = 2;
        goto L37
    L30:
        if (r4.equals("c") == false) goto L37;
        r3 = 1;
        goto L37
    L34:
        if (r4.equals("b") == false) goto L37;
        r3 = 0;
        goto L37
    }

    public static Cue newCueForText(CharSequence r1) {
        WebvttCueInfoBuilder r0 = new WebvttCueInfoBuilder();
        r0.text = r1;
        return r0.toCueBuilder().build();
    }

    public static WebvttCueInfo parseCue(ParsableByteArray r5, List<WebvttCssStyle> r6) {
        String r0 = r5.readLine();
        if (r0 != null) goto L5;
        return null;
    L5:
        Pattern r2 = CUE_HEADER_PATTERN;
        Matcher r3 = r2.matcher(r0);
        if (r3.matches() == true) goto L8;
        String r32 = r5.readLine();
        if (r32 != null) goto L12;
        return null;
    L12:
        Matcher r22 = r2.matcher(r32);
        if (r22.matches() == true) goto L15;
        return null;
    L15:
        return parseCue(r0.trim(), r22, r5, r6);
    L8:
        return parseCue(null, r3, r5, r6);
    }

    private static WebvttCueInfo parseCue(String r3, Matcher r4, ParsableByteArray r5, List<WebvttCssStyle> r6) {
        WebvttCueInfoBuilder r0 = new WebvttCueInfoBuilder();
        r0.startTimeUs = WebvttParserUtil.parseTimestampUs((String) Assertions.checkNotNull(r4.group(1)));     // Catch: IllegalArgumentException -> L13
        r0.endTimeUs = WebvttParserUtil.parseTimestampUs((String) Assertions.checkNotNull(r4.group(2)));     // Catch: IllegalArgumentException -> L13
        parseCueSettingsList((String) Assertions.checkNotNull(r4.group(3)), r0);
        StringBuilder r42 = new StringBuilder();
        String r1 = r5.readLine();
    L6:
        if (TextUtils.isEmpty(r1) == true) goto L11;
        if (r42.length() <= 0) goto L10;
        r42.append("\n");
    L10:
        r42.append(r1.trim());
        r1 = r5.readLine();
        goto L6
    L11:
        r0.text = parseCueText(r3, r42.toString(), r6);
        return r0.build();
    L13:
        Log.w("WebvttCueParser", "Skipping cue with bad header: " + r4.group());
        return null;
    }

    public static Cue.Builder parseCueSettingsList(String r1) {
        WebvttCueInfoBuilder r0 = new WebvttCueInfoBuilder();
        parseCueSettingsList(r1, r0);
        return r0.toCueBuilder();
    }

    private static void parseCueSettingsList(String r5, WebvttCueInfoBuilder r6) {
        Matcher r52 = CUE_SETTING_PATTERN.matcher(r5);
    L4:
        if (r52.find() == false) goto L24;
        String r1 = (String) Assertions.checkNotNull(r52.group(1));
        String r2 = (String) Assertions.checkNotNull(r52.group(2));
        if ("line".equals(r1) == true) goto L8;
        if ("align".equals(r1) == true) goto L11;
        if ("position".equals(r1) == true) goto L14;
        if ("size".equals(r1) == true) goto L17;
        if ("vertical".equals(r1) == true) goto L20;
        Log.w("WebvttCueParser", "Unknown cue setting " + r1 + ":" + r2);     // Catch: NumberFormatException -> L23
        goto L4
    L20:
        r6.verticalType = parseVerticalAttribute(r2);     // Catch: NumberFormatException -> L23
        goto L4
    L17:
        r6.size = WebvttParserUtil.parsePercentage(r2);     // Catch: NumberFormatException -> L23
        goto L4
    L14:
        parsePositionAttribute(r2, r6);     // Catch: NumberFormatException -> L23
        goto L4
    L11:
        r6.textAlignment = parseTextAlignment(r2);     // Catch: NumberFormatException -> L23
        goto L4
    L8:
        parseLineAttribute(r2, r6);     // Catch: NumberFormatException -> L23
    L23:
        Log.w("WebvttCueParser", "Skipping bad cue setting: " + r52.group());
        goto L4
    }

    public static SpannedString parseCueText(String r11, String r12, List<WebvttCssStyle> r13) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        ArrayDeque r1 = new ArrayDeque();
        ArrayList r2 = new ArrayList();
        int r4 = 0;
    L4:
        if (r4 >= r12.length()) goto L59;
        char r5 = r12.charAt(r4);
        if (r5 != '&') goto L8;
        r4 = r4 + 1;
        int r6 = r12.indexOf(59, r4);
        int r7 = r12.indexOf(32, r4);
        if (r6 != (-1)) goto L49;
        r6 = r7;
    L52:
        if (r6 != (-1)) goto L53;
        r0.append(r5);
        goto L4
    L53:
        applyEntity(r12.substring(r4, r6), r0);
        if (r6 != r7) goto L56;
        r0.append(" ");
    L56:
        r4 = r6 + 1;
        goto L4
    L49:
        if (r7 == (-1)) goto L52;
        r6 = Math.min(r6, r7);
        goto L52
    L8:
        if (r5 != '<') goto L9;
        int r52 = r4 + 1;
        if (r52 >= r12.length()) goto L32;
        int r8 = 1;
        if (r12.charAt(r52) != '/') goto L16;
        boolean r62 = true;
    L17:
        r52 = findEndOfTag(r12, r52);
        int r9 = r52 - 2;
        if (r12.charAt(r9) != '/') goto L20;
        boolean r72 = true;
    L21:
        if (r62 == false) goto L23;
        r8 = 2;
    L23:
        int r42 = r4 + r8;
        if (r72 == true) goto L27;
        r9 = r52 - 1;
    L27:
        String r43 = r12.substring(r42, r9);
        if (r43.trim().isEmpty() == true) goto L32;
        String r82 = getTagName(r43);
        if (isSupportedTag(r82) == false) goto L32;
        if (r62 == true) goto L35;
        if (r72 == true) goto L32;
        r1.push(StartTag.buildStartTag(r43, r0.length()));
    L35:
        if (r1.isEmpty() == true) goto L32;
        StartTag r44 = (StartTag) r1.pop();
        applySpansForTag(r11, r44, r2, r0, r13);
        if (r1.isEmpty() == true) goto L40;
        r2.add(new Element(r44, r0.length(), null));
    L42:
        if (r44.name.equals(r82) == false) goto L35;
    L40:
        r2.clear();
        goto L42
    L20:
        r72 = false;
        goto L21
    L16:
        r62 = false;
    L32:
        r4 = r52;
        goto L4
    L9:
        r0.append(r5);
        r4 = r4 + 1;
    L59:
        if (r1.isEmpty() == true) goto L61;
        applySpansForTag(r11, (StartTag) r1.pop(), r2, r0, r13);
        goto L59
    L61:
        applySpansForTag(r11, StartTag.buildWholeCueVirtualTag(), Collections.EMPTY_LIST, r0, r13);
        return SpannedString.valueOf(r0);
    }

    private static int parseLineAnchor(String r5) {
        r5.getClass();
        char r4 = 65535;
        switch(r5.hashCode()) {
            case -1364013995: goto L18;
            case -1074341483: goto L14;
            case 100571: goto L10;
            case 109757538: goto L6;
            default: goto L21;
        };
    L21:
        switch(r4) {
            case 0: goto L26;
            case 1: goto L26;
            case 2: goto L25;
            case 3: goto L24;
            default: goto L22;
        };
    L22:
        Log.w("WebvttCueParser", "Invalid anchor value: ".concat(r5));
        return Integer.MIN_VALUE;
    L24:
        return 0;
    L25:
        return 2;
    L26:
        return 1;
    L6:
        if (r5.equals("start") == false) goto L21;
        r4 = 3;
        goto L21
    L10:
        if (r5.equals("end") == false) goto L21;
        r4 = 2;
        goto L21
    L14:
        if (r5.equals("middle") == false) goto L21;
        r4 = 1;
        goto L21
    L18:
        if (r5.equals("center") == false) goto L21;
        r4 = 0;
        goto L21
    }

    private static void parseLineAttribute(String r3, WebvttCueInfoBuilder r4) {
        int r0 = r3.indexOf(44);
        if (r0 == (-1)) goto L6;
        r4.lineAnchor = parseLineAnchor(r3.substring(r0 + 1));
        r3 = r3.substring(0, r0);
    L6:
        if (r3.endsWith("%") == false) goto L9;
        r4.line = WebvttParserUtil.parsePercentage(r3);
        r4.lineType = 0;
        return;
    L9:
        r4.line = Integer.parseInt(r3);
        r4.lineType = 1;
    }

    private static int parsePositionAnchor(String r5) {
        r5.getClass();
        char r4 = 65535;
        switch(r5.hashCode()) {
            case -1842484672: goto L26;
            case -1364013995: goto L22;
            case -1276788989: goto L18;
            case -1074341483: goto L14;
            case 100571: goto L10;
            case 109757538: goto L6;
            default: goto L29;
        };
    L29:
        switch(r4) {
            case 0: goto L34;
            case 1: goto L33;
            case 2: goto L32;
            case 3: goto L33;
            case 4: goto L32;
            case 5: goto L34;
            default: goto L30;
        };
    L30:
        Log.w("WebvttCueParser", "Invalid anchor value: ".concat(r5));
        return Integer.MIN_VALUE;
    L32:
        return 2;
    L33:
        return 1;
    L34:
        return 0;
    L6:
        if (r5.equals("start") == false) goto L29;
        r4 = 5;
        goto L29
    L10:
        if (r5.equals("end") == false) goto L29;
        r4 = 4;
        goto L29
    L14:
        if (r5.equals("middle") == false) goto L29;
        r4 = 3;
        goto L29
    L18:
        if (r5.equals("line-right") == false) goto L29;
        r4 = 2;
        goto L29
    L22:
        if (r5.equals("center") == false) goto L29;
        r4 = 1;
        goto L29
    L26:
        if (r5.equals("line-left") == false) goto L29;
        r4 = 0;
        goto L29
    }

    private static void parsePositionAttribute(String r2, WebvttCueInfoBuilder r3) {
        int r0 = r2.indexOf(44);
        if (r0 == (-1)) goto L5;
        r3.positionAnchor = parsePositionAnchor(r2.substring(r0 + 1));
        r2 = r2.substring(0, r0);
    L5:
        r3.position = WebvttParserUtil.parsePercentage(r2);
    }

    private static int parseTextAlignment(String r7) {
        r7.getClass();
        char r6 = 65535;
        switch(r7.hashCode()) {
            case -1364013995: goto L26;
            case -1074341483: goto L22;
            case 100571: goto L18;
            case 3317767: goto L14;
            case 108511772: goto L10;
            case 109757538: goto L6;
            default: goto L29;
        };
    L29:
        switch(r6) {
            case 0: goto L36;
            case 1: goto L36;
            case 2: goto L35;
            case 3: goto L34;
            case 4: goto L33;
            case 5: goto L32;
            default: goto L30;
        };
    L30:
        Log.w("WebvttCueParser", "Invalid alignment value: ".concat(r7));
        return 2;
    L32:
        return 1;
    L33:
        return 5;
    L34:
        return 4;
    L35:
        return 3;
    L36:
        return 2;
    L6:
        if (r7.equals("start") == false) goto L29;
        r6 = 5;
        goto L29
    L10:
        if (r7.equals("right") == false) goto L29;
        r6 = 4;
        goto L29
    L14:
        if (r7.equals("left") == false) goto L29;
        r6 = 3;
        goto L29
    L18:
        if (r7.equals("end") == false) goto L29;
        r6 = 2;
        goto L29
    L22:
        if (r7.equals("middle") == false) goto L29;
        r6 = 1;
        goto L29
    L26:
        if (r7.equals("center") == false) goto L29;
        r6 = 0;
        goto L29
    }

    private static int parseVerticalAttribute(String r1) {
        r1.getClass();
        if (r1.equals("lr") == false) goto L5;
        return 2;
    L5:
        if (r1.equals("rl") == true) goto L8;
        Log.w("WebvttCueParser", "Invalid 'vertical' value: ".concat(r1));
        return Integer.MIN_VALUE;
    L8:
        return 1;
    }
}
