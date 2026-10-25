.class public Lcom/exteragram/messenger/ai/network/backend/RestBackend;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/exteragram/messenger/ai/network/backend/Backend;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;,
        Lcom/exteragram/messenger/ai/network/backend/RestBackend$ReasoningContentFilter;,
        Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;
    }
.end annotation


# static fields
.field private static final ERROR_MESSAGE_KEYS:[Ljava/lang/String;

.field private static final STREAM_SYMBOLS_LIMIT:I


# instance fields
.field private final httpClient:Lokhttp3/OkHttpClient;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .line 46
    invoke-static {}, Lorg/telegram/messenger/SharedConfig;->getDevicePerformanceClass()I

    move-result v0

    const/4 v1, 0x1

    if-lt v0, v1, :cond_0

    const/16 v0, 0xa

    goto :goto_0

    :cond_0
    const/16 v0, 0x14

    :goto_0
    sput v0, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->STREAM_SYMBOLS_LIMIT:I

    .line 59
    const-string v0, "message"

    const-string v1, "detail"

    const-string v2, "error"

    filled-new-array {v2, v0, v1}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->ERROR_MESSAGE_KEYS:[Ljava/lang/String;

    return-void
.end method

.method public constructor <init>()V
    .locals 4

    .line 63
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 64
    sget-object v0, Lcom/exteragram/messenger/utils/network/ExteraHttpClient;->INSTANCE:Lcom/exteragram/messenger/utils/network/ExteraHttpClient;

    invoke-virtual {v0}, Lcom/exteragram/messenger/utils/network/ExteraHttpClient;->getClient()Lokhttp3/OkHttpClient;

    move-result-object v0

    invoke-virtual {v0}, Lokhttp3/OkHttpClient;->newBuilder()Lokhttp3/OkHttpClient$Builder;

    move-result-object v0

    sget-object v1, Lcom/exteragram/messenger/ai/network/ProxyDns;->INSTANCE:Lokhttp3/Dns;

    .line 65
    invoke-virtual {v0, v1}, Lokhttp3/OkHttpClient$Builder;->dns(Lokhttp3/Dns;)Lokhttp3/OkHttpClient$Builder;

    move-result-object v0

    const-wide/16 v1, 0x1

    .line 66
    sget-object v3, Ljava/util/concurrent/TimeUnit;->MINUTES:Ljava/util/concurrent/TimeUnit;

    invoke-virtual {v0, v1, v2, v3}, Lokhttp3/OkHttpClient$Builder;->connectTimeout(JLjava/util/concurrent/TimeUnit;)Lokhttp3/OkHttpClient$Builder;

    move-result-object v0

    const-wide/16 v1, 0x5

    .line 67
    invoke-virtual {v0, v1, v2, v3}, Lokhttp3/OkHttpClient$Builder;->readTimeout(JLjava/util/concurrent/TimeUnit;)Lokhttp3/OkHttpClient$Builder;

    move-result-object v0

    .line 68
    invoke-virtual {v0}, Lokhttp3/OkHttpClient$Builder;->build()Lokhttp3/OkHttpClient;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->httpClient:Lokhttp3/OkHttpClient;

    return-void
.end method

.method private applyReasoningConfig(Lorg/json/JSONObject;Lcom/exteragram/messenger/ai/data/Service;Ljava/lang/String;Z)V
    .locals 3

    .line 227
    invoke-virtual {p2}, Lcom/exteragram/messenger/ai/data/Service;->isReasoningEnabled()Z

    move-result v0

    if-eqz v0, :cond_0

    goto/16 :goto_4

    .line 231
    :cond_0
    invoke-virtual {p2}, Lcom/exteragram/messenger/ai/data/Service;->getModel()Ljava/lang/String;

    move-result-object p2

    .line 232
    const-string v0, ""

    if-nez p3, :cond_1

    move-object p3, v0

    goto :goto_0

    :cond_1
    sget-object v1, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-virtual {p3, v1}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p3

    :goto_0
    if-nez p2, :cond_2

    goto :goto_1

    .line 233
    :cond_2
    sget-object v0, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-virtual {p2, v0}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object v0

    .line 236
    :goto_1
    const-string p2, "openrouter.ai"

    invoke-virtual {p3, p2}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p2

    const-string v1, "effort"

    const-string v2, "reasoning"

    if-eqz p2, :cond_3

    .line 237
    new-instance p0, Lorg/json/JSONObject;

    invoke-direct {p0}, Lorg/json/JSONObject;-><init>()V

    const-string p2, "none"

    invoke-virtual {p0, v1, p2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p0

    invoke-virtual {p1, v2, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    goto :goto_2

    .line 238
    :cond_3
    invoke-direct {p0, p3}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->isGeminiService(Ljava/lang/String;)Z

    move-result p2

    if-eqz p2, :cond_4

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->isGeminiReasoningModel(Ljava/lang/String;)Z

    move-result p2

    if-eqz p2, :cond_4

    .line 239
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->getGeminiReasoningEffort(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    goto :goto_3

    .line 240
    :cond_4
    invoke-direct {p0, p3}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->isOpenAiService(Ljava/lang/String;)Z

    move-result p2

    if-eqz p2, :cond_5

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->isOpenAiReasoningModel(Ljava/lang/String;)Z

    move-result p2

    if-eqz p2, :cond_5

    .line 241
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->getOpenAiReasoningEffort(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    goto :goto_3

    .line 242
    :cond_5
    invoke-direct {p0, p3}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->isPerplexityService(Ljava/lang/String;)Z

    move-result p2

    if-eqz p2, :cond_7

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->isGeminiReasoningModel(Ljava/lang/String;)Z

    move-result p2

    if-nez p2, :cond_6

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->isOpenAiReasoningModel(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_7

    .line 243
    :cond_6
    const-string p0, "minimal"

    goto :goto_3

    :cond_7
    :goto_2
    const/4 p0, 0x0

    :goto_3
    if-nez p0, :cond_8

    :goto_4
    return-void

    :cond_8
    if-eqz p4, :cond_9

    .line 250
    new-instance p2, Lorg/json/JSONObject;

    invoke-direct {p2}, Lorg/json/JSONObject;-><init>()V

    invoke-virtual {p2, v1, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p0

    invoke-virtual {p1, v2, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    return-void

    .line 252
    :cond_9
    const-string p2, "reasoning_effort"

    invoke-virtual {p1, p2, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    return-void
.end method

.method private createMessageObject(Lcom/exteragram/messenger/ai/data/Message;Z)Lorg/json/JSONObject;
    .locals 6

    .line 310
    new-instance p0, Lorg/json/JSONObject;

    invoke-direct {p0}, Lorg/json/JSONObject;-><init>()V

    .line 311
    const-string v0, "type"

    if-eqz p2, :cond_0

    .line 312
    const-string v1, "message"

    invoke-virtual {p0, v0, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 314
    :cond_0
    const-string v1, "role"

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Message;->role()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p0, v1, v2}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 316
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Message;->getImageData()[B

    move-result-object v1

    const-string v2, "content"

    if-eqz v1, :cond_4

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Message;->getMimeType()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_4

    .line 317
    new-instance v1, Lorg/json/JSONArray;

    invoke-direct {v1}, Lorg/json/JSONArray;-><init>()V

    .line 319
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Message;->content()Ljava/lang/String;

    move-result-object v3

    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-nez v3, :cond_2

    .line 320
    new-instance v3, Lorg/json/JSONObject;

    invoke-direct {v3}, Lorg/json/JSONObject;-><init>()V

    .line 321
    const-string v4, "text"

    if-eqz p2, :cond_1

    const-string v5, "input_text"

    goto :goto_0

    :cond_1
    move-object v5, v4

    :goto_0
    invoke-virtual {v3, v0, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v3

    .line 322
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Message;->content()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v3, v4, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v3

    .line 320
    invoke-virtual {v1, v3}, Lorg/json/JSONArray;->put(Ljava/lang/Object;)Lorg/json/JSONArray;

    .line 325
    :cond_2
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Message;->getImageData()[B

    move-result-object v3

    const/4 v4, 0x2

    invoke-static {v3, v4}, Landroid/util/Base64;->encodeToString([BI)Ljava/lang/String;

    move-result-object v3

    .line 326
    new-instance v4, Ljava/lang/StringBuilder;

    const-string v5, "data:"

    invoke-direct {v4, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Message;->getMimeType()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, ";base64,"

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 327
    const-string v3, "image_url"

    if-eqz p2, :cond_3

    .line 328
    new-instance p2, Lorg/json/JSONObject;

    invoke-direct {p2}, Lorg/json/JSONObject;-><init>()V

    const-string v4, "input_image"

    .line 329
    invoke-virtual {p2, v0, v4}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    .line 330
    invoke-virtual {p2, v3, p1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p1

    .line 328
    invoke-virtual {v1, p1}, Lorg/json/JSONArray;->put(Ljava/lang/Object;)Lorg/json/JSONArray;

    goto :goto_1

    .line 332
    :cond_3
    new-instance p2, Lorg/json/JSONObject;

    invoke-direct {p2}, Lorg/json/JSONObject;-><init>()V

    .line 333
    invoke-virtual {p2, v0, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p2

    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    const-string v4, "url"

    .line 334
    invoke-virtual {v0, v4, p1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p1

    invoke-virtual {p2, v3, p1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object p1

    .line 332
    invoke-virtual {v1, p1}, Lorg/json/JSONArray;->put(Ljava/lang/Object;)Lorg/json/JSONArray;

    .line 337
    :goto_1
    invoke-virtual {p0, v2, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    return-object p0

    .line 339
    :cond_4
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Message;->content()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, v2, p1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    return-object p0
.end method

.method private createRequest(Lcom/exteragram/messenger/ai/network/backend/BackendRequest;Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;)Lokhttp3/Request;
    .locals 8

    .line 168
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->service()Lcom/exteragram/messenger/ai/data/Service;

    move-result-object v0

    .line 169
    invoke-virtual {p2}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;->responsesApi()Z

    move-result v1

    .line 171
    new-instance v2, Lorg/json/JSONObject;

    invoke-direct {v2}, Lorg/json/JSONObject;-><init>()V

    .line 172
    new-instance v3, Lorg/json/JSONArray;

    invoke-direct {v3}, Lorg/json/JSONArray;-><init>()V

    .line 175
    :try_start_0
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->role()Lcom/exteragram/messenger/ai/data/Role;

    move-result-object v4

    if-eqz v4, :cond_1

    .line 176
    invoke-virtual {v4}, Lcom/exteragram/messenger/ai/data/Role;->getPrompt()Ljava/lang/String;

    move-result-object v5

    invoke-static {v5}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v5

    if-nez v5, :cond_1

    if-eqz v1, :cond_0

    .line 178
    const-string v5, "instructions"

    invoke-virtual {v4}, Lcom/exteragram/messenger/ai/data/Role;->getPrompt()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v2, v5, v4}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    goto :goto_0

    .line 180
    :cond_0
    new-instance v5, Lorg/json/JSONObject;

    invoke-direct {v5}, Lorg/json/JSONObject;-><init>()V

    const-string v6, "role"

    const-string v7, "system"

    .line 181
    invoke-virtual {v5, v6, v7}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v5

    const-string v6, "content"

    .line 182
    invoke-virtual {v4}, Lcom/exteragram/messenger/ai/data/Role;->getPrompt()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v5, v6, v4}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    move-result-object v4

    .line 180
    invoke-virtual {v3, v4}, Lorg/json/JSONArray;->put(Ljava/lang/Object;)Lorg/json/JSONArray;

    .line 186
    :cond_1
    :goto_0
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->history()Ljava/util/List;

    move-result-object v4

    invoke-interface {v4}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v4

    :goto_1
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_2

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/exteragram/messenger/ai/data/Message;

    .line 187
    invoke-direct {p0, v5, v1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->createMessageObject(Lcom/exteragram/messenger/ai/data/Message;Z)Lorg/json/JSONObject;

    move-result-object v5

    invoke-virtual {v3, v5}, Lorg/json/JSONArray;->put(Ljava/lang/Object;)Lorg/json/JSONArray;

    goto :goto_1

    .line 189
    :cond_2
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->currentMessage()Lcom/exteragram/messenger/ai/data/Message;

    move-result-object v4

    invoke-direct {p0, v4, v1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->createMessageObject(Lcom/exteragram/messenger/ai/data/Message;Z)Lorg/json/JSONObject;

    move-result-object v4

    invoke-virtual {v3, v4}, Lorg/json/JSONArray;->put(Ljava/lang/Object;)Lorg/json/JSONArray;

    .line 191
    const-string v4, "model"

    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/data/Service;->getModel()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v2, v4, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    if-eqz v1, :cond_3

    .line 192
    const-string v4, "input"

    goto :goto_2

    :cond_3
    const-string v4, "messages"

    :goto_2
    invoke-virtual {v2, v4, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    .line 193
    const-string v4, "stream"

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->stream()Z

    move-result v5

    invoke-virtual {v2, v4, v5}, Lorg/json/JSONObject;->put(Ljava/lang/String;Z)Lorg/json/JSONObject;

    .line 194
    const-string v4, "temperature"

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->temperature()F

    move-result v5

    float-to-double v5, v5

    invoke-virtual {v2, v4, v5, v6}, Lorg/json/JSONObject;->put(Ljava/lang/String;D)Lorg/json/JSONObject;

    .line 195
    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/data/Service;->getUrl()Ljava/lang/String;

    move-result-object v4

    invoke-direct {p0, v2, v0, v4, v1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->applyReasoningConfig(Lorg/json/JSONObject;Lcom/exteragram/messenger/ai/data/Service;Ljava/lang/String;Z)V

    if-eqz v1, :cond_4

    .line 196
    const-string p0, "max_output_tokens"

    goto :goto_3

    :cond_4
    const-string p0, "max_tokens"

    :goto_3
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->maxOutputTokens()I

    move-result v4

    invoke-virtual {v2, p0, v4}, Lorg/json/JSONObject;->put(Ljava/lang/String;I)Lorg/json/JSONObject;

    const/4 p0, 0x0

    if-eqz v1, :cond_5

    .line 198
    const-string v1, "store"

    invoke-virtual {v2, v1, p0}, Lorg/json/JSONObject;->put(Ljava/lang/String;Z)Lorg/json/JSONObject;

    .line 201
    :cond_5
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "AI_REQUEST_URL: "

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;->url()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    .line 202
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "AI_REQUEST_MODEL: "

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/data/Service;->getModel()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    .line 203
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "AI_REQUEST_MESSAGES: "

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Lorg/json/JSONArray;->length()I

    move-result v3

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v3, ", stream="

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->stream()Z

    move-result v3

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v3, ", image="

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->currentMessage()Lcom/exteragram/messenger/ai/data/Message;

    move-result-object p1

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Message;->getImageData()[B

    move-result-object p1

    if-eqz p1, :cond_6

    const/4 p0, 0x1

    :cond_6
    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 211
    invoke-virtual {v2}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object p0

    .line 212
    const-string p1, "application/json"

    invoke-static {p1}, Lokhttp3/MediaType;->parse(Ljava/lang/String;)Lokhttp3/MediaType;

    move-result-object v1

    .line 210
    invoke-static {p0, v1}, Lokhttp3/RequestBody;->create(Ljava/lang/String;Lokhttp3/MediaType;)Lokhttp3/RequestBody;

    move-result-object p0

    .line 215
    new-instance v1, Lokhttp3/Request$Builder;

    invoke-direct {v1}, Lokhttp3/Request$Builder;-><init>()V

    .line 216
    invoke-virtual {p2}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;->url()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {v1, p2}, Lokhttp3/Request$Builder;->url(Ljava/lang/String;)Lokhttp3/Request$Builder;

    move-result-object p2

    const-string v1, "Content-Type"

    .line 217
    invoke-virtual {p2, v1, p1}, Lokhttp3/Request$Builder;->addHeader(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Request$Builder;

    move-result-object p1

    new-instance p2, Ljava/lang/StringBuilder;

    const-string v1, "Bearer "

    invoke-direct {p2, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 218
    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/data/Service;->getKey()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    const-string v0, "Authorization"

    invoke-virtual {p1, v0, p2}, Lokhttp3/Request$Builder;->addHeader(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Request$Builder;

    move-result-object p1

    const-string p2, "User-Agent"

    .line 219
    invoke-static {}, Lcom/exteragram/messenger/translator/TranslatorUtils;->formatUserAgent()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, p2, v0}, Lokhttp3/Request$Builder;->addHeader(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Request$Builder;

    move-result-object p1

    const-string p2, "HTTP-Referer"

    const-string v0, "exteragram.app"

    .line 220
    invoke-virtual {p1, p2, v0}, Lokhttp3/Request$Builder;->addHeader(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Request$Builder;

    move-result-object p1

    const-string p2, "X-Title"

    const-string v0, "exteraGram"

    .line 221
    invoke-virtual {p1, p2, v0}, Lokhttp3/Request$Builder;->addHeader(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Request$Builder;

    move-result-object p1

    .line 223
    invoke-virtual {p1, p0}, Lokhttp3/Request$Builder;->post(Lokhttp3/RequestBody;)Lokhttp3/Request$Builder;

    move-result-object p0

    invoke-virtual {p0}, Lokhttp3/Request$Builder;->build()Lokhttp3/Request;

    move-result-object p0

    return-object p0

    :catch_0
    move-exception p0

    .line 206
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method private extractErrorMessage(Ljava/lang/String;)Ljava/lang/String;
    .locals 5

    .line 607
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p0

    const/4 v0, 0x0

    if-eqz p0, :cond_0

    return-object v0

    .line 610
    :cond_0
    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    .line 612
    :try_start_0
    const-string p1, "["

    invoke-virtual {p0, p1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    const-string v1, "message"

    const-string v2, "error"

    const/4 v3, 0x0

    if-eqz p1, :cond_5

    .line 613
    :try_start_1
    new-instance p1, Lorg/json/JSONArray;

    invoke-direct {p1, p0}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    .line 614
    :goto_0
    invoke-virtual {p1}, Lorg/json/JSONArray;->length()I

    move-result p0

    if-ge v3, p0, :cond_4

    .line 615
    invoke-virtual {p1, v3}, Lorg/json/JSONArray;->optJSONObject(I)Lorg/json/JSONObject;

    move-result-object p0

    if-nez p0, :cond_1

    move-object p0, v0

    goto :goto_1

    .line 616
    :cond_1
    invoke-virtual {p0, v2}, Lorg/json/JSONObject;->optJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object p0

    :goto_1
    if-nez p0, :cond_2

    move-object p0, v0

    goto :goto_2

    .line 617
    :cond_2
    invoke-virtual {p0, v1, v0}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 618
    :goto_2
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_3

    return-object p0

    :cond_3
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_4
    return-object v0

    .line 624
    :cond_5
    const-string p1, "{"

    invoke-virtual {p0, p1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_9

    .line 625
    new-instance p1, Lorg/json/JSONObject;

    invoke-direct {p1, p0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 626
    invoke-virtual {p1, v2}, Lorg/json/JSONObject;->optJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object p0

    if-eqz p0, :cond_6

    .line 628
    invoke-virtual {p0, v1, v0}, Lorg/json/JSONObject;->optString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 630
    :cond_6
    sget-object p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->ERROR_MESSAGE_KEYS:[Ljava/lang/String;

    array-length v1, p0

    :goto_3
    if-ge v3, v1, :cond_8

    aget-object v2, p0, v3

    .line 631
    invoke-virtual {p1, v2}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v2

    instance-of v4, v2, Ljava/lang/String;

    if-eqz v4, :cond_7

    check-cast v2, Ljava/lang/String;

    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v4
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    if-nez v4, :cond_7

    return-object v2

    :cond_7
    add-int/lit8 v3, v3, 0x1

    goto :goto_3

    :cond_8
    return-object v0

    .line 640
    :cond_9
    const-string p1, "<"

    invoke-virtual {p0, p1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p1

    if-nez p1, :cond_a

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p1

    const/16 v1, 0xc8

    if-gt p1, v1, :cond_a

    const/16 p1, 0xa

    invoke-virtual {p0, p1}, Ljava/lang/String;->indexOf(I)I

    move-result p1

    if-gez p1, :cond_a

    return-object p0

    :catch_0
    :cond_a
    return-object v0
.end method

.method private getGeminiReasoningEffort(Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    .line 276
    const-string p0, "gemini-2.5"

    invoke-virtual {p1, p0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_0

    const-string p0, "pro"

    invoke-virtual {p1, p0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p0

    if-nez p0, :cond_0

    .line 277
    const-string p0, "none"

    return-object p0

    .line 279
    :cond_0
    const-string p0, "minimal"

    return-object p0
.end method

.method private getOpenAiReasoningEffort(Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    .line 292
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->stripProviderPrefix(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    .line 293
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->supportsOpenAiNoReasoning(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_0

    const-string p0, "none"

    return-object p0

    :cond_0
    const-string p0, "minimal"

    return-object p0
.end method

.method private getResponsesStreamError(Lorg/json/JSONObject;)Ljava/lang/String;
    .locals 1

    .line 532
    const-string p0, "error"

    invoke-virtual {p1, p0}, Lorg/json/JSONObject;->optJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object v0

    if-nez v0, :cond_1

    .line 534
    const-string v0, "response"

    invoke-virtual {p1, v0}, Lorg/json/JSONObject;->optJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 535
    invoke-virtual {v0, p0}, Lorg/json/JSONObject;->optJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object p0

    :goto_0
    move-object v0, p0

    goto :goto_1

    :cond_0
    const/4 p0, 0x0

    goto :goto_0

    .line 537
    :cond_1
    :goto_1
    const-string p0, "message"

    if-eqz v0, :cond_2

    invoke-virtual {v0, p0}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    goto :goto_2

    :cond_2
    invoke-virtual {p1, p0}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 538
    :goto_2
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_3

    const-string p0, "Unknown error"

    :cond_3
    return-object p0
.end method

.method private handleStreamResponse(Lokhttp3/ResponseBody;Lcom/exteragram/messenger/ai/network/backend/BackendSink;Z)V
    .locals 8

    .line 345
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    .line 346
    new-instance v1, Lcom/exteragram/messenger/ai/network/backend/RestBackend$ReasoningContentFilter;

    const/4 v2, 0x0

    invoke-direct {v1, v2}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$ReasoningContentFilter;-><init>(Lcom/exteragram/messenger/ai/network/backend/RestBackend-IA;)V

    .line 350
    :try_start_0
    new-instance v3, Ljava/io/BufferedReader;

    new-instance v4, Ljava/io/InputStreamReader;

    invoke-virtual {p1}, Lokhttp3/ResponseBody;->byteStream()Ljava/io/InputStream;

    move-result-object p1

    invoke-direct {v4, p1}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;)V

    invoke-direct {v3, v4}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    const/4 p1, 0x0

    move v4, p1

    .line 352
    :cond_0
    :goto_0
    :try_start_1
    invoke-virtual {v3}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v5

    if-eqz v5, :cond_9

    .line 353
    invoke-interface {p2}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->isActive()Z

    move-result v6

    if-nez v6, :cond_1

    goto/16 :goto_2

    .line 356
    :cond_1
    invoke-static {v5}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v6

    if-eqz v6, :cond_2

    goto :goto_0

    .line 357
    :cond_2
    const-string v6, "data:"

    invoke-virtual {v5, v6}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_0

    const/4 v6, 0x5

    .line 358
    invoke-virtual {v5, v6}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v5

    .line 361
    const-string v6, "[DONE]"

    invoke-virtual {v5, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v6

    if-eqz v6, :cond_3

    .line 362
    sget-object v5, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->DONE:Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;

    goto :goto_1

    :catchall_0
    move-exception p1

    goto :goto_3

    :cond_3
    if-eqz p3, :cond_4

    .line 364
    invoke-direct {p0, v5}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->parseResponsesStreamPart(Ljava/lang/String;)Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;

    move-result-object v5

    goto :goto_1

    .line 366
    :cond_4
    invoke-direct {p0, v5}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->parseStreamResponsePart(Ljava/lang/String;)Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;

    move-result-object v5

    .line 369
    :goto_1
    invoke-virtual {v5}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->error()Ljava/lang/String;

    move-result-object v6

    if-eqz v6, :cond_5

    .line 370
    invoke-virtual {v5}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->error()Ljava/lang/String;

    move-result-object v2

    goto :goto_2

    .line 373
    :cond_5
    invoke-virtual {v5}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->hasReasoning()Z

    move-result v6

    if-eqz v6, :cond_6

    .line 374
    invoke-interface {p2}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onThinking()V

    .line 377
    :cond_6
    invoke-virtual {v5}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->content()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v1, v6}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$ReasoningContentFilter;->filter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    .line 378
    invoke-virtual {v1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$ReasoningContentFilter;->consumeReasoningSignal()Z

    move-result v7

    if-eqz v7, :cond_7

    .line 379
    invoke-interface {p2}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onThinking()V

    .line 382
    :cond_7
    invoke-static {v6}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v7

    if-nez v7, :cond_8

    .line 383
    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 384
    invoke-virtual {v6}, Ljava/lang/String;->length()I

    move-result v6

    add-int/2addr v4, v6

    .line 385
    sget v6, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->STREAM_SYMBOLS_LIMIT:I

    if-lt v4, v6, :cond_8

    .line 386
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-interface {p2, v4}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onChunk(Ljava/lang/String;)V

    move v4, p1

    .line 391
    :cond_8
    invoke-virtual {v5}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->done()Z

    move-result v5

    if-eqz v5, :cond_0

    if-lez v4, :cond_9

    .line 393
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-interface {p2, p1}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onChunk(Ljava/lang/String;)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 399
    :cond_9
    :goto_2
    :try_start_2
    invoke-virtual {v3}, Ljava/io/BufferedReader;->close()V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    goto :goto_7

    :catch_0
    move-exception p1

    goto :goto_5

    .line 350
    :goto_3
    :try_start_3
    invoke-virtual {v3}, Ljava/io/BufferedReader;->close()V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    goto :goto_4

    :catchall_1
    move-exception p3

    :try_start_4
    invoke-virtual {p1, p3}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :goto_4
    throw p1
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_0

    .line 400
    :goto_5
    invoke-interface {p2}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->isActive()Z

    move-result p3

    if-eqz p3, :cond_b

    .line 401
    invoke-static {p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 402
    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object p3

    if-eqz p3, :cond_a

    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object p1

    :goto_6
    move-object v2, p1

    goto :goto_7

    :cond_a
    const-string p1, "Unknown error"

    goto :goto_6

    .line 406
    :cond_b
    :goto_7
    invoke-interface {p2}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->isActive()Z

    move-result p1

    if-nez p1, :cond_c

    goto :goto_8

    :cond_c
    if-eqz v2, :cond_d

    const/16 p0, 0x1f4

    .line 411
    invoke-interface {p2, p0, v2}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onError(ILjava/lang/String;)V

    return-void

    .line 415
    :cond_d
    invoke-virtual {v1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$ReasoningContentFilter;->flush()Ljava/lang/String;

    move-result-object p1

    .line 416
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p3

    if-nez p3, :cond_e

    .line 417
    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 418
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-interface {p2, p1}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onChunk(Ljava/lang/String;)V

    .line 421
    :cond_e
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->trimTrailing(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 422
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_f

    .line 423
    invoke-interface {p2, p0}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onComplete(Ljava/lang/String;)V

    goto :goto_8

    :cond_f
    const/16 p0, 0xcc

    .line 425
    const-string p1, "Response body is empty"

    invoke-interface {p2, p0, p1}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onError(ILjava/lang/String;)V

    :goto_8
    return-void
.end method

.method private hasReasoning(Lorg/json/JSONObject;)Z
    .locals 1

    .line 542
    const-string v0, "reasoning"

    invoke-direct {p0, p1, v0}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->hasValue(Lorg/json/JSONObject;Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_1

    const-string v0, "reasoning_content"

    .line 543
    invoke-direct {p0, p1, v0}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->hasValue(Lorg/json/JSONObject;Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_1

    const-string v0, "reasoning_details"

    .line 544
    invoke-direct {p0, p1, v0}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->hasValue(Lorg/json/JSONObject;Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method private hasValue(Lorg/json/JSONObject;Ljava/lang/String;)Z
    .locals 1

    const/4 p0, 0x0

    if-eqz p1, :cond_4

    .line 548
    invoke-virtual {p1, p2}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_4

    invoke-virtual {p1, p2}, Lorg/json/JSONObject;->isNull(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 551
    :cond_0
    invoke-virtual {p1, p2}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p1

    .line 552
    instance-of p2, p1, Ljava/lang/String;

    const/4 v0, 0x1

    if-eqz p2, :cond_1

    check-cast p1, Ljava/lang/String;

    .line 553
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p0

    xor-int/2addr p0, v0

    return p0

    .line 555
    :cond_1
    instance-of p2, p1, Lorg/json/JSONArray;

    if-eqz p2, :cond_3

    check-cast p1, Lorg/json/JSONArray;

    .line 556
    invoke-virtual {p1}, Lorg/json/JSONArray;->length()I

    move-result p1

    if-lez p1, :cond_2

    return v0

    :cond_2
    return p0

    :cond_3
    if-eqz p1, :cond_4

    .line 558
    sget-object p2, Lorg/json/JSONObject;->NULL:Ljava/lang/Object;

    if-eq p1, p2, :cond_4

    return v0

    :cond_4
    :goto_0
    return p0
.end method

.method private isGeminiReasoningModel(Ljava/lang/String;)Z
    .locals 0

    .line 261
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->stripProviderPrefix(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 262
    const-string p1, "gemini-2.5"

    invoke-virtual {p0, p1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p1

    if-nez p1, :cond_1

    const-string p1, "gemini-3"

    .line 263
    invoke-virtual {p0, p1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p1

    if-nez p1, :cond_1

    const-string p1, "thinking"

    .line 264
    invoke-virtual {p0, p1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method private isGeminiService(Ljava/lang/String;)Z
    .locals 0

    .line 257
    const-string p0, "generativelanguage.googleapis"

    invoke-virtual {p1, p0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p0

    return p0
.end method

.method private isOpenAiReasoningModel(Ljava/lang/String;)Z
    .locals 0

    .line 283
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->stripProviderPrefix(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 284
    const-string p1, "gpt-5-chat"

    invoke-virtual {p0, p1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_1

    const-string p1, "gpt-5"

    .line 285
    invoke-virtual {p0, p1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p1

    if-nez p1, :cond_0

    const-string p1, "o1"

    .line 286
    invoke-virtual {p0, p1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p1

    if-nez p1, :cond_0

    const-string p1, "o3"

    .line 287
    invoke-virtual {p0, p1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p1

    if-nez p1, :cond_0

    const-string p1, "o4"

    .line 288
    invoke-virtual {p0, p1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_1

    :cond_0
    const/4 p0, 0x1

    return p0

    :cond_1
    const/4 p0, 0x0

    return p0
.end method

.method private isOpenAiService(Ljava/lang/String;)Z
    .locals 0

    .line 268
    const-string p0, "api.openai.com"

    invoke-virtual {p1, p0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p0

    return p0
.end method

.method private isPerplexityService(Ljava/lang/String;)Z
    .locals 0

    .line 272
    const-string p0, "api.perplexity.ai"

    invoke-virtual {p1, p0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p0

    return p0
.end method

.method private parseErrorMessage(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    .line 599
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->extractErrorMessage(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 600
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_0

    return-object p0

    .line 603
    :cond_0
    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p0

    if-nez p0, :cond_1

    sget-object p0, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-virtual {p2, p0}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_1
    const-string p0, ""

    return-object p0
.end method

.method private parseResponseContent(Ljava/lang/String;Z)Ljava/lang/String;
    .locals 3

    .line 431
    const-string v0, "content"

    const/4 v1, 0x0

    :try_start_0
    new-instance v2, Lorg/json/JSONObject;

    invoke-direct {v2, p1}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    if-eqz p2, :cond_1

    .line 433
    invoke-direct {p0, v2}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->parseResponsesOutput(Lorg/json/JSONObject;)Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 434
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->stripReasoningMarkup(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :catch_0
    move-exception p0

    goto :goto_1

    :cond_0
    return-object v1

    .line 437
    :cond_1
    const-string p1, "choices"

    invoke-virtual {v2, p1}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object p1

    if-eqz p1, :cond_5

    .line 439
    invoke-virtual {p1}, Lorg/json/JSONArray;->length()I

    move-result p2

    if-lez p2, :cond_5

    const/4 p2, 0x0

    .line 440
    invoke-virtual {p1, p2}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object p1

    .line 441
    const-string p2, "message"

    invoke-virtual {p1, p2}, Lorg/json/JSONObject;->optJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object p1

    if-eqz p1, :cond_4

    .line 443
    invoke-virtual {p1, v0}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result p2

    if-eqz p2, :cond_4

    invoke-virtual {p1, v0}, Lorg/json/JSONObject;->isNull(Ljava/lang/String;)Z

    move-result p2

    if-eqz p2, :cond_2

    goto :goto_0

    .line 447
    :cond_2
    invoke-virtual {p1, v0}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p1

    if-nez p1, :cond_3

    return-object v1

    .line 451
    :cond_3
    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    .line 452
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->stripReasoningMarkup(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :cond_4
    :goto_0
    return-object v1

    .line 455
    :goto_1
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :cond_5
    return-object v1
.end method

.method private parseResponsesOutput(Lorg/json/JSONObject;)Ljava/lang/String;
    .locals 9

    .line 461
    const-string p0, "output"

    invoke-virtual {p1, p0}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object p0

    const/4 p1, 0x0

    if-nez p0, :cond_0

    return-object p1

    .line 466
    :cond_0
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const/4 v1, 0x0

    move v2, v1

    .line 467
    :goto_0
    invoke-virtual {p0}, Lorg/json/JSONArray;->length()I

    move-result v3

    if-ge v2, v3, :cond_5

    .line 468
    invoke-virtual {p0, v2}, Lorg/json/JSONArray;->optJSONObject(I)Lorg/json/JSONObject;

    move-result-object v3

    .line 469
    const-string v4, "type"

    if-eqz v3, :cond_1

    const-string v5, "message"

    invoke-virtual {v3, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_1

    const-string v5, "content"

    invoke-virtual {v3, v5}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v3

    goto :goto_1

    :cond_1
    move-object v3, p1

    :goto_1
    if-nez v3, :cond_2

    goto :goto_3

    :cond_2
    move v5, v1

    .line 473
    :goto_2
    invoke-virtual {v3}, Lorg/json/JSONArray;->length()I

    move-result v6

    if-ge v5, v6, :cond_4

    .line 474
    invoke-virtual {v3, v5}, Lorg/json/JSONArray;->optJSONObject(I)Lorg/json/JSONObject;

    move-result-object v6

    if-eqz v6, :cond_3

    .line 475
    const-string v7, "output_text"

    invoke-virtual {v6, v4}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v7, v8}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-eqz v7, :cond_3

    .line 476
    const-string v7, "text"

    invoke-virtual {v6, v7}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_3
    add-int/lit8 v5, v5, 0x1

    goto :goto_2

    :cond_4
    :goto_3
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 480
    :cond_5
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private parseResponsesStreamPart(Ljava/lang/String;)Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;
    .locals 10

    .line 506
    const-string v0, "type"

    const-string v1, ""

    const/4 v2, 0x0

    :try_start_0
    new-instance v3, Lorg/json/JSONObject;

    invoke-direct {v3, p1}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 507
    invoke-virtual {v3, v0}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    .line 508
    invoke-virtual {p1}, Ljava/lang/String;->hashCode()I

    move-result v4

    sparse-switch v4, :sswitch_data_0

    goto/16 :goto_3

    :sswitch_0
    const-string p0, "response.output_item.added"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_1

    .line 519
    const-string p0, "item"

    invoke-virtual {v3, p0}, Lorg/json/JSONObject;->optJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object p0

    .line 520
    new-instance p1, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;

    if-eqz p0, :cond_0

    const-string v3, "reasoning"

    invoke-virtual {p0, v0}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v3, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    move p0, v2

    :goto_0
    invoke-direct {p1, v1, p0}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;-><init>(Ljava/lang/String;Z)V

    return-object p1

    .line 508
    :sswitch_1
    const-string p0, "response.output_text.delta"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_1

    .line 510
    new-instance p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;

    const-string p1, "delta"

    invoke-virtual {v3, p1}, Lorg/json/JSONObject;->optString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1, v2}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;-><init>(Ljava/lang/String;Z)V

    return-object p0

    .line 508
    :sswitch_2
    const-string p0, "response.completed"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_1

    goto :goto_2

    :sswitch_3
    const-string v0, "error"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    goto :goto_1

    :sswitch_4
    const-string v0, "response.failed"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 516
    :goto_1
    new-instance v4, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;

    const-string v5, ""

    invoke-direct {p0, v3}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->getResponsesStreamError(Lorg/json/JSONObject;)Ljava/lang/String;

    move-result-object v8

    const/4 v9, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x1

    invoke-direct/range {v4 .. v9}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;-><init>(Ljava/lang/String;ZZLjava/lang/String;Lcom/exteragram/messenger/ai/network/backend/RestBackend-IA;)V

    return-object v4

    .line 508
    :sswitch_5
    const-string p0, "response.incomplete"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_1

    .line 513
    :goto_2
    sget-object p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;->DONE:Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;

    return-object p0

    .line 523
    :cond_1
    :goto_3
    new-instance p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;

    const-string v0, "response.reasoning"

    invoke-virtual {p1, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p1

    invoke-direct {p0, v1, p1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;-><init>(Ljava/lang/String;Z)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    .line 528
    :catch_0
    new-instance p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;

    invoke-direct {p0, v1, v2}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;-><init>(Ljava/lang/String;Z)V

    return-object p0

    :sswitch_data_0
    .sparse-switch
        -0x3a2c2ed5 -> :sswitch_5
        -0xaf3f576 -> :sswitch_4
        0x5c4d208 -> :sswitch_3
        0x3092c0de -> :sswitch_2
        0x6d6f6148 -> :sswitch_1
        0x761d37f6 -> :sswitch_0
    .end sparse-switch
.end method

.method private parseStreamResponsePart(Ljava/lang/String;)Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;
    .locals 4

    .line 485
    const-string v0, ""

    const/4 v1, 0x0

    :try_start_0
    new-instance v2, Lorg/json/JSONObject;

    invoke-direct {v2, p1}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    .line 486
    const-string p1, "choices"

    invoke-virtual {v2, p1}, Lorg/json/JSONObject;->optJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object p1

    if-eqz p1, :cond_3

    .line 488
    invoke-virtual {p1}, Lorg/json/JSONArray;->length()I

    move-result v2

    if-lez v2, :cond_3

    .line 489
    invoke-virtual {p1, v1}, Lorg/json/JSONArray;->getJSONObject(I)Lorg/json/JSONObject;

    move-result-object p1

    .line 490
    const-string v2, "delta"

    invoke-virtual {p1, v2}, Lorg/json/JSONObject;->optJSONObject(Ljava/lang/String;)Lorg/json/JSONObject;

    move-result-object p1

    if-nez p1, :cond_0

    .line 492
    new-instance p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;

    invoke-direct {p0, v0, v1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;-><init>(Ljava/lang/String;Z)V

    return-object p0

    .line 495
    :cond_0
    const-string v2, "content"

    invoke-virtual {p1, v2}, Lorg/json/JSONObject;->opt(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v2

    if-eqz v2, :cond_2

    .line 496
    sget-object v3, Lorg/json/JSONObject;->NULL:Ljava/lang/Object;

    if-ne v2, v3, :cond_1

    goto :goto_0

    :cond_1
    invoke-virtual {v2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v2

    goto :goto_1

    :cond_2
    :goto_0
    move-object v2, v0

    .line 497
    :goto_1
    new-instance v3, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;

    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->hasReasoning(Lorg/json/JSONObject;)Z

    move-result p0

    invoke-direct {v3, v2, p0}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;-><init>(Ljava/lang/String;Z)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object v3

    .line 501
    :catch_0
    :cond_3
    new-instance p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;

    invoke-direct {p0, v0, v1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$StreamResponsePart;-><init>(Ljava/lang/String;Z)V

    return-object p0
.end method

.method private resolveEndpoint(Ljava/lang/String;)Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;
    .locals 5

    .line 148
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    return-object v1

    .line 151
    :cond_0
    sget-object v0, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-virtual {p1, v0}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object v2

    .line 152
    invoke-direct {p0, v2}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->isGeminiService(Ljava/lang/String;)Z

    move-result v3

    const/4 v4, 0x0

    if-eqz v3, :cond_1

    .line 153
    new-instance p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;

    const-string p1, "https://generativelanguage.googleapis.com/v1beta/openai/chat/completions"

    invoke-direct {p0, p1, v4, v1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;-><init>(Ljava/lang/String;ZLcom/exteragram/messenger/ai/network/backend/RestBackend-IA;)V

    return-object p0

    .line 155
    :cond_1
    invoke-direct {p0, v2}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->isPerplexityService(Ljava/lang/String;)Z

    move-result p0

    const/4 v3, 0x1

    if-eqz p0, :cond_2

    const-string p0, "/router"

    invoke-virtual {v2, p0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p0

    if-nez p0, :cond_2

    .line 156
    new-instance p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;

    const-string p1, "https://api.perplexity.ai/v1/agent"

    invoke-direct {p0, p1, v3, v1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;-><init>(Ljava/lang/String;ZLcom/exteragram/messenger/ai/network/backend/RestBackend-IA;)V

    return-object p0

    .line 159
    :cond_2
    const-string p0, "/"

    invoke-virtual {p1, p0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_3

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result p0

    sub-int/2addr p0, v3

    invoke-virtual {p1, v4, p0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p1

    .line 160
    :cond_3
    invoke-virtual {p1, v0}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p0

    .line 161
    const-string v0, "/responses"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_6

    const-string v0, "/agent"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_4

    goto :goto_1

    .line 164
    :cond_4
    new-instance v0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;

    const-string v2, "/chat/completions"

    invoke-virtual {p0, v2}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_5

    goto :goto_0

    :cond_5
    invoke-virtual {p1, v2}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    :goto_0
    invoke-direct {v0, p1, v4, v1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;-><init>(Ljava/lang/String;ZLcom/exteragram/messenger/ai/network/backend/RestBackend-IA;)V

    return-object v0

    .line 162
    :cond_6
    :goto_1
    new-instance p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;

    invoke-direct {p0, p1, v3, v1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;-><init>(Ljava/lang/String;ZLcom/exteragram/messenger/ai/network/backend/RestBackend-IA;)V

    return-object p0
.end method

.method private stripProviderPrefix(Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    const/16 p0, 0x2f

    .line 305
    invoke-virtual {p1, p0}, Ljava/lang/String;->indexOf(I)I

    move-result p0

    if-ltz p0, :cond_0

    add-int/lit8 p0, p0, 0x1

    .line 306
    invoke-virtual {p1, p0}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_0
    return-object p1
.end method

.method private stripReasoningMarkup(Ljava/lang/String;)Ljava/lang/String;
    .locals 4

    .line 562
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v1

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    .line 563
    sget-object v1, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-virtual {p1, v1}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    .line 565
    :goto_0
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v3

    if-ge v2, v3, :cond_2

    .line 566
    const-string v3, "<think>"

    invoke-virtual {v1, v3, v2}, Ljava/lang/String;->indexOf(Ljava/lang/String;I)I

    move-result v3

    if-gez v3, :cond_0

    .line 568
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v1

    invoke-virtual {v0, p1, v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/CharSequence;II)Ljava/lang/StringBuilder;

    goto :goto_1

    .line 572
    :cond_0
    invoke-virtual {v0, p1, v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/CharSequence;II)Ljava/lang/StringBuilder;

    add-int/lit8 v3, v3, 0x7

    .line 573
    const-string v2, "</think>"

    invoke-virtual {v1, v2, v3}, Ljava/lang/String;->indexOf(Ljava/lang/String;I)I

    move-result v2

    if-gez v2, :cond_1

    goto :goto_1

    :cond_1
    add-int/lit8 v2, v2, 0x8

    goto :goto_0

    .line 579
    :cond_2
    :goto_1
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->trimLeading(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private supportsOpenAiNoReasoning(Ljava/lang/String;)Z
    .locals 0

    .line 297
    const-string p0, "gpt-5.1"

    invoke-virtual {p1, p0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p0

    if-nez p0, :cond_1

    const-string p0, "gpt-5.2"

    .line 298
    invoke-virtual {p1, p0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p0

    if-nez p0, :cond_1

    const-string p0, "gpt-5.3"

    .line 299
    invoke-virtual {p1, p0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p0

    if-nez p0, :cond_1

    const-string p0, "gpt-5.4"

    .line 300
    invoke-virtual {p1, p0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p0

    if-nez p0, :cond_1

    const-string p0, "gpt-5.5"

    .line 301
    invoke-virtual {p1, p0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method private trimLeading(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    const/4 p0, 0x0

    .line 584
    :goto_0
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v0

    if-ge p0, v0, :cond_0

    invoke-virtual {p1, p0}, Ljava/lang/String;->charAt(I)C

    move-result v0

    invoke-static {v0}, Ljava/lang/Character;->isWhitespace(C)Z

    move-result v0

    if-eqz v0, :cond_0

    add-int/lit8 p0, p0, 0x1

    goto :goto_0

    .line 587
    :cond_0
    invoke-virtual {p1, p0}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private trimTrailing(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    .line 591
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result p0

    :goto_0
    if-lez p0, :cond_0

    add-int/lit8 v0, p0, -0x1

    .line 592
    invoke-virtual {p1, v0}, Ljava/lang/String;->charAt(I)C

    move-result v0

    invoke-static {v0}, Ljava/lang/Character;->isWhitespace(C)Z

    move-result v0

    if-eqz v0, :cond_0

    add-int/lit8 p0, p0, -0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    .line 595
    invoke-virtual {p1, v0, p0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public execute(Lcom/exteragram/messenger/ai/network/backend/BackendRequest;Lcom/exteragram/messenger/ai/network/backend/BackendSink;)V
    .locals 6

    .line 98
    const-string v0, "AI_ERROR_RESPONSE_BODY ("

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->service()Lcom/exteragram/messenger/ai/data/Service;

    move-result-object v1

    invoke-virtual {v1}, Lcom/exteragram/messenger/ai/data/Service;->getUrl()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->resolveEndpoint(Ljava/lang/String;)Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;

    move-result-object v1

    const/4 v2, 0x0

    if-eqz v1, :cond_0

    .line 99
    invoke-direct {p0, p1, v1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->createRequest(Lcom/exteragram/messenger/ai/network/backend/BackendRequest;Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;)Lokhttp3/Request;

    move-result-object v3

    goto :goto_0

    :cond_0
    move-object v3, v2

    :goto_0
    const/16 v4, 0x1f4

    if-nez v3, :cond_1

    .line 101
    const-string p0, "Failed to create request body"

    invoke-interface {p2, v4, p0}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onError(ILjava/lang/String;)V

    return-void

    .line 105
    :cond_1
    invoke-interface {p2}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->isActive()Z

    move-result v5

    if-nez v5, :cond_2

    return-void

    .line 109
    :cond_2
    iget-object v5, p0, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->httpClient:Lokhttp3/OkHttpClient;

    invoke-virtual {v5, v3}, Lokhttp3/OkHttpClient;->newCall(Lokhttp3/Request;)Lokhttp3/Call;

    move-result-object v3

    .line 110
    invoke-static {v3}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v5, Lcom/exteragram/messenger/ai/network/backend/RestBackend$$ExternalSyntheticLambda0;

    invoke-direct {v5, v3}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$$ExternalSyntheticLambda0;-><init>(Lokhttp3/Call;)V

    invoke-interface {p2, v5}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onCancellable(Ljava/lang/Runnable;)V

    .line 112
    invoke-interface {v3}, Lokhttp3/Call;->execute()Lokhttp3/Response;

    move-result-object v3

    .line 113
    :try_start_0
    invoke-virtual {v3}, Lokhttp3/Response;->isSuccessful()Z

    move-result v5
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-nez v5, :cond_3

    .line 116
    :try_start_1
    invoke-virtual {v3}, Lokhttp3/Response;->body()Lokhttp3/ResponseBody;

    move-result-object p1

    invoke-virtual {p1}, Lokhttp3/ResponseBody;->string()Ljava/lang/String;

    move-result-object v2

    .line 117
    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3}, Lokhttp3/Response;->code()I

    move-result v0

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, "): "

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;)V
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_1

    :catchall_0
    move-exception p0

    goto :goto_3

    :catch_0
    move-exception p1

    .line 119
    :try_start_2
    const-string v0, "AI_ERROR_READING_RESPONSE_BODY: "

    invoke-static {v0, p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 121
    :goto_1
    invoke-virtual {v3}, Lokhttp3/Response;->code()I

    move-result p1

    invoke-virtual {v3}, Lokhttp3/Response;->message()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v2, v0}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->parseErrorMessage(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-interface {p2, p1, p0}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onError(ILjava/lang/String;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 144
    invoke-virtual {v3}, Lokhttp3/Response;->close()V

    return-void

    .line 125
    :cond_3
    :try_start_3
    invoke-virtual {v3}, Lokhttp3/Response;->body()Lokhttp3/ResponseBody;

    move-result-object v0

    .line 126
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->stream()Z

    move-result p1

    if-eqz p1, :cond_4

    .line 127
    invoke-virtual {v1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;->responsesApi()Z

    move-result p1

    invoke-direct {p0, v0, p2, p1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->handleStreamResponse(Lokhttp3/ResponseBody;Lcom/exteragram/messenger/ai/network/backend/BackendSink;Z)V

    goto :goto_2

    .line 129
    :cond_4
    invoke-virtual {v0}, Lokhttp3/ResponseBody;->string()Ljava/lang/String;

    move-result-object p1

    .line 130
    invoke-virtual {v1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend$Endpoint;->responsesApi()Z

    move-result v0

    invoke-direct {p0, p1, v0}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->parseResponseContent(Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v0

    .line 131
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_5

    .line 132
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;->extractErrorMessage(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 133
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_5

    .line 134
    invoke-interface {p2, v4, p0}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onError(ILjava/lang/String;)V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 144
    invoke-virtual {v3}, Lokhttp3/Response;->close()V

    return-void

    :cond_5
    if-nez v0, :cond_6

    .line 139
    :try_start_4
    const-string p0, "Failed to parse response"

    invoke-interface {p2, v4, p0}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onError(ILjava/lang/String;)V
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    .line 144
    invoke-virtual {v3}, Lokhttp3/Response;->close()V

    return-void

    .line 142
    :cond_6
    :try_start_5
    invoke-interface {p2, v0}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onComplete(Ljava/lang/String;)V
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    .line 144
    :goto_2
    invoke-virtual {v3}, Lokhttp3/Response;->close()V

    return-void

    :goto_3
    if-eqz v3, :cond_7

    .line 112
    :try_start_6
    invoke-virtual {v3}, Lokhttp3/Response;->close()V
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_1

    goto :goto_4

    :catchall_1
    move-exception p1

    invoke-virtual {p0, p1}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :cond_7
    :goto_4
    throw p0
.end method

.method public getHistoryCharsLimit()I
    .locals 0

    const/16 p0, 0x5dc0

    return p0
.end method

.method public getHistoryMessagesLimit()I
    .locals 0

    const/16 p0, 0x20

    return p0
.end method

.method public getMaxOutputTokens()I
    .locals 0

    const/16 p0, 0x1000

    return p0
.end method

.method public getMaxTemperature()F
    .locals 0

    const/high16 p0, 0x40000000    # 2.0f

    return p0
.end method

.method public supportsImages()Z
    .locals 0

    const/4 p0, 0x1

    return p0
.end method
