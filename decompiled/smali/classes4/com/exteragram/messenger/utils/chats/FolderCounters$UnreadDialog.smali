.class final Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/utils/chats/FolderCounters;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "UnreadDialog"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0010\u000b\n\u0002\u0008\n\u0008\u0002\u0018\u00002\u00020\u0001B\'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0008\u00a2\u0006\u0004\u0008\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u000b\u0010\u000cR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0008\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0010\u0010\u0011\u00a8\u0006\u0012"
    }
    d2 = {
        "Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;",
        "",
        "id",
        "",
        "typeFlags",
        "",
        "weight",
        "archived",
        "",
        "<init>",
        "(JIIZ)V",
        "getId",
        "()J",
        "getTypeFlags",
        "()I",
        "getWeight",
        "getArchived",
        "()Z",
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
.field private final archived:Z

.field private final id:J

.field private final typeFlags:I

.field private final weight:I


# direct methods
.method public constructor <init>(JIIZ)V
    .locals 0

    .line 30
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 31
    iput-wide p1, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;->id:J

    .line 32
    iput p3, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;->typeFlags:I

    .line 33
    iput p4, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;->weight:I

    .line 34
    iput-boolean p5, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;->archived:Z

    return-void
.end method


# virtual methods
.method public final getArchived()Z
    .locals 0

    .line 34
    iget-boolean p0, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;->archived:Z

    return p0
.end method

.method public final getId()J
    .locals 2

    .line 31
    iget-wide v0, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;->id:J

    return-wide v0
.end method

.method public final getTypeFlags()I
    .locals 0

    .line 32
    iget p0, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;->typeFlags:I

    return p0
.end method

.method public final getWeight()I
    .locals 0

    .line 33
    iget p0, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;->weight:I

    return p0
.end method
