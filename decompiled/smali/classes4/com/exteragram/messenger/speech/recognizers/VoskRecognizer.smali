.class public Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionProvider;
.implements Ljava/lang/AutoCloseable;


# instance fields
.field private final client:Lokhttp3/OkHttpClient;

.field private final loadedModels:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Lorg/vosk/Model;",
            ">;"
        }
    .end annotation
.end field

.field private final models:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;",
            ">;"
        }
    .end annotation
.end field

.field private final modelsDir:Ljava/io/File;


# direct methods
.method public static synthetic $r8$lambda$24CEprXJp1zOKeZzj9XgmV9Hp6M(Ljava/lang/String;Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;)Z
    .locals 0

    .line 250
    invoke-virtual {p1}, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;->getLanguage()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$3Xv1azFJtwThwB0jQsW9JYtX44E(Ljava/lang/String;)Ljava/lang/IllegalArgumentException;
    .locals 3

    .line 157
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Model not found: "

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    return-object v0
.end method

.method public static synthetic $r8$lambda$BkjlPKLR-HILlaZGEU7X7SJ5FRI(Ljava/lang/String;Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;)Z
    .locals 0

    .line 260
    invoke-virtual {p1}, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;->getLanguage()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$MtzCrWBshN7rmxoQ9Z8XAbCYDDA(Ljava/lang/String;)Ljava/lang/IllegalArgumentException;
    .locals 3

    .line 218
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Model not found: "

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    return-object v0
.end method

.method public static synthetic $r8$lambda$OCePRm0KUqV3PSMV_6Yq_0ZSAso(Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;)Z
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->lambda$listDownloadedModels$0(Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;)Z

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$f5p2Qnm1JoBv4np-X_eqVy9QyiQ(Ljava/lang/String;Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;)Z
    .locals 0

    .line 155
    invoke-virtual {p1}, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;->getLanguage()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$pfREJSiBahlF3yJLlAZp-lcczUQ(Ljava/lang/String;Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;)Z
    .locals 0

    .line 216
    invoke-virtual {p1}, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;->getLanguage()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public constructor <init>()V
    .locals 3

    .line 48
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 49
    sget-object v0, Lcom/exteragram/messenger/utils/network/ExteraHttpClient;->INSTANCE:Lcom/exteragram/messenger/utils/network/ExteraHttpClient;

    invoke-virtual {v0}, Lcom/exteragram/messenger/utils/network/ExteraHttpClient;->getClient()Lokhttp3/OkHttpClient;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->client:Lokhttp3/OkHttpClient;

    .line 50
    new-instance v0, Ljava/io/File;

    sget-object v1, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    const/4 v2, 0x0

    .line 51
    invoke-virtual {v1, v2}, Landroid/content/Context;->getExternalFilesDir(Ljava/lang/String;)Ljava/io/File;

    move-result-object v1

    const-string v2, "Vosk Models"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    iput-object v0, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->modelsDir:Ljava/io/File;

    .line 54
    new-instance v0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer$1;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer$1;-><init>(Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;)V

    iput-object v0, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->models:Ljava/util/List;

    .line 81
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->loadedModels:Ljava/util/Map;

    return-void
.end method

.method private deleteDirectory(Ljava/io/File;)V
    .locals 4

    .line 234
    invoke-virtual {p1}, Ljava/io/File;->isDirectory()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 235
    invoke-virtual {p1}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 237
    array-length v1, v0

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_0

    aget-object v3, v0, v2

    .line 238
    invoke-direct {p0, v3}, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->deleteDirectory(Ljava/io/File;)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 242
    :cond_0
    invoke-virtual {p1}, Ljava/io/File;->delete()Z

    move-result p0

    if-nez p0, :cond_1

    .line 243
    new-instance p0, Ljava/lang/StringBuilder;

    const-string v0, "Failed to delete file or directory: "

    invoke-direct {p0, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;)V

    :cond_1
    return-void
.end method

.method private isDirectoryEmpty(Ljava/io/File;)Z
    .locals 0

    .line 148
    invoke-virtual {p1}, Ljava/io/File;->list()[Ljava/lang/String;

    move-result-object p0

    if-eqz p0, :cond_1

    .line 149
    array-length p0, p0

    if-nez p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method private synthetic lambda$listDownloadedModels$0(Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;)Z
    .locals 2

    .line 138
    new-instance v0, Ljava/io/File;

    iget-object v1, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->modelsDir:Ljava/io/File;

    invoke-virtual {p1}, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;->getLanguage()Ljava/lang/String;

    move-result-object p1

    invoke-direct {v0, v1, p1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 139
    new-instance p1, Ljava/io/File;

    const-string v1, "model.zip"

    invoke-direct {p1, v0, v1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 140
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 141
    invoke-virtual {p1}, Ljava/io/File;->exists()Z

    move-result p1

    if-nez p1, :cond_0

    .line 142
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->isDirectoryEmpty(Ljava/io/File;)Z

    move-result p0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private static unpackZip(Ljava/lang/String;Ljava/lang/String;)V
    .locals 8

    .line 84
    new-instance v0, Ljava/io/File;

    invoke-direct {v0, p1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 85
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result p1

    if-nez p1, :cond_0

    .line 86
    invoke-virtual {v0}, Ljava/io/File;->mkdirs()Z

    :cond_0
    const/16 p1, 0x400

    .line 89
    new-array p1, p1, [B

    .line 90
    new-instance v1, Ljava/util/zip/ZipInputStream;

    new-instance v2, Ljava/io/FileInputStream;

    invoke-direct {v2, p0}, Ljava/io/FileInputStream;-><init>(Ljava/lang/String;)V

    invoke-direct {v1, v2}, Ljava/util/zip/ZipInputStream;-><init>(Ljava/io/InputStream;)V

    .line 91
    invoke-virtual {v1}, Ljava/util/zip/ZipInputStream;->getNextEntry()Ljava/util/zip/ZipEntry;

    move-result-object p0

    const/4 v2, 0x0

    .line 95
    const-string v3, "/"

    if-eqz p0, :cond_1

    .line 96
    invoke-virtual {p0}, Ljava/util/zip/ZipEntry;->getName()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4, v3}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v4

    aget-object v4, v4, v2

    goto :goto_0

    :cond_1
    const/4 v4, 0x0

    :goto_0
    if-eqz p0, :cond_5

    .line 101
    invoke-virtual {p0}, Ljava/util/zip/ZipEntry;->getName()Ljava/lang/String;

    move-result-object v5

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_2

    .line 102
    invoke-virtual {v1}, Ljava/util/zip/ZipInputStream;->getNextEntry()Ljava/util/zip/ZipEntry;

    move-result-object p0

    goto :goto_0

    .line 107
    :cond_2
    invoke-virtual {p0}, Ljava/util/zip/ZipEntry;->getName()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4}, Ljava/lang/String;->length()I

    move-result v6

    add-int/lit8 v6, v6, 0x1

    invoke-virtual {v5, v6}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v5

    .line 108
    new-instance v6, Ljava/io/File;

    invoke-direct {v6, v0, v5}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 111
    new-instance v5, Ljava/io/File;

    invoke-virtual {v6}, Ljava/io/File;->getParent()Ljava/lang/String;

    move-result-object v7

    invoke-direct {v5, v7}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v5}, Ljava/io/File;->mkdirs()Z

    .line 113
    invoke-virtual {p0}, Ljava/util/zip/ZipEntry;->isDirectory()Z

    move-result p0

    if-nez p0, :cond_4

    .line 114
    new-instance p0, Ljava/io/FileOutputStream;

    invoke-direct {p0, v6}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V

    .line 116
    :goto_1
    invoke-virtual {v1, p1}, Ljava/io/InputStream;->read([B)I

    move-result v5

    if-lez v5, :cond_3

    .line 117
    invoke-virtual {p0, p1, v2, v5}, Ljava/io/FileOutputStream;->write([BII)V

    goto :goto_1

    .line 119
    :cond_3
    invoke-virtual {p0}, Ljava/io/FileOutputStream;->close()V

    .line 122
    :cond_4
    invoke-virtual {v1}, Ljava/util/zip/ZipInputStream;->getNextEntry()Ljava/util/zip/ZipEntry;

    move-result-object p0

    goto :goto_0

    .line 125
    :cond_5
    invoke-virtual {v1}, Ljava/util/zip/ZipInputStream;->closeEntry()V

    .line 126
    invoke-virtual {v1}, Ljava/util/zip/ZipInputStream;->close()V

    return-void
.end method


# virtual methods
.method public close()V
    .locals 0

    .line 347
    invoke-virtual {p0}, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->unloadModels()V

    return-void
.end method

.method public deleteModel(Ljava/lang/String;)V
    .locals 3

    .line 215
    iget-object v0, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->models:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/Collection;->stream()Ljava/util/stream/Stream;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer$$ExternalSyntheticLambda4;

    invoke-direct {v1, p1}, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer$$ExternalSyntheticLambda4;-><init>(Ljava/lang/String;)V

    .line 216
    invoke-interface {v0, v1}, Ljava/util/stream/Stream;->filter(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;

    move-result-object v0

    .line 217
    invoke-interface {v0}, Ljava/util/stream/Stream;->findFirst()Ljava/util/Optional;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer$$ExternalSyntheticLambda5;

    invoke-direct {v1, p1}, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer$$ExternalSyntheticLambda5;-><init>(Ljava/lang/String;)V

    .line 218
    invoke-virtual {v0, v1}, Ljava/util/Optional;->orElseThrow(Ljava/util/function/Supplier;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;

    .line 220
    iget-object v0, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->loadedModels:Ljava/util/Map;

    invoke-virtual {p1}, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;->getLanguage()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/Map;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/vosk/Model;

    if-eqz v0, :cond_0

    .line 222
    invoke-virtual {v0}, Lorg/vosk/Model;->close()V

    .line 225
    :cond_0
    new-instance v0, Ljava/io/File;

    iget-object v1, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->modelsDir:Ljava/io/File;

    invoke-virtual {p1}, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;->getLanguage()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 226
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 230
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->deleteDirectory(Ljava/io/File;)V

    return-void

    .line 227
    :cond_1
    const-string p0, "Model is not downloaded: "

    invoke-virtual {p1}, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;->getLanguage()Ljava/lang/String;

    move-result-object p1

    invoke-static {p0, p1}, Lcom/android/dx/dex/code/CstInsn$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Object;)V

    return-void
.end method

.method public downloadModel(Ljava/lang/String;Lcom/exteragram/messenger/speech/VoiceRecognitionController$DownloadModelCallback;)V
    .locals 7

    .line 154
    const-string v0, "Failed to download: "

    iget-object v1, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->models:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/Collection;->stream()Ljava/util/stream/Stream;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer$$ExternalSyntheticLambda6;

    invoke-direct {v2, p1}, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer$$ExternalSyntheticLambda6;-><init>(Ljava/lang/String;)V

    .line 155
    invoke-interface {v1, v2}, Ljava/util/stream/Stream;->filter(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;

    move-result-object v1

    .line 156
    invoke-interface {v1}, Ljava/util/stream/Stream;->findFirst()Ljava/util/Optional;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer$$ExternalSyntheticLambda7;

    invoke-direct {v2, p1}, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer$$ExternalSyntheticLambda7;-><init>(Ljava/lang/String;)V

    .line 157
    invoke-virtual {v1, v2}, Ljava/util/Optional;->orElseThrow(Ljava/util/function/Supplier;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;

    .line 159
    new-instance v1, Ljava/io/File;

    iget-object v2, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->modelsDir:Ljava/io/File;

    invoke-virtual {p1}, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;->getLanguage()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v1, v2, v3}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 161
    new-instance v2, Ljava/io/File;

    const-string v3, "model.zip"

    invoke-direct {v2, v1, v3}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 162
    invoke-virtual {v2}, Ljava/io/File;->exists()Z

    move-result v2

    if-eqz v2, :cond_0

    .line 164
    :try_start_0
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->deleteDirectory(Ljava/io/File;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p0

    .line 166
    new-instance p1, Ljava/io/IOException;

    const-string v0, "Failed to delete existing model directory"

    invoke-direct {p1, v0, p0}, Ljava/io/IOException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    invoke-interface {p2, p1}, Lcom/exteragram/messenger/speech/VoiceRecognitionController$DownloadModelCallback;->onError(Ljava/lang/Exception;)V

    return-void

    .line 171
    :cond_0
    :goto_0
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v2

    if-nez v2, :cond_1

    .line 172
    invoke-virtual {v1}, Ljava/io/File;->mkdirs()Z

    .line 176
    :cond_1
    :try_start_1
    new-instance v2, Lokhttp3/Request$Builder;

    invoke-direct {v2}, Lokhttp3/Request$Builder;-><init>()V

    invoke-virtual {p1}, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;->getUrl()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v2, p1}, Lokhttp3/Request$Builder;->url(Ljava/lang/String;)Lokhttp3/Request$Builder;

    move-result-object p1

    invoke-virtual {p1}, Lokhttp3/Request$Builder;->build()Lokhttp3/Request;

    move-result-object p1

    .line 177
    iget-object p0, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->client:Lokhttp3/OkHttpClient;

    invoke-virtual {p0, p1}, Lokhttp3/OkHttpClient;->newCall(Lokhttp3/Request;)Lokhttp3/Call;

    move-result-object p0

    invoke-interface {p0}, Lokhttp3/Call;->execute()Lokhttp3/Response;

    move-result-object p0

    .line 179
    invoke-virtual {p0}, Lokhttp3/Response;->isSuccessful()Z

    move-result p1

    if-nez p1, :cond_2

    .line 180
    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;)V

    goto :goto_1

    :catch_1
    move-exception p0

    goto/16 :goto_8

    .line 183
    :cond_2
    :goto_1
    new-instance p1, Ljava/io/File;

    invoke-direct {p1, v1, v3}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 184
    invoke-virtual {p0}, Lokhttp3/Response;->body()Lokhttp3/ResponseBody;

    move-result-object v0

    invoke-virtual {v0}, Lokhttp3/ResponseBody;->byteStream()Ljava/io/InputStream;

    move-result-object v0
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    .line 185
    :try_start_2
    new-instance v2, Ljava/io/FileOutputStream;

    invoke-direct {v2, p1}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    .line 186
    :try_start_3
    invoke-virtual {p0}, Lokhttp3/Response;->body()Lokhttp3/ResponseBody;

    move-result-object p0

    invoke-virtual {p0}, Lokhttp3/ResponseBody;->contentLength()J

    move-result-wide v3

    const/16 p0, 0x1000

    .line 188
    new-array p0, p0, [B

    .line 190
    :goto_2
    invoke-virtual {v0, p0}, Ljava/io/InputStream;->read([B)I

    move-result v5

    const/4 v6, -0x1

    if-eq v5, v6, :cond_3

    const/4 v6, 0x0

    .line 191
    invoke-virtual {v2, p0, v6, v5}, Ljava/io/OutputStream;->write([BII)V

    .line 193
    invoke-virtual {p1}, Ljava/io/File;->length()J

    move-result-wide v5

    long-to-float v5, v5

    long-to-float v6, v3

    div-float/2addr v5, v6

    invoke-interface {p2, v5}, Lcom/exteragram/messenger/speech/VoiceRecognitionController$DownloadModelCallback;->onProgress(F)V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    goto :goto_2

    :catchall_0
    move-exception p0

    goto :goto_4

    .line 195
    :cond_3
    :try_start_4
    invoke-virtual {v2}, Ljava/io/OutputStream;->close()V
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    :try_start_5
    invoke-virtual {v0}, Ljava/io/InputStream;->close()V

    .line 197
    invoke-virtual {p1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    invoke-static {p0, v0}, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->unpackZip(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_5
    .catch Ljava/lang/Exception; {:try_start_5 .. :try_end_5} :catch_1

    .line 200
    :try_start_6
    invoke-virtual {p1}, Ljava/io/File;->delete()Z

    move-result p0

    if-nez p0, :cond_4

    .line 201
    invoke-virtual {p1}, Ljava/io/File;->deleteOnExit()V
    :try_end_6
    .catch Ljava/lang/Exception; {:try_start_6 .. :try_end_6} :catch_2

    goto :goto_3

    :catch_2
    move-exception p0

    .line 204
    :try_start_7
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 207
    :cond_4
    :goto_3
    invoke-interface {p2}, Lcom/exteragram/messenger/speech/VoiceRecognitionController$DownloadModelCallback;->onCompleted()V
    :try_end_7
    .catch Ljava/lang/Exception; {:try_start_7 .. :try_end_7} :catch_1

    goto :goto_9

    :catchall_1
    move-exception p0

    goto :goto_6

    .line 184
    :goto_4
    :try_start_8
    invoke-virtual {v2}, Ljava/io/OutputStream;->close()V
    :try_end_8
    .catchall {:try_start_8 .. :try_end_8} :catchall_2

    goto :goto_5

    :catchall_2
    move-exception p1

    :try_start_9
    invoke-virtual {p0, p1}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :goto_5
    throw p0
    :try_end_9
    .catchall {:try_start_9 .. :try_end_9} :catchall_1

    :goto_6
    if-eqz v0, :cond_5

    :try_start_a
    invoke-virtual {v0}, Ljava/io/InputStream;->close()V
    :try_end_a
    .catchall {:try_start_a .. :try_end_a} :catchall_3

    goto :goto_7

    :catchall_3
    move-exception p1

    :try_start_b
    invoke-virtual {p0, p1}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :cond_5
    :goto_7
    throw p0
    :try_end_b
    .catch Ljava/lang/Exception; {:try_start_b .. :try_end_b} :catch_1

    .line 209
    :goto_8
    invoke-interface {p2, p0}, Lcom/exteragram/messenger/speech/VoiceRecognitionController$DownloadModelCallback;->onError(Ljava/lang/Exception;)V

    :goto_9
    return-void
.end method

.method public hasLoadedModels()Z
    .locals 0

    .line 342
    iget-object p0, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->loadedModels:Ljava/util/Map;

    invoke-interface {p0}, Ljava/util/Map;->isEmpty()Z

    move-result p0

    xor-int/lit8 p0, p0, 0x1

    return p0
.end method

.method public listAvailableModels()Ljava/util/List;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;",
            ">;"
        }
    .end annotation

    .line 131
    iget-object p0, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->models:Ljava/util/List;

    return-object p0
.end method

.method public listDownloadedModels()Ljava/util/List;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;",
            ">;"
        }
    .end annotation

    .line 136
    iget-object v0, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->models:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/Collection;->stream()Ljava/util/stream/Stream;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;)V

    .line 137
    invoke-interface {v0, v1}, Ljava/util/stream/Stream;->filter(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;

    move-result-object p0

    .line 144
    invoke-static {}, Ljava/util/stream/Collectors;->toList()Ljava/util/stream/Collector;

    move-result-object v0

    invoke-interface {p0, v0}, Ljava/util/stream/Stream;->collect(Ljava/util/stream/Collector;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/util/List;

    return-object p0
.end method

.method public recognize(Ljava/lang/String;Ljava/lang/String;Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionCallback;)V
    .locals 6

    .line 249
    const-string v0, "Recognizing: "

    const-string v1, "Model loaded: "

    const-string v2, "Loading model: "

    iget-object v3, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->models:Ljava/util/List;

    invoke-interface {v3}, Ljava/util/Collection;->stream()Ljava/util/stream/Stream;

    move-result-object v3

    new-instance v4, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer$$ExternalSyntheticLambda2;

    invoke-direct {v4, p2}, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer$$ExternalSyntheticLambda2;-><init>(Ljava/lang/String;)V

    .line 250
    invoke-interface {v3, v4}, Ljava/util/stream/Stream;->filter(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;

    move-result-object v3

    .line 251
    invoke-interface {v3}, Ljava/util/stream/Stream;->findFirst()Ljava/util/Optional;

    move-result-object v3

    const/4 v4, 0x0

    .line 252
    invoke-virtual {v3, v4}, Ljava/util/Optional;->orElse(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;

    if-nez v3, :cond_0

    .line 255
    invoke-interface {p3, p2}, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionCallback;->onLanguageNotSupported(Ljava/lang/String;)V

    return-void

    .line 259
    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->listDownloadedModels()Ljava/util/List;

    move-result-object v3

    invoke-interface {v3}, Ljava/util/Collection;->stream()Ljava/util/stream/Stream;

    move-result-object v3

    new-instance v5, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer$$ExternalSyntheticLambda3;

    invoke-direct {v5, p2}, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer$$ExternalSyntheticLambda3;-><init>(Ljava/lang/String;)V

    .line 260
    invoke-interface {v3, v5}, Ljava/util/stream/Stream;->filter(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;

    move-result-object v3

    .line 261
    invoke-interface {v3}, Ljava/util/stream/Stream;->findFirst()Ljava/util/Optional;

    move-result-object v3

    .line 262
    invoke-virtual {v3, v4}, Ljava/util/Optional;->orElse(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionModel;

    if-nez v3, :cond_1

    .line 264
    invoke-interface {p3, p2}, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionCallback;->onLanguageNotDownloaded(Ljava/lang/String;)V

    return-void

    .line 269
    :cond_1
    :try_start_0
    iget-object v3, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->loadedModels:Ljava/util/Map;

    invoke-interface {v3, p2}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_2

    .line 270
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    .line 271
    sget-object v2, Lorg/vosk/LogLevel;->INFO:Lorg/vosk/LogLevel;

    invoke-static {v2}, Lorg/vosk/LibVosk;->setLogLevel(Lorg/vosk/LogLevel;)V

    .line 272
    new-instance v2, Lorg/vosk/Model;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v4, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->modelsDir:Ljava/io/File;

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v4, "/"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v2, v3}, Lorg/vosk/Model;-><init>(Ljava/lang/String;)V

    .line 273
    iget-object v3, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->loadedModels:Ljava/util/Map;

    invoke-interface {v3, p2, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 274
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    goto :goto_0

    :catch_0
    move-exception p0

    goto :goto_1

    .line 276
    :cond_2
    :goto_0
    iget-object v1, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->loadedModels:Ljava/util/Map;

    invoke-interface {v1, p2}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lorg/vosk/Model;

    const/4 v1, 0x0

    .line 278
    invoke-static {p1, v1}, Lcom/exteragram/messenger/speech/utils/FormatConverter;->extractAndConvertToPcm(Ljava/lang/String;Z)Ljava/io/InputStream;

    move-result-object v1

    .line 279
    invoke-static {p1}, Lcom/exteragram/messenger/speech/utils/FormatConverter;->getSampleRate(Ljava/lang/String;)I

    move-result v2

    .line 281
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    .line 283
    new-instance p1, Lorg/vosk/Recognizer;

    int-to-float v0, v2

    invoke-direct {p1, p2, v0}, Lorg/vosk/Recognizer;-><init>(Lorg/vosk/Model;F)V

    .line 285
    new-instance p2, Lorg/vosk/android/SpeechStreamService;

    invoke-direct {p2, p1, v1, v0}, Lorg/vosk/android/SpeechStreamService;-><init>(Lorg/vosk/Recognizer;Ljava/io/InputStream;F)V

    .line 286
    new-instance v0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer$2;

    invoke-direct {v0, p0, p3, p1}, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer$2;-><init>(Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionCallback;Lorg/vosk/Recognizer;)V

    invoke-virtual {p2, v0}, Lorg/vosk/android/SpeechStreamService;->start(Lorg/vosk/android/RecognitionListener;)Z
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    .line 329
    :goto_1
    const-string p1, "Failed to recognize"

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 330
    invoke-interface {p3, p0}, Lcom/exteragram/messenger/speech/VoiceRecognitionController$RecognitionCallback;->onError(Ljava/lang/Exception;)V

    return-void
.end method

.method public unloadModels()V
    .locals 2

    .line 336
    iget-object v0, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->loadedModels:Ljava/util/Map;

    invoke-interface {v0}, Ljava/util/Map;->values()Ljava/util/Collection;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer$$ExternalSyntheticLambda1;

    invoke-direct {v1}, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer$$ExternalSyntheticLambda1;-><init>()V

    invoke-interface {v0, v1}, Ljava/lang/Iterable;->forEach(Ljava/util/function/Consumer;)V

    .line 337
    iget-object p0, p0, Lcom/exteragram/messenger/speech/recognizers/VoskRecognizer;->loadedModels:Ljava/util/Map;

    invoke-interface {p0}, Ljava/util/Map;->clear()V

    return-void
.end method
