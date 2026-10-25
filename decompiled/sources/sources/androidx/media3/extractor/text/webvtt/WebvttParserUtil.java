package androidx.media3.extractor.text.webvtt;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public abstract class WebvttParserUtil {
    private static final Pattern COMMENT = null;

    static {
        COMMENT = Pattern.compile("^NOTE([ \t].*)?$");
    }

    public static Matcher findNextCueHeader(ParsableByteArray r2) {
    L2:
        String r0 = r2.readLine();
        if (r0 == null) goto L14;
        if (COMMENT.matcher(r0).matches() == true) goto L6;
        Matcher r02 = WebvttCueParser.CUE_HEADER_PATTERN.matcher(r0);
        if (r02.matches() == false) goto L2;
        return r02;
    L6:
        String r03 = r2.readLine();
        if (r03 == null) goto L2;
        if (r03.isEmpty() == true) goto L2;
    L14:
        return null;
    }

    public static boolean isWebvttHeaderLine(ParsableByteArray r1) {
        String r12 = r1.readLine();
        if (r12 != null) goto L5;
        return false;
    L5:
        if (r12.startsWith("WEBVTT") == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static float parsePercentage(String r2) {
        if (r2.endsWith("%") == false) goto L7;
        return Float.parseFloat(r2.substring(0, r2.length() - 1)) / 100.0f;
    L7:
        throw new NumberFormatException("Percentages must end with %");
    }

    public static long parseTimestampUs(String r10) {
        String[] r102 = Util.splitAtFirst(r10, "\\.");
        int r0 = 0;
        String[] r1 = Util.split(r102[0], ":");
        int r2 = r1.length;
        long r5 = 0;
    L3:
        if (r0 >= r2) goto L5;
        r5 = (r5 * 60) + Long.parseLong(r1[r0]);
        r0 = r0 + 1;
        goto L3
    L5:
        long r52 = r5 * 1000;
        if (r102.length != 2) goto L13;
        String r103 = r102[1].trim();
        if (r103.length() != 3) goto L10;
        r52 = r52 + Long.parseLong(r103);
        goto L13
    L10:
        f$$ExternalSyntheticBUOutline1.m("Expected 3 decimal places, got: ".concat(r103));
        return 0;
    L13:
        return r52 * 1000;
    }

    public static void validateWebvttHeaderLine(ParsableByteArray r2) {
        int r0 = r2.getPosition();
        if (isWebvttHeaderLine(r2) == false) goto L5;
        return;
    L5:
        r2.setPosition(r0);
        throw ParserException.createForMalformedContainer("Expected WEBVTT. Got " + r2.readLine(), null);
    }
}
