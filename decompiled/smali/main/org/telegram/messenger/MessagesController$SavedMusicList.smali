.class public Lorg/telegram/messenger/MessagesController$SavedMusicList;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/messenger/MessagesController;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "SavedMusicList"
.end annotation


# instance fields
.field public final currentAccount:I

.field public final dialogId:J

.field public endReached:Z

.field public final list:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lorg/telegram/messenger/MessageObject;",
            ">;"
        }
    .end annotation
.end field

.field public loading:Z

.field public totalCount:I


# direct methods
.method public static synthetic $r8$lambda$gH-OEX94Q9gpkf8NKIkQjJbqtIs(Lorg/telegram/messenger/MessagesController$SavedMusicList;Lorg/telegram/tgnet/TLObject;Ljava/util/ArrayList;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/messenger/MessagesController$SavedMusicList;->lambda$load$0(Lorg/telegram/tgnet/TLObject;Ljava/util/ArrayList;)V

    return-void
.end method

.method public static synthetic $r8$lambda$nWSGnKYQNlmzk3fN-EJkDthmq94(Lorg/telegram/messenger/MessagesController$SavedMusicList;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/messenger/MessagesController$SavedMusicList;->lambda$load$1(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method

.method public constructor <init>(IJ)V
    .locals 1

    .line 25190
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 25201
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->list:Ljava/util/ArrayList;

    .line 25191
    iput p1, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->currentAccount:I

    .line 25192
    iput-wide p2, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->dialogId:J

    return-void
.end method

.method private synthetic lambda$load$0(Lorg/telegram/tgnet/TLObject;Ljava/util/ArrayList;)V
    .locals 7

    .line 25236
    instance-of v0, p1, Lorg/telegram/tgnet/TLRPC$TL_savedMusic;

    const/4 v1, 0x0

    if-eqz v0, :cond_3

    .line 25237
    check-cast p1, Lorg/telegram/tgnet/TLRPC$TL_savedMusic;

    .line 25238
    iget v0, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->totalCount:I

    const/4 v2, 0x1

    if-gtz v0, :cond_1

    iget-object v0, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->list:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ne v0, v2, :cond_1

    .line 25239
    iget-object v0, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->list:Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/messenger/MessageObject;

    invoke-virtual {v0}, Lorg/telegram/messenger/MessageObject;->getDocument()Lorg/telegram/tgnet/TLRPC$Document;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {p2}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/messenger/MessageObject;

    invoke-virtual {v0}, Lorg/telegram/messenger/MessageObject;->getDocument()Lorg/telegram/tgnet/TLRPC$Document;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->list:Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/messenger/MessageObject;

    invoke-virtual {v0}, Lorg/telegram/messenger/MessageObject;->getDocument()Lorg/telegram/tgnet/TLRPC$Document;

    move-result-object v0

    iget-wide v3, v0, Lorg/telegram/tgnet/TLRPC$Document;->id:J

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/messenger/MessageObject;

    invoke-virtual {v0}, Lorg/telegram/messenger/MessageObject;->getDocument()Lorg/telegram/tgnet/TLRPC$Document;

    move-result-object v0

    iget-wide v5, v0, Lorg/telegram/tgnet/TLRPC$Document;->id:J

    cmp-long v0, v3, v5

    if-nez v0, :cond_0

    .line 25240
    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    goto :goto_0

    .line 25242
    :cond_0
    iget-object v0, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->list:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 25245
    :cond_1
    :goto_0
    iget p1, p1, Lorg/telegram/tgnet/TLRPC$SavedMusic;->count:I

    iput p1, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->totalCount:I

    .line 25246
    iget-object p1, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->list:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    .line 25247
    iget-object p1, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->list:Ljava/util/ArrayList;

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result p1

    iget p2, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->totalCount:I

    if-lt p1, p2, :cond_2

    goto :goto_1

    :cond_2
    move v2, v1

    :goto_1
    iput-boolean v2, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->endReached:Z

    .line 25250
    :cond_3
    iput-boolean v1, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->loading:Z

    .line 25251
    iget p1, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->currentAccount:I

    invoke-static {p1}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget p2, Lorg/telegram/messenger/NotificationCenter;->musicListLoaded:I

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-virtual {p1, p2, p0}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    return-void
.end method

.method private synthetic lambda$load$1(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 4

    .line 25228
    new-instance p2, Ljava/util/ArrayList;

    invoke-direct {p2}, Ljava/util/ArrayList;-><init>()V

    .line 25229
    instance-of v0, p1, Lorg/telegram/tgnet/TLRPC$TL_savedMusic;

    if-eqz v0, :cond_0

    .line 25230
    move-object v0, p1

    check-cast v0, Lorg/telegram/tgnet/TLRPC$TL_savedMusic;

    .line 25231
    iget-object v0, v0, Lorg/telegram/tgnet/TLRPC$SavedMusic;->documents:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_0

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    add-int/lit8 v2, v2, 0x1

    check-cast v3, Lorg/telegram/tgnet/TLRPC$Document;

    .line 25232
    invoke-virtual {p0, v3}, Lorg/telegram/messenger/MessagesController$SavedMusicList;->toMessageObject(Lorg/telegram/tgnet/TLRPC$Document;)Lorg/telegram/messenger/MessageObject;

    move-result-object v3

    invoke-virtual {p2, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 25235
    :cond_0
    new-instance v0, Lorg/telegram/messenger/MessagesController$SavedMusicList$$ExternalSyntheticLambda1;

    invoke-direct {v0, p0, p1, p2}, Lorg/telegram/messenger/MessagesController$SavedMusicList$$ExternalSyntheticLambda1;-><init>(Lorg/telegram/messenger/MessagesController$SavedMusicList;Lorg/telegram/tgnet/TLObject;Ljava/util/ArrayList;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method


# virtual methods
.method public add(Lorg/telegram/tgnet/TLRPC$Document;)V
    .locals 2

    .line 25272
    iget-object v0, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->list:Ljava/util/ArrayList;

    const/4 v1, 0x0

    invoke-virtual {p0, p1}, Lorg/telegram/messenger/MessagesController$SavedMusicList;->toMessageObject(Lorg/telegram/tgnet/TLRPC$Document;)Lorg/telegram/messenger/MessageObject;

    move-result-object p1

    invoke-virtual {v0, v1, p1}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    .line 25273
    invoke-virtual {p0}, Lorg/telegram/messenger/MessagesController$SavedMusicList;->updateFirstMusic()V

    return-void
.end method

.method public getFirstDocument()Lorg/telegram/tgnet/TLRPC$Document;
    .locals 2

    .line 25257
    iget-object v0, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->list:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    return-object v1

    .line 25258
    :cond_0
    iget-object p0, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->list:Ljava/util/ArrayList;

    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/telegram/messenger/MessageObject;

    if-nez p0, :cond_1

    return-object v1

    .line 25260
    :cond_1
    invoke-virtual {p0}, Lorg/telegram/messenger/MessageObject;->getDocument()Lorg/telegram/tgnet/TLRPC$Document;

    move-result-object p0

    return-object p0
.end method

.method public load()V
    .locals 4

    .line 25219
    iget-boolean v0, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->loading:Z

    if-nez v0, :cond_1

    iget-boolean v0, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->endReached:Z

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x1

    .line 25221
    iput-boolean v0, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->loading:Z

    .line 25223
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_getSavedMusic;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_getSavedMusic;-><init>()V

    .line 25224
    iget v1, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->currentAccount:I

    invoke-static {v1}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v1

    iget-wide v2, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->dialogId:J

    invoke-virtual {v1, v2, v3}, Lorg/telegram/messenger/MessagesController;->getInputUser(J)Lorg/telegram/tgnet/TLRPC$InputUser;

    move-result-object v1

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$TL_getSavedMusic;->id:Lorg/telegram/tgnet/TLRPC$InputUser;

    .line 25225
    iget-object v1, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->list:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$TL_getSavedMusic;->offset:I

    const/16 v1, 0x1e

    .line 25226
    iput v1, v0, Lorg/telegram/tgnet/TLRPC$TL_getSavedMusic;->limit:I

    .line 25227
    iget v1, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->currentAccount:I

    invoke-static {v1}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object v1

    new-instance v2, Lorg/telegram/messenger/MessagesController$SavedMusicList$$ExternalSyntheticLambda0;

    invoke-direct {v2, p0}, Lorg/telegram/messenger/MessagesController$SavedMusicList$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/messenger/MessagesController$SavedMusicList;)V

    invoke-virtual {v1, v0, v2}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequest(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/RequestDelegate;)I

    :cond_1
    :goto_0
    return-void
.end method

.method public move(II)V
    .locals 6

    .line 25277
    invoke-virtual {p0}, Lorg/telegram/messenger/MessagesController$SavedMusicList;->getFirstDocument()Lorg/telegram/tgnet/TLRPC$Document;

    move-result-object v0

    .line 25278
    iget-object v1, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->list:Ljava/util/ArrayList;

    invoke-virtual {v1, p2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lorg/telegram/messenger/MessageObject;

    .line 25279
    iget-object v2, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->list:Ljava/util/ArrayList;

    invoke-virtual {v2, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lorg/telegram/messenger/MessageObject;

    .line 25280
    iget-object v3, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->list:Ljava/util/ArrayList;

    invoke-virtual {v3, p1, v1}, Ljava/util/ArrayList;->set(ILjava/lang/Object;)Ljava/lang/Object;

    .line 25281
    iget-object p1, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->list:Ljava/util/ArrayList;

    invoke-virtual {p1, p2, v2}, Ljava/util/ArrayList;->set(ILjava/lang/Object;)Ljava/lang/Object;

    .line 25282
    invoke-virtual {p0}, Lorg/telegram/messenger/MessagesController$SavedMusicList;->getFirstDocument()Lorg/telegram/tgnet/TLRPC$Document;

    move-result-object p1

    if-eq p1, v0, :cond_0

    .line 25283
    invoke-virtual {p0}, Lorg/telegram/messenger/MessagesController$SavedMusicList;->updateFirstMusic()V

    :cond_0
    const/4 p1, 0x0

    if-nez p2, :cond_1

    move-object p2, p1

    goto :goto_0

    .line 25286
    :cond_1
    iget-object v0, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->list:Ljava/util/ArrayList;

    add-int/lit8 p2, p2, -0x1

    invoke-virtual {v0, p2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lorg/telegram/messenger/MessageObject;

    .line 25288
    :goto_0
    invoke-virtual {v2}, Lorg/telegram/messenger/MessageObject;->getDocument()Lorg/telegram/tgnet/TLRPC$Document;

    move-result-object v0

    if-nez p2, :cond_2

    move-object p2, p1

    goto :goto_1

    .line 25289
    :cond_2
    invoke-virtual {p2}, Lorg/telegram/messenger/MessageObject;->getDocument()Lorg/telegram/tgnet/TLRPC$Document;

    move-result-object p2

    .line 25291
    :goto_1
    new-instance v1, Lorg/telegram/tgnet/TLRPC$TL_account_saveMusic;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLRPC$TL_account_saveMusic;-><init>()V

    if-nez v0, :cond_3

    return-void

    .line 25293
    :cond_3
    new-instance v2, Lorg/telegram/tgnet/TLRPC$TL_inputDocument;

    invoke-direct {v2}, Lorg/telegram/tgnet/TLRPC$TL_inputDocument;-><init>()V

    iput-object v2, v1, Lorg/telegram/tgnet/TLRPC$TL_account_saveMusic;->id:Lorg/telegram/tgnet/TLRPC$InputDocument;

    .line 25294
    iget-wide v3, v0, Lorg/telegram/tgnet/TLRPC$Document;->id:J

    iput-wide v3, v2, Lorg/telegram/tgnet/TLRPC$InputDocument;->id:J

    .line 25295
    iget-wide v3, v0, Lorg/telegram/tgnet/TLRPC$Document;->access_hash:J

    iput-wide v3, v2, Lorg/telegram/tgnet/TLRPC$InputDocument;->access_hash:J

    .line 25296
    iget-object v0, v0, Lorg/telegram/tgnet/TLRPC$Document;->file_reference:[B

    iput-object v0, v2, Lorg/telegram/tgnet/TLRPC$InputDocument;->file_reference:[B

    const/4 v3, 0x0

    if-nez v0, :cond_4

    .line 25298
    new-array v0, v3, [B

    iput-object v0, v2, Lorg/telegram/tgnet/TLRPC$InputDocument;->file_reference:[B

    :cond_4
    if-eqz p2, :cond_5

    .line 25301
    iget v0, v1, Lorg/telegram/tgnet/TLRPC$TL_account_saveMusic;->flags:I

    or-int/lit8 v0, v0, 0x2

    iput v0, v1, Lorg/telegram/tgnet/TLRPC$TL_account_saveMusic;->flags:I

    .line 25302
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputDocument;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputDocument;-><init>()V

    iput-object v0, v1, Lorg/telegram/tgnet/TLRPC$TL_account_saveMusic;->after_id:Lorg/telegram/tgnet/TLRPC$InputDocument;

    .line 25303
    iget-wide v4, p2, Lorg/telegram/tgnet/TLRPC$Document;->id:J

    iput-wide v4, v0, Lorg/telegram/tgnet/TLRPC$InputDocument;->id:J

    .line 25304
    iget-wide v4, p2, Lorg/telegram/tgnet/TLRPC$Document;->access_hash:J

    iput-wide v4, v0, Lorg/telegram/tgnet/TLRPC$InputDocument;->access_hash:J

    .line 25305
    iget-object p2, p2, Lorg/telegram/tgnet/TLRPC$Document;->file_reference:[B

    iput-object p2, v0, Lorg/telegram/tgnet/TLRPC$InputDocument;->file_reference:[B

    if-nez p2, :cond_5

    .line 25307
    new-array p2, v3, [B

    iput-object p2, v0, Lorg/telegram/tgnet/TLRPC$InputDocument;->file_reference:[B

    .line 25310
    :cond_5
    iput-boolean v3, v1, Lorg/telegram/tgnet/TLRPC$TL_account_saveMusic;->unsave:Z

    .line 25311
    iget p0, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->currentAccount:I

    invoke-static {p0}, Lorg/telegram/tgnet/ConnectionsManager;->getInstance(I)Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object p0

    invoke-virtual {p0, v1, p1}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequest(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/RequestDelegate;)I

    return-void
.end method

.method public remove(Lorg/telegram/messenger/MessageObject;)V
    .locals 2

    .line 25264
    invoke-virtual {p0}, Lorg/telegram/messenger/MessagesController$SavedMusicList;->getFirstDocument()Lorg/telegram/tgnet/TLRPC$Document;

    move-result-object v0

    .line 25265
    iget-object v1, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->list:Ljava/util/ArrayList;

    invoke-virtual {v1, p1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    .line 25266
    invoke-virtual {p0}, Lorg/telegram/messenger/MessagesController$SavedMusicList;->getFirstDocument()Lorg/telegram/tgnet/TLRPC$Document;

    move-result-object p1

    if-eq p1, v0, :cond_0

    .line 25267
    invoke-virtual {p0}, Lorg/telegram/messenger/MessagesController$SavedMusicList;->updateFirstMusic()V

    :cond_0
    return-void
.end method

.method public setup(Lorg/telegram/tgnet/TLRPC$Document;)V
    .locals 2

    .line 25196
    iget-object v0, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->list:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 25197
    invoke-virtual {p0}, Lorg/telegram/messenger/MessagesController$SavedMusicList;->load()V

    .line 25198
    iget-object v0, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->list:Ljava/util/ArrayList;

    const/4 v1, 0x0

    invoke-virtual {p0, p1}, Lorg/telegram/messenger/MessagesController$SavedMusicList;->toMessageObject(Lorg/telegram/tgnet/TLRPC$Document;)Lorg/telegram/messenger/MessageObject;

    move-result-object p0

    invoke-virtual {v0, v1, p0}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    return-void
.end method

.method public toMessageObject(Lorg/telegram/tgnet/TLRPC$Document;)Lorg/telegram/messenger/MessageObject;
    .locals 4

    .line 25204
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_message;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_message;-><init>()V

    .line 25205
    invoke-static {}, Lorg/telegram/messenger/SharedConfig;->getLastLocalId()I

    move-result v1

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->id:I

    .line 25206
    iget v1, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->currentAccount:I

    invoke-static {v1}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v1

    iget-wide v2, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->dialogId:J

    invoke-virtual {v1, v2, v3}, Lorg/telegram/messenger/MessagesController;->getPeer(J)Lorg/telegram/tgnet/TLRPC$Peer;

    move-result-object v1

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->peer_id:Lorg/telegram/tgnet/TLRPC$Peer;

    .line 25207
    iget v1, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->currentAccount:I

    invoke-static {v1}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v1

    iget-wide v2, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->dialogId:J

    invoke-virtual {v1, v2, v3}, Lorg/telegram/messenger/MessagesController;->getPeer(J)Lorg/telegram/tgnet/TLRPC$Peer;

    move-result-object v1

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->from_id:Lorg/telegram/tgnet/TLRPC$Peer;

    .line 25208
    new-instance v1, Lorg/telegram/tgnet/TLRPC$TL_messageMediaDocument;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLRPC$TL_messageMediaDocument;-><init>()V

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->media:Lorg/telegram/tgnet/TLRPC$MessageMedia;

    .line 25209
    iput-object p1, v1, Lorg/telegram/tgnet/TLRPC$MessageMedia;->document:Lorg/telegram/tgnet/TLRPC$Document;

    .line 25210
    new-instance p1, Lorg/telegram/messenger/MessageObject;

    iget p0, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->currentAccount:I

    const/4 v1, 0x0

    invoke-direct {p1, p0, v0, v1, v1}, Lorg/telegram/messenger/MessageObject;-><init>(ILorg/telegram/tgnet/TLRPC$Message;ZZ)V

    .line 25211
    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->checkMediaExistance()V

    return-object p1
.end method

.method public updateFirstMusic()V
    .locals 4

    .line 25315
    invoke-virtual {p0}, Lorg/telegram/messenger/MessagesController$SavedMusicList;->getFirstDocument()Lorg/telegram/tgnet/TLRPC$Document;

    move-result-object v0

    .line 25317
    iget v1, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->currentAccount:I

    invoke-static {v1}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v1

    iget-wide v2, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->dialogId:J

    invoke-virtual {v1, v2, v3}, Lorg/telegram/messenger/MessagesController;->getUserFull(J)Lorg/telegram/tgnet/TLRPC$UserFull;

    move-result-object v1

    if-nez v1, :cond_0

    return-void

    .line 25324
    :cond_0
    iget v2, v1, Lorg/telegram/tgnet/TLRPC$UserFull;->flags2:I

    if-nez v0, :cond_1

    const v0, -0x200001

    and-int/2addr v0, v2

    .line 25321
    iput v0, v1, Lorg/telegram/tgnet/TLRPC$UserFull;->flags2:I

    const/4 v0, 0x0

    .line 25322
    iput-object v0, v1, Lorg/telegram/tgnet/TLRPC$UserFull;->saved_music:Lorg/telegram/tgnet/TLRPC$Document;

    goto :goto_0

    :cond_1
    const/high16 v3, 0x200000

    or-int/2addr v2, v3

    .line 25324
    iput v2, v1, Lorg/telegram/tgnet/TLRPC$UserFull;->flags2:I

    .line 25325
    iput-object v0, v1, Lorg/telegram/tgnet/TLRPC$UserFull;->saved_music:Lorg/telegram/tgnet/TLRPC$Document;

    .line 25328
    :goto_0
    iget v0, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/MessagesStorage;->getInstance(I)Lorg/telegram/messenger/MessagesStorage;

    move-result-object v0

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lorg/telegram/messenger/MessagesStorage;->updateUserInfo(Lorg/telegram/tgnet/TLRPC$UserFull;Z)V

    .line 25329
    iget v0, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object v0

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/UserConfig;->saveConfig(Z)V

    .line 25330
    iget v0, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->profileMusicUpdated:I

    iget-wide v2, p0, Lorg/telegram/messenger/MessagesController$SavedMusicList;->dialogId:J

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p0

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-virtual {v0, v1, p0}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    return-void
.end method
