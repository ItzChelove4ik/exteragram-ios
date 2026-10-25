.class public Lorg/telegram/messenger/MediaController$PhotoEntry;
.super Lorg/telegram/messenger/MediaController$MediaEditState;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/messenger/MediaController;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "PhotoEntry"
.end annotation


# static fields
.field private static livePhotoQueue:Lorg/telegram/messenger/DispatchQueue;


# instance fields
.field public bucketId:I

.field public canDeleteAfter:Z

.field public dateTaken:J

.field public discardLivePhoto:Ljava/lang/Boolean;

.field public duration:I

.field public emoji:Ljava/lang/String;

.field public emojiMarkup:Lorg/telegram/tgnet/TLRPC$VideoSize;

.field public gradientBottomColor:I

.field public gradientTopColor:I

.field public hasSpoiler:Z

.field public height:I

.field public imageId:I

.field public invert:I

.field public isAttachSpoilerRevealed:Z

.field public isChatPreviewSpoilerRevealed:Z

.field public isLivePhoto:Z

.field public isMuted:Z

.field private livePhotoParseRunnable:Ljava/lang/Runnable;

.field public livePhotoTimestampUs:J

.field public orientation:I

.field private parsedXmp:Z

.field public path:Ljava/lang/String;

.field public size:J

.field public starsAmount:J

.field public thumb:Landroid/graphics/drawable/BitmapDrawable;

.field public videoOrientation:I

.field public width:I


# direct methods
.method public static synthetic $r8$lambda$00rTUQG4eGysbgHZO8BUAfWCv4Y(Lorg/telegram/messenger/MediaController$PhotoEntry;[JLjava/lang/Runnable;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/messenger/MediaController$PhotoEntry;->lambda$isLivePhotoAsync$0([JLjava/lang/Runnable;)V

    return-void
.end method

.method public static synthetic $r8$lambda$5uL1E1cylqV2HhYfXapshlkeIGI(Lorg/telegram/messenger/MediaController$PhotoEntry;Ljava/lang/String;Ljava/lang/Runnable;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/messenger/MediaController$PhotoEntry;->lambda$isLivePhotoAsync$1(Ljava/lang/String;Ljava/lang/Runnable;)V

    return-void
.end method

.method public static synthetic $r8$lambda$Dg9GKh3ZBT33Nm4pT0eG3r8mFP8(Lorg/telegram/messenger/MediaController$PhotoEntry;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/messenger/MediaController$PhotoEntry;->lambda$rebuildPhoto$2(Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;

    move-result-object p0

    return-object p0
.end method

.method public constructor <init>(IIJLjava/lang/String;IIZIIJ)V
    .locals 1

    .line 742
    invoke-direct {p0}, Lorg/telegram/messenger/MediaController$MediaEditState;-><init>()V

    const/4 v0, -0x1

    .line 627
    iput v0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->videoOrientation:I

    .line 743
    iput p1, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->bucketId:I

    .line 744
    iput p2, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->imageId:I

    .line 745
    iput-wide p3, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->dateTaken:J

    .line 746
    iput-object p5, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->path:Ljava/lang/String;

    .line 747
    iput p9, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->width:I

    .line 748
    iput p10, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->height:I

    .line 749
    iput-wide p11, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->size:J

    .line 750
    iput p7, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->duration:I

    .line 751
    iput p6, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->orientation:I

    .line 752
    iput-boolean p8, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->isVideo:Z

    return-void
.end method

.method public constructor <init>(IIJLjava/lang/String;IZIIJ)V
    .locals 1

    .line 726
    invoke-direct {p0}, Lorg/telegram/messenger/MediaController$MediaEditState;-><init>()V

    const/4 v0, -0x1

    .line 627
    iput v0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->videoOrientation:I

    .line 727
    iput p1, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->bucketId:I

    .line 728
    iput p2, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->imageId:I

    .line 729
    iput-wide p3, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->dateTaken:J

    .line 730
    iput-object p5, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->path:Ljava/lang/String;

    .line 731
    iput p8, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->width:I

    .line 732
    iput p9, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->height:I

    .line 733
    iput-wide p10, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->size:J

    if-eqz p7, :cond_0

    .line 735
    iput p6, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->duration:I

    goto :goto_0

    .line 737
    :cond_0
    iput p6, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->orientation:I

    .line 739
    :goto_0
    iput-boolean p7, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->isVideo:Z

    return-void
.end method

.method private applyLivePhoto([J)V
    .locals 3

    const/4 v0, 0x1

    .line 679
    iput-boolean v0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->parsedXmp:Z

    const/4 v1, 0x0

    if-eqz p1, :cond_0

    .line 681
    iput-boolean v0, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->isVideo:Z

    .line 682
    iput-boolean v0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->isLivePhoto:Z

    .line 683
    aget-wide v1, p1, v1

    iput-wide v1, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->livePhotoVideoOffset:J

    .line 684
    aget-wide v0, p1, v0

    iput-wide v0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->livePhotoTimestampUs:J

    return-void

    .line 686
    :cond_0
    iput-boolean v1, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->isLivePhoto:Z

    return-void
.end method

.method private synthetic lambda$isLivePhotoAsync$0([JLjava/lang/Runnable;)V
    .locals 1

    const/4 v0, 0x0

    .line 659
    iput-object v0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->livePhotoParseRunnable:Ljava/lang/Runnable;

    .line 660
    iget-boolean v0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->parsedXmp:Z

    if-nez v0, :cond_0

    .line 661
    invoke-direct {p0, p1}, Lorg/telegram/messenger/MediaController$PhotoEntry;->applyLivePhoto([J)V

    .line 663
    :cond_0
    invoke-interface {p2}, Ljava/lang/Runnable;->run()V

    return-void
.end method

.method private synthetic lambda$isLivePhotoAsync$1(Ljava/lang/String;Ljava/lang/Runnable;)V
    .locals 1

    .line 657
    invoke-static {p1}, Lorg/telegram/messenger/MediaController$PhotoEntry;->parseLivePhoto(Ljava/lang/String;)[J

    move-result-object p1

    .line 658
    new-instance v0, Lorg/telegram/messenger/MediaController$PhotoEntry$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0, p1, p2}, Lorg/telegram/messenger/MediaController$PhotoEntry$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/messenger/MediaController$PhotoEntry;[JLjava/lang/Runnable;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private synthetic lambda$rebuildPhoto$2(Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    .locals 1

    .line 849
    iget-object v0, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->filterPath:Ljava/lang/String;

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->path:Ljava/lang/String;

    :goto_0
    invoke-static {v0, p1}, Landroid/graphics/BitmapFactory;->decodeFile(Ljava/lang/String;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;

    move-result-object p0

    return-object p0
.end method

.method private static parseLivePhoto(Ljava/lang/String;)[J
    .locals 12

    .line 692
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    const/4 v2, 0x1

    const/4 v3, 0x0

    const/4 v4, 0x0

    .line 694
    :try_start_0
    new-instance v5, Landroidx/exifinterface/media/ExifInterface;

    new-instance v6, Ljava/io/File;

    invoke-direct {v6, p0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-direct {v5, v6}, Landroidx/exifinterface/media/ExifInterface;-><init>(Ljava/io/File;)V

    .line 695
    const-string v6, "Xmp"

    invoke-virtual {v5, v6}, Landroidx/exifinterface/media/ExifInterface;->getAttribute(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    if-eqz v5, :cond_3

    .line 697
    invoke-static {v5}, Landroidx/media3/extractor/jpeg/XmpMotionPhotoDescriptionParser;->parse(Ljava/lang/String;)Landroidx/media3/extractor/jpeg/MotionPhotoDescription;

    move-result-object v5

    if-eqz v5, :cond_3

    move v6, v3

    move-object v7, v4

    move-object v8, v7

    .line 700
    :goto_0
    iget-object v9, v5, Landroidx/media3/extractor/jpeg/MotionPhotoDescription;->items:Ljava/util/List;

    invoke-interface {v9}, Ljava/util/List;->size()I

    move-result v9

    if-ge v6, v9, :cond_2

    .line 701
    iget-object v9, v5, Landroidx/media3/extractor/jpeg/MotionPhotoDescription;->items:Ljava/util/List;

    invoke-interface {v9, v6}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v9

    check-cast v9, Landroidx/media3/extractor/jpeg/MotionPhotoDescription$ContainerItem;

    .line 702
    const-string v10, "Primary"

    iget-object v11, v9, Landroidx/media3/extractor/jpeg/MotionPhotoDescription$ContainerItem;->semantic:Ljava/lang/String;

    invoke-virtual {v10, v11}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v10

    if-eqz v10, :cond_0

    move-object v7, v9

    goto :goto_1

    .line 704
    :cond_0
    const-string v10, "MotionPhoto"

    iget-object v11, v9, Landroidx/media3/extractor/jpeg/MotionPhotoDescription$ContainerItem;->semantic:Ljava/lang/String;

    invoke-virtual {v10, v11}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v10

    if-eqz v10, :cond_1

    move-object v8, v9

    :cond_1
    :goto_1
    add-int/lit8 v6, v6, 0x1

    goto :goto_0

    :catch_0
    move-exception p0

    goto :goto_2

    :cond_2
    if-eqz v7, :cond_3

    if-eqz v8, :cond_3

    .line 708
    iget-wide v6, v8, Landroidx/media3/extractor/jpeg/MotionPhotoDescription$ContainerItem;->length:J
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    const-wide/16 v9, 0x0

    cmp-long v6, v6, v9

    if-lez v6, :cond_3

    .line 710
    :try_start_1
    new-instance v6, Ljava/io/File;

    invoke-direct {v6, p0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 711
    invoke-virtual {v6}, Ljava/io/File;->length()J

    move-result-wide v6

    iget-wide v8, v8, Landroidx/media3/extractor/jpeg/MotionPhotoDescription$ContainerItem;->length:J

    sub-long/2addr v6, v8

    const/4 p0, 0x2

    .line 712
    new-array p0, p0, [J

    aput-wide v6, p0, v3

    iget-wide v5, v5, Landroidx/media3/extractor/jpeg/MotionPhotoDescription;->photoPresentationTimestampUs:J

    aput-wide v5, p0, v2
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    move-object v4, p0

    goto :goto_3

    :catch_1
    move-exception p0

    .line 714
    :try_start_2
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    goto :goto_3

    .line 720
    :goto_2
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 722
    :cond_3
    :goto_3
    new-instance p0, Ljava/lang/StringBuilder;

    const-string v5, "parsed isLivePhoto()="

    invoke-direct {p0, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    if-eqz v4, :cond_4

    goto :goto_4

    :cond_4
    move v2, v3

    :goto_4
    invoke-virtual {p0, v2}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v2, " in "

    invoke-virtual {p0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    sub-long/2addr v2, v0

    invoke-virtual {p0, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, "ms"

    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    return-object v4
.end method


# virtual methods
.method public cancelLivePhotoParse()V
    .locals 2

    .line 672
    iget-object v0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->livePhotoParseRunnable:Ljava/lang/Runnable;

    if-eqz v0, :cond_0

    .line 673
    sget-object v1, Lorg/telegram/messenger/MediaController$PhotoEntry;->livePhotoQueue:Lorg/telegram/messenger/DispatchQueue;

    invoke-virtual {v1, v0}, Lorg/telegram/messenger/DispatchQueue;->cancelRunnable(Ljava/lang/Runnable;)V

    const/4 v0, 0x0

    .line 674
    iput-object v0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->livePhotoParseRunnable:Ljava/lang/Runnable;

    :cond_0
    return-void
.end method

.method public bridge synthetic clone()Ljava/lang/Object;
    .locals 0

    .line 601
    invoke-virtual {p0}, Lorg/telegram/messenger/MediaController$PhotoEntry;->clone()Lorg/telegram/messenger/MediaController$PhotoEntry;

    move-result-object p0

    return-object p0
.end method

.method public clone()Lorg/telegram/messenger/MediaController$PhotoEntry;
    .locals 13

    .line 779
    new-instance v0, Lorg/telegram/messenger/MediaController$PhotoEntry;

    iget v1, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->bucketId:I

    iget v2, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->imageId:I

    iget-wide v3, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->dateTaken:J

    iget-object v5, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->path:Ljava/lang/String;

    iget v6, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->orientation:I

    iget v7, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->duration:I

    iget-boolean v8, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->isVideo:Z

    iget v9, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->width:I

    iget v10, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->height:I

    iget-wide v11, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->size:J

    invoke-direct/range {v0 .. v12}, Lorg/telegram/messenger/MediaController$PhotoEntry;-><init>(IIJLjava/lang/String;IIZIIJ)V

    .line 780
    iget v1, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->invert:I

    iput v1, v0, Lorg/telegram/messenger/MediaController$PhotoEntry;->invert:I

    .line 781
    iget-boolean v1, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->isMuted:Z

    iput-boolean v1, v0, Lorg/telegram/messenger/MediaController$PhotoEntry;->isMuted:Z

    .line 782
    iget-boolean v1, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->canDeleteAfter:Z

    iput-boolean v1, v0, Lorg/telegram/messenger/MediaController$PhotoEntry;->canDeleteAfter:Z

    .line 783
    iget-boolean v1, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->hasSpoiler:Z

    iput-boolean v1, v0, Lorg/telegram/messenger/MediaController$PhotoEntry;->hasSpoiler:Z

    .line 784
    iget-wide v1, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->starsAmount:J

    iput-wide v1, v0, Lorg/telegram/messenger/MediaController$PhotoEntry;->starsAmount:J

    .line 785
    iget-boolean v1, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->isChatPreviewSpoilerRevealed:Z

    iput-boolean v1, v0, Lorg/telegram/messenger/MediaController$PhotoEntry;->isChatPreviewSpoilerRevealed:Z

    .line 786
    iget-boolean v1, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->isAttachSpoilerRevealed:Z

    iput-boolean v1, v0, Lorg/telegram/messenger/MediaController$PhotoEntry;->isAttachSpoilerRevealed:Z

    .line 787
    iget-object v1, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->emojiMarkup:Lorg/telegram/tgnet/TLRPC$VideoSize;

    iput-object v1, v0, Lorg/telegram/messenger/MediaController$PhotoEntry;->emojiMarkup:Lorg/telegram/tgnet/TLRPC$VideoSize;

    .line 788
    iget v1, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->gradientTopColor:I

    iput v1, v0, Lorg/telegram/messenger/MediaController$PhotoEntry;->gradientTopColor:I

    .line 789
    iget v1, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->gradientBottomColor:I

    iput v1, v0, Lorg/telegram/messenger/MediaController$PhotoEntry;->gradientBottomColor:I

    .line 790
    iget-object v1, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->discardLivePhoto:Ljava/lang/Boolean;

    iput-object v1, v0, Lorg/telegram/messenger/MediaController$PhotoEntry;->discardLivePhoto:Ljava/lang/Boolean;

    .line 791
    invoke-virtual {v0, p0}, Lorg/telegram/messenger/MediaController$PhotoEntry;->copyFrom(Lorg/telegram/messenger/MediaController$MediaEditState;)V

    return-object v0
.end method

.method public copyFrom(Lorg/telegram/messenger/MediaController$MediaEditState;)V
    .locals 7

    .line 769
    invoke-super {p0, p1}, Lorg/telegram/messenger/MediaController$MediaEditState;->copyFrom(Lorg/telegram/messenger/MediaController$MediaEditState;)V

    .line 770
    instance-of v0, p1, Lorg/telegram/messenger/MediaController$PhotoEntry;

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-eqz v0, :cond_0

    move-object v3, p1

    check-cast v3, Lorg/telegram/messenger/MediaController$PhotoEntry;

    iget-boolean v3, v3, Lorg/telegram/messenger/MediaController$PhotoEntry;->hasSpoiler:Z

    if-eqz v3, :cond_0

    move v3, v2

    goto :goto_0

    :cond_0
    move v3, v1

    :goto_0
    iput-boolean v3, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->hasSpoiler:Z

    const-wide/16 v3, 0x0

    if-eqz v0, :cond_1

    .line 771
    move-object v5, p1

    check-cast v5, Lorg/telegram/messenger/MediaController$PhotoEntry;

    iget-wide v5, v5, Lorg/telegram/messenger/MediaController$PhotoEntry;->starsAmount:J

    goto :goto_1

    :cond_1
    move-wide v5, v3

    :goto_1
    iput-wide v5, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->starsAmount:J

    if-eqz v0, :cond_2

    .line 772
    move-object v5, p1

    check-cast v5, Lorg/telegram/messenger/MediaController$PhotoEntry;

    iget-boolean v5, v5, Lorg/telegram/messenger/MediaController$PhotoEntry;->parsedXmp:Z

    if-eqz v5, :cond_2

    move v5, v2

    goto :goto_2

    :cond_2
    move v5, v1

    :goto_2
    iput-boolean v5, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->parsedXmp:Z

    if-eqz v0, :cond_3

    .line 773
    move-object v5, p1

    check-cast v5, Lorg/telegram/messenger/MediaController$PhotoEntry;

    iget-boolean v5, v5, Lorg/telegram/messenger/MediaController$PhotoEntry;->isLivePhoto:Z

    if-eqz v5, :cond_3

    move v1, v2

    :cond_3
    iput-boolean v1, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->isLivePhoto:Z

    if-eqz v0, :cond_4

    .line 774
    move-object v1, p1

    check-cast v1, Lorg/telegram/messenger/MediaController$PhotoEntry;

    iget-wide v1, v1, Lorg/telegram/messenger/MediaController$MediaEditState;->livePhotoVideoOffset:J

    goto :goto_3

    :cond_4
    move-wide v1, v3

    :goto_3
    iput-wide v1, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->livePhotoVideoOffset:J

    if-eqz v0, :cond_5

    .line 775
    check-cast p1, Lorg/telegram/messenger/MediaController$PhotoEntry;

    iget-wide v3, p1, Lorg/telegram/messenger/MediaController$PhotoEntry;->livePhotoTimestampUs:J

    :cond_5
    iput-wide v3, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->livePhotoTimestampUs:J

    return-void
.end method

.method public deleteAll()V
    .locals 2

    .line 814
    iget-object v0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->path:Ljava/lang/String;

    if-eqz v0, :cond_0

    .line 816
    :try_start_0
    new-instance v0, Ljava/io/File;

    iget-object v1, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->path:Ljava/lang/String;

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->delete()Z
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 819
    :catch_0
    :cond_0
    iget-object v0, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->fullPaintPath:Ljava/lang/String;

    if-eqz v0, :cond_1

    .line 821
    :try_start_1
    new-instance v0, Ljava/io/File;

    iget-object v1, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->fullPaintPath:Ljava/lang/String;

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->delete()Z
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    .line 824
    :catch_1
    :cond_1
    iget-object v0, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->paintPath:Ljava/lang/String;

    if-eqz v0, :cond_2

    .line 826
    :try_start_2
    new-instance v0, Ljava/io/File;

    iget-object v1, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->paintPath:Ljava/lang/String;

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->delete()Z
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_2

    .line 829
    :catch_2
    :cond_2
    iget-object v0, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->imagePath:Ljava/lang/String;

    if-eqz v0, :cond_3

    .line 831
    :try_start_3
    new-instance v0, Ljava/io/File;

    iget-object v1, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->imagePath:Ljava/lang/String;

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->delete()Z
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_3

    .line 834
    :catch_3
    :cond_3
    iget-object v0, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->filterPath:Ljava/lang/String;

    if-eqz v0, :cond_4

    .line 836
    :try_start_4
    new-instance v0, Ljava/io/File;

    iget-object v1, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->filterPath:Ljava/lang/String;

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->delete()Z
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_4

    .line 839
    :catch_4
    :cond_4
    iget-object v0, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->croppedPaintPath:Ljava/lang/String;

    if-eqz v0, :cond_5

    .line 841
    :try_start_5
    new-instance v0, Ljava/io/File;

    iget-object p0, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->croppedPaintPath:Ljava/lang/String;

    invoke-direct {v0, p0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->delete()Z
    :try_end_5
    .catch Ljava/lang/Exception; {:try_start_5 .. :try_end_5} :catch_5

    :catch_5
    :cond_5
    return-void
.end method

.method public getPath()Ljava/lang/String;
    .locals 0

    .line 797
    iget-object p0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->path:Ljava/lang/String;

    return-object p0
.end method

.method public isLivePhoto()Z
    .locals 1

    .line 644
    iget-boolean v0, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->isVideo:Z

    if-nez v0, :cond_1

    iget-boolean v0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->parsedXmp:Z

    if-eqz v0, :cond_0

    goto :goto_0

    .line 645
    :cond_0
    iget-object v0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->path:Ljava/lang/String;

    invoke-static {v0}, Lorg/telegram/messenger/MediaController$PhotoEntry;->parseLivePhoto(Ljava/lang/String;)[J

    move-result-object v0

    invoke-direct {p0, v0}, Lorg/telegram/messenger/MediaController$PhotoEntry;->applyLivePhoto([J)V

    .line 646
    iget-boolean p0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->isLivePhoto:Z

    return p0

    .line 644
    :cond_1
    :goto_0
    iget-boolean p0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->isLivePhoto:Z

    return p0
.end method

.method public isLivePhotoAsync(Ljava/lang/Runnable;)Z
    .locals 2

    .line 650
    iget-boolean v0, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->isVideo:Z

    if-nez v0, :cond_3

    iget-boolean v0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->parsedXmp:Z

    if-eqz v0, :cond_0

    goto :goto_0

    .line 651
    :cond_0
    iget-object v0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->livePhotoParseRunnable:Ljava/lang/Runnable;

    if-nez v0, :cond_2

    .line 652
    sget-object v0, Lorg/telegram/messenger/MediaController$PhotoEntry;->livePhotoQueue:Lorg/telegram/messenger/DispatchQueue;

    if-nez v0, :cond_1

    .line 653
    new-instance v0, Lorg/telegram/messenger/DispatchQueue;

    const-string v1, "livePhotoQueue"

    invoke-direct {v0, v1}, Lorg/telegram/messenger/DispatchQueue;-><init>(Ljava/lang/String;)V

    sput-object v0, Lorg/telegram/messenger/MediaController$PhotoEntry;->livePhotoQueue:Lorg/telegram/messenger/DispatchQueue;

    .line 655
    :cond_1
    iget-object v0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->path:Ljava/lang/String;

    .line 656
    new-instance v1, Lorg/telegram/messenger/MediaController$PhotoEntry$$ExternalSyntheticLambda1;

    invoke-direct {v1, p0, v0, p1}, Lorg/telegram/messenger/MediaController$PhotoEntry$$ExternalSyntheticLambda1;-><init>(Lorg/telegram/messenger/MediaController$PhotoEntry;Ljava/lang/String;Ljava/lang/Runnable;)V

    iput-object v1, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->livePhotoParseRunnable:Ljava/lang/Runnable;

    .line 666
    sget-object p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->livePhotoQueue:Lorg/telegram/messenger/DispatchQueue;

    invoke-virtual {p0, v1}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    :cond_2
    const/4 p0, 0x0

    return p0

    .line 650
    :cond_3
    :goto_0
    iget-boolean p0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->isLivePhoto:Z

    return p0
.end method

.method public isUnalivePhoto()Z
    .locals 0

    .line 620
    iget-object p0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->discardLivePhoto:Ljava/lang/Boolean;

    if-nez p0, :cond_0

    .line 621
    sget-boolean p0, Lorg/telegram/messenger/SharedConfig;->photoLiveDefault:Z

    xor-int/lit8 p0, p0, 0x1

    return p0

    .line 622
    :cond_0
    invoke-virtual {p0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0

    return p0
.end method

.method public rebuildPhoto(Z)V
    .locals 18

    move-object/from16 v0, p0

    .line 847
    iget-object v1, v0, Lorg/telegram/messenger/MediaController$MediaEditState;->filterPath:Ljava/lang/String;

    if-eqz v1, :cond_0

    goto :goto_0

    :cond_0
    iget-object v1, v0, Lorg/telegram/messenger/MediaController$PhotoEntry;->path:Ljava/lang/String;

    :goto_0
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->getImageOrientation(Ljava/lang/String;)Landroid/util/Pair;

    move-result-object v1

    .line 848
    sget-object v3, Landroid/graphics/Bitmap$CompressFormat;->JPEG:Landroid/graphics/Bitmap$CompressFormat;

    .line 849
    new-instance v2, Lorg/telegram/messenger/MediaController$PhotoEntry$$ExternalSyntheticLambda2;

    invoke-direct {v2, v0}, Lorg/telegram/messenger/MediaController$PhotoEntry$$ExternalSyntheticLambda2;-><init>(Lorg/telegram/messenger/MediaController$PhotoEntry;)V

    invoke-static/range {p1 .. p1}, Lorg/telegram/messenger/AndroidUtilities;->getPhotoSize(Z)I

    move-result v4

    invoke-static/range {p1 .. p1}, Lorg/telegram/messenger/AndroidUtilities;->getPhotoSize(Z)I

    move-result v5

    const/4 v6, 0x0

    const/4 v10, 0x1

    invoke-static {v2, v4, v5, v6, v10}, Lorg/telegram/ui/Stories/recorder/StoryEntry;->getScaledBitmap(Lorg/telegram/ui/Stories/recorder/StoryEntry$DecodeBitmap;IIZZ)Landroid/graphics/Bitmap;

    move-result-object v11

    .line 850
    iget-object v2, v0, Lorg/telegram/messenger/MediaController$MediaEditState;->imagePath:Ljava/lang/String;

    const/4 v4, 0x0

    if-eqz v2, :cond_1

    .line 851
    new-instance v2, Ljava/io/File;

    iget-object v5, v0, Lorg/telegram/messenger/MediaController$MediaEditState;->imagePath:Ljava/lang/String;

    invoke-direct {v2, v5}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2}, Ljava/io/File;->delete()Z

    iput-object v4, v0, Lorg/telegram/messenger/MediaController$MediaEditState;->imagePath:Ljava/lang/String;

    .line 855
    :cond_1
    iget-object v2, v0, Lorg/telegram/messenger/MediaController$MediaEditState;->cropState:Lorg/telegram/messenger/MediaController$CropState;

    if-eqz v2, :cond_2

    .line 856
    iget-object v5, v1, Landroid/util/Pair;->first:Ljava/lang/Object;

    check-cast v5, Ljava/lang/Integer;

    invoke-virtual {v5}, Ljava/lang/Integer;->intValue()I

    move-result v5

    iget-object v1, v1, Landroid/util/Pair;->second:Ljava/lang/Object;

    check-cast v1, Ljava/lang/Integer;

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    filled-new-array {v5, v1}, [I

    move-result-object v1

    invoke-static {v11, v2, v1, v10}, Lorg/telegram/ui/PhotoViewer;->createCroppedBitmap(Landroid/graphics/Bitmap;Lorg/telegram/messenger/MediaController$CropState;[IZ)Landroid/graphics/Bitmap;

    move-result-object v1

    .line 857
    invoke-static {v11}, Lorg/telegram/messenger/AndroidUtilities;->recycleBitmap(Landroid/graphics/Bitmap;)V

    :goto_1
    move-object v2, v1

    goto :goto_3

    .line 859
    :cond_2
    iget-object v2, v1, Landroid/util/Pair;->first:Ljava/lang/Object;

    check-cast v2, Ljava/lang/Integer;

    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v2

    if-eqz v2, :cond_5

    .line 860
    new-instance v2, Landroid/graphics/Matrix;

    invoke-direct {v2}, Landroid/graphics/Matrix;-><init>()V

    .line 861
    iget-object v5, v1, Landroid/util/Pair;->first:Ljava/lang/Object;

    check-cast v5, Ljava/lang/Integer;

    invoke-virtual {v5}, Ljava/lang/Integer;->intValue()I

    move-result v5

    int-to-float v5, v5

    invoke-virtual {v2, v5}, Landroid/graphics/Matrix;->postRotate(F)Z

    .line 862
    iget-object v5, v1, Landroid/util/Pair;->second:Ljava/lang/Object;

    check-cast v5, Ljava/lang/Integer;

    invoke-virtual {v5}, Ljava/lang/Integer;->intValue()I

    move-result v5

    const/high16 v7, 0x3f800000    # 1.0f

    const/high16 v8, -0x40800000    # -1.0f

    if-ne v5, v10, :cond_3

    .line 863
    invoke-virtual {v2, v8, v7}, Landroid/graphics/Matrix;->postScale(FF)Z

    goto :goto_2

    .line 864
    :cond_3
    iget-object v1, v1, Landroid/util/Pair;->second:Ljava/lang/Object;

    check-cast v1, Ljava/lang/Integer;

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    const/4 v5, 0x2

    if-ne v1, v5, :cond_4

    .line 865
    invoke-virtual {v2, v7, v8}, Landroid/graphics/Matrix;->postScale(FF)Z

    .line 867
    :cond_4
    :goto_2
    invoke-virtual {v11}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v14

    invoke-virtual {v11}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v15

    const/16 v17, 0x1

    const/4 v12, 0x0

    const/4 v13, 0x0

    move-object/from16 v16, v2

    invoke-static/range {v11 .. v17}, Lorg/telegram/messenger/Bitmaps;->createBitmap(Landroid/graphics/Bitmap;IIIILandroid/graphics/Matrix;Z)Landroid/graphics/Bitmap;

    move-result-object v1

    .line 868
    invoke-static {v11}, Lorg/telegram/messenger/AndroidUtilities;->recycleBitmap(Landroid/graphics/Bitmap;)V

    goto :goto_1

    :cond_5
    move-object v2, v11

    .line 873
    :goto_3
    iget-object v1, v0, Lorg/telegram/messenger/MediaController$MediaEditState;->fullPaintPath:Ljava/lang/String;

    const/16 v5, 0x57

    const/16 v7, 0x63

    if-nez v1, :cond_7

    .line 874
    invoke-static/range {p1 .. p1}, Lorg/telegram/messenger/AndroidUtilities;->getPhotoSize(Z)I

    move-result v1

    int-to-float v4, v1

    invoke-static/range {p1 .. p1}, Lorg/telegram/messenger/AndroidUtilities;->getPhotoSize(Z)I

    move-result v1

    int-to-float v1, v1

    if-eqz p1, :cond_6

    move v6, v7

    goto :goto_4

    :cond_6
    move v6, v5

    :goto_4
    const/16 v8, 0x65

    const/16 v9, 0x65

    const/4 v7, 0x0

    move v5, v1

    invoke-static/range {v2 .. v9}, Lorg/telegram/messenger/ImageLoader;->scaleAndSaveImage(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap$CompressFormat;FFIZII)Lorg/telegram/tgnet/TLRPC$PhotoSize;

    move-result-object v1

    .line 875
    sget v3, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {v3}, Lorg/telegram/messenger/FileLoader;->getInstance(I)Lorg/telegram/messenger/FileLoader;

    move-result-object v3

    invoke-virtual {v3, v1, v10}, Lorg/telegram/messenger/FileLoader;->getPathToAttach(Lorg/telegram/tgnet/TLObject;Z)Ljava/io/File;

    move-result-object v1

    invoke-virtual {v1}, Ljava/io/File;->toString()Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lorg/telegram/messenger/MediaController$MediaEditState;->imagePath:Ljava/lang/String;

    goto :goto_7

    .line 878
    :cond_7
    iget-object v8, v0, Lorg/telegram/messenger/MediaController$MediaEditState;->cropState:Lorg/telegram/messenger/MediaController$CropState;

    if-eqz v8, :cond_8

    .line 879
    invoke-static {v1}, Landroid/graphics/BitmapFactory;->decodeFile(Ljava/lang/String;)Landroid/graphics/Bitmap;

    move-result-object v1

    .line 880
    iget-object v8, v0, Lorg/telegram/messenger/MediaController$MediaEditState;->cropState:Lorg/telegram/messenger/MediaController$CropState;

    invoke-static {v1, v8, v4, v6}, Lorg/telegram/ui/PhotoViewer;->createCroppedBitmap(Landroid/graphics/Bitmap;Lorg/telegram/messenger/MediaController$CropState;[IZ)Landroid/graphics/Bitmap;

    move-result-object v4

    .line 881
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->recycleBitmap(Landroid/graphics/Bitmap;)V

    goto :goto_5

    .line 883
    :cond_8
    invoke-static {v1}, Landroid/graphics/BitmapFactory;->decodeFile(Ljava/lang/String;)Landroid/graphics/Bitmap;

    move-result-object v4

    .line 886
    :goto_5
    :try_start_0
    new-instance v1, Landroid/graphics/Paint;

    const/4 v6, 0x3

    invoke-direct {v1, v6}, Landroid/graphics/Paint;-><init>(I)V

    .line 887
    invoke-virtual {v2}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v6

    invoke-virtual {v2}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v8

    sget-object v9, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {v6, v8, v9}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v6

    .line 888
    new-instance v8, Landroid/graphics/Canvas;

    invoke-direct {v8, v6}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    const/4 v9, 0x0

    .line 890
    invoke-virtual {v8, v2, v9, v9, v1}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V

    .line 891
    invoke-virtual {v2}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v10

    int-to-float v10, v10

    invoke-virtual {v4}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v11

    int-to-float v11, v11

    div-float/2addr v10, v11

    invoke-virtual {v2}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v11

    int-to-float v11, v11

    invoke-virtual {v4}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v12

    int-to-float v12, v12

    div-float/2addr v11, v12

    invoke-virtual {v8, v10, v11}, Landroid/graphics/Canvas;->scale(FF)V

    .line 892
    invoke-virtual {v8, v4, v9, v9, v1}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V

    .line 894
    invoke-static {}, Lorg/telegram/ui/PhotoViewer;->getTempFileAbsolutePath()Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lorg/telegram/messenger/MediaController$MediaEditState;->imagePath:Ljava/lang/String;

    if-eqz p1, :cond_9

    move v5, v7

    .line 895
    :cond_9
    new-instance v1, Ljava/io/FileOutputStream;

    iget-object v0, v0, Lorg/telegram/messenger/MediaController$MediaEditState;->imagePath:Ljava/lang/String;

    invoke-direct {v1, v0}, Ljava/io/FileOutputStream;-><init>(Ljava/lang/String;)V

    invoke-virtual {v6, v3, v5, v1}, Landroid/graphics/Bitmap;->compress(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_6

    :catch_0
    move-exception v0

    .line 897
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :goto_6
    if-eqz v4, :cond_a

    .line 900
    invoke-virtual {v4}, Landroid/graphics/Bitmap;->recycle()V

    :cond_a
    :goto_7
    if-eqz v2, :cond_b

    .line 904
    invoke-virtual {v2}, Landroid/graphics/Bitmap;->recycle()V

    :cond_b
    return-void
.end method

.method public reset()V
    .locals 2

    .line 802
    iget-boolean v0, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->isVideo:Z

    if-eqz v0, :cond_0

    .line 803
    iget-object v0, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->filterPath:Ljava/lang/String;

    if-eqz v0, :cond_0

    .line 804
    new-instance v0, Ljava/io/File;

    iget-object v1, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->filterPath:Ljava/lang/String;

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->delete()Z

    const/4 v0, 0x0

    .line 805
    iput-object v0, p0, Lorg/telegram/messenger/MediaController$MediaEditState;->filterPath:Ljava/lang/String;

    :cond_0
    const/4 v0, 0x0

    .line 808
    iput-boolean v0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->hasSpoiler:Z

    const-wide/16 v0, 0x0

    .line 809
    iput-wide v0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->starsAmount:J

    .line 810
    invoke-super {p0}, Lorg/telegram/messenger/MediaController$MediaEditState;->reset()V

    return-void
.end method

.method public setOrientation(II)Lorg/telegram/messenger/MediaController$PhotoEntry;
    .locals 0

    .line 762
    iput p1, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->orientation:I

    .line 763
    iput p2, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->invert:I

    return-object p0
.end method

.method public setOrientation(Landroid/util/Pair;)Lorg/telegram/messenger/MediaController$PhotoEntry;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/util/Pair<",
            "Ljava/lang/Integer;",
            "Ljava/lang/Integer;",
            ">;)",
            "Lorg/telegram/messenger/MediaController$PhotoEntry;"
        }
    .end annotation

    .line 756
    iget-object v0, p1, Landroid/util/Pair;->first:Ljava/lang/Object;

    check-cast v0, Ljava/lang/Integer;

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    iput v0, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->orientation:I

    .line 757
    iget-object p1, p1, Landroid/util/Pair;->second:Ljava/lang/Object;

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    iput p1, p0, Lorg/telegram/messenger/MediaController$PhotoEntry;->invert:I

    return-object p0
.end method
