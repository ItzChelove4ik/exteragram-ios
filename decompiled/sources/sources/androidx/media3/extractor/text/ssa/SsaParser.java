package androidx.media3.extractor.text.ssa;

import android.graphics.PointF;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.ssa.SsaStyle;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.common.base.Ascii;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class SsaParser implements SubtitleParser {
    private static final Pattern SSA_TIMECODE_PATTERN = null;
    private final SsaDialogueFormat dialogueFormatFromInitializationData;
    private final boolean haveInitializationData;
    private final ParsableByteArray parsableByteArray;
    private float screenHeight;
    private float screenWidth;
    private Map<String, SsaStyle> styles;

    static {
        SSA_TIMECODE_PATTERN = Pattern.compile("(?:(\\d+):)?(\\d+):(\\d+)[:.](\\d+)");
    }

    public SsaParser(List<byte[]> r4) {
        this.screenWidth = -3.4028235E38f;
        this.screenHeight = -3.4028235E38f;
        this.parsableByteArray = new ParsableByteArray();
        if (r4 != null) goto L5;
    L8:
        this.haveInitializationData = false;
        this.dialogueFormatFromInitializationData = null;
        return;
    L5:
        if (r4.isEmpty() == true) goto L8;
        this.haveInitializationData = true;
        String r0 = Util.fromUtf8Bytes(r4.get(0));
        Assertions.checkArgument(r0.startsWith("Format:"));
        this.dialogueFormatFromInitializationData = (SsaDialogueFormat) Assertions.checkNotNull(SsaDialogueFormat.fromFormatLine(r0));
        parseHeader(new ParsableByteArray(r4.get(1)), StandardCharsets.UTF_8);
    }

    private static int addCuePlacerholderByTime(long r3, List<Long> r5, List<List<Cue>> r6) {
        int r0 = r5.size() - 1;
    L3:
        if (r0 < 0) goto L11;
        if (r5.get(r0).longValue() == r3) goto L6;
        if (r5.get(r0).longValue() < r3) goto L9;
        r0 = r0 - 1;
        goto L3
    L9:
        int r02 = r0 + 1;
    L12:
        r5.add(r02, Long.valueOf(r3));
        if (r02 != 0) goto L15;
        ArrayList r32 = new ArrayList();
    L16:
        r6.add(r02, r32);
        return r02;
    L15:
        r32 = new ArrayList(r6.get(r02 - 1));
        goto L16
    L6:
        return r0;
    L11:
        r02 = 0;
        goto L12
    }

    private static float computeDefaultLineOrPosition(int r1) {
        if (r1 != 0) goto L4;
        return 0.05f;
    L4:
        if (r1 != 1) goto L6;
        return 0.5f;
    L6:
        if (r1 == 2) goto L9;
        return -3.4028235E38f;
    L9:
        return 0.95f;
    }

    private static Cue createCue(String r7, int r8, SsaStyle r9, SsaStyle.Overrides r10, float r11, float r12) {
        SpannableString r0 = new SpannableString(r7);
        Cue.Builder r72 = new Cue.Builder().setText(r0).setZIndex(r8);
        if (r9 != null) goto L5;
    L33:
        int r02 = r10.alignment;
        if (r02 != (-1)) goto L39;
        if (r9 == null) goto L38;
        r02 = r9.alignment;
        goto L39
    L38:
        r02 = -1;
    L39:
        r72.setTextAlignment(toTextAlignment(r02)).setPositionAnchor(toPositionAnchor(r02)).setLineAnchor(toLineAnchor(r02));
        PointF r92 = r10.position;
        if (r92 != null) goto L42;
    L46:
        r72.setPosition(computeDefaultLineOrPosition(r72.getPositionAnchor()));
        r72.setLine(computeDefaultLineOrPosition(r72.getLineAnchor()), 0);
    L48:
        return r72.build();
    L42:
        if (r12 == (-3.4028235E38f)) goto L46;
        if (r11 == (-3.4028235E38f)) goto L46;
        r72.setPosition(r92.x / r11);
        r72.setLine(r10.position.y / r12, 0);
        goto L48
    L5:
        if (r9.primaryColor == null) goto L8;
        r0.setSpan(new ForegroundColorSpan(r9.primaryColor.intValue()), 0, r0.length(), 33);
    L8:
        if (r9.borderStyle == 3) goto L10;
    L12:
        float r2 = r9.fontSize;
        if (r2 != (-3.4028235E38f)) goto L15;
    L17:
        boolean r22 = r9.bold;
        if (r22 == true) goto L20;
    L22:
        if (r22 == false) goto L25;
        r0.setSpan(new StyleSpan(1), 0, r0.length(), 33);
    L28:
        if (r9.underline == false) goto L31;
        r0.setSpan(new UnderlineSpan(), 0, r0.length(), 33);
    L31:
        if (r9.strikeout == false) goto L33;
        r0.setSpan(new StrikethroughSpan(), 0, r0.length(), 33);
        goto L33
    L25:
        if (r9.italic == false) goto L28;
        r0.setSpan(new StyleSpan(2), 0, r0.length(), 33);
        goto L28
    L20:
        if (r9.italic == false) goto L22;
        r0.setSpan(new StyleSpan(3), 0, r0.length(), 33);
        goto L28
    L15:
        if (r12 == (-3.4028235E38f)) goto L17;
        r72.setTextSize(r2 / r12, 1);
        goto L17
    L10:
        if (r9.outlineColor == null) goto L12;
        r0.setSpan(new BackgroundColorSpan(r9.outlineColor.intValue()), 0, r0.length(), 33);
        goto L12
    }

    private Charset detectUtfCharset(ParsableByteArray r1) {
        Charset r0 = r1.readUtfCharsetFromBom();
        if (r0 == null) goto L6;
        return r0;
    L6:
        return StandardCharsets.UTF_8;
    }

    private void parseDialogueLine(String r17, SsaDialogueFormat r18, List<List<Cue>> r19, List<Long> r20) {
        Assertions.checkArgument(r17.startsWith("Dialogue:"));
        String[] r5 = r17.substring(9).split(",", r18.length);
        if (r5.length == r18.length) goto L6;
        Log.w("SsaParser", "Skipping dialogue line with fewer columns than format: ".concat(r17));
        return;
    L6:
        int r6 = r18.layerIndex;
        if (r6 != (-1)) goto L33;
    L11:
        int r62 = 0;
    L9:
        int r10 = r62;
        long r11 = parseTimecodeUs(r5[r18.startTimeIndex]);
        if (r11 != (-9223372036854775807L)) goto L16;
        Log.w("SsaParser", "Skipping invalid timing: ".concat(r17));
        return;
    L16:
        long r7 = parseTimecodeUs(r5[r18.endTimeIndex]);
        if (r7 != (-9223372036854775807L)) goto L19;
    L31:
        Log.w("SsaParser", "Skipping invalid timing: ".concat(r17));
        return;
    L19:
        if (r7 <= r11) goto L31;
        Map<String, SsaStyle> r1 = this.styles;
        if (r1 == null) goto L26;
        int r63 = r18.styleIndex;
        if (r63 == (-1)) goto L26;
        SsaStyle r12 = r1.get(r5[r63].trim());
    L27:
        String r2 = r5[r18.textIndex];
        SsaStyle r112 = r12;
        Cue r0 = createCue(SsaStyle.Overrides.stripStyleOverrides(r2).replace("\\N", "\n").replace("\\n", "\n").replace("\\h", " "), r10, r112, SsaStyle.Overrides.parseFromDialogue(r2), this.screenWidth, this.screenHeight);
        int r13 = addCuePlacerholderByTime(r11, r20, r19);
        int r22 = addCuePlacerholderByTime(r7, r20, r19);
    L28:
        if (r13 >= r22) goto L30;
        r19.get(r13).add(r0);
        r13 = r13 + 1;
        goto L28
    L30:
        return;
    L26:
        r12 = null;
        goto L27
    L33:
        r62 = Integer.parseInt(r5[r6].trim());     // Catch: RuntimeException -> L10
    L10:
        Log.w("SsaParser", "Fail to parse layer: " + r5[r18.layerIndex]);
        goto L11
    }

    private void parseEventBody(ParsableByteArray r4, List<List<Cue>> r5, List<Long> r6, Charset r7) {
        if (this.haveInitializationData == false) goto L5;
        SsaDialogueFormat r0 = this.dialogueFormatFromInitializationData;
    L6:
        String r1 = r4.readLine(r7);
        if (r1 == null) goto L16;
        if (r1.startsWith("Format:") == true) goto L10;
        if (r1.startsWith("Dialogue:") == false) goto L6;
        if (r0 == null) goto L14;
        parseDialogueLine(r1, r0, r5, r6);
        goto L6
    L14:
        Log.w("SsaParser", "Skipping dialogue line before complete format: ".concat(r1));
        goto L6
    L10:
        r0 = SsaDialogueFormat.fromFormatLine(r1);
        goto L6
    L16:
        return;
    L5:
        r0 = null;
        goto L6
    }

    private void parseHeader(ParsableByteArray r3, Charset r4) {
    L2:
        String r0 = r3.readLine(r4);
        if (r0 == null) goto L15;
        if ("[Script Info]".equalsIgnoreCase(r0) == true) goto L6;
        if ("[V4+ Styles]".equalsIgnoreCase(r0) == true) goto L9;
        if ("[V4 Styles]".equalsIgnoreCase(r0) == true) goto L12;
        if ("[Events]".equalsIgnoreCase(r0) == false) goto L2;
        return;
    L12:
        Log.i("SsaParser", "[V4 Styles] are not supported");
        goto L2
    L9:
        this.styles = parseStyles(r3, r4);
        goto L2
    L6:
        parseScriptInfo(r3, r4);
        goto L2
    }

    private void parseScriptInfo(ParsableByteArray r5, Charset r6) {
    L2:
        String r0 = r5.readLine(r6);
        if (r0 == null) goto L19;
        if (r5.bytesLeft() != 0) goto L7;
    L8:
        String[] r02 = r0.split(":");
        if (r02.length != 2) goto L2;
        String r1 = Ascii.toLowerCase(r02[0].trim());
        r1.getClass();
        if (r1.equals("playresx") == false) goto L14;
        this.screenWidth = Float.parseFloat(r02[1].trim());     // Catch: NumberFormatException -> L20
        goto L2
    L14:
        if (r1.equals("playresy") == false) goto L2;
        this.screenHeight = Float.parseFloat(r02[1].trim());     // Catch: NumberFormatException -> L20
        goto L2
    L7:
        if (r5.peekCodePoint(r6) != 91) goto L8;
        return;
    }

    private static Map<String, SsaStyle> parseStyles(ParsableByteArray r5, Charset r6) {
        LinkedHashMap r0 = new LinkedHashMap();
        SsaStyle.Format r1 = null;
    L3:
        String r2 = r5.readLine(r6);
        if (r2 == null) goto L19;
        if (r5.bytesLeft() == 0) goto L10;
        if (r5.peekCodePoint(r6) == 91) goto L19;
    L10:
        if (r2.startsWith("Format:") == true) goto L11;
        if (r2.startsWith("Style:") == false) goto L3;
        if (r1 == null) goto L15;
        SsaStyle r22 = SsaStyle.fromStyleLine(r2, r1);
        if (r22 == null) goto L3;
        r0.put(r22.name, r22);
        goto L3
    L15:
        Log.w("SsaParser", "Skipping 'Style:' line before 'Format:' line: ".concat(r2));
        goto L3
    L11:
        r1 = SsaStyle.Format.fromFormatLine(r2);
    L19:
        return r0;
    }

    private static long parseTimecodeUs(String r6) {
        Matcher r62 = SSA_TIMECODE_PATTERN.matcher(r6.trim());
        if (r62.matches() == true) goto L7;
        return -9223372036854775807L;
    L7:
        return (((Long.parseLong((String) Util.castNonNull(r62.group(1))) * 3600000000L) + (Long.parseLong((String) Util.castNonNull(r62.group(2))) * 60000000)) + (Long.parseLong((String) Util.castNonNull(r62.group(3))) * 1000000)) + (Long.parseLong((String) Util.castNonNull(r62.group(4))) * NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
    }

    private static int toLineAnchor(int r3) {
        switch(r3) {
            case -1: goto L12;
            case 0: goto L4;
            case 1: goto L10;
            case 2: goto L10;
            case 3: goto L10;
            case 4: goto L8;
            case 5: goto L8;
            case 6: goto L8;
            case 7: goto L6;
            case 8: goto L6;
            case 9: goto L6;
            default: goto L4;
        };
    L4:
        Log.w("SsaParser", "Unknown alignment: " + r3);
        return Integer.MIN_VALUE;
    L6:
        return 0;
    L8:
        return 1;
    L10:
        return 2;
    L12:
        return Integer.MIN_VALUE;
    }

    private static int toPositionAnchor(int r3) {
        switch(r3) {
            case -1: goto L12;
            case 0: goto L4;
            case 1: goto L10;
            case 2: goto L8;
            case 3: goto L6;
            case 4: goto L10;
            case 5: goto L8;
            case 6: goto L6;
            case 7: goto L10;
            case 8: goto L8;
            case 9: goto L6;
            default: goto L4;
        };
    L4:
        Log.w("SsaParser", "Unknown alignment: " + r3);
        return Integer.MIN_VALUE;
    L6:
        return 2;
    L8:
        return 1;
    L10:
        return 0;
    L12:
        return Integer.MIN_VALUE;
    }

    private static Layout.Alignment toTextAlignment(int r3) {
        switch(r3) {
            case -1: goto L12;
            case 0: goto L4;
            case 1: goto L11;
            case 2: goto L9;
            case 3: goto L7;
            case 4: goto L11;
            case 5: goto L9;
            case 6: goto L7;
            case 7: goto L11;
            case 8: goto L9;
            case 9: goto L7;
            default: goto L4;
        };
    L4:
        Log.w("SsaParser", "Unknown alignment: " + r3);
        return null;
    L12:
        return null;
    L7:
        return Layout.Alignment.ALIGN_OPPOSITE;
    L9:
        return Layout.Alignment.ALIGN_CENTER;
    L11:
        return Layout.Alignment.ALIGN_NORMAL;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public int getCueReplacementBehavior() {
        return 1;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public void parse(byte[] r19, int r20, int r21, SubtitleParser.OutputOptions r22, Consumer<CuesWithTiming> r23) {
        ArrayList r4 = new ArrayList();
        ArrayList r5 = new ArrayList();
        this.parsableByteArray.reset(r19, r20 + r21);
        this.parsableByteArray.setPosition(r20);
        Charset r1 = detectUtfCharset(this.parsableByteArray);
        if (this.haveInitializationData == true) goto L5;
        parseHeader(this.parsableByteArray, r1);
    L5:
        parseEventBody(this.parsableByteArray, r4, r5, r1);
        if (r22.startTimeUs != (-9223372036854775807L)) goto L8;
    L10:
        ArrayList r0 = null;
    L11:
        int r12 = 0;
        int r8 = 0;
    L13:
        if (r8 >= r4.size()) goto L31;
        List<Cue> r11 = r4.get(r8);
        if (r11.isEmpty() == false) goto L19;
        if (r8 == 0) goto L19;
    L28:
        r8 = r8 + 1;
    L19:
        if (r8 == (r4.size() - 1)) goto L29;
        long r122 = r5.get(r8).longValue();
        long r16 = r5.get(r8 + 1).longValue();
        CuesWithTiming r10 = new CuesWithTiming(r11, r122, r16 - r122);
        long r112 = r22.startTimeUs;
        if (r112 != (-9223372036854775807L)) goto L23;
    L27:
        r23.accept(r10);
        goto L28
    L23:
        if (r16 >= r112) goto L27;
        if (r0 == null) goto L28;
        r0.add(r10);
        goto L28
    L29:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return;
    L31:
        if (r0 == null) goto L35;
        int r2 = r0.size();
    L33:
        if (r12 >= r2) goto L43;
        Object r42 = r0.get(r12);
        r12 = r12 + 1;
        r23.accept((CuesWithTiming) r42);
        goto L33
    L43:
        return;
    L35:
        return;
    L8:
        if (r22.outputAllCues == false) goto L10;
        r0 = new ArrayList();
        goto L11
    }
}
