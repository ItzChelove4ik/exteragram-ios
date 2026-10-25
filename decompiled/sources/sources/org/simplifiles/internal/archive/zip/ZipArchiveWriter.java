package org.simplifiles.internal.archive.zip;

import com.exteragram.messenger.plugins.PluginsConstants;
import j$.nio.file.CopyOption;
import j$.nio.file.FileVisitOption;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.OpenOption;
import j$.nio.file.Path;
import j$.nio.file.StandardCopyOption;
import j$.nio.file.StandardOpenOption;
import j$.nio.file.attribute.FileAttribute;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.SequencesKt;
import kotlin.streams.jdk8.StreamsKt;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.simplifiles.archive.ArchiveSaveOptions;
import org.simplifiles.exception.ArchiveOperationCanceledException;
import org.simplifiles.exception.ArchiveWriteException;
import org.simplifiles.files.OverwritePolicy;
import org.simplifiles.internal.SaturatingMathKt;
import org.simplifiles.internal.io.SymlinkDecision;
import org.simplifiles.internal.io.SymlinkSupport;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\t\bÀ\u0002\u0018\u00002\u00020\u0001:\u00015B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ=\u0010\u0015\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00042\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J=\u0010\u0018\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00042\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0018\u0010\u0016J'\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ%\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u001f\u0010 J%\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b!\u0010 J\u001f\u0010\"\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\"\u0010#J\u001f\u0010$\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b$\u0010%J7\u0010+\u001a\u00020\u00142\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020\b2\u0006\u0010*\u001a\u00020)2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0004\b+\u0010,J\u001d\u0010.\u001a\u00020-2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u000eH\u0002¢\u0006\u0004\b.\u0010/J\u0017\u00100\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b0\u00101J%\u00103\u001a\u00020\u00142\u0006\u00102\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b3\u00104¨\u00066"}, d2 = {"Lorg/simplifiles/internal/archive/zip/ZipArchiveWriter;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "j$/nio/file/Path", "path", _UrlKt.FRAGMENT_ENCODE_SET, "replacingExisting", "Ljava/io/OutputStream;", "newOutputStream", "(Lj$/nio/file/Path;Z)Ljava/io/OutputStream;", "Ljava/util/zip/ZipOutputStream;", "zip", "normalizedRoot", _UrlKt.FRAGMENT_ENCODE_SET, "directories", "Lorg/simplifiles/internal/archive/zip/ZipArchiveWriter$SaveProgress;", "progress", "Lorg/simplifiles/archive/ArchiveSaveOptions;", "options", _UrlKt.FRAGMENT_ENCODE_SET, "writeDirectories", "(Ljava/util/zip/ZipOutputStream;Lj$/nio/file/Path;Ljava/util/List;Lorg/simplifiles/internal/archive/zip/ZipArchiveWriter$SaveProgress;Lorg/simplifiles/archive/ArchiveSaveOptions;)V", "files", "writeFiles", _UrlKt.FRAGMENT_ENCODE_SET, "archivePath", "source", "Ljava/util/zip/ZipEntry;", "timestampedEntry", "(Ljava/lang/String;Lj$/nio/file/Path;Lorg/simplifiles/archive/ArchiveSaveOptions;)Ljava/util/zip/ZipEntry;", "listDirectories", "(Lj$/nio/file/Path;Lorg/simplifiles/archive/ArchiveSaveOptions;)Ljava/util/List;", "listFiles", "included", "(Lj$/nio/file/Path;Lorg/simplifiles/archive/ArchiveSaveOptions;)Z", "entryPath", "(Lj$/nio/file/Path;Lj$/nio/file/Path;)Ljava/lang/String;", "Ljava/io/InputStream;", PluginsConstants.Settings.TYPE_INPUT, "output", _UrlKt.FRAGMENT_ENCODE_SET, "bufferSize", "copy", "(Ljava/io/InputStream;Ljava/io/OutputStream;ILorg/simplifiles/internal/archive/zip/ZipArchiveWriter$SaveProgress;Ljava/lang/String;)V", _UrlKt.FRAGMENT_ENCODE_SET, "totalFileSize", "(Ljava/util/List;)J", "checkCanceled", "(Lorg/simplifiles/archive/ArchiveSaveOptions;)V", "root", "write", "(Lj$/nio/file/Path;Lj$/nio/file/Path;Lorg/simplifiles/archive/ArchiveSaveOptions;)V", "SaveProgress", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nZipArchiveWriter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ZipArchiveWriter.kt\norg/simplifiles/internal/archive/zip/ZipArchiveWriter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,553:1\n1#2:554\n1849#3,3:555\n1068#3:558\n1849#3,3:564\n614#4:559\n1342#4,2:560\n614#4:562\n614#4:563\n*S KotlinDebug\n*F\n+ 1 ZipArchiveWriter.kt\norg/simplifiles/internal/archive/zip/ZipArchiveWriter\n*L\n117#1:555,3\n212#1:558\n431#1:564,3\n225#1:559\n226#1:560,2\n376#1:562\n389#1:563\n*E\n"})
/* loaded from: classes5.dex */
public final class ZipArchiveWriter {
    public static final ZipArchiveWriter INSTANCE = null;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\n\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0011\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/simplifiles/internal/archive/zip/ZipArchiveWriter$SaveProgress;", _UrlKt.FRAGMENT_ENCODE_SET, "options", "Lorg/simplifiles/archive/ArchiveSaveOptions;", "totalEntries", _UrlKt.FRAGMENT_ENCODE_SET, "totalBytes", "<init>", "(Lorg/simplifiles/archive/ArchiveSaveOptions;JJ)V", "entriesProcessed", "bytesWritten", "checkCanceled", _UrlKt.FRAGMENT_ENCODE_SET, "bytes", "currentEntryPath", _UrlKt.FRAGMENT_ENCODE_SET, "entryCompleted", "emit", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class SaveProgress {
        private long bytesWritten;
        private long entriesProcessed;
        private final ArchiveSaveOptions options;
        private final long totalBytes;
        private final long totalEntries;

        public SaveProgress(ArchiveSaveOptions r1, long r2, long r4) {
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
            int[] r0 = new int[OverwritePolicy.values().length];
            r0[OverwritePolicy.ERROR.ordinal()] = 1;     // Catch: NoSuchFieldError -> L14
        L20:
            r0[OverwritePolicy.SKIP.ordinal()] = 2;     // Catch: NoSuchFieldError -> L15
        L26:
            r0[OverwritePolicy.REPLACE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L16
        L8:
            $EnumSwitchMapping$0 = r0;
            int[] r02 = new int[SymlinkDecision.values().length];
            r02[SymlinkDecision.INCLUDE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L17
        L22:
            r02[SymlinkDecision.SKIP.ordinal()] = 2;     // Catch: NoSuchFieldError -> L18
        L28:
            r02[SymlinkDecision.FAIL.ordinal()] = 3;     // Catch: NoSuchFieldError -> L19
        L12:
            $EnumSwitchMapping$1 = r02;
        }
    }

    public static /* synthetic */ boolean $r8$lambda$250kMCqAD9ns1kpOfO8kvI0lcEk(Path r0) {
        return listFiles$lambda$0$1(r0);
    }

    public static /* synthetic */ boolean $r8$lambda$BnsDqbdRbvJNOdYAMqyhqS2Yfk4(ArchiveSaveOptions r0, Path r1, Path r2) {
        return listFiles$lambda$0$2(r0, r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$kP6ahtrb59bb_vOUpYUDATVWDnA(Path r0, Path r1) {
        return listDirectories$lambda$0$1(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$oY7hhDm2fS8onxsdPYPc1CefN4U(ArchiveSaveOptions r0, Path r1, Path r2) {
        return listDirectories$lambda$0$2(r0, r1, r2);
    }

    /* renamed from: $r8$lambda$pUxXI_DRO-nN69fU6uV_-QlnrxY, reason: not valid java name */
    public static /* synthetic */ boolean m3861$r8$lambda$pUxXI_DROnN69fU6uV_QlnrxY(ArchiveSaveOptions r0, Path r1) {
        return listFiles$lambda$0$0(r0, r1);
    }

    public static /* synthetic */ boolean $r8$lambda$usFLzXFoiCtGBD1WgvghnosfRl4(ArchiveSaveOptions r0, Path r1) {
        return listDirectories$lambda$0$0(r0, r1);
    }

    static {
        INSTANCE = new ZipArchiveWriter();
    }

    private ZipArchiveWriter() {
    }

    private final void checkCanceled(ArchiveSaveOptions r1) {
        if (r1.getCancellationToken().isCancellationRequested() == true) goto L6;
        return;
    L6:
        throw new ArchiveOperationCanceledException();
    }

    private final void copy(InputStream r3, OutputStream r4, int r5, SaveProgress r6, String r7) {
        byte[] r2 = new byte[r5];
    L3:
        r6.checkCanceled();
        int r52 = r3.read(r2);
        if (r52 < 0) goto L5;
        r4.write(r2, 0, r52);
        r6.bytesWritten(r52, r7);
        goto L3
    }

    private final String entryPath(Path r7, Path r8) {
        return StringsKt.replace$default(r7.relativize(r8).toString(), '\\', '/', false, 4, null);
    }

    private final boolean included(Path r1, ArchiveSaveOptions r2) {
        SymlinkDecision r0 = SymlinkSupport.INSTANCE.decide(r1, r2.getSymlinkPolicy());
        int r02 = WhenMappings.$EnumSwitchMapping$1[r0.ordinal()];
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
        throw new ArchiveWriteException(r1, "source tree contains a symbolic link");
    }

    private final List<Path> listDirectories(Path r3, ArchiveSaveOptions r4) {
        Stream<Path> r2 = Files.walk(r3, new FileVisitOption[0]);
        List<Path> r32 = SequencesKt.toList(SequencesKt.sortedWith(SequencesKt.filter(SequencesKt.filter(SequencesKt.filter(StreamsKt.asSequence(r2), new ZipArchiveWriter$$ExternalSyntheticLambda0(r4)), new ZipArchiveWriter$$ExternalSyntheticLambda1(r3)), new ZipArchiveWriter$$ExternalSyntheticLambda2(r4, r3)), new ZipArchiveWriter$listDirectories$lambda$0$$inlined$sortedBy$1(r3)));     // Catch: Throwable -> L6
        AutoCloseableKt.closeFinally(r2, null);
        return r32;
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        AutoCloseableKt.closeFinally(r2, th);
        throw th;
    }

    private static final boolean listDirectories$lambda$0$0(ArchiveSaveOptions r1, Path r2) {
        return INSTANCE.included(r2, r1);
    }

    private static final boolean listDirectories$lambda$0$1(Path r1, Path r2) {
        if (Intrinsics.areEqual(r2, r1) == false) goto L5;
    L8:
        return false;
    L5:
        if (Files.isDirectory(r2, new LinkOption[0]) == false) goto L8;
        return true;
    }

    private static final boolean listDirectories$lambda$0$2(ArchiveSaveOptions r2, Path r3, Path r4) {
        return r2.getEntryFilter().include(INSTANCE.entryPath(r3, r4) + '/');
    }

    private final List<Path> listFiles(Path r3, ArchiveSaveOptions r4) {
        Stream<Path> r2 = Files.walk(r3, new FileVisitOption[0]);
        List<Path> r32 = SequencesKt.toList(SequencesKt.sortedWith(SequencesKt.filter(SequencesKt.filter(SequencesKt.filter(StreamsKt.asSequence(r2), new ZipArchiveWriter$$ExternalSyntheticLambda3(r4)), new ZipArchiveWriter$$ExternalSyntheticLambda4()), new ZipArchiveWriter$$ExternalSyntheticLambda5(r4, r3)), new ZipArchiveWriter$listFiles$lambda$0$$inlined$sortedBy$1(r3)));     // Catch: Throwable -> L6
        AutoCloseableKt.closeFinally(r2, null);
        return r32;
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        AutoCloseableKt.closeFinally(r2, th);
        throw th;
    }

    private static final boolean listFiles$lambda$0$0(ArchiveSaveOptions r1, Path r2) {
        return INSTANCE.included(r2, r1);
    }

    private static final boolean listFiles$lambda$0$1(Path r1) {
        return Files.isRegularFile(r1, new LinkOption[0]);
    }

    private static final boolean listFiles$lambda$0$2(ArchiveSaveOptions r1, Path r2, Path r3) {
        return r1.getEntryFilter().include(INSTANCE.entryPath(r2, r3));
    }

    private final OutputStream newOutputStream(Path r3, boolean r4) {
        if (r4 == false) goto L7;
        return Files.newOutputStream(r3, new OpenOption[]{StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE});
    L7:
        return Files.newOutputStream(r3, new OpenOption[]{StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE});
    }

    private final ZipEntry timestampedEntry(String r5, Path r6, ArchiveSaveOptions r7) {
        ZipEntry r4 = new ZipEntry(r5);
        if (r7.getEntryTimestamp() != (-1)) goto L5;
        long r52 = Files.getLastModifiedTime(r6, new LinkOption[0]).toMillis();
    L6:
        r4.setTime(r52);
        return r4;
    L5:
        r52 = r7.getEntryTimestamp();
        goto L6
    }

    private final long totalFileSize(List<? extends Path> r5) {
        Iterator<T> r4 = r5.iterator();
        long r0 = 0;
    L4:
        if (r4.hasNext() == false) goto L6;
        r0 = SaturatingMathKt.saturatingPlus(r0, Files.size((Path) r4.next()));
        goto L4
    L6:
        return r0;
    }

    private final void writeDirectories(ZipOutputStream r10, Path r11, List<? extends Path> r12, SaveProgress r13, ArchiveSaveOptions r14) {
        Iterator<? extends Path> r122 = r12.iterator();
    L4:
        if (r122.hasNext() == false) goto L6;
        Path r0 = r122.next();
        r13.checkCanceled();
        String r1 = StringsKt.replace$default(r11.relativize(r0).toString(), '\\', '/', false, 4, null) + '/';
        r10.putNextEntry(timestampedEntry(r1, r0, r14));
        r10.closeEntry();
        r13.entryCompleted(r1);
        goto L4
    }

    private final void writeFiles(ZipOutputStream r17, Path r18, List<? extends Path> r19, SaveProgress r20, ArchiveSaveOptions r21) {
        Iterator<? extends Path> r0 = r19.iterator();
    L4:
        if (r0.hasNext() == false) goto L14;
        Path r1 = r0.next();
        r20.checkCanceled();
        String r15 = StringsKt.replace$default(r18.relativize(r1).toString(), '\\', '/', false, 4, null);
        r17.putNextEntry(timestampedEntry(r15, r1, r21));
        InputStream r11 = Files.newInputStream(r1, new OpenOption[0]);
        INSTANCE.copy(r11, r17, r21.getBufferSize(), r20, r15);     // Catch: Throwable -> L8
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L8
        CloseableKt.closeFinally(r11, null);
        r17.closeEntry();
        r20.entryCompleted(r15);
    L8:
        th = move-exception;
        throw th;     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        CloseableKt.closeFinally(r11, th);
        throw th;
    }

    public final void write(Path r18, Path r19, ArchiveSaveOptions r20) {
        Path r7 = r18.toAbsolutePath().normalize();
        Path r8 = r19.toAbsolutePath().normalize();
        if (r8.startsWith(r7) == true) goto L52;
        checkCanceled(r20);
        boolean r2 = Files.exists(r8, new LinkOption[0]);
        if (r2 == false) goto L21;
        int r3 = WhenMappings.$EnumSwitchMapping$0[r20.getOverwritePolicy().ordinal()];
        if (r3 == 1) goto L20;
        if (r3 != 2) goto L11;
        return;
    L11:
        if (r3 == 3) goto L13;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return;
    L13:
        if (Files.isDirectory(r8, new LinkOption[0]) == false) goto L21;
        throw new ArchiveWriteException(r19, "output path is a directory");
    L20:
        throw new ArchiveWriteException(r19, "output file already exists");
    L21:
        Path r1 = r8.getParent();
        if (r1 == null) goto L24;
        Files.createDirectories(r1, new FileAttribute[0]);
    L24:
        List<Path> r11 = listDirectories(r7, r20);
        List<Path> r12 = listFiles(r7, r20);
        if (r2 == true) goto L27;
    L29:
        boolean r13 = false;
    L30:
        if (r13 == false) goto L32;
        Path r14 = r8.getParent();
        StringBuilder r22 = new StringBuilder();
        r22.append(r8.getFileName());
        r22.append('.');
        Path r142 = Files.createTempFile(r14, r22.toString(), ".tmp", new FileAttribute[0]);
    L33:
        SaveProgress r4 = new SaveProgress(r20, r11.size() + r12.size(), totalFileSize(r12));
        r4.emit(null);     // Catch: Throwable -> L40
        ZipOutputStream r15 = new ZipOutputStream(newOutputStream(r142, r13));     // Catch: Throwable -> L40
        r15.setLevel(r20.getCompressionLevel());     // Catch: Throwable -> L43
        ZipArchiveWriter r0 = INSTANCE;     // Catch: Throwable -> L43
        r0.writeDirectories(r15, r7, r11, r4, r20);     // Catch: Throwable -> L43
        r0.writeFiles(r15, r7, r12, r4, r20);     // Catch: Throwable -> L43
        Unit r02 = Unit.INSTANCE;     // Catch: Throwable -> L43
        CloseableKt.closeFinally(r15, null);     // Catch: Throwable -> L40
        if (Intrinsics.areEqual(r142, r8) == true) goto L58;
        Files.move(r142, r8, new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});     // Catch: Throwable -> L40
        return;
    L58:
        return;
    L43:
        th = move-exception;
        throw th;     // Catch: Throwable -> L46
    L46:
        th = move-exception;
        CloseableKt.closeFinally(r15, th);     // Catch: Throwable -> L40
        throw th;     // Catch: Throwable -> L40
    L40:
        th = move-exception;
        Files.deleteIfExists(r142);
        throw th;
    L32:
        r142 = r8;
        goto L33
    L27:
        if (r20.getOverwritePolicy() != OverwritePolicy.REPLACE) goto L29;
        r13 = true;
        goto L30
    L52:
        throw new ArchiveWriteException(r19, "output path must be outside source directory");
    }
}
