package org.simplifiles;

import j$.nio.file.Path;
import j$.nio.file.Paths;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;
import org.simplifiles.archive.ArchiveSource;
import org.simplifiles.files.SimpliDirectory;
import org.simplifiles.files.SimpliFile;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\u0007\u0010\u000bJ\u0017\u0010\n\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\n\u0010\rJ\u0017\u0010\n\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\n\u0010\u000fJ\u0017\u0010\n\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\n\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\u0012\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/simplifiles/SimpliFiles;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "j$/nio/file/Path", "path", "Lorg/simplifiles/archive/ArchiveSource;", "archive", "(Lj$/nio/file/Path;)Lorg/simplifiles/archive/ArchiveSource;", "Ljava/io/File;", "file", "(Ljava/io/File;)Lorg/simplifiles/archive/ArchiveSource;", "Lorg/simplifiles/files/SimpliFile;", "(Lj$/nio/file/Path;)Lorg/simplifiles/files/SimpliFile;", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/String;)Lorg/simplifiles/files/SimpliFile;", "(Ljava/io/File;)Lorg/simplifiles/files/SimpliFile;", "Lorg/simplifiles/files/SimpliDirectory;", "directory", "(Lj$/nio/file/Path;)Lorg/simplifiles/files/SimpliDirectory;", "(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SimpliFiles {
    public static final SimpliFiles INSTANCE = null;

    static {
        INSTANCE = new SimpliFiles();
    }

    private SimpliFiles() {
    }

    @JvmStatic
    public static final ArchiveSource archive(Path r3) {
        return new ArchiveSource(r3, null, 2, null);
    }

    @JvmStatic
    public static final ArchiveSource archive(File r1) {
        return archive(Paths.get(r1.getPath(), new String[0]));
    }

    @JvmStatic
    public static final SimpliDirectory directory(Path r1) {
        return new SimpliDirectory(r1);
    }

    @JvmStatic
    public static final SimpliDirectory directory(File r1) {
        return directory(Paths.get(r1.getPath(), new String[0]));
    }

    @JvmStatic
    public static final SimpliFile file(Path r1) {
        return new SimpliFile(r1);
    }

    @JvmStatic
    public static final SimpliFile file(File r1) {
        return file(Paths.get(r1.getPath(), new String[0]));
    }

    @JvmStatic
    public static final SimpliFile file(String r1) {
        return file(Paths.get(r1, new String[0]));
    }
}
