package kotlin.text;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000fB\u0011\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\n\u0010\f\u001a\u00020\rH\u0096\u0082\u0004J\n\u0010\u000e\u001a\u00020\u0002H\u0096\u0082\u0004R\u000f\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\bX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\t\u001a\u00020\bX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\bX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000b\u001a\u00020\bX\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlin/text/LinesIterator;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "string", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/CharSequence;)V", "state", _UrlKt.FRAGMENT_ENCODE_SET, "tokenStartIndex", "delimiterStartIndex", "delimiterLength", "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "next", "State", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class LinesIterator implements Iterator<String>, KMappedMarker {

    @Deprecated
    public static final int EXHAUSTED = 2;

    @Deprecated
    public static final int HAS_NEXT = 1;
    private static final State State = null;

    @Deprecated
    public static final int UNKNOWN = 0;
    private int delimiterLength;
    private int delimiterStartIndex;
    private int state;
    private final CharSequence string;
    private int tokenStartIndex;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u000f\u0010\u0004\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlin/text/LinesIterator$State;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "UNKNOWN", _UrlKt.FRAGMENT_ENCODE_SET, "HAS_NEXT", "EXHAUSTED", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class State {
        private State() {
        }

        public /* synthetic */ State(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        State = new State(null);
    }

    public LinesIterator(CharSequence r1) {
        this.string = r1;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        int r0 = this.state;
        if (r0 == 0) goto L7;
        if (r0 != 1) goto L6;
        return true;
    L6:
        return false;
    L7:
        int r3 = 2;
        if (this.delimiterLength >= 0) goto L11;
        this.state = 2;
        return false;
    L11:
        int r02 = this.string.length();
        int r1 = this.tokenStartIndex;
        int r4 = this.string.length();
    L12:
        if (r1 >= r4) goto L25;
        char r5 = this.string.charAt(r1);
        if (r5 == '\n') goto L17;
        if (r5 == '\r') goto L17;
        r1 = r1 + 1;
    L17:
        if (r5 != '\r') goto L23;
        int r03 = r1 + 1;
        if (r03 >= this.string.length()) goto L23;
        if (this.string.charAt(r03) != '\n') goto L23;
    L24:
        r02 = r1;
    L26:
        this.state = 1;
        this.delimiterLength = r3;
        this.delimiterStartIndex = r02;
        return true;
    L23:
        r3 = 1;
        goto L24
    L25:
        r3 = -1;
        goto L26
    }

    @Override // java.util.Iterator
    public /* bridge */ /* synthetic */ String next() {
        return next2();
    }

    @Override // java.util.Iterator
    /* renamed from: next, reason: avoid collision after fix types in other method */
    public String next2() {
        if (hasNext() == false) goto L6;
        this.state = 0;
        int r0 = this.delimiterStartIndex;
        int r1 = this.tokenStartIndex;
        this.tokenStartIndex = this.delimiterLength + r0;
        return this.string.subSequence(r1, r0).toString();
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
