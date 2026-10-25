.class public final Lorg/simplifiles/archive/ArchiveSource;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/simplifiles/archive/ArchiveSource$WhenMappings;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u000e\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0010\u000b\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0002\u0008\n\u0018\u00002\u00020\u0001B\u001b\u0008\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0008\u0008\u0002\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\u0008\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0008H\u0002\u00a2\u0006\u0004\u0008\u000b\u0010\u000cJ\u0011\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002\u00a2\u0006\u0004\u0008\u000e\u0010\u000fJ\u001f\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rH\u0002\u00a2\u0006\u0004\u0008\u0013\u0010\u0014J\u0015\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\u0008\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0012\u00a2\u0006\u0004\u0008\u0017\u0010\u0018J\u0015\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\u0008\u001a\u0010\u001bJ\u001d\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0008\u00a2\u0006\u0004\u0008\u001a\u0010\u001cJ\u0015\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001d\u00a2\u0006\u0004\u0008\u001a\u0010\u001fJ\u001d\u0010!\u001a\u00020 2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0008\u00a2\u0006\u0004\u0008!\u0010\"J\u001d\u0010!\u001a\u00020 2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\u0008\u00a2\u0006\u0004\u0008!\u0010#J\u001a\u0010&\u001a\u00020%2\u0008\u0010$\u001a\u0004\u0018\u00010\u0001H\u0096\u0002\u00a2\u0006\u0004\u0008&\u0010\'J\u000f\u0010)\u001a\u00020(H\u0016\u00a2\u0006\u0004\u0008)\u0010*J\u000f\u0010+\u001a\u00020\rH\u0016\u00a2\u0006\u0004\u0008+\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\u000c\n\u0004\u0008\u0003\u0010,\u001a\u0004\u0008-\u0010.R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\u000c\n\u0004\u0008\u0005\u0010/\u001a\u0004\u00080\u00101\u00a8\u00062"
    }
    d2 = {
        "Lorg/simplifiles/archive/ArchiveSource;",
        "",
        "j$/nio/file/Path",
        "path",
        "Lorg/simplifiles/archive/security/SecurityPolicy;",
        "policy",
        "<init>",
        "(Lj$/nio/file/Path;Lorg/simplifiles/archive/security/SecurityPolicy;)V",
        "Lorg/simplifiles/archive/ArchiveExtractionOptions;",
        "options",
        "",
        "ensureNotCanceled",
        "(Lorg/simplifiles/archive/ArchiveExtractionOptions;)V",
        "",
        "unreadableFileReason",
        "()Ljava/lang/String;",
        "code",
        "message",
        "Lorg/simplifiles/archive/ValidationReport;",
        "blockerReport",
        "(Ljava/lang/String;Ljava/lang/String;)Lorg/simplifiles/archive/ValidationReport;",
        "withPolicy",
        "(Lorg/simplifiles/archive/security/SecurityPolicy;)Lorg/simplifiles/archive/ArchiveSource;",
        "validate",
        "()Lorg/simplifiles/archive/ValidationReport;",
        "Lorg/simplifiles/archive/ExtractedArchive;",
        "extractTo",
        "(Lj$/nio/file/Path;)Lorg/simplifiles/archive/ExtractedArchive;",
        "(Lj$/nio/file/Path;Lorg/simplifiles/archive/ArchiveExtractionOptions;)Lorg/simplifiles/archive/ExtractedArchive;",
        "Ljava/io/File;",
        "file",
        "(Ljava/io/File;)Lorg/simplifiles/archive/ExtractedArchive;",
        "Lorg/simplifiles/files/SimpliDirectory;",
        "extractToDirectory",
        "(Lj$/nio/file/Path;Lorg/simplifiles/archive/ArchiveExtractionOptions;)Lorg/simplifiles/files/SimpliDirectory;",
        "(Ljava/io/File;Lorg/simplifiles/archive/ArchiveExtractionOptions;)Lorg/simplifiles/files/SimpliDirectory;",
        "other",
        "",
        "equals",
        "(Ljava/lang/Object;)Z",
        "",
        "hashCode",
        "()I",
        "toString",
        "Lj$/nio/file/Path;",
        "getPath",
        "()Lj$/nio/file/Path;",
        "Lorg/simplifiles/archive/security/SecurityPolicy;",
        "getPolicy",
        "()Lorg/simplifiles/archive/security/SecurityPolicy;",
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


# instance fields
.field private final path:Lj$/nio/file/Path;

.field private final policy:Lorg/simplifiles/archive/security/SecurityPolicy;


# direct methods
.method public constructor <init>(Lj$/nio/file/Path;Lorg/simplifiles/archive/security/SecurityPolicy;)V
    .locals 0

    .line 27
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 28
    iput-object p1, p0, Lorg/simplifiles/archive/ArchiveSource;->path:Lj$/nio/file/Path;

    .line 29
    iput-object p2, p0, Lorg/simplifiles/archive/ArchiveSource;->policy:Lorg/simplifiles/archive/security/SecurityPolicy;

    return-void
.end method

.method public synthetic constructor <init>(Lj$/nio/file/Path;Lorg/simplifiles/archive/security/SecurityPolicy;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    and-int/lit8 p3, p3, 0x2

    if-eqz p3, :cond_0

    .line 29
    sget-object p2, Lorg/simplifiles/archive/security/SecurityPolicy;->Companion:Lorg/simplifiles/archive/security/SecurityPolicy$Companion;

    invoke-virtual {p2}, Lorg/simplifiles/archive/security/SecurityPolicy$Companion;->strict()Lorg/simplifiles/archive/security/SecurityPolicy;

    move-result-object p2

    .line 27
    :cond_0
    invoke-direct {p0, p1, p2}, Lorg/simplifiles/archive/ArchiveSource;-><init>(Lj$/nio/file/Path;Lorg/simplifiles/archive/security/SecurityPolicy;)V

    return-void
.end method

.method private final blockerReport(Ljava/lang/String;Ljava/lang/String;)Lorg/simplifiles/archive/ValidationReport;
    .locals 8

    .line 253
    new-instance v0, Lorg/simplifiles/archive/ValidationReport;

    .line 255
    new-instance v1, Lorg/simplifiles/archive/ArchiveIssue;

    .line 256
    sget-object v2, Lorg/simplifiles/archive/ArchiveIssueSeverity;->BLOCKER:Lorg/simplifiles/archive/ArchiveIssueSeverity;

    const/16 v6, 0x8

    const/4 v7, 0x0

    const/4 v5, 0x0

    move-object v3, p1

    move-object v4, p2

    .line 255
    invoke-direct/range {v1 .. v7}, Lorg/simplifiles/archive/ArchiveIssue;-><init>(Lorg/simplifiles/archive/ArchiveIssueSeverity;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 254
    invoke-static {v1}, Lkotlin/collections/CollectionsKt;->listOf(Ljava/lang/Object;)Ljava/util/List;

    move-result-object v3

    const/4 v4, 0x3

    const/4 v1, 0x0

    const/4 v2, 0x0

    .line 253
    invoke-direct/range {v0 .. v5}, Lorg/simplifiles/archive/ValidationReport;-><init>(Lorg/simplifiles/archive/ArchiveFormat;Ljava/util/List;Ljava/util/List;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-object v0
.end method

.method private final ensureNotCanceled(Lorg/simplifiles/archive/ArchiveExtractionOptions;)V
    .locals 0

    .line 230
    invoke-virtual {p1}, Lorg/simplifiles/archive/ArchiveExtractionOptions;->getCancellationToken()Lorg/simplifiles/archive/CancellationToken;

    move-result-object p0

    invoke-interface {p0}, Lorg/simplifiles/archive/CancellationToken;->isCancellationRequested()Z

    move-result p0

    if-nez p0, :cond_0

    return-void

    .line 231
    :cond_0
    new-instance p0, Lorg/simplifiles/exception/ArchiveOperationCanceledException;

    invoke-direct {p0}, Lorg/simplifiles/exception/ArchiveOperationCanceledException;-><init>()V

    throw p0
.end method

.method private final unreadableFileReason()Ljava/lang/String;
    .locals 3

    .line 239
    iget-object v0, p0, Lorg/simplifiles/archive/ArchiveSource;->path:Lj$/nio/file/Path;

    const/4 v1, 0x0

    new-array v2, v1, [Lj$/nio/file/LinkOption;

    invoke-static {v0, v2}, Lj$/nio/file/Files;->exists(Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Z

    move-result v0

    if-nez v0, :cond_0

    const-string p0, "Archive file does not exist."

    return-object p0

    .line 240
    :cond_0
    iget-object p0, p0, Lorg/simplifiles/archive/ArchiveSource;->path:Lj$/nio/file/Path;

    new-array v0, v1, [Lj$/nio/file/LinkOption;

    invoke-static {p0, v0}, Lj$/nio/file/Files;->isRegularFile(Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Z

    move-result p0

    if-nez p0, :cond_1

    const-string p0, "Archive path is not a regular file."

    return-object p0

    :cond_1
    const/4 p0, 0x0

    return-object p0
.end method


# virtual methods
.method public equals(Ljava/lang/Object;)Z
    .locals 2

    if-eq p0, p1, :cond_1

    .line 264
    instance-of v0, p1, Lorg/simplifiles/archive/ArchiveSource;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lorg/simplifiles/archive/ArchiveSource;->path:Lj$/nio/file/Path;

    check-cast p1, Lorg/simplifiles/archive/ArchiveSource;

    iget-object v1, p1, Lorg/simplifiles/archive/ArchiveSource;->path:Lj$/nio/file/Path;

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object p0, p0, Lorg/simplifiles/archive/ArchiveSource;->policy:Lorg/simplifiles/archive/security/SecurityPolicy;

    iget-object p1, p1, Lorg/simplifiles/archive/ArchiveSource;->policy:Lorg/simplifiles/archive/security/SecurityPolicy;

    invoke-static {p0, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method public final extractTo(Lj$/nio/file/Path;)Lorg/simplifiles/archive/ExtractedArchive;
    .locals 1

    .line 122
    sget-object v0, Lorg/simplifiles/archive/ArchiveExtractionOptions;->Companion:Lorg/simplifiles/archive/ArchiveExtractionOptions$Companion;

    invoke-virtual {v0}, Lorg/simplifiles/archive/ArchiveExtractionOptions$Companion;->defaults()Lorg/simplifiles/archive/ArchiveExtractionOptions;

    move-result-object v0

    invoke-virtual {p0, p1, v0}, Lorg/simplifiles/archive/ArchiveSource;->extractTo(Lj$/nio/file/Path;Lorg/simplifiles/archive/ArchiveExtractionOptions;)Lorg/simplifiles/archive/ExtractedArchive;

    move-result-object p0

    return-object p0
.end method

.method public final extractTo(Lj$/nio/file/Path;Lorg/simplifiles/archive/ArchiveExtractionOptions;)Lorg/simplifiles/archive/ExtractedArchive;
    .locals 10

    .line 130
    invoke-direct {p0, p2}, Lorg/simplifiles/archive/ArchiveSource;->ensureNotCanceled(Lorg/simplifiles/archive/ArchiveExtractionOptions;)V

    .line 131
    invoke-virtual {p0}, Lorg/simplifiles/archive/ArchiveSource;->validate()Lorg/simplifiles/archive/ValidationReport;

    move-result-object v0

    .line 132
    invoke-virtual {v0}, Lorg/simplifiles/archive/ValidationReport;->isSafe()Z

    move-result v1

    if-eqz v1, :cond_3

    .line 135
    invoke-direct {p0, p2}, Lorg/simplifiles/archive/ArchiveSource;->ensureNotCanceled(Lorg/simplifiles/archive/ArchiveExtractionOptions;)V

    .line 137
    invoke-virtual {v0}, Lorg/simplifiles/archive/ValidationReport;->getFormat()Lorg/simplifiles/archive/ArchiveFormat;

    move-result-object v1

    const/4 v2, -0x1

    if-nez v1, :cond_0

    move v1, v2

    goto :goto_0

    :cond_0
    sget-object v3, Lorg/simplifiles/archive/ArchiveSource$WhenMappings;->$EnumSwitchMapping$0:[I

    invoke-virtual {v1}, Ljava/lang/Enum;->ordinal()I

    move-result v1

    aget v1, v3, v1

    :goto_0
    if-eq v1, v2, :cond_2

    const/4 v2, 0x1

    if-ne v1, v2, :cond_1

    .line 138
    sget-object v3, Lorg/simplifiles/internal/archive/zip/ZipArchiveExtractor;->INSTANCE:Lorg/simplifiles/internal/archive/zip/ZipArchiveExtractor;

    .line 139
    iget-object v4, p0, Lorg/simplifiles/archive/ArchiveSource;->path:Lj$/nio/file/Path;

    .line 141
    iget-object v6, p0, Lorg/simplifiles/archive/ArchiveSource;->policy:Lorg/simplifiles/archive/security/SecurityPolicy;

    const/4 v7, 0x0

    .line 143
    invoke-virtual {v0}, Lorg/simplifiles/archive/ValidationReport;->getEntries()Ljava/util/List;

    move-result-object v8

    move-object v5, p1

    move-object v9, p2

    .line 138
    invoke-virtual/range {v3 .. v9}, Lorg/simplifiles/internal/archive/zip/ZipArchiveExtractor;->extract(Lj$/nio/file/Path;Lj$/nio/file/Path;Lorg/simplifiles/archive/security/SecurityPolicy;ZLjava/util/List;Lorg/simplifiles/archive/ArchiveExtractionOptions;)Lorg/simplifiles/archive/ExtractedArchive;

    move-result-object p0

    return-object p0

    .line 137
    :cond_1
    invoke-static {}, Lkotlin/LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;->m()V

    const/4 p0, 0x0

    return-object p0

    .line 147
    :cond_2
    new-instance p0, Lorg/simplifiles/exception/ArchiveValidationException;

    invoke-direct {p0, v0}, Lorg/simplifiles/exception/ArchiveValidationException;-><init>(Lorg/simplifiles/archive/ValidationReport;)V

    throw p0

    .line 133
    :cond_3
    new-instance p0, Lorg/simplifiles/exception/ArchiveValidationException;

    invoke-direct {p0, v0}, Lorg/simplifiles/exception/ArchiveValidationException;-><init>(Lorg/simplifiles/archive/ValidationReport;)V

    throw p0
.end method

.method public final extractTo(Ljava/io/File;)Lorg/simplifiles/archive/ExtractedArchive;
    .locals 1

    .line 156
    invoke-virtual {p1}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object p1

    const/4 v0, 0x0

    new-array v0, v0, [Ljava/lang/String;

    invoke-static {p1, v0}, Lj$/nio/file/Paths;->get(Ljava/lang/String;[Ljava/lang/String;)Lj$/nio/file/Path;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/simplifiles/archive/ArchiveSource;->extractTo(Lj$/nio/file/Path;)Lorg/simplifiles/archive/ExtractedArchive;

    move-result-object p0

    return-object p0
.end method

.method public final extractToDirectory(Lj$/nio/file/Path;Lorg/simplifiles/archive/ArchiveExtractionOptions;)Lorg/simplifiles/files/SimpliDirectory;
    .locals 0

    .line 171
    invoke-virtual {p0, p1, p2}, Lorg/simplifiles/archive/ArchiveSource;->extractTo(Lj$/nio/file/Path;Lorg/simplifiles/archive/ArchiveExtractionOptions;)Lorg/simplifiles/archive/ExtractedArchive;

    move-result-object p0

    .line 172
    :try_start_0
    new-instance p1, Lorg/simplifiles/files/SimpliDirectory;

    invoke-virtual {p0}, Lorg/simplifiles/archive/ExtractedArchive;->getRoot()Lj$/nio/file/Path;

    move-result-object p2

    invoke-direct {p1, p2}, Lorg/simplifiles/files/SimpliDirectory;-><init>(Lj$/nio/file/Path;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    const/4 p2, 0x0

    .line 171
    invoke-static {p0, p2}, Lkotlin/jdk7/AutoCloseableKt;->closeFinally(Ljava/lang/AutoCloseable;Ljava/lang/Throwable;)V

    return-object p1

    :catchall_0
    move-exception p1

    :try_start_1
    throw p1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    :catchall_1
    move-exception p2

    invoke-static {p0, p1}, Lkotlin/jdk7/AutoCloseableKt;->closeFinally(Ljava/lang/AutoCloseable;Ljava/lang/Throwable;)V

    throw p2
.end method

.method public final extractToDirectory(Ljava/io/File;Lorg/simplifiles/archive/ArchiveExtractionOptions;)Lorg/simplifiles/files/SimpliDirectory;
    .locals 1

    .line 185
    invoke-virtual {p1}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object p1

    const/4 v0, 0x0

    new-array v0, v0, [Ljava/lang/String;

    invoke-static {p1, v0}, Lj$/nio/file/Paths;->get(Ljava/lang/String;[Ljava/lang/String;)Lj$/nio/file/Path;

    move-result-object p1

    invoke-virtual {p0, p1, p2}, Lorg/simplifiles/archive/ArchiveSource;->extractToDirectory(Lj$/nio/file/Path;Lorg/simplifiles/archive/ArchiveExtractionOptions;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object p0

    return-object p0
.end method

.method public hashCode()I
    .locals 1

    .line 266
    iget-object v0, p0, Lorg/simplifiles/archive/ArchiveSource;->path:Lj$/nio/file/Path;

    invoke-virtual {v0}, Ljava/lang/Object;->hashCode()I

    move-result v0

    mul-int/lit8 v0, v0, 0x1f

    iget-object p0, p0, Lorg/simplifiles/archive/ArchiveSource;->policy:Lorg/simplifiles/archive/security/SecurityPolicy;

    invoke-virtual {p0}, Lorg/simplifiles/archive/security/SecurityPolicy;->hashCode()I

    move-result p0

    add-int/2addr v0, p0

    return v0
.end method

.method public toString()Ljava/lang/String;
    .locals 2

    .line 268
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "ArchiveSource(path="

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v1, p0, Lorg/simplifiles/archive/ArchiveSource;->path:Lj$/nio/file/Path;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v1, ", policy="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p0, p0, Lorg/simplifiles/archive/ArchiveSource;->policy:Lorg/simplifiles/archive/security/SecurityPolicy;

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const/16 p0, 0x29

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public final validate()Lorg/simplifiles/archive/ValidationReport;
    .locals 12

    .line 61
    invoke-direct {p0}, Lorg/simplifiles/archive/ArchiveSource;->unreadableFileReason()Ljava/lang/String;

    move-result-object v0

    const-string v1, "archive.unreadable"

    if-eqz v0, :cond_0

    .line 62
    invoke-direct {p0, v1, v0}, Lorg/simplifiles/archive/ArchiveSource;->blockerReport(Ljava/lang/String;Ljava/lang/String;)Lorg/simplifiles/archive/ValidationReport;

    move-result-object p0

    return-object p0

    .line 66
    :cond_0
    :try_start_0
    sget-object v0, Lorg/simplifiles/internal/archive/ArchiveFormatDetector;->INSTANCE:Lorg/simplifiles/internal/archive/ArchiveFormatDetector;

    iget-object v2, p0, Lorg/simplifiles/archive/ArchiveSource;->path:Lj$/nio/file/Path;

    invoke-virtual {v0, v2}, Lorg/simplifiles/internal/archive/ArchiveFormatDetector;->detect(Lj$/nio/file/Path;)Lorg/simplifiles/archive/ArchiveFormat;

    move-result-object v4
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_1

    if-nez v4, :cond_1

    .line 73
    const-string v0, "archive.format.unsupported"

    .line 74
    const-string v1, "Unsupported archive format."

    .line 72
    invoke-direct {p0, v0, v1}, Lorg/simplifiles/archive/ArchiveSource;->blockerReport(Ljava/lang/String;Ljava/lang/String;)Lorg/simplifiles/archive/ValidationReport;

    move-result-object p0

    return-object p0

    .line 78
    :cond_1
    :try_start_1
    sget-object v0, Lorg/simplifiles/archive/ArchiveSource$WhenMappings;->$EnumSwitchMapping$0:[I

    invoke-virtual {v4}, Ljava/lang/Enum;->ordinal()I

    move-result v1

    aget v0, v0, v1

    const/4 v1, 0x1

    if-ne v0, v1, :cond_2

    .line 79
    sget-object v0, Lorg/simplifiles/internal/archive/zip/ZipArchiveReader;->INSTANCE:Lorg/simplifiles/internal/archive/zip/ZipArchiveReader;

    iget-object v1, p0, Lorg/simplifiles/archive/ArchiveSource;->path:Lj$/nio/file/Path;

    invoke-virtual {v0, v1}, Lorg/simplifiles/internal/archive/zip/ZipArchiveReader;->inspect(Lj$/nio/file/Path;)Lorg/simplifiles/archive/ArchiveInspection;

    move-result-object v0
    :try_end_1
    .catch Lorg/simplifiles/exception/CorruptedArchiveException; {:try_start_1 .. :try_end_1} :catch_0

    .line 94
    sget-object v1, Lorg/simplifiles/internal/archive/ArchiveValidator;->INSTANCE:Lorg/simplifiles/internal/archive/ArchiveValidator;

    iget-object p0, p0, Lorg/simplifiles/archive/ArchiveSource;->policy:Lorg/simplifiles/archive/security/SecurityPolicy;

    invoke-virtual {v1, v0, p0}, Lorg/simplifiles/internal/archive/ArchiveValidator;->validate(Lorg/simplifiles/archive/ArchiveInspection;Lorg/simplifiles/archive/security/SecurityPolicy;)Lorg/simplifiles/archive/ValidationReport;

    move-result-object p0

    return-object p0

    :catch_0
    move-exception v0

    move-object p0, v0

    goto :goto_0

    .line 78
    :cond_2
    :try_start_2
    new-instance p0, Lkotlin/NoWhenBranchMatchedException;

    invoke-direct {p0}, Lkotlin/NoWhenBranchMatchedException;-><init>()V

    throw p0
    :try_end_2
    .catch Lorg/simplifiles/exception/CorruptedArchiveException; {:try_start_2 .. :try_end_2} :catch_0

    .line 82
    :goto_0
    new-instance v3, Lorg/simplifiles/archive/ValidationReport;

    .line 85
    new-instance v5, Lorg/simplifiles/archive/ArchiveIssue;

    .line 86
    sget-object v6, Lorg/simplifiles/archive/ArchiveIssueSeverity;->BLOCKER:Lorg/simplifiles/archive/ArchiveIssueSeverity;

    .line 88
    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object p0

    if-nez p0, :cond_3

    const-string p0, "Archive is corrupted."

    :cond_3
    move-object v8, p0

    const/16 v10, 0x8

    const/4 v11, 0x0

    .line 85
    const-string v7, "archive.corrupted"

    const/4 v9, 0x0

    invoke-direct/range {v5 .. v11}, Lorg/simplifiles/archive/ArchiveIssue;-><init>(Lorg/simplifiles/archive/ArchiveIssueSeverity;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 84
    invoke-static {v5}, Lkotlin/collections/CollectionsKt;->listOf(Ljava/lang/Object;)Ljava/util/List;

    move-result-object v6

    const/4 v7, 0x2

    const/4 v8, 0x0

    const/4 v5, 0x0

    .line 82
    invoke-direct/range {v3 .. v8}, Lorg/simplifiles/archive/ValidationReport;-><init>(Lorg/simplifiles/archive/ArchiveFormat;Ljava/util/List;Ljava/util/List;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-object v3

    :catch_1
    move-exception v0

    .line 70
    invoke-virtual {v0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_4

    const-string v0, "Archive cannot be read."

    .line 68
    :cond_4
    invoke-direct {p0, v1, v0}, Lorg/simplifiles/archive/ArchiveSource;->blockerReport(Ljava/lang/String;Ljava/lang/String;)Lorg/simplifiles/archive/ValidationReport;

    move-result-object p0

    return-object p0
.end method

.method public final withPolicy(Lorg/simplifiles/archive/security/SecurityPolicy;)Lorg/simplifiles/archive/ArchiveSource;
    .locals 1

    .line 34
    new-instance v0, Lorg/simplifiles/archive/ArchiveSource;

    iget-object p0, p0, Lorg/simplifiles/archive/ArchiveSource;->path:Lj$/nio/file/Path;

    invoke-direct {v0, p0, p1}, Lorg/simplifiles/archive/ArchiveSource;-><init>(Lj$/nio/file/Path;Lorg/simplifiles/archive/security/SecurityPolicy;)V

    return-object v0
.end method
