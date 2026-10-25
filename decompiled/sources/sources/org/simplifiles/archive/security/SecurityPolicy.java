package org.simplifiles.archive.security;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u001c\b\u0086\b\u0018\u0000 ,2\u00020\u0001:\u0002,-Bc\b\u0007\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\n\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u001a\u0010\u0018\u001a\u00020\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001a\u001a\u0004\b\u001d\u0010\u001cR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001e\u0010\u001cR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001f\u001a\u0004\b \u0010!R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\"\u001a\u0004\b#\u0010\u0016R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010\f\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\f\u0010$\u001a\u0004\b'\u0010&R\u0017\u0010\r\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\r\u0010$\u001a\u0004\b(\u0010&R\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010)\u001a\u0004\b*\u0010+¨\u0006."}, d2 = {"Lorg/simplifiles/archive/security/SecurityPolicy;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "maxEntries", "maxTotalUncompressedSize", "maxSingleFileSize", _UrlKt.FRAGMENT_ENCODE_SET, "maxCompressionRatio", _UrlKt.FRAGMENT_ENCODE_SET, "maxNestedArchiveDepth", _UrlKt.FRAGMENT_ENCODE_SET, "allowSymlinks", "allowHardlinks", "allowAbsolutePaths", "Lorg/simplifiles/archive/security/DuplicatePolicy;", "duplicatePolicy", "<init>", "(JJJDIZZZLorg/simplifiles/archive/security/DuplicatePolicy;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "J", "getMaxEntries", "()J", "getMaxTotalUncompressedSize", "getMaxSingleFileSize", "D", "getMaxCompressionRatio", "()D", "I", "getMaxNestedArchiveDepth", "Z", "getAllowSymlinks", "()Z", "getAllowHardlinks", "getAllowAbsolutePaths", "Lorg/simplifiles/archive/security/DuplicatePolicy;", "getDuplicatePolicy", "()Lorg/simplifiles/archive/security/DuplicatePolicy;", "Companion", "Builder", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSecurityPolicy.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SecurityPolicy.kt\norg/simplifiles/archive/security/SecurityPolicy\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,150:1\n1#2:151\n*E\n"})
/* loaded from: classes.dex */
public final class SecurityPolicy {
    public static final Companion Companion = null;
    private final boolean allowAbsolutePaths;
    private final boolean allowHardlinks;
    private final boolean allowSymlinks;
    private final DuplicatePolicy duplicatePolicy;
    private final double maxCompressionRatio;
    private final long maxEntries;
    private final int maxNestedArchiveDepth;
    private final long maxSingleFileSize;
    private final long maxTotalUncompressedSize;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0013\b\u0000\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\n\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\tJ\u0015\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\tJ\u0015\u0010\r\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010R\u0016\u0010\b\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\b\u0010\u0011R\u0016\u0010\n\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010\u0011R\u0016\u0010\u000b\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\u0011R\u0016\u0010\r\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010\u0012R\u0016\u0010\u0014\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0017\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0019\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010\u0018R\u0016\u0010\u001a\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u0018R\u0016\u0010\u001c\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Lorg/simplifiles/archive/security/SecurityPolicy$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "Lorg/simplifiles/archive/security/SecurityPolicy;", "policy", "<init>", "(Lorg/simplifiles/archive/security/SecurityPolicy;)V", _UrlKt.FRAGMENT_ENCODE_SET, "value", "maxEntries", "(J)Lorg/simplifiles/archive/security/SecurityPolicy$Builder;", "maxTotalUncompressedSize", "maxSingleFileSize", _UrlKt.FRAGMENT_ENCODE_SET, "maxCompressionRatio", "(D)Lorg/simplifiles/archive/security/SecurityPolicy$Builder;", "build", "()Lorg/simplifiles/archive/security/SecurityPolicy;", "J", "D", _UrlKt.FRAGMENT_ENCODE_SET, "maxNestedArchiveDepth", "I", _UrlKt.FRAGMENT_ENCODE_SET, "allowSymlinks", "Z", "allowHardlinks", "allowAbsolutePaths", "Lorg/simplifiles/archive/security/DuplicatePolicy;", "duplicatePolicy", "Lorg/simplifiles/archive/security/DuplicatePolicy;", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Builder {
        private boolean allowAbsolutePaths;
        private boolean allowHardlinks;
        private boolean allowSymlinks;
        private DuplicatePolicy duplicatePolicy;
        private double maxCompressionRatio;
        private long maxEntries;
        private int maxNestedArchiveDepth;
        private long maxSingleFileSize;
        private long maxTotalUncompressedSize;

        public Builder(SecurityPolicy r3) {
            this.maxEntries = r3.getMaxEntries();
            this.maxTotalUncompressedSize = r3.getMaxTotalUncompressedSize();
            this.maxSingleFileSize = r3.getMaxSingleFileSize();
            this.maxCompressionRatio = r3.getMaxCompressionRatio();
            this.maxNestedArchiveDepth = r3.getMaxNestedArchiveDepth();
            this.allowSymlinks = r3.getAllowSymlinks();
            this.allowHardlinks = r3.getAllowHardlinks();
            this.allowAbsolutePaths = r3.getAllowAbsolutePaths();
            this.duplicatePolicy = r3.getDuplicatePolicy();
        }

        public /* synthetic */ Builder(SecurityPolicy r1, int r2, DefaultConstructorMarker r3) {
            if ((r2 & 1) == 0) goto L5;
            r1 = SecurityPolicy.Companion.strict();
        L5:
            this(r1);
        }

        public final SecurityPolicy build() {
            return new SecurityPolicy(this.maxEntries, this.maxTotalUncompressedSize, this.maxSingleFileSize, this.maxCompressionRatio, this.maxNestedArchiveDepth, this.allowSymlinks, this.allowHardlinks, this.allowAbsolutePaths, this.duplicatePolicy);
        }

        public final Builder maxCompressionRatio(double r1) {
            this.maxCompressionRatio = r1;
            return this;
        }

        public final Builder maxEntries(long r1) {
            this.maxEntries = r1;
            return this;
        }

        public final Builder maxSingleFileSize(long r1) {
            this.maxSingleFileSize = r1;
            return this;
        }

        public final Builder maxTotalUncompressedSize(long r1) {
            this.maxTotalUncompressedSize = r1;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007J\b\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lorg/simplifiles/archive/security/SecurityPolicy$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "strict", "Lorg/simplifiles/archive/security/SecurityPolicy;", "builder", "Lorg/simplifiles/archive/security/SecurityPolicy$Builder;", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final Builder builder() {
            return new Builder(null, 1, null);
        }

        @JvmStatic
        public final SecurityPolicy strict() {
            return new SecurityPolicy(0, 0, 0, 0.0d, 0, false, false, false, null, 511, null);
        }
    }

    static {
        Companion = new Companion(null);
    }

    @JvmOverloads
    public SecurityPolicy(long r1, long r3, long r5, double r7, int r9, boolean r10, boolean r11, boolean r12, DuplicatePolicy r13) {
        this.maxEntries = r1;
        this.maxTotalUncompressedSize = r3;
        this.maxSingleFileSize = r5;
        this.maxCompressionRatio = r7;
        this.maxNestedArchiveDepth = r9;
        this.allowSymlinks = r10;
        this.allowHardlinks = r11;
        this.allowAbsolutePaths = r12;
        this.duplicatePolicy = r13;
        if (r1 > 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("maxEntries must be positive.");
        throw null;
    L5:
        if (r3 > 0) goto L7;
        f$$ExternalSyntheticBUOutline1.m("maxTotalUncompressedSize must be positive.");
        throw null;
    L7:
        if (r5 > 0) goto L9;
        f$$ExternalSyntheticBUOutline1.m("maxSingleFileSize must be positive.");
        throw null;
    L9:
        if (r7 <= 0.0d) goto L14;
        if (r9 < 0) goto L12;
        return;
    L12:
        f$$ExternalSyntheticBUOutline1.m("maxNestedArchiveDepth must not be negative.");
        throw null;
    L14:
        f$$ExternalSyntheticBUOutline1.m("maxCompressionRatio must be positive.");
        throw null;
    }

    public /* synthetic */ SecurityPolicy(long r15, long r17, long r19, double r21, int r23, boolean r24, boolean r25, boolean r26, DuplicatePolicy r27, int r28, DefaultConstructorMarker r29) {
        if ((r28 & 1) == 0) goto L5;
        long r1 = NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
    L7:
        if ((r28 & 2) == 0) goto L9;
        long r3 = 1000000000;
    L11:
        if ((r28 & 4) == 0) goto L13;
        long r5 = 100000000;
    L15:
        if ((r28 & 8) == 0) goto L17;
        double r7 = 100.0d;
    L18:
        boolean r10 = false;
        if ((r28 & 16) == 0) goto L21;
        int r9 = 0;
    L23:
        if ((r28 & 32) == 0) goto L25;
        boolean r11 = false;
    L27:
        if ((r28 & 64) == 0) goto L29;
        boolean r12 = false;
    L31:
        if ((r28 & 128) != 0) goto L35;
        r10 = r26;
    L35:
        if ((r28 & 256) == 0) goto L38;
        DuplicatePolicy r282 = DuplicatePolicy.ERROR;
    L39:
        this(r1, r3, r5, r7, r9, r11, r12, r10, r282);
        return;
    L38:
        r282 = r27;
        goto L39
    L29:
        r12 = r25;
        goto L31
    L25:
        r11 = r24;
        goto L27
    L21:
        r9 = r23;
        goto L23
    L17:
        r7 = r21;
        goto L18
    L13:
        r5 = r19;
        goto L15
    L9:
        r3 = r17;
        goto L11
    L5:
        r1 = r15;
        goto L7
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof SecurityPolicy) == true) goto L8;
        return false;
    L8:
        SecurityPolicy r82 = (SecurityPolicy) r8;
        if (this.maxEntries == r82.maxEntries) goto L12;
        return false;
    L12:
        if (this.maxTotalUncompressedSize == r82.maxTotalUncompressedSize) goto L15;
        return false;
    L15:
        if (this.maxSingleFileSize == r82.maxSingleFileSize) goto L18;
        return false;
    L18:
        if (Double.compare(this.maxCompressionRatio, r82.maxCompressionRatio) == 0) goto L21;
        return false;
    L21:
        if (this.maxNestedArchiveDepth == r82.maxNestedArchiveDepth) goto L24;
        return false;
    L24:
        if (this.allowSymlinks == r82.allowSymlinks) goto L27;
        return false;
    L27:
        if (this.allowHardlinks == r82.allowHardlinks) goto L30;
        return false;
    L30:
        if (this.allowAbsolutePaths == r82.allowAbsolutePaths) goto L33;
        return false;
    L33:
        if (this.duplicatePolicy == r82.duplicatePolicy) goto L35;
        return false;
    L35:
        return true;
    }

    public final boolean getAllowAbsolutePaths() {
        return this.allowAbsolutePaths;
    }

    public final boolean getAllowHardlinks() {
        return this.allowHardlinks;
    }

    public final boolean getAllowSymlinks() {
        return this.allowSymlinks;
    }

    public final DuplicatePolicy getDuplicatePolicy() {
        return this.duplicatePolicy;
    }

    public final double getMaxCompressionRatio() {
        return this.maxCompressionRatio;
    }

    public final long getMaxEntries() {
        return this.maxEntries;
    }

    public final int getMaxNestedArchiveDepth() {
        return this.maxNestedArchiveDepth;
    }

    public final long getMaxSingleFileSize() {
        return this.maxSingleFileSize;
    }

    public final long getMaxTotalUncompressedSize() {
        return this.maxTotalUncompressedSize;
    }

    public int hashCode() {
        return (((((((((((((((Long.hashCode(this.maxEntries) * 31) + Long.hashCode(this.maxTotalUncompressedSize)) * 31) + Long.hashCode(this.maxSingleFileSize)) * 31) + Double.hashCode(this.maxCompressionRatio)) * 31) + Integer.hashCode(this.maxNestedArchiveDepth)) * 31) + Boolean.hashCode(this.allowSymlinks)) * 31) + Boolean.hashCode(this.allowHardlinks)) * 31) + Boolean.hashCode(this.allowAbsolutePaths)) * 31) + this.duplicatePolicy.hashCode();
    }

    public String toString() {
        return "SecurityPolicy(maxEntries=" + this.maxEntries + ", maxTotalUncompressedSize=" + this.maxTotalUncompressedSize + ", maxSingleFileSize=" + this.maxSingleFileSize + ", maxCompressionRatio=" + this.maxCompressionRatio + ", maxNestedArchiveDepth=" + this.maxNestedArchiveDepth + ", allowSymlinks=" + this.allowSymlinks + ", allowHardlinks=" + this.allowHardlinks + ", allowAbsolutePaths=" + this.allowAbsolutePaths + ", duplicatePolicy=" + this.duplicatePolicy + ')';
    }
}
