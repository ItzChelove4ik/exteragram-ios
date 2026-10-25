.class public Lorg/telegram/messenger/pip/PipActivityController;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field public final activity:Landroid/app/Activity;

.field private final handler:Lorg/telegram/messenger/pip/PipActivityHandler;

.field private maxPrioritySource:Lorg/telegram/messenger/pip/PipSource;

.field private mediaSession:Landroidx/media3/session/MediaSession;

.field private pipContentView:Lorg/telegram/messenger/pip/PipActivityContentLayout;

.field private final sources:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Lorg/telegram/messenger/pip/PipSource;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Landroid/app/Activity;)V
    .locals 1

    .line 29
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 23
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/pip/PipActivityController;->sources:Ljava/util/HashMap;

    .line 30
    iput-object p1, p0, Lorg/telegram/messenger/pip/PipActivityController;->activity:Landroid/app/Activity;

    .line 31
    new-instance v0, Lorg/telegram/messenger/pip/PipActivityHandler;

    invoke-direct {v0, p1}, Lorg/telegram/messenger/pip/PipActivityHandler;-><init>(Landroid/app/Activity;)V

    iput-object v0, p0, Lorg/telegram/messenger/pip/PipActivityController;->handler:Lorg/telegram/messenger/pip/PipActivityHandler;

    .line 33
    sget-boolean p1, Lorg/telegram/messenger/BuildVars;->DEBUG_PRIVATE_VERSION:Z

    if-eqz p1, :cond_0

    .line 34
    new-instance p1, Lorg/telegram/messenger/pip/PipActivityController$1;

    invoke-direct {p1, p0}, Lorg/telegram/messenger/pip/PipActivityController$1;-><init>(Lorg/telegram/messenger/pip/PipActivityController;)V

    invoke-virtual {v0, p1}, Lorg/telegram/messenger/pip/PipActivityHandler;->addPipListener(Lorg/telegram/messenger/pip/activity/IPipActivityListener;)V

    .line 65
    new-instance p1, Lorg/telegram/messenger/pip/PipActivityController$2;

    invoke-direct {p1, p0}, Lorg/telegram/messenger/pip/PipActivityController$2;-><init>(Lorg/telegram/messenger/pip/PipActivityController;)V

    invoke-virtual {v0, p1}, Lorg/telegram/messenger/pip/PipActivityHandler;->addAnimationListener(Lorg/telegram/messenger/pip/activity/IPipActivityAnimationListener;)V

    :cond_0
    return-void
.end method

.method private onMaxPrioritySourceChanged(Lorg/telegram/messenger/pip/PipSource;Lorg/telegram/messenger/pip/PipSource;)V
    .locals 5

    .line 144
    new-instance v0, Ljava/lang/StringBuilder;

    const-string/jumbo v1, "onMaxPrioritySourceChanged "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    const/4 v1, 0x0

    if-eqz p2, :cond_0

    iget-object v2, p2, Lorg/telegram/messenger/pip/PipSource;->tag:Ljava/lang/String;

    goto :goto_0

    :cond_0
    move-object v2, v1

    :goto_0
    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v2, "PIP_DEBUG"

    invoke-static {v2, v0}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 146
    iget-object v0, p0, Lorg/telegram/messenger/pip/PipActivityController;->activity:Landroid/app/Activity;

    invoke-static {v0, p2}, Lorg/telegram/messenger/pip/utils/PipUtils;->applyPictureInPictureParams(Landroid/app/Activity;Lorg/telegram/messenger/pip/PipSource;)V

    const/4 v0, 0x1

    const/4 v2, 0x0

    if-eqz p1, :cond_1

    .line 148
    iget-boolean v3, p1, Lorg/telegram/messenger/pip/PipSource;->needMediaSession:Z

    if-eqz v3, :cond_1

    move v3, v0

    goto :goto_1

    :cond_1
    move v3, v2

    :goto_1
    if-eqz p2, :cond_2

    .line 149
    iget-boolean v4, p2, Lorg/telegram/messenger/pip/PipSource;->needMediaSession:Z

    if-eqz v4, :cond_2

    goto :goto_2

    :cond_2
    move v0, v2

    :goto_2
    if-eq v3, v0, :cond_4

    .line 151
    iget-object v0, p0, Lorg/telegram/messenger/pip/PipActivityController;->mediaSession:Landroidx/media3/session/MediaSession;

    if-eqz v0, :cond_3

    .line 152
    invoke-virtual {v0}, Landroidx/media3/session/MediaSession;->release()V

    .line 153
    iput-object v1, p0, Lorg/telegram/messenger/pip/PipActivityController;->mediaSession:Landroidx/media3/session/MediaSession;

    :cond_3
    if-eqz p2, :cond_4

    .line 158
    new-instance v0, Landroidx/media3/session/MediaSession$Builder;

    iget-object v1, p0, Lorg/telegram/messenger/pip/PipActivityController;->activity:Landroid/app/Activity;

    iget-object v3, p2, Lorg/telegram/messenger/pip/PipSource;->player:Landroidx/media3/common/Player;

    invoke-direct {v0, v1, v3}, Landroidx/media3/session/MediaSession$Builder;-><init>(Landroid/content/Context;Landroidx/media3/common/Player;)V

    const-string/jumbo v1, "pip-media-session"

    .line 159
    invoke-virtual {v0, v1}, Landroidx/media3/session/MediaSession$Builder;->setId(Ljava/lang/String;)Landroidx/media3/session/MediaSession$Builder;

    move-result-object v0

    .line 160
    invoke-virtual {v0}, Landroidx/media3/session/MediaSession$Builder;->build()Landroidx/media3/session/MediaSession;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/messenger/pip/PipActivityController;->mediaSession:Landroidx/media3/session/MediaSession;

    :cond_4
    if-eqz p1, :cond_5

    .line 167
    iget-object v0, p1, Lorg/telegram/messenger/pip/PipSource;->state2:Lorg/telegram/messenger/pip/source/PipSourceHandlerState2;

    invoke-virtual {v0}, Lorg/telegram/messenger/pip/source/PipSourceHandlerState2;->onLoseMaxPriority()V

    :cond_5
    if-eqz p2, :cond_7

    .line 171
    iget-object p1, p0, Lorg/telegram/messenger/pip/PipActivityController;->mediaSession:Landroidx/media3/session/MediaSession;

    if-eqz p1, :cond_6

    invoke-virtual {p1}, Landroidx/media3/session/MediaSession;->getPlayer()Landroidx/media3/common/Player;

    move-result-object p1

    iget-object v0, p2, Lorg/telegram/messenger/pip/PipSource;->player:Landroidx/media3/common/Player;

    if-eq p1, v0, :cond_6

    .line 172
    iget-object p1, p0, Lorg/telegram/messenger/pip/PipActivityController;->mediaSession:Landroidx/media3/session/MediaSession;

    invoke-virtual {p1, v0}, Landroidx/media3/session/MediaSession;->setPlayer(Landroidx/media3/common/Player;)V

    .line 174
    :cond_6
    iget-object p1, p0, Lorg/telegram/messenger/pip/PipActivityController;->pipContentView:Lorg/telegram/messenger/pip/PipActivityContentLayout;

    invoke-virtual {p1}, Landroid/view/View;->bringToFront()V

    .line 175
    iget-object p1, p2, Lorg/telegram/messenger/pip/PipSource;->state2:Lorg/telegram/messenger/pip/source/PipSourceHandlerState2;

    invoke-virtual {p1}, Lorg/telegram/messenger/pip/source/PipSourceHandlerState2;->onReceiveMaxPriority()V

    goto :goto_3

    :cond_7
    if-eqz p1, :cond_8

    .line 177
    iget-object p1, p0, Lorg/telegram/messenger/pip/PipActivityController;->activity:Landroid/app/Activity;

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->isInPictureInPictureMode(Landroid/app/Activity;)Z

    move-result p1

    if-eqz p1, :cond_8

    .line 178
    iget-object p1, p0, Lorg/telegram/messenger/pip/PipActivityController;->activity:Landroid/app/Activity;

    invoke-virtual {p1, v2}, Landroid/app/Activity;->moveTaskToBack(Z)Z

    .line 184
    :cond_8
    :goto_3
    iget-object p0, p0, Lorg/telegram/messenger/pip/PipActivityController;->pipContentView:Lorg/telegram/messenger/pip/PipActivityContentLayout;

    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method

.method private updateSources()V
    .locals 6

    .line 122
    iget-object v0, p0, Lorg/telegram/messenger/pip/PipActivityController;->maxPrioritySource:Lorg/telegram/messenger/pip/PipSource;

    .line 125
    iget-object v1, p0, Lorg/telegram/messenger/pip/PipActivityController;->sources:Ljava/util/HashMap;

    invoke-virtual {v1}, Ljava/util/HashMap;->values()Ljava/util/Collection;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v1

    const/4 v2, 0x0

    :cond_0
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_3

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lorg/telegram/messenger/pip/PipSource;

    .line 126
    invoke-virtual {v3}, Lorg/telegram/messenger/pip/PipSource;->isAvailable()Z

    move-result v4

    if-nez v4, :cond_1

    iget-object v4, v3, Lorg/telegram/messenger/pip/PipSource;->state2:Lorg/telegram/messenger/pip/source/PipSourceHandlerState2;

    invoke-virtual {v4}, Lorg/telegram/messenger/pip/source/PipSourceHandlerState2;->isAttachedToPip()Z

    move-result v4

    if-nez v4, :cond_1

    goto :goto_0

    :cond_1
    if-nez v2, :cond_2

    goto :goto_1

    .line 132
    :cond_2
    iget v4, v3, Lorg/telegram/messenger/pip/PipSource;->priority:I

    iget v5, v2, Lorg/telegram/messenger/pip/PipSource;->priority:I

    if-le v4, v5, :cond_0

    :goto_1
    move-object v2, v3

    goto :goto_0

    :cond_3
    if-eq v0, v2, :cond_4

    .line 138
    iput-object v2, p0, Lorg/telegram/messenger/pip/PipActivityController;->maxPrioritySource:Lorg/telegram/messenger/pip/PipSource;

    .line 139
    invoke-direct {p0, v0, v2}, Lorg/telegram/messenger/pip/PipActivityController;->onMaxPrioritySourceChanged(Lorg/telegram/messenger/pip/PipSource;Lorg/telegram/messenger/pip/PipSource;)V

    :cond_4
    return-void
.end method


# virtual methods
.method public addActionListener(Ljava/lang/String;Lorg/telegram/messenger/pip/activity/IPipActivityActionListener;)V
    .locals 0

    .line 246
    iget-object p0, p0, Lorg/telegram/messenger/pip/PipActivityController;->handler:Lorg/telegram/messenger/pip/PipActivityHandler;

    invoke-virtual {p0, p1, p2}, Lorg/telegram/messenger/pip/PipActivityHandler;->addActionListener(Ljava/lang/String;Lorg/telegram/messenger/pip/activity/IPipActivityActionListener;)V

    return-void
.end method

.method public addAnimationListener(Lorg/telegram/messenger/pip/activity/IPipActivityAnimationListener;)V
    .locals 0

    .line 238
    iget-object p0, p0, Lorg/telegram/messenger/pip/PipActivityController;->handler:Lorg/telegram/messenger/pip/PipActivityHandler;

    invoke-virtual {p0, p1}, Lorg/telegram/messenger/pip/PipActivityHandler;->addAnimationListener(Lorg/telegram/messenger/pip/activity/IPipActivityAnimationListener;)V

    return-void
.end method

.method public addPipListener(Lorg/telegram/messenger/pip/activity/IPipActivityListener;)V
    .locals 0

    .line 230
    iget-object p0, p0, Lorg/telegram/messenger/pip/PipActivityController;->handler:Lorg/telegram/messenger/pip/PipActivityHandler;

    invoke-virtual {p0, p1}, Lorg/telegram/messenger/pip/PipActivityHandler;->addPipListener(Lorg/telegram/messenger/pip/activity/IPipActivityListener;)V

    return-void
.end method

.method public dispatchSourceAvailabilityChanged(Lorg/telegram/messenger/pip/PipSource;)V
    .locals 0

    .line 207
    invoke-direct {p0}, Lorg/telegram/messenger/pip/PipActivityController;->updateSources()V

    .line 208
    iget-object p0, p0, Lorg/telegram/messenger/pip/PipActivityController;->pipContentView:Lorg/telegram/messenger/pip/PipActivityContentLayout;

    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method

.method public dispatchSourceParamsChanged(Lorg/telegram/messenger/pip/PipSource;)V
    .locals 1

    .line 212
    iget-object v0, p0, Lorg/telegram/messenger/pip/PipActivityController;->maxPrioritySource:Lorg/telegram/messenger/pip/PipSource;

    if-ne v0, p1, :cond_0

    .line 213
    iget-object v0, p0, Lorg/telegram/messenger/pip/PipActivityController;->activity:Landroid/app/Activity;

    invoke-static {v0, p1}, Lorg/telegram/messenger/pip/utils/PipUtils;->applyPictureInPictureParams(Landroid/app/Activity;Lorg/telegram/messenger/pip/PipSource;)V

    .line 214
    iget-object v0, p0, Lorg/telegram/messenger/pip/PipActivityController;->mediaSession:Landroidx/media3/session/MediaSession;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroidx/media3/session/MediaSession;->getPlayer()Landroidx/media3/common/Player;

    move-result-object v0

    iget-object p1, p1, Lorg/telegram/messenger/pip/PipSource;->player:Landroidx/media3/common/Player;

    if-eq v0, p1, :cond_0

    .line 215
    iget-object v0, p0, Lorg/telegram/messenger/pip/PipActivityController;->mediaSession:Landroidx/media3/session/MediaSession;

    invoke-virtual {v0, p1}, Landroidx/media3/session/MediaSession;->setPlayer(Landroidx/media3/common/Player;)V

    .line 218
    :cond_0
    iget-object p0, p0, Lorg/telegram/messenger/pip/PipActivityController;->pipContentView:Lorg/telegram/messenger/pip/PipActivityContentLayout;

    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method

.method public dispatchSourceRegister(Lorg/telegram/messenger/pip/PipSource;)V
    .locals 2

    .line 196
    iget-object v0, p0, Lorg/telegram/messenger/pip/PipActivityController;->sources:Ljava/util/HashMap;

    iget-object v1, p1, Lorg/telegram/messenger/pip/PipSource;->tag:Ljava/lang/String;

    invoke-virtual {v0, v1, p1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 197
    invoke-direct {p0}, Lorg/telegram/messenger/pip/PipActivityController;->updateSources()V

    return-void
.end method

.method public dispatchSourceUnregister(Lorg/telegram/messenger/pip/PipSource;)V
    .locals 1

    .line 201
    iget-object v0, p0, Lorg/telegram/messenger/pip/PipActivityController;->sources:Ljava/util/HashMap;

    iget-object p1, p1, Lorg/telegram/messenger/pip/PipSource;->tag:Ljava/lang/String;

    invoke-virtual {v0, p1}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 202
    invoke-direct {p0}, Lorg/telegram/messenger/pip/PipActivityController;->updateSources()V

    :cond_0
    return-void
.end method

.method public getHandler()Lorg/telegram/messenger/pip/activity/IPipActivityHandler;
    .locals 0

    .line 100
    iget-object p0, p0, Lorg/telegram/messenger/pip/PipActivityController;->handler:Lorg/telegram/messenger/pip/PipActivityHandler;

    return-object p0
.end method

.method public getPipContentView()Landroid/view/ViewGroup;
    .locals 2

    .line 104
    iget-object v0, p0, Lorg/telegram/messenger/pip/PipActivityController;->pipContentView:Lorg/telegram/messenger/pip/PipActivityContentLayout;

    if-nez v0, :cond_0

    .line 105
    new-instance v0, Lorg/telegram/messenger/pip/PipActivityContentLayout;

    iget-object v1, p0, Lorg/telegram/messenger/pip/PipActivityController;->activity:Landroid/app/Activity;

    invoke-direct {v0, v1}, Lorg/telegram/messenger/pip/PipActivityContentLayout;-><init>(Landroid/app/Activity;)V

    iput-object v0, p0, Lorg/telegram/messenger/pip/PipActivityController;->pipContentView:Lorg/telegram/messenger/pip/PipActivityContentLayout;

    .line 108
    :cond_0
    iget-object p0, p0, Lorg/telegram/messenger/pip/PipActivityController;->pipContentView:Lorg/telegram/messenger/pip/PipActivityContentLayout;

    return-object p0
.end method

.method public hasContentForPictureInPictureMode()Z
    .locals 0

    .line 226
    iget-object p0, p0, Lorg/telegram/messenger/pip/PipActivityController;->maxPrioritySource:Lorg/telegram/messenger/pip/PipSource;

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public removeActionListener(Ljava/lang/String;Lorg/telegram/messenger/pip/activity/IPipActivityActionListener;)V
    .locals 0

    .line 250
    iget-object p0, p0, Lorg/telegram/messenger/pip/PipActivityController;->handler:Lorg/telegram/messenger/pip/PipActivityHandler;

    invoke-virtual {p0, p1, p2}, Lorg/telegram/messenger/pip/PipActivityHandler;->removeActionListener(Ljava/lang/String;Lorg/telegram/messenger/pip/activity/IPipActivityActionListener;)V

    return-void
.end method

.method public removeAnimationListener(Lorg/telegram/messenger/pip/activity/IPipActivityAnimationListener;)V
    .locals 0

    .line 242
    iget-object p0, p0, Lorg/telegram/messenger/pip/PipActivityController;->handler:Lorg/telegram/messenger/pip/PipActivityHandler;

    invoke-virtual {p0, p1}, Lorg/telegram/messenger/pip/PipActivityHandler;->removeAnimationListener(Lorg/telegram/messenger/pip/activity/IPipActivityAnimationListener;)V

    return-void
.end method

.method public removePipListener(Lorg/telegram/messenger/pip/activity/IPipActivityListener;)V
    .locals 0

    .line 234
    iget-object p0, p0, Lorg/telegram/messenger/pip/PipActivityController;->handler:Lorg/telegram/messenger/pip/PipActivityHandler;

    invoke-virtual {p0, p1}, Lorg/telegram/messenger/pip/PipActivityHandler;->removePipListener(Lorg/telegram/messenger/pip/activity/IPipActivityListener;)V

    return-void
.end method
