.class public final Lorg/simplifiles/files/DirectoryTransferOptions;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/simplifiles/files/DirectoryTransferOptions$Companion;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u000e\n\u0002\u0008\u000e\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B1\u0008\u0007\u0012\u0008\u0008\u0002\u0010\u0003\u001a\u00020\u0002\u0012\u0008\u0008\u0002\u0010\u0005\u001a\u00020\u0004\u0012\u0008\u0008\u0002\u0010\u0006\u001a\u00020\u0004\u0012\u0008\u0008\u0002\u0010\u0008\u001a\u00020\u0007\u00a2\u0006\u0004\u0008\t\u0010\nJ\u000f\u0010\u000c\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\u0008\u000c\u0010\rR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\u000c\n\u0004\u0008\u0003\u0010\u000e\u001a\u0004\u0008\u000f\u0010\u0010R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\u000c\n\u0004\u0008\u0005\u0010\u0011\u001a\u0004\u0008\u0012\u0010\u0013R\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\u000c\n\u0004\u0008\u0006\u0010\u0011\u001a\u0004\u0008\u0014\u0010\u0013R\u0017\u0010\u0008\u001a\u00020\u00078\u0006\u00a2\u0006\u000c\n\u0004\u0008\u0008\u0010\u0015\u001a\u0004\u0008\u0016\u0010\u0017\u00a8\u0006\u0019"
    }
    d2 = {
        "Lorg/simplifiles/files/DirectoryTransferOptions;",
        "",
        "Lorg/simplifiles/files/DirectoryOverwritePolicy;",
        "overwritePolicy",
        "",
        "maxFiles",
        "maxBytes",
        "Lorg/simplifiles/files/SymlinkPolicy;",
        "symlinkPolicy",
        "<init>",
        "(Lorg/simplifiles/files/DirectoryOverwritePolicy;JJLorg/simplifiles/files/SymlinkPolicy;)V",
        "",
        "toString",
        "()Ljava/lang/String;",
        "Lorg/simplifiles/files/DirectoryOverwritePolicy;",
        "getOverwritePolicy",
        "()Lorg/simplifiles/files/DirectoryOverwritePolicy;",
        "J",
        "getMaxFiles",
        "()J",
        "getMaxBytes",
        "Lorg/simplifiles/files/SymlinkPolicy;",
        "getSymlinkPolicy",
        "()Lorg/simplifiles/files/SymlinkPolicy;",
        "Companion",
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
        "SMAP\nDirectoryTransferOptions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DirectoryTransferOptions.kt\norg/simplifiles/files/DirectoryTransferOptions\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,78:1\n1#2:79\n*E\n"
    }
.end annotation


# static fields
.field public static final Companion:Lorg/simplifiles/files/DirectoryTransferOptions$Companion;


# instance fields
.field private final maxBytes:J

.field private final maxFiles:J

.field private final overwritePolicy:Lorg/simplifiles/files/DirectoryOverwritePolicy;

.field private final symlinkPolicy:Lorg/simplifiles/files/SymlinkPolicy;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lorg/simplifiles/files/DirectoryTransferOptions$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lorg/simplifiles/files/DirectoryTransferOptions$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lorg/simplifiles/files/DirectoryTransferOptions;->Companion:Lorg/simplifiles/files/DirectoryTransferOptions$Companion;

    return-void
.end method

.method public constructor <init>(Lorg/simplifiles/files/DirectoryOverwritePolicy;JJLorg/simplifiles/files/SymlinkPolicy;)V
    .locals 0
    .annotation build Lkotlin/jvm/JvmOverloads;
    .end annotation

    .line 8
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 9
    iput-object p1, p0, Lorg/simplifiles/files/DirectoryTransferOptions;->overwritePolicy:Lorg/simplifiles/files/DirectoryOverwritePolicy;

    .line 10
    iput-wide p2, p0, Lorg/simplifiles/files/DirectoryTransferOptions;->maxFiles:J

    .line 11
    iput-wide p4, p0, Lorg/simplifiles/files/DirectoryTransferOptions;->maxBytes:J

    .line 12
    iput-object p6, p0, Lorg/simplifiles/files/DirectoryTransferOptions;->symlinkPolicy:Lorg/simplifiles/files/SymlinkPolicy;

    const-wide/16 p0, 0x0

    cmp-long p2, p2, p0

    if-ltz p2, :cond_1

    cmp-long p0, p4, p0

    if-ltz p0, :cond_0

    return-void

    .line 16
    :cond_0
    const-string p0, "maxBytes must not be negative."

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    throw p0

    .line 15
    :cond_1
    const-string p0, "maxFiles must not be negative."

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    throw p0
.end method

.method public synthetic constructor <init>(Lorg/simplifiles/files/DirectoryOverwritePolicy;JJLorg/simplifiles/files/SymlinkPolicy;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 2

    and-int/lit8 p8, p7, 0x1

    if-eqz p8, :cond_0

    .line 9
    sget-object p1, Lorg/simplifiles/files/DirectoryOverwritePolicy;->ERROR:Lorg/simplifiles/files/DirectoryOverwritePolicy;

    :cond_0
    and-int/lit8 p8, p7, 0x2

    const-wide v0, 0x7fffffffffffffffL

    if-eqz p8, :cond_1

    move-wide p2, v0

    :cond_1
    and-int/lit8 p8, p7, 0x4

    if-eqz p8, :cond_2

    move-wide p4, v0

    :cond_2
    and-int/lit8 p7, p7, 0x8

    if-eqz p7, :cond_3

    .line 12
    sget-object p6, Lorg/simplifiles/files/SymlinkPolicy;->SKIP:Lorg/simplifiles/files/SymlinkPolicy;

    :cond_3
    move-object p8, p6

    move-wide p6, p4

    move-wide p4, p2

    move-object p2, p0

    move-object p3, p1

    .line 8
    invoke-direct/range {p2 .. p8}, Lorg/simplifiles/files/DirectoryTransferOptions;-><init>(Lorg/simplifiles/files/DirectoryOverwritePolicy;JJLorg/simplifiles/files/SymlinkPolicy;)V

    return-void
.end method


# virtual methods
.method public final getMaxBytes()J
    .locals 2

    .line 11
    iget-wide v0, p0, Lorg/simplifiles/files/DirectoryTransferOptions;->maxBytes:J

    return-wide v0
.end method

.method public final getMaxFiles()J
    .locals 2

    .line 10
    iget-wide v0, p0, Lorg/simplifiles/files/DirectoryTransferOptions;->maxFiles:J

    return-wide v0
.end method

.method public final getOverwritePolicy()Lorg/simplifiles/files/DirectoryOverwritePolicy;
    .locals 0

    .line 9
    iget-object p0, p0, Lorg/simplifiles/files/DirectoryTransferOptions;->overwritePolicy:Lorg/simplifiles/files/DirectoryOverwritePolicy;

    return-object p0
.end method

.method public final getSymlinkPolicy()Lorg/simplifiles/files/SymlinkPolicy;
    .locals 0

    .line 12
    iget-object p0, p0, Lorg/simplifiles/files/DirectoryTransferOptions;->symlinkPolicy:Lorg/simplifiles/files/SymlinkPolicy;

    return-object p0
.end method

.method public toString()Ljava/lang/String;
    .locals 3

    .line 35
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "DirectoryTransferOptions(overwritePolicy="

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v1, p0, Lorg/simplifiles/files/DirectoryTransferOptions;->overwritePolicy:Lorg/simplifiles/files/DirectoryOverwritePolicy;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v1, ", maxFiles="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v1, p0, Lorg/simplifiles/files/DirectoryTransferOptions;->maxFiles:J

    invoke-virtual {v0, v1, v2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v1, ", maxBytes="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 36
    iget-wide v1, p0, Lorg/simplifiles/files/DirectoryTransferOptions;->maxBytes:J

    .line 35
    invoke-virtual {v0, v1, v2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    .line 36
    const-string v1, ", symlinkPolicy="

    .line 35
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 36
    iget-object p0, p0, Lorg/simplifiles/files/DirectoryTransferOptions;->symlinkPolicy:Lorg/simplifiles/files/SymlinkPolicy;

    .line 35
    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const/16 p0, 0x29

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
