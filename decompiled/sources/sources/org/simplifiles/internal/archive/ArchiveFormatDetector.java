package org.simplifiles.internal.archive;

import com.exteragram.messenger.plugins.PluginsConstants;
import j$.nio.file.Files;
import j$.nio.file.OpenOption;
import j$.nio.file.Path;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.simplifiles.archive.ArchiveFormat;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\n\u001a\u00020\tH\u0000¢\u0006\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lorg/simplifiles/internal/archive/ArchiveFormatDetector;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "j$/nio/file/Path", "path", "Lorg/simplifiles/archive/ArchiveFormat;", "detect", "(Lj$/nio/file/Path;)Lorg/simplifiles/archive/ArchiveFormat;", "Ljava/io/InputStream;", PluginsConstants.Settings.TYPE_INPUT, _UrlKt.FRAGMENT_ENCODE_SET, "readSignature$simplifiles", "(Ljava/io/InputStream;)[B", "readSignature", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nArchiveFormatDetector.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ArchiveFormatDetector.kt\norg/simplifiles/internal/archive/ArchiveFormatDetector\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,55:1\n1#2:56\n*E\n"})
/* loaded from: classes5.dex */
public final class ArchiveFormatDetector {
    public static final ArchiveFormatDetector INSTANCE = null;

    static {
        INSTANCE = new ArchiveFormatDetector();
    }

    private ArchiveFormatDetector() {
    }

    public final ArchiveFormat detect(Path r6) {
        InputStream r62 = Files.newInputStream(r6, new OpenOption[0]);
        byte[] r5 = readSignature$simplifiles(r62);     // Catch: Throwable -> L25
        CloseableKt.closeFinally(r62, null);
        if (r5 != null) goto L7;
        return null;
    L7:
        int r63 = r5[0] & UByte.MAX_VALUE;
        int r0 = r5[1] & UByte.MAX_VALUE;
        int r2 = r5[2] & UByte.MAX_VALUE;
        int r52 = r5[3] & UByte.MAX_VALUE;
        if (r63 == 80) goto L10;
    L24:
        return null;
    L10:
        if (r0 != 75) goto L24;
        if (r2 != 3) goto L15;
        if (r52 != 4) goto L15;
    L23:
        return ArchiveFormat.ZIP;
    L15:
        if (r2 != 5) goto L19;
        if (r52 == 6) goto L23;
    L19:
        if (r2 != 7) goto L24;
        if (r52 != 8) goto L24;
    L25:
        th = move-exception;
        throw th;     // Catch: Throwable -> L27
    L27:
        th = move-exception;
        CloseableKt.closeFinally(r62, th);
        throw th;
    }

    public final byte[] readSignature$simplifiles(InputStream r4) {
        byte[] r0 = new byte[4];
        int r1 = 0;
    L3:
        if (r1 >= 4) goto L9;
        int r2 = r4.read(r0, r1, 4 - r1);
        if (r2 < 0) goto L6;
        r1 = r1 + r2;
        goto L3
    L6:
        return null;
    L9:
        return r0;
    }
}
