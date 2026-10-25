package androidx.core.util;

import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes3.dex */
public abstract class TimeUtils {
    private static char[] sFormatStr;
    private static final Object sFormatSync = null;

    static {
        sFormatSync = new Object();
        sFormatStr = new char[24];
    }

    private static int accumField(int r2, int r3, boolean r4, int r5) {
        if (r2 > 99) goto L22;
        if (r4 == false) goto L8;
        if (r5 >= 3) goto L22;
    L8:
        if (r2 > 9) goto L20;
        if (r4 == false) goto L12;
        if (r5 >= 2) goto L20;
    L12:
        if (r4 == true) goto L18;
        if (r2 > 0) goto L18;
        return 0;
    L18:
        return r3 + 1;
    L20:
        return r3 + 2;
    L22:
        return r3 + 3;
    }

    public static void formatDuration(long r2, StringBuilder r4) {
        Object r0 = sFormatSync;
        monitor-enter(r0);
        int r22 = formatDurationLocked(r2, 0);     // Catch: Throwable -> L8
        r4.append(sFormatStr, 0, r22);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    private static int formatDurationLocked(long r16, int r18) {
        long r0 = r16;
        if (sFormatStr.length >= r18) goto L5;
        sFormatStr = new char[r18];
    L5:
        char[] r4 = sFormatStr;
        if (r0 != 0) goto L12;
        int r02 = r18 - 1;
    L8:
        if (r02 <= 0) goto L10;
        r4[0] = ' ';
        goto L8
    L10:
        r4[0] = '0';
        return 1;
    L12:
        if (r0 <= 0) goto L14;
        char r3 = SignatureVisitor.EXTENDS;
    L15:
        int r12 = (int) (r0 % 1000);
        int r03 = (int) Math.floor(r0 / 1000);
        if (r03 <= 86400) goto L18;
        int r6 = r03 / 86400;
        r03 = r03 - (86400 * r6);
    L20:
        if (r03 <= 3600) goto L22;
        int r1 = r03 / 3600;
        r03 = r03 - (r1 * 3600);
    L24:
        if (r03 <= 60) goto L26;
        int r7 = r03 / 60;
        r03 = r03 - (r7 * 60);
        int r13 = r7;
    L28:
        if (r18 == 0) goto L48;
        int r72 = accumField(r6, 1, false, 0);
        if (r72 <= 0) goto L32;
        boolean r8 = true;
    L33:
        int r73 = r72 + accumField(r1, 1, r8, 2);
        if (r73 <= 0) goto L36;
        boolean r82 = true;
    L37:
        int r74 = r73 + accumField(r13, 1, r82, 2);
        if (r74 <= 0) goto L40;
        boolean r83 = true;
    L41:
        int r75 = r74 + accumField(r03, 1, r83, 2);
        if (r75 <= 0) goto L44;
        int r84 = 3;
    L45:
        int r76 = r75 + (accumField(r12, 2, true, r84) + 1);
        int r85 = 0;
    L46:
        if (r76 >= r18) goto L49;
        r4[r85] = ' ';
        r85 = r85 + 1;
        r76 = r76 + 1;
    L49:
        r4[r85] = r3;
        int r77 = r85 + 1;
        if (r18 == 0) goto L52;
        boolean r2 = true;
    L53:
        int r32 = printField(r4, r6, 'd', r77, false, 0);
        if (r32 == r77) goto L56;
        boolean r86 = true;
    L57:
        if (r2 == false) goto L59;
        int r9 = 2;
    L60:
        int r78 = printField(r4, r1, 'h', r32, r86, r9);
        if (r78 == r77) goto L63;
        boolean r87 = true;
    L64:
        if (r2 == false) goto L66;
        int r92 = 2;
    L67:
        int r79 = printField(r4, r13, 'm', r78, r87, r92);
        if (r79 == r77) goto L70;
        boolean r88 = true;
    L71:
        if (r2 == false) goto L73;
        int r93 = 2;
    L74:
        int r710 = printField(r4, r03, 's', r79, r88, r93);
        if (r2 == false) goto L78;
        if (r710 == r77) goto L78;
        int r94 = 3;
    L79:
        int r04 = printField(r4, r12, 'm', r710, true, r94);
        r4[r04] = 's';
        return r04 + 1;
    L78:
        r94 = 0;
        goto L79
    L73:
        r93 = 0;
        goto L74
    L70:
        r88 = false;
        goto L71
    L66:
        r92 = 0;
        goto L67
    L63:
        r87 = false;
        goto L64
    L59:
        r9 = 0;
        goto L60
    L56:
        r86 = false;
        goto L57
    L52:
        r2 = false;
        goto L53
    L44:
        r84 = 0;
        goto L45
    L40:
        r83 = false;
        goto L41
    L36:
        r82 = false;
        goto L37
    L32:
        r8 = false;
        goto L33
    L48:
        r85 = 0;
        goto L49
    L26:
        r13 = 0;
        goto L28
    L22:
        r1 = 0;
        goto L24
    L18:
        r6 = 0;
        goto L20
    L14:
        r0 = -r0;
        r3 = SignatureVisitor.SUPER;
        goto L15
    }

    private static int printField(char[] r2, int r3, char r4, int r5, boolean r6, int r7) {
        if (r6 == true) goto L6;
        if (r3 > 0) goto L6;
        return r5;
    L6:
        if (r6 == false) goto L10;
        if (r7 < 3) goto L10;
    L11:
        int r0 = r3 / 100;
        r2[r5] = (char) (r0 + 48);
        int r1 = r5 + 1;
        r3 = r3 - (r0 * 100);
    L14:
        if (r6 == false) goto L17;
        if (r7 < 2) goto L17;
    L19:
        int r52 = r3 / 10;
        r2[r1] = (char) (r52 + 48);
        r1 = r1 + 1;
        r3 = r3 - (r52 * 10);
    L20:
        r2[r1] = (char) (r3 + 48);
        r2[r1 + 1] = r4;
        return r1 + 2;
    L17:
        if (r3 > 9) goto L19;
        if (r5 == r1) goto L20;
    L10:
        if (r3 > 99) goto L11;
        r1 = r5;
        goto L14
    }
}
