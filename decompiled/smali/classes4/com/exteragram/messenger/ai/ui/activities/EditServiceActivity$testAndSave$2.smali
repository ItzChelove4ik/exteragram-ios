.class public final Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$testAndSave$2;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/exteragram/messenger/ai/network/GenerationCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->testAndSave(Lcom/exteragram/messenger/ai/data/Service;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0008\u0004\n\u0002\u0010\u0008\n\u0002\u0008\u0002*\u0001\u0000\u0008\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0016J\u0018\u0010\u0008\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0016\u00a8\u0006\u000c"
    }
    d2 = {
        "com/exteragram/messenger/ai/ui/activities/EditServiceActivity$testAndSave$2",
        "Lcom/exteragram/messenger/ai/network/GenerationCallback;",
        "onChunk",
        "",
        "string",
        "",
        "onResponse",
        "finalResponse",
        "onError",
        "errorCode",
        "",
        "errorMessage",
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
.field final synthetic $service:Lcom/exteragram/messenger/ai/data/Service;

.field final synthetic this$0:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;


# direct methods
.method public static $r8$lambda$IiF_AungF8sXl5BJJVqdLioh6QU(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;Lcom/exteragram/messenger/ai/data/Service;)V
    .locals 2

    .line 414
    invoke-static {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->access$clearTestingState(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;)V

    .line 415
    invoke-static {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->access$getCurrentService$p(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;)Lcom/exteragram/messenger/ai/data/Service;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 416
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object v0

    invoke-static {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->access$getCurrentService$p(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;)Lcom/exteragram/messenger/ai/data/Service;

    move-result-object v1

    invoke-virtual {v0, v1, p1}, Lcom/exteragram/messenger/ai/AiController;->updateService(Lcom/exteragram/messenger/ai/data/Service;Lcom/exteragram/messenger/ai/data/Service;)V

    goto :goto_0

    .line 418
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/ai/AiController;->addService(Lcom/exteragram/messenger/ai/data/Service;)V

    .line 420
    :goto_0
    invoke-static {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->access$applySelection(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;Lcom/exteragram/messenger/ai/data/Service;)V

    return-void
.end method

.method public static $r8$lambda$pRZeN_KicN8khW_IJzcsWQJulRY(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;ILjava/lang/String;)V
    .locals 0

    .line 424
    invoke-static {p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->access$clearTestingState(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;)V

    .line 425
    invoke-static {p0, p1, p2}, Lcom/exteragram/messenger/ai/AiController;->showErrorBulletin(Lorg/telegram/ui/ActionBar/BaseFragment;ILjava/lang/String;)V

    .line 426
    invoke-static {p0, p1, p2}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->access$getFieldForError(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;ILjava/lang/String;)Lorg/telegram/ui/Cells/EditTextCell;

    move-result-object p1

    invoke-static {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;->access$showFieldError(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;Lorg/telegram/ui/Cells/EditTextCell;)V

    return-void
.end method

.method public constructor <init>(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;Lcom/exteragram/messenger/ai/data/Service;)V
    .locals 0

    iput-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$testAndSave$2;->this$0:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;

    iput-object p2, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$testAndSave$2;->$service:Lcom/exteragram/messenger/ai/data/Service;

    .line 410
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onChunk(Ljava/lang/String;)V
    .locals 0

    return-void
.end method

.method public onError(ILjava/lang/String;)V
    .locals 1

    .line 423
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$testAndSave$2;->this$0:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;

    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$testAndSave$2$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0, p1, p2}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$testAndSave$2$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;ILjava/lang/String;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public onResponse(Ljava/lang/String;)V
    .locals 1

    .line 413
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$testAndSave$2;->this$0:Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;

    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$testAndSave$2;->$service:Lcom/exteragram/messenger/ai/data/Service;

    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$testAndSave$2$$ExternalSyntheticLambda1;

    invoke-direct {v0, p1, p0}, Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity$testAndSave$2$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/ai/ui/activities/EditServiceActivity;Lcom/exteragram/messenger/ai/data/Service;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method
