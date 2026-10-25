.class final Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Entry"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0008\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u000f\u0008\u0002\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0008\u0008\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0008\u0008\u0002\u0010\u0008\u001a\u00020\t\u00a2\u0006\u0004\u0008\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u000c\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u000e\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u0010\u0010\u0011\"\u0004\u0008\u0012\u0010\u0013R\u001a\u0010\u0008\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u0014\u0010\u0015\"\u0004\u0008\u0016\u0010\u0017\u00a8\u0006\u0018"
    }
    d2 = {
        "Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;",
        "",
        "id",
        "",
        "message",
        "Lcom/exteragram/messenger/ai/data/Message;",
        "content",
        "",
        "expanded",
        "",
        "<init>",
        "(ILcom/exteragram/messenger/ai/data/Message;Ljava/lang/CharSequence;Z)V",
        "getId",
        "()I",
        "getMessage",
        "()Lcom/exteragram/messenger/ai/data/Message;",
        "getContent",
        "()Ljava/lang/CharSequence;",
        "setContent",
        "(Ljava/lang/CharSequence;)V",
        "getExpanded",
        "()Z",
        "setExpanded",
        "(Z)V",
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
.field private content:Ljava/lang/CharSequence;

.field private expanded:Z

.field private final id:I

.field private final message:Lcom/exteragram/messenger/ai/data/Message;


# direct methods
.method public constructor <init>(ILcom/exteragram/messenger/ai/data/Message;Ljava/lang/CharSequence;Z)V
    .locals 0

    .line 42
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 43
    iput p1, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->id:I

    .line 44
    iput-object p2, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->message:Lcom/exteragram/messenger/ai/data/Message;

    .line 45
    iput-object p3, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->content:Ljava/lang/CharSequence;

    .line 46
    iput-boolean p4, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->expanded:Z

    return-void
.end method

.method public synthetic constructor <init>(ILcom/exteragram/messenger/ai/data/Message;Ljava/lang/CharSequence;ZILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    and-int/lit8 p6, p5, 0x4

    if-eqz p6, :cond_1

    .line 45
    invoke-virtual {p2}, Lcom/exteragram/messenger/ai/data/Message;->content()Ljava/lang/String;

    move-result-object p3

    if-eqz p3, :cond_0

    goto :goto_0

    :cond_0
    const-string p3, ""

    :cond_1
    :goto_0
    and-int/lit8 p5, p5, 0x8

    if-eqz p5, :cond_2

    const/4 p4, 0x0

    .line 42
    :cond_2
    invoke-direct {p0, p1, p2, p3, p4}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;-><init>(ILcom/exteragram/messenger/ai/data/Message;Ljava/lang/CharSequence;Z)V

    return-void
.end method


# virtual methods
.method public final getContent()Ljava/lang/CharSequence;
    .locals 0

    .line 45
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->content:Ljava/lang/CharSequence;

    return-object p0
.end method

.method public final getExpanded()Z
    .locals 0

    .line 46
    iget-boolean p0, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->expanded:Z

    return p0
.end method

.method public final getId()I
    .locals 0

    .line 43
    iget p0, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->id:I

    return p0
.end method

.method public final getMessage()Lcom/exteragram/messenger/ai/data/Message;
    .locals 0

    .line 44
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->message:Lcom/exteragram/messenger/ai/data/Message;

    return-object p0
.end method

.method public final setContent(Ljava/lang/CharSequence;)V
    .locals 0

    .line 45
    iput-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->content:Ljava/lang/CharSequence;

    return-void
.end method

.method public final setExpanded(Z)V
    .locals 0

    .line 46
    iput-boolean p1, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$Entry;->expanded:Z

    return-void
.end method
