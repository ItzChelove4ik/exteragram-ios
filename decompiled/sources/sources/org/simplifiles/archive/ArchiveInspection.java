package org.simplifiles.archive;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lorg/simplifiles/archive/ArchiveInspection;", _UrlKt.FRAGMENT_ENCODE_SET, "Lorg/simplifiles/archive/ArchiveFormat;", "format", _UrlKt.FRAGMENT_ENCODE_SET, "Lorg/simplifiles/archive/ArchiveEntryInfo;", "entries", "<init>", "(Lorg/simplifiles/archive/ArchiveFormat;Ljava/util/List;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Lorg/simplifiles/archive/ArchiveFormat;", "getFormat", "()Lorg/simplifiles/archive/ArchiveFormat;", "Ljava/util/List;", "getEntries", "()Ljava/util/List;", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nArchiveInspection.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ArchiveInspection.kt\norg/simplifiles/archive/ArchiveInspection\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,26:1\n1313#2,3:27\n*S KotlinDebug\n*F\n+ 1 ArchiveInspection.kt\norg/simplifiles/archive/ArchiveInspection\n*L\n24#1:27,3\n*E\n"})
/* loaded from: classes5.dex */
public final class ArchiveInspection {
    private final List<ArchiveEntryInfo> entries;
    private final ArchiveFormat format;

    public ArchiveInspection(ArchiveFormat r1, List<ArchiveEntryInfo> r2) {
        this.format = r1;
        this.entries = r2;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof ArchiveInspection) == true) goto L8;
        return false;
    L8:
        ArchiveInspection r52 = (ArchiveInspection) r5;
        if (this.format == r52.format) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.entries, r52.entries) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public final List<ArchiveEntryInfo> getEntries() {
        return this.entries;
    }

    public final ArchiveFormat getFormat() {
        return this.format;
    }

    public int hashCode() {
        return (this.format.hashCode() * 31) + this.entries.hashCode();
    }

    public String toString() {
        return "ArchiveInspection(format=" + this.format + ", entries=" + this.entries + ')';
    }
}
