package j$.sun.nio.cs;

import java.nio.CharBuffer;
import java.nio.charset.CoderResult;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    public CoderResult f276a;

    /* renamed from: b, reason: collision with root package name */
    public boolean f277b;

    public final int a(char r5, CharBuffer r6) {
        if (Character.isHighSurrogate(r5) == false) goto L15;
        if (r6.hasRemaining() == true) goto L8;
        this.f276a = CoderResult.UNDERFLOW;
        return -1;
    L8:
        char r62 = r6.get();
        if (Character.isLowSurrogate(r62) == false) goto L12;
        int r52 = Character.toCodePoint(r5, r62);
        this.f277b = true;
        this.f276a = null;
        return r52;
    L12:
        this.f276a = CoderResult.malformedForLength(1);
        return -1;
    L15:
        if (Character.isLowSurrogate(r5) == false) goto L18;
        this.f276a = CoderResult.malformedForLength(1);
        return -1;
    L18:
        this.f277b = false;
        this.f276a = null;
        return r5;
    }

    public final int b(char r5, char[] r6, int r7, int r8) {
        if (Character.isHighSurrogate(r5) == false) goto L15;
        if ((r8 - r7) >= 2) goto L8;
        this.f276a = CoderResult.UNDERFLOW;
        return -1;
    L8:
        char r62 = r6[r7 + 1];
        if (Character.isLowSurrogate(r62) == false) goto L12;
        int r52 = Character.toCodePoint(r5, r62);
        this.f277b = true;
        this.f276a = null;
        return r52;
    L12:
        this.f276a = CoderResult.malformedForLength(1);
        return -1;
    L15:
        if (Character.isLowSurrogate(r5) == false) goto L18;
        this.f276a = CoderResult.malformedForLength(1);
        return -1;
    L18:
        this.f277b = false;
        this.f276a = null;
        return r5;
    }
}
