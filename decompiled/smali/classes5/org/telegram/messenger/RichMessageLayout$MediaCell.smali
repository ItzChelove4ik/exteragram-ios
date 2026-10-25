.class public Lorg/telegram/messenger/RichMessageLayout$MediaCell;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/DownloadController$FileDownloadProgressListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/messenger/RichMessageLayout;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "MediaCell"
.end annotation


# static fields
.field private static fancyBlurFilter:Landroid/graphics/ColorMatrixColorFilter;


# instance fields
.field public final aspectRatio:F

.field public autoDownload:Z

.field public final blurImageReceiver:Lorg/telegram/messenger/ImageReceiver;

.field private blurSource:Landroid/graphics/Bitmap;

.field private buttonPressed:Z

.field private final buttonSize:I

.field private buttonState:I

.field private buttonX:I

.field private buttonY:I

.field public final document:Lorg/telegram/tgnet/TLRPC$Document;

.field public h:I

.field public final imageReceiver:Lorg/telegram/messenger/ImageReceiver;

.field public final isVideo:Z

.field private mediaForced:Z

.field private final observerTag:I

.field public final pageBlock:Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

.field private parentView:Landroid/view/View;

.field public final photo:Lorg/telegram/tgnet/TLRPC$Photo;

.field private photoPressed:Z

.field public final previewThumb:Lorg/telegram/tgnet/TLRPC$PhotoSize;

.field public radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

.field public final realVideo:Z

.field public final root:Lorg/telegram/messenger/RichMessageLayout;

.field public final sizeFull:Lorg/telegram/tgnet/TLRPC$PhotoSize;

.field private final spoilerReveal:Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;

.field public final strippedSize:Lorg/telegram/tgnet/TLRPC$PhotoSize;

.field public final strippedThumb:Lorg/telegram/tgnet/TLRPC$PhotoSize;

.field public w:I

.field public x:I

.field public y:I


# direct methods
.method public static bridge synthetic -$$Nest$fgetparentView(Lorg/telegram/messenger/RichMessageLayout$MediaCell;)Landroid/view/View;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->parentView:Landroid/view/View;

    return-object p0
.end method

.method private constructor <init>(Lorg/telegram/messenger/RichMessageLayout;Lorg/telegram/tgnet/tl/TL_iv$pageBlockPhoto;)V
    .locals 5

    .line 8094
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 8056
    new-instance v0, Lorg/telegram/messenger/ImageReceiver;

    invoke-direct {v0}, Lorg/telegram/messenger/ImageReceiver;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    .line 8057
    new-instance v1, Lorg/telegram/messenger/ImageReceiver;

    invoke-direct {v1}, Lorg/telegram/messenger/ImageReceiver;-><init>()V

    iput-object v1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->blurImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    const/4 v2, -0x1

    .line 8076
    iput v2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonState:I

    const/high16 v2, 0x42400000    # 48.0f

    .line 8078
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    iput v2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonSize:I

    .line 8437
    new-instance v2, Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;

    invoke-direct {v2}, Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;-><init>()V

    iput-object v2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->spoilerReveal:Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;

    .line 8095
    iput-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    .line 8096
    iput-object p2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->pageBlock:Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    .line 8097
    iget-wide v2, p2, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPhoto;->photo_id:J

    invoke-virtual {p1, v2, v3}, Lorg/telegram/messenger/RichMessageLayout;->getPhoto(J)Lorg/telegram/tgnet/TLRPC$Photo;

    move-result-object p2

    iput-object p2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->photo:Lorg/telegram/tgnet/TLRPC$Photo;

    const/4 v2, 0x0

    if-eqz p2, :cond_0

    .line 8099
    iget-object v3, p2, Lorg/telegram/tgnet/TLRPC$Photo;->sizes:Ljava/util/ArrayList;

    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->getPhotoSize()I

    move-result v4

    invoke-static {v3, v4}, Lorg/telegram/messenger/FileLoader;->getClosestPhotoSizeWithSize(Ljava/util/ArrayList;I)Lorg/telegram/tgnet/TLRPC$PhotoSize;

    move-result-object v3

    iput-object v3, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->sizeFull:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    .line 8100
    iget-object p2, p2, Lorg/telegram/tgnet/TLRPC$Photo;->sizes:Ljava/util/ArrayList;

    invoke-static {p2}, Lorg/telegram/messenger/FileLoader;->getStrippedPhotoSize(Ljava/util/ArrayList;)Lorg/telegram/tgnet/TLRPC$TL_photoStrippedSize;

    move-result-object p2

    iput-object p2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->strippedSize:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    goto :goto_0

    .line 8102
    :cond_0
    iput-object v2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->sizeFull:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    .line 8103
    iput-object v2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->strippedSize:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    .line 8105
    :goto_0
    iput-object v2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->document:Lorg/telegram/tgnet/TLRPC$Document;

    .line 8106
    iput-object v2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->previewThumb:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    .line 8107
    iput-object v2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->strippedThumb:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    const/4 p2, 0x0

    .line 8108
    iput-boolean p2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->isVideo:Z

    .line 8109
    iput-boolean p2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->realVideo:Z

    .line 8110
    iget-object p2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->sizeFull:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    if-eqz p2, :cond_1

    iget v2, p2, Lorg/telegram/tgnet/TLRPC$PhotoSize;->h:I

    if-lez v2, :cond_1

    iget p2, p2, Lorg/telegram/tgnet/TLRPC$PhotoSize;->w:I

    int-to-float p2, p2

    int-to-float v2, v2

    div-float/2addr p2, v2

    goto :goto_1

    :cond_1
    const/high16 p2, 0x3f800000    # 1.0f

    :goto_1
    iput p2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->aspectRatio:F

    .line 8111
    iget p1, p1, Lorg/telegram/messenger/RichMessageLayout;->currentAccount:I

    invoke-static {p1}, Lorg/telegram/messenger/DownloadController;->getInstance(I)Lorg/telegram/messenger/DownloadController;

    move-result-object p1

    invoke-virtual {p1}, Lorg/telegram/messenger/DownloadController;->generateObserverTag()I

    move-result p1

    iput p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->observerTag:I

    const/4 p0, 0x1

    .line 8112
    invoke-virtual {v0, p0}, Lorg/telegram/messenger/ImageReceiver;->setAllowLoadingOnAttachedOnly(Z)V

    .line 8113
    invoke-virtual {v1, p0}, Lorg/telegram/messenger/ImageReceiver;->setAllowLoadingOnAttachedOnly(Z)V

    return-void
.end method

.method private constructor <init>(Lorg/telegram/messenger/RichMessageLayout;Lorg/telegram/tgnet/tl/TL_iv$pageBlockVideo;)V
    .locals 5

    .line 8116
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 8056
    new-instance v0, Lorg/telegram/messenger/ImageReceiver;

    invoke-direct {v0}, Lorg/telegram/messenger/ImageReceiver;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    .line 8057
    new-instance v0, Lorg/telegram/messenger/ImageReceiver;

    invoke-direct {v0}, Lorg/telegram/messenger/ImageReceiver;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->blurImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    const/4 v0, -0x1

    .line 8076
    iput v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonState:I

    const/high16 v0, 0x42400000    # 48.0f

    .line 8078
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    iput v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonSize:I

    .line 8437
    new-instance v0, Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;

    invoke-direct {v0}, Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;-><init>()V

    iput-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->spoilerReveal:Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;

    .line 8117
    iput-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    .line 8118
    iput-object p2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->pageBlock:Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    const/4 v0, 0x0

    .line 8119
    iput-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->photo:Lorg/telegram/tgnet/TLRPC$Photo;

    .line 8120
    iput-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->sizeFull:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    .line 8121
    iput-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->strippedSize:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    .line 8122
    iget-wide v1, p2, Lorg/telegram/tgnet/tl/TL_iv$pageBlockVideo;->video_id:J

    invoke-virtual {p1, v1, v2}, Lorg/telegram/messenger/RichMessageLayout;->getDocument(J)Lorg/telegram/tgnet/TLRPC$Document;

    move-result-object p2

    iput-object p2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->document:Lorg/telegram/tgnet/TLRPC$Document;

    .line 8123
    invoke-static {p2}, Lorg/telegram/messenger/MessageObject;->isVideoDocument(Lorg/telegram/tgnet/TLRPC$Document;)Z

    move-result v1

    iput-boolean v1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->realVideo:Z

    const/4 v2, 0x0

    const/4 v3, 0x1

    if-nez v1, :cond_1

    .line 8124
    invoke-static {p2}, Lorg/telegram/messenger/MessageObject;->isGifDocument(Lorg/telegram/tgnet/TLRPC$Document;)Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_0

    :cond_0
    move v1, v2

    goto :goto_1

    :cond_1
    :goto_0
    move v1, v3

    :goto_1
    iput-boolean v1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->isVideo:Z

    if-eqz p2, :cond_2

    .line 8126
    iget-object v1, p2, Lorg/telegram/tgnet/TLRPC$Document;->thumbs:Ljava/util/ArrayList;

    const/16 v4, 0x140

    invoke-static {v1, v4, v2, v0, v3}, Lorg/telegram/messenger/FileLoader;->getClosestPhotoSizeWithSize(Ljava/util/ArrayList;IZLorg/telegram/tgnet/TLRPC$PhotoSize;Z)Lorg/telegram/tgnet/TLRPC$PhotoSize;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->previewThumb:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    .line 8127
    iget-object v0, p2, Lorg/telegram/tgnet/TLRPC$Document;->thumbs:Ljava/util/ArrayList;

    invoke-static {v0}, Lorg/telegram/messenger/FileLoader;->getStrippedPhotoSize(Ljava/util/ArrayList;)Lorg/telegram/tgnet/TLRPC$TL_photoStrippedSize;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->strippedThumb:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    goto :goto_2

    .line 8129
    :cond_2
    iput-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->previewThumb:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    .line 8130
    iput-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->strippedThumb:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    :goto_2
    if-eqz p2, :cond_4

    .line 8134
    :goto_3
    iget-object p2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->document:Lorg/telegram/tgnet/TLRPC$Document;

    iget-object p2, p2, Lorg/telegram/tgnet/TLRPC$Document;->attributes:Ljava/util/ArrayList;

    invoke-virtual {p2}, Ljava/util/ArrayList;->size()I

    move-result p2

    if-ge v2, p2, :cond_4

    .line 8135
    iget-object p2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->document:Lorg/telegram/tgnet/TLRPC$Document;

    iget-object p2, p2, Lorg/telegram/tgnet/TLRPC$Document;->attributes:Ljava/util/ArrayList;

    invoke-virtual {p2, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lorg/telegram/tgnet/TLRPC$DocumentAttribute;

    .line 8136
    instance-of v0, p2, Lorg/telegram/tgnet/TLRPC$TL_documentAttributeVideo;

    if-eqz v0, :cond_3

    iget v0, p2, Lorg/telegram/tgnet/TLRPC$DocumentAttribute;->h:I

    if-lez v0, :cond_3

    .line 8137
    iget p2, p2, Lorg/telegram/tgnet/TLRPC$DocumentAttribute;->w:I

    int-to-float p2, p2

    int-to-float v0, v0

    div-float/2addr p2, v0

    goto :goto_4

    :cond_3
    add-int/lit8 v2, v2, 0x1

    goto :goto_3

    :cond_4
    const/high16 p2, 0x3f800000    # 1.0f

    .line 8142
    :goto_4
    iput p2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->aspectRatio:F

    .line 8143
    iget p1, p1, Lorg/telegram/messenger/RichMessageLayout;->currentAccount:I

    invoke-static {p1}, Lorg/telegram/messenger/DownloadController;->getInstance(I)Lorg/telegram/messenger/DownloadController;

    move-result-object p1

    invoke-virtual {p1}, Lorg/telegram/messenger/DownloadController;->generateObserverTag()I

    move-result p1

    iput p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->observerTag:I

    .line 8144
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {p1, v3}, Lorg/telegram/messenger/ImageReceiver;->setAllowLoadingOnAttachedOnly(Z)V

    .line 8145
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->blurImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {p1, v3}, Lorg/telegram/messenger/ImageReceiver;->setAllowLoadingOnAttachedOnly(Z)V

    .line 8146
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    new-instance p2, Lorg/telegram/messenger/RichMessageLayout$MediaCell$1;

    invoke-direct {p2, p0}, Lorg/telegram/messenger/RichMessageLayout$MediaCell$1;-><init>(Lorg/telegram/messenger/RichMessageLayout$MediaCell;)V

    invoke-virtual {p1, p2}, Lorg/telegram/messenger/ImageReceiver;->setDelegate(Lorg/telegram/messenger/ImageReceiver$ImageReceiverDelegate;)V

    return-void
.end method

.method private allowAutoplay()Z
    .locals 0

    .line 8169
    iget-boolean p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->realVideo:Z

    if-eqz p0, :cond_0

    invoke-static {}, Lorg/telegram/messenger/SharedConfig;->isAutoplayVideo()Z

    move-result p0

    return p0

    :cond_0
    invoke-static {}, Lorg/telegram/messenger/SharedConfig;->isAutoplayGifs()Z

    move-result p0

    return p0
.end method

.method private applyImage(Z)V
    .locals 16

    move-object/from16 v0, p0

    .line 8202
    iget-object v1, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->photo:Lorg/telegram/tgnet/TLRPC$Photo;

    const/4 v2, 0x0

    if-eqz v1, :cond_2

    iget-object v3, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->sizeFull:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    if-eqz v3, :cond_2

    .line 8203
    iget-object v3, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->strippedSize:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    if-eqz v3, :cond_0

    invoke-static {v3, v1}, Lorg/telegram/messenger/ImageLocation;->getForPhoto(Lorg/telegram/tgnet/TLRPC$PhotoSize;Lorg/telegram/tgnet/TLRPC$Photo;)Lorg/telegram/messenger/ImageLocation;

    move-result-object v2

    :cond_0
    move-object v8, v2

    .line 8212
    iget-object v3, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    if-eqz p1, :cond_1

    .line 8205
    iget-object v1, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->sizeFull:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    iget-object v2, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->photo:Lorg/telegram/tgnet/TLRPC$Photo;

    .line 8207
    invoke-static {v1, v2}, Lorg/telegram/messenger/ImageLocation;->getForPhoto(Lorg/telegram/tgnet/TLRPC$PhotoSize;Lorg/telegram/tgnet/TLRPC$Photo;)Lorg/telegram/messenger/ImageLocation;

    move-result-object v6

    iget-object v1, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->sizeFull:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    iget v1, v1, Lorg/telegram/tgnet/TLRPC$PhotoSize;->size:I

    int-to-long v11, v1

    iget-object v0, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    iget-object v14, v0, Lorg/telegram/messenger/RichMessageLayout;->messageObject:Lorg/telegram/messenger/MessageObject;

    const/4 v15, 0x1

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v7, 0x0

    .line 8205
    const-string v9, "b1"

    const/4 v10, 0x0

    const/4 v13, 0x0

    invoke-virtual/range {v3 .. v15}, Lorg/telegram/messenger/ImageReceiver;->setImage(Lorg/telegram/messenger/ImageLocation;Ljava/lang/String;Lorg/telegram/messenger/ImageLocation;Ljava/lang/String;Lorg/telegram/messenger/ImageLocation;Ljava/lang/String;Landroid/graphics/drawable/Drawable;JLjava/lang/String;Ljava/lang/Object;I)V

    return-void

    .line 8212
    :cond_1
    iget-object v1, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->sizeFull:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    iget v1, v1, Lorg/telegram/tgnet/TLRPC$PhotoSize;->size:I

    int-to-long v11, v1

    iget-object v0, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    iget-object v14, v0, Lorg/telegram/messenger/RichMessageLayout;->messageObject:Lorg/telegram/messenger/MessageObject;

    const/4 v15, 0x1

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const-string v9, "b1"

    const/4 v10, 0x0

    const/4 v13, 0x0

    invoke-virtual/range {v3 .. v15}, Lorg/telegram/messenger/ImageReceiver;->setImage(Lorg/telegram/messenger/ImageLocation;Ljava/lang/String;Lorg/telegram/messenger/ImageLocation;Ljava/lang/String;Lorg/telegram/messenger/ImageLocation;Ljava/lang/String;Landroid/graphics/drawable/Drawable;JLjava/lang/String;Ljava/lang/Object;I)V

    return-void

    .line 8219
    :cond_2
    iget-object v1, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->document:Lorg/telegram/tgnet/TLRPC$Document;

    if-eqz v1, :cond_7

    .line 8220
    iget-object v3, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->strippedThumb:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    if-eqz v3, :cond_3

    invoke-static {v3, v1}, Lorg/telegram/messenger/ImageLocation;->getForDocument(Lorg/telegram/tgnet/TLRPC$PhotoSize;Lorg/telegram/tgnet/TLRPC$Document;)Lorg/telegram/messenger/ImageLocation;

    move-result-object v1

    move-object v8, v1

    goto :goto_0

    :cond_3
    move-object v8, v2

    .line 8221
    :goto_0
    iget-object v1, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->previewThumb:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    if-eqz v1, :cond_4

    iget-object v2, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->document:Lorg/telegram/tgnet/TLRPC$Document;

    invoke-static {v1, v2}, Lorg/telegram/messenger/ImageLocation;->getForDocument(Lorg/telegram/tgnet/TLRPC$PhotoSize;Lorg/telegram/tgnet/TLRPC$Document;)Lorg/telegram/messenger/ImageLocation;

    move-result-object v2

    :cond_4
    move-object v6, v2

    if-eqz p1, :cond_6

    .line 8222
    iget-boolean v1, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->isVideo:Z

    if-eqz v1, :cond_6

    invoke-direct {v0}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->allowAutoplay()Z

    move-result v1

    if-nez v1, :cond_5

    iget-boolean v1, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->mediaForced:Z

    if-eqz v1, :cond_6

    .line 8223
    :cond_5
    iget-object v1, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    const/4 v2, 0x1

    invoke-virtual {v1, v2}, Lorg/telegram/messenger/ImageReceiver;->setAllowStartAnimation(Z)V

    .line 8224
    iget-object v1, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v1, v2}, Lorg/telegram/messenger/ImageReceiver;->setAutoRepeat(I)V

    .line 8225
    iget-object v3, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    iget-object v1, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->document:Lorg/telegram/tgnet/TLRPC$Document;

    .line 8226
    invoke-static {v1}, Lorg/telegram/messenger/ImageLocation;->getForDocument(Lorg/telegram/tgnet/TLRPC$Document;)Lorg/telegram/messenger/ImageLocation;

    move-result-object v4

    iget-object v1, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->document:Lorg/telegram/tgnet/TLRPC$Document;

    iget-wide v11, v1, Lorg/telegram/tgnet/TLRPC$Document;->size:J

    iget-object v0, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    iget-object v14, v0, Lorg/telegram/messenger/RichMessageLayout;->messageObject:Lorg/telegram/messenger/MessageObject;

    const/4 v15, 0x1

    .line 8225
    const-string v5, "g"

    const/4 v7, 0x0

    const-string v9, "b1"

    const/4 v10, 0x0

    const-string v13, "mp4"

    invoke-virtual/range {v3 .. v15}, Lorg/telegram/messenger/ImageReceiver;->setImage(Lorg/telegram/messenger/ImageLocation;Ljava/lang/String;Lorg/telegram/messenger/ImageLocation;Ljava/lang/String;Lorg/telegram/messenger/ImageLocation;Ljava/lang/String;Landroid/graphics/drawable/Drawable;JLjava/lang/String;Ljava/lang/Object;I)V

    return-void

    .line 8232
    :cond_6
    iget-object v3, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    iget-object v1, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->document:Lorg/telegram/tgnet/TLRPC$Document;

    iget-wide v11, v1, Lorg/telegram/tgnet/TLRPC$Document;->size:J

    iget-object v0, v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    iget-object v14, v0, Lorg/telegram/messenger/RichMessageLayout;->messageObject:Lorg/telegram/messenger/MessageObject;

    const/4 v15, 0x1

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v7, 0x0

    const-string v9, "b1"

    const/4 v10, 0x0

    const-string v13, "mp4"

    invoke-virtual/range {v3 .. v15}, Lorg/telegram/messenger/ImageReceiver;->setImage(Lorg/telegram/messenger/ImageLocation;Ljava/lang/String;Lorg/telegram/messenger/ImageLocation;Ljava/lang/String;Lorg/telegram/messenger/ImageLocation;Ljava/lang/String;Landroid/graphics/drawable/Drawable;JLjava/lang/String;Ljava/lang/Object;I)V

    :cond_7
    return-void
.end method

.method private computeAutoDownload()Z
    .locals 5

    .line 8173
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->document:Lorg/telegram/tgnet/TLRPC$Document;

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-eqz v0, :cond_2

    .line 8174
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->isVideo:Z

    if-eqz v0, :cond_1

    .line 8175
    invoke-direct {p0}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->allowAutoplay()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    iget v0, v0, Lorg/telegram/messenger/RichMessageLayout;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/DownloadController;->getInstance(I)Lorg/telegram/messenger/DownloadController;

    move-result-object v0

    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->document:Lorg/telegram/tgnet/TLRPC$Document;

    iget-wide v3, p0, Lorg/telegram/tgnet/TLRPC$Document;->size:J

    const/4 p0, 0x4

    invoke-virtual {v0, p0, v3, v4}, Lorg/telegram/messenger/DownloadController;->canDownloadMedia(IJ)Z

    move-result p0

    if-eqz p0, :cond_0

    return v2

    :cond_0
    return v1

    :cond_1
    return v2

    .line 8178
    :cond_2
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    iget p0, p0, Lorg/telegram/messenger/RichMessageLayout;->currentAccount:I

    invoke-static {p0}, Lorg/telegram/messenger/DownloadController;->getInstance(I)Lorg/telegram/messenger/DownloadController;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/messenger/DownloadController;->getCurrentDownloadMask()I

    move-result p0

    and-int/2addr p0, v2

    if-eqz p0, :cond_3

    return v2

    :cond_3
    return v1
.end method

.method private didPressButton(Landroid/view/View;Z)V
    .locals 5

    .line 8337
    iget v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonState:I

    const/4 v1, 0x3

    const/4 v2, 0x1

    if-nez v0, :cond_2

    .line 8338
    iput-boolean v2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->mediaForced:Z

    .line 8339
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    if-eqz v0, :cond_0

    const/4 v3, 0x0

    invoke-virtual {v0, v3, p2}, Lorg/telegram/ui/Components/RadialProgress2;->setProgress(FZ)V

    .line 8340
    :cond_0
    invoke-direct {p0, v2}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->applyImage(Z)V

    .line 8341
    iput v2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonState:I

    .line 8342
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    if-eqz p0, :cond_1

    invoke-virtual {p0, v1, v2, p2}, Lorg/telegram/ui/Components/RadialProgress2;->setIcon(IZZ)V

    :cond_1
    if-eqz p1, :cond_7

    .line 8343
    invoke-virtual {p1}, Landroid/view/View;->invalidate()V

    return-void

    :cond_2
    const/4 v3, 0x2

    const/4 v4, 0x0

    if-ne v0, v2, :cond_4

    .line 8345
    iput-boolean v4, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->mediaForced:Z

    .line 8346
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0}, Lorg/telegram/messenger/ImageReceiver;->cancelLoadImage()V

    .line 8347
    iput v4, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonState:I

    .line 8348
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    if-eqz p0, :cond_3

    invoke-virtual {p0, v3, v4, p2}, Lorg/telegram/ui/Components/RadialProgress2;->setIcon(IZZ)V

    :cond_3
    if-eqz p1, :cond_7

    .line 8349
    invoke-virtual {p1}, Landroid/view/View;->invalidate()V

    return-void

    :cond_4
    if-ne v0, v3, :cond_6

    .line 8351
    iput-boolean v2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->mediaForced:Z

    .line 8352
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0, v2}, Lorg/telegram/messenger/ImageReceiver;->setAllowStartAnimation(Z)V

    .line 8353
    invoke-direct {p0, v2}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->applyImage(Z)V

    .line 8354
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0}, Lorg/telegram/messenger/ImageReceiver;->startAnimation()V

    const/4 v0, -0x1

    .line 8355
    iput v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonState:I

    .line 8356
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    if-eqz p0, :cond_5

    const/4 v0, 0x4

    invoke-virtual {p0, v0, v4, p2}, Lorg/telegram/ui/Components/RadialProgress2;->setIcon(IZZ)V

    :cond_5
    if-eqz p1, :cond_7

    .line 8357
    invoke-virtual {p1}, Landroid/view/View;->invalidate()V

    return-void

    :cond_6
    if-ne v0, v1, :cond_7

    .line 8359
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    invoke-static {p1}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$fgetdelegate(Lorg/telegram/messenger/RichMessageLayout;)Lorg/telegram/ui/Cells/ChatMessageCell$ChatMessageCellDelegate;

    move-result-object p1

    if-eqz p1, :cond_7

    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    invoke-static {p1}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$fgetdelegate(Lorg/telegram/messenger/RichMessageLayout;)Lorg/telegram/ui/Cells/ChatMessageCell$ChatMessageCellDelegate;

    move-result-object p1

    iget-object p2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    invoke-static {p2}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$fgetcell(Lorg/telegram/messenger/RichMessageLayout;)Lorg/telegram/ui/Cells/ChatMessageCell;

    move-result-object p2

    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->pageBlock:Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    invoke-interface {p1, p2, p0}, Lorg/telegram/ui/Cells/ChatMessageCell$ChatMessageCellDelegate;->openArticlePhoto(Lorg/telegram/ui/Cells/ChatMessageCell;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Z

    :cond_7
    return-void
.end method

.method private drawSpoiler(Landroid/graphics/Canvas;)V
    .locals 11

    .line 8449
    invoke-direct {p0}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->prepareBlurImage()V

    .line 8450
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0}, Lorg/telegram/messenger/ImageReceiver;->getImageX()F

    move-result v0

    .line 8451
    iget-object v1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v1}, Lorg/telegram/messenger/ImageReceiver;->getImageY()F

    move-result v1

    .line 8452
    iget-object v2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v2}, Lorg/telegram/messenger/ImageReceiver;->getImageWidth()F

    move-result v2

    .line 8453
    iget-object v3, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v3}, Lorg/telegram/messenger/ImageReceiver;->getImageHeight()F

    move-result v3

    const/4 v4, 0x0

    cmpg-float v5, v2, v4

    if-lez v5, :cond_3

    cmpg-float v4, v3, v4

    if-gtz v4, :cond_0

    goto :goto_1

    .line 8455
    :cond_0
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    add-float v4, v0, v2

    add-float v5, v1, v3

    .line 8456
    invoke-virtual {p1, v0, v1, v4, v5}, Landroid/graphics/Canvas;->clipRect(FFFF)Z

    .line 8457
    iget-object v4, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->spoilerReveal:Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;

    invoke-virtual {v4, p1}, Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;->clipOut(Landroid/graphics/Canvas;)V

    .line 8458
    iget-object v4, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->blurImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v4}, Lorg/telegram/messenger/ImageReceiver;->getBitmap()Landroid/graphics/Bitmap;

    move-result-object v4

    if-eqz v4, :cond_1

    .line 8459
    iget-object v4, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->blurImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v4, v0, v1, v2, v3}, Lorg/telegram/messenger/ImageReceiver;->setImageCoords(FFFF)V

    .line 8460
    iget-object v4, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->blurImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    iget-object v5, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v5}, Lorg/telegram/messenger/ImageReceiver;->getCurrentAlpha()F

    move-result v5

    invoke-virtual {v4, v5}, Lorg/telegram/messenger/ImageReceiver;->setAlpha(F)V

    .line 8461
    iget-object v4, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->blurImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v4, p1}, Lorg/telegram/messenger/ImageReceiver;->draw(Landroid/graphics/Canvas;)Z

    .line 8463
    :cond_1
    iget-object v4, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    invoke-virtual {v4}, Lorg/telegram/messenger/RichMessageLayout;->getMediaSpoilerEffect()Lorg/telegram/ui/Components/spoilers/SpoilerEffect2;

    move-result-object v5

    if-eqz v5, :cond_2

    .line 8465
    invoke-virtual {p1, v0, v1}, Landroid/graphics/Canvas;->translate(FF)V

    .line 8466
    iget-object v7, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->parentView:Landroid/view/View;

    invoke-static {v2}, Ljava/lang/Math;->round(F)I

    move-result v8

    invoke-static {v3}, Ljava/lang/Math;->round(F)I

    move-result v9

    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0}, Lorg/telegram/messenger/ImageReceiver;->getCurrentAlpha()F

    move-result v10

    move-object v6, p1

    invoke-virtual/range {v5 .. v10}, Lorg/telegram/ui/Components/spoilers/SpoilerEffect2;->draw(Landroid/graphics/Canvas;Landroid/view/View;IIF)V

    goto :goto_0

    :cond_2
    move-object v6, p1

    .line 8468
    :goto_0
    invoke-virtual {v6}, Landroid/graphics/Canvas;->restore()V

    .line 8469
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->parentView:Landroid/view/View;

    if-eqz p0, :cond_3

    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    :cond_3
    :goto_1
    return-void
.end method

.method public static forPageBlock(Lorg/telegram/messenger/RichMessageLayout;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Lorg/telegram/messenger/RichMessageLayout$MediaCell;
    .locals 1

    .line 8086
    instance-of v0, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPhoto;

    if-eqz v0, :cond_0

    .line 8087
    new-instance v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;

    check-cast p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPhoto;

    invoke-direct {v0, p0, p1}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;-><init>(Lorg/telegram/messenger/RichMessageLayout;Lorg/telegram/tgnet/tl/TL_iv$pageBlockPhoto;)V

    return-object v0

    .line 8088
    :cond_0
    instance-of v0, p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockVideo;

    if-eqz v0, :cond_1

    .line 8089
    new-instance v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;

    check-cast p1, Lorg/telegram/tgnet/tl/TL_iv$pageBlockVideo;

    invoke-direct {v0, p0, p1}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;-><init>(Lorg/telegram/messenger/RichMessageLayout;Lorg/telegram/tgnet/tl/TL_iv$pageBlockVideo;)V

    return-object v0

    :cond_1
    const/4 p0, 0x0

    return-object p0
.end method

.method private isOnButton(FF)Z
    .locals 2

    .line 8368
    iget v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonState:I

    const/4 v1, -0x1

    if-eq v0, v1, :cond_0

    iget v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonX:I

    int-to-float v1, v0

    cmpl-float v1, p1, v1

    if-ltz v1, :cond_0

    iget v1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonSize:I

    add-int/2addr v0, v1

    int-to-float v0, v0

    cmpg-float p1, p1, v0

    if-gtz p1, :cond_0

    iget p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonY:I

    int-to-float p1, p0

    cmpl-float p1, p2, p1

    if-ltz p1, :cond_0

    add-int/2addr p0, v1

    int-to-float p0, p0

    cmpg-float p0, p2, p0

    if-gtz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private isSpoiler()Z
    .locals 1

    .line 8272
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->pageBlock:Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPhoto;

    if-eqz v0, :cond_0

    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPhoto;

    iget-boolean p0, p0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockPhoto;->spoiler:Z

    return p0

    .line 8273
    :cond_0
    instance-of v0, p0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockVideo;

    if-eqz v0, :cond_1

    check-cast p0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockVideo;

    iget-boolean p0, p0, Lorg/telegram/tgnet/tl/TL_iv$pageBlockVideo;->spoiler:Z

    return p0

    :cond_1
    const/4 p0, 0x0

    return p0
.end method

.method private prepareBlurImage()V
    .locals 3

    .line 8278
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->blurImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0}, Lorg/telegram/messenger/ImageReceiver;->getBitmap()Landroid/graphics/Bitmap;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0}, Lorg/telegram/messenger/ImageReceiver;->getAnimation()Lorg/telegram/ui/Components/AnimatedFileDrawable;

    move-result-object v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 8279
    :cond_0
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0}, Lorg/telegram/messenger/ImageReceiver;->getBitmap()Landroid/graphics/Bitmap;

    move-result-object v0

    if-eqz v0, :cond_4

    .line 8280
    invoke-virtual {v0}, Landroid/graphics/Bitmap;->isRecycled()Z

    move-result v1

    if-eqz v1, :cond_1

    goto :goto_0

    .line 8281
    :cond_1
    iget-object v1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->blurSource:Landroid/graphics/Bitmap;

    if-ne v0, v1, :cond_2

    iget-object v1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->blurImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v1}, Lorg/telegram/messenger/ImageReceiver;->getBitmap()Landroid/graphics/Bitmap;

    move-result-object v1

    if-eqz v1, :cond_2

    goto :goto_0

    .line 8282
    :cond_2
    iput-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->blurSource:Landroid/graphics/Bitmap;

    .line 8283
    iget-object v1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->blurImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    const/4 v2, 0x0

    invoke-static {v0, v2}, Lorg/telegram/messenger/Utilities;->stackBlurBitmapMax(Landroid/graphics/Bitmap;Z)Landroid/graphics/Bitmap;

    move-result-object v0

    invoke-virtual {v1, v0}, Lorg/telegram/messenger/ImageReceiver;->setImageBitmap(Landroid/graphics/Bitmap;)V

    .line 8284
    sget-object v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->fancyBlurFilter:Landroid/graphics/ColorMatrixColorFilter;

    if-nez v0, :cond_3

    .line 8285
    new-instance v0, Landroid/graphics/ColorMatrix;

    invoke-direct {v0}, Landroid/graphics/ColorMatrix;-><init>()V

    const v1, 0x3f666666    # 0.9f

    .line 8286
    invoke-static {v0, v1}, Lorg/telegram/messenger/AndroidUtilities;->multiplyBrightnessColorMatrix(Landroid/graphics/ColorMatrix;F)V

    const v1, 0x3f19999a    # 0.6f

    .line 8287
    invoke-static {v0, v1}, Lorg/telegram/messenger/AndroidUtilities;->adjustSaturationColorMatrix(Landroid/graphics/ColorMatrix;F)V

    .line 8288
    new-instance v1, Landroid/graphics/ColorMatrixColorFilter;

    invoke-direct {v1, v0}, Landroid/graphics/ColorMatrixColorFilter;-><init>(Landroid/graphics/ColorMatrix;)V

    sput-object v1, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->fancyBlurFilter:Landroid/graphics/ColorMatrixColorFilter;

    .line 8290
    :cond_3
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->blurImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    sget-object v0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->fancyBlurFilter:Landroid/graphics/ColorMatrixColorFilter;

    invoke-virtual {p0, v0}, Lorg/telegram/messenger/ImageReceiver;->setColorFilter(Landroid/graphics/ColorFilter;)V

    :cond_4
    :goto_0
    return-void
.end method


# virtual methods
.method public attach(Landroid/view/View;)V
    .locals 1

    .line 8255
    iput-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->parentView:Landroid/view/View;

    .line 8256
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0, p1}, Lorg/telegram/messenger/ImageReceiver;->setParentView(Landroid/view/View;)V

    .line 8257
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0}, Lorg/telegram/messenger/ImageReceiver;->onAttachedToWindow()Z

    .line 8258
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->blurImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0, p1}, Lorg/telegram/messenger/ImageReceiver;->setParentView(Landroid/view/View;)V

    .line 8259
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->blurImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0}, Lorg/telegram/messenger/ImageReceiver;->onAttachedToWindow()Z

    .line 8260
    invoke-virtual {p0, p1}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->ensureProgress(Landroid/view/View;)V

    const/4 v0, 0x0

    .line 8261
    invoke-virtual {p0, p1, v0}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->updateButtonState(Landroid/view/View;Z)V

    return-void
.end method

.method public detach()V
    .locals 1

    .line 8265
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0}, Lorg/telegram/messenger/ImageReceiver;->onDetachedFromWindow()V

    .line 8266
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->blurImageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0}, Lorg/telegram/messenger/ImageReceiver;->onDetachedFromWindow()V

    const/4 v0, 0x0

    .line 8267
    iput-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->blurSource:Landroid/graphics/Bitmap;

    .line 8268
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    iget v0, v0, Lorg/telegram/messenger/RichMessageLayout;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/DownloadController;->getInstance(I)Lorg/telegram/messenger/DownloadController;

    move-result-object v0

    invoke-virtual {v0, p0}, Lorg/telegram/messenger/DownloadController;->removeLoadingFileObserver(Lorg/telegram/messenger/DownloadController$FileDownloadProgressListener;)V

    return-void
.end method

.method public draw(Landroid/graphics/Canvas;)V
    .locals 2

    .line 8440
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0, p1}, Lorg/telegram/messenger/ImageReceiver;->draw(Landroid/graphics/Canvas;)Z

    .line 8441
    invoke-direct {p0}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->isSpoiler()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->spoilerReveal:Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;

    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;->fullyRevealed()Z

    move-result v0

    if-nez v0, :cond_0

    .line 8442
    invoke-direct {p0, p1}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->drawSpoiler(Landroid/graphics/Canvas;)V

    return-void

    .line 8445
    :cond_0
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    if-eqz v0, :cond_1

    iget p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonState:I

    const/4 v1, -0x1

    if-eq p0, v1, :cond_1

    invoke-virtual {v0, p1}, Lorg/telegram/ui/Components/RadialProgress2;->draw(Landroid/graphics/Canvas;)V

    :cond_1
    return-void
.end method

.method public ensureProgress(Landroid/view/View;)V
    .locals 4

    .line 8243
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    if-nez v0, :cond_0

    if-eqz p1, :cond_0

    .line 8244
    new-instance v0, Lorg/telegram/ui/Components/RadialProgress2;

    invoke-direct {v0, p1}, Lorg/telegram/ui/Components/RadialProgress2;-><init>(Landroid/view/View;)V

    iput-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    const/4 p1, -0x1

    .line 8245
    invoke-virtual {v0, p1}, Lorg/telegram/ui/Components/RadialProgress2;->setProgressColor(I)V

    .line 8246
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    const/high16 v1, 0x7f000000

    const v2, -0x262627

    const/high16 v3, 0x66000000

    invoke-virtual {v0, v3, v1, p1, v2}, Lorg/telegram/ui/Components/RadialProgress2;->setColors(IIII)V

    .line 8247
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    iget v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonX:I

    iget v1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonY:I

    iget p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonSize:I

    add-int v2, v0, p0

    add-int/2addr p0, v1

    invoke-virtual {p1, v0, v1, v2, p0}, Lorg/telegram/ui/Components/RadialProgress2;->setProgressRect(IIII)V

    return-void

    :cond_0
    if-eqz v0, :cond_1

    if-eqz p1, :cond_1

    .line 8249
    invoke-virtual {v0, p1}, Lorg/telegram/ui/Components/RadialProgress2;->setParent(Landroid/view/View;)V

    .line 8250
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    iget v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonX:I

    iget v1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonY:I

    iget p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonSize:I

    add-int v2, v0, p0

    add-int/2addr p0, v1

    invoke-virtual {p1, v0, v1, v2, p0}, Lorg/telegram/ui/Components/RadialProgress2;->setProgressRect(IIII)V

    :cond_1
    return-void
.end method

.method public fileExists()Z
    .locals 4

    .line 8182
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->sizeFull:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-eqz v0, :cond_2

    .line 8183
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    iget v0, v0, Lorg/telegram/messenger/RichMessageLayout;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/FileLoader;->getInstance(I)Lorg/telegram/messenger/FileLoader;

    move-result-object v0

    iget-object v3, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->sizeFull:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    invoke-virtual {v0, v3, v2}, Lorg/telegram/messenger/FileLoader;->getPathToAttach(Lorg/telegram/tgnet/TLObject;Z)Ljava/io/File;

    move-result-object v0

    .line 8184
    iget-object v3, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    iget v3, v3, Lorg/telegram/messenger/RichMessageLayout;->currentAccount:I

    invoke-static {v3}, Lorg/telegram/messenger/FileLoader;->getInstance(I)Lorg/telegram/messenger/FileLoader;

    move-result-object v3

    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->sizeFull:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    invoke-virtual {v3, p0, v1}, Lorg/telegram/messenger/FileLoader;->getPathToAttach(Lorg/telegram/tgnet/TLObject;Z)Ljava/io/File;

    move-result-object p0

    .line 8185
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v0

    if-nez v0, :cond_1

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Ljava/io/File;->exists()Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    return v1

    :cond_1
    :goto_0
    return v2

    .line 8187
    :cond_2
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->document:Lorg/telegram/tgnet/TLRPC$Document;

    if-eqz v0, :cond_6

    .line 8188
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    iget v0, v0, Lorg/telegram/messenger/RichMessageLayout;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/FileLoader;->getInstance(I)Lorg/telegram/messenger/FileLoader;

    move-result-object v0

    iget-object v3, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->document:Lorg/telegram/tgnet/TLRPC$Document;

    invoke-virtual {v0, v3}, Lorg/telegram/messenger/FileLoader;->getPathToAttach(Lorg/telegram/tgnet/TLObject;)Ljava/io/File;

    move-result-object v0

    .line 8189
    iget-object v3, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    iget v3, v3, Lorg/telegram/messenger/RichMessageLayout;->currentAccount:I

    invoke-static {v3}, Lorg/telegram/messenger/FileLoader;->getInstance(I)Lorg/telegram/messenger/FileLoader;

    move-result-object v3

    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->document:Lorg/telegram/tgnet/TLRPC$Document;

    invoke-virtual {v3, p0, v2}, Lorg/telegram/messenger/FileLoader;->getPathToAttach(Lorg/telegram/tgnet/TLObject;Z)Ljava/io/File;

    move-result-object p0

    if-eqz v0, :cond_3

    .line 8190
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v0

    if-nez v0, :cond_4

    :cond_3
    if-eqz p0, :cond_5

    invoke-virtual {p0}, Ljava/io/File;->exists()Z

    move-result p0

    if-eqz p0, :cond_5

    :cond_4
    return v2

    :cond_5
    return v1

    :cond_6
    return v2
.end method

.method public getAccessibilityText()Ljava/lang/CharSequence;
    .locals 3

    .line 8416
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->isVideo:Z

    if-eqz v0, :cond_0

    sget v0, Lorg/telegram/messenger/R$string;->AttachVideo:I

    goto :goto_0

    :cond_0
    sget v0, Lorg/telegram/messenger/R$string;->AttachPhoto:I

    :goto_0
    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 8417
    invoke-direct {p0}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->isSpoiler()Z

    move-result v1

    if-eqz v1, :cond_1

    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->spoilerReveal:Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;

    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;->fullyRevealed()Z

    move-result p0

    if-nez p0, :cond_1

    .line 8418
    sget p0, Lorg/telegram/messenger/R$string;->Spoiler:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    const/4 v1, 0x3

    new-array v1, v1, [Ljava/lang/CharSequence;

    const/4 v2, 0x0

    aput-object v0, v1, v2

    const-string v0, ", "

    const/4 v2, 0x1

    aput-object v0, v1, v2

    const/4 v0, 0x2

    aput-object p0, v1, v0

    invoke-static {v1}, Landroid/text/TextUtils;->concat([Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0

    :cond_1
    return-object v0
.end method

.method public getFileName()Ljava/lang/String;
    .locals 1

    .line 8196
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->sizeFull:Lorg/telegram/tgnet/TLRPC$PhotoSize;

    if-eqz v0, :cond_0

    invoke-static {v0}, Lorg/telegram/messenger/FileLoader;->getAttachFileName(Lorg/telegram/tgnet/TLObject;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 8197
    :cond_0
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->document:Lorg/telegram/tgnet/TLRPC$Document;

    if-eqz p0, :cond_1

    invoke-static {p0}, Lorg/telegram/messenger/FileLoader;->getAttachFileName(Lorg/telegram/tgnet/TLObject;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_1
    const/4 p0, 0x0

    return-object p0
.end method

.method public getObserverTag()I
    .locals 0

    .line 8472
    iget p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->observerTag:I

    return p0
.end method

.method public isInside(FF)Z
    .locals 2

    .line 8364
    iget v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->x:I

    int-to-float v1, v0

    cmpl-float v1, p1, v1

    if-ltz v1, :cond_0

    iget v1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->w:I

    add-int/2addr v0, v1

    int-to-float v0, v0

    cmpg-float p1, p1, v0

    if-gtz p1, :cond_0

    iget p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->y:I

    int-to-float v0, p1

    cmpl-float v0, p2, v0

    if-ltz v0, :cond_0

    iget p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->h:I

    add-int/2addr p1, p0

    int-to-float p0, p1

    cmpg-float p0, p2, p0

    if-gtz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public onAccessibilityClick(Landroid/view/View;)Z
    .locals 8

    .line 8424
    invoke-direct {p0}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->isSpoiler()Z

    move-result v0

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->spoilerReveal:Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;

    invoke-virtual {v0}, Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;->isRevealing()Z

    move-result v0

    if-nez v0, :cond_0

    .line 8425
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0}, Lorg/telegram/messenger/ImageReceiver;->getImageWidth()F

    move-result v6

    .line 8426
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0}, Lorg/telegram/messenger/ImageReceiver;->getImageHeight()F

    move-result v7

    .line 8427
    iget-object v2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->spoilerReveal:Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;

    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v0}, Lorg/telegram/messenger/ImageReceiver;->getImageX()F

    move-result v0

    const/high16 v3, 0x40000000    # 2.0f

    div-float v4, v6, v3

    add-float/2addr v4, v0

    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {p0}, Lorg/telegram/messenger/ImageReceiver;->getImageY()F

    move-result p0

    div-float v0, v7, v3

    add-float v5, p0, v0

    move-object v3, p1

    invoke-virtual/range {v2 .. v7}, Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;->start(Landroid/view/View;FFFF)V

    return v1

    .line 8430
    :cond_0
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    invoke-static {p1}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$fgetdelegate(Lorg/telegram/messenger/RichMessageLayout;)Lorg/telegram/ui/Cells/ChatMessageCell$ChatMessageCellDelegate;

    move-result-object p1

    if-eqz p1, :cond_1

    .line 8431
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    invoke-static {p1}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$fgetdelegate(Lorg/telegram/messenger/RichMessageLayout;)Lorg/telegram/ui/Cells/ChatMessageCell$ChatMessageCellDelegate;

    move-result-object p1

    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    invoke-static {v0}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$fgetcell(Lorg/telegram/messenger/RichMessageLayout;)Lorg/telegram/ui/Cells/ChatMessageCell;

    move-result-object v0

    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->pageBlock:Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    invoke-interface {p1, v0, p0}, Lorg/telegram/ui/Cells/ChatMessageCell$ChatMessageCellDelegate;->openArticlePhoto(Lorg/telegram/ui/Cells/ChatMessageCell;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Z

    return v1

    :cond_1
    const/4 p0, 0x0

    return p0
.end method

.method public onFailedDownload(Ljava/lang/String;Z)V
    .locals 0

    .line 8474
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->parentView:Landroid/view/View;

    const/4 p2, 0x0

    invoke-virtual {p0, p1, p2}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->updateButtonState(Landroid/view/View;Z)V

    return-void
.end method

.method public onProgressDownload(Ljava/lang/String;JJ)V
    .locals 3

    .line 8485
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    const/4 v0, 0x1

    if-eqz p1, :cond_1

    const-wide/16 v1, 0x0

    cmp-long v1, p4, v1

    if-gtz v1, :cond_0

    const/4 p2, 0x0

    goto :goto_0

    :cond_0
    long-to-float p2, p2

    long-to-float p3, p4

    div-float/2addr p2, p3

    :goto_0
    const/high16 p3, 0x3f800000    # 1.0f

    .line 8486
    invoke-static {p3, p2}, Ljava/lang/Math;->min(FF)F

    move-result p2

    invoke-virtual {p1, p2, v0}, Lorg/telegram/ui/Components/RadialProgress2;->setProgress(FZ)V

    .line 8488
    :cond_1
    iget p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonState:I

    if-eq p1, v0, :cond_2

    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->parentView:Landroid/view/View;

    invoke-virtual {p0, p1, v0}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->updateButtonState(Landroid/view/View;Z)V

    :cond_2
    return-void
.end method

.method public onProgressUpload(Ljava/lang/String;JJZ)V
    .locals 0

    return-void
.end method

.method public onSuccessDownload(Ljava/lang/String;)V
    .locals 2

    .line 8477
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    const/4 v0, 0x1

    if-eqz p1, :cond_0

    const/high16 v1, 0x3f800000    # 1.0f

    invoke-virtual {p1, v1, v0}, Lorg/telegram/ui/Components/RadialProgress2;->setProgress(FZ)V

    .line 8478
    :cond_0
    iget-boolean p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->isVideo:Z

    if-eqz p1, :cond_2

    invoke-direct {p0}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->allowAutoplay()Z

    move-result p1

    if-nez p1, :cond_1

    iget-boolean p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->mediaForced:Z

    if-eqz p1, :cond_2

    .line 8479
    :cond_1
    invoke-direct {p0, v0}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->applyImage(Z)V

    .line 8481
    :cond_2
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->parentView:Landroid/view/View;

    invoke-virtual {p0, p1, v0}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->updateButtonState(Landroid/view/View;Z)V

    return-void
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;Landroid/view/View;)Z
    .locals 8

    .line 8372
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result v0

    .line 8373
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result v1

    .line 8374
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result p1

    .line 8375
    invoke-virtual {p0, v1, p1}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->isInside(FF)Z

    move-result v2

    .line 8376
    invoke-direct {p0, v1, p1}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->isOnButton(FF)Z

    move-result p1

    const/4 v1, 0x1

    const/4 v3, 0x0

    if-nez v0, :cond_4

    if-eqz v2, :cond_2

    if-nez p1, :cond_0

    .line 8378
    iget p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonState:I

    if-eqz p1, :cond_0

    const/4 v0, 0x2

    if-ne p1, v0, :cond_2

    .line 8379
    :cond_0
    iput-boolean v1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonPressed:Z

    if-eqz p2, :cond_1

    .line 8380
    invoke-virtual {p2}, Landroid/view/View;->invalidate()V

    :cond_1
    return v1

    :cond_2
    if-eqz v2, :cond_3

    .line 8383
    iput-boolean v1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->photoPressed:Z

    return v1

    :cond_3
    return v3

    :cond_4
    if-ne v0, v1, :cond_b

    .line 8387
    iget-boolean p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonPressed:Z

    if-eqz p1, :cond_6

    .line 8388
    iput-boolean v3, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonPressed:Z

    if-eqz p2, :cond_5

    .line 8389
    invoke-virtual {p2, v3}, Landroid/view/View;->playSoundEffect(I)V

    invoke-virtual {p2}, Landroid/view/View;->invalidate()V

    .line 8390
    :cond_5
    invoke-direct {p0, p2, v1}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->didPressButton(Landroid/view/View;Z)V

    return v1

    .line 8393
    :cond_6
    iget-boolean p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->photoPressed:Z

    if-eqz p1, :cond_a

    .line 8394
    iput-boolean v3, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->photoPressed:Z

    if-eqz v2, :cond_a

    if-eqz p2, :cond_7

    .line 8396
    invoke-virtual {p2, v3}, Landroid/view/View;->playSoundEffect(I)V

    .line 8397
    :cond_7
    invoke-direct {p0}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->isSpoiler()Z

    move-result p1

    if-eqz p1, :cond_8

    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->spoilerReveal:Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;

    invoke-virtual {p1}, Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;->isRevealing()Z

    move-result p1

    if-nez p1, :cond_8

    .line 8398
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {p1}, Lorg/telegram/messenger/ImageReceiver;->getImageWidth()F

    move-result v6

    .line 8399
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {p1}, Lorg/telegram/messenger/ImageReceiver;->getImageHeight()F

    move-result v7

    .line 8400
    iget-object v2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->spoilerReveal:Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;

    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {p1}, Lorg/telegram/messenger/ImageReceiver;->getImageX()F

    move-result p1

    const/high16 v0, 0x40000000    # 2.0f

    div-float v3, v6, v0

    add-float v4, p1, v3

    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {p0}, Lorg/telegram/messenger/ImageReceiver;->getImageY()F

    move-result p0

    div-float p1, v7, v0

    add-float v5, p0, p1

    move-object v3, p2

    invoke-virtual/range {v2 .. v7}, Lorg/telegram/messenger/RichMessageLayout$SpoilerReveal;->start(Landroid/view/View;FFFF)V

    goto :goto_0

    .line 8401
    :cond_8
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    invoke-static {p1}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$fgetdelegate(Lorg/telegram/messenger/RichMessageLayout;)Lorg/telegram/ui/Cells/ChatMessageCell$ChatMessageCellDelegate;

    move-result-object p1

    if-eqz p1, :cond_9

    .line 8402
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    invoke-static {p1}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$fgetdelegate(Lorg/telegram/messenger/RichMessageLayout;)Lorg/telegram/ui/Cells/ChatMessageCell$ChatMessageCellDelegate;

    move-result-object p1

    iget-object p2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    invoke-static {p2}, Lorg/telegram/messenger/RichMessageLayout;->-$$Nest$fgetcell(Lorg/telegram/messenger/RichMessageLayout;)Lorg/telegram/ui/Cells/ChatMessageCell;

    move-result-object p2

    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->pageBlock:Lorg/telegram/tgnet/tl/TL_iv$PageBlock;

    invoke-interface {p1, p2, p0}, Lorg/telegram/ui/Cells/ChatMessageCell$ChatMessageCellDelegate;->openArticlePhoto(Lorg/telegram/ui/Cells/ChatMessageCell;Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)Z

    :cond_9
    :goto_0
    return v1

    :cond_a
    return v3

    :cond_b
    const/4 p1, 0x3

    if-ne v0, p1, :cond_c

    .line 8410
    iput-boolean v3, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->photoPressed:Z

    iput-boolean v3, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonPressed:Z

    return v3

    .line 8412
    :cond_c
    iget-boolean p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->photoPressed:Z

    if-nez p1, :cond_e

    iget-boolean p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonPressed:Z

    if-eqz p0, :cond_d

    goto :goto_1

    :cond_d
    return v3

    :cond_e
    :goto_1
    return v1
.end method

.method public setRect(IIII)V
    .locals 5

    .line 8157
    iput p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->x:I

    iput p2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->y:I

    iput p3, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->w:I

    iput p4, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->h:I

    .line 8158
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    int-to-float v1, p1

    int-to-float v2, p2

    int-to-float v3, p3

    int-to-float v4, p4

    invoke-virtual {v0, v1, v2, v3, v4}, Lorg/telegram/messenger/ImageReceiver;->setImageCoords(FFFF)V

    .line 8159
    iget v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonSize:I

    sub-int/2addr p3, v0

    div-int/lit8 p3, p3, 0x2

    add-int/2addr p1, p3

    iput p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonX:I

    sub-int/2addr p4, v0

    .line 8160
    div-int/lit8 p4, p4, 0x2

    add-int/2addr p2, p4

    iput p2, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonY:I

    .line 8161
    iget-object p3, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    if-eqz p3, :cond_0

    add-int p4, p1, v0

    add-int/2addr v0, p2

    .line 8162
    invoke-virtual {p3, p1, p2, p4, v0}, Lorg/telegram/ui/Components/RadialProgress2;->setProgressRect(IIII)V

    .line 8164
    :cond_0
    invoke-direct {p0}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->computeAutoDownload()Z

    move-result p1

    iput-boolean p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->autoDownload:Z

    if-nez p1, :cond_2

    .line 8165
    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->fileExists()Z

    move-result p1

    if-eqz p1, :cond_1

    goto :goto_0

    :cond_1
    const/4 p1, 0x0

    goto :goto_1

    :cond_2
    :goto_0
    const/4 p1, 0x1

    :goto_1
    invoke-direct {p0, p1}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->applyImage(Z)V

    return-void
.end method

.method public updateButtonState(Landroid/view/View;Z)V
    .locals 9

    if-nez p1, :cond_0

    .line 8294
    iget-object p1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->parentView:Landroid/view/View;

    .line 8295
    :cond_0
    invoke-virtual {p0, p1}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->ensureProgress(Landroid/view/View;)V

    .line 8296
    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->getFileName()Ljava/lang/String;

    move-result-object v0

    .line 8297
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    const/4 v2, 0x4

    const/4 v3, -0x1

    const/4 v4, 0x0

    if-eqz v1, :cond_1

    .line 8298
    iput v3, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonState:I

    .line 8299
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    if-eqz p0, :cond_e

    invoke-virtual {p0, v2, v4, v4}, Lorg/telegram/ui/Components/RadialProgress2;->setIcon(IZZ)V

    return-void

    .line 8302
    :cond_1
    iget-object v1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v1}, Lorg/telegram/messenger/ImageReceiver;->getAnimation()Lorg/telegram/ui/Components/AnimatedFileDrawable;

    move-result-object v1

    const/4 v5, 0x1

    if-eqz v1, :cond_3

    .line 8303
    invoke-virtual {v1}, Lorg/telegram/ui/Components/AnimatedFileDrawable;->hasBitmap()Z

    move-result v1

    if-nez v1, :cond_2

    iget-object v1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->imageReceiver:Lorg/telegram/messenger/ImageReceiver;

    invoke-virtual {v1}, Lorg/telegram/messenger/ImageReceiver;->isAnimationRunning()Z

    move-result v1

    if-eqz v1, :cond_3

    :cond_2
    move v1, v5

    goto :goto_0

    :cond_3
    move v1, v4

    .line 8304
    :goto_0
    invoke-virtual {p0}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->fileExists()Z

    move-result v6

    const/4 v7, 0x2

    const/4 v8, 0x3

    if-nez v6, :cond_a

    iget-boolean v6, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->isVideo:Z

    if-eqz v6, :cond_4

    if-eqz v1, :cond_4

    goto :goto_3

    .line 8317
    :cond_4
    iget-object v1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    iget v1, v1, Lorg/telegram/messenger/RichMessageLayout;->currentAccount:I

    invoke-static {v1}, Lorg/telegram/messenger/DownloadController;->getInstance(I)Lorg/telegram/messenger/DownloadController;

    move-result-object v1

    const/4 v2, 0x0

    invoke-virtual {v1, v0, v2, p0}, Lorg/telegram/messenger/DownloadController;->addLoadingFileObserver(Ljava/lang/String;Lorg/telegram/messenger/MessageObject;Lorg/telegram/messenger/DownloadController$FileDownloadProgressListener;)V

    .line 8319
    iget-boolean v1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->autoDownload:Z

    const/4 v2, 0x0

    if-nez v1, :cond_7

    iget-boolean v1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->mediaForced:Z

    if-nez v1, :cond_7

    iget-object v1, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    iget v1, v1, Lorg/telegram/messenger/RichMessageLayout;->currentAccount:I

    invoke-static {v1}, Lorg/telegram/messenger/FileLoader;->getInstance(I)Lorg/telegram/messenger/FileLoader;

    move-result-object v1

    invoke-virtual {v1, v0}, Lorg/telegram/messenger/FileLoader;->isLoadingFile(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_5

    goto :goto_1

    .line 8324
    :cond_5
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->realVideo:Z

    if-eqz v0, :cond_6

    .line 8325
    iput v8, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonState:I

    .line 8326
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    if-eqz v0, :cond_9

    invoke-virtual {v0, v4, v5, p2}, Lorg/telegram/ui/Components/RadialProgress2;->setIcon(IZZ)V

    goto :goto_2

    .line 8328
    :cond_6
    iput v4, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonState:I

    .line 8329
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    if-eqz v0, :cond_9

    invoke-virtual {v0, v7, v5, p2}, Lorg/telegram/ui/Components/RadialProgress2;->setIcon(IZZ)V

    goto :goto_2

    .line 8320
    :cond_7
    :goto_1
    iput v5, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonState:I

    .line 8321
    invoke-static {}, Lorg/telegram/messenger/ImageLoader;->getInstance()Lorg/telegram/messenger/ImageLoader;

    move-result-object v1

    invoke-virtual {v1, v0}, Lorg/telegram/messenger/ImageLoader;->getFileProgress(Ljava/lang/String;)Ljava/lang/Float;

    move-result-object v0

    if-eqz v0, :cond_8

    .line 8322
    invoke-virtual {v0}, Ljava/lang/Float;->floatValue()F

    move-result v2

    .line 8323
    :cond_8
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    if-eqz v0, :cond_9

    invoke-virtual {v0, v8, v5, p2}, Lorg/telegram/ui/Components/RadialProgress2;->setIcon(IZZ)V

    .line 8331
    :cond_9
    :goto_2
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    if-eqz p0, :cond_d

    invoke-virtual {p0, v2, v4}, Lorg/telegram/ui/Components/RadialProgress2;->setProgress(FZ)V

    goto :goto_4

    .line 8305
    :cond_a
    :goto_3
    iget-object v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->root:Lorg/telegram/messenger/RichMessageLayout;

    iget v0, v0, Lorg/telegram/messenger/RichMessageLayout;->currentAccount:I

    invoke-static {v0}, Lorg/telegram/messenger/DownloadController;->getInstance(I)Lorg/telegram/messenger/DownloadController;

    move-result-object v0

    invoke-virtual {v0, p0}, Lorg/telegram/messenger/DownloadController;->removeLoadingFileObserver(Lorg/telegram/messenger/DownloadController$FileDownloadProgressListener;)V

    .line 8306
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->realVideo:Z

    if-eqz v0, :cond_b

    if-nez v1, :cond_b

    .line 8307
    iput v8, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonState:I

    .line 8308
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    if-eqz p0, :cond_d

    invoke-virtual {p0, v4, v4, p2}, Lorg/telegram/ui/Components/RadialProgress2;->setIcon(IZZ)V

    goto :goto_4

    .line 8309
    :cond_b
    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->isVideo:Z

    if-eqz v0, :cond_c

    if-nez v1, :cond_c

    invoke-direct {p0}, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->allowAutoplay()Z

    move-result v0

    if-nez v0, :cond_c

    iget-boolean v0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->mediaForced:Z

    if-nez v0, :cond_c

    .line 8310
    iput v7, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonState:I

    .line 8311
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    if-eqz p0, :cond_d

    const/16 v0, 0x8

    invoke-virtual {p0, v0, v4, p2}, Lorg/telegram/ui/Components/RadialProgress2;->setIcon(IZZ)V

    goto :goto_4

    .line 8313
    :cond_c
    iput v3, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->buttonState:I

    .line 8314
    iget-object p0, p0, Lorg/telegram/messenger/RichMessageLayout$MediaCell;->radialProgress:Lorg/telegram/ui/Components/RadialProgress2;

    if-eqz p0, :cond_d

    invoke-virtual {p0, v2, v4, p2}, Lorg/telegram/ui/Components/RadialProgress2;->setIcon(IZZ)V

    :cond_d
    :goto_4
    if-eqz p1, :cond_e

    .line 8333
    invoke-virtual {p1}, Landroid/view/View;->invalidate()V

    :cond_e
    return-void
.end method
