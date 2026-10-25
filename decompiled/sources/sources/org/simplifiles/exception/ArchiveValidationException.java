package org.simplifiles.exception;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.simplifiles.archive.ArchiveIssue;
import org.simplifiles.archive.ValidationReport;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/simplifiles/exception/ArchiveValidationException;", "Lorg/simplifiles/exception/SimpliFilesException;", "report", "Lorg/simplifiles/archive/ValidationReport;", "<init>", "(Lorg/simplifiles/archive/ValidationReport;)V", "getReport", "()Lorg/simplifiles/archive/ValidationReport;", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ArchiveValidationException extends SimpliFilesException {
    private final ValidationReport report;

    public ArchiveValidationException(ValidationReport r3) {
        ArchiveIssue r0 = (ArchiveIssue) CollectionsKt.firstOrNull(r3.getIssues());
        if (r0 == null) goto L6;
        String r02 = r0.getMessage();
        if (r02 == null) goto L6;
    L7:
        super("Archive failed validation: ".concat(r02));
        this.report = r3;
        return;
    L6:
        r02 = "unknown validation issue";
        goto L7
    }

    public final ValidationReport getReport() {
        return this.report;
    }
}
