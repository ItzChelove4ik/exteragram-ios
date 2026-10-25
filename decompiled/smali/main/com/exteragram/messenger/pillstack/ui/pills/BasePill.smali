.class public abstract Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;
.super Landroid/widget/FrameLayout;
.source "SourceFile"


# static fields
.field private static final globalLastUpdateTimes:Landroid/util/SparseArray;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/SparseArray<",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private final autoRefreshRunnable:Ljava/lang/Runnable;

.field protected loading:Z

.field protected loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

.field protected loadingTargetView:Landroid/view/View;

.field private final rectF:Landroid/graphics/RectF;

.field protected resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

.field private stackVisible:Z


# direct methods
.method public static synthetic $r8$lambda$cBJm39agtpK7S37SSBb-izCrNRA(Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->lambda$new$0()V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 1

    .line 45
    new-instance v0, Landroid/util/SparseArray;

    invoke-direct {v0}, Landroid/util/SparseArray;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->globalLastUpdateTimes:Landroid/util/SparseArray;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V
    .locals 1

    .line 55
    invoke-direct {p0, p1}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    .line 41
    new-instance p1, Landroid/graphics/RectF;

    invoke-direct {p1}, Landroid/graphics/RectF;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->rectF:Landroid/graphics/RectF;

    const/4 p1, 0x1

    .line 47
    iput-boolean p1, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->stackVisible:Z

    .line 49
    new-instance p1, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill$$ExternalSyntheticLambda0;

    invoke-direct {p1, p0}, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;)V

    iput-object p1, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->autoRefreshRunnable:Ljava/lang/Runnable;

    .line 56
    iput-object p2, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    .line 57
    new-instance p1, Landroid/widget/FrameLayout$LayoutParams;

    .line 60
    sget-boolean p2, Lorg/telegram/messenger/LocaleController;->isRTL:Z

    if-eqz p2, :cond_0

    const/4 p2, 0x3

    goto :goto_0

    :cond_0
    const/4 p2, 0x5

    :goto_0
    or-int/lit8 p2, p2, 0x10

    const/4 v0, -0x2

    invoke-direct {p1, v0, v0, p2}, Landroid/widget/FrameLayout$LayoutParams;-><init>(III)V

    .line 57
    invoke-virtual {p0, p1}, Landroid/view/View;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    const/4 p1, 0x0

    .line 62
    invoke-virtual {p0, p1}, Landroid/view/ViewGroup;->setClipChildren(Z)V

    .line 63
    invoke-virtual {p0, p1}, Landroid/view/ViewGroup;->setClipToPadding(Z)V

    return-void
.end method

.method private synthetic lambda$new$0()V
    .locals 1

    const/4 v0, 0x0

    .line 50
    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->onUpdateData(Z)V

    .line 51
    invoke-direct {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->scheduleNextUpdate()V

    return-void
.end method

.method private scheduleNextUpdate()V
    .locals 4

    .line 94
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->autoRefreshRunnable:Ljava/lang/Runnable;

    invoke-virtual {p0, v0}, Landroid/view/View;->removeCallbacks(Ljava/lang/Runnable;)Z

    .line 95
    iget-boolean v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->stackVisible:Z

    if-nez v0, :cond_0

    goto :goto_0

    .line 98
    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->getRefreshInterval()J

    move-result-wide v0

    const-wide/16 v2, 0x0

    cmp-long v2, v0, v2

    if-lez v2, :cond_1

    .line 100
    iget-object v2, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->autoRefreshRunnable:Ljava/lang/Runnable;

    invoke-virtual {p0, v2, v0, v1}, Landroid/view/View;->postDelayed(Ljava/lang/Runnable;J)Z

    :cond_1
    :goto_0
    return-void
.end method


# virtual methods
.method public animateSizeChange()V
    .locals 3

    .line 186
    invoke-virtual {p0}, Landroid/view/View;->isLaidOut()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Landroid/view/View;->getVisibility()I

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 189
    :cond_0
    invoke-virtual {p0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    invoke-interface {v0}, Landroid/view/ViewParent;->getParent()Landroid/view/ViewParent;

    move-result-object v0

    instance-of v0, v0, Landroid/view/ViewGroup;

    if-eqz v0, :cond_1

    .line 190
    invoke-virtual {p0}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object p0

    invoke-interface {p0}, Landroid/view/ViewParent;->getParent()Landroid/view/ViewParent;

    move-result-object p0

    check-cast p0, Landroid/view/ViewGroup;

    new-instance v0, Landroid/transition/TransitionSet;

    invoke-direct {v0}, Landroid/transition/TransitionSet;-><init>()V

    new-instance v1, Landroid/transition/ChangeBounds;

    invoke-direct {v1}, Landroid/transition/ChangeBounds;-><init>()V

    .line 191
    invoke-virtual {v0, v1}, Landroid/transition/TransitionSet;->addTransition(Landroid/transition/Transition;)Landroid/transition/TransitionSet;

    move-result-object v0

    const-wide/16 v1, 0x12c

    .line 192
    invoke-virtual {v0, v1, v2}, Landroid/transition/TransitionSet;->setDuration(J)Landroid/transition/TransitionSet;

    move-result-object v0

    sget-object v1, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    .line 193
    invoke-virtual {v0, v1}, Landroid/transition/TransitionSet;->setInterpolator(Landroid/animation/TimeInterpolator;)Landroid/transition/TransitionSet;

    move-result-object v0

    .line 190
    invoke-static {p0, v0}, Landroid/transition/TransitionManager;->beginDelayedTransition(Landroid/view/ViewGroup;Landroid/transition/Transition;)V

    :cond_1
    :goto_0
    return-void
.end method

.method public dispatchDraw(Landroid/graphics/Canvas;)V
    .locals 5

    .line 223
    invoke-super {p0, p1}, Landroid/view/View;->dispatchDraw(Landroid/graphics/Canvas;)V

    .line 224
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    if-eqz v0, :cond_3

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->getAlpha()I

    move-result v0

    if-gtz v0, :cond_0

    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/LoadingDrawable;->isDisappearing()Z

    move-result v0

    if-nez v0, :cond_3

    .line 225
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->loadingTargetView:Landroid/view/View;

    if-eqz v0, :cond_1

    goto :goto_0

    :cond_1
    move-object v0, p0

    .line 226
    :goto_0
    iget-object v1, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->rectF:Landroid/graphics/RectF;

    invoke-virtual {v0}, Landroid/view/View;->getLeft()I

    move-result v2

    int-to-float v2, v2

    invoke-virtual {v0}, Landroid/view/View;->getTop()I

    move-result v3

    int-to-float v3, v3

    invoke-virtual {v0}, Landroid/view/View;->getRight()I

    move-result v4

    int-to-float v4, v4

    invoke-virtual {v0}, Landroid/view/View;->getBottom()I

    move-result v0

    int-to-float v0, v0

    invoke-virtual {v1, v2, v3, v4, v0}, Landroid/graphics/RectF;->set(FFFF)V

    .line 227
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    iget-boolean v1, v0, Lorg/telegram/ui/Components/LoadingDrawable;->stroke:Z

    if-eqz v1, :cond_2

    .line 228
    iget-object v0, v0, Lorg/telegram/ui/Components/LoadingDrawable;->strokePaint:Landroid/graphics/Paint;

    invoke-virtual {v0}, Landroid/graphics/Paint;->getStrokeWidth()F

    move-result v0

    const/high16 v1, 0x40000000    # 2.0f

    div-float/2addr v0, v1

    .line 229
    iget-object v1, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->rectF:Landroid/graphics/RectF;

    invoke-virtual {v1, v0, v0}, Landroid/graphics/RectF;->inset(FF)V

    .line 231
    :cond_2
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    iget-object v1, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->rectF:Landroid/graphics/RectF;

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/LoadingDrawable;->setBounds(Landroid/graphics/RectF;)V

    .line 232
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    invoke-virtual {v0, p1}, Lorg/telegram/ui/Components/LoadingDrawable;->draw(Landroid/graphics/Canvas;)V

    .line 233
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    :cond_3
    return-void
.end method

.method public abstract getPillId()I
.end method

.method public abstract getRefreshInterval()J
.end method

.method public getThemedColor(I)I
    .locals 0

    .line 214
    iget-object p0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {p1, p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    return p0
.end method

.method public getThemedColor(IF)I
    .locals 0

    .line 218
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->getThemedColor(I)I

    move-result p0

    invoke-static {p0, p2}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result p0

    return p0
.end method

.method public isRefreshDue()Z
    .locals 8

    .line 105
    invoke-virtual {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->getRefreshInterval()J

    move-result-wide v0

    const-wide/16 v2, 0x0

    cmp-long v4, v0, v2

    const/4 v5, 0x1

    if-gtz v4, :cond_0

    return v5

    .line 109
    :cond_0
    sget-object v4, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->globalLastUpdateTimes:Landroid/util/SparseArray;

    invoke-virtual {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->getPillId()I

    move-result p0

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v6

    invoke-virtual {v4, p0, v6}, Landroid/util/SparseArray;->get(ILjava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Long;

    invoke-virtual {p0}, Ljava/lang/Long;->longValue()J

    move-result-wide v6

    cmp-long p0, v6, v2

    if-eqz p0, :cond_2

    .line 110
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v2

    sub-long/2addr v2, v6

    cmp-long p0, v2, v0

    if-ltz p0, :cond_1

    goto :goto_0

    :cond_1
    const/4 p0, 0x0

    return p0

    :cond_2
    :goto_0
    return v5
.end method

.method public markDataUpdated()V
    .locals 4

    .line 162
    sget-object v0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->globalLastUpdateTimes:Landroid/util/SparseArray;

    invoke-virtual {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->getPillId()I

    move-result v1

    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v2

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-virtual {v0, v1, v2}, Landroid/util/SparseArray;->put(ILjava/lang/Object;)V

    .line 163
    invoke-direct {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->scheduleNextUpdate()V

    return-void
.end method

.method public onAttachedToWindow()V
    .locals 9

    .line 115
    invoke-super {p0}, Landroid/view/View;->onAttachedToWindow()V

    .line 116
    invoke-virtual {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->getRefreshInterval()J

    move-result-wide v0

    const-wide/16 v2, 0x0

    cmp-long v4, v0, v2

    if-gtz v4, :cond_0

    goto :goto_0

    .line 120
    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->getPillId()I

    move-result v4

    invoke-static {v4}, Lcom/exteragram/messenger/pillstack/core/PillStackConfig;->checkAndClearPendingUpdate(I)Z

    move-result v4

    if-eqz v4, :cond_1

    const/4 v0, 0x1

    .line 121
    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->onUpdateData(Z)V

    .line 122
    invoke-direct {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->scheduleNextUpdate()V

    return-void

    .line 125
    :cond_1
    iget-boolean v4, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->stackVisible:Z

    if-nez v4, :cond_2

    :goto_0
    return-void

    .line 128
    :cond_2
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v4

    .line 129
    sget-object v6, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->globalLastUpdateTimes:Landroid/util/SparseArray;

    invoke-virtual {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->getPillId()I

    move-result v7

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v8

    invoke-virtual {v6, v7, v8}, Landroid/util/SparseArray;->get(ILjava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/Long;

    invoke-virtual {v6}, Ljava/lang/Long;->longValue()J

    move-result-wide v6

    cmp-long v2, v6, v2

    if-eqz v2, :cond_4

    sub-long/2addr v4, v6

    cmp-long v2, v4, v0

    if-ltz v2, :cond_3

    goto :goto_1

    .line 133
    :cond_3
    iget-object v2, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->autoRefreshRunnable:Ljava/lang/Runnable;

    sub-long/2addr v0, v4

    invoke-virtual {p0, v2, v0, v1}, Landroid/view/View;->postDelayed(Ljava/lang/Runnable;J)Z

    return-void

    .line 131
    :cond_4
    :goto_1
    iget-object p0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->autoRefreshRunnable:Ljava/lang/Runnable;

    invoke-interface {p0}, Ljava/lang/Runnable;->run()V

    return-void
.end method

.method public onDetachedFromWindow()V
    .locals 1

    .line 139
    invoke-super {p0}, Landroid/view/View;->onDetachedFromWindow()V

    .line 140
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->autoRefreshRunnable:Ljava/lang/Runnable;

    invoke-virtual {p0, v0}, Landroid/view/View;->removeCallbacks(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public abstract onPillClicked()V
.end method

.method public abstract onPillLongClicked()Z
.end method

.method public onPillSelected()V
    .locals 0

    return-void
.end method

.method public onPillUnselected()V
    .locals 0

    return-void
.end method

.method public onStackVisibilityChanged(Z)V
    .locals 4

    .line 144
    iget-boolean v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->stackVisible:Z

    if-ne v0, p1, :cond_0

    goto :goto_0

    .line 147
    :cond_0
    iput-boolean p1, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->stackVisible:Z

    if-eqz p1, :cond_3

    .line 149
    invoke-virtual {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->getRefreshInterval()J

    move-result-wide v0

    const-wide/16 v2, 0x0

    cmp-long p1, v0, v2

    if-lez p1, :cond_2

    .line 150
    invoke-virtual {p0}, Landroid/view/View;->isAttachedToWindow()Z

    move-result p1

    if-eqz p1, :cond_2

    .line 151
    invoke-virtual {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->isRefreshDue()Z

    move-result p1

    if-eqz p1, :cond_1

    const/4 p1, 0x0

    .line 152
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->onUpdateData(Z)V

    .line 154
    :cond_1
    invoke-direct {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->scheduleNextUpdate()V

    :cond_2
    :goto_0
    return-void

    .line 157
    :cond_3
    iget-object p1, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->autoRefreshRunnable:Ljava/lang/Runnable;

    invoke-virtual {p0, p1}, Landroid/view/View;->removeCallbacks(Ljava/lang/Runnable;)Z

    return-void
.end method

.method public abstract onUpdateData(Z)V
.end method

.method public setLoadingTargetView(Landroid/view/View;)V
    .locals 0

    .line 167
    iput-object p1, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->loadingTargetView:Landroid/view/View;

    return-void
.end method

.method public startLoading()V
    .locals 2

    const/4 v0, 0x1

    .line 171
    iput-boolean v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->loading:Z

    .line 172
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    if-nez v0, :cond_0

    .line 173
    new-instance v0, Lorg/telegram/ui/Components/LoadingDrawable;

    iget-object v1, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-direct {v0, v1}, Lorg/telegram/ui/Components/LoadingDrawable;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    iput-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    .line 174
    invoke-virtual {v0, p0}, Landroid/graphics/drawable/Drawable;->setCallback(Landroid/graphics/drawable/Drawable$Callback;)V

    .line 175
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    const/high16 v1, 0x40000000    # 2.0f

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/LoadingDrawable;->setGradientScale(F)V

    .line 176
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    const/high16 v1, 0x41600000    # 14.0f

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/LoadingDrawable;->setRadiiDp(F)V

    .line 177
    invoke-virtual {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->updateLoadingColors()V

    .line 179
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/LoadingDrawable;->reset()V

    .line 180
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/LoadingDrawable;->resetDisappear()V

    .line 181
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    const/16 v1, 0xff

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/LoadingDrawable;->setAlpha(I)V

    .line 182
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method

.method public stopLoading()V
    .locals 1

    const/4 v0, 0x0

    .line 198
    iput-boolean v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->loading:Z

    .line 199
    iget-object p0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    if-eqz p0, :cond_0

    .line 200
    invoke-virtual {p0}, Lorg/telegram/ui/Components/LoadingDrawable;->disappear()V

    :cond_0
    return-void
.end method

.method public abstract updateColors()V
.end method

.method public updateLoadingColors()V
    .locals 5

    .line 205
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    if-eqz v0, :cond_0

    .line 206
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    iget-object v1, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v0, v1}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v0

    .line 207
    iget-object p0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    const v1, 0x3d4ccccd    # 0.05f

    .line 208
    invoke-static {v0, v1}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v1

    const v2, 0x3e19999a    # 0.15f

    invoke-static {v0, v2}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v2

    const v3, 0x3dcccccd    # 0.1f

    .line 209
    invoke-static {v0, v3}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v3

    const/high16 v4, 0x3e800000    # 0.25f

    invoke-static {v0, v4}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v0

    .line 207
    invoke-virtual {p0, v1, v2, v3, v0}, Lorg/telegram/ui/Components/LoadingDrawable;->setColors(IIII)V

    :cond_0
    return-void
.end method

.method public verifyDrawable(Landroid/graphics/drawable/Drawable;)Z
    .locals 1

    .line 239
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/BasePill;->loadingDrawable:Lorg/telegram/ui/Components/LoadingDrawable;

    if-eq p1, v0, :cond_1

    invoke-super {p0, p1}, Landroid/view/View;->verifyDrawable(Landroid/graphics/drawable/Drawable;)Z

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
