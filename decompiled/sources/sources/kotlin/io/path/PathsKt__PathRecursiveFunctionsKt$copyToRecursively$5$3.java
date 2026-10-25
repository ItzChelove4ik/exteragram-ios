package kotlin.io.path;

import j$.nio.file.FileVisitResult;
import j$.nio.file.Path;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final /* synthetic */ class PathsKt__PathRecursiveFunctionsKt$copyToRecursively$5$3 extends FunctionReferenceImpl implements Function2<Path, Exception, FileVisitResult> {
    final /* synthetic */ Path $normalizedTarget;
    final /* synthetic */ Function3<Path, Path, Exception, OnErrorResult> $onError;
    final /* synthetic */ Path $target;
    final /* synthetic */ Path $this_copyToRecursively;

    /* JADX WARN: Multi-variable type inference failed */
    public PathsKt__PathRecursiveFunctionsKt$copyToRecursively$5$3(Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> r7, Path r8, Path r9, Path r10) {
        this.$onError = r7;
        this.$this_copyToRecursively = r8;
        this.$target = r9;
        this.$normalizedTarget = r10;
        super(2, Intrinsics.Kotlin.class, "error", "copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(Lkotlin/jvm/functions/Function3;Ljava/nio/file/Path;Ljava/nio/file/Path;Ljava/nio/file/Path;Ljava/nio/file/Path;Ljava/lang/Exception;)Ljava/nio/file/FileVisitResult;", 0);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final FileVisitResult invoke2(Path r7, Exception r8) {
        return PathsKt__PathRecursiveFunctionsKt.access$copyToRecursively$error(this.$onError, this.$this_copyToRecursively, this.$target, this.$normalizedTarget, r7, r8);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ FileVisitResult invoke(Path r1, Exception r2) {
        return invoke2(r1, r2);
    }
}
