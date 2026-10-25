package okio;

import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0011\u0018\u00002\u00020\u0001Bq\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\u0018\b\u0002\u0010\u000e\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0004\b\u000f\u0010\u0010Jw\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\u0018\b\u0002\u0010\u000e\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0003\u0010\u0017R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0004\u0010\u0017R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\u001b\u001a\u0004\b\u001e\u0010\u001dR\u0019\u0010\n\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\n\u0010\u001b\u001a\u0004\b\u001f\u0010\u001dR\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u001b\u001a\u0004\b \u0010\u001dR'\u0010\u000e\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0004\u0012\u00020\u00010\f8\u0006¢\u0006\f\n\u0004\b\u000e\u0010!\u001a\u0004\b\"\u0010#¨\u0006$"}, d2 = {"Lokio/FileMetadata;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "isRegularFile", "isDirectory", "Lokio/Path;", "symlinkTarget", _UrlKt.FRAGMENT_ENCODE_SET, "size", "createdAtMillis", "lastModifiedAtMillis", "lastAccessedAtMillis", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KClass;", "extras", "<init>", "(ZZLokio/Path;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/util/Map;)V", "copy", "(ZZLokio/Path;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/util/Map;)Lokio/FileMetadata;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Z", "()Z", "Lokio/Path;", "getSymlinkTarget", "()Lokio/Path;", "Ljava/lang/Long;", "getSize", "()Ljava/lang/Long;", "getCreatedAtMillis", "getLastModifiedAtMillis", "getLastAccessedAtMillis", "Ljava/util/Map;", "getExtras", "()Ljava/util/Map;", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class FileMetadata {
    private final Long createdAtMillis;
    private final Map<KClass<?>, Object> extras;
    private final boolean isDirectory;
    private final boolean isRegularFile;
    private final Long lastAccessedAtMillis;
    private final Long lastModifiedAtMillis;
    private final Long size;
    private final Path symlinkTarget;

    public FileMetadata(boolean r1, boolean r2, Path r3, Long r4, Long r5, Long r6, Long r7, Map<KClass<?>, ? extends Object> r8) {
        this.isRegularFile = r1;
        this.isDirectory = r2;
        this.symlinkTarget = r3;
        this.size = r4;
        this.createdAtMillis = r5;
        this.lastModifiedAtMillis = r6;
        this.lastAccessedAtMillis = r7;
        this.extras = MapsKt.toMap(r8);
    }

    public /* synthetic */ FileMetadata(boolean r2, boolean r3, Path r4, Long r5, Long r6, Long r7, Long r8, Map r9, int r10, DefaultConstructorMarker r11) {
        if ((r10 & 1) == 0) goto L6;
        r2 = false;
    L6:
        if ((r10 & 2) == 0) goto L9;
        r3 = false;
    L9:
        if ((r10 & 4) == 0) goto L12;
        r4 = null;
    L12:
        if ((r10 & 8) == 0) goto L15;
        r5 = null;
    L15:
        if ((r10 & 16) == 0) goto L18;
        r6 = null;
    L18:
        if ((r10 & 32) == 0) goto L21;
        r7 = null;
    L21:
        if ((r10 & 64) == 0) goto L24;
        r8 = null;
    L24:
        if ((r10 & 128) == 0) goto L26;
        r9 = MapsKt.emptyMap();
    L26:
        this(r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ FileMetadata copy$default(FileMetadata r0, boolean r1, boolean r2, Path r3, Long r4, Long r5, Long r6, Long r7, Map r8, int r9, Object r10) {
        if ((r9 & 1) == 0) goto L6;
        r1 = r0.isRegularFile;
    L6:
        if ((r9 & 2) == 0) goto L9;
        r2 = r0.isDirectory;
    L9:
        if ((r9 & 4) == 0) goto L12;
        r3 = r0.symlinkTarget;
    L12:
        if ((r9 & 8) == 0) goto L15;
        r4 = r0.size;
    L15:
        if ((r9 & 16) == 0) goto L18;
        r5 = r0.createdAtMillis;
    L18:
        if ((r9 & 32) == 0) goto L21;
        r6 = r0.lastModifiedAtMillis;
    L21:
        if ((r9 & 64) == 0) goto L24;
        r7 = r0.lastAccessedAtMillis;
    L24:
        if ((r9 & 128) == 0) goto L26;
        r8 = r0.extras;
    L26:
        Long r92 = r7;
        Map r102 = r8;
        Long r72 = r5;
        Long r82 = r6;
        Path r52 = r3;
        Long r62 = r4;
        return r0.copy(r1, r2, r52, r62, r72, r82, r92, r102);
    }

    public final FileMetadata copy(boolean r1, boolean r2, Path r3, Long r4, Long r5, Long r6, Long r7, Map<KClass<?>, ? extends Object> r8) {
        return new FileMetadata(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public final Long getSize() {
        return this.size;
    }

    public final Path getSymlinkTarget() {
        return this.symlinkTarget;
    }

    public final boolean isDirectory() {
        return this.isDirectory;
    }

    public String toString() {
        ArrayList r0 = new ArrayList();
        if (this.isRegularFile == false) goto L6;
        r0.add("isRegularFile");
    L6:
        if (this.isDirectory == false) goto L9;
        r0.add("isDirectory");
    L9:
        if (this.size == null) goto L12;
        r0.add("byteCount=" + this.size.longValue());
    L12:
        if (this.createdAtMillis == null) goto L15;
        r0.add("createdAt=" + this.createdAtMillis.longValue());
    L15:
        if (this.lastModifiedAtMillis == null) goto L18;
        r0.add("lastModifiedAt=" + this.lastModifiedAtMillis.longValue());
    L18:
        if (this.lastAccessedAtMillis == null) goto L21;
        r0.add("lastAccessedAt=" + this.lastAccessedAtMillis.longValue());
    L21:
        if (this.extras.isEmpty() == true) goto L24;
        r0.add("extras=" + this.extras);
    L24:
        return CollectionsKt.joinToString$default(r0, ", ", "FileMetadata(", ")", 0, null, null, 56, null);
    }
}
