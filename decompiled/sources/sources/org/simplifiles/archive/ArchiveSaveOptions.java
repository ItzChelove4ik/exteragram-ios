package org.simplifiles.archive;

import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.simplifiles.files.OverwritePolicy;
import org.simplifiles.files.SymlinkPolicy;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u001b\u0018\u0000 ,2\u00020\u0001:\u0002,-B[\b\u0007\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\n\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\n\u0010\u0019\u001a\u0004\b\u001f\u0010\u001bR\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010 \u001a\u0004\b!\u0010\"R\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010#\u001a\u0004\b$\u0010%R\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010&\u001a\u0004\b'\u0010(R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010)\u001a\u0004\b*\u0010+¨\u0006."}, d2 = {"Lorg/simplifiles/archive/ArchiveSaveOptions;", _UrlKt.FRAGMENT_ENCODE_SET, "Lorg/simplifiles/archive/ArchiveSaveProgressListener;", "progressListener", "Lorg/simplifiles/archive/CancellationToken;", "cancellationToken", _UrlKt.FRAGMENT_ENCODE_SET, "bufferSize", "Lorg/simplifiles/files/OverwritePolicy;", "overwritePolicy", "compressionLevel", "Lorg/simplifiles/archive/ArchiveEntryFilter;", "entryFilter", "Lorg/simplifiles/files/SymlinkPolicy;", "symlinkPolicy", _UrlKt.FRAGMENT_ENCODE_SET, "entryTimestamp", "<init>", "(Lorg/simplifiles/archive/ArchiveSaveProgressListener;Lorg/simplifiles/archive/CancellationToken;ILorg/simplifiles/files/OverwritePolicy;ILorg/simplifiles/archive/ArchiveEntryFilter;Lorg/simplifiles/files/SymlinkPolicy;J)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Lorg/simplifiles/archive/CancellationToken;", "getCancellationToken", "()Lorg/simplifiles/archive/CancellationToken;", "I", "getBufferSize", "()I", "Lorg/simplifiles/files/OverwritePolicy;", "getOverwritePolicy", "()Lorg/simplifiles/files/OverwritePolicy;", "getCompressionLevel", "Lorg/simplifiles/archive/ArchiveEntryFilter;", "getEntryFilter", "()Lorg/simplifiles/archive/ArchiveEntryFilter;", "Lorg/simplifiles/files/SymlinkPolicy;", "getSymlinkPolicy", "()Lorg/simplifiles/files/SymlinkPolicy;", "J", "getEntryTimestamp", "()J", "Lorg/simplifiles/archive/ArchiveSaveProgressListener;", "getProgressListener", "()Lorg/simplifiles/archive/ArchiveSaveProgressListener;", "Companion", "Builder", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nArchiveSaveOptions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ArchiveSaveOptions.kt\norg/simplifiles/archive/ArchiveSaveOptions\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,133:1\n1#2:134\n*E\n"})
/* loaded from: classes.dex */
public final class ArchiveSaveOptions {
    public static final Companion Companion = null;
    private final int bufferSize;
    private final CancellationToken cancellationToken;
    private final int compressionLevel;
    private final ArchiveEntryFilter entryFilter;
    private final long entryTimestamp;
    private final OverwritePolicy overwritePolicy;
    private final SymlinkPolicy symlinkPolicy;

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0013\b\u0000\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\f\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0002¢\u0006\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0011\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0016\u0010\b\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\b\u0010\u0015R\u0016\u0010\f\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\u0014R\u0016\u0010\u0017\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001a\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001d\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"Lorg/simplifiles/archive/ArchiveSaveOptions$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "Lorg/simplifiles/archive/ArchiveSaveOptions;", "options", "<init>", "(Lorg/simplifiles/archive/ArchiveSaveOptions;)V", "Lorg/simplifiles/files/OverwritePolicy;", "policy", "overwritePolicy", "(Lorg/simplifiles/files/OverwritePolicy;)Lorg/simplifiles/archive/ArchiveSaveOptions$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "value", "compressionLevel", "(I)Lorg/simplifiles/archive/ArchiveSaveOptions$Builder;", "build", "()Lorg/simplifiles/archive/ArchiveSaveOptions;", "Lorg/simplifiles/archive/CancellationToken;", "cancellationToken", "Lorg/simplifiles/archive/CancellationToken;", "bufferSize", "I", "Lorg/simplifiles/files/OverwritePolicy;", "Lorg/simplifiles/archive/ArchiveEntryFilter;", "entryFilter", "Lorg/simplifiles/archive/ArchiveEntryFilter;", "Lorg/simplifiles/files/SymlinkPolicy;", "symlinkPolicy", "Lorg/simplifiles/files/SymlinkPolicy;", _UrlKt.FRAGMENT_ENCODE_SET, "entryTimestamp", "J", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Builder {
        private int bufferSize;
        private CancellationToken cancellationToken;
        private int compressionLevel;
        private ArchiveEntryFilter entryFilter;
        private long entryTimestamp;
        private OverwritePolicy overwritePolicy;
        private SymlinkPolicy symlinkPolicy;

        public Builder(ArchiveSaveOptions r3) {
            r3.getProgressListener();
            this.cancellationToken = r3.getCancellationToken();
            this.bufferSize = r3.getBufferSize();
            this.overwritePolicy = r3.getOverwritePolicy();
            this.compressionLevel = r3.getCompressionLevel();
            this.entryFilter = r3.getEntryFilter();
            this.symlinkPolicy = r3.getSymlinkPolicy();
            this.entryTimestamp = r3.getEntryTimestamp();
        }

        public /* synthetic */ Builder(ArchiveSaveOptions r1, int r2, DefaultConstructorMarker r3) {
            if ((r2 & 1) == 0) goto L5;
            r1 = ArchiveSaveOptions.Companion.defaults();
        L5:
            this(r1);
        }

        public final ArchiveSaveOptions build() {
            return new ArchiveSaveOptions(null, this.cancellationToken, this.bufferSize, this.overwritePolicy, this.compressionLevel, this.entryFilter, this.symlinkPolicy, this.entryTimestamp);
        }

        public final Builder compressionLevel(int r1) {
            this.compressionLevel = r1;
            return this;
        }

        public final Builder overwritePolicy(OverwritePolicy r1) {
            this.overwritePolicy = r1;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/simplifiles/archive/ArchiveSaveOptions$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "DEFAULT_BUFFER_SIZE", _UrlKt.FRAGMENT_ENCODE_SET, "DEFAULT_COMPRESSION_LEVEL", "NO_COMPRESSION_LEVEL", "BEST_SPEED_LEVEL", "BEST_COMPRESSION_LEVEL", "PRESERVE_SOURCE_TIMESTAMP", _UrlKt.FRAGMENT_ENCODE_SET, "defaults", "Lorg/simplifiles/archive/ArchiveSaveOptions;", "builder", "Lorg/simplifiles/archive/ArchiveSaveOptions$Builder;", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
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
        public final ArchiveSaveOptions defaults() {
            return new ArchiveSaveOptions(null, null, 0, null, 0, null, null, 0, 255, null);
        }
    }

    static {
        Companion = new Companion(null);
    }

    @JvmOverloads
    public ArchiveSaveOptions(ArchiveSaveProgressListener r1, CancellationToken r2, int r3, OverwritePolicy r4, int r5, ArchiveEntryFilter r6, SymlinkPolicy r7, long r8) {
        this.cancellationToken = r2;
        this.bufferSize = r3;
        this.overwritePolicy = r4;
        this.compressionLevel = r5;
        this.entryFilter = r6;
        this.symlinkPolicy = r7;
        this.entryTimestamp = r8;
        if (r3 > 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("bufferSize must be positive.");
        throw null;
    L5:
        if ((-1) <= r5) goto L7;
    L16:
        f$$ExternalSyntheticBUOutline1.m("compressionLevel must be between -1 and 9.");
        throw null;
    L7:
        if (r5 >= 10) goto L16;
        if (r8 != (-1)) goto L11;
        return;
    L11:
        if (r8 >= 0) goto L20;
        f$$ExternalSyntheticBUOutline1.m("entryTimestamp must be PRESERVE_SOURCE_TIMESTAMP or a non-negative epoch millisecond value.");
        throw null;
    }

    public /* synthetic */ ArchiveSaveOptions(ArchiveSaveProgressListener r1, CancellationToken r2, int r3, OverwritePolicy r4, int r5, ArchiveEntryFilter r6, SymlinkPolicy r7, long r8, int r10, DefaultConstructorMarker r11) {
        if ((r10 & 1) == 0) goto L6;
        r1 = null;
    L6:
        if ((r10 & 2) == 0) goto L9;
        r2 = CancellationToken.Companion.none();
    L9:
        if ((r10 & 4) == 0) goto L12;
        r3 = 65536;
    L12:
        if ((r10 & 8) == 0) goto L15;
        r4 = OverwritePolicy.ERROR;
    L15:
        if ((r10 & 16) == 0) goto L18;
        r5 = -1;
    L18:
        if ((r10 & 32) == 0) goto L21;
        r6 = ArchiveEntryFilter.Companion.includeAll();
    L21:
        if ((r10 & 64) == 0) goto L24;
        r7 = SymlinkPolicy.SKIP;
    L24:
        if ((r10 & 128) == 0) goto L26;
        r8 = -1;
    L26:
        long r102 = r8;
        ArchiveEntryFilter r82 = r6;
        SymlinkPolicy r9 = r7;
        OverwritePolicy r62 = r4;
        int r72 = r5;
        int r52 = r3;
        ArchiveSaveProgressListener r32 = r1;
        this(r32, r2, r52, r62, r72, r82, r9, r102);
    }

    public final int getBufferSize() {
        return this.bufferSize;
    }

    public final CancellationToken getCancellationToken() {
        return this.cancellationToken;
    }

    public final int getCompressionLevel() {
        return this.compressionLevel;
    }

    public final ArchiveEntryFilter getEntryFilter() {
        return this.entryFilter;
    }

    public final long getEntryTimestamp() {
        return this.entryTimestamp;
    }

    public final OverwritePolicy getOverwritePolicy() {
        return this.overwritePolicy;
    }

    public final ArchiveSaveProgressListener getProgressListener() {
        return null;
    }

    public final SymlinkPolicy getSymlinkPolicy() {
        return this.symlinkPolicy;
    }

    public String toString() {
        return "ArchiveSaveOptions(bufferSize=" + this.bufferSize + ", overwritePolicy=" + this.overwritePolicy + ", compressionLevel=" + this.compressionLevel + ", symlinkPolicy=" + this.symlinkPolicy + ", entryTimestamp=" + this.entryTimestamp + ", hasProgressListener=false)";
    }
}
