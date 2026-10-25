package kotlin.io.path;

import j$.nio.file.CopyOption;
import j$.nio.file.FileVisitResult;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.Path;
import j$.nio.file.SecureDirectoryStream;
import j$.nio.file.StandardCopyOption;
import j$.nio.file.attribute.BasicFileAttributeView;
import j$.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystemLoopException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.IgnorableReturnValue;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.SpreadBuilder;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\b\u0012\u001a~\u0010\u000e\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002Q\b\u0002\u0010\n\u001aK\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0001\u0012\u0017\u0012\u00150\u0006j\u0002`\u0007¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u00022\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u000e\u0010\u000f\u001a»\u0001\u0010\u000e\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002Q\b\u0002\u0010\n\u001aK\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0001\u0012\u0017\u0012\u00150\u0006j\u0002`\u0007¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u00022\u0006\u0010\f\u001a\u00020\u000b2C\b\u0002\u0010\u0013\u001a=\u0012\u0004\u0012\u00020\u0010\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0001\u0012\u0004\u0012\u00020\u00110\u0002¢\u0006\u0002\b\u0012H\u0007¢\u0006\u0004\b\u000e\u0010\u0014\u001a\u0013\u0010\u0018\u001a\u00020\u0015*\u00020\u0011H\u0003¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0013\u0010\u0018\u001a\u00020\u0015*\u00020\tH\u0003¢\u0006\u0004\b\u0016\u0010\u0019\u001a\u0013\u0010\u001b\u001a\u00020\u001a*\u00020\u0000H\u0007¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u001d\u0010 \u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u001d*\u00020\u0000H\u0002¢\u0006\u0004\b\u001e\u0010\u001f\u001a&\u0010'\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020!2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001a0#H\u0082\b¢\u0006\u0004\b%\u0010&\u001a&\u0010+\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010(2\f\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000#H\u0082\b¢\u0006\u0004\b)\u0010*\u001a3\u00100\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u00000,2\u0006\u0010\u0004\u001a\u00020\u00002\b\u0010-\u001a\u0004\u0018\u00010\u00002\u0006\u0010\"\u001a\u00020!H\u0002¢\u0006\u0004\b.\u0010/\u001a)\u00103\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u00000,2\u0006\u0010\u0004\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020!H\u0002¢\u0006\u0004\b1\u00102\u001a5\u0010:\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020\u00000,2\u0006\u00104\u001a\u00020\u00002\u0012\u00107\u001a\n\u0012\u0006\b\u0001\u0012\u00020605\"\u000206H\u0002¢\u0006\u0004\b8\u00109\u001a)\u0010>\u001a\u00020\u001a2\u0006\u0010;\u001a\u00020\u00002\b\u0010-\u001a\u0004\u0018\u00010\u00002\u0006\u0010\"\u001a\u00020!H\u0002¢\u0006\u0004\b<\u0010=\u001a\u001f\u0010B\u001a\u00020\u001a2\u0006\u0010?\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020!H\u0002¢\u0006\u0004\b@\u0010A\u001a\u0013\u0010C\u001a\u00020\u001a*\u00020\u0000H\u0000¢\u0006\u0004\bC\u0010\u001c\u001a\u001b\u0010F\u001a\u00020\u001a*\u00020\u00002\u0006\u0010-\u001a\u00020\u0000H\u0002¢\u0006\u0004\bD\u0010E¨\u0006G"}, d2 = {"j$/nio/file/Path", "target", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "source", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "Lkotlin/io/path/OnErrorResult;", "onError", _UrlKt.FRAGMENT_ENCODE_SET, "followLinks", "overwrite", "copyToRecursively", "(Lj$/nio/file/Path;Lj$/nio/file/Path;Lkotlin/jvm/functions/Function3;ZZ)Lj$/nio/file/Path;", "Lkotlin/io/path/CopyActionContext;", "Lkotlin/io/path/CopyActionResult;", "Lkotlin/ExtensionFunctionType;", "copyAction", "(Lj$/nio/file/Path;Lj$/nio/file/Path;Lkotlin/jvm/functions/Function3;ZLkotlin/jvm/functions/Function3;)Lj$/nio/file/Path;", "j$/nio/file/FileVisitResult", "toFileVisitResult$PathsKt__PathRecursiveFunctionsKt", "(Lkotlin/io/path/CopyActionResult;)Lj$/nio/file/FileVisitResult;", "toFileVisitResult", "(Lkotlin/io/path/OnErrorResult;)Lj$/nio/file/FileVisitResult;", _UrlKt.FRAGMENT_ENCODE_SET, "deleteRecursively", "(Lj$/nio/file/Path;)V", _UrlKt.FRAGMENT_ENCODE_SET, "deleteRecursivelyImpl$PathsKt__PathRecursiveFunctionsKt", "(Lj$/nio/file/Path;)Ljava/util/List;", "deleteRecursivelyImpl", "Lkotlin/io/path/ExceptionsCollector;", "collector", "Lkotlin/Function0;", "function", "collectIfThrows$PathsKt__PathRecursiveFunctionsKt", "(Lkotlin/io/path/ExceptionsCollector;Lkotlin/jvm/functions/Function0;)V", "collectIfThrows", "R", "tryIgnoreNoSuchFileException$PathsKt__PathRecursiveFunctionsKt", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "tryIgnoreNoSuchFileException", "j$/nio/file/SecureDirectoryStream", "parent", "handleEntry$PathsKt__PathRecursiveFunctionsKt", "(Lj$/nio/file/SecureDirectoryStream;Lj$/nio/file/Path;Lj$/nio/file/Path;Lkotlin/io/path/ExceptionsCollector;)V", "handleEntry", "enterDirectory$PathsKt__PathRecursiveFunctionsKt", "(Lj$/nio/file/SecureDirectoryStream;Lj$/nio/file/Path;Lkotlin/io/path/ExceptionsCollector;)V", "enterDirectory", "entryName", _UrlKt.FRAGMENT_ENCODE_SET, "j$/nio/file/LinkOption", "options", "isDirectory$PathsKt__PathRecursiveFunctionsKt", "(Lj$/nio/file/SecureDirectoryStream;Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Z", "isDirectory", "entry", "insecureHandleEntry$PathsKt__PathRecursiveFunctionsKt", "(Lj$/nio/file/Path;Lj$/nio/file/Path;Lkotlin/io/path/ExceptionsCollector;)V", "insecureHandleEntry", "path", "insecureEnterDirectory$PathsKt__PathRecursiveFunctionsKt", "(Lj$/nio/file/Path;Lkotlin/io/path/ExceptionsCollector;)V", "insecureEnterDirectory", "checkFileName", "checkNotSameAs$PathsKt__PathRecursiveFunctionsKt", "(Lj$/nio/file/Path;Lj$/nio/file/Path;)V", "checkNotSameAs", "kotlin-stdlib-jdk7"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/io/path/PathsKt")
@SourceDebugExtension({"SMAP\nPathRecursiveFunctions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathRecursiveFunctions.kt\nkotlin/io/path/PathsKt__PathRecursiveFunctionsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,536:1\n382#1,2:540\n390#1:542\n390#1:543\n384#1,4:544\n382#1,2:548\n390#1:550\n384#1,4:551\n390#1:555\n382#1,6:556\n382#1,2:562\n390#1:564\n384#1,4:565\n1#2:537\n1915#3,2:538\n*S KotlinDebug\n*F\n+ 1 PathRecursiveFunctions.kt\nkotlin/io/path/PathsKt__PathRecursiveFunctionsKt\n*L\n398#1:540,2\n413#1:542\n416#1:543\n398#1:544,4\n424#1:548,2\n425#1:550\n424#1:551,4\n436#1:555\n444#1:556,6\n467#1:562,2\n468#1:564\n467#1:565,4\n318#1:538,2\n*E\n"})
/* loaded from: classes5.dex */
class PathsKt__PathRecursiveFunctionsKt extends PathsKt__PathReadWriteKt {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = null;

        static {
            int[] r0 = new int[CopyActionResult.values().length];
            r0[CopyActionResult.CONTINUE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L12
        L17:
            r0[CopyActionResult.TERMINATE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L13
        L23:
            r0[CopyActionResult.SKIP_SUBTREE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L14
        L7:
            $EnumSwitchMapping$0 = r0;
            int[] r02 = new int[OnErrorResult.values().length];
            r02[OnErrorResult.TERMINATE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L15
        L19:
            r02[OnErrorResult.SKIP_SUBTREE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L16
        L10:
            $EnumSwitchMapping$1 = r02;
        }
    }

    public static /* synthetic */ CopyActionResult $r8$lambda$3VYkoVP17knpGuiVw5BoXBQ_zc8(boolean r0, CopyActionContext r1, Path r2, Path r3) {
        return copyToRecursively$lambda$1$PathsKt__PathRecursiveFunctionsKt(r0, r1, r2, r3);
    }

    public static /* synthetic */ CopyActionResult $r8$lambda$A3AggpeIcVTXny9TWGNUBxfCgaU(boolean r0, CopyActionContext r1, Path r2, Path r3) {
        return copyToRecursively$lambda$3$PathsKt__PathRecursiveFunctionsKt(r0, r1, r2, r3);
    }

    public static /* synthetic */ OnErrorResult $r8$lambda$De4yXv8T0vx7waJvVzG_4HFVSss(Path r0, Path r1, Exception r2) {
        return copyToRecursively$lambda$2$PathsKt__PathRecursiveFunctionsKt(r0, r1, r2);
    }

    public static /* synthetic */ FileVisitResult $r8$lambda$IkZ125cv9leHdM_R4zOtmPWi6nc(ArrayList r0, Function3 r1, Path r2, Path r3, Path r4, Path r5, IOException r6) {
        return copyToRecursively$lambda$5$1$PathsKt__PathRecursiveFunctionsKt(r0, r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$Q20yqNtXDRXNb_l9dEjE2GC-B44, reason: not valid java name */
    public static /* synthetic */ FileVisitResult m3242$r8$lambda$Q20yqNtXDRXNb_l9dEjE2GCB44(ArrayList r0, Function3 r1, Path r2, Path r3, Path r4, Function3 r5, Path r6, BasicFileAttributes r7) {
        return copyToRecursively$lambda$5$0$PathsKt__PathRecursiveFunctionsKt(r0, r1, r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$RTX0Ejym-JswkQvhEjqK7fQVJOQ, reason: not valid java name */
    public static /* synthetic */ OnErrorResult m3243$r8$lambda$RTX0EjymJswkQvhEjqK7fQVJOQ(Path r0, Path r1, Exception r2) {
        return copyToRecursively$lambda$0$PathsKt__PathRecursiveFunctionsKt(r0, r1, r2);
    }

    /* renamed from: $r8$lambda$iE84oOjPIAfm60pr-kh4MNeINmU, reason: not valid java name */
    public static /* synthetic */ Unit m3244$r8$lambda$iE84oOjPIAfm60prkh4MNeINmU(ArrayList r0, Function3 r1, Path r2, Path r3, Path r4, Function3 r5, FileVisitorBuilder r6) {
        return copyToRecursively$lambda$5$PathsKt__PathRecursiveFunctionsKt(r0, r1, r2, r3, r4, r5, r6);
    }

    public PathsKt__PathRecursiveFunctionsKt() {
    }

    public static final /* synthetic */ FileVisitResult access$copyToRecursively$copy(ArrayList r0, Function3 r1, Path r2, Path r3, Path r4, Function3 r5, Path r6, BasicFileAttributes r7) {
        return copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(r0, r1, r2, r3, r4, r5, r6, r7);
    }

    public static final /* synthetic */ FileVisitResult access$copyToRecursively$error(Function3 r0, Path r1, Path r2, Path r3, Path r4, Exception r5) {
        return copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(r0, r1, r2, r3, r4, r5);
    }

    public static final void checkFileName(Path r3) {
        String r0 = PathsKt__PathUtilsKt.getName(r3);
        int r1 = r0.hashCode();
        if (r1 == 46) goto L31;
        if (r1 == 1518) goto L28;
        if (r1 == 45679) goto L25;
        if (r1 == 45724) goto L22;
        if (r1 == 1472) goto L19;
        if (r1 == 1473) goto L16;
        return;
    L16:
        if (r0.equals("./") == true) goto L34;
        return;
    L34:
        throw new IllegalFileNameException(r3);
    L19:
        if (r0.equals("..") == true) goto L34;
        return;
    L22:
        if (r0.equals("..\\") == true) goto L34;
        return;
    L25:
        if (r0.equals("../") == true) goto L34;
        return;
    L28:
        if (r0.equals(".\\") == true) goto L34;
        return;
    L31:
        if (r0.equals(".") == true) goto L34;
    }

    private static final void checkNotSameAs$PathsKt__PathRecursiveFunctionsKt(Path r1, Path r2) {
        if (Files.isSymbolicLink(r1) == false) goto L5;
        return;
    L5:
        if (Files.isSameFile(r1, r2) == true) goto L8;
        return;
    L8:
        throw new FileSystemLoopException(r1.toString());
    }

    private static final void collectIfThrows$PathsKt__PathRecursiveFunctionsKt(ExceptionsCollector r0, Function0<Unit> r1) {
        r1.invoke();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        r0.collect(e);
    }

    @SinceKotlin(version = "1.8")
    @ExperimentalPathApi
    @IgnorableReturnValue
    public static final Path copyToRecursively(Path r7, Path r8, Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> r9, boolean r10, Function3<? super CopyActionContext, ? super Path, ? super Path, ? extends CopyActionResult> r11) {
        LinkOption[] r1 = LinkFollowing.INSTANCE.toLinkOptions(r10);
        LinkOption[] r12 = (LinkOption[]) Arrays.copyOf(r1, r1.length);
        if (Files.exists(r7, (LinkOption[]) Arrays.copyOf(r12, r12.length)) == false) goto L37;
        boolean r13 = false;
        if (Files.exists(r7, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0)) == false) goto L34;
        if (r10 == true) goto L10;
        if (Files.isSymbolicLink(r7) == true) goto L34;
    L10:
        if (Files.exists(r8, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0)) == true) goto L12;
    L14:
        boolean r2 = false;
    L15:
        if (r2 == false) goto L19;
        if (Files.isSameFile(r7, r8) == true) goto L34;
    L19:
        if (Intrinsics.areEqual(r7.getFileSystem(), r8.getFileSystem()) == false) goto L30;
        if (r2 == false) goto L23;
        r13 = r8.toRealPath(new LinkOption[0]).startsWith(r7.toRealPath(new LinkOption[0]));
        goto L30
    L23:
        Path r22 = r8.getParent();
        if (r22 == null) goto L30;
        if (Files.exists(r22, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0)) == false) goto L30;
        if (r22.toRealPath(new LinkOption[0]).startsWith(r7.toRealPath(new LinkOption[0])) == false) goto L30;
        r13 = true;
    L30:
        if (r13 == false) goto L34;
        throw new FileSystemException(r7.toString(), r8.toString(), "Recursively copying a directory into its subdirectory is prohibited.");
    L12:
        if (Files.isSymbolicLink(r8) == true) goto L14;
        r2 = true;
    L34:
        PathsKt__PathUtilsKt.visitFileTree$default(r7, 0, r10, new PathsKt__PathRecursiveFunctionsKt$$ExternalSyntheticLambda3(new ArrayList(), r11, r7, r8, r8.normalize(), r9), 1, null);
        return r8;
    L37:
        throw new NoSuchFileException(r7.toString(), r8.toString(), "The source file doesn't exist.");
    }

    @SinceKotlin(version = "1.8")
    @ExperimentalPathApi
    @IgnorableReturnValue
    public static final Path copyToRecursively(Path r7, Path r8, Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> r9, boolean r10, boolean r11) {
        if (r11 == false) goto L6;
        return copyToRecursively(r7, r8, r9, r10, new PathsKt__PathRecursiveFunctionsKt$$ExternalSyntheticLambda2(r10));
    L6:
        return copyToRecursively$default(r7, r8, r9, r10, null, 8, null);
    }

    private static final FileVisitResult copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(ArrayList<Path> r1, Function3<? super CopyActionContext, ? super Path, ? super Path, ? extends CopyActionResult> r2, Path r3, Path r4, Path r5, Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> r6, Path r7, BasicFileAttributes r8) {
    L5:
        e = move-exception;
        return copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(r6, r3, r4, r5, r7, e);
    L3:
        if (r1.isEmpty() == true) goto L7;
        checkFileName(r7);     // Catch: Exception -> L5
        checkNotSameAs$PathsKt__PathRecursiveFunctionsKt(r7, (Path) CollectionsKt.last(r1));     // Catch: Exception -> L5
    L7:
        return toFileVisitResult$PathsKt__PathRecursiveFunctionsKt(r2.invoke(DefaultCopyActionContext.INSTANCE, r7, copyToRecursively$destination$PathsKt__PathRecursiveFunctionsKt(r3, r4, r5, r7)));
    }

    public static /* synthetic */ Path copyToRecursively$default(Path r0, Path r1, Function3 r2, boolean r3, Function3 r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L6;
        r2 = new PathsKt__PathRecursiveFunctionsKt$$ExternalSyntheticLambda0();
    L6:
        if ((r5 & 8) == 0) goto L9;
        r4 = new PathsKt__PathRecursiveFunctionsKt$$ExternalSyntheticLambda1(r3);
    L9:
        return copyToRecursively(r0, r1, r2, r3, r4);
    }

    public static /* synthetic */ Path copyToRecursively$default(Path r0, Path r1, Function3 r2, boolean r3, boolean r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L6;
        r2 = new PathsKt__PathRecursiveFunctionsKt$$ExternalSyntheticLambda6();
    L6:
        return copyToRecursively(r0, r1, r2, r3, r4);
    }

    private static final Path copyToRecursively$destination$PathsKt__PathRecursiveFunctionsKt(Path r0, Path r1, Path r2, Path r3) {
        Path r02 = r1.resolve(PathsKt__PathUtilsKt.relativeTo(r3, r0).toString());
        if (r02.normalize().startsWith(r2) == false) goto L6;
        return r02;
    L6:
        throw new IllegalFileNameException(r3, r02, "Copying files to outside the specified target directory is prohibited. The directory being recursively copied might contain an entry with an illegal name.");
    }

    private static final FileVisitResult copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> r0, Path r1, Path r2, Path r3, Path r4, Exception r5) {
        return toFileVisitResult$PathsKt__PathRecursiveFunctionsKt(r0.invoke(r4, copyToRecursively$destination$PathsKt__PathRecursiveFunctionsKt(r1, r2, r3, r4), r5));
    }

    private static final OnErrorResult copyToRecursively$lambda$0$PathsKt__PathRecursiveFunctionsKt(Path r0, Path r1, Exception r2) {
        throw r2;
    }

    private static final CopyActionResult copyToRecursively$lambda$1$PathsKt__PathRecursiveFunctionsKt(boolean r2, CopyActionContext r3, Path r4, Path r5) {
        LinkOption[] r22 = LinkFollowing.INSTANCE.toLinkOptions(r2);
        boolean r32 = Files.isDirectory(r5, (LinkOption[]) Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1));
        LinkOption[] r0 = (LinkOption[]) Arrays.copyOf(r22, r22.length);
        if (Files.isDirectory(r4, (LinkOption[]) Arrays.copyOf(r0, r0.length)) == false) goto L5;
        if (r32 == false) goto L5;
    L9:
        return CopyActionResult.CONTINUE;
    L5:
        if (r32 == false) goto L7;
        deleteRecursively(r5);
    L7:
        SpreadBuilder r33 = new SpreadBuilder(2);
        r33.addSpread(r22);
        r33.add(StandardCopyOption.REPLACE_EXISTING);
        CopyOption[] r23 = (CopyOption[]) r33.toArray(new CopyOption[r33.size()]);
        Files.copy(r4, r5, (CopyOption[]) Arrays.copyOf(r23, r23.length));
        goto L9
    }

    private static final OnErrorResult copyToRecursively$lambda$2$PathsKt__PathRecursiveFunctionsKt(Path r0, Path r1, Exception r2) {
        throw r2;
    }

    private static final CopyActionResult copyToRecursively$lambda$3$PathsKt__PathRecursiveFunctionsKt(boolean r0, CopyActionContext r1, Path r2, Path r3) {
        return r1.copyToIgnoringExistingDirectory(r2, r3, r0);
    }

    private static final FileVisitResult copyToRecursively$lambda$5$0$PathsKt__PathRecursiveFunctionsKt(ArrayList r0, Function3 r1, Path r2, Path r3, Path r4, Function3 r5, Path r6, BasicFileAttributes r7) {
        FileVisitResult r12 = copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(r0, r1, r2, r3, r4, r5, r6, r7);
        if (r12 != FileVisitResult.CONTINUE) goto L5;
        r0.add(r6);
    L5:
        return r12;
    }

    private static final FileVisitResult copyToRecursively$lambda$5$1$PathsKt__PathRecursiveFunctionsKt(ArrayList r0, Function3 r1, Path r2, Path r3, Path r4, Path r5, IOException r6) {
        CollectionsKt.removeLast(r0);
        if (r6 != null) goto L7;
        return FileVisitResult.CONTINUE;
    L7:
        return copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(r1, r2, r3, r4, r5, r6);
    }

    private static final Unit copyToRecursively$lambda$5$PathsKt__PathRecursiveFunctionsKt(ArrayList r8, Function3 r9, Path r10, Path r11, Path r12, Function3 r13, FileVisitorBuilder r14) {
        r14.onPreVisitDirectory(new PathsKt__PathRecursiveFunctionsKt$$ExternalSyntheticLambda4(r8, r9, r10, r11, r12, r13));
        r14.onVisitFile(new PathsKt__PathRecursiveFunctionsKt$copyToRecursively$5$2(r8, r9, r10, r11, r12, r13));
        r14.onVisitFileFailed(new PathsKt__PathRecursiveFunctionsKt$copyToRecursively$5$3(r13, r10, r11, r12));
        r14.onPostVisitDirectory(new PathsKt__PathRecursiveFunctionsKt$$ExternalSyntheticLambda5(r8, r13, r10, r11, r12));
        return Unit.INSTANCE;
    }

    @SinceKotlin(version = "1.8")
    @ExperimentalPathApi
    public static final void deleteRecursively(Path r2) {
        List<Exception> r22 = deleteRecursivelyImpl$PathsKt__PathRecursiveFunctionsKt(r2);
        if (r22.isEmpty() == true) goto L9;
        FileSystemException r0 = new FileSystemException("Failed to delete one or more files. See suppressed exceptions for details.");
        Iterator<T> r23 = r22.iterator();
    L6:
        if (r23.hasNext() == false) goto L8;
        ExceptionsKt.addSuppressed(r0, (Exception) r23.next());
        goto L6
    L8:
        throw r0;
    }

    private static final List<Exception> deleteRecursivelyImpl$PathsKt__PathRecursiveFunctionsKt(Path r8) {
        boolean r1 = false;
        boolean r2 = true;
        ExceptionsCollector r0 = new ExceptionsCollector(0, 1, null);
        Path r4 = r8.getFileName();
        if (r4 == null) goto L23;
        Path r5 = r8.getParent();
        if (r5 != null) goto L29;
        r5 = r8.getFileSystem().getPath(_UrlKt.FRAGMENT_ENCODE_SET, new String[0]);
    L29:
        DirectoryStream<Path> r6 = Files.newDirectoryStream(r5);     // Catch: Throwable -> L9
    L10:
        if (r6 == null) goto L23;
    L14:
        th = move-exception;
        throw th;     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        CloseableKt.closeFinally(r6, th);
        throw th;
    L12:
        if ((r6 instanceof SecureDirectoryStream) == false) goto L16;
        r0.setPath(r5);     // Catch: Throwable -> L14
        handleEntry$PathsKt__PathRecursiveFunctionsKt((SecureDirectoryStream) r6, r4, null, r0);     // Catch: Throwable -> L14
    L17:
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L14
        CloseableKt.closeFinally(r6, null);
        r2 = r1;
        goto L23
    L16:
        r1 = true;
    L9:
        r6 = null;
    L23:
        if (r2 == false) goto L26;
        insecureHandleEntry$PathsKt__PathRecursiveFunctionsKt(r8, null, r0);
    L26:
        return r0.getCollectedExceptions();
    }

    private static final void enterDirectory$PathsKt__PathRecursiveFunctionsKt(SecureDirectoryStream<Path> r3, Path r4, ExceptionsCollector r5) {
        SecureDirectoryStream<Path> r32 = r3.newDirectoryStream(r4, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});     // Catch: Exception -> L5 NoSuchFileException -> L7
    L8:
        if (r32 == null) goto L31;
        Iterator<Path> r42 = r32.iterator();     // Catch: Throwable -> L13
    L11:
        if (r42.hasNext() == false) goto L15;
        handleEntry$PathsKt__PathRecursiveFunctionsKt(r32, r42.next().getFileName(), r5.getPath(), r5);     // Catch: Throwable -> L13
        goto L11
    L15:
        Unit r43 = Unit.INSTANCE;     // Catch: Throwable -> L13
        CloseableKt.closeFinally(r32, null);     // Catch: Exception -> L5
        return;
    L13:
        th = move-exception;
        throw th;     // Catch: Throwable -> L19
    L19:
        th = move-exception;
        CloseableKt.closeFinally(r32, th);     // Catch: Exception -> L5
        throw th;     // Catch: Exception -> L5
    L31:
        return;
    L5:
        e = move-exception;
        r5.collect(e);
        return;
    L7:
        r32 = null;
        goto L8
    }

    private static final void handleEntry$PathsKt__PathRecursiveFunctionsKt(SecureDirectoryStream<Path> r1, Path r2, Path r3, ExceptionsCollector r4) {
        r4.enterEntry(r2);
        if (r3 == null) goto L20;
        Path r0 = r4.getPath();     // Catch: Exception -> L5
        checkFileName(r0);     // Catch: Exception -> L5
        checkNotSameAs$PathsKt__PathRecursiveFunctionsKt(r0, r3);     // Catch: Exception -> L5
    L5:
        e = move-exception;
        r4.collect(e);
    L15:
        r4.exitEntry(r2);
        return;
    L20:
        if (isDirectory$PathsKt__PathRecursiveFunctionsKt(r1, r2, new LinkOption[]{LinkOption.NOFOLLOW_LINKS}) == false) goto L12;
        int r32 = r4.getTotalExceptions();     // Catch: Exception -> L5
        enterDirectory$PathsKt__PathRecursiveFunctionsKt(r1, r2, r4);     // Catch: Exception -> L5
        if (r32 != r4.getTotalExceptions()) goto L15;
        r1.deleteDirectory(r2);     // Catch: Exception -> L5 NoSuchFileException -> L17
        Unit r12 = Unit.INSTANCE;     // Catch: Exception -> L5 NoSuchFileException -> L17
    L19:
        goto L15
    L12:
        r1.deleteFile(r2);     // Catch: Exception -> L5 NoSuchFileException -> L17
        Unit r13 = Unit.INSTANCE;     // Catch: Exception -> L5 NoSuchFileException -> L17
        goto L19
    }

    private static final void insecureEnterDirectory$PathsKt__PathRecursiveFunctionsKt(Path r4, ExceptionsCollector r5) {
        DirectoryStream<Path> r1 = Files.newDirectoryStream(r4);     // Catch: Exception -> L5 NoSuchFileException -> L7
    L8:
        if (r1 == null) goto L31;
        Iterator<Path> r2 = r1.iterator();     // Catch: Throwable -> L13
    L11:
        if (r2.hasNext() == false) goto L15;
        insecureHandleEntry$PathsKt__PathRecursiveFunctionsKt(r2.next(), r4, r5);     // Catch: Throwable -> L13
        goto L11
    L15:
        Unit r42 = Unit.INSTANCE;     // Catch: Throwable -> L13
        CloseableKt.closeFinally(r1, null);     // Catch: Exception -> L5
        return;
    L13:
        th = move-exception;
        throw th;     // Catch: Throwable -> L19
    L19:
        th = move-exception;
        CloseableKt.closeFinally(r1, th);     // Catch: Exception -> L5
        throw th;     // Catch: Exception -> L5
    L31:
        return;
    L5:
        e = move-exception;
        r5.collect(e);
        return;
    L7:
        r1 = null;
        goto L8
    }

    private static final void insecureHandleEntry$PathsKt__PathRecursiveFunctionsKt(Path r1, Path r2, ExceptionsCollector r3) {
        if (r2 == null) goto L7;
        checkFileName(r1);     // Catch: Exception -> L4
        checkNotSameAs$PathsKt__PathRecursiveFunctionsKt(r1, r2);     // Catch: Exception -> L4
    L4:
        e = move-exception;
        r3.collect(e);
        return;
    L7:
        if (Files.isDirectory(r1, (LinkOption[]) Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1)) == false) goto L12;
        int r22 = r3.getTotalExceptions();     // Catch: Exception -> L4
        insecureEnterDirectory$PathsKt__PathRecursiveFunctionsKt(r1, r3);     // Catch: Exception -> L4
        if (r22 != r3.getTotalExceptions()) goto L17;
        Files.deleteIfExists(r1);     // Catch: Exception -> L4
        return;
    L17:
        return;
    L12:
        Files.deleteIfExists(r1);     // Catch: Exception -> L4
    }

    private static final boolean isDirectory$PathsKt__PathRecursiveFunctionsKt(SecureDirectoryStream<Path> r2, Path r3, LinkOption... r4) {
        Boolean r22 = Boolean.valueOf(((BasicFileAttributeView) r2.getFileAttributeView(r3, BasicFileAttributeView.class, (LinkOption[]) Arrays.copyOf(r4, r4.length))).readAttributes().isDirectory());     // Catch: NoSuchFileException -> L4
    L5:
        if (r22 != null) goto L11;
        return false;
    L11:
        return r22.booleanValue();
    L4:
        r22 = null;
        goto L5
    }

    @ExperimentalPathApi
    private static final FileVisitResult toFileVisitResult$PathsKt__PathRecursiveFunctionsKt(CopyActionResult r1) {
        int r12 = WhenMappings.$EnumSwitchMapping$0[r1.ordinal()];
        if (r12 == 1) goto L15;
        if (r12 == 2) goto L13;
        if (r12 == 3) goto L9;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L9:
        return FileVisitResult.SKIP_SUBTREE;
    L13:
        return FileVisitResult.TERMINATE;
    L15:
        return FileVisitResult.CONTINUE;
    }

    @ExperimentalPathApi
    private static final FileVisitResult toFileVisitResult$PathsKt__PathRecursiveFunctionsKt(OnErrorResult r1) {
        int r12 = WhenMappings.$EnumSwitchMapping$1[r1.ordinal()];
        if (r12 == 1) goto L11;
        if (r12 == 2) goto L7;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L7:
        return FileVisitResult.SKIP_SUBTREE;
    L11:
        return FileVisitResult.TERMINATE;
    }

    private static final <R> R tryIgnoreNoSuchFileException$PathsKt__PathRecursiveFunctionsKt(Function0<? extends R> r0) {
        return r0.invoke();
    L4:
        return null;
    }
}
