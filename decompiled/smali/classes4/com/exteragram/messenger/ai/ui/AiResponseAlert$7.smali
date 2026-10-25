.class Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/exteragram/messenger/ai/network/GenerationCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->generate()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;


# direct methods
.method public static synthetic $r8$lambda$NkvHoNIwLEn7kQ9kKPYMlUR-ceU(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7;->lambda$onError$0()V

    return-void
.end method

.method public constructor <init>(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)V
    .locals 0

    .line 536
    iput-object p1, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private synthetic lambda$onError$0()V
    .locals 0

    .line 564
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fgetheaderView(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$HeaderView;->openModelSelect()V

    return-void
.end method


# virtual methods
.method public onChunk(Ljava/lang/String;)V
    .locals 1

    .line 554
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 555
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v0, p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fputcurrentResponse(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;Ljava/lang/CharSequence;)V

    .line 556
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p0, p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$msetResponse(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;Ljava/lang/String;)V

    :cond_0
    return-void
.end method

.method public onError(ILjava/lang/String;)V
    .locals 4

    .line 562
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mstopThinking(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)V

    .line 563
    invoke-static {p1}, Lcom/exteragram/messenger/ai/AiController;->isOnDeviceError(I)Z

    move-result v0

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mavailableServices(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-le v0, v1, :cond_0

    .line 564
    new-instance v0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7;)V

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    .line 566
    :goto_0
    iget-object v2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v2}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$300(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Landroid/view/ViewGroup;

    move-result-object v2

    iget-object v3, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v3}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->access$400(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    move-result-object v3

    invoke-static {v2, v3, p1, p2, v0}, Lcom/exteragram/messenger/ai/AiController;->showErrorBulletin(Landroid/view/ViewGroup;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;ILjava/lang/String;Ljava/lang/Runnable;)V

    .line 567
    iget-object p1, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fgetadapter(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Lcom/exteragram/messenger/ai/ui/AiResponseAlert$PaddedAdapter;

    move-result-object p1

    iget-object p2, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p2}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fgetresponseContainer(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Lcom/exteragram/messenger/ai/ui/AiResponseAlert$ResponseContainer;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$PaddedAdapter;->updateMainView(Landroid/view/View;)V

    .line 568
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    const/4 p1, 0x0

    invoke-static {p0, p1, v1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mupdateMainButton(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;ZZ)V

    return-void
.end method

.method public onResponse(Ljava/lang/String;)V
    .locals 2

    .line 544
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 545
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v0, p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fputcurrentResponse(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;Ljava/lang/CharSequence;)V

    .line 546
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fgetuseHistory(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)Z

    move-result v1

    invoke-static {v0, v1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$fputturnSaved(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;Z)V

    .line 547
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {v0, p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$msetResponse(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;Ljava/lang/String;)V

    .line 548
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    const/4 p1, 0x0

    invoke-static {p0, p1}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mupdateMainButton(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;Z)V

    :cond_0
    return-void
.end method

.method public onThinking()V
    .locals 0

    .line 539
    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$7;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-static {p0}, Lcom/exteragram/messenger/ai/ui/AiResponseAlert;->-$$Nest$mshowThinking(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;)V

    return-void
.end method
