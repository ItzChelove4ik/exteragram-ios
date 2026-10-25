.class public final Lorg/telegram/messenger/ResLottieMeta;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/messenger/ResLottieMeta$Holder;
    }
.end annotation


# static fields
.field private static final ASSET_NAME:Ljava/lang/String; = "lottie_meta.bin"

.field private static final ENTRY_SIZE:I = 0x8

.field public static final NOT_FOUND:J = -0x1L


# direct methods
.method public static bridge synthetic -$$Nest$smload()[J
    .locals 1

    .line 0
    invoke-static {}, Lorg/telegram/messenger/ResLottieMeta;->load()[J

    move-result-object v0

    return-object v0
.end method

.method private constructor <init>()V
    .locals 0

    .line 28
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static find(I)J
    .locals 7
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "ResourceType"
        }
    .end annotation

    .line 73
    invoke-static {}, Lorg/telegram/messenger/ResLottieMeta$Holder;->-$$Nest$sfgetDATA()[J

    move-result-object v0

    .line 76
    array-length v1, v0

    add-int/lit8 v1, v1, -0x1

    const/4 v2, 0x0

    :goto_0
    if-gt v2, v1, :cond_2

    add-int v3, v2, v1

    ushr-int/lit8 v3, v3, 0x1

    .line 80
    aget-wide v4, v0, v3

    .line 81
    invoke-static {v4, v5}, Lorg/telegram/messenger/ResLottieMeta;->resIdOf(J)I

    move-result v6

    if-ge v6, p0, :cond_0

    add-int/lit8 v3, v3, 0x1

    move v2, v3

    goto :goto_0

    :cond_0
    if-le v6, p0, :cond_1

    add-int/lit8 v3, v3, -0x1

    move v1, v3

    goto :goto_0

    :cond_1
    return-wide v4

    :cond_2
    const-wide/16 v0, -0x1

    return-wide v0
.end method

.method public static fpsOf(J)I
    .locals 2

    const/16 v0, 0x18

    ushr-long/2addr p0, v0

    const-wide/16 v0, 0xff

    and-long/2addr p0, v0

    long-to-int p0, p0

    return p0
.end method

.method public static frameCountOf(J)I
    .locals 2

    const-wide/32 v0, 0x7fffff

    and-long/2addr p0, v0

    long-to-int p0, p0

    return p0
.end method

.method public static isMonoColorOf(J)Z
    .locals 2

    const-wide/32 v0, 0x800000

    and-long/2addr p0, v0

    const-wide/16 v0, 0x0

    cmp-long p0, p0, v0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private static load()[J
    .locals 8

    .line 36
    const-string v0, "Unable to load lottie_meta.bin"

    const-string v1, "lottie_meta.bin has invalid size: "

    :try_start_0
    new-instance v2, Ljava/io/BufferedInputStream;

    sget-object v3, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    .line 38
    invoke-virtual {v3}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v3

    .line 39
    invoke-virtual {v3}, Landroid/content/res/Resources;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v3

    const-string v4, "lottie_meta.bin"

    .line 40
    invoke-virtual {v3, v4}, Landroid/content/res/AssetManager;->open(Ljava/lang/String;)Ljava/io/InputStream;

    move-result-object v3

    invoke-direct {v2, v3}, Ljava/io/BufferedInputStream;-><init>(Ljava/io/InputStream;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    .line 42
    :try_start_1
    invoke-virtual {v2}, Ljava/io/InputStream;->available()I

    move-result v3

    .line 43
    rem-int/lit8 v4, v3, 0x8

    if-nez v4, :cond_1

    .line 49
    div-int/lit8 v3, v3, 0x8

    .line 50
    new-array v1, v3, [J

    .line 51
    new-instance v4, Lorg/telegram/tgnet/SerializedData;

    invoke-direct {v4, v2}, Lorg/telegram/tgnet/SerializedData;-><init>(Ljava/io/InputStream;)V

    const/4 v5, 0x0

    :goto_0
    if-ge v5, v3, :cond_0

    const/4 v6, 0x1

    .line 54
    invoke-virtual {v4, v6}, Lorg/telegram/tgnet/SerializedData;->readInt64(Z)J

    move-result-wide v6

    aput-wide v6, v1, v5
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    add-int/lit8 v5, v5, 0x1

    goto :goto_0

    :catchall_0
    move-exception v1

    goto :goto_1

    .line 58
    :cond_0
    :try_start_2
    invoke-virtual {v2}, Ljava/io/InputStream;->close()V
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_1
    .catch Ljava/lang/RuntimeException; {:try_start_2 .. :try_end_2} :catch_0

    return-object v1

    :catch_0
    move-exception v1

    goto :goto_3

    :catch_1
    move-exception v1

    goto :goto_4

    .line 44
    :cond_1
    :try_start_3
    new-instance v4, Ljava/lang/IllegalStateException;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v4, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v4
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 36
    :goto_1
    :try_start_4
    invoke-virtual {v2}, Ljava/io/InputStream;->close()V
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    goto :goto_2

    :catchall_1
    move-exception v2

    :try_start_5
    invoke-virtual {v1, v2}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :goto_2
    throw v1
    :try_end_5
    .catch Ljava/io/IOException; {:try_start_5 .. :try_end_5} :catch_1
    .catch Ljava/lang/RuntimeException; {:try_start_5 .. :try_end_5} :catch_0

    .line 61
    :goto_3
    sget-boolean v2, Lorg/telegram/messenger/BuildVars;->LOGS_ENABLED:Z

    if-eqz v2, :cond_2

    .line 62
    invoke-static {v0, v1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 64
    :cond_2
    throw v1

    .line 59
    :goto_4
    invoke-static {v0, v1}, Lorg/mvel2/util/Make$Map$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Throwable;)V

    const/4 v0, 0x0

    return-object v0
.end method

.method public static resIdOf(J)I
    .locals 1

    const/16 v0, 0x20

    ushr-long/2addr p0, v0

    long-to-int p0, p0

    return p0
.end method
