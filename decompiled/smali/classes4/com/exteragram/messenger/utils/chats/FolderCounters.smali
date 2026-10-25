.class public final Lcom/exteragram/messenger/utils/chats/FolderCounters;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;,
        Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0010\t\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0004\u0018\u0000 \'2\u00020\u0001:\u0002&\'B\u0011\u0008\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\u0008\u0004\u0010\u0005J\u0006\u0010\u000c\u001a\u00020\u0003J\u000e\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fJp\u0010\u0010\u001a\u00020\u00112\u000c\u0010\u0012\u001a\u0008\u0012\u0004\u0012\u00020\u000f0\u00132\u000c\u0010\u0014\u001a\u0008\u0012\u0004\u0012\u00020\u00160\u00152\u000c\u0010\u0017\u001a\u0008\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0018\u001a\u00020\u00192\u000c\u0010\u001a\u001a\u0008\u0012\u0004\u0012\u00020\u001b0\u00152\u000c\u0010\u001c\u001a\u0008\u0012\u0004\u0012\u00020\u000b0\u00152\u000c\u0010\u001d\u001a\u0008\u0012\u0004\u0012\u00020\u000b0\u00152\u000c\u0010\u001e\u001a\u0008\u0012\u0004\u0012\u00020\u00030\u0015J\u0006\u0010\u001f\u001a\u00020\u0011J$\u0010 \u001a\u001e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u000b0\u0008j\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u000b`\tH\u0002J(\u0010\"\u001a\u00020\u00032\u000c\u0010#\u001a\u0008\u0012\u0004\u0012\u00020$0\u00132\u0006\u0010%\u001a\u00020\u00032\u0008\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R*\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0008j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006("
    }
    d2 = {
        "Lcom/exteragram/messenger/utils/chats/FolderCounters;",
        "",
        "account",
        "",
        "<init>",
        "(I)V",
        "mainCount",
        "filterCounts",
        "Ljava/util/HashMap;",
        "Lkotlin/collections/HashMap;",
        "recountScheduled",
        "",
        "getMainUnreadCount",
        "getUnreadCount",
        "filter",
        "Lorg/telegram/messenger/MessagesController$DialogFilter;",
        "update",
        "",
        "filters",
        "",
        "usersDict",
        "Landroidx/collection/LongSparseArray;",
        "Lorg/telegram/tgnet/TLRPC$User;",
        "encUsersDict",
        "encryptedChatsByUsersCount",
        "Lorg/telegram/messenger/support/LongSparseIntArray;",
        "chatsDict",
        "Lorg/telegram/tgnet/TLRPC$Chat;",
        "mutedDialogs",
        "archivedDialogs",
        "dialogsWithMentions",
        "scheduleRecount",
        "loadForumMutes",
        "",
        "count",
        "dialogs",
        "Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;",
        "flags",
        "UnreadDialog",
        "Companion",
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


# static fields
.field public static final Companion:Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;

.field private static final instances:[Lcom/exteragram/messenger/utils/chats/FolderCounters;

.field private static final lockObjects:[Ljava/lang/Object;


# instance fields
.field private final account:I

.field private filterCounts:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/Integer;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private mainCount:I

.field private volatile recountScheduled:Z


# direct methods
.method public static $r8$lambda$70DlWAvopMmbZjuP5Rkp7m_mrV0(Lcom/exteragram/messenger/utils/chats/FolderCounters;ILjava/util/HashMap;)V
    .locals 0

    .line 98
    iput p1, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->mainCount:I

    .line 99
    iput-object p2, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->filterCounts:Ljava/util/HashMap;

    return-void
.end method

.method public static $r8$lambda$a7yR4wl_zPymeOD2mbHLE9R84bk(Lcom/exteragram/messenger/utils/chats/FolderCounters;Lorg/telegram/messenger/MessagesStorage;)V
    .locals 1

    const/4 v0, 0x0

    .line 108
    iput-boolean v0, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->recountScheduled:Z

    .line 109
    invoke-virtual {p1, v0}, Lorg/telegram/messenger/MessagesStorage;->resetAllUnreadCounters(Z)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 4

    new-instance v0, Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->Companion:Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;

    const/16 v0, 0x10

    .line 155
    new-array v1, v0, [Lcom/exteragram/messenger/utils/chats/FolderCounters;

    sput-object v1, Lcom/exteragram/messenger/utils/chats/FolderCounters;->instances:[Lcom/exteragram/messenger/utils/chats/FolderCounters;

    .line 156
    new-array v1, v0, [Ljava/lang/Object;

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v0, :cond_0

    new-instance v3, Ljava/lang/Object;

    invoke-direct {v3}, Ljava/lang/Object;-><init>()V

    aput-object v3, v1, v2

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_0
    sput-object v1, Lcom/exteragram/messenger/utils/chats/FolderCounters;->lockObjects:[Ljava/lang/Object;

    return-void
.end method

.method private constructor <init>(I)V
    .locals 0

    .line 28
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput p1, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->account:I

    .line 38
    new-instance p1, Ljava/util/HashMap;

    invoke-direct {p1}, Ljava/util/HashMap;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->filterCounts:Ljava/util/HashMap;

    return-void
.end method

.method public synthetic constructor <init>(ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/utils/chats/FolderCounters;-><init>(I)V

    return-void
.end method

.method public static final synthetic access$getInstances$cp()[Lcom/exteragram/messenger/utils/chats/FolderCounters;
    .locals 1

    .line 28
    sget-object v0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->instances:[Lcom/exteragram/messenger/utils/chats/FolderCounters;

    return-object v0
.end method

.method public static final synthetic access$getLockObjects$cp()[Ljava/lang/Object;
    .locals 1

    .line 28
    sget-object v0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->lockObjects:[Ljava/lang/Object;

    return-object v0
.end method

.method private final count(Ljava/util/List;ILorg/telegram/messenger/MessagesController$DialogFilter;)I
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;",
            ">;I",
            "Lorg/telegram/messenger/MessagesController$DialogFilter;",
            ")I"
        }
    .end annotation

    const/4 p0, 0x0

    if-eqz p3, :cond_0

    .line 134
    iget-object v0, p3, Lorg/telegram/messenger/MessagesController$DialogFilter;->alwaysShow:Ljava/util/ArrayList;

    if-eqz v0, :cond_0

    invoke-static {v0}, Lkotlin/collections/CollectionsKt;->toHashSet(Ljava/lang/Iterable;)Ljava/util/HashSet;

    move-result-object v0

    goto :goto_0

    :cond_0
    move-object v0, p0

    :goto_0
    if-eqz p3, :cond_1

    .line 135
    iget-object p3, p3, Lorg/telegram/messenger/MessagesController$DialogFilter;->neverShow:Ljava/util/ArrayList;

    if-eqz p3, :cond_1

    invoke-static {p3}, Lkotlin/collections/CollectionsKt;->toHashSet(Ljava/lang/Iterable;)Ljava/util/HashSet;

    move-result-object p0

    .line 137
    :cond_1
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    const/4 p3, 0x0

    :cond_2
    :goto_1
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_6

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;

    const/4 v2, 0x1

    if-eqz p0, :cond_3

    .line 139
    invoke-virtual {v1}, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;->getId()J

    move-result-wide v3

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    invoke-virtual {p0, v3}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result v3

    if-ne v3, v2, :cond_3

    goto :goto_1

    :cond_3
    if-eqz v0, :cond_4

    .line 140
    invoke-virtual {v1}, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;->getId()J

    move-result-wide v3

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    invoke-virtual {v0, v3}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result v3

    if-ne v3, v2, :cond_4

    goto :goto_2

    .line 141
    :cond_4
    invoke-virtual {v1}, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;->getArchived()Z

    move-result v2

    if-eqz v2, :cond_5

    sget v2, Lorg/telegram/messenger/MessagesController;->DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED:I

    and-int/2addr v2, p2

    if-eqz v2, :cond_5

    goto :goto_1

    .line 142
    :cond_5
    invoke-virtual {v1}, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;->getTypeFlags()I

    move-result v2

    and-int/2addr v2, p2

    invoke-virtual {v1}, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;->getTypeFlags()I

    move-result v3

    if-ne v2, v3, :cond_2

    .line 145
    :goto_2
    invoke-virtual {v1}, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;->getWeight()I

    move-result v1

    add-int/2addr p3, v1

    goto :goto_1

    :cond_6
    return p3
.end method

.method public static final getInstance(I)Lcom/exteragram/messenger/utils/chats/FolderCounters;
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->Companion:Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;->getInstance(I)Lcom/exteragram/messenger/utils/chats/FolderCounters;

    move-result-object p0

    return-object p0
.end method

.method private final loadForumMutes()Ljava/util/HashMap;
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/HashMap<",
            "Ljava/lang/Long;",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation

    .line 114
    iget v0, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->account:I

    invoke-static {v0}, Lorg/telegram/messenger/MessagesStorage;->getInstance(I)Lorg/telegram/messenger/MessagesStorage;

    move-result-object v0

    .line 115
    iget p0, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->account:I

    invoke-static {p0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p0

    .line 116
    new-instance v1, Ljava/util/HashMap;

    invoke-direct {v1}, Ljava/util/HashMap;-><init>()V

    const/4 v2, 0x0

    .line 119
    :try_start_0
    invoke-virtual {v0}, Lorg/telegram/messenger/MessagesStorage;->getDatabase()Lorg/telegram/SQLite/SQLiteDatabase;

    move-result-object v3

    const-string v4, "SELECT did, topic_id, unread_mentions FROM topics WHERE unread_count > 0 OR unread_mentions > 0"

    const/4 v5, 0x0

    new-array v6, v5, [Ljava/lang/Object;

    invoke-virtual {v3, v4, v6}, Lorg/telegram/SQLite/SQLiteDatabase;->queryFinalized(Ljava/lang/String;[Ljava/lang/Object;)Lorg/telegram/SQLite/SQLiteCursor;

    move-result-object v2

    .line 120
    :cond_0
    :goto_0
    invoke-virtual {v2}, Lorg/telegram/SQLite/SQLiteCursor;->next()Z

    move-result v3

    if-eqz v3, :cond_3

    .line 121
    invoke-virtual {v2, v5}, Lorg/telegram/SQLite/SQLiteCursor;->longValue(I)J

    move-result-wide v3

    .line 122
    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v6

    invoke-virtual {v1, v6}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    sget-object v7, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    invoke-static {v6, v7}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v6

    if-nez v6, :cond_0

    const/16 v6, 0xd

    invoke-virtual {v0, v3, v4, v6}, Lorg/telegram/messenger/MessagesStorage;->isForum(JI)Z

    move-result v6

    if-nez v6, :cond_1

    goto :goto_0

    .line 123
    :cond_1
    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v6

    const/4 v7, 0x2

    invoke-virtual {v2, v7}, Lorg/telegram/SQLite/SQLiteCursor;->intValue(I)I

    move-result v7

    if-nez v7, :cond_2

    const/4 v7, 0x1

    invoke-virtual {v2, v7}, Lorg/telegram/SQLite/SQLiteCursor;->longValue(I)J

    move-result-wide v8

    invoke-virtual {p0, v3, v4, v8, v9}, Lorg/telegram/messenger/MessagesController;->isDialogMuted(JJ)Z

    move-result v3

    if-eqz v3, :cond_2

    goto :goto_1

    :catchall_0
    move-exception p0

    goto :goto_3

    :catch_0
    move-exception p0

    goto :goto_2

    :cond_2
    move v7, v5

    :goto_1
    invoke-static {v7}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v3

    invoke-interface {v1, v6, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    .line 128
    :cond_3
    invoke-virtual {v2}, Lorg/telegram/SQLite/SQLiteCursor;->dispose()V

    return-object v1

    .line 126
    :goto_2
    :try_start_1
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-eqz v2, :cond_4

    .line 128
    invoke-virtual {v2}, Lorg/telegram/SQLite/SQLiteCursor;->dispose()V

    :cond_4
    return-object v1

    :goto_3
    if-eqz v2, :cond_5

    invoke-virtual {v2}, Lorg/telegram/SQLite/SQLiteCursor;->dispose()V

    :cond_5
    throw p0
.end method


# virtual methods
.method public final getMainUnreadCount()I
    .locals 1

    .line 43
    sget-object v0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->Companion:Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;

    invoke-static {v0}, Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;->access$isUnmutedOnly(Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget p0, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->mainCount:I

    return p0

    :cond_0
    iget p0, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->account:I

    invoke-static {p0}, Lorg/telegram/messenger/MessagesStorage;->getInstance(I)Lorg/telegram/messenger/MessagesStorage;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/messenger/MessagesStorage;->getMainUnreadCount()I

    move-result p0

    return p0
.end method

.method public final getUnreadCount(Lorg/telegram/messenger/MessagesController$DialogFilter;)I
    .locals 1

    .line 46
    sget-object v0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->Companion:Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;

    invoke-static {v0}, Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;->access$isUnmutedOnly(Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;)Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object p0, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->filterCounts:Ljava/util/HashMap;

    iget p1, p1, Lorg/telegram/messenger/MessagesController$DialogFilter;->id:I

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {p0, p1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Integer;

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    iget p0, p1, Lorg/telegram/messenger/MessagesController$DialogFilter;->unreadCount:I

    return p0
.end method

.method public final scheduleRecount()V
    .locals 5

    .line 104
    sget-object v0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->Companion:Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;

    invoke-static {v0}, Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;->access$isUnmutedOnly(Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;)Z

    move-result v0

    if-eqz v0, :cond_1

    iget-boolean v0, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->recountScheduled:Z

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x1

    .line 105
    iput-boolean v0, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->recountScheduled:Z

    .line 106
    iget v0, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->account:I

    invoke-static {v0}, Lorg/telegram/messenger/MessagesStorage;->getInstance(I)Lorg/telegram/messenger/MessagesStorage;

    move-result-object v0

    .line 107
    invoke-virtual {v0}, Lorg/telegram/messenger/MessagesStorage;->getStorageQueue()Lorg/telegram/messenger/DispatchQueue;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/utils/chats/FolderCounters$$ExternalSyntheticLambda1;

    invoke-direct {v2, p0, v0}, Lcom/exteragram/messenger/utils/chats/FolderCounters$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/utils/chats/FolderCounters;Lorg/telegram/messenger/MessagesStorage;)V

    const-wide/16 v3, 0x1f4

    invoke-virtual {v1, v2, v3, v4}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;J)Z

    :cond_1
    :goto_0
    return-void
.end method

.method public final update(Ljava/util/List;Landroidx/collection/LongSparseArray;Landroidx/collection/LongSparseArray;Lorg/telegram/messenger/support/LongSparseIntArray;Landroidx/collection/LongSparseArray;Landroidx/collection/LongSparseArray;Landroidx/collection/LongSparseArray;Landroidx/collection/LongSparseArray;)V
    .locals 20
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "+",
            "Lorg/telegram/messenger/MessagesController$DialogFilter;",
            ">;",
            "Landroidx/collection/LongSparseArray<",
            "Lorg/telegram/tgnet/TLRPC$User;",
            ">;",
            "Landroidx/collection/LongSparseArray<",
            "Lorg/telegram/tgnet/TLRPC$User;",
            ">;",
            "Lorg/telegram/messenger/support/LongSparseIntArray;",
            "Landroidx/collection/LongSparseArray<",
            "Lorg/telegram/tgnet/TLRPC$Chat;",
            ">;",
            "Landroidx/collection/LongSparseArray<",
            "Ljava/lang/Boolean;",
            ">;",
            "Landroidx/collection/LongSparseArray<",
            "Ljava/lang/Boolean;",
            ">;",
            "Landroidx/collection/LongSparseArray<",
            "Ljava/lang/Integer;",
            ">;)V"
        }
    .end annotation

    move-object/from16 v0, p0

    move-object/from16 v1, p5

    move-object/from16 v2, p6

    move-object/from16 v3, p7

    .line 58
    sget-object v4, Lcom/exteragram/messenger/utils/chats/FolderCounters;->Companion:Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;

    invoke-static {v4}, Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;->access$isUnmutedOnly(Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;)Z

    move-result v4

    if-nez v4, :cond_0

    return-void

    .line 59
    :cond_0
    invoke-direct {v0}, Lcom/exteragram/messenger/utils/chats/FolderCounters;->loadForumMutes()Ljava/util/HashMap;

    move-result-object v4

    .line 60
    new-instance v5, Ljava/util/ArrayList;

    invoke-direct {v5}, Ljava/util/ArrayList;-><init>()V

    .line 61
    invoke-virtual/range {p2 .. p2}, Landroidx/collection/LongSparseArray;->size()I

    move-result v6

    const/4 v7, 0x0

    move v8, v7

    :goto_0
    if-ge v8, v6, :cond_7

    move-object/from16 v9, p2

    .line 62
    invoke-virtual {v9, v8}, Landroidx/collection/LongSparseArray;->valueAt(I)Ljava/lang/Object;

    move-result-object v10

    check-cast v10, Lorg/telegram/tgnet/TLRPC$User;

    .line 63
    iget-wide v11, v10, Lorg/telegram/tgnet/TLRPC$User;->id:J

    invoke-static {v11, v12}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v11

    invoke-virtual {v4, v11}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v11

    check-cast v11, Ljava/lang/Boolean;

    if-eqz v11, :cond_1

    invoke-virtual {v11}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v11

    goto :goto_1

    :cond_1
    iget-wide v11, v10, Lorg/telegram/tgnet/TLRPC$User;->id:J

    invoke-virtual {v2, v11, v12}, Landroidx/collection/LongSparseArray;->containsKey(J)Z

    move-result v11

    :goto_1
    if-nez v11, :cond_6

    .line 65
    invoke-static {v1, v10}, Lorg/telegram/messenger/ChatObject;->isUserCollapsedInCommunity(Landroidx/collection/LongSparseArray;Lorg/telegram/tgnet/TLRPC$User;)Z

    move-result v11

    if-eqz v11, :cond_2

    sget v11, Lorg/telegram/messenger/MessagesController;->DIALOG_FILTER_FLAG_ALL_CHATS:I

    :goto_2
    move v15, v11

    goto :goto_4

    .line 66
    :cond_2
    iget-boolean v11, v10, Lorg/telegram/tgnet/TLRPC$User;->bot:Z

    if-eqz v11, :cond_3

    sget v11, Lorg/telegram/messenger/MessagesController;->DIALOG_FILTER_FLAG_BOTS:I

    goto :goto_2

    .line 67
    :cond_3
    iget-boolean v11, v10, Lorg/telegram/tgnet/TLRPC$User;->self:Z

    if-nez v11, :cond_5

    iget-boolean v11, v10, Lorg/telegram/tgnet/TLRPC$User;->contact:Z

    if-eqz v11, :cond_4

    goto :goto_3

    .line 68
    :cond_4
    sget v11, Lorg/telegram/messenger/MessagesController;->DIALOG_FILTER_FLAG_NON_CONTACTS:I

    goto :goto_2

    .line 67
    :cond_5
    :goto_3
    sget v11, Lorg/telegram/messenger/MessagesController;->DIALOG_FILTER_FLAG_CONTACTS:I

    goto :goto_2

    .line 70
    :goto_4
    new-instance v12, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;

    iget-wide v13, v10, Lorg/telegram/tgnet/TLRPC$User;->id:J

    const/16 v16, 0x1

    invoke-virtual {v3, v13, v14}, Landroidx/collection/LongSparseArray;->containsKey(J)Z

    move-result v17

    invoke-direct/range {v12 .. v17}, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;-><init>(JIIZ)V

    invoke-interface {v5, v12}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    :cond_6
    add-int/lit8 v8, v8, 0x1

    goto :goto_0

    .line 72
    :cond_7
    invoke-virtual/range {p3 .. p3}, Landroidx/collection/LongSparseArray;->size()I

    move-result v6

    move v8, v7

    :goto_5
    if-ge v8, v6, :cond_c

    move-object/from16 v9, p3

    .line 73
    invoke-virtual {v9, v8}, Landroidx/collection/LongSparseArray;->valueAt(I)Ljava/lang/Object;

    move-result-object v10

    check-cast v10, Lorg/telegram/tgnet/TLRPC$User;

    .line 74
    iget-wide v11, v10, Lorg/telegram/tgnet/TLRPC$User;->id:J

    move-object/from16 v13, p4

    invoke-virtual {v13, v11, v12, v7}, Lorg/telegram/messenger/support/LongSparseIntArray;->get(JI)I

    move-result v18

    if-eqz v18, :cond_b

    .line 75
    iget-wide v11, v10, Lorg/telegram/tgnet/TLRPC$User;->id:J

    invoke-virtual {v2, v11, v12}, Landroidx/collection/LongSparseArray;->containsKey(J)Z

    move-result v11

    if-eqz v11, :cond_8

    goto :goto_9

    .line 76
    :cond_8
    iget-boolean v11, v10, Lorg/telegram/tgnet/TLRPC$User;->self:Z

    if-nez v11, :cond_a

    iget-boolean v11, v10, Lorg/telegram/tgnet/TLRPC$User;->contact:Z

    if-eqz v11, :cond_9

    goto :goto_7

    :cond_9
    sget v11, Lorg/telegram/messenger/MessagesController;->DIALOG_FILTER_FLAG_NON_CONTACTS:I

    :goto_6
    move/from16 v17, v11

    goto :goto_8

    :cond_a
    :goto_7
    sget v11, Lorg/telegram/messenger/MessagesController;->DIALOG_FILTER_FLAG_CONTACTS:I

    goto :goto_6

    .line 77
    :goto_8
    new-instance v14, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;

    iget-wide v10, v10, Lorg/telegram/tgnet/TLRPC$User;->id:J

    invoke-virtual {v3, v10, v11}, Landroidx/collection/LongSparseArray;->containsKey(J)Z

    move-result v19

    move-wide v15, v10

    invoke-direct/range {v14 .. v19}, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;-><init>(JIIZ)V

    invoke-interface {v5, v14}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    :cond_b
    :goto_9
    add-int/lit8 v8, v8, 0x1

    goto :goto_5

    .line 79
    :cond_c
    invoke-virtual {v1}, Landroidx/collection/LongSparseArray;->size()I

    move-result v6

    move v8, v7

    :goto_a
    if-ge v8, v6, :cond_15

    .line 80
    invoke-virtual {v1, v8}, Landroidx/collection/LongSparseArray;->valueAt(I)Ljava/lang/Object;

    move-result-object v9

    check-cast v9, Lorg/telegram/tgnet/TLRPC$Chat;

    if-nez v9, :cond_e

    :cond_d
    move-object/from16 v10, p8

    goto :goto_e

    .line 81
    :cond_e
    invoke-static {v9}, Lorg/telegram/messenger/ChatObject;->isCommunity(Lorg/telegram/tgnet/TLRPC$Chat;)Z

    move-result v10

    if-nez v10, :cond_d

    .line 82
    iget-wide v10, v9, Lorg/telegram/tgnet/TLRPC$Chat;->id:J

    neg-long v13, v10

    .line 83
    invoke-static {v13, v14}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v10

    invoke-virtual {v4, v10}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v10

    check-cast v10, Ljava/lang/Boolean;

    if-eqz v10, :cond_f

    invoke-virtual {v10}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v10

    move v11, v10

    move-object/from16 v10, p8

    goto :goto_b

    :cond_f
    invoke-virtual {v2, v13, v14}, Landroidx/collection/LongSparseArray;->containsKey(J)Z

    move-result v10

    if-eqz v10, :cond_10

    move-object/from16 v10, p8

    invoke-virtual {v10, v13, v14}, Landroidx/collection/LongSparseArray;->containsKey(J)Z

    move-result v11

    if-nez v11, :cond_11

    const/4 v11, 0x1

    goto :goto_b

    :cond_10
    move-object/from16 v10, p8

    :cond_11
    move v11, v7

    :goto_b
    if-nez v11, :cond_14

    .line 85
    invoke-static {v1, v9}, Lorg/telegram/messenger/ChatObject;->isChatCollapsedInCommunity(Landroidx/collection/LongSparseArray;Lorg/telegram/tgnet/TLRPC$Chat;)Z

    move-result v11

    if-eqz v11, :cond_12

    sget v9, Lorg/telegram/messenger/MessagesController;->DIALOG_FILTER_FLAG_ALL_CHATS:I

    :goto_c
    move v15, v9

    goto :goto_d

    .line 86
    :cond_12
    invoke-static {v9}, Lorg/telegram/messenger/ChatObject;->isChannel(Lorg/telegram/tgnet/TLRPC$Chat;)Z

    move-result v11

    if-eqz v11, :cond_13

    iget-boolean v9, v9, Lorg/telegram/tgnet/TLRPC$Chat;->megagroup:Z

    if-nez v9, :cond_13

    sget v9, Lorg/telegram/messenger/MessagesController;->DIALOG_FILTER_FLAG_CHANNELS:I

    goto :goto_c

    .line 87
    :cond_13
    sget v9, Lorg/telegram/messenger/MessagesController;->DIALOG_FILTER_FLAG_GROUPS:I

    goto :goto_c

    .line 89
    :goto_d
    new-instance v12, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;

    const/16 v16, 0x1

    invoke-virtual {v3, v13, v14}, Landroidx/collection/LongSparseArray;->containsKey(J)Z

    move-result v17

    invoke-direct/range {v12 .. v17}, Lcom/exteragram/messenger/utils/chats/FolderCounters$UnreadDialog;-><init>(JIIZ)V

    invoke-interface {v5, v12}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    :cond_14
    :goto_e
    add-int/lit8 v8, v8, 0x1

    goto :goto_a

    .line 92
    :cond_15
    sget v1, Lorg/telegram/messenger/MessagesController;->DIALOG_FILTER_FLAG_ALL_CHATS:I

    sget v2, Lorg/telegram/messenger/MessagesController;->DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED:I

    or-int/2addr v1, v2

    const/4 v2, 0x0

    invoke-direct {v0, v5, v1, v2}, Lcom/exteragram/messenger/utils/chats/FolderCounters;->count(Ljava/util/List;ILorg/telegram/messenger/MessagesController$DialogFilter;)I

    move-result v1

    .line 93
    new-instance v2, Ljava/util/HashMap;

    invoke-direct {v2}, Ljava/util/HashMap;-><init>()V

    .line 94
    invoke-interface/range {p1 .. p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :goto_f
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_16

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lorg/telegram/messenger/MessagesController$DialogFilter;

    .line 95
    iget v6, v4, Lorg/telegram/messenger/MessagesController$DialogFilter;->id:I

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    iget v7, v4, Lorg/telegram/messenger/MessagesController$DialogFilter;->flags:I

    invoke-direct {v0, v5, v7, v4}, Lcom/exteragram/messenger/utils/chats/FolderCounters;->count(Ljava/util/List;ILorg/telegram/messenger/MessagesController$DialogFilter;)I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-interface {v2, v6, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_f

    .line 97
    :cond_16
    new-instance v3, Lcom/exteragram/messenger/utils/chats/FolderCounters$$ExternalSyntheticLambda0;

    invoke-direct {v3, v0, v1, v2}, Lcom/exteragram/messenger/utils/chats/FolderCounters$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/utils/chats/FolderCounters;ILjava/util/HashMap;)V

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method
