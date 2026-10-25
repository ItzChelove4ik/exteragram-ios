.class public final Lorg/simplifiles/archive/ArchiveSaveOptions;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/simplifiles/archive/ArchiveSaveOptions$Builder;,
        Lorg/simplifiles/archive/ArchiveSaveOptions$Companion;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0008\u0003\n\u0002\u0010\u000e\n\u0002\u0008\u001b\u0018\u0000 ,2\u00020\u0001:\u0002,-B[\u0008\u0007\u0012\n\u0008\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0008\u0008\u0002\u0010\u0005\u001a\u00020\u0004\u0012\u0008\u0008\u0002\u0010\u0007\u001a\u00020\u0006\u0012\u0008\u0008\u0002\u0010\t\u001a\u00020\u0008\u0012\u0008\u0008\u0002\u0010\n\u001a\u00020\u0006\u0012\u0008\u0008\u0002\u0010\u000c\u001a\u00020\u000b\u0012\u0008\u0008\u0002\u0010\u000e\u001a\u00020\r\u0012\u0008\u0008\u0002\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\u0008\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016\u00a2\u0006\u0004\u0008\u0014\u0010\u0015R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\u000c\n\u0004\u0008\u0005\u0010\u0016\u001a\u0004\u0008\u0017\u0010\u0018R\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\u000c\n\u0004\u0008\u0007\u0010\u0019\u001a\u0004\u0008\u001a\u0010\u001bR\u0017\u0010\t\u001a\u00020\u00088\u0006\u00a2\u0006\u000c\n\u0004\u0008\t\u0010\u001c\u001a\u0004\u0008\u001d\u0010\u001eR\u0017\u0010\n\u001a\u00020\u00068\u0006\u00a2\u0006\u000c\n\u0004\u0008\n\u0010\u0019\u001a\u0004\u0008\u001f\u0010\u001bR\u0017\u0010\u000c\u001a\u00020\u000b8\u0006\u00a2\u0006\u000c\n\u0004\u0008\u000c\u0010 \u001a\u0004\u0008!\u0010\"R\u0017\u0010\u000e\u001a\u00020\r8\u0006\u00a2\u0006\u000c\n\u0004\u0008\u000e\u0010#\u001a\u0004\u0008$\u0010%R\u0017\u0010\u0010\u001a\u00020\u000f8\u0006\u00a2\u0006\u000c\n\u0004\u0008\u0010\u0010&\u001a\u0004\u0008\'\u0010(R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006\u00a2\u0006\u000c\n\u0004\u0008\u0003\u0010)\u001a\u0004\u0008*\u0010+\u00a8\u0006."
    }
    d2 = {
        "Lorg/simplifiles/archive/ArchiveSaveOptions;",
        "",
        "Lorg/simplifiles/archive/ArchiveSaveProgressListener;",
        "progressListener",
        "Lorg/simplifiles/archive/CancellationToken;",
        "cancellationToken",
        "",
        "bufferSize",
        "Lorg/simplifiles/files/OverwritePolicy;",
        "overwritePolicy",
        "compressionLevel",
        "Lorg/simplifiles/archive/ArchiveEntryFilter;",
        "entryFilter",
        "Lorg/simplifiles/files/SymlinkPolicy;",
        "symlinkPolicy",
        "",
        "entryTimestamp",
        "<init>",
        "(Lorg/simplifiles/archive/ArchiveSaveProgressListener;Lorg/simplifiles/archive/CancellationToken;ILorg/simplifiles/files/OverwritePolicy;ILorg/simplifiles/archive/ArchiveEntryFilter;Lorg/simplifiles/files/SymlinkPolicy;J)V",
        "",
        "toString",
        "()Ljava/lang/String;",
        "Lorg/simplifiles/archive/CancellationToken;",
        "getCancellationToken",
        "()Lorg/simplifiles/archive/CancellationToken;",
        "I",
        "getBufferSize",
        "()I",
        "Lorg/simplifiles/files/OverwritePolicy;",
        "getOverwritePolicy",
        "()Lorg/simplifiles/files/OverwritePolicy;",
        "getCompressionLevel",
        "Lorg/simplifiles/archive/ArchiveEntryFilter;",
        "getEntryFilter",
        "()Lorg/simplifiles/archive/ArchiveEntryFilter;",
        "Lorg/simplifiles/files/SymlinkPolicy;",
        "getSymlinkPolicy",
        "()Lorg/simplifiles/files/SymlinkPolicy;",
        "J",
        "getEntryTimestamp",
        "()J",
        "Lorg/simplifiles/archive/ArchiveSaveProgressListener;",
        "getProgressListener",
        "()Lorg/simplifiles/archive/ArchiveSaveProgressListener;",
        "Companion",
        "Builder",
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
        "SMAP\nArchiveSaveOptions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ArchiveSaveOptions.kt\norg/simplifiles/archive/ArchiveSaveOptions\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,133:1\n1#2:134\n*E\n"
    }
.end annotation


# static fields
.field public static final Companion:Lorg/simplifiles/archive/ArchiveSaveOptions$Companion;


# instance fields
.field private final bufferSize:I

.field private final cancellationToken:Lorg/simplifiles/archive/CancellationToken;

.field private final compressionLevel:I

.field private final entryFilter:Lorg/simplifiles/archive/ArchiveEntryFilter;

.field private final entryTimestamp:J

.field private final overwritePolicy:Lorg/simplifiles/files/OverwritePolicy;

.field private final symlinkPolicy:Lorg/simplifiles/files/SymlinkPolicy;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lorg/simplifiles/archive/ArchiveSaveOptions$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lorg/simplifiles/archive/ArchiveSaveOptions$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lorg/simplifiles/archive/ArchiveSaveOptions;->Companion:Lorg/simplifiles/archive/ArchiveSaveOptions$Companion;

    return-void
.end method

.method public constructor <init>(Lorg/simplifiles/archive/ArchiveSaveProgressListener;Lorg/simplifiles/archive/CancellationToken;ILorg/simplifiles/files/OverwritePolicy;ILorg/simplifiles/archive/ArchiveEntryFilter;Lorg/simplifiles/files/SymlinkPolicy;J)V
    .locals 0
    .annotation build Lkotlin/jvm/JvmOverloads;
    .end annotation

    .line 14
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 16
    iput-object p2, p0, Lorg/simplifiles/archive/ArchiveSaveOptions;->cancellationToken:Lorg/simplifiles/archive/CancellationToken;

    .line 17
    iput p3, p0, Lorg/simplifiles/archive/ArchiveSaveOptions;->bufferSize:I

    .line 18
    iput-object p4, p0, Lorg/simplifiles/archive/ArchiveSaveOptions;->overwritePolicy:Lorg/simplifiles/files/OverwritePolicy;

    .line 19
    iput p5, p0, Lorg/simplifiles/archive/ArchiveSaveOptions;->compressionLevel:I

    .line 20
    iput-object p6, p0, Lorg/simplifiles/archive/ArchiveSaveOptions;->entryFilter:Lorg/simplifiles/archive/ArchiveEntryFilter;

    .line 21
    iput-object p7, p0, Lorg/simplifiles/archive/ArchiveSaveOptions;->symlinkPolicy:Lorg/simplifiles/files/SymlinkPolicy;

    .line 22
    iput-wide p8, p0, Lorg/simplifiles/archive/ArchiveSaveOptions;->entryTimestamp:J

    if-lez p3, :cond_3

    const/4 p0, -0x1

    if-gt p0, p5, :cond_2

    const/16 p0, 0xa

    if-ge p5, p0, :cond_2

    const-wide/16 p0, -0x1

    cmp-long p0, p8, p0

    if-eqz p0, :cond_1

    const-wide/16 p0, 0x0

    cmp-long p0, p8, p0

    if-ltz p0, :cond_0

    goto :goto_0

    .line 30
    :cond_0
    const-string p0, "entryTimestamp must be PRESERVE_SOURCE_TIMESTAMP or a non-negative epoch millisecond value."

    .line 29
    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    throw p0

    :cond_1
    :goto_0
    return-void

    .line 27
    :cond_2
    const-string p0, "compressionLevel must be between -1 and 9."

    .line 26
    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    throw p0

    .line 25
    :cond_3
    const-string p0, "bufferSize must be positive."

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    throw p0
.end method

.method public synthetic constructor <init>(Lorg/simplifiles/archive/ArchiveSaveProgressListener;Lorg/simplifiles/archive/CancellationToken;ILorg/simplifiles/files/OverwritePolicy;ILorg/simplifiles/archive/ArchiveEntryFilter;Lorg/simplifiles/files/SymlinkPolicy;JILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    and-int/lit8 p11, p10, 0x1

    if-eqz p11, :cond_0

    const/4 p1, 0x0

    :cond_0
    and-int/lit8 p11, p10, 0x2

    if-eqz p11, :cond_1

    .line 16
    sget-object p2, Lorg/simplifiles/archive/CancellationToken;->Companion:Lorg/simplifiles/archive/CancellationToken$Companion;

    invoke-virtual {p2}, Lorg/simplifiles/archive/CancellationToken$Companion;->none()Lorg/simplifiles/archive/CancellationToken;

    move-result-object p2

    :cond_1
    and-int/lit8 p11, p10, 0x4

    if-eqz p11, :cond_2

    const/high16 p3, 0x10000

    :cond_2
    and-int/lit8 p11, p10, 0x8

    if-eqz p11, :cond_3

    .line 18
    sget-object p4, Lorg/simplifiles/files/OverwritePolicy;->ERROR:Lorg/simplifiles/files/OverwritePolicy;

    :cond_3
    and-int/lit8 p11, p10, 0x10

    if-eqz p11, :cond_4

    const/4 p5, -0x1

    :cond_4
    and-int/lit8 p11, p10, 0x20

    if-eqz p11, :cond_5

    .line 20
    sget-object p6, Lorg/simplifiles/archive/ArchiveEntryFilter;->Companion:Lorg/simplifiles/archive/ArchiveEntryFilter$Companion;

    invoke-virtual {p6}, Lorg/simplifiles/archive/ArchiveEntryFilter$Companion;->includeAll()Lorg/simplifiles/archive/ArchiveEntryFilter;

    move-result-object p6

    :cond_5
    and-int/lit8 p11, p10, 0x40

    if-eqz p11, :cond_6

    .line 21
    sget-object p7, Lorg/simplifiles/files/SymlinkPolicy;->SKIP:Lorg/simplifiles/files/SymlinkPolicy;

    :cond_6
    and-int/lit16 p10, p10, 0x80

    if-eqz p10, :cond_7

    const-wide/16 p8, -0x1

    :cond_7
    move-wide p10, p8

    move-object p8, p6

    move-object p9, p7

    move-object p6, p4

    move p7, p5

    move-object p4, p2

    move p5, p3

    move-object p2, p0

    move-object p3, p1

    .line 14
    invoke-direct/range {p2 .. p11}, Lorg/simplifiles/archive/ArchiveSaveOptions;-><init>(Lorg/simplifiles/archive/ArchiveSaveProgressListener;Lorg/simplifiles/archive/CancellationToken;ILorg/simplifiles/files/OverwritePolicy;ILorg/simplifiles/archive/ArchiveEntryFilter;Lorg/simplifiles/files/SymlinkPolicy;J)V

    return-void
.end method


# virtual methods
.method public final getBufferSize()I
    .locals 0

    .line 17
    iget p0, p0, Lorg/simplifiles/archive/ArchiveSaveOptions;->bufferSize:I

    return p0
.end method

.method public final getCancellationToken()Lorg/simplifiles/archive/CancellationToken;
    .locals 0

    .line 16
    iget-object p0, p0, Lorg/simplifiles/archive/ArchiveSaveOptions;->cancellationToken:Lorg/simplifiles/archive/CancellationToken;

    return-object p0
.end method

.method public final getCompressionLevel()I
    .locals 0

    .line 19
    iget p0, p0, Lorg/simplifiles/archive/ArchiveSaveOptions;->compressionLevel:I

    return p0
.end method

.method public final getEntryFilter()Lorg/simplifiles/archive/ArchiveEntryFilter;
    .locals 0

    .line 20
    iget-object p0, p0, Lorg/simplifiles/archive/ArchiveSaveOptions;->entryFilter:Lorg/simplifiles/archive/ArchiveEntryFilter;

    return-object p0
.end method

.method public final getEntryTimestamp()J
    .locals 2

    .line 22
    iget-wide v0, p0, Lorg/simplifiles/archive/ArchiveSaveOptions;->entryTimestamp:J

    return-wide v0
.end method

.method public final getOverwritePolicy()Lorg/simplifiles/files/OverwritePolicy;
    .locals 0

    .line 18
    iget-object p0, p0, Lorg/simplifiles/archive/ArchiveSaveOptions;->overwritePolicy:Lorg/simplifiles/files/OverwritePolicy;

    return-object p0
.end method

.method public final getProgressListener()Lorg/simplifiles/archive/ArchiveSaveProgressListener;
    .locals 0

    const/4 p0, 0x0

    return-object p0
.end method

.method public final getSymlinkPolicy()Lorg/simplifiles/files/SymlinkPolicy;
    .locals 0

    .line 21
    iget-object p0, p0, Lorg/simplifiles/archive/ArchiveSaveOptions;->symlinkPolicy:Lorg/simplifiles/files/SymlinkPolicy;

    return-object p0
.end method

.method public toString()Ljava/lang/String;
    .locals 3

    .line 60
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "ArchiveSaveOptions(bufferSize="

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget v1, p0, Lorg/simplifiles/archive/ArchiveSaveOptions;->bufferSize:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, ", overwritePolicy="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lorg/simplifiles/archive/ArchiveSaveOptions;->overwritePolicy:Lorg/simplifiles/files/OverwritePolicy;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v1, ", compressionLevel="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 61
    iget v1, p0, Lorg/simplifiles/archive/ArchiveSaveOptions;->compressionLevel:I

    .line 60
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    .line 61
    const-string v1, ", symlinkPolicy="

    .line 60
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 61
    iget-object v1, p0, Lorg/simplifiles/archive/ArchiveSaveOptions;->symlinkPolicy:Lorg/simplifiles/files/SymlinkPolicy;

    .line 60
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    .line 61
    const-string v1, ", entryTimestamp="

    .line 60
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 62
    iget-wide v1, p0, Lorg/simplifiles/archive/ArchiveSaveOptions;->entryTimestamp:J

    .line 60
    invoke-virtual {v0, v1, v2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    .line 62
    const-string p0, ", hasProgressListener="

    .line 60
    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/4 p0, 0x0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const/16 p0, 0x29

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
