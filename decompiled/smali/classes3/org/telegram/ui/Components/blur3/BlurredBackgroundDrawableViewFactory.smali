.class public Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private engine:Lorg/telegram/utils/glass/GlassEngine;

.field private isLiquidGlassEffectAllowed:Z

.field private linkedDrawables:Lme/vkryl/core/reference/ReferenceList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lme/vkryl/core/reference/ReferenceList<",
            "Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;",
            ">;"
        }
    .end annotation
.end field

.field private linkedViews:Lme/vkryl/core/reference/ReferenceList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lme/vkryl/core/reference/ReferenceList<",
            "Landroid/view/View;",
            ">;"
        }
    .end annotation
.end field

.field private outsetX:I

.field private outsetY:I

.field private parent:Landroid/view/ViewGroup;

.field private final source:Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;

.field private trackReattach:Z

.field private viewPositionWatcher:Lorg/telegram/ui/Components/chat/ViewPositionWatcher;


# direct methods
.method public static synthetic $r8$lambda$zQYw2Ior9IE1MD7jp9GiuMfrb8I(Ljava/lang/ref/WeakReference;Landroid/view/View;Landroid/graphics/RectF;)V
    .locals 1

    .line 125
    invoke-virtual {p0}, Ljava/lang/ref/Reference;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    if-nez p0, :cond_0

    return-void

    .line 129
    :cond_0
    iget v0, p2, Landroid/graphics/RectF;->left:F

    iget p2, p2, Landroid/graphics/RectF;->top:F

    invoke-virtual {p0, v0, p2}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->setSourceOffset(FF)V

    .line 130
    invoke-virtual {p1}, Landroid/view/View;->invalidate()V

    return-void
.end method

.method public constructor <init>(Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;)V
    .locals 0

    .line 25
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 26
    iput-object p1, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->source:Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;

    return-void
.end method

.method public constructor <init>(Lorg/telegram/ui/Components/chat/ViewPositionWatcher;Landroid/view/ViewGroup;Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;)V
    .locals 0

    .line 30
    invoke-direct {p0, p3}, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;-><init>(Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;)V

    .line 31
    invoke-virtual {p0, p1, p2}, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->setSourceRootView(Lorg/telegram/ui/Components/chat/ViewPositionWatcher;Landroid/view/ViewGroup;)V

    return-void
.end method


# virtual methods
.method public create()Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;
    .locals 1

    const/4 v0, 0x0

    .line 88
    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->create(Landroid/view/View;)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    move-result-object p0

    return-object p0
.end method

.method public create(Landroid/view/View;)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;
    .locals 1

    const/4 v0, 0x0

    .line 92
    invoke-virtual {p0, p1, v0}, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->create(Landroid/view/View;Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    move-result-object p0

    return-object p0
.end method

.method public create(Landroid/view/View;Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;
    .locals 1

    const/4 v0, 0x0

    .line 100
    invoke-virtual {p0, p1, p2, v0}, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->create(Landroid/view/View;Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;Z)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    move-result-object p0

    return-object p0
.end method

.method public create(Landroid/view/View;Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;Z)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;
    .locals 7

    .line 104
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->source:Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;

    invoke-interface {v0}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;->createDrawable()Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    move-result-object v0

    .line 105
    iget-boolean v1, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->isLiquidGlassEffectAllowed:Z

    if-eqz v1, :cond_0

    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v2, 0x21

    if-lt v1, v2, :cond_0

    .line 106
    instance-of v1, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawableRenderNode;

    if-eqz v1, :cond_0

    .line 107
    move-object v1, v0

    check-cast v1, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawableRenderNode;

    invoke-virtual {v1}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawableRenderNode;->setLiquidGlassEffectAllowed()V

    .line 111
    :cond_0
    invoke-virtual {v0, p2}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->setColorProvider(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    .line 112
    iget p2, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->outsetX:I

    iget v1, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->outsetY:I

    invoke-virtual {v0, p2, v1}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->setOutset(II)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    .line 114
    iget-object p2, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->linkedViews:Lme/vkryl/core/reference/ReferenceList;

    if-eqz p2, :cond_1

    if-eqz p1, :cond_1

    .line 115
    invoke-virtual {p2, p1}, Lme/vkryl/core/reference/ReferenceList;->add(Ljava/lang/Object;)Z

    .line 118
    :cond_1
    iget-object p2, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->engine:Lorg/telegram/utils/glass/GlassEngine;

    if-eqz p2, :cond_2

    if-eqz p1, :cond_2

    .line 119
    invoke-virtual {p2, p1, v0}, Lorg/telegram/utils/glass/GlassEngine;->registerDrawable(Landroid/view/View;Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;)V

    .line 122
    :cond_2
    iget-object p2, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->viewPositionWatcher:Lorg/telegram/ui/Components/chat/ViewPositionWatcher;

    if-eqz p2, :cond_3

    iget-object p2, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->parent:Landroid/view/ViewGroup;

    if-eqz p2, :cond_3

    if-eqz p1, :cond_3

    .line 123
    new-instance p2, Ljava/lang/ref/WeakReference;

    invoke-direct {p2, v0}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    .line 124
    iget-object v1, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->viewPositionWatcher:Lorg/telegram/ui/Components/chat/ViewPositionWatcher;

    iget-object v3, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->parent:Landroid/view/ViewGroup;

    new-instance v4, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory$$ExternalSyntheticLambda0;

    invoke-direct {v4, p2}, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory$$ExternalSyntheticLambda0;-><init>(Ljava/lang/ref/WeakReference;)V

    iget-boolean v6, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->trackReattach:Z

    move-object v2, p1

    move v5, p3

    invoke-virtual/range {v1 .. v6}, Lorg/telegram/ui/Components/chat/ViewPositionWatcher;->subscribe(Landroid/view/View;Landroid/view/ViewGroup;Lorg/telegram/ui/Components/chat/ViewPositionWatcher$OnChangedListener;ZZ)V

    .line 134
    :cond_3
    iget-object p0, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->linkedDrawables:Lme/vkryl/core/reference/ReferenceList;

    if-eqz p0, :cond_4

    .line 135
    invoke-virtual {p0, v0}, Lme/vkryl/core/reference/ReferenceList;->add(Ljava/lang/Object;)Z

    :cond_4
    return-object v0
.end method

.method public create(Landroid/view/View;Z)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;
    .locals 1

    const/4 v0, 0x0

    .line 96
    invoke-virtual {p0, p1, v0, p2}, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->create(Landroid/view/View;Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;Z)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    move-result-object p0

    return-object p0
.end method

.method public invalidateAllLinkedViews()V
    .locals 1

    .line 73
    iget-object p0, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->linkedViews:Lme/vkryl/core/reference/ReferenceList;

    if-eqz p0, :cond_0

    .line 74
    invoke-virtual {p0}, Lme/vkryl/core/reference/ReferenceList;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/View;

    .line 75
    invoke-virtual {v0}, Landroid/view/View;->invalidate()V

    goto :goto_0

    :cond_0
    return-void
.end method

.method public setGlassEngine(Lorg/telegram/utils/glass/GlassEngine;)V
    .locals 0

    .line 40
    iput-object p1, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->engine:Lorg/telegram/utils/glass/GlassEngine;

    return-void
.end method

.method public setLinkedDrawablesRef(Lme/vkryl/core/reference/ReferenceList;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lme/vkryl/core/reference/ReferenceList<",
            "Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;",
            ">;)V"
        }
    .end annotation

    .line 69
    iput-object p1, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->linkedDrawables:Lme/vkryl/core/reference/ReferenceList;

    return-void
.end method

.method public setLinkedViewsRef(Lme/vkryl/core/reference/ReferenceList;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lme/vkryl/core/reference/ReferenceList<",
            "Landroid/view/View;",
            ">;)V"
        }
    .end annotation

    .line 65
    iput-object p1, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->linkedViews:Lme/vkryl/core/reference/ReferenceList;

    return-void
.end method

.method public setLiquidGlassEffectAllowed(Z)V
    .locals 0

    .line 84
    iput-boolean p1, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->isLiquidGlassEffectAllowed:Z

    return-void
.end method

.method public setOutset(I)V
    .locals 0

    .line 44
    invoke-virtual {p0, p1, p1}, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->setOutset(II)V

    return-void
.end method

.method public setOutset(II)V
    .locals 0

    .line 48
    iput p1, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->outsetX:I

    .line 49
    iput p2, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->outsetY:I

    return-void
.end method

.method public setSourceRootView(Lorg/telegram/ui/Components/chat/ViewPositionWatcher;Landroid/view/ViewGroup;)V
    .locals 0

    .line 35
    iput-object p1, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->viewPositionWatcher:Lorg/telegram/ui/Components/chat/ViewPositionWatcher;

    .line 36
    iput-object p2, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->parent:Landroid/view/ViewGroup;

    return-void
.end method

.method public setTrackReattach(Z)V
    .locals 0

    .line 60
    iput-boolean p1, p0, Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;->trackReattach:Z

    return-void
.end method
