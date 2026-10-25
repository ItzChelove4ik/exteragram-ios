package androidx.media3.extractor.text.ssa;

import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import com.google.common.base.Ascii;

/* loaded from: classes4.dex */
final class SsaDialogueFormat {
    public final int endTimeIndex;
    public final int layerIndex;
    public final int length;
    public final int startTimeIndex;
    public final int styleIndex;
    public final int textIndex;

    private SsaDialogueFormat(int r1, int r2, int r3, int r4, int r5, int r6) {
        this.layerIndex = r1;
        this.startTimeIndex = r2;
        this.endTimeIndex = r3;
        this.styleIndex = r4;
        this.textIndex = r5;
        this.length = r6;
    }

    public static SsaDialogueFormat fromFormatLine(String r10) {
        Assertions.checkArgument(r10.startsWith("Format:"));
        String[] r102 = TextUtils.split(r10.substring(7), ",");
        int r4 = -1;
        int r5 = -1;
        int r6 = -1;
        int r7 = -1;
        int r8 = -1;
        int r2 = 0;
    L4:
        if (r2 >= r102.length) goto L36;
        String r3 = Ascii.toLowerCase(r102[r2].trim());
        r3.getClass();
        switch(r3.hashCode()) {
            case 100571: goto L25;
            case 3556653: goto L21;
            case 102749521: goto L17;
            case 109757538: goto L13;
            case 109780401: goto L9;
            default: goto L7;
        };
    L7:
        char r32 = 65535;
    L28:
        switch(r32) {
            case 0: goto L34;
            case 1: goto L33;
            case 2: goto L32;
            case 3: goto L31;
            case 4: goto L30;
            default: goto L35;
        };
    L30:
        r7 = r2;
        goto L35
    L31:
        r5 = r2;
        goto L35
    L32:
        r4 = r2;
        goto L35
    L33:
        r8 = r2;
        goto L35
    L34:
        r6 = r2;
    L35:
        r2 = r2 + 1;
        goto L4
    L9:
        if (r3.equals("style") == false) goto L7;
        r32 = 4;
        goto L28
    L13:
        if (r3.equals("start") == false) goto L7;
        r32 = 3;
        goto L28
    L17:
        if (r3.equals("layer") == false) goto L7;
        r32 = 2;
        goto L28
    L21:
        if (r3.equals("text") == false) goto L7;
        r32 = 1;
        goto L28
    L25:
        if (r3.equals("end") == false) goto L7;
        r32 = 0;
        goto L28
    L36:
        if (r5 == (-1)) goto L41;
        if (r6 == (-1)) goto L50;
        if (r8 != (-1)) goto L40;
        return null;
    L40:
        return new SsaDialogueFormat(r4, r5, r6, r7, r8, r102.length);
    L50:
        return null;
    L41:
        return null;
    }
}
