.class public Lorg/telegram/ui/DownloadProgressIcon;
.super Landroid/view/View;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/ui/DownloadProgressIcon$ProgressObserver;
    }
.end annotation


# instance fields
.field private currentAccount:I

.field currentColor:I

.field currentListeners:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/DownloadProgressIcon$ProgressObserver;",
            ">;"
        }
    .end annotation
.end field

.field currentProgress:F

.field downloadCompleteDrawable:Lorg/telegram/ui/Components/RLottieDrawable;

.field downloadCompleteImageReceiver:Lorg/telegram/messenger/ImageReceiver;

.field downloadDrawable:Lorg/telegram/ui/Components/RLottieDrawable;

.field downloadImageReceiver:Lorg/telegram/messenger/ImageReceiver;

.field paint:Landroid/graphics/Paint;

.field paint2:Landroid/graphics/Paint;

.field progress:F

.field progressDt:F

.field showCompletedIcon:Z

.field private wasDrawn:Z


# direct methods
.method public constructor <init>(ILandroid/content/Context;)V
    .locals 8

    .line 43
    invoke-direct {p0, p2}, Landroid/view/View;-><init>(Landroid/content/Context;)V

    .line 25
    new-instance p2, Landroid/graphics/Paint;

    const/4 v0, 0x1

    invoke-direct {p2, v0}, Landroid/graphics/Paint;-><init>(I)V

    iput-object p2, p0, Lorg/telegram/ui/DownloadProgressIcon;->paint:Landroid/graphics/Paint;

    .line 26
    new-instance p2, Landroid/graphics/Paint;

    invoke-direct {p2, v0}, Landroid/graphics/Paint;-><init>(I)V

    iput-object p2, p0, Lorg/telegram/ui/DownloadProgressIcon;->paint2:Landroid/graphics/Paint;

    .line 28
    new-instance p2, Ljava/util/ArrayList;

    invoke-direct {p2}, Ljava/util/ArrayList;-><init>()V

    iput-object p2, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentListeners:Ljava/util/ArrayList;

    .line 33
    new-instance p2, Lorg/telegram/messenger/ImageReceiver;

    invoke-direct {p2, p0}, Lorg/telegram/messenger/ImageReceiver;-><init>(Landroid/view/View;)V

    iput-object p2, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    .line 34
    new-instance p2, Lorg/telegram/messenger/ImageReceiver;

    invoke-direct {p2, p0}, Lorg/telegram/messenger/ImageReceiver;-><init>(Landroid/view/View;)V

    iput-object p2, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadCompleteImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    .line 44
    iput p1, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentAccount:I

    .line 45
    iget-object p1, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    iput-boolean v0, p1, Lorg/telegram/messenger/ImageReceiver;->ignoreNotifications:Z

    .line 46
    iput-boolean v0, p2, Lorg/telegram/messenger/ImageReceiver;->ignoreNotifications:Z

    .line 48
    new-instance v1, Lorg/telegram/ui/Components/RLottieDrawable;

    sget v2, Lorg/telegram/messenger/R$raw;->download_progress:I

    const/high16 p1, 0x41e00000    # 28.0f

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    const/4 v5, 0x1

    const/4 v6, 0x0

    invoke-direct/range {v1 .. v6}, Lorg/telegram/ui/Components/RLottieDrawable;-><init>(IIIZ[I)V

    iput-object v1, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadDrawable:Lorg/telegram/ui/Components/RLottieDrawable;

    .line 49
    new-instance v2, Lorg/telegram/ui/Components/RLottieDrawable;

    sget v3, Lorg/telegram/messenger/R$raw;->download_finish:I

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    const/4 v6, 0x1

    const/4 v7, 0x0

    invoke-direct/range {v2 .. v7}, Lorg/telegram/ui/Components/RLottieDrawable;-><init>(IIIZ[I)V

    iput-object v2, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadCompleteDrawable:Lorg/telegram/ui/Components/RLottieDrawable;

    .line 51
    iget-object p1, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadDrawable:Lorg/telegram/ui/Components/RLottieDrawable;

    sget p2, Lorg/telegram/ui/ActionBar/Theme;->key_actionBarDefaultIcon:I

    invoke-static {p2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result v1

    const-string v2, "Shape Layer 3"

    invoke-virtual {p1, v2, v1}, Lorg/telegram/ui/Components/RLottieDrawable;->setLayerColor(Ljava/lang/String;I)V

    .line 52
    iget-object p1, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadDrawable:Lorg/telegram/ui/Components/RLottieDrawable;

    const-string v1, "Arrow 4"

    invoke-static {p2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result v2

    invoke-virtual {p1, v1, v2}, Lorg/telegram/ui/Components/RLottieDrawable;->setLayerColor(Ljava/lang/String;I)V

    .line 53
    iget-object p1, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadCompleteDrawable:Lorg/telegram/ui/Components/RLottieDrawable;

    const-string v1, "Shape Layer 8"

    invoke-static {p2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result v2

    invoke-virtual {p1, v1, v2}, Lorg/telegram/ui/Components/RLottieDrawable;->setLayerColor(Ljava/lang/String;I)V

    .line 54
    iget-object p1, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadCompleteDrawable:Lorg/telegram/ui/Components/RLottieDrawable;

    const-string v1, "Arrow 8"

    invoke-static {p2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result p2

    invoke-virtual {p1, v1, p2}, Lorg/telegram/ui/Components/RLottieDrawable;->setLayerColor(Ljava/lang/String;I)V

    .line 57
    iget-object p1, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    iget-object p2, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadDrawable:Lorg/telegram/ui/Components/RLottieDrawable;

    invoke-virtual {p1, p2}, Lorg/telegram/messenger/ImageReceiver;->setImageBitmap(Landroid/graphics/drawable/Drawable;)V

    .line 58
    iget-object p1, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadCompleteImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    iget-object p2, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadCompleteDrawable:Lorg/telegram/ui/Components/RLottieDrawable;

    invoke-virtual {p1, p2}, Lorg/telegram/messenger/ImageReceiver;->setImageBitmap(Landroid/graphics/drawable/Drawable;)V

    .line 60
    iget-object p1, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {p1, v0}, Lorg/telegram/messenger/ImageReceiver;->setAutoRepeat(I)V

    .line 61
    iget-object p1, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadDrawable:Lorg/telegram/ui/Components/RLottieDrawable;

    invoke-virtual {p1, v0}, Lorg/telegram/ui/Components/RLottieDrawable;->setAutoRepeat(I)V

    .line 62
    iget-object p0, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadDrawable:Lorg/telegram/ui/Components/RLottieDrawable;

    invoke-virtual {p0}, Lorg/telegram/ui/Components/RLottieDrawable;->start()V

    return-void
.end method

.method private detachCurrentListeners()V
    .locals 3

    const/4 v0, 0x0

    .line 216
    :goto_0
    iget-object v1, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentListeners:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    if-ge v0, v1, :cond_0

    .line 217
    iget v1, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentAccount:I

    invoke-static {v1}, Lorg/telegram/messenger/DownloadController;->getInstance(I)Lorg/telegram/messenger/DownloadController;

    move-result-object v1

    iget-object v2, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentListeners:Ljava/util/ArrayList;

    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lorg/telegram/messenger/DownloadController$FileDownloadProgressListener;

    invoke-virtual {v1, v2}, Lorg/telegram/messenger/DownloadController;->removeLoadingFileObserver(Lorg/telegram/messenger/DownloadController$FileDownloadProgressListener;)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 219
    :cond_0
    iget-object p0, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentListeners:Ljava/util/ArrayList;

    invoke-virtual {p0}, Ljava/util/ArrayList;->clear()V

    return-void
.end method

.method private updateDownloadingListeners()V
    .locals 7

    .line 161
    iget v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/DownloadController;->getInstance(I)Lorg/telegram/messenger/DownloadController;

    move-result-object v0

    .line 162
    new-instance v1, Ljava/util/HashMap;

    invoke-direct {v1}, Ljava/util/HashMap;-><init>()V

    const/4 v2, 0x0

    move v3, v2

    .line 163
    :goto_0
    iget-object v4, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentListeners:Ljava/util/ArrayList;

    invoke-virtual {v4}, Ljava/util/ArrayList;->size()I

    move-result v4

    .line 167
    iget-object v5, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentListeners:Ljava/util/ArrayList;

    if-ge v3, v4, :cond_0

    .line 164
    invoke-virtual {v5, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lorg/telegram/ui/DownloadProgressIcon$ProgressObserver;

    invoke-static {v4}, Lorg/telegram/ui/DownloadProgressIcon$ProgressObserver;->-$$Nest$fgetfileName(Lorg/telegram/ui/DownloadProgressIcon$ProgressObserver;)Ljava/lang/String;

    move-result-object v4

    iget-object v5, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentListeners:Ljava/util/ArrayList;

    invoke-virtual {v5, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lorg/telegram/ui/DownloadProgressIcon$ProgressObserver;

    invoke-virtual {v1, v4, v5}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 165
    iget v4, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentAccount:I

    invoke-static {v4}, Lorg/telegram/messenger/DownloadController;->getInstance(I)Lorg/telegram/messenger/DownloadController;

    move-result-object v4

    iget-object v5, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentListeners:Ljava/util/ArrayList;

    invoke-virtual {v5, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lorg/telegram/messenger/DownloadController$FileDownloadProgressListener;

    invoke-virtual {v4, v5}, Lorg/telegram/messenger/DownloadController;->removeLoadingFileObserver(Lorg/telegram/messenger/DownloadController$FileDownloadProgressListener;)V

    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 167
    :cond_0
    invoke-virtual {v5}, Ljava/util/ArrayList;->clear()V

    move v3, v2

    .line 168
    :goto_1
    iget-object v4, v0, Lorg/telegram/messenger/DownloadController;->downloadingFiles:Ljava/util/ArrayList;

    invoke-virtual {v4}, Ljava/util/ArrayList;->size()I

    move-result v4

    if-ge v3, v4, :cond_3

    .line 169
    iget-object v4, v0, Lorg/telegram/messenger/DownloadController;->downloadingFiles:Ljava/util/ArrayList;

    invoke-virtual {v4, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lorg/telegram/messenger/MessageObject;

    invoke-virtual {v4}, Lorg/telegram/messenger/MessageObject;->getFileName()Ljava/lang/String;

    move-result-object v4

    .line 170
    iget v5, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentAccount:I

    invoke-static {v5}, Lorg/telegram/messenger/FileLoader;->getInstance(I)Lorg/telegram/messenger/FileLoader;

    move-result-object v5

    invoke-virtual {v5, v4}, Lorg/telegram/messenger/FileLoader;->isLoadingFile(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_2

    .line 171
    invoke-virtual {v1, v4}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lorg/telegram/ui/DownloadProgressIcon$ProgressObserver;

    if-nez v5, :cond_1

    .line 173
    new-instance v5, Lorg/telegram/ui/DownloadProgressIcon$ProgressObserver;

    const/4 v6, 0x0

    invoke-direct {v5, p0, v4, v6}, Lorg/telegram/ui/DownloadProgressIcon$ProgressObserver;-><init>(Lorg/telegram/ui/DownloadProgressIcon;Ljava/lang/String;Lorg/telegram/ui/DownloadProgressIcon-IA;)V

    .line 175
    :cond_1
    iget v6, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentAccount:I

    invoke-static {v6}, Lorg/telegram/messenger/DownloadController;->getInstance(I)Lorg/telegram/messenger/DownloadController;

    move-result-object v6

    invoke-virtual {v6, v4, v5}, Lorg/telegram/messenger/DownloadController;->addLoadingFileObserver(Ljava/lang/String;Lorg/telegram/messenger/DownloadController$FileDownloadProgressListener;)V

    .line 176
    iget-object v4, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentListeners:Ljava/util/ArrayList;

    invoke-virtual {v4, v5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_2
    add-int/lit8 v3, v3, 0x1

    goto :goto_1

    .line 179
    :cond_3
    iget-object v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentListeners:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-nez v0, :cond_5

    iget-boolean v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->wasDrawn:Z

    if-nez v0, :cond_5

    .line 180
    iget v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/DownloadController;->getInstance(I)Lorg/telegram/messenger/DownloadController;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/messenger/DownloadController;->hasUnviewedDownloads()Z

    move-result v0

    if-eqz v0, :cond_4

    const/high16 v0, 0x3f800000    # 1.0f

    .line 181
    iput v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->progress:F

    .line 182
    iput v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentProgress:F

    const/4 v0, 0x1

    .line 183
    iput-boolean v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->showCompletedIcon:Z

    return-void

    :cond_4
    const/4 v0, 0x0

    .line 185
    iput v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->progress:F

    .line 186
    iput v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentProgress:F

    .line 187
    iput-boolean v2, p0, Lorg/telegram/ui/DownloadProgressIcon;->showCompletedIcon:Z

    :cond_5
    return-void
.end method


# virtual methods
.method public varargs didReceivedNotification(II[Ljava/lang/Object;)V
    .locals 0

    .line 224
    sget p2, Lorg/telegram/messenger/NotificationCenter;->onDownloadingFilesChanged:I

    if-ne p1, p2, :cond_0

    .line 225
    invoke-direct {p0}, Lorg/telegram/ui/DownloadProgressIcon;->updateDownloadingListeners()V

    .line 226
    invoke-virtual {p0}, Lorg/telegram/ui/DownloadProgressIcon;->updateProgress()V

    :cond_0
    return-void
.end method

.method public onAttachedToWindow()V
    .locals 2

    .line 143
    invoke-super {p0}, Landroid/view/View;->onAttachedToWindow()V

    .line 144
    invoke-direct {p0}, Lorg/telegram/ui/DownloadProgressIcon;->updateDownloadingListeners()V

    .line 145
    iget v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->onDownloadingFilesChanged:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 146
    iget-object v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0}, Lorg/telegram/messenger/ImageReceiver;->onAttachedToWindow()Z

    .line 147
    iget-object p0, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadCompleteImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {p0}, Lorg/telegram/messenger/ImageReceiver;->onAttachedToWindow()Z

    return-void
.end method

.method public onDetachedFromWindow()V
    .locals 2

    .line 152
    invoke-super {p0}, Landroid/view/View;->onDetachedFromWindow()V

    .line 153
    invoke-direct {p0}, Lorg/telegram/ui/DownloadProgressIcon;->detachCurrentListeners()V

    .line 154
    iget v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/NotificationCenter;->getInstance(I)Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->onDownloadingFilesChanged:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 155
    iget-object v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0}, Lorg/telegram/messenger/ImageReceiver;->onDetachedFromWindow()V

    .line 156
    iget-object p0, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadCompleteImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {p0}, Lorg/telegram/messenger/ImageReceiver;->onDetachedFromWindow()V

    return-void
.end method

.method public onDraw(Landroid/graphics/Canvas;)V
    .locals 9

    .line 81
    invoke-super {p0, p1}, Landroid/view/View;->onDraw(Landroid/graphics/Canvas;)V

    .line 82
    invoke-virtual {p0}, Landroid/view/View;->getAlpha()F

    move-result v0

    const/4 v1, 0x0

    cmpl-float v0, v0, v1

    if-nez v0, :cond_0

    goto/16 :goto_2

    .line 86
    :cond_0
    iget v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentColor:I

    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_actionBarDefaultIcon:I

    invoke-static {v2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result v3

    if-eq v0, v3, :cond_1

    .line 87
    invoke-static {v2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result v0

    iput v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentColor:I

    .line 88
    iget-object v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->paint:Landroid/graphics/Paint;

    invoke-static {v2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result v3

    invoke-virtual {v0, v3}, Landroid/graphics/Paint;->setColor(I)V

    .line 89
    iget-object v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->paint2:Landroid/graphics/Paint;

    invoke-static {v2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result v3

    invoke-virtual {v0, v3}, Landroid/graphics/Paint;->setColor(I)V

    .line 90
    iget-object v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    new-instance v3, Landroid/graphics/PorterDuffColorFilter;

    invoke-static {v2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result v4

    sget-object v5, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v3, v4, v5}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v0, v3}, Lorg/telegram/messenger/ImageReceiver;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 91
    iget-object v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadCompleteImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    new-instance v3, Landroid/graphics/PorterDuffColorFilter;

    invoke-static {v2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result v2

    invoke-direct {v3, v2, v5}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v0, v3}, Lorg/telegram/messenger/ImageReceiver;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 92
    iget-object v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->paint2:Landroid/graphics/Paint;

    const/16 v2, 0x64

    invoke-virtual {v0, v2}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 95
    :cond_1
    iget v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentProgress:F

    iget v2, p0, Lorg/telegram/ui/DownloadProgressIcon;->progress:F

    cmpl-float v3, v0, v2

    if-eqz v3, :cond_4

    .line 96
    iget v3, p0, Lorg/telegram/ui/DownloadProgressIcon;->progressDt:F

    add-float/2addr v0, v3

    iput v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentProgress:F

    cmpl-float v4, v3, v1

    if-lez v4, :cond_2

    cmpl-float v4, v0, v2

    if-lez v4, :cond_2

    .line 98
    iput v2, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentProgress:F

    goto :goto_0

    :cond_2
    cmpg-float v3, v3, v1

    if-gez v3, :cond_3

    cmpg-float v0, v0, v2

    if-gez v0, :cond_3

    .line 100
    iput v2, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentProgress:F

    goto :goto_0

    .line 102
    :cond_3
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    .line 106
    :cond_4
    :goto_0
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v0

    div-int/lit8 v0, v0, 0x2

    const/high16 v2, 0x41000000    # 8.0f

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    add-int/2addr v0, v2

    const/high16 v2, 0x3f800000    # 1.0f

    .line 107
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    int-to-float v3, v3

    const/high16 v4, 0x41800000    # 16.0f

    .line 109
    invoke-static {v4}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    .line 110
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v5

    int-to-float v5, v5

    const/high16 v6, 0x40000000    # 2.0f

    mul-float/2addr v6, v4

    sub-float/2addr v5, v6

    .line 111
    sget-object v6, Lorg/telegram/messenger/AndroidUtilities;->rectTmp:Landroid/graphics/RectF;

    int-to-float v0, v0

    sub-float v7, v0, v3

    invoke-virtual {p0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v8

    int-to-float v8, v8

    sub-float/2addr v8, v4

    add-float/2addr v0, v3

    invoke-virtual {v6, v4, v7, v8, v0}, Landroid/graphics/RectF;->set(FFFF)V

    .line 112
    iget-object v8, p0, Lorg/telegram/ui/DownloadProgressIcon;->paint2:Landroid/graphics/Paint;

    invoke-virtual {p1, v6, v3, v3, v8}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V

    .line 114
    iget v8, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentProgress:F

    mul-float/2addr v5, v8

    add-float/2addr v5, v4

    invoke-virtual {v6, v4, v7, v5, v0}, Landroid/graphics/RectF;->set(FFFF)V

    .line 115
    iget-object v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->paint:Landroid/graphics/Paint;

    invoke-virtual {p1, v6, v3, v3, v0}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V

    .line 117
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    .line 118
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v0

    int-to-float v0, v0

    invoke-virtual {p1, v1, v1, v0, v7}, Landroid/graphics/Canvas;->clipRect(FFFF)Z

    .line 119
    iget v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->progress:F

    cmpl-float v0, v0, v2

    const/4 v3, 0x0

    if-eqz v0, :cond_5

    .line 120
    iput-boolean v3, p0, Lorg/telegram/ui/DownloadProgressIcon;->showCompletedIcon:Z

    .line 122
    :cond_5
    iget-boolean v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->showCompletedIcon:Z

    if-eqz v0, :cond_6

    .line 123
    iget-object v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadCompleteImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0, p1}, Lorg/telegram/messenger/ImageReceiver;->draw(Landroid/graphics/Canvas;)Z

    goto :goto_1

    .line 125
    :cond_6
    iget-object v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0, p1}, Lorg/telegram/messenger/ImageReceiver;->draw(Landroid/graphics/Canvas;)Z

    .line 128
    :goto_1
    iget v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->progress:F

    cmpl-float v0, v0, v2

    const/4 v2, 0x1

    if-nez v0, :cond_7

    iget-boolean v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->showCompletedIcon:Z

    if-nez v0, :cond_7

    .line 129
    iget-object v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadDrawable:Lorg/telegram/ui/Components/RLottieDrawable;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/RLottieDrawable;->getCurrentFrame()I

    move-result v0

    if-nez v0, :cond_7

    .line 130
    iget-object v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadCompleteDrawable:Lorg/telegram/ui/Components/RLottieDrawable;

    invoke-virtual {v0, v3, v3}, Lorg/telegram/ui/Components/RLottieDrawable;->setCurrentFrame(IZ)V

    .line 131
    iget-object v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadCompleteDrawable:Lorg/telegram/ui/Components/RLottieDrawable;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/RLottieDrawable;->start()V

    .line 132
    iput-boolean v2, p0, Lorg/telegram/ui/DownloadProgressIcon;->showCompletedIcon:Z

    .line 135
    :cond_7
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    .line 136
    invoke-virtual {p0}, Landroid/view/View;->getAlpha()F

    move-result p1

    cmpl-float p1, p1, v1

    if-eqz p1, :cond_8

    .line 137
    iput-boolean v2, p0, Lorg/telegram/ui/DownloadProgressIcon;->wasDrawn:Z

    :cond_8
    :goto_2
    return-void
.end method

.method public onMeasure(II)V
    .locals 3

    .line 73
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result p1

    const/high16 v0, 0x40000000    # 2.0f

    invoke-static {p1, v0}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result p1

    invoke-static {p2}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result p2

    invoke-static {p2, v0}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result p2

    invoke-super {p0, p1, p2}, Landroid/view/View;->onMeasure(II)V

    const/high16 p1, 0x41700000    # 15.0f

    .line 74
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    .line 75
    iget-object p2, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    int-to-float v0, p1

    invoke-virtual {p0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    mul-int/lit8 p1, p1, 0x2

    sub-int/2addr v1, p1

    int-to-float v1, v1

    invoke-virtual {p0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v2

    sub-int/2addr v2, p1

    int-to-float v2, v2

    invoke-virtual {p2, v0, v0, v1, v2}, Lorg/telegram/messenger/ImageReceiver;->setImageCoords(FFFF)V

    .line 76
    iget-object p2, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadCompleteImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {p0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    sub-int/2addr v1, p1

    int-to-float v1, v1

    invoke-virtual {p0}, Landroid/view/View;->getMeasuredHeight()I

    move-result p0

    sub-int/2addr p0, p1

    int-to-float p0, p0

    invoke-virtual {p2, v0, v0, v1, p0}, Lorg/telegram/messenger/ImageReceiver;->setImageCoords(FFFF)V

    return-void
.end method

.method public setAlpha(F)V
    .locals 1

    const/4 v0, 0x0

    cmpl-float v0, p1, v0

    if-nez v0, :cond_0

    const/4 v0, 0x0

    .line 271
    iput-boolean v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->wasDrawn:Z

    .line 273
    :cond_0
    invoke-super {p0, p1}, Landroid/view/View;->setAlpha(F)V

    return-void
.end method

.method public setVisibility(I)V
    .locals 1

    if-eqz p1, :cond_0

    const/4 v0, 0x0

    .line 279
    iput-boolean v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->wasDrawn:Z

    .line 281
    :cond_0
    invoke-super {p0, p1}, Landroid/view/View;->setVisibility(I)V

    return-void
.end method

.method public updateColors()V
    .locals 5

    .line 66
    iget-object v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadDrawable:Lorg/telegram/ui/Components/RLottieDrawable;

    new-instance v1, Landroid/graphics/PorterDuffColorFilter;

    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_actionBarDefaultIcon:I

    invoke-static {v2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result v3

    sget-object v4, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v1, v3, v4}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v0, v1}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 67
    iget-object v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->downloadCompleteDrawable:Lorg/telegram/ui/Components/RLottieDrawable;

    new-instance v1, Landroid/graphics/PorterDuffColorFilter;

    invoke-static {v2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result v2

    sget-object v3, Landroid/graphics/PorterDuff$Mode;->SRC:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v1, v2, v3}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {v0, v1}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 68
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method

.method public updateProgress()V
    .locals 9

    .line 193
    iget v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/MessagesStorage;->getInstance(I)Lorg/telegram/messenger/MessagesStorage;

    const-wide/16 v0, 0x0

    const/4 v2, 0x0

    move-wide v3, v0

    move-wide v5, v3

    .line 196
    :goto_0
    iget-object v7, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentListeners:Ljava/util/ArrayList;

    invoke-virtual {v7}, Ljava/util/ArrayList;->size()I

    move-result v7

    if-ge v2, v7, :cond_0

    .line 197
    iget-object v7, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentListeners:Ljava/util/ArrayList;

    invoke-virtual {v7, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lorg/telegram/ui/DownloadProgressIcon$ProgressObserver;

    iget-wide v7, v7, Lorg/telegram/ui/DownloadProgressIcon$ProgressObserver;->total:J

    add-long/2addr v3, v7

    .line 198
    iget-object v7, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentListeners:Ljava/util/ArrayList;

    invoke-virtual {v7, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lorg/telegram/ui/DownloadProgressIcon$ProgressObserver;

    iget-wide v7, v7, Lorg/telegram/ui/DownloadProgressIcon$ProgressObserver;->downloaded:J

    add-long/2addr v5, v7

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_0
    cmp-long v0, v3, v0

    const/high16 v1, 0x3f800000    # 1.0f

    if-nez v0, :cond_1

    .line 201
    iput v1, p0, Lorg/telegram/ui/DownloadProgressIcon;->progress:F

    goto :goto_1

    :cond_1
    long-to-float v0, v5

    long-to-float v2, v3

    div-float/2addr v0, v2

    .line 203
    iput v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->progress:F

    .line 205
    :goto_1
    iget v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->progress:F

    cmpl-float v2, v0, v1

    if-lez v2, :cond_2

    .line 206
    iput v1, p0, Lorg/telegram/ui/DownloadProgressIcon;->progress:F

    goto :goto_2

    :cond_2
    const/4 v1, 0x0

    cmpg-float v0, v0, v1

    if-gez v0, :cond_3

    .line 208
    iput v1, p0, Lorg/telegram/ui/DownloadProgressIcon;->progress:F

    .line 211
    :cond_3
    :goto_2
    iget v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->progress:F

    iget v1, p0, Lorg/telegram/ui/DownloadProgressIcon;->currentProgress:F

    sub-float/2addr v0, v1

    const/high16 v1, 0x41800000    # 16.0f

    mul-float/2addr v0, v1

    const/high16 v1, 0x43160000    # 150.0f

    div-float/2addr v0, v1

    iput v0, p0, Lorg/telegram/ui/DownloadProgressIcon;->progressDt:F

    .line 212
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method
