package org.simplifiles.exception;

import j$.nio.file.Path;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/simplifiles/exception/ArchiveWriteException;", "Lorg/simplifiles/exception/SimpliFilesException;", "j$/nio/file/Path", "path", _UrlKt.FRAGMENT_ENCODE_SET, "message", "<init>", "(Lj$/nio/file/Path;Ljava/lang/String;)V", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ArchiveWriteException extends SimpliFilesException {
    public ArchiveWriteException(Path r3, String r4) {
        super("Cannot write archive '" + r3 + "': " + r4);
    }
}
