package org.simplifiles.internal.archive.zip;

import androidx.room.BaseRoomConnectionManager$$ExternalSyntheticBUOutline0;
import com.exteragram.messenger.plugins.PluginsConstants;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.OpenOption;
import j$.nio.file.Path;
import j$.nio.file.StandardOpenOption;
import j$.nio.file.attribute.FileAttribute;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlin.time.InstantKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;
import org.simplifiles.archive.ArchiveEntryInfo;
import org.simplifiles.archive.ArchiveExtractionOptions;
import org.simplifiles.archive.ArchiveFormat;
import org.simplifiles.archive.ArchiveIssue;
import org.simplifiles.archive.ArchiveIssueSeverity;
import org.simplifiles.archive.ExtractedArchive;
import org.simplifiles.archive.ExtractionTargetPolicy;
import org.simplifiles.archive.ValidationReport;
import org.simplifiles.archive.security.DuplicatePolicy;
import org.simplifiles.archive.security.SecurityPolicy;
import org.simplifiles.exception.ArchiveOperationCanceledException;
import org.simplifiles.exception.ArchiveValidationException;
import org.simplifiles.exception.ExtractionTargetException;
import org.simplifiles.internal.SaturatingMathKt;
import org.simplifiles.internal.archive.ArchivePathAnalyzer;
import org.simplifiles.internal.archive.ArchivePathResolver;
import org.simplifiles.internal.io.FileTreeCleaner;

@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001CB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\f\u0010\rJ7\u0010\u0015\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J/\u0010\u001c\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0018\u001a\u00020\u00172\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ%\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0002¢\u0006\u0004\b\u001f\u0010 JO\u0010(\u001a\u00020%2\u0006\u0010\"\u001a\u00020!2\u0006\u0010$\u001a\u00020#2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010&\u001a\u00020%2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010'\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b(\u0010)J\u001d\u0010-\u001a\u00020%2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020+0*H\u0002¢\u0006\u0004\b-\u0010.J/\u00101\u001a\u00020%2\u0006\u0010/\u001a\u00020%2\u0006\u00100\u001a\u00020%2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u001aH\u0002¢\u0006\u0004\b1\u00102J/\u00103\u001a\u00020%2\u0006\u0010/\u001a\u00020%2\u0006\u00100\u001a\u00020%2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u001aH\u0002¢\u0006\u0004\b3\u00102J'\u00105\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u00104\u001a\u00020%2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b5\u00106J+\u0010:\u001a\u0002092\u0006\u00107\u001a\u00020\u001a2\u0006\u00108\u001a\u00020\u001a2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001aH\u0002¢\u0006\u0004\b:\u0010;JC\u0010A\u001a\u00020@2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010=\u001a\u00020\u000b2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020+0*2\u0006\u0010?\u001a\u00020>¢\u0006\u0004\bA\u0010B¨\u0006D"}, d2 = {"Lorg/simplifiles/internal/archive/zip/ZipArchiveExtractor;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "j$/nio/file/Path", "root", "Lorg/simplifiles/archive/ExtractionTargetPolicy;", "targetPolicy", _UrlKt.FRAGMENT_ENCODE_SET, "prepareTarget", "(Lj$/nio/file/Path;Lorg/simplifiles/archive/ExtractionTargetPolicy;)V", _UrlKt.FRAGMENT_ENCODE_SET, "isDirectoryEmpty", "(Lj$/nio/file/Path;)Z", "source", "Lorg/simplifiles/archive/security/SecurityPolicy;", "policy", "Lorg/simplifiles/internal/archive/zip/ZipArchiveExtractor$ExtractionProgress;", "progress", _UrlKt.FRAGMENT_ENCODE_SET, "bufferSize", "extractEntries", "(Lj$/nio/file/Path;Lj$/nio/file/Path;Lorg/simplifiles/archive/security/SecurityPolicy;Lorg/simplifiles/internal/archive/zip/ZipArchiveExtractor$ExtractionProgress;I)V", "Ljava/util/zip/ZipEntry;", "entry", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "destinationPaths", "destinationPathFor", "(Ljava/util/zip/ZipEntry;Ljava/util/Set;Lorg/simplifiles/archive/security/SecurityPolicy;)Ljava/lang/String;", "path", "renamedPath", "(Ljava/lang/String;Ljava/util/Set;)Ljava/lang/String;", "Ljava/io/InputStream;", PluginsConstants.Settings.TYPE_INPUT, "Ljava/io/OutputStream;", "output", _UrlKt.FRAGMENT_ENCODE_SET, "totalWritten", "destinationPath", "copyWithLimits", "(Ljava/io/InputStream;Ljava/io/OutputStream;Ljava/util/zip/ZipEntry;JLorg/simplifiles/archive/security/SecurityPolicy;Lorg/simplifiles/internal/archive/zip/ZipArchiveExtractor$ExtractionProgress;Ljava/lang/String;I)J", _UrlKt.FRAGMENT_ENCODE_SET, "Lorg/simplifiles/archive/ArchiveEntryInfo;", "entries", "knownUncompressedSize", "(Ljava/util/List;)J", "current", "added", "checkedAddEntry", "(JJLorg/simplifiles/archive/security/SecurityPolicy;Ljava/lang/String;)J", "checkedAddTotal", "written", "validateRuntimeCompressionRatio", "(Ljava/util/zip/ZipEntry;JLorg/simplifiles/archive/security/SecurityPolicy;)V", "code", "message", _UrlKt.FRAGMENT_ENCODE_SET, "failValidation", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Void;", "targetRoot", "cleanupOnClose", "Lorg/simplifiles/archive/ArchiveExtractionOptions;", "options", "Lorg/simplifiles/archive/ExtractedArchive;", "extract", "(Lj$/nio/file/Path;Lj$/nio/file/Path;Lorg/simplifiles/archive/security/SecurityPolicy;ZLjava/util/List;Lorg/simplifiles/archive/ArchiveExtractionOptions;)Lorg/simplifiles/archive/ExtractedArchive;", "ExtractionProgress", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nZipArchiveExtractor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ZipArchiveExtractor.kt\norg/simplifiles/internal/archive/zip/ZipArchiveExtractor\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,392:1\n1#2:393\n1313#3,3:394\n*S KotlinDebug\n*F\n+ 1 ZipArchiveExtractor.kt\norg/simplifiles/internal/archive/zip/ZipArchiveExtractor\n*L\n257#1:394,3\n*E\n"})
/* loaded from: classes5.dex */
public final class ZipArchiveExtractor {
    public static final ZipArchiveExtractor INSTANCE = null;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\n\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0011\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/simplifiles/internal/archive/zip/ZipArchiveExtractor$ExtractionProgress;", _UrlKt.FRAGMENT_ENCODE_SET, "options", "Lorg/simplifiles/archive/ArchiveExtractionOptions;", "totalEntries", _UrlKt.FRAGMENT_ENCODE_SET, "totalBytes", "<init>", "(Lorg/simplifiles/archive/ArchiveExtractionOptions;JJ)V", "entriesProcessed", "bytesWritten", "checkCanceled", _UrlKt.FRAGMENT_ENCODE_SET, "bytes", "currentEntryPath", _UrlKt.FRAGMENT_ENCODE_SET, "entryCompleted", "emit", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class ExtractionProgress {
        private long bytesWritten;
        private long entriesProcessed;
        private final ArchiveExtractionOptions options;
        private final long totalBytes;
        private final long totalEntries;

        public ExtractionProgress(ArchiveExtractionOptions r1, long r2, long r4) {
            this.options = r1;
            this.totalEntries = r2;
            this.totalBytes = r4;
        }

        public final void bytesWritten(long r3, String r5) {
            this.bytesWritten += r3;
            emit(r5);
        }

        public final void checkCanceled() {
            if (this.options.getCancellationToken().isCancellationRequested() == true) goto L6;
            return;
        L6:
            throw new ArchiveOperationCanceledException();
        }

        public final void emit(String r1) {
            this.options.getProgressListener();
        }

        public final void entryCompleted(String r5) {
            this.entriesProcessed++;
            emit(r5);
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = null;

        static {
            int[] r0 = new int[ExtractionTargetPolicy.values().length];
            r0[ExtractionTargetPolicy.ERROR_IF_NOT_EMPTY.ordinal()] = 1;     // Catch: NoSuchFieldError -> L15
        L24:
            r0[ExtractionTargetPolicy.CLEAN.ordinal()] = 2;     // Catch: NoSuchFieldError -> L16
        L30:
            r0[ExtractionTargetPolicy.REPLACE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L17
        L8:
            $EnumSwitchMapping$0 = r0;
            int[] r02 = new int[DuplicatePolicy.values().length];
            r02[DuplicatePolicy.ERROR.ordinal()] = 1;     // Catch: NoSuchFieldError -> L18
        L26:
            r02[DuplicatePolicy.KEEP_FIRST.ordinal()] = 2;     // Catch: NoSuchFieldError -> L19
        L32:
            r02[DuplicatePolicy.KEEP_LAST.ordinal()] = 3;     // Catch: NoSuchFieldError -> L20
        L22:
            r02[DuplicatePolicy.RENAME.ordinal()] = 4;     // Catch: NoSuchFieldError -> L21
        L13:
            $EnumSwitchMapping$1 = r02;
        }
    }

    /* renamed from: $r8$lambda$HFw4Gzget5hn-Vs1MWAMsnPLKxQ, reason: not valid java name */
    public static boolean m3859$r8$lambda$HFw4Gzget5hnVs1MWAMsnPLKxQ(ArchiveEntryInfo r0) {
        return r0.isDirectory();
    }

    public static boolean $r8$lambda$I0VupinfQHn1k287rurTObXXIUI(long r2) {
        if (r2 <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    /* renamed from: $r8$lambda$lFWqiBEwLyJA6HC2-yPi_0wUfHk, reason: not valid java name */
    public static long m3860$r8$lambda$lFWqiBEwLyJA6HC2yPi_0wUfHk(ArchiveEntryInfo r2) {
        return r2.getUncompressedSize();
    }

    static {
        INSTANCE = new ZipArchiveExtractor();
    }

    private ZipArchiveExtractor() {
    }

    private final long checkedAddEntry(long r4, long r6, SecurityPolicy r8, String r9) {
        if ((LongCompanionObject.MAX_VALUE - r4) < r6) goto L9;
        long r42 = r4 + r6;
        if (r42 > r8.getMaxSingleFileSize()) goto L7;
        return r42;
    L7:
        failValidation("archive.entry.size.too_large", "Archive entry exceeded " + r8.getMaxSingleFileSize() + " bytes while extracting.", r9);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L9:
        failValidation("archive.entry.size.overflow", "Archive entry size overflows Long.", r9);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    private final long checkedAddTotal(long r4, long r6, SecurityPolicy r8, String r9) {
        if ((LongCompanionObject.MAX_VALUE - r4) < r6) goto L9;
        long r42 = r4 + r6;
        if (r42 > r8.getMaxTotalUncompressedSize()) goto L7;
        return r42;
    L7:
        failValidation("archive.total_size.too_large", "Archive exceeded " + r8.getMaxTotalUncompressedSize() + " total bytes while extracting.", r9);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L9:
        failValidation("archive.total_size.overflow", "Archive total size overflows Long.", r9);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    private final long copyWithLimits(InputStream r18, OutputStream r19, ZipEntry r20, long r21, SecurityPolicy r23, ExtractionProgress r24, String r25, int r26) {
        byte[] r0 = new byte[r26];
        long r4 = 0;
    L3:
        r24.checkCanceled();
        int r2 = r18.read(r0);
        if (r2 < 0) goto L5;
        long r6 = r2;
        long r13 = checkedAddEntry(r4, r6, r23, r20.getName());
        checkedAddTotal(r21, r13, r23, r20.getName());
        r19.write(r0, 0, r2);
        r24.bytesWritten(r6, r25);
        r4 = r13;
        goto L3
    L5:
        return r4;
    }

    private final String destinationPathFor(ZipEntry r4, Set<String> r5, SecurityPolicy r6) {
        String r0 = ArchivePathAnalyzer.INSTANCE.analyze(r4.getName()).getNormalizedPath();
        if (r0 != null) goto L5;
        failValidation("archive.entry.path.invalid", "Archive entry path is invalid.", r4.getName());
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        if (r5.add(r0) == true) goto L19;
        int r62 = WhenMappings.$EnumSwitchMapping$1[r6.getDuplicatePolicy().ordinal()];
        if (r62 != 1) goto L10;
        failValidation("archive.entry.duplicate", "Archive contains duplicate entry path: " + r0 + '.', r4.getName());
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L10:
        if (r62 != 2) goto L12;
        return null;
    L12:
        if (r62 == 3) goto L19;
        if (r62 == 4) goto L16;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L16:
        return renamedPath(r0, r5);
    L19:
        return r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [org.simplifiles.internal.archive.zip.ZipArchiveExtractor] */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.io.Closeable, java.util.zip.ZipFile] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.zip.ZipEntry] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.io.Closeable] */
    private final void extractEntries(Path r20, Path r21, SecurityPolicy r22, ExtractionProgress r23, int r24) {
        SecurityPolicy r5 = r22;
        ?? r10 = new ZipFile(r20.toFile());
        LinkedHashSet r11 = new LinkedHashSet();     // Catch: Throwable -> L9
        Iterator r12 = SequencesKt.asSequence(CollectionsKt.iterator(r10.entries())).iterator();     // Catch: Throwable -> L9
        long r1 = 0;
    L5:
        if (r12.hasNext() == false) goto L40;
        ?? r3 = (ZipEntry) r12.next();     // Catch: Throwable -> L9
        r23.checkCanceled();     // Catch: Throwable -> L9
        ?? r0 = INSTANCE;     // Catch: Throwable -> L9
        String r8 = r0.destinationPathFor(r3, r11, r5);     // Catch: Throwable -> L9
        if (r8 == null) goto L8;
        Path r4 = ArchivePathResolver.INSTANCE.resolve(r21, r8, r5.getAllowAbsolutePaths());     // Catch: Throwable -> L9
        if (r3.isDirectory() == true) goto L13;
        Files.createDirectories(r4.getParent(), new FileAttribute[0]);     // Catch: Throwable -> L9
        if (r5.getDuplicatePolicy() != DuplicatePolicy.KEEP_LAST) goto L17;
        Files.deleteIfExists(r4);     // Catch: Throwable -> L9
    L17:
        long r15 = r1;
        InputStream r13 = r10.getInputStream(r3);     // Catch: Throwable -> L9
        OutputStream r2 = Files.newOutputStream(r4, new OpenOption[]{StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE});     // Catch: Throwable -> L34
        long r152 = r0.copyWithLimits(r13, r2, r3, r15, r5, r23, r8, r24);     // Catch: Throwable -> L28
        r3 = r13;
        CloseableKt.closeFinally(r2, null);     // Catch: Throwable -> L26
        CloseableKt.closeFinally(r3, null);     // Catch: Throwable -> L9
        r5 = r22;
        r1 = r0.checkedAddTotal(r15, r152, r5, r3.getName());     // Catch: Throwable -> L9
        r0.validateRuntimeCompressionRatio(r3, r152, r5);     // Catch: Throwable -> L9
        r23.entryCompleted(r8);     // Catch: Throwable -> L9
        goto L5
    L28:
        th = move-exception;
        r3 = r13;
        throw th;     // Catch: Throwable -> L31
    L31:
        th = move-exception;
        CloseableKt.closeFinally(r2, th);     // Catch: Throwable -> L26
        throw th;     // Catch: Throwable -> L26
    L26:
        th = th;
    L27:
        Throwable r14 = th;
        throw r14;     // Catch: Throwable -> L37
    L37:
        th = move-exception;
        CloseableKt.closeFinally(r3, r14);     // Catch: Throwable -> L9
        throw th;     // Catch: Throwable -> L9
    L34:
        th = th;
        r3 = r13;
        goto L27
    L13:
        Files.createDirectories(r4, new FileAttribute[0]);     // Catch: Throwable -> L9
        r23.entryCompleted(r8);     // Catch: Throwable -> L9
        goto L5
    L8:
        r23.entryCompleted(r3.getName());     // Catch: Throwable -> L9
        goto L5
    L40:
        Unit r02 = Unit.INSTANCE;     // Catch: Throwable -> L9
        CloseableKt.closeFinally(r10, null);
        return;
    L9:
        th = move-exception;
        throw th;     // Catch: Throwable -> L44
    L44:
        th = move-exception;
        CloseableKt.closeFinally(r10, th);
        throw th;
    }

    private final Void failValidation(String r7, String r8, String r9) {
        throw new ArchiveValidationException(new ValidationReport(ArchiveFormat.ZIP, null, CollectionsKt.listOf(new ArchiveIssue(ArchiveIssueSeverity.ERROR, r7, r8, r9)), 2, null));
    }

    private final boolean isDirectoryEmpty(Path r2) {
        Stream<Path> r1 = Files.list(r2);
        boolean r22 = !r1.findAny().isPresent();
        AutoCloseableKt.closeFinally(r1, null);
        return r22;
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        AutoCloseableKt.closeFinally(r1, th);
        throw th;
    }

    private final long knownUncompressedSize(List<ArchiveEntryInfo> r5) {
        Iterator r4 = SequencesKt.filter(SequencesKt.map(SequencesKt.filterNot(CollectionsKt.asSequence(r5), new ZipArchiveExtractor$$ExternalSyntheticLambda0()), new ZipArchiveExtractor$$ExternalSyntheticLambda1()), new ZipArchiveExtractor$$ExternalSyntheticLambda2()).iterator();
        long r0 = 0;
    L4:
        if (r4.hasNext() == false) goto L6;
        r0 = SaturatingMathKt.saturatingPlus(r0, ((Number) r4.next()).longValue());
        goto L4
    L6:
        return r0;
    }

    private final void prepareTarget(Path r4, ExtractionTargetPolicy r5) {
        if (Files.exists(r4, new LinkOption[0]) == false) goto L28;
        int r52 = WhenMappings.$EnumSwitchMapping$0[r5.ordinal()];
        if (r52 == 1) goto L20;
        if (r52 == 2) goto L14;
        if (r52 != 3) goto L11;
        FileTreeCleaner.INSTANCE.deleteRecursively(r4);
        goto L28
    L11:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return;
    L14:
        if (Files.isDirectory(r4, new LinkOption[0]) == false) goto L18;
        FileTreeCleaner.INSTANCE.deleteContents(r4);
        return;
    L18:
        throw new ExtractionTargetException(r4, "target exists but is not a directory");
    L20:
        if (Files.isDirectory(r4, new LinkOption[0]) == false) goto L27;
        if (isDirectoryEmpty(r4) == false) goto L25;
        return;
    L25:
        throw new ExtractionTargetException(r4, "target directory must be empty");
    L27:
        throw new ExtractionTargetException(r4, "target exists but is not a directory");
    L28:
        Files.createDirectories(r4, new FileAttribute[0]);
    }

    private final String renamedPath(String r9, Set<String> r10) {
        String r1 = StringsKt.substringBeforeLast(r9, '/', _UrlKt.FRAGMENT_ENCODE_SET);
        String r2 = StringsKt.substringAfterLast$default(r9, '/', null, 2, null);
        String r5 = StringsKt.substringBeforeLast(r2, '.', r2);
        String r0 = StringsKt.substringAfterLast(r2, '.', _UrlKt.FRAGMENT_ENCODE_SET);
        int r22 = 1;
    L4:
        if (r0.length() != 0) goto L6;
        String r6 = r5 + SignatureVisitor.SUPER + r22;
    L8:
        if (r1.length() == 0) goto L12;
        r6 = r1 + '/' + r6;
    L12:
        if (r10.add(r6) == true) goto L13;
        if (r22 == Integer.MAX_VALUE) goto L17;
        r22 = r22 + 1;
        goto L4
    L17:
        BaseRoomConnectionManager$$ExternalSyntheticBUOutline0.m("Unable to generate unique archive entry name for ", r9, 46);
        return null;
    L13:
        return r6;
    L6:
        r6 = r5 + SignatureVisitor.SUPER + r22 + '.' + r0;
        goto L8
    }

    private final void validateRuntimeCompressionRatio(ZipEntry r5, long r6, SecurityPolicy r8) {
        if (r5.getCompressedSize() >= 0) goto L5;
        return;
    L5:
        if (r6 > 0) goto L8;
        return;
    L8:
        if (r5.getCompressedSize() != 0) goto L10;
        double r62 = Double.POSITIVE_INFINITY;
    L12:
        if (r62 <= r8.getMaxCompressionRatio()) goto L18;
        failValidation("archive.entry.compression_ratio.too_high", "Archive entry compression ratio is " + r62 + ", limit is " + r8.getMaxCompressionRatio() + '.', r5.getName());
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return;
    L18:
        return;
    L10:
        r62 = r6 / r5.getCompressedSize();
        goto L12
    }

    public final ExtractedArchive extract(Path r10, Path r11, SecurityPolicy r12, boolean r13, List<ArchiveEntryInfo> r14, ArchiveExtractionOptions r15) {
        Path r2 = r11.toAbsolutePath().normalize();
        boolean r112 = Files.exists(r2, new LinkOption[0]);
        ExtractionProgress r3 = new ExtractionProgress(r15, r14.size(), knownUncompressedSize(r14));
        r3.checkCanceled();
        prepareTarget(r2, r15.getTargetPolicy());
        r3.emit(null);     // Catch: Throwable -> L5
        extractEntries(r10, r2, r12, r3, r15.getBufferSize());     // Catch: Throwable -> L5
        return new ExtractedArchive(r2, r13);
    L5:
        th = move-exception;
        if (r112 == false) goto L9;
        FileTreeCleaner.INSTANCE.deleteContents(r2);
        throw th;
    L9:
        FileTreeCleaner.INSTANCE.deleteRecursively(r2);
        throw th;
    }
}
