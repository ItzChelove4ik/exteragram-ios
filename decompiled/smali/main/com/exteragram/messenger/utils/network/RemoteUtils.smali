.class public abstract Lcom/exteragram/messenger/utils/network/RemoteUtils;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final CONFIG_REFRESH_INTERVAL:J

.field private static final CONFIG_RETRY_INTERVAL:J

.field private static final MESSAGES_REQUEST_TIMEOUT:J

.field private static final fetchInProgress:Ljava/util/concurrent/atomic/AtomicBoolean;

.field private static volatile lastFailedFetchTime:J

.field private static messagesRequestGeneration:I

.field private static final messagesRequestLock:Ljava/lang/Object;

.field private static messagesTimeoutRunnable:Ljava/lang/Runnable;

.field private static pendingMessagesCallbacks:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lorg/telegram/messenger/Utilities$Callback2<",
            "Lorg/telegram/tgnet/TLRPC$messages_Messages;",
            "Lorg/telegram/tgnet/TLRPC$TL_error;",
            ">;>;"
        }
    .end annotation
.end field

.field public static sharedPreferences:Landroid/content/SharedPreferences;


# direct methods
.method public static synthetic $r8$lambda$0p9UTAdmiW_DBagD4pp-dMeLYUk(Ljava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/messenger/Utilities$Callback2;)V
    .locals 2

    const/4 v0, 0x0

    const/4 v1, 0x1

    .line 343
    invoke-virtual {p0, v0, v1}, Ljava/util/concurrent/atomic/AtomicBoolean;->compareAndSet(ZZ)Z

    move-result p0

    if-eqz p0, :cond_0

    .line 344
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_error;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_error;-><init>()V

    const/16 v0, 0x198

    .line 345
    iput v0, p0, Lorg/telegram/tgnet/TLRPC$TL_error;->code:I

    .line 346
    const-string v0, "REQUEST_TIMEOUT"

    iput-object v0, p0, Lorg/telegram/tgnet/TLRPC$TL_error;->text:Ljava/lang/String;

    const/4 v0, 0x0

    .line 347
    invoke-interface {p1, v0, p0}, Lorg/telegram/messenger/Utilities$Callback2;->run(Ljava/lang/Object;Ljava/lang/Object;)V

    :cond_0
    return-void
.end method

.method public static synthetic $r8$lambda$3SpRrpMm50vpRClqPPGUprUYYYw(Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;Ljava/lang/Runnable;ILorg/telegram/tgnet/TLRPC$Chat;)V
    .locals 4

    if-eqz p3, :cond_0

    .line 285
    iget-wide v0, p3, Lorg/telegram/tgnet/TLRPC$Chat;->id:J

    const-wide v2, 0x84c3e8bbL

    cmp-long v0, v0, v2

    if-nez v0, :cond_0

    .line 286
    new-instance p2, Lorg/telegram/tgnet/TLRPC$TL_inputPeerChannel;

    invoke-direct {p2}, Lorg/telegram/tgnet/TLRPC$TL_inputPeerChannel;-><init>()V

    iput-object p2, p0, Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;->peer:Lorg/telegram/tgnet/TLRPC$InputPeer;

    .line 287
    iget-wide v0, p3, Lorg/telegram/tgnet/TLRPC$Chat;->id:J

    iput-wide v0, p2, Lorg/telegram/tgnet/TLRPC$InputPeer;->channel_id:J

    .line 288
    iget-wide v0, p3, Lorg/telegram/tgnet/TLRPC$Chat;->access_hash:J

    iput-wide v0, p2, Lorg/telegram/tgnet/TLRPC$InputPeer;->access_hash:J

    .line 289
    invoke-interface {p1}, Ljava/lang/Runnable;->run()V

    return-void

    .line 291
    :cond_0
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_error;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_error;-><init>()V

    const/16 p1, 0x190

    .line 292
    iput p1, p0, Lorg/telegram/tgnet/TLRPC$TL_error;->code:I

    .line 293
    const-string p1, "CHANNEL_RESOLVE_FAILED"

    iput-object p1, p0, Lorg/telegram/tgnet/TLRPC$TL_error;->text:Ljava/lang/String;

    const/4 p1, 0x0

    .line 294
    invoke-static {p2, p1, p0}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->deliverMessagesResult(ILorg/telegram/tgnet/TLRPC$messages_Messages;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method

.method public static synthetic $r8$lambda$4QYm8KFjMBfJJ4laGtinXysTpck(Ljava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/messenger/AccountInstance;Ljava/util/concurrent/atomic/AtomicInteger;Ljava/util/concurrent/atomic/AtomicInteger;Ljava/lang/Runnable;Ljava/util/concurrent/atomic/AtomicReference;ILjava/lang/Runnable;)V
    .locals 0

    .line 371
    invoke-virtual {p0}, Ljava/util/concurrent/atomic/AtomicBoolean;->get()Z

    move-result p0

    if-eqz p0, :cond_0

    return-void

    .line 374
    :cond_0
    invoke-virtual {p1}, Lorg/telegram/messenger/AccountInstance;->getConnectionsManager()Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object p0

    invoke-virtual {p2}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result p1

    const/4 p2, 0x0

    invoke-virtual {p0, p1, p2}, Lorg/telegram/tgnet/ConnectionsManager;->cancelRequest(IZ)V

    .line 375
    invoke-virtual {p3}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result p0

    const/4 p1, 0x3

    if-ge p0, p1, :cond_1

    .line 376
    invoke-static {p4}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    .line 377
    invoke-virtual {p5}, Ljava/util/concurrent/atomic/AtomicReference;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Runnable;

    int-to-long p1, p6

    invoke-static {p0, p1, p2}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    return-void

    .line 379
    :cond_1
    invoke-interface {p7}, Ljava/lang/Runnable;->run()V

    return-void
.end method

.method public static synthetic $r8$lambda$6r48Eh4Wsu_QezTRhT-68nEzoPU()V
    .locals 0

    .line 0
    return-void
.end method

.method public static synthetic $r8$lambda$84O1WLvUC3qDyZTPzn_dgZu6QAQ(Lorg/telegram/tgnet/TLRPC$TL_messages_search;Ljava/lang/Runnable;Ljava/util/concurrent/atomic/AtomicReference;ILjava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/messenger/Utilities$Callback2;Lorg/telegram/tgnet/TLRPC$Chat;)V
    .locals 4

    if-eqz p6, :cond_0

    .line 391
    iget-wide v0, p6, Lorg/telegram/tgnet/TLRPC$Chat;->id:J

    const-wide v2, 0x84c3e8bbL

    cmp-long v0, v0, v2

    if-nez v0, :cond_0

    .line 392
    new-instance p4, Lorg/telegram/tgnet/TLRPC$TL_inputPeerChannel;

    invoke-direct {p4}, Lorg/telegram/tgnet/TLRPC$TL_inputPeerChannel;-><init>()V

    iput-object p4, p0, Lorg/telegram/tgnet/TLRPC$TL_messages_search;->peer:Lorg/telegram/tgnet/TLRPC$InputPeer;

    .line 393
    iget-wide v0, p6, Lorg/telegram/tgnet/TLRPC$Chat;->id:J

    iput-wide v0, p4, Lorg/telegram/tgnet/TLRPC$InputPeer;->channel_id:J

    .line 394
    iget-wide p5, p6, Lorg/telegram/tgnet/TLRPC$Chat;->access_hash:J

    iput-wide p5, p4, Lorg/telegram/tgnet/TLRPC$InputPeer;->access_hash:J

    .line 395
    invoke-interface {p1}, Ljava/lang/Runnable;->run()V

    .line 396
    invoke-virtual {p2}, Ljava/util/concurrent/atomic/AtomicReference;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Runnable;

    int-to-long p1, p3

    invoke-static {p0, p1, p2}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    return-void

    :cond_0
    const/4 p0, 0x0

    const/4 p1, 0x1

    .line 398
    invoke-virtual {p4, p0, p1}, Ljava/util/concurrent/atomic/AtomicBoolean;->compareAndSet(ZZ)Z

    move-result p0

    if-eqz p0, :cond_1

    .line 399
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_error;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_error;-><init>()V

    const/16 p1, 0x190

    .line 400
    iput p1, p0, Lorg/telegram/tgnet/TLRPC$TL_error;->code:I

    .line 401
    const-string p1, "CHANNEL_RESOLVE_FAILED"

    iput-object p1, p0, Lorg/telegram/tgnet/TLRPC$TL_error;->text:Ljava/lang/String;

    const/4 p1, 0x0

    .line 402
    invoke-interface {p5, p1, p0}, Lorg/telegram/messenger/Utilities$Callback2;->run(Ljava/lang/Object;Ljava/lang/Object;)V

    :cond_1
    return-void
.end method

.method public static synthetic $r8$lambda$E3fegTDk-h-tibGgJJ4Zwo5Ymw0(Lorg/telegram/messenger/AccountInstance;Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;I)V
    .locals 1

    .line 269
    invoke-virtual {p0}, Lorg/telegram/messenger/AccountInstance;->getConnectionsManager()Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object p0

    new-instance v0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda12;

    invoke-direct {v0, p2}, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda12;-><init>(I)V

    invoke-virtual {p0, p1, v0}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequest(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/RequestDelegate;)I

    return-void
.end method

.method public static synthetic $r8$lambda$LmfFRuJdEdEVAug9vbi59pl5km0(Lorg/telegram/tgnet/TLRPC$messages_Messages;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 9

    .line 117
    sget-object v0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->fetchInProgress:Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V

    if-nez p1, :cond_7

    if-nez p0, :cond_0

    goto/16 :goto_3

    .line 123
    :cond_0
    new-instance p1, Ljava/util/HashSet;

    invoke-direct {p1}, Ljava/util/HashSet;-><init>()V

    .line 126
    iget-object v0, p0, Lorg/telegram/tgnet/TLRPC$messages_Messages;->messages:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v2, 0x1

    sub-int/2addr v0, v2

    move v3, v1

    :goto_0
    if-ltz v0, :cond_4

    .line 127
    iget-object v4, p0, Lorg/telegram/tgnet/TLRPC$messages_Messages;->messages:Ljava/util/ArrayList;

    invoke-virtual {v4, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lorg/telegram/tgnet/TLRPC$Message;

    .line 128
    instance-of v5, v4, Lorg/telegram/tgnet/TLRPC$TL_message;

    if-eqz v5, :cond_3

    .line 129
    iget-object v5, v4, Lorg/telegram/tgnet/TLRPC$Message;->message:Ljava/lang/String;

    const-string/jumbo v6, "remote_config"

    invoke-virtual {v5, v6}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 131
    iget-object v3, v4, Lorg/telegram/tgnet/TLRPC$Message;->message:Ljava/lang/String;

    const-string v4, "\n"

    invoke-virtual {v3, v4}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v3

    .line 132
    array-length v4, v3

    if-le v4, v2, :cond_2

    .line 133
    array-length v4, v3

    move v5, v1

    :goto_1
    if-ge v5, v4, :cond_2

    aget-object v6, v3, v5

    .line 134
    const-string v7, "="

    const/4 v8, 0x2

    invoke-virtual {v6, v7, v8}, Ljava/lang/String;->split(Ljava/lang/String;I)[Ljava/lang/String;

    move-result-object v6

    .line 135
    array-length v7, v6

    if-ne v7, v8, :cond_1

    .line 136
    aget-object v7, v6, v1

    invoke-virtual {v7}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v7

    .line 137
    aget-object v6, v6, v2

    invoke-virtual {v6}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v6

    .line 138
    const-string/jumbo v8, "null"

    invoke-virtual {v6, v8}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v8

    if-nez v8, :cond_1

    .line 139
    invoke-static {v7, v6}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->updateValue(Ljava/lang/String;Ljava/lang/String;)V

    .line 140
    invoke-interface {p1, v7}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    :cond_1
    add-int/lit8 v5, v5, 0x1

    goto :goto_1

    :cond_2
    move v3, v2

    :cond_3
    add-int/lit8 v0, v0, -0x1

    goto :goto_0

    :cond_4
    if-eqz v3, :cond_6

    .line 149
    invoke-interface {p1}, Ljava/util/Set;->isEmpty()Z

    move-result p0

    if-eqz p0, :cond_5

    goto :goto_2

    :cond_5
    const-wide/16 v0, 0x0

    .line 154
    sput-wide v0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->lastFailedFetchTime:J

    .line 155
    sget-object p0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->sharedPreferences:Landroid/content/SharedPreferences;

    invoke-interface {p0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    const-string v0, "__last_fetch_attempt_time"

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    invoke-interface {p0, v0, v1, v2}, Landroid/content/SharedPreferences$Editor;->putLong(Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 156
    invoke-static {p1}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->removeOldPreferences(Ljava/util/Set;)V

    return-void

    .line 150
    :cond_6
    :goto_2
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p0

    sput-wide p0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->lastFailedFetchTime:J

    return-void

    .line 119
    :cond_7
    :goto_3
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p0

    sput-wide p0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->lastFailedFetchTime:J

    return-void
.end method

.method public static synthetic $r8$lambda$OnWrYNguU3DJfMISnu8tl4pwRVk(I)V
    .locals 2

    .line 247
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_error;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_error;-><init>()V

    const/16 v1, 0x198

    .line 248
    iput v1, v0, Lorg/telegram/tgnet/TLRPC$TL_error;->code:I

    .line 249
    const-string v1, "REQUEST_TIMEOUT"

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$TL_error;->text:Ljava/lang/String;

    const/4 v1, 0x0

    .line 250
    invoke-static {p0, v1, v0}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->deliverMessagesResult(ILorg/telegram/tgnet/TLRPC$messages_Messages;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method

.method public static synthetic $r8$lambda$Q2nxzL4QXD-hawk8oMmFpxL7pOE(Lorg/telegram/tgnet/TLRPC$TL_messages_search;Lorg/telegram/messenger/AccountInstance;Ljava/lang/Runnable;Ljava/util/concurrent/atomic/AtomicReference;ILjava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/messenger/Utilities$Callback2;)V
    .locals 7

    .line 384
    invoke-virtual {p1}, Lorg/telegram/messenger/AccountInstance;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object p1

    const-wide v0, -0x84c3e8bbL

    invoke-virtual {p1, v0, v1}, Lorg/telegram/messenger/MessagesController;->getInputPeer(J)Lorg/telegram/tgnet/TLRPC$InputPeer;

    move-result-object p1

    iput-object p1, p0, Lorg/telegram/tgnet/TLRPC$TL_messages_search;->peer:Lorg/telegram/tgnet/TLRPC$InputPeer;

    .line 385
    iget-wide v0, p1, Lorg/telegram/tgnet/TLRPC$InputPeer;->access_hash:J

    const-wide/16 v2, 0x0

    cmp-long p1, v0, v2

    if-eqz p1, :cond_0

    .line 386
    invoke-interface {p2}, Ljava/lang/Runnable;->run()V

    .line 387
    invoke-virtual {p3}, Ljava/util/concurrent/atomic/AtomicReference;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Runnable;

    int-to-long p1, p4

    invoke-static {p0, p1, p2}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    return-void

    .line 390
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/utils/chats/ChatUtils;->getInstance()Lcom/exteragram/messenger/utils/chats/ChatUtils;

    move-result-object p1

    new-instance v0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda7;

    move-object v1, p0

    move-object v2, p2

    move-object v3, p3

    move v4, p4

    move-object v5, p5

    move-object v6, p6

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda7;-><init>(Lorg/telegram/tgnet/TLRPC$TL_messages_search;Ljava/lang/Runnable;Ljava/util/concurrent/atomic/AtomicReference;ILjava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/messenger/Utilities$Callback2;)V

    const-string p0, "XS6GEcz5ZXMu82UvXQc"

    invoke-virtual {p1, p0, v0}, Lcom/exteragram/messenger/utils/chats/ChatUtils;->resolveChannel(Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;)V

    return-void
.end method

.method public static synthetic $r8$lambda$UEIegV2IjuUzBjubE_kaeXJS8OE(Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;Lorg/telegram/messenger/AccountInstance;Ljava/lang/Runnable;I)V
    .locals 4

    .line 279
    invoke-virtual {p1}, Lorg/telegram/messenger/AccountInstance;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object p1

    const-wide v0, -0x84c3e8bbL

    invoke-virtual {p1, v0, v1}, Lorg/telegram/messenger/MessagesController;->getInputPeer(J)Lorg/telegram/tgnet/TLRPC$InputPeer;

    move-result-object p1

    iput-object p1, p0, Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;->peer:Lorg/telegram/tgnet/TLRPC$InputPeer;

    .line 280
    iget-wide v0, p1, Lorg/telegram/tgnet/TLRPC$InputPeer;->access_hash:J

    const-wide/16 v2, 0x0

    cmp-long p1, v0, v2

    if-eqz p1, :cond_0

    .line 281
    invoke-interface {p2}, Ljava/lang/Runnable;->run()V

    return-void

    .line 284
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/utils/chats/ChatUtils;->getInstance()Lcom/exteragram/messenger/utils/chats/ChatUtils;

    move-result-object p1

    new-instance v0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda11;

    invoke-direct {v0, p0, p2, p3}, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda11;-><init>(Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;Ljava/lang/Runnable;I)V

    const-string p0, "XS6GEcz5ZXMu82UvXQc"

    invoke-virtual {p1, p0, v0}, Lcom/exteragram/messenger/utils/chats/ChatUtils;->resolveChannel(Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;)V

    return-void
.end method

.method public static synthetic $r8$lambda$VPbxsBgeVDZRlr6n6eb9kvQGVxM(Ljava/util/concurrent/atomic/AtomicBoolean;Ljava/util/concurrent/atomic/AtomicReference;Lorg/telegram/messenger/Utilities$Callback2;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 2

    const/4 v0, 0x0

    const/4 v1, 0x1

    .line 357
    invoke-virtual {p0, v0, v1}, Ljava/util/concurrent/atomic/AtomicBoolean;->compareAndSet(ZZ)Z

    move-result p0

    if-nez p0, :cond_0

    return-void

    .line 360
    :cond_0
    invoke-virtual {p1}, Ljava/util/concurrent/atomic/AtomicReference;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Runnable;

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    const/4 p0, 0x0

    if-nez p4, :cond_2

    if-nez p3, :cond_1

    goto :goto_0

    .line 365
    :cond_1
    check-cast p3, Lorg/telegram/tgnet/TLRPC$messages_Messages;

    .line 366
    invoke-interface {p2, p3, p0}, Lorg/telegram/messenger/Utilities$Callback2;->run(Ljava/lang/Object;Ljava/lang/Object;)V

    return-void

    .line 362
    :cond_2
    :goto_0
    invoke-interface {p2, p0, p4}, Lorg/telegram/messenger/Utilities$Callback2;->run(Ljava/lang/Object;Ljava/lang/Object;)V

    return-void
.end method

.method public static synthetic $r8$lambda$Zys4bse1bgzGUgsT-0MB9ycKDLM(Ljava/util/concurrent/atomic/AtomicBoolean;Ljava/util/concurrent/atomic/AtomicInteger;Ljava/util/concurrent/atomic/AtomicInteger;Lorg/telegram/messenger/AccountInstance;Lorg/telegram/tgnet/TLRPC$TL_messages_search;Ljava/util/concurrent/atomic/AtomicReference;Lorg/telegram/messenger/Utilities$Callback2;)V
    .locals 1

    .line 352
    invoke-virtual {p0}, Ljava/util/concurrent/atomic/AtomicBoolean;->get()Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    .line 355
    :cond_0
    invoke-virtual {p1}, Ljava/util/concurrent/atomic/AtomicInteger;->incrementAndGet()I

    .line 356
    invoke-virtual {p3}, Lorg/telegram/messenger/AccountInstance;->getConnectionsManager()Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object p1

    new-instance p3, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda6;

    invoke-direct {p3, p0, p5, p6}, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda6;-><init>(Ljava/util/concurrent/atomic/AtomicBoolean;Ljava/util/concurrent/atomic/AtomicReference;Lorg/telegram/messenger/Utilities$Callback2;)V

    invoke-virtual {p1, p4, p3}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequest(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/RequestDelegate;)I

    move-result p0

    invoke-virtual {p2, p0}, Ljava/util/concurrent/atomic/AtomicInteger;->set(I)V

    return-void
.end method

.method public static synthetic $r8$lambda$fbLQZhiW34Arj2DTfMUPETN-MPM(ILorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 1

    const/4 v0, 0x0

    if-nez p2, :cond_1

    if-nez p1, :cond_0

    goto :goto_0

    .line 274
    :cond_0
    check-cast p1, Lorg/telegram/tgnet/TLRPC$messages_Messages;

    invoke-static {p0, p1, v0}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->deliverMessagesResult(ILorg/telegram/tgnet/TLRPC$messages_Messages;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void

    .line 271
    :cond_1
    :goto_0
    invoke-static {p0, v0, p2}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->deliverMessagesResult(ILorg/telegram/tgnet/TLRPC$messages_Messages;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 2

    const-wide/16 v0, 0xa

    .line 66
    invoke-static {v0, v1}, Lj$/time/Duration;->ofMinutes(J)Lj$/time/Duration;

    move-result-object v0

    invoke-virtual {v0}, Lj$/time/Duration;->toMillis()J

    move-result-wide v0

    sput-wide v0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->CONFIG_REFRESH_INTERVAL:J

    const-wide/16 v0, 0x1e

    .line 67
    invoke-static {v0, v1}, Lj$/time/Duration;->ofSeconds(J)Lj$/time/Duration;

    move-result-object v0

    invoke-virtual {v0}, Lj$/time/Duration;->toMillis()J

    move-result-wide v0

    sput-wide v0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->CONFIG_RETRY_INTERVAL:J

    const-wide/16 v0, 0x3c

    .line 68
    invoke-static {v0, v1}, Lj$/time/Duration;->ofSeconds(J)Lj$/time/Duration;

    move-result-object v0

    invoke-virtual {v0}, Lj$/time/Duration;->toMillis()J

    move-result-wide v0

    sput-wide v0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->MESSAGES_REQUEST_TIMEOUT:J

    .line 71
    new-instance v0, Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-direct {v0}, Ljava/util/concurrent/atomic/AtomicBoolean;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->fetchInProgress:Ljava/util/concurrent/atomic/AtomicBoolean;

    .line 74
    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->messagesRequestLock:Ljava/lang/Object;

    return-void
.end method

.method private static areValuesEqual(Ljava/lang/Object;Ljava/lang/Object;)Z
    .locals 0

    if-eqz p0, :cond_1

    if-nez p1, :cond_0

    goto :goto_0

    .line 183
    :cond_0
    invoke-virtual {p0, p1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p0

    return p0

    :cond_1
    :goto_0
    if-ne p0, p1, :cond_2

    const/4 p0, 0x1

    return p0

    :cond_2
    const/4 p0, 0x0

    return p0
.end method

.method private static deliverMessagesResult(ILorg/telegram/tgnet/TLRPC$messages_Messages;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 3

    .line 303
    sget-object v0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->messagesRequestLock:Ljava/lang/Object;

    monitor-enter v0

    .line 304
    :try_start_0
    sget v1, Lcom/exteragram/messenger/utils/network/RemoteUtils;->messagesRequestGeneration:I

    if-ne p0, v1, :cond_3

    sget-object p0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->pendingMessagesCallbacks:Ljava/util/ArrayList;

    if-nez p0, :cond_0

    goto :goto_1

    .line 308
    :cond_0
    sget-object v1, Lcom/exteragram/messenger/utils/network/RemoteUtils;->messagesTimeoutRunnable:Ljava/lang/Runnable;

    const/4 v2, 0x0

    .line 309
    sput-object v2, Lcom/exteragram/messenger/utils/network/RemoteUtils;->pendingMessagesCallbacks:Ljava/util/ArrayList;

    .line 310
    sput-object v2, Lcom/exteragram/messenger/utils/network/RemoteUtils;->messagesTimeoutRunnable:Ljava/lang/Runnable;

    .line 311
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v1, :cond_1

    .line 313
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    .line 315
    :cond_1
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v1, 0x0

    :goto_0
    if-ge v1, v0, :cond_2

    invoke-virtual {p0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    add-int/lit8 v1, v1, 0x1

    check-cast v2, Lorg/telegram/messenger/Utilities$Callback2;

    .line 316
    invoke-interface {v2, p1, p2}, Lorg/telegram/messenger/Utilities$Callback2;->run(Ljava/lang/Object;Ljava/lang/Object;)V

    goto :goto_0

    :cond_2
    return-void

    :catchall_0
    move-exception p0

    goto :goto_2

    .line 305
    :cond_3
    :goto_1
    :try_start_1
    monitor-exit v0

    return-void

    .line 311
    :goto_2
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p0
.end method

.method public static forceRefresh()V
    .locals 2

    .line 107
    invoke-static {}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->initCached()V

    const-wide/16 v0, 0x0

    .line 108
    sput-wide v0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->lastFailedFetchTime:J

    .line 109
    invoke-static {}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->loadConfig()V

    return-void
.end method

.method public static getBooleanConfigValue(Ljava/lang/String;Z)Ljava/lang/Boolean;
    .locals 3

    .line 471
    :try_start_0
    invoke-static {}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->getPrefs()Landroid/content/SharedPreferences;

    move-result-object v0

    if-nez v0, :cond_0

    .line 473
    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception v0

    goto :goto_1

    .line 476
    :cond_0
    :try_start_1
    invoke-interface {v0, p0, p1}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v1

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0
    :try_end_1
    .catch Ljava/lang/ClassCastException; {:try_start_1 .. :try_end_1} :catch_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    return-object p0

    .line 478
    :catch_1
    :try_start_2
    invoke-interface {v0}, Landroid/content/SharedPreferences;->getAll()Ljava/util/Map;

    move-result-object v0

    invoke-interface {v0, p0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 479
    instance-of v1, v0, Ljava/lang/String;

    if-eqz v1, :cond_1

    check-cast v0, Ljava/lang/String;

    invoke-static {v0}, Ljava/lang/Boolean;->parseBoolean(Ljava/lang/String;)Z

    move-result v0

    goto :goto_0

    :cond_1
    move v0, p1

    :goto_0
    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    return-object p0

    .line 482
    :goto_1
    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Error getting value for key: "

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, v0}, Lcom/exteragram/messenger/utils/AppUtils;->log(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 484
    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0

    return-object p0
.end method

.method public static getFloatConfigValue(Ljava/lang/String;F)Ljava/lang/Float;
    .locals 3

    .line 449
    :try_start_0
    invoke-static {}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->getPrefs()Landroid/content/SharedPreferences;

    move-result-object v0

    if-nez v0, :cond_0

    .line 451
    invoke-static {p1}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object p0

    return-object p0

    :catch_0
    move-exception v0

    goto :goto_0

    .line 453
    :cond_0
    invoke-interface {v0}, Landroid/content/SharedPreferences;->getAll()Ljava/util/Map;

    move-result-object v0

    invoke-interface {v0, p0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 454
    instance-of v1, v0, Ljava/lang/String;

    if-eqz v1, :cond_1

    .line 455
    check-cast v0, Ljava/lang/String;

    invoke-static {v0}, Ljava/lang/Float;->parseFloat(Ljava/lang/String;)F

    move-result v0

    invoke-static {v0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object p0

    return-object p0

    .line 456
    :cond_1
    instance-of v1, v0, Ljava/lang/Float;

    if-eqz v1, :cond_2

    .line 457
    check-cast v0, Ljava/lang/Float;

    return-object v0

    .line 458
    :cond_2
    instance-of v1, v0, Ljava/lang/Long;

    if-eqz v1, :cond_3

    .line 459
    check-cast v0, Ljava/lang/Long;

    invoke-virtual {v0}, Ljava/lang/Long;->floatValue()F

    move-result v0

    invoke-static {v0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object p0

    return-object p0

    .line 460
    :cond_3
    instance-of v1, v0, Ljava/lang/Integer;

    if-eqz v1, :cond_4

    .line 461
    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->floatValue()F

    move-result v0

    invoke-static {v0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    .line 464
    :goto_0
    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Error getting value for key: "

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, v0}, Lcom/exteragram/messenger/utils/AppUtils;->log(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 466
    :cond_4
    invoke-static {p1}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object p0

    return-object p0
.end method

.method public static getIntConfigValue(Ljava/lang/String;I)Ljava/lang/Integer;
    .locals 3

    .line 411
    :try_start_0
    invoke-static {}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->getPrefs()Landroid/content/SharedPreferences;

    move-result-object v0

    if-nez v0, :cond_0

    .line 413
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    return-object p0

    :catch_0
    move-exception v0

    goto :goto_0

    .line 415
    :cond_0
    invoke-interface {v0}, Landroid/content/SharedPreferences;->getAll()Ljava/util/Map;

    move-result-object v0

    invoke-interface {v0, p0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 416
    instance-of v1, v0, Ljava/lang/String;

    if-eqz v1, :cond_1

    .line 417
    check-cast v0, Ljava/lang/String;

    invoke-static {v0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    return-object p0

    .line 418
    :cond_1
    instance-of v1, v0, Ljava/lang/Long;

    if-eqz v1, :cond_2

    .line 419
    check-cast v0, Ljava/lang/Long;

    invoke-virtual {v0}, Ljava/lang/Long;->intValue()I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    return-object p0

    .line 420
    :cond_2
    instance-of v1, v0, Ljava/lang/Integer;

    if-eqz v1, :cond_3

    .line 421
    check-cast v0, Ljava/lang/Integer;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    .line 424
    :goto_0
    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Error getting int config value for key: "

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, v0}, Lcom/exteragram/messenger/utils/AppUtils;->log(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 426
    :cond_3
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    return-object p0
.end method

.method public static getMessages(Lorg/telegram/messenger/Utilities$Callback2;)V
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/telegram/messenger/Utilities$Callback2<",
            "Lorg/telegram/tgnet/TLRPC$messages_Messages;",
            "Lorg/telegram/tgnet/TLRPC$TL_error;",
            ">;)V"
        }
    .end annotation

    .line 238
    sget-object v0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->messagesRequestLock:Ljava/lang/Object;

    monitor-enter v0

    .line 239
    :try_start_0
    sget-object v1, Lcom/exteragram/messenger/utils/network/RemoteUtils;->pendingMessagesCallbacks:Ljava/util/ArrayList;

    if-eqz v1, :cond_0

    .line 240
    invoke-virtual {v1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 241
    monitor-exit v0

    return-void

    :catchall_0
    move-exception p0

    goto :goto_0

    .line 243
    :cond_0
    sget v1, Lcom/exteragram/messenger/utils/network/RemoteUtils;->messagesRequestGeneration:I

    add-int/lit8 v1, v1, 0x1

    sput v1, Lcom/exteragram/messenger/utils/network/RemoteUtils;->messagesRequestGeneration:I

    .line 244
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    sput-object v2, Lcom/exteragram/messenger/utils/network/RemoteUtils;->pendingMessagesCallbacks:Ljava/util/ArrayList;

    .line 245
    invoke-virtual {v2, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 246
    new-instance p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda8;

    invoke-direct {p0, v1}, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda8;-><init>(I)V

    sput-object p0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->messagesTimeoutRunnable:Ljava/lang/Runnable;

    .line 252
    sget-wide v2, Lcom/exteragram/messenger/utils/network/RemoteUtils;->MESSAGES_REQUEST_TIMEOUT:J

    invoke-static {p0, v2, v3}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    .line 253
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 255
    sget-object p0, Lorg/telegram/messenger/ApplicationLoader;->applicationHandler:Landroid/os/Handler;

    if-nez p0, :cond_1

    .line 256
    new-instance p0, Lorg/telegram/tgnet/TLRPC$TL_error;

    invoke-direct {p0}, Lorg/telegram/tgnet/TLRPC$TL_error;-><init>()V

    const/16 v0, 0x1f7

    .line 257
    iput v0, p0, Lorg/telegram/tgnet/TLRPC$TL_error;->code:I

    .line 258
    const-string v0, "APP_NOT_READY"

    iput-object v0, p0, Lorg/telegram/tgnet/TLRPC$TL_error;->text:Ljava/lang/String;

    const/4 v0, 0x0

    .line 259
    invoke-static {v1, v0, p0}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->deliverMessagesResult(ILorg/telegram/tgnet/TLRPC$messages_Messages;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void

    .line 263
    :cond_1
    sget p0, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {p0}, Lorg/telegram/messenger/AccountInstance;->getInstance(I)Lorg/telegram/messenger/AccountInstance;

    move-result-object p0

    .line 264
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;-><init>()V

    const/4 v2, 0x0

    .line 265
    iput v2, v0, Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;->offset_id:I

    const/16 v2, 0x64

    .line 266
    iput v2, v0, Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;->limit:I

    .line 268
    new-instance v2, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda9;

    invoke-direct {v2, p0, v0, v1}, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda9;-><init>(Lorg/telegram/messenger/AccountInstance;Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;I)V

    .line 278
    new-instance v3, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda10;

    invoke-direct {v3, v0, p0, v2, v1}, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda10;-><init>(Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;Lorg/telegram/messenger/AccountInstance;Ljava/lang/Runnable;I)V

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void

    .line 253
    :goto_0
    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p0
.end method

.method private static getPrefs()Landroid/content/SharedPreferences;
    .locals 1

    .line 81
    sget-object v0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->sharedPreferences:Landroid/content/SharedPreferences;

    if-nez v0, :cond_0

    .line 82
    invoke-static {}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->initCached()V

    .line 84
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->sharedPreferences:Landroid/content/SharedPreferences;

    return-object v0
.end method

.method public static getStringConfigValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 3

    .line 509
    :try_start_0
    invoke-static {}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->getPrefs()Landroid/content/SharedPreferences;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 513
    :cond_0
    invoke-interface {v0}, Landroid/content/SharedPreferences;->getAll()Ljava/util/Map;

    move-result-object v0

    invoke-interface {v0, p0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 515
    invoke-static {v0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception v0

    goto :goto_1

    :cond_1
    :goto_0
    return-object p1

    .line 518
    :goto_1
    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Error getting value for key: "

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, v0}, Lcom/exteragram/messenger/utils/AppUtils;->log(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-object p1
.end method

.method public static getStringSetConfigValue(Ljava/lang/String;Ljava/util/Set;)Ljava/util/Set;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/Set<",
            "Ljava/lang/String;",
            ">;)",
            "Ljava/util/Set<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .line 489
    :try_start_0
    invoke-static {}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->getPrefs()Landroid/content/SharedPreferences;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 493
    :cond_0
    invoke-interface {v0}, Landroid/content/SharedPreferences;->getAll()Ljava/util/Map;

    move-result-object v0

    invoke-interface {v0, p0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 494
    instance-of v1, v0, Ljava/util/Set;

    if-eqz v1, :cond_1

    .line 496
    check-cast v0, Ljava/util/Set;

    return-object v0

    :catch_0
    move-exception v0

    goto :goto_1

    .line 497
    :cond_1
    instance-of v1, v0, Ljava/lang/String;

    if-eqz v1, :cond_2

    .line 498
    check-cast v0, Ljava/lang/String;

    const-string v1, ",\\s*"

    invoke-virtual {v0, v1}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v0

    .line 499
    new-instance v1, Ljava/util/HashSet;

    invoke-static {v0}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v0

    invoke-direct {v1, v0}, Ljava/util/HashSet;-><init>(Ljava/util/Collection;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object v1

    :cond_2
    :goto_0
    return-object p1

    .line 502
    :goto_1
    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Error getting value for key: "

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, v0}, Lcom/exteragram/messenger/utils/AppUtils;->log(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-object p1
.end method

.method public static init()V
    .locals 8

    .line 94
    invoke-static {}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->initCached()V

    .line 95
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    .line 96
    sget-object v2, Lcom/exteragram/messenger/utils/network/RemoteUtils;->sharedPreferences:Landroid/content/SharedPreferences;

    const-string v3, "__last_fetch_attempt_time"

    const-wide/16 v4, 0x0

    invoke-interface {v2, v3, v4, v5}, Landroid/content/SharedPreferences;->getLong(Ljava/lang/String;J)J

    move-result-wide v2

    sub-long v2, v0, v2

    .line 97
    invoke-static {v2, v3}, Ljava/lang/Math;->abs(J)J

    move-result-wide v2

    sget-wide v6, Lcom/exteragram/messenger/utils/network/RemoteUtils;->CONFIG_REFRESH_INTERVAL:J

    cmp-long v2, v2, v6

    if-gez v2, :cond_0

    goto :goto_0

    .line 100
    :cond_0
    sget-wide v2, Lcom/exteragram/messenger/utils/network/RemoteUtils;->lastFailedFetchTime:J

    cmp-long v2, v2, v4

    if-eqz v2, :cond_1

    sget-wide v2, Lcom/exteragram/messenger/utils/network/RemoteUtils;->lastFailedFetchTime:J

    sub-long/2addr v0, v2

    invoke-static {v0, v1}, Ljava/lang/Math;->abs(J)J

    move-result-wide v0

    sget-wide v2, Lcom/exteragram/messenger/utils/network/RemoteUtils;->CONFIG_RETRY_INTERVAL:J

    cmp-long v0, v0, v2

    if-gez v0, :cond_1

    :goto_0
    return-void

    .line 103
    :cond_1
    invoke-static {}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->loadConfig()V

    return-void
.end method

.method public static initCached()V
    .locals 3

    .line 88
    sget-object v0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->sharedPreferences:Landroid/content/SharedPreferences;

    if-nez v0, :cond_0

    .line 89
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    const-string v1, "exteraremoteconfig"

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->sharedPreferences:Landroid/content/SharedPreferences;

    :cond_0
    return-void
.end method

.method private static loadConfig()V
    .locals 3

    .line 113
    sget-object v0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->fetchInProgress:Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v1, 0x0

    const/4 v2, 0x1

    invoke-virtual {v0, v1, v2}, Ljava/util/concurrent/atomic/AtomicBoolean;->compareAndSet(ZZ)Z

    move-result v0

    if-nez v0, :cond_0

    return-void

    .line 116
    :cond_0
    new-instance v0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda5;

    invoke-direct {v0}, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda5;-><init>()V

    invoke-static {v0}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->getMessages(Lorg/telegram/messenger/Utilities$Callback2;)V

    return-void
.end method

.method private static parseConfigValue(Ljava/lang/String;)Ljava/lang/Object;
    .locals 2

    .line 194
    const-string v0, "-?\\d+"

    invoke-virtual {p0, v0}, Ljava/lang/String;->matches(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 196
    :try_start_0
    invoke-static {p0}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v0

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    .line 198
    :catch_0
    invoke-static {p0}, Ljava/lang/Float;->parseFloat(Ljava/lang/String;)F

    move-result p0

    invoke-static {p0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object p0

    return-object p0

    .line 201
    :cond_0
    const-string v0, "-?\\d+(\\.\\d+)"

    invoke-virtual {p0, v0}, Ljava/lang/String;->matches(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 202
    invoke-static {p0}, Ljava/lang/Float;->parseFloat(Ljava/lang/String;)F

    move-result p0

    invoke-static {p0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object p0

    return-object p0

    .line 204
    :cond_1
    const-string/jumbo v0, "true"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 205
    sget-object p0, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    return-object p0

    .line 207
    :cond_2
    const-string v0, "false"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_3

    .line 208
    sget-object p0, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    return-object p0

    .line 210
    :cond_3
    const-string v0, "["

    invoke-virtual {p0, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_5

    const-string v0, "]"

    invoke-virtual {p0, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_5

    .line 211
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v0

    const/4 v1, 0x1

    sub-int/2addr v0, v1

    invoke-virtual {p0, v1, v0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p0

    .line 212
    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_4

    .line 213
    new-instance p0, Ljava/util/HashSet;

    invoke-direct {p0}, Ljava/util/HashSet;-><init>()V

    return-object p0

    .line 215
    :cond_4
    const-string v0, ",\\s*"

    invoke-virtual {p0, v0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p0

    .line 216
    new-instance v0, Ljava/util/HashSet;

    invoke-static {p0}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/util/HashSet;-><init>(Ljava/util/Collection;)V

    return-object v0

    :cond_5
    return-object p0
.end method

.method private static removeOldPreferences(Ljava/util/Set;)V
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Set<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    .line 161
    sget-object v0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->sharedPreferences:Landroid/content/SharedPreferences;

    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 162
    sget-object v1, Lcom/exteragram/messenger/utils/network/RemoteUtils;->sharedPreferences:Landroid/content/SharedPreferences;

    invoke-interface {v1}, Landroid/content/SharedPreferences;->getAll()Ljava/util/Map;

    move-result-object v1

    .line 163
    invoke-interface {v1}, Ljava/util/Map;->keySet()Ljava/util/Set;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    .line 164
    invoke-interface {p0, v2}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_0

    const-string v3, "__last_fetch_attempt_time"

    invoke-virtual {v3, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_0

    .line 165
    invoke-interface {v0, v2}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    goto :goto_0

    .line 168
    :cond_1
    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method private static saveConfigValueToPreferences(Landroid/content/SharedPreferences$Editor;Ljava/lang/String;Ljava/lang/Object;)V
    .locals 2

    .line 222
    instance-of v0, p2, Ljava/lang/Long;

    if-eqz v0, :cond_0

    .line 223
    check-cast p2, Ljava/lang/Long;

    invoke-virtual {p2}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    invoke-interface {p0, p1, v0, v1}, Landroid/content/SharedPreferences$Editor;->putLong(Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;

    return-void

    .line 224
    :cond_0
    instance-of v0, p2, Ljava/lang/Float;

    if-eqz v0, :cond_1

    .line 225
    check-cast p2, Ljava/lang/Float;

    invoke-virtual {p2}, Ljava/lang/Float;->floatValue()F

    move-result p2

    invoke-interface {p0, p1, p2}, Landroid/content/SharedPreferences$Editor;->putFloat(Ljava/lang/String;F)Landroid/content/SharedPreferences$Editor;

    return-void

    .line 226
    :cond_1
    instance-of v0, p2, Ljava/lang/Boolean;

    if-eqz v0, :cond_2

    .line 227
    check-cast p2, Ljava/lang/Boolean;

    invoke-virtual {p2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p2

    invoke-interface {p0, p1, p2}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    return-void

    .line 228
    :cond_2
    instance-of v0, p2, Ljava/util/Set;

    if-eqz v0, :cond_3

    .line 230
    check-cast p2, Ljava/util/Set;

    invoke-interface {p0, p1, p2}, Landroid/content/SharedPreferences$Editor;->putStringSet(Ljava/lang/String;Ljava/util/Set;)Landroid/content/SharedPreferences$Editor;

    return-void

    .line 231
    :cond_3
    instance-of v0, p2, Ljava/lang/String;

    if-eqz v0, :cond_4

    .line 232
    check-cast p2, Ljava/lang/String;

    invoke-interface {p0, p1, p2}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    :cond_4
    return-void
.end method

.method private static saveToPreferences(Ljava/lang/String;Ljava/lang/String;)V
    .locals 1

    .line 187
    sget-object v0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->sharedPreferences:Landroid/content/SharedPreferences;

    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 188
    invoke-static {p1}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->parseConfigValue(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p1

    .line 189
    invoke-static {v0, p0, p1}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->saveConfigValueToPreferences(Landroid/content/SharedPreferences$Editor;Ljava/lang/String;Ljava/lang/Object;)V

    .line 190
    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    return-void
.end method

.method public static searchMessages(ILjava/lang/String;Lorg/telegram/tgnet/TLRPC$MessagesFilter;Lorg/telegram/messenger/Utilities$Callback2;I)V
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I",
            "Ljava/lang/String;",
            "Lorg/telegram/tgnet/TLRPC$MessagesFilter;",
            "Lorg/telegram/messenger/Utilities$Callback2<",
            "Lorg/telegram/tgnet/TLRPC$messages_Messages;",
            "Lorg/telegram/tgnet/TLRPC$TL_error;",
            ">;I)V"
        }
    .end annotation

    .line 329
    sget v0, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/AccountInstance;->getInstance(I)Lorg/telegram/messenger/AccountInstance;

    move-result-object v3

    .line 331
    new-instance v2, Lorg/telegram/tgnet/TLRPC$TL_messages_search;

    invoke-direct {v2}, Lorg/telegram/tgnet/TLRPC$TL_messages_search;-><init>()V

    .line 332
    iput-object p1, v2, Lorg/telegram/tgnet/TLRPC$TL_messages_search;->q:Ljava/lang/String;

    const/4 p1, 0x0

    .line 333
    iput p1, v2, Lorg/telegram/tgnet/TLRPC$TL_messages_search;->offset_id:I

    .line 334
    iput p0, v2, Lorg/telegram/tgnet/TLRPC$TL_messages_search;->limit:I

    .line 335
    iput-object p2, v2, Lorg/telegram/tgnet/TLRPC$TL_messages_search;->filter:Lorg/telegram/tgnet/TLRPC$MessagesFilter;

    .line 337
    new-instance v7, Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-direct {v7, p1}, Ljava/util/concurrent/atomic/AtomicBoolean;-><init>(Z)V

    .line 338
    new-instance v5, Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-direct {v5}, Ljava/util/concurrent/atomic/AtomicInteger;-><init>()V

    .line 339
    new-instance v4, Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-direct {v4}, Ljava/util/concurrent/atomic/AtomicInteger;-><init>()V

    move-object v6, v2

    move-object v2, v7

    .line 340
    new-instance v7, Ljava/util/concurrent/atomic/AtomicReference;

    new-instance p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda0;

    invoke-direct {p0}, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda0;-><init>()V

    invoke-direct {v7, p0}, Ljava/util/concurrent/atomic/AtomicReference;-><init>(Ljava/lang/Object;)V

    .line 342
    new-instance v9, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda1;

    invoke-direct {v9, v2, p3}, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda1;-><init>(Ljava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/messenger/Utilities$Callback2;)V

    .line 351
    new-instance v1, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda2;

    move-object v8, v5

    move-object v5, v3

    move-object v3, v8

    move-object v8, p3

    invoke-direct/range {v1 .. v8}, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda2;-><init>(Ljava/util/concurrent/atomic/AtomicBoolean;Ljava/util/concurrent/atomic/AtomicInteger;Ljava/util/concurrent/atomic/AtomicInteger;Lorg/telegram/messenger/AccountInstance;Lorg/telegram/tgnet/TLRPC$TL_messages_search;Ljava/util/concurrent/atomic/AtomicReference;Lorg/telegram/messenger/Utilities$Callback2;)V

    move-object p0, v5

    move-object v5, v3

    move-object v3, p0

    move-object p1, v6

    move-object p0, v8

    .line 370
    new-instance p2, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda3;

    move v8, p4

    move-object v6, v1

    move-object v1, p2

    invoke-direct/range {v1 .. v9}, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda3;-><init>(Ljava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/messenger/AccountInstance;Ljava/util/concurrent/atomic/AtomicInteger;Ljava/util/concurrent/atomic/AtomicInteger;Ljava/lang/Runnable;Ljava/util/concurrent/atomic/AtomicReference;ILjava/lang/Runnable;)V

    move-object v1, v6

    move v6, v8

    invoke-virtual {v7, p2}, Ljava/util/concurrent/atomic/AtomicReference;->set(Ljava/lang/Object;)V

    move-object v4, v1

    .line 383
    new-instance v1, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda4;

    move-object v8, p0

    move-object v5, v7

    move-object v7, v2

    move-object v2, p1

    invoke-direct/range {v1 .. v8}, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda4;-><init>(Lorg/telegram/tgnet/TLRPC$TL_messages_search;Lorg/telegram/messenger/AccountInstance;Ljava/lang/Runnable;Ljava/util/concurrent/atomic/AtomicReference;ILjava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/messenger/Utilities$Callback2;)V

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static searchMessages(Ljava/lang/String;Lorg/telegram/tgnet/TLRPC$MessagesFilter;Lorg/telegram/messenger/Utilities$Callback2;I)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Lorg/telegram/tgnet/TLRPC$MessagesFilter;",
            "Lorg/telegram/messenger/Utilities$Callback2<",
            "Lorg/telegram/tgnet/TLRPC$messages_Messages;",
            "Lorg/telegram/tgnet/TLRPC$TL_error;",
            ">;I)V"
        }
    .end annotation

    const/16 v0, 0x32

    .line 321
    invoke-static {v0, p0, p1, p2, p3}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->searchMessages(ILjava/lang/String;Lorg/telegram/tgnet/TLRPC$MessagesFilter;Lorg/telegram/messenger/Utilities$Callback2;I)V

    return-void
.end method

.method private static updateValue(Ljava/lang/String;Ljava/lang/String;)V
    .locals 2

    .line 172
    sget-object v0, Lcom/exteragram/messenger/utils/network/RemoteUtils;->sharedPreferences:Landroid/content/SharedPreferences;

    invoke-interface {v0}, Landroid/content/SharedPreferences;->getAll()Ljava/util/Map;

    move-result-object v0

    invoke-interface {v0, p0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 173
    invoke-static {p1}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->parseConfigValue(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    .line 174
    invoke-static {v0, v1}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->areValuesEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 175
    invoke-static {p0, p1}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->saveToPreferences(Ljava/lang/String;Ljava/lang/String;)V

    :cond_0
    return-void
.end method
