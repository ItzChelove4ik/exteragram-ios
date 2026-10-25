.class public final Lcom/exteragram/messenger/plugins/ui/components/TranslatableText$show$1$2;
.super Landroid/animation/AnimatorListenerAdapter;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->show(Z)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\u0008\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016\u00a8\u0006\u0006"
    }
    d2 = {
        "com/exteragram/messenger/plugins/ui/components/TranslatableText$show$1$2",
        "Landroid/animation/AnimatorListenerAdapter;",
        "onAnimationEnd",
        "",
        "animation",
        "Landroid/animation/Animator;",
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
.field final synthetic $view:Landroid/widget/TextView;

.field final synthetic this$0:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;Landroid/widget/TextView;)V
    .locals 0

    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText$show$1$2;->this$0:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    iput-object p2, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText$show$1$2;->$view:Landroid/widget/TextView;

    .line 1305
    invoke-direct {p0}, Landroid/animation/AnimatorListenerAdapter;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 2

    .line 1307
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText$show$1$2;->this$0:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    const/4 v0, 0x0

    invoke-static {p1, v0}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->access$setOldLayout$p(Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;Landroid/text/Layout;)V

    .line 1308
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText$show$1$2;->this$0:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    const/high16 v1, 0x3f800000    # 1.0f

    invoke-static {p1, v1}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->access$setTransitionProgress$p(Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;F)V

    .line 1309
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText$show$1$2;->this$0:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    invoke-static {p1, v0}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->access$setTransitionAnimator$p(Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;Landroid/animation/ValueAnimator;)V

    .line 1310
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText$show$1$2;->$view:Landroid/widget/TextView;

    invoke-virtual {p1}, Landroid/view/View;->requestLayout()V

    .line 1311
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText$show$1$2;->$view:Landroid/widget/TextView;

    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method
