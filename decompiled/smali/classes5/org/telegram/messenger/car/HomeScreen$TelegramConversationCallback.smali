.class final Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/car/app/messaging/model/ConversationCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/messenger/car/HomeScreen;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "TelegramConversationCallback"
.end annotation


# instance fields
.field private final currentAccount:I

.field private final dialogId:J

.field private final maxId:I


# direct methods
.method public static synthetic $r8$lambda$-dQld8PWbzKMFfksBAmUlgjQtYo(Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;->lambda$onMarkAsRead$0()V

    return-void
.end method

.method public static synthetic $r8$lambda$bedXiha-wqzzo8K7JT5h7SRnTuA(Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;->lambda$onTextReply$1(Ljava/lang/String;)V

    return-void
.end method

.method public constructor <init>(IJI)V
    .locals 0

    .line 407
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 408
    iput p1, p0, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;->currentAccount:I

    .line 409
    iput-wide p2, p0, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;->dialogId:J

    .line 410
    iput p4, p0, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;->maxId:I

    return-void
.end method

.method private synthetic lambda$onMarkAsRead$0()V
    .locals 13

    .line 416
    iget v0, p0, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v1

    iget-wide v2, p0, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;->dialogId:J

    iget v4, p0, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;->maxId:I

    const/4 v11, 0x1

    const/4 v12, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const-wide/16 v8, 0x0

    const/4 v10, 0x0

    move v5, v4

    invoke-virtual/range {v1 .. v12}, Lorg/telegram/messenger/MessagesController;->markDialogAsRead(JIIIZJIZI)V

    .line 418
    iget v0, p0, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v0

    iget-wide v1, p0, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;->dialogId:J

    const-wide/16 v3, 0x0

    invoke-virtual {v0, v1, v2, v3, v4}, Lorg/telegram/messenger/MessagesController;->markReactionsAsRead(JJ)V

    return-void
.end method

.method private synthetic lambda$onTextReply$1(Ljava/lang/String;)V
    .locals 17

    move-object/from16 v0, p0

    .line 425
    iget v1, v0, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;->currentAccount:I

    invoke-static {v1}, Lorg/telegram/messenger/AccountInstance;->getInstance(I)Lorg/telegram/messenger/AccountInstance;

    move-result-object v1

    .line 426
    invoke-virtual {v1}, Lorg/telegram/messenger/AccountInstance;->getSendMessagesHelper()Lorg/telegram/messenger/SendMessagesHelper;

    move-result-object v1

    iget-wide v3, v0, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;->dialogId:J

    const/4 v15, 0x0

    const/16 v16, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x1

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v12, 0x1

    const/4 v13, 0x0

    const/4 v14, 0x0

    move-object/from16 v2, p1

    invoke-static/range {v2 .. v16}, Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;->of(Ljava/lang/String;JLorg/telegram/messenger/MessageObject;Lorg/telegram/messenger/MessageObject;Lorg/telegram/tgnet/TLRPC$WebPage;ZLjava/util/ArrayList;Lorg/telegram/tgnet/TLRPC$ReplyMarkup;Ljava/util/HashMap;ZIILorg/telegram/messenger/MessageObject$SendAnimationData;Z)Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;

    move-result-object v2

    invoke-virtual {v1, v2}, Lorg/telegram/messenger/SendMessagesHelper;->sendMessage(Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;)V

    .line 428
    iget v1, v0, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;->currentAccount:I

    invoke-static {v1}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v2

    iget-wide v3, v0, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;->dialogId:J

    iget v5, v0, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;->maxId:I

    const/4 v7, 0x0

    const/4 v8, 0x0

    const-wide/16 v9, 0x0

    const/4 v11, 0x0

    move v6, v5

    invoke-virtual/range {v2 .. v13}, Lorg/telegram/messenger/MessagesController;->markDialogAsRead(JIIIZJIZI)V

    return-void
.end method


# virtual methods
.method public onMarkAsRead()V
    .locals 1

    .line 415
    new-instance v0, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0}, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public onTextReply(Ljava/lang/String;)V
    .locals 1

    .line 424
    new-instance v0, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback$$ExternalSyntheticLambda1;

    invoke-direct {v0, p0, p1}, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback$$ExternalSyntheticLambda1;-><init>(Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;Ljava/lang/String;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method
