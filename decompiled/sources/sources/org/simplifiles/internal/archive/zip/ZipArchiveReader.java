package org.simplifiles.internal.archive.zip;

import j$.nio.file.Path;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.sequences.SequencesKt;
import okhttp3.internal.url._UrlKt;
import org.simplifiles.archive.ArchiveEntryInfo;
import org.simplifiles.archive.ArchiveFormat;
import org.simplifiles.archive.ArchiveInspection;
import org.simplifiles.exception.CorruptedArchiveException;
import org.simplifiles.internal.archive.ArchivePathAnalysis;
import org.simplifiles.internal.archive.ArchivePathAnalyzer;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/simplifiles/internal/archive/zip/ZipArchiveReader;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "j$/nio/file/Path", "path", "Lorg/simplifiles/archive/ArchiveInspection;", "inspect", "(Lj$/nio/file/Path;)Lorg/simplifiles/archive/ArchiveInspection;", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ZipArchiveReader {
    public static final ZipArchiveReader INSTANCE = null;

    public static /* synthetic */ ArchiveEntryInfo $r8$lambda$1KaTiVwr14CpY5YJEnpM4wWu87U(ZipEntry r0) {
        return inspect$lambda$0$0(r0);
    }

    static {
        INSTANCE = new ZipArchiveReader();
    }

    private ZipArchiveReader() {
    }

    private static final ArchiveEntryInfo inspect$lambda$0$0(ZipEntry r10) {
        ArchivePathAnalysis r0 = ArchivePathAnalyzer.INSTANCE.analyze(r10.getName());
        return new ArchiveEntryInfo(r10.getName(), r0.getNormalizedPath(), r10.isDirectory(), r10.getCompressedSize(), r10.getSize(), r10.getMethod());
    }

    public final ArchiveInspection inspect(Path r3) {
        ZipFile r2 = new ZipFile(r3.toFile());     // Catch: IOException -> L8
        List r0 = SequencesKt.toList(SequencesKt.map(SequencesKt.asSequence(CollectionsKt.iterator(r2.entries())), new ZipArchiveReader$$ExternalSyntheticLambda0()));     // Catch: Throwable -> L10
    L5:
        CloseableKt.closeFinally(r2, null);     // Catch: IOException -> L8
        return new ArchiveInspection(ArchiveFormat.ZIP, r0);
    L10:
        th = move-exception;
        throw th;     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        CloseableKt.closeFinally(r2, th);     // Catch: IOException -> L8
        throw th;     // Catch: IOException -> L8
    L8:
        e = move-exception;
        throw new CorruptedArchiveException(r3, e);
    }
}
