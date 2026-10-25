package org.simplifiles.internal.archive;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u000b\b\u0080\b\u0018\u00002\u00020\u0001B)\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0010\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0014\u001a\u0004\b\u0005\u0010\u0015R\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0014\u001a\u0004\b\u0006\u0010\u0015R\u0017\u0010\u0007\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0014\u001a\u0004\b\u0016\u0010\u0015¨\u0006\u0017"}, d2 = {"Lorg/simplifiles/internal/archive/ArchivePathAnalysis;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "normalizedPath", _UrlKt.FRAGMENT_ENCODE_SET, "isEmpty", "isAbsolute", "containsParentTraversal", "<init>", "(Ljava/lang/String;ZZZ)V", "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getNormalizedPath", "Z", "()Z", "getContainsParentTraversal", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ArchivePathAnalysis {
    private final boolean containsParentTraversal;
    private final boolean isAbsolute;
    private final boolean isEmpty;
    private final String normalizedPath;

    public ArchivePathAnalysis(String r1, boolean r2, boolean r3, boolean r4) {
        this.normalizedPath = r1;
        this.isEmpty = r2;
        this.isAbsolute = r3;
        this.containsParentTraversal = r4;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof ArchivePathAnalysis) == true) goto L8;
        return false;
    L8:
        ArchivePathAnalysis r52 = (ArchivePathAnalysis) r5;
        if (Intrinsics.areEqual(this.normalizedPath, r52.normalizedPath) == true) goto L12;
        return false;
    L12:
        if (this.isEmpty == r52.isEmpty) goto L15;
        return false;
    L15:
        if (this.isAbsolute == r52.isAbsolute) goto L18;
        return false;
    L18:
        if (this.containsParentTraversal == r52.containsParentTraversal) goto L20;
        return false;
    L20:
        return true;
    }

    public final boolean getContainsParentTraversal() {
        return this.containsParentTraversal;
    }

    public final String getNormalizedPath() {
        return this.normalizedPath;
    }

    public int hashCode() {
        String r0 = this.normalizedPath;
        if (r0 != null) goto L5;
        int r02 = 0;
    L7:
        return (((((r02 * 31) + Boolean.hashCode(this.isEmpty)) * 31) + Boolean.hashCode(this.isAbsolute)) * 31) + Boolean.hashCode(this.containsParentTraversal);
    L5:
        r02 = r0.hashCode();
        goto L7
    }

    public final boolean isAbsolute() {
        return this.isAbsolute;
    }

    public final boolean isEmpty() {
        return this.isEmpty;
    }

    public String toString() {
        return "ArchivePathAnalysis(normalizedPath=" + this.normalizedPath + ", isEmpty=" + this.isEmpty + ", isAbsolute=" + this.isAbsolute + ", containsParentTraversal=" + this.containsParentTraversal + ')';
    }
}
