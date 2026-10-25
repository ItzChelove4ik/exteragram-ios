package androidx.media3.extractor.text.subrip;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.collect.ImmutableList;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class SubripParser implements SubtitleParser {
    private static final Pattern SUBRIP_TAG_PATTERN = null;
    private static final Pattern SUBRIP_TIMING_LINE = null;
    private final ParsableByteArray parsableByteArray;
    private final ArrayList<String> tags;
    private final StringBuilder textBuilder;

    static {
        SUBRIP_TIMING_LINE = Pattern.compile("\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d{3}))?)\\s*-->\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d{3}))?)\\s*");
        SUBRIP_TAG_PATTERN = Pattern.compile("\\{\\\\.*?\\}");
    }

    public SubripParser() {
        this.textBuilder = new StringBuilder();
        this.tags = new ArrayList();
        this.parsableByteArray = new ParsableByteArray();
    }

    private Cue buildCue(Spanned r13, String r14) {
        Cue.Builder r12 = new Cue.Builder().setText(r13);
        if (r14 != null) goto L7;
        return r12.build();
    L7:
        switch(r14.hashCode()) {
            case -685620710: goto L28;
            case -685620679: goto L26;
            case -685620648: goto L24;
            case -685620617: goto L21;
            case -685620586: goto L19;
            case -685620555: goto L17;
            case -685620524: goto L14;
            case -685620493: goto L12;
            case -685620462: goto L10;
            default: goto L30;
        };
    L12:
        r14.equals("{\\an8}");
        goto L30
    L19:
        r14.equals("{\\an5}");
        goto L30
    L26:
        r14.equals("{\\an2}");
    L30:
        r12.setPositionAnchor(1);
    L32:
        switch(r14.hashCode()) {
            case -685620710: goto L53;
            case -685620679: goto L50;
            case -685620648: goto L47;
            case -685620617: goto L45;
            case -685620586: goto L44;
            case -685620555: goto L43;
            case -685620524: goto L41;
            case -685620493: goto L38;
            case -685620462: goto L35;
            default: goto L55;
        };
    L43:
        r14.equals("{\\an6}");
        goto L55
    L44:
        r14.equals("{\\an5}");
        goto L55
    L45:
        r14.equals("{\\an4}");
    L55:
        r12.setLineAnchor(1);
    L57:
        return r12.setPosition(getFractionalPositionForAnchorType(r12.getPositionAnchor())).setLine(getFractionalPositionForAnchorType(r12.getLineAnchor()), 0).build();
    L35:
        if (r14.equals("{\\an9}") == false) goto L55;
    L42:
        r12.setLineAnchor(0);
        goto L57
    L38:
        if (r14.equals("{\\an8}") == false) goto L55;
    L41:
        if (r14.equals("{\\an7}") == false) goto L55;
    L47:
        if (r14.equals("{\\an3}") == false) goto L55;
    L54:
        r12.setLineAnchor(2);
        goto L57
    L50:
        if (r14.equals("{\\an2}") == false) goto L55;
    L53:
        if (r14.equals("{\\an1}") == false) goto L55;
    L10:
        if (r14.equals("{\\an9}") == false) goto L30;
    L25:
        r12.setPositionAnchor(2);
        goto L32
    L14:
        if (r14.equals("{\\an7}") == false) goto L30;
    L29:
        r12.setPositionAnchor(0);
        goto L32
    L17:
        if (r14.equals("{\\an6}") == false) goto L30;
    L21:
        if (r14.equals("{\\an4}") == false) goto L30;
    L24:
        if (r14.equals("{\\an3}") == false) goto L30;
    L28:
        if (r14.equals("{\\an1}") == false) goto L30;
        goto L29
    }

    private Charset detectUtfCharset(ParsableByteArray r1) {
        Charset r0 = r1.readUtfCharsetFromBom();
        if (r0 == null) goto L6;
        return r0;
    L6:
        return StandardCharsets.UTF_8;
    }

    public static float getFractionalPositionForAnchorType(int r1) {
        if (r1 != 0) goto L4;
        return 0.08f;
    L4:
        if (r1 != 1) goto L6;
        return 0.5f;
    L6:
        if (r1 != 2) goto L9;
        return 0.92f;
    L9:
        Segment$$ExternalSyntheticBUOutline0.m();
        return 0.0f;
    }

    private static long parseTimecode(Matcher r6, int r7) {
        String r0 = r6.group(r7 + 1);
        if (r0 == null) goto L5;
        long r02 = Long.parseLong(r0) * DurationKt.MILLIS_IN_HOUR;
    L6:
        long r03 = (r02 + (Long.parseLong((String) Assertions.checkNotNull(r6.group(r7 + 2))) * 60000)) + (Long.parseLong((String) Assertions.checkNotNull(r6.group(r7 + 3))) * 1000);
        String r62 = r6.group(r7 + 4);
        if (r62 == null) goto L10;
        r03 = r03 + Long.parseLong(r62);
    L10:
        return r03 * 1000;
    L5:
        r02 = 0;
        goto L6
    }

    private String processLine(String r6, ArrayList<String> r7) {
        String r5 = r6.trim();
        StringBuilder r62 = new StringBuilder(r5);
        Matcher r52 = SUBRIP_TAG_PATTERN.matcher(r5);
        int r0 = 0;
    L4:
        if (r52.find() == false) goto L7;
        String r1 = r52.group();
        r7.add(r1);
        int r2 = r52.start() - r0;
        int r12 = r1.length();
        r62.replace(r2, r2 + r12, _UrlKt.FRAGMENT_ENCODE_SET);
        r0 = r0 + r12;
        goto L4
    L7:
        return r62.toString();
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public int getCueReplacementBehavior() {
        return 1;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public void parse(byte[] r19, int r20, int r21, SubtitleParser.OutputOptions r22, Consumer<CuesWithTiming> r23) {
        this.parsableByteArray.reset(r19, r20 + r21);
        this.parsableByteArray.setPosition(r20);
        Charset r1 = detectUtfCharset(this.parsableByteArray);
        long r7 = -9223372036854775807L;
        if (r22.startTimeUs != (-9223372036854775807L)) goto L5;
    L7:
        ArrayList r5 = null;
    L8:
        String r9 = this.parsableByteArray.readLine(r1);
        int r10 = 0;
        if (r9 == null) goto L45;
        if (r9.isEmpty() == true) goto L8;
        Integer.parseInt(r9);     // Catch: NumberFormatException -> L44
        String r92 = this.parsableByteArray.readLine(r1);
        if (r92 == null) goto L16;
        Matcher r11 = SUBRIP_TIMING_LINE.matcher(r92);
        if (r11.matches() == true) goto L19;
        Log.w("SubripParser", "Skipping invalid timing: ".concat(r92));
        goto L8
    L19:
        long r14 = parseTimecode(r11, 1);
        long r112 = parseTimecode(r11, 6);
        this.textBuilder.setLength(0);
        this.tags.clear();
        String r93 = this.parsableByteArray.readLine(r1);
    L20:
        boolean r13 = TextUtils.isEmpty(r93);
        StringBuilder r6 = this.textBuilder;
        if (r13 == true) goto L26;
        if (r6.length() <= 0) goto L25;
        this.textBuilder.append("<br>");
    L25:
        this.textBuilder.append(processLine(r93, this.tags));
        r93 = this.parsableByteArray.readLine(r1);
        goto L20
    L26:
        Spanned r62 = Html.fromHtml(r6.toString());
    L28:
        if (r10 >= this.tags.size()) goto L33;
        String r94 = this.tags.get(r10);
        if (r94.matches("\\{\\\\an[1-9]\\}") == true) goto L31;
        r10 = r10 + 1;
    L31:
        long r202 = r7;
        long r72 = r22.startTimeUs;
        if (r72 == r202) goto L41;
        if (r112 >= r72) goto L41;
        if (r5 == null) goto L42;
        r5.add(new CuesWithTiming(ImmutableList.of(buildCue(r62, r94)), r14, r112 - r14));
    L42:
        r7 = r202;
    L41:
        r23.accept(new CuesWithTiming(ImmutableList.of(buildCue(r62, r94)), r14, r112 - r14));
        goto L42
    L33:
        r94 = null;
        goto L31
    L16:
        Log.w("SubripParser", "Unexpected end");
    L44:
        Log.w("SubripParser", "Skipping invalid index: ".concat(r9));
    L45:
        if (r5 == null) goto L49;
        int r0 = r5.size();
    L47:
        if (r10 >= r0) goto L69;
        Object r12 = r5.get(r10);
        r10 = r10 + 1;
        r23.accept((CuesWithTiming) r12);
        goto L47
    L69:
        return;
    L49:
        return;
    L5:
        if (r22.outputAllCues == false) goto L7;
        r5 = new ArrayList();
        goto L8
    }
}
