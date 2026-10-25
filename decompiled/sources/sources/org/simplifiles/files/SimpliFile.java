package org.simplifiles.files;

import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.MediaStatus;
import j$.nio.file.CopyOption;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.OpenOption;
import j$.nio.file.Path;
import j$.nio.file.Paths;
import j$.nio.file.StandardCopyOption;
import j$.nio.file.StandardOpenOption;
import j$.nio.file.attribute.FileAttribute;
import j$.nio.file.attribute.FileTime;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.AtomicMoveNotSupportedException;
import java.util.Iterator;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import okhttp3.internal.url._UrlKt;
import org.simplifiles.exception.FileOperationException;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J'\u0010\f\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u0006¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0014\u0010\u0016J!\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0007¢\u0006\u0004\b\u001a\u0010\u001bJ5\u0010\u001f\u001a\u00020\u001d2\u0006\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\u0018\u001a\u00020\u00172\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001d0\u001cH\u0007¢\u0006\u0004\b\u001f\u0010 J!\u0010!\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0004\b!\u0010\"J!\u0010$\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\u00192\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0007¢\u0006\u0004\b$\u0010%J\u0015\u0010'\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u0013¢\u0006\u0004\b'\u0010(J!\u0010)\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0004\b)\u0010\"J\r\u0010*\u001a\u00020\u0000¢\u0006\u0004\b*\u0010+J\r\u0010,\u001a\u00020\u000e¢\u0006\u0004\b,\u0010\u0010J\u001d\u00100\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\u00022\u0006\u0010/\u001a\u00020.¢\u0006\u0004\b0\u00101J\u001d\u00100\u001a\u00020\u00002\u0006\u0010-\u001a\u0002022\u0006\u0010/\u001a\u00020.¢\u0006\u0004\b0\u00103J\u001d\u00104\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\u00022\u0006\u0010/\u001a\u00020.¢\u0006\u0004\b4\u00101J\u001d\u00104\u001a\u00020\u00002\u0006\u0010-\u001a\u0002022\u0006\u0010/\u001a\u00020.¢\u0006\u0004\b4\u00103J\u001a\u00106\u001a\u00020\u000e2\b\u00105\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b6\u00107J\u000f\u00109\u001a\u000208H\u0016¢\u0006\u0004\b9\u0010:J\u000f\u0010;\u001a\u00020\u0019H\u0016¢\u0006\u0004\b;\u0010<R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010=\u001a\u0004\b>\u0010?R\u0011\u0010B\u001a\u0002028F¢\u0006\u0006\u001a\u0004\b@\u0010AR\u0011\u0010\u000f\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\bC\u0010\u0010¨\u0006D"}, d2 = {"Lorg/simplifiles/files/SimpliFile;", _UrlKt.FRAGMENT_ENCODE_SET, "j$/nio/file/Path", "path", "<init>", "(Lj$/nio/file/Path;)V", "Ljava/io/InputStream;", PluginsConstants.Settings.TYPE_INPUT, "Ljava/io/OutputStream;", "output", _UrlKt.FRAGMENT_ENCODE_SET, "maxBytes", "copyFrom", "(Ljava/io/InputStream;Ljava/io/OutputStream;J)J", _UrlKt.FRAGMENT_ENCODE_SET, "exists", "()Z", "inputStream", "()Ljava/io/InputStream;", _UrlKt.FRAGMENT_ENCODE_SET, "readBytes", "()[B", "(J)[B", "Ljava/nio/charset/Charset;", "charset", _UrlKt.FRAGMENT_ENCODE_SET, "readText", "(JLjava/nio/charset/Charset;)Ljava/lang/String;", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "block", "forEachLine", "(JLjava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)V", "writeFrom", "(Ljava/io/InputStream;J)Lorg/simplifiles/files/SimpliFile;", "text", "writeTextAtomic", "(Ljava/lang/String;Ljava/nio/charset/Charset;)V", "bytes", "writeBytesAtomic", "([B)V", "writeFromAtomic", "touch", "()Lorg/simplifiles/files/SimpliFile;", "delete", "target", "Lorg/simplifiles/files/OverwritePolicy;", "overwritePolicy", "copyTo", "(Lj$/nio/file/Path;Lorg/simplifiles/files/OverwritePolicy;)Lorg/simplifiles/files/SimpliFile;", "Ljava/io/File;", "(Ljava/io/File;Lorg/simplifiles/files/OverwritePolicy;)Lorg/simplifiles/files/SimpliFile;", "moveTo", "other", "equals", "(Ljava/lang/Object;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "toString", "()Ljava/lang/String;", "Lj$/nio/file/Path;", "getPath", "()Lj$/nio/file/Path;", "getFile", "()Ljava/io/File;", "file", "getExists", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSimpliFile.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SimpliFile.kt\norg/simplifiles/files/SimpliFile\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ReadWrite.kt\nkotlin/io/TextStreamsKt\n+ 4 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,400:1\n1#2:401\n1#2:403\n66#3:402\n1342#4,2:404\n*S KotlinDebug\n*F\n+ 1 SimpliFile.kt\norg/simplifiles/files/SimpliFile\n*L\n98#1:403\n98#1:402\n99#1:404,2\n*E\n"})
/* loaded from: classes.dex */
public final class SimpliFile {
    private final Path path;

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[OverwritePolicy.values().length];
            r0[OverwritePolicy.ERROR.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L11:
            r0[OverwritePolicy.REPLACE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L15:
            r0[OverwritePolicy.SKIP.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L6:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public SimpliFile(Path r1) {
        this.path = r1;
    }

    private final long copyFrom(InputStream r10, OutputStream r11, long r12) {
        if (r12 < 0) goto L17;
        byte[] r2 = new byte[65536];
        long r3 = 0;
    L5:
        long r5 = r12 - r3;
        if (r5 == 0) goto L8;
        int r52 = r10.read(r2, 0, (int) Math.min(MediaStatus.COMMAND_FOLLOW, r5));
        if (r52 < 0) goto L15;
        r11.write(r2, 0, r52);
        r3 = r3 + r52;
    L15:
        return r3;
    L8:
        if (r10.read() < 0) goto L15;
        SimpliFile$$ExternalSyntheticBUOutline1.m("Input exceeds write limit of ", r12, this.path);
        return 0;
    L17:
        f$$ExternalSyntheticBUOutline1.m("maxBytes must not be negative.");
        return 0;
    }

    public static /* synthetic */ void forEachLine$default(SimpliFile r0, long r1, Charset r3, Function1 r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L5;
        r3 = Charsets.UTF_8;
    L5:
        r0.forEachLine(r1, r3, r4);
    }

    public static /* synthetic */ String readText$default(SimpliFile r0, long r1, Charset r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r3 = Charsets.UTF_8;
    L6:
        return r0.readText(r1, r3);
    }

    public static /* synthetic */ SimpliFile writeFrom$default(SimpliFile r0, InputStream r1, long r2, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = LongCompanionObject.MAX_VALUE;
    L6:
        return r0.writeFrom(r1, r2);
    }

    public static /* synthetic */ SimpliFile writeFromAtomic$default(SimpliFile r0, InputStream r1, long r2, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = LongCompanionObject.MAX_VALUE;
    L6:
        return r0.writeFromAtomic(r1, r2);
    }

    public static /* synthetic */ void writeTextAtomic$default(SimpliFile r0, String r1, Charset r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = Charsets.UTF_8;
    L5:
        r0.writeTextAtomic(r1, r2);
    }

    public final SimpliFile copyTo(Path r5, OverwritePolicy r6) {
        Path r0 = r5.getParent();
        if (r0 == null) goto L5;
        Files.createDirectories(r0, new FileAttribute[0]);
    L5:
        int r62 = WhenMappings.$EnumSwitchMapping$0[r6.ordinal()];
        if (r62 == 1) goto L19;
        if (r62 != 2) goto L10;
        Files.copy(this.path, r5, new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
    L22:
        return new SimpliFile(r5);
    L10:
        if (r62 == 3) goto L12;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L12:
        if (Files.exists(r5, new LinkOption[0]) == true) goto L14;
        Files.copy(this.path, r5, new CopyOption[0]);
    L14:
        Unit r4 = Unit.INSTANCE;
        goto L22
    L19:
        if (Files.exists(r5, new LinkOption[0]) == true) goto L23;
        Files.copy(this.path, r5, new CopyOption[0]);
        goto L22
    L23:
        SimpliFile$$ExternalSyntheticBUOutline0.m("Target already exists: ", r5);
        return null;
    }

    public final SimpliFile copyTo(File r2, OverwritePolicy r3) {
        return copyTo(Paths.get(r2.getPath(), new String[0]), r3);
    }

    public final boolean delete() {
        return Files.deleteIfExists(this.path);
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L4;
        return true;
    L4:
        if ((r2 instanceof SimpliFile) == true) goto L6;
        return false;
    L6:
        if (Intrinsics.areEqual(this.path, ((SimpliFile) r2).path) == true) goto L13;
        return false;
    L13:
        return true;
    }

    public final boolean exists() {
        return getExists();
    }

    @JvmOverloads
    public final void forEachLine(long r4, Charset r6, Function1<? super String, Unit> r7) {
        if (r4 < 0) goto L27;
        InputStream r0 = inputStream();
        BufferedReader r3 = new BufferedReader(new InputStreamReader(new BoundedInputStream(r0, r4, this.path), r6), 8192);     // Catch: Throwable -> L17
        Iterator<String> r42 = TextStreamsKt.lineSequence(r3).iterator();     // Catch: Throwable -> L10
    L8:
        if (r42.hasNext() == false) goto L12;
        r7.invoke(r42.next());     // Catch: Throwable -> L10
        goto L8
    L12:
        Unit r43 = Unit.INSTANCE;     // Catch: Throwable -> L10
    L14:
        CloseableKt.closeFinally(r3, null);     // Catch: Throwable -> L17
        CloseableKt.closeFinally(r0, null);
        return;
    L10:
        th = move-exception;
        throw th;     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        CloseableKt.closeFinally(r3, th);     // Catch: Throwable -> L17
        throw th;     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        throw th;     // Catch: Throwable -> L24
    L24:
        th = move-exception;
        CloseableKt.closeFinally(r0, th);
        throw th;
    L27:
        f$$ExternalSyntheticBUOutline1.m("maxBytes must not be negative.");
    }

    public final boolean getExists() {
        return Files.exists(this.path, new LinkOption[0]);
    }

    public final File getFile() {
        return new File(this.path.toString());
    }

    public int hashCode() {
        return this.path.hashCode();
    }

    public final InputStream inputStream() {
        return Files.newInputStream(this.path, new OpenOption[0]);
    }

    public final SimpliFile moveTo(Path r5, OverwritePolicy r6) {
        Path r0 = r5.getParent();
        if (r0 == null) goto L5;
        Files.createDirectories(r0, new FileAttribute[0]);
    L5:
        int r62 = WhenMappings.$EnumSwitchMapping$0[r6.ordinal()];
        if (r62 == 1) goto L19;
        if (r62 != 2) goto L10;
        Files.move(this.path, r5, new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
    L22:
        return new SimpliFile(r5);
    L10:
        if (r62 == 3) goto L12;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L12:
        if (Files.exists(r5, new LinkOption[0]) == true) goto L14;
        Files.move(this.path, r5, new CopyOption[0]);
    L14:
        Unit r4 = Unit.INSTANCE;
        goto L22
    L19:
        if (Files.exists(r5, new LinkOption[0]) == true) goto L23;
        Files.move(this.path, r5, new CopyOption[0]);
        goto L22
    L23:
        SimpliFile$$ExternalSyntheticBUOutline0.m("Target already exists: ", r5);
        return null;
    }

    public final SimpliFile moveTo(File r2, OverwritePolicy r3) {
        return moveTo(Paths.get(r2.getPath(), new String[0]), r3);
    }

    public final byte[] readBytes() {
        return Files.readAllBytes(this.path);
    }

    public final byte[] readBytes(long r3) {
        if (r3 >= 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("maxBytes must not be negative.");
        return null;
    L5:
        if (Files.size(this.path) <= r3) goto L7;
        SimpliFile$$ExternalSyntheticBUOutline1.m("File exceeds read limit of ", r3, this.path);
        return null;
    L7:
        return readBytes();
    }

    @JvmOverloads
    public final String readText(long r2, Charset r4) {
        return new String(readBytes(r2), r4);
    }

    public String toString() {
        return "SimpliFile(path=" + this.path + ')';
    }

    public final SimpliFile touch() {
        boolean r0 = Files.isDirectory(this.path, new LinkOption[0]);
        Path r2 = this.path;
        if (r0 == true) goto L9;
        Path r02 = r2.getParent();
        if (r02 == null) goto L7;
        Files.createDirectories(r02, new FileAttribute[0]);
    L7:
        Files.write(this.path, new byte[0], new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.APPEND});
        Files.setLastModifiedTime(this.path, FileTime.fromMillis(System.currentTimeMillis()));
        return this;
    L9:
        SimpliFile$$ExternalSyntheticBUOutline0.m("Path is a directory: ", r2);
        return null;
    }

    public final void writeBytesAtomic(byte[] r7) {
        Path r0 = this.path.getParent();
        if (r0 != null) goto L5;
        r0 = Paths.get(".", new String[0]).toAbsolutePath().normalize();
    L5:
        Files.createDirectories(r0, new FileAttribute[0]);
        Path r3 = this.path.getFileName();
        if (r3 == null) goto L20;
        String r32 = r3.toString();
        if (r32 == null) goto L20;
        Path r02 = Files.createTempFile(r0, "." + r32 + '.', ".tmp", new FileAttribute[0]);
        Files.write(r02, r7, new OpenOption[]{StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE});     // Catch: Throwable -> L13
        Files.move(r02, this.path, new CopyOption[]{StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING});     // Catch: Throwable -> L13 AtomicMoveNotSupportedException -> L15
        return;
    L15:
        Files.move(r02, this.path, new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});     // Catch: Throwable -> L13
        return;
    L13:
        th = move-exception;
        Files.deleteIfExists(r02);
        throw th;
    L20:
        throw new FileOperationException("File path must include a file name.");
    }

    @JvmOverloads
    public final SimpliFile writeFrom(InputStream r5, long r6) {
        Path r0 = this.path.getParent();
        if (r0 == null) goto L5;
        Files.createDirectories(r0, new FileAttribute[0]);
    L5:
        OutputStream r02 = Files.newOutputStream(this.path, new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE});
        copyFrom(r5, r02, r6);     // Catch: Throwable -> L9
        CloseableKt.closeFinally(r02, null);
        return this;
    L9:
        th = move-exception;
        throw th;     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        CloseableKt.closeFinally(r02, th);
        throw th;
    }

    @JvmOverloads
    public final SimpliFile writeFromAtomic(InputStream r7, long r8) {
        Path r0 = this.path.getParent();
        if (r0 != null) goto L5;
        r0 = Paths.get(".", new String[0]).toAbsolutePath().normalize();
    L5:
        Files.createDirectories(r0, new FileAttribute[0]);
        Path r3 = this.path.getFileName();
        if (r3 == null) goto L28;
        String r32 = r3.toString();
        if (r32 == null) goto L28;
        Path r02 = Files.createTempFile(r0, "." + r32 + '.', ".tmp", new FileAttribute[0]);
        OutputStream r33 = Files.newOutputStream(r02, new OpenOption[]{StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE});     // Catch: Throwable -> L16
        copyFrom(r7, r33, r8);     // Catch: Throwable -> L20
    L13:
        CloseableKt.closeFinally(r33, null);     // Catch: Throwable -> L16
        Files.move(r02, this.path, new CopyOption[]{StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING});     // Catch: Throwable -> L16 AtomicMoveNotSupportedException -> L18
        return this;
    L18:
        Files.move(r02, this.path, new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});     // Catch: Throwable -> L16
        return this;
    L20:
        th = move-exception;
        throw th;     // Catch: Throwable -> L22
    L22:
        th = move-exception;
        CloseableKt.closeFinally(r33, th);     // Catch: Throwable -> L16
        throw th;     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        Files.deleteIfExists(r02);
        throw th;
    L28:
        throw new FileOperationException("File path must include a file name.");
    }

    @JvmOverloads
    public final void writeTextAtomic(String r1, Charset r2) {
        writeBytesAtomic(r1.getBytes(r2));
    }
}
