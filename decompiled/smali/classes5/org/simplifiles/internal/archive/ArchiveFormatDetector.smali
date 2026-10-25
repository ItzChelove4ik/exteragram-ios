.class public final Lorg/simplifiles/internal/archive/ArchiveFormatDetector;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0008\u0004\u0008\u00c0\u0002\u0018\u00002\u00020\u0001B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\u0008\u0007\u0010\u0008J\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\n\u001a\u00020\tH\u0000\u00a2\u0006\u0004\u0008\u000c\u0010\r\u00a8\u0006\u000f"
    }
    d2 = {
        "Lorg/simplifiles/internal/archive/ArchiveFormatDetector;",
        "",
        "<init>",
        "()V",
        "j$/nio/file/Path",
        "path",
        "Lorg/simplifiles/archive/ArchiveFormat;",
        "detect",
        "(Lj$/nio/file/Path;)Lorg/simplifiles/archive/ArchiveFormat;",
        "Ljava/io/InputStream;",
        "input",
        "",
        "readSignature$simplifiles",
        "(Ljava/io/InputStream;)[B",
        "readSignature",
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
        "SMAP\nArchiveFormatDetector.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ArchiveFormatDetector.kt\norg/simplifiles/internal/archive/ArchiveFormatDetector\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,55:1\n1#2:56\n*E\n"
    }
.end annotation


# static fields
.field public static final INSTANCE:Lorg/simplifiles/internal/archive/ArchiveFormatDetector;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lorg/simplifiles/internal/archive/ArchiveFormatDetector;

    invoke-direct {v0}, Lorg/simplifiles/internal/archive/ArchiveFormatDetector;-><init>()V

    sput-object v0, Lorg/simplifiles/internal/archive/ArchiveFormatDetector;->INSTANCE:Lorg/simplifiles/internal/archive/ArchiveFormatDetector;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 8
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final detect(Lj$/nio/file/Path;)Lorg/simplifiles/archive/ArchiveFormat;
    .locals 5

    const/4 v0, 0x0

    .line 12
    new-array v1, v0, [Lj$/nio/file/OpenOption;

    invoke-static {p1, v1}, Lj$/nio/file/Files;->newInputStream(Lj$/nio/file/Path;[Lj$/nio/file/OpenOption;)Ljava/io/InputStream;

    move-result-object p1

    :try_start_0
    invoke-virtual {p0, p1}, Lorg/simplifiles/internal/archive/ArchiveFormatDetector;->readSignature$simplifiles(Ljava/io/InputStream;)[B

    move-result-object p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    const/4 v1, 0x0

    invoke-static {p1, v1}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    if-nez p0, :cond_0

    return-object v1

    .line 14
    :cond_0
    aget-byte p1, p0, v0

    and-int/lit16 p1, p1, 0xff

    const/4 v0, 0x1

    .line 15
    aget-byte v0, p0, v0

    and-int/lit16 v0, v0, 0xff

    const/4 v2, 0x2

    .line 16
    aget-byte v2, p0, v2

    and-int/lit16 v2, v2, 0xff

    const/4 v3, 0x3

    .line 17
    aget-byte p0, p0, v3

    and-int/lit16 p0, p0, 0xff

    const/16 v4, 0x50

    if-ne p1, v4, :cond_4

    const/16 p1, 0x4b

    if-ne v0, p1, :cond_4

    if-ne v2, v3, :cond_1

    const/4 p1, 0x4

    if-eq p0, p1, :cond_3

    :cond_1
    const/4 p1, 0x5

    if-ne v2, p1, :cond_2

    const/4 p1, 0x6

    if-eq p0, p1, :cond_3

    :cond_2
    const/4 p1, 0x7

    if-ne v2, p1, :cond_4

    const/16 p1, 0x8

    if-ne p0, p1, :cond_4

    .line 28
    :cond_3
    sget-object p0, Lorg/simplifiles/archive/ArchiveFormat;->ZIP:Lorg/simplifiles/archive/ArchiveFormat;

    return-object p0

    :cond_4
    return-object v1

    :catchall_0
    move-exception p0

    .line 12
    :try_start_1
    throw p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    :catchall_1
    move-exception v0

    invoke-static {p1, p0}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    throw v0
.end method

.method public final readSignature$simplifiles(Ljava/io/InputStream;)[B
    .locals 3

    const/4 p0, 0x4

    .line 41
    new-array v0, p0, [B

    const/4 v1, 0x0

    :goto_0
    if-ge v1, p0, :cond_1

    rsub-int/lit8 v2, v1, 0x4

    .line 45
    invoke-virtual {p1, v0, v1, v2}, Ljava/io/InputStream;->read([BII)I

    move-result v2

    if-gez v2, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    add-int/2addr v1, v2

    goto :goto_0

    :cond_1
    return-object v0
.end method
