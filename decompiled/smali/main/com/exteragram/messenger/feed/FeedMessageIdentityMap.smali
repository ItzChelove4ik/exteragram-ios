.class final Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;,
        Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$GroupKey;
    }
.end annotation


# instance fields
.field private final generatedIds:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private final groupMembers:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$GroupKey;",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/messenger/MessageObject;",
            ">;>;"
        }
    .end annotation
.end field

.field private lastGeneratedId:I

.field private final messagesByRealId:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;",
            "Lorg/telegram/messenger/MessageObject;",
            ">;"
        }
    .end annotation
.end field

.field private final realIdsByGeneratedId:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/Integer;",
            "Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 1

    .line 9
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 11
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->generatedIds:Ljava/util/HashMap;

    .line 12
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->realIdsByGeneratedId:Ljava/util/concurrent/ConcurrentHashMap;

    .line 13
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->messagesByRealId:Ljava/util/HashMap;

    .line 14
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->groupMembers:Ljava/util/HashMap;

    const v0, 0x7ffffff5

    .line 15
    iput v0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->lastGeneratedId:I

    return-void
.end method

.method private addToGroup(Lorg/telegram/messenger/MessageObject;J)V
    .locals 3

    .line 95
    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->hasValidGroupId()Z

    move-result v0

    if-nez v0, :cond_0

    const/4 p0, 0x0

    .line 96
    iput-boolean p0, p1, Lorg/telegram/messenger/MessageObject;->isPrimaryGroupMessage:Z

    return-void

    .line 99
    :cond_0
    new-instance v0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$GroupKey;

    iget-object v1, p1, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    iget-wide v1, v1, Lorg/telegram/tgnet/TLRPC$Message;->grouped_id:J

    invoke-direct {v0, p2, p3, v1, v2}, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$GroupKey;-><init>(JJ)V

    .line 100
    iget-object p2, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->groupMembers:Ljava/util/HashMap;

    invoke-virtual {p2, v0}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/util/ArrayList;

    if-nez p2, :cond_1

    .line 102
    new-instance p2, Ljava/util/ArrayList;

    invoke-direct {p2}, Ljava/util/ArrayList;-><init>()V

    .line 103
    iget-object p0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->groupMembers:Ljava/util/HashMap;

    invoke-virtual {p0, v0, p2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 105
    :cond_1
    invoke-virtual {p2, p1}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_2

    .line 106
    invoke-virtual {p2, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 108
    :cond_2
    invoke-static {p2}, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->electPrimary(Ljava/util/ArrayList;)V

    return-void
.end method

.method private static electPrimary(Ljava/util/ArrayList;)V
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/messenger/MessageObject;",
            ">;)V"
        }
    .end annotation

    const/4 v0, 0x0

    const/4 v1, 0x0

    move v2, v1

    .line 131
    :goto_0
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v3

    if-ge v2, v3, :cond_2

    .line 132
    invoke-virtual {p0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lorg/telegram/messenger/MessageObject;

    if-eqz v0, :cond_0

    .line 133
    invoke-virtual {v3}, Lorg/telegram/messenger/MessageObject;->getRealId()I

    move-result v4

    invoke-virtual {v0}, Lorg/telegram/messenger/MessageObject;->getRealId()I

    move-result v5

    if-le v4, v5, :cond_1

    :cond_0
    move-object v0, v3

    :cond_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_2
    move v2, v1

    .line 137
    :goto_1
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v3

    if-ge v2, v3, :cond_4

    .line 138
    invoke-virtual {p0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lorg/telegram/messenger/MessageObject;

    invoke-virtual {p0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    if-ne v4, v0, :cond_3

    const/4 v4, 0x1

    goto :goto_2

    :cond_3
    move v4, v1

    :goto_2
    iput-boolean v4, v3, Lorg/telegram/messenger/MessageObject;->isPrimaryGroupMessage:Z

    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    :cond_4
    return-void
.end method

.method private removeFromGroup(Lorg/telegram/messenger/MessageObject;J)V
    .locals 3

    .line 112
    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->hasValidGroupId()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 115
    :cond_0
    new-instance v0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$GroupKey;

    iget-object v1, p1, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    iget-wide v1, v1, Lorg/telegram/tgnet/TLRPC$Message;->grouped_id:J

    invoke-direct {v0, p2, p3, v1, v2}, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$GroupKey;-><init>(JJ)V

    .line 116
    iget-object p2, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->groupMembers:Ljava/util/HashMap;

    invoke-virtual {p2, v0}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/util/ArrayList;

    if-nez p2, :cond_1

    :goto_0
    return-void

    .line 120
    :cond_1
    invoke-virtual {p2, p1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    const/4 p3, 0x0

    .line 121
    iput-boolean p3, p1, Lorg/telegram/messenger/MessageObject;->isPrimaryGroupMessage:Z

    .line 122
    invoke-virtual {p2}, Ljava/util/ArrayList;->isEmpty()Z

    move-result p1

    if-eqz p1, :cond_2

    .line 123
    iget-object p0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->groupMembers:Ljava/util/HashMap;

    invoke-virtual {p0, v0}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    return-void

    .line 125
    :cond_2
    invoke-static {p2}, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->electPrimary(Ljava/util/ArrayList;)V

    return-void
.end method


# virtual methods
.method public clear()V
    .locals 1

    .line 87
    iget-object v0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->generatedIds:Ljava/util/HashMap;

    invoke-virtual {v0}, Ljava/util/HashMap;->clear()V

    .line 88
    iget-object v0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->realIdsByGeneratedId:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentHashMap;->clear()V

    .line 89
    iget-object v0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->messagesByRealId:Ljava/util/HashMap;

    invoke-virtual {v0}, Ljava/util/HashMap;->clear()V

    .line 90
    iget-object v0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->groupMembers:Ljava/util/HashMap;

    invoke-virtual {v0}, Ljava/util/HashMap;->clear()V

    const v0, 0x7ffffff5

    .line 91
    iput v0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->lastGeneratedId:I

    return-void
.end method

.method public getByAnyId(JI)Lorg/telegram/messenger/MessageObject;
    .locals 2

    .line 61
    iget-object v0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->messagesByRealId:Ljava/util/HashMap;

    new-instance v1, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;

    invoke-direct {v1, p1, p2, p3}, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;-><init>(JI)V

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/messenger/MessageObject;

    if-eqz v0, :cond_0

    return-object v0

    .line 65
    :cond_0
    invoke-virtual {p0, p1, p2, p3}, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->resolveRealMessageId(JI)I

    move-result v0

    if-eq v0, p3, :cond_1

    .line 66
    iget-object p0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->messagesByRealId:Ljava/util/HashMap;

    new-instance p3, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;

    invoke-direct {p3, p1, p2, v0}, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;-><init>(JI)V

    invoke-virtual {p0, p3}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/telegram/messenger/MessageObject;

    return-object p0

    :cond_1
    const/4 p0, 0x0

    return-object p0
.end method

.method public getByRealId(JI)Lorg/telegram/messenger/MessageObject;
    .locals 1

    .line 57
    iget-object p0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->messagesByRealId:Ljava/util/HashMap;

    new-instance v0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;

    invoke-direct {v0, p1, p2, p3}, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;-><init>(JI)V

    invoke-virtual {p0, v0}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/telegram/messenger/MessageObject;

    return-object p0
.end method

.method public isEmpty()Z
    .locals 0

    .line 83
    iget-object p0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->realIdsByGeneratedId:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p0}, Ljava/util/concurrent/ConcurrentHashMap;->isEmpty()Z

    move-result p0

    return p0
.end method

.method public purge(Lorg/telegram/messenger/MessageObject;)V
    .locals 4

    .line 49
    new-instance v0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;

    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getDialogId()J

    move-result-wide v1

    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getRealId()I

    move-result v3

    invoke-direct {v0, v1, v2, v3}, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;-><init>(JI)V

    .line 50
    iget-object v1, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->generatedIds:Ljava/util/HashMap;

    invoke-virtual {v1, v0}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 51
    iget-object v1, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->messagesByRealId:Ljava/util/HashMap;

    invoke-virtual {v1, v0}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 52
    iget-object v1, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->realIdsByGeneratedId:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getId()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 53
    iget-wide v0, v0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;->dialog_id:J

    invoke-direct {p0, p1, v0, v1}, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->removeFromGroup(Lorg/telegram/messenger/MessageObject;J)V

    return-void
.end method

.method public register(Lorg/telegram/messenger/MessageObject;)Z
    .locals 5

    .line 18
    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getRealId()I

    move-result v0

    .line 19
    new-instance v1, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;

    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getDialogId()J

    move-result-wide v2

    invoke-direct {v1, v2, v3, v0}, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;-><init>(JI)V

    .line 20
    iget-object v2, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->generatedIds:Ljava/util/HashMap;

    invoke-virtual {v2, v1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Integer;

    if-nez v2, :cond_0

    .line 22
    iget v2, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->lastGeneratedId:I

    add-int/lit8 v3, v2, -0x1

    iput v3, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->lastGeneratedId:I

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    .line 23
    iget-object v3, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->generatedIds:Ljava/util/HashMap;

    invoke-virtual {v3, v1, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 25
    :cond_0
    iget-object v3, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->realIdsByGeneratedId:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v3, v2, v1}, Ljava/util/concurrent/ConcurrentHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 27
    iget-object v3, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->messagesByRealId:Ljava/util/HashMap;

    invoke-virtual {v3, v1}, Ljava/util/HashMap;->containsKey(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_1

    .line 28
    iget-object v3, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->messagesByRealId:Ljava/util/HashMap;

    invoke-virtual {v3, v1, p1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 29
    iget-wide v3, v1, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;->dialog_id:J

    invoke-direct {p0, p1, v3, v4}, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->addToGroup(Lorg/telegram/messenger/MessageObject;J)V

    const/4 p0, 0x1

    goto :goto_0

    :cond_1
    const/4 p0, 0x0

    .line 32
    :goto_0
    iget-object p1, p1, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    iput v0, p1, Lorg/telegram/tgnet/TLRPC$Message;->realId:I

    .line 33
    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v0

    iput v0, p1, Lorg/telegram/tgnet/TLRPC$Message;->id:I

    return p0
.end method

.method public replace(Lorg/telegram/messenger/MessageObject;)V
    .locals 4

    .line 38
    new-instance v0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;

    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getDialogId()J

    move-result-wide v1

    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getRealId()I

    move-result v3

    invoke-direct {v0, v1, v2, v3}, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;-><init>(JI)V

    .line 39
    iget-object v1, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->generatedIds:Ljava/util/HashMap;

    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getId()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v1, v0, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 40
    iget-object v1, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->realIdsByGeneratedId:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getId()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v1, v2, v0}, Ljava/util/concurrent/ConcurrentHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 41
    iget-object v1, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->messagesByRealId:Ljava/util/HashMap;

    invoke-virtual {v1, v0, p1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lorg/telegram/messenger/MessageObject;

    if-eqz v1, :cond_0

    .line 43
    iget-wide v2, v0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;->dialog_id:J

    invoke-direct {p0, v1, v2, v3}, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->removeFromGroup(Lorg/telegram/messenger/MessageObject;J)V

    .line 45
    :cond_0
    iget-wide v0, v0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;->dialog_id:J

    invoke-direct {p0, p1, v0, v1}, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->addToGroup(Lorg/telegram/messenger/MessageObject;J)V

    return-void
.end method

.method public resolveRealDialogId(I)J
    .locals 0

    .line 78
    iget-object p0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->realIdsByGeneratedId:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;

    if-eqz p0, :cond_0

    .line 79
    iget-wide p0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;->dialog_id:J

    return-wide p0

    :cond_0
    const-wide/16 p0, 0x0

    return-wide p0
.end method

.method public resolveRealMessageId(JI)I
    .locals 2

    .line 70
    iget-object p0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap;->realIdsByGeneratedId:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;

    if-eqz p0, :cond_0

    .line 71
    iget-wide v0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;->dialog_id:J

    cmp-long p1, v0, p1

    if-nez p1, :cond_0

    .line 72
    iget p0, p0, Lcom/exteragram/messenger/feed/FeedMessageIdentityMap$MessageCompositeID;->id:I

    return p0

    :cond_0
    return p3
.end method
