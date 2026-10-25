.class public Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager$FilterMetadata;
    }
.end annotation


# static fields
.field private static instance:Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;

.field private static final redirectPattern:Ljava/util/regex/Pattern;


# instance fields
.field private final client:Lokhttp3/OkHttpClient;

.field private final lock:Ljava/lang/Object;

.field private final prefs:Landroid/content/SharedPreferences;

.field private final queue:Lorg/telegram/messenger/DispatchQueue;


# direct methods
.method public static synthetic $r8$lambda$IRmt47v_HDFeJ0fY0-sq8SCLzqI(Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;[Ljava/lang/String;Ljava/lang/Runnable;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->lambda$update$0([Ljava/lang/String;Ljava/lang/Runnable;)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 1

    .line 38
    const-string v0, "!\\s*Redirect:\\s*(\\S+)"

    invoke-static {v0}, Ljava/util/regex/Pattern;->compile(Ljava/lang/String;)Ljava/util/regex/Pattern;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->redirectPattern:Ljava/util/regex/Pattern;

    return-void
.end method

.method public constructor <init>()V
    .locals 2

    .line 45
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 43
    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->lock:Ljava/lang/Object;

    .line 46
    new-instance v0, Lorg/telegram/messenger/DispatchQueue;

    const-string v1, "SubscriptionsManager"

    invoke-direct {v0, v1}, Lorg/telegram/messenger/DispatchQueue;-><init>(Ljava/lang/String;)V

    iput-object v0, p0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->queue:Lorg/telegram/messenger/DispatchQueue;

    .line 47
    sget-object v0, Lcom/exteragram/messenger/utils/network/ExteraHttpClient;->INSTANCE:Lcom/exteragram/messenger/utils/network/ExteraHttpClient;

    invoke-virtual {v0}, Lcom/exteragram/messenger/utils/network/ExteraHttpClient;->getClient()Lokhttp3/OkHttpClient;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->client:Lokhttp3/OkHttpClient;

    .line 48
    const-string/jumbo v0, "ublock_subscriptions"

    invoke-static {v0}, Lcom/exteragram/messenger/backup/PreferencesUtils;->getPreferences(Ljava/lang/String;)Landroid/content/SharedPreferences;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->prefs:Landroid/content/SharedPreferences;

    return-void
.end method

.method private calculateExpiration(Ljava/lang/String;)J
    .locals 6

    .line 229
    const-string v0, "Expires"

    invoke-direct {p0, p1, v0}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->extractMetadataValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    const-wide/32 v0, 0x19bfcc00

    if-nez p0, :cond_0

    .line 231
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p0

    :goto_0
    add-long/2addr p0, v0

    return-wide p0

    .line 235
    :cond_0
    :try_start_0
    const-string p1, "[^0-9]"

    const-string v2, ""

    invoke-virtual {p0, p1, v2}, Ljava/lang/String;->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result p1

    .line 236
    const-string/jumbo v2, "hour"

    invoke-virtual {p0, v2}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 237
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    sget-object p0, Ljava/util/concurrent/TimeUnit;->HOURS:Ljava/util/concurrent/TimeUnit;

    int-to-long v4, p1

    invoke-virtual {p0, v4, v5}, Ljava/util/concurrent/TimeUnit;->toMillis(J)J

    move-result-wide p0

    add-long/2addr v2, p0

    return-wide v2

    .line 238
    :cond_1
    const-string v2, "day"

    invoke-virtual {p0, v2}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_2

    .line 239
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    sget-object p0, Ljava/util/concurrent/TimeUnit;->DAYS:Ljava/util/concurrent/TimeUnit;

    int-to-long v4, p1

    invoke-virtual {p0, v4, v5}, Ljava/util/concurrent/TimeUnit;->toMillis(J)J

    move-result-wide p0

    add-long/2addr v2, p0

    return-wide v2

    .line 241
    :cond_2
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p0
    :try_end_0
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_0

    add-long/2addr p0, v0

    return-wide p0

    .line 244
    :catch_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p0

    goto :goto_0
.end method

.method private countRules(Ljava/lang/String;)I
    .locals 4

    .line 218
    const-string p0, "\n"

    invoke-virtual {p1, p0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p0

    .line 219
    array-length p1, p0

    const/4 v0, 0x0

    move v1, v0

    :goto_0
    if-ge v0, p1, :cond_1

    aget-object v2, p0, v0

    .line 220
    invoke-virtual {v2}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    .line 221
    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_0

    const-string v3, "!"

    invoke-virtual {v2, v3}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v2

    if-nez v2, :cond_0

    add-int/lit8 v1, v1, 0x1

    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_1
    return v1
.end method

.method private extractMetadataValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    .line 211
    new-instance p0, Ljava/lang/StringBuilder;

    const-string v0, "!\\s*"

    invoke-direct {p0, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p2, ":\\s*(.+)"

    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Ljava/util/regex/Pattern;->compile(Ljava/lang/String;)Ljava/util/regex/Pattern;

    move-result-object p0

    .line 212
    invoke-virtual {p0, p1}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object p0

    .line 213
    invoke-virtual {p0}, Ljava/util/regex/Matcher;->find()Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Ljava/util/regex/Matcher;->group(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method private extractRedirect(Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    .line 191
    sget-object p0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->redirectPattern:Ljava/util/regex/Pattern;

    invoke-virtual {p0, p1}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object p0

    .line 192
    invoke-virtual {p0}, Ljava/util/regex/Matcher;->find()Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Ljava/util/regex/Matcher;->group(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method private fetchSubscription(Ljava/lang/String;I)Z
    .locals 9

    .line 143
    const-string/jumbo v0, "metadata_"

    const-string/jumbo v1, "redirect_"

    const/4 v2, 0x0

    :try_start_0
    new-instance v3, Lokhttp3/Request$Builder;

    invoke-direct {v3}, Lokhttp3/Request$Builder;-><init>()V

    invoke-virtual {v3, p1}, Lokhttp3/Request$Builder;->url(Ljava/lang/String;)Lokhttp3/Request$Builder;

    move-result-object v3

    const-string v4, "User-Agent"

    const-string v5, "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.4 Safari/605.1.15"

    invoke-virtual {v3, v4, v5}, Lokhttp3/Request$Builder;->header(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Request$Builder;

    move-result-object v3

    invoke-virtual {v3}, Lokhttp3/Request$Builder;->build()Lokhttp3/Request;

    move-result-object v3

    .line 144
    iget-object v4, p0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->client:Lokhttp3/OkHttpClient;

    invoke-virtual {v4, v3}, Lokhttp3/OkHttpClient;->newCall(Lokhttp3/Request;)Lokhttp3/Call;

    move-result-object v3

    invoke-interface {v3}, Lokhttp3/Call;->execute()Lokhttp3/Response;

    move-result-object v3
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1

    .line 145
    :try_start_1
    invoke-virtual {v3}, Lokhttp3/Response;->isSuccessful()Z

    move-result v4
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    if-nez v4, :cond_1

    .line 184
    :cond_0
    :goto_0
    :try_start_2
    invoke-virtual {v3}, Lokhttp3/Response;->close()V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_1

    return v2

    .line 149
    :cond_1
    :try_start_3
    invoke-virtual {v3}, Lokhttp3/Response;->body()Lokhttp3/ResponseBody;

    move-result-object v4

    invoke-virtual {v4}, Lokhttp3/ResponseBody;->string()Ljava/lang/String;

    move-result-object v4

    .line 151
    invoke-direct {p0, v4}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->extractRedirect(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    const/4 v6, 0x1

    if-eqz v5, :cond_3

    const/4 v0, 0x3

    if-ge p2, v0, :cond_0

    add-int/2addr p2, v6

    .line 153
    invoke-direct {p0, v5, p2}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->fetchSubscription(Ljava/lang/String;I)Z

    move-result p2

    if-nez p2, :cond_2

    goto :goto_0

    .line 156
    :cond_2
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->unsubscribe(Ljava/lang/String;)V

    .line 157
    iget-object p2, p0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->lock:Ljava/lang/Object;

    monitor-enter p2
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    .line 158
    :try_start_4
    iget-object p0, p0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->prefs:Landroid/content/SharedPreferences;

    invoke-interface {p0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-interface {p0, p1, v5}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 159
    monitor-exit p2
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    .line 184
    :goto_1
    :try_start_5
    invoke-virtual {v3}, Lokhttp3/Response;->close()V
    :try_end_5
    .catch Ljava/lang/Exception; {:try_start_5 .. :try_end_5} :catch_1

    return v6

    :catchall_0
    move-exception p0

    .line 159
    :try_start_6
    monitor-exit p2
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_0

    :try_start_7
    throw p0

    :catchall_1
    move-exception p0

    goto/16 :goto_3

    .line 163
    :cond_3
    invoke-direct {p0, p1, v4}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->parseMetadata(Ljava/lang/String;Ljava/lang/String;)Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager$FilterMetadata;

    move-result-object p2

    .line 165
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->getFileForUrl(Ljava/lang/String;)Ljava/io/File;

    move-result-object v1

    .line 166
    new-instance v5, Ljava/io/File;

    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v8, ".tmp"

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-direct {v5, v7}, Ljava/io/File;-><init>(Ljava/lang/String;)V
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_1

    .line 167
    :try_start_8
    new-instance v7, Ljava/io/FileOutputStream;

    invoke-direct {v7, v5}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V
    :try_end_8
    .catch Ljava/io/IOException; {:try_start_8 .. :try_end_8} :catch_0
    .catchall {:try_start_8 .. :try_end_8} :catchall_1

    .line 168
    :try_start_9
    sget-object v8, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {v4, v8}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object v4

    invoke-virtual {v7, v4}, Ljava/io/FileOutputStream;->write([B)V
    :try_end_9
    .catchall {:try_start_9 .. :try_end_9} :catchall_3

    .line 169
    :try_start_a
    invoke-virtual {v7}, Ljava/io/FileOutputStream;->close()V
    :try_end_a
    .catch Ljava/io/IOException; {:try_start_a .. :try_end_a} :catch_0
    .catchall {:try_start_a .. :try_end_a} :catchall_1

    .line 173
    :try_start_b
    invoke-virtual {v5, v1}, Ljava/io/File;->renameTo(Ljava/io/File;)Z

    move-result v1

    if-nez v1, :cond_4

    .line 174
    invoke-virtual {v5}, Ljava/io/File;->delete()Z

    goto/16 :goto_0

    .line 178
    :cond_4
    iget-object v1, p0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->lock:Ljava/lang/Object;

    monitor-enter v1
    :try_end_b
    .catchall {:try_start_b .. :try_end_b} :catchall_1

    .line 179
    :try_start_c
    iget-object p0, p0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->prefs:Landroid/content/SharedPreferences;

    invoke-interface {p0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    .line 180
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p2}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager$FilterMetadata;->-$$Nest$mtoJson(Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager$FilterMetadata;)Lorg/json/JSONObject;

    move-result-object p2

    invoke-virtual {p2}, Lorg/json/JSONObject;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-interface {p0, p1, p2}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 181
    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 182
    monitor-exit v1

    goto :goto_1

    :catchall_2
    move-exception p0

    monitor-exit v1
    :try_end_c
    .catchall {:try_start_c .. :try_end_c} :catchall_2

    :try_start_d
    throw p0
    :try_end_d
    .catchall {:try_start_d .. :try_end_d} :catchall_1

    :catchall_3
    move-exception p0

    .line 167
    :try_start_e
    invoke-virtual {v7}, Ljava/io/FileOutputStream;->close()V
    :try_end_e
    .catchall {:try_start_e .. :try_end_e} :catchall_4

    goto :goto_2

    :catchall_4
    move-exception p1

    :try_start_f
    invoke-virtual {p0, p1}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :goto_2
    throw p0
    :try_end_f
    .catch Ljava/io/IOException; {:try_start_f .. :try_end_f} :catch_0
    .catchall {:try_start_f .. :try_end_f} :catchall_1

    .line 170
    :catch_0
    :try_start_10
    invoke-virtual {v5}, Ljava/io/File;->delete()Z
    :try_end_10
    .catchall {:try_start_10 .. :try_end_10} :catchall_1

    .line 184
    :try_start_11
    invoke-virtual {v3}, Lokhttp3/Response;->close()V
    :try_end_11
    .catch Ljava/lang/Exception; {:try_start_11 .. :try_end_11} :catch_1

    return v2

    :goto_3
    if-eqz v3, :cond_5

    .line 144
    :try_start_12
    invoke-virtual {v3}, Lokhttp3/Response;->close()V
    :try_end_12
    .catchall {:try_start_12 .. :try_end_12} :catchall_5

    goto :goto_4

    :catchall_5
    move-exception p1

    :try_start_13
    invoke-virtual {p0, p1}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :cond_5
    :goto_4
    throw p0
    :try_end_13
    .catch Ljava/lang/Exception; {:try_start_13 .. :try_end_13} :catch_1

    :catch_1
    return v2
.end method

.method private getFileForUrl(Ljava/lang/String;)Ljava/io/File;
    .locals 2

    .line 59
    new-instance p0, Ljava/io/File;

    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getFilesDir()Ljava/io/File;

    move-result-object v0

    const-string v1, "adblock"

    invoke-direct {p0, v0, v1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 60
    invoke-virtual {p0}, Ljava/io/File;->exists()Z

    move-result v0

    if-nez v0, :cond_0

    .line 61
    invoke-virtual {p0}, Ljava/io/File;->mkdirs()Z

    .line 63
    :cond_0
    sget-object v0, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-virtual {p1, v0}, Ljava/lang/String;->getBytes(Ljava/nio/charset/Charset;)[B

    move-result-object p1

    const/16 v0, 0xa

    invoke-static {p1, v0}, Landroid/util/Base64;->encodeToString([BI)Ljava/lang/String;

    move-result-object p1

    .line 64
    new-instance v0, Ljava/io/File;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, ".txt"

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {v0, p0, p1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    return-object v0
.end method

.method public static getInstance()Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;
    .locals 1

    .line 52
    sget-object v0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->instance:Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;

    if-nez v0, :cond_0

    .line 53
    new-instance v0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;

    invoke-direct {v0}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->instance:Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;

    .line 55
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->instance:Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;

    return-object v0
.end method

.method private synthetic lambda$update$0([Ljava/lang/String;Ljava/lang/Runnable;)V
    .locals 9

    .line 69
    new-instance v0, Ljava/util/LinkedHashSet;

    invoke-direct {v0}, Ljava/util/LinkedHashSet;-><init>()V

    .line 70
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    .line 71
    invoke-virtual {p0}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->getSubscriptions()Ljava/util/List;

    move-result-object v3

    invoke-interface {v3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :cond_0
    :goto_0
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_2

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager$FilterMetadata;

    .line 72
    iget-wide v5, v4, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager$FilterMetadata;->expires:J

    cmp-long v5, v1, v5

    if-gez v5, :cond_1

    iget-object v5, v4, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager$FilterMetadata;->url:Ljava/lang/String;

    invoke-direct {p0, v5}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->getFileForUrl(Ljava/lang/String;)Ljava/io/File;

    move-result-object v5

    invoke-virtual {v5}, Ljava/io/File;->exists()Z

    move-result v5

    if-nez v5, :cond_0

    .line 73
    :cond_1
    iget-object v4, v4, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager$FilterMetadata;->url:Ljava/lang/String;

    invoke-virtual {v0, v4}, Ljava/util/AbstractCollection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 76
    :cond_2
    iget-object v1, p0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->lock:Ljava/lang/Object;

    monitor-enter v1

    .line 77
    :try_start_0
    array-length v2, p1

    const/4 v3, 0x0

    move v4, v3

    :goto_1
    if-ge v4, v2, :cond_4

    aget-object v5, p1, v4

    .line 78
    iget-object v6, p0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->prefs:Landroid/content/SharedPreferences;

    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    const-string/jumbo v8, "metadata_"

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-interface {v6, v7}, Landroid/content/SharedPreferences;->contains(Ljava/lang/String;)Z

    move-result v6

    if-nez v6, :cond_3

    iget-object v6, p0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->prefs:Landroid/content/SharedPreferences;

    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    const-string/jumbo v8, "redirect_"

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-interface {v6, v7}, Landroid/content/SharedPreferences;->contains(Ljava/lang/String;)Z

    move-result v6

    if-nez v6, :cond_3

    .line 79
    invoke-virtual {v0, v5}, Ljava/util/AbstractCollection;->add(Ljava/lang/Object;)Z

    goto :goto_2

    :catchall_0
    move-exception p0

    goto :goto_4

    :cond_3
    :goto_2
    add-int/lit8 v4, v4, 0x1

    goto :goto_1

    .line 82
    :cond_4
    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 83
    invoke-virtual {v0}, Ljava/util/AbstractCollection;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_3
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_5

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 84
    invoke-direct {p0, v0, v3}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->fetchSubscription(Ljava/lang/String;I)Z

    goto :goto_3

    :cond_5
    if-eqz p2, :cond_6

    .line 87
    invoke-interface {p2}, Ljava/lang/Runnable;->run()V

    :cond_6
    return-void

    .line 82
    :goto_4
    :try_start_1
    monitor-exit v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p0
.end method

.method private parseMetadata(Ljava/lang/String;Ljava/lang/String;)Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager$FilterMetadata;
    .locals 10

    .line 196
    const-string v0, "Title"

    invoke-direct {p0, p2, v0}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->extractMetadataValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 197
    const-string v1, "Homepage"

    invoke-direct {p0, p2, v1}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->extractMetadataValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 198
    invoke-direct {p0, p2}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->countRules(Ljava/lang/String;)I

    move-result v6

    .line 199
    invoke-direct {p0, p2}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->calculateExpiration(Ljava/lang/String;)J

    move-result-wide v7

    .line 201
    new-instance v2, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager$FilterMetadata;

    if-eqz v0, :cond_0

    :goto_0
    move-object v4, v0

    goto :goto_1

    .line 203
    :cond_0
    const-string v0, "Unnamed list"

    goto :goto_0

    :goto_1
    if-eqz v1, :cond_1

    :goto_2
    move-object v5, v1

    goto :goto_3

    .line 204
    :cond_1
    const-string v1, ""

    goto :goto_2

    :goto_3
    const/4 v9, 0x0

    move-object v3, p1

    invoke-direct/range {v2 .. v9}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager$FilterMetadata;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IJLcom/exteragram/messenger/adblock/backend/SubscriptionsManager-IA;)V

    return-object v2
.end method

.method private unsubscribe(Ljava/lang/String;)V
    .locals 4

    .line 97
    const-string/jumbo v0, "metadata_"

    iget-object v1, p0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->lock:Ljava/lang/Object;

    monitor-enter v1

    .line 98
    :try_start_0
    iget-object v2, p0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->prefs:Landroid/content/SharedPreferences;

    invoke-interface {v2}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v2

    .line 99
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-interface {v2, v0}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 100
    invoke-interface {v2}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 101
    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 102
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->getFileForUrl(Ljava/lang/String;)Ljava/io/File;

    move-result-object p0

    .line 103
    invoke-virtual {p0}, Ljava/io/File;->exists()Z

    move-result p1

    if-eqz p1, :cond_0

    .line 104
    invoke-virtual {p0}, Ljava/io/File;->delete()Z

    :cond_0
    return-void

    :catchall_0
    move-exception p0

    .line 101
    :try_start_1
    monitor-exit v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p0
.end method


# virtual methods
.method public getSubscriptionFilePaths()Ljava/util/List;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .line 130
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 131
    invoke-virtual {p0}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->getSubscriptions()Ljava/util/List;

    move-result-object v1

    .line 132
    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager$FilterMetadata;

    .line 133
    iget-object v2, v2, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager$FilterMetadata;->url:Ljava/lang/String;

    invoke-direct {p0, v2}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->getFileForUrl(Ljava/lang/String;)Ljava/io/File;

    move-result-object v2

    .line 134
    invoke-virtual {v2}, Ljava/io/File;->exists()Z

    move-result v3

    if-eqz v3, :cond_0

    .line 135
    invoke-virtual {v2}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_1
    return-object v0
.end method

.method public getSubscriptions()Ljava/util/List;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager$FilterMetadata;",
            ">;"
        }
    .end annotation

    .line 110
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 111
    iget-object v1, p0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->lock:Ljava/lang/Object;

    monitor-enter v1

    .line 112
    :try_start_0
    iget-object v2, p0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->prefs:Landroid/content/SharedPreferences;

    invoke-interface {v2}, Landroid/content/SharedPreferences;->getAll()Ljava/util/Map;

    move-result-object v2

    .line 113
    invoke-interface {v2}, Ljava/util/Map;->keySet()Ljava/util/Set;

    move-result-object v2

    invoke-interface {v2}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :catch_0
    :cond_0
    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_1

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    .line 114
    const-string/jumbo v4, "metadata_"

    invoke-virtual {v3, v4}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v4
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v4, :cond_0

    .line 116
    :try_start_1
    iget-object v4, p0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->prefs:Landroid/content/SharedPreferences;

    const/4 v5, 0x0

    invoke-interface {v4, v3, v5}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    if-eqz v3, :cond_0

    .line 118
    new-instance v4, Lorg/json/JSONObject;

    invoke-direct {v4, v3}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    invoke-static {v4}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager$FilterMetadata;->-$$Nest$smfromJson(Lorg/json/JSONObject;)Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager$FilterMetadata;

    move-result-object v3

    invoke-interface {v0, v3}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_1
    .catch Lorg/json/JSONException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    .line 124
    :cond_1
    :try_start_2
    monitor-exit v1
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 125
    invoke-static {v0}, Ljava/util/Collections;->unmodifiableList(Ljava/util/List;)Ljava/util/List;

    move-result-object p0

    return-object p0

    .line 124
    :goto_1
    :try_start_3
    monitor-exit v1
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    throw p0
.end method

.method public hasFilters()Z
    .locals 0

    .line 93
    invoke-virtual {p0}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->getSubscriptionFilePaths()Ljava/util/List;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/List;->isEmpty()Z

    move-result p0

    xor-int/lit8 p0, p0, 0x1

    return p0
.end method

.method public update([Ljava/lang/String;Ljava/lang/Runnable;)V
    .locals 2

    .line 68
    iget-object v0, p0, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;->queue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v1, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0, p1, p2}, Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/adblock/backend/SubscriptionsManager;[Ljava/lang/String;Ljava/lang/Runnable;)V

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method
