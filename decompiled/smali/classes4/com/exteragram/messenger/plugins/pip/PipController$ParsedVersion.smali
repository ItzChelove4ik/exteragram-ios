.class final Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/plugins/pip/PipController;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "ParsedVersion"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0008\r\n\u0002\u0010\u000b\n\u0002\u0008\u0004\u0008\u0082\u0008\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000c\u0010\u0006\u001a\u0008\u0012\u0004\u0012\u00020\u00050\u0007\u00a2\u0006\u0004\u0008\u0008\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\u0012\u001a\u0008\u0012\u0004\u0012\u00020\u00050\u0007H\u00c6\u0003J-\u0010\u0013\u001a\u00020\u00002\u0008\u0008\u0002\u0010\u0002\u001a\u00020\u00032\u0008\u0008\u0002\u0010\u0004\u001a\u00020\u00052\u000e\u0008\u0002\u0010\u0006\u001a\u0008\u0012\u0004\u0012\u00020\u00050\u0007H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\u0008\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u000c\u0010\rR\u0017\u0010\u0006\u001a\u0008\u0012\u0004\u0012\u00020\u00050\u0007\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u000e\u0010\u000f\u00a8\u0006\u0019"
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;",
        "",
        "epoch",
        "",
        "publicVersion",
        "",
        "parts",
        "",
        "<init>",
        "(ILjava/lang/String;Ljava/util/List;)V",
        "getEpoch",
        "()I",
        "getPublicVersion",
        "()Ljava/lang/String;",
        "getParts",
        "()Ljava/util/List;",
        "component1",
        "component2",
        "component3",
        "copy",
        "equals",
        "",
        "other",
        "hashCode",
        "toString",
        "TMessagesProj"
    }
    k = 0x1
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation


# instance fields
.field private final epoch:I

.field private final parts:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private final publicVersion:Ljava/lang/String;


# direct methods
.method public constructor <init>(ILjava/lang/String;Ljava/util/List;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    .line 91
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 92
    iput p1, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->epoch:I

    .line 93
    iput-object p2, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->publicVersion:Ljava/lang/String;

    .line 94
    iput-object p3, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->parts:Ljava/util/List;

    return-void
.end method

.method public static synthetic copy$default(Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;ILjava/lang/String;Ljava/util/List;ILjava/lang/Object;)Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;
    .locals 0

    and-int/lit8 p5, p4, 0x1

    if-eqz p5, :cond_0

    iget p1, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->epoch:I

    :cond_0
    and-int/lit8 p5, p4, 0x2

    if-eqz p5, :cond_1

    iget-object p2, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->publicVersion:Ljava/lang/String;

    :cond_1
    and-int/lit8 p4, p4, 0x4

    if-eqz p4, :cond_2

    iget-object p3, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->parts:Ljava/util/List;

    :cond_2
    invoke-virtual {p0, p1, p2, p3}, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->copy(ILjava/lang/String;Ljava/util/List;)Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public final component1()I
    .locals 0

    iget p0, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->epoch:I

    return p0
.end method

.method public final component2()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->publicVersion:Ljava/lang/String;

    return-object p0
.end method

.method public final component3()Ljava/util/List;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    iget-object p0, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->parts:Ljava/util/List;

    return-object p0
.end method

.method public final copy(ILjava/lang/String;Ljava/util/List;)Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;)",
            "Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;"
        }
    .end annotation

    new-instance p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;

    invoke-direct {p0, p1, p2, p3}, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;-><init>(ILjava/lang/String;Ljava/util/List;)V

    return-object p0
.end method

.method public equals(Ljava/lang/Object;)Z
    .locals 4

    const/4 v0, 0x1

    if-ne p0, p1, :cond_0

    return v0

    :cond_0
    instance-of v1, p1, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;

    const/4 v2, 0x0

    if-nez v1, :cond_1

    return v2

    :cond_1
    check-cast p1, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;

    iget v1, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->epoch:I

    iget v3, p1, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->epoch:I

    if-eq v1, v3, :cond_2

    return v2

    :cond_2
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->publicVersion:Ljava/lang/String;

    iget-object v3, p1, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->publicVersion:Ljava/lang/String;

    invoke-static {v1, v3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_3

    return v2

    :cond_3
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->parts:Ljava/util/List;

    iget-object p1, p1, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->parts:Ljava/util/List;

    invoke-static {p0, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_4

    return v2

    :cond_4
    return v0
.end method

.method public final getEpoch()I
    .locals 0

    .line 92
    iget p0, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->epoch:I

    return p0
.end method

.method public final getParts()Ljava/util/List;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .line 94
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->parts:Ljava/util/List;

    return-object p0
.end method

.method public final getPublicVersion()Ljava/lang/String;
    .locals 0

    .line 93
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->publicVersion:Ljava/lang/String;

    return-object p0
.end method

.method public hashCode()I
    .locals 2

    iget v0, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->epoch:I

    invoke-static {v0}, Ljava/lang/Integer;->hashCode(I)I

    move-result v0

    mul-int/lit8 v0, v0, 0x1f

    iget-object v1, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->publicVersion:Ljava/lang/String;

    invoke-virtual {v1}, Ljava/lang/String;->hashCode()I

    move-result v1

    add-int/2addr v0, v1

    mul-int/lit8 v0, v0, 0x1f

    iget-object p0, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->parts:Ljava/util/List;

    invoke-virtual {p0}, Ljava/lang/Object;->hashCode()I

    move-result p0

    add-int/2addr v0, p0

    return v0
.end method

.method public toString()Ljava/lang/String;
    .locals 4

    iget v0, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->epoch:I

    iget-object v1, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->publicVersion:Ljava/lang/String;

    iget-object p0, p0, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->parts:Ljava/util/List;

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "ParsedVersion(epoch="

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, ", publicVersion="

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", parts="

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p0, ")"

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
