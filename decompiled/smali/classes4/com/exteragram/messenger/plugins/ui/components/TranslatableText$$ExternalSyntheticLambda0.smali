.class public final synthetic Lcom/exteragram/messenger/plugins/ui/components/TranslatableText$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/animation/ValueAnimator$AnimatorUpdateListener;


# instance fields
.field public final synthetic f$0:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

.field public final synthetic f$1:Landroid/widget/TextView;


# direct methods
.method public synthetic constructor <init>(Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;Landroid/widget/TextView;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText$$ExternalSyntheticLambda0;->f$0:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    iput-object p2, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText$$ExternalSyntheticLambda0;->f$1:Landroid/widget/TextView;

    return-void
.end method


# virtual methods
.method public final onAnimationUpdate(Landroid/animation/ValueAnimator;)V
    .locals 1

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText$$ExternalSyntheticLambda0;->f$0:Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText$$ExternalSyntheticLambda0;->f$1:Landroid/widget/TextView;

    invoke-static {v0, p0, p1}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->$r8$lambda$JRgU_sneSrKGk0HLUPOQr7eYGE4(Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;Landroid/widget/TextView;Landroid/animation/ValueAnimator;)V

    return-void
.end method
