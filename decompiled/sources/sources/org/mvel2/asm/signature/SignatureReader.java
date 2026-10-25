package org.mvel2.asm.signature;

import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class SignatureReader {
    private final String signatureValue;

    public SignatureReader(String r1) {
        this.signatureValue = r1;
    }

    private static int parseType(String r9, int r10, SignatureVisitor r11) {
        int r0 = r10 + 1;
        char r102 = r9.charAt(r10);
        if (r102 != 'F') goto L5;
    L61:
        r11.visitBaseType(r102);
        return r0;
    L5:
        if (r102 != 'L') goto L7;
        int r103 = r0;
        boolean r1 = false;
        boolean r5 = false;
    L28:
        int r6 = r0 + 1;
        char r7 = r9.charAt(r0);
        if (r7 == '.') goto L52;
        if (r7 == ';') goto L52;
        if (r7 == '<') goto L34;
        r0 = r6;
        goto L28
    L34:
        String r02 = r9.substring(r103, r0);
        if (r5 == false) goto L37;
        r11.visitInnerClassType(r02);
    L38:
        r0 = r6;
    L39:
        char r12 = r9.charAt(r0);
        if (r12 == '>') goto L50;
        if (r12 != '*') goto L44;
        r0 = r0 + 1;
        r11.visitTypeArgument();
        goto L39
    L44:
        if (r12 == '+') goto L48;
        if (r12 == '-') goto L48;
        r0 = parseType(r9, r0, r11.visitTypeArgument(SignatureVisitor.INSTANCEOF));
    L48:
        r0 = parseType(r9, r0 + 1, r11.visitTypeArgument(r12));
        goto L39
    L50:
        r1 = true;
        goto L28
    L37:
        r11.visitClassType(r02);
    L52:
        if (r1 == true) goto L57;
        String r104 = r9.substring(r103, r0);
        if (r5 == false) goto L56;
        r11.visitInnerClassType(r104);
        goto L57
    L56:
        r11.visitClassType(r104);
    L57:
        if (r7 == ';') goto L58;
        r1 = false;
        r5 = true;
        r103 = r6;
        r0 = r103;
        goto L28
    L58:
        r11.visitEnd();
        return r6;
    L7:
        if (r102 == 'V') goto L61;
        if (r102 == 'I') goto L61;
        if (r102 == 'J') goto L61;
        if (r102 == 'S') goto L61;
        if (r102 != 'T') goto L17;
        int r105 = r9.indexOf(59, r0);
        r11.visitTypeVariable(r9.substring(r0, r105));
        return r105 + 1;
    L17:
        if (r102 == 'Z') goto L61;
        if (r102 == '[') goto L24;
        switch(r102) {
            case 66: goto L61;
            case 67: goto L61;
            case 68: goto L61;
            default: goto L21;
        };
    L21:
        Segment$$ExternalSyntheticBUOutline0.m();
        return 0;
    L24:
        return parseType(r9, r0, r11.visitArrayType());
    }

    public void accept(SignatureVisitor r6) {
        String r5 = this.signatureValue;
        int r0 = r5.length();
        int r1 = 0;
        if (r5.charAt(0) != '<') goto L18;
        r1 = 2;
    L5:
        int r3 = r5.indexOf(58, r1);
        r6.visitFormalTypeParameter(r5.substring(r1 - 1, r3));
        int r32 = r3 + 1;
        char r12 = r5.charAt(r32);
        if (r12 != 'L') goto L8;
    L11:
        r32 = parseType(r5, r32, r6.visitClassBound());
    L12:
        r1 = r32 + 1;
        char r33 = r5.charAt(r32);
        if (r33 != ':') goto L16;
        r32 = parseType(r5, r1, r6.visitInterfaceBound());
        goto L12
    L16:
        if (r33 != '>') goto L5;
    L8:
        if (r12 == '[') goto L11;
        if (r12 != 'T') goto L12;
    L18:
        if (r5.charAt(r1) != '(') goto L26;
        int r13 = r1 + 1;
    L21:
        if (r5.charAt(r13) == ')') goto L23;
        r13 = parseType(r5, r13, r6.visitParameterType());
        goto L21
    L23:
        int r14 = parseType(r5, r13 + 1, r6.visitReturnType());
    L24:
        if (r14 >= r0) goto L29;
        r14 = parseType(r5, r14 + 1, r6.visitExceptionType());
        goto L24
    L29:
        return;
    L26:
        int r15 = parseType(r5, r1, r6.visitSuperclass());
    L27:
        if (r15 >= r0) goto L37;
        r15 = parseType(r5, r15, r6.visitInterface());
        goto L27
    }

    public void acceptType(SignatureVisitor r2) {
        parseType(this.signatureValue, 0, r2);
    }
}
