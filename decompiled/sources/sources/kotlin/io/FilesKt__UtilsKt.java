package kotlin.io;

import androidx.core.os.BundleKt$$ExternalSyntheticBUOutline0;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\u001a,\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0001H\u0087\u0080\u0004\u001a,\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0001H\u0087\u0080\u0004\u001a\u0016\u0010\u000e\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u0016\u0010\u0010\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u0016\u0010\u0011\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u001d\u0010\u0013\u001a\u0004\u0018\u00010\u0003*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0082\u0080\u0004¢\u0006\u0002\b\u0014\u001a*\u0010\u0015\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00012\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0087\u0080\b\u001a<\u0010\u001b\u001a\u00020\u0018*\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00012\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u001a\b\u0002\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u001dH\u0087\u0080\b\u001a\u000e\u0010 \u001a\u00020\u0018*\u00020\u0001H\u0087\u0080\b\u001a\u0016\u0010!\u001a\u00020\u0018*\u00020\u00012\u0006\u0010\"\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u0016\u0010!\u001a\u00020\u0018*\u00020\u00012\u0006\u0010\"\u001a\u00020\u0003H\u0086\u0080\u0004\u001a\u0016\u0010#\u001a\u00020\u0018*\u00020\u00012\u0006\u0010\"\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u0016\u0010#\u001a\u00020\u0018*\u00020\u00012\u0006\u0010\"\u001a\u00020\u0003H\u0086\u0080\u0004\u001a\u000e\u0010$\u001a\u00020\u0001*\u00020\u0001H\u0086\u0080\u0004\u001a\u0013\u0010$\u001a\u00020%*\u00020%H\u0082\u0080\u0004¢\u0006\u0002\b&\u001a\u001f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00010'*\b\u0012\u0004\u0012\u00020\u00010'H\u0082\u0080\u0004¢\u0006\u0002\b&\u001a\u0016\u0010(\u001a\u00020\u0001*\u00020\u00012\u0006\u0010)\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u0016\u0010(\u001a\u00020\u0001*\u00020\u00012\u0006\u0010)\u001a\u00020\u0003H\u0086\u0080\u0004\u001a\u0016\u0010*\u001a\u00020\u0001*\u00020\u00012\u0006\u0010)\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u0016\u0010*\u001a\u00020\u0001*\u00020\u00012\u0006\u0010)\u001a\u00020\u0003H\u0086\u0080\u0004\"\u0019\u0010\u0007\u001a\u00020\u0003*\u00020\u00018FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\b\u0010\t\"\u0019\u0010\n\u001a\u00020\u0003*\u00020\u00018FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u000b\u0010\t\"\u0019\u0010\f\u001a\u00020\u0003*\u00020\u00018FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\r\u0010\t¨\u0006+"}, d2 = {"createTempDir", "Ljava/io/File;", "prefix", _UrlKt.FRAGMENT_ENCODE_SET, "suffix", "directory", "createTempFile", "extension", "getExtension", "(Ljava/io/File;)Ljava/lang/String;", "invariantSeparatorsPath", "getInvariantSeparatorsPath", "nameWithoutExtension", "getNameWithoutExtension", "toRelativeString", "base", "relativeTo", "relativeToOrSelf", "relativeToOrNull", "toRelativeStringOrNull", "toRelativeStringOrNull$FilesKt__UtilsKt", "copyTo", "target", "overwrite", _UrlKt.FRAGMENT_ENCODE_SET, "bufferSize", _UrlKt.FRAGMENT_ENCODE_SET, "copyRecursively", "onError", "Lkotlin/Function2;", "Ljava/io/IOException;", "Lkotlin/io/OnErrorAction;", "deleteRecursively", "startsWith", "other", "endsWith", "normalize", "Lkotlin/io/FilePathComponents;", "normalize$FilesKt__UtilsKt", _UrlKt.FRAGMENT_ENCODE_SET, "resolve", "relative", "resolveSibling", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/io/FilesKt")
@SourceDebugExtension({"SMAP\nUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Utils.kt\nkotlin/io/FilesKt__UtilsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,478:1\n1#2:479\n1313#3,3:480\n*S KotlinDebug\n*F\n+ 1 Utils.kt\nkotlin/io/FilesKt__UtilsKt\n*L\n352#1:480,3\n*E\n"})
/* loaded from: classes.dex */
class FilesKt__UtilsKt extends FilesKt__FileTreeWalkKt {
    public static /* synthetic */ OnErrorAction $r8$lambda$PN4W4m_bMsOvVN4PKSOdmJGtU3Q(File r0, IOException r1) {
        return copyRecursively$lambda$0$FilesKt__UtilsKt(r0, r1);
    }

    /* renamed from: $r8$lambda$lnI-AFWpcqImJk9omrkfsWa50BI, reason: not valid java name */
    public static /* synthetic */ Unit m3241$r8$lambda$lnIAFWpcqImJk9omrkfsWa50BI(Function2 r0, File r1, IOException r2) {
        return copyRecursively$lambda$1$FilesKt__UtilsKt(r0, r1, r2);
    }

    public FilesKt__UtilsKt() {
    }

    @IgnorableReturnValue
    public static final boolean copyRecursively(File r12, File r13, boolean r14, Function2<? super File, ? super IOException, ? extends OnErrorAction> r15) {
        if (r12.exists() == true) goto L46;
        if (r15.invoke(r12, new NoSuchFileException(r12, null, "The source file doesn't exist.", 2, null)) == OnErrorAction.TERMINATE) goto L7;
        return true;
    L7:
        return false;
    L46:
        Iterator<File> r122 = FilesKt__FileTreeWalkKt.walkTopDown(r12).onFail(new FilesKt__UtilsKt$$ExternalSyntheticLambda0(r15)).iterator();     // Catch: TerminateException -> L45
    L10:
        if (r122.hasNext() == false) goto L44;
        File r5 = r122.next();     // Catch: TerminateException -> L45
        if (r5.exists() == false) goto L15;
        File r6 = new File(r13, toRelativeString(r5, r12));     // Catch: TerminateException -> L45
        if (r6.exists() == false) goto L36;
        if (r5.isDirectory() == true) goto L22;
    L23:
        if (r14 == false) goto L33;
        if (r6.isDirectory() == false) goto L31;
        if (deleteRecursively(r6) == true) goto L36;
    L31:
        if (r6.delete() == true) goto L36;
    L33:
        if (r15.invoke(r6, new FileAlreadyExistsException(r5, r6, "The destination file already exists.")) != OnErrorAction.TERMINATE) goto L10;
        return false;
    L22:
        if (r6.isDirectory() == false) goto L23;
    L36:
        if (r5.isDirectory() == true) goto L37;
        boolean r7 = r14;
        if (copyTo$default(r5, r6, r7, 0, 4, null).length() != r5.length()) goto L41;
    L43:
        r14 = r7;
        goto L10
    L41:
        if (r15.invoke(r5, new IOException("Source file wasn't copied completely, length of destination file differs.")) != OnErrorAction.TERMINATE) goto L43;
        return false;
    L37:
        r6.mkdirs();     // Catch: TerminateException -> L45
        goto L10
    L15:
        if (r15.invoke(r5, new NoSuchFileException(r5, null, "The source file doesn't exist.", 2, null)) != OnErrorAction.TERMINATE) goto L10;
        return false;
    L44:
        return true;
    L45:
        return false;
    }

    public static /* synthetic */ boolean copyRecursively$default(File r0, File r1, boolean r2, Function2 r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = false;
    L6:
        if ((r4 & 4) == 0) goto L9;
        r3 = new FilesKt__UtilsKt$$ExternalSyntheticLambda1();
    L9:
        return copyRecursively(r0, r1, r2, r3);
    }

    private static final OnErrorAction copyRecursively$lambda$0$FilesKt__UtilsKt(File r0, IOException r1) {
        throw r1;
    }

    private static final Unit copyRecursively$lambda$1$FilesKt__UtilsKt(Function2 r0, File r1, IOException r2) {
        if (r0.invoke(r1, r2) == OnErrorAction.TERMINATE) goto L7;
        return Unit.INSTANCE;
    L7:
        throw new TerminateException(r1);
    }

    @IgnorableReturnValue
    public static final File copyTo(File r6, File r7, boolean r8, int r9) {
        if (r6.exists() == false) goto L45;
        if (r7.exists() == false) goto L15;
        if (r8 == false) goto L13;
        if (r7.delete() == true) goto L15;
        throw new FileAlreadyExistsException(r6, r7, "Tried to overwrite the destination, but failed to delete it.");
    L13:
        throw new FileAlreadyExistsException(r6, r7, "The destination file already exists.");
    L15:
        if (r6.isDirectory() == true) goto L17;
        File r82 = r7.getParentFile();
        if (r82 == null) goto L24;
        r82.mkdirs();
    L24:
        FileInputStream r83 = new FileInputStream(r6);
        FileOutputStream r62 = new FileOutputStream(r7);     // Catch: Throwable -> L31
        ByteStreamsKt.copyTo(r83, r62, r9);     // Catch: Throwable -> L33
        Unit r92 = Unit.INSTANCE;     // Catch: Throwable -> L33
    L28:
        CloseableKt.closeFinally(r62, null);     // Catch: Throwable -> L31
        CloseableKt.closeFinally(r83, null);
        return r7;
    L33:
        th = move-exception;
        throw th;     // Catch: Throwable -> L36
    L36:
        th = move-exception;
        CloseableKt.closeFinally(r62, th);     // Catch: Throwable -> L31
        throw th;     // Catch: Throwable -> L31
    L31:
        th = move-exception;
        throw th;     // Catch: Throwable -> L41
    L41:
        th = move-exception;
        CloseableKt.closeFinally(r83, th);
        throw th;
    L17:
        if (r7.mkdirs() == false) goto L20;
        return r7;
    L20:
        throw new FileSystemException(r6, r7, "Failed to create target directory.");
    L45:
        throw new NoSuchFileException(r6, null, "The source file doesn't exist.", 2, null);
    }

    public static /* synthetic */ File copyTo$default(File r0, File r1, boolean r2, int r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = false;
    L6:
        if ((r4 & 4) == 0) goto L9;
        r3 = 8192;
    L9:
        return copyTo(r0, r1, r2, r3);
    }

    @Deprecated(message = "Avoid creating temporary directories in the default temp location with this function due to too wide permissions on the newly created directory. Use kotlin.io.path.createTempDirectory instead.")
    @DeprecatedSinceKotlin(errorSince = MVEL.VERSION, warningSince = "1.4")
    public static final File createTempDir(String r1, String r2, File r3) {
        File r12 = File.createTempFile(r1, r2, r3);
        r12.delete();
        if (r12.mkdir() == false) goto L6;
        return r12;
    L6:
        throw new IOException("Unable to create temporary directory " + r12 + '.');
    }

    public static /* synthetic */ File createTempDir$default(String r1, String r2, File r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = "tmp";
    L6:
        if ((r4 & 2) == 0) goto L9;
        r2 = null;
    L9:
        if ((r4 & 4) == 0) goto L12;
        r3 = null;
    L12:
        return createTempDir(r1, r2, r3);
    }

    @Deprecated(message = "Avoid creating temporary files in the default temp location with this function due to too wide permissions on the newly created file. Use kotlin.io.path.createTempFile instead or resort to java.io.File.createTempFile.")
    @DeprecatedSinceKotlin(errorSince = MVEL.VERSION, warningSince = "1.4")
    public static final File createTempFile(String r0, String r1, File r2) {
        return File.createTempFile(r0, r1, r2);
    }

    public static /* synthetic */ File createTempFile$default(String r1, String r2, File r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = "tmp";
    L6:
        if ((r4 & 2) == 0) goto L9;
        r2 = null;
    L9:
        if ((r4 & 4) == 0) goto L12;
        r3 = null;
    L12:
        return createTempFile(r1, r2, r3);
    }

    @IgnorableReturnValue
    public static boolean deleteRecursively(File r4) {
        Iterator<File> r42 = FilesKt__FileTreeWalkKt.walkBottomUp(r4).iterator();
    L3:
        boolean r1 = true;
    L5:
        if (r42.hasNext() == false) goto L13;
        File r2 = r42.next();
        if (r2.delete() == true) goto L10;
        if (r2.exists() == false) goto L10;
    L12:
        r1 = false;
    L10:
        if (r1 == false) goto L12;
    L13:
        return r1;
    }

    public static final boolean endsWith(File r3, File r4) {
        FilePathComponents r0 = FilesKt__FilePathComponentsKt.toComponents(r3);
        FilePathComponents r1 = FilesKt__FilePathComponentsKt.toComponents(r4);
        if (r1.isRooted() == true) goto L5;
        int r32 = r0.getSize() - r1.getSize();
        if (r32 >= 0) goto L11;
        return false;
    L11:
        return r0.getSegments().subList(r32, r0.getSize()).equals(r1.getSegments());
    L5:
        return Intrinsics.areEqual(r3, r4);
    }

    public static final boolean endsWith(File r1, String r2) {
        return endsWith(r1, new File(r2));
    }

    public static String getExtension(File r2) {
        return StringsKt.substringAfterLast(r2.getName(), '.', _UrlKt.FRAGMENT_ENCODE_SET);
    }

    public static String getInvariantSeparatorsPath(File r6) {
        char r1 = File.separatorChar;
        if (r1 == '/') goto L7;
        return StringsKt.replace$default(r6.getPath(), r1, '/', false, 4, null);
    L7:
        return r6.getPath();
    }

    public static final String getNameWithoutExtension(File r3) {
        return StringsKt.substringBeforeLast$default(r3.getName(), ".", null, 2, null);
    }

    public static final File normalize(File r10) {
        FilePathComponents r102 = FilesKt__FilePathComponentsKt.toComponents(r10);
        return resolve(r102.getRoot(), CollectionsKt.joinToString$default(normalize$FilesKt__UtilsKt(r102.getSegments()), File.separator, null, null, 0, null, null, 62, null));
    }

    private static final List<File> normalize$FilesKt__UtilsKt(List<? extends File> r4) {
        ArrayList r0 = new ArrayList(r4.size());
        Iterator<? extends File> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L17;
        File r1 = r42.next();
        String r2 = r1.getName();
        if (Intrinsics.areEqual(r2, ".") == true) goto L7;
        if (Intrinsics.areEqual(r2, "..") == true) goto L11;
        r0.add(r1);
        goto L4
    L11:
        if (r0.isEmpty() == true) goto L15;
        if (Intrinsics.areEqual(((File) CollectionsKt.last(r0)).getName(), "..") == true) goto L15;
        Comparable r12 = (Comparable) r0.remove(r0.size() - 1);
    L15:
        r0.add(r1);
        goto L4
    L7:
        Unit r13 = Unit.INSTANCE;
        goto L4
    L17:
        return r0;
    }

    private static final FilePathComponents normalize$FilesKt__UtilsKt(FilePathComponents r2) {
        return new FilePathComponents(r2.getRoot(), normalize$FilesKt__UtilsKt(r2.getSegments()));
    }

    public static File relativeTo(File r1, File r2) {
        return new File(toRelativeString(r1, r2));
    }

    public static final File relativeToOrNull(File r0, File r1) {
        String r02 = toRelativeStringOrNull$FilesKt__UtilsKt(r0, r1);
        if (r02 != null) goto L5;
        return null;
    L5:
        return new File(r02);
    }

    public static final File relativeToOrSelf(File r0, File r1) {
        String r12 = toRelativeStringOrNull$FilesKt__UtilsKt(r0, r1);
        if (r12 != null) goto L5;
        return r0;
    L5:
        return new File(r12);
    }

    public static final File resolve(File r4, File r5) {
        if (FilesKt__FilePathComponentsKt.isRooted(r5) == false) goto L5;
        return r5;
    L5:
        String r42 = r4.toString();
        if (r42.length() == 0) goto L11;
        char r0 = File.separatorChar;
        if (StringsKt.endsWith$default(r42, r0, false, 2, null) == true) goto L11;
        return new File(r42 + r0 + r5);
    L11:
        return new File(r42 + r5);
    }

    public static final File resolve(File r1, String r2) {
        return resolve(r1, new File(r2));
    }

    public static final File resolveSibling(File r2, File r3) {
        FilePathComponents r22 = FilesKt__FilePathComponentsKt.toComponents(r2);
        if (r22.getSize() != 0) goto L5;
        File r0 = new File("..");
    L7:
        return resolve(resolve(r22.getRoot(), r0), r3);
    L5:
        r0 = r22.subPath(0, r22.getSize() - 1);
        goto L7
    }

    public static final File resolveSibling(File r1, String r2) {
        return resolveSibling(r1, new File(r2));
    }

    public static final boolean startsWith(File r3, File r4) {
        FilePathComponents r32 = FilesKt__FilePathComponentsKt.toComponents(r3);
        FilePathComponents r42 = FilesKt__FilePathComponentsKt.toComponents(r4);
        if (Intrinsics.areEqual(r32.getRoot(), r42.getRoot()) == true) goto L6;
        return false;
    L6:
        if (r32.getSize() >= r42.getSize()) goto L9;
        return false;
    L9:
        return r32.getSegments().subList(0, r42.getSize()).equals(r42.getSegments());
    }

    public static final boolean startsWith(File r1, String r2) {
        return startsWith(r1, new File(r2));
    }

    public static final String toRelativeString(File r3, File r4) {
        String r0 = toRelativeStringOrNull$FilesKt__UtilsKt(r3, r4);
        if (r0 == null) goto L5;
        return r0;
    L5:
        BundleKt$$ExternalSyntheticBUOutline0.m("this and base files have different roots: ", r3, " and ", r4, 46);
        return null;
    }

    private static final String toRelativeStringOrNull$FilesKt__UtilsKt(File r17, File r18) {
        FilePathComponents r0 = normalize$FilesKt__UtilsKt(FilesKt__FilePathComponentsKt.toComponents(r17));
        FilePathComponents r1 = normalize$FilesKt__UtilsKt(FilesKt__FilePathComponentsKt.toComponents(r18));
        if (Intrinsics.areEqual(r0.getRoot(), r1.getRoot()) == true) goto L5;
        return null;
    L5:
        int r2 = r1.getSize();
        int r4 = r0.getSize();
        int r5 = Math.min(r4, r2);
        int r6 = 0;
    L6:
        if (r6 >= r5) goto L10;
        if (Intrinsics.areEqual(r0.getSegments().get(r6), r1.getSegments().get(r6)) == false) goto L10;
        r6 = r6 + 1;
    L10:
        StringBuilder r8 = new StringBuilder();
        int r52 = r2 - 1;
        if (r6 <= r52) goto L13;
    L20:
        if (r6 >= r4) goto L25;
        if (r6 >= r2) goto L23;
        r8.append(File.separatorChar);
    L23:
        CollectionsKt.joinTo$default(CollectionsKt.drop(r0.getSegments(), r6), r8, File.separator, null, null, 0, null, null, 124, null);
    L25:
        return r8.toString();
    L13:
        if (Intrinsics.areEqual(r1.getSegments().get(r52).getName(), "..") == true) goto L14;
        r8.append("..");
        if (r52 == r6) goto L18;
        r8.append(File.separatorChar);
    L18:
        if (r52 == r6) goto L20;
        r52 = r52 - 1;
        goto L13
    L14:
        return null;
    }
}
