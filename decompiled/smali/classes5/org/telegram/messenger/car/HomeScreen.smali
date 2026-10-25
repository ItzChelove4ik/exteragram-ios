.class public Lorg/telegram/messenger/car/HomeScreen;
.super Landroidx/car/app/Screen;
.source "SourceFile"

# interfaces
.implements Landroidx/lifecycle/DefaultLifecycleObserver;
.implements Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;
    }
.end annotation


# instance fields
.field private activeTabId:Ljava/lang/String;

.field private currentAccount:I

.field private musicLoadKicked:Z

.field private final sessionStartMillis:J


# direct methods
.method public static synthetic $r8$lambda$lg05nMvz86bSR6ca6FQRWJSVMPE(Lorg/telegram/messenger/car/HomeScreen;JLjava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/messenger/car/HomeScreen;->lambda$buildMusicTemplate$0(JLjava/lang/String;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputactiveTabId(Lorg/telegram/messenger/car/HomeScreen;Ljava/lang/String;)V
    .locals 0

    .line 0
    iput-object p1, p0, Lorg/telegram/messenger/car/HomeScreen;->activeTabId:Ljava/lang/String;

    return-void
.end method

.method public constructor <init>(Landroidx/car/app/CarContext;)V
    .locals 2

    .line 78
    invoke-direct {p0, p1}, Landroidx/car/app/Screen;-><init>(Landroidx/car/app/CarContext;)V

    .line 74
    const-string/jumbo v0, "tab_notifications"

    iput-object v0, p0, Lorg/telegram/messenger/car/HomeScreen;->activeTabId:Ljava/lang/String;

    .line 79
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iput-wide v0, p0, Lorg/telegram/messenger/car/HomeScreen;->sessionStartMillis:J

    .line 80
    sget v0, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    iput v0, p0, Lorg/telegram/messenger/car/HomeScreen;->currentAccount:I

    .line 81
    invoke-virtual {p1}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object p1

    invoke-static {p1}, Lorg/telegram/messenger/TelegramMediaSession;->getInstance(Landroid/content/Context;)Lorg/telegram/messenger/TelegramMediaSession;

    move-result-object p1

    invoke-virtual {p1}, Lorg/telegram/messenger/TelegramMediaSession;->enablePlaybackMirroring()V

    .line 82
    invoke-virtual {p0}, Landroidx/car/app/Screen;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object p1

    invoke-virtual {p1, p0}, Landroidx/lifecycle/Lifecycle;->addObserver(Landroidx/lifecycle/LifecycleObserver;)V

    return-void
.end method

.method private buildCarMessage(Lorg/telegram/messenger/MessageObject;JZLorg/telegram/tgnet/TLRPC$User;Lorg/telegram/tgnet/TLRPC$Chat;)Landroidx/car/app/messaging/model/CarMessage;
    .locals 4

    .line 252
    const-string p4, ""

    const/4 v0, 0x1

    new-array v1, v0, [Ljava/lang/String;

    .line 253
    new-array v0, v0, [Z

    .line 256
    :try_start_0
    iget p0, p0, Lorg/telegram/messenger/car/HomeScreen;->currentAccount:I

    invoke-static {p0}, Lorg/telegram/messenger/NotificationsController;->getInstance(I)Lorg/telegram/messenger/NotificationsController;

    move-result-object p0

    .line 257
    invoke-virtual {p0, p1, v1, v0}, Lorg/telegram/messenger/NotificationsController;->getShortStringForMessage(Lorg/telegram/messenger/MessageObject;[Ljava/lang/String;[Z)Ljava/lang/String;

    move-result-object p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    .line 259
    :catchall_0
    iget-object p0, p1, Lorg/telegram/messenger/MessageObject;->messageText:Ljava/lang/CharSequence;

    if-eqz p0, :cond_0

    invoke-interface {p0}, Ljava/lang/CharSequence;->toString()Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    :cond_0
    move-object p0, p4

    :goto_0
    if-nez p0, :cond_1

    const/4 p0, 0x0

    return-object p0

    .line 263
    :cond_1
    new-instance v0, Landroidx/core/app/Person$Builder;

    invoke-direct {v0}, Landroidx/core/app/Person$Builder;-><init>()V

    const/4 v2, 0x0

    .line 264
    aget-object v1, v1, v2

    const-string/jumbo v3, "u"

    if-eqz v1, :cond_2

    .line 265
    invoke-virtual {v0, v1}, Landroidx/core/app/Person$Builder;->setName(Ljava/lang/CharSequence;)Landroidx/core/app/Person$Builder;

    move-result-object p2

    new-instance p3, Ljava/lang/StringBuilder;

    invoke-direct {p3, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getSenderId()J

    move-result-wide p4

    invoke-virtual {p3, p4, p5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {p3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p3

    invoke-virtual {p2, p3}, Landroidx/core/app/Person$Builder;->setKey(Ljava/lang/String;)Landroidx/core/app/Person$Builder;

    goto :goto_1

    .line 266
    :cond_2
    invoke-static {p2, p3}, Lorg/telegram/messenger/DialogObject;->isUserDialog(J)Z

    move-result v1

    if-eqz v1, :cond_3

    if-eqz p5, :cond_3

    .line 267
    iget-object p2, p5, Lorg/telegram/tgnet/TLRPC$User;->first_name:Ljava/lang/String;

    iget-object p3, p5, Lorg/telegram/tgnet/TLRPC$User;->last_name:Ljava/lang/String;

    invoke-static {p2, p3}, Lorg/telegram/messenger/ContactsController;->formatName(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {v0, p2}, Landroidx/core/app/Person$Builder;->setName(Ljava/lang/CharSequence;)Landroidx/core/app/Person$Builder;

    move-result-object p2

    new-instance p3, Ljava/lang/StringBuilder;

    invoke-direct {p3, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide p4, p5, Lorg/telegram/tgnet/TLRPC$User;->id:J

    invoke-virtual {p3, p4, p5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {p3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p3

    .line 268
    invoke-virtual {p2, p3}, Landroidx/core/app/Person$Builder;->setKey(Ljava/lang/String;)Landroidx/core/app/Person$Builder;

    goto :goto_1

    :cond_3
    if-eqz p6, :cond_5

    .line 270
    iget-object p2, p6, Lorg/telegram/tgnet/TLRPC$Chat;->title:Ljava/lang/String;

    if-eqz p2, :cond_4

    move-object p4, p2

    :cond_4
    invoke-virtual {v0, p4}, Landroidx/core/app/Person$Builder;->setName(Ljava/lang/CharSequence;)Landroidx/core/app/Person$Builder;

    move-result-object p2

    new-instance p3, Ljava/lang/StringBuilder;

    const-string p4, "c"

    invoke-direct {p3, p4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide p4, p6, Lorg/telegram/tgnet/TLRPC$Chat;->id:J

    invoke-virtual {p3, p4, p5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {p3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p3

    invoke-virtual {p2, p3}, Landroidx/core/app/Person$Builder;->setKey(Ljava/lang/String;)Landroidx/core/app/Person$Builder;

    goto :goto_1

    .line 272
    :cond_5
    invoke-virtual {v0, p4}, Landroidx/core/app/Person$Builder;->setName(Ljava/lang/CharSequence;)Landroidx/core/app/Person$Builder;

    move-result-object p4

    new-instance p5, Ljava/lang/StringBuilder;

    const-string p6, "d"

    invoke-direct {p5, p6}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p5, p2, p3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {p5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p4, p2}, Landroidx/core/app/Person$Builder;->setKey(Ljava/lang/String;)Landroidx/core/app/Person$Builder;

    .line 275
    :goto_1
    new-instance p2, Landroidx/car/app/messaging/model/CarMessage$Builder;

    invoke-direct {p2}, Landroidx/car/app/messaging/model/CarMessage$Builder;-><init>()V

    .line 276
    invoke-static {p0}, Landroidx/car/app/model/CarText;->create(Ljava/lang/CharSequence;)Landroidx/car/app/model/CarText;

    move-result-object p0

    invoke-virtual {p2, p0}, Landroidx/car/app/messaging/model/CarMessage$Builder;->setBody(Landroidx/car/app/model/CarText;)Landroidx/car/app/messaging/model/CarMessage$Builder;

    move-result-object p0

    iget-object p1, p1, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    iget p1, p1, Lorg/telegram/tgnet/TLRPC$Message;->date:I

    int-to-long p1, p1

    const-wide/16 p3, 0x3e8

    mul-long/2addr p1, p3

    .line 277
    invoke-virtual {p0, p1, p2}, Landroidx/car/app/messaging/model/CarMessage$Builder;->setReceivedTimeEpochMillis(J)Landroidx/car/app/messaging/model/CarMessage$Builder;

    move-result-object p0

    .line 278
    invoke-virtual {v0}, Landroidx/core/app/Person$Builder;->build()Landroidx/core/app/Person;

    move-result-object p1

    invoke-virtual {p0, p1}, Landroidx/car/app/messaging/model/CarMessage$Builder;->setSender(Landroidx/core/app/Person;)Landroidx/car/app/messaging/model/CarMessage$Builder;

    move-result-object p0

    .line 279
    invoke-virtual {p0, v2}, Landroidx/car/app/messaging/model/CarMessage$Builder;->setRead(Z)Landroidx/car/app/messaging/model/CarMessage$Builder;

    move-result-object p0

    .line 280
    invoke-virtual {p0}, Landroidx/car/app/messaging/model/CarMessage$Builder;->build()Landroidx/car/app/messaging/model/CarMessage;

    move-result-object p0

    return-object p0
.end method

.method private buildConversationItem(JLjava/util/ArrayList;)Landroidx/car/app/messaging/model/ConversationItem;
    .locals 19
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(J",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/messenger/MessageObject;",
            ">;)",
            "Landroidx/car/app/messaging/model/ConversationItem;"
        }
    .end annotation

    move-object/from16 v0, p0

    move-wide/from16 v2, p1

    move-object/from16 v7, p3

    .line 191
    invoke-virtual {v7}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v1

    const/4 v8, 0x0

    if-eqz v1, :cond_0

    return-object v8

    .line 192
    :cond_0
    invoke-static {v7}, Ljava/util/Collections;->reverse(Ljava/util/List;)V

    .line 193
    iget v1, v0, Lorg/telegram/messenger/car/HomeScreen;->currentAccount:I

    invoke-static {v1}, Lorg/telegram/messenger/AccountInstance;->getInstance(I)Lorg/telegram/messenger/AccountInstance;

    move-result-object v1

    .line 194
    invoke-virtual {v1}, Lorg/telegram/messenger/AccountInstance;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object v1

    .line 200
    invoke-static {v2, v3}, Lorg/telegram/messenger/DialogObject;->isUserDialog(J)Z

    move-result v4

    const/4 v5, 0x0

    if-eqz v4, :cond_2

    .line 201
    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v1, v4}, Lorg/telegram/messenger/MessagesController;->getUser(Ljava/lang/Long;)Lorg/telegram/tgnet/TLRPC$User;

    move-result-object v1

    if-nez v1, :cond_1

    return-object v8

    .line 203
    :cond_1
    iget-object v4, v1, Lorg/telegram/tgnet/TLRPC$User;->first_name:Ljava/lang/String;

    iget-object v6, v1, Lorg/telegram/tgnet/TLRPC$User;->last_name:Ljava/lang/String;

    invoke-static {v4, v6}, Lorg/telegram/messenger/ContactsController;->formatName(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    move-object v10, v4

    move v4, v5

    move-object v6, v8

    goto :goto_3

    :cond_2
    neg-long v10, v2

    .line 205
    invoke-static {v10, v11}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-virtual {v1, v4}, Lorg/telegram/messenger/MessagesController;->getChat(Ljava/lang/Long;)Lorg/telegram/tgnet/TLRPC$Chat;

    move-result-object v1

    if-nez v1, :cond_3

    return-object v8

    .line 207
    :cond_3
    iget-object v4, v1, Lorg/telegram/tgnet/TLRPC$Chat;->title:Ljava/lang/String;

    if-eqz v4, :cond_4

    goto :goto_0

    :cond_4
    const-string v4, ""

    .line 208
    :goto_0
    invoke-static {v1}, Lorg/telegram/messenger/ChatObject;->isChannel(Lorg/telegram/tgnet/TLRPC$Chat;)Z

    move-result v6

    if-eqz v6, :cond_6

    invoke-static {v1}, Lorg/telegram/messenger/ChatObject;->isMegagroup(Lorg/telegram/tgnet/TLRPC$Chat;)Z

    move-result v6

    if-eqz v6, :cond_5

    goto :goto_1

    :cond_5
    move v6, v5

    goto :goto_2

    :cond_6
    :goto_1
    const/4 v6, 0x1

    :goto_2
    move-object v10, v4

    move v4, v6

    move-object v6, v1

    move-object v1, v8

    .line 211
    :goto_3
    new-instance v11, Landroidx/core/app/Person$Builder;

    invoke-direct {v11}, Landroidx/core/app/Person$Builder;-><init>()V

    .line 212
    invoke-virtual {v11, v10}, Landroidx/core/app/Person$Builder;->setName(Ljava/lang/CharSequence;)Landroidx/core/app/Person$Builder;

    move-result-object v11

    .line 213
    invoke-static {v2, v3}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v12

    invoke-virtual {v11, v12}, Landroidx/core/app/Person$Builder;->setKey(Ljava/lang/String;)Landroidx/core/app/Person$Builder;

    move-result-object v11

    .line 214
    invoke-direct {v0, v1, v6}, Lorg/telegram/messenger/car/HomeScreen;->loadAvatarIcon(Lorg/telegram/tgnet/TLRPC$User;Lorg/telegram/tgnet/TLRPC$Chat;)Landroidx/core/graphics/drawable/IconCompat;

    move-result-object v12

    if-eqz v12, :cond_7

    .line 215
    invoke-virtual {v11, v12}, Landroidx/core/app/Person$Builder;->setIcon(Landroidx/core/graphics/drawable/IconCompat;)Landroidx/core/app/Person$Builder;

    .line 217
    :cond_7
    iget v11, v0, Lorg/telegram/messenger/car/HomeScreen;->currentAccount:I

    invoke-static {v11}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object v11

    invoke-virtual {v11}, Lorg/telegram/messenger/UserConfig;->getCurrentUser()Lorg/telegram/tgnet/TLRPC$User;

    move-result-object v11

    .line 218
    new-instance v13, Landroidx/core/app/Person$Builder;

    invoke-direct {v13}, Landroidx/core/app/Person$Builder;-><init>()V

    sget v14, Lorg/telegram/messenger/R$string;->FromYou:I

    .line 219
    invoke-static {v14}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v14

    invoke-virtual {v13, v14}, Landroidx/core/app/Person$Builder;->setName(Ljava/lang/CharSequence;)Landroidx/core/app/Person$Builder;

    move-result-object v13

    new-instance v14, Ljava/lang/StringBuilder;

    const-string v15, "self_"

    invoke-direct {v14, v15}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget v15, v0, Lorg/telegram/messenger/car/HomeScreen;->currentAccount:I

    .line 220
    invoke-static {v15}, Lorg/telegram/messenger/UserConfig;->getInstance(I)Lorg/telegram/messenger/UserConfig;

    move-result-object v15

    move-object/from16 v16, v8

    const/16 v17, 0x1

    iget-wide v8, v15, Lorg/telegram/messenger/UserConfig;->clientUserId:J

    invoke-virtual {v14, v8, v9}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v14}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v13, v8}, Landroidx/core/app/Person$Builder;->setKey(Ljava/lang/String;)Landroidx/core/app/Person$Builder;

    move-result-object v8

    if-eqz v11, :cond_8

    .line 222
    invoke-direct {v0, v11}, Lorg/telegram/messenger/car/HomeScreen;->loadUserAvatarIcon(Lorg/telegram/tgnet/TLRPC$User;)Landroidx/core/graphics/drawable/IconCompat;

    move-result-object v9

    if-eqz v9, :cond_8

    .line 223
    invoke-virtual {v8, v9}, Landroidx/core/app/Person$Builder;->setIcon(Landroidx/core/graphics/drawable/IconCompat;)Landroidx/core/app/Person$Builder;

    .line 225
    :cond_8
    invoke-virtual {v8}, Landroidx/core/app/Person$Builder;->build()Landroidx/core/app/Person;

    move-result-object v8

    .line 227
    new-instance v9, Ljava/util/ArrayList;

    invoke-direct {v9}, Ljava/util/ArrayList;-><init>()V

    .line 228
    invoke-virtual {v7}, Ljava/util/ArrayList;->size()I

    move-result v11

    add-int/lit8 v11, v11, -0x5

    invoke-static {v5, v11}, Ljava/lang/Math;->max(II)I

    move-result v5

    move v11, v5

    .line 229
    :goto_4
    invoke-virtual {v7}, Ljava/util/ArrayList;->size()I

    move-result v5

    if-ge v11, v5, :cond_a

    .line 230
    invoke-virtual {v7, v11}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lorg/telegram/messenger/MessageObject;

    move-object/from16 v18, v5

    move-object v5, v1

    move-object/from16 v1, v18

    .line 231
    invoke-direct/range {v0 .. v6}, Lorg/telegram/messenger/car/HomeScreen;->buildCarMessage(Lorg/telegram/messenger/MessageObject;JZLorg/telegram/tgnet/TLRPC$User;Lorg/telegram/tgnet/TLRPC$Chat;)Landroidx/car/app/messaging/model/CarMessage;

    move-result-object v1

    if-eqz v1, :cond_9

    .line 232
    invoke-interface {v9, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :cond_9
    add-int/lit8 v11, v11, 0x1

    move-object v1, v5

    goto :goto_4

    .line 234
    :cond_a
    invoke-interface {v9}, Ljava/util/List;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_b

    return-object v16

    .line 236
    :cond_b
    invoke-virtual {v7}, Ljava/util/ArrayList;->size()I

    move-result v1

    add-int/lit8 v1, v1, -0x1

    invoke-virtual {v7, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lorg/telegram/messenger/MessageObject;

    invoke-virtual {v1}, Lorg/telegram/messenger/MessageObject;->getId()I

    move-result v1

    .line 238
    new-instance v5, Landroidx/car/app/messaging/model/ConversationItem$Builder;

    invoke-direct {v5}, Landroidx/car/app/messaging/model/ConversationItem$Builder;-><init>()V

    .line 239
    invoke-static {v2, v3}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Landroidx/car/app/messaging/model/ConversationItem$Builder;->setId(Ljava/lang/String;)Landroidx/car/app/messaging/model/ConversationItem$Builder;

    move-result-object v5

    .line 240
    invoke-static {v10}, Landroidx/car/app/model/CarText;->create(Ljava/lang/CharSequence;)Landroidx/car/app/model/CarText;

    move-result-object v6

    invoke-virtual {v5, v6}, Landroidx/car/app/messaging/model/ConversationItem$Builder;->setTitle(Landroidx/car/app/model/CarText;)Landroidx/car/app/messaging/model/ConversationItem$Builder;

    move-result-object v5

    .line 241
    invoke-virtual {v5, v8}, Landroidx/car/app/messaging/model/ConversationItem$Builder;->setSelf(Landroidx/core/app/Person;)Landroidx/car/app/messaging/model/ConversationItem$Builder;

    move-result-object v5

    .line 242
    invoke-virtual {v5, v9}, Landroidx/car/app/messaging/model/ConversationItem$Builder;->setMessages(Ljava/util/List;)Landroidx/car/app/messaging/model/ConversationItem$Builder;

    move-result-object v5

    .line 243
    invoke-virtual {v5, v4}, Landroidx/car/app/messaging/model/ConversationItem$Builder;->setGroupConversation(Z)Landroidx/car/app/messaging/model/ConversationItem$Builder;

    move-result-object v4

    new-instance v5, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;

    iget v0, v0, Lorg/telegram/messenger/car/HomeScreen;->currentAccount:I

    invoke-direct {v5, v0, v2, v3, v1}, Lorg/telegram/messenger/car/HomeScreen$TelegramConversationCallback;-><init>(IJI)V

    .line 244
    invoke-virtual {v4, v5}, Landroidx/car/app/messaging/model/ConversationItem$Builder;->setConversationCallback(Landroidx/car/app/messaging/model/ConversationCallback;)Landroidx/car/app/messaging/model/ConversationItem$Builder;

    move-result-object v0

    if-eqz v12, :cond_c

    .line 246
    new-instance v1, Landroidx/car/app/model/CarIcon$Builder;

    invoke-direct {v1, v12}, Landroidx/car/app/model/CarIcon$Builder;-><init>(Landroidx/core/graphics/drawable/IconCompat;)V

    invoke-virtual {v1}, Landroidx/car/app/model/CarIcon$Builder;->build()Landroidx/car/app/model/CarIcon;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroidx/car/app/messaging/model/ConversationItem$Builder;->setIcon(Landroidx/car/app/model/CarIcon;)Landroidx/car/app/messaging/model/ConversationItem$Builder;

    .line 248
    :cond_c
    invoke-virtual {v0}, Landroidx/car/app/messaging/model/ConversationItem$Builder;->build()Landroidx/car/app/messaging/model/ConversationItem;

    move-result-object v0

    return-object v0
.end method

.method private buildMusicTemplate()Landroidx/car/app/model/Template;
    .locals 17

    move-object/from16 v0, p0

    .line 286
    invoke-virtual {v0}, Landroidx/car/app/Screen;->getCarContext()Landroidx/car/app/CarContext;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/messenger/TelegramMediaSession;->getInstance(Landroid/content/Context;)Lorg/telegram/messenger/TelegramMediaSession;

    move-result-object v1

    .line 287
    invoke-virtual {v1}, Lorg/telegram/messenger/TelegramMediaSession;->isChatsLoaded()Z

    move-result v2

    const/4 v3, 0x1

    if-nez v2, :cond_1

    .line 288
    iget-boolean v2, v0, Lorg/telegram/messenger/car/HomeScreen;->musicLoadKicked:Z

    if-nez v2, :cond_0

    .line 289
    iput-boolean v3, v0, Lorg/telegram/messenger/car/HomeScreen;->musicLoadKicked:Z

    .line 290
    new-instance v2, Lorg/telegram/messenger/car/HomeScreen$$ExternalSyntheticLambda0;

    invoke-direct {v2, v0}, Lorg/telegram/messenger/car/HomeScreen$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/messenger/car/HomeScreen;)V

    invoke-virtual {v1, v2}, Lorg/telegram/messenger/TelegramMediaSession;->ensureLoaded(Ljava/lang/Runnable;)V

    .line 292
    :cond_0
    new-instance v0, Landroidx/car/app/model/ListTemplate$Builder;

    invoke-direct {v0}, Landroidx/car/app/model/ListTemplate$Builder;-><init>()V

    invoke-virtual {v0, v3}, Landroidx/car/app/model/ListTemplate$Builder;->setLoading(Z)Landroidx/car/app/model/ListTemplate$Builder;

    move-result-object v0

    invoke-virtual {v0}, Landroidx/car/app/model/ListTemplate$Builder;->build()Landroidx/car/app/model/ListTemplate;

    move-result-object v0

    return-object v0

    .line 294
    :cond_1
    invoke-virtual {v1}, Lorg/telegram/messenger/TelegramMediaSession;->getMusicDialogsSortedByVisibleOrder()Ljava/util/ArrayList;

    move-result-object v2

    if-eqz v2, :cond_f

    .line 295
    invoke-virtual {v2}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v4

    if-eqz v4, :cond_2

    goto/16 :goto_6

    .line 299
    :cond_2
    new-instance v4, Landroidx/car/app/model/ItemList$Builder;

    invoke-direct {v4}, Landroidx/car/app/model/ItemList$Builder;-><init>()V

    const/4 v5, 0x0

    move v6, v5

    move v7, v6

    .line 301
    :goto_0
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v8

    if-ge v6, v8, :cond_e

    const/16 v8, 0x32

    if-ge v7, v8, :cond_e

    .line 302
    invoke-virtual {v2, v6}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Ljava/lang/Long;

    invoke-virtual {v8}, Ljava/lang/Long;->longValue()J

    move-result-wide v8

    .line 303
    invoke-virtual {v1, v8, v9}, Lorg/telegram/messenger/TelegramMediaSession;->getMusicMessages(J)Ljava/util/ArrayList;

    move-result-object v10

    if-eqz v10, :cond_3

    .line 304
    invoke-virtual {v10}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v11

    if-eqz v11, :cond_3

    goto/16 :goto_5

    .line 307
    :cond_3
    invoke-static {v8, v9}, Lorg/telegram/messenger/DialogObject;->isUserDialog(J)Z

    move-result v11

    const/4 v12, 0x0

    if-eqz v11, :cond_6

    .line 308
    invoke-virtual {v1, v8, v9}, Lorg/telegram/messenger/TelegramMediaSession;->getMusicUser(J)Lorg/telegram/tgnet/TLRPC$User;

    move-result-object v11

    if-nez v11, :cond_4

    goto/16 :goto_5

    .line 310
    :cond_4
    invoke-static {v11}, Lorg/telegram/messenger/UserObject;->isUserSelf(Lorg/telegram/tgnet/TLRPC$User;)Z

    move-result v13

    if-eqz v13, :cond_5

    .line 311
    sget v11, Lorg/telegram/messenger/R$string;->SavedMessages:I

    invoke-static {v11}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v11

    goto :goto_3

    .line 313
    :cond_5
    iget-object v13, v11, Lorg/telegram/tgnet/TLRPC$User;->first_name:Ljava/lang/String;

    iget-object v14, v11, Lorg/telegram/tgnet/TLRPC$User;->last_name:Ljava/lang/String;

    invoke-static {v13, v14}, Lorg/telegram/messenger/ContactsController;->formatName(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v13

    .line 314
    iget-object v11, v11, Lorg/telegram/tgnet/TLRPC$User;->photo:Lorg/telegram/tgnet/TLRPC$UserProfilePhoto;

    if-eqz v11, :cond_9

    iget-object v11, v11, Lorg/telegram/tgnet/TLRPC$UserProfilePhoto;->photo_small:Lorg/telegram/tgnet/TLRPC$FileLocation;

    instance-of v14, v11, Lorg/telegram/tgnet/TLRPC$TL_fileLocationUnavailable;

    if-nez v14, :cond_9

    goto :goto_2

    :cond_6
    neg-long v13, v8

    .line 319
    invoke-virtual {v1, v13, v14}, Lorg/telegram/messenger/TelegramMediaSession;->getMusicChat(J)Lorg/telegram/tgnet/TLRPC$Chat;

    move-result-object v11

    if-nez v11, :cond_7

    goto :goto_5

    .line 321
    :cond_7
    iget-object v13, v11, Lorg/telegram/tgnet/TLRPC$Chat;->title:Ljava/lang/String;

    if-eqz v13, :cond_8

    goto :goto_1

    :cond_8
    const-string v13, ""

    .line 322
    :goto_1
    iget-object v11, v11, Lorg/telegram/tgnet/TLRPC$Chat;->photo:Lorg/telegram/tgnet/TLRPC$ChatPhoto;

    if-eqz v11, :cond_9

    iget-object v11, v11, Lorg/telegram/tgnet/TLRPC$ChatPhoto;->photo_small:Lorg/telegram/tgnet/TLRPC$FileLocation;

    instance-of v14, v11, Lorg/telegram/tgnet/TLRPC$TL_fileLocationUnavailable;

    if-nez v14, :cond_9

    :goto_2
    move-object/from16 v16, v13

    move-object v13, v11

    move-object/from16 v11, v16

    goto :goto_4

    :cond_9
    move-object v11, v13

    :goto_3
    move-object v13, v12

    .line 326
    :goto_4
    invoke-static {v11}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v14

    if-eqz v14, :cond_a

    goto :goto_5

    .line 328
    :cond_a
    new-instance v14, Landroidx/car/app/model/Row$Builder;

    invoke-direct {v14}, Landroidx/car/app/model/Row$Builder;-><init>()V

    .line 329
    invoke-virtual {v14, v11}, Landroidx/car/app/model/Row$Builder;->setTitle(Ljava/lang/CharSequence;)Landroidx/car/app/model/Row$Builder;

    move-result-object v14

    .line 330
    invoke-virtual {v14, v3}, Landroidx/car/app/model/Row$Builder;->setBrowsable(Z)Landroidx/car/app/model/Row$Builder;

    move-result-object v14

    new-instance v15, Lorg/telegram/messenger/car/HomeScreen$$ExternalSyntheticLambda1;

    invoke-direct {v15, v0, v8, v9, v11}, Lorg/telegram/messenger/car/HomeScreen$$ExternalSyntheticLambda1;-><init>(Lorg/telegram/messenger/car/HomeScreen;JLjava/lang/String;)V

    .line 331
    invoke-virtual {v14, v15}, Landroidx/car/app/model/Row$Builder;->setOnClickListener(Landroidx/car/app/model/OnClickListener;)Landroidx/car/app/model/Row$Builder;

    move-result-object v8

    if-eqz v10, :cond_b

    .line 333
    invoke-virtual {v10}, Ljava/util/ArrayList;->size()I

    move-result v9

    new-array v10, v5, [Ljava/lang/Object;

    const-string v11, "MusicFiles"

    invoke-static {v11, v9, v10}, Lorg/telegram/messenger/LocaleController;->formatPluralString(Ljava/lang/String;I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v8, v9}, Landroidx/car/app/model/Row$Builder;->addText(Ljava/lang/CharSequence;)Landroidx/car/app/model/Row$Builder;

    :cond_b
    if-eqz v13, :cond_c

    .line 337
    invoke-direct {v0, v13}, Lorg/telegram/messenger/car/HomeScreen;->iconFromAvatar(Lorg/telegram/tgnet/TLRPC$FileLocation;)Landroidx/core/graphics/drawable/IconCompat;

    move-result-object v12

    :cond_c
    if-eqz v12, :cond_d

    .line 340
    new-instance v9, Landroidx/car/app/model/CarIcon$Builder;

    invoke-direct {v9, v12}, Landroidx/car/app/model/CarIcon$Builder;-><init>(Landroidx/core/graphics/drawable/IconCompat;)V

    invoke-virtual {v9}, Landroidx/car/app/model/CarIcon$Builder;->build()Landroidx/car/app/model/CarIcon;

    move-result-object v9

    const/4 v10, 0x2

    invoke-virtual {v8, v9, v10}, Landroidx/car/app/model/Row$Builder;->setImage(Landroidx/car/app/model/CarIcon;I)Landroidx/car/app/model/Row$Builder;

    .line 342
    :cond_d
    invoke-virtual {v8}, Landroidx/car/app/model/Row$Builder;->build()Landroidx/car/app/model/Row;

    move-result-object v8

    invoke-virtual {v4, v8}, Landroidx/car/app/model/ItemList$Builder;->addItem(Landroidx/car/app/model/Item;)Landroidx/car/app/model/ItemList$Builder;

    add-int/lit8 v7, v7, 0x1

    :goto_5
    add-int/lit8 v6, v6, 0x1

    goto/16 :goto_0

    .line 345
    :cond_e
    new-instance v0, Landroidx/car/app/model/ListTemplate$Builder;

    invoke-direct {v0}, Landroidx/car/app/model/ListTemplate$Builder;-><init>()V

    .line 346
    invoke-virtual {v4}, Landroidx/car/app/model/ItemList$Builder;->build()Landroidx/car/app/model/ItemList;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroidx/car/app/model/ListTemplate$Builder;->setSingleList(Landroidx/car/app/model/ItemList;)Landroidx/car/app/model/ListTemplate$Builder;

    move-result-object v0

    .line 347
    invoke-virtual {v0}, Landroidx/car/app/model/ListTemplate$Builder;->build()Landroidx/car/app/model/ListTemplate;

    move-result-object v0

    return-object v0

    .line 296
    :cond_f
    :goto_6
    new-instance v0, Landroidx/car/app/model/MessageTemplate$Builder;

    sget v1, Lorg/telegram/messenger/R$string;->NoCarMusic:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Landroidx/car/app/model/MessageTemplate$Builder;-><init>(Ljava/lang/CharSequence;)V

    .line 297
    invoke-virtual {v0}, Landroidx/car/app/model/MessageTemplate$Builder;->build()Landroidx/car/app/model/MessageTemplate;

    move-result-object v0

    return-object v0
.end method

.method private buildNotificationsTemplate()Landroidx/car/app/model/Template;
    .locals 7

    .line 152
    invoke-direct {p0}, Lorg/telegram/messenger/car/HomeScreen;->collectUnreadDuringDrive()Ljava/util/Map;

    move-result-object v0

    .line 153
    invoke-interface {v0}, Ljava/util/Map;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 154
    new-instance p0, Landroidx/car/app/model/MessageTemplate$Builder;

    sget v0, Lorg/telegram/messenger/R$string;->NoNewCarMessages:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Landroidx/car/app/model/MessageTemplate$Builder;-><init>(Ljava/lang/CharSequence;)V

    .line 155
    invoke-virtual {p0}, Landroidx/car/app/model/MessageTemplate$Builder;->build()Landroidx/car/app/model/MessageTemplate;

    move-result-object p0

    return-object p0

    .line 157
    :cond_0
    new-instance v1, Landroidx/car/app/model/ItemList$Builder;

    invoke-direct {v1}, Landroidx/car/app/model/ItemList$Builder;-><init>()V

    .line 159
    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    const/4 v2, 0x0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_3

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/util/Map$Entry;

    add-int/lit8 v4, v2, 0x1

    const/4 v5, 0x6

    if-lt v2, v5, :cond_1

    goto :goto_1

    .line 161
    :cond_1
    invoke-interface {v3}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Long;

    invoke-virtual {v2}, Ljava/lang/Long;->longValue()J

    move-result-wide v5

    invoke-interface {v3}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/ArrayList;

    invoke-direct {p0, v5, v6, v2}, Lorg/telegram/messenger/car/HomeScreen;->buildConversationItem(JLjava/util/ArrayList;)Landroidx/car/app/messaging/model/ConversationItem;

    move-result-object v2

    if-eqz v2, :cond_2

    .line 162
    invoke-virtual {v1, v2}, Landroidx/car/app/model/ItemList$Builder;->addItem(Landroidx/car/app/model/Item;)Landroidx/car/app/model/ItemList$Builder;

    :cond_2
    move v2, v4

    goto :goto_0

    .line 164
    :cond_3
    :goto_1
    new-instance p0, Landroidx/car/app/model/ListTemplate$Builder;

    invoke-direct {p0}, Landroidx/car/app/model/ListTemplate$Builder;-><init>()V

    .line 165
    invoke-virtual {v1}, Landroidx/car/app/model/ItemList$Builder;->build()Landroidx/car/app/model/ItemList;

    move-result-object v0

    invoke-virtual {p0, v0}, Landroidx/car/app/model/ListTemplate$Builder;->setSingleList(Landroidx/car/app/model/ItemList;)Landroidx/car/app/model/ListTemplate$Builder;

    move-result-object p0

    .line 166
    invoke-virtual {p0}, Landroidx/car/app/model/ListTemplate$Builder;->build()Landroidx/car/app/model/ListTemplate;

    move-result-object p0

    return-object p0
.end method

.method private buildTabContent(Ljava/lang/String;)Landroidx/car/app/model/Template;
    .locals 2

    .line 140
    invoke-virtual {p1}, Ljava/lang/String;->hashCode()I

    move-result v0

    const v1, -0x779f4242

    if-eq v0, v1, :cond_1

    const v1, 0x73ca4cfb

    if-eq v0, v1, :cond_0

    goto :goto_0

    :cond_0
    const-string/jumbo v0, "tab_music"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_2

    .line 142
    invoke-direct {p0}, Lorg/telegram/messenger/car/HomeScreen;->buildMusicTemplate()Landroidx/car/app/model/Template;

    move-result-object p0

    return-object p0

    .line 140
    :cond_1
    const-string/jumbo v0, "tab_notifications"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    .line 145
    :cond_2
    :goto_0
    invoke-direct {p0}, Lorg/telegram/messenger/car/HomeScreen;->buildNotificationsTemplate()Landroidx/car/app/model/Template;

    move-result-object p0

    return-object p0
.end method

.method private collectUnreadDuringDrive()Ljava/util/Map;
    .locals 9
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map<",
            "Ljava/lang/Long;",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/messenger/MessageObject;",
            ">;>;"
        }
    .end annotation

    .line 170
    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    .line 171
    iget v1, p0, Lorg/telegram/messenger/car/HomeScreen;->currentAccount:I

    invoke-static {v1}, Lorg/telegram/messenger/NotificationsController;->getInstance(I)Lorg/telegram/messenger/NotificationsController;

    move-result-object v1

    .line 172
    invoke-virtual {v1}, Lorg/telegram/messenger/NotificationsController;->getPushMessagesSnapshot()Ljava/util/ArrayList;

    move-result-object v1

    .line 173
    iget-wide v2, p0, Lorg/telegram/messenger/car/HomeScreen;->sessionStartMillis:J

    const-wide/16 v4, 0x3e8

    div-long/2addr v2, v4

    .line 174
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result p0

    const/4 v4, 0x0

    :cond_0
    :goto_0
    if-ge v4, p0, :cond_6

    invoke-virtual {v1, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    add-int/lit8 v4, v4, 0x1

    check-cast v5, Lorg/telegram/messenger/MessageObject;

    if-eqz v5, :cond_0

    .line 175
    iget-object v6, v5, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    if-nez v6, :cond_1

    goto :goto_0

    .line 176
    :cond_1
    iget v6, v6, Lorg/telegram/tgnet/TLRPC$Message;->date:I

    int-to-long v6, v6

    cmp-long v6, v6, v2

    if-gez v6, :cond_2

    goto :goto_0

    .line 177
    :cond_2
    invoke-virtual {v5}, Lorg/telegram/messenger/MessageObject;->getDialogId()J

    move-result-wide v6

    .line 178
    invoke-static {v6, v7}, Lorg/telegram/messenger/DialogObject;->isEncryptedDialog(J)Z

    move-result v8

    if-eqz v8, :cond_3

    goto :goto_0

    .line 179
    :cond_3
    invoke-static {v6, v7}, Lorg/telegram/messenger/UserObject;->isReplyUser(J)Z

    move-result v8

    if-eqz v8, :cond_4

    goto :goto_0

    .line 180
    :cond_4
    invoke-static {v6, v7}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v8

    invoke-virtual {v0, v8}, Ljava/util/LinkedHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Ljava/util/ArrayList;

    if-nez v8, :cond_5

    .line 182
    new-instance v8, Ljava/util/ArrayList;

    invoke-direct {v8}, Ljava/util/ArrayList;-><init>()V

    .line 183
    invoke-static {v6, v7}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v6

    invoke-virtual {v0, v6, v8}, Ljava/util/AbstractMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 185
    :cond_5
    invoke-virtual {v8, v5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_6
    return-object v0
.end method

.method private iconFromAvatar(Lorg/telegram/tgnet/TLRPC$FileLocation;)Landroidx/core/graphics/drawable/IconCompat;
    .locals 3

    const/4 v0, 0x0

    if-eqz p1, :cond_4

    .line 372
    instance-of v1, p1, Lorg/telegram/tgnet/TLRPC$TL_fileLocationUnavailable;

    if-eqz v1, :cond_0

    goto :goto_0

    .line 374
    :cond_0
    :try_start_0
    iget v1, p0, Lorg/telegram/messenger/car/HomeScreen;->currentAccount:I

    invoke-static {v1}, Lorg/telegram/messenger/FileLoader;->getInstance(I)Lorg/telegram/messenger/FileLoader;

    move-result-object v1

    const/4 v2, 0x1

    invoke-virtual {v1, p1, v2}, Lorg/telegram/messenger/FileLoader;->getPathToAttach(Lorg/telegram/tgnet/TLObject;Z)Ljava/io/File;

    move-result-object p1

    .line 375
    invoke-virtual {p1}, Ljava/io/File;->exists()Z

    move-result v1

    if-nez v1, :cond_1

    return-object v0

    .line 376
    :cond_1
    new-instance v1, Landroid/graphics/BitmapFactory$Options;

    invoke-direct {v1}, Landroid/graphics/BitmapFactory$Options;-><init>()V

    const/4 v2, 0x2

    .line 377
    iput v2, v1, Landroid/graphics/BitmapFactory$Options;->inSampleSize:I

    .line 378
    invoke-virtual {p1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1, v1}, Landroid/graphics/BitmapFactory;->decodeFile(Ljava/lang/String;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;

    move-result-object p1

    if-nez p1, :cond_2

    return-object v0

    .line 380
    :cond_2
    invoke-direct {p0, p1}, Lorg/telegram/messenger/car/HomeScreen;->makeRound(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;

    move-result-object p0

    if-eqz p0, :cond_3

    move-object p1, p0

    .line 381
    :cond_3
    invoke-static {p1}, Landroidx/core/graphics/drawable/IconCompat;->createWithBitmap(Landroid/graphics/Bitmap;)Landroidx/core/graphics/drawable/IconCompat;

    move-result-object p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-object p0

    :catchall_0
    :cond_4
    :goto_0
    return-object v0
.end method

.method private iconResource(I)Landroidx/car/app/model/CarIcon;
    .locals 1

    .line 353
    new-instance v0, Landroidx/car/app/model/CarIcon$Builder;

    invoke-virtual {p0}, Landroidx/car/app/Screen;->getCarContext()Landroidx/car/app/CarContext;

    move-result-object p0

    invoke-static {p0, p1}, Landroidx/core/graphics/drawable/IconCompat;->createWithResource(Landroid/content/Context;I)Landroidx/core/graphics/drawable/IconCompat;

    move-result-object p0

    invoke-direct {v0, p0}, Landroidx/car/app/model/CarIcon$Builder;-><init>(Landroidx/core/graphics/drawable/IconCompat;)V

    invoke-virtual {v0}, Landroidx/car/app/model/CarIcon$Builder;->build()Landroidx/car/app/model/CarIcon;

    move-result-object p0

    return-object p0
.end method

.method private synthetic lambda$buildMusicTemplate$0(JLjava/lang/String;)V
    .locals 2

    .line 331
    invoke-virtual {p0}, Landroidx/car/app/Screen;->getScreenManager()Landroidx/car/app/ScreenManager;

    move-result-object v0

    new-instance v1, Lorg/telegram/messenger/car/MusicSongsScreen;

    invoke-virtual {p0}, Landroidx/car/app/Screen;->getCarContext()Landroidx/car/app/CarContext;

    move-result-object p0

    invoke-direct {v1, p0, p1, p2, p3}, Lorg/telegram/messenger/car/MusicSongsScreen;-><init>(Landroidx/car/app/CarContext;JLjava/lang/String;)V

    invoke-virtual {v0, v1}, Landroidx/car/app/ScreenManager;->push(Landroidx/car/app/Screen;)V

    return-void
.end method

.method private loadAvatarIcon(Lorg/telegram/tgnet/TLRPC$User;Lorg/telegram/tgnet/TLRPC$Chat;)Landroidx/core/graphics/drawable/IconCompat;
    .locals 0

    if-eqz p1, :cond_0

    .line 358
    iget-object p1, p1, Lorg/telegram/tgnet/TLRPC$User;->photo:Lorg/telegram/tgnet/TLRPC$UserProfilePhoto;

    if-eqz p1, :cond_0

    .line 359
    iget-object p1, p1, Lorg/telegram/tgnet/TLRPC$UserProfilePhoto;->photo_small:Lorg/telegram/tgnet/TLRPC$FileLocation;

    goto :goto_0

    :cond_0
    if-eqz p2, :cond_1

    .line 360
    iget-object p1, p2, Lorg/telegram/tgnet/TLRPC$Chat;->photo:Lorg/telegram/tgnet/TLRPC$ChatPhoto;

    if-eqz p1, :cond_1

    .line 361
    iget-object p1, p1, Lorg/telegram/tgnet/TLRPC$ChatPhoto;->photo_small:Lorg/telegram/tgnet/TLRPC$FileLocation;

    goto :goto_0

    :cond_1
    const/4 p1, 0x0

    .line 363
    :goto_0
    invoke-direct {p0, p1}, Lorg/telegram/messenger/car/HomeScreen;->iconFromAvatar(Lorg/telegram/tgnet/TLRPC$FileLocation;)Landroidx/core/graphics/drawable/IconCompat;

    move-result-object p0

    return-object p0
.end method

.method private loadUserAvatarIcon(Lorg/telegram/tgnet/TLRPC$User;)Landroidx/core/graphics/drawable/IconCompat;
    .locals 0

    if-eqz p1, :cond_1

    .line 367
    iget-object p1, p1, Lorg/telegram/tgnet/TLRPC$User;->photo:Lorg/telegram/tgnet/TLRPC$UserProfilePhoto;

    if-nez p1, :cond_0

    goto :goto_0

    .line 368
    :cond_0
    iget-object p1, p1, Lorg/telegram/tgnet/TLRPC$UserProfilePhoto;->photo_small:Lorg/telegram/tgnet/TLRPC$FileLocation;

    invoke-direct {p0, p1}, Lorg/telegram/messenger/car/HomeScreen;->iconFromAvatar(Lorg/telegram/tgnet/TLRPC$FileLocation;)Landroidx/core/graphics/drawable/IconCompat;

    move-result-object p0

    return-object p0

    :cond_1
    :goto_0
    const/4 p0, 0x0

    return-object p0
.end method

.method private makeRound(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
    .locals 6

    .line 389
    :try_start_0
    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result p0

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v0

    sget-object v1, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {p0, v0, v1}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object p0

    const/4 v0, 0x0

    .line 390
    invoke-virtual {p0, v0}, Landroid/graphics/Bitmap;->eraseColor(I)V

    .line 391
    new-instance v0, Landroid/graphics/Canvas;

    invoke-direct {v0, p0}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    .line 392
    new-instance v1, Landroid/graphics/Paint;

    const/4 v2, 0x1

    invoke-direct {v1, v2}, Landroid/graphics/Paint;-><init>(I)V

    .line 393
    new-instance v2, Landroid/graphics/BitmapShader;

    sget-object v3, Landroid/graphics/Shader$TileMode;->CLAMP:Landroid/graphics/Shader$TileMode;

    invoke-direct {v2, p1, v3, v3}, Landroid/graphics/BitmapShader;-><init>(Landroid/graphics/Bitmap;Landroid/graphics/Shader$TileMode;Landroid/graphics/Shader$TileMode;)V

    invoke-virtual {v1, v2}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    .line 394
    new-instance v2, Landroid/graphics/RectF;

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v3

    int-to-float v3, v3

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v4

    int-to-float v4, v4

    const/4 v5, 0x0

    invoke-direct {v2, v5, v5, v3, v4}, Landroid/graphics/RectF;-><init>(FFFF)V

    .line 395
    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v3

    int-to-float v3, v3

    invoke-virtual {p1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result p1

    int-to-float p1, p1

    invoke-virtual {v0, v2, v3, p1, v1}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-object p0

    :catchall_0
    const/4 p0, 0x0

    return-object p0
.end method


# virtual methods
.method public varargs didReceivedNotification(II[Ljava/lang/Object;)V
    .locals 0

    .line 101
    sget p2, Lorg/telegram/messenger/NotificationCenter;->activeAccountChanged:I

    if-ne p1, p2, :cond_0

    .line 102
    sget p1, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    iput p1, p0, Lorg/telegram/messenger/car/HomeScreen;->currentAccount:I

    const/4 p1, 0x0

    .line 103
    iput-boolean p1, p0, Lorg/telegram/messenger/car/HomeScreen;->musicLoadKicked:Z

    .line 104
    invoke-virtual {p0}, Landroidx/car/app/Screen;->invalidate()V

    return-void

    .line 105
    :cond_0
    sget p2, Lorg/telegram/messenger/NotificationCenter;->pushMessagesUpdated:I

    if-eq p1, p2, :cond_1

    sget p2, Lorg/telegram/messenger/NotificationCenter;->notificationsCountUpdated:I

    if-ne p1, p2, :cond_2

    :cond_1
    const-string/jumbo p1, "tab_notifications"

    iget-object p2, p0, Lorg/telegram/messenger/car/HomeScreen;->activeTabId:Ljava/lang/String;

    .line 106
    invoke-virtual {p1, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_2

    .line 107
    invoke-virtual {p0}, Landroidx/car/app/Screen;->invalidate()V

    :cond_2
    return-void
.end method

.method public onGetTemplate()Landroidx/car/app/model/Template;
    .locals 3

    .line 114
    new-instance v0, Landroidx/car/app/model/TabTemplate$Builder;

    new-instance v1, Lorg/telegram/messenger/car/HomeScreen$1;

    invoke-direct {v1, p0}, Lorg/telegram/messenger/car/HomeScreen$1;-><init>(Lorg/telegram/messenger/car/HomeScreen;)V

    invoke-direct {v0, v1}, Landroidx/car/app/model/TabTemplate$Builder;-><init>(Landroidx/car/app/model/TabTemplate$TabCallback;)V

    .line 121
    sget-object v1, Landroidx/car/app/model/Action;->APP_ICON:Landroidx/car/app/model/Action;

    invoke-virtual {v0, v1}, Landroidx/car/app/model/TabTemplate$Builder;->setHeaderAction(Landroidx/car/app/model/Action;)Landroidx/car/app/model/TabTemplate$Builder;

    .line 123
    new-instance v1, Landroidx/car/app/model/Tab$Builder;

    invoke-direct {v1}, Landroidx/car/app/model/Tab$Builder;-><init>()V

    const-string/jumbo v2, "tab_notifications"

    .line 124
    invoke-virtual {v1, v2}, Landroidx/car/app/model/Tab$Builder;->setContentId(Ljava/lang/String;)Landroidx/car/app/model/Tab$Builder;

    move-result-object v1

    sget v2, Lorg/telegram/messenger/R$drawable;->msg_notifications:I

    .line 125
    invoke-direct {p0, v2}, Lorg/telegram/messenger/car/HomeScreen;->iconResource(I)Landroidx/car/app/model/CarIcon;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroidx/car/app/model/Tab$Builder;->setIcon(Landroidx/car/app/model/CarIcon;)Landroidx/car/app/model/Tab$Builder;

    move-result-object v1

    sget v2, Lorg/telegram/messenger/R$string;->Notifications:I

    .line 126
    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroidx/car/app/model/Tab$Builder;->setTitle(Ljava/lang/CharSequence;)Landroidx/car/app/model/Tab$Builder;

    move-result-object v1

    .line 127
    invoke-virtual {v1}, Landroidx/car/app/model/Tab$Builder;->build()Landroidx/car/app/model/Tab;

    move-result-object v1

    .line 123
    invoke-virtual {v0, v1}, Landroidx/car/app/model/TabTemplate$Builder;->addTab(Landroidx/car/app/model/Tab;)Landroidx/car/app/model/TabTemplate$Builder;

    .line 128
    new-instance v1, Landroidx/car/app/model/Tab$Builder;

    invoke-direct {v1}, Landroidx/car/app/model/Tab$Builder;-><init>()V

    const-string/jumbo v2, "tab_music"

    .line 129
    invoke-virtual {v1, v2}, Landroidx/car/app/model/Tab$Builder;->setContentId(Ljava/lang/String;)Landroidx/car/app/model/Tab$Builder;

    move-result-object v1

    sget v2, Lorg/telegram/messenger/R$drawable;->filled_widget_music:I

    .line 130
    invoke-direct {p0, v2}, Lorg/telegram/messenger/car/HomeScreen;->iconResource(I)Landroidx/car/app/model/CarIcon;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroidx/car/app/model/Tab$Builder;->setIcon(Landroidx/car/app/model/CarIcon;)Landroidx/car/app/model/Tab$Builder;

    move-result-object v1

    sget v2, Lorg/telegram/messenger/R$string;->Music:I

    .line 131
    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroidx/car/app/model/Tab$Builder;->setTitle(Ljava/lang/CharSequence;)Landroidx/car/app/model/Tab$Builder;

    move-result-object v1

    .line 132
    invoke-virtual {v1}, Landroidx/car/app/model/Tab$Builder;->build()Landroidx/car/app/model/Tab;

    move-result-object v1

    .line 128
    invoke-virtual {v0, v1}, Landroidx/car/app/model/TabTemplate$Builder;->addTab(Landroidx/car/app/model/Tab;)Landroidx/car/app/model/TabTemplate$Builder;

    .line 134
    iget-object v1, p0, Lorg/telegram/messenger/car/HomeScreen;->activeTabId:Ljava/lang/String;

    invoke-virtual {v0, v1}, Landroidx/car/app/model/TabTemplate$Builder;->setActiveTabContentId(Ljava/lang/String;)Landroidx/car/app/model/TabTemplate$Builder;

    .line 135
    new-instance v1, Landroidx/car/app/model/TabContents$Builder;

    iget-object v2, p0, Lorg/telegram/messenger/car/HomeScreen;->activeTabId:Ljava/lang/String;

    invoke-direct {p0, v2}, Lorg/telegram/messenger/car/HomeScreen;->buildTabContent(Ljava/lang/String;)Landroidx/car/app/model/Template;

    move-result-object p0

    invoke-direct {v1, p0}, Landroidx/car/app/model/TabContents$Builder;-><init>(Landroidx/car/app/model/Template;)V

    invoke-virtual {v1}, Landroidx/car/app/model/TabContents$Builder;->build()Landroidx/car/app/model/TabContents;

    move-result-object p0

    invoke-virtual {v0, p0}, Landroidx/car/app/model/TabTemplate$Builder;->setTabContents(Landroidx/car/app/model/TabContents;)Landroidx/car/app/model/TabTemplate$Builder;

    .line 136
    invoke-virtual {v0}, Landroidx/car/app/model/TabTemplate$Builder;->build()Landroidx/car/app/model/TabTemplate;

    move-result-object p0

    return-object p0
.end method

.method public onPause(Landroidx/lifecycle/LifecycleOwner;)V
    .locals 1

    .line 94
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget v0, Lorg/telegram/messenger/NotificationCenter;->pushMessagesUpdated:I

    invoke-virtual {p1, p0, v0}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 95
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget v0, Lorg/telegram/messenger/NotificationCenter;->notificationsCountUpdated:I

    invoke-virtual {p1, p0, v0}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 96
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget v0, Lorg/telegram/messenger/NotificationCenter;->activeAccountChanged:I

    invoke-virtual {p1, p0, v0}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    return-void
.end method

.method public onResume(Landroidx/lifecycle/LifecycleOwner;)V
    .locals 1

    .line 87
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget v0, Lorg/telegram/messenger/NotificationCenter;->pushMessagesUpdated:I

    invoke-virtual {p1, p0, v0}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 88
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget v0, Lorg/telegram/messenger/NotificationCenter;->notificationsCountUpdated:I

    invoke-virtual {p1, p0, v0}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 89
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget v0, Lorg/telegram/messenger/NotificationCenter;->activeAccountChanged:I

    invoke-virtual {p1, p0, v0}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    return-void
.end method
