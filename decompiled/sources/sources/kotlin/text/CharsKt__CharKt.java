package kotlin.text;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.io.encoding.Base64$$ExternalSyntheticBUOutline0;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\b\n\u0002\u0010\f\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\u0080\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0080\u0004\u001a\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0005\u001a\u001d\u0010\u0004\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0006\u001a\u000e\u0010\u0007\u001a\u00020\u0002*\u00020\u0001H\u0087\u0080\u0004\u001a\u0016\u0010\u0007\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0080\u0004\u001a\u000e\u0010\b\u001a\u00020\t*\u00020\u0002H\u0087\u0080\u0004\u001a\u0016\u0010\n\u001a\u00020\t*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\tH\u0087\u008a\u0004\u001a \u0010\f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\rH\u0086\u0080\u0004\u001a\u000e\u0010\u000f\u001a\u00020\r*\u00020\u0002H\u0086\u0080\u0004¨\u0006\u0010"}, d2 = {"digitToInt", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "radix", "digitToIntOrNull", "(C)Ljava/lang/Integer;", "(CI)Ljava/lang/Integer;", "digitToChar", "titlecase", _UrlKt.FRAGMENT_ENCODE_SET, "plus", "other", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "ignoreCase", "isSurrogate", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/text/CharsKt")
@SourceDebugExtension({"SMAP\nChar.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Char.kt\nkotlin/text/CharsKt__CharKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,339:1\n1#2:340\n*E\n"})
/* loaded from: classes.dex */
class CharsKt__CharKt extends CharsKt__CharJVMKt {
    public CharsKt__CharKt() {
    }

    @SinceKotlin(version = "1.5")
    public static final char digitToChar(int r2) {
        if (r2 >= 0) goto L4;
    L7:
        CharsKt__CharKt$$ExternalSyntheticBUOutline0.m("Int ", r2, " is not a decimal digit");
        return 0;
    L4:
        if (r2 >= 10) goto L7;
        return (char) (r2 + 48);
    }

    @SinceKotlin(version = "1.5")
    public static final char digitToChar(int r2, int r3) {
        if (2 <= r3) goto L5;
    L16:
        CharsKt__CharKt$$ExternalSyntheticBUOutline0.m("Invalid radix: ", r3, ". Valid radix values are in range 2..36");
        return 0;
    L5:
        if (r3 >= 37) goto L16;
        if (r2 < 0) goto L14;
        if (r2 >= r3) goto L14;
        if (r2 >= 10) goto L13;
        return (char) (r2 + 48);
    L13:
        return (char) (((char) (r2 + 65)) - '\n');
    L14:
        Base64$$ExternalSyntheticBUOutline0.m("Digit ", r2, " does not represent a valid digit in radix ", r3);
        return 0;
    }

    @SinceKotlin(version = "1.5")
    public static final int digitToInt(char r3) {
        int r0 = CharsKt__CharJVMKt.digitOf(r3, 10);
        if (r0 < 0) goto L6;
        return r0;
    L6:
        throw new IllegalArgumentException("Char " + r3 + " is not a decimal digit");
    }

    @SinceKotlin(version = "1.5")
    public static final int digitToInt(char r3, int r4) {
        Integer r0 = digitToIntOrNull(r3, r4);
        if (r0 == null) goto L7;
        return r0.intValue();
    L7:
        throw new IllegalArgumentException("Char " + r3 + " is not a digit in the given radix=" + r4);
    }

    @SinceKotlin(version = "1.5")
    public static final Integer digitToIntOrNull(char r1) {
        Integer r12 = Integer.valueOf(CharsKt__CharJVMKt.digitOf(r1, 10));
        if (r12.intValue() < 0) goto L5;
        return r12;
    L5:
        return null;
    }

    @SinceKotlin(version = "1.5")
    public static final Integer digitToIntOrNull(char r0, int r1) {
        CharsKt__CharJVMKt.checkRadix(r1);
        Integer r02 = Integer.valueOf(CharsKt__CharJVMKt.digitOf(r0, r1));
        if (r02.intValue() < 0) goto L5;
        return r02;
    L5:
        return null;
    }

    public static final boolean equals(char r2, char r3, boolean r4) {
        if (r2 != r3) goto L6;
        return true;
    L6:
        if (r4 == true) goto L8;
        return false;
    L8:
        char r22 = Character.toUpperCase(r2);
        char r32 = Character.toUpperCase(r3);
        if (r22 != r32) goto L11;
    L14:
        return true;
    L11:
        if (Character.toLowerCase(r22) == Character.toLowerCase(r32)) goto L14;
        return false;
    }

    public static /* synthetic */ boolean equals$default(char r0, char r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = false;
    L6:
        return equals(r0, r1, r2);
    }

    public static boolean isSurrogate(char r2) {
        if (55296 <= r2) goto L5;
    L8:
        return false;
    L5:
        if (r2 >= 57344) goto L8;
        return true;
    }

    @InlineOnly
    private static final String plus(char r1, String r2) {
        return r1 + r2;
    }

    @SinceKotlin(version = "1.5")
    public static final String titlecase(char r0) {
        return _OneToManyTitlecaseMappingsKt.titlecaseImpl(r0);
    }
}
