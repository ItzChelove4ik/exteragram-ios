.class public final synthetic Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lcom/exteragram/messenger/ai/data/Message;

.field public final synthetic f$1:Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;


# direct methods
.method public synthetic constructor <init>(Lcom/exteragram/messenger/ai/data/Message;Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$$ExternalSyntheticLambda0;->f$0:Lcom/exteragram/messenger/ai/data/Message;

    iput-object p2, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$$ExternalSyntheticLambda0;->f$1:Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$$ExternalSyntheticLambda0;->f$0:Lcom/exteragram/messenger/ai/data/Message;

    iget-object p0, p0, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity$$ExternalSyntheticLambda0;->f$1:Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;

    invoke-static {v0, p0}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;->$r8$lambda$TeKMotS-U3GWzCgiEGZlnRzfEBQ(Lcom/exteragram/messenger/ai/data/Message;Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;)V

    return-void
.end method
