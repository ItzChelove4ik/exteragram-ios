.class public abstract Lcom/exteragram/messenger/components/PreformattedScrollView;
.super Landroid/widget/HorizontalScrollView;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/components/PreformattedScrollView$Group;
    }
.end annotation


# instance fields
.field private group:Lcom/exteragram/messenger/components/PreformattedScrollView$Group;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 0

    .line 33
    invoke-direct {p0, p1}, Landroid/widget/HorizontalScrollView;-><init>(Landroid/content/Context;)V

    return-void
.end method


# virtual methods
.method public adjustContentWidth(I)I
    .locals 1

    .line 59
    iget-object v0, p0, Lcom/exteragram/messenger/components/PreformattedScrollView;->group:Lcom/exteragram/messenger/components/PreformattedScrollView$Group;

    if-eqz v0, :cond_0

    invoke-static {v0, p0, p1}, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->-$$Nest$madjustContentWidth(Lcom/exteragram/messenger/components/PreformattedScrollView$Group;Lcom/exteragram/messenger/components/PreformattedScrollView;I)I

    move-result p0

    return p0

    :cond_0
    return p1
.end method

.method public dispatchTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 1

    .line 64
    iget-object v0, p0, Lcom/exteragram/messenger/components/PreformattedScrollView;->group:Lcom/exteragram/messenger/components/PreformattedScrollView$Group;

    if-eqz v0, :cond_0

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result v0

    if-nez v0, :cond_0

    .line 65
    iget-object v0, p0, Lcom/exteragram/messenger/components/PreformattedScrollView;->group:Lcom/exteragram/messenger/components/PreformattedScrollView$Group;

    invoke-static {v0, p0}, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->-$$Nest$fputdriver(Lcom/exteragram/messenger/components/PreformattedScrollView$Group;Lcom/exteragram/messenger/components/PreformattedScrollView;)V

    .line 67
    :cond_0
    invoke-super {p0, p1}, Landroid/view/View;->dispatchTouchEvent(Landroid/view/MotionEvent;)Z

    move-result p0

    return p0
.end method

.method public onAttachedToWindow()V
    .locals 3

    .line 88
    invoke-super {p0}, Landroid/view/View;->onAttachedToWindow()V

    .line 89
    iget-object v0, p0, Lcom/exteragram/messenger/components/PreformattedScrollView;->group:Lcom/exteragram/messenger/components/PreformattedScrollView$Group;

    if-eqz v0, :cond_0

    .line 90
    invoke-static {v0, p0}, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->-$$Nest$madd(Lcom/exteragram/messenger/components/PreformattedScrollView$Group;Lcom/exteragram/messenger/components/PreformattedScrollView;)V

    .line 91
    iget-object v0, p0, Lcom/exteragram/messenger/components/PreformattedScrollView;->group:Lcom/exteragram/messenger/components/PreformattedScrollView$Group;

    invoke-static {v0}, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->-$$Nest$fgetscrollX(Lcom/exteragram/messenger/components/PreformattedScrollView$Group;)I

    move-result v0

    invoke-virtual {p0}, Landroid/view/View;->getScrollY()I

    move-result v1

    invoke-virtual {p0, v0, v1}, Landroid/view/View;->scrollTo(II)V

    .line 92
    invoke-virtual {p0}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v0

    if-lez v0, :cond_0

    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v1

    invoke-virtual {v1}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    iget-object v2, p0, Lcom/exteragram/messenger/components/PreformattedScrollView;->group:Lcom/exteragram/messenger/components/PreformattedScrollView$Group;

    invoke-static {v2}, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->-$$Nest$fgetcontentWidth(Lcom/exteragram/messenger/components/PreformattedScrollView$Group;)I

    move-result v2

    if-ge v1, v2, :cond_0

    .line 93
    invoke-virtual {p0, v0}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object p0

    .line 94
    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v0, Lcom/exteragram/messenger/components/PreformattedScrollView$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/components/PreformattedScrollView$$ExternalSyntheticLambda0;-><init>(Landroid/view/View;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    :cond_0
    return-void
.end method

.method public onDetachedFromWindow()V
    .locals 1

    .line 101
    invoke-super {p0}, Landroid/view/View;->onDetachedFromWindow()V

    .line 102
    iget-object v0, p0, Lcom/exteragram/messenger/components/PreformattedScrollView;->group:Lcom/exteragram/messenger/components/PreformattedScrollView$Group;

    if-eqz v0, :cond_0

    .line 103
    invoke-static {v0, p0}, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->-$$Nest$mremove(Lcom/exteragram/messenger/components/PreformattedScrollView$Group;Lcom/exteragram/messenger/components/PreformattedScrollView;)V

    :cond_0
    return-void
.end method

.method public onLayout(ZIIII)V
    .locals 0

    .line 80
    invoke-super/range {p0 .. p5}, Landroid/widget/HorizontalScrollView;->onLayout(ZIIII)V

    .line 81
    iget-object p1, p0, Lcom/exteragram/messenger/components/PreformattedScrollView;->group:Lcom/exteragram/messenger/components/PreformattedScrollView$Group;

    if-eqz p1, :cond_0

    .line 82
    invoke-static {p1}, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->-$$Nest$fgetscrollX(Lcom/exteragram/messenger/components/PreformattedScrollView$Group;)I

    move-result p1

    invoke-virtual {p0}, Landroid/view/View;->getScrollY()I

    move-result p2

    invoke-virtual {p0, p1, p2}, Landroid/view/View;->scrollTo(II)V

    :cond_0
    return-void
.end method

.method public onScrollChanged(IIII)V
    .locals 0

    .line 72
    invoke-super {p0, p1, p2, p3, p4}, Landroid/view/View;->onScrollChanged(IIII)V

    .line 73
    iget-object p2, p0, Lcom/exteragram/messenger/components/PreformattedScrollView;->group:Lcom/exteragram/messenger/components/PreformattedScrollView$Group;

    if-eqz p2, :cond_0

    invoke-static {p2}, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->-$$Nest$fgetdriver(Lcom/exteragram/messenger/components/PreformattedScrollView$Group;)Lcom/exteragram/messenger/components/PreformattedScrollView;

    move-result-object p2

    if-ne p2, p0, :cond_0

    .line 74
    iget-object p2, p0, Lcom/exteragram/messenger/components/PreformattedScrollView;->group:Lcom/exteragram/messenger/components/PreformattedScrollView$Group;

    invoke-static {p2, p0, p1}, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->-$$Nest$mscrollTo(Lcom/exteragram/messenger/components/PreformattedScrollView$Group;Lcom/exteragram/messenger/components/PreformattedScrollView;I)V

    :cond_0
    return-void
.end method

.method public setBlock(Lorg/telegram/tgnet/tl/TL_iv$PageBlock;)V
    .locals 4

    .line 37
    instance-of v0, p1, Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    check-cast p1, Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;

    goto :goto_0

    :cond_0
    move-object p1, v1

    .line 38
    :goto_0
    iget-object v0, p0, Lcom/exteragram/messenger/components/PreformattedScrollView;->group:Lcom/exteragram/messenger/components/PreformattedScrollView$Group;

    if-eqz v0, :cond_1

    .line 39
    invoke-static {v0, p0}, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->-$$Nest$mremove(Lcom/exteragram/messenger/components/PreformattedScrollView$Group;Lcom/exteragram/messenger/components/PreformattedScrollView;)V

    :cond_1
    if-eqz p1, :cond_2

    .line 41
    iget-object v1, p1, Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;->scrollGroup:Lcom/exteragram/messenger/components/PreformattedScrollView$Group;

    :cond_2
    iput-object v1, p0, Lcom/exteragram/messenger/components/PreformattedScrollView;->group:Lcom/exteragram/messenger/components/PreformattedScrollView$Group;

    if-eqz v1, :cond_3

    .line 42
    invoke-virtual {p0}, Landroid/view/View;->isAttachedToWindow()Z

    move-result v0

    if-eqz v0, :cond_3

    .line 43
    iget-object v0, p0, Lcom/exteragram/messenger/components/PreformattedScrollView;->group:Lcom/exteragram/messenger/components/PreformattedScrollView$Group;

    invoke-static {v0, p0}, Lcom/exteragram/messenger/components/PreformattedScrollView$Group;->-$$Nest$madd(Lcom/exteragram/messenger/components/PreformattedScrollView$Group;Lcom/exteragram/messenger/components/PreformattedScrollView;)V

    :cond_3
    const/4 v0, 0x1

    const/4 v1, 0x0

    if-nez p1, :cond_4

    move v2, v0

    goto :goto_1

    :cond_4
    move v2, v1

    .line 46
    :goto_1
    invoke-virtual {p0, v2}, Landroid/view/View;->setHorizontalScrollBarEnabled(Z)V

    if-eqz p1, :cond_5

    .line 48
    iget-boolean v2, p1, Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;->joinsPrevious:Z

    if-eqz v2, :cond_5

    move v2, v0

    goto :goto_2

    :cond_5
    move v2, v1

    :goto_2
    if-eqz p1, :cond_6

    .line 49
    iget-boolean p1, p1, Lcom/exteragram/messenger/utils/MarkdownUtils$PreformattedChunk;->joinsNext:Z

    if-eqz p1, :cond_6

    goto :goto_3

    :cond_6
    move v0, v1

    :goto_3
    const/high16 p1, 0x41000000    # 8.0f

    if-eqz v2, :cond_7

    move v3, v1

    goto :goto_4

    .line 50
    :cond_7
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    :goto_4
    if-eqz v0, :cond_8

    move p1, v1

    goto :goto_5

    :cond_8
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    :goto_5
    invoke-virtual {p0, v1, v3, v1, p1}, Landroid/view/View;->setPadding(IIII)V

    .line 51
    invoke-virtual {p0}, Landroid/view/ViewGroup;->getChildCount()I

    move-result p1

    if-lez p1, :cond_b

    .line 52
    invoke-virtual {p0, v1}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object p0

    invoke-virtual {p0}, Landroid/view/View;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object p0

    check-cast p0, Landroid/view/ViewGroup$MarginLayoutParams;

    const/high16 p1, 0x41400000    # 12.0f

    if-eqz v2, :cond_9

    goto :goto_6

    .line 53
    :cond_9
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    :goto_6
    iput v1, p0, Landroid/view/ViewGroup$MarginLayoutParams;->topMargin:I

    if-eqz v0, :cond_a

    const/high16 p1, 0x40800000    # 4.0f

    .line 54
    :cond_a
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p1

    iput p1, p0, Landroid/view/ViewGroup$MarginLayoutParams;->bottomMargin:I

    :cond_b
    return-void
.end method
