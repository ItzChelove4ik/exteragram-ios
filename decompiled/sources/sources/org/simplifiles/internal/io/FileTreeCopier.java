package org.simplifiles.internal.io;

import j$.nio.file.CopyOption;
import j$.nio.file.FileVisitOption;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.Path;
import j$.nio.file.StandardCopyOption;
import j$.nio.file.attribute.FileAttribute;
import java.util.Iterator;
import java.util.stream.Stream;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.SequencesKt;
import kotlin.streams.jdk8.StreamsKt;
import okhttp3.internal.url._UrlKt;
import org.simplifiles.exception.FileOperationException;
import org.simplifiles.files.DirectoryTransferOptions;
import org.simplifiles.files.SimpliFile$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\r\u0010\u000eJ'\u0010\u0010\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/simplifiles/internal/io/FileTreeCopier;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "j$/nio/file/Path", "path", "Lorg/simplifiles/files/DirectoryTransferOptions;", "options", _UrlKt.FRAGMENT_ENCODE_SET, "included", "(Lj$/nio/file/Path;Lorg/simplifiles/files/DirectoryTransferOptions;)Z", "source", _UrlKt.FRAGMENT_ENCODE_SET, "validateDirectory", "(Lj$/nio/file/Path;Lorg/simplifiles/files/DirectoryTransferOptions;)V", "target", "copyDirectory", "(Lj$/nio/file/Path;Lj$/nio/file/Path;Lorg/simplifiles/files/DirectoryTransferOptions;)V", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFileTreeCopier.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileTreeCopier.kt\norg/simplifiles/internal/io/FileTreeCopier\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,77:1\n1342#2,2:78\n614#2:80\n1342#2,2:81\n*S KotlinDebug\n*F\n+ 1 FileTreeCopier.kt\norg/simplifiles/internal/io/FileTreeCopier\n*L\n22#1:78,2\n46#1:80\n47#1:81,2\n*E\n"})
/* loaded from: classes5.dex */
public final class FileTreeCopier {
    public static final FileTreeCopier INSTANCE = null;

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[SymlinkDecision.values().length];
            r0[SymlinkDecision.INCLUDE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L11:
            r0[SymlinkDecision.SKIP.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L15:
            r0[SymlinkDecision.FAIL.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L6:
            $EnumSwitchMapping$0 = r0;
        }
    }

    /* renamed from: $r8$lambda$js9gTUjWA9s28TU846zKjOMG-cI, reason: not valid java name */
    public static /* synthetic */ boolean m3863$r8$lambda$js9gTUjWA9s28TU846zKjOMGcI(Path r0) {
        return validateDirectory$lambda$0$1(r0);
    }

    public static /* synthetic */ boolean $r8$lambda$mUdELbRj367i4FBJiko7U8j6JPQ(DirectoryTransferOptions r0, Path r1) {
        return validateDirectory$lambda$0$0(r0, r1);
    }

    /* renamed from: $r8$lambda$v-gR1FcIqk7lNtPkCVl7ezhx3g8, reason: not valid java name */
    public static /* synthetic */ boolean m3864$r8$lambda$vgR1FcIqk7lNtPkCVl7ezhx3g8(DirectoryTransferOptions r0, Path r1) {
        return copyDirectory$lambda$0$0(r0, r1);
    }

    static {
        INSTANCE = new FileTreeCopier();
    }

    private FileTreeCopier() {
    }

    private static final boolean copyDirectory$lambda$0$0(DirectoryTransferOptions r1, Path r2) {
        return INSTANCE.included(r2, r1);
    }

    private final boolean included(Path r1, DirectoryTransferOptions r2) {
        SymlinkDecision r0 = SymlinkSupport.INSTANCE.decide(r1, r2.getSymlinkPolicy());
        int r02 = WhenMappings.$EnumSwitchMapping$0[r0.ordinal()];
        if (r02 != 1) goto L5;
        return true;
    L5:
        if (r02 != 2) goto L7;
        return false;
    L7:
        if (r02 == 3) goto L11;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return false;
    L11:
        SimpliFile$$ExternalSyntheticBUOutline0.m("Directory contains a symbolic link: ", r1);
        return false;
    }

    private static final boolean validateDirectory$lambda$0$0(DirectoryTransferOptions r1, Path r2) {
        return INSTANCE.included(r2, r1);
    }

    private static final boolean validateDirectory$lambda$0$1(Path r1) {
        return Files.isRegularFile(r1, new LinkOption[0]);
    }

    public final void copyDirectory(Path r6, Path r7, DirectoryTransferOptions r8) {
        validateDirectory(r6, r8);
        Stream<Path> r0 = Files.walk(r6, new FileVisitOption[0]);
        Iterator r82 = SequencesKt.sortedWith(SequencesKt.filter(StreamsKt.asSequence(r0), new FileTreeCopier$$ExternalSyntheticLambda2(r8)), new FileTreeCopier$copyDirectory$lambda$0$$inlined$sortedBy$1()).iterator();     // Catch: Throwable -> L13
    L5:
        if (r82.hasNext() == false) goto L20;
        Path r1 = (Path) r82.next();     // Catch: Throwable -> L13
        Path r2 = r7.resolve(r6.relativize(r1));     // Catch: Throwable -> L13
        if (Files.isDirectory(r1, new LinkOption[0]) == true) goto L9;
        Files.createDirectories(r2.getParent(), new FileAttribute[0]);     // Catch: Throwable -> L13
        if (Files.isDirectory(r2, new LinkOption[0]) == false) goto L19;
        FileTreeCleaner.INSTANCE.deleteRecursively(r2);     // Catch: Throwable -> L13
    L19:
        Files.copy(r1, r2, new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});     // Catch: Throwable -> L13
        goto L5
    L9:
        if (Files.exists(r2, new LinkOption[0]) == true) goto L11;
    L15:
        Files.createDirectories(r2, new FileAttribute[0]);     // Catch: Throwable -> L13
        goto L5
    L11:
        if (Files.isDirectory(r2, new LinkOption[0]) == true) goto L15;
        Files.deleteIfExists(r2);     // Catch: Throwable -> L13
        goto L15
    L20:
        Unit r5 = Unit.INSTANCE;     // Catch: Throwable -> L13
        AutoCloseableKt.closeFinally(r0, null);
        return;
    L13:
        th = move-exception;
        throw th;     // Catch: Throwable -> L24
    L24:
        th = move-exception;
        AutoCloseableKt.closeFinally(r0, th);
        throw th;
    }

    public final void validateDirectory(Path r9, DirectoryTransferOptions r10) {
        Stream<Path> r8 = Files.walk(r9, new FileVisitOption[0]);
        Iterator r0 = SequencesKt.filter(SequencesKt.filter(StreamsKt.asSequence(r8), new FileTreeCopier$$ExternalSyntheticLambda0(r10)), new FileTreeCopier$$ExternalSyntheticLambda1()).iterator();     // Catch: Throwable -> L14
        long r1 = 0;
        long r3 = 0;
    L5:
        if (r0.hasNext() == false) goto L18;
        Path r5 = (Path) r0.next();     // Catch: Throwable -> L14
        r1 = r1 + 1;     // Catch: Throwable -> L14
        if (r1 > r10.getMaxFiles()) goto L17;
        r3 = r3 + Files.size(r5);     // Catch: Throwable -> L14
        if (r3 <= r10.getMaxBytes()) goto L5;
        throw new FileOperationException("Directory exceeds copy limit of " + r10.getMaxBytes() + " bytes: " + r9);     // Catch: Throwable -> L14
    L17:
        throw new FileOperationException("Directory exceeds copy limit of " + r10.getMaxFiles() + " files: " + r9);     // Catch: Throwable -> L14
    L18:
        Unit r92 = Unit.INSTANCE;     // Catch: Throwable -> L14
        AutoCloseableKt.closeFinally(r8, null);
        return;
    L14:
        th = move-exception;
        throw th;     // Catch: Throwable -> L22
    L22:
        th = move-exception;
        AutoCloseableKt.closeFinally(r8, th);
        throw th;
    }
}
