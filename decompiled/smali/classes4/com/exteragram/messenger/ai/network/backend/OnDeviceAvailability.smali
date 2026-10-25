.class public abstract Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;
    }
.end annotation


# static fields
.field private static final configGeneration:Ljava/util/concurrent/atomic/AtomicInteger;

.field private static final downloadPercents:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/Integer;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private static final downloadingModels:Ljava/util/Set;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Set<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private static volatile modelName:Ljava/lang/String;

.field private static volatile modelNameGeneration:I

.field private static volatile otherModelSupported:Z

.field private static final pendingCallbacks:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Ljava/lang/Runnable;",
            ">;"
        }
    .end annotation
.end field

.field private static recheckRequested:Z

.field private static final refreshedOnce:Ljava/util/concurrent/atomic/AtomicBoolean;

.field private static final refreshing:Ljava/util/concurrent/atomic/AtomicBoolean;

.field private static final restored:Ljava/util/concurrent/atomic/AtomicBoolean;

.field private static final sessionStatuses:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/Integer;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private static volatile status:I

.field private static volatile supportedThisSession:Z

.field private static volatile systemPromptSupported:Z

.field private static volatile thinkingSupported:Z

.field private static volatile tokenLimit:I

.field private static final warmedUp:Ljava/util/concurrent/atomic/AtomicBoolean;


# direct methods
.method public static synthetic $r8$lambda$KicUEHGdAl2FIno-2ohu8c5fiD0()V
    .locals 6

    const/4 v0, 0x0

    const/4 v1, 0x0

    .line 397
    :try_start_0
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->createModel()Lcom/google/mlkit/genai/prompt/GenerativeModel;

    move-result-object v1

    .line 398
    invoke-static {v1}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;->from(Lcom/google/mlkit/genai/prompt/GenerativeModel;)Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;

    move-result-object v2

    invoke-virtual {v2}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;->warmup()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v2

    sget-object v3, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    const-wide/16 v4, 0x1e

    invoke-interface {v2, v4, v5, v3}, Ljava/util/concurrent/Future;->get(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/InterruptedException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 406
    invoke-static {v1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->closeQuietly(Lcom/google/mlkit/genai/prompt/GenerativeModel;)V

    return-void

    :catchall_0
    move-exception v2

    .line 403
    :try_start_1
    sget-object v3, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->warmedUp:Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-virtual {v3, v0}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V

    .line 404
    const-string v0, "AI_ON_DEVICE_WARMUP"

    invoke-static {v0, v2}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    .line 406
    :goto_0
    invoke-static {v1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->closeQuietly(Lcom/google/mlkit/genai/prompt/GenerativeModel;)V

    goto :goto_1

    :catchall_1
    move-exception v0

    goto :goto_2

    .line 400
    :catch_0
    :try_start_2
    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Thread;->interrupt()V

    .line 401
    sget-object v2, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->warmedUp:Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-virtual {v2, v0}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    goto :goto_0

    :goto_1
    return-void

    .line 406
    :goto_2
    invoke-static {v1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->closeQuietly(Lcom/google/mlkit/genai/prompt/GenerativeModel;)V

    .line 407
    throw v0
.end method

.method public static synthetic $r8$lambda$Yv1A38WI11jz5Zzz_QcDbhaaM4E()V
    .locals 0

    .line 0
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->check()V

    return-void
.end method

.method public static synthetic $r8$lambda$edA7BpxC7ENFv9jOjeLPcqoKwKs(Ljava/lang/Runnable;Ljava/util/concurrent/ExecutorService;)V
    .locals 0

    .line 514
    :try_start_0
    invoke-interface {p0}, Ljava/lang/Runnable;->run()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 516
    invoke-interface {p1}, Ljava/util/concurrent/ExecutorService;->shutdown()V

    return-void

    :catchall_0
    move-exception p0

    invoke-interface {p1}, Ljava/util/concurrent/ExecutorService;->shutdown()V

    .line 517
    throw p0
.end method

.method public static synthetic $r8$lambda$gRihwTHakZKAHuErdKTQDB0RBd8()V
    .locals 3

    .line 507
    sget v0, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->servicesUpdated:I

    const/4 v2, 0x0

    new-array v2, v2, [Ljava/lang/Object;

    invoke-virtual {v0, v1, v2}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    return-void
.end method

.method public static synthetic $r8$lambda$zfvooT6TwoW5Ai-QwCsZshX1_Yg(ILcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;)V
    .locals 0

    .line 421
    invoke-static {p0, p1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->runDownload(ILcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$sfgetdownloadPercents()Ljava/util/Map;
    .locals 1

    .line 0
    sget-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->downloadPercents:Ljava/util/Map;

    return-object v0
.end method

.method public static bridge synthetic -$$Nest$smnotifyServicesUpdated()V
    .locals 0

    .line 0
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->notifyServicesUpdated()V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 1

    .line 62
    new-instance v0, Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-direct {v0}, Ljava/util/concurrent/atomic/AtomicBoolean;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->refreshing:Ljava/util/concurrent/atomic/AtomicBoolean;

    .line 63
    invoke-static {}, Ljava/util/concurrent/ConcurrentHashMap;->newKeySet()Ljava/util/concurrent/ConcurrentHashMap$KeySetView;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->downloadingModels:Ljava/util/Set;

    .line 64
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->downloadPercents:Ljava/util/Map;

    .line 65
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->sessionStatuses:Ljava/util/Map;

    .line 66
    new-instance v0, Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-direct {v0}, Ljava/util/concurrent/atomic/AtomicBoolean;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->restored:Ljava/util/concurrent/atomic/AtomicBoolean;

    .line 67
    new-instance v0, Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-direct {v0}, Ljava/util/concurrent/atomic/AtomicBoolean;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->refreshedOnce:Ljava/util/concurrent/atomic/AtomicBoolean;

    .line 68
    new-instance v0, Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-direct {v0}, Ljava/util/concurrent/atomic/AtomicBoolean;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->warmedUp:Ljava/util/concurrent/atomic/AtomicBoolean;

    .line 69
    new-instance v0, Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-direct {v0}, Ljava/util/concurrent/atomic/AtomicInteger;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->configGeneration:Ljava/util/concurrent/atomic/AtomicInteger;

    .line 70
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->pendingCallbacks:Ljava/util/List;

    const/4 v0, -0x1

    .line 73
    sput v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->status:I

    return-void
.end method

.method private static apply(I)V
    .locals 2

    .line 494
    invoke-static {p0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->isStatusSupported(I)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    .line 495
    sput-boolean v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->supportedThisSession:Z

    .line 497
    :cond_0
    sget v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->status:I

    if-ne v0, p0, :cond_1

    return-void

    .line 500
    :cond_1
    sput p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->status:I

    .line 501
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "onDeviceFeatureStatus"

    invoke-interface {v0, v1, p0}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 502
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->notifyServicesUpdated()V

    return-void
.end method

.method private static check()V
    .locals 7

    .line 234
    :goto_0
    sget-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->configGeneration:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v1

    .line 235
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->getModelKey()I

    move-result v2

    .line 236
    invoke-static {v1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->checkStatus(I)I

    move-result v3

    .line 237
    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v4

    if-eq v1, v4, :cond_0

    goto :goto_0

    .line 240
    :cond_0
    sget-object v4, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->sessionStatuses:Ljava/util/Map;

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    invoke-interface {v4, v5, v6}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    if-nez v3, :cond_1

    .line 242
    invoke-static {v2}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->updateOtherModelSupported(I)V

    .line 245
    :cond_1
    sget v2, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->modelNameGeneration:I

    if-eq v2, v1, :cond_2

    sget-object v2, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->modelName:Ljava/lang/String;

    if-eqz v2, :cond_2

    const/4 v2, 0x0

    .line 246
    sput-object v2, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->modelName:Ljava/lang/String;

    .line 247
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object v2

    const-string v4, "onDeviceModelName"

    invoke-interface {v2, v4}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v2

    invoke-interface {v2}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 248
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->notifyServicesUpdated()V

    .line 250
    :cond_2
    invoke-static {v3}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->apply(I)V

    .line 253
    sget-object v2, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->pendingCallbacks:Ljava/util/List;

    monitor-enter v2

    .line 254
    :try_start_0
    sget-boolean v3, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->recheckRequested:Z

    const/4 v4, 0x0

    if-nez v3, :cond_5

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v0

    if-eq v1, v0, :cond_3

    goto :goto_2

    .line 258
    :cond_3
    sget-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->refreshing:Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-virtual {v0, v4}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V

    .line 259
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0, v2}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    .line 260
    invoke-interface {v2}, Ljava/util/List;->clear()V

    .line 261
    monitor-exit v2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 262
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    :goto_1
    if-ge v4, v1, :cond_4

    invoke-virtual {v0, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    add-int/lit8 v4, v4, 0x1

    check-cast v2, Ljava/lang/Runnable;

    .line 263
    invoke-static {v2}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->runOnDone(Ljava/lang/Runnable;)V

    goto :goto_1

    :cond_4
    return-void

    :catchall_0
    move-exception v0

    goto :goto_3

    .line 255
    :cond_5
    :goto_2
    :try_start_1
    sput-boolean v4, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->recheckRequested:Z

    .line 256
    monitor-exit v2

    goto :goto_0

    .line 261
    :goto_3
    monitor-exit v2
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method private static checkStatus(I)I
    .locals 7

    const/4 v0, 0x0

    .line 307
    :try_start_0
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->createModel()Lcom/google/mlkit/genai/prompt/GenerativeModel;

    move-result-object v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 308
    :try_start_1
    invoke-static {v1}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;->from(Lcom/google/mlkit/genai/prompt/GenerativeModel;)Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;

    move-result-object v2

    .line 309
    invoke-virtual {v2}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;->checkStatus()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v3

    sget-object v4, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    const-wide/16 v5, 0xa

    invoke-interface {v3, v5, v6, v4}, Ljava/util/concurrent/Future;->get(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/Integer;

    if-nez v3, :cond_0

    const/4 v0, -0x1

    goto :goto_0

    .line 310
    :cond_0
    invoke-virtual {v3}, Ljava/lang/Integer;->intValue()I

    move-result v0

    :goto_0
    const/4 v3, 0x3

    if-ne v0, v3, :cond_1

    .line 312
    invoke-static {v2, p0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->readMetadata(Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;I)V

    goto :goto_1

    :catchall_0
    move-exception p0

    goto :goto_2

    .line 313
    :cond_1
    invoke-static {v0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->isStatusSupported(I)Z

    move-result v3

    if-eqz v3, :cond_2

    invoke-static {v2, p0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->readModelName(Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;I)Z

    move-result p0

    if-eqz p0, :cond_2

    .line 314
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->notifyServicesUpdated()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 319
    :cond_2
    :goto_1
    invoke-static {v1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->closeQuietly(Lcom/google/mlkit/genai/prompt/GenerativeModel;)V

    return v0

    :catchall_1
    move-exception p0

    const/4 v1, 0x0

    .line 317
    :goto_2
    :try_start_2
    const-string v2, "AI_ON_DEVICE_STATUS_FAILED"

    invoke-static {v2, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_2

    .line 319
    invoke-static {v1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->closeQuietly(Lcom/google/mlkit/genai/prompt/GenerativeModel;)V

    return v0

    :catchall_2
    move-exception p0

    invoke-static {v1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->closeQuietly(Lcom/google/mlkit/genai/prompt/GenerativeModel;)V

    .line 320
    throw p0
.end method

.method private static closeQuietly(Lcom/google/mlkit/genai/prompt/GenerativeModel;)V
    .locals 0

    if-nez p0, :cond_0

    return-void

    .line 526
    :cond_0
    :try_start_0
    invoke-interface {p0}, Lcom/google/mlkit/genai/prompt/GenerativeModel;->close()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    :catchall_0
    return-void
.end method

.method public static createModel()Lcom/google/mlkit/genai/prompt/GenerativeModel;
    .locals 1

    .line 100
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->getModelKey()I

    move-result v0

    invoke-static {v0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->createModel(I)Lcom/google/mlkit/genai/prompt/GenerativeModel;

    move-result-object v0

    return-object v0
.end method

.method private static createModel(I)Lcom/google/mlkit/genai/prompt/GenerativeModel;
    .locals 3

    .line 104
    invoke-static {}, Lcom/google/mlkit/genai/prompt/ModelConfig;->builder()Lcom/google/mlkit/genai/prompt/ModelConfig$Builder;

    move-result-object v0

    and-int/lit8 v1, p0, 0x2

    const/4 v2, 0x1

    if-eqz v1, :cond_0

    move v1, v2

    goto :goto_0

    :cond_0
    const/4 v1, 0x0

    .line 105
    :goto_0
    invoke-virtual {v0, v1}, Lcom/google/mlkit/genai/prompt/ModelConfig$Builder;->setReleaseStage(I)V

    and-int/2addr p0, v2

    if-eqz p0, :cond_1

    goto :goto_1

    :cond_1
    const/4 v2, 0x2

    .line 106
    :goto_1
    invoke-virtual {v0, v2}, Lcom/google/mlkit/genai/prompt/ModelConfig$Builder;->setPreference(I)V

    .line 107
    new-instance p0, Lcom/google/mlkit/genai/prompt/GenerationConfig$Builder;

    invoke-direct {p0}, Lcom/google/mlkit/genai/prompt/GenerationConfig$Builder;-><init>()V

    .line 108
    invoke-virtual {v0}, Lcom/google/mlkit/genai/prompt/ModelConfig$Builder;->build()Lcom/google/mlkit/genai/prompt/ModelConfig;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/google/mlkit/genai/prompt/GenerationConfig$Builder;->setModelConfig(Lcom/google/mlkit/genai/prompt/ModelConfig;)V

    .line 109
    sget-object v0, Lcom/google/mlkit/genai/prompt/Generation;->INSTANCE:Lcom/google/mlkit/genai/prompt/Generation;

    invoke-virtual {p0}, Lcom/google/mlkit/genai/prompt/GenerationConfig$Builder;->build()Lcom/google/mlkit/genai/prompt/GenerationConfig;

    move-result-object p0

    invoke-virtual {v0, p0}, Lcom/google/mlkit/genai/prompt/Generation;->getClient(Lcom/google/mlkit/genai/prompt/GenerationConfig;)Lcom/google/mlkit/genai/prompt/GenerativeModel;

    move-result-object p0

    return-object p0
.end method

.method public static download(ILcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;)V
    .locals 2

    .line 412
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x1a

    if-ge v0, v1, :cond_0

    .line 413
    new-instance p0, Ljava/lang/IllegalStateException;

    const-string v0, "unsupported android version"

    invoke-direct {p0, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    invoke-interface {p1, p0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;->onFailed(Ljava/lang/Exception;)V

    return-void

    .line 416
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->downloadingModels:Ljava/util/Set;

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    return-void

    .line 419
    :cond_1
    sget-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->downloadPercents:Ljava/util/Map;

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/Map;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 420
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->notifyServicesUpdated()V

    .line 421
    new-instance v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$$ExternalSyntheticLambda4;

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$$ExternalSyntheticLambda4;-><init>(ILcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;)V

    invoke-static {v0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->runOnWorker(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static ensureMetadata(Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;I)V
    .locals 1

    .line 168
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->ensureRestored()V

    .line 169
    sget v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->tokenLimit:I

    if-lez v0, :cond_0

    return-void

    .line 172
    :cond_0
    invoke-static {p0, p1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->readMetadata(Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;I)V

    return-void
.end method

.method private static ensureRestored()V
    .locals 4

    .line 483
    sget-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->restored:Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v1, 0x1

    const/4 v2, 0x0

    invoke-virtual {v0, v2, v1}, Ljava/util/concurrent/atomic/AtomicBoolean;->compareAndSet(ZZ)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 484
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object v0

    const-string v1, "onDeviceFeatureStatus"

    const/4 v3, -0x1

    invoke-interface {v0, v1, v3}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result v0

    sput v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->status:I

    .line 485
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object v0

    const-string v1, "onDeviceModelName"

    const/4 v3, 0x0

    invoke-interface {v0, v1, v3}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->modelName:Ljava/lang/String;

    .line 486
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object v0

    const-string v1, "onDeviceThinkingSupported"

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    sput-boolean v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->thinkingSupported:Z

    .line 487
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object v0

    const-string v1, "onDeviceSystemPromptSupported"

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    sput-boolean v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->systemPromptSupported:Z

    .line 488
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object v0

    const-string v1, "onDeviceTokenLimit"

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result v0

    sput v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->tokenLimit:I

    .line 489
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object v0

    const-string v1, "onDeviceOtherModelSupported"

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    sput-boolean v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->otherModelSupported:Z

    :cond_0
    return-void
.end method

.method public static getConfigGeneration()I
    .locals 1

    .line 113
    sget-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->configGeneration:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v0

    return v0
.end method

.method public static getDownloadPercent()I
    .locals 2

    .line 198
    sget-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->downloadPercents:Ljava/util/Map;

    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->getModelKey()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    if-eqz v0, :cond_0

    .line 199
    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    return v0

    :cond_0
    const/4 v0, -0x1

    return v0
.end method

.method public static getModelKey()I
    .locals 2

    .line 96
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getOnDevicePreviewModel()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x2

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getOnDeviceFastModel()Z

    move-result v1

    or-int/2addr v0, v1

    return v0
.end method

.method public static getModelName()Ljava/lang/String;
    .locals 1

    .line 148
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->ensureRestored()V

    .line 149
    sget-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->modelName:Ljava/lang/String;

    return-object v0
.end method

.method public static getStatus()I
    .locals 3

    .line 132
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x1a

    const/4 v2, 0x0

    if-ge v0, v1, :cond_0

    return v2

    .line 135
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->ensureRestored()V

    .line 136
    sget-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->refreshedOnce:Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v1, 0x1

    invoke-virtual {v0, v2, v1}, Ljava/util/concurrent/atomic/AtomicBoolean;->compareAndSet(ZZ)Z

    move-result v0

    if-eqz v0, :cond_1

    const/4 v0, 0x0

    .line 137
    invoke-static {v0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->refresh(Ljava/lang/Runnable;)V

    .line 139
    :cond_1
    sget v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->status:I

    return v0
.end method

.method public static getTokenLimit()I
    .locals 1

    .line 163
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->ensureRestored()V

    .line 164
    sget v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->tokenLimit:I

    return v0
.end method

.method public static isDownloadInProgress()Z
    .locals 2

    .line 194
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->isDownloading()Z

    move-result v0

    if-nez v0, :cond_1

    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->getStatus()I

    move-result v0

    const/4 v1, 0x2

    if-ne v0, v1, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    return v0

    :cond_1
    :goto_0
    const/4 v0, 0x1

    return v0
.end method

.method public static isDownloading()Z
    .locals 2

    .line 190
    sget-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->downloadingModels:Ljava/util/Set;

    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->getModelKey()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result v0

    return v0
.end method

.method public static isReady()Z
    .locals 2

    .line 143
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->getStatus()I

    move-result v0

    const/4 v1, 0x3

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    return v0

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method private static isStatusSupported(I)Z
    .locals 2

    const/4 v0, 0x3

    const/4 v1, 0x1

    if-eq p0, v0, :cond_1

    if-eq p0, v1, :cond_1

    const/4 v0, 0x2

    if-ne p0, v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    return v1
.end method

.method public static isSupported()Z
    .locals 1

    .line 176
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->getStatus()I

    move-result v0

    invoke-static {v0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->isStatusSupported(I)Z

    move-result v0

    if-nez v0, :cond_1

    sget-boolean v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->supportedThisSession:Z

    if-nez v0, :cond_1

    sget-boolean v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->otherModelSupported:Z

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    return v0

    :cond_1
    :goto_0
    const/4 v0, 0x1

    return v0
.end method

.method public static isSystemPromptSupported()Z
    .locals 1

    .line 158
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->ensureRestored()V

    .line 159
    sget-boolean v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->systemPromptSupported:Z

    return v0
.end method

.method public static isThinkingSupported()Z
    .locals 1

    .line 153
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->ensureRestored()V

    .line 154
    sget-boolean v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->thinkingSupported:Z

    return v0
.end method

.method public static needsDownload()Z
    .locals 2

    .line 186
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->getStatus()I

    move-result v0

    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    return v1

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method private static notifyServicesUpdated()V
    .locals 1

    .line 506
    new-instance v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$$ExternalSyntheticLambda1;

    invoke-direct {v0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$$ExternalSyntheticLambda1;-><init>()V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static onModelConfigChanged(Ljava/lang/Runnable;)V
    .locals 2

    .line 117
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->ensureRestored()V

    .line 118
    sget-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->configGeneration:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->incrementAndGet()I

    .line 119
    sget-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->warmedUp:Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V

    .line 120
    sput-boolean v1, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->thinkingSupported:Z

    .line 121
    sput-boolean v1, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->systemPromptSupported:Z

    .line 122
    sput v1, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->tokenLimit:I

    .line 123
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "onDeviceThinkingSupported"

    .line 124
    invoke-interface {v0, v1}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "onDeviceSystemPromptSupported"

    .line 125
    invoke-interface {v0, v1}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "onDeviceTokenLimit"

    .line 126
    invoke-interface {v0, v1}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 127
    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 128
    invoke-static {p0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->refresh(Ljava/lang/Runnable;)V

    return-void
.end method

.method private static probeStatus(I)I
    .locals 4

    .line 292
    :try_start_0
    invoke-static {p0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->createModel(I)Lcom/google/mlkit/genai/prompt/GenerativeModel;

    move-result-object p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 293
    :try_start_1
    invoke-static {p0}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;->from(Lcom/google/mlkit/genai/prompt/GenerativeModel;)Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;

    move-result-object v0

    invoke-virtual {v0}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;->checkStatus()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v0

    sget-object v1, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    const-wide/16 v2, 0xa

    invoke-interface {v0, v2, v3, v1}, Ljava/util/concurrent/Future;->get(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    if-nez v0, :cond_0

    const/4 v0, -0x1

    goto :goto_0

    .line 294
    :cond_0
    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 299
    :goto_0
    invoke-static {p0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->closeQuietly(Lcom/google/mlkit/genai/prompt/GenerativeModel;)V

    return v0

    :catchall_0
    move-exception v0

    goto :goto_1

    :catchall_1
    move-exception v0

    const/4 p0, 0x0

    .line 296
    :goto_1
    :try_start_2
    const-string v1, "AI_ON_DEVICE_PROBE_FAILED"

    invoke-static {v1, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_2

    .line 299
    invoke-static {p0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->closeQuietly(Lcom/google/mlkit/genai/prompt/GenerativeModel;)V

    const/4 p0, 0x0

    return p0

    :catchall_2
    move-exception v0

    invoke-static {p0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->closeQuietly(Lcom/google/mlkit/genai/prompt/GenerativeModel;)V

    .line 300
    throw v0
.end method

.method private static readMetadata(Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;I)V
    .locals 7

    .line 344
    sget-object v0, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    invoke-static {p0, p1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->readModelName(Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;I)Z

    move-result v1

    const-wide/16 v2, 0xa

    .line 346
    :try_start_0
    sget-object v4, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    invoke-virtual {p0}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;->isThinkingModeAvailable()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v5

    invoke-interface {v5, v2, v3, v0}, Ljava/util/concurrent/Future;->get(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/Boolean;->equals(Ljava/lang/Object;)Z

    move-result v4

    .line 347
    sget-object v5, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->configGeneration:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v5}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v5

    if-eq p1, v5, :cond_0

    goto/16 :goto_3

    .line 350
    :cond_0
    sget-boolean v5, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->thinkingSupported:Z

    if-eq v4, v5, :cond_1

    .line 351
    sput-boolean v4, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->thinkingSupported:Z

    .line 352
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object v5

    const-string v6, "onDeviceThinkingSupported"

    invoke-interface {v5, v6, v4}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object v4

    invoke-interface {v4}, Landroid/content/SharedPreferences$Editor;->apply()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    const/4 v1, 0x1

    goto :goto_0

    :catch_0
    move-exception v4

    .line 356
    const-string v5, "AI_ON_DEVICE_THINKING"

    invoke-static {v5, v4}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 359
    :cond_1
    :goto_0
    :try_start_1
    sget-object v4, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    invoke-virtual {p0}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;->isSystemPromptAvailable()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v5

    invoke-interface {v5, v2, v3, v0}, Ljava/util/concurrent/Future;->get(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/Boolean;->equals(Ljava/lang/Object;)Z

    move-result v4

    .line 360
    sget-object v5, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->configGeneration:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v5}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v5

    if-eq p1, v5, :cond_2

    goto :goto_3

    .line 363
    :cond_2
    sget-boolean v5, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->systemPromptSupported:Z

    if-eq v4, v5, :cond_3

    .line 364
    sput-boolean v4, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->systemPromptSupported:Z

    .line 365
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object v5

    const-string v6, "onDeviceSystemPromptSupported"

    invoke-interface {v5, v6, v4}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object v4

    invoke-interface {v4}, Landroid/content/SharedPreferences$Editor;->apply()V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_1

    :catch_1
    move-exception v4

    .line 368
    const-string v5, "AI_ON_DEVICE_SYSTEM_PROMPT"

    invoke-static {v5, v4}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 371
    :cond_3
    :goto_1
    :try_start_2
    invoke-virtual {p0}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;->getTokenLimit()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    invoke-interface {p0, v2, v3, v0}, Ljava/util/concurrent/Future;->get(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Integer;

    .line 372
    sget-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->configGeneration:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v0

    if-eq p1, v0, :cond_4

    goto :goto_3

    :cond_4
    if-eqz p0, :cond_5

    .line 375
    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p1

    if-lez p1, :cond_5

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p1

    sget v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->tokenLimit:I

    if-eq p1, v0, :cond_5

    .line 376
    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p1

    sput p1, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->tokenLimit:I

    .line 377
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object p1

    const-string v0, "onDeviceTokenLimit"

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    invoke-interface {p1, v0, p0}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_2

    goto :goto_2

    :catch_2
    move-exception p0

    .line 380
    const-string p1, "AI_ON_DEVICE_TOKEN_LIMIT"

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_5
    :goto_2
    if-eqz v1, :cond_6

    .line 383
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->notifyServicesUpdated()V

    :cond_6
    :goto_3
    return-void
.end method

.method private static readModelName(Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;I)Z
    .locals 4

    const/4 v0, 0x0

    .line 326
    :try_start_0
    invoke-virtual {p0}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;->getBaseModelName()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object p0

    sget-object v1, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    const-wide/16 v2, 0xa

    invoke-interface {p0, v2, v3, v1}, Ljava/util/concurrent/Future;->get(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/String;

    .line 327
    sget-object v1, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->configGeneration:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v1}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v1

    if-ne p1, v1, :cond_2

    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_0

    .line 330
    :cond_0
    sput p1, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->modelNameGeneration:I

    .line 331
    sget-object p1, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->modelName:Ljava/lang/String;

    invoke-virtual {p0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_1

    return v0

    .line 334
    :cond_1
    sput-object p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->modelName:Ljava/lang/String;

    .line 335
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object p1

    const-string v1, "onDeviceModelName"

    invoke-interface {p1, v1, p0}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    const/4 p0, 0x1

    return p0

    :catch_0
    move-exception p0

    goto :goto_1

    :cond_2
    :goto_0
    return v0

    .line 338
    :goto_1
    const-string p1, "AI_ON_DEVICE_MODEL_NAME"

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return v0
.end method

.method public static refresh(Ljava/lang/Runnable;)V
    .locals 3

    .line 211
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x1a

    const/4 v2, 0x0

    if-ge v0, v1, :cond_0

    .line 212
    invoke-static {v2}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->apply(I)V

    .line 213
    invoke-static {p0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->runOnDone(Ljava/lang/Runnable;)V

    return-void

    .line 217
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->pendingCallbacks:Ljava/util/List;

    monitor-enter v0

    if-eqz p0, :cond_1

    .line 219
    :try_start_0
    invoke-interface {v0, p0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    .line 221
    :cond_1
    :goto_0
    sget-object p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->refreshing:Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v1, 0x1

    invoke-virtual {p0, v2, v1}, Ljava/util/concurrent/atomic/AtomicBoolean;->compareAndSet(ZZ)Z

    move-result p0

    if-nez p0, :cond_2

    .line 222
    sput-boolean v1, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->recheckRequested:Z

    .line 223
    monitor-exit v0

    return-void

    .line 225
    :cond_2
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 227
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->ensureRestored()V

    .line 228
    sget-object p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->refreshedOnce:Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-virtual {p0, v1}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V

    .line 229
    new-instance p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$$ExternalSyntheticLambda0;

    invoke-direct {p0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$$ExternalSyntheticLambda0;-><init>()V

    invoke-static {p0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->runOnWorker(Ljava/lang/Runnable;)V

    return-void

    .line 225
    :goto_1
    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p0
.end method

.method public static report(II)V
    .locals 2

    .line 203
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->ensureRestored()V

    .line 204
    sget-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->refreshedOnce:Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V

    .line 205
    sget-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->configGeneration:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v0

    if-ne p1, v0, :cond_0

    .line 206
    invoke-static {p0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->apply(I)V

    :cond_0
    return-void
.end method

.method private static runDownload(ILcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;)V
    .locals 10

    .line 425
    const-string v0, "AI_ON_DEVICE_DOWNLOAD"

    const-string v1, "model is not available after download, status="

    const/4 v2, 0x0

    .line 427
    :try_start_0
    invoke-static {p0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->createModel(I)Lcom/google/mlkit/genai/prompt/GenerativeModel;

    move-result-object v3
    :try_end_0
    .catch Ljava/lang/InterruptedException; {:try_start_0 .. :try_end_0} :catch_3
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_2
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 428
    :try_start_1
    invoke-static {v3}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;->from(Lcom/google/mlkit/genai/prompt/GenerativeModel;)Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;

    move-result-object v4

    const/4 v5, 0x1

    .line 429
    new-array v5, v5, [J

    const/4 v6, 0x0

    const-wide/16 v7, 0x0

    aput-wide v7, v5, v6

    .line 431
    new-instance v7, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$1;

    invoke-direct {v7, v5, p1, p0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$1;-><init>([JLcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;I)V

    invoke-virtual {v4, v7}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;->download(Lcom/google/mlkit/genai/common/DownloadCallback;)Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v7

    .line 454
    invoke-interface {v7}, Ljava/util/concurrent/Future;->get()Ljava/lang/Object;

    .line 456
    invoke-virtual {v4}, Lcom/google/mlkit/genai/prompt/java/GenerativeModelFutures;->checkStatus()Lcom/google/common/util/concurrent/ListenableFuture;

    move-result-object v4

    sget-object v7, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    const-wide/16 v8, 0xa

    invoke-interface {v4, v8, v9, v7}, Ljava/util/concurrent/Future;->get(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/Integer;

    invoke-virtual {v4}, Ljava/lang/Integer;->intValue()I

    move-result v4

    .line 457
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->getModelKey()I

    move-result v7

    if-ne p0, v7, :cond_0

    .line 458
    invoke-static {v2}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->refresh(Ljava/lang/Runnable;)V

    goto :goto_0

    :catchall_0
    move-exception p1

    move-object v2, v3

    goto/16 :goto_6

    :catch_0
    move-exception v1

    move-object v2, v3

    goto :goto_2

    :catch_1
    move-exception v1

    move-object v2, v3

    goto :goto_4

    :cond_0
    :goto_0
    const/4 v2, 0x3

    if-ne v4, v2, :cond_1

    .line 462
    aget-wide v1, v5, v6

    const/high16 v4, 0x3f800000    # 1.0f

    invoke-interface {p1, v4, v1, v2}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;->onProgress(FJ)V

    .line 463
    invoke-interface {p1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;->onCompleted()V

    goto :goto_1

    :cond_1
    const/4 v2, 0x2

    if-eq v4, v2, :cond_2

    .line 465
    new-instance v2, Ljava/lang/IllegalStateException;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v2, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    invoke-interface {p1, v2}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;->onFailed(Ljava/lang/Exception;)V
    :try_end_1
    .catch Ljava/lang/InterruptedException; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 475
    :cond_2
    :goto_1
    invoke-static {v3}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->closeQuietly(Lcom/google/mlkit/genai/prompt/GenerativeModel;)V

    .line 476
    sget-object p1, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->downloadPercents:Ljava/util/Map;

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-interface {p1, v0}, Ljava/util/Map;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 477
    sget-object p1, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->downloadingModels:Ljava/util/Set;

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    invoke-interface {p1, p0}, Ljava/util/Set;->remove(Ljava/lang/Object;)Z

    .line 478
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->notifyServicesUpdated()V

    return-void

    :catchall_1
    move-exception p1

    goto :goto_6

    :catch_2
    move-exception v1

    .line 472
    :goto_2
    :try_start_2
    invoke-static {v0, v1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 473
    invoke-interface {p1, v1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;->onFailed(Ljava/lang/Exception;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    .line 475
    :goto_3
    invoke-static {v2}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->closeQuietly(Lcom/google/mlkit/genai/prompt/GenerativeModel;)V

    .line 476
    sget-object p1, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->downloadPercents:Ljava/util/Map;

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-interface {p1, v0}, Ljava/util/Map;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 477
    sget-object p1, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->downloadingModels:Ljava/util/Set;

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    invoke-interface {p1, p0}, Ljava/util/Set;->remove(Ljava/lang/Object;)Z

    .line 478
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->notifyServicesUpdated()V

    goto :goto_5

    :catch_3
    move-exception v1

    .line 468
    :goto_4
    :try_start_3
    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Thread;->interrupt()V

    .line 469
    invoke-static {v0, v1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 470
    invoke-interface {p1, v1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$DownloadListener;->onFailed(Ljava/lang/Exception;)V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    goto :goto_3

    :goto_5
    return-void

    .line 475
    :goto_6
    invoke-static {v2}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->closeQuietly(Lcom/google/mlkit/genai/prompt/GenerativeModel;)V

    .line 476
    sget-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->downloadPercents:Ljava/util/Map;

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/Map;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 477
    sget-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->downloadingModels:Ljava/util/Set;

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    invoke-interface {v0, p0}, Ljava/util/Set;->remove(Ljava/lang/Object;)Z

    .line 478
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->notifyServicesUpdated()V

    .line 479
    throw p1
.end method

.method private static runOnDone(Ljava/lang/Runnable;)V
    .locals 0

    if-eqz p0, :cond_0

    .line 533
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    :cond_0
    return-void
.end method

.method private static runOnWorker(Ljava/lang/Runnable;)V
    .locals 2

    .line 511
    invoke-static {}, Ljava/util/concurrent/Executors;->newSingleThreadExecutor()Ljava/util/concurrent/ExecutorService;

    move-result-object v0

    .line 512
    new-instance v1, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$$ExternalSyntheticLambda3;

    invoke-direct {v1, p0, v0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$$ExternalSyntheticLambda3;-><init>(Ljava/lang/Runnable;Ljava/util/concurrent/ExecutorService;)V

    invoke-interface {v0, v1}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    return-void
.end method

.method private static updateOtherModelSupported(I)V
    .locals 4

    const/4 v0, 0x0

    move v1, v0

    :goto_0
    const/4 v2, 0x3

    if-gt v0, v2, :cond_2

    if-nez v1, :cond_2

    if-ne v0, p0, :cond_0

    goto :goto_1

    .line 275
    :cond_0
    sget-object v1, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->sessionStatuses:Ljava/util/Map;

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {v1, v2}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Integer;

    if-nez v2, :cond_1

    .line 277
    invoke-static {v0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->probeStatus(I)I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    .line 278
    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-interface {v1, v3, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 280
    :cond_1
    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v1

    invoke-static {v1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->isStatusSupported(I)Z

    move-result v1

    :goto_1
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 282
    :cond_2
    sget-boolean p0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->otherModelSupported:Z

    if-eq v1, p0, :cond_3

    .line 283
    sput-boolean v1, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->otherModelSupported:Z

    .line 284
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    const-string v0, "onDeviceOtherModelSupported"

    invoke-interface {p0, v0, v1}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 285
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->notifyServicesUpdated()V

    :cond_3
    return-void
.end method

.method public static warmup()V
    .locals 3

    .line 388
    sget-object v0, Lcom/exteragram/messenger/ai/AiConfig;->ON_DEVICE_SERVICE:Lcom/exteragram/messenger/ai/data/Service;

    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/data/Service;->getId()Ljava/lang/String;

    move-result-object v0

    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getSelectedServiceId()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->isReady()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 391
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->warmedUp:Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v1, 0x0

    const/4 v2, 0x1

    invoke-virtual {v0, v1, v2}, Ljava/util/concurrent/atomic/AtomicBoolean;->compareAndSet(ZZ)Z

    move-result v0

    if-nez v0, :cond_1

    goto :goto_0

    .line 394
    :cond_1
    new-instance v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$$ExternalSyntheticLambda2;

    invoke-direct {v0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability$$ExternalSyntheticLambda2;-><init>()V

    invoke-static {v0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->runOnWorker(Ljava/lang/Runnable;)V

    :cond_2
    :goto_0
    return-void
.end method
