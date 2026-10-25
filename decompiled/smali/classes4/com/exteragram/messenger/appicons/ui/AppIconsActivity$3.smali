.class Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$3;
.super Landroid/animation/AnimatorListenerAdapter;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->animateBackgroundColor(I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

.field final synthetic val$color:I


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;I)V
    .locals 0

    .line 288
    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$3;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    iput p2, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$3;->val$color:I

    invoke-direct {p0}, Landroid/animation/AnimatorListenerAdapter;-><init>()V

    return-void
.end method


# virtual methods
.method public onAnimationEnd(Landroid/animation/Animator;)V
    .locals 1

    .line 291
    iget-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$3;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    iget v0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$3;->val$color:I

    invoke-static {p1, v0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$fputbackgroundColor(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;I)V

    .line 292
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$3;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-static {p0}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->-$$Nest$mupdateStatusBar(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)V

    return-void
.end method
