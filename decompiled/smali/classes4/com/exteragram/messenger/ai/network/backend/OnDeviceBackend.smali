.class public Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/exteragram/messenger/ai/network/backend/Backend;


# static fields
.field private static volatile cachingUnsupported:Z


# direct methods
.method public static synthetic $r8$lambda$ou-CoPEgPbw-7-oeG85KvVCFEWs(Lcom/google/common/util/concurrent/ListenableFuture;)V
    .locals 1

    const/4 v0, 0x1

    .line 252
    invoke-interface {p0, v0}, Ljava/util/concurrent/Future;->cancel(Z)Z

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .line 49
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private buildInstruction(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    .line 354
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    .line 355
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 356
    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "\n\n"

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 358
    :cond_0
    const-string p1, "Unless told otherwise, always reply in the same language the user writes in."

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 359
    invoke-static {}, Lorg/telegram/messenger/LocaleController;->getInstance()Lorg/telegram/messenger/LocaleController;

    move-result-object p1

    invoke-virtual {p1}, Lorg/telegram/messenger/LocaleController;->getCurrentLocale()Ljava/util/Locale;

    move-result-object p1

    if-eqz p1, :cond_1

    .line 360
    sget-object v0, Ljava/util/Locale;->ENGLISH:Ljava/util/Locale;

    invoke-virtual {p1, v0}, Ljava/util/Locale;->getDisplayLanguage(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_1
    const/4 p1, 0x0

    .line 361
    :goto_0
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_2

    .line 362
    const-string v0, " If the language is unclear, reply in "

    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 p1, 0x2e

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    .line 364
    :cond_2
    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private buildPrompt(Ljava/util/List;Lcom/exteragram/messenger/ai/data/Message;I)Ljava/lang/String;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/ai/data/Message;",
            ">;",
            "Lcom/exteragram/messenger/ai/data/Message;",
            "I)",
            "Ljava/lang/String;"
        }
    .end annotation

    .line 368
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    .line 369
    :goto_0
    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v0

    const-string v1, "User: "

    if-ge p3, v0, :cond_3

    .line 370
    invoke-interface {p1, p3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/ai/data/Message;

    if-eqz v0, :cond_2

    .line 371
    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/data/Message;->content()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_0

    goto :goto_1

    .line 374
    :cond_0
    const-string v2, "assistant"

    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/data/Message;->role()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_1

    const-string v1, "Assistant: "

    :cond_1
    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 375
    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/data/Message;->content()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, "\n\n"

    .line 376
    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    :cond_2
    :goto_1
    add-int/lit8 p3, p3, 0x1

    goto :goto_0

    .line 378
    :cond_3
    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Lcom/exteragram/messenger/ai/data/Message;->content()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 379
    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private countTokens(Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;Lcom/google/mlkit/genai/prompt/GenerateContentRequest;)I
    .locals 2

    .line 403
    :try_start_0
    invoke-virtual {p1, p2}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;->countTokens(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    sget-object p1, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    const-wide/16 v0, 0xa

    invoke-interface {p0, v0, v1, p1}, Ljava/util/concurrent/Future;->get(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/mlkit/genai/prompt/CountTokensResponse;

    invoke-virtual {p0}, Lcom/google/mlkit/genai/prompt/CountTokensResponse;->getTotalTokens()I

    move-result p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return p0

    :catch_0
    move-exception p0

    .line 405
    const-string p1, "AI_ON_DEVICE_COUNT_TOKENS"

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    const/4 p0, 0x0

    return p0
.end method

.method private createImagePart(Lcom/exteragram/messenger/ai/data/Message;)Lcom/google/mlkit/genai/prompt/ImagePart;
    .locals 1

    .line 384
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Message;->getImageData()[B

    move-result-object p0

    const/4 p1, 0x0

    if-eqz p0, :cond_1

    .line 385
    array-length v0, p0

    if-nez v0, :cond_0

    goto :goto_0

    .line 389
    :cond_0
    :try_start_0
    new-instance v0, Lcom/google/mlkit/genai/prompt/ImagePart;

    invoke-direct {v0, p0}, Lcom/google/mlkit/genai/prompt/ImagePart;-><init>([B)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    move-exception p0

    .line 391
    const-string v0, "AI_ON_DEVICE_IMAGE_REJECTED"

    invoke-static {v0, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_1
    :goto_0
    return-object p1
.end method

.method private createRequest(Lcom/exteragram/messenger/ai/network/backend/BackendRequest;Lcom/google/mlkit/genai/prompt/SystemInstruction;Ljava/lang/String;Lcom/google/mlkit/genai/prompt/ImagePart;Ljava/lang/String;Z)Lcom/google/mlkit/genai/prompt/GenerateContentRequest;
    .locals 0

    .line 286
    new-instance p0, Lcom/google/mlkit/genai/prompt/TextPart;

    invoke-direct {p0, p5}, Lcom/google/mlkit/genai/prompt/TextPart;-><init>(Ljava/lang/String;)V

    if-eqz p2, :cond_0

    if-eqz p4, :cond_0

    .line 289
    new-instance p5, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;

    invoke-direct {p5, p2, p4, p0}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;-><init>(Lcom/google/mlkit/genai/prompt/SystemInstruction;Lcom/google/mlkit/genai/prompt/ImagePart;Lcom/google/mlkit/genai/prompt/TextPart;)V

    goto :goto_0

    :cond_0
    if-eqz p2, :cond_1

    .line 291
    new-instance p5, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;

    invoke-direct {p5, p2, p0}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;-><init>(Lcom/google/mlkit/genai/prompt/SystemInstruction;Lcom/google/mlkit/genai/prompt/TextPart;)V

    goto :goto_0

    :cond_1
    if-eqz p4, :cond_2

    .line 293
    new-instance p5, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;

    invoke-direct {p5, p4, p0}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;-><init>(Lcom/google/mlkit/genai/prompt/ImagePart;Lcom/google/mlkit/genai/prompt/TextPart;)V

    goto :goto_0

    .line 295
    :cond_2
    new-instance p5, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;

    invoke-direct {p5, p0}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;-><init>(Lcom/google/mlkit/genai/prompt/TextPart;)V

    .line 297
    :goto_0
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->temperature()F

    move-result p0

    invoke-static {p0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object p0

    invoke-virtual {p5, p0}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->setTemperature(Ljava/lang/Float;)V

    .line 298
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->maxOutputTokens()I

    move-result p0

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    invoke-virtual {p5, p0}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->setMaxOutputTokens(Ljava/lang/Integer;)V

    .line 299
    invoke-virtual {p5, p6}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->setEnableThinking(Z)V

    if-eqz p3, :cond_3

    .line 301
    invoke-virtual {p5, p3}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->setCachedContextName(Ljava/lang/String;)V

    .line 303
    :cond_3
    invoke-virtual {p5}, Lcom/google/mlkit/genai/prompt/GenerateContentRequest$Builder;->build()Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    move-result-object p0

    return-object p0
.end method

.method private ensureCachedContext(Lcom/google/mlkit/genai/prompt/GenerativeModel;Ljava/lang/String;)Ljava/lang/String;
    .locals 9

    .line 308
    sget-object p0, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    sget-boolean v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;->cachingUnsupported:Z

    const/4 v1, 0x0

    if-nez v0, :cond_9

    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto/16 :goto_4

    .line 311
    :cond_0
    invoke-static {p2}, Lorg/telegram/messenger/Utilities;->MD5(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 312
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_1

    return-object v1

    .line 316
    :cond_1
    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "extera_role_"

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 318
    :try_start_0
    invoke-static {p1}, Lcom/google/mlkit/genai/prompt/java/CachesFutures;->from(Lcom/google/mlkit/genai/prompt/GenerativeModel;)Lcom/google/mlkit/genai/prompt/java/CachesFutures;

    move-result-object p1

    .line 319
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    .line 320
    invoke-virtual {p1}, Lcom/google/mlkit/genai/prompt/java/CachesFutures;->list()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v4

    const-wide/16 v5, 0xa

    invoke-interface {v4, v5, v6, p0}, Ljava/util/concurrent/Future;->get(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/util/List;

    if-eqz v4, :cond_6

    .line 322
    invoke-interface {v4}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v4

    :cond_2
    :goto_0
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v7

    if-eqz v7, :cond_6

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lcom/google/mlkit/genai/prompt/CachedContext;

    if-nez v7, :cond_3

    move-object v7, v1

    goto :goto_1

    .line 323
    :cond_3
    invoke-virtual {v7}, Lcom/google/mlkit/genai/prompt/CachedContext;->getName()Ljava/lang/String;

    move-result-object v7

    :goto_1
    if-eqz v7, :cond_2

    .line 324
    invoke-virtual {v7, v3}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v8

    if-nez v8, :cond_4

    goto :goto_0

    .line 327
    :cond_4
    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v8

    if-eqz v8, :cond_5

    return-object v0

    .line 330
    :cond_5
    invoke-interface {v2, v7}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :catch_0
    move-exception p0

    goto :goto_3

    .line 333
    :cond_6
    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v3

    const/4 v4, 0x4

    if-lt v3, v4, :cond_7

    .line 334
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v3

    const/4 v4, 0x0

    :goto_2
    if-ge v4, v3, :cond_7

    invoke-virtual {v2, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v7

    add-int/lit8 v4, v4, 0x1

    check-cast v7, Ljava/lang/String;

    .line 335
    invoke-virtual {p1, v7}, Lcom/google/mlkit/genai/prompt/java/CachesFutures;->delete(Ljava/lang/String;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v7

    invoke-interface {v7, v5, v6, p0}, Ljava/util/concurrent/Future;->get(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;

    goto :goto_2

    .line 339
    :cond_7
    new-instance v2, Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest$Builder;

    new-instance v3, Lcom/google/mlkit/genai/prompt/PromptPrefix;

    invoke-direct {v3, p2}, Lcom/google/mlkit/genai/prompt/PromptPrefix;-><init>(Ljava/lang/String;)V

    invoke-direct {v2, v0, v3}, Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest$Builder;-><init>(Ljava/lang/String;Lcom/google/mlkit/genai/prompt/PromptPrefix;)V

    .line 340
    invoke-virtual {v2}, Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest$Builder;->build()Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/google/mlkit/genai/prompt/java/CachesFutures;->create(Lcom/google/mlkit/genai/prompt/CreateCachedContextRequest;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p1

    const-wide/16 v2, 0x1e

    .line 341
    invoke-interface {p1, v2, v3, p0}, Ljava/util/concurrent/Future;->get(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/mlkit/genai/prompt/CachedContext;

    if-nez p0, :cond_8

    return-object v1

    .line 342
    :cond_8
    invoke-virtual {p0}, Lcom/google/mlkit/genai/prompt/CachedContext;->getName()Ljava/lang/String;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/InterruptedException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :goto_3
    const/4 p1, 0x1

    .line 347
    sput-boolean p1, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;->cachingUnsupported:Z

    .line 348
    const-string p1, "AI_ON_DEVICE_CACHE"

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-object v1

    .line 344
    :catch_1
    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Thread;->interrupt()V

    :cond_9
    :goto_4
    return-object v1
.end method

.method private extractText(Lcom/google/mlkit/genai/prompt/GenerateContentResponse;)Ljava/lang/String;
    .locals 1

    const/4 p0, 0x0

    if-nez p1, :cond_0

    return-object p0

    .line 414
    :cond_0
    invoke-virtual {p1}, Lcom/google/mlkit/genai/prompt/GenerateContentResponse;->getCandidates()Ljava/util/List;

    move-result-object p1

    if-eqz p1, :cond_2

    .line 415
    invoke-interface {p1}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_1

    goto :goto_0

    :cond_1
    const/4 p0, 0x0

    .line 418
    invoke-interface {p1, p0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/mlkit/genai/prompt/Candidate;

    invoke-virtual {p0}, Lcom/google/mlkit/genai/prompt/Candidate;->getText()Ljava/lang/String;

    move-result-object p0

    :cond_2
    :goto_0
    return-object p0
.end method

.method private static findGenAiException(Ljava/lang/Throwable;)Lcom/google/mlkit/genai/common/GenAiException;
    .locals 1

    :goto_0
    if-eqz p0, :cond_1

    .line 158
    instance-of v0, p0, Lcom/google/mlkit/genai/common/GenAiException;

    if-eqz v0, :cond_0

    check-cast p0, Lcom/google/mlkit/genai/common/GenAiException;

    return-object p0

    .line 157
    :cond_0
    invoke-virtual {p0}, Ljava/lang/Throwable;->getCause()Ljava/lang/Throwable;

    move-result-object p0

    goto :goto_0

    :cond_1
    const/4 p0, 0x0

    return-object p0
.end method

.method private generate(Lcom/exteragram/messenger/ai/network/backend/BackendRequest;Lcom/exteragram/messenger/ai/network/backend/BackendSink;Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;Lcom/google/mlkit/genai/prompt/GenerativeModel;)V
    .locals 16

    move-object/from16 v0, p0

    move-object/from16 v7, p2

    move-object/from16 v8, p3

    .line 182
    invoke-virtual/range {p1 .. p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->role()Lcom/exteragram/messenger/ai/data/Role;

    move-result-object v1

    const/4 v9, 0x0

    if-eqz v1, :cond_0

    .line 183
    invoke-virtual {v1}, Lcom/exteragram/messenger/ai/data/Role;->getPrompt()Ljava/lang/String;

    move-result-object v1

    goto :goto_0

    :cond_0
    move-object v1, v9

    :goto_0
    invoke-direct {v0, v1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;->buildInstruction(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v10

    .line 184
    invoke-virtual/range {p1 .. p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->service()Lcom/exteragram/messenger/ai/data/Service;

    move-result-object v1

    invoke-virtual {v1}, Lcom/exteragram/messenger/ai/data/Service;->isReasoningEnabled()Z

    move-result v1

    const/4 v2, 0x0

    if-eqz v1, :cond_1

    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->isThinkingSupported()Z

    move-result v1

    if-eqz v1, :cond_1

    const/4 v1, 0x1

    move v6, v1

    goto :goto_1

    :cond_1
    move v6, v2

    .line 188
    :goto_1
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->isSystemPromptSupported()Z

    move-result v1

    if-eqz v1, :cond_2

    .line 189
    new-instance v1, Lcom/google/mlkit/genai/prompt/SystemInstruction;

    invoke-direct {v1, v10}, Lcom/google/mlkit/genai/prompt/SystemInstruction;-><init>(Ljava/lang/String;)V

    const-string v3, ""

    :goto_2
    move-object v11, v3

    goto :goto_3

    .line 191
    :cond_2
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "\n\n"

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    move-object v1, v9

    goto :goto_2

    .line 194
    :goto_3
    invoke-virtual/range {p1 .. p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->currentMessage()Lcom/exteragram/messenger/ai/data/Message;

    move-result-object v3

    invoke-direct {v0, v3}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;->createImagePart(Lcom/exteragram/messenger/ai/data/Message;)Lcom/google/mlkit/genai/prompt/ImagePart;

    move-result-object v4

    .line 195
    invoke-virtual/range {p1 .. p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->history()Ljava/util/List;

    move-result-object v12

    .line 196
    invoke-direct {v0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;->resolveTokenLimit()I

    move-result v3

    invoke-virtual/range {p1 .. p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->maxOutputTokens()I

    move-result v5

    sub-int/2addr v3, v5

    add-int/lit16 v13, v3, -0x100

    move v14, v2

    .line 202
    :goto_4
    invoke-interface {v7}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->isActive()Z

    move-result v2

    if-nez v2, :cond_3

    goto/16 :goto_9

    .line 205
    :cond_3
    invoke-virtual/range {p1 .. p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->currentMessage()Lcom/exteragram/messenger/ai/data/Message;

    move-result-object v2

    invoke-direct {v0, v12, v2, v14}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;->buildPrompt(Ljava/util/List;Lcom/exteragram/messenger/ai/data/Message;I)Ljava/lang/String;

    move-result-object v15

    .line 206
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    const/4 v3, 0x0

    move-object v2, v1

    move-object/from16 v1, p1

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;->createRequest(Lcom/exteragram/messenger/ai/network/backend/BackendRequest;Lcom/google/mlkit/genai/prompt/SystemInstruction;Ljava/lang/String;Lcom/google/mlkit/genai/prompt/ImagePart;Ljava/lang/String;Z)Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    move-result-object v3

    invoke-direct {v0, v8, v3}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;->countTokens(Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;Lcom/google/mlkit/genai/prompt/GenerateContentRequest;)I

    move-result v1

    if-le v1, v13, :cond_5

    .line 207
    invoke-interface {v12}, Ljava/util/List;->size()I

    move-result v3

    if-lt v14, v3, :cond_4

    goto :goto_5

    :cond_4
    add-int/lit8 v14, v14, 0x2

    move-object v1, v2

    goto :goto_4

    :cond_5
    :goto_5
    if-le v1, v13, :cond_6

    const/4 v0, -0x4

    .line 214
    const-string v1, "prompt exceeds the on-device token limit"

    invoke-interface {v7, v0, v1}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onError(ILjava/lang/String;)V

    return-void

    .line 218
    :cond_6
    invoke-interface {v7}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->isActive()Z

    move-result v1

    if-nez v1, :cond_7

    goto/16 :goto_9

    :cond_7
    if-nez v2, :cond_8

    if-nez v4, :cond_8

    move-object/from16 v1, p4

    .line 222
    invoke-direct {v0, v1, v10}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;->ensureCachedContext(Lcom/google/mlkit/genai/prompt/GenerativeModel;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    :cond_8
    move-object v3, v9

    if-eqz v3, :cond_9

    const/4 v2, 0x0

    move-object/from16 v1, p1

    move-object v5, v15

    .line 224
    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;->createRequest(Lcom/exteragram/messenger/ai/network/backend/BackendRequest;Lcom/google/mlkit/genai/prompt/SystemInstruction;Ljava/lang/String;Lcom/google/mlkit/genai/prompt/ImagePart;Ljava/lang/String;Z)Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    move-result-object v2

    move-object/from16 v0, p0

    goto :goto_6

    :cond_9
    move-object v5, v15

    .line 225
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    const/4 v3, 0x0

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;->createRequest(Lcom/exteragram/messenger/ai/network/backend/BackendRequest;Lcom/google/mlkit/genai/prompt/SystemInstruction;Ljava/lang/String;Lcom/google/mlkit/genai/prompt/ImagePart;Ljava/lang/String;Z)Lcom/google/mlkit/genai/prompt/GenerateContentRequest;

    move-result-object v2

    .line 227
    :goto_6
    new-instance v1, Ljava/lang/StringBuffer;

    invoke-direct {v1}, Ljava/lang/StringBuffer;-><init>()V

    .line 230
    invoke-virtual/range {p1 .. p1}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;->stream()Z

    move-result v3

    if-eqz v3, :cond_a

    .line 231
    new-instance v3, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend$1;

    invoke-direct {v3, v0, v7, v1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend$1;-><init>(Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;Lcom/exteragram/messenger/ai/network/backend/BackendSink;Ljava/lang/StringBuffer;)V

    invoke-virtual {v8, v2, v3}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;->generateContent(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;Lcom/google/mlkit/genai/common/StreamingCallback;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v2

    goto :goto_7

    .line 249
    :cond_a
    invoke-virtual {v8, v2}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;->generateContent(Lcom/google/mlkit/genai/prompt/GenerateContentRequest;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v2

    .line 252
    :goto_7
    new-instance v3, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend$$ExternalSyntheticLambda0;

    invoke-direct {v3, v2}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend$$ExternalSyntheticLambda0;-><init>(Lcom/google/common/util/concurrent/ListenableFuture;)V

    invoke-interface {v7, v3}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onCancellable(Ljava/lang/Runnable;)V

    .line 256
    :try_start_0
    invoke-interface {v2}, Ljava/util/concurrent/Future;->get()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/mlkit/genai/prompt/GenerateContentResponse;
    :try_end_0
    .catch Ljava/util/concurrent/ExecutionException; {:try_start_0 .. :try_end_0} :catch_0

    .line 268
    invoke-interface {v7}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->isActive()Z

    move-result v3

    if-nez v3, :cond_b

    goto :goto_9

    .line 272
    :cond_b
    invoke-direct {v0, v2}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;->extractText(Lcom/google/mlkit/genai/prompt/GenerateContentResponse;)Ljava/lang/String;

    move-result-object v2

    .line 273
    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-eqz v3, :cond_c

    .line 274
    invoke-virtual {v1}, Ljava/lang/StringBuffer;->toString()Ljava/lang/String;

    move-result-object v2

    .line 276
    :cond_c
    invoke-direct {v0, v2}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;->trimTrailing(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 278
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_d

    const/16 v0, 0xcc

    .line 279
    const-string v1, "Response body is empty"

    invoke-interface {v7, v0, v1}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onError(ILjava/lang/String;)V

    return-void

    .line 281
    :cond_d
    invoke-interface {v7, v0}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onComplete(Ljava/lang/String;)V

    return-void

    :catch_0
    move-exception v0

    .line 258
    invoke-static {v0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;->findGenAiException(Ljava/lang/Throwable;)Lcom/google/mlkit/genai/common/GenAiException;

    move-result-object v1

    if-eqz v1, :cond_10

    .line 259
    invoke-virtual {v1}, Lcom/google/mlkit/genai/common/GenAiException;->getErrorCode()I

    move-result v2

    invoke-static {v2}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;->isInferenceFailure(I)Z

    move-result v2

    if-eqz v2, :cond_10

    .line 262
    invoke-interface {v7}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->isActive()Z

    move-result v2

    if-eqz v2, :cond_f

    .line 263
    const-string v2, "AI_ON_DEVICE_INFERENCE_FAILED"

    invoke-static {v2, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 264
    invoke-virtual {v1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_e

    invoke-virtual {v1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v0

    goto :goto_8

    :cond_e
    const-string v0, "Inference failed"

    :goto_8
    const/4 v1, -0x5

    invoke-interface {v7, v1, v0}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onError(ILjava/lang/String;)V

    :cond_f
    :goto_9
    return-void

    .line 260
    :cond_10
    throw v0
.end method

.method private static isInferenceFailure(I)Z
    .locals 1

    const/16 v0, -0x65

    if-eq p0, v0, :cond_0

    const/16 v0, 0xc

    if-eq p0, v0, :cond_0

    const/16 v0, 0x10

    if-eq p0, v0, :cond_0

    const/16 v0, 0x1b

    if-eq p0, v0, :cond_0

    const/16 v0, 0x1e

    if-eq p0, v0, :cond_0

    const/16 v0, 0x1f5

    if-eq p0, v0, :cond_0

    const/16 v0, 0x25c

    if-eq p0, v0, :cond_0

    const/4 v0, 0x7

    if-eq p0, v0, :cond_0

    const/16 v0, 0x8

    if-eq p0, v0, :cond_0

    const/16 v0, 0x9

    if-eq p0, v0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private resolveTokenLimit()I
    .locals 0

    .line 397
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->getTokenLimit()I

    move-result p0

    if-lez p0, :cond_0

    return p0

    :cond_0
    const/16 p0, 0xfa0

    return p0
.end method

.method private trimTrailing(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    if-nez p1, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 425
    :cond_0
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result p0

    :goto_0
    if-lez p0, :cond_1

    add-int/lit8 v0, p0, -0x1

    .line 426
    invoke-virtual {p1, v0}, Ljava/lang/String;->charAt(I)C

    move-result v0

    invoke-static {v0}, Ljava/lang/Character;->isWhitespace(C)Z

    move-result v0

    if-eqz v0, :cond_1

    add-int/lit8 p0, p0, -0x1

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    .line 429
    invoke-virtual {p1, v0, p0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public execute(Lcom/exteragram/messenger/ai/network/backend/BackendRequest;Lcom/exteragram/messenger/ai/network/backend/BackendSink;)V
    .locals 8

    .line 98
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x1a

    const/4 v2, -0x1

    if-ge v0, v1, :cond_0

    .line 99
    const-string p0, "on-device model requires android 8.0"

    invoke-interface {p2, v2, p0}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onError(ILjava/lang/String;)V

    return-void

    .line 104
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->getConfigGeneration()I

    move-result v0

    const/4 v1, 0x0

    .line 106
    :try_start_0
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->createModel()Lcom/google/mlkit/genai/prompt/GenerativeModel;

    move-result-object v1

    .line 107
    invoke-static {v1}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;->from(Lcom/google/mlkit/genai/prompt/GenerativeModel;)Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;

    move-result-object v3

    .line 109
    invoke-virtual {v3}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;->checkStatus()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v4

    sget-object v5, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    const-wide/16 v6, 0xa

    invoke-interface {v4, v6, v7, v5}, Ljava/util/concurrent/Future;->get(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/Integer;

    invoke-virtual {v4}, Ljava/lang/Integer;->intValue()I

    move-result v4

    .line 110
    invoke-static {v4, v0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->report(II)V

    .line 112
    invoke-interface {p2}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->isActive()Z

    move-result v5

    if-nez v5, :cond_1

    if-eqz v1, :cond_8

    goto :goto_0

    :cond_1
    if-nez v4, :cond_2

    .line 117
    const-string p0, "on-device model is not available"

    invoke-interface {p2, v2, p0}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onError(ILjava/lang/String;)V

    if-eqz v1, :cond_8

    goto :goto_0

    :catchall_0
    move-exception p0

    goto/16 :goto_6

    :catch_0
    move-exception p0

    goto :goto_1

    :cond_2
    const/4 v2, 0x1

    if-ne v4, v2, :cond_3

    .line 121
    const-string p0, "on-device model is not downloaded"

    const/4 p1, -0x2

    invoke-interface {p2, p1, p0}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onError(ILjava/lang/String;)V

    if-eqz v1, :cond_8

    goto :goto_0

    :cond_3
    const/4 v2, 0x2

    if-ne v4, v2, :cond_4

    .line 125
    const-string p0, "on-device model is downloading"

    const/4 p1, -0x3

    invoke-interface {p2, p1, p0}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onError(ILjava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/InterruptedException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v1, :cond_8

    .line 148
    :goto_0
    :try_start_1
    invoke-interface {v1}, Lcom/google/mlkit/genai/prompt/GenerativeModel;->close()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    :catchall_1
    return-void

    .line 129
    :cond_4
    :try_start_2
    invoke-static {v3, v0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->ensureMetadata(Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;I)V

    .line 131
    invoke-direct {p0, p1, p2, v3, v1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;->generate(Lcom/exteragram/messenger/ai/network/backend/BackendRequest;Lcom/exteragram/messenger/ai/network/backend/BackendSink;Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;Lcom/google/mlkit/genai/prompt/GenerativeModel;)V
    :try_end_2
    .catch Ljava/lang/InterruptedException; {:try_start_2 .. :try_end_2} :catch_1
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    if-eqz v1, :cond_8

    .line 148
    :try_start_3
    invoke-interface {v1}, Lcom/google/mlkit/genai/prompt/GenerativeModel;->close()V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_2

    return-void

    .line 135
    :goto_1
    :try_start_4
    invoke-interface {p2}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->isActive()Z

    move-result p1

    if-eqz p1, :cond_7

    .line 136
    const-string p1, "AI_ON_DEVICE_FAILED"

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 137
    invoke-static {p0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;->findGenAiException(Ljava/lang/Throwable;)Lcom/google/mlkit/genai/common/GenAiException;

    move-result-object p1

    .line 138
    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_5

    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object p0

    goto :goto_2

    :cond_5
    const-string p0, "Unknown error"

    :goto_2
    if-eqz p1, :cond_6

    .line 139
    invoke-virtual {p1}, Lcom/google/mlkit/genai/common/GenAiException;->getErrorCode()I

    move-result p1

    const/16 v0, 0xc

    if-ne p1, v0, :cond_6

    const/4 p1, -0x4

    .line 140
    invoke-interface {p2, p1, p0}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onError(ILjava/lang/String;)V

    goto :goto_3

    :cond_6
    const/16 p1, 0x1f4

    .line 142
    invoke-interface {p2, p1, p0}, Lcom/exteragram/messenger/ai/network/backend/BackendSink;->onError(ILjava/lang/String;)V
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    :cond_7
    :goto_3
    if-eqz v1, :cond_8

    .line 148
    :goto_4
    :try_start_5
    invoke-interface {v1}, Lcom/google/mlkit/genai/prompt/GenerativeModel;->close()V
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_2

    goto :goto_5

    .line 133
    :catch_1
    :try_start_6
    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Thread;->interrupt()V
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_0

    if-eqz v1, :cond_8

    goto :goto_4

    :catchall_2
    :cond_8
    :goto_5
    return-void

    :goto_6
    if-eqz v1, :cond_9

    .line 148
    :try_start_7
    invoke-interface {v1}, Lcom/google/mlkit/genai/prompt/GenerativeModel;->close()V
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_3

    .line 152
    :catchall_3
    :cond_9
    throw p0
.end method

.method public getHistoryCharsLimit()I
    .locals 0

    const/16 p0, 0x2ee0

    return p0
.end method

.method public getHistoryMessagesLimit()I
    .locals 0

    const/16 p0, 0x18

    return p0
.end method

.method public getMaxOutputTokens()I
    .locals 0

    const/16 p0, 0x400

    return p0
.end method

.method public getMaxTemperature()F
    .locals 0

    const/high16 p0, 0x3f800000    # 1.0f

    return p0
.end method

.method public supportsImages()Z
    .locals 0

    const/4 p0, 0x1

    return p0
.end method
