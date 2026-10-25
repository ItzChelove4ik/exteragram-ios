.class final Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0008\n\u0002\u0018\u0002\n\u0002\u0008\u0004\u0008\u0002\u0018\u00002\u00020\u0001B7\u0012\u0008\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\u000c\u0010\u0007\u001a\u0008\u0012\u0004\u0012\u00020\t0\u0008\u00a2\u0006\u0004\u0008\n\u0010\u000bJ\u0006\u0010+\u001a\u00020\tJ\u0006\u0010,\u001a\u00020\tJ\u000e\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00020#J\u000e\u0010/\u001a\u00020&2\u0006\u00100\u001a\u00020&J\u000e\u00101\u001a\u00020&2\u0006\u00102\u001a\u000203J\u0016\u00104\u001a\u00020\t2\u0006\u00102\u001a\u0002032\u0006\u00105\u001a\u00020&J\u0010\u00106\u001a\u00020\t2\u0006\u00102\u001a\u000203H\u0002R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u000c\u0010\rR\u001e\u0010\u0004\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u0008\u0012\u0004\u0012\u00020\t0\u0008X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u0010\u0010\u0011\"\u0004\u0008\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u0015\u0010\r\"\u0004\u0008\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u0019\u0010\r\"\u0004\u0008\u001a\u0010\u0017R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010!X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\'\u001a\u00020(X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00067"
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;",
        "",
        "original",
        "",
        "format",
        "Lkotlin/Function1;",
        "",
        "onTransitionUpdate",
        "Lkotlin/Function0;",
        "",
        "<init>",
        "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V",
        "getOriginal",
        "()Ljava/lang/String;",
        "view",
        "Landroid/widget/TextView;",
        "getView",
        "()Landroid/widget/TextView;",
        "setView",
        "(Landroid/widget/TextView;)V",
        "language",
        "getLanguage",
        "setLanguage",
        "(Ljava/lang/String;)V",
        "translation",
        "getTranslation",
        "setTranslation",
        "shownText",
        "loadingPath",
        "Lorg/telegram/ui/Components/LinkPath;",
        "loadingDrawable",
        "Lorg/telegram/ui/Components/LoadingDrawable;",
        "loadingLayout",
        "Landroid/text/Layout;",
        "loading",
        "",
        "oldLayout",
        "oldHeight",
        "",
        "transitionProgress",
        "",
        "transitionAnimator",
        "Landroid/animation/ValueAnimator;",
        "startLoading",
        "stopLoading",
        "show",
        "translated",
        "getHeight",
        "measuredHeight",
        "beforeDraw",
        "canvas",
        "Landroid/graphics/Canvas;",
        "afterDraw",
        "saveCount",
        "drawLoading",
        "TMessagesProj"
    }
    k = 0x1
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation


# instance fields
.field private final format:Lkotlin/jvm/functions/Function1;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlin/jvm/functions/Function1<",
            "Ljava/lang/String;",
            "Ljava/lang/CharSequence;",
            ">;"
        }
    .end annotation
.end field

.field private language:Ljava/lang/String;

.field private loading:Z

.field private final loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

.field private loadingLayout:Landroid/text/Layout;

.field private final loadingPath:Lorg/telegram/ui/Components/LinkPath;

.field private oldHeight:I

.field private oldLayout:Landroid/text/Layout;

.field private final onTransitionUpdate:Lkotlin/jvm/functions/Function0;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lkotlin/jvm/functions/Function0<",
            "Lkotlin/Unit;",
            ">;"
        }
    .end annotation
.end field

.field private final original:Ljava/lang/String;

.field private shownText:Ljava/lang/String;

.field private transitionAnimator:Landroid/animation/ValueAnimator;

.field private transitionProgress:F

.field private translation:Ljava/lang/String;

.field private view:Landroid/widget/TextView;


# direct methods
.method public static $r8$lambda$JRgU_sneSrKGk0HLUPOQr7eYGE4(Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;Landroid/widget/TextView;Landroid/animation/ValueAnimator;)V
    .locals 0

    .line 1300
    invoke-virtual {p2}, Landroid/animation/ValueAnimator;->getAnimatedValue()Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/Float;

    invoke-virtual {p2}, Ljava/lang/Float;->floatValue()F

    move-result p2

    iput p2, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->transitionProgress:F

    .line 1301
    invoke-virtual {p1}, Landroid/view/View;->requestLayout()V

    .line 1302
    invoke-virtual {p1}, Landroid/view/View;->invalidate()V

    .line 1303
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->onTransitionUpdate:Lkotlin/jvm/functions/Function0;

    invoke-interface {p0}, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Ljava/lang/String;",
            "+",
            "Ljava/lang/CharSequence;",
            ">;",
            "Lkotlin/jvm/functions/Function0<",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    .line 1248
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 1249
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->original:Ljava/lang/String;

    .line 1250
    iput-object p2, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->format:Lkotlin/jvm/functions/Function1;

    .line 1251
    iput-object p3, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->onTransitionUpdate:Lkotlin/jvm/functions/Function0;

    .line 1256
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->shownText:Ljava/lang/String;

    .line 1258
    new-instance p1, Lorg/telegram/ui/Components/LinkPath;

    const/4 p2, 0x1

    invoke-direct {p1, p2}, Lorg/telegram/ui/Components/LinkPath;-><init>(Z)V

    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->loadingPath:Lorg/telegram/ui/Components/LinkPath;

    .line 1259
    new-instance p3, Lorg/telegram/ui/Components/LoadingDrawable;

    invoke-direct {p3}, Lorg/telegram/ui/Components/LoadingDrawable;-><init>()V

    .line 1260
    invoke-virtual {p3, p1}, Lorg/telegram/ui/Components/LoadingDrawable;->usePath(Landroid/graphics/Path;)V

    .line 1261
    invoke-virtual {p3, p2}, Lorg/telegram/ui/Components/LoadingDrawable;->setAppearByGradient(Z)V

    const/high16 p1, 0x40800000    # 4.0f

    .line 1262
    invoke-virtual {p3, p1}, Lorg/telegram/ui/Components/LoadingDrawable;->setRadiiDp(F)V

    .line 1259
    iput-object p3, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    const/high16 p1, 0x3f800000    # 1.0f

    .line 1269
    iput p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->transitionProgress:F

    return-void
.end method

.method public static final synthetic access$setOldLayout$p(Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;Landroid/text/Layout;)V
    .locals 0

    .line 1248
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->oldLayout:Landroid/text/Layout;

    return-void
.end method

.method public static final synthetic access$setTransitionAnimator$p(Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;Landroid/animation/ValueAnimator;)V
    .locals 0

    .line 1248
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->transitionAnimator:Landroid/animation/ValueAnimator;

    return-void
.end method

.method public static final synthetic access$setTransitionProgress$p(Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;F)V
    .locals 0

    .line 1248
    iput p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->transitionProgress:F

    return-void
.end method

.method private final drawLoading(Landroid/graphics/Canvas;)V
    .locals 7

    .line 1342
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->view:Landroid/widget/TextView;

    if-nez v0, :cond_0

    goto :goto_0

    .line 1343
    :cond_0
    iget-boolean v1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->loading:Z

    if-nez v1, :cond_1

    goto :goto_0

    .line 1346
    :cond_1
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    invoke-virtual {v1}, Lorg/telegram/ui/Components/LoadingDrawable;->isDisappeared()Z

    move-result v1

    const/4 v2, 0x0

    if-eqz v1, :cond_2

    .line 1347
    iput-boolean v2, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->loading:Z

    return-void

    .line 1350
    :cond_2
    invoke-virtual {v0}, Landroid/widget/TextView;->getLayout()Landroid/text/Layout;

    move-result-object v1

    if-nez v1, :cond_3

    :goto_0
    return-void

    .line 1351
    :cond_3
    iget-object v3, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->loadingLayout:Landroid/text/Layout;

    if-eq v1, v3, :cond_4

    .line 1352
    iput-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->loadingLayout:Landroid/text/Layout;

    .line 1353
    iget-object v3, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->loadingPath:Lorg/telegram/ui/Components/LinkPath;

    invoke-virtual {v3}, Lorg/telegram/ui/Components/CornerPath;->rewind()V

    .line 1354
    iget-object v3, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->loadingPath:Lorg/telegram/ui/Components/LinkPath;

    invoke-virtual {v0}, Landroid/widget/TextView;->getTotalPaddingLeft()I

    move-result v4

    int-to-float v4, v4

    invoke-virtual {v0}, Landroid/widget/TextView;->getTotalPaddingTop()I

    move-result v5

    int-to-float v5, v5

    invoke-virtual {v3, v1, v2, v4, v5}, Lorg/telegram/ui/Components/LinkPath;->setCurrentLayout(Landroid/text/Layout;IFF)V

    .line 1355
    invoke-virtual {v1}, Landroid/text/Layout;->getText()Ljava/lang/CharSequence;

    move-result-object v3

    invoke-interface {v3}, Ljava/lang/CharSequence;->length()I

    move-result v3

    iget-object v4, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->loadingPath:Lorg/telegram/ui/Components/LinkPath;

    invoke-virtual {v1, v2, v3, v4}, Landroid/text/Layout;->getSelectionPath(IILandroid/graphics/Path;)V

    .line 1356
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    invoke-virtual {v1}, Lorg/telegram/ui/Components/LoadingDrawable;->updateBounds()V

    .line 1358
    :cond_4
    invoke-virtual {v0}, Landroid/widget/TextView;->getCurrentTextColor()I

    move-result v1

    .line 1359
    iget-object v2, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    const v3, 0x3d4ccccd    # 0.05f

    .line 1360
    invoke-static {v1, v3}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v3

    const v4, 0x3e19999a    # 0.15f

    .line 1361
    invoke-static {v1, v4}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v4

    const v5, 0x3dcccccd    # 0.1f

    .line 1362
    invoke-static {v1, v5}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v5

    const v6, 0x3e99999a    # 0.3f

    .line 1363
    invoke-static {v1, v6}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v1

    .line 1359
    invoke-virtual {v2, v3, v4, v5, v1}, Lorg/telegram/ui/Components/LoadingDrawable;->setColors(IIII)V

    .line 1365
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/LoadingDrawable;->draw(Landroid/graphics/Canvas;)V

    .line 1366
    invoke-virtual {v0}, Landroid/view/View;->invalidate()V

    return-void
.end method


# virtual methods
.method public final afterDraw(Landroid/graphics/Canvas;I)V
    .locals 0

    if-ltz p2, :cond_0

    .line 1336
    invoke-virtual {p1, p2}, Landroid/graphics/Canvas;->restoreToCount(I)V

    .line 1338
    :cond_0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->drawLoading(Landroid/graphics/Canvas;)V

    return-void
.end method

.method public final beforeDraw(Landroid/graphics/Canvas;)I
    .locals 10

    .line 1322
    iget-object v7, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->view:Landroid/widget/TextView;

    const/4 v2, -0x1

    if-nez v7, :cond_0

    return v2

    .line 1323
    :cond_0
    iget-object v8, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->oldLayout:Landroid/text/Layout;

    if-nez v8, :cond_1

    return v2

    .line 1324
    :cond_1
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    .line 1325
    invoke-virtual {v7}, Landroid/view/View;->getWidth()I

    move-result v2

    invoke-virtual {v7}, Landroid/view/View;->getHeight()I

    move-result v3

    const/4 v4, 0x0

    invoke-virtual {p1, v4, v4, v2, v3}, Landroid/graphics/Canvas;->clipRect(IIII)Z

    .line 1326
    invoke-virtual {v7}, Landroid/widget/TextView;->getTotalPaddingLeft()I

    move-result v2

    int-to-float v2, v2

    invoke-virtual {v7}, Landroid/widget/TextView;->getTotalPaddingTop()I

    move-result v3

    int-to-float v3, v3

    invoke-virtual {p1, v2, v3}, Landroid/graphics/Canvas;->translate(FF)V

    .line 1327
    invoke-virtual {v8}, Landroid/text/Layout;->getWidth()I

    move-result v2

    int-to-float v4, v2

    invoke-virtual {v8}, Landroid/text/Layout;->getHeight()I

    move-result v2

    int-to-float v5, v2

    const/high16 v2, 0x3f800000    # 1.0f

    iget v3, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->transitionProgress:F

    sub-float/2addr v2, v3

    const/high16 v9, 0x437f0000    # 255.0f

    mul-float/2addr v2, v9

    float-to-int v6, v2

    const/4 v2, 0x0

    const/4 v3, 0x0

    move-object v1, p1

    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->saveLayerAlpha(FFFFI)I

    .line 1328
    invoke-virtual {v8, p1}, Landroid/text/Layout;->draw(Landroid/graphics/Canvas;)V

    .line 1329
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    .line 1330
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    .line 1331
    invoke-virtual {v7}, Landroid/view/View;->getWidth()I

    move-result v2

    int-to-float v3, v2

    invoke-virtual {v7}, Landroid/view/View;->getHeight()I

    move-result v2

    int-to-float v4, v2

    iget v0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->transitionProgress:F

    mul-float/2addr v9, v0

    float-to-int v5, v9

    const/4 v1, 0x0

    const/4 v2, 0x0

    move-object v0, p1

    invoke-virtual/range {v0 .. v5}, Landroid/graphics/Canvas;->saveLayerAlpha(FFFFI)I

    move-result v0

    return v0
.end method

.method public final getHeight(I)I
    .locals 1

    .line 1319
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->oldLayout:Landroid/text/Layout;

    if-nez v0, :cond_0

    return p1

    :cond_0
    iget v0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->oldHeight:I

    iget p0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->transitionProgress:F

    invoke-static {v0, p1, p0}, Lorg/telegram/messenger/AndroidUtilities;->lerp(IIF)I

    move-result p0

    return p0
.end method

.method public final getLanguage()Ljava/lang/String;
    .locals 0

    .line 1254
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->language:Ljava/lang/String;

    return-object p0
.end method

.method public final getOriginal()Ljava/lang/String;
    .locals 0

    .line 1249
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->original:Ljava/lang/String;

    return-object p0
.end method

.method public final getTranslation()Ljava/lang/String;
    .locals 0

    .line 1255
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->translation:Ljava/lang/String;

    return-object p0
.end method

.method public final getView()Landroid/widget/TextView;
    .locals 0

    .line 1253
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->view:Landroid/widget/TextView;

    return-object p0
.end method

.method public final setLanguage(Ljava/lang/String;)V
    .locals 0

    .line 1254
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->language:Ljava/lang/String;

    return-void
.end method

.method public final setTranslation(Ljava/lang/String;)V
    .locals 0

    .line 1255
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->translation:Ljava/lang/String;

    return-void
.end method

.method public final setView(Landroid/widget/TextView;)V
    .locals 0

    .line 1253
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->view:Landroid/widget/TextView;

    return-void
.end method

.method public final show(Z)V
    .locals 3

    .line 1285
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->view:Landroid/widget/TextView;

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    if-eqz p1, :cond_1

    .line 1286
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->translation:Ljava/lang/String;

    if-nez p1, :cond_2

    :cond_1
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->original:Ljava/lang/String;

    .line 1287
    :cond_2
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->shownText:Ljava/lang/String;

    invoke-static {p1, v1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_3

    :goto_0
    return-void

    .line 1290
    :cond_3
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->shownText:Ljava/lang/String;

    .line 1291
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->transitionAnimator:Landroid/animation/ValueAnimator;

    if-eqz v1, :cond_4

    invoke-virtual {v1}, Landroid/animation/ValueAnimator;->cancel()V

    .line 1292
    :cond_4
    invoke-virtual {v0}, Landroid/widget/TextView;->getLayout()Landroid/text/Layout;

    move-result-object v1

    iput-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->oldLayout:Landroid/text/Layout;

    .line 1293
    invoke-virtual {v0}, Landroid/view/View;->getHeight()I

    move-result v1

    iput v1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->oldHeight:I

    .line 1294
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->format:Lkotlin/jvm/functions/Function1;

    invoke-interface {v1, p1}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/CharSequence;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    const/4 p1, 0x0

    .line 1295
    iput p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->transitionProgress:F

    const/4 p1, 0x2

    .line 1296
    new-array p1, p1, [F

    fill-array-data p1, :array_0

    invoke-static {p1}, Landroid/animation/ValueAnimator;->ofFloat([F)Landroid/animation/ValueAnimator;

    move-result-object p1

    const-wide/16 v1, 0x15e

    .line 1297
    invoke-virtual {p1, v1, v2}, Landroid/animation/ValueAnimator;->setDuration(J)Landroid/animation/ValueAnimator;

    .line 1298
    sget-object v1, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    invoke-virtual {p1, v1}, Landroid/animation/ValueAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)V

    .line 1299
    new-instance v1, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0, v0}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;Landroid/widget/TextView;)V

    invoke-virtual {p1, v1}, Landroid/animation/ValueAnimator;->addUpdateListener(Landroid/animation/ValueAnimator$AnimatorUpdateListener;)V

    .line 1305
    new-instance v1, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText$show$1$2;

    invoke-direct {v1, p0, v0}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText$show$1$2;-><init>(Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;Landroid/widget/TextView;)V

    invoke-virtual {p1, v1}, Landroid/animation/Animator;->addListener(Landroid/animation/Animator$AnimatorListener;)V

    .line 1314
    invoke-virtual {p1}, Landroid/animation/ValueAnimator;->start()V

    .line 1296
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->transitionAnimator:Landroid/animation/ValueAnimator;

    return-void

    nop

    :array_0
    .array-data 4
        0x0
        0x3f800000    # 1.0f
    .end array-data
.end method

.method public final startLoading()V
    .locals 1

    const/4 v0, 0x1

    .line 1273
    iput-boolean v0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->loading:Z

    .line 1274
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/LoadingDrawable;->reset()V

    .line 1275
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/LoadingDrawable;->resetDisappear()V

    .line 1276
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->view:Landroid/widget/TextView;

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    :cond_0
    return-void
.end method

.method public final stopLoading()V
    .locals 1

    .line 1280
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/LoadingDrawable;->disappear()V

    .line 1281
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->view:Landroid/widget/TextView;

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    :cond_0
    return-void
.end method
