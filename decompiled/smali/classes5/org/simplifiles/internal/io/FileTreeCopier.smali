.class public final Lorg/simplifiles/internal/io/FileTreeCopier;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/simplifiles/internal/io/FileTreeCopier$WhenMappings;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u0003\n\u0002\u0010\u0002\n\u0002\u0008\u0006\u0008\u00c0\u0002\u0018\u00002\u00020\u0001B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u00082\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\u0008\t\u0010\nJ\u001d\u0010\r\u001a\u00020\u000c2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\u0008\r\u0010\u000eJ\'\u0010\u0010\u001a\u00020\u000c2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0008\u0008\u0002\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\u0008\u0010\u0010\u0011\u00a8\u0006\u0012"
    }
    d2 = {
        "Lorg/simplifiles/internal/io/FileTreeCopier;",
        "",
        "<init>",
        "()V",
        "j$/nio/file/Path",
        "path",
        "Lorg/simplifiles/files/DirectoryTransferOptions;",
        "options",
        "",
        "included",
        "(Lj$/nio/file/Path;Lorg/simplifiles/files/DirectoryTransferOptions;)Z",
        "source",
        "",
        "validateDirectory",
        "(Lj$/nio/file/Path;Lorg/simplifiles/files/DirectoryTransferOptions;)V",
        "target",
        "copyDirectory",
        "(Lj$/nio/file/Path;Lj$/nio/file/Path;Lorg/simplifiles/files/DirectoryTransferOptions;)V",
        "simplifiles"
    }
    k = 0x1
    mv = {
        0x2,
        0x3,
        0x0
    }
    xi = 0x30
.end annotation

.annotation build Lkotlin/jvm/internal/SourceDebugExtension;
    value = {
        "SMAP\nFileTreeCopier.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileTreeCopier.kt\norg/simplifiles/internal/io/FileTreeCopier\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,77:1\n1342#2,2:78\n614#2:80\n1342#2,2:81\n*S KotlinDebug\n*F\n+ 1 FileTreeCopier.kt\norg/simplifiles/internal/io/FileTreeCopier\n*L\n22#1:78,2\n46#1:80\n47#1:81,2\n*E\n"
    }
.end annotation


# static fields
.field public static final INSTANCE:Lorg/simplifiles/internal/io/FileTreeCopier;


# direct methods
.method public static synthetic $r8$lambda$js9gTUjWA9s28TU846zKjOMG-cI(Lj$/nio/file/Path;)Z
    .locals 0

    .line 0
    invoke-static {p0}, Lorg/simplifiles/internal/io/FileTreeCopier;->validateDirectory$lambda$0$1(Lj$/nio/file/Path;)Z

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$mUdELbRj367i4FBJiko7U8j6JPQ(Lorg/simplifiles/files/DirectoryTransferOptions;Lj$/nio/file/Path;)Z
    .locals 0

    .line 0
    invoke-static {p0, p1}, Lorg/simplifiles/internal/io/FileTreeCopier;->validateDirectory$lambda$0$0(Lorg/simplifiles/files/DirectoryTransferOptions;Lj$/nio/file/Path;)Z

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$v-gR1FcIqk7lNtPkCVl7ezhx3g8(Lorg/simplifiles/files/DirectoryTransferOptions;Lj$/nio/file/Path;)Z
    .locals 0

    .line 0
    invoke-static {p0, p1}, Lorg/simplifiles/internal/io/FileTreeCopier;->copyDirectory$lambda$0$0(Lorg/simplifiles/files/DirectoryTransferOptions;Lj$/nio/file/Path;)Z

    move-result p0

    return p0
.end method

.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lorg/simplifiles/internal/io/FileTreeCopier;

    invoke-direct {v0}, Lorg/simplifiles/internal/io/FileTreeCopier;-><init>()V

    sput-object v0, Lorg/simplifiles/internal/io/FileTreeCopier;->INSTANCE:Lorg/simplifiles/internal/io/FileTreeCopier;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 10
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private static final copyDirectory$lambda$0$0(Lorg/simplifiles/files/DirectoryTransferOptions;Lj$/nio/file/Path;)Z
    .locals 1

    .line 45
    sget-object v0, Lorg/simplifiles/internal/io/FileTreeCopier;->INSTANCE:Lorg/simplifiles/internal/io/FileTreeCopier;

    invoke-direct {v0, p1, p0}, Lorg/simplifiles/internal/io/FileTreeCopier;->included(Lj$/nio/file/Path;Lorg/simplifiles/files/DirectoryTransferOptions;)Z

    move-result p0

    return p0
.end method

.method private final included(Lj$/nio/file/Path;Lorg/simplifiles/files/DirectoryTransferOptions;)Z
    .locals 0

    .line 71
    sget-object p0, Lorg/simplifiles/internal/io/SymlinkSupport;->INSTANCE:Lorg/simplifiles/internal/io/SymlinkSupport;

    invoke-virtual {p2}, Lorg/simplifiles/files/DirectoryTransferOptions;->getSymlinkPolicy()Lorg/simplifiles/files/SymlinkPolicy;

    move-result-object p2

    invoke-virtual {p0, p1, p2}, Lorg/simplifiles/internal/io/SymlinkSupport;->decide(Lj$/nio/file/Path;Lorg/simplifiles/files/SymlinkPolicy;)Lorg/simplifiles/internal/io/SymlinkDecision;

    move-result-object p0

    sget-object p2, Lorg/simplifiles/internal/io/FileTreeCopier$WhenMappings;->$EnumSwitchMapping$0:[I

    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    move-result p0

    aget p0, p2, p0

    const/4 p2, 0x1

    if-eq p0, p2, :cond_2

    const/4 p2, 0x2

    if-eq p0, p2, :cond_1

    const/4 p2, 0x3

    if-eq p0, p2, :cond_0

    invoke-static {}, Lkotlin/LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;->m()V

    :goto_0
    const/4 p0, 0x0

    return p0

    .line 74
    :cond_0
    const-string p0, "Directory contains a symbolic link: "

    invoke-static {p0, p1}, Lorg/simplifiles/files/SimpliFile$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Object;)V

    goto :goto_0

    :cond_1
    const/4 p0, 0x0

    return p0

    :cond_2
    return p2
.end method

.method private static final validateDirectory$lambda$0$0(Lorg/simplifiles/files/DirectoryTransferOptions;Lj$/nio/file/Path;)Z
    .locals 1

    .line 20
    sget-object v0, Lorg/simplifiles/internal/io/FileTreeCopier;->INSTANCE:Lorg/simplifiles/internal/io/FileTreeCopier;

    invoke-direct {v0, p1, p0}, Lorg/simplifiles/internal/io/FileTreeCopier;->included(Lj$/nio/file/Path;Lorg/simplifiles/files/DirectoryTransferOptions;)Z

    move-result p0

    return p0
.end method

.method private static final validateDirectory$lambda$0$1(Lj$/nio/file/Path;)Z
    .locals 1

    const/4 v0, 0x0

    .line 21
    new-array v0, v0, [Lj$/nio/file/LinkOption;

    invoke-static {p0, v0}, Lj$/nio/file/Files;->isRegularFile(Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Z

    move-result p0

    return p0
.end method


# virtual methods
.method public final copyDirectory(Lj$/nio/file/Path;Lj$/nio/file/Path;Lorg/simplifiles/files/DirectoryTransferOptions;)V
    .locals 5

    .line 41
    invoke-virtual {p0, p1, p3}, Lorg/simplifiles/internal/io/FileTreeCopier;->validateDirectory(Lj$/nio/file/Path;Lorg/simplifiles/files/DirectoryTransferOptions;)V

    const/4 p0, 0x0

    .line 43
    new-array v0, p0, [Lj$/nio/file/FileVisitOption;

    invoke-static {p1, v0}, Lj$/nio/file/Files;->walk(Lj$/nio/file/Path;[Lj$/nio/file/FileVisitOption;)Ljava/util/stream/Stream;

    move-result-object v0

    .line 44
    :try_start_0
    invoke-static {v0}, Lkotlin/streams/jdk8/StreamsKt;->asSequence(Ljava/util/stream/Stream;)Lkotlin/sequences/Sequence;

    move-result-object v1

    .line 45
    new-instance v2, Lorg/simplifiles/internal/io/FileTreeCopier$$ExternalSyntheticLambda2;

    invoke-direct {v2, p3}, Lorg/simplifiles/internal/io/FileTreeCopier$$ExternalSyntheticLambda2;-><init>(Lorg/simplifiles/files/DirectoryTransferOptions;)V

    invoke-static {v1, v2}, Lkotlin/sequences/SequencesKt;->filter(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;

    move-result-object p3

    .line 614
    new-instance v1, Lorg/simplifiles/internal/io/FileTreeCopier$copyDirectory$lambda$0$$inlined$sortedBy$1;

    invoke-direct {v1}, Lorg/simplifiles/internal/io/FileTreeCopier$copyDirectory$lambda$0$$inlined$sortedBy$1;-><init>()V

    invoke-static {p3, v1}, Lkotlin/sequences/SequencesKt;->sortedWith(Lkotlin/sequences/Sequence;Ljava/util/Comparator;)Lkotlin/sequences/Sequence;

    move-result-object p3

    .line 1342
    invoke-interface {p3}, Lkotlin/sequences/Sequence;->iterator()Ljava/util/Iterator;

    move-result-object p3

    :goto_0
    invoke-interface {p3}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_3

    invoke-interface {p3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lj$/nio/file/Path;

    .line 48
    invoke-interface {p1, v1}, Lj$/nio/file/Path;->relativize(Lj$/nio/file/Path;)Lj$/nio/file/Path;

    move-result-object v2

    .line 49
    invoke-interface {p2, v2}, Lj$/nio/file/Path;->resolve(Lj$/nio/file/Path;)Lj$/nio/file/Path;

    move-result-object v2

    .line 51
    new-array v3, p0, [Lj$/nio/file/LinkOption;

    invoke-static {v1, v3}, Lj$/nio/file/Files;->isDirectory(Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 52
    new-array v1, p0, [Lj$/nio/file/LinkOption;

    invoke-static {v2, v1}, Lj$/nio/file/Files;->exists(Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Z

    move-result v1

    if-eqz v1, :cond_0

    new-array v1, p0, [Lj$/nio/file/LinkOption;

    invoke-static {v2, v1}, Lj$/nio/file/Files;->isDirectory(Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Z

    move-result v1

    if-nez v1, :cond_0

    .line 53
    invoke-static {v2}, Lj$/nio/file/Files;->deleteIfExists(Lj$/nio/file/Path;)Z

    goto :goto_1

    :catchall_0
    move-exception p0

    goto :goto_2

    .line 55
    :cond_0
    :goto_1
    new-array v1, p0, [Lj$/nio/file/attribute/FileAttribute;

    invoke-static {v2, v1}, Lj$/nio/file/Files;->createDirectories(Lj$/nio/file/Path;[Lj$/nio/file/attribute/FileAttribute;)Lj$/nio/file/Path;

    goto :goto_0

    .line 57
    :cond_1
    invoke-interface {v2}, Lj$/nio/file/Path;->getParent()Lj$/nio/file/Path;

    move-result-object v3

    new-array v4, p0, [Lj$/nio/file/attribute/FileAttribute;

    invoke-static {v3, v4}, Lj$/nio/file/Files;->createDirectories(Lj$/nio/file/Path;[Lj$/nio/file/attribute/FileAttribute;)Lj$/nio/file/Path;

    .line 58
    new-array v3, p0, [Lj$/nio/file/LinkOption;

    invoke-static {v2, v3}, Lj$/nio/file/Files;->isDirectory(Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Z

    move-result v3

    if-eqz v3, :cond_2

    .line 59
    sget-object v3, Lorg/simplifiles/internal/io/FileTreeCleaner;->INSTANCE:Lorg/simplifiles/internal/io/FileTreeCleaner;

    invoke-virtual {v3, v2}, Lorg/simplifiles/internal/io/FileTreeCleaner;->deleteRecursively(Lj$/nio/file/Path;)V

    :cond_2
    const/4 v3, 0x1

    .line 61
    new-array v3, v3, [Lj$/nio/file/CopyOption;

    sget-object v4, Lj$/nio/file/StandardCopyOption;->REPLACE_EXISTING:Lj$/nio/file/StandardCopyOption;

    aput-object v4, v3, p0

    invoke-static {v1, v2, v3}, Lj$/nio/file/Files;->copy(Lj$/nio/file/Path;Lj$/nio/file/Path;[Lj$/nio/file/CopyOption;)Lj$/nio/file/Path;

    goto :goto_0

    .line 64
    :cond_3
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    const/4 p0, 0x0

    .line 43
    invoke-static {v0, p0}, Lkotlin/jdk7/AutoCloseableKt;->closeFinally(Ljava/lang/AutoCloseable;Ljava/lang/Throwable;)V

    return-void

    :goto_2
    :try_start_1
    throw p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    :catchall_1
    move-exception p1

    invoke-static {v0, p0}, Lkotlin/jdk7/AutoCloseableKt;->closeFinally(Ljava/lang/AutoCloseable;Ljava/lang/Throwable;)V

    throw p1
.end method

.method public final validateDirectory(Lj$/nio/file/Path;Lorg/simplifiles/files/DirectoryTransferOptions;)V
    .locals 8

    const/4 p0, 0x0

    .line 18
    new-array p0, p0, [Lj$/nio/file/FileVisitOption;

    invoke-static {p1, p0}, Lj$/nio/file/Files;->walk(Lj$/nio/file/Path;[Lj$/nio/file/FileVisitOption;)Ljava/util/stream/Stream;

    move-result-object p0

    .line 19
    :try_start_0
    invoke-static {p0}, Lkotlin/streams/jdk8/StreamsKt;->asSequence(Ljava/util/stream/Stream;)Lkotlin/sequences/Sequence;

    move-result-object v0

    .line 20
    new-instance v1, Lorg/simplifiles/internal/io/FileTreeCopier$$ExternalSyntheticLambda0;

    invoke-direct {v1, p2}, Lorg/simplifiles/internal/io/FileTreeCopier$$ExternalSyntheticLambda0;-><init>(Lorg/simplifiles/files/DirectoryTransferOptions;)V

    invoke-static {v0, v1}, Lkotlin/sequences/SequencesKt;->filter(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;

    move-result-object v0

    new-instance v1, Lorg/simplifiles/internal/io/FileTreeCopier$$ExternalSyntheticLambda1;

    invoke-direct {v1}, Lorg/simplifiles/internal/io/FileTreeCopier$$ExternalSyntheticLambda1;-><init>()V

    .line 21
    invoke-static {v0, v1}, Lkotlin/sequences/SequencesKt;->filter(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;

    move-result-object v0

    .line 1342
    invoke-interface {v0}, Lkotlin/sequences/Sequence;->iterator()Ljava/util/Iterator;

    move-result-object v0

    const-wide/16 v1, 0x0

    move-wide v3, v1

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lj$/nio/file/Path;

    const-wide/16 v6, 0x1

    add-long/2addr v1, v6

    .line 24
    invoke-virtual {p2}, Lorg/simplifiles/files/DirectoryTransferOptions;->getMaxFiles()J

    move-result-wide v6
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    cmp-long v6, v1, v6

    const-string v7, "Directory exceeds copy limit of "

    if-gtz v6, :cond_1

    .line 28
    :try_start_1
    invoke-static {v5}, Lj$/nio/file/Files;->size(Lj$/nio/file/Path;)J

    move-result-wide v5

    add-long/2addr v3, v5

    .line 29
    invoke-virtual {p2}, Lorg/simplifiles/files/DirectoryTransferOptions;->getMaxBytes()J

    move-result-wide v5

    cmp-long v5, v3, v5

    if-gtz v5, :cond_0

    goto :goto_0

    .line 30
    :cond_0
    new-instance v0, Lorg/simplifiles/exception/FileOperationException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Lorg/simplifiles/files/DirectoryTransferOptions;->getMaxBytes()J

    move-result-wide v2

    invoke-virtual {v1, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p2, " bytes: "

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {v0, p1}, Lorg/simplifiles/exception/FileOperationException;-><init>(Ljava/lang/String;)V

    throw v0

    :catchall_0
    move-exception p1

    goto :goto_1

    .line 25
    :cond_1
    new-instance v0, Lorg/simplifiles/exception/FileOperationException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Lorg/simplifiles/files/DirectoryTransferOptions;->getMaxFiles()J

    move-result-wide v2

    invoke-virtual {v1, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p2, " files: "

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {v0, p1}, Lorg/simplifiles/exception/FileOperationException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 33
    :cond_2
    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    const/4 p1, 0x0

    .line 18
    invoke-static {p0, p1}, Lkotlin/jdk7/AutoCloseableKt;->closeFinally(Ljava/lang/AutoCloseable;Ljava/lang/Throwable;)V

    return-void

    :goto_1
    :try_start_2
    throw p1
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    :catchall_1
    move-exception p2

    invoke-static {p0, p1}, Lkotlin/jdk7/AutoCloseableKt;->closeFinally(Ljava/lang/AutoCloseable;Ljava/lang/Throwable;)V

    throw p2
.end method
