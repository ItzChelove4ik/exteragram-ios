.class public Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$ScriptletInfo;,
        Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$Scriptlet;
    }
.end annotation


# static fields
.field private static final SCRIPTLETS_MAP:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$ScriptletInfo;",
            ">;"
        }
    .end annotation
.end field

.field private static instance:Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;


# instance fields
.field private final client:Lokhttp3/OkHttpClient;

.field private final lock:Ljava/lang/Object;

.field private final prefs:Landroid/content/SharedPreferences;

.field private final queue:Lorg/telegram/messenger/DispatchQueue;


# direct methods
.method public static synthetic $r8$lambda$7FMBwQ17heqJ6aKfwlFCy3qfSzk(Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;Lorg/telegram/messenger/Utilities$Callback;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->lambda$download$0(Lorg/telegram/messenger/Utilities$Callback;)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 1

    .line 33
    new-instance v0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$1;

    invoke-direct {v0}, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$1;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->SCRIPTLETS_MAP:Ljava/util/Map;

    return-void
.end method

.method public constructor <init>()V
    .locals 2

    .line 94
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 92
    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->lock:Ljava/lang/Object;

    .line 95
    new-instance v0, Lorg/telegram/messenger/DispatchQueue;

    const-string v1, "ScriptletsManager"

    invoke-direct {v0, v1}, Lorg/telegram/messenger/DispatchQueue;-><init>(Ljava/lang/String;)V

    iput-object v0, p0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->queue:Lorg/telegram/messenger/DispatchQueue;

    .line 96
    sget-object v0, Lcom/exteragram/messenger/utils/network/ExteraHttpClient;->INSTANCE:Lcom/exteragram/messenger/utils/network/ExteraHttpClient;

    invoke-virtual {v0}, Lcom/exteragram/messenger/utils/network/ExteraHttpClient;->getClient()Lokhttp3/OkHttpClient;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->client:Lokhttp3/OkHttpClient;

    .line 97
    const-string/jumbo v0, "ublock_scriptlets"

    invoke-static {v0}, Lcom/exteragram/messenger/backup/PreferencesUtils;->getPreferences(Ljava/lang/String;)Landroid/content/SharedPreferences;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->prefs:Landroid/content/SharedPreferences;

    return-void
.end method

.method private downloadScriptlet(Ljava/lang/String;Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$ScriptletInfo;)Z
    .locals 8

    .line 162
    iget-object v0, p0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    .line 163
    :try_start_0
    iget-object v1, p0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->prefs:Landroid/content/SharedPreferences;

    invoke-interface {v1, p1}, Landroid/content/SharedPreferences;->contains(Ljava/lang/String;)Z

    move-result v1

    const/4 v2, 0x1

    if-eqz v1, :cond_0

    .line 164
    monitor-exit v0

    return v2

    :catchall_0
    move-exception p0

    goto/16 :goto_5

    .line 166
    :cond_0
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 167
    new-instance v0, Lokhttp3/Request$Builder;

    invoke-direct {v0}, Lokhttp3/Request$Builder;-><init>()V

    new-instance v1, Ljava/lang/StringBuilder;

    const-string/jumbo v3, "https://raw.githubusercontent.com/gorhill/uBlock/master/src/web_accessible_resources/"

    invoke-direct {v1, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lokhttp3/Request$Builder;->url(Ljava/lang/String;)Lokhttp3/Request$Builder;

    move-result-object v0

    const-string v1, "User-Agent"

    const-string v3, "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.4 Safari/605.1.15"

    invoke-virtual {v0, v1, v3}, Lokhttp3/Request$Builder;->header(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Request$Builder;

    move-result-object v0

    invoke-virtual {v0}, Lokhttp3/Request$Builder;->build()Lokhttp3/Request;

    move-result-object v0

    const/4 v1, 0x0

    .line 168
    :try_start_1
    iget-object v3, p0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->client:Lokhttp3/OkHttpClient;

    invoke-virtual {v3, v0}, Lokhttp3/OkHttpClient;->newCall(Lokhttp3/Request;)Lokhttp3/Call;

    move-result-object v0

    invoke-interface {v0}, Lokhttp3/Call;->execute()Lokhttp3/Response;

    move-result-object v0
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_0

    .line 169
    :try_start_2
    invoke-virtual {v0}, Lokhttp3/Response;->code()I

    move-result v3
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_2

    const/16 v4, 0x194

    if-ne v3, v4, :cond_1

    .line 196
    :goto_0
    :try_start_3
    invoke-virtual {v0}, Lokhttp3/Response;->close()V
    :try_end_3
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_3} :catch_0

    return v2

    .line 172
    :cond_1
    :try_start_4
    invoke-virtual {v0}, Lokhttp3/Response;->isSuccessful()Z

    move-result v3
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_2

    if-nez v3, :cond_2

    .line 196
    :try_start_5
    invoke-virtual {v0}, Lokhttp3/Response;->close()V
    :try_end_5
    .catch Ljava/io/IOException; {:try_start_5 .. :try_end_5} :catch_0

    return v1

    .line 175
    :cond_2
    :try_start_6
    invoke-virtual {v0}, Lokhttp3/Response;->body()Lokhttp3/ResponseBody;

    move-result-object v3

    invoke-virtual {v3}, Lokhttp3/ResponseBody;->bytes()[B

    move-result-object v3

    const/4 v4, 0x2

    .line 176
    invoke-static {v3, v4}, Landroid/util/Base64;->encodeToString([BI)Ljava/lang/String;

    move-result-object v3

    .line 178
    iget-object v4, p0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->lock:Ljava/lang/Object;

    monitor-enter v4
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_2

    .line 179
    :try_start_7
    iget-object p0, p0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->prefs:Landroid/content/SharedPreferences;

    invoke-interface {p0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    .line 180
    invoke-interface {p0, p1, v3}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 182
    iget-object v3, p2, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$ScriptletInfo;->alias:Ljava/lang/Object;

    if-eqz v3, :cond_5

    .line 183
    new-instance v3, Lorg/json/JSONArray;

    invoke-direct {v3}, Lorg/json/JSONArray;-><init>()V

    .line 184
    iget-object p2, p2, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$ScriptletInfo;->alias:Ljava/lang/Object;

    instance-of v5, p2, Ljava/lang/String;

    if-eqz v5, :cond_3

    .line 185
    invoke-virtual {v3, p2}, Lorg/json/JSONArray;->put(Ljava/lang/Object;)Lorg/json/JSONArray;

    goto :goto_2

    :catchall_1
    move-exception p0

    goto :goto_3

    .line 186
    :cond_3
    instance-of v5, p2, [Ljava/lang/String;

    if-eqz v5, :cond_4

    .line 187
    check-cast p2, [Ljava/lang/String;

    array-length v5, p2

    move v6, v1

    :goto_1
    if-ge v6, v5, :cond_4

    aget-object v7, p2, v6

    .line 188
    invoke-virtual {v3, v7}, Lorg/json/JSONArray;->put(Ljava/lang/Object;)Lorg/json/JSONArray;

    add-int/lit8 v6, v6, 0x1

    goto :goto_1

    .line 191
    :cond_4
    :goto_2
    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "_aliases"

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v3}, Lorg/json/JSONArray;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-interface {p0, p1, p2}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 193
    :cond_5
    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 194
    monitor-exit v4

    goto :goto_0

    :goto_3
    monitor-exit v4
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_1

    :try_start_8
    throw p0
    :try_end_8
    .catchall {:try_start_8 .. :try_end_8} :catchall_2

    :catchall_2
    move-exception p0

    if-eqz v0, :cond_6

    .line 168
    :try_start_9
    invoke-virtual {v0}, Lokhttp3/Response;->close()V
    :try_end_9
    .catchall {:try_start_9 .. :try_end_9} :catchall_3

    goto :goto_4

    :catchall_3
    move-exception p1

    :try_start_a
    invoke-virtual {p0, p1}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :cond_6
    :goto_4
    throw p0
    :try_end_a
    .catch Ljava/io/IOException; {:try_start_a .. :try_end_a} :catch_0

    :catch_0
    return v1

    .line 166
    :goto_5
    :try_start_b
    monitor-exit v0
    :try_end_b
    .catchall {:try_start_b .. :try_end_b} :catchall_0

    throw p0
.end method

.method public static getExtension(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    .line 108
    const-string v0, ".css"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_0

    return-object v0

    .line 111
    :cond_0
    const-string v0, ".gif"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_1

    return-object v0

    .line 114
    :cond_1
    const-string v0, ".html"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_2

    return-object v0

    .line 117
    :cond_2
    const-string v0, ".js"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_3

    return-object v0

    .line 120
    :cond_3
    const-string v0, ".json"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_4

    return-object v0

    .line 123
    :cond_4
    const-string v0, ".mp3"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_5

    return-object v0

    .line 126
    :cond_5
    const-string v0, ".mp4"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_6

    return-object v0

    .line 129
    :cond_6
    const-string v0, ".png"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_7

    return-object v0

    .line 132
    :cond_7
    const-string v0, ".txt"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_8

    return-object v0

    .line 135
    :cond_8
    const-string v1, ".xml"

    invoke-virtual {p0, v1}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_9

    return-object v1

    :cond_9
    return-object v0
.end method

.method public static getInstance()Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;
    .locals 1

    .line 101
    sget-object v0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->instance:Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;

    if-nez v0, :cond_0

    .line 102
    new-instance v0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;

    invoke-direct {v0}, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->instance:Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;

    .line 104
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->instance:Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;

    return-object v0
.end method

.method private synthetic lambda$download$0(Lorg/telegram/messenger/Utilities$Callback;)V
    .locals 4

    .line 144
    sget-object v0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->SCRIPTLETS_MAP:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    const/4 v2, 0x1

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    .line 145
    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$ScriptletInfo;

    invoke-direct {p0, v3, v1}, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->downloadScriptlet(Ljava/lang/String;Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$ScriptletInfo;)Z

    move-result v1

    if-nez v1, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    :cond_1
    move v0, v2

    :goto_0
    if-eqz v0, :cond_2

    .line 151
    iget-object v1, p0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->lock:Ljava/lang/Object;

    monitor-enter v1

    .line 152
    :try_start_0
    iget-object p0, p0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->prefs:Landroid/content/SharedPreferences;

    invoke-interface {p0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    const-string v3, "__downloaded"

    invoke-interface {p0, v3, v2}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 153
    monitor-exit v1

    goto :goto_1

    :catchall_0
    move-exception p0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0

    :cond_2
    :goto_1
    if-eqz p1, :cond_3

    .line 156
    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0

    invoke-interface {p1, p0}, Lorg/telegram/messenger/Utilities$Callback;->run(Ljava/lang/Object;)V

    :cond_3
    return-void
.end method


# virtual methods
.method public download(Lorg/telegram/messenger/Utilities$Callback;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/telegram/messenger/Utilities$Callback<",
            "Ljava/lang/Boolean;",
            ">;)V"
        }
    .end annotation

    .line 142
    iget-object v0, p0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->queue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v1, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0, p1}, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;Lorg/telegram/messenger/Utilities$Callback;)V

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public isDownloaded()Z
    .locals 3

    .line 202
    iget-object v0, p0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->lock:Ljava/lang/Object;

    monitor-enter v0

    .line 203
    :try_start_0
    iget-object p0, p0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->prefs:Landroid/content/SharedPreferences;

    const-string v1, "__downloaded"

    const/4 v2, 0x0

    invoke-interface {p0, v1, v2}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    monitor-exit v0

    return p0

    :catchall_0
    move-exception p0

    .line 204
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method public iterScriptlets()Ljava/util/Collection;
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Collection<",
            "Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$Scriptlet;",
            ">;"
        }
    .end annotation

    .line 209
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 211
    iget-object v1, p0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->lock:Ljava/lang/Object;

    monitor-enter v1

    .line 212
    :try_start_0
    sget-object v2, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->SCRIPTLETS_MAP:Ljava/util/Map;

    invoke-interface {v2}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v2

    invoke-interface {v2}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_0
    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_2

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/util/Map$Entry;

    .line 213
    invoke-interface {v3}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    .line 214
    iget-object v4, p0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->prefs:Landroid/content/SharedPreferences;

    const/4 v5, 0x0

    invoke-interface {v4, v3, v5}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    if-eqz v4, :cond_0

    .line 217
    new-instance v6, Ljava/util/ArrayList;

    invoke-direct {v6}, Ljava/util/ArrayList;-><init>()V

    .line 218
    iget-object v7, p0, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager;->prefs:Landroid/content/SharedPreferences;

    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v8, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v9, "_aliases"

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-interface {v7, v8, v5}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v7, :cond_1

    .line 222
    :try_start_1
    new-instance v8, Lorg/json/JSONArray;

    invoke-direct {v8, v7}, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V

    const/4 v7, 0x0

    .line 223
    :goto_1
    invoke-virtual {v8}, Lorg/json/JSONArray;->length()I

    move-result v9

    if-ge v7, v9, :cond_1

    .line 224
    invoke-virtual {v8, v7}, Lorg/json/JSONArray;->getString(I)Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v6, v9}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z
    :try_end_1
    .catch Lorg/json/JSONException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    add-int/lit8 v7, v7, 0x1

    goto :goto_1

    :catchall_0
    move-exception p0

    goto :goto_2

    .line 230
    :catch_0
    :cond_1
    :try_start_2
    new-instance v7, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$Scriptlet;

    invoke-direct {v7, v3, v6, v4, v5}, Lcom/exteragram/messenger/adblock/backend/ScriptletsManager$Scriptlet;-><init>(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/exteragram/messenger/adblock/backend/ScriptletsManager-IA;)V

    invoke-interface {v0, v7}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 233
    :cond_2
    monitor-exit v1
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 235
    invoke-static {v0}, Ljava/util/Collections;->unmodifiableList(Ljava/util/List;)Ljava/util/List;

    move-result-object p0

    return-object p0

    .line 233
    :goto_2
    :try_start_3
    monitor-exit v1
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    throw p0
.end method
