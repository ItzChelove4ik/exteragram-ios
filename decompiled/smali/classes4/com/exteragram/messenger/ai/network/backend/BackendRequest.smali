.class public final Lcom/exteragram/messenger/ai/network/backend/BackendRequest;
.super Lcom/android/tools/r8/RecordTag;
.source "SourceFile"


# instance fields
.field private final currentMessage:Lcom/exteragram/messenger/ai/data/Message;

.field private final history:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/ai/data/Message;",
            ">;"
        }
    .end annotation
.end field

.field private final maxOutputTokens:I

.field private final role:Lcom/exteragram/messenger/ai/data/Role;

.field private final service:Lcom/exteragram/messenger/ai/data/Service;

.field private final stream:Z

.field private final temperature:F


# direct methods
.method private synthetic $record$equals(Ljava/lang/Object;)Z
    .locals 2

    .line 0
    instance-of v0, p1, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;

    if-eqz v0, :cond_0

    check-cast p1, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;

    iget-boolean v0, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->stream:Z

    iget-boolean v1, p1, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->stream:Z

    if-ne v0, v1, :cond_0

    iget v0, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->maxOutputTokens:I

    iget v1, p1, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->maxOutputTokens:I

    if-ne v0, v1, :cond_0

    iget v0, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->temperature:F

    iget v1, p1, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->temperature:F

    cmpl-float v0, v0, v1

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->service:Lcom/exteragram/messenger/ai/data/Service;

    iget-object v1, p1, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->service:Lcom/exteragram/messenger/ai/data/Service;

    invoke-static {v0, v1}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->role:Lcom/exteragram/messenger/ai/data/Role;

    iget-object v1, p1, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->role:Lcom/exteragram/messenger/ai/data/Role;

    invoke-static {v0, v1}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->history:Ljava/util/List;

    iget-object v1, p1, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->history:Ljava/util/List;

    invoke-static {v0, v1}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->currentMessage:Lcom/exteragram/messenger/ai/data/Message;

    iget-object p1, p1, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->currentMessage:Lcom/exteragram/messenger/ai/data/Message;

    invoke-static {p0, p1}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private synthetic $record$getFieldsAsObjects()[Ljava/lang/Object;
    .locals 8

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->service:Lcom/exteragram/messenger/ai/data/Service;

    iget-object v1, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->role:Lcom/exteragram/messenger/ai/data/Role;

    iget-object v2, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->history:Ljava/util/List;

    iget-object v3, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->currentMessage:Lcom/exteragram/messenger/ai/data/Message;

    iget-boolean v4, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->stream:Z

    invoke-static {v4}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v4

    iget v5, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->temperature:F

    invoke-static {v5}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v5

    iget p0, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->maxOutputTokens:I

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    const/4 v6, 0x7

    new-array v6, v6, [Ljava/lang/Object;

    const/4 v7, 0x0

    aput-object v0, v6, v7

    const/4 v0, 0x1

    aput-object v1, v6, v0

    const/4 v0, 0x2

    aput-object v2, v6, v0

    const/4 v0, 0x3

    aput-object v3, v6, v0

    const/4 v0, 0x4

    aput-object v4, v6, v0

    const/4 v0, 0x5

    aput-object v5, v6, v0

    const/4 v0, 0x6

    aput-object p0, v6, v0

    return-object v6
.end method

.method public constructor <init>(Lcom/exteragram/messenger/ai/data/Service;Lcom/exteragram/messenger/ai/data/Role;Ljava/util/List;Lcom/exteragram/messenger/ai/data/Message;ZFI)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/exteragram/messenger/ai/data/Service;",
            "Lcom/exteragram/messenger/ai/data/Role;",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/ai/data/Message;",
            ">;",
            "Lcom/exteragram/messenger/ai/data/Message;",
            "ZFI)V"
        }
    .end annotation

    .line 23
    invoke-direct {p0}, Lcom/android/tools/r8/RecordTag;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->service:Lcom/exteragram/messenger/ai/data/Service;

    iput-object p2, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->role:Lcom/exteragram/messenger/ai/data/Role;

    iput-object p3, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->history:Ljava/util/List;

    iput-object p4, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->currentMessage:Lcom/exteragram/messenger/ai/data/Message;

    iput-boolean p5, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->stream:Z

    iput p6, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->temperature:F

    iput p7, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->maxOutputTokens:I

    return-void
.end method


# virtual methods
.method public currentMessage()Lcom/exteragram/messenger/ai/data/Message;
    .locals 0

    .line 23
    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->currentMessage:Lcom/exteragram/messenger/ai/data/Message;

    return-object p0
.end method

.method public final equals(Ljava/lang/Object;)Z
    .locals 0

    .line 23
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->$record$equals(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public final hashCode()I
    .locals 7

    .line 23
    iget-boolean v0, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->stream:Z

    iget v1, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->maxOutputTokens:I

    iget v2, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->temperature:F

    iget-object v3, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->service:Lcom/exteragram/messenger/ai/data/Service;

    iget-object v4, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->role:Lcom/exteragram/messenger/ai/data/Role;

    iget-object v5, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->history:Ljava/util/List;

    iget-object v6, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->currentMessage:Lcom/exteragram/messenger/ai/data/Message;

    invoke-static/range {v0 .. v6}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest$$ExternalSyntheticRecord0;->m(ZIFLjava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)I

    move-result p0

    return p0
.end method

.method public history()Ljava/util/List;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/ai/data/Message;",
            ">;"
        }
    .end annotation

    .line 23
    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->history:Ljava/util/List;

    return-object p0
.end method

.method public maxOutputTokens()I
    .locals 0

    .line 23
    iget p0, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->maxOutputTokens:I

    return p0
.end method

.method public role()Lcom/exteragram/messenger/ai/data/Role;
    .locals 0

    .line 23
    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->role:Lcom/exteragram/messenger/ai/data/Role;

    return-object p0
.end method

.method public service()Lcom/exteragram/messenger/ai/data/Service;
    .locals 0

    .line 23
    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->service:Lcom/exteragram/messenger/ai/data/Service;

    return-object p0
.end method

.method public stream()Z
    .locals 0

    .line 23
    iget-boolean p0, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->stream:Z

    return p0
.end method

.method public temperature()F
    .locals 0

    .line 23
    iget p0, p0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->temperature:F

    return p0
.end method

.method public final toString()Ljava/lang/String;
    .locals 2

    .line 23
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->$record$getFieldsAsObjects()[Ljava/lang/Object;

    move-result-object p0

    const-class v0, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;

    const-string v1, "service;role;history;currentMessage;stream;temperature;maxOutputTokens"

    invoke-static {p0, v0, v1}, Lcom/exteragram/messenger/ai/network/Client$ImagePayload$$ExternalSyntheticRecord1;->m([Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
