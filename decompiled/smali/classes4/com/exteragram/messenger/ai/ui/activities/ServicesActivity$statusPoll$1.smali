.class public final Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$statusPoll$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;-><init>()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\u0008\n\u0018\u00002\u00020\u0001J\u0008\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"
    }
    d2 = {
        "com/exteragram/messenger/ai/ui/activities/ServicesActivity$statusPoll$1",
        "Ljava/lang/Runnable;",
        "run",
        "",
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
.field final synthetic this$0:Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V
    .locals 0

    iput-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$statusPoll$1;->this$0:Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;

    .line 60
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 2

    .line 62
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$statusPoll$1;->this$0:Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;

    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 63
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$statusPoll$1;->this$0:Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;

    new-instance v1, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$statusPoll$1$$ExternalSyntheticLambda0;

    invoke-direct {v1, v0}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity$statusPoll$1$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;)V

    invoke-static {v1}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->refresh(Ljava/lang/Runnable;)V

    .line 64
    invoke-static {}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->isDownloadInProgress()Z

    move-result v0

    if-eqz v0, :cond_1

    const-wide/16 v0, 0x1388

    .line 65
    invoke-static {p0, v0, v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    :cond_1
    :goto_0
    return-void
.end method
