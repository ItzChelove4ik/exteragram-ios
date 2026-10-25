.class public Lorg/telegram/messenger/car/MusicSongsScreen;
.super Landroidx/car/app/Screen;
.source "SourceFile"

# interfaces
.implements Landroidx/lifecycle/DefaultLifecycleObserver;
.implements Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;


# instance fields
.field private final dialogId:J

.field private final title:Ljava/lang/String;


# direct methods
.method public static synthetic $r8$lambda$0aIkc1iNQsT3k6dPRZTlsLUOY04(Lorg/telegram/messenger/car/MusicSongsScreen;I)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/messenger/car/MusicSongsScreen;->lambda$onGetTemplate$0(I)V

    return-void
.end method

.method public constructor <init>(Landroidx/car/app/CarContext;JLjava/lang/String;)V
    .locals 0

    .line 38
    invoke-direct {p0, p1}, Landroidx/car/app/Screen;-><init>(Landroidx/car/app/CarContext;)V

    .line 39
    iput-wide p2, p0, Lorg/telegram/messenger/car/MusicSongsScreen;->dialogId:J

    if-eqz p4, :cond_0

    goto :goto_0

    .line 40
    :cond_0
    const-string p4, ""

    :goto_0
    iput-object p4, p0, Lorg/telegram/messenger/car/MusicSongsScreen;->title:Ljava/lang/String;

    .line 41
    invoke-virtual {p0}, Landroidx/car/app/Screen;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object p1

    invoke-virtual {p1, p0}, Landroidx/lifecycle/Lifecycle;->addObserver(Landroidx/lifecycle/LifecycleObserver;)V

    return-void
.end method

.method private synthetic lambda$onGetTemplate$0(I)V
    .locals 0

    .line 117
    invoke-direct {p0, p1}, Lorg/telegram/messenger/car/MusicSongsScreen;->playAtIndex(I)V

    return-void
.end method

.method private playAtIndex(I)V
    .locals 4

    .line 130
    :try_start_0
    invoke-virtual {p0}, Landroidx/car/app/Screen;->getCarContext()Landroidx/car/app/CarContext;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/messenger/TelegramMediaSession;->getInstance(Landroid/content/Context;)Lorg/telegram/messenger/TelegramMediaSession;

    move-result-object v0

    .line 131
    invoke-virtual {v0}, Lorg/telegram/messenger/TelegramMediaSession;->getSession()Landroid/support/v4/media/session/MediaSessionCompat;

    move-result-object v0

    invoke-virtual {v0}, Landroid/support/v4/media/session/MediaSessionCompat;->getController()Landroid/support/v4/media/session/MediaControllerCompat;

    move-result-object v0

    .line 132
    invoke-virtual {v0}, Landroid/support/v4/media/session/MediaControllerCompat;->getTransportControls()Landroid/support/v4/media/session/MediaControllerCompat$TransportControls;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    iget-wide v2, p0, Lorg/telegram/messenger/car/MusicSongsScreen;->dialogId:J

    invoke-virtual {v1, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p0, "_"

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    const/4 p1, 0x0

    invoke-virtual {v0, p0, p1}, Landroid/support/v4/media/session/MediaControllerCompat$TransportControls;->playFromMediaId(Ljava/lang/String;Landroid/os/Bundle;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    :catchall_0
    return-void
.end method


# virtual methods
.method public varargs didReceivedNotification(II[Ljava/lang/Object;)V
    .locals 0

    .line 64
    sget p2, Lorg/telegram/messenger/NotificationCenter;->activeAccountChanged:I

    if-ne p1, p2, :cond_0

    .line 65
    invoke-virtual {p0}, Landroidx/car/app/Screen;->getScreenManager()Landroidx/car/app/ScreenManager;

    move-result-object p0

    invoke-virtual {p0}, Landroidx/car/app/ScreenManager;->pop()V

    return-void

    .line 68
    :cond_0
    invoke-virtual {p0}, Landroidx/car/app/Screen;->invalidate()V

    return-void
.end method

.method public onGetTemplate()Landroidx/car/app/model/Template;
    .locals 15

    .line 74
    invoke-virtual {p0}, Landroidx/car/app/Screen;->getCarContext()Landroidx/car/app/CarContext;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/messenger/TelegramMediaSession;->getInstance(Landroid/content/Context;)Lorg/telegram/messenger/TelegramMediaSession;

    move-result-object v0

    .line 75
    iget-object v1, p0, Lorg/telegram/messenger/car/MusicSongsScreen;->title:Ljava/lang/String;

    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    const-string v2, " "

    if-eqz v1, :cond_0

    move-object v1, v2

    goto :goto_0

    :cond_0
    iget-object v1, p0, Lorg/telegram/messenger/car/MusicSongsScreen;->title:Ljava/lang/String;

    .line 77
    :goto_0
    iget-wide v3, p0, Lorg/telegram/messenger/car/MusicSongsScreen;->dialogId:J

    invoke-virtual {v0, v3, v4}, Lorg/telegram/messenger/TelegramMediaSession;->getMusicMessages(J)Ljava/util/ArrayList;

    move-result-object v3

    if-nez v3, :cond_1

    .line 79
    iget-wide v2, p0, Lorg/telegram/messenger/car/MusicSongsScreen;->dialogId:J

    new-instance v4, Lorg/telegram/messenger/car/MusicSongsScreen$$ExternalSyntheticLambda0;

    invoke-direct {v4, p0}, Lorg/telegram/messenger/car/MusicSongsScreen$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/messenger/car/MusicSongsScreen;)V

    invoke-virtual {v0, v2, v3, v4}, Lorg/telegram/messenger/TelegramMediaSession;->loadMusicForDialog(JLjava/lang/Runnable;)V

    .line 80
    new-instance p0, Landroidx/car/app/model/ListTemplate$Builder;

    invoke-direct {p0}, Landroidx/car/app/model/ListTemplate$Builder;-><init>()V

    .line 81
    invoke-virtual {p0, v1}, Landroidx/car/app/model/ListTemplate$Builder;->setTitle(Ljava/lang/CharSequence;)Landroidx/car/app/model/ListTemplate$Builder;

    move-result-object p0

    sget-object v0, Landroidx/car/app/model/Action;->BACK:Landroidx/car/app/model/Action;

    .line 82
    invoke-virtual {p0, v0}, Landroidx/car/app/model/ListTemplate$Builder;->setHeaderAction(Landroidx/car/app/model/Action;)Landroidx/car/app/model/ListTemplate$Builder;

    move-result-object p0

    const/4 v0, 0x1

    .line 83
    invoke-virtual {p0, v0}, Landroidx/car/app/model/ListTemplate$Builder;->setLoading(Z)Landroidx/car/app/model/ListTemplate$Builder;

    move-result-object p0

    .line 84
    invoke-virtual {p0}, Landroidx/car/app/model/ListTemplate$Builder;->build()Landroidx/car/app/model/ListTemplate;

    move-result-object p0

    return-object p0

    .line 86
    :cond_1
    invoke-virtual {v3}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_2

    .line 87
    new-instance p0, Landroidx/car/app/model/MessageTemplate$Builder;

    sget v0, Lorg/telegram/messenger/R$string;->NoCarMusic:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Landroidx/car/app/model/MessageTemplate$Builder;-><init>(Ljava/lang/CharSequence;)V

    .line 88
    invoke-virtual {p0, v1}, Landroidx/car/app/model/MessageTemplate$Builder;->setTitle(Ljava/lang/CharSequence;)Landroidx/car/app/model/MessageTemplate$Builder;

    move-result-object p0

    sget-object v0, Landroidx/car/app/model/Action;->BACK:Landroidx/car/app/model/Action;

    .line 89
    invoke-virtual {p0, v0}, Landroidx/car/app/model/MessageTemplate$Builder;->setHeaderAction(Landroidx/car/app/model/Action;)Landroidx/car/app/model/MessageTemplate$Builder;

    move-result-object p0

    .line 90
    invoke-virtual {p0}, Landroidx/car/app/model/MessageTemplate$Builder;->build()Landroidx/car/app/model/MessageTemplate;

    move-result-object p0

    return-object p0

    .line 93
    :cond_2
    invoke-static {}, Lorg/telegram/messenger/MediaController;->getInstance()Lorg/telegram/messenger/MediaController;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/messenger/MediaController;->getPlayingMessageObject()Lorg/telegram/messenger/MessageObject;

    move-result-object v0

    const-wide/16 v4, 0x0

    if-eqz v0, :cond_3

    .line 94
    invoke-virtual {v0}, Lorg/telegram/messenger/MessageObject;->getId()I

    move-result v6

    int-to-long v6, v6

    goto :goto_1

    :cond_3
    move-wide v6, v4

    :goto_1
    if-eqz v0, :cond_4

    .line 95
    invoke-virtual {v0}, Lorg/telegram/messenger/MessageObject;->getDialogId()J

    move-result-wide v4

    .line 97
    :cond_4
    new-instance v8, Landroidx/car/app/model/ItemList$Builder;

    invoke-direct {v8}, Landroidx/car/app/model/ItemList$Builder;-><init>()V

    .line 98
    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v9

    const/16 v10, 0xc8

    invoke-static {v9, v10}, Ljava/lang/Math;->min(II)I

    move-result v9

    const/4 v10, 0x0

    :goto_2
    if-ge v10, v9, :cond_9

    .line 100
    invoke-virtual {v3, v10}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v11

    check-cast v11, Lorg/telegram/messenger/MessageObject;

    if-nez v11, :cond_5

    goto :goto_4

    .line 102
    :cond_5
    invoke-virtual {v11}, Lorg/telegram/messenger/MessageObject;->getMusicTitle()Ljava/lang/String;

    move-result-object v12

    .line 103
    invoke-virtual {v11}, Lorg/telegram/messenger/MessageObject;->getMusicAuthor()Ljava/lang/String;

    move-result-object v13

    .line 105
    new-instance v14, Landroidx/car/app/model/Row$Builder;

    invoke-direct {v14}, Landroidx/car/app/model/Row$Builder;-><init>()V

    if-eqz v12, :cond_6

    goto :goto_3

    :cond_6
    move-object v12, v2

    .line 106
    :goto_3
    invoke-virtual {v14, v12}, Landroidx/car/app/model/Row$Builder;->setTitle(Ljava/lang/CharSequence;)Landroidx/car/app/model/Row$Builder;

    move-result-object v12

    if-eqz v13, :cond_7

    .line 107
    invoke-virtual {v13}, Ljava/lang/String;->isEmpty()Z

    move-result v14

    if-nez v14, :cond_7

    .line 108
    invoke-virtual {v12, v13}, Landroidx/car/app/model/Row$Builder;->addText(Ljava/lang/CharSequence;)Landroidx/car/app/model/Row$Builder;

    :cond_7
    if-eqz v0, :cond_8

    .line 111
    invoke-virtual {v11}, Lorg/telegram/messenger/MessageObject;->getDialogId()J

    move-result-wide v13

    cmp-long v13, v4, v13

    if-nez v13, :cond_8

    .line 112
    invoke-virtual {v11}, Lorg/telegram/messenger/MessageObject;->getId()I

    move-result v11

    int-to-long v13, v11

    cmp-long v11, v6, v13

    if-nez v11, :cond_8

    .line 114
    new-instance v11, Landroidx/car/app/model/CarIcon$Builder;

    invoke-virtual {p0}, Landroidx/car/app/Screen;->getCarContext()Landroidx/car/app/CarContext;

    move-result-object v13

    sget v14, Lorg/telegram/messenger/R$drawable;->ic_player:I

    invoke-static {v13, v14}, Landroidx/core/graphics/drawable/IconCompat;->createWithResource(Landroid/content/Context;I)Landroidx/core/graphics/drawable/IconCompat;

    move-result-object v13

    invoke-direct {v11, v13}, Landroidx/car/app/model/CarIcon$Builder;-><init>(Landroidx/core/graphics/drawable/IconCompat;)V

    invoke-virtual {v11}, Landroidx/car/app/model/CarIcon$Builder;->build()Landroidx/car/app/model/CarIcon;

    move-result-object v11

    invoke-virtual {v12, v11}, Landroidx/car/app/model/Row$Builder;->setImage(Landroidx/car/app/model/CarIcon;)Landroidx/car/app/model/Row$Builder;

    .line 117
    :cond_8
    new-instance v11, Lorg/telegram/messenger/car/MusicSongsScreen$$ExternalSyntheticLambda1;

    invoke-direct {v11, p0, v10}, Lorg/telegram/messenger/car/MusicSongsScreen$$ExternalSyntheticLambda1;-><init>(Lorg/telegram/messenger/car/MusicSongsScreen;I)V

    invoke-virtual {v12, v11}, Landroidx/car/app/model/Row$Builder;->setOnClickListener(Landroidx/car/app/model/OnClickListener;)Landroidx/car/app/model/Row$Builder;

    .line 118
    invoke-virtual {v12}, Landroidx/car/app/model/Row$Builder;->build()Landroidx/car/app/model/Row;

    move-result-object v11

    invoke-virtual {v8, v11}, Landroidx/car/app/model/ItemList$Builder;->addItem(Landroidx/car/app/model/Item;)Landroidx/car/app/model/ItemList$Builder;

    :goto_4
    add-int/lit8 v10, v10, 0x1

    goto :goto_2

    .line 121
    :cond_9
    new-instance p0, Landroidx/car/app/model/ListTemplate$Builder;

    invoke-direct {p0}, Landroidx/car/app/model/ListTemplate$Builder;-><init>()V

    .line 122
    invoke-virtual {p0, v1}, Landroidx/car/app/model/ListTemplate$Builder;->setTitle(Ljava/lang/CharSequence;)Landroidx/car/app/model/ListTemplate$Builder;

    move-result-object p0

    sget-object v0, Landroidx/car/app/model/Action;->BACK:Landroidx/car/app/model/Action;

    .line 123
    invoke-virtual {p0, v0}, Landroidx/car/app/model/ListTemplate$Builder;->setHeaderAction(Landroidx/car/app/model/Action;)Landroidx/car/app/model/ListTemplate$Builder;

    move-result-object p0

    .line 124
    invoke-virtual {v8}, Landroidx/car/app/model/ItemList$Builder;->build()Landroidx/car/app/model/ItemList;

    move-result-object v0

    invoke-virtual {p0, v0}, Landroidx/car/app/model/ListTemplate$Builder;->setSingleList(Landroidx/car/app/model/ItemList;)Landroidx/car/app/model/ListTemplate$Builder;

    move-result-object p0

    .line 125
    invoke-virtual {p0}, Landroidx/car/app/model/ListTemplate$Builder;->build()Landroidx/car/app/model/ListTemplate;

    move-result-object p0

    return-object p0
.end method

.method public onPause(Landroidx/lifecycle/LifecycleOwner;)V
    .locals 2

    const/4 p1, 0x0

    :goto_0
    const/16 v0, 0x10

    if-ge p1, v0, :cond_0

    .line 56
    invoke-static {p1}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->messagePlayingDidStart:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 57
    invoke-static {p1}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->messagePlayingPlayStateChanged:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 59
    :cond_0
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget v0, Lorg/telegram/messenger/NotificationCenter;->activeAccountChanged:I

    invoke-virtual {p1, p0, v0}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    return-void
.end method

.method public onResume(Landroidx/lifecycle/LifecycleOwner;)V
    .locals 2

    const/4 p1, 0x0

    :goto_0
    const/16 v0, 0x10

    if-ge p1, v0, :cond_0

    .line 47
    invoke-static {p1}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->messagePlayingDidStart:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 48
    invoke-static {p1}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->messagePlayingPlayStateChanged:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 50
    :cond_0
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget v0, Lorg/telegram/messenger/NotificationCenter;->activeAccountChanged:I

    invoke-virtual {p1, p0, v0}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    return-void
.end method
