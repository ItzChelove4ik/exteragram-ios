package kotlin.io;

import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\b\u0080\b\u0018\u00002\u00020\u0001B\u001f\b@\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0014H\u0086\u0080\u0004J\n\u0010\u001a\u001a\u00020\u0003HÆ\u0083\u0004J\u0010\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0083\u0004J)\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÀ\u0081\u0004¢\u0006\u0002\b\u001dJ\u0014\u0010\u001e\u001a\u00020\u00112\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010!\u001a\u00020\rHÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001b\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\f\u001a\u00020\r8FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\u0010\u001a\u00020\u00118FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012R\u0015\u0010\u0013\u001a\u00020\u00148FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\""}, d2 = {"Lkotlin/io/FilePathComponents;", _UrlKt.FRAGMENT_ENCODE_SET, "root", "Ljava/io/File;", "segments", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/io/File;Ljava/util/List;)V", "getRoot", "()Ljava/io/File;", "getSegments", "()Ljava/util/List;", "rootName", _UrlKt.FRAGMENT_ENCODE_SET, "getRootName", "()Ljava/lang/String;", "isRooted", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "size", _UrlKt.FRAGMENT_ENCODE_SET, "getSize", "()I", "subPath", "beginIndex", "endIndex", "component1", "component2", "copy", "copy$kotlin_stdlib", "equals", "other", "hashCode", "toString", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FilePathComponents {
    private final File root;
    private final List<File> segments;

    /* JADX WARN: Multi-variable type inference failed */
    public FilePathComponents(File r1, List<? extends File> r2) {
        this.root = r1;
        this.segments = r2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FilePathComponents copy$kotlin_stdlib$default(FilePathComponents r0, File r1, List r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = r0.root;
    L6:
        if ((r3 & 2) == 0) goto L9;
        r2 = r0.segments;
    L9:
        return r0.copy$kotlin_stdlib(r1, r2);
    }

    public final File component1() {
        return this.root;
    }

    public final List<File> component2() {
        return this.segments;
    }

    public final FilePathComponents copy$kotlin_stdlib(File r1, List<? extends File> r2) {
        return new FilePathComponents(r1, r2);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof FilePathComponents) == true) goto L8;
        return false;
    L8:
        FilePathComponents r52 = (FilePathComponents) r5;
        if (Intrinsics.areEqual(this.root, r52.root) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.segments, r52.segments) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public final File getRoot() {
        return this.root;
    }

    public final String getRootName() {
        return this.root.getPath();
    }

    public final List<File> getSegments() {
        return this.segments;
    }

    public final int getSize() {
        return this.segments.size();
    }

    public int hashCode() {
        return (this.root.hashCode() * 31) + this.segments.hashCode();
    }

    public final boolean isRooted() {
        if (this.root.getPath().length() <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final File subPath(int r11, int r12) {
        if (r11 < 0) goto L8;
        if (r11 > r12) goto L8;
        if (r12 > getSize()) goto L8;
        return new File(CollectionsKt.joinToString$default(this.segments.subList(r11, r12), File.separator, null, null, 0, null, null, 62, null));
    L8:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    public String toString() {
        return "FilePathComponents(root=" + this.root + ", segments=" + this.segments + ')';
    }
}
