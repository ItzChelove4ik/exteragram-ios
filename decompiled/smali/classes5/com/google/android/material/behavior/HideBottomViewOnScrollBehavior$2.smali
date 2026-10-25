.class Lcom/google/android/material/behavior/HideBottomViewOnScrollBehavior$2;
.super Landroid/animation/AnimatorListenerAdapter;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/google/android/material/behavior/HideBottomViewOnScrollBehavior;->animateChildTo(Landroid/view/View;IJLandroid/animation/TimeInterpolator;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/google/android/material/behavior/HideBottomViewOnScrollBehavior;

.field final synthetic val$child:Landroid/view/View;


# direct methods
.method public constructor <init>(Lcom/google/android/material/behavior/HideBottomViewOnScrollBehavior;Landroid/view/View;)V
    .locals 0

    .line 352
    iput-object p1, p0, Lcom/google/android/material/behavior/HideBottomViewOnScrollBehavior$2;->this$0:Lcom/google/android/material/behavior/HideBottomViewOnScrollBehavior;

    iput-object p2, p0, Lcom/google/android/material/behavior/HideBottomViewOnScrollBehavior$2;->val$child:Landroid/view/View;

    invoke-direct {p0}, Landroid/animation/AnimatorListenerAdapter;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 1

    .line 355
    iget-object p1, p0, Lcom/google/android/material/behavior/HideBottomViewOnScrollBehavior$2;->this$0:Lcom/google/android/material/behavior/HideBottomViewOnScrollBehavior;

    const/4 v0, 0x0

    invoke-static {p1, v0}, Lcom/google/android/material/behavior/HideBottomViewOnScrollBehavior;->access$202(Lcom/google/android/material/behavior/HideBottomViewOnScrollBehavior;Landroid/view/ViewPropertyAnimator;)Landroid/view/ViewPropertyAnimator;

    .line 356
    iget-object p1, p0, Lcom/google/android/material/behavior/HideBottomViewOnScrollBehavior$2;->this$0:Lcom/google/android/material/behavior/HideBottomViewOnScrollBehavior;

    invoke-static {p1}, Lcom/google/android/material/behavior/HideBottomViewOnScrollBehavior;->access$300(Lcom/google/android/material/behavior/HideBottomViewOnScrollBehavior;)I

    move-result p1

    const/4 v0, 0x1

    if-ne p1, v0, :cond_0

    iget-object p1, p0, Lcom/google/android/material/behavior/HideBottomViewOnScrollBehavior$2;->val$child:Landroid/view/View;

    .line 357
    invoke-virtual {p1}, Landroid/view/View;->getVisibility()I

    move-result p1

    if-nez p1, :cond_0

    .line 358
    iget-object p0, p0, Lcom/google/android/material/behavior/HideBottomViewOnScrollBehavior$2;->val$child:Landroid/view/View;

    const/4 p1, 0x4

    invoke-virtual {p0, p1}, Landroid/view/View;->setVisibility(I)V

    :cond_0
    return-void
.end method
