package androidx.media3.extractor.text.webvtt;

import android.text.TextUtils;
import androidx.core.app.NavUtils$$ExternalSyntheticBUOutline0;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.LegacySubtitleUtil;
import androidx.media3.extractor.text.SubtitleParser;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class WebvttParser implements SubtitleParser {
    private final WebvttCssParser cssParser;
    private final ParsableByteArray parsableWebvttData;

    public WebvttParser() {
        this.parsableWebvttData = new ParsableByteArray();
        this.cssParser = new WebvttCssParser();
    }

    private static int getNextEvent(ParsableByteArray r5) {
        int r2 = -1;
        int r3 = 0;
    L3:
        if (r2 != (-1)) goto L14;
        r3 = r5.getPosition();
        String r22 = r5.readLine();
        if (r22 == null) goto L6;
        if ("STYLE".equals(r22) == true) goto L9;
        if (r22.startsWith("NOTE") == true) goto L12;
        r2 = 3;
        goto L3
    L12:
        r2 = 1;
        goto L3
    L9:
        r2 = 2;
        goto L3
    L6:
        r2 = 0;
        goto L3
    L14:
        r5.setPosition(r3);
        return r2;
    }

    private static void skipComment(ParsableByteArray r1) {
    L3:
        if (TextUtils.isEmpty(r1.readLine()) == false) goto L3;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public int getCueReplacementBehavior() {
        return 1;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public void parse(byte[] r2, int r3, int r4, SubtitleParser.OutputOptions r5, Consumer<CuesWithTiming> r6) {
        this.parsableWebvttData.reset(r2, r4 + r3);
        this.parsableWebvttData.setPosition(r3);
        ArrayList r22 = new ArrayList();
        WebvttParserUtil.validateWebvttHeaderLine(this.parsableWebvttData);     // Catch: ParserException -> L27
    L5:
        if (TextUtils.isEmpty(this.parsableWebvttData.readLine()) == false) goto L5;
        ArrayList r32 = new ArrayList();
    L8:
        int r42 = getNextEvent(this.parsableWebvttData);
        if (r42 == 0) goto L25;
        if (r42 == 1) goto L12;
        if (r42 == 2) goto L16;
        if (r42 != 3) goto L8;
        WebvttCueInfo r43 = WebvttCueParser.parseCue(this.parsableWebvttData, r22);
        if (r43 == null) goto L8;
        r32.add(r43);
        goto L8
    L16:
        if (r32.isEmpty() == false) goto L18;
        this.parsableWebvttData.readLine();
        r22.addAll(this.cssParser.parseBlock(this.parsableWebvttData));
        goto L8
    L18:
        f$$ExternalSyntheticBUOutline1.m("A style block was found after the first cue.");
        return;
    L12:
        skipComment(this.parsableWebvttData);
        goto L8
    L25:
        LegacySubtitleUtil.toCuesWithTiming(new WebvttSubtitle(r32), r5, r6);
        return;
    L27:
        e = move-exception;
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
    }
}
