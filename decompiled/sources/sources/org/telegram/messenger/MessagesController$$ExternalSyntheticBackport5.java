package org.telegram.messenger;

import kotlin.UByte$$ExternalSyntheticBackport2;
import kotlin.UByte$$ExternalSyntheticBackport3;

/* loaded from: classes5.dex */
public abstract /* synthetic */ class MessagesController$$ExternalSyntheticBackport5 {
    public static /* synthetic */ long m(CharSequence r16, int r17, int r18, int r19) {
        int r2 = r18 - r17;
        if (r2 == 0) goto L34;
        if (r19 < 2) goto L32;
        if (r19 > 36) goto L32;
        long r3 = r19;
        long r7 = UByte$$ExternalSyntheticBackport3.m(-1, r3);
        if (r16.charAt(r17) == '+') goto L11;
    L13:
        int r22 = r17;
    L14:
        long r9 = 0;
        long r11 = 0;
    L15:
        if (r22 >= r18) goto L30;
        int r14 = Character.digit(r16.charAt(r22), r19);
        if (r14 == (-1)) goto L29;
        if (r11 < r9) goto L27;
        if (r11 > r7) goto L27;
        if (r11 != r7) goto L25;
        if (r14 > ((int) UByte$$ExternalSyntheticBackport2.m(-1, r3))) goto L27;
    L25:
        r11 = (r11 * r3) + r14;
        r22 = r22 + 1;
        r9 = 0;
    L27:
        throw new NumberFormatException("Too large for unsigned long: ".concat(r16.toString()));
    L29:
        throw new NumberFormatException(r16.toString());
    L30:
        return r11;
    L11:
        if (r2 <= 1) goto L13;
        r22 = r17 + 1;
    L32:
        throw new NumberFormatException("illegal radix: ".concat(String.valueOf(r19)));
    L34:
        throw new NumberFormatException("empty string");
    }
}
