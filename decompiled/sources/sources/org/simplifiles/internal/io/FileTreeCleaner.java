package org.simplifiles.internal.io;

import j$.nio.file.FileVisitOption;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.SequencesKt;
import kotlin.streams.jdk8.StreamsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Lorg/simplifiles/internal/io/FileTreeCleaner;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "j$/nio/file/Path", "root", _UrlKt.FRAGMENT_ENCODE_SET, "deleteContents", "(Lj$/nio/file/Path;)V", "deleteRecursively", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFileTreeCleaner.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileTreeCleaner.kt\norg/simplifiles/internal/io/FileTreeCleaner\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,34:1\n628#2:35\n1342#2,2:36\n628#2:38\n1342#2,2:39\n*S KotlinDebug\n*F\n+ 1 FileTreeCleaner.kt\norg/simplifiles/internal/io/FileTreeCleaner\n*L\n17#1:35\n18#1:36,2\n29#1:38\n30#1:39,2\n*E\n"})
/* loaded from: classes5.dex */
public final class FileTreeCleaner {
    public static final FileTreeCleaner INSTANCE = null;

    /* renamed from: $r8$lambda$aWbxpewK81k7ka-LIC4ibzdkUnc, reason: not valid java name */
    public static /* synthetic */ boolean m3862$r8$lambda$aWbxpewK81k7kaLIC4ibzdkUnc(Path r0, Path r1) {
        return deleteContents$lambda$0$0(r0, r1);
    }

    static {
        INSTANCE = new FileTreeCleaner();
    }

    private FileTreeCleaner() {
    }

    private static final boolean deleteContents$lambda$0$0(Path r0, Path r1) {
        return !Intrinsics.areEqual(r1, r0);
    }

    public final void deleteContents(Path r3) {
        if (Files.exists(r3, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0)) == true) goto L5;
        return;
    L5:
        Stream<Path> r2 = Files.walk(r3, new FileVisitOption[0]);
        Iterator r32 = SequencesKt.sortedWith(SequencesKt.filter(StreamsKt.asSequence(r2), new FileTreeCleaner$$ExternalSyntheticLambda0(r3)), new FileTreeCleaner$deleteContents$lambda$0$$inlined$sortedByDescending$1()).iterator();     // Catch: Throwable -> L10
    L8:
        if (r32.hasNext() == false) goto L12;
        Files.deleteIfExists((Path) r32.next());     // Catch: Throwable -> L10
        goto L8
    L12:
        Unit r33 = Unit.INSTANCE;     // Catch: Throwable -> L10
        AutoCloseableKt.closeFinally(r2, null);
        return;
    L10:
        th = move-exception;
        throw th;     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        AutoCloseableKt.closeFinally(r2, th);
        throw th;
    }

    public final void deleteRecursively(Path r2) {
        if (Files.exists(r2, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0)) == true) goto L5;
        return;
    L5:
        Stream<Path> r1 = Files.walk(r2, new FileVisitOption[0]);
        Iterator r22 = SequencesKt.sortedWith(StreamsKt.asSequence(r1), new FileTreeCleaner$deleteRecursively$lambda$0$$inlined$sortedByDescending$1()).iterator();     // Catch: Throwable -> L10
    L8:
        if (r22.hasNext() == false) goto L12;
        Files.deleteIfExists((Path) r22.next());     // Catch: Throwable -> L10
        goto L8
    L12:
        Unit r23 = Unit.INSTANCE;     // Catch: Throwable -> L10
        AutoCloseableKt.closeFinally(r1, null);
        return;
    L10:
        th = move-exception;
        throw th;     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        AutoCloseableKt.closeFinally(r1, th);
        throw th;
    }
}
