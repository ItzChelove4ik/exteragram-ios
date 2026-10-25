package org.simplifiles.internal.archive;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.simplifiles.archive.ArchiveEntryInfo;
import org.simplifiles.archive.ArchiveInspection;
import org.simplifiles.archive.ArchiveIssue;
import org.simplifiles.archive.ArchiveIssueSeverity;
import org.simplifiles.archive.ValidationReport;
import org.simplifiles.archive.security.DuplicatePolicy;
import org.simplifiles.archive.security.SecurityPolicy;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ,\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\b\u001a\u00020\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002JP\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00150\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u00172\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\u001e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00152\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00150\u0017H\u0002J\u001e\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u00152\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00150 H\u0002J.\u0010!\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\"2\u0006\u0010\b\u001a\u00020\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\u001e\u0010#\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J&\u0010$\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J&\u0010%\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002¨\u0006&"}, d2 = {"Lorg/simplifiles/internal/archive/ArchiveValidator;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "validate", "Lorg/simplifiles/archive/ValidationReport;", "inspection", "Lorg/simplifiles/archive/ArchiveInspection;", "policy", "Lorg/simplifiles/archive/security/SecurityPolicy;", "validateEntries", _UrlKt.FRAGMENT_ENCODE_SET, "entries", _UrlKt.FRAGMENT_ENCODE_SET, "Lorg/simplifiles/archive/ArchiveEntryInfo;", "issues", _UrlKt.FRAGMENT_ENCODE_SET, "Lorg/simplifiles/archive/ArchiveIssue;", "validatePathConflict", "entry", "normalizedPath", _UrlKt.FRAGMENT_ENCODE_SET, "filePaths", _UrlKt.FRAGMENT_ENCODE_SET, "fileParentPaths", "directoryPaths", "hasExistingPathAncestor", _UrlKt.FRAGMENT_ENCODE_SET, "path", "existingPaths", "addParentPaths", "parentPaths", _UrlKt.FRAGMENT_ENCODE_SET, "validatePath", "Lorg/simplifiles/internal/archive/ArchivePathAnalysis;", "validateCompressionMethod", "validateEntrySize", "validateCompressionRatio", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ArchiveValidator {
    public static final ArchiveValidator INSTANCE = null;

    static {
        INSTANCE = new ArchiveValidator();
    }

    private ArchiveValidator() {
    }

    private final void addParentPaths(String r7, Set<String> r8) {
        int r6 = StringsKt.indexOf$default(r7, '/', 0, false, 6, null);
    L3:
        if (r6 < 0) goto L5;
        r8.add(r7.substring(0, r6));
        r6 = StringsKt.indexOf$default(r7, '/', r6 + 1, false, 4, null);
        goto L3
    }

    private final boolean hasExistingPathAncestor(String r7, Set<String> r8) {
        int r6 = StringsKt.indexOf$default(r7, '/', 0, false, 6, null);
    L4:
        if (r6 < 0) goto L10;
        if (r8.contains(r7.substring(0, r6)) == true) goto L7;
        r6 = StringsKt.indexOf$default(r7, '/', r6 + 1, false, 4, null);
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    private final void validateCompressionMethod(ArchiveEntryInfo r4, List<ArchiveIssue> r5) {
        if (r4.getCompressionMethod() != 0) goto L5;
        return;
    L5:
        if (r4.getCompressionMethod() == 8) goto L9;
        r5.add(new ArchiveIssue(ArchiveIssueSeverity.ERROR, "archive.entry.method.unsupported", "Archive entry uses unsupported compression method: " + r4.getCompressionMethod() + '.', r4.getPath()));
        return;
    }

    private final void validateCompressionRatio(ArchiveEntryInfo r6, SecurityPolicy r7, List<ArchiveIssue> r8) {
        if (r6.getCompressedSize() >= 0) goto L5;
        return;
    L5:
        if (r6.getUncompressedSize() > 0) goto L8;
        return;
    L8:
        if (r6.getCompressedSize() != 0) goto L10;
        double r0 = Double.POSITIVE_INFINITY;
    L12:
        if (r0 <= r7.getMaxCompressionRatio()) goto L16;
        r8.add(new ArchiveIssue(ArchiveIssueSeverity.ERROR, "archive.entry.compression_ratio.too_high", "Archive entry compression ratio is " + r0 + ", limit is " + r7.getMaxCompressionRatio() + '.', r6.getPath()));
        return;
    L16:
        return;
    L10:
        r0 = r6.getUncompressedSize() / r6.getCompressedSize();
        goto L12
    }

    private final void validateEntries(List<ArchiveEntryInfo> r26, SecurityPolicy r27, List<ArchiveIssue> r28) {
        LinkedHashSet r9 = new LinkedHashSet();
        LinkedHashSet r5 = new LinkedHashSet();
        LinkedHashSet r6 = new LinkedHashSet();
        LinkedHashSet r7 = new LinkedHashSet();
        Iterator<ArchiveEntryInfo> r10 = r26.iterator();
        boolean r13 = false;
        long r14 = 0;
    L4:
        if (r10.hasNext() == false) goto L30;
        ArchiveEntryInfo r2 = r10.next();
        ArchivePathAnalysis r4 = ArchivePathAnalyzer.INSTANCE.analyze(r2.getPath());
        validatePath(r2, r4, r27, r28);
        validateCompressionMethod(r2, r28);
        String r11 = r4.getNormalizedPath();
        if (r11 != null) goto L8;
    L12:
        ArchiveEntryInfo r18 = r2;
        ArchivePathAnalysis r19 = r4;
        LinkedHashSet r20 = r5;
    L13:
        String r42 = r19.getNormalizedPath();
        if (r42 == null) goto L19;
        ArchiveValidator r22 = INSTANCE;
        ArchiveEntryInfo r3 = r18;
        r5 = r20;
        r22.validatePathConflict(r3, r42, r5, r6, r7, r28);
        if (r3.isDirectory() == false) goto L18;
        r7.add(r42);
    L21:
        if (r3.isDirectory() == true) goto L4;
        validateEntrySize(r3, r27, r28);
        validateCompressionRatio(r3, r27, r28);
        if (r3.getUncompressedSize() < 0) goto L4;
        if ((LongCompanionObject.MAX_VALUE - r14) < r3.getUncompressedSize()) goto L26;
        r14 = r14 + r3.getUncompressedSize();
        goto L4
    L26:
        if (r13 == true) goto L4;
        r28.add(new ArchiveIssue(ArchiveIssueSeverity.ERROR, "archive.total_size.overflow", "Total uncompressed size overflows Long.", null, 8, null));
        r13 = true;
        goto L4
    L18:
        r5.add(r42);
        r22.addParentPaths(r42, r6);
        goto L21
    L19:
        r3 = r18;
        r5 = r20;
        goto L21
    L8:
        if (r27.getDuplicatePolicy() != DuplicatePolicy.ERROR) goto L12;
        if (r9.add(r11) == true) goto L12;
        r18 = r2;
        r19 = r4;
        r20 = r5;
        r28.add(new ArchiveIssue(ArchiveIssueSeverity.ERROR, "archive.entry.duplicate", "Archive contains duplicate entry path: " + r11 + '.', r18.getPath()));
        goto L13
    L30:
        if (r14 <= r27.getMaxTotalUncompressedSize()) goto L45;
        r28.add(new ArchiveIssue(ArchiveIssueSeverity.ERROR, "archive.total_size.too_large", "Archive uncompressed size is " + r14 + " bytes, limit is " + r27.getMaxTotalUncompressedSize() + '.', null, 8, null));
        return;
    }

    private final void validateEntrySize(ArchiveEntryInfo r5, SecurityPolicy r6, List<ArchiveIssue> r7) {
        if (r5.getUncompressedSize() >= 0) goto L7;
        r7.add(new ArchiveIssue(ArchiveIssueSeverity.ERROR, "archive.entry.size.unknown", "Archive entry uncompressed size is unknown.", r5.getPath()));
        return;
    L7:
        if (r5.getUncompressedSize() <= r6.getMaxSingleFileSize()) goto L10;
        r7.add(new ArchiveIssue(ArchiveIssueSeverity.ERROR, "archive.entry.size.too_large", "Archive entry is " + r5.getUncompressedSize() + " bytes, limit is " + r6.getMaxSingleFileSize() + '.', r5.getPath()));
        return;
    }

    private final void validatePath(ArchiveEntryInfo r6, ArchivePathAnalysis r7, SecurityPolicy r8, List<ArchiveIssue> r9) {
        if (r7.isEmpty() == false) goto L6;
        r9.add(new ArchiveIssue(ArchiveIssueSeverity.ERROR, "archive.entry.path.empty", "Archive entry path is empty.", r6.getPath()));
    L6:
        if (r8.getAllowAbsolutePaths() == true) goto L11;
        if (r7.isAbsolute() == false) goto L11;
        r9.add(new ArchiveIssue(ArchiveIssueSeverity.ERROR, "archive.entry.path.absolute", "Archive entry path is absolute.", r6.getPath()));
    L11:
        if (r7.getContainsParentTraversal() == false) goto L14;
        r9.add(new ArchiveIssue(ArchiveIssueSeverity.ERROR, "archive.entry.path.traversal", "Archive entry path contains parent traversal.", r6.getPath()));
        return;
    }

    private final void validatePathConflict(ArchiveEntryInfo r2, String r3, Set<String> r4, Set<String> r5, Set<String> r6, List<ArchiveIssue> r7) {
        if (r2.isDirectory() == false) goto L11;
        if (r4.contains(r3) == false) goto L7;
    L18:
        r7.add(new ArchiveIssue(ArchiveIssueSeverity.ERROR, "archive.entry.path.conflict", "Archive entry path conflicts with another file or directory path: " + r3 + '.', r2.getPath()));
        return;
    L7:
        if (hasExistingPathAncestor(r3, r4) == true) goto L18;
        return;
    L11:
        if (r6.contains(r3) == true) goto L18;
        if (hasExistingPathAncestor(r3, r4) == true) goto L18;
        if (r5.contains(r3) == true) goto L18;
    }

    public final ValidationReport validate(ArchiveInspection r10, SecurityPolicy r11) {
        ArrayList r0 = new ArrayList();
        if (r10.getEntries().size() <= r11.getMaxEntries()) goto L5;
        r0.add(new ArchiveIssue(ArchiveIssueSeverity.ERROR, "archive.entries.too_many", "Archive contains " + r10.getEntries().size() + " entries, limit is " + r11.getMaxEntries() + '.', null, 8, null));
    L5:
        validateEntries(r10.getEntries(), r11, r0);
        return new ValidationReport(r10.getFormat(), r10.getEntries(), r0);
    }
}
