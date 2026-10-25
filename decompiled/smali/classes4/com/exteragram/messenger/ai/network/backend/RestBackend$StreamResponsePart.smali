.class final Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;
.super Lcom/android/tools/r8/RecordTag;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/ai/network/backend/RestBackend;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "StreamResponsePart"
.end annotation


# static fields
.field static final DONE:Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;


# instance fields
.field private final content:Ljava/lang/String;

.field private final done:Z

.field private final error:Ljava/lang/String;

.field private final hasReasoning:Z


# direct methods
.method private synthetic $record$equals(Ljava/lang/Object;)Z
    .locals 2

    .line 0
    instance-of v0, p1, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;

    if-eqz v0, :cond_0

    check-cast p1, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;

    iget-boolean v0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->hasReasoning:Z

    iget-boolean v1, p1, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->hasReasoning:Z

    if-ne v0, v1, :cond_0

    iget-boolean v0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->done:Z

    iget-boolean v1, p1, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->done:Z

    if-ne v0, v1, :cond_0

    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->content:Ljava/lang/String;

    iget-object v1, p1, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->content:Ljava/lang/String;

    invoke-static {v0, v1}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->error:Ljava/lang/String;

    iget-object p1, p1, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->error:Ljava/lang/String;

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
    .locals 5

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->content:Ljava/lang/String;

    iget-boolean v1, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->hasReasoning:Z

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    iget-boolean v2, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->done:Z

    invoke-static {v2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->error:Ljava/lang/String;

    const/4 v3, 0x4

    new-array v3, v3, [Ljava/lang/Object;

    const/4 v4, 0x0

    aput-object v0, v3, v4

    const/4 v0, 0x1

    aput-object v1, v3, v0

    const/4 v0, 0x2

    aput-object v2, v3, v0

    const/4 v0, 0x3

    aput-object p0, v3, v0

    return-object v3
.end method

.method static constructor <clinit>()V
    .locals 5

    .line 731
    new-instance v0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;

    const/4 v1, 0x1

    const/4 v2, 0x0

    const-string v3, ""

    const/4 v4, 0x0

    invoke-direct {v0, v3, v4, v1, v2}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;-><init>(Ljava/lang/String;ZZLjava/lang/String;)V

    sput-object v0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->DONE:Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;Z)V
    .locals 2

    const/4 v0, 0x0

    const/4 v1, 0x0

    .line 734
    invoke-direct {p0, p1, p2, v0, v1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;-><init>(Ljava/lang/String;ZZLjava/lang/String;)V

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;ZZLjava/lang/String;)V
    .locals 0

    .line 730
    invoke-direct {p0}, Lcom/android/tools/r8/RecordTag;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->content:Ljava/lang/String;

    iput-boolean p2, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->hasReasoning:Z

    iput-boolean p3, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->done:Z

    iput-object p4, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->error:Ljava/lang/String;

    return-void
.end method

.method public synthetic constructor <init>(Ljava/lang/String;ZZLjava/lang/String;Lcom/exteragram/messenger/ai/network/backend/RestBackend-IA;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;-><init>(Ljava/lang/String;ZZLjava/lang/String;)V

    return-void
.end method


# virtual methods
.method public content()Ljava/lang/String;
    .locals 0

    .line 730
    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->content:Ljava/lang/String;

    return-object p0
.end method

.method public done()Z
    .locals 0

    .line 730
    iget-boolean p0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->done:Z

    return p0
.end method

.method public final equals(Ljava/lang/Object;)Z
    .locals 0

    .line 730
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->$record$equals(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public error()Ljava/lang/String;
    .locals 0

    .line 730
    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->error:Ljava/lang/String;

    return-object p0
.end method

.method public hasReasoning()Z
    .locals 0

    .line 730
    iget-boolean p0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->hasReasoning:Z

    return p0
.end method

.method public final hashCode()I
    .locals 3

    .line 730
    iget-boolean v0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->hasReasoning:Z

    iget-boolean v1, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->done:Z

    iget-object v2, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->content:Ljava/lang/String;

    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->error:Ljava/lang/String;

    invoke-static {v0, v1, v2, p0}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart$$ExternalSyntheticRecord0;->m(ZZLjava/lang/Object;Ljava/lang/Object;)I

    move-result p0

    return p0
.end method

.method public final toString()Ljava/lang/String;
    .locals 2

    .line 730
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->$record$getFieldsAsObjects()[Ljava/lang/Object;

    move-result-object p0

    const-class v0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;

    const-string v1, "content;hasReasoning;done;error"

    invoke-static {p0, v0, v1}, Lcom/exteragram/messenger/ai/network/Client$ImagePayload$$ExternalSyntheticRecord1;->m([Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
