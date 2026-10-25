.class public final Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/utils/chats/FolderCounters;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Companion"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0008\u0086\u0003\u0018\u00002\u00020\u0001B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0007H\u0007J\u0008\u0010\u0010\u001a\u00020\u0011H\u0007J\u0008\u0010\u0012\u001a\u00020\u0013H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u0018\u0010\u0008\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000bR\u0016\u0010\u000c\u001a\u0008\u0012\u0004\u0012\u00020\u00010\tX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\r\u00a8\u0006\u0014"
    }
    d2 = {
        "Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;",
        "",
        "<init>",
        "()V",
        "RECOUNT_DELAY_MS",
        "",
        "FORUM_TYPES",
        "",
        "instances",
        "",
        "Lcom/exteragram/messenger/utils/chats/FolderCounters;",
        "[Lcom/exteragram/messenger/utils/chats/FolderCounters;",
        "lockObjects",
        "[Ljava/lang/Object;",
        "getInstance",
        "num",
        "recountAll",
        "",
        "isUnmutedOnly",
        "",
        "TMessagesProj"
    }
    k = 0x1
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation


# direct methods
.method public static $r8$lambda$mmuf5hkB-A5cLvdcQiUTHwQwckM(Lorg/telegram/messenger/MessagesStorage;)V
    .locals 1

    const/4 v0, 0x0

    .line 179
    invoke-virtual {p0, v0}, Lorg/telegram/messenger/MessagesStorage;->resetAllUnreadCounters(Z)V

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 151
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public synthetic constructor <init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;-><init>()V

    return-void
.end method

.method public static final synthetic access$isUnmutedOnly(Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;)Z
    .locals 0

    .line 151
    invoke-direct {p0}, Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;->isUnmutedOnly()Z

    move-result p0

    return p0
.end method

.method private final isUnmutedOnly()Z
    .locals 1

    .line 183
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getTabCounterMode()Lcom/exteragram/messenger/TabCounterMode;

    move-result-object p0

    sget-object v0, Lcom/exteragram/messenger/TabCounterMode;->UNMUTED:Lcom/exteragram/messenger/TabCounterMode;

    if-ne p0, v0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method


# virtual methods
.method public final getInstance(I)Lcom/exteragram/messenger/utils/chats/FolderCounters;
    .locals 2
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 160
    invoke-static {}, Lcom/exteragram/messenger/utils/chats/FolderCounters;->access$getInstances$cp()[Lcom/exteragram/messenger/utils/chats/FolderCounters;

    move-result-object p0

    aget-object p0, p0, p1

    if-nez p0, :cond_1

    .line 162
    invoke-static {}, Lcom/exteragram/messenger/utils/chats/FolderCounters;->access$getLockObjects$cp()[Ljava/lang/Object;

    move-result-object p0

    aget-object p0, p0, p1

    monitor-enter p0

    .line 163
    :try_start_0
    invoke-static {}, Lcom/exteragram/messenger/utils/chats/FolderCounters;->access$getInstances$cp()[Lcom/exteragram/messenger/utils/chats/FolderCounters;

    move-result-object v0

    aget-object v0, v0, p1

    if-nez v0, :cond_0

    .line 165
    new-instance v0, Lcom/exteragram/messenger/utils/chats/FolderCounters;

    const/4 v1, 0x0

    invoke-direct {v0, p1, v1}, Lcom/exteragram/messenger/utils/chats/FolderCounters;-><init>(ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 166
    invoke-static {}, Lcom/exteragram/messenger/utils/chats/FolderCounters;->access$getInstances$cp()[Lcom/exteragram/messenger/utils/chats/FolderCounters;

    move-result-object v1

    aput-object v0, v1, p1

    goto :goto_0

    :catchall_0
    move-exception p1

    goto :goto_1

    .line 168
    :cond_0
    :goto_0
    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 162
    monitor-exit p0

    return-object v0

    :goto_1
    monitor-exit p0

    throw p1

    :cond_1
    return-object p0
.end method

.method public final recountAll()V
    .locals 3
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 175
    invoke-direct {p0}, Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;->isUnmutedOnly()Z

    move-result p0

    if-nez p0, :cond_0

    goto :goto_1

    :cond_0
    const/4 p0, 0x0

    :goto_0
    const/16 v0, 0x10

    if-ge p0, v0, :cond_2

    .line 177
    invoke-static {p0}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/messenger/UserConfig;->isClientActivated()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 178
    invoke-static {p0}, Lorg/telegram/messenger/MessagesStorage;->getInstance(I)Lorg/telegram/messenger/MessagesStorage;

    move-result-object v0

    .line 179
    invoke-virtual {v0}, Lorg/telegram/messenger/MessagesStorage;->getStorageQueue()Lorg/telegram/messenger/DispatchQueue;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion$$ExternalSyntheticLambda0;

    invoke-direct {v2, v0}, Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/messenger/MessagesStorage;)V

    invoke-virtual {v1, v2}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    :cond_1
    add-int/lit8 p0, p0, 0x1

    goto :goto_0

    :cond_2
    :goto_1
    return-void
.end method
