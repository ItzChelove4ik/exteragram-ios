.class public final synthetic Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda7;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Z

.field public final synthetic f$1:Ljava/lang/Runnable;


# direct methods
.method public synthetic constructor <init>(ZLjava/lang/Runnable;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-boolean p1, p0, Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda7;->f$0:Z

    iput-object p2, p0, Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda7;->f$1:Ljava/lang/Runnable;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget-boolean v0, p0, Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda7;->f$0:Z

    iget-object p0, p0, Lcom/exteragram/messenger/adblock/backend/AdBlockManager$$ExternalSyntheticLambda7;->f$1:Ljava/lang/Runnable;

    invoke-static {v0, p0}, Lcom/exteragram/messenger/adblock/backend/AdBlockManager;->$r8$lambda$VkMrvF4u0rpTiyBLPhtCni5XDeE(ZLjava/lang/Runnable;)V

    return-void
.end method
