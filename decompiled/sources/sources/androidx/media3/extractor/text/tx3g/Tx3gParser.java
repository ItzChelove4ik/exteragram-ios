package androidx.media3.extractor.text.tx3g;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.collect.ImmutableList;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import kotlin.UByte;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class Tx3gParser implements SubtitleParser {
    private final int calculatedVideoTrackHeight;
    private final boolean customVerticalPlacement;
    private final int defaultColorRgba;
    private final int defaultFontFace;
    private final String defaultFontFamily;
    private final float defaultVerticalPlacement;
    private final ParsableByteArray parsableByteArray;

    public Tx3gParser(List<byte[]> r7) {
        this.parsableByteArray = new ParsableByteArray();
        String r2 = "sans-serif";
        boolean r3 = false;
        if (r7.size() == 1) goto L5;
    L20:
        this.defaultFontFace = 0;
        this.defaultColorRgba = -1;
        this.defaultFontFamily = "sans-serif";
        this.customVerticalPlacement = false;
        this.defaultVerticalPlacement = 0.85f;
        this.calculatedVideoTrackHeight = -1;
        return;
    L5:
        if (r7.get(0).length != 48) goto L7;
    L8:
        byte[] r72 = r7.get(0);
        this.defaultFontFace = r72[24];
        this.defaultColorRgba = ((((r72[26] & UByte.MAX_VALUE) << 24) | ((r72[27] & UByte.MAX_VALUE) << 16)) | ((r72[28] & UByte.MAX_VALUE) << 8)) | (r72[29] & UByte.MAX_VALUE);
        if ("Serif".equals(Util.fromUtf8Bytes(r72, 43, r72.length - 43)) == false) goto L11;
        r2 = "serif";
    L11:
        this.defaultFontFamily = r2;
        int r0 = r72[25] * 20;
        this.calculatedVideoTrackHeight = r0;
        if ((r72[0] & 32) == 0) goto L14;
        r3 = true;
    L14:
        this.customVerticalPlacement = r3;
        if (r3 == false) goto L18;
        int r1 = (r72[10] & UByte.MAX_VALUE) << 8;
        this.defaultVerticalPlacement = Util.constrainValue(((r72[11] & UByte.MAX_VALUE) | r1) / r0, 0.0f, 0.95f);
        return;
    L18:
        this.defaultVerticalPlacement = 0.85f;
        return;
    L7:
        if (r7.get(0).length != 53) goto L20;
        goto L8
    }

    private void applyStyleRecord(ParsableByteArray r10, SpannableStringBuilder r11) {
        if (r10.bytesLeft() < 12) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        int r6 = r10.readUnsignedShort();
        int r02 = r10.readUnsignedShort();
        r10.skipBytes(2);
        int r4 = r10.readUnsignedByte();
        r10.skipBytes(1);
        int r102 = r10.readInt();
        if (r02 <= r11.length()) goto L9;
        Log.w("Tx3gParser", "Truncating styl end (" + r02 + ") to cueText.length() (" + r11.length() + ").");
        r02 = r11.length();
    L9:
        int r7 = r02;
        if (r6 < r7) goto L13;
        Log.w("Tx3gParser", "Ignoring styl with start (" + r6 + ") >= end (" + r7 + ").");
        return;
    L13:
        attachFontFace(r11, r4, this.defaultFontFace, r6, r7, 0);
        attachColor(r11, r102, this.defaultColorRgba, r6, r7, 0);
        return;
    L5:
        r0 = false;
        goto L6
    }

    private static void attachColor(SpannableStringBuilder r0, int r1, int r2, int r3, int r4, int r5) {
        if (r1 == r2) goto L5;
        int r22 = (r1 & 255) << 24;
        r0.setSpan(new ForegroundColorSpan((r1 >>> 8) | r22), r3, r4, r5 | 33);
        return;
    }

    private static void attachFontFace(SpannableStringBuilder r5, int r6, int r7, int r8, int r9, int r10) {
        if (r6 == r7) goto L28;
        int r72 = r10 | 33;
        boolean r1 = true;
        if ((r6 & 1) == 0) goto L6;
        boolean r102 = true;
    L8:
        if ((r6 & 2) == 0) goto L10;
        boolean r2 = true;
    L11:
        if (r102 == false) goto L15;
        if (r2 == false) goto L14;
        r5.setSpan(new StyleSpan(3), r8, r9, r72);
    L18:
        if ((r6 & 4) != 0) goto L21;
        r1 = false;
    L21:
        if (r1 == false) goto L23;
        r5.setSpan(new UnderlineSpan(), r8, r9, r72);
    L23:
        if (r1 == true) goto L29;
        if (r102 == true) goto L30;
        if (r2 == true) goto L31;
        r5.setSpan(new StyleSpan(0), r8, r9, r72);
        return;
    L31:
        return;
    L30:
        return;
    L29:
        return;
    L14:
        r5.setSpan(new StyleSpan(1), r8, r9, r72);
        goto L18
    L15:
        if (r2 == false) goto L18;
        r5.setSpan(new StyleSpan(2), r8, r9, r72);
        goto L18
    L10:
        r2 = false;
        goto L11
    L6:
        r102 = false;
        goto L8
    }

    private static void attachFontFamily(SpannableStringBuilder r1, String r2, int r3, int r4) {
        if (r2 == "sans-serif") goto L6;
        r1.setSpan(new TypefaceSpan(r2), r3, r4, 16711713);
        return;
    }

    private static String readSubtitleText(ParsableByteArray r4) {
        if (r4.bytesLeft() < 2) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        int r02 = r4.readUnsignedShort();
        if (r02 != 0) goto L10;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        int r1 = r4.getPosition();
        Charset r2 = r4.readUtfCharsetFromBom();
        int r03 = r02 - (r4.getPosition() - r1);
        if (r2 != null) goto L15;
        r2 = StandardCharsets.UTF_8;
    L15:
        return r4.readString(r03, r2);
    L5:
        r0 = false;
        goto L6
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public int getCueReplacementBehavior() {
        return 2;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public void parse(byte[] r8, int r9, int r10, SubtitleParser.OutputOptions r11, Consumer<CuesWithTiming> r12) {
        this.parsableByteArray.reset(r8, r10 + r9);
        this.parsableByteArray.setPosition(r9);
        String r82 = readSubtitleText(this.parsableByteArray);
        if (r82.isEmpty() == false) goto L6;
        r12.accept(new CuesWithTiming(ImmutableList.of(), -9223372036854775807L, -9223372036854775807L));
        return;
    L6:
        SpannableStringBuilder r1 = new SpannableStringBuilder(r82);
        attachFontFace(r1, this.defaultFontFace, 0, 0, r1.length(), 16711680);
        attachColor(r1, this.defaultColorRgba, -1, 0, r1.length(), 16711680);
        attachFontFamily(r1, this.defaultFontFamily, 0, r1.length());
        float r83 = this.defaultVerticalPlacement;
    L8:
        if (this.parsableByteArray.bytesLeft() < 8) goto L28;
        int r92 = this.parsableByteArray.getPosition();
        int r112 = this.parsableByteArray.readInt();
        int r0 = this.parsableByteArray.readInt();
        boolean r4 = true;
        if (r0 != 1937013100) goto L19;
        if (this.parsableByteArray.bytesLeft() >= 2) goto L15;
        r4 = false;
    L15:
        Assertions.checkArgument(r4);
        int r02 = this.parsableByteArray.readUnsignedShort();
        int r2 = 0;
    L16:
        if (r2 >= r02) goto L27;
        applyStyleRecord(this.parsableByteArray, r1);
        r2 = r2 + 1;
    L27:
        this.parsableByteArray.setPosition(r92 + r112);
        goto L8
    L19:
        if (r0 != 1952608120) goto L27;
        if (this.customVerticalPlacement == false) goto L27;
        if (this.parsableByteArray.bytesLeft() >= 2) goto L26;
        r4 = false;
    L26:
        Assertions.checkArgument(r4);
        r83 = Util.constrainValue(this.parsableByteArray.readUnsignedShort() / this.calculatedVideoTrackHeight, 0.0f, 0.95f);
        goto L27
    L28:
        r12.accept(new CuesWithTiming(ImmutableList.of(new Cue.Builder().setText(r1).setLine(r83, 0).setLineAnchor(0).build()), -9223372036854775807L, -9223372036854775807L));
    }
}
