package androidx.media3.extractor.text.webvtt;

import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ColorParser;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import com.google.common.base.Ascii;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class WebvttCssParser {
    private static final Pattern FONT_SIZE_PATTERN = null;
    private static final Pattern VOICE_NAME_PATTERN = null;
    private final StringBuilder stringBuilder;
    private final ParsableByteArray styleInput;

    static {
        VOICE_NAME_PATTERN = Pattern.compile("\\[voice=\"([^\"]*)\"\\]");
        FONT_SIZE_PATTERN = Pattern.compile("^((?:[0-9]*\\.)?[0-9]+)(px|em|%)$");
    }

    public WebvttCssParser() {
        this.styleInput = new ParsableByteArray();
        this.stringBuilder = new StringBuilder();
    }

    private void applySelectorToStyle(WebvttCssStyle r6, String r7) {
        if (r7.isEmpty() == true) goto L20;
        int r5 = r7.indexOf(91);
        if (r5 == (-1)) goto L11;
        Matcher r3 = VOICE_NAME_PATTERN.matcher(r7.substring(r5));
        if (r3.matches() == false) goto L10;
        r6.setTargetVoice((String) Assertions.checkNotNull(r3.group(1)));
    L10:
        r7 = r7.substring(0, r5);
    L11:
        String[] r52 = Util.split(r7, "\\.");
        String r72 = r52[0];
        int r32 = r72.indexOf(35);
        if (r32 == (-1)) goto L14;
        r6.setTargetTagName(r72.substring(0, r32));
        r6.setTargetId(r72.substring(r32 + 1));
    L16:
        if (r52.length <= 1) goto L19;
        r6.setTargetClasses((String[]) Util.nullSafeArrayCopyOfRange(r52, 1, r52.length));
        return;
    L19:
        return;
    L14:
        r6.setTargetTagName(r72);
        goto L16
    }

    private static boolean maybeSkipComment(ParsableByteArray r7) {
        int r0 = r7.getPosition();
        int r1 = r7.limit();
        byte[] r2 = r7.getData();
        if ((r0 + 2) > r1) goto L18;
        int r3 = r0 + 1;
        if (r2[r0] != 47) goto L27;
        int r02 = r0 + 2;
        if (r2[r3] != 42) goto L28;
    L8:
        int r32 = r02 + 1;
        if (r32 >= r1) goto L16;
        if (((char) r2[r02]) != '*') goto L15;
        if (((char) r2[r32]) != '/') goto L15;
        r02 = r02 + 2;
        r1 = r02;
    L15:
        r02 = r32;
        goto L8
    L16:
        r7.skipBytes(r1 - r7.getPosition());
        return true;
    L28:
        return false;
    L27:
        return false;
    L18:
        return false;
    }

    private static boolean maybeSkipWhitespace(ParsableByteArray r2) {
        char r0 = peekCharAtPosition(r2, r2.getPosition());
        if (r0 != '\t') goto L5;
    L14:
        r2.skipBytes(1);
        return true;
    L5:
        if (r0 == '\n') goto L14;
        if (r0 == '\f') goto L14;
        if (r0 == '\r') goto L14;
        if (r0 == ' ') goto L14;
        return false;
    }

    private static void parseFontSize(String r5, WebvttCssStyle r6) {
        Matcher r0 = FONT_SIZE_PATTERN.matcher(Ascii.toLowerCase(r5));
        if (r0.matches() == true) goto L6;
        Log.w("WebvttCssParser", "Invalid font-size: '" + r5 + "'.");
        return;
    L6:
        String r1 = (String) Assertions.checkNotNull(r0.group(2));
        r1.getClass();
        char r4 = 65535;
        switch(r1.hashCode()) {
            case 37: goto L18;
            case 3240: goto L14;
            case 3592: goto L10;
            default: goto L21;
        };
    L21:
        switch(r4) {
            case 0: goto L26;
            case 1: goto L25;
            case 2: goto L24;
            default: goto L22;
        };
    L22:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return;
    L24:
        r6.setFontSizeUnit(1);
    L27:
        r6.setFontSize(Float.parseFloat((String) Assertions.checkNotNull(r0.group(1))));
        return;
    L25:
        r6.setFontSizeUnit(2);
        goto L27
    L26:
        r6.setFontSizeUnit(3);
        goto L27
    L10:
        if (r1.equals("px") == false) goto L21;
        r4 = 2;
        goto L21
    L14:
        if (r1.equals("em") == false) goto L21;
        r4 = 1;
        goto L21
    L18:
        if (r1.equals("%") == false) goto L21;
        r4 = 0;
        goto L21
    }

    private static String parseIdentifier(ParsableByteArray r5, StringBuilder r6) {
        boolean r0 = false;
        r6.setLength(0);
        int r1 = r5.getPosition();
        int r2 = r5.limit();
    L3:
        if (r1 >= r2) goto L28;
        if (r0 == true) goto L28;
        char r3 = (char) r5.getData()[r1];
        if (r3 < 'A') goto L10;
        if (r3 > 'Z') goto L10;
    L27:
        r1 = r1 + 1;
        r6.append(r3);
    L10:
        if (r3 < 'a') goto L14;
        if (r3 <= 'z') goto L27;
    L14:
        if (r3 < '0') goto L18;
        if (r3 <= '9') goto L27;
    L18:
        if (r3 == '#') goto L27;
        if (r3 == '-') goto L27;
        if (r3 == '.') goto L27;
        if (r3 == '_') goto L27;
        r0 = true;
    L28:
        r5.skipBytes(r1 - r5.getPosition());
        return r6.toString();
    }

    public static String parseNextToken(ParsableByteArray r1, StringBuilder r2) {
        skipWhitespaceAndComments(r1);
        if (r1.bytesLeft() != 0) goto L6;
        return null;
    L6:
        String r22 = parseIdentifier(r1, r2);
        if (r22.isEmpty() == true) goto L10;
        return r22;
    L10:
        return _UrlKt.FRAGMENT_ENCODE_SET + ((char) r1.readUnsignedByte());
    }

    private static String parsePropertyValue(ParsableByteArray r5, StringBuilder r6) {
        StringBuilder r0 = new StringBuilder();
        boolean r1 = false;
    L3:
        if (r1 == true) goto L16;
        int r2 = r5.getPosition();
        String r3 = parseNextToken(r5, r6);
        if (r3 == null) goto L6;
        if ("}".equals(r3) == true) goto L14;
        if (";".equals(r3) == true) goto L14;
        r0.append(r3);
    L14:
        r5.setPosition(r2);
        r1 = true;
        goto L3
    L6:
        return null;
    L16:
        return r0.toString();
    }

    private static String parseSelector(ParsableByteArray r4, StringBuilder r5) {
        skipWhitespaceAndComments(r4);
        if (r4.bytesLeft() >= 5) goto L6;
        return null;
    L6:
        if ("::cue".equals(r4.readString(5)) == true) goto L8;
        return null;
    L8:
        int r0 = r4.getPosition();
        String r2 = parseNextToken(r4, r5);
        if (r2 != null) goto L12;
        return null;
    L12:
        if ("{".equals(r2) == false) goto L16;
        r4.setPosition(r0);
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L16:
        if ("(".equals(r2) == false) goto L18;
        String r02 = readCueTarget(r4);
    L20:
        if (")".equals(parseNextToken(r4, r5)) == true) goto L22;
        return null;
    L22:
        return r02;
    L18:
        r02 = null;
        goto L20
    }

    private static void parseStyleDeclaration(ParsableByteArray r4, WebvttCssStyle r5, StringBuilder r6) {
        skipWhitespaceAndComments(r4);
        String r0 = parseIdentifier(r4, r6);
        if (r0.isEmpty() == false) goto L6;
        return;
    L6:
        if (":".equals(parseNextToken(r4, r6)) == false) goto L81;
        skipWhitespaceAndComments(r4);
        String r1 = parsePropertyValue(r4, r6);
        if (r1 != null) goto L11;
        return;
    L11:
        if (r1.isEmpty() == true) goto L82;
        int r2 = r4.getPosition();
        String r62 = parseNextToken(r4, r6);
        if (";".equals(r62) == true) goto L20;
        if ("}".equals(r62) == false) goto L74;
        r4.setPosition(r2);
        goto L20
    L74:
        return;
    L20:
        if ("color".equals(r0) == false) goto L24;
        r5.setFontColor(ColorParser.parseCssColor(r1));
        return;
    L24:
        if ("background-color".equals(r0) == false) goto L27;
        r5.setBackgroundColor(ColorParser.parseCssColor(r1));
        return;
    L27:
        boolean r63 = true;
        if ("ruby-position".equals(r0) == false) goto L38;
        if ("over".equals(r1) == false) goto L34;
        r5.setRubyPosition(1);
        return;
    L34:
        if ("under".equals(r1) == false) goto L75;
        r5.setRubyPosition(2);
        return;
    L75:
        return;
    L38:
        if ("text-combine-upright".equals(r0) == false) goto L48;
        if ("all".equals(r1) == false) goto L42;
    L45:
        r5.setCombineUpright(r63);
        return;
    L42:
        if (r1.startsWith("digits") == true) goto L45;
        r63 = false;
        goto L45
    L48:
        if ("text-decoration".equals(r0) == false) goto L54;
        if ("underline".equals(r1) == false) goto L76;
        r5.setUnderline(true);
        return;
    L76:
        return;
    L54:
        if ("font-family".equals(r0) == false) goto L58;
        r5.setFontFamily(r1);
        return;
    L58:
        if ("font-weight".equals(r0) == false) goto L64;
        if ("bold".equals(r1) == false) goto L77;
        r5.setBold(true);
        return;
    L77:
        return;
    L64:
        if ("font-style".equals(r0) == false) goto L70;
        if ("italic".equals(r1) == false) goto L78;
        r5.setItalic(true);
        return;
    L78:
        return;
    L70:
        if ("font-size".equals(r0) == false) goto L79;
        parseFontSize(r1, r5);
        return;
    L79:
        return;
    L82:
        return;
    }

    private static char peekCharAtPosition(ParsableByteArray r0, int r1) {
        return (char) r0.getData()[r1];
    }

    private static String readCueTarget(ParsableByteArray r5) {
        int r0 = r5.getPosition();
        int r1 = r5.limit();
        boolean r3 = false;
    L3:
        if (r0 >= r1) goto L11;
        if (r3 == true) goto L11;
        int r4 = r0 + 1;
        if (((char) r5.getData()[r0]) != ')') goto L8;
        r3 = true;
    L9:
        r0 = r4;
        goto L3
    L8:
        r3 = false;
    L11:
        return r5.readString((r0 - 1) - r5.getPosition()).trim();
    }

    public static void skipStyleBlock(ParsableByteArray r1) {
    L3:
        if (TextUtils.isEmpty(r1.readLine()) == false) goto L3;
    }

    public static void skipWhitespaceAndComments(ParsableByteArray r3) {
    L3:
        boolean r1 = true;
    L5:
        if (r3.bytesLeft() <= 0) goto L13;
        if (r1 == false) goto L19;
        if (maybeSkipWhitespace(r3) == true) goto L3;
        if (maybeSkipComment(r3) == true) goto L3;
        r1 = false;
        goto L5
    L19:
        return;
    }

    public List<WebvttCssStyle> parseBlock(ParsableByteArray r7) {
        this.stringBuilder.setLength(0);
        int r0 = r7.getPosition();
        skipStyleBlock(r7);
        this.styleInput.reset(r7.getData(), r7.getPosition());
        this.styleInput.setPosition(r0);
        ArrayList r72 = new ArrayList();
    L3:
        String r02 = parseSelector(this.styleInput, this.stringBuilder);
        if (r02 == null) goto L24;
        if ("{".equals(parseNextToken(this.styleInput, this.stringBuilder)) == false) goto L24;
        WebvttCssStyle r2 = new WebvttCssStyle();
        applySelectorToStyle(r2, r02);
        String r03 = null;
        boolean r3 = false;
    L10:
        if (r3 == true) goto L22;
        int r04 = this.styleInput.getPosition();
        String r32 = parseNextToken(this.styleInput, this.stringBuilder);
        if (r32 != null) goto L14;
    L17:
        boolean r4 = true;
    L18:
        if (r4 == true) goto L20;
        this.styleInput.setPosition(r04);
        parseStyleDeclaration(this.styleInput, r2, this.stringBuilder);
    L20:
        r03 = r32;
        r3 = r4;
        goto L10
    L14:
        if ("}".equals(r32) == true) goto L17;
        r4 = false;
        goto L18
    L22:
        if ("}".equals(r03) == false) goto L3;
        r72.add(r2);
    L24:
        return r72;
    }
}
