package androidx.media3.extractor.text;

import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Consumer;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.List;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class LegacySubtitleUtil {
    private static int getStartIndex(Subtitle r4, long r5) {
        if (r5 != (-9223372036854775807L)) goto L6;
        return 0;
    L6:
        int r0 = r4.getNextEventTimeIndex(r5);
        if (r0 != (-1)) goto L9;
        r0 = r4.getEventTimeCount();
    L9:
        if (r0 > 0) goto L11;
        return r0;
    L11:
        if (r4.getEventTime(r0 - 1) == r5) goto L13;
        return r0;
    L13:
        return r0 - 1;
    }

    private static void outputSubtitleEvent(Subtitle r6, int r7, Consumer<CuesWithTiming> r8) {
        long r2 = r6.getEventTime(r7);
        List<Cue> r1 = r6.getCues(r2);
        if (r1.isEmpty() == false) goto L6;
        return;
    L6:
        if (r7 == (r6.getEventTimeCount() - 1)) goto L11;
        long r4 = r6.getEventTime(r7 + 1) - r6.getEventTime(r7);
        if (r4 <= 0) goto L13;
        r8.accept(new CuesWithTiming(r1, r2, r4));
        return;
    L13:
        return;
    L11:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }

    public static void toCuesWithTiming(Subtitle r11, SubtitleParser.OutputOptions r12, Consumer<CuesWithTiming> r13) {
        int r0 = getStartIndex(r11, r12.startTimeUs);
        int r2 = 0;
        if (r12.startTimeUs != (-9223372036854775807L)) goto L5;
    L11:
        boolean r1 = false;
    L12:
        int r3 = r0;
    L14:
        if (r3 >= r11.getEventTimeCount()) goto L17;
        outputSubtitleEvent(r11, r3, r13);
        r3 = r3 + 1;
        goto L14
    L17:
        if (r12.outputAllCues == false) goto L28;
        if (r1 == false) goto L20;
        r0 = r0 - 1;
    L20:
        if (r2 >= r0) goto L22;
        outputSubtitleEvent(r11, r2, r13);
        r2 = r2 + 1;
        goto L20
    L22:
        if (r1 == false) goto L29;
        r13.accept(new CuesWithTiming(r11.getCues(r12.startTimeUs), r11.getEventTime(r0), r12.startTimeUs - r11.getEventTime(r0)));
        return;
    L29:
        return;
    L28:
        return;
    L5:
        if (r0 >= r11.getEventTimeCount()) goto L11;
        List<Cue> r6 = r11.getCues(r12.startTimeUs);
        long r32 = r11.getEventTime(r0);
        if (r6.isEmpty() == true) goto L11;
        long r7 = r12.startTimeUs;
        if (r7 >= r32) goto L11;
        r13.accept(new CuesWithTiming(r6, r7, r32 - r7));
        r1 = true;
        goto L12
    }
}
