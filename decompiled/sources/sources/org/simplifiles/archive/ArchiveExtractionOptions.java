package org.simplifiles.archive;

import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0011\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001b\u001cB3\b\u0007\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Lorg/simplifiles/archive/ArchiveExtractionOptions;", _UrlKt.FRAGMENT_ENCODE_SET, "Lorg/simplifiles/archive/ArchiveProgressListener;", "progressListener", "Lorg/simplifiles/archive/CancellationToken;", "cancellationToken", _UrlKt.FRAGMENT_ENCODE_SET, "bufferSize", "Lorg/simplifiles/archive/ExtractionTargetPolicy;", "targetPolicy", "<init>", "(Lorg/simplifiles/archive/ArchiveProgressListener;Lorg/simplifiles/archive/CancellationToken;ILorg/simplifiles/archive/ExtractionTargetPolicy;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Lorg/simplifiles/archive/CancellationToken;", "getCancellationToken", "()Lorg/simplifiles/archive/CancellationToken;", "I", "getBufferSize", "()I", "Lorg/simplifiles/archive/ExtractionTargetPolicy;", "getTargetPolicy", "()Lorg/simplifiles/archive/ExtractionTargetPolicy;", "Lorg/simplifiles/archive/ArchiveProgressListener;", "getProgressListener", "()Lorg/simplifiles/archive/ArchiveProgressListener;", "Companion", "Builder", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nArchiveExtractionOptions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ArchiveExtractionOptions.kt\norg/simplifiles/archive/ArchiveExtractionOptions\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,76:1\n1#2:77\n*E\n"})
/* loaded from: classes.dex */
public final class ArchiveExtractionOptions {
    public static final Companion Companion = null;
    private final int bufferSize;
    private final CancellationToken cancellationToken;
    private final ExtractionTargetPolicy targetPolicy;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\b\u0000\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\f\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0002¢\u0006\u0004\b\u000e\u0010\u000fR\u0016\u0010\b\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\b\u0010\u0010R\u0016\u0010\u0012\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0016\u0010\f\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/simplifiles/archive/ArchiveExtractionOptions$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "Lorg/simplifiles/archive/ArchiveExtractionOptions;", "options", "<init>", "(Lorg/simplifiles/archive/ArchiveExtractionOptions;)V", "Lorg/simplifiles/archive/CancellationToken;", "token", "cancellationToken", "(Lorg/simplifiles/archive/CancellationToken;)Lorg/simplifiles/archive/ArchiveExtractionOptions$Builder;", "Lorg/simplifiles/archive/ExtractionTargetPolicy;", "policy", "targetPolicy", "(Lorg/simplifiles/archive/ExtractionTargetPolicy;)Lorg/simplifiles/archive/ArchiveExtractionOptions$Builder;", "build", "()Lorg/simplifiles/archive/ArchiveExtractionOptions;", "Lorg/simplifiles/archive/CancellationToken;", _UrlKt.FRAGMENT_ENCODE_SET, "bufferSize", "I", "Lorg/simplifiles/archive/ExtractionTargetPolicy;", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Builder {
        private int bufferSize;
        private CancellationToken cancellationToken;
        private ExtractionTargetPolicy targetPolicy;

        public Builder(ArchiveExtractionOptions r2) {
            r2.getProgressListener();
            this.cancellationToken = r2.getCancellationToken();
            this.bufferSize = r2.getBufferSize();
            this.targetPolicy = r2.getTargetPolicy();
        }

        public /* synthetic */ Builder(ArchiveExtractionOptions r1, int r2, DefaultConstructorMarker r3) {
            if ((r2 & 1) == 0) goto L5;
            r1 = ArchiveExtractionOptions.Companion.defaults();
        L5:
            this(r1);
        }

        public final ArchiveExtractionOptions build() {
            return new ArchiveExtractionOptions(null, this.cancellationToken, this.bufferSize, this.targetPolicy);
        }

        public final Builder cancellationToken(CancellationToken r1) {
            this.cancellationToken = r1;
            return this;
        }

        public final Builder targetPolicy(ExtractionTargetPolicy r1) {
            this.targetPolicy = r1;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0007J\b\u0010\b\u001a\u00020\tH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/simplifiles/archive/ArchiveExtractionOptions$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "DEFAULT_BUFFER_SIZE", _UrlKt.FRAGMENT_ENCODE_SET, "defaults", "Lorg/simplifiles/archive/ArchiveExtractionOptions;", "builder", "Lorg/simplifiles/archive/ArchiveExtractionOptions$Builder;", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
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
        public final ArchiveExtractionOptions defaults() {
            return new ArchiveExtractionOptions(null, null, 0, null, 15, null);
        }
    }

    static {
        Companion = new Companion(null);
    }

    @JvmOverloads
    public ArchiveExtractionOptions(ArchiveProgressListener r1, CancellationToken r2, int r3, ExtractionTargetPolicy r4) {
        this.cancellationToken = r2;
        this.bufferSize = r3;
        this.targetPolicy = r4;
        if (r3 <= 0) goto L5;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("bufferSize must be positive.");
        throw null;
    }

    public /* synthetic */ ArchiveExtractionOptions(ArchiveProgressListener r1, CancellationToken r2, int r3, ExtractionTargetPolicy r4, int r5, DefaultConstructorMarker r6) {
        if ((r5 & 1) == 0) goto L6;
        r1 = null;
    L6:
        if ((r5 & 2) == 0) goto L9;
        r2 = CancellationToken.Companion.none();
    L9:
        if ((r5 & 4) == 0) goto L12;
        r3 = 65536;
    L12:
        if ((r5 & 8) == 0) goto L14;
        r4 = ExtractionTargetPolicy.ERROR_IF_NOT_EMPTY;
    L14:
        this(r1, r2, r3, r4);
    }

    public final int getBufferSize() {
        return this.bufferSize;
    }

    public final CancellationToken getCancellationToken() {
        return this.cancellationToken;
    }

    public final ArchiveProgressListener getProgressListener() {
        return null;
    }

    public final ExtractionTargetPolicy getTargetPolicy() {
        return this.targetPolicy;
    }

    public String toString() {
        return "ArchiveExtractionOptions(bufferSize=" + this.bufferSize + ", targetPolicy=" + this.targetPolicy + ", hasProgressListener=false)";
    }
}
