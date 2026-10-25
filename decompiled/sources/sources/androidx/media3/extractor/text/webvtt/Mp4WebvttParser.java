package androidx.media3.extractor.text.webvtt;

import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.ArrayList;
import java.util.Collections;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class Mp4WebvttParser implements SubtitleParser {
    private final ParsableByteArray parsableByteArray;

    public Mp4WebvttParser() {
        this.parsableByteArray = new ParsableByteArray();
    }

    private static Cue parseVttCueBox(ParsableByteArray r7, int r8) {
        CharSequence r1 = null;
        Cue.Builder r2 = null;
    L3:
        if (r8 <= 0) goto L14;
        if (r8 < 8) goto L7;
        boolean r4 = true;
    L8:
        Assertions.checkArgument(r4, "Incomplete vtt cue box header found.");
        int r42 = r7.readInt();
        int r5 = r7.readInt();
        int r43 = r42 - 8;
        String r3 = Util.fromUtf8Bytes(r7.getData(), r7.getPosition(), r43);
        r7.skipBytes(r43);
        r8 = (r8 - 8) - r43;
        if (r5 == 1937011815) goto L10;
        if (r5 != 1885436268) goto L3;
        r1 = WebvttCueParser.parseCueText(null, r3.trim(), Collections.EMPTY_LIST);
        goto L3
    L10:
        r2 = WebvttCueParser.parseCueSettingsList(r3);
        goto L3
    L7:
        r4 = false;
        goto L8
    L14:
        if (r1 != null) goto L16;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L16:
        if (r2 == null) goto L20;
        return r2.setText(r1).build();
    L20:
        return WebvttCueParser.newCueForText(r1);
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public int getCueReplacementBehavior() {
        return 2;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public void parse(byte[] r7, int r8, int r9, SubtitleParser.OutputOptions r10, Consumer<CuesWithTiming> r11) {
        this.parsableByteArray.reset(r7, r9 + r8);
        this.parsableByteArray.setPosition(r8);
        ArrayList r1 = new ArrayList();
    L4:
        if (this.parsableByteArray.bytesLeft() <= 0) goto L13;
        if (this.parsableByteArray.bytesLeft() < 8) goto L8;
        boolean r72 = true;
    L9:
        Assertions.checkArgument(r72, "Incomplete Mp4Webvtt Top Level box header found.");
        int r73 = this.parsableByteArray.readInt();
        int r82 = this.parsableByteArray.readInt();
        ParsableByteArray r92 = this.parsableByteArray;
        if (r82 == 1987343459) goto L11;
        r92.skipBytes(r73 - 8);
        goto L4
    L11:
        r1.add(parseVttCueBox(r92, r73 - 8));
        goto L4
    L8:
        r72 = false;
        goto L9
    L13:
        r11.accept(new CuesWithTiming(r1, -9223372036854775807L, -9223372036854775807L));
    }
}
