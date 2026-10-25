.class public Lcom/exteragram/messenger/ai/network/Client;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/ai/network/Client$Builder;,
        Lcom/exteragram/messenger/ai/network/Client$ImagePayload;,
        Lcom/exteragram/messenger/ai/network/Client$RequestSink;
    }
.end annotation


# instance fields
.field private final activeCancellables:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/Runnable;",
            ">;"
        }
    .end annotation
.end field

.field private final activeRequests:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Ljava/util/concurrent/ExecutorService;",
            ">;"
        }
    .end annotation
.end field

.field private final isGenerating:Ljava/util/concurrent/atomic/AtomicBoolean;

.field private final onDeviceBackend:Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;

.field private final restBackend:Lcom/exteragram/messenger/ai/network/backend/RestBackend;

.field private final roleOverride:Lcom/exteragram/messenger/ai/data/Role;

.field private final serviceOverride:Lcom/exteragram/messenger/ai/data/Service;


# direct methods
.method public static synthetic $r8$lambda$8PcWxu2bDwiysw_AQG9wpzHuiZI(Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/ai/network/Client;->lambda$notifyThinking$2(Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;)V

    return-void
.end method

.method public static synthetic $r8$lambda$TZ5gYF91OPAMPjVjRhU8c3pjR_k(Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLcom/exteragram/messenger/ai/network/GenerationCallback;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p6}, Lcom/exteragram/messenger/ai/network/Client;->lambda$getResponse$0(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLcom/exteragram/messenger/ai/network/GenerationCallback;)V

    return-void
.end method

.method public static synthetic $r8$lambda$YanB1L6ZWsHpkKjYmgMbSoPA-ug(Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;ILjava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lcom/exteragram/messenger/ai/network/Client;->lambda$notifyErrorAndFinish$4(Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;ILjava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$pBnI5nMHAARFoQKLTeC7fFLHz8g(Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lcom/exteragram/messenger/ai/network/Client;->lambda$notifyResponseAndFinish$3(Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;Ljava/lang/String;)V

    return-void
.end method

.method public static synthetic $r8$lambda$xP2U07ZRDJ83U_GZxFeG2frDpts(Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lcom/exteragram/messenger/ai/network/Client;->lambda$sendStreamChunk$1(Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;Ljava/lang/String;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$fgetactiveCancellables(Lcom/exteragram/messenger/ai/network/Client;)Ljava/util/concurrent/ConcurrentHashMap;
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/Client;->activeCancellables:Ljava/util/concurrent/ConcurrentHashMap;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetactiveRequests(Lcom/exteragram/messenger/ai/network/Client;)Ljava/util/concurrent/ConcurrentHashMap;
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/Client;->activeRequests:Ljava/util/concurrent/ConcurrentHashMap;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$mnotifyErrorAndFinish(Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;ILjava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3, p4}, Lcom/exteragram/messenger/ai/network/Client;->notifyErrorAndFinish(Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;ILjava/lang/String;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mnotifyResponseAndFinish(Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lcom/exteragram/messenger/ai/network/Client;->notifyResponseAndFinish(Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;Ljava/lang/String;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$mnotifyThinking(Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/ai/network/Client;->notifyThinking(Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$msaveTurn(Lcom/exteragram/messenger/ai/network/Client;Lcom/exteragram/messenger/ai/data/Message;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/ai/network/Client;->saveTurn(Lcom/exteragram/messenger/ai/data/Message;Ljava/lang/String;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$msendStreamChunk(Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lcom/exteragram/messenger/ai/network/Client;->sendStreamChunk(Ljava/lang/String;Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;)V

    return-void
.end method

.method private constructor <init>(Lcom/exteragram/messenger/ai/network/Client$Builder;)V
    .locals 2

    .line 61
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 52
    new-instance v0, Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Ljava/util/concurrent/atomic/AtomicBoolean;-><init>(Z)V

    iput-object v0, p0, Lcom/exteragram/messenger/ai/network/Client;->isGenerating:Ljava/util/concurrent/atomic/AtomicBoolean;

    .line 53
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/ai/network/Client;->activeRequests:Ljava/util/concurrent/ConcurrentHashMap;

    .line 54
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/ai/network/Client;->activeCancellables:Ljava/util/concurrent/ConcurrentHashMap;

    .line 58
    new-instance v0, Lcom/exteragram/messenger/ai/network/backend/RestBackend;

    invoke-direct {v0}, Lcom/exteragram/messenger/ai/network/backend/RestBackend;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/ai/network/Client;->restBackend:Lcom/exteragram/messenger/ai/network/backend/RestBackend;

    .line 59
    new-instance v0, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;

    invoke-direct {v0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/ai/network/Client;->onDeviceBackend:Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;

    .line 62
    invoke-static {p1}, Lcom/exteragram/messenger/ai/network/Client$Builder;->-$$Nest$fgetserviceOverride(Lcom/exteragram/messenger/ai/network/Client$Builder;)Lcom/exteragram/messenger/ai/data/Service;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/ai/network/Client;->serviceOverride:Lcom/exteragram/messenger/ai/data/Service;

    .line 63
    invoke-static {p1}, Lcom/exteragram/messenger/ai/network/Client$Builder;->-$$Nest$fgetroleOverride(Lcom/exteragram/messenger/ai/network/Client$Builder;)Lcom/exteragram/messenger/ai/data/Role;

    move-result-object p1

    iput-object p1, p0, Lcom/exteragram/messenger/ai/network/Client;->roleOverride:Lcom/exteragram/messenger/ai/data/Role;

    return-void
.end method

.method public synthetic constructor <init>(Lcom/exteragram/messenger/ai/network/Client$Builder;Lcom/exteragram/messenger/ai/network/Client-IA;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/network/Client;-><init>(Lcom/exteragram/messenger/ai/network/Client$Builder;)V

    return-void
.end method

.method private static compressImage(Ljava/lang/String;)Lcom/exteragram/messenger/ai/network/Client$ImagePayload;
    .locals 6

    const/4 v0, 0x0

    .line 112
    :try_start_0
    new-instance v1, Landroid/graphics/BitmapFactory$Options;

    invoke-direct {v1}, Landroid/graphics/BitmapFactory$Options;-><init>()V

    const/4 v2, 0x1

    .line 113
    iput-boolean v2, v1, Landroid/graphics/BitmapFactory$Options;->inJustDecodeBounds:Z

    .line 114
    invoke-static {p0, v1}, Landroid/graphics/BitmapFactory;->decodeFile(Ljava/lang/String;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;

    .line 115
    iget v3, v1, Landroid/graphics/BitmapFactory$Options;->outWidth:I

    if-lez v3, :cond_7

    iget v3, v1, Landroid/graphics/BitmapFactory$Options;->outHeight:I

    if-gtz v3, :cond_0

    goto/16 :goto_4

    .line 119
    :cond_0
    new-instance v3, Landroid/graphics/BitmapFactory$Options;

    invoke-direct {v3}, Landroid/graphics/BitmapFactory$Options;-><init>()V

    .line 120
    iput v2, v3, Landroid/graphics/BitmapFactory$Options;->inSampleSize:I

    .line 121
    :goto_0
    iget v2, v1, Landroid/graphics/BitmapFactory$Options;->outWidth:I

    iget v4, v3, Landroid/graphics/BitmapFactory$Options;->inSampleSize:I

    div-int/2addr v2, v4

    const/16 v5, 0x800

    if-gt v2, v5, :cond_6

    iget v2, v1, Landroid/graphics/BitmapFactory$Options;->outHeight:I

    div-int/2addr v2, v4

    if-le v2, v5, :cond_1

    goto :goto_3

    .line 125
    :cond_1
    invoke-static {p0, v3}, Landroid/graphics/BitmapFactory;->decodeFile(Ljava/lang/String;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;

    move-result-object v1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    if-nez v1, :cond_2

    return-object v0

    .line 130
    :cond_2
    :try_start_1
    new-instance v2, Ljava/io/ByteArrayOutputStream;

    invoke-direct {v2}, Ljava/io/ByteArrayOutputStream;-><init>()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    const/16 v3, 0x55

    .line 133
    :cond_3
    :try_start_2
    invoke-virtual {v2}, Ljava/io/ByteArrayOutputStream;->reset()V

    .line 134
    sget-object v4, Landroid/graphics/Bitmap$CompressFormat;->JPEG:Landroid/graphics/Bitmap$CompressFormat;

    invoke-virtual {v1, v4, v3, v2}, Landroid/graphics/Bitmap;->compress(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z

    add-int/lit8 v3, v3, -0xa

    .line 136
    invoke-virtual {v2}, Ljava/io/ByteArrayOutputStream;->size()I

    move-result v4

    const/high16 v5, 0x400000

    if-le v4, v5, :cond_4

    const/16 v4, 0x37

    if-ge v3, v4, :cond_3

    .line 137
    :cond_4
    invoke-virtual {v2}, Ljava/io/ByteArrayOutputStream;->size()I

    move-result v3
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    if-le v3, v5, :cond_5

    .line 141
    :try_start_3
    invoke-virtual {v2}, Ljava/io/ByteArrayOutputStream;->close()V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 142
    :try_start_4
    invoke-virtual {v1}, Landroid/graphics/Bitmap;->recycle()V
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_0

    return-object v0

    :catch_0
    move-exception v1

    goto :goto_5

    :catchall_0
    move-exception v2

    goto :goto_2

    .line 140
    :cond_5
    :try_start_5
    new-instance v3, Lcom/exteragram/messenger/ai/network/Client$ImagePayload;

    invoke-virtual {v2}, Ljava/io/ByteArrayOutputStream;->toByteArray()[B

    move-result-object v4

    const-string v5, "image/jpeg"

    invoke-direct {v3, v4, v5, v0}, Lcom/exteragram/messenger/ai/network/Client$ImagePayload;-><init>([BLjava/lang/String;Lcom/exteragram/messenger/ai/network/Client-IA;)V
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_1

    .line 141
    :try_start_6
    invoke-virtual {v2}, Ljava/io/ByteArrayOutputStream;->close()V
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_0

    .line 142
    :try_start_7
    invoke-virtual {v1}, Landroid/graphics/Bitmap;->recycle()V
    :try_end_7
    .catch Ljava/lang/Exception; {:try_start_7 .. :try_end_7} :catch_0

    return-object v3

    :catchall_1
    move-exception v3

    .line 130
    :try_start_8
    invoke-virtual {v2}, Ljava/io/ByteArrayOutputStream;->close()V
    :try_end_8
    .catchall {:try_start_8 .. :try_end_8} :catchall_2

    goto :goto_1

    :catchall_2
    move-exception v2

    :try_start_9
    invoke-virtual {v3, v2}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :goto_1
    throw v3
    :try_end_9
    .catchall {:try_start_9 .. :try_end_9} :catchall_0

    .line 142
    :goto_2
    :try_start_a
    invoke-virtual {v1}, Landroid/graphics/Bitmap;->recycle()V

    .line 143
    throw v2

    :cond_6
    :goto_3
    mul-int/lit8 v4, v4, 0x2

    .line 122
    iput v4, v3, Landroid/graphics/BitmapFactory$Options;->inSampleSize:I
    :try_end_a
    .catch Ljava/lang/Exception; {:try_start_a .. :try_end_a} :catch_0

    goto :goto_0

    :cond_7
    :goto_4
    return-object v0

    .line 145
    :goto_5
    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "Error compressing image: "

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, v1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-object v0
.end method

.method private copyTextMessage(Lcom/exteragram/messenger/ai/data/Message;)Lcom/exteragram/messenger/ai/data/Message;
    .locals 1

    .line 294
    new-instance p0, Lcom/exteragram/messenger/ai/data/Message;

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Message;->role()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Message;->content()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, v0, p1}, Lcom/exteragram/messenger/ai/data/Message;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    return-object p0
.end method

.method private finishRequest(Ljava/lang/String;)V
    .locals 1

    .line 327
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/Client;->activeCancellables:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 328
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/Client;->activeRequests:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/util/concurrent/ExecutorService;

    if-eqz p1, :cond_0

    .line 330
    invoke-interface {p1}, Ljava/util/concurrent/ExecutorService;->shutdown()V

    .line 332
    :cond_0
    iget-object p1, p0, Lcom/exteragram/messenger/ai/network/Client;->activeRequests:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p1}, Ljava/util/concurrent/ConcurrentHashMap;->isEmpty()Z

    move-result p1

    if-eqz p1, :cond_1

    .line 333
    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/Client;->isGenerating:Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 p1, 0x0

    invoke-virtual {p0, p1}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V

    :cond_1
    return-void
.end method

.method private getActiveRole()Lcom/exteragram/messenger/ai/data/Role;
    .locals 1

    .line 159
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/Client;->roleOverride:Lcom/exteragram/messenger/ai/data/Role;

    if-eqz v0, :cond_0

    return-object v0

    :cond_0
    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/Client;->serviceOverride:Lcom/exteragram/messenger/ai/data/Service;

    if-nez p0, :cond_1

    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/AiController;->getSelectedRole()Lcom/exteragram/messenger/ai/data/Role;

    move-result-object p0

    return-object p0

    :cond_1
    const/4 p0, 0x0

    return-object p0
.end method

.method public static getMimeType(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    .line 67
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 69
    :cond_0
    invoke-virtual {p0}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object p0

    .line 70
    const-string v0, ".png"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 71
    const-string p0, "image/png"

    return-object p0

    .line 72
    :cond_1
    const-string v0, ".webp"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 73
    const-string p0, "image/webp"

    return-object p0

    .line 74
    :cond_2
    const-string v0, ".heic"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_4

    const-string v0, ".heif"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_3

    goto :goto_0

    .line 77
    :cond_3
    const-string p0, "image/jpeg"

    return-object p0

    .line 75
    :cond_4
    :goto_0
    const-string p0, "image/heic"

    return-object p0
.end method

.method private getSelectedService()Lcom/exteragram/messenger/ai/data/Service;
    .locals 0

    .line 151
    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/Client;->serviceOverride:Lcom/exteragram/messenger/ai/data/Service;

    if-eqz p0, :cond_0

    return-object p0

    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/AiController;->getSelected()Lcom/exteragram/messenger/ai/data/Service;

    move-result-object p0

    return-object p0
.end method

.method private historyForRequest(Ljava/util/ArrayList;Lcom/exteragram/messenger/ai/network/backend/Backend;)Ljava/util/ArrayList;
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/ai/data/Message;",
            ">;",
            "Lcom/exteragram/messenger/ai/network/backend/Backend;",
            ")",
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/ai/data/Message;",
            ">;"
        }
    .end annotation

    .line 244
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 245
    invoke-interface {p2}, Lcom/exteragram/messenger/ai/network/backend/Backend;->getHistoryCharsLimit()I

    move-result v1

    .line 246
    invoke-interface {p2}, Lcom/exteragram/messenger/ai/network/backend/Backend;->getHistoryMessagesLimit()I

    move-result p2

    .line 249
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v2

    const/4 v3, 0x1

    sub-int/2addr v2, v3

    const/4 v4, 0x0

    move v5, v4

    :goto_0
    if-lez v2, :cond_4

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v6

    if-ge v6, p2, :cond_4

    .line 250
    invoke-virtual {p1, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lcom/exteragram/messenger/ai/data/Message;

    if-eqz v6, :cond_3

    .line 251
    invoke-virtual {v6}, Lcom/exteragram/messenger/ai/data/Message;->role()Ljava/lang/String;

    move-result-object v7

    const-string v8, "assistant"

    invoke-virtual {v8, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v7

    if-eqz v7, :cond_3

    invoke-virtual {v6}, Lcom/exteragram/messenger/ai/data/Message;->content()Ljava/lang/String;

    move-result-object v7

    invoke-static {v7}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v7

    if-eqz v7, :cond_0

    goto :goto_1

    :cond_0
    add-int/lit8 v7, v2, -0x1

    .line 255
    invoke-virtual {p1, v7}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lcom/exteragram/messenger/ai/data/Message;

    if-eqz v7, :cond_3

    .line 256
    invoke-virtual {v7}, Lcom/exteragram/messenger/ai/data/Message;->role()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v8, v9}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v8

    if-nez v8, :cond_3

    invoke-virtual {v7}, Lcom/exteragram/messenger/ai/data/Message;->content()Ljava/lang/String;

    move-result-object v8

    invoke-static {v8}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v8

    if-eqz v8, :cond_1

    goto :goto_1

    .line 260
    :cond_1
    invoke-virtual {v7}, Lcom/exteragram/messenger/ai/data/Message;->content()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/String;->length()I

    move-result v8

    invoke-virtual {v6}, Lcom/exteragram/messenger/ai/data/Message;->content()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v9}, Ljava/lang/String;->length()I

    move-result v9

    add-int/2addr v8, v9

    add-int/2addr v8, v5

    if-gt v8, v1, :cond_3

    .line 261
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v9

    add-int/lit8 v9, v9, 0x2

    if-le v9, p2, :cond_2

    goto :goto_1

    .line 265
    :cond_2
    invoke-direct {p0, v7}, Lcom/exteragram/messenger/ai/network/Client;->copyTextMessage(Lcom/exteragram/messenger/ai/data/Message;)Lcom/exteragram/messenger/ai/data/Message;

    move-result-object v5

    invoke-virtual {v0, v4, v5}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    .line 266
    invoke-direct {p0, v6}, Lcom/exteragram/messenger/ai/network/Client;->copyTextMessage(Lcom/exteragram/messenger/ai/data/Message;)Lcom/exteragram/messenger/ai/data/Message;

    move-result-object v5

    invoke-virtual {v0, v3, v5}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    move v5, v8

    :cond_3
    :goto_1
    add-int/lit8 v2, v2, -0x1

    goto :goto_0

    :cond_4
    return-object v0
.end method

.method private synthetic lambda$getResponse$0(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLcom/exteragram/messenger/ai/network/GenerationCallback;)V
    .locals 10

    .line 172
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/Client;->isGenerating:Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V

    .line 175
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/network/Client;->getSelectedService()Lcom/exteragram/messenger/ai/data/Service;

    move-result-object v3

    .line 176
    invoke-direct {p0, v3}, Lcom/exteragram/messenger/ai/network/Client;->resolveBackend(Lcom/exteragram/messenger/ai/data/Service;)Lcom/exteragram/messenger/ai/network/backend/Backend;

    move-result-object v0

    .line 179
    invoke-interface {v0}, Lcom/exteragram/messenger/ai/network/backend/Backend;->supportsImages()Z

    move-result v1

    const/4 v2, 0x0

    if-eqz v1, :cond_0

    invoke-static {p1}, Lcom/exteragram/messenger/ai/AiController;->canSendImage(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 180
    invoke-static {p1}, Lcom/exteragram/messenger/ai/network/Client;->loadImagePayload(Ljava/lang/String;)Lcom/exteragram/messenger/ai/network/Client$ImagePayload;

    move-result-object p1

    goto :goto_0

    :catch_0
    move-exception v0

    move-object p1, v0

    goto/16 :goto_4

    :cond_0
    move-object p1, v2

    .line 183
    :goto_0
    iget-object v1, p0, Lcom/exteragram/messenger/ai/network/Client;->activeRequests:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v1, p2}, Ljava/util/concurrent/ConcurrentHashMap;->containsKey(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1

    goto/16 :goto_5

    .line 187
    :cond_1
    new-instance v6, Lcom/exteragram/messenger/ai/data/Message;

    const-string v1, "user"

    if-eqz p1, :cond_2

    .line 188
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/network/Client$ImagePayload;->data()[B

    move-result-object v4

    goto :goto_1

    :cond_2
    move-object v4, v2

    :goto_1
    if-eqz p1, :cond_3

    .line 189
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/network/Client$ImagePayload;->mimeType()Ljava/lang/String;

    move-result-object v2

    :cond_3
    invoke-direct {v6, v1, p3, v4, v2}, Lcom/exteragram/messenger/ai/data/Message;-><init>(Ljava/lang/String;Ljava/lang/String;[BLjava/lang/String;)V

    if-eqz p4, :cond_4

    .line 192
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getConversationHistory()Ljava/util/ArrayList;

    move-result-object p1

    invoke-direct {p0, p1, v0}, Lcom/exteragram/messenger/ai/network/Client;->historyForRequest(Ljava/util/ArrayList;Lcom/exteragram/messenger/ai/network/backend/Backend;)Ljava/util/ArrayList;

    move-result-object p1

    :goto_2
    move-object v5, p1

    goto :goto_3

    .line 193
    :cond_4
    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    goto :goto_2

    .line 195
    :goto_3
    iget-object p1, p0, Lcom/exteragram/messenger/ai/network/Client;->activeRequests:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p1, p2}, Ljava/util/concurrent/ConcurrentHashMap;->containsKey(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_5

    goto :goto_5

    .line 199
    :cond_5
    new-instance v2, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;

    .line 201
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/network/Client;->getActiveRole()Lcom/exteragram/messenger/ai/data/Role;

    move-result-object v4

    .line 205
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getTemperature()I

    move-result p1

    int-to-float p1, p1

    const/high16 v1, 0x41a00000    # 20.0f

    div-float/2addr p1, v1

    invoke-interface {v0}, Lcom/exteragram/messenger/ai/network/backend/Backend;->getMaxTemperature()F

    move-result v1

    mul-float v8, p1, v1

    .line 206
    invoke-interface {v0}, Lcom/exteragram/messenger/ai/network/backend/Backend;->getMaxOutputTokens()I

    move-result v9

    move v7, p5

    invoke-direct/range {v2 .. v9}, Lcom/exteragram/messenger/ai/network/backend/BackendRequest;-><init>(Lcom/exteragram/messenger/ai/data/Service;Lcom/exteragram/messenger/ai/data/Role;Ljava/util/List;Lcom/exteragram/messenger/ai/data/Message;ZFI)V

    .line 209
    new-instance v4, Lcom/exteragram/messenger/ai/network/Client$RequestSink;

    move-object v5, p0

    move v8, p4

    move-object/from16 v9, p6

    move-object v7, v6

    move-object v6, p2

    invoke-direct/range {v4 .. v9}, Lcom/exteragram/messenger/ai/network/Client$RequestSink;-><init>(Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Lcom/exteragram/messenger/ai/data/Message;ZLcom/exteragram/messenger/ai/network/GenerationCallback;)V

    invoke-interface {v0, v2, v4}, Lcom/exteragram/messenger/ai/network/backend/Backend;->execute(Lcom/exteragram/messenger/ai/network/backend/BackendRequest;Lcom/exteragram/messenger/ai/network/backend/BackendSink;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    .line 211
    :goto_4
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/Client;->activeRequests:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p2}, Ljava/util/concurrent/ConcurrentHashMap;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_6

    :goto_5
    return-void

    .line 214
    :cond_6
    const-string v0, "AI Error: "

    invoke-static {v0, p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 217
    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_7

    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object p1

    goto :goto_6

    :cond_7
    const-string p1, "Unknown error"

    :goto_6
    const/16 v0, 0x1f4

    move-object/from16 v9, p6

    .line 215
    invoke-direct {p0, p2, v9, v0, p1}, Lcom/exteragram/messenger/ai/network/Client;->notifyErrorAndFinish(Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;ILjava/lang/String;)V

    return-void
.end method

.method private synthetic lambda$notifyErrorAndFinish$4(Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;ILjava/lang/String;)V
    .locals 1

    .line 312
    :try_start_0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/Client;->activeRequests:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    if-eqz p2, :cond_0

    .line 313
    invoke-interface {p2, p3, p4}, Lcom/exteragram/messenger/ai/network/GenerationCallback;->onError(ILjava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception p2

    goto :goto_1

    .line 316
    :cond_0
    :goto_0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/network/Client;->finishRequest(Ljava/lang/String;)V

    return-void

    :goto_1
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/network/Client;->finishRequest(Ljava/lang/String;)V

    .line 317
    throw p2
.end method

.method private synthetic lambda$notifyResponseAndFinish$3(Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;Ljava/lang/String;)V
    .locals 1

    .line 300
    :try_start_0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/Client;->activeRequests:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    if-eqz p2, :cond_0

    .line 301
    invoke-interface {p2, p3}, Lcom/exteragram/messenger/ai/network/GenerationCallback;->onResponse(Ljava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception p2

    goto :goto_1

    .line 304
    :cond_0
    :goto_0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/network/Client;->finishRequest(Ljava/lang/String;)V

    return-void

    :goto_1
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/network/Client;->finishRequest(Ljava/lang/String;)V

    .line 305
    throw p2
.end method

.method private synthetic lambda$notifyThinking$2(Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;)V
    .locals 0

    .line 237
    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/Client;->activeRequests:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->containsKey(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    if-eqz p2, :cond_0

    .line 238
    invoke-interface {p2}, Lcom/exteragram/messenger/ai/network/GenerationCallback;->onThinking()V

    :cond_0
    return-void
.end method

.method private synthetic lambda$sendStreamChunk$1(Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;Ljava/lang/String;)V
    .locals 0

    .line 228
    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/Client;->activeRequests:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->containsKey(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    if-eqz p2, :cond_0

    .line 229
    invoke-interface {p2, p3}, Lcom/exteragram/messenger/ai/network/GenerationCallback;->onChunk(Ljava/lang/String;)V

    :cond_0
    return-void
.end method

.method private static loadImagePayload(Ljava/lang/String;)Lcom/exteragram/messenger/ai/network/Client$ImagePayload;
    .locals 8

    .line 81
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    return-object v1

    .line 82
    :cond_0
    new-instance v0, Ljava/io/File;

    invoke-direct {v0, p0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 83
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v2

    if-eqz v2, :cond_5

    invoke-virtual {v0}, Ljava/io/File;->isFile()Z

    move-result v2

    if-nez v2, :cond_1

    goto/16 :goto_6

    .line 86
    :cond_1
    invoke-virtual {v0}, Ljava/io/File;->length()J

    move-result-wide v2

    const-wide/16 v4, 0x0

    cmp-long v2, v2, v4

    if-nez v2, :cond_2

    return-object v1

    .line 90
    :cond_2
    invoke-virtual {v0}, Ljava/io/File;->length()J

    move-result-wide v2

    const-wide/32 v4, 0x400000

    cmp-long v2, v2, v4

    if-lez v2, :cond_3

    .line 91
    invoke-static {p0}, Lcom/exteragram/messenger/ai/network/Client;->compressImage(Ljava/lang/String;)Lcom/exteragram/messenger/ai/network/Client$ImagePayload;

    move-result-object p0

    return-object p0

    .line 94
    :cond_3
    :try_start_0
    new-instance v2, Ljava/io/FileInputStream;

    invoke-direct {v2, v0}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 95
    :try_start_1
    new-instance v3, Ljava/io/ByteArrayOutputStream;

    invoke-virtual {v0}, Ljava/io/File;->length()J

    move-result-wide v6

    invoke-static {v6, v7, v4, v5}, Ljava/lang/Math;->min(JJ)J

    move-result-wide v4

    long-to-int v0, v4

    invoke-direct {v3, v0}, Ljava/io/ByteArrayOutputStream;-><init>(I)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    const/16 v0, 0x400

    .line 97
    :try_start_2
    new-array v0, v0, [B

    .line 99
    :goto_0
    invoke-virtual {v2, v0}, Ljava/io/FileInputStream;->read([B)I

    move-result v4

    const/4 v5, -0x1

    if-eq v4, v5, :cond_4

    const/4 v5, 0x0

    .line 100
    invoke-virtual {v3, v0, v5, v4}, Ljava/io/ByteArrayOutputStream;->write([BII)V

    goto :goto_0

    :catchall_0
    move-exception v0

    goto :goto_1

    .line 102
    :cond_4
    new-instance v0, Lcom/exteragram/messenger/ai/network/Client$ImagePayload;

    invoke-virtual {v3}, Ljava/io/ByteArrayOutputStream;->toByteArray()[B

    move-result-object v4

    invoke-static {p0}, Lcom/exteragram/messenger/ai/network/Client;->getMimeType(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    invoke-direct {v0, v4, v5, v1}, Lcom/exteragram/messenger/ai/network/Client$ImagePayload;-><init>([BLjava/lang/String;Lcom/exteragram/messenger/ai/network/Client-IA;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 104
    :try_start_3
    invoke-virtual {v3}, Ljava/io/ByteArrayOutputStream;->close()V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    :try_start_4
    invoke-virtual {v2}, Ljava/io/FileInputStream;->close()V
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_0

    return-object v0

    :catch_0
    move-exception v0

    goto :goto_5

    :catchall_1
    move-exception v0

    goto :goto_3

    .line 94
    :goto_1
    :try_start_5
    invoke-virtual {v3}, Ljava/io/ByteArrayOutputStream;->close()V
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_2

    goto :goto_2

    :catchall_2
    move-exception v3

    :try_start_6
    invoke-virtual {v0, v3}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :goto_2
    throw v0
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_1

    :goto_3
    :try_start_7
    invoke-virtual {v2}, Ljava/io/FileInputStream;->close()V
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_3

    goto :goto_4

    :catchall_3
    move-exception v2

    :try_start_8
    invoke-virtual {v0, v2}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :goto_4
    throw v0
    :try_end_8
    .catch Ljava/io/IOException; {:try_start_8 .. :try_end_8} :catch_0

    .line 105
    :goto_5
    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "Error loading image: "

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_5
    :goto_6
    return-object v1
.end method

.method private notifyErrorAndFinish(Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;ILjava/lang/String;)V
    .locals 6

    .line 310
    new-instance v0, Lcom/exteragram/messenger/ai/network/Client$$ExternalSyntheticLambda1;

    move-object v1, p0

    move-object v2, p1

    move-object v3, p2

    move v4, p3

    move-object v5, p4

    invoke-direct/range {v0 .. v5}, Lcom/exteragram/messenger/ai/network/Client$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;ILjava/lang/String;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private notifyResponseAndFinish(Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;Ljava/lang/String;)V
    .locals 1

    .line 298
    new-instance v0, Lcom/exteragram/messenger/ai/network/Client$$ExternalSyntheticLambda2;

    invoke-direct {v0, p0, p1, p2, p3}, Lcom/exteragram/messenger/ai/network/Client$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;Ljava/lang/String;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private notifyThinking(Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;)V
    .locals 1

    .line 236
    new-instance v0, Lcom/exteragram/messenger/ai/network/Client$$ExternalSyntheticLambda4;

    invoke-direct {v0, p0, p1, p2}, Lcom/exteragram/messenger/ai/network/Client$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private resolveBackend(Lcom/exteragram/messenger/ai/data/Service;)Lcom/exteragram/messenger/ai/network/backend/Backend;
    .locals 0

    if-eqz p1, :cond_0

    .line 155
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Service;->isOnDevice()Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/Client;->onDeviceBackend:Lcom/exteragram/messenger/ai/network/backend/OnDeviceBackend;

    return-object p0

    :cond_0
    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/Client;->restBackend:Lcom/exteragram/messenger/ai/network/backend/RestBackend;

    return-object p0
.end method

.method private saveTurn(Lcom/exteragram/messenger/ai/data/Message;Ljava/lang/String;)V
    .locals 5

    .line 274
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getConversationHistory()Ljava/util/ArrayList;

    move-result-object v0

    .line 275
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/network/Client;->copyTextMessage(Lcom/exteragram/messenger/ai/data/Message;)Lcom/exteragram/messenger/ai/data/Message;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 276
    new-instance p0, Lcom/exteragram/messenger/ai/data/Message;

    const-string p1, "assistant"

    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/ai/data/Message;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 279
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result p0

    const/4 p2, 0x0

    move v1, p2

    move v2, v1

    :goto_0
    if-ge v2, p0, :cond_1

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    add-int/lit8 v2, v2, 0x1

    check-cast v3, Lcom/exteragram/messenger/ai/data/Message;

    .line 280
    invoke-virtual {v3}, Lcom/exteragram/messenger/ai/data/Message;->content()Ljava/lang/String;

    move-result-object v4

    if-nez v4, :cond_0

    move v3, p2

    goto :goto_1

    :cond_0
    invoke-virtual {v3}, Lcom/exteragram/messenger/ai/data/Message;->content()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v3

    :goto_1
    add-int/2addr v1, v3

    goto :goto_0

    .line 282
    :cond_1
    :goto_2
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result p0

    const/4 v2, 0x2

    if-le p0, v2, :cond_4

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result p0

    const/16 v3, 0x40

    if-gt p0, v3, :cond_2

    const p0, 0xfa00

    if-le v1, p0, :cond_4

    .line 283
    :cond_2
    invoke-virtual {v0, p2}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/ai/data/Message;

    .line 284
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/data/Message;->content()Ljava/lang/String;

    move-result-object v2

    if-nez v2, :cond_3

    move p0, p2

    goto :goto_3

    :cond_3
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/data/Message;->content()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p0

    :goto_3
    sub-int/2addr v1, p0

    goto :goto_2

    .line 286
    :cond_4
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result p0

    if-le p0, v2, :cond_5

    invoke-virtual {v0, p2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/ai/data/Message;

    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/data/Message;->role()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_5

    .line 287
    invoke-virtual {v0, p2}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    .line 290
    :cond_5
    invoke-static {v0}, Lcom/exteragram/messenger/ai/AiConfig;->saveConversationHistory(Ljava/util/ArrayList;)V

    return-void
.end method

.method private sendStreamChunk(Ljava/lang/String;Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;)V
    .locals 1

    .line 226
    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 227
    new-instance v0, Lcom/exteragram/messenger/ai/network/Client$$ExternalSyntheticLambda3;

    invoke-direct {v0, p0, p1, p3, p2}, Lcom/exteragram/messenger/ai/network/Client$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;Ljava/lang/String;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    :cond_0
    return-void
.end method


# virtual methods
.method public getResponse(Ljava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;)Ljava/lang/String;
    .locals 6

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v2, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v5, p2

    .line 163
    invoke-virtual/range {v0 .. v5}, Lcom/exteragram/messenger/ai/network/Client;->getResponse(Ljava/lang/String;ZZLjava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public getResponse(Ljava/lang/String;ZZLjava/lang/String;Lcom/exteragram/messenger/ai/network/GenerationCallback;)Ljava/lang/String;
    .locals 9

    .line 167
    invoke-static {}, Ljava/util/UUID;->randomUUID()Ljava/util/UUID;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/UUID;->toString()Ljava/lang/String;

    move-result-object v4

    .line 168
    invoke-static {}, Ljava/util/concurrent/Executors;->newSingleThreadExecutor()Ljava/util/concurrent/ExecutorService;

    move-result-object v0

    .line 169
    iget-object v1, p0, Lcom/exteragram/messenger/ai/network/Client;->activeRequests:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v1, v4, v0}, Ljava/util/concurrent/ConcurrentHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 171
    new-instance v1, Lcom/exteragram/messenger/ai/network/Client$$ExternalSyntheticLambda0;

    move-object v2, p0

    move-object v5, p1

    move v6, p2

    move v7, p3

    move-object v3, p4

    move-object v8, p5

    invoke-direct/range {v1 .. v8}, Lcom/exteragram/messenger/ai/network/Client$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLcom/exteragram/messenger/ai/network/GenerationCallback;)V

    invoke-interface {v0, v1}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    return-object v4
.end method

.method public isGenerating()Z
    .locals 0

    .line 322
    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/Client;->isGenerating:Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-virtual {p0}, Ljava/util/concurrent/atomic/AtomicBoolean;->get()Z

    move-result p0

    return p0
.end method

.method public stopRequest(Ljava/lang/String;)V
    .locals 1

    .line 339
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_1

    .line 342
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/Client;->activeCancellables:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Runnable;

    if-eqz v0, :cond_1

    .line 345
    :try_start_0
    invoke-interface {v0}, Ljava/lang/Runnable;->run()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    .line 347
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 350
    :cond_1
    :goto_0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/network/Client;->activeRequests:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/util/concurrent/ExecutorService;

    if-eqz p1, :cond_2

    .line 352
    invoke-interface {p1}, Ljava/util/concurrent/ExecutorService;->shutdownNow()Ljava/util/List;

    .line 354
    :cond_2
    iget-object p1, p0, Lcom/exteragram/messenger/ai/network/Client;->activeRequests:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p1}, Ljava/util/concurrent/ConcurrentHashMap;->isEmpty()Z

    move-result p1

    if-eqz p1, :cond_3

    .line 355
    iget-object p0, p0, Lcom/exteragram/messenger/ai/network/Client;->isGenerating:Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 p1, 0x0

    invoke-virtual {p0, p1}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V

    :cond_3
    :goto_1
    return-void
.end method
