package kotlin;

import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.1")
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0018B!\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007B\u0019\bV\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\bJ\"\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0082\u0080\u0004J\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0096\u0082\u0004J\n\u0010\u0015\u001a\u00020\u0003H\u0096\u0080\u0004J\u0012\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0000H\u0096\u0082\u0004J\u001a\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0086\u0080\u0004J\"\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0086\u0080\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0004\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0015\u0010\u0005\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u000f\u0010\r\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lkotlin/KotlinVersion;", _UrlKt.FRAGMENT_ENCODE_SET, "major", _UrlKt.FRAGMENT_ENCODE_SET, "minor", "patch", "<init>", "(III)V", "(II)V", "getMajor", "()I", "getMinor", "getPatch", "version", "versionOf", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "compareTo", "isAtLeast", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public final class KotlinVersion implements Comparable<KotlinVersion> {

    @JvmField
    public static final KotlinVersion CURRENT = null;
    public static final Companion Companion = null;
    public static final int MAX_COMPONENT_VALUE = 255;
    private final int major;
    private final int minor;
    private final int patch;
    private final int version;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u000f\u0010\u0004\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0084\b¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlin/KotlinVersion$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "MAX_COMPONENT_VALUE", _UrlKt.FRAGMENT_ENCODE_SET, "CURRENT", "Lkotlin/KotlinVersion;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
        CURRENT = KotlinVersionCurrentValue.get();
    }

    public KotlinVersion(int r2, int r3) {
        this(r2, r3, 0);
    }

    public KotlinVersion(int r1, int r2, int r3) {
        this.major = r1;
        this.minor = r2;
        this.patch = r3;
        this.version = versionOf(r1, r2, r3);
    }

    private final int versionOf(int r2, int r3, int r4) {
        if (r2 < 0) goto L12;
        if (r2 >= 256) goto L12;
        if (r3 < 0) goto L12;
        if (r3 >= 256) goto L12;
        if (r4 < 0) goto L12;
        if (r4 >= 256) goto L12;
        return ((r2 << 16) + (r3 << 8)) + r4;
    L12:
        throw new IllegalArgumentException(("Version components are out of range: " + r2 + '.' + r3 + '.' + r4).toString());
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(KotlinVersion r1) {
        return compareTo2(r1);
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(KotlinVersion r1) {
        return this.version - r1.version;
    }

    public boolean equals(Object r3) {
        if (this != r3) goto L6;
        return true;
    L6:
        if ((r3 instanceof KotlinVersion) == false) goto L8;
        KotlinVersion r32 = (KotlinVersion) r3;
    L10:
        if (r32 != null) goto L13;
        return false;
    L13:
        if (this.version != r32.version) goto L15;
        return true;
    L15:
        return false;
    L8:
        r32 = null;
        goto L10
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public final int getPatch() {
        return this.patch;
    }

    public int hashCode() {
        return this.version;
    }

    public final boolean isAtLeast(int r2, int r3) {
        int r0 = this.major;
        if (r0 > r2) goto L10;
        if (r0 == r2) goto L6;
        return false;
    L6:
        if (this.minor >= r3) goto L13;
        return false;
    L13:
        return true;
    L10:
        return true;
    }

    public final boolean isAtLeast(int r2, int r3, int r4) {
        int r0 = this.major;
        if (r0 > r2) goto L13;
        if (r0 != r2) goto L11;
        int r22 = this.minor;
        if (r22 > r3) goto L17;
        if (r22 == r3) goto L9;
        return false;
    L9:
        if (this.patch >= r4) goto L18;
        return false;
    L18:
        return true;
    L17:
        return true;
    L11:
        return false;
    L13:
        return true;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        r0.append(this.major);
        r0.append('.');
        r0.append(this.minor);
        r0.append('.');
        r0.append(this.patch);
        return r0.toString();
    }
}
