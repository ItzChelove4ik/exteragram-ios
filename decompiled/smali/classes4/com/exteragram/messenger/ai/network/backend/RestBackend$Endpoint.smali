.class final Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;
.super Lcom/android/tools/r8/RecordTag;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/ai/network/backend/RestBackend;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Endpoint"
.end annotation


# instance fields
.field private final responsesApi:Z

.field private final url:Ljava/lang/String;


# direct methods
.method private synthetic $record$equals(Ljava/lang/Object;)Z
    .locals 2

    .line 0
    instance-of v0, p1, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;

    if-eqz v0, :cond_0

    check-cast p1, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;

    iget-boolean v0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;->responsesApi:Z

    iget-boolean v1, p1, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;->responsesApi:Z

    if-ne v0, v1, :cond_0

    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;->url:Ljava/lang/String;

    iget-object p1, p1, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;->url:Ljava/lang/String;

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
    .locals 3

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;->url:Ljava/lang/String;

    iget-boolean p0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;->responsesApi:Z

    invoke-static {p0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0

    const/4 v1, 0x2

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    aput-object v0, v1, v2

    const/4 v0, 0x1

    aput-object p0, v1, v0

    return-object v1
.end method

.method private constructor <init>(Ljava/lang/String;Z)V
    .locals 0

    .line 727
    invoke-direct {p0}, Lcom/android/tools/r8/RecordTag;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;->url:Ljava/lang/String;

    iput-boolean p2, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;->responsesApi:Z

    return-void
.end method

.method public synthetic constructor <init>(Ljava/lang/String;ZLcom/exteragram/messenger/ai/network/backend/RestBackend-IA;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;-><init>(Ljava/lang/String;Z)V

    return-void
.end method


# virtual methods
.method public final equals(Ljava/lang/Object;)Z
    .locals 0

    .line 727
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;->$record$equals(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public final hashCode()I
    .locals 1

    .line 727
    iget-boolean v0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;->responsesApi:Z

    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;->url:Ljava/lang/String;

    invoke-static {v0, p0}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint$$ExternalSyntheticRecord0;->m(ZLjava/lang/Object;)I

    move-result p0

    return p0
.end method

.method public responsesApi()Z
    .locals 0

    .line 727
    iget-boolean p0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;->responsesApi:Z

    return p0
.end method

.method public final toString()Ljava/lang/String;
    .locals 2

    .line 727
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;->$record$getFieldsAsObjects()[Ljava/lang/Object;

    move-result-object p0

    const-class v0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;

    const-string v1, "url;responsesApi"

    invoke-static {p0, v0, v1}, Lcom/exteragram/messenger/ai/network/Client$ImagePayload$$ExternalSyntheticRecord1;->m([Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public url()Ljava/lang/String;
    .locals 0

    .line 727
    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;->url:Ljava/lang/String;

    return-object p0
.end method
