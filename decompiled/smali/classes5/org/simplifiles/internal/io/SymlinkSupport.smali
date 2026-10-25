.class public final Lorg/simplifiles/internal/io/SymlinkSupport;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/simplifiles/internal/io/SymlinkSupport$WhenMappings;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\u0008\u00c0\u0002\u0018\u00002\u00020\u0001B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u001d\u0010\t\u001a\u00020\u00082\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\u0008\t\u0010\n\u00a8\u0006\u000b"
    }
    d2 = {
        "Lorg/simplifiles/internal/io/SymlinkSupport;",
        "",
        "<init>",
        "()V",
        "j$/nio/file/Path",
        "path",
        "Lorg/simplifiles/files/SymlinkPolicy;",
        "policy",
        "Lorg/simplifiles/internal/io/SymlinkDecision;",
        "decide",
        "(Lj$/nio/file/Path;Lorg/simplifiles/files/SymlinkPolicy;)Lorg/simplifiles/internal/io/SymlinkDecision;",
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


# static fields
.field public static final INSTANCE:Lorg/simplifiles/internal/io/SymlinkSupport;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lorg/simplifiles/internal/io/SymlinkSupport;

    invoke-direct {v0}, Lorg/simplifiles/internal/io/SymlinkSupport;-><init>()V

    sput-object v0, Lorg/simplifiles/internal/io/SymlinkSupport;->INSTANCE:Lorg/simplifiles/internal/io/SymlinkSupport;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 13
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final decide(Lj$/nio/file/Path;Lorg/simplifiles/files/SymlinkPolicy;)Lorg/simplifiles/internal/io/SymlinkDecision;
    .locals 0

    .line 18
    invoke-static {p1}, Lj$/nio/file/Files;->isSymbolicLink(Lj$/nio/file/Path;)Z

    move-result p0

    if-nez p0, :cond_0

    .line 19
    sget-object p0, Lorg/simplifiles/internal/io/SymlinkDecision;->INCLUDE:Lorg/simplifiles/internal/io/SymlinkDecision;

    return-object p0

    .line 22
    :cond_0
    sget-object p0, Lorg/simplifiles/internal/io/SymlinkSupport$WhenMappings;->$EnumSwitchMapping$0:[I

    invoke-virtual {p2}, Ljava/lang/Enum;->ordinal()I

    move-result p1

    aget p0, p0, p1

    const/4 p1, 0x1

    if-eq p0, p1, :cond_3

    const/4 p1, 0x2

    if-eq p0, p1, :cond_2

    const/4 p1, 0x3

    if-ne p0, p1, :cond_1

    .line 25
    sget-object p0, Lorg/simplifiles/internal/io/SymlinkDecision;->FAIL:Lorg/simplifiles/internal/io/SymlinkDecision;

    return-object p0

    .line 22
    :cond_1
    invoke-static {}, Lkotlin/LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;->m()V

    const/4 p0, 0x0

    return-object p0

    .line 24
    :cond_2
    sget-object p0, Lorg/simplifiles/internal/io/SymlinkDecision;->SKIP:Lorg/simplifiles/internal/io/SymlinkDecision;

    return-object p0

    .line 23
    :cond_3
    sget-object p0, Lorg/simplifiles/internal/io/SymlinkDecision;->INCLUDE:Lorg/simplifiles/internal/io/SymlinkDecision;

    return-object p0
.end method
