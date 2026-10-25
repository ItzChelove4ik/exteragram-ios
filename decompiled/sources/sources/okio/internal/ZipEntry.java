package okio.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import okio.Path;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b,\n\u0002\u0010!\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B±\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0017\u0010\u0018J-\u0010\u001b\u001a\u00020\u00002\b\u0010\u0014\u001a\u0004\u0018\u00010\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001f\u001a\u0004\b\u0005\u0010 R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010\n\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\n\u0010$\u001a\u0004\b'\u0010&R\u0017\u0010\u000b\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u000b\u0010$\u001a\u0004\b(\u0010&R\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010)\u001a\u0004\b*\u0010+R\u0017\u0010\u000e\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u000e\u0010$\u001a\u0004\b,\u0010&R\u0017\u0010\u000f\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u000f\u0010)\u001a\u0004\b-\u0010+R\u0017\u0010\u0010\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010)\u001a\u0004\b.\u0010+R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\u0011\u0010/\u001a\u0004\b0\u00101R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\u0012\u0010/\u001a\u0004\b2\u00101R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\u0013\u0010/\u001a\u0004\b3\u00101R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\f8\u0006¢\u0006\f\n\u0004\b\u0014\u00104\u001a\u0004\b5\u00106R\u0019\u0010\u0015\u001a\u0004\u0018\u00010\f8\u0006¢\u0006\f\n\u0004\b\u0015\u00104\u001a\u0004\b7\u00106R\u0019\u0010\u0016\u001a\u0004\u0018\u00010\f8\u0006¢\u0006\f\n\u0004\b\u0016\u00104\u001a\u0004\b8\u00106R\u001d\u0010:\u001a\b\u0012\u0004\u0012\u00020\u0002098\u0006¢\u0006\f\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=R\u0016\u0010?\u001a\u0004\u0018\u00010\b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b>\u00101R\u0016\u0010A\u001a\u0004\u0018\u00010\b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b@\u00101R\u0016\u0010C\u001a\u0004\u0018\u00010\b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bB\u00101¨\u0006D"}, d2 = {"Lokio/internal/ZipEntry;", _UrlKt.FRAGMENT_ENCODE_SET, "Lokio/Path;", "canonicalPath", _UrlKt.FRAGMENT_ENCODE_SET, "isDirectory", _UrlKt.FRAGMENT_ENCODE_SET, "comment", _UrlKt.FRAGMENT_ENCODE_SET, "crc", "compressedSize", "size", _UrlKt.FRAGMENT_ENCODE_SET, "compressionMethod", "offset", "dosLastModifiedAtDate", "dosLastModifiedAtTime", "ntfsLastModifiedAtFiletime", "ntfsLastAccessedAtFiletime", "ntfsCreatedAtFiletime", "extendedLastModifiedAtSeconds", "extendedLastAccessedAtSeconds", "extendedCreatedAtSeconds", "<init>", "(Lokio/Path;ZLjava/lang/String;JJJIJIILjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "copy$okio", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lokio/internal/ZipEntry;", "copy", "Lokio/Path;", "getCanonicalPath", "()Lokio/Path;", "Z", "()Z", "Ljava/lang/String;", "getComment", "()Ljava/lang/String;", "J", "getCrc", "()J", "getCompressedSize", "getSize", "I", "getCompressionMethod", "()I", "getOffset", "getDosLastModifiedAtDate", "getDosLastModifiedAtTime", "Ljava/lang/Long;", "getNtfsLastModifiedAtFiletime", "()Ljava/lang/Long;", "getNtfsLastAccessedAtFiletime", "getNtfsCreatedAtFiletime", "Ljava/lang/Integer;", "getExtendedLastModifiedAtSeconds", "()Ljava/lang/Integer;", "getExtendedLastAccessedAtSeconds", "getExtendedCreatedAtSeconds", _UrlKt.FRAGMENT_ENCODE_SET, "children", "Ljava/util/List;", "getChildren", "()Ljava/util/List;", "getLastAccessedAtMillis$okio", "lastAccessedAtMillis", "getLastModifiedAtMillis$okio", "lastModifiedAtMillis", "getCreatedAtMillis$okio", "createdAtMillis", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ZipEntry {
    private final Path canonicalPath;
    private final List<Path> children;
    private final String comment;
    private final long compressedSize;
    private final int compressionMethod;
    private final long crc;
    private final int dosLastModifiedAtDate;
    private final int dosLastModifiedAtTime;
    private final Integer extendedCreatedAtSeconds;
    private final Integer extendedLastAccessedAtSeconds;
    private final Integer extendedLastModifiedAtSeconds;
    private final boolean isDirectory;
    private final Long ntfsCreatedAtFiletime;
    private final Long ntfsLastAccessedAtFiletime;
    private final Long ntfsLastModifiedAtFiletime;
    private final long offset;
    private final long size;

    public ZipEntry(Path r1, boolean r2, String r3, long r4, long r6, long r8, int r10, long r11, int r13, int r14, Long r15, Long r16, Long r17, Integer r18, Integer r19, Integer r20) {
        this.canonicalPath = r1;
        this.isDirectory = r2;
        this.comment = r3;
        this.crc = r4;
        this.compressedSize = r6;
        this.size = r8;
        this.compressionMethod = r10;
        this.offset = r11;
        this.dosLastModifiedAtDate = r13;
        this.dosLastModifiedAtTime = r14;
        this.ntfsLastModifiedAtFiletime = r15;
        this.ntfsLastAccessedAtFiletime = r16;
        this.ntfsCreatedAtFiletime = r17;
        this.extendedLastModifiedAtSeconds = r18;
        this.extendedLastAccessedAtSeconds = r19;
        this.extendedCreatedAtSeconds = r20;
        this.children = new ArrayList();
    }

    public /* synthetic */ ZipEntry(Path r18, boolean r19, String r20, long r21, long r23, long r25, int r27, long r28, int r30, int r31, Long r32, Long r33, Long r34, Integer r35, Integer r36, Integer r37, int r38, DefaultConstructorMarker r39) {
        if ((r38 & 2) == 0) goto L5;
        boolean r1 = false;
    L7:
        if ((r38 & 4) == 0) goto L9;
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        long r4 = -1;
        if ((r38 & 8) == 0) goto L13;
        long r6 = -1;
    L15:
        if ((r38 & 16) == 0) goto L17;
        long r8 = -1;
    L19:
        if ((r38 & 32) == 0) goto L21;
        long r10 = -1;
    L22:
        int r12 = -1;
        if ((r38 & 64) == 0) goto L25;
        int r3 = -1;
    L27:
        if ((r38 & 128) != 0) goto L31;
        r4 = r28;
    L31:
        if ((r38 & 256) == 0) goto L33;
        int r13 = -1;
    L35:
        if ((r38 & 512) != 0) goto L39;
        r12 = r31;
    L39:
        if ((r38 & 1024) == 0) goto L41;
        Long r14 = null;
    L43:
        if ((r38 & 2048) == 0) goto L45;
        Long r15 = null;
    L46:
        boolean r392 = r1;
        if ((r38 & 4096) == 0) goto L49;
        Long r16 = null;
    L50:
        Long r202 = r16;
        if ((r38 & 8192) == 0) goto L53;
        Integer r17 = null;
    L54:
        Integer r212 = r17;
        if ((r38 & 16384) == 0) goto L57;
        Integer r110 = null;
    L59:
        if ((r38 & 32768) == 0) goto L62;
        Integer r372 = r212;
        boolean r213 = r392;
        Integer r393 = null;
    L63:
        this(r18, r213, r2, r6, r8, r10, r3, r4, r13, r12, r14, r15, r202, r372, r110, r393);
        return;
    L62:
        r372 = r212;
        r213 = r392;
        r393 = r37;
        goto L63
    L57:
        r110 = r36;
        goto L59
    L53:
        r17 = r35;
        goto L54
    L49:
        r16 = r34;
        goto L50
    L45:
        r15 = r33;
        goto L46
    L41:
        r14 = r32;
        goto L43
    L33:
        r13 = r30;
        goto L35
    L25:
        r3 = r27;
        goto L27
    L21:
        r10 = r25;
        goto L22
    L17:
        r8 = r23;
        goto L19
    L13:
        r6 = r21;
        goto L15
    L9:
        r2 = r20;
        goto L10
    L5:
        r1 = r19;
        goto L7
    }

    public final ZipEntry copy$okio(Integer r22, Integer r23, Integer r24) {
        return new ZipEntry(this.canonicalPath, this.isDirectory, this.comment, this.crc, this.compressedSize, this.size, this.compressionMethod, this.offset, this.dosLastModifiedAtDate, this.dosLastModifiedAtTime, this.ntfsLastModifiedAtFiletime, this.ntfsLastAccessedAtFiletime, this.ntfsCreatedAtFiletime, r22, r23, r24);
    }

    public final Path getCanonicalPath() {
        return this.canonicalPath;
    }

    public final List<Path> getChildren() {
        return this.children;
    }

    public final long getCompressedSize() {
        return this.compressedSize;
    }

    public final int getCompressionMethod() {
        return this.compressionMethod;
    }

    public final Long getCreatedAtMillis$okio() {
        Long r0 = this.ntfsCreatedAtFiletime;
        if (r0 == null) goto L7;
        return Long.valueOf(ZipFilesKt.filetimeToEpochMillis(r0.longValue()));
    L7:
        if (this.extendedCreatedAtSeconds != null) goto L9;
        return null;
    L9:
        return Long.valueOf(r4.intValue() * 1000);
    }

    public final Long getLastAccessedAtMillis$okio() {
        Long r0 = this.ntfsLastAccessedAtFiletime;
        if (r0 == null) goto L7;
        return Long.valueOf(ZipFilesKt.filetimeToEpochMillis(r0.longValue()));
    L7:
        if (this.extendedLastAccessedAtSeconds != null) goto L9;
        return null;
    L9:
        return Long.valueOf(r4.intValue() * 1000);
    }

    public final Long getLastModifiedAtMillis$okio() {
        Long r0 = this.ntfsLastModifiedAtFiletime;
        if (r0 == null) goto L7;
        return Long.valueOf(ZipFilesKt.filetimeToEpochMillis(r0.longValue()));
    L7:
        if (this.extendedLastModifiedAtSeconds != null) goto L9;
        int r02 = this.dosLastModifiedAtTime;
        if (r02 != (-1)) goto L13;
        return null;
    L13:
        return ZipFilesKt.dosDateTimeToEpochMillis(this.dosLastModifiedAtDate, r02);
    L9:
        return Long.valueOf(r0.intValue() * 1000);
    }

    public final long getOffset() {
        return this.offset;
    }

    public final long getSize() {
        return this.size;
    }

    public final boolean isDirectory() {
        return this.isDirectory;
    }
}
