.class final Lcom/exteragram/messenger/feed/FeedTimelineLoader;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;,
        Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelEnumeration;,
        Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;,
        Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;,
        Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;,
        Lcom/exteragram/messenger/feed/FeedTimelineLoader$NewerPage;,
        Lcom/exteragram/messenger/feed/FeedTimelineLoader$WindowPage;
    }
.end annotation


# instance fields
.field private final channelCacheEpoch:Ljava/util/concurrent/atomic/AtomicInteger;

.field private volatile channelSetCache:Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;

.field private final currentAccount:I


# direct methods
.method public static synthetic $r8$lambda$4LSIuD87ztyDn-jvK-p79_BpgdA([J[J)I
    .locals 5

    const/4 v0, 0x2

    .line 281
    aget-wide v1, p1, v0

    aget-wide v3, p0, v0

    invoke-static {v1, v2, v3, v4}, Ljava/lang/Long;->compare(JJ)I

    move-result p0

    return p0
.end method

.method public constructor <init>(I)V
    .locals 1

    .line 33
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 30
    new-instance v0, Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-direct {v0}, Ljava/util/concurrent/atomic/AtomicInteger;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->channelCacheEpoch:Ljava/util/concurrent/atomic/AtomicInteger;

    .line 34
    iput p1, p0, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->currentAccount:I

    return-void
.end method

.method private static appendCursorBound(Ljava/lang/StringBuilder;Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;ZZ)V
    .locals 2

    if-eqz p2, :cond_0

    .line 397
    const-string p2, "<"

    goto :goto_0

    :cond_0
    const-string p2, ">"

    .line 398
    :goto_0
    const-string v0, " AND (date "

    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 v0, 0x20

    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    iget v1, p1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->date:I

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, " OR date = "

    .line 399
    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v1, p1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->date:I

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, " AND (uid "

    .line 400
    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    iget-wide v0, p1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->uid:J

    invoke-virtual {p0, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, " OR uid = "

    .line 401
    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v0, p1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->uid:J

    invoke-virtual {p0, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, " AND mid "

    .line 402
    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    if-eqz p3, :cond_1

    const-string p2, "= "

    goto :goto_1

    :cond_1
    const-string p2, " "

    :goto_1
    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget p1, p1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->mid:I

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p1, "))"

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    return-void
.end method

.method private buildChannelSet(ZLjava/util/HashSet;II)Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;
    .locals 19
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(Z",
            "Ljava/util/HashSet<",
            "Ljava/lang/Long;",
            ">;II)",
            "Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;"
        }
    .end annotation

    move-object/from16 v0, p0

    .line 185
    iget v0, v0, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/MessagesStorage;->getInstance(I)Lorg/telegram/messenger/MessagesStorage;

    move-result-object v0

    .line 186
    new-instance v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;

    move/from16 v2, p3

    move/from16 v3, p4

    invoke-direct {v1, v2, v3}, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;-><init>(II)V

    .line 187
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    .line 188
    new-instance v3, Ljava/util/ArrayList;

    invoke-direct {v3}, Ljava/util/ArrayList;-><init>()V

    const/4 v4, 0x1

    .line 190
    :try_start_0
    invoke-virtual {v0}, Lorg/telegram/messenger/MessagesStorage;->getDatabase()Lorg/telegram/SQLite/SQLiteDatabase;

    move-result-object v5

    const-string v6, "SELECT did, inbox_max, unread_count, last_mid, folder_id FROM dialogs WHERE did < 0"

    const/4 v7, 0x0

    new-array v8, v7, [Ljava/lang/Object;

    invoke-virtual {v5, v6, v8}, Lorg/telegram/SQLite/SQLiteDatabase;->queryFinalized(Ljava/lang/String;[Ljava/lang/Object;)Lorg/telegram/SQLite/SQLiteCursor;

    move-result-object v5

    .line 191
    :goto_0
    invoke-virtual {v5}, Lorg/telegram/SQLite/SQLiteCursor;->next()Z

    move-result v6

    const/4 v8, 0x4

    const/4 v9, 0x3

    const/4 v10, 0x2

    if-eqz v6, :cond_1

    .line 192
    invoke-virtual {v5, v7}, Lorg/telegram/SQLite/SQLiteCursor;->longValue(I)J

    move-result-wide v11

    .line 193
    invoke-static {v11, v12}, Lorg/telegram/messenger/DialogObject;->isChatDialog(J)Z

    move-result v6

    if-nez v6, :cond_0

    goto :goto_0

    .line 196
    :cond_0
    invoke-virtual {v5, v4}, Lorg/telegram/SQLite/SQLiteCursor;->intValue(I)I

    move-result v6

    int-to-long v13, v6

    invoke-virtual {v5, v10}, Lorg/telegram/SQLite/SQLiteCursor;->intValue(I)I

    move-result v6

    move/from16 p0, v10

    move-wide/from16 p3, v11

    int-to-long v10, v6

    invoke-virtual {v5, v9}, Lorg/telegram/SQLite/SQLiteCursor;->intValue(I)I

    move-result v6

    move v12, v9

    move-wide v15, v10

    int-to-long v9, v6

    invoke-virtual {v5, v8}, Lorg/telegram/SQLite/SQLiteCursor;->intValue(I)I

    move-result v6

    move v11, v12

    move-wide/from16 v17, v13

    int-to-long v12, v6

    const/4 v6, 0x5

    new-array v6, v6, [J

    aput-wide p3, v6, v7

    aput-wide v17, v6, v4

    aput-wide v15, v6, p0

    aput-wide v9, v6, v11

    aput-wide v12, v6, v8

    invoke-virtual {v2, v6}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    move-wide/from16 v8, p3

    neg-long v8, v8

    .line 197
    invoke-static {v8, v9}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v6

    invoke-virtual {v3, v6}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :catch_0
    move-exception v0

    goto/16 :goto_6

    :cond_1
    move v11, v9

    move/from16 p0, v10

    .line 199
    invoke-virtual {v5}, Lorg/telegram/SQLite/SQLiteCursor;->dispose()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 205
    invoke-virtual {v2}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v5

    if-eqz v5, :cond_2

    goto/16 :goto_5

    .line 208
    :cond_2
    new-instance v5, Ljava/util/ArrayList;

    invoke-direct {v5}, Ljava/util/ArrayList;-><init>()V

    .line 210
    :try_start_1
    const-string v6, ","

    invoke-static {v6, v3}, Landroid/text/TextUtils;->join(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v3, v5}, Lorg/telegram/messenger/MessagesStorage;->getChatsInternal(Ljava/lang/String;Ljava/util/ArrayList;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    .line 216
    new-instance v0, Landroidx/collection/LongSparseArray;

    invoke-direct {v0}, Landroidx/collection/LongSparseArray;-><init>()V

    move v3, v7

    .line 217
    :goto_1
    invoke-virtual {v5}, Ljava/util/ArrayList;->size()I

    move-result v6

    if-ge v3, v6, :cond_3

    .line 218
    invoke-virtual {v5, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lorg/telegram/tgnet/TLRPC$Chat;

    iget-wide v9, v6, Lorg/telegram/tgnet/TLRPC$Chat;->id:J

    invoke-virtual {v5, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lorg/telegram/tgnet/TLRPC$Chat;

    invoke-virtual {v0, v9, v10, v6}, Landroidx/collection/LongSparseArray;->put(JLjava/lang/Object;)V

    add-int/lit8 v3, v3, 0x1

    goto :goto_1

    :cond_3
    move v3, v7

    .line 220
    :goto_2
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v5

    if-ge v3, v5, :cond_7

    .line 221
    invoke-virtual {v2, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, [J

    .line 222
    aget-wide v9, v5, v7

    neg-long v12, v9

    .line 223
    invoke-virtual {v0, v12, v13}, Landroidx/collection/LongSparseArray;->get(J)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lorg/telegram/tgnet/TLRPC$Chat;

    .line 224
    invoke-static {v6}, Lcom/exteragram/messenger/feed/FeedController;->isEligibleChannel(Lorg/telegram/tgnet/TLRPC$Chat;)Z

    move-result v12

    if-nez v12, :cond_4

    :goto_3
    move-object/from16 v12, p2

    goto :goto_4

    .line 227
    :cond_4
    aget-wide v12, v5, v8

    const-wide/16 v14, 0x1

    cmp-long v12, v12, v14

    if-nez v12, :cond_5

    if-nez p1, :cond_5

    goto :goto_3

    .line 230
    :cond_5
    iput-boolean v4, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;->hasChannels:Z

    .line 231
    iget-object v12, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;->channels:Ljava/util/ArrayList;

    invoke-virtual {v12, v6}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 232
    invoke-static {v9, v10}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v6

    move-object/from16 v12, p2

    invoke-virtual {v12, v6}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result v6

    if-eqz v6, :cond_6

    goto :goto_4

    .line 235
    :cond_6
    iget-object v6, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;->includedRows:Ljava/util/ArrayList;

    aget-wide v13, v5, v4

    aget-wide v15, v5, p0

    aget-wide v17, v5, v11

    new-array v5, v8, [J

    aput-wide v9, v5, v7

    aput-wide v13, v5, v4

    aput-wide v15, v5, p0

    aput-wide v17, v5, v11

    invoke-virtual {v6, v5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :goto_4
    add-int/lit8 v3, v3, 0x1

    goto :goto_2

    :cond_7
    :goto_5
    return-object v1

    :catch_1
    move-exception v0

    .line 212
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 213
    iput-boolean v4, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;->failed:Z

    return-object v1

    .line 201
    :goto_6
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 202
    iput-boolean v4, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;->failed:Z

    return-object v1
.end method

.method private static clusterGroupedMessages(Ljava/util/ArrayList;)V
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/tgnet/TLRPC$Message;",
            ">;)V"
        }
    .end annotation

    .line 597
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v1, 0x3

    if-ge v0, v1, :cond_0

    goto :goto_3

    .line 600
    :cond_0
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    const/4 v1, 0x0

    move v2, v1

    move v3, v2

    .line 602
    :goto_0
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v4

    const-wide/16 v5, 0x0

    if-ge v2, v4, :cond_3

    .line 603
    invoke-virtual {p0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lorg/telegram/tgnet/TLRPC$Message;

    iget-wide v7, v4, Lorg/telegram/tgnet/TLRPC$Message;->grouped_id:J

    cmp-long v4, v7, v5

    if-nez v4, :cond_1

    goto :goto_2

    .line 607
    :cond_1
    invoke-static {v7, v8}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v0, v4}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/util/ArrayList;

    if-nez v4, :cond_2

    .line 609
    new-instance v4, Ljava/util/ArrayList;

    invoke-direct {v4}, Ljava/util/ArrayList;-><init>()V

    .line 610
    invoke-static {v7, v8}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    invoke-virtual {v0, v5, v4}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_1

    :cond_2
    const/4 v3, 0x1

    .line 614
    :goto_1
    invoke-virtual {p0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lorg/telegram/tgnet/TLRPC$Message;

    invoke-virtual {v4, v5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :goto_2
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_3
    if-nez v3, :cond_4

    :goto_3
    return-void

    .line 619
    :cond_4
    new-instance v2, Ljava/util/ArrayList;

    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v3

    invoke-direct {v2, v3}, Ljava/util/ArrayList;-><init>(I)V

    .line 620
    new-instance v3, Ljava/util/HashSet;

    invoke-direct {v3}, Ljava/util/HashSet;-><init>()V

    .line 621
    :goto_4
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v4

    if-ge v1, v4, :cond_7

    .line 622
    invoke-virtual {p0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lorg/telegram/tgnet/TLRPC$Message;

    .line 623
    iget-wide v7, v4, Lorg/telegram/tgnet/TLRPC$Message;->grouped_id:J

    cmp-long v9, v7, v5

    if-nez v9, :cond_5

    .line 625
    invoke-virtual {v2, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_5

    .line 626
    :cond_5
    invoke-static {v7, v8}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_6

    .line 627
    invoke-static {v7, v8}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v0, v4}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/util/Collection;

    invoke-virtual {v2, v4}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    :cond_6
    :goto_5
    add-int/lit8 v1, v1, 0x1

    goto :goto_4

    .line 630
    :cond_7
    invoke-virtual {p0}, Ljava/util/ArrayList;->clear()V

    .line 631
    invoke-virtual {p0, v2}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    return-void
.end method

.method private static compareDesc(Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;)I
    .locals 7

    .line 406
    iget v0, p0, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->date:I

    iget v1, p1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->date:I

    const/4 v2, 0x1

    const/4 v3, -0x1

    if-eq v0, v1, :cond_1

    if-le v0, v1, :cond_0

    return v3

    :cond_0
    return v2

    .line 409
    :cond_1
    iget-wide v0, p0, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->uid:J

    iget-wide v4, p1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->uid:J

    cmp-long v6, v0, v4

    if-eqz v6, :cond_3

    cmp-long p0, v0, v4

    if-lez p0, :cond_2

    return v3

    :cond_2
    return v2

    .line 412
    :cond_3
    iget p0, p0, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->mid:I

    iget p1, p1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->mid:I

    invoke-static {p0, p1}, Ljava/lang/Integer;->compare(II)I

    move-result p0

    neg-int p0, p0

    return p0
.end method

.method private completeTrailingAlbum(Lorg/telegram/messenger/MessagesStorage;Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;Ljava/util/ArrayList;Ljava/util/ArrayList;)V
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/telegram/messenger/MessagesStorage;",
            "Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;",
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;",
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;)V"
        }
    .end annotation

    .line 504
    iget-object v0, p2, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;->messages:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 507
    :cond_0
    iget-object v0, p2, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;->messages:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    add-int/lit8 v1, v1, -0x1

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/tgnet/TLRPC$Message;

    .line 508
    iget-wide v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->grouped_id:J

    const-wide/16 v3, 0x0

    cmp-long v1, v1, v3

    if-nez v1, :cond_1

    :goto_0
    return-void

    .line 511
    :cond_1
    invoke-virtual {p1}, Lorg/telegram/messenger/MessagesStorage;->getDatabase()Lorg/telegram/SQLite/SQLiteDatabase;

    move-result-object p1

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "SELECT data, mid, date, uid FROM messages_v2 WHERE uid = "

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v2, v0, Lorg/telegram/tgnet/TLRPC$Message;->dialog_id:J

    invoke-virtual {v1, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v2, " AND mid > 0 AND mid < "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v2, v0, Lorg/telegram/tgnet/TLRPC$Message;->id:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, " ORDER BY date DESC, mid DESC LIMIT 9"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    new-array v2, v2, [Ljava/lang/Object;

    invoke-virtual {p1, v1, v2}, Lorg/telegram/SQLite/SQLiteDatabase;->queryFinalized(Ljava/lang/String;[Ljava/lang/Object;)Lorg/telegram/SQLite/SQLiteCursor;

    move-result-object p1

    .line 516
    :goto_1
    :try_start_0
    invoke-virtual {p1}, Lorg/telegram/SQLite/SQLiteCursor;->next()Z

    move-result v1

    if-eqz v1, :cond_3

    .line 517
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->readMessage(Lorg/telegram/SQLite/SQLiteCursor;)Lorg/telegram/tgnet/TLRPC$Message;

    move-result-object v1

    if-eqz v1, :cond_3

    .line 518
    iget-wide v2, v1, Lorg/telegram/tgnet/TLRPC$Message;->grouped_id:J

    iget-wide v4, v0, Lorg/telegram/tgnet/TLRPC$Message;->grouped_id:J

    cmp-long v2, v2, v4

    if-eqz v2, :cond_2

    goto :goto_2

    .line 521
    :cond_2
    iget-object v2, p2, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;->messages:Ljava/util/ArrayList;

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    const/4 v2, 0x0

    .line 522
    invoke-static {v1, p3, p4, v2}, Lorg/telegram/messenger/MessagesStorage;->addUsersAndChatsFromMessage(Lorg/telegram/tgnet/TLRPC$Message;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_1

    :catchall_0
    move-exception p0

    goto :goto_3

    .line 525
    :cond_3
    :goto_2
    invoke-virtual {p1}, Lorg/telegram/SQLite/SQLiteCursor;->dispose()V

    return-void

    :goto_3
    invoke-virtual {p1}, Lorg/telegram/SQLite/SQLiteCursor;->dispose()V

    .line 526
    throw p0
.end method

.method private findUnreadBoundary(Lorg/telegram/messenger/MessagesStorage;Ljava/util/ArrayList;I)Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;
    .locals 8
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/telegram/messenger/MessagesStorage;",
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;",
            ">;I)",
            "Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;"
        }
    .end annotation

    .line 350
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const/4 v1, 0x0

    const/4 v2, 0x0

    move v3, v2

    move v4, v3

    .line 352
    :goto_0
    invoke-virtual {p2}, Ljava/util/ArrayList;->size()I

    move-result v5

    if-ge v3, v5, :cond_7

    .line 353
    invoke-virtual {p2, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;

    .line 354
    iget v6, v5, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->topMessage:I

    iget v7, v5, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->readInboxMax:I

    if-gt v6, v7, :cond_0

    iget v6, v5, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->unreadCount:I

    if-lez v6, :cond_2

    .line 355
    :cond_0
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->length()I

    move-result v6

    if-lez v6, :cond_1

    .line 356
    const-string v6, " OR "

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 358
    :cond_1
    const-string/jumbo v6, "uid = "

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v6, v5, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->dialogId:J

    invoke-virtual {v0, v6, v7}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v6, " AND mid > "

    invoke-virtual {v0, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v5, v5, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->readInboxMax:I

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    add-int/lit8 v4, v4, 0x1

    :cond_2
    if-lez v4, :cond_6

    const/16 v5, 0x40

    if-eq v4, v5, :cond_3

    .line 361
    invoke-virtual {p2}, Ljava/util/ArrayList;->size()I

    move-result v5

    add-int/lit8 v5, v5, -0x1

    if-ne v3, v5, :cond_6

    .line 362
    :cond_3
    invoke-direct {p0, p1, v0, p3}, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->queryUnreadBoundary(Lorg/telegram/messenger/MessagesStorage;Ljava/lang/StringBuilder;I)Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;

    move-result-object v4

    if-eqz v4, :cond_5

    if-eqz v1, :cond_4

    .line 363
    invoke-static {v4, v1}, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->compareDesc(Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;)I

    move-result v5

    if-lez v5, :cond_5

    :cond_4
    move-object v1, v4

    .line 366
    :cond_5
    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->setLength(I)V

    move v4, v2

    :cond_6
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_7
    return-object v1
.end method

.method private static loadChannelDepths(Lorg/telegram/messenger/MessagesStorage;Ljava/util/ArrayList;)V
    .locals 11
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/telegram/messenger/MessagesStorage;",
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;",
            ">;)V"
        }
    .end annotation

    .line 551
    new-instance v0, Landroidx/collection/LongSparseArray;

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v1

    invoke-direct {v0, v1}, Landroidx/collection/LongSparseArray;-><init>(I)V

    const/4 v1, 0x0

    move v2, v1

    .line 552
    :goto_0
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v3

    if-ge v2, v3, :cond_0

    .line 553
    invoke-virtual {p1, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;

    .line 554
    iput v1, v3, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->depthMid:I

    const v4, 0x7fffffff

    .line 555
    iput v4, v3, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->depthDate:I

    .line 556
    iput-boolean v1, v3, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->hasCached:Z

    .line 557
    iput-boolean v1, v3, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->localStartReached:Z

    .line 558
    iget-wide v4, v3, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->dialogId:J

    invoke-virtual {v0, v4, v5, v3}, Landroidx/collection/LongSparseArray;->put(JLjava/lang/Object;)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_0
    move v2, v1

    .line 560
    :goto_1
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v3

    const/4 v4, 0x1

    if-ge v2, v3, :cond_5

    add-int/lit8 v3, v2, 0x40

    .line 561
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v5

    invoke-static {v3, v5}, Ljava/lang/Math;->min(II)I

    move-result v5

    .line 562
    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    :goto_2
    if-ge v2, v5, :cond_2

    .line 564
    invoke-virtual {v6}, Ljava/lang/StringBuilder;->length()I

    move-result v7

    if-lez v7, :cond_1

    .line 565
    const-string v7, " UNION ALL "

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 567
    :cond_1
    invoke-virtual {p1, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;

    .line 568
    iget v8, v7, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->holeEnd:I

    invoke-static {v8, v4}, Ljava/lang/Math;->max(II)I

    move-result v8

    .line 569
    const-string v9, "SELECT uid, mid, date FROM (SELECT uid, mid, date FROM messages_v2 WHERE uid = "

    invoke-virtual {v6, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v9, v7, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->dialogId:J

    .line 570
    invoke-virtual {v6, v9, v10}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v7, " AND mid >= "

    .line 571
    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 572
    invoke-virtual {v6, v8}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v7, " ORDER BY date ASC, mid ASC LIMIT 1)"

    .line 573
    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    add-int/lit8 v2, v2, 0x1

    goto :goto_2

    .line 575
    :cond_2
    invoke-virtual {p0}, Lorg/telegram/messenger/MessagesStorage;->getDatabase()Lorg/telegram/SQLite/SQLiteDatabase;

    move-result-object v2

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    new-array v6, v1, [Ljava/lang/Object;

    invoke-virtual {v2, v5, v6}, Lorg/telegram/SQLite/SQLiteDatabase;->queryFinalized(Ljava/lang/String;[Ljava/lang/Object;)Lorg/telegram/SQLite/SQLiteCursor;

    move-result-object v2

    .line 577
    :goto_3
    :try_start_0
    invoke-virtual {v2}, Lorg/telegram/SQLite/SQLiteCursor;->next()Z

    move-result v5

    if-eqz v5, :cond_4

    .line 578
    invoke-virtual {v2, v1}, Lorg/telegram/SQLite/SQLiteCursor;->longValue(I)J

    move-result-wide v5

    invoke-virtual {v0, v5, v6}, Landroidx/collection/LongSparseArray;->get(J)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;

    if-nez v5, :cond_3

    goto :goto_3

    .line 582
    :cond_3
    invoke-virtual {v2, v4}, Lorg/telegram/SQLite/SQLiteCursor;->intValue(I)I

    move-result v6

    iput v6, v5, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->depthMid:I

    const/4 v6, 0x2

    .line 583
    invoke-virtual {v2, v6}, Lorg/telegram/SQLite/SQLiteCursor;->intValue(I)I

    move-result v6

    iput v6, v5, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->depthDate:I

    .line 584
    iput-boolean v4, v5, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->hasCached:Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_3

    :catchall_0
    move-exception p0

    goto :goto_4

    .line 587
    :cond_4
    invoke-virtual {v2}, Lorg/telegram/SQLite/SQLiteCursor;->dispose()V

    move v2, v3

    goto/16 :goto_1

    :goto_4
    invoke-virtual {v2}, Lorg/telegram/SQLite/SQLiteCursor;->dispose()V

    .line 588
    throw p0

    :cond_5
    move p0, v1

    .line 590
    :goto_5
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge p0, v0, :cond_7

    .line 591
    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;

    .line 592
    iget-boolean v2, v0, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->hasHole:Z

    if-nez v2, :cond_6

    iget-boolean v2, v0, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->hasCached:Z

    if-eqz v2, :cond_6

    move v2, v4

    goto :goto_6

    :cond_6
    move v2, v1

    :goto_6
    iput-boolean v2, v0, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->localStartReached:Z

    add-int/lit8 p0, p0, 0x1

    goto :goto_5

    :cond_7
    return-void
.end method

.method private loadChunk(Lorg/telegram/messenger/MessagesStorage;Ljava/lang/String;ILcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;Ljava/util/ArrayList;Ljava/util/ArrayList;)I
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/telegram/messenger/MessagesStorage;",
            "Ljava/lang/String;",
            "I",
            "Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;",
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;",
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;)I"
        }
    .end annotation

    .line 321
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "SELECT data, mid, date, uid FROM messages_v2 WHERE uid IN ("

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 322
    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p2, ") AND mid > 0"

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    if-lez p3, :cond_0

    .line 324
    const-string p2, " AND date >= "

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    .line 326
    :cond_0
    iget-object p2, p4, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;->last:Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;

    invoke-virtual {p2}, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->isEmpty()Z

    move-result p2

    const/4 p3, 0x0

    const/4 v1, 0x1

    if-nez p2, :cond_1

    .line 327
    iget-object p2, p4, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;->last:Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;

    invoke-static {v0, p2, v1, p3}, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->appendCursorBound(Ljava/lang/StringBuilder;Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;ZZ)V

    .line 329
    :cond_1
    const-string p2, " ORDER BY date DESC, uid DESC, mid DESC LIMIT "

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 p2, 0x1e

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    .line 331
    invoke-virtual {p1}, Lorg/telegram/messenger/MessagesStorage;->getDatabase()Lorg/telegram/SQLite/SQLiteDatabase;

    move-result-object p1

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    new-array v0, p3, [Ljava/lang/Object;

    invoke-virtual {p1, p2, v0}, Lorg/telegram/SQLite/SQLiteDatabase;->queryFinalized(Ljava/lang/String;[Ljava/lang/Object;)Lorg/telegram/SQLite/SQLiteCursor;

    move-result-object p1

    .line 332
    :cond_2
    :goto_0
    invoke-virtual {p1}, Lorg/telegram/SQLite/SQLiteCursor;->next()Z

    move-result p2

    if-eqz p2, :cond_4

    add-int/lit8 p3, p3, 0x1

    .line 334
    iget-object p2, p4, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;->last:Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;

    const/4 v0, 0x2

    invoke-virtual {p1, v0}, Lorg/telegram/SQLite/SQLiteCursor;->intValue(I)I

    move-result v0

    const/4 v2, 0x3

    invoke-virtual {p1, v2}, Lorg/telegram/SQLite/SQLiteCursor;->longValue(I)J

    move-result-wide v2

    invoke-virtual {p1, v1}, Lorg/telegram/SQLite/SQLiteCursor;->intValue(I)I

    move-result v4

    invoke-virtual {p2, v0, v2, v3, v4}, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->set(IJI)V

    .line 335
    iget-object p2, p4, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;->first:Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;

    invoke-virtual {p2}, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->isEmpty()Z

    move-result p2

    if-eqz p2, :cond_3

    .line 336
    iget-object p2, p4, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;->first:Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;

    iget-object v0, p4, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;->last:Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;

    iget v2, v0, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->date:I

    iget-wide v3, v0, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->uid:J

    iget v0, v0, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->mid:I

    invoke-virtual {p2, v2, v3, v4, v0}, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->set(IJI)V

    .line 338
    :cond_3
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->readMessage(Lorg/telegram/SQLite/SQLiteCursor;)Lorg/telegram/tgnet/TLRPC$Message;

    move-result-object p2

    if-eqz p2, :cond_2

    .line 340
    iget-object v0, p4, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;->messages:Ljava/util/ArrayList;

    invoke-virtual {v0, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    const/4 v0, 0x0

    .line 341
    invoke-static {p2, p5, p6, v0}, Lorg/telegram/messenger/MessagesStorage;->addUsersAndChatsFromMessage(Lorg/telegram/tgnet/TLRPC$Message;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;)V

    goto :goto_0

    .line 344
    :cond_4
    invoke-virtual {p1}, Lorg/telegram/SQLite/SQLiteCursor;->dispose()V

    return p3
.end method

.method private queryUnreadBoundary(Lorg/telegram/messenger/MessagesStorage;Ljava/lang/StringBuilder;I)Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;
    .locals 2

    .line 374
    new-instance p0, Ljava/lang/StringBuilder;

    const-string v0, "SELECT date, uid, mid FROM messages_v2 WHERE mid > 0 AND ("

    invoke-direct {p0, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;

    const-string p2, ")"

    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    if-lez p3, :cond_0

    .line 376
    const-string p2, " AND date >= "

    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    .line 378
    :cond_0
    const-string p2, " ORDER BY date ASC, uid ASC, mid ASC LIMIT 1"

    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 380
    :try_start_0
    invoke-virtual {p1}, Lorg/telegram/messenger/MessagesStorage;->getDatabase()Lorg/telegram/SQLite/SQLiteDatabase;

    move-result-object p1

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    const/4 p2, 0x0

    new-array p3, p2, [Ljava/lang/Object;

    invoke-virtual {p1, p0, p3}, Lorg/telegram/SQLite/SQLiteDatabase;->queryFinalized(Ljava/lang/String;[Ljava/lang/Object;)Lorg/telegram/SQLite/SQLiteCursor;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 382
    :try_start_1
    invoke-virtual {p0}, Lorg/telegram/SQLite/SQLiteCursor;->next()Z

    move-result p1

    if-eqz p1, :cond_1

    .line 383
    new-instance p1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;

    invoke-direct {p1}, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;-><init>()V

    .line 384
    invoke-virtual {p0, p2}, Lorg/telegram/SQLite/SQLiteCursor;->intValue(I)I

    move-result p2

    const/4 p3, 0x1

    invoke-virtual {p0, p3}, Lorg/telegram/SQLite/SQLiteCursor;->longValue(I)J

    move-result-wide v0

    const/4 p3, 0x2

    invoke-virtual {p0, p3}, Lorg/telegram/SQLite/SQLiteCursor;->intValue(I)I

    move-result p3

    invoke-virtual {p1, p2, v0, v1, p3}, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->set(IJI)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 388
    :try_start_2
    invoke-virtual {p0}, Lorg/telegram/SQLite/SQLiteCursor;->dispose()V

    return-object p1

    :catchall_0
    move-exception p1

    goto :goto_0

    :cond_1
    invoke-virtual {p0}, Lorg/telegram/SQLite/SQLiteCursor;->dispose()V

    goto :goto_1

    :goto_0
    invoke-virtual {p0}, Lorg/telegram/SQLite/SQLiteCursor;->dispose()V

    .line 389
    throw p1
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    :catch_0
    move-exception p0

    .line 391
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :goto_1
    const/4 p0, 0x0

    return-object p0
.end method

.method private readMessage(Lorg/telegram/SQLite/SQLiteCursor;)Lorg/telegram/tgnet/TLRPC$Message;
    .locals 5

    const/4 v0, 0x0

    .line 530
    invoke-virtual {p1, v0}, Lorg/telegram/SQLite/SQLiteCursor;->byteBufferValue(I)Lorg/telegram/tgnet/NativeByteBuffer;

    move-result-object v1

    const/4 v2, 0x0

    if-nez v1, :cond_0

    return-object v2

    .line 534
    :cond_0
    invoke-virtual {v1, v0}, Lorg/telegram/tgnet/NativeByteBuffer;->readInt32(Z)I

    move-result v3

    invoke-static {v1, v3, v0}, Lorg/telegram/tgnet/TLRPC$Message;->TLdeserialize(Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLRPC$Message;

    move-result-object v0

    if-nez v0, :cond_1

    .line 536
    invoke-virtual {v1}, Lorg/telegram/tgnet/NativeByteBuffer;->reuse()V

    return-object v2

    .line 539
    :cond_1
    iget p0, p0, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->currentAccount:I

    invoke-static {p0}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object p0

    iget-wide v3, p0, Lorg/telegram/messenger/UserConfig;->clientUserId:J

    invoke-virtual {v0, v1, v3, v4}, Lorg/telegram/tgnet/TLRPC$Message;->readAttachPath(Lorg/telegram/tgnet/InputSerializedData;J)V

    .line 540
    invoke-virtual {v1}, Lorg/telegram/tgnet/NativeByteBuffer;->reuse()V

    .line 541
    instance-of p0, v0, Lorg/telegram/tgnet/TLRPC$TL_messageEmpty;

    if-nez p0, :cond_3

    iget-object p0, v0, Lorg/telegram/tgnet/TLRPC$Message;->action:Lorg/telegram/tgnet/TLRPC$MessageAction;

    if-eqz p0, :cond_2

    goto :goto_0

    :cond_2
    const/4 p0, 0x1

    .line 544
    invoke-virtual {p1, p0}, Lorg/telegram/SQLite/SQLiteCursor;->intValue(I)I

    move-result p0

    iput p0, v0, Lorg/telegram/tgnet/TLRPC$Message;->id:I

    const/4 p0, 0x2

    .line 545
    invoke-virtual {p1, p0}, Lorg/telegram/SQLite/SQLiteCursor;->intValue(I)I

    move-result p0

    iput p0, v0, Lorg/telegram/tgnet/TLRPC$Message;->date:I

    const/4 p0, 0x3

    .line 546
    invoke-virtual {p1, p0}, Lorg/telegram/SQLite/SQLiteCursor;->longValue(I)J

    move-result-wide p0

    iput-wide p0, v0, Lorg/telegram/tgnet/TLRPC$Message;->dialog_id:J

    return-object v0

    :cond_3
    :goto_0
    return-object v2
.end method


# virtual methods
.method public enumerateChannels(Lcom/exteragram/messenger/feed/FeedConfig;IZ)Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelEnumeration;
    .locals 16

    move-object/from16 v1, p0

    move/from16 v0, p2

    const/4 v2, 0x0

    move/from16 v3, p3

    move v4, v2

    .line 137
    :goto_0
    monitor-enter p0

    .line 138
    :try_start_0
    iget-object v5, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->channelSetCache:Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;

    .line 139
    iget-object v6, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->channelCacheEpoch:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v6}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v6

    .line 140
    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_2

    .line 141
    invoke-virtual/range {p1 .. p1}, Lcom/exteragram/messenger/feed/FeedConfig;->snapshot()Lcom/exteragram/messenger/feed/FeedConfig$Snapshot;

    move-result-object v7

    .line 142
    invoke-virtual {v7}, Lcom/exteragram/messenger/feed/FeedConfig$Snapshot;->getGeneration()I

    move-result v8

    if-nez v3, :cond_0

    if-eqz v5, :cond_0

    .line 143
    iget v3, v5, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;->sessionGen:I

    if-ne v3, v0, :cond_0

    iget v3, v5, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;->configGen:I

    if-eq v3, v8, :cond_2

    .line 144
    :cond_0
    invoke-virtual {v7}, Lcom/exteragram/messenger/feed/FeedConfig$Snapshot;->getIncludeArchived()Z

    move-result v3

    new-instance v5, Ljava/util/HashSet;

    invoke-virtual {v7}, Lcom/exteragram/messenger/feed/FeedConfig$Snapshot;->getExcludedChannels()Ljava/util/Set;

    move-result-object v7

    invoke-direct {v5, v7}, Ljava/util/HashSet;-><init>(Ljava/util/Collection;)V

    invoke-direct {v1, v3, v5, v0, v8}, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->buildChannelSet(ZLjava/util/HashSet;II)Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;

    move-result-object v5

    .line 145
    iget-boolean v3, v5, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;->failed:Z

    if-nez v3, :cond_2

    .line 146
    monitor-enter p0

    .line 147
    :try_start_1
    iget-object v3, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->channelCacheEpoch:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v3}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v3

    if-ne v6, v3, :cond_1

    .line 148
    iput-object v5, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->channelSetCache:Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;

    goto :goto_1

    :catchall_0
    move-exception v0

    goto :goto_2

    .line 150
    :cond_1
    :goto_1
    monitor-exit p0

    goto :goto_3

    :goto_2
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0

    .line 153
    :cond_2
    :goto_3
    iget-boolean v3, v5, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;->failed:Z

    const/4 v7, 0x3

    const/4 v8, 0x1

    if-nez v3, :cond_5

    if-lt v4, v7, :cond_3

    goto :goto_5

    .line 156
    :cond_3
    monitor-enter p0

    .line 157
    :try_start_2
    iget-object v3, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->channelCacheEpoch:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v3}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v3

    if-ne v6, v3, :cond_4

    .line 158
    monitor-exit p0

    goto :goto_5

    :catchall_1
    move-exception v0

    goto :goto_4

    .line 160
    :cond_4
    monitor-exit p0

    add-int/lit8 v4, v4, 0x1

    move v3, v8

    goto :goto_0

    :goto_4
    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    throw v0

    .line 163
    :cond_5
    :goto_5
    new-instance v0, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelEnumeration;

    invoke-direct {v0}, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelEnumeration;-><init>()V

    .line 164
    iget-boolean v1, v5, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;->hasChannels:Z

    iput-boolean v1, v0, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelEnumeration;->hasChannels:Z

    .line 165
    iget-boolean v1, v5, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;->failed:Z

    iput-boolean v1, v0, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelEnumeration;->failed:Z

    .line 166
    iget v1, v5, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;->configGen:I

    iput v1, v0, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelEnumeration;->configGeneration:I

    .line 167
    iput v6, v0, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelEnumeration;->cacheEpoch:I

    .line 168
    iget-object v1, v0, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelEnumeration;->channels:Ljava/util/ArrayList;

    iget-object v3, v5, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;->channels:Ljava/util/ArrayList;

    invoke-virtual {v1, v3}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    move v1, v2

    .line 169
    :goto_6
    iget-object v3, v5, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;->includedRows:Ljava/util/ArrayList;

    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v3

    if-ge v1, v3, :cond_6

    .line 170
    iget-object v3, v5, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;->includedRows:Ljava/util/ArrayList;

    invoke-virtual {v3, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, [J

    .line 171
    iget-object v4, v0, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelEnumeration;->included:Ljava/util/ArrayList;

    new-instance v9, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;

    aget-wide v10, v3, v2

    aget-wide v12, v3, v8

    long-to-int v12, v12

    const/4 v6, 0x2

    aget-wide v13, v3, v6

    long-to-int v13, v13

    aget-wide v14, v3, v7

    long-to-int v14, v14

    invoke-direct/range {v9 .. v14}, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;-><init>(JIII)V

    invoke-virtual {v4, v9}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    add-int/lit8 v1, v1, 0x1

    goto :goto_6

    :cond_6
    return-object v0

    :catchall_2
    move-exception v0

    .line 140
    :try_start_3
    monitor-exit p0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_2

    throw v0
.end method

.method public declared-synchronized getChannelCacheEpoch()I
    .locals 1

    monitor-enter p0

    .line 177
    :try_start_0
    iget-object v0, p0, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->channelCacheEpoch:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return v0

    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method public declared-synchronized invalidateChannelCache()V
    .locals 1

    monitor-enter p0

    .line 128
    :try_start_0
    iget-object v0, p0, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->channelCacheEpoch:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->incrementAndGet()I

    const/4 v0, 0x0

    .line 129
    iput-object v0, p0, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->channelSetCache:Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSet;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 130
    monitor-exit p0

    return-void

    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method public declared-synchronized isEnumerationCurrent(Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelEnumeration;)Z
    .locals 1

    monitor-enter p0

    if-eqz p1, :cond_0

    .line 181
    :try_start_0
    iget p1, p1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelEnumeration;->cacheEpoch:I

    iget-object v0, p0, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->channelCacheEpoch:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v0

    if-ne p1, v0, :cond_0

    const/4 p1, 0x1

    goto :goto_0

    :catchall_0
    move-exception p1

    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p1

    :cond_0
    const/4 p1, 0x0

    :goto_0
    monitor-exit p0

    return p1
.end method

.method public loadChannelWindow(Ljava/util/ArrayList;Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;)Lcom/exteragram/messenger/feed/FeedTimelineLoader$WindowPage;
    .locals 8
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;",
            "Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;",
            "Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;",
            ")",
            "Lcom/exteragram/messenger/feed/FeedTimelineLoader$WindowPage;"
        }
    .end annotation

    .line 458
    const-string v0, ","

    new-instance v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$WindowPage;

    invoke-direct {v1}, Lcom/exteragram/messenger/feed/FeedTimelineLoader$WindowPage;-><init>()V

    .line 459
    invoke-virtual {p1}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_6

    invoke-virtual {p2}, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_6

    invoke-virtual {p3}, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_0

    goto/16 :goto_4

    :cond_0
    const/4 v2, 0x1

    .line 463
    :try_start_0
    iget v3, p0, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->currentAccount:I

    invoke-static {v3}, Lorg/telegram/messenger/MessagesStorage;->getInstance(I)Lorg/telegram/messenger/MessagesStorage;

    move-result-object v3

    .line 464
    new-instance v4, Ljava/util/ArrayList;

    invoke-direct {v4}, Ljava/util/ArrayList;-><init>()V

    .line 465
    new-instance v5, Ljava/util/ArrayList;

    invoke-direct {v5}, Ljava/util/ArrayList;-><init>()V

    .line 466
    new-instance v6, Ljava/lang/StringBuilder;

    const-string v7, "SELECT data, mid, date, uid FROM messages_v2 WHERE uid IN ("

    invoke-direct {v6, v7}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 467
    invoke-static {v0, p1}, Landroid/text/TextUtils;->join(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v6, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, ") AND mid > 0"

    invoke-virtual {v6, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 468
    invoke-static {v6, p2, v2, v2}, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->appendCursorBound(Ljava/lang/StringBuilder;Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;ZZ)V

    const/4 p1, 0x0

    .line 469
    invoke-static {v6, p3, p1, v2}, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->appendCursorBound(Ljava/lang/StringBuilder;Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;ZZ)V

    .line 470
    const-string p2, " ORDER BY date DESC, uid DESC, mid DESC LIMIT "

    invoke-virtual {v6, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 p2, 0x1f5

    invoke-virtual {v6, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    .line 472
    invoke-virtual {v3}, Lorg/telegram/messenger/MessagesStorage;->getDatabase()Lorg/telegram/SQLite/SQLiteDatabase;

    move-result-object p2

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p3

    new-array v6, p1, [Ljava/lang/Object;

    invoke-virtual {p2, p3, v6}, Lorg/telegram/SQLite/SQLiteDatabase;->queryFinalized(Ljava/lang/String;[Ljava/lang/Object;)Lorg/telegram/SQLite/SQLiteCursor;

    move-result-object p2

    .line 473
    :cond_1
    :goto_0
    invoke-virtual {p2}, Lorg/telegram/SQLite/SQLiteCursor;->next()Z

    move-result p3

    if-eqz p3, :cond_3

    add-int/2addr p1, v2

    const/16 p3, 0x1f4

    if-le p1, p3, :cond_2

    .line 476
    iput-boolean v2, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$WindowPage;->truncated:Z

    goto :goto_1

    :catch_0
    move-exception p0

    goto :goto_2

    .line 479
    :cond_2
    invoke-direct {p0, p2}, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->readMessage(Lorg/telegram/SQLite/SQLiteCursor;)Lorg/telegram/tgnet/TLRPC$Message;

    move-result-object p3

    if-eqz p3, :cond_1

    .line 481
    iget-object v6, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$WindowPage;->messages:Ljava/util/ArrayList;

    invoke-virtual {v6, p3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    const/4 v6, 0x0

    .line 482
    invoke-static {p3, v4, v5, v6}, Lorg/telegram/messenger/MessagesStorage;->addUsersAndChatsFromMessage(Lorg/telegram/tgnet/TLRPC$Message;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;)V

    goto :goto_0

    .line 485
    :cond_3
    :goto_1
    invoke-virtual {p2}, Lorg/telegram/SQLite/SQLiteCursor;->dispose()V

    .line 486
    invoke-virtual {v4}, Ljava/util/ArrayList;->isEmpty()Z

    move-result p0

    if-nez p0, :cond_4

    .line 487
    iget-object p0, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$WindowPage;->users:Ljava/util/ArrayList;

    invoke-virtual {v3, v4, p0}, Lorg/telegram/messenger/MessagesStorage;->getUsersInternal(Ljava/util/ArrayList;Ljava/util/ArrayList;)V

    .line 489
    :cond_4
    invoke-virtual {v5}, Ljava/util/ArrayList;->isEmpty()Z

    move-result p0

    if-nez p0, :cond_5

    .line 490
    invoke-static {v0, v5}, Landroid/text/TextUtils;->join(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;

    move-result-object p0

    iget-object p1, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$WindowPage;->chats:Ljava/util/ArrayList;

    invoke-virtual {v3, p0, p1}, Lorg/telegram/messenger/MessagesStorage;->getChatsInternal(Ljava/lang/String;Ljava/util/ArrayList;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_3

    .line 493
    :goto_2
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 494
    iput-boolean v2, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$WindowPage;->failed:Z

    .line 495
    iget-object p0, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$WindowPage;->messages:Ljava/util/ArrayList;

    invoke-virtual {p0}, Ljava/util/ArrayList;->clear()V

    .line 496
    iget-object p0, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$WindowPage;->users:Ljava/util/ArrayList;

    invoke-virtual {p0}, Ljava/util/ArrayList;->clear()V

    .line 497
    iget-object p0, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$WindowPage;->chats:Ljava/util/ArrayList;

    invoke-virtual {p0}, Ljava/util/ArrayList;->clear()V

    .line 499
    :cond_5
    :goto_3
    iget-object p0, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$WindowPage;->messages:Ljava/util/ArrayList;

    invoke-static {p0}, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->clusterGroupedMessages(Ljava/util/ArrayList;)V

    :cond_6
    :goto_4
    return-object v1
.end method

.method public loadNewerPage(Ljava/util/ArrayList;Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;)Lcom/exteragram/messenger/feed/FeedTimelineLoader$NewerPage;
    .locals 13
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;",
            ">;",
            "Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;",
            ")",
            "Lcom/exteragram/messenger/feed/FeedTimelineLoader$NewerPage;"
        }
    .end annotation

    .line 416
    const-string v0, ","

    new-instance v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$NewerPage;

    invoke-direct {v1}, Lcom/exteragram/messenger/feed/FeedTimelineLoader$NewerPage;-><init>()V

    .line 417
    iget-object v2, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$NewerPage;->first:Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;

    iget v3, p2, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->date:I

    iget-wide v4, p2, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->uid:J

    iget v6, p2, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->mid:I

    invoke-virtual {v2, v3, v4, v5, v6}, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->set(IJI)V

    const/4 v2, 0x1

    .line 419
    :try_start_0
    new-instance v3, Ljava/util/ArrayList;

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v4

    invoke-direct {v3, v4}, Ljava/util/ArrayList;-><init>(I)V

    .line 420
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v4

    const/4 v5, 0x0

    move v6, v5

    :goto_0
    if-ge v6, v4, :cond_0

    invoke-virtual {p1, v6}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v7

    add-int/lit8 v6, v6, 0x1

    check-cast v7, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;

    .line 421
    iget-wide v7, v7, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->dialogId:J

    invoke-static {v7, v8}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v7

    invoke-virtual {v3, v7}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :catch_0
    move-exception p0

    goto/16 :goto_2

    .line 423
    :cond_0
    iget p1, p0, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->currentAccount:I

    invoke-static {p1}, Lorg/telegram/messenger/MessagesStorage;->getInstance(I)Lorg/telegram/messenger/MessagesStorage;

    move-result-object p1

    .line 424
    new-instance v4, Ljava/util/ArrayList;

    invoke-direct {v4}, Ljava/util/ArrayList;-><init>()V

    .line 425
    new-instance v6, Ljava/util/ArrayList;

    invoke-direct {v6}, Ljava/util/ArrayList;-><init>()V

    .line 426
    new-instance v7, Ljava/lang/StringBuilder;

    const-string v8, "SELECT data, mid, date, uid FROM messages_v2 WHERE uid IN ("

    invoke-direct {v7, v8}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 427
    invoke-static {v0, v3}, Landroid/text/TextUtils;->join(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v7, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, ") AND mid > 0"

    invoke-virtual {v7, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 428
    invoke-static {v7, p2, v5, v5}, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->appendCursorBound(Ljava/lang/StringBuilder;Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;ZZ)V

    .line 429
    const-string p2, " ORDER BY date ASC, uid ASC, mid ASC LIMIT "

    invoke-virtual {v7, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 p2, 0x32

    invoke-virtual {v7, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    .line 430
    invoke-virtual {p1}, Lorg/telegram/messenger/MessagesStorage;->getDatabase()Lorg/telegram/SQLite/SQLiteDatabase;

    move-result-object v3

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    new-array v8, v5, [Ljava/lang/Object;

    invoke-virtual {v3, v7, v8}, Lorg/telegram/SQLite/SQLiteDatabase;->queryFinalized(Ljava/lang/String;[Ljava/lang/Object;)Lorg/telegram/SQLite/SQLiteCursor;

    move-result-object v3

    move v7, v5

    .line 432
    :cond_1
    :goto_1
    invoke-virtual {v3}, Lorg/telegram/SQLite/SQLiteCursor;->next()Z

    move-result v8

    if-eqz v8, :cond_2

    add-int/lit8 v7, v7, 0x1

    .line 434
    iget-object v8, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$NewerPage;->first:Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;

    const/4 v9, 0x2

    invoke-virtual {v3, v9}, Lorg/telegram/SQLite/SQLiteCursor;->intValue(I)I

    move-result v9

    const/4 v10, 0x3

    invoke-virtual {v3, v10}, Lorg/telegram/SQLite/SQLiteCursor;->longValue(I)J

    move-result-wide v10

    invoke-virtual {v3, v2}, Lorg/telegram/SQLite/SQLiteCursor;->intValue(I)I

    move-result v12

    invoke-virtual {v8, v9, v10, v11, v12}, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->set(IJI)V

    .line 435
    invoke-direct {p0, v3}, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->readMessage(Lorg/telegram/SQLite/SQLiteCursor;)Lorg/telegram/tgnet/TLRPC$Message;

    move-result-object v8

    if-eqz v8, :cond_1

    .line 437
    iget-object v9, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$NewerPage;->messages:Ljava/util/ArrayList;

    invoke-virtual {v9, v8}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    const/4 v9, 0x0

    .line 438
    invoke-static {v8, v4, v6, v9}, Lorg/telegram/messenger/MessagesStorage;->addUsersAndChatsFromMessage(Lorg/telegram/tgnet/TLRPC$Message;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;)V

    goto :goto_1

    .line 441
    :cond_2
    invoke-virtual {v3}, Lorg/telegram/SQLite/SQLiteCursor;->dispose()V

    if-ne v7, p2, :cond_3

    move v5, v2

    .line 442
    :cond_3
    iput-boolean v5, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$NewerPage;->hasMore:Z

    .line 443
    invoke-virtual {v4}, Ljava/util/ArrayList;->isEmpty()Z

    move-result p0

    if-nez p0, :cond_4

    .line 444
    iget-object p0, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$NewerPage;->users:Ljava/util/ArrayList;

    invoke-virtual {p1, v4, p0}, Lorg/telegram/messenger/MessagesStorage;->getUsersInternal(Ljava/util/ArrayList;Ljava/util/ArrayList;)V

    .line 446
    :cond_4
    invoke-virtual {v6}, Ljava/util/ArrayList;->isEmpty()Z

    move-result p0

    if-nez p0, :cond_5

    .line 447
    invoke-static {v0, v6}, Landroid/text/TextUtils;->join(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;

    move-result-object p0

    iget-object p2, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$NewerPage;->chats:Ljava/util/ArrayList;

    invoke-virtual {p1, p0, p2}, Lorg/telegram/messenger/MessagesStorage;->getChatsInternal(Ljava/lang/String;Ljava/util/ArrayList;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_3

    .line 450
    :goto_2
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 451
    iput-boolean v2, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$NewerPage;->failed:Z

    .line 453
    :cond_5
    :goto_3
    iget-object p0, v1, Lcom/exteragram/messenger/feed/FeedTimelineLoader$NewerPage;->messages:Ljava/util/ArrayList;

    invoke-static {p0}, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->clusterGroupedMessages(Ljava/util/ArrayList;)V

    return-object v1
.end method

.method public loadOlderPage(Ljava/util/ArrayList;Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;Ljava/util/HashSet;)Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;
    .locals 21
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;",
            ">;",
            "Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;",
            "Ljava/util/HashSet<",
            "Ljava/lang/Long;",
            ">;)",
            "Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;"
        }
    .end annotation

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    .line 241
    const-string v7, ","

    new-instance v4, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;

    invoke-direct {v4}, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;-><init>()V

    .line 242
    invoke-virtual {v2}, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->isEmpty()Z

    move-result v3

    .line 243
    iget-object v5, v4, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;->last:Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;

    iget v6, v2, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->date:I

    iget-wide v8, v2, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->uid:J

    iget v2, v2, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->mid:I

    invoke-virtual {v5, v6, v8, v9, v2}, Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;->set(IJI)V

    const/4 v8, 0x1

    .line 245
    :try_start_0
    new-instance v9, Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v2

    invoke-direct {v9, v2}, Ljava/util/ArrayList;-><init>(I)V

    .line 246
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v2

    const/4 v10, 0x0

    move v5, v10

    :goto_0
    if-ge v5, v2, :cond_0

    invoke-virtual {v1, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    add-int/lit8 v5, v5, 0x1

    check-cast v6, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;

    .line 247
    iget-wide v11, v6, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->dialogId:J

    invoke-static {v11, v12}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v6

    invoke-virtual {v9, v6}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :catch_0
    move-exception v0

    move/from16 v17, v8

    goto/16 :goto_d

    .line 249
    :cond_0
    invoke-static {v7, v9}, Landroid/text/TextUtils;->join(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;

    move-result-object v2

    .line 250
    iget v5, v0, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->currentAccount:I

    invoke-static {v5}, Lorg/telegram/messenger/MessagesStorage;->getInstance(I)Lorg/telegram/messenger/MessagesStorage;

    move-result-object v5

    .line 252
    new-instance v6, Ljava/util/HashMap;

    invoke-direct {v6}, Ljava/util/HashMap;-><init>()V

    .line 253
    invoke-virtual {v5}, Lorg/telegram/messenger/MessagesStorage;->getDatabase()Lorg/telegram/SQLite/SQLiteDatabase;

    move-result-object v11

    new-instance v12, Ljava/lang/StringBuilder;

    invoke-direct {v12}, Ljava/lang/StringBuilder;-><init>()V

    const-string v13, "SELECT uid, max(end) FROM messages_holes WHERE uid IN ("

    invoke-virtual {v12, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v12, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v13, ") GROUP BY uid"

    invoke-virtual {v12, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v12}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v12

    new-array v13, v10, [Ljava/lang/Object;

    invoke-virtual {v11, v12, v13}, Lorg/telegram/SQLite/SQLiteDatabase;->queryFinalized(Ljava/lang/String;[Ljava/lang/Object;)Lorg/telegram/SQLite/SQLiteCursor;

    move-result-object v11

    .line 254
    :goto_1
    invoke-virtual {v11}, Lorg/telegram/SQLite/SQLiteCursor;->next()Z

    move-result v12

    if-eqz v12, :cond_1

    .line 255
    invoke-virtual {v11, v10}, Lorg/telegram/SQLite/SQLiteCursor;->longValue(I)J

    move-result-wide v12

    invoke-static {v12, v13}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v12

    invoke-virtual {v11, v8}, Lorg/telegram/SQLite/SQLiteCursor;->intValue(I)I

    move-result v13

    invoke-static {v13}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v13

    invoke-virtual {v6, v12, v13}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_1

    .line 257
    :cond_1
    invoke-virtual {v11}, Lorg/telegram/SQLite/SQLiteCursor;->dispose()V

    .line 258
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v11

    move v12, v10

    :goto_2
    if-ge v12, v11, :cond_4

    invoke-virtual {v1, v12}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v13

    add-int/lit8 v12, v12, 0x1

    check-cast v13, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;

    .line 259
    iget-wide v14, v13, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->dialogId:J

    invoke-static {v14, v15}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v14

    invoke-virtual {v6, v14}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v14

    check-cast v14, Ljava/lang/Integer;

    if-eqz v14, :cond_2

    move v15, v8

    goto :goto_3

    :cond_2
    move v15, v10

    .line 260
    :goto_3
    iput-boolean v15, v13, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->hasHole:Z

    if-eqz v15, :cond_3

    .line 261
    invoke-virtual {v14}, Ljava/lang/Integer;->intValue()I

    move-result v14

    goto :goto_4

    :cond_3
    move v14, v10

    :goto_4
    iput v14, v13, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->holeEnd:I

    goto :goto_2

    .line 263
    :cond_4
    invoke-static {v5, v1}, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->loadChannelDepths(Lorg/telegram/messenger/MessagesStorage;Ljava/util/ArrayList;)V

    .line 266
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v6

    move v11, v3

    move v3, v10

    move v12, v3

    :goto_5
    if-ge v12, v6, :cond_a

    invoke-virtual {v1, v12}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v13

    add-int/lit8 v12, v12, 0x1

    check-cast v13, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;

    .line 267
    iget-boolean v14, v13, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->localStartReached:Z

    if-nez v14, :cond_5

    iget-wide v14, v13, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->dialogId:J

    invoke-static {v14, v15}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v14

    move-object/from16 v15, p3

    invoke-virtual {v15, v14}, Ljava/util/HashSet;->contains(Ljava/lang/Object;)Z

    move-result v14

    if-nez v14, :cond_6

    move v14, v8

    goto :goto_6

    :cond_5
    move-object/from16 v15, p3

    :cond_6
    move v14, v10

    :goto_6
    iput-boolean v14, v13, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->incomplete:Z

    if-eqz v14, :cond_9

    .line 269
    iput-boolean v8, v4, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;->hasIncomplete:Z

    .line 270
    iget v14, v13, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->depthDate:I

    invoke-static {v3, v14}, Ljava/lang/Math;->max(II)I

    move-result v3

    .line 272
    iget-boolean v14, v13, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->hasCached:Z

    if-eqz v14, :cond_7

    .line 273
    iget v14, v13, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->depthMid:I

    move/from16 p2, v10

    move/from16 v16, v11

    int-to-long v10, v14

    goto :goto_7

    :cond_7
    move/from16 p2, v10

    move/from16 v16, v11

    .line 275
    iget v10, v13, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->holeEnd:I

    iget v11, v13, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->topMessage:I

    invoke-static {v10, v11}, Ljava/lang/Math;->max(II)I

    move-result v10

    if-lez v10, :cond_8

    add-int/lit8 v10, v10, 0x1

    int-to-long v10, v10

    goto :goto_7

    :cond_8
    const-wide/16 v10, 0x0

    .line 278
    :goto_7
    iget-object v14, v4, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;->backfillCandidates:Ljava/util/ArrayList;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    move/from16 v17, v8

    move-object/from16 v18, v9

    :try_start_1
    iget-wide v8, v13, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->dialogId:J

    iget v13, v13, Lcom/exteragram/messenger/feed/FeedTimelineLoader$ChannelSnapshot;->depthDate:I

    move-object/from16 v19, v2

    move/from16 v20, v3

    int-to-long v2, v13

    const/4 v13, 0x3

    new-array v13, v13, [J

    aput-wide v8, v13, p2

    aput-wide v10, v13, v17

    const/4 v8, 0x2

    aput-wide v2, v13, v8

    invoke-virtual {v14, v13}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    move/from16 v3, v20

    goto :goto_8

    :catch_1
    move-exception v0

    goto/16 :goto_d

    :cond_9
    move-object/from16 v19, v2

    move/from16 v17, v8

    move-object/from16 v18, v9

    move/from16 p2, v10

    move/from16 v16, v11

    :goto_8
    move/from16 v10, p2

    move/from16 v11, v16

    move/from16 v8, v17

    move-object/from16 v9, v18

    move-object/from16 v2, v19

    goto/16 :goto_5

    :cond_a
    move-object/from16 v19, v2

    move/from16 v17, v8

    move-object/from16 v18, v9

    move/from16 p2, v10

    move/from16 v16, v11

    .line 281
    iget-object v2, v4, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;->backfillCandidates:Ljava/util/ArrayList;

    new-instance v6, Lcom/exteragram/messenger/feed/FeedTimelineLoader$$ExternalSyntheticLambda0;

    invoke-direct {v6}, Lcom/exteragram/messenger/feed/FeedTimelineLoader$$ExternalSyntheticLambda0;-><init>()V

    invoke-virtual {v2, v6}, Ljava/util/ArrayList;->sort(Ljava/util/Comparator;)V

    const v2, 0x7fffffff

    if-ne v3, v2, :cond_b

    return-object v4

    :cond_b
    if-eqz v16, :cond_c

    .line 286
    invoke-direct {v0, v5, v1, v3}, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->findUnreadBoundary(Lorg/telegram/messenger/MessagesStorage;Ljava/util/ArrayList;I)Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;

    move-result-object v1

    :goto_9
    move-object v8, v1

    move-object v1, v5

    goto :goto_a

    :cond_c
    const/4 v1, 0x0

    goto :goto_9

    .line 287
    :goto_a
    new-instance v5, Ljava/util/ArrayList;

    invoke-direct {v5}, Ljava/util/ArrayList;-><init>()V

    .line 288
    new-instance v6, Ljava/util/ArrayList;

    invoke-direct {v6}, Ljava/util/ArrayList;-><init>()V

    move/from16 v9, p2

    move-object/from16 v2, v19

    .line 291
    :cond_d
    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->loadChunk(Lorg/telegram/messenger/MessagesStorage;Ljava/lang/String;ILcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;Ljava/util/ArrayList;Ljava/util/ArrayList;)I

    move-result v10

    iput v10, v4, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;->lastChunkRowCount:I

    add-int/2addr v9, v10

    const/16 v11, 0x1e

    if-lt v10, v11, :cond_f

    if-eqz v8, :cond_f

    const/16 v10, 0xc8

    if-lt v9, v10, :cond_e

    goto :goto_b

    .line 296
    :cond_e
    iget-object v10, v4, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;->last:Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;

    invoke-static {v10, v8}, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->compareDesc(Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;Lcom/exteragram/messenger/feed/FeedTimelineLoader$Cursor;)I

    move-result v10

    if-ltz v10, :cond_d

    .line 300
    :cond_f
    :goto_b
    invoke-direct {v0, v1, v4, v5, v6}, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->completeTrailingAlbum(Lorg/telegram/messenger/MessagesStorage;Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;Ljava/util/ArrayList;Ljava/util/ArrayList;)V

    .line 301
    invoke-virtual/range {v18 .. v18}, Ljava/util/ArrayList;->size()I

    move-result v0

    move/from16 v10, p2

    :goto_c
    if-ge v10, v0, :cond_11

    move-object/from16 v2, v18

    invoke-virtual {v2, v10}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    add-int/lit8 v10, v10, 0x1

    check-cast v3, Ljava/lang/Long;

    invoke-virtual {v3}, Ljava/lang/Long;->longValue()J

    move-result-wide v8

    neg-long v8, v8

    .line 302
    invoke-static {v8, v9}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    invoke-virtual {v6, v3}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_10

    .line 303
    invoke-static {v8, v9}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    invoke-virtual {v6, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_10
    move-object/from16 v18, v2

    goto :goto_c

    .line 306
    :cond_11
    invoke-virtual {v5}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_12

    .line 307
    iget-object v0, v4, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;->users:Ljava/util/ArrayList;

    invoke-virtual {v1, v5, v0}, Lorg/telegram/messenger/MessagesStorage;->getUsersInternal(Ljava/util/ArrayList;Ljava/util/ArrayList;)V

    .line 309
    :cond_12
    invoke-virtual {v6}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_13

    .line 310
    invoke-static {v7, v6}, Landroid/text/TextUtils;->join(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;

    move-result-object v0

    iget-object v2, v4, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;->chats:Ljava/util/ArrayList;

    invoke-virtual {v1, v0, v2}, Lorg/telegram/messenger/MessagesStorage;->getChatsInternal(Ljava/lang/String;Ljava/util/ArrayList;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_e

    .line 313
    :goto_d
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    move/from16 v1, v17

    .line 314
    iput-boolean v1, v4, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;->failed:Z

    .line 316
    :cond_13
    :goto_e
    iget-object v0, v4, Lcom/exteragram/messenger/feed/FeedTimelineLoader$OlderPage;->messages:Ljava/util/ArrayList;

    invoke-static {v0}, Lcom/exteragram/messenger/feed/FeedTimelineLoader;->clusterGroupedMessages(Ljava/util/ArrayList;)V

    return-object v4
.end method
