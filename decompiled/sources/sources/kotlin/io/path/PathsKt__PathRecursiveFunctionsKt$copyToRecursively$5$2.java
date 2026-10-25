package kotlin.io.path;

import j$.nio.file.FileVisitResult;
import j$.nio.file.Path;
import j$.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final /* synthetic */ class PathsKt__PathRecursiveFunctionsKt$copyToRecursively$5$2 extends FunctionReferenceImpl implements Function2<Path, BasicFileAttributes, FileVisitResult> {
    final /* synthetic */ Function3<CopyActionContext, Path, Path, CopyActionResult> $copyAction;
    final /* synthetic */ Path $normalizedTarget;
    final /* synthetic */ Function3<Path, Path, Exception, OnErrorResult> $onError;
    final /* synthetic */ ArrayList<Path> $stack;
    final /* synthetic */ Path $target;
    final /* synthetic */ Path $this_copyToRecursively;

    /* JADX WARN: Multi-variable type inference failed */
    public PathsKt__PathRecursiveFunctionsKt$copyToRecursively$5$2(ArrayList<Path> r1, Function3<? super CopyActionContext, ? super Path, ? super Path, ? extends CopyActionResult> r2, Path r3, Path r4, Path r5, Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> r6) {
        this.$stack = r1;
        this.$copyAction = r2;
        this.$this_copyToRecursively = r3;
        this.$target = r4;
        this.$normalizedTarget = r5;
        this.$onError = r6;
        super(2, Intrinsics.Kotlin.class, "copy", "copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(Ljava/util/ArrayList;Lkotlin/jvm/functions/Function3;Ljava/nio/file/Path;Ljava/nio/file/Path;Ljava/nio/file/Path;Lkotlin/jvm/functions/Function3;Ljava/nio/file/Path;Ljava/nio/file/attribute/BasicFileAttributes;)Ljava/nio/file/FileVisitResult;", 0);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final FileVisitResult invoke2(Path r9, BasicFileAttributes r10) {
        return PathsKt__PathRecursiveFunctionsKt.access$copyToRecursively$copy(this.$stack, this.$copyAction, this.$this_copyToRecursively, this.$target, this.$normalizedTarget, this.$onError, r9, r10);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ FileVisitResult invoke(Path r1, BasicFileAttributes r2) {
        return invoke2(r1, r2);
    }
}
