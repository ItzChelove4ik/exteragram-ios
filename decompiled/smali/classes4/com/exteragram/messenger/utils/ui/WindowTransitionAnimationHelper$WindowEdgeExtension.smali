.class public final Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "WindowEdgeExtension"
.end annotation


# instance fields
.field private captured:Z

.field private final node:Landroid/graphics/RenderNode;


# direct methods
.method public constructor <init>()V
    .locals 1

    .line 77
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 81
    const-string v0, "windowEdgeExtension"

    invoke-static {v0}, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0;->m(Ljava/lang/String;)Landroid/graphics/RenderNode;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;->node:Landroid/graphics/RenderNode;

    return-void
.end method


# virtual methods
.method public capture(Landroid/view/View;Z)Z
    .locals 5

    .line 89
    invoke-virtual {p1}, Landroid/view/View;->getWidth()I

    move-result v0

    .line 90
    invoke-virtual {p1}, Landroid/view/View;->getHeight()I

    move-result v1

    const/4 v2, 0x0

    if-lez v0, :cond_2

    if-gtz v1, :cond_0

    goto :goto_1

    .line 94
    :cond_0
    iget-object v3, p0, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;->node:Landroid/graphics/RenderNode;

    const/4 v4, 0x1

    invoke-virtual {v3, v2, v2, v4, v1}, Landroid/graphics/RenderNode;->setPosition(IIII)Z

    .line 95
    iget-object v2, p0, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;->node:Landroid/graphics/RenderNode;

    const/4 v3, 0x0

    invoke-virtual {v2, v3}, Landroid/graphics/RenderNode;->setPivotX(F)Z

    .line 96
    iget-object v2, p0, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;->node:Landroid/graphics/RenderNode;

    invoke-virtual {v2, v3}, Landroid/graphics/RenderNode;->setPivotY(F)Z

    .line 97
    iget-object v2, p0, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;->node:Landroid/graphics/RenderNode;

    invoke-virtual {v2, v4, v1}, Landroid/graphics/RenderNode;->beginRecording(II)Landroid/graphics/RecordingCanvas;

    move-result-object v1

    if-eqz p2, :cond_1

    move p2, v3

    goto :goto_0

    :cond_1
    sub-int/2addr v0, v4

    neg-int p2, v0

    int-to-float p2, p2

    .line 99
    :goto_0
    :try_start_0
    invoke-virtual {v1, p2, v3}, Landroid/graphics/RecordingCanvas;->translate(FF)V

    .line 100
    invoke-virtual {p1, v1}, Landroid/view/View;->draw(Landroid/graphics/Canvas;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 102
    iget-object p1, p0, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;->node:Landroid/graphics/RenderNode;

    invoke-virtual {p1}, Landroid/graphics/RenderNode;->endRecording()V

    .line 104
    iget-object p1, p0, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;->node:Landroid/graphics/RenderNode;

    const/4 p2, 0x0

    invoke-virtual {p1, v4, p2}, Landroid/graphics/RenderNode;->setUseCompositingLayer(ZLandroid/graphics/Paint;)Z

    .line 105
    iput-boolean v4, p0, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;->captured:Z

    return v4

    :catchall_0
    move-exception p1

    .line 102
    iget-object p0, p0, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;->node:Landroid/graphics/RenderNode;

    invoke-virtual {p0}, Landroid/graphics/RenderNode;->endRecording()V

    .line 103
    throw p1

    :cond_2
    :goto_1
    return v2
.end method

.method public draw(Landroid/graphics/Canvas;FFF)V
    .locals 2

    .line 110
    iget-boolean v0, p0, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;->captured:Z

    if-eqz v0, :cond_1

    const/4 v0, 0x0

    cmpg-float v1, p3, v0

    if-lez v1, :cond_1

    cmpg-float v0, p4, v0

    if-lez v0, :cond_1

    invoke-static {p1}, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline1;->m(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 113
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;->node:Landroid/graphics/RenderNode;

    const/high16 v1, 0x3f800000    # 1.0f

    div-float/2addr p3, v1

    invoke-virtual {v0, p3}, Landroid/graphics/RenderNode;->setScaleX(F)Z

    .line 114
    iget-object p3, p0, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;->node:Landroid/graphics/RenderNode;

    invoke-virtual {p3, p2}, Landroid/graphics/RenderNode;->setTranslationX(F)Z

    .line 115
    iget-object p2, p0, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;->node:Landroid/graphics/RenderNode;

    invoke-virtual {p2, p4}, Landroid/graphics/RenderNode;->setAlpha(F)Z

    .line 116
    iget-object p0, p0, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;->node:Landroid/graphics/RenderNode;

    invoke-virtual {p1, p0}, Landroid/graphics/Canvas;->drawRenderNode(Landroid/graphics/RenderNode;)V

    :cond_1
    :goto_0
    return-void
.end method

.method public isCaptured()Z
    .locals 0

    .line 85
    iget-boolean p0, p0, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;->captured:Z

    return p0
.end method

.method public release()V
    .locals 3

    .line 120
    iget-boolean v0, p0, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;->captured:Z

    if-nez v0, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x0

    .line 123
    iput-boolean v0, p0, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;->captured:Z

    .line 124
    iget-object v1, p0, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;->node:Landroid/graphics/RenderNode;

    const/4 v2, 0x0

    invoke-virtual {v1, v0, v2}, Landroid/graphics/RenderNode;->setUseCompositingLayer(ZLandroid/graphics/Paint;)Z

    .line 125
    iget-object p0, p0, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;->node:Landroid/graphics/RenderNode;

    invoke-virtual {p0}, Landroid/graphics/RenderNode;->discardDisplayList()V

    return-void
.end method
