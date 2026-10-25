.class public Lorg/telegram/ui/Components/MediaActionDrawable;
.super Landroid/graphics/drawable/Drawable;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/ui/Components/MediaActionDrawable$MediaActionDrawableDelegate;
    }
.end annotation


# instance fields
.field private animatedDownloadProgress:F

.field private animatingTransition:Z

.field private backPaint:Landroid/graphics/Paint;

.field private colorFilter:Landroid/graphics/ColorFilter;

.field private currentIcon:I

.field private final customIconDrawables:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray<",
            "[",
            "Landroid/graphics/drawable/Drawable;",
            ">;"
        }
    .end annotation
.end field

.field private delegate:Lorg/telegram/ui/Components/MediaActionDrawable$MediaActionDrawableDelegate;

.field private downloadIconScale:F

.field private downloadProgress:F

.field private downloadProgressAnimationStart:F

.field private downloadProgressTime:F

.field private downloadRadOffset:F

.field public drawProgressCircle:Z

.field private drawablePaint:Landroid/graphics/Paint;

.field private gradientDrawable:Landroid/graphics/LinearGradient;

.field private gradientMatrix:Landroid/graphics/Matrix;

.field private hasOverlayImage:Z

.field private interpolator:Landroid/view/animation/DecelerateInterpolator;

.field private isMini:Z

.field private lastAnimationTime:J

.field private lastPercent:I

.field private messageDrawable:Lorg/telegram/ui/ActionBar/MessageDrawable;

.field private nextIcon:I

.field private overrideAlpha:F

.field public paint:Landroid/graphics/Paint;

.field public paint2:Landroid/graphics/Paint;

.field private paint3:Landroid/graphics/Paint;

.field private percentString:Ljava/lang/String;

.field private percentStringWidth:I

.field private rect:Landroid/graphics/RectF;

.field private savedTransitionProgress:F

.field private scale:F

.field private textPaint:Landroid/text/TextPaint;

.field private transitionAnimationTime:F

.field private transitionProgress:F

.field private final whiteColorFilter:Landroid/graphics/ColorFilter;


# direct methods
.method public constructor <init>()V
    .locals 4

    .line 122
    invoke-direct {p0}, Landroid/graphics/drawable/Drawable;-><init>()V

    .line 53
    new-instance v0, Landroid/text/TextPaint;

    const/4 v1, 0x1

    invoke-direct {v0, v1}, Landroid/text/TextPaint;-><init>(I)V

    iput-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->textPaint:Landroid/text/TextPaint;

    .line 54
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0, v1}, Landroid/graphics/Paint;-><init>(I)V

    iput-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    .line 55
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0, v1}, Landroid/graphics/Paint;-><init>(I)V

    iput-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->backPaint:Landroid/graphics/Paint;

    .line 56
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0, v1}, Landroid/graphics/Paint;-><init>(I)V

    iput-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint2:Landroid/graphics/Paint;

    .line 57
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0, v1}, Landroid/graphics/Paint;-><init>(I)V

    iput-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint3:Landroid/graphics/Paint;

    .line 58
    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0, v1}, Landroid/graphics/Paint;-><init>(I)V

    iput-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->drawablePaint:Landroid/graphics/Paint;

    .line 59
    new-instance v0, Landroid/graphics/RectF;

    invoke-direct {v0}, Landroid/graphics/RectF;-><init>()V

    iput-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->rect:Landroid/graphics/RectF;

    .line 61
    new-instance v0, Landroid/graphics/PorterDuffColorFilter;

    sget-object v2, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    const/4 v3, -0x1

    invoke-direct {v0, v3, v2}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    iput-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->whiteColorFilter:Landroid/graphics/ColorFilter;

    .line 62
    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    iput-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->customIconDrawables:Landroid/util/SparseArray;

    const/high16 v0, 0x3f800000    # 1.0f

    .line 63
    iput v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    .line 64
    new-instance v2, Landroid/view/animation/DecelerateInterpolator;

    invoke-direct {v2}, Landroid/view/animation/DecelerateInterpolator;-><init>()V

    iput-object v2, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->interpolator:Landroid/view/animation/DecelerateInterpolator;

    const/high16 v2, 0x43c80000    # 400.0f

    .line 68
    iput v2, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionAnimationTime:F

    .line 70
    iput v3, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->lastPercent:I

    .line 74
    iput v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->overrideAlpha:F

    .line 78
    iput v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    .line 105
    iput-boolean v1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->drawProgressCircle:Z

    .line 106
    iput v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadIconScale:F

    .line 123
    iget-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    invoke-virtual {v0, v3}, Landroid/graphics/Paint;->setColor(I)V

    .line 124
    iget-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    sget-object v1, Landroid/graphics/Paint$Cap;->ROUND:Landroid/graphics/Paint$Cap;

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setStrokeCap(Landroid/graphics/Paint$Cap;)V

    .line 125
    iget-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    const/high16 v1, 0x40400000    # 3.0f

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    int-to-float v1, v1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    .line 126
    iget-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    sget-object v1, Landroid/graphics/Paint$Style;->STROKE:Landroid/graphics/Paint$Style;

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    .line 128
    iget-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint3:Landroid/graphics/Paint;

    invoke-virtual {v0, v3}, Landroid/graphics/Paint;->setColor(I)V

    .line 130
    iget-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->textPaint:Landroid/text/TextPaint;

    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->bold()Landroid/graphics/Typeface;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    .line 131
    iget-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->textPaint:Landroid/text/TextPaint;

    const/high16 v1, 0x41500000    # 13.0f

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    int-to-float v1, v1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setTextSize(F)V

    .line 132
    iget-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->textPaint:Landroid/text/TextPaint;

    invoke-virtual {v0, v3}, Landroid/graphics/Paint;->setColor(I)V

    .line 134
    iget-object p0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint2:Landroid/graphics/Paint;

    invoke-virtual {p0, v3}, Landroid/graphics/Paint;->setColor(I)V

    return-void
.end method

.method private drawTintedDrawable(Landroid/graphics/Canvas;Landroid/graphics/drawable/Drawable;IIFI)V
    .locals 9

    .line 182
    invoke-virtual {p2}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v0

    int-to-float v0, v0

    mul-float/2addr v0, p5

    float-to-int v0, v0

    .line 183
    invoke-virtual {p2}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v1

    int-to-float v1, v1

    mul-float/2addr v1, p5

    float-to-int p5, v1

    .line 184
    div-int/lit8 v0, v0, 0x2

    sub-int v1, p3, v0

    .line 185
    div-int/lit8 p5, p5, 0x2

    sub-int v2, p4, p5

    add-int/2addr p3, v0

    add-int/2addr p4, p5

    const/4 p5, 0x1

    .line 189
    invoke-virtual {p0, p5}, Lorg/telegram/ui/Components/MediaActionDrawable;->applyShaderMatrix(Z)V

    int-to-float v4, v1

    int-to-float v5, v2

    int-to-float v6, p3

    int-to-float v7, p4

    const/4 v8, 0x0

    move-object v3, p1

    .line 191
    invoke-virtual/range {v3 .. v8}, Landroid/graphics/Canvas;->saveLayer(FFFFLandroid/graphics/Paint;)I

    move-result p1

    .line 192
    iget-object p5, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->whiteColorFilter:Landroid/graphics/ColorFilter;

    invoke-virtual {p2, p5}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    const/16 p5, 0xff

    .line 193
    invoke-virtual {p2, p5}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 194
    invoke-virtual {p2, v1, v2, p3, p4}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 195
    invoke-virtual {p2, v3}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    .line 197
    iget-object p2, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->drawablePaint:Landroid/graphics/Paint;

    iget-object p3, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint2:Landroid/graphics/Paint;

    invoke-virtual {p2, p3}, Landroid/graphics/Paint;->set(Landroid/graphics/Paint;)V

    .line 198
    iget-object p2, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->drawablePaint:Landroid/graphics/Paint;

    invoke-virtual {p2, p6}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 199
    iget-object p2, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->drawablePaint:Landroid/graphics/Paint;

    new-instance p3, Landroid/graphics/PorterDuffXfermode;

    sget-object p4, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {p3, p4}, Landroid/graphics/PorterDuffXfermode;-><init>(Landroid/graphics/PorterDuff$Mode;)V

    invoke-virtual {p2, p3}, Landroid/graphics/Paint;->setXfermode(Landroid/graphics/Xfermode;)Landroid/graphics/Xfermode;

    .line 200
    iget-object v8, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->drawablePaint:Landroid/graphics/Paint;

    invoke-virtual/range {v3 .. v8}, Landroid/graphics/Canvas;->drawRect(FFFFLandroid/graphics/Paint;)V

    .line 201
    iget-object p0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->drawablePaint:Landroid/graphics/Paint;

    const/4 p2, 0x0

    invoke-virtual {p0, p2}, Landroid/graphics/Paint;->setXfermode(Landroid/graphics/Xfermode;)Landroid/graphics/Xfermode;

    .line 202
    invoke-virtual {v3, p1}, Landroid/graphics/Canvas;->restoreToCount(I)V

    return-void
.end method

.method public static getCircleValue(F)F
    .locals 2

    :goto_0
    const/high16 v0, 0x43b40000    # 360.0f

    cmpl-float v1, p0, v0

    if-lez v1, :cond_0

    sub-float/2addr p0, v0

    goto :goto_0

    :cond_0
    return p0
.end method

.method private getCustomIconDrawable(I)Landroid/graphics/drawable/Drawable;
    .locals 3

    const/16 v0, 0x10

    if-ne p1, v0, :cond_0

    .line 1061
    sget-object v0, Lorg/telegram/ui/ActionBar/Theme;->chat_pluginIcon:Landroid/graphics/drawable/Drawable;

    goto :goto_0

    :cond_0
    const/16 v0, 0x11

    if-ne p1, v0, :cond_1

    .line 1062
    sget-object v0, Lorg/telegram/ui/ActionBar/Theme;->chat_settingsIcon:Landroid/graphics/drawable/Drawable;

    goto :goto_0

    :cond_1
    const/16 v0, 0x12

    if-ne p1, v0, :cond_2

    .line 1063
    sget-object v0, Lorg/telegram/ui/ActionBar/Theme;->chat_stickersIcon:Landroid/graphics/drawable/Drawable;

    goto :goto_0

    .line 1064
    :cond_2
    invoke-static {p1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginFileIconDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    :goto_0
    if-nez v0, :cond_3

    const/4 p0, 0x0

    return-object p0

    .line 1069
    :cond_3
    iget-object v1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->customIconDrawables:Landroid/util/SparseArray;

    invoke-virtual {v1, p1}, Landroid/util/SparseArray;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, [Landroid/graphics/drawable/Drawable;

    if-eqz v1, :cond_4

    const/4 v2, 0x0

    .line 1070
    aget-object v2, v1, v2

    if-eq v2, v0, :cond_6

    .line 1071
    :cond_4
    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->getConstantState()Landroid/graphics/drawable/Drawable$ConstantState;

    move-result-object v1

    if-eqz v1, :cond_5

    .line 1072
    invoke-virtual {v1}, Landroid/graphics/drawable/Drawable$ConstantState;->newDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v1

    invoke-virtual {v1}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object v1

    goto :goto_1

    :cond_5
    move-object v1, v0

    :goto_1
    filled-new-array {v0, v1}, [Landroid/graphics/drawable/Drawable;

    move-result-object v1

    .line 1073
    iget-object p0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->customIconDrawables:Landroid/util/SparseArray;

    invoke-virtual {p0, p1, v1}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    :cond_6
    const/4 p0, 0x1

    .line 1075
    aget-object p0, v1, p0

    return-object p0
.end method

.method public static isCustomFileIcon(I)Z
    .locals 1

    const/16 v0, 0x10

    if-eq p0, v0, :cond_1

    const/16 v0, 0x11

    if-eq p0, v0, :cond_1

    const/16 v0, 0x12

    if-eq p0, v0, :cond_1

    .line 50
    invoke-static {p0}, Lcom/exteragram/messenger/plugins/PluginsController;->isPluginFileIcon(I)Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method


# virtual methods
.method public applyShaderMatrix(Z)V
    .locals 4

    .line 331
    iget-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->messageDrawable:Lorg/telegram/ui/ActionBar/MessageDrawable;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/MessageDrawable;->hasGradient()Z

    move-result v0

    if-eqz v0, :cond_1

    iget-boolean v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->hasOverlayImage:Z

    if-nez v0, :cond_1

    .line 332
    invoke-virtual {p0}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v0

    .line 333
    iget-object v1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->messageDrawable:Lorg/telegram/ui/ActionBar/MessageDrawable;

    invoke-virtual {v1}, Lorg/telegram/ui/ActionBar/MessageDrawable;->getGradientShader()Landroid/graphics/Shader;

    move-result-object v1

    .line 334
    iget-object v2, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->messageDrawable:Lorg/telegram/ui/ActionBar/MessageDrawable;

    invoke-virtual {v2}, Lorg/telegram/ui/ActionBar/MessageDrawable;->getMatrix()Landroid/graphics/Matrix;

    move-result-object v2

    .line 335
    invoke-virtual {v2}, Landroid/graphics/Matrix;->reset()V

    .line 336
    iget-object v3, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->messageDrawable:Lorg/telegram/ui/ActionBar/MessageDrawable;

    invoke-virtual {v3}, Lorg/telegram/ui/ActionBar/MessageDrawable;->applyMatrixScale()V

    if-eqz p1, :cond_0

    .line 338
    invoke-virtual {v0}, Landroid/graphics/Rect;->centerX()I

    move-result p1

    neg-int p1, p1

    int-to-float p1, p1

    iget-object p0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->messageDrawable:Lorg/telegram/ui/ActionBar/MessageDrawable;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/MessageDrawable;->getTopY()I

    move-result p0

    neg-int p0, p0

    iget v0, v0, Landroid/graphics/Rect;->top:I

    add-int/2addr p0, v0

    int-to-float p0, p0

    invoke-virtual {v2, p1, p0}, Landroid/graphics/Matrix;->postTranslate(FF)Z

    goto :goto_0

    .line 340
    :cond_0
    iget-object p0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->messageDrawable:Lorg/telegram/ui/ActionBar/MessageDrawable;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/MessageDrawable;->getTopY()I

    move-result p0

    neg-int p0, p0

    int-to-float p0, p0

    const/4 p1, 0x0

    invoke-virtual {v2, p1, p0}, Landroid/graphics/Matrix;->postTranslate(FF)Z

    .line 342
    :goto_0
    invoke-virtual {v1, v2}, Landroid/graphics/Shader;->setLocalMatrix(Landroid/graphics/Matrix;)V

    :cond_1
    return-void
.end method

.method public draw(Landroid/graphics/Canvas;)V
    .locals 37

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    .line 348
    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v7

    .line 350
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->messageDrawable:Lorg/telegram/ui/ActionBar/MessageDrawable;

    const/4 v8, 0x0

    const/4 v9, 0x0

    if-eqz v2, :cond_0

    invoke-virtual {v2}, Lorg/telegram/ui/ActionBar/MessageDrawable;->hasGradient()Z

    move-result v2

    if-eqz v2, :cond_0

    iget-boolean v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->hasOverlayImage:Z

    if-nez v2, :cond_0

    .line 351
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->messageDrawable:Lorg/telegram/ui/ActionBar/MessageDrawable;

    invoke-virtual {v2}, Lorg/telegram/ui/ActionBar/MessageDrawable;->getGradientShader()Landroid/graphics/Shader;

    move-result-object v2

    .line 352
    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    invoke-virtual {v3, v2}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    .line 353
    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint2:Landroid/graphics/Paint;

    invoke-virtual {v3, v2}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    .line 354
    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint3:Landroid/graphics/Paint;

    invoke-virtual {v3, v2}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    goto :goto_0

    .line 355
    :cond_0
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->gradientDrawable:Landroid/graphics/LinearGradient;

    if-eqz v2, :cond_1

    iget-boolean v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->hasOverlayImage:Z

    if-nez v2, :cond_1

    .line 356
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->gradientMatrix:Landroid/graphics/Matrix;

    invoke-virtual {v2}, Landroid/graphics/Matrix;->reset()V

    .line 357
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->gradientMatrix:Landroid/graphics/Matrix;

    iget v3, v7, Landroid/graphics/Rect;->top:I

    int-to-float v3, v3

    invoke-virtual {v2, v9, v3}, Landroid/graphics/Matrix;->setTranslate(FF)V

    .line 358
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->gradientDrawable:Landroid/graphics/LinearGradient;

    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->gradientMatrix:Landroid/graphics/Matrix;

    invoke-virtual {v2, v3}, Landroid/graphics/Shader;->setLocalMatrix(Landroid/graphics/Matrix;)V

    .line 359
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->gradientDrawable:Landroid/graphics/LinearGradient;

    invoke-virtual {v2, v3}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    .line 360
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint2:Landroid/graphics/Paint;

    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->gradientDrawable:Landroid/graphics/LinearGradient;

    invoke-virtual {v2, v3}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    .line 361
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint3:Landroid/graphics/Paint;

    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->gradientDrawable:Landroid/graphics/LinearGradient;

    invoke-virtual {v2, v3}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    goto :goto_0

    .line 363
    :cond_1
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    invoke-virtual {v2, v8}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    .line 364
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint2:Landroid/graphics/Paint;

    invoke-virtual {v2, v8}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    .line 365
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint3:Landroid/graphics/Paint;

    invoke-virtual {v2, v8}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    .line 368
    :goto_0
    invoke-virtual {v7}, Landroid/graphics/Rect;->centerX()I

    move-result v10

    .line 369
    invoke-virtual {v7}, Landroid/graphics/Rect;->centerY()I

    move-result v11

    .line 373
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    const/16 v12, 0xa

    const/4 v13, 0x6

    const/4 v14, 0x4

    const/4 v15, 0x3

    const/4 v3, 0x0

    const/16 v4, 0xe

    const/high16 v5, 0x3f800000    # 1.0f

    if-ne v2, v14, :cond_3

    .line 374
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    if-eq v2, v15, :cond_2

    if-eq v2, v4, :cond_2

    .line 375
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    move-result v2

    .line 376
    iget v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    sub-float v6, v5, v6

    int-to-float v8, v10

    move/from16 v17, v9

    int-to-float v9, v11

    .line 377
    invoke-virtual {v1, v6, v6, v8, v9}, Landroid/graphics/Canvas;->scale(FFFF)V

    :goto_1
    move v8, v2

    goto :goto_3

    :cond_2
    move/from16 v17, v9

    goto :goto_2

    :cond_3
    move/from16 v17, v9

    if-eq v2, v13, :cond_4

    if-ne v2, v12, :cond_5

    .line 379
    :cond_4
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    if-ne v2, v14, :cond_5

    .line 380
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    move-result v2

    .line 381
    iget v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    int-to-float v8, v10

    int-to-float v9, v11

    invoke-virtual {v1, v6, v6, v8, v9}, Landroid/graphics/Canvas;->scale(FFFF)V

    goto :goto_1

    :cond_5
    :goto_2
    move v8, v3

    :goto_3
    const/high16 v9, 0x40400000    # 3.0f

    .line 384
    invoke-static {v9}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    .line 385
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    const v18, 0x3e19999a    # 0.15f

    const/high16 v19, 0x42b40000    # 90.0f

    const/high16 v20, 0x41400000    # 12.0f

    const/high16 v21, 0x42c80000    # 100.0f

    const/high16 v22, 0x40600000    # 3.5f

    const/high16 v23, 0x40e00000    # 7.0f

    const/high16 v24, 0x40000000    # 2.0f

    move/from16 v25, v9

    const/high16 v26, 0x3f000000    # 0.5f

    const/4 v13, 0x2

    const/high16 v27, 0x437f0000    # 255.0f

    if-eq v2, v13, :cond_6

    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v2, v13, :cond_24

    .line 386
    :cond_6
    invoke-virtual {v0, v3}, Lorg/telegram/ui/Components/MediaActionDrawable;->applyShaderMatrix(Z)V

    .line 388
    iget-boolean v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->drawProgressCircle:Z

    const/high16 v28, 0x41100000    # 9.0f

    const/high16 v29, 0x41000000    # 8.0f

    if-eqz v2, :cond_19

    int-to-float v2, v11

    .line 389
    invoke-static/range {v28 .. v28}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    int-to-float v3, v3

    iget v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    mul-float/2addr v3, v6

    sub-float v3, v2, v3

    .line 390
    invoke-static/range {v28 .. v28}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    int-to-float v6, v6

    iget v12, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    mul-float/2addr v6, v12

    add-float/2addr v6, v2

    .line 392
    invoke-static/range {v20 .. v20}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v12

    int-to-float v12, v12

    iget v14, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    mul-float/2addr v12, v14

    add-float/2addr v12, v2

    .line 395
    iget v14, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    if-eq v14, v15, :cond_7

    if-ne v14, v4, :cond_8

    :cond_7
    iget v14, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v14, v13, :cond_8

    .line 396
    iget-object v14, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    iget v9, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    div-float v9, v9, v26

    invoke-static {v5, v9}, Ljava/lang/Math;->min(FF)F

    move-result v9

    mul-float v9, v9, v27

    float-to-int v9, v9

    invoke-virtual {v14, v9}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 397
    iget v9, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    .line 398
    invoke-static/range {v20 .. v20}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v14

    int-to-float v14, v14

    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    mul-float/2addr v14, v5

    add-float/2addr v14, v2

    const/high16 v33, 0x3f800000    # 1.0f

    goto :goto_6

    .line 400
    :cond_8
    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-eq v5, v15, :cond_9

    if-eq v5, v4, :cond_9

    if-eq v5, v13, :cond_9

    .line 401
    iget-object v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    iget v9, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->savedTransitionProgress:F

    div-float v9, v9, v26

    const/high16 v14, 0x3f800000    # 1.0f

    invoke-static {v14, v9}, Ljava/lang/Math;->min(FF)F

    move-result v9

    mul-float v9, v9, v27

    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    sub-float v4, v14, v4

    mul-float/2addr v9, v4

    float-to-int v4, v9

    invoke-virtual {v5, v4}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 402
    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->savedTransitionProgress:F

    :goto_4
    move v9, v4

    const/high16 v33, 0x3f800000    # 1.0f

    goto :goto_5

    .line 404
    :cond_9
    iget-object v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    const/16 v5, 0xff

    invoke-virtual {v4, v5}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 405
    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    goto :goto_4

    .line 407
    :goto_5
    invoke-static/range {v33 .. v33}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    mul-float/2addr v4, v5

    add-float v14, v2, v4

    .line 411
    :goto_6
    iget-boolean v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->animatingTransition:Z

    if-eqz v4, :cond_16

    .line 413
    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-eq v4, v13, :cond_a

    cmpg-float v5, v9, v26

    if-gtz v5, :cond_b

    :cond_a
    move v1, v13

    goto/16 :goto_d

    :cond_b
    const/high16 v3, 0x41500000    # 13.0f

    .line 432
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    int-to-float v3, v3

    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    mul-float/2addr v3, v4

    mul-float/2addr v3, v4

    iget-boolean v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->isMini:Z

    if-eqz v4, :cond_c

    invoke-static/range {v24 .. v24}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    goto :goto_7

    :cond_c
    const/4 v4, 0x0

    :goto_7
    int-to-float v4, v4

    add-float/2addr v3, v4

    sub-float v9, v9, v26

    div-float v4, v9, v26

    const v5, 0x3e4ccccd    # 0.2f

    cmpl-float v6, v9, v5

    if-lez v6, :cond_d

    sub-float/2addr v9, v5

    const v5, 0x3e99999a    # 0.3f

    div-float v5, v9, v5

    move/from16 v28, v5

    move/from16 v9, v33

    goto :goto_8

    :cond_d
    div-float v5, v9, v5

    move v9, v5

    move/from16 v28, v17

    .line 444
    :goto_8
    iget-object v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->rect:Landroid/graphics/RectF;

    int-to-float v6, v10

    sub-float v13, v6, v3

    div-float v3, v3, v24

    sub-float v15, v12, v3

    add-float/2addr v3, v12

    invoke-virtual {v5, v13, v15, v6, v3}, Landroid/graphics/RectF;->set(FFFF)V

    mul-float v3, v28, v21

    move v5, v2

    .line 446
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->rect:Landroid/graphics/RectF;

    const/high16 v13, 0x42d00000    # 104.0f

    mul-float/2addr v4, v13

    sub-float/2addr v4, v3

    move v13, v5

    const/4 v5, 0x0

    move v15, v6

    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move/from16 v35, v13

    move v13, v15

    const/16 v15, 0xe

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawArc(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V

    sub-float v2, v12, v14

    mul-float/2addr v2, v9

    add-float/2addr v14, v2

    cmpl-float v2, v28, v17

    if-lez v2, :cond_14

    .line 454
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v2, v15, :cond_e

    move/from16 v2, v17

    goto :goto_9

    :cond_e
    const/high16 v2, -0x3dcc0000    # -45.0f

    sub-float v5, v33, v28

    mul-float/2addr v2, v5

    .line 459
    :goto_9
    invoke-static/range {v23 .. v23}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    int-to-float v3, v3

    mul-float v3, v3, v28

    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    mul-float/2addr v3, v4

    mul-float v4, v28, v27

    float-to-int v4, v4

    .line 461
    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    const/4 v6, 0x3

    if-eq v5, v6, :cond_f

    if-eq v5, v15, :cond_f

    const/4 v6, 0x2

    if-eq v5, v6, :cond_f

    .line 462
    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    div-float v5, v5, v26

    move/from16 v6, v33

    invoke-static {v6, v5}, Ljava/lang/Math;->min(FF)F

    move-result v5

    sub-float v5, v6, v5

    int-to-float v4, v4

    mul-float/2addr v4, v5

    float-to-int v4, v4

    :cond_f
    move v9, v4

    cmpl-float v28, v2, v17

    if-eqz v28, :cond_10

    .line 468
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    move/from16 v5, v35

    .line 469
    invoke-virtual {v1, v2, v13, v5}, Landroid/graphics/Canvas;->rotate(FFF)V

    goto :goto_a

    :cond_10
    move/from16 v5, v35

    :goto_a
    if-eqz v9, :cond_13

    .line 472
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    invoke-virtual {v2, v9}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 473
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v2, v15, :cond_12

    .line 474
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint3:Landroid/graphics/Paint;

    invoke-virtual {v2, v9}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 475
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->rect:Landroid/graphics/RectF;

    invoke-static/range {v22 .. v22}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    sub-int v3, v10, v3

    int-to-float v3, v3

    invoke-static/range {v22 .. v22}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    sub-int v4, v11, v4

    int-to-float v4, v4

    invoke-static/range {v22 .. v22}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    add-int/2addr v5, v10

    int-to-float v5, v5

    invoke-static/range {v22 .. v22}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    add-int/2addr v6, v11

    int-to-float v6, v6

    invoke-virtual {v2, v3, v4, v5, v6}, Landroid/graphics/RectF;->set(FFFF)V

    .line 476
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->rect:Landroid/graphics/RectF;

    invoke-static/range {v24 .. v24}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    int-to-float v3, v3

    invoke-static/range {v24 .. v24}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    iget-object v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint3:Landroid/graphics/Paint;

    invoke-virtual {v1, v2, v3, v4, v5}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V

    .line 478
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    int-to-float v3, v9

    mul-float v3, v3, v18

    float-to-int v3, v3

    invoke-virtual {v2, v3}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 479
    iget-boolean v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->isMini:Z

    if-eqz v2, :cond_11

    move/from16 v6, v24

    goto :goto_b

    :cond_11
    const/high16 v6, 0x40800000    # 4.0f

    :goto_b
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 480
    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->rect:Landroid/graphics/RectF;

    iget v4, v7, Landroid/graphics/Rect;->left:I

    add-int/2addr v4, v2

    int-to-float v4, v4

    iget v5, v7, Landroid/graphics/Rect;->top:I

    add-int/2addr v5, v2

    int-to-float v5, v5

    iget v6, v7, Landroid/graphics/Rect;->right:I

    sub-int/2addr v6, v2

    int-to-float v6, v6

    iget v15, v7, Landroid/graphics/Rect;->bottom:I

    sub-int/2addr v15, v2

    int-to-float v2, v15

    invoke-virtual {v3, v4, v5, v6, v2}, Landroid/graphics/RectF;->set(FFFF)V

    .line 481
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->rect:Landroid/graphics/RectF;

    const/4 v5, 0x0

    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    const/4 v3, 0x0

    const/high16 v4, 0x43b40000    # 360.0f

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawArc(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V

    .line 482
    iget-object v1, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    invoke-virtual {v1, v9}, Landroid/graphics/Paint;->setAlpha(I)V

    goto :goto_c

    :cond_12
    sub-float v2, v13, v3

    move v1, v3

    sub-float v3, v5, v1

    add-float v4, v13, v1

    add-float/2addr v5, v1

    .line 484
    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move-object/from16 v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    .line 485
    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move v1, v4

    move v4, v2

    move v2, v1

    move-object/from16 v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    :cond_13
    :goto_c
    if-eqz v28, :cond_14

    .line 489
    invoke-virtual/range {p1 .. p1}, Landroid/graphics/Canvas;->restore()V

    :cond_14
    move v6, v12

    move v1, v13

    move v2, v1

    move v3, v14

    goto :goto_f

    :goto_d
    if-ne v4, v1, :cond_15

    const/high16 v33, 0x3f800000    # 1.0f

    sub-float v5, v33, v9

    goto :goto_e

    :cond_15
    const/high16 v33, 0x3f800000    # 1.0f

    div-float v5, v9, v26

    sub-float v9, v33, v5

    :goto_e
    sub-float/2addr v14, v3

    mul-float/2addr v14, v5

    add-float/2addr v3, v14

    sub-float/2addr v12, v6

    mul-float/2addr v12, v5

    add-float/2addr v6, v12

    int-to-float v1, v10

    .line 425
    invoke-static/range {v29 .. v29}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    mul-float/2addr v2, v9

    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    mul-float/2addr v2, v4

    sub-float v2, v1, v2

    .line 426
    invoke-static/range {v29 .. v29}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    mul-float/2addr v4, v9

    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    mul-float/2addr v4, v5

    add-float/2addr v1, v4

    .line 427
    invoke-static/range {v29 .. v29}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    mul-float/2addr v4, v9

    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    goto :goto_10

    :goto_f
    move v9, v1

    move v5, v6

    move v13, v12

    move v12, v2

    goto :goto_11

    :cond_16
    int-to-float v1, v10

    .line 496
    invoke-static/range {v29 .. v29}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    mul-float/2addr v2, v4

    sub-float v2, v1, v2

    .line 497
    invoke-static/range {v29 .. v29}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    mul-float/2addr v4, v5

    add-float/2addr v1, v4

    .line 498
    invoke-static/range {v29 .. v29}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    :goto_10
    mul-float/2addr v4, v5

    sub-float v12, v6, v4

    goto :goto_f

    :goto_11
    cmpl-float v1, v3, v5

    if-eqz v1, :cond_17

    int-to-float v2, v10

    .line 501
    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move v4, v2

    move-object/from16 v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    :cond_17
    int-to-float v4, v10

    cmpl-float v1, v12, v4

    if-eqz v1, :cond_18

    .line 504
    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move-object/from16 v1, p1

    move v2, v12

    move v3, v13

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    .line 505
    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move v2, v9

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    goto/16 :goto_19

    :cond_18
    move-object/from16 v1, p1

    goto/16 :goto_19

    .line 508
    :cond_19
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    const/16 v15, 0xe

    if-eq v2, v15, :cond_1b

    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v2, v15, :cond_1a

    goto :goto_12

    :cond_1a
    const/4 v3, 0x0

    goto :goto_13

    :cond_1b
    :goto_12
    const/4 v3, 0x1

    .line 509
    :goto_13
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    const/4 v6, 0x2

    const/high16 v33, 0x3f800000    # 1.0f

    if-ne v2, v6, :cond_1c

    sub-float v5, v33, v4

    move v9, v5

    goto :goto_14

    :cond_1c
    move v9, v4

    .line 510
    :goto_14
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadIconScale:F

    mul-float/2addr v2, v4

    .line 512
    invoke-static/range {v29 .. v29}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    mul-float v12, v4, v2

    invoke-static/range {v33 .. v33}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    mul-float v13, v4, v2

    .line 513
    invoke-static/range {v23 .. v23}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    mul-float v14, v4, v2

    invoke-static/range {v28 .. v28}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    mul-float v15, v4, v2

    .line 514
    invoke-static/range {v24 .. v24}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    mul-float v28, v4, v2

    invoke-static/range {v22 .. v22}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    if-nez v3, :cond_22

    .line 517
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    const/4 v6, 0x3

    if-eq v2, v6, :cond_1d

    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v3, v6, :cond_1f

    .line 519
    :cond_1d
    iget-boolean v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->animatingTransition:Z

    if-eqz v3, :cond_1f

    .line 520
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    mul-float v2, v9, v19

    int-to-float v3, v10

    int-to-float v4, v11

    .line 521
    invoke-virtual {v1, v2, v3, v4}, Landroid/graphics/Canvas;->rotate(FFF)V

    const/high16 v33, 0x3f800000    # 1.0f

    sub-float v5, v33, v9

    cmpl-float v2, v5, v17

    if-lez v2, :cond_1e

    .line 525
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    mul-float v6, v5, v27

    float-to-int v6, v6

    invoke-virtual {v2, v6}, Landroid/graphics/Paint;->setAlpha(I)V

    mul-float/2addr v5, v15

    move v2, v3

    sub-float v3, v4, v5

    add-float/2addr v5, v4

    .line 528
    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move/from16 v29, v4

    move v4, v2

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    move/from16 v31, v2

    goto :goto_15

    :cond_1e
    move/from16 v31, v3

    move/from16 v29, v4

    .line 531
    :goto_15
    iget-object v1, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    const/16 v5, 0xff

    invoke-virtual {v1, v5}, Landroid/graphics/Paint;->setAlpha(I)V

    add-float v4, v29, v13

    mul-float v1, v12, v9

    sub-float v3, v4, v1

    add-float v4, v29, v15

    mul-float v28, v28, v9

    sub-float v5, v4, v28

    sub-float v1, v31, v12

    mul-float/2addr v13, v9

    add-float v2, v1, v13

    mul-float/2addr v14, v9

    add-float v4, v31, v14

    .line 534
    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move-object/from16 v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    add-float v1, v31, v12

    sub-float v2, v1, v13

    sub-float v4, v31, v14

    .line 535
    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move-object/from16 v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    .line 537
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    goto/16 :goto_19

    .line 539
    :cond_1f
    iget-boolean v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->animatingTransition:Z

    if-eqz v3, :cond_20

    move v5, v9

    :goto_16
    const/high16 v33, 0x3f800000    # 1.0f

    goto :goto_17

    :cond_20
    const/4 v6, 0x2

    if-ne v2, v6, :cond_21

    move/from16 v5, v17

    goto :goto_16

    :cond_21
    const/high16 v5, 0x3f800000    # 1.0f

    goto :goto_16

    :goto_17
    sub-float v5, v33, v5

    mul-float v2, v5, v27

    float-to-int v2, v2

    if-lez v2, :cond_24

    .line 544
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    int-to-float v4, v10

    int-to-float v3, v11

    .line 545
    invoke-virtual {v1, v5, v5, v4, v3}, Landroid/graphics/Canvas;->scale(FFFF)V

    .line 546
    iget-object v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    invoke-virtual {v5, v2}, Landroid/graphics/Paint;->setAlpha(I)V

    add-float v5, v3, v15

    sub-float v9, v5, v12

    sub-float/2addr v3, v15

    .line 551
    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move v2, v4

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    sub-float v4, v2, v12

    .line 552
    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move v1, v4

    move v4, v2

    move v2, v1

    move-object/from16 v1, p1

    move v3, v9

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    move v2, v4

    add-float v4, v2, v12

    .line 553
    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move v1, v4

    move v4, v2

    move v2, v1

    move-object/from16 v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    .line 555
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    goto/16 :goto_19

    :cond_22
    const/high16 v33, 0x3f800000    # 1.0f

    sub-float v5, v33, v9

    cmpl-float v3, v5, v17

    if-lez v3, :cond_23

    .line 561
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    int-to-float v4, v10

    int-to-float v3, v11

    .line 562
    invoke-virtual {v1, v5, v5, v4, v3}, Landroid/graphics/Canvas;->scale(FFFF)V

    .line 563
    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    mul-float v5, v5, v27

    iget v13, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->overrideAlpha:F

    mul-float/2addr v5, v13

    float-to-int v5, v5

    invoke-virtual {v6, v5}, Landroid/graphics/Paint;->setAlpha(I)V

    add-float v5, v3, v15

    sub-float v13, v5, v12

    sub-float/2addr v3, v15

    .line 568
    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move v14, v2

    move v2, v4

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    sub-float v4, v2, v12

    .line 569
    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move v1, v4

    move v4, v2

    move v2, v1

    move-object/from16 v1, p1

    move v3, v13

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    move v2, v4

    add-float v4, v2, v12

    .line 570
    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move v1, v4

    move v4, v2

    move v2, v1

    move-object/from16 v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    .line 571
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    goto :goto_18

    :cond_23
    move v14, v2

    :goto_18
    cmpl-float v2, v9, v17

    if-lez v2, :cond_24

    .line 575
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    int-to-float v2, v10

    int-to-float v3, v11

    .line 576
    invoke-virtual {v1, v9, v9, v2, v3}, Landroid/graphics/Canvas;->scale(FFFF)V

    .line 577
    iget-object v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint3:Landroid/graphics/Paint;

    mul-float v9, v9, v27

    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->overrideAlpha:F

    mul-float/2addr v9, v5

    float-to-int v5, v9

    invoke-virtual {v4, v5}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 578
    iget-object v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->rect:Landroid/graphics/RectF;

    sub-float v5, v2, v14

    sub-float v6, v3, v14

    add-float/2addr v2, v14

    add-float/2addr v3, v14

    invoke-virtual {v4, v5, v6, v2, v3}, Landroid/graphics/RectF;->set(FFFF)V

    .line 579
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->rect:Landroid/graphics/RectF;

    invoke-static/range {v24 .. v24}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    int-to-float v3, v3

    invoke-static/range {v24 .. v24}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    iget-object v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint3:Landroid/graphics/Paint;

    invoke-virtual {v1, v2, v3, v4, v5}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V

    .line 581
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    .line 587
    :cond_24
    :goto_19
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    const/high16 v9, 0x43b40000    # 360.0f

    const/16 v13, 0x9

    const/16 v14, 0x8

    const/4 v15, 0x5

    const/16 v4, 0xd

    const/4 v6, 0x3

    if-eq v2, v6, :cond_2c

    const/16 v5, 0xe

    if-eq v2, v5, :cond_2c

    const/4 v3, 0x4

    if-ne v2, v3, :cond_26

    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-eq v3, v5, :cond_25

    if-ne v3, v6, :cond_26

    :cond_25
    move/from16 v29, v4

    const/4 v2, 0x0

    const/high16 v3, 0x40800000    # 4.0f

    const/16 v12, 0xf

    goto/16 :goto_1e

    :cond_26
    const/16 v3, 0xa

    if-eq v2, v3, :cond_28

    .line 713
    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-eq v5, v3, :cond_28

    if-ne v2, v4, :cond_27

    goto :goto_1a

    :cond_27
    const/16 v12, 0xf

    goto/16 :goto_2d

    .line 715
    :cond_28
    :goto_1a
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    const/4 v3, 0x4

    if-eq v2, v3, :cond_2a

    const/4 v3, 0x6

    if-ne v2, v3, :cond_29

    goto :goto_1b

    :cond_29
    const/16 v2, 0xff

    goto :goto_1c

    .line 716
    :cond_2a
    :goto_1b
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    const/high16 v33, 0x3f800000    # 1.0f

    sub-float v5, v33, v2

    mul-float v5, v5, v27

    float-to-int v2, v5

    :goto_1c
    if-eqz v2, :cond_27

    const/4 v3, 0x0

    .line 724
    invoke-virtual {v0, v3}, Lorg/telegram/ui/Components/MediaActionDrawable;->applyShaderMatrix(Z)V

    .line 725
    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    int-to-float v2, v2

    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->overrideAlpha:F

    mul-float/2addr v2, v5

    float-to-int v2, v2

    invoke-virtual {v3, v2}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 727
    iget-boolean v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->drawProgressCircle:Z

    if-eqz v2, :cond_27

    .line 728
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->animatedDownloadProgress:F

    mul-float/2addr v2, v9

    const/high16 v3, 0x40800000    # 4.0f

    invoke-static {v3, v2}, Ljava/lang/Math;->max(FF)F

    move-result v2

    .line 729
    iget-boolean v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->isMini:Z

    if-eqz v5, :cond_2b

    move/from16 v6, v24

    goto :goto_1d

    :cond_2b
    move v6, v3

    :goto_1d
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    .line 730
    iget-object v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->rect:Landroid/graphics/RectF;

    iget v6, v7, Landroid/graphics/Rect;->left:I

    add-int/2addr v6, v3

    int-to-float v6, v6

    iget v9, v7, Landroid/graphics/Rect;->top:I

    add-int/2addr v9, v3

    int-to-float v9, v9

    iget v4, v7, Landroid/graphics/Rect;->right:I

    sub-int/2addr v4, v3

    int-to-float v4, v4

    iget v12, v7, Landroid/graphics/Rect;->bottom:I

    sub-int/2addr v12, v3

    int-to-float v3, v12

    invoke-virtual {v5, v6, v9, v4, v3}, Landroid/graphics/RectF;->set(FFFF)V

    move v4, v2

    .line 731
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->rect:Landroid/graphics/RectF;

    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadRadOffset:F

    const/4 v5, 0x0

    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    const/16 v12, 0xf

    const/16 v29, 0xd

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawArc(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V

    goto/16 :goto_2d

    :cond_2c
    move/from16 v29, v4

    const/high16 v3, 0x40800000    # 4.0f

    const/16 v12, 0xf

    const/4 v2, 0x0

    .line 588
    :goto_1e
    invoke-virtual {v0, v2}, Lorg/telegram/ui/Components/MediaActionDrawable;->applyShaderMatrix(Z)V

    .line 595
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    const/4 v6, 0x2

    if-ne v2, v6, :cond_2e

    .line 596
    iget-boolean v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->drawProgressCircle:Z

    if-eqz v2, :cond_2d

    .line 597
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    cmpg-float v4, v2, v26

    if-gtz v4, :cond_2d

    div-float v2, v2, v26

    const/high16 v33, 0x3f800000    # 1.0f

    sub-float v5, v33, v2

    .line 600
    invoke-static/range {v23 .. v23}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    mul-float/2addr v2, v5

    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    mul-float/2addr v2, v4

    mul-float v5, v5, v27

    float-to-int v4, v5

    goto :goto_1f

    :cond_2d
    move/from16 v2, v17

    const/4 v4, 0x0

    :goto_1f
    move v13, v4

    move/from16 v28, v9

    move/from16 v3, v17

    move v4, v3

    move v9, v4

    const/high16 v5, 0x3f800000    # 1.0f

    :goto_20
    const/high16 v6, 0x3f800000    # 1.0f

    goto/16 :goto_29

    :cond_2e
    if-eq v2, v12, :cond_2f

    if-eqz v2, :cond_2f

    const/4 v4, 0x1

    if-eq v2, v4, :cond_2f

    if-eq v2, v15, :cond_2f

    .line 611
    invoke-static {v2}, Lorg/telegram/ui/Components/MediaActionDrawable;->isCustomFileIcon(I)Z

    move-result v2

    if-nez v2, :cond_2f

    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-eq v2, v14, :cond_2f

    if-eq v2, v13, :cond_2f

    const/4 v4, 0x7

    if-eq v2, v4, :cond_2f

    const/4 v4, 0x6

    if-ne v2, v4, :cond_30

    :cond_2f
    move/from16 v28, v9

    goto/16 :goto_27

    :cond_30
    const/4 v4, 0x4

    if-ne v2, v4, :cond_32

    .line 628
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    const/high16 v33, 0x3f800000    # 1.0f

    sub-float v5, v33, v2

    .line 630
    invoke-static/range {v23 .. v23}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    mul-float/2addr v2, v4

    mul-float v4, v5, v27

    float-to-int v4, v4

    .line 635
    iget v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    const/16 v3, 0xe

    if-ne v6, v3, :cond_31

    .line 636
    iget v3, v7, Landroid/graphics/Rect;->left:I

    int-to-float v3, v3

    .line 637
    iget v6, v7, Landroid/graphics/Rect;->top:I

    :goto_21
    int-to-float v6, v6

    goto :goto_22

    .line 639
    :cond_31
    invoke-virtual {v7}, Landroid/graphics/Rect;->centerX()I

    move-result v3

    int-to-float v3, v3

    .line 640
    invoke-virtual {v7}, Landroid/graphics/Rect;->centerY()I

    move-result v6

    goto :goto_21

    :goto_22
    move v13, v4

    move/from16 v28, v9

    move/from16 v4, v17

    move v9, v6

    goto :goto_20

    :cond_32
    const/16 v3, 0xe

    if-eq v2, v3, :cond_34

    const/4 v6, 0x3

    if-ne v2, v6, :cond_33

    goto :goto_23

    .line 664
    :cond_33
    invoke-static/range {v23 .. v23}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    mul-float/2addr v2, v3

    move/from16 v28, v9

    move/from16 v3, v17

    move v4, v3

    move v9, v4

    const/high16 v5, 0x3f800000    # 1.0f

    const/high16 v6, 0x3f800000    # 1.0f

    const/16 v13, 0xff

    goto/16 :goto_29

    .line 643
    :cond_34
    :goto_23
    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    const/high16 v33, 0x3f800000    # 1.0f

    sub-float v2, v33, v5

    .line 645
    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    const/4 v4, 0x4

    if-ne v3, v4, :cond_35

    move v2, v5

    move/from16 v3, v17

    goto :goto_24

    :cond_35
    const/high16 v3, 0x42340000    # 45.0f

    mul-float/2addr v2, v3

    move v3, v2

    const/high16 v2, 0x3f800000    # 1.0f

    .line 653
    :goto_24
    invoke-static/range {v23 .. v23}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    iget v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    mul-float/2addr v4, v6

    mul-float v5, v5, v27

    float-to-int v5, v5

    .line 655
    iget v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    move/from16 v28, v9

    const/16 v9, 0xe

    if-ne v6, v9, :cond_36

    .line 656
    iget v6, v7, Landroid/graphics/Rect;->left:I

    int-to-float v6, v6

    .line 657
    iget v9, v7, Landroid/graphics/Rect;->top:I

    :goto_25
    int-to-float v9, v9

    goto :goto_26

    .line 659
    :cond_36
    invoke-virtual {v7}, Landroid/graphics/Rect;->centerX()I

    move-result v6

    int-to-float v6, v6

    .line 660
    invoke-virtual {v7}, Landroid/graphics/Rect;->centerY()I

    move-result v9

    goto :goto_25

    :goto_26
    move v13, v5

    move v5, v2

    move v2, v4

    move v4, v3

    move v3, v6

    goto/16 :goto_20

    .line 614
    :goto_27
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    .line 617
    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    const/4 v4, 0x6

    if-ne v2, v4, :cond_37

    div-float v3, v3, v26

    const/high16 v6, 0x3f800000    # 1.0f

    .line 615
    invoke-static {v6, v3}, Ljava/lang/Math;->min(FF)F

    move-result v3

    goto :goto_28

    :cond_37
    const/high16 v6, 0x3f800000    # 1.0f

    :goto_28
    sub-float v5, v6, v3

    .line 623
    invoke-virtual {v7}, Landroid/graphics/Rect;->centerX()I

    move-result v2

    int-to-float v2, v2

    .line 624
    invoke-virtual {v7}, Landroid/graphics/Rect;->centerY()I

    move-result v3

    int-to-float v3, v3

    .line 625
    invoke-static/range {v23 .. v23}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    mul-float/2addr v4, v5

    iget v9, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    mul-float/2addr v4, v9

    mul-float v9, v5, v24

    .line 626
    invoke-static {v6, v9}, Ljava/lang/Math;->min(FF)F

    move-result v9

    mul-float v9, v9, v27

    float-to-int v9, v9

    move v13, v9

    move v9, v3

    move v3, v2

    move v2, v4

    move/from16 v4, v17

    :goto_29
    cmpl-float v36, v5, v6

    if-eqz v36, :cond_38

    .line 668
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    .line 669
    invoke-virtual {v1, v5, v5, v3, v9}, Landroid/graphics/Canvas;->scale(FFFF)V

    :cond_38
    cmpl-float v9, v4, v17

    if-eqz v9, :cond_39

    .line 672
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    int-to-float v3, v10

    int-to-float v5, v11

    .line 673
    invoke-virtual {v1, v4, v3, v5}, Landroid/graphics/Canvas;->rotate(FFF)V

    :cond_39
    if-eqz v13, :cond_3c

    .line 676
    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    int-to-float v4, v13

    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->overrideAlpha:F

    mul-float/2addr v5, v4

    float-to-int v5, v5

    invoke-virtual {v3, v5}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 677
    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    const/16 v5, 0xe

    if-eq v3, v5, :cond_3a

    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v3, v5, :cond_3b

    :cond_3a
    const/high16 v14, 0x40800000    # 4.0f

    goto :goto_2a

    :cond_3b
    int-to-float v3, v10

    sub-float v4, v3, v2

    int-to-float v5, v11

    move v6, v3

    sub-float v3, v5, v2

    add-float/2addr v6, v2

    add-float/2addr v5, v2

    move v2, v6

    .line 682
    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move v14, v4

    move v4, v2

    move v2, v14

    const/high16 v14, 0x40800000    # 4.0f

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    .line 683
    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move v1, v4

    move v4, v2

    move v2, v1

    move-object/from16 v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    goto :goto_2b

    .line 678
    :goto_2a
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint3:Landroid/graphics/Paint;

    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->overrideAlpha:F

    mul-float/2addr v4, v3

    float-to-int v3, v4

    invoke-virtual {v2, v3}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 679
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->rect:Landroid/graphics/RectF;

    invoke-static/range {v22 .. v22}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    sub-int v3, v10, v3

    int-to-float v3, v3

    invoke-static/range {v22 .. v22}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    sub-int v4, v11, v4

    int-to-float v4, v4

    invoke-static/range {v22 .. v22}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    add-int/2addr v5, v10

    int-to-float v5, v5

    invoke-static/range {v22 .. v22}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    add-int/2addr v6, v11

    int-to-float v6, v6

    invoke-virtual {v2, v3, v4, v5, v6}, Landroid/graphics/RectF;->set(FFFF)V

    .line 680
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->rect:Landroid/graphics/RectF;

    invoke-static/range {v24 .. v24}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    int-to-float v3, v3

    invoke-static/range {v24 .. v24}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    iget-object v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint3:Landroid/graphics/Paint;

    invoke-virtual {v1, v2, v3, v4, v5}, Landroid/graphics/Canvas;->drawRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;)V

    goto :goto_2b

    :cond_3c
    const/high16 v14, 0x40800000    # 4.0f

    :goto_2b
    if-eqz v9, :cond_3d

    .line 687
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    :cond_3d
    if-eqz v36, :cond_3e

    .line 690
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    .line 696
    :cond_3e
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    const/4 v6, 0x3

    if-eq v2, v6, :cond_3f

    const/16 v3, 0xe

    if-eq v2, v3, :cond_3f

    const/4 v4, 0x4

    if-ne v2, v4, :cond_43

    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-eq v2, v3, :cond_3f

    if-ne v2, v6, :cond_43

    :cond_3f
    if-eqz v13, :cond_43

    .line 697
    iget-boolean v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->drawProgressCircle:Z

    if-eqz v2, :cond_43

    .line 698
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->animatedDownloadProgress:F

    mul-float v2, v2, v28

    invoke-static {v14, v2}, Ljava/lang/Math;->max(FF)F

    move-result v9

    .line 699
    iget-boolean v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->isMini:Z

    if-eqz v2, :cond_40

    move/from16 v6, v24

    goto :goto_2c

    :cond_40
    move v6, v14

    :goto_2c
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 700
    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->rect:Landroid/graphics/RectF;

    iget v4, v7, Landroid/graphics/Rect;->left:I

    add-int/2addr v4, v2

    int-to-float v4, v4

    iget v5, v7, Landroid/graphics/Rect;->top:I

    add-int/2addr v5, v2

    int-to-float v5, v5

    iget v6, v7, Landroid/graphics/Rect;->right:I

    sub-int/2addr v6, v2

    int-to-float v6, v6

    iget v14, v7, Landroid/graphics/Rect;->bottom:I

    sub-int/2addr v14, v2

    int-to-float v2, v14

    invoke-virtual {v3, v4, v5, v6, v2}, Landroid/graphics/RectF;->set(FFFF)V

    .line 702
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    const/16 v3, 0xe

    if-eq v2, v3, :cond_41

    const/4 v4, 0x4

    if-ne v2, v4, :cond_42

    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v2, v3, :cond_42

    .line 703
    :cond_41
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    int-to-float v3, v13

    mul-float v3, v3, v18

    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->overrideAlpha:F

    mul-float/2addr v3, v4

    float-to-int v3, v3

    invoke-virtual {v2, v3}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 704
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->rect:Landroid/graphics/RectF;

    const/4 v5, 0x0

    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    const/4 v3, 0x0

    const/high16 v4, 0x43b40000    # 360.0f

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawArc(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V

    .line 705
    iget-object v1, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    invoke-virtual {v1, v13}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 707
    :cond_42
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->rect:Landroid/graphics/RectF;

    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadRadOffset:F

    const/4 v5, 0x0

    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move-object/from16 v1, p1

    move v4, v9

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawArc(Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V

    .line 743
    :cond_43
    :goto_2d
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v2, v3, :cond_44

    const/high16 v9, 0x3f800000    # 1.0f

    const/high16 v13, 0x3f800000    # 1.0f

    goto :goto_30

    :cond_44
    const/4 v4, 0x4

    if-eq v2, v4, :cond_45

    const/4 v6, 0x3

    if-eq v2, v6, :cond_45

    const/16 v3, 0xe

    if-ne v2, v3, :cond_46

    :cond_45
    const/high16 v6, 0x3f800000    # 1.0f

    goto :goto_2f

    .line 749
    :cond_46
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    div-float v2, v2, v26

    const/high16 v6, 0x3f800000    # 1.0f

    invoke-static {v6, v2}, Ljava/lang/Math;->min(FF)F

    move-result v5

    .line 750
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    div-float v2, v2, v26

    sub-float v2, v6, v2

    move/from16 v3, v17

    invoke-static {v3, v2}, Ljava/lang/Math;->max(FF)F

    move-result v2

    :goto_2e
    move v13, v2

    move v9, v5

    goto :goto_30

    .line 746
    :goto_2f
    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    sub-float v2, v6, v5

    goto :goto_2e

    .line 753
    :goto_30
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v2, v12, :cond_47

    .line 754
    sget-object v3, Lorg/telegram/ui/ActionBar/Theme;->chat_updatePath:[Landroid/graphics/Path;

    :goto_31
    const/4 v4, 0x0

    goto :goto_32

    .line 755
    :cond_47
    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    if-ne v3, v12, :cond_48

    .line 756
    sget-object v3, Lorg/telegram/ui/ActionBar/Theme;->chat_updatePath:[Landroid/graphics/Path;

    move-object v4, v3

    const/4 v3, 0x0

    goto :goto_32

    :cond_48
    const/4 v3, 0x0

    goto :goto_31

    :goto_32
    if-ne v2, v15, :cond_4a

    .line 759
    sget-object v3, Lorg/telegram/ui/ActionBar/Theme;->chat_filePath:[Landroid/graphics/Path;

    :cond_49
    :goto_33
    move-object v12, v3

    move-object v14, v4

    goto :goto_34

    .line 760
    :cond_4a
    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    if-ne v5, v15, :cond_49

    .line 761
    sget-object v4, Lorg/telegram/ui/ActionBar/Theme;->chat_filePath:[Landroid/graphics/Path;

    goto :goto_33

    .line 763
    :goto_34
    invoke-static {v2}, Lorg/telegram/ui/Components/MediaActionDrawable;->isCustomFileIcon(I)Z

    move-result v2

    if-eqz v2, :cond_4b

    .line 764
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    invoke-direct {v0, v2}, Lorg/telegram/ui/Components/MediaActionDrawable;->getCustomIconDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v2

    goto :goto_35

    :cond_4b
    const/4 v2, 0x0

    .line 765
    :goto_35
    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    invoke-static {v3}, Lorg/telegram/ui/Components/MediaActionDrawable;->isCustomFileIcon(I)Z

    move-result v3

    if-eqz v3, :cond_4c

    .line 766
    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    invoke-direct {v0, v3}, Lorg/telegram/ui/Components/MediaActionDrawable;->getCustomIconDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v3

    move-object/from16 v16, v3

    goto :goto_36

    :cond_4c
    const/16 v16, 0x0

    .line 767
    :goto_36
    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    const/4 v4, 0x7

    if-ne v3, v4, :cond_4e

    .line 768
    sget-object v2, Lorg/telegram/ui/ActionBar/Theme;->chat_flameIcon:Landroid/graphics/drawable/Drawable;

    :cond_4d
    :goto_37
    const/16 v4, 0x8

    goto :goto_38

    .line 769
    :cond_4e
    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    if-ne v5, v4, :cond_4d

    .line 770
    sget-object v16, Lorg/telegram/ui/ActionBar/Theme;->chat_flameIcon:Landroid/graphics/drawable/Drawable;

    goto :goto_37

    :goto_38
    if-ne v3, v4, :cond_50

    .line 773
    sget-object v2, Lorg/telegram/ui/ActionBar/Theme;->chat_gifIcon:Landroid/graphics/drawable/Drawable;

    :cond_4f
    :goto_39
    move-object v15, v2

    move-object/from16 v2, v16

    goto :goto_3a

    .line 774
    :cond_50
    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    if-ne v5, v4, :cond_4f

    .line 775
    sget-object v16, Lorg/telegram/ui/ActionBar/Theme;->chat_gifIcon:Landroid/graphics/drawable/Drawable;

    goto :goto_39

    .line 779
    :goto_3a
    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    const/high16 v16, 0x40c00000    # 6.0f

    const/16 v5, 0x9

    if-eq v4, v5, :cond_51

    if-ne v3, v5, :cond_52

    :cond_51
    const/4 v3, 0x0

    goto :goto_3b

    :cond_52
    move-object/from16 v28, v7

    move-object v7, v2

    goto/16 :goto_3e

    .line 780
    :goto_3b
    invoke-virtual {v0, v3}, Lorg/telegram/ui/Components/MediaActionDrawable;->applyShaderMatrix(Z)V

    .line 781
    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v4, v5, :cond_53

    const/16 v4, 0xff

    goto :goto_3c

    :cond_53
    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    mul-float v4, v4, v27

    float-to-int v4, v4

    :goto_3c
    invoke-virtual {v3, v4}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 782
    invoke-static/range {v23 .. v23}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    add-int/2addr v3, v11

    .line 783
    invoke-static/range {v25 .. v25}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    sub-int v4, v10, v4

    .line 784
    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    iget v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-eq v5, v6, :cond_54

    .line 785
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    .line 786
    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    int-to-float v6, v10

    move-object/from16 v18, v2

    int-to-float v2, v11

    invoke-virtual {v1, v5, v5, v6, v2}, Landroid/graphics/Canvas;->scale(FFFF)V

    goto :goto_3d

    :cond_54
    move-object/from16 v18, v2

    .line 788
    :goto_3d
    invoke-static/range {v16 .. v16}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    sub-int v2, v4, v2

    int-to-float v2, v2

    invoke-static/range {v16 .. v16}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    sub-int v5, v3, v5

    int-to-float v5, v5

    move v6, v4

    int-to-float v4, v6

    move/from16 v22, v5

    int-to-float v5, v3

    move/from16 v24, v6

    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move-object/from16 v28, v7

    move-object/from16 v7, v18

    move/from16 v18, v3

    move/from16 v3, v22

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    move v2, v4

    move v3, v5

    .line 789
    invoke-static/range {v20 .. v20}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    add-int v4, v24, v1

    int-to-float v4, v4

    invoke-static/range {v20 .. v20}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    sub-int v1, v18, v1

    int-to-float v5, v1

    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move-object/from16 v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    .line 790
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-eq v2, v3, :cond_55

    .line 791
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    .line 794
    :cond_55
    :goto_3e
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    const/16 v3, 0xc

    if-eq v2, v3, :cond_56

    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v2, v3, :cond_57

    :cond_56
    const/4 v3, 0x0

    goto :goto_3f

    :cond_57
    move/from16 v22, v13

    const/16 v13, 0xd

    goto/16 :goto_42

    .line 795
    :goto_3f
    invoke-virtual {v0, v3}, Lorg/telegram/ui/Components/MediaActionDrawable;->applyShaderMatrix(Z)V

    .line 797
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v2, v3, :cond_58

    const/16 v4, 0xd

    const/high16 v5, 0x3f800000    # 1.0f

    goto :goto_40

    .line 802
    :cond_58
    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    const/16 v4, 0xd

    if-ne v3, v4, :cond_59

    goto :goto_40

    :cond_59
    const/high16 v33, 0x3f800000    # 1.0f

    sub-float v5, v33, v5

    .line 805
    :goto_40
    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    if-ne v2, v3, :cond_5a

    const/16 v2, 0xff

    goto :goto_41

    :cond_5a
    mul-float v2, v5, v27

    float-to-int v2, v2

    :goto_41
    invoke-virtual {v6, v2}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 806
    invoke-static/range {v23 .. v23}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    .line 807
    invoke-static/range {v25 .. v25}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    .line 808
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-eq v2, v3, :cond_5b

    .line 809
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    int-to-float v2, v10

    int-to-float v3, v11

    .line 810
    invoke-virtual {v1, v5, v5, v2, v3}, Landroid/graphics/Canvas;->scale(FFFF)V

    .line 813
    :cond_5b
    invoke-static/range {v23 .. v23}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    int-to-float v2, v2

    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    mul-float/2addr v2, v3

    int-to-float v3, v10

    move v5, v2

    sub-float v2, v3, v5

    int-to-float v6, v11

    move/from16 v18, v3

    sub-float v3, v6, v5

    add-float v18, v18, v5

    add-float/2addr v5, v6

    .line 814
    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move/from16 v22, v13

    move v13, v4

    move/from16 v4, v18

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    .line 815
    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move v1, v4

    move v4, v2

    move v2, v1

    move-object/from16 v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    .line 817
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-eq v2, v3, :cond_5c

    .line 818
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    .line 821
    :cond_5c
    :goto_42
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    const/high16 v3, 0x40a00000    # 5.0f

    if-eq v2, v13, :cond_5d

    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v2, v13, :cond_5e

    :cond_5d
    const/4 v2, 0x0

    goto :goto_43

    :cond_5e
    move/from16 v18, v3

    goto/16 :goto_45

    .line 822
    :goto_43
    invoke-virtual {v0, v2}, Lorg/telegram/ui/Components/MediaActionDrawable;->applyShaderMatrix(Z)V

    .line 824
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v2, v4, :cond_5f

    const/high16 v5, 0x3f800000    # 1.0f

    goto :goto_44

    .line 829
    :cond_5f
    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    if-ne v4, v13, :cond_60

    goto :goto_44

    :cond_60
    const/high16 v33, 0x3f800000    # 1.0f

    sub-float v5, v33, v5

    .line 832
    :goto_44
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->textPaint:Landroid/text/TextPaint;

    mul-float v4, v5, v27

    float-to-int v4, v4

    invoke-virtual {v2, v4}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 833
    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    add-int/2addr v2, v11

    .line 834
    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->percentStringWidth:I

    const/16 v34, 0x2

    div-int/lit8 v4, v4, 0x2

    sub-int v4, v10, v4

    .line 835
    iget v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    move/from16 v18, v3

    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-eq v6, v3, :cond_61

    .line 836
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    int-to-float v3, v10

    int-to-float v6, v11

    .line 837
    invoke-virtual {v1, v5, v5, v3, v6}, Landroid/graphics/Canvas;->scale(FFFF)V

    .line 839
    :cond_61
    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->animatedDownloadProgress:F

    mul-float v3, v3, v21

    float-to-int v3, v3

    .line 840
    iget-object v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->percentString:Ljava/lang/String;

    if-eqz v5, :cond_62

    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->lastPercent:I

    if-eq v3, v5, :cond_63

    .line 841
    :cond_62
    iput v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->lastPercent:I

    .line 842
    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    filled-new-array {v3}, [Ljava/lang/Object;

    move-result-object v3

    const-string v5, "%d%%"

    invoke-static {v5, v3}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    iput-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->percentString:Ljava/lang/String;

    .line 843
    iget-object v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->textPaint:Landroid/text/TextPaint;

    invoke-virtual {v5, v3}, Landroid/graphics/Paint;->measureText(Ljava/lang/String;)F

    move-result v3

    float-to-double v5, v3

    invoke-static {v5, v6}, Ljava/lang/Math;->ceil(D)D

    move-result-wide v5

    double-to-int v3, v5

    iput v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->percentStringWidth:I

    .line 845
    :cond_63
    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->percentString:Ljava/lang/String;

    int-to-float v4, v4

    int-to-float v2, v2

    iget-object v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->textPaint:Landroid/text/TextPaint;

    invoke-virtual {v1, v3, v4, v2, v5}, Landroid/graphics/Canvas;->drawText(Ljava/lang/String;FFLandroid/graphics/Paint;)V

    .line 846
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-eq v2, v3, :cond_64

    .line 847
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    .line 850
    :cond_64
    :goto_45
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    const/4 v4, 0x1

    if-eqz v2, :cond_65

    if-eq v2, v4, :cond_65

    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-eqz v3, :cond_65

    if-ne v3, v4, :cond_7c

    :cond_65
    if-nez v2, :cond_66

    .line 852
    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-eq v3, v4, :cond_67

    :cond_66
    if-ne v2, v4, :cond_6c

    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-nez v3, :cond_6b

    .line 853
    :cond_67
    iget-boolean v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->animatingTransition:Z

    .line 860
    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-eqz v3, :cond_69

    .line 857
    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    if-nez v4, :cond_68

    const/high16 v33, 0x3f800000    # 1.0f

    sub-float v3, v33, v3

    :cond_68
    move v5, v3

    const/4 v3, 0x1

    goto :goto_48

    :cond_69
    const/4 v3, 0x1

    if-ne v4, v3, :cond_6a

    :goto_46
    const/high16 v5, 0x3f800000    # 1.0f

    goto :goto_48

    :cond_6a
    const/4 v5, 0x0

    goto :goto_48

    :cond_6b
    const/4 v3, 0x1

    goto :goto_47

    :cond_6c
    move v3, v4

    :goto_47
    if-ne v2, v3, :cond_6a

    goto :goto_46

    .line 866
    :goto_48
    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-eqz v4, :cond_6d

    if-ne v4, v3, :cond_6e

    :cond_6d
    if-eqz v2, :cond_71

    if-eq v2, v3, :cond_71

    .line 870
    :cond_6e
    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint2:Landroid/graphics/Paint;

    const/4 v6, 0x4

    if-ne v4, v6, :cond_6f

    .line 868
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    const/high16 v33, 0x3f800000    # 1.0f

    sub-float v2, v33, v2

    mul-float v2, v2, v27

    float-to-int v2, v2

    invoke-virtual {v3, v2}, Landroid/graphics/Paint;->setAlpha(I)V

    :goto_49
    const/4 v4, 0x1

    goto :goto_4b

    :cond_6f
    if-ne v2, v4, :cond_70

    const/16 v2, 0xff

    goto :goto_4a

    .line 870
    :cond_70
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    mul-float v2, v2, v27

    float-to-int v2, v2

    :goto_4a
    invoke-virtual {v3, v2}, Landroid/graphics/Paint;->setAlpha(I)V

    goto :goto_49

    .line 873
    :cond_71
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint2:Landroid/graphics/Paint;

    const/16 v3, 0xff

    invoke-virtual {v2, v3}, Landroid/graphics/Paint;->setAlpha(I)V

    goto :goto_49

    .line 876
    :goto_4b
    invoke-virtual {v0, v4}, Lorg/telegram/ui/Components/MediaActionDrawable;->applyShaderMatrix(Z)V

    .line 878
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    .line 879
    invoke-virtual/range {v28 .. v28}, Landroid/graphics/Rect;->centerX()I

    move-result v2

    int-to-float v2, v2

    const/high16 v33, 0x3f800000    # 1.0f

    invoke-static/range {v33 .. v33}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    int-to-float v3, v3

    sub-float v4, v33, v5

    mul-float/2addr v3, v4

    add-float/2addr v2, v3

    invoke-virtual/range {v28 .. v28}, Landroid/graphics/Rect;->centerY()I

    move-result v3

    int-to-float v3, v3

    invoke-virtual {v1, v2, v3}, Landroid/graphics/Canvas;->translate(FF)V

    const/high16 v2, 0x43fa0000    # 500.0f

    mul-float/2addr v5, v2

    .line 881
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    const/4 v4, 0x1

    if-ne v2, v4, :cond_72

    move/from16 v3, v19

    goto :goto_4c

    :cond_72
    const/4 v3, 0x0

    :goto_4c
    const/high16 v24, 0x42be0000    # 95.0f

    const/high16 v28, 0x43c00000    # 384.0f

    const/high16 v29, 0x43f20000    # 484.0f

    if-nez v2, :cond_76

    .line 882
    iget v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v6, v4, :cond_76

    cmpg-float v2, v5, v28

    if-gez v2, :cond_73

    .line 884
    sget-object v2, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_BOTH:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    div-float v3, v5, v28

    invoke-virtual {v2, v3}, Lorg/telegram/ui/Components/CubicBezierInterpolator;->getInterpolation(F)F

    move-result v2

    mul-float v19, v2, v24

    goto :goto_4d

    :cond_73
    cmpg-float v2, v5, v29

    if-gez v2, :cond_74

    .line 886
    sget-object v2, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_BOTH:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    sub-float v3, v5, v28

    div-float v3, v3, v21

    invoke-virtual {v2, v3}, Lorg/telegram/ui/Components/CubicBezierInterpolator;->getInterpolation(F)F

    move-result v2

    mul-float v2, v2, v18

    sub-float v19, v24, v2

    :cond_74
    :goto_4d
    add-float v5, v5, v21

    :cond_75
    :goto_4e
    move/from16 v3, v19

    goto :goto_4f

    :cond_76
    if-ne v2, v4, :cond_78

    .line 891
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-nez v2, :cond_78

    cmpg-float v2, v5, v21

    const/high16 v3, -0x3f600000    # -5.0f

    if-gez v2, :cond_77

    .line 893
    sget-object v2, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_BOTH:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    div-float v4, v5, v21

    invoke-virtual {v2, v4}, Lorg/telegram/ui/Components/CubicBezierInterpolator;->getInterpolation(F)F

    move-result v2

    mul-float v19, v2, v3

    goto :goto_4e

    :cond_77
    cmpg-float v2, v5, v29

    if-gez v2, :cond_75

    .line 895
    sget-object v2, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_BOTH:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    sub-float v4, v5, v21

    div-float v4, v4, v28

    invoke-virtual {v2, v4}, Lorg/telegram/ui/Components/CubicBezierInterpolator;->getInterpolation(F)F

    move-result v2

    mul-float v2, v2, v24

    add-float v19, v2, v3

    goto :goto_4e

    .line 900
    :cond_78
    :goto_4f
    invoke-virtual {v1, v3}, Landroid/graphics/Canvas;->rotate(F)V

    .line 901
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    if-eqz v2, :cond_79

    const/4 v4, 0x1

    if-ne v2, v4, :cond_7a

    :cond_79
    const/4 v4, 0x4

    if-ne v2, v4, :cond_7b

    .line 902
    :cond_7a
    invoke-virtual {v1, v9, v9}, Landroid/graphics/Canvas;->scale(FF)V

    .line 904
    :cond_7b
    sget-object v2, Lorg/telegram/ui/ActionBar/Theme;->playPauseAnimator:Lorg/telegram/ui/Components/PathAnimator;

    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint2:Landroid/graphics/Paint;

    invoke-virtual {v2, v1, v3, v5}, Lorg/telegram/ui/Components/PathAnimator;->draw(Landroid/graphics/Canvas;Landroid/graphics/Paint;F)V

    const/high16 v2, -0x40800000    # -1.0f

    const/high16 v6, 0x3f800000    # 1.0f

    .line 905
    invoke-virtual {v1, v6, v2}, Landroid/graphics/Canvas;->scale(FF)V

    .line 906
    sget-object v2, Lorg/telegram/ui/ActionBar/Theme;->playPauseAnimator:Lorg/telegram/ui/Components/PathAnimator;

    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint2:Landroid/graphics/Paint;

    invoke-virtual {v2, v1, v3, v5}, Lorg/telegram/ui/Components/PathAnimator;->draw(Landroid/graphics/Canvas;Landroid/graphics/Paint;F)V

    .line 908
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    .line 910
    :cond_7c
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    const/4 v4, 0x6

    if-eq v2, v4, :cond_7d

    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v2, v4, :cond_7e

    :cond_7d
    const/4 v3, 0x0

    goto :goto_50

    :cond_7e
    move/from16 v16, v9

    goto/16 :goto_56

    .line 911
    :goto_50
    invoke-virtual {v0, v3}, Lorg/telegram/ui/Components/MediaActionDrawable;->applyShaderMatrix(Z)V

    .line 914
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    if-eq v2, v4, :cond_81

    .line 915
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    cmpl-float v3, v2, v26

    if-lez v3, :cond_80

    sub-float v2, v2, v26

    div-float v2, v2, v26

    div-float v3, v2, v26

    const/high16 v6, 0x3f800000    # 1.0f

    .line 917
    invoke-static {v6, v3}, Ljava/lang/Math;->min(FF)F

    move-result v3

    sub-float v5, v6, v3

    cmpl-float v3, v2, v26

    if-lez v3, :cond_7f

    sub-float v2, v2, v26

    div-float v2, v2, v26

    goto :goto_51

    :cond_7f
    const/4 v2, 0x0

    goto :goto_51

    :cond_80
    const/4 v2, 0x0

    const/high16 v5, 0x3f800000    # 1.0f

    .line 923
    :goto_51
    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    const/16 v4, 0xff

    invoke-virtual {v3, v4}, Landroid/graphics/Paint;->setAlpha(I)V

    move/from16 v18, v2

    goto :goto_53

    :cond_81
    const/16 v4, 0xff

    .line 927
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    .line 930
    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    const/4 v5, 0x6

    if-eq v2, v5, :cond_82

    .line 928
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    const/high16 v33, 0x3f800000    # 1.0f

    sub-float v5, v33, v2

    mul-float v5, v5, v27

    float-to-int v2, v5

    invoke-virtual {v3, v2}, Landroid/graphics/Paint;->setAlpha(I)V

    goto :goto_52

    .line 930
    :cond_82
    invoke-virtual {v3, v4}, Landroid/graphics/Paint;->setAlpha(I)V

    :goto_52
    const/4 v5, 0x0

    const/high16 v18, 0x3f800000    # 1.0f

    .line 933
    :goto_53
    invoke-static/range {v23 .. v23}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    add-int/2addr v2, v11

    .line 934
    invoke-static/range {v25 .. v25}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    sub-int v3, v10, v3

    const/high16 v33, 0x3f800000    # 1.0f

    cmpg-float v4, v5, v33

    if-gez v4, :cond_83

    .line 936
    invoke-static/range {v16 .. v16}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    sub-int v4, v3, v4

    int-to-float v4, v4

    invoke-static/range {v16 .. v16}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    sub-int v6, v2, v6

    int-to-float v6, v6

    int-to-float v13, v3

    invoke-static/range {v16 .. v16}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    int-to-float v1, v1

    mul-float/2addr v1, v5

    sub-float/2addr v13, v1

    int-to-float v1, v2

    move/from16 v19, v1

    invoke-static/range {v16 .. v16}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    int-to-float v1, v1

    mul-float/2addr v1, v5

    sub-float v5, v19, v1

    move v1, v3

    move v3, v6

    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move/from16 v16, v13

    move v13, v2

    move v2, v4

    move/from16 v4, v16

    move/from16 v16, v9

    move v9, v1

    move-object/from16 v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    :goto_54
    const/16 v17, 0x0

    goto :goto_55

    :cond_83
    move v13, v2

    move/from16 v16, v9

    move v9, v3

    goto :goto_54

    :goto_55
    cmpl-float v1, v18, v17

    if-lez v1, :cond_84

    int-to-float v2, v9

    int-to-float v3, v13

    .line 939
    invoke-static/range {v20 .. v20}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    int-to-float v1, v1

    mul-float v1, v1, v18

    add-float v4, v2, v1

    invoke-static/range {v20 .. v20}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    int-to-float v1, v1

    mul-float v1, v1, v18

    sub-float v5, v3, v1

    iget-object v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    move-object/from16 v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->drawLine(FFFFLandroid/graphics/Paint;)V

    :cond_84
    :goto_56
    if-eqz v7, :cond_87

    if-eq v7, v15, :cond_87

    .line 944
    iget v1, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v1, v2, :cond_85

    const/16 v6, 0xff

    goto :goto_57

    :cond_85
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    const/high16 v33, 0x3f800000    # 1.0f

    sub-float v5, v33, v2

    mul-float v5, v5, v27

    float-to-int v5, v5

    move v6, v5

    .line 945
    :goto_57
    invoke-static {v1}, Lorg/telegram/ui/Components/MediaActionDrawable;->isCustomFileIcon(I)Z

    move-result v1

    if-eqz v1, :cond_86

    move-object/from16 v1, p1

    move-object v2, v7

    move v3, v10

    move v4, v11

    move/from16 v5, v22

    .line 946
    invoke-direct/range {v0 .. v6}, Lorg/telegram/ui/Components/MediaActionDrawable;->drawTintedDrawable(Landroid/graphics/Canvas;Landroid/graphics/drawable/Drawable;IIFI)V

    move v7, v5

    goto :goto_58

    :cond_86
    move-object/from16 v1, p1

    move-object v2, v7

    move v3, v10

    move v4, v11

    move/from16 v7, v22

    .line 948
    invoke-virtual {v2}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v5

    int-to-float v5, v5

    mul-float/2addr v5, v7

    float-to-int v5, v5

    .line 949
    invoke-virtual {v2}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v9

    int-to-float v9, v9

    mul-float/2addr v9, v7

    float-to-int v9, v9

    .line 950
    iget-object v10, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->colorFilter:Landroid/graphics/ColorFilter;

    invoke-virtual {v2, v10}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 951
    invoke-virtual {v2, v6}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    const/16 v34, 0x2

    .line 952
    div-int/lit8 v5, v5, 0x2

    sub-int v10, v3, v5

    div-int/lit8 v9, v9, 0x2

    sub-int v11, v4, v9

    add-int/2addr v5, v3

    add-int v6, v4, v9

    invoke-virtual {v2, v10, v11, v5, v6}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 953
    invoke-virtual {v2, v1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    goto :goto_58

    :cond_87
    move-object/from16 v1, p1

    move v3, v10

    move v4, v11

    move/from16 v7, v22

    :goto_58
    if-eqz v15, :cond_8a

    .line 957
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    iget v5, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v2, v5, :cond_88

    const/16 v6, 0xff

    goto :goto_59

    :cond_88
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    mul-float v2, v2, v27

    float-to-int v2, v2

    move v6, v2

    .line 958
    :goto_59
    invoke-static {v5}, Lorg/telegram/ui/Components/MediaActionDrawable;->isCustomFileIcon(I)Z

    move-result v2

    if-eqz v2, :cond_89

    move-object v2, v15

    move/from16 v5, v16

    .line 959
    invoke-direct/range {v0 .. v6}, Lorg/telegram/ui/Components/MediaActionDrawable;->drawTintedDrawable(Landroid/graphics/Canvas;Landroid/graphics/drawable/Drawable;IIFI)V

    goto :goto_5a

    :cond_89
    move-object v2, v15

    move/from16 v5, v16

    .line 961
    invoke-virtual {v2}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v9

    int-to-float v9, v9

    mul-float/2addr v9, v5

    float-to-int v9, v9

    .line 962
    invoke-virtual {v2}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v10

    int-to-float v10, v10

    mul-float/2addr v10, v5

    float-to-int v10, v10

    .line 963
    iget-object v11, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->colorFilter:Landroid/graphics/ColorFilter;

    invoke-virtual {v2, v11}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 964
    invoke-virtual {v2, v6}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    const/16 v34, 0x2

    .line 965
    div-int/lit8 v9, v9, 0x2

    sub-int v6, v3, v9

    div-int/lit8 v10, v10, 0x2

    sub-int v11, v4, v10

    add-int/2addr v9, v3

    add-int/2addr v10, v4

    invoke-virtual {v2, v6, v11, v9, v10}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 966
    invoke-virtual {v2, v1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    goto :goto_5a

    :cond_8a
    move/from16 v5, v16

    :goto_5a
    const/high16 v2, 0x41c00000    # 24.0f

    if-eqz v14, :cond_8e

    if-eq v14, v12, :cond_8e

    .line 971
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v6

    .line 972
    iget-object v9, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint2:Landroid/graphics/Paint;

    sget-object v10, Landroid/graphics/Paint$Style;->FILL_AND_STROKE:Landroid/graphics/Paint$Style;

    invoke-virtual {v9, v10}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    .line 973
    iget-object v9, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint2:Landroid/graphics/Paint;

    iget v10, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    iget v11, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v10, v11, :cond_8b

    const/16 v10, 0xff

    goto :goto_5b

    :cond_8b
    iget v10, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    const/high16 v33, 0x3f800000    # 1.0f

    sub-float v10, v33, v10

    mul-float v10, v10, v27

    float-to-int v10, v10

    :goto_5b
    invoke-virtual {v9, v10}, Landroid/graphics/Paint;->setAlpha(I)V

    const/4 v9, 0x1

    .line 974
    invoke-virtual {v0, v9}, Lorg/telegram/ui/Components/MediaActionDrawable;->applyShaderMatrix(Z)V

    .line 975
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    int-to-float v9, v3

    int-to-float v10, v4

    .line 976
    invoke-virtual {v1, v9, v10}, Landroid/graphics/Canvas;->translate(FF)V

    .line 977
    invoke-virtual {v1, v7, v7}, Landroid/graphics/Canvas;->scale(FF)V

    neg-int v6, v6

    const/16 v34, 0x2

    .line 978
    div-int/lit8 v6, v6, 0x2

    int-to-float v6, v6

    invoke-virtual {v1, v6, v6}, Landroid/graphics/Canvas;->translate(FF)V

    const/16 v30, 0x0

    .line 979
    aget-object v6, v14, v30

    if-eqz v6, :cond_8c

    .line 980
    iget-object v7, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint2:Landroid/graphics/Paint;

    invoke-virtual {v1, v6, v7}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    :cond_8c
    const/16 v32, 0x1

    .line 982
    aget-object v6, v14, v32

    if-eqz v6, :cond_8d

    .line 983
    iget-object v7, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->backPaint:Landroid/graphics/Paint;

    invoke-virtual {v1, v6, v7}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    .line 985
    :cond_8d
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    :cond_8e
    if-eqz v12, :cond_94

    .line 988
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    .line 989
    iget v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    iget v7, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne v6, v7, :cond_8f

    const/16 v6, 0xff

    goto :goto_5c

    :cond_8f
    iget v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    mul-float v6, v6, v27

    float-to-int v6, v6

    .line 990
    :goto_5c
    iget-object v7, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint2:Landroid/graphics/Paint;

    sget-object v9, Landroid/graphics/Paint$Style;->FILL_AND_STROKE:Landroid/graphics/Paint$Style;

    invoke-virtual {v7, v9}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    .line 991
    iget-object v7, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint2:Landroid/graphics/Paint;

    invoke-virtual {v7, v6}, Landroid/graphics/Paint;->setAlpha(I)V

    const/4 v9, 0x1

    .line 992
    invoke-virtual {v0, v9}, Lorg/telegram/ui/Components/MediaActionDrawable;->applyShaderMatrix(Z)V

    .line 993
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    int-to-float v3, v3

    int-to-float v4, v4

    .line 994
    invoke-virtual {v1, v3, v4}, Landroid/graphics/Canvas;->translate(FF)V

    .line 995
    invoke-virtual {v1, v5, v5}, Landroid/graphics/Canvas;->scale(FF)V

    neg-int v2, v2

    const/16 v34, 0x2

    .line 996
    div-int/lit8 v2, v2, 0x2

    int-to-float v2, v2

    invoke-virtual {v1, v2, v2}, Landroid/graphics/Canvas;->translate(FF)V

    const/16 v30, 0x0

    .line 997
    aget-object v2, v12, v30

    if-eqz v2, :cond_90

    .line 998
    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint2:Landroid/graphics/Paint;

    invoke-virtual {v1, v2, v3}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    .line 1000
    :cond_90
    array-length v2, v12

    const/4 v3, 0x3

    if-lt v2, v3, :cond_91

    aget-object v2, v12, v34

    if-eqz v2, :cond_91

    .line 1001
    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    invoke-virtual {v1, v2, v3}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    :cond_91
    const/16 v32, 0x1

    .line 1003
    aget-object v2, v12, v32

    if-eqz v2, :cond_93

    .line 1010
    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->backPaint:Landroid/graphics/Paint;

    const/16 v5, 0xff

    if-eq v6, v5, :cond_92

    .line 1005
    invoke-virtual {v3}, Landroid/graphics/Paint;->getAlpha()I

    move-result v2

    .line 1006
    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->backPaint:Landroid/graphics/Paint;

    int-to-float v4, v2

    int-to-float v5, v6

    div-float v5, v5, v27

    mul-float/2addr v4, v5

    float-to-int v4, v4

    invoke-virtual {v3, v4}, Landroid/graphics/Paint;->setAlpha(I)V

    const/16 v32, 0x1

    .line 1007
    aget-object v3, v12, v32

    iget-object v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->backPaint:Landroid/graphics/Paint;

    invoke-virtual {v1, v3, v4}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    .line 1008
    iget-object v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->backPaint:Landroid/graphics/Paint;

    invoke-virtual {v3, v2}, Landroid/graphics/Paint;->setAlpha(I)V

    goto :goto_5d

    .line 1010
    :cond_92
    invoke-virtual {v1, v2, v3}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    .line 1013
    :cond_93
    :goto_5d
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    .line 1016
    :cond_94
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    .line 1017
    iget-wide v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->lastAnimationTime:J

    sub-long v4, v2, v4

    const-wide/16 v6, 0x11

    cmp-long v9, v4, v6

    if-lez v9, :cond_95

    move-wide v4, v6

    .line 1021
    :cond_95
    iput-wide v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->lastAnimationTime:J

    .line 1023
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    const/4 v6, 0x3

    if-eq v2, v6, :cond_97

    const/16 v3, 0xe

    if-eq v2, v3, :cond_97

    const/4 v6, 0x4

    if-ne v2, v6, :cond_96

    iget v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-eq v6, v3, :cond_97

    :cond_96
    const/16 v3, 0xa

    if-eq v2, v3, :cond_97

    const/16 v13, 0xd

    if-ne v2, v13, :cond_9a

    .line 1024
    :cond_97
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadRadOffset:F

    const-wide/16 v6, 0x168

    mul-long/2addr v6, v4

    long-to-float v3, v6

    const v6, 0x451c4000    # 2500.0f

    div-float/2addr v3, v6

    add-float/2addr v2, v3

    iput v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadRadOffset:F

    .line 1025
    invoke-static {v2}, Lorg/telegram/ui/Components/MediaActionDrawable;->getCircleValue(F)F

    move-result v2

    iput v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadRadOffset:F

    .line 1027
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    const/4 v6, 0x2

    if-eq v2, v6, :cond_99

    .line 1028
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadProgress:F

    iget v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadProgressAnimationStart:F

    sub-float v6, v2, v3

    const/4 v7, 0x0

    cmpl-float v9, v6, v7

    if-lez v9, :cond_99

    .line 1030
    iget v9, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadProgressTime:F

    long-to-float v10, v4

    add-float/2addr v9, v10

    iput v9, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadProgressTime:F

    const/high16 v10, 0x43480000    # 200.0f

    cmpl-float v11, v9, v10

    if-ltz v11, :cond_98

    .line 1032
    iput v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->animatedDownloadProgress:F

    .line 1033
    iput v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadProgressAnimationStart:F

    .line 1034
    iput v7, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadProgressTime:F

    goto :goto_5e

    .line 1036
    :cond_98
    iget-object v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->interpolator:Landroid/view/animation/DecelerateInterpolator;

    div-float/2addr v9, v10

    invoke-virtual {v2, v9}, Landroid/view/animation/DecelerateInterpolator;->getInterpolation(F)F

    move-result v2

    mul-float/2addr v6, v2

    add-float/2addr v3, v6

    iput v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->animatedDownloadProgress:F

    .line 1040
    :cond_99
    :goto_5e
    invoke-virtual {v0}, Lorg/telegram/ui/Components/MediaActionDrawable;->invalidateSelf()V

    .line 1043
    :cond_9a
    iget-boolean v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->animatingTransition:Z

    if-eqz v2, :cond_9c

    .line 1044
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    const/high16 v6, 0x3f800000    # 1.0f

    cmpg-float v3, v2, v6

    if-gez v3, :cond_9c

    long-to-float v3, v4

    .line 1045
    iget v4, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionAnimationTime:F

    div-float/2addr v3, v4

    add-float/2addr v2, v3

    iput v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    cmpl-float v2, v2, v6

    if-ltz v2, :cond_9b

    .line 1047
    iget v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    iput v2, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    .line 1048
    iput v6, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    const/4 v3, 0x0

    .line 1049
    iput-boolean v3, v0, Lorg/telegram/ui/Components/MediaActionDrawable;->animatingTransition:Z

    .line 1051
    :cond_9b
    invoke-virtual {v0}, Lorg/telegram/ui/Components/MediaActionDrawable;->invalidateSelf()V

    :cond_9c
    const/4 v4, 0x1

    if-lt v8, v4, :cond_9d

    .line 1055
    invoke-virtual {v1, v8}, Landroid/graphics/Canvas;->restoreToCount(I)V

    :cond_9d
    return-void
.end method

.method public getCurrentIcon()I
    .locals 0

    .line 256
    iget p0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    return p0
.end method

.method public getDownloadIconScale()F
    .locals 0
    .annotation build Landroid/support/annotation/Keep;
    .end annotation

    .line 114
    iget p0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadIconScale:F

    return p0
.end method

.method public getIntrinsicHeight()I
    .locals 0

    const/high16 p0, 0x42400000    # 48.0f

    .line 1085
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    return p0
.end method

.method public getIntrinsicWidth()I
    .locals 0

    const/high16 p0, 0x42400000    # 48.0f

    .line 1080
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    return p0
.end method

.method public getMinimumHeight()I
    .locals 0

    const/high16 p0, 0x42400000    # 48.0f

    .line 1095
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    return p0
.end method

.method public getMinimumWidth()I
    .locals 0

    const/high16 p0, 0x42400000    # 48.0f

    .line 1090
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    return p0
.end method

.method public getOpacity()I
    .locals 0

    const/4 p0, -0x2

    return p0
.end method

.method public getPreviousIcon()I
    .locals 0

    .line 260
    iget p0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    return p0
.end method

.method public getProgress()F
    .locals 0

    .line 282
    iget p0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadProgress:F

    return p0
.end method

.method public getTransitionProgress()F
    .locals 1

    .line 297
    iget-boolean v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->animatingTransition:Z

    if-eqz v0, :cond_0

    iget p0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    return p0

    :cond_0
    const/high16 p0, 0x3f800000    # 1.0f

    return p0
.end method

.method public invalidateSelf()V
    .locals 0

    .line 324
    invoke-super {p0}, Landroid/graphics/drawable/Drawable;->invalidateSelf()V

    .line 325
    iget-object p0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->delegate:Lorg/telegram/ui/Components/MediaActionDrawable$MediaActionDrawableDelegate;

    if-eqz p0, :cond_0

    .line 326
    invoke-interface {p0}, Lorg/telegram/ui/Components/MediaActionDrawable$MediaActionDrawableDelegate;->invalidate()V

    :cond_0
    return-void
.end method

.method public setAlpha(I)V
    .locals 0

    return-void
.end method

.method public setBackColor(I)V
    .locals 1

    .line 164
    iget-object p0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->backPaint:Landroid/graphics/Paint;

    const/high16 v0, -0x1000000

    or-int/2addr p1, v0

    invoke-virtual {p0, p1}, Landroid/graphics/Paint;->setColor(I)V

    return-void
.end method

.method public setBackgroundDrawable(Lorg/telegram/ui/ActionBar/MessageDrawable;)V
    .locals 0

    .line 301
    iput-object p1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->messageDrawable:Lorg/telegram/ui/ActionBar/MessageDrawable;

    return-void
.end method

.method public setBackgroundGradientDrawable(Landroid/graphics/LinearGradient;)V
    .locals 0

    .line 305
    iput-object p1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->gradientDrawable:Landroid/graphics/LinearGradient;

    .line 306
    new-instance p1, Landroid/graphics/Matrix;

    invoke-direct {p1}, Landroid/graphics/Matrix;-><init>()V

    iput-object p1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->gradientMatrix:Landroid/graphics/Matrix;

    return-void
.end method

.method public setBounds(IIII)V
    .locals 0

    .line 315
    invoke-super {p0, p1, p2, p3, p4}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    sub-int/2addr p3, p1

    int-to-float p1, p3

    .line 316
    invoke-virtual {p0}, Lorg/telegram/ui/Components/MediaActionDrawable;->getIntrinsicWidth()I

    move-result p2

    int-to-float p2, p2

    div-float/2addr p1, p2

    iput p1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->scale:F

    const p2, 0x3f333333    # 0.7f

    cmpg-float p1, p1, p2

    if-gez p1, :cond_0

    .line 318
    iget-object p0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    const/high16 p1, 0x40000000    # 2.0f

    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    int-to-float p1, p1

    invoke-virtual {p0, p1}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    :cond_0
    return-void
.end method

.method public setColor(I)V
    .locals 2

    .line 156
    iget-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    const/high16 v1, -0x1000000

    or-int/2addr v1, p1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 157
    iget-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint2:Landroid/graphics/Paint;

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 158
    iget-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint3:Landroid/graphics/Paint;

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 159
    iget-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->textPaint:Landroid/text/TextPaint;

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 160
    new-instance v0, Landroid/graphics/PorterDuffColorFilter;

    sget-object v1, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {v0, p1, v1}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    iput-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->colorFilter:Landroid/graphics/ColorFilter;

    return-void
.end method

.method public setColorFilter(Landroid/graphics/ColorFilter;)V
    .locals 1

    .line 148
    iput-object p1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->colorFilter:Landroid/graphics/ColorFilter;

    .line 149
    iget-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    invoke-virtual {v0, p1}, Landroid/graphics/Paint;->setColorFilter(Landroid/graphics/ColorFilter;)Landroid/graphics/ColorFilter;

    .line 150
    iget-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint2:Landroid/graphics/Paint;

    invoke-virtual {v0, p1}, Landroid/graphics/Paint;->setColorFilter(Landroid/graphics/ColorFilter;)Landroid/graphics/ColorFilter;

    .line 151
    iget-object v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint3:Landroid/graphics/Paint;

    invoke-virtual {v0, p1}, Landroid/graphics/Paint;->setColorFilter(Landroid/graphics/ColorFilter;)Landroid/graphics/ColorFilter;

    .line 152
    iget-object p0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->textPaint:Landroid/text/TextPaint;

    invoke-virtual {p0, p1}, Landroid/graphics/Paint;->setColorFilter(Landroid/graphics/ColorFilter;)Landroid/graphics/ColorFilter;

    return-void
.end method

.method public setDelegate(Lorg/telegram/ui/Components/MediaActionDrawable$MediaActionDrawableDelegate;)V
    .locals 0

    .line 206
    iput-object p1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->delegate:Lorg/telegram/ui/Components/MediaActionDrawable$MediaActionDrawableDelegate;

    return-void
.end method

.method public setDownloadIconScale(F)V
    .locals 0

    .line 109
    iput p1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadIconScale:F

    return-void
.end method

.method public setHasOverlayImage(Z)V
    .locals 0

    .line 310
    iput-boolean p1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->hasOverlayImage:Z

    return-void
.end method

.method public setIcon(IZ)Z
    .locals 7

    .line 210
    iget v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    const/high16 v1, 0x3f800000    # 1.0f

    if-ne v0, p1, :cond_0

    iget v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-eq v0, p1, :cond_0

    .line 211
    iput v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    .line 212
    iput v1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    .line 237
    :cond_0
    iget v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    const/4 v2, 0x3

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/16 v5, 0xe

    const/4 v6, 0x1

    if-eqz p2, :cond_e

    if-eq v0, p1, :cond_d

    .line 215
    iget p2, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    if-ne p2, p1, :cond_1

    goto :goto_2

    :cond_1
    if-nez v0, :cond_2

    if-eq p1, v6, :cond_3

    :cond_2
    if-ne v0, v6, :cond_4

    if-nez p1, :cond_4

    :cond_3
    const/high16 v0, 0x43960000    # 300.0f

    .line 219
    iput v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionAnimationTime:F

    goto :goto_1

    :cond_4
    const/4 v1, 0x2

    if-ne v0, v1, :cond_7

    if-eq p1, v2, :cond_5

    if-ne p1, v5, :cond_7

    .line 221
    :cond_5
    iget-boolean v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->drawProgressCircle:Z

    if-eqz v0, :cond_6

    const/high16 v0, 0x43c80000    # 400.0f

    goto :goto_0

    :cond_6
    const/high16 v0, 0x437a0000    # 250.0f

    :goto_0
    iput v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionAnimationTime:F

    goto :goto_1

    :cond_7
    const/4 v1, 0x4

    if-eq v0, v1, :cond_8

    const/4 v3, 0x6

    if-ne p1, v3, :cond_8

    const/high16 v0, 0x43b40000    # 360.0f

    .line 223
    iput v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionAnimationTime:F

    goto :goto_1

    :cond_8
    if-ne v0, v1, :cond_9

    if-eq p1, v5, :cond_a

    :cond_9
    if-ne v0, v5, :cond_b

    if-ne p1, v1, :cond_b

    :cond_a
    const/high16 v0, 0x43200000    # 160.0f

    .line 225
    iput v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionAnimationTime:F

    goto :goto_1

    :cond_b
    const/high16 v0, 0x435c0000    # 220.0f

    .line 227
    iput v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionAnimationTime:F

    .line 229
    :goto_1
    iget-boolean v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->animatingTransition:Z

    if-eqz v0, :cond_c

    .line 230
    iput p2, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    .line 232
    :cond_c
    iput-boolean v6, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->animatingTransition:Z

    .line 233
    iput p1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    .line 234
    iget p2, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    iput p2, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->savedTransitionProgress:F

    .line 235
    iput v4, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    goto :goto_3

    :cond_d
    :goto_2
    return v3

    :cond_e
    if-ne v0, p1, :cond_f

    return v3

    .line 240
    :cond_f
    iput-boolean v3, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->animatingTransition:Z

    .line 241
    iput p1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->nextIcon:I

    iput p1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->currentIcon:I

    .line 242
    iget p2, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    iput p2, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->savedTransitionProgress:F

    .line 243
    iput v1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->transitionProgress:F

    :goto_3
    if-eq p1, v2, :cond_10

    if-ne p1, v5, :cond_11

    :cond_10
    const/high16 p1, 0x42e00000    # 112.0f

    .line 246
    iput p1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadRadOffset:F

    .line 247
    iput v4, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->animatedDownloadProgress:F

    .line 248
    iput v4, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadProgressAnimationStart:F

    .line 249
    iput v4, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadProgressTime:F

    .line 251
    :cond_11
    invoke-virtual {p0}, Lorg/telegram/ui/Components/MediaActionDrawable;->invalidateSelf()V

    return v6
.end method

.method public setMini(Z)V
    .locals 0

    .line 172
    iput-boolean p1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->isMini:Z

    .line 173
    iget-object p0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->paint:Landroid/graphics/Paint;

    if-eqz p1, :cond_0

    const/high16 p1, 0x40000000    # 2.0f

    goto :goto_0

    :cond_0
    const/high16 p1, 0x40400000    # 3.0f

    :goto_0
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    int-to-float p1, p1

    invoke-virtual {p0, p1}, Landroid/graphics/Paint;->setStrokeWidth(F)V

    return-void
.end method

.method public setOverrideAlpha(F)V
    .locals 0

    .line 143
    iput p1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->overrideAlpha:F

    return-void
.end method

.method public setProgress(FZ)V
    .locals 1

    .line 264
    iget v0, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadProgress:F

    cmpl-float v0, v0, p1

    if-nez v0, :cond_0

    return-void

    :cond_0
    if-nez p2, :cond_1

    .line 268
    iput p1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->animatedDownloadProgress:F

    .line 269
    iput p1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadProgressAnimationStart:F

    goto :goto_0

    .line 271
    :cond_1
    iget p2, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->animatedDownloadProgress:F

    cmpl-float p2, p2, p1

    if-lez p2, :cond_2

    .line 272
    iput p1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->animatedDownloadProgress:F

    .line 274
    :cond_2
    iget p2, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->animatedDownloadProgress:F

    iput p2, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadProgressAnimationStart:F

    .line 276
    :goto_0
    iput p1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadProgress:F

    const/4 p1, 0x0

    .line 277
    iput p1, p0, Lorg/telegram/ui/Components/MediaActionDrawable;->downloadProgressTime:F

    .line 278
    invoke-virtual {p0}, Lorg/telegram/ui/Components/MediaActionDrawable;->invalidateSelf()V

    return-void
.end method
