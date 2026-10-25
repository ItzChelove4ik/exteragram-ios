package org.simplifiles.archive;

import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.Path;
import j$.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;
import org.simplifiles.archive.security.SecurityPolicy;
import org.simplifiles.exception.ArchiveOperationCanceledException;
import org.simplifiles.exception.ArchiveValidationException;
import org.simplifiles.exception.CorruptedArchiveException;
import org.simplifiles.files.SimpliDirectory;
import org.simplifiles.internal.archive.ArchiveFormatDetector;
import org.simplifiles.internal.archive.ArchiveValidator;
import org.simplifiles.internal.archive.zip.ZipArchiveExtractor;
import org.simplifiles.internal.archive.zip.ZipArchiveReader;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u0001B\u001b\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0011\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0012¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u001d\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u001a\u0010\u001cJ\u0015\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\u001a\u0010\u001fJ\u001d\u0010!\u001a\u00020 2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b!\u0010\"J\u001d\u0010!\u001a\u00020 2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b!\u0010#J\u001a\u0010&\u001a\u00020%2\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b&\u0010'J\u000f\u0010)\u001a\u00020(H\u0016¢\u0006\u0004\b)\u0010*J\u000f\u0010+\u001a\u00020\rH\u0016¢\u0006\u0004\b+\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010,\u001a\u0004\b-\u0010.R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010/\u001a\u0004\b0\u00101¨\u00062"}, d2 = {"Lorg/simplifiles/archive/ArchiveSource;", _UrlKt.FRAGMENT_ENCODE_SET, "j$/nio/file/Path", "path", "Lorg/simplifiles/archive/security/SecurityPolicy;", "policy", "<init>", "(Lj$/nio/file/Path;Lorg/simplifiles/archive/security/SecurityPolicy;)V", "Lorg/simplifiles/archive/ArchiveExtractionOptions;", "options", _UrlKt.FRAGMENT_ENCODE_SET, "ensureNotCanceled", "(Lorg/simplifiles/archive/ArchiveExtractionOptions;)V", _UrlKt.FRAGMENT_ENCODE_SET, "unreadableFileReason", "()Ljava/lang/String;", "code", "message", "Lorg/simplifiles/archive/ValidationReport;", "blockerReport", "(Ljava/lang/String;Ljava/lang/String;)Lorg/simplifiles/archive/ValidationReport;", "withPolicy", "(Lorg/simplifiles/archive/security/SecurityPolicy;)Lorg/simplifiles/archive/ArchiveSource;", "validate", "()Lorg/simplifiles/archive/ValidationReport;", "Lorg/simplifiles/archive/ExtractedArchive;", "extractTo", "(Lj$/nio/file/Path;)Lorg/simplifiles/archive/ExtractedArchive;", "(Lj$/nio/file/Path;Lorg/simplifiles/archive/ArchiveExtractionOptions;)Lorg/simplifiles/archive/ExtractedArchive;", "Ljava/io/File;", "file", "(Ljava/io/File;)Lorg/simplifiles/archive/ExtractedArchive;", "Lorg/simplifiles/files/SimpliDirectory;", "extractToDirectory", "(Lj$/nio/file/Path;Lorg/simplifiles/archive/ArchiveExtractionOptions;)Lorg/simplifiles/files/SimpliDirectory;", "(Ljava/io/File;Lorg/simplifiles/archive/ArchiveExtractionOptions;)Lorg/simplifiles/files/SimpliDirectory;", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "toString", "Lj$/nio/file/Path;", "getPath", "()Lj$/nio/file/Path;", "Lorg/simplifiles/archive/security/SecurityPolicy;", "getPolicy", "()Lorg/simplifiles/archive/security/SecurityPolicy;", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ArchiveSource {
    private final Path path;
    private final SecurityPolicy policy;

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[ArchiveFormat.values().length];
            r0[ArchiveFormat.ZIP.ordinal()] = 1;     // Catch: NoSuchFieldError -> L6
        L4:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public ArchiveSource(Path r1, SecurityPolicy r2) {
        this.path = r1;
        this.policy = r2;
    }

    public /* synthetic */ ArchiveSource(Path r1, SecurityPolicy r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = SecurityPolicy.Companion.strict();
    L5:
        this(r1, r2);
    }

    private final ValidationReport blockerReport(String r9, String r10) {
        return new ValidationReport(null, null, CollectionsKt.listOf(new ArchiveIssue(ArchiveIssueSeverity.BLOCKER, r9, r10, null, 8, null)), 3, null);
    }

    private final void ensureNotCanceled(ArchiveExtractionOptions r1) {
        if (r1.getCancellationToken().isCancellationRequested() == true) goto L6;
        return;
    L6:
        throw new ArchiveOperationCanceledException();
    }

    private final String unreadableFileReason() {
        if (Files.exists(this.path, new LinkOption[0]) == true) goto L7;
        return "Archive file does not exist.";
    L7:
        if (Files.isRegularFile(this.path, new LinkOption[0]) == true) goto L10;
        return "Archive path is not a regular file.";
    L10:
        return null;
    }

    public boolean equals(Object r3) {
        if (this != r3) goto L4;
        return true;
    L4:
        if ((r3 instanceof ArchiveSource) == false) goto L10;
        ArchiveSource r32 = (ArchiveSource) r3;
        if (Intrinsics.areEqual(this.path, r32.path) == true) goto L8;
        return false;
    L8:
        if (Intrinsics.areEqual(this.policy, r32.policy) == true) goto L16;
        return false;
    L16:
        return true;
    L10:
        return false;
    }

    public final ExtractedArchive extractTo(Path r2) {
        return extractTo(r2, ArchiveExtractionOptions.Companion.defaults());
    }

    public final ExtractedArchive extractTo(Path r11, ArchiveExtractionOptions r12) {
        ensureNotCanceled(r12);
        ValidationReport r0 = validate();
        if (r0.isSafe() == false) goto L18;
        ensureNotCanceled(r12);
        ArchiveFormat r1 = r0.getFormat();
        if (r1 != null) goto L7;
        int r13 = -1;
    L8:
        if (r13 == (-1)) goto L16;
        if (r13 == 1) goto L12;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L12:
        return ZipArchiveExtractor.INSTANCE.extract(this.path, r11, this.policy, false, r0.getEntries(), r12);
    L16:
        throw new ArchiveValidationException(r0);
    L7:
        r13 = WhenMappings.$EnumSwitchMapping$0[r1.ordinal()];
        goto L8
    L18:
        throw new ArchiveValidationException(r0);
    }

    public final ExtractedArchive extractTo(File r2) {
        return extractTo(Paths.get(r2.getPath(), new String[0]));
    }

    public final SimpliDirectory extractToDirectory(Path r1, ArchiveExtractionOptions r2) {
        ExtractedArchive r0 = extractTo(r1, r2);
        SimpliDirectory r12 = new SimpliDirectory(r0.getRoot());     // Catch: Throwable -> L6
        AutoCloseableKt.closeFinally(r0, null);
        return r12;
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        AutoCloseableKt.closeFinally(r0, th);
        throw th;
    }

    public final SimpliDirectory extractToDirectory(File r2, ArchiveExtractionOptions r3) {
        return extractToDirectory(Paths.get(r2.getPath(), new String[0]), r3);
    }

    public int hashCode() {
        return (this.path.hashCode() * 31) + this.policy.hashCode();
    }

    public String toString() {
        return "ArchiveSource(path=" + this.path + ", policy=" + this.policy + ')';
    }

    public final ValidationReport validate() {
        String r0 = unreadableFileReason();
        if (r0 != null) goto L5;
        ArchiveFormat r4 = ArchiveFormatDetector.INSTANCE.detect(this.path);     // Catch: IOException -> L24
        if (r4 == null) goto L9;
    L15:
        e = move-exception;
        ArchiveIssueSeverity r6 = ArchiveIssueSeverity.BLOCKER;
        String r12 = e.getMessage();
        if (r12 != null) goto L23;
        r12 = "Archive is corrupted.";
    L23:
        return new ValidationReport(r4, null, CollectionsKt.listOf(new ArchiveIssue(r6, "archive.corrupted", r12, null, 8, null)), 2, null);
    L11:
        if (WhenMappings.$EnumSwitchMapping$0[r4.ordinal()] != 1) goto L18;
        ArchiveInspection r02 = ZipArchiveReader.INSTANCE.inspect(this.path);     // Catch: CorruptedArchiveException -> L15
        return ArchiveValidator.INSTANCE.validate(r02, this.policy);
    L18:
        throw new NoWhenBranchMatchedException();     // Catch: CorruptedArchiveException -> L15
    L9:
        return blockerReport("archive.format.unsupported", "Unsupported archive format.");
    L24:
        e = move-exception;
        String r03 = e.getMessage();
        if (r03 != null) goto L29;
        r03 = "Archive cannot be read.";
    L29:
        return blockerReport("archive.unreadable", r03);
    L5:
        return blockerReport("archive.unreadable", r0);
    }

    public final ArchiveSource withPolicy(SecurityPolicy r2) {
        return new ArchiveSource(this.path, r2);
    }
}
