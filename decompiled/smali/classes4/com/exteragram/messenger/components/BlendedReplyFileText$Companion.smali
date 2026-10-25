.class public final Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/components/BlendedReplyFileText;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Companion"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\u0008\u0086\u0003\u0018\u00002\u00020\u0001B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\u0008\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007\u00a8\u0006\u0008\u00b2\u0006\u000c\u0010\t\u001a\u0004\u0018\u00010\nX\u008a\u0084\u0002"
    }
    d2 = {
        "Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;",
        "",
        "<init>",
        "()V",
        "getType",
        "Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;",
        "reply",
        "Lorg/telegram/messenger/MessageObject;",
        "TMessagesProj",
        "file",
        "Ljava/io/File;"
    }
    k = 0x1
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation

.annotation build Lkotlin/jvm/internal/SourceDebugExtension;
    value = {
        "SMAP\nBlendedReplySpans.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BlendedReplySpans.kt\ncom/exteragram/messenger/components/BlendedReplyFileText$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,172:1\n1#2:173\n*E\n"
    }
.end annotation


# direct methods
.method public static $r8$lambda$q50XyqiFF0WlGV82xdVe1wcVvn4(Lorg/telegram/messenger/MessageObject;)Ljava/io/File;
    .locals 3

    .line 107
    iget v0, p0, Lorg/telegram/messenger/MessageObject;->type:I

    const/16 v1, 0x9

    const/4 v2, 0x0

    if-eq v0, v1, :cond_0

    goto :goto_1

    .line 108
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/utils/chats/ChatUtils;->getInstance()Lcom/exteragram/messenger/utils/chats/ChatUtils;

    move-result-object v0

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/utils/chats/ChatUtils;->getPathToMessage(Lorg/telegram/messenger/MessageObject;)Ljava/lang/String;

    move-result-object p0

    if-eqz p0, :cond_2

    .line 109
    invoke-interface {p0}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-lez v0, :cond_1

    goto :goto_0

    :cond_1
    move-object p0, v2

    :goto_0
    if-eqz p0, :cond_2

    .line 110
    new-instance v0, Ljava/io/File;

    invoke-direct {v0, p0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    return-object v0

    :cond_2
    :goto_1
    return-object v2
.end method

.method private constructor <init>()V
    .locals 0

    .line 99
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public synthetic constructor <init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;-><init>()V

    return-void
.end method

.method private static final getType$isBackup(Lkotlin/Lazy;)Z
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/Lazy<",
            "+",
            "Ljava/io/File;",
            ">;)Z"
        }
    .end annotation

    .line 122
    invoke-static {p0}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;->getType$lambda$2(Lkotlin/Lazy;)Ljava/io/File;

    move-result-object p0

    const/4 v0, 0x0

    if-eqz p0, :cond_0

    invoke-static {}, Lcom/exteragram/messenger/backup/PreferencesUtils;->getInstance()Lcom/exteragram/messenger/backup/PreferencesUtils;

    move-result-object v1

    invoke-virtual {v1, p0}, Lcom/exteragram/messenger/backup/PreferencesUtils;->isBackup(Ljava/io/File;)Z

    move-result p0

    const/4 v1, 0x1

    if-ne p0, v1, :cond_0

    return v1

    :cond_0
    return v0
.end method

.method private static final getType$isFile(Lorg/telegram/messenger/MessageObject;)Z
    .locals 1

    .line 113
    iget p0, p0, Lorg/telegram/messenger/MessageObject;->type:I

    const/16 v0, 0x9

    if-ne p0, v0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private static final getType$isGif(Lorg/telegram/messenger/MessageObject;Ljava/lang/String;)Z
    .locals 1

    .line 120
    invoke-virtual {p0}, Lorg/telegram/messenger/MessageObject;->getDocument()Lorg/telegram/tgnet/TLRPC$Document;

    move-result-object p0

    if-eqz p0, :cond_0

    iget-object p0, p0, Lorg/telegram/tgnet/TLRPC$Document;->mime_type:Ljava/lang/String;

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    const-string v0, "image/gif"

    invoke-static {p0, v0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_2

    invoke-static {p1}, Landroidx/media3/common/MimeTypes;->isVideo(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_1

    goto :goto_1

    :cond_1
    const/4 p0, 0x0

    return p0

    :cond_2
    :goto_1
    const/4 p0, 0x1

    return p0
.end method

.method private static final getType$isGift(Lorg/telegram/messenger/MessageObject;)Z
    .locals 0

    .line 115
    invoke-virtual {p0}, Lorg/telegram/messenger/MessageObject;->isAnyGift()Z

    move-result p0

    return p0
.end method

.method private static final getType$isIconPack(Lkotlin/Lazy;Lorg/telegram/messenger/MessageObject;)Z
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/Lazy<",
            "+",
            "Ljava/io/File;",
            ">;",
            "Lorg/telegram/messenger/MessageObject;",
            ")Z"
        }
    .end annotation

    .line 123
    invoke-static {p0}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;->getType$lambda$2(Lkotlin/Lazy;)Ljava/io/File;

    move-result-object p0

    const/4 v0, 0x0

    if-eqz p0, :cond_0

    sget-object v1, Lcom/exteragram/messenger/icons/IconManager;->INSTANCE:Lcom/exteragram/messenger/icons/IconManager;

    invoke-virtual {p0}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v1, p0, p1}, Lcom/exteragram/messenger/icons/IconManager;->isIconPack(Ljava/lang/String;Lorg/telegram/messenger/MessageObject;)Z

    move-result p0

    const/4 p1, 0x1

    if-ne p0, p1, :cond_0

    return p1

    :cond_0
    return v0
.end method

.method private static final getType$isMusic(Lorg/telegram/messenger/MessageObject;)Z
    .locals 1

    .line 114
    iget p0, p0, Lorg/telegram/messenger/MessageObject;->type:I

    const/16 v0, 0xe

    if-ne p0, v0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private static final getType$isPhoto(Lorg/telegram/messenger/MessageObject;Ljava/lang/String;)Z
    .locals 2

    .line 118
    iget v0, p0, Lorg/telegram/messenger/MessageObject;->type:I

    const/4 v1, 0x1

    if-eq v0, v1, :cond_1

    invoke-static {p0}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;->getType$isFile(Lorg/telegram/messenger/MessageObject;)Z

    move-result p0

    if-eqz p0, :cond_0

    invoke-static {p1}, Landroidx/media3/common/MimeTypes;->isImage(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    return v1
.end method

.method private static final getType$isPlugin(Lkotlin/Lazy;Lorg/telegram/messenger/MessageObject;)Z
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/Lazy<",
            "+",
            "Ljava/io/File;",
            ">;",
            "Lorg/telegram/messenger/MessageObject;",
            ")Z"
        }
    .end annotation

    .line 121
    invoke-static {p0}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;->getType$lambda$2(Lkotlin/Lazy;)Ljava/io/File;

    move-result-object p0

    const/4 v0, 0x0

    if-eqz p0, :cond_0

    sget-object v1, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v1, p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->isPlugin(Ljava/io/File;Lorg/telegram/messenger/MessageObject;)Z

    move-result p0

    const/4 p1, 0x1

    if-ne p0, p1, :cond_0

    return p1

    :cond_0
    return v0
.end method

.method private static final getType$isRound(Lorg/telegram/messenger/MessageObject;)Z
    .locals 1

    .line 117
    iget p0, p0, Lorg/telegram/messenger/MessageObject;->type:I

    const/4 v0, 0x5

    if-ne p0, v0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private static final getType$isVideo(Lorg/telegram/messenger/MessageObject;Ljava/lang/String;)Z
    .locals 2

    .line 119
    iget v0, p0, Lorg/telegram/messenger/MessageObject;->type:I

    const/4 v1, 0x3

    if-eq v0, v1, :cond_1

    invoke-static {p0}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;->getType$isFile(Lorg/telegram/messenger/MessageObject;)Z

    move-result p0

    if-eqz p0, :cond_0

    invoke-static {p1}, Landroidx/media3/common/MimeTypes;->isVideo(Ljava/lang/String;)Z

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

.method private static final getType$isVoice(Lorg/telegram/messenger/MessageObject;)Z
    .locals 1

    .line 116
    iget p0, p0, Lorg/telegram/messenger/MessageObject;->type:I

    const/4 v0, 0x2

    if-ne p0, v0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private static final getType$lambda$2(Lkotlin/Lazy;)Ljava/io/File;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/Lazy<",
            "+",
            "Ljava/io/File;",
            ">;)",
            "Ljava/io/File;"
        }
    .end annotation

    .line 106
    invoke-interface {p0}, Lkotlin/Lazy;->getValue()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/io/File;

    return-object p0
.end method


# virtual methods
.method public final getType(Lorg/telegram/messenger/MessageObject;)Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;
    .locals 2
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    if-nez p1, :cond_0

    .line 103
    sget-object p0, Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;->UNKNOWN:Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;

    return-object p0

    .line 105
    :cond_0
    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getDocument()Lorg/telegram/tgnet/TLRPC$Document;

    move-result-object p0

    if-eqz p0, :cond_1

    iget-object p0, p0, Lorg/telegram/tgnet/TLRPC$Document;->mime_type:Ljava/lang/String;

    goto :goto_0

    :cond_1
    const/4 p0, 0x0

    .line 106
    :goto_0
    sget-object v0, Lkotlin/LazyThreadSafetyMode;->NONE:Lkotlin/LazyThreadSafetyMode;

    new-instance v1, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion$$ExternalSyntheticLambda0;

    invoke-direct {v1, p1}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/messenger/MessageObject;)V

    invoke-static {v0, v1}, Lkotlin/LazyKt;->lazy(Lkotlin/LazyThreadSafetyMode;Lkotlin/jvm/functions/Function0;)Lkotlin/Lazy;

    move-result-object v0

    .line 126
    invoke-static {p1}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;->getType$isMusic(Lorg/telegram/messenger/MessageObject;)Z

    move-result v1

    if-eqz v1, :cond_2

    sget-object p0, Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;->MUSIC:Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;

    return-object p0

    .line 127
    :cond_2
    invoke-static {p1}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;->getType$isGift(Lorg/telegram/messenger/MessageObject;)Z

    move-result v1

    if-eqz v1, :cond_3

    sget-object p0, Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;->GIFT:Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;

    return-object p0

    .line 128
    :cond_3
    invoke-static {p1}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;->getType$isVoice(Lorg/telegram/messenger/MessageObject;)Z

    move-result v1

    if-eqz v1, :cond_4

    sget-object p0, Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;->VOICE:Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;

    return-object p0

    .line 129
    :cond_4
    invoke-static {p1}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;->getType$isRound(Lorg/telegram/messenger/MessageObject;)Z

    move-result v1

    if-eqz v1, :cond_5

    sget-object p0, Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;->ROUND_VIDEO:Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;

    return-object p0

    .line 130
    :cond_5
    invoke-static {p1, p0}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;->getType$isPhoto(Lorg/telegram/messenger/MessageObject;Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_6

    sget-object p0, Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;->PHOTO:Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;

    return-object p0

    .line 131
    :cond_6
    invoke-static {p1, p0}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;->getType$isVideo(Lorg/telegram/messenger/MessageObject;Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_7

    sget-object p0, Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;->VIDEO:Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;

    return-object p0

    .line 132
    :cond_7
    invoke-static {p1, p0}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;->getType$isGif(Lorg/telegram/messenger/MessageObject;Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_8

    sget-object p0, Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;->UNKNOWN:Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;

    return-object p0

    .line 133
    :cond_8
    invoke-static {v0, p1}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;->getType$isPlugin(Lkotlin/Lazy;Lorg/telegram/messenger/MessageObject;)Z

    move-result p0

    if-eqz p0, :cond_9

    sget-object p0, Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;->PLUGIN:Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;

    return-object p0

    .line 134
    :cond_9
    invoke-static {v0}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;->getType$isBackup(Lkotlin/Lazy;)Z

    move-result p0

    if-eqz p0, :cond_a

    sget-object p0, Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;->BACKUP:Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;

    return-object p0

    .line 135
    :cond_a
    invoke-static {v0, p1}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;->getType$isIconPack(Lkotlin/Lazy;Lorg/telegram/messenger/MessageObject;)Z

    move-result p0

    if-eqz p0, :cond_b

    sget-object p0, Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;->ICON_PACK:Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;

    return-object p0

    .line 136
    :cond_b
    invoke-static {p1}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;->getType$isFile(Lorg/telegram/messenger/MessageObject;)Z

    move-result p0

    if-eqz p0, :cond_c

    sget-object p0, Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;->FILE:Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;

    return-object p0

    .line 137
    :cond_c
    sget-object p0, Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;->UNKNOWN:Lcom/exteragram/messenger/components/BlendedReplyFileText$AttachType;

    return-object p0
.end method
