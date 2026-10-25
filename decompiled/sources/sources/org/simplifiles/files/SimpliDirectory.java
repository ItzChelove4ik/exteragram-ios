package org.simplifiles.files;

import j$.nio.file.CopyOption;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.Path;
import j$.nio.file.Paths;
import j$.nio.file.StandardCopyOption;
import j$.nio.file.attribute.FileAttribute;
import java.io.File;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.simplifiles.archive.ArchiveSaveOptions;
import org.simplifiles.exception.FileOperationException;
import org.simplifiles.internal.archive.zip.ZipArchiveWriter;
import org.simplifiles.internal.files.SafePathResolver;
import org.simplifiles.internal.io.FileTreeCleaner;
import org.simplifiles.internal.io.FileTreeCopier;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\f\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0010\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0013\u0010\u0013\u001a\u00020\u000e*\u00020\u0012H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\u0015¢\u0006\u0004\b\u001a\u0010\u0017J\r\u0010\u001b\u001a\u00020\u0000¢\u0006\u0004\b\u001b\u0010\u0019J\u0015\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\n¢\u0006\u0004\b\u001c\u0010\u001dJ\u0015\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0003\u001a\u00020\n¢\u0006\u0004\b\u001f\u0010 J\u001d\u0010\"\u001a\u00020\u001e2\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020!¢\u0006\u0004\b\"\u0010#J\u001d\u0010\"\u001a\u00020\u001e2\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u0012¢\u0006\u0004\b\"\u0010%J\u001d\u0010\"\u001a\u00020\u001e2\u0006\u0010\t\u001a\u00020&2\u0006\u0010\u000f\u001a\u00020!¢\u0006\u0004\b\"\u0010'J\u001d\u0010\"\u001a\u00020\u001e2\u0006\u0010\t\u001a\u00020&2\u0006\u0010$\u001a\u00020\u0012¢\u0006\u0004\b\"\u0010(J\u001d\u0010)\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u0012¢\u0006\u0004\b)\u0010*J\u001d\u0010)\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b)\u0010+J\u001d\u0010)\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020&2\u0006\u0010$\u001a\u00020\u0012¢\u0006\u0004\b)\u0010,J\u001d\u0010-\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u0012¢\u0006\u0004\b-\u0010*J\u001d\u0010-\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b-\u0010+J\u001d\u0010-\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020&2\u0006\u0010$\u001a\u00020\u0012¢\u0006\u0004\b-\u0010,J\u001a\u0010/\u001a\u00020\u00152\b\u0010.\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b/\u00100J\u000f\u00102\u001a\u000201H\u0016¢\u0006\u0004\b2\u00103J\u000f\u00104\u001a\u00020\nH\u0016¢\u0006\u0004\b4\u00105R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u00106\u001a\u0004\b7\u00108R\u0011\u0010\u001f\u001a\u00020&8F¢\u0006\u0006\u001a\u0004\b9\u0010:R\u0011\u0010\u0016\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b;\u0010\u0017¨\u0006<"}, d2 = {"Lorg/simplifiles/files/SimpliDirectory;", _UrlKt.FRAGMENT_ENCODE_SET, "j$/nio/file/Path", "path", "<init>", "(Lj$/nio/file/Path;)V", _UrlKt.FRAGMENT_ENCODE_SET, "ensureExists", "()V", "target", _UrlKt.FRAGMENT_ENCODE_SET, "operation", "rejectSelfTarget", "(Lj$/nio/file/Path;Ljava/lang/String;)V", "Lorg/simplifiles/files/DirectoryTransferOptions;", "options", "validateBeforeReplacing", "(Lj$/nio/file/Path;Lorg/simplifiles/files/DirectoryTransferOptions;)V", "Lorg/simplifiles/files/OverwritePolicy;", "toDirectoryTransferOptions", "(Lorg/simplifiles/files/OverwritePolicy;)Lorg/simplifiles/files/DirectoryTransferOptions;", _UrlKt.FRAGMENT_ENCODE_SET, "exists", "()Z", "create", "()Lorg/simplifiles/files/SimpliDirectory;", "deleteRecursively", "clean", "resolveInside", "(Ljava/lang/String;)Lj$/nio/file/Path;", "Lorg/simplifiles/files/SimpliFile;", "file", "(Ljava/lang/String;)Lorg/simplifiles/files/SimpliFile;", "Lorg/simplifiles/archive/ArchiveSaveOptions;", "zipTo", "(Lj$/nio/file/Path;Lorg/simplifiles/archive/ArchiveSaveOptions;)Lorg/simplifiles/files/SimpliFile;", "overwritePolicy", "(Lj$/nio/file/Path;Lorg/simplifiles/files/OverwritePolicy;)Lorg/simplifiles/files/SimpliFile;", "Ljava/io/File;", "(Ljava/io/File;Lorg/simplifiles/archive/ArchiveSaveOptions;)Lorg/simplifiles/files/SimpliFile;", "(Ljava/io/File;Lorg/simplifiles/files/OverwritePolicy;)Lorg/simplifiles/files/SimpliFile;", "copyTo", "(Lj$/nio/file/Path;Lorg/simplifiles/files/OverwritePolicy;)Lorg/simplifiles/files/SimpliDirectory;", "(Lj$/nio/file/Path;Lorg/simplifiles/files/DirectoryTransferOptions;)Lorg/simplifiles/files/SimpliDirectory;", "(Ljava/io/File;Lorg/simplifiles/files/OverwritePolicy;)Lorg/simplifiles/files/SimpliDirectory;", "moveTo", "other", "equals", "(Ljava/lang/Object;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "toString", "()Ljava/lang/String;", "Lj$/nio/file/Path;", "getPath", "()Lj$/nio/file/Path;", "getFile", "()Ljava/io/File;", "getExists", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSimpliDirectory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SimpliDirectory.kt\norg/simplifiles/files/SimpliDirectory\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,325:1\n288#1,11:326\n288#1,11:337\n1#2:348\n*S KotlinDebug\n*F\n+ 1 SimpliDirectory.kt\norg/simplifiles/files/SimpliDirectory\n*L\n181#1:326,11\n228#1:337,11\n*E\n"})
/* loaded from: classes.dex */
public final class SimpliDirectory {
    private final Path path;

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = null;

        static {
            int[] r0 = new int[DirectoryOverwritePolicy.values().length];
            r0[DirectoryOverwritePolicy.ERROR.ordinal()] = 1;     // Catch: NoSuchFieldError -> L15
        L26:
            r0[DirectoryOverwritePolicy.SKIP.ordinal()] = 2;     // Catch: NoSuchFieldError -> L16
        L34:
            r0[DirectoryOverwritePolicy.REPLACE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L17
        L28:
            r0[DirectoryOverwritePolicy.MERGE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L18
        L9:
            $EnumSwitchMapping$0 = r0;
            int[] r02 = new int[OverwritePolicy.values().length];
            r02[OverwritePolicy.ERROR.ordinal()] = 1;     // Catch: NoSuchFieldError -> L19
        L30:
            r02[OverwritePolicy.REPLACE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L20
        L22:
            r02[OverwritePolicy.SKIP.ordinal()] = 3;     // Catch: NoSuchFieldError -> L21
        L13:
            $EnumSwitchMapping$1 = r02;
        }
    }

    public SimpliDirectory(Path r1) {
        this.path = r1;
    }

    private final void ensureExists() {
        if (getExists() == false) goto L6;
        return;
    L6:
        throw new FileOperationException("Directory does not exist: " + this.path);
    }

    private final void rejectSelfTarget(Path r3, String r4) {
        if (r3.toAbsolutePath().normalize().startsWith(this.path.toAbsolutePath().normalize()) == true) goto L6;
        return;
    L6:
        throw new FileOperationException("Directory cannot be " + r4 + " into itself: " + r3);
    }

    private final DirectoryTransferOptions toDirectoryTransferOptions(OverwritePolicy r10) {
        int r9 = WhenMappings.$EnumSwitchMapping$1[r10.ordinal()];
        if (r9 != 1) goto L5;
        DirectoryOverwritePolicy r92 = DirectoryOverwritePolicy.ERROR;
    L15:
        return new DirectoryTransferOptions(r92, 0, 0, null, 14, null);
    L5:
        if (r9 != 2) goto L7;
        r92 = DirectoryOverwritePolicy.REPLACE;
        goto L15
    L7:
        if (r9 != 3) goto L10;
        r92 = DirectoryOverwritePolicy.SKIP;
        goto L15
    L10:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    private final void validateBeforeReplacing(Path r2, DirectoryTransferOptions r3) {
        if (Files.exists(r2, new LinkOption[0]) == true) goto L5;
        return;
    L5:
        if (r3.getOverwritePolicy() != DirectoryOverwritePolicy.REPLACE) goto L9;
        FileTreeCopier.INSTANCE.validateDirectory(this.path, r3);
        return;
    }

    public final SimpliDirectory clean() {
        if (getExists() == true) goto L6;
        create();
        return this;
    L6:
        FileTreeCleaner.INSTANCE.deleteContents(this.path);
        return this;
    }

    public final SimpliDirectory copyTo(Path r3, DirectoryTransferOptions r4) {
        ensureExists();
        rejectSelfTarget(r3, "copied");
        validateBeforeReplacing(r3, r4);
        if (Files.exists(r3, new LinkOption[0]) == false) goto L17;
        DirectoryOverwritePolicy r0 = r4.getOverwritePolicy();
        int r02 = WhenMappings.$EnumSwitchMapping$0[r0.ordinal()];
        if (r02 != 1) goto L8;
        SimpliFile$$ExternalSyntheticBUOutline0.m("Target already exists: ", r3);
        return null;
    L8:
        if (r02 == 2) goto L20;
        if (r02 != 3) goto L12;
        FileTreeCleaner.INSTANCE.deleteRecursively(r3);
        goto L17
    L12:
        if (r02 == 4) goto L17;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L20:
        return new SimpliDirectory(r3);
    L17:
        FileTreeCopier.INSTANCE.copyDirectory(this.path, r3, r4);
        return new SimpliDirectory(r3);
    }

    public final SimpliDirectory copyTo(Path r1, OverwritePolicy r2) {
        return copyTo(r1, toDirectoryTransferOptions(r2));
    }

    public final SimpliDirectory copyTo(File r2, OverwritePolicy r3) {
        return copyTo(Paths.get(r2.getPath(), new String[0]), r3);
    }

    public final SimpliDirectory create() {
        Files.createDirectories(this.path, new FileAttribute[0]);
        return this;
    }

    public final boolean deleteRecursively() {
        if (getExists() == true) goto L6;
        return false;
    L6:
        FileTreeCleaner.INSTANCE.deleteRecursively(this.path);
        return true;
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L4;
        return true;
    L4:
        if ((r2 instanceof SimpliDirectory) == true) goto L6;
        return false;
    L6:
        if (Intrinsics.areEqual(this.path, ((SimpliDirectory) r2).path) == true) goto L13;
        return false;
    L13:
        return true;
    }

    public final boolean exists() {
        return getExists();
    }

    public final SimpliFile file(String r2) {
        return new SimpliFile(resolveInside(r2));
    }

    public final boolean getExists() {
        return Files.isDirectory(this.path, new LinkOption[0]);
    }

    public final File getFile() {
        return new File(this.path.toString());
    }

    public int hashCode() {
        return this.path.hashCode();
    }

    public final SimpliDirectory moveTo(Path r6, DirectoryTransferOptions r7) {
        ensureExists();
        rejectSelfTarget(r6, "moved");
        validateBeforeReplacing(r6, r7);
        if (Files.exists(r6, new LinkOption[0]) == false) goto L18;
        int r1 = WhenMappings.$EnumSwitchMapping$0[r7.getOverwritePolicy().ordinal()];
        if (r1 != 1) goto L8;
        SimpliFile$$ExternalSyntheticBUOutline0.m("Target already exists: ", r6);
        return null;
    L8:
        if (r1 == 2) goto L29;
        if (r1 != 3) goto L12;
        FileTreeCleaner.INSTANCE.deleteRecursively(r6);
        goto L18
    L12:
        if (r1 == 4) goto L18;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L29:
        return new SimpliDirectory(r6);
    L18:
        if (r7.getOverwritePolicy() == DirectoryOverwritePolicy.MERGE) goto L20;
    L23:
        FileTreeCopier.INSTANCE.validateDirectory(this.path, r7);
        Path r72 = r6.getParent();
        if (r72 == null) goto L26;
        Files.createDirectories(r72, new FileAttribute[0]);
    L26:
        Files.move(this.path, r6, new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
        return new SimpliDirectory(r6);
    L20:
        if (Files.exists(r6, new LinkOption[0]) == false) goto L23;
        FileTreeCopier.INSTANCE.copyDirectory(this.path, r6, r7);
        FileTreeCleaner.INSTANCE.deleteRecursively(this.path);
        return new SimpliDirectory(r6);
    }

    public final SimpliDirectory moveTo(Path r1, OverwritePolicy r2) {
        return moveTo(r1, toDirectoryTransferOptions(r2));
    }

    public final SimpliDirectory moveTo(File r2, OverwritePolicy r3) {
        return moveTo(Paths.get(r2.getPath(), new String[0]), r3);
    }

    public final Path resolveInside(String r2) {
        return SafePathResolver.INSTANCE.resolveInside(this.path, r2);
    }

    public String toString() {
        return "SimpliDirectory(path=" + this.path + ')';
    }

    public final SimpliFile zipTo(Path r2, ArchiveSaveOptions r3) {
        if (getExists() == false) goto L7;
        ZipArchiveWriter.INSTANCE.write(this.path, r2, r3);
        return new SimpliFile(r2);
    L7:
        throw new FileOperationException("Directory does not exist: " + this.path);
    }

    public final SimpliFile zipTo(Path r2, OverwritePolicy r3) {
        return zipTo(r2, ArchiveSaveOptions.Companion.builder().overwritePolicy(r3).build());
    }

    public final SimpliFile zipTo(File r2, ArchiveSaveOptions r3) {
        return zipTo(Paths.get(r2.getPath(), new String[0]), r3);
    }

    public final SimpliFile zipTo(File r2, OverwritePolicy r3) {
        return zipTo(Paths.get(r2.getPath(), new String[0]), r3);
    }
}
