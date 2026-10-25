package kotlin.random;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.rop.code.RegisterSpec;
import java.io.InvalidObjectException;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00172\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0001\u0017B9\b@\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\fB\u0019\bP\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\u000fJ\n\u0010\u0010\u001a\u00020\u0011H\u0082\u0080\u0004J\n\u0010\u0012\u001a\u00020\u0013H\u0082\u0080\u0004J\n\u0010\u0014\u001a\u00020\u0005H\u0096\u0080\u0004J\u0012\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005H\u0096\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0005X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\u0005X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\b\u001a\u00020\u0005X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\t\u001a\u00020\u0005X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\u0005X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lkotlin/random/XorWowRandom;", "Lkotlin/random/Random;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "x", _UrlKt.FRAGMENT_ENCODE_SET, "y", "z", "w", RegisterSpec.PREFIX, "addend", "<init>", "(IIIIII)V", "seed1", "seed2", "(II)V", "checkInvariants", _UrlKt.FRAGMENT_ENCODE_SET, "readResolve", _UrlKt.FRAGMENT_ENCODE_SET, "nextInt", "nextBits", "bitCount", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nXorWowRandom.kt\nKotlin\n*S Kotlin\n*F\n+ 1 XorWowRandom.kt\nkotlin/random/XorWowRandom\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,68:1\n1#2:69\n*E\n"})
/* loaded from: classes5.dex */
public final class XorWowRandom extends Random implements Serializable {
    private static final Companion Companion = null;
    private static final long serialVersionUID = 0;
    private int addend;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlin/random/XorWowRandom$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "serialVersionUID", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
    }

    public XorWowRandom(int r8, int r9) {
        this(r8, r9, 0, 0, ~r8, (r8 << 10) ^ (r9 >>> 4));
    }

    public XorWowRandom(int r1, int r2, int r3, int r4, int r5, int r6) {
        this.x = r1;
        this.y = r2;
        this.z = r3;
        this.w = r4;
        this.v = r5;
        this.addend = r6;
        checkInvariants();
        int r12 = 0;
    L4:
        if (r12 >= 64) goto L6;
        nextInt();
        r12 = r12 + 1;
        goto L4
    }

    private final void checkInvariants() {
        int r0 = ((this.x | this.y) | this.z) | this.w;
        if ((this.v | r0) == 0) goto L5;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("Initial state must have at least one non-zero element.");
    }

    private final Object readResolve() {
        checkInvariants();     // Catch: Throwable -> L4
        return this;
    L4:
        th = move-exception;
        throw new InvalidObjectException(th.getMessage()).initCause(th);
    }

    @Override // kotlin.random.Random
    public int nextBits(int r1) {
        return RandomKt.takeUpperBits(nextInt(), r1);
    }

    @Override // kotlin.random.Random
    public int nextInt() {
        int r0 = this.x;
        int r02 = r0 ^ (r0 >>> 2);
        this.x = this.y;
        this.y = this.z;
        this.z = this.w;
        int r1 = this.v;
        this.w = r1;
        int r03 = ((r02 ^ (r02 << 1)) ^ r1) ^ (r1 << 4);
        this.v = r03;
        int r12 = this.addend + 362437;
        this.addend = r12;
        return r03 + r12;
    }
}
