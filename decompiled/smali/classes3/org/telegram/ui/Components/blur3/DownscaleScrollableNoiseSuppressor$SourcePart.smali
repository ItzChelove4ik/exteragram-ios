.class Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = "SourcePart"
.end annotation


# instance fields
.field lastHash:J

.field final position:Landroid/graphics/Rect;

.field final renderNode:Landroid/graphics/RenderNode;

.field final renderNodesForBlur:Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;

.field final renderNodesForGlass:Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;

.field final synthetic this$0:Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;


# direct methods
.method public static bridge synthetic -$$Nest$msetPosition(Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;Landroid/graphics/RectF;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;->setPosition(Landroid/graphics/RectF;)V

    return-void
.end method

.method private constructor <init>(Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;)V
    .locals 7

    .line 409
    iput-object p1, p0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;->this$0:Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    .line 403
    invoke-static {v0}, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0;->m(Ljava/lang/String;)Landroid/graphics/RenderNode;

    move-result-object v1

    iput-object v1, p0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;->renderNode:Landroid/graphics/RenderNode;

    .line 406
    new-instance v1, Landroid/graphics/Rect;

    invoke-direct {v1}, Landroid/graphics/Rect;-><init>()V

    iput-object v1, p0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;->position:Landroid/graphics/Rect;

    .line 410
    iget-boolean v1, p1, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;->isLiquidGlassEnabled:Z

    const/4 v2, 0x1

    const-string v3, "blur"

    const/4 v4, 0x0

    const/16 v5, 0x8

    if-eqz v1, :cond_0

    .line 411
    new-instance v0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;

    const-string v1, "glass"

    invoke-direct {v0, p1, v1, v4, v2}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;-><init>(Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;Ljava/lang/String;IZ)V

    iput-object v0, p0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;->renderNodesForGlass:Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;

    const/4 v1, 0x4

    .line 412
    invoke-virtual {v0, v1, v1}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;->setScale(II)V

    const/high16 v1, 0x40c00000    # 6.0f

    .line 413
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    invoke-static {}, Lorg/telegram/messenger/utils/RenderNodeEffects;->getSaturationX3RenderEffect()Landroid/graphics/RenderEffect;

    move-result-object v2

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;->setPrimaryEffectBlur(FLandroid/graphics/RenderEffect;)V

    .line 414
    new-instance v0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;

    invoke-direct {v0, p1, v3, v4}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;-><init>(Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;Ljava/lang/String;I)V

    iput-object v0, p0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;->renderNodesForBlur:Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;

    .line 415
    invoke-virtual {v0, v5, v5}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;->setScale(II)V

    const p0, 0x42195c29    # 38.34f

    .line 416
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result p0

    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;->setPrimaryEffectBlur(F)V

    return-void

    .line 417
    :cond_0
    invoke-static {p1}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;->-$$Nest$fgetsimpleMode(Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;)Z

    move-result v1

    const/high16 v6, 0x42200000    # 40.0f

    if-eqz v1, :cond_3

    .line 418
    new-instance v1, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;

    invoke-direct {v1, p1, v3, v4}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;-><init>(Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;Ljava/lang/String;I)V

    iput-object v1, p0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;->renderNodesForBlur:Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;

    .line 419
    iget-boolean p1, p1, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;->allowNoiseSuppress:Z

    const/16 v2, 0x10

    if-eqz p1, :cond_1

    move v3, v2

    goto :goto_0

    :cond_1
    move v3, v5

    :goto_0
    if-eqz p1, :cond_2

    move v5, v2

    :cond_2
    invoke-virtual {v1, v3, v5}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;->setScale(II)V

    .line 420
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result p1

    invoke-static {}, Lorg/telegram/messenger/utils/RenderNodeEffects;->getSaturationX3RenderEffect()Landroid/graphics/RenderEffect;

    move-result-object v2

    invoke-virtual {v1, p1, v2}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;->setPrimaryEffectBlur(FLandroid/graphics/RenderEffect;)V

    .line 421
    iput-object v0, p0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;->renderNodesForGlass:Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;

    return-void

    .line 423
    :cond_3
    new-instance v1, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;

    invoke-direct {v1, p1, v3, v2}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;-><init>(Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;Ljava/lang/String;I)V

    iput-object v1, p0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;->renderNodesForBlur:Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;

    .line 424
    invoke-virtual {v1, v5, v5}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;->setScale(II)V

    .line 425
    invoke-static {v6}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result p1

    invoke-virtual {v1, p1}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;->setPrimaryEffectBlur(F)V

    .line 426
    invoke-static {}, Lorg/telegram/messenger/utils/RenderNodeEffects;->getSaturationX3RenderEffect()Landroid/graphics/RenderEffect;

    move-result-object p1

    invoke-virtual {v1, v4, p1}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;->setSecondaryEffect(ILandroid/graphics/RenderEffect;)V

    .line 427
    iput-object v0, p0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;->renderNodesForGlass:Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;

    return-void
.end method

.method public synthetic constructor <init>(Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor-IA;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;-><init>(Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;)V

    return-void
.end method

.method private setPosition(Landroid/graphics/RectF;)V
    .locals 3

    .line 433
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;->position:Landroid/graphics/Rect;

    iget v1, p1, Landroid/graphics/RectF;->left:F

    const/16 v2, 0x10

    invoke-static {v1, v2}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;->-$$Nest$smroundDown(FI)I

    move-result v1

    iput v1, v0, Landroid/graphics/Rect;->left:I

    .line 434
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;->position:Landroid/graphics/Rect;

    iget v1, p1, Landroid/graphics/RectF;->top:F

    invoke-static {v1, v2}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;->-$$Nest$smroundDown(FI)I

    move-result v1

    iput v1, v0, Landroid/graphics/Rect;->top:I

    .line 435
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;->position:Landroid/graphics/Rect;

    iget v1, p1, Landroid/graphics/RectF;->right:F

    invoke-static {v1, v2}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;->roundUp(FI)I

    move-result v1

    iput v1, v0, Landroid/graphics/Rect;->right:I

    .line 436
    iget-object p0, p0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;->position:Landroid/graphics/Rect;

    iget p1, p1, Landroid/graphics/RectF;->bottom:F

    invoke-static {p1, v2}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;->roundUp(FI)I

    move-result p1

    iput p1, p0, Landroid/graphics/Rect;->bottom:I

    return-void
.end method


# virtual methods
.method public invalidate()V
    .locals 2

    .line 440
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;->renderNodesForGlass:Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;

    if-eqz v0, :cond_0

    .line 441
    iget-object v1, p0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;->renderNode:Landroid/graphics/RenderNode;

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;->invalidateRenderNodes(Landroid/graphics/RenderNode;)V

    .line 442
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;->renderNodesForBlur:Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;

    iget-object p0, p0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;->renderNodesForGlass:Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;

    invoke-static {p0}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;->-$$Nest$fgetrenderNodeRestored(Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;)[Landroid/graphics/RenderNode;

    move-result-object p0

    const/4 v1, 0x0

    aget-object p0, p0, v1

    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;->invalidateRenderNodes(Landroid/graphics/RenderNode;)V

    return-void

    .line 444
    :cond_0
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;->renderNodesForBlur:Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;

    iget-object p0, p0, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$SourcePart;->renderNode:Landroid/graphics/RenderNode;

    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor$DownscaledRenderNode;->invalidateRenderNodes(Landroid/graphics/RenderNode;)V

    return-void
.end method
