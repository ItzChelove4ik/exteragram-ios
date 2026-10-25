.class public Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;
.super Landroid/view/View;
.source "SourceFile"


# static fields
.field private static final DEFAULT_ICONS:[I


# instance fields
.field private animationProgress:F

.field private animator:Landroid/animation/ValueAnimator;

.field private attached:Z

.field private final availableIconIds:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private final changeRunnable:Ljava/lang/Runnable;

.field private final circCenterSize:I

.field private final circInnerIconSize:I

.field private final circInnerRadius:I

.field private final circOuterIconSize:I

.field private final circOuterRadius:I

.field private final cols:I

.field private count:I

.field private final currentIconIds:[I

.field private final currentIcons:[Landroid/graphics/drawable/Drawable;

.field private currentPack:Lcom/exteragram/messenger/icons/IconPack;

.field private final gap:I

.field private final iconSize:I

.field private isCircularMode:Z

.field private final nextIcons:[Landroid/graphics/drawable/Drawable;

.field private final random:Ljava/util/Random;

.field private refreshTimeMilliseconds:I

.field private final rows:I

.field private shouldAnimate:Z


# direct methods
.method public static synthetic $r8$lambda$5d9zIL87q6VlgvZ9VCnwqztMveU(Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;Lcom/exteragram/messenger/icons/IconPack;Z[Landroid/graphics/drawable/Drawable;Ljava/util/List;[I)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->lambda$loadIcons$0(Lcom/exteragram/messenger/icons/IconPack;Z[Landroid/graphics/drawable/Drawable;Ljava/util/List;[I)V

    return-void
.end method

.method public static synthetic $r8$lambda$K1rUdr1VhEe2Tscr8I1N6dNDQ9E(Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;Ljava/util/List;I[ILcom/exteragram/messenger/icons/IconPack;Z)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->lambda$loadIcons$1(Ljava/util/List;I[ILcom/exteragram/messenger/icons/IconPack;Z)V

    return-void
.end method

.method public static synthetic $r8$lambda$anQMzBtDkODpNgmohQ7LG6jrfWA(Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->animateToNext()V

    return-void
.end method

.method public static synthetic $r8$lambda$icQBdcR_Kirao_mcuOyzACmdSRs(Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;Landroid/animation/ValueAnimator;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->lambda$startTransition$2(Landroid/animation/ValueAnimator;)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$fgetanimator(Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;)Landroid/animation/ValueAnimator;
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->animator:Landroid/animation/ValueAnimator;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetattached(Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->attached:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetcurrentIconIds(Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;)[I
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->currentIconIds:[I

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetcurrentIcons(Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;)[Landroid/graphics/drawable/Drawable;
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->currentIcons:[Landroid/graphics/drawable/Drawable;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetnextIcons(Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;)[Landroid/graphics/drawable/Drawable;
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->nextIcons:[Landroid/graphics/drawable/Drawable;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fputanimationProgress(Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;F)V
    .locals 0

    .line 0
    iput p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->animationProgress:F

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputanimator(Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;Landroid/animation/ValueAnimator;)V
    .locals 0

    .line 0
    iput-object p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->animator:Landroid/animation/ValueAnimator;

    return-void
.end method

.method public static bridge synthetic -$$Nest$mscheduleNext(Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->scheduleNext()V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 4

    .line 56
    sget v0, Lorg/telegram/messenger/R$drawable;->msg_sticker:I

    sget v1, Lorg/telegram/messenger/R$drawable;->msg_link2:I

    sget v2, Lorg/telegram/messenger/R$drawable;->msg_pin:I

    sget v3, Lorg/telegram/messenger/R$drawable;->msg_photos:I

    filled-new-array {v0, v1, v2, v3}, [I

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->DEFAULT_ICONS:[I

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    .line 74
    invoke-direct {p0, p1}, Landroid/view/View;-><init>(Landroid/content/Context;)V

    .line 35
    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->availableIconIds:Ljava/util/List;

    const/16 p1, 0x14

    .line 36
    new-array v0, p1, [Landroid/graphics/drawable/Drawable;

    iput-object v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->currentIcons:[Landroid/graphics/drawable/Drawable;

    .line 37
    new-array v0, p1, [Landroid/graphics/drawable/Drawable;

    iput-object v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->nextIcons:[Landroid/graphics/drawable/Drawable;

    .line 38
    new-array p1, p1, [I

    iput-object p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->currentIconIds:[I

    .line 39
    new-instance p1, Ljava/util/Random;

    invoke-direct {p1}, Ljava/util/Random;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->random:Ljava/util/Random;

    const/4 p1, 0x2

    .line 42
    iput p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->rows:I

    .line 43
    iput p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->cols:I

    const/4 p1, 0x4

    .line 44
    iput p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->count:I

    const/high16 p1, 0x41a00000    # 20.0f

    .line 45
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    iput v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->iconSize:I

    const/high16 v0, 0x40800000    # 4.0f

    .line 46
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    iput v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->gap:I

    const/4 v0, 0x0

    .line 48
    iput-boolean v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->isCircularMode:Z

    const/high16 v0, 0x42380000    # 46.0f

    .line 50
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    iput v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->circInnerRadius:I

    const/high16 v0, 0x41c00000    # 24.0f

    .line 51
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    iput v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->circInnerIconSize:I

    const/high16 v0, 0x42900000    # 72.0f

    .line 52
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    iput v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->circOuterRadius:I

    .line 53
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    iput p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->circOuterIconSize:I

    const/high16 p1, 0x42100000    # 36.0f

    .line 54
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    iput p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->circCenterSize:I

    const/4 p1, 0x0

    .line 64
    iput p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->animationProgress:F

    const/16 p1, 0x1388

    .line 66
    iput p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->refreshTimeMilliseconds:I

    .line 71
    new-instance p1, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView$$ExternalSyntheticLambda1;

    invoke-direct {p1, p0}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;)V

    iput-object p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->changeRunnable:Ljava/lang/Runnable;

    return-void
.end method

.method private animateToNext()V
    .locals 1

    .line 303
    iget-boolean v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->attached:Z

    if-eqz v0, :cond_1

    iget-boolean v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->shouldAnimate:Z

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x1

    .line 304
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->loadIcons(Z)V

    :cond_1
    :goto_0
    return-void
.end method

.method private drawCircularLayout(Landroid/graphics/Canvas;)V
    .locals 17

    move-object/from16 v0, p0

    .line 437
    invoke-virtual {v0}, Landroid/view/View;->getWidth()I

    move-result v1

    .line 438
    invoke-virtual {v0}, Landroid/view/View;->getHeight()I

    move-result v2

    .line 439
    div-int/lit8 v7, v1, 0x2

    .line 440
    div-int/lit8 v8, v2, 0x2

    .line 442
    iget v5, v0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->circCenterSize:I

    div-int/lit8 v1, v5, 0x2

    sub-int v3, v7, v1

    div-int/lit8 v1, v5, 0x2

    sub-int v4, v8, v1

    const/high16 v6, 0x3f800000    # 1.0f

    const/4 v2, 0x0

    move-object/from16 v1, p1

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->drawIcon(Landroid/graphics/Canvas;IIIIF)V

    const/4 v9, 0x0

    move v1, v9

    :goto_0
    const/4 v10, 0x6

    const-wide/high16 v11, 0x404e000000000000L    # 60.0

    if-ge v1, v10, :cond_1

    const/4 v2, 0x1

    add-int/2addr v2, v1

    .line 451
    iget-object v3, v0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->currentIcons:[Landroid/graphics/drawable/Drawable;

    array-length v3, v3

    if-lt v2, v3, :cond_0

    goto :goto_1

    :cond_0
    int-to-double v3, v1

    mul-double/2addr v3, v11

    .line 453
    invoke-static {v3, v4}, Ljava/lang/Math;->toRadians(D)D

    move-result-wide v3

    int-to-double v5, v7

    .line 455
    iget v1, v0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->circInnerRadius:I

    int-to-double v10, v1

    invoke-static {v3, v4}, Ljava/lang/Math;->cos(D)D

    move-result-wide v12

    mul-double/2addr v10, v12

    add-double/2addr v5, v10

    double-to-int v1, v5

    iget v5, v0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->circInnerIconSize:I

    div-int/lit8 v5, v5, 0x2

    sub-int/2addr v1, v5

    int-to-double v5, v8

    .line 456
    iget v10, v0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->circInnerRadius:I

    int-to-double v10, v10

    invoke-static {v3, v4}, Ljava/lang/Math;->sin(D)D

    move-result-wide v3

    mul-double/2addr v10, v3

    add-double/2addr v5, v10

    double-to-int v3, v5

    iget v5, v0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->circInnerIconSize:I

    div-int/lit8 v4, v5, 0x2

    sub-int v4, v3, v4

    const v6, 0x3f333333    # 0.7f

    move v3, v1

    move-object/from16 v1, p1

    .line 458
    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->drawIcon(Landroid/graphics/Canvas;IIIIF)V

    move v1, v2

    goto :goto_0

    :cond_1
    :goto_1
    if-ge v9, v10, :cond_3

    const/4 v1, 0x7

    add-int v2, v1, v9

    .line 465
    iget-object v1, v0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->currentIcons:[Landroid/graphics/drawable/Drawable;

    array-length v1, v1

    if-lt v2, v1, :cond_2

    goto :goto_2

    :cond_2
    int-to-double v3, v9

    mul-double/2addr v3, v11

    const-wide/high16 v5, 0x403e000000000000L    # 30.0

    add-double/2addr v3, v5

    .line 467
    invoke-static {v3, v4}, Ljava/lang/Math;->toRadians(D)D

    move-result-wide v3

    int-to-double v5, v7

    .line 469
    iget v1, v0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->circOuterRadius:I

    int-to-double v13, v1

    invoke-static {v3, v4}, Ljava/lang/Math;->cos(D)D

    move-result-wide v15

    mul-double/2addr v13, v15

    add-double/2addr v5, v13

    double-to-int v1, v5

    iget v5, v0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->circOuterIconSize:I

    div-int/lit8 v5, v5, 0x2

    sub-int/2addr v1, v5

    int-to-double v5, v8

    .line 470
    iget v13, v0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->circOuterRadius:I

    int-to-double v13, v13

    invoke-static {v3, v4}, Ljava/lang/Math;->sin(D)D

    move-result-wide v3

    mul-double/2addr v13, v3

    add-double/2addr v5, v13

    double-to-int v3, v5

    iget v5, v0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->circOuterIconSize:I

    div-int/lit8 v4, v5, 0x2

    sub-int v4, v3, v4

    const v6, 0x3e99999a    # 0.3f

    move v3, v1

    move-object/from16 v1, p1

    .line 472
    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->drawIcon(Landroid/graphics/Canvas;IIIIF)V

    add-int/lit8 v9, v9, 0x1

    move-object/from16 v0, p0

    goto :goto_1

    :cond_3
    :goto_2
    return-void
.end method

.method private drawIcon(Landroid/graphics/Canvas;IIIIF)V
    .locals 3

    .line 477
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->currentIcons:[Landroid/graphics/drawable/Drawable;

    array-length v0, v0

    if-lt p2, v0, :cond_0

    return-void

    .line 479
    :cond_0
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    int-to-float p3, p3

    int-to-float p4, p4

    .line 480
    invoke-virtual {p1, p3, p4}, Landroid/graphics/Canvas;->translate(FF)V

    .line 482
    iget-object p3, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->currentIcons:[Landroid/graphics/drawable/Drawable;

    aget-object p3, p3, p2

    .line 483
    iget-object p4, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->nextIcons:[Landroid/graphics/drawable/Drawable;

    aget-object p2, p4, p2

    const/high16 p4, 0x437f0000    # 255.0f

    const/4 v0, 0x0

    if-eqz p3, :cond_1

    .line 486
    invoke-virtual {p3, v0, v0, p5, p5}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    const/high16 v1, 0x3f800000    # 1.0f

    .line 487
    iget v2, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->animationProgress:F

    sub-float/2addr v1, v2

    mul-float/2addr v1, p6

    mul-float/2addr v1, p4

    float-to-int v1, v1

    .line 488
    invoke-virtual {p3, v1}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 489
    invoke-virtual {p3, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    :cond_1
    if-eqz p2, :cond_2

    .line 493
    invoke-virtual {p2, v0, v0, p5, p5}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 494
    iget p0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->animationProgress:F

    mul-float/2addr p0, p6

    mul-float/2addr p0, p4

    float-to-int p0, p0

    .line 495
    invoke-virtual {p2, p0}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 496
    invoke-virtual {p2, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 499
    :cond_2
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    return-void
.end method

.method private getIconDrawableInternal(Lcom/exteragram/messenger/icons/IconPack;I)Landroid/graphics/drawable/Drawable;
    .locals 2

    const/4 v0, 0x0

    if-nez p2, :cond_0

    return-object v0

    .line 263
    :cond_0
    invoke-virtual {p1}, Lcom/exteragram/messenger/icons/IconPack;->isBase()Z

    move-result v1

    if-eqz v1, :cond_2

    .line 264
    invoke-virtual {p1}, Lcom/exteragram/messenger/icons/IconPack;->getPreinstalledMap()Landroid/util/SparseIntArray;

    move-result-object p1

    const/4 v1, -0x1

    if-eqz p1, :cond_1

    .line 265
    invoke-virtual {p1, p2, v1}, Landroid/util/SparseIntArray;->get(II)I

    move-result p1

    goto :goto_0

    :cond_1
    move p1, v1

    :goto_0
    if-eq p1, v1, :cond_3

    .line 267
    invoke-virtual {p0}, Landroid/view/View;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    goto :goto_1

    .line 270
    :cond_2
    sget-object v0, Lcom/exteragram/messenger/icons/IconManager;->INSTANCE:Lcom/exteragram/messenger/icons/IconManager;

    invoke-virtual {v0, p1, p2}, Lcom/exteragram/messenger/icons/IconManager;->getPackIconDrawable(Lcom/exteragram/messenger/icons/IconPack;I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    :cond_3
    :goto_1
    if-nez v0, :cond_5

    .line 274
    invoke-virtual {p0}, Landroid/view/View;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    instance-of p1, p1, Lcom/exteragram/messenger/icons/ExteraResources;

    if-eqz p1, :cond_4

    .line 276
    :try_start_0
    invoke-virtual {p0}, Landroid/view/View;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    check-cast p1, Lcom/exteragram/messenger/icons/ExteraResources;

    invoke-virtual {p1, p2}, Lcom/exteragram/messenger/icons/ExteraResources;->getOriginalDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_2

    .line 278
    :catch_0
    invoke-virtual {p0}, Landroid/view/View;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-virtual {p0, p2}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    goto :goto_2

    .line 281
    :cond_4
    invoke-virtual {p0}, Landroid/view/View;->getResources()Landroid/content/res/Resources;

    move-result-object p0

    invoke-virtual {p0, p2}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    :cond_5
    :goto_2
    if-eqz v0, :cond_6

    .line 286
    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 287
    new-instance p0, Landroid/graphics/PorterDuffColorFilter;

    sget p1, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteGrayIcon:I

    .line 288
    invoke-static {p1}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result p1

    sget-object p2, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {p0, p1, p2}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    .line 287
    invoke-virtual {v0, p0}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    :cond_6
    return-object v0
.end method

.method private synthetic lambda$loadIcons$0(Lcom/exteragram/messenger/icons/IconPack;Z[Landroid/graphics/drawable/Drawable;Ljava/util/List;[I)V
    .locals 1

    .line 202
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->currentPack:Lcom/exteragram/messenger/icons/IconPack;

    if-eq v0, p1, :cond_0

    return-void

    :cond_0
    const/4 p1, 0x0

    if-eqz p2, :cond_1

    .line 205
    iget-object p2, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->nextIcons:[Landroid/graphics/drawable/Drawable;

    array-length p5, p2

    invoke-static {p3, p1, p2, p1, p5}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    .line 206
    invoke-direct {p0, p4}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->startTransition(Ljava/util/List;)V

    return-void

    .line 208
    :cond_1
    :goto_0
    iget-object p2, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->currentIcons:[Landroid/graphics/drawable/Drawable;

    array-length p4, p2

    if-ge p1, p4, :cond_2

    .line 209
    iget-object p4, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->currentIconIds:[I

    aget v0, p5, p1

    aput v0, p4, p1

    .line 210
    aget-object p4, p3, p1

    aput-object p4, p2, p1

    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    .line 212
    :cond_2
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    .line 213
    iget-boolean p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->attached:Z

    if-eqz p1, :cond_3

    iget-boolean p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->shouldAnimate:Z

    if-eqz p1, :cond_3

    .line 214
    invoke-direct {p0}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->scheduleNext()V

    return-void

    .line 216
    :cond_3
    iget-object p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->changeRunnable:Ljava/lang/Runnable;

    invoke-virtual {p0, p1}, Landroid/view/View;->removeCallbacks(Ljava/lang/Runnable;)Z

    return-void
.end method

.method private synthetic lambda$loadIcons$1(Ljava/util/List;I[ILcom/exteragram/messenger/icons/IconPack;Z)V
    .locals 7

    .line 182
    invoke-direct {p0, p1, p2, p3}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->pickRandomIconIdsInternal(Ljava/util/List;I[I)Ljava/util/List;

    move-result-object v5

    .line 183
    iget-object p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->currentIconIds:[I

    array-length p2, p1

    new-array v4, p2, [Landroid/graphics/drawable/Drawable;

    .line 184
    array-length p1, p1

    new-array v6, p1, [I

    const/4 p1, 0x0

    move p2, p1

    .line 186
    :goto_0
    iget-object p3, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->currentIconIds:[I

    array-length p3, p3

    if-ge p2, p3, :cond_2

    .line 187
    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result p3

    if-ge p2, p3, :cond_0

    .line 188
    invoke-interface {v5, p2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Ljava/lang/Integer;

    invoke-virtual {p3}, Ljava/lang/Integer;->intValue()I

    move-result p3

    aput p3, v6, p2

    .line 189
    invoke-direct {p0, p4, p3}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->getIconDrawableInternal(Lcom/exteragram/messenger/icons/IconPack;I)Landroid/graphics/drawable/Drawable;

    move-result-object p3

    aput-object p3, v4, p2

    goto :goto_1

    .line 191
    :cond_0
    invoke-interface {v5}, Ljava/util/List;->isEmpty()Z

    move-result p3

    if-nez p3, :cond_1

    iget-boolean p3, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->isCircularMode:Z

    if-eqz p3, :cond_1

    .line 192
    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result p3

    rem-int p3, p2, p3

    invoke-interface {v5, p3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Ljava/lang/Integer;

    invoke-virtual {p3}, Ljava/lang/Integer;->intValue()I

    move-result p3

    aput p3, v6, p2

    .line 193
    invoke-direct {p0, p4, p3}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->getIconDrawableInternal(Lcom/exteragram/messenger/icons/IconPack;I)Landroid/graphics/drawable/Drawable;

    move-result-object p3

    aput-object p3, v4, p2

    goto :goto_1

    .line 195
    :cond_1
    aput p1, v6, p2

    const/4 p3, 0x0

    .line 196
    aput-object p3, v4, p2

    :goto_1
    add-int/lit8 p2, p2, 0x1

    goto :goto_0

    .line 201
    :cond_2
    new-instance v0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView$$ExternalSyntheticLambda2;

    move-object v1, p0

    move-object v2, p4

    move v3, p5

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;Lcom/exteragram/messenger/icons/IconPack;Z[Landroid/graphics/drawable/Drawable;Ljava/util/List;[I)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private synthetic lambda$startTransition$2(Landroid/animation/ValueAnimator;)V
    .locals 0

    .line 319
    invoke-virtual {p1}, Landroid/animation/ValueAnimator;->getAnimatedValue()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Float;

    invoke-virtual {p1}, Ljava/lang/Float;->floatValue()F

    move-result p1

    iput p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->animationProgress:F

    .line 320
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method

.method private loadIcons(Z)V
    .locals 8

    .line 171
    iget-object v5, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->currentPack:Lcom/exteragram/messenger/icons/IconPack;

    .line 172
    new-instance v2, Ljava/util/ArrayList;

    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->availableIconIds:Ljava/util/List;

    invoke-direct {v2, v0}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    if-eqz p1, :cond_0

    .line 173
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->currentIconIds:[I

    invoke-virtual {v0}, [I->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [I

    :goto_0
    move-object v4, v0

    goto :goto_1

    :cond_0
    const/4 v0, 0x0

    goto :goto_0

    .line 175
    :goto_1
    invoke-virtual {v5}, Lcom/exteragram/messenger/icons/IconPack;->isBase()Z

    move-result v0

    .line 178
    iget v1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->count:I

    if-nez v0, :cond_1

    .line 176
    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v0

    invoke-static {v1, v0}, Ljava/lang/Math;->min(II)I

    move-result v1

    :cond_1
    move v3, v1

    .line 181
    sget-object v7, Lorg/telegram/messenger/Utilities;->globalQueue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView$$ExternalSyntheticLambda0;

    move-object v1, p0

    move v6, p1

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;Ljava/util/List;I[ILcom/exteragram/messenger/icons/IconPack;Z)V

    invoke-virtual {v7, v0}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method private pickRandomIconIdsInternal(Ljava/util/List;I[I)Ljava/util/List;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Ljava/lang/Integer;",
            ">;I[I)",
            "Ljava/util/List<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation

    .line 224
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 225
    invoke-interface {p1}, Ljava/util/List;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    goto/16 :goto_4

    .line 227
    :cond_0
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1, p1}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    const/4 v2, 0x0

    if-eqz p3, :cond_1

    .line 229
    array-length v3, p3

    move v4, v2

    :goto_0
    if-ge v4, v3, :cond_1

    aget v5, p3, v4

    .line 230
    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-interface {v1, v5}, Ljava/util/List;->remove(Ljava/lang/Object;)Z

    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    .line 234
    :cond_1
    invoke-interface {v1}, Ljava/util/List;->isEmpty()Z

    move-result p3

    if-eqz p3, :cond_2

    .line 235
    invoke-interface {v1, p1}, Ljava/util/List;->addAll(Ljava/util/Collection;)Z

    .line 238
    :cond_2
    new-instance p1, Ljava/util/HashSet;

    invoke-direct {p1}, Ljava/util/HashSet;-><init>()V

    .line 239
    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result p3

    move v3, v2

    .line 242
    :goto_1
    invoke-interface {p1}, Ljava/util/Set;->size()I

    move-result v4

    if-ge v4, p2, :cond_3

    invoke-interface {p1}, Ljava/util/Set;->size()I

    move-result v4

    if-ge v4, p3, :cond_3

    mul-int/lit8 v4, p2, 0x4

    if-ge v3, v4, :cond_3

    .line 243
    iget-object v4, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->random:Ljava/util/Random;

    invoke-virtual {v4, p3}, Ljava/util/Random;->nextInt(I)I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-interface {p1, v4}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    add-int/lit8 v3, v3, 0x1

    goto :goto_1

    .line 247
    :cond_3
    invoke-interface {p1}, Ljava/util/Set;->size()I

    move-result p0

    if-ge p0, p2, :cond_4

    invoke-interface {p1}, Ljava/util/Set;->size()I

    move-result p0

    if-ge p0, p3, :cond_4

    :goto_2
    if-ge v2, p3, :cond_4

    .line 248
    invoke-interface {p1}, Ljava/util/Set;->size()I

    move-result p0

    if-ge p0, p2, :cond_4

    .line 249
    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    invoke-interface {p1, p0}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    add-int/lit8 v2, v2, 0x1

    goto :goto_2

    .line 253
    :cond_4
    invoke-interface {p1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_3
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_5

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    .line 254
    invoke-interface {v1, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    invoke-interface {v0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_3

    :cond_5
    :goto_4
    return-object v0
.end method

.method private static sameIconSource(Lcom/exteragram/messenger/icons/IconPack;Lcom/exteragram/messenger/icons/IconPack;)Z
    .locals 2

    .line 163
    invoke-virtual {p0}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1}, Lcom/exteragram/messenger/icons/IconPack;->getId()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    .line 166
    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/icons/IconPack;->getIcons()Ljava/util/Map;

    move-result-object p0

    invoke-virtual {p1}, Lcom/exteragram/messenger/icons/IconPack;->getIcons()Ljava/util/Map;

    move-result-object p1

    if-eq p0, p1, :cond_2

    .line 167
    invoke-interface {p0}, Ljava/util/Map;->isEmpty()Z

    move-result p0

    if-eqz p0, :cond_1

    invoke-interface {p1}, Ljava/util/Map;->isEmpty()Z

    move-result p0

    if-eqz p0, :cond_1

    goto :goto_0

    :cond_1
    return v1

    :cond_2
    :goto_0
    const/4 p0, 0x1

    return p0
.end method

.method private scheduleNext()V
    .locals 3

    .line 296
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->changeRunnable:Ljava/lang/Runnable;

    invoke-virtual {p0, v0}, Landroid/view/View;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 297
    iget-boolean v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->attached:Z

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->shouldAnimate:Z

    if-eqz v0, :cond_0

    .line 298
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->changeRunnable:Ljava/lang/Runnable;

    iget v1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->refreshTimeMilliseconds:I

    int-to-long v1, v1

    invoke-virtual {p0, v0, v1, v2}, Landroid/view/View;->postDelayed(Ljava/lang/Runnable;J)Z

    :cond_0
    return-void
.end method

.method private startTransition(Ljava/util/List;)V
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Ljava/lang/Integer;",
            ">;)V"
        }
    .end annotation

    .line 308
    iget-boolean v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->attached:Z

    if-nez v0, :cond_0

    return-void

    .line 311
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->animator:Landroid/animation/ValueAnimator;

    if-eqz v0, :cond_1

    .line 312
    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->cancel()V

    :cond_1
    const/4 v0, 0x2

    .line 315
    new-array v0, v0, [F

    fill-array-data v0, :array_0

    invoke-static {v0}, Landroid/animation/ValueAnimator;->ofFloat([F)Landroid/animation/ValueAnimator;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->animator:Landroid/animation/ValueAnimator;

    const-wide/16 v1, 0x12c

    .line 316
    invoke-virtual {v0, v1, v2}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    .line 317
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->animator:Landroid/animation/ValueAnimator;

    sget-object v1, Lorg/telegram/ui/Components/CubicBezierInterpolator;->DEFAULT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    invoke-virtual {v0, v1}, Landroid/animation/ValueAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 318
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->animator:Landroid/animation/ValueAnimator;

    new-instance v1, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView$$ExternalSyntheticLambda3;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;)V

    invoke-virtual {v0, v1}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 322
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->animator:Landroid/animation/ValueAnimator;

    new-instance v1, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView$1;

    invoke-direct {v1, p0, p1}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView$1;-><init>(Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;Ljava/util/List;)V

    invoke-virtual {v0, v1}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 352
    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->animator:Landroid/animation/ValueAnimator;

    invoke-virtual {p0}, Landroid/animation/ValueAnimator;->start()V

    return-void

    :array_0
    .array-data 4
        0x0
        0x3f800000    # 1.0f
    .end array-data
.end method


# virtual methods
.method public onAttachedToWindow()V
    .locals 1

    .line 357
    invoke-super {p0}, Landroid/view/View;->onAttachedToWindow()V

    const/4 v0, 0x1

    .line 358
    iput-boolean v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->attached:Z

    .line 359
    iget-boolean v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->shouldAnimate:Z

    if-eqz v0, :cond_0

    .line 360
    invoke-direct {p0}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->scheduleNext()V

    :cond_0
    return-void
.end method

.method public onDetachedFromWindow()V
    .locals 2

    const/4 v0, 0x0

    .line 366
    iput-boolean v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->attached:Z

    .line 367
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->changeRunnable:Ljava/lang/Runnable;

    invoke-virtual {p0, v0}, Landroid/view/View;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 368
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->animator:Landroid/animation/ValueAnimator;

    if-eqz v0, :cond_0

    const/4 v1, 0x0

    .line 370
    iput-object v1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->animator:Landroid/animation/ValueAnimator;

    .line 371
    invoke-virtual {v0}, Landroid/animation/ValueAnimator;->cancel()V

    .line 373
    :cond_0
    invoke-super {p0}, Landroid/view/View;->onDetachedFromWindow()V

    return-void
.end method

.method public onDraw(Landroid/graphics/Canvas;)V
    .locals 13

    .line 378
    iget-boolean v1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->isCircularMode:Z

    if-eqz v1, :cond_0

    .line 379
    invoke-direct/range {p0 .. p1}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->drawCircularLayout(Landroid/graphics/Canvas;)V

    return-void

    .line 383
    :cond_0
    iget v1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->count:I

    const/4 v2, 0x4

    const/4 v7, 0x0

    const/4 v8, 0x2

    if-ne v1, v2, :cond_6

    .line 385
    iget-object v1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->currentIcons:[Landroid/graphics/drawable/Drawable;

    array-length v2, v1

    move v3, v7

    :goto_0
    if-ge v7, v2, :cond_2

    aget-object v4, v1, v7

    if-eqz v4, :cond_1

    add-int/lit8 v3, v3, 0x1

    :cond_1
    add-int/lit8 v7, v7, 0x1

    goto :goto_0

    :cond_2
    const/4 v1, 0x1

    if-ne v3, v1, :cond_3

    const/high16 v1, 0x41c00000    # 24.0f

    .line 390
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    .line 391
    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v1

    sub-int/2addr v1, v5

    div-int/lit8 v3, v1, 0x2

    .line 392
    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result v1

    sub-int/2addr v1, v5

    div-int/lit8 v4, v1, 0x2

    const/4 v2, 0x0

    const/high16 v6, 0x3f800000    # 1.0f

    move-object v0, p0

    move-object v1, p1

    .line 393
    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->drawIcon(Landroid/graphics/Canvas;IIIIF)V

    return-void

    :cond_3
    const/high16 v0, 0x40800000    # 4.0f

    const/high16 v1, 0x41a00000    # 20.0f

    if-ne v3, v8, :cond_4

    .line 395
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    .line 396
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    .line 397
    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result v0

    sub-int/2addr v0, v5

    div-int/lit8 v4, v0, 0x2

    const/4 v3, 0x0

    const/high16 v6, 0x3f800000    # 1.0f

    const/4 v2, 0x0

    move-object v0, p0

    move-object v1, p1

    .line 398
    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->drawIcon(Landroid/graphics/Canvas;IIIIF)V

    add-int v3, v5, v7

    const/4 v2, 0x1

    .line 399
    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->drawIcon(Landroid/graphics/Canvas;IIIIF)V

    return-void

    :cond_4
    const/4 v2, 0x3

    if-ne v3, v2, :cond_5

    .line 401
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    .line 402
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v7

    const/4 v4, 0x0

    const/high16 v6, 0x3f800000    # 1.0f

    const/4 v2, 0x0

    const/4 v3, 0x0

    move-object v0, p0

    move-object v1, p1

    .line 403
    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->drawIcon(Landroid/graphics/Canvas;IIIIF)V

    add-int v3, v5, v7

    const/4 v2, 0x1

    .line 404
    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->drawIcon(Landroid/graphics/Canvas;IIIIF)V

    .line 405
    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v0

    sub-int/2addr v0, v5

    div-int/2addr v0, v8

    const/4 v2, 0x2

    move v4, v3

    move v3, v0

    move-object v0, p0

    .line 406
    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->drawIcon(Landroid/graphics/Canvas;IIIIF)V

    return-void

    .line 408
    :cond_5
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    .line 409
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    add-int v7, v5, v0

    const/4 v4, 0x0

    const/high16 v6, 0x3f800000    # 1.0f

    const/4 v2, 0x0

    const/4 v3, 0x0

    move-object v0, p0

    move-object v1, p1

    .line 412
    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->drawIcon(Landroid/graphics/Canvas;IIIIF)V

    const/4 v2, 0x1

    move v3, v7

    .line 413
    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->drawIcon(Landroid/graphics/Canvas;IIIIF)V

    const/4 v0, 0x0

    const/4 v2, 0x2

    move v4, v3

    move v3, v0

    move-object v0, p0

    .line 414
    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->drawIcon(Landroid/graphics/Canvas;IIIIF)V

    move v3, v4

    const/4 v2, 0x3

    .line 415
    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->drawIcon(Landroid/graphics/Canvas;IIIIF)V

    return-void

    .line 421
    :cond_6
    invoke-virtual {p0}, Landroid/view/View;->getPaddingLeft()I

    move-result v9

    .line 422
    invoke-virtual {p0}, Landroid/view/View;->getPaddingTop()I

    move-result v10

    move v1, v7

    move v11, v1

    :goto_1
    if-ge v11, v8, :cond_9

    move v2, v1

    move v12, v7

    :goto_2
    if-ge v12, v8, :cond_8

    .line 426
    iget-object v1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->currentIcons:[Landroid/graphics/drawable/Drawable;

    array-length v1, v1

    if-lt v2, v1, :cond_7

    goto :goto_3

    .line 428
    :cond_7
    iget v5, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->iconSize:I

    iget v1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->gap:I

    add-int v3, v5, v1

    mul-int/2addr v3, v12

    add-int/2addr v3, v9

    add-int/2addr v1, v5

    mul-int/2addr v1, v11

    add-int v4, v10, v1

    const/high16 v6, 0x3f800000    # 1.0f

    move-object v0, p0

    move-object v1, p1

    .line 430
    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->drawIcon(Landroid/graphics/Canvas;IIIIF)V

    add-int/lit8 v2, v2, 0x1

    add-int/lit8 v12, v12, 0x1

    goto :goto_2

    :cond_8
    :goto_3
    add-int/lit8 v11, v11, 0x1

    move v1, v2

    goto :goto_1

    :cond_9
    return-void
.end method

.method public onMeasure(II)V
    .locals 3

    .line 94
    iget-boolean v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->isCircularMode:Z

    if-eqz v0, :cond_0

    .line 95
    iget v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->circOuterRadius:I

    iget v1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->circOuterIconSize:I

    div-int/lit8 v1, v1, 0x2

    add-int/2addr v0, v1

    mul-int/lit8 v0, v0, 0x2

    const/high16 v1, 0x41000000    # 8.0f

    .line 96
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    add-int/2addr v0, v1

    .line 98
    invoke-virtual {p0}, Landroid/view/View;->getSuggestedMinimumWidth()I

    move-result v1

    invoke-static {v1, p1}, Landroid/view/View;->getDefaultSize(II)I

    move-result p1

    .line 99
    invoke-static {v0, p2}, Landroid/view/View;->resolveSize(II)I

    move-result p2

    .line 97
    invoke-virtual {p0, p1, p2}, Landroid/view/View;->setMeasuredDimension(II)V

    return-void

    .line 102
    :cond_0
    iget v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->iconSize:I

    mul-int/lit8 v0, v0, 0x2

    iget v1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->gap:I

    add-int/2addr v0, v1

    invoke-virtual {p0}, Landroid/view/View;->getPaddingLeft()I

    move-result v1

    add-int/2addr v0, v1

    invoke-virtual {p0}, Landroid/view/View;->getPaddingRight()I

    move-result v1

    add-int/2addr v0, v1

    .line 103
    iget v1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->iconSize:I

    mul-int/lit8 v1, v1, 0x2

    iget v2, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->gap:I

    add-int/2addr v1, v2

    invoke-virtual {p0}, Landroid/view/View;->getPaddingTop()I

    move-result v2

    add-int/2addr v1, v2

    invoke-virtual {p0}, Landroid/view/View;->getPaddingBottom()I

    move-result v2

    add-int/2addr v1, v2

    .line 104
    invoke-static {v0, p1}, Landroid/view/View;->resolveSize(II)I

    move-result p1

    invoke-static {v1, p2}, Landroid/view/View;->resolveSize(II)I

    move-result p2

    invoke-virtual {p0, p1, p2}, Landroid/view/View;->setMeasuredDimension(II)V

    return-void
.end method

.method public setCircularMode(Z)V
    .locals 0

    .line 78
    iput-boolean p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->isCircularMode:Z

    if-eqz p1, :cond_0

    const/16 p1, 0xd

    .line 80
    iput p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->count:I

    .line 82
    :cond_0
    invoke-virtual {p0}, Landroid/view/View;->requestLayout()V

    .line 83
    iget-object p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->currentPack:Lcom/exteragram/messenger/icons/IconPack;

    if-eqz p1, :cond_1

    .line 84
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->setIconPack(Lcom/exteragram/messenger/icons/IconPack;)V

    :cond_1
    return-void
.end method

.method public setIconPack(Lcom/exteragram/messenger/icons/IconPack;)V
    .locals 10

    .line 109
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->currentPack:Lcom/exteragram/messenger/icons/IconPack;

    if-eqz v0, :cond_0

    invoke-static {v0, p1}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->sameIconSource(Lcom/exteragram/messenger/icons/IconPack;Lcom/exteragram/messenger/icons/IconPack;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->availableIconIds:Ljava/util/List;

    .line 110
    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->availableIconIds:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    iget v1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->count:I

    if-lt v0, v1, :cond_0

    return-void

    .line 112
    :cond_0
    iput-object p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->currentPack:Lcom/exteragram/messenger/icons/IconPack;

    .line 113
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->availableIconIds:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->clear()V

    .line 115
    invoke-virtual {p1}, Lcom/exteragram/messenger/icons/IconPack;->isBase()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_2

    .line 116
    sget-object p1, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->DEFAULT_ICONS:[I

    array-length v0, p1

    move v2, v1

    :goto_0
    if-ge v2, v0, :cond_1

    aget v3, p1, v2

    .line 117
    iget-object v4, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->availableIconIds:Ljava/util/List;

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-interface {v4, v3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 119
    :cond_1
    iput-boolean v1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->shouldAnimate:Z

    goto/16 :goto_5

    .line 121
    :cond_2
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 122
    new-instance v2, Ljava/util/HashSet;

    invoke-direct {v2}, Ljava/util/HashSet;-><init>()V

    .line 124
    invoke-virtual {p1}, Lcom/exteragram/messenger/icons/IconPack;->getIcons()Ljava/util/Map;

    move-result-object p1

    invoke-interface {p1}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object p1

    invoke-interface {p1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_3
    :goto_1
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_4

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/util/Map$Entry;

    .line 125
    invoke-interface {v3}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    .line 126
    invoke-interface {v3}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    .line 127
    invoke-interface {v2, v3}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result v5

    if-nez v5, :cond_3

    .line 128
    invoke-interface {v2, v3}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    .line 129
    invoke-interface {v0, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_1

    .line 133
    :cond_4
    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result p1

    const/4 v3, 0x1

    if-eqz p1, :cond_5

    .line 134
    iget-object p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->availableIconIds:Ljava/util/List;

    sget v0, Lorg/telegram/messenger/R$drawable;->msg_media:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-interface {p1, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_3

    .line 136
    :cond_5
    sget-object p1, Lcom/exteragram/messenger/icons/IconManager;->INSTANCE:Lcom/exteragram/messenger/icons/IconManager;

    invoke-virtual {p1}, Lcom/exteragram/messenger/icons/IconManager;->getSystemIcons()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object p1

    .line 137
    invoke-interface {p1}, Ljava/util/Map;->isEmpty()Z

    move-result v4

    if-nez v4, :cond_7

    .line 138
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v4

    move v5, v1

    :cond_6
    :goto_2
    if-ge v5, v4, :cond_a

    invoke-virtual {v0, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    add-int/lit8 v5, v5, 0x1

    check-cast v6, Ljava/lang/String;

    .line 139
    invoke-interface {p1, v6}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/Integer;

    if-eqz v6, :cond_6

    .line 141
    iget-object v7, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->availableIconIds:Ljava/util/List;

    invoke-interface {v7, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_2

    .line 146
    :cond_7
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result p1

    move v4, v1

    move v5, v4

    :cond_8
    if-ge v5, p1, :cond_a

    invoke-virtual {v0, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v6

    add-int/lit8 v5, v5, 0x1

    check-cast v6, Ljava/lang/String;

    .line 147
    invoke-virtual {p0}, Landroid/view/View;->getResources()Landroid/content/res/Resources;

    move-result-object v7

    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object v8

    invoke-virtual {v8}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v8

    const-string v9, "drawable"

    invoke-virtual {v7, v6, v9, v8}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v6

    if-eqz v6, :cond_9

    .line 149
    iget-object v7, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->availableIconIds:Ljava/util/List;

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    invoke-interface {v7, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :cond_9
    add-int/2addr v4, v3

    const/16 v6, 0x1e

    if-le v4, v6, :cond_8

    .line 156
    :cond_a
    :goto_3
    invoke-interface {v2}, Ljava/util/Set;->size()I

    move-result p1

    iget v0, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->count:I

    mul-int/lit8 v0, v0, 0x2

    if-lt p1, v0, :cond_b

    goto :goto_4

    :cond_b
    move v3, v1

    :goto_4
    iput-boolean v3, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->shouldAnimate:Z

    .line 159
    :goto_5
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->loadIcons(Z)V

    return-void
.end method

.method public setRefreshTime(I)V
    .locals 0

    .line 89
    iput p1, p0, Lcom/exteragram/messenger/icons/ui/components/IconPackPreviewView;->refreshTimeMilliseconds:I

    return-void
.end method
