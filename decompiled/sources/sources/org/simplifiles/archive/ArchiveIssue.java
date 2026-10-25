package org.simplifiles.archive;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u000bR\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0016\u001a\u0004\b\u0018\u0010\u000bR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0016\u001a\u0004\b\u0019\u0010\u000b¨\u0006\u001a"}, d2 = {"Lorg/simplifiles/archive/ArchiveIssue;", _UrlKt.FRAGMENT_ENCODE_SET, "Lorg/simplifiles/archive/ArchiveIssueSeverity;", "severity", _UrlKt.FRAGMENT_ENCODE_SET, "code", "message", "path", "<init>", "(Lorg/simplifiles/archive/ArchiveIssueSeverity;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Lorg/simplifiles/archive/ArchiveIssueSeverity;", "getSeverity", "()Lorg/simplifiles/archive/ArchiveIssueSeverity;", "Ljava/lang/String;", "getCode", "getMessage", "getPath", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ArchiveIssue {
    private final String code;
    private final String message;
    private final String path;
    private final ArchiveIssueSeverity severity;

    public ArchiveIssue(ArchiveIssueSeverity r1, String r2, String r3, String r4) {
        this.severity = r1;
        this.code = r2;
        this.message = r3;
        this.path = r4;
    }

    public /* synthetic */ ArchiveIssue(ArchiveIssueSeverity r1, String r2, String r3, String r4, int r5, DefaultConstructorMarker r6) {
        if ((r5 & 8) == 0) goto L5;
        r4 = null;
    L5:
        this(r1, r2, r3, r4);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof ArchiveIssue) == true) goto L8;
        return false;
    L8:
        ArchiveIssue r52 = (ArchiveIssue) r5;
        if (this.severity == r52.severity) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.code, r52.code) == true) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(this.message, r52.message) == true) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(this.path, r52.path) == true) goto L20;
        return false;
    L20:
        return true;
    }

    public final String getCode() {
        return this.code;
    }

    public final String getMessage() {
        return this.message;
    }

    public final ArchiveIssueSeverity getSeverity() {
        return this.severity;
    }

    public int hashCode() {
        int r0 = ((((this.severity.hashCode() * 31) + this.code.hashCode()) * 31) + this.message.hashCode()) * 31;
        String r2 = this.path;
        if (r2 != null) goto L5;
        int r22 = 0;
    L7:
        return r0 + r22;
    L5:
        r22 = r2.hashCode();
        goto L7
    }

    public String toString() {
        return "ArchiveIssue(severity=" + this.severity + ", code=" + this.code + ", message=" + this.message + ", path=" + this.path + ')';
    }
}
