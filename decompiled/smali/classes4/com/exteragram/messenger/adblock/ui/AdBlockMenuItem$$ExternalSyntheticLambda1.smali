.class public final synthetic Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem$$ExternalSyntheticLambda1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lcom/exteragram/messenger/adblock/WebAdBlocker;


# direct methods
.method public synthetic constructor <init>(Lcom/exteragram/messenger/adblock/WebAdBlocker;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem$$ExternalSyntheticLambda1;->f$0:Lcom/exteragram/messenger/adblock/WebAdBlocker;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem$$ExternalSyntheticLambda1;->f$0:Lcom/exteragram/messenger/adblock/WebAdBlocker;

    invoke-virtual {p0}, Lcom/exteragram/messenger/adblock/WebAdBlocker;->reload()V

    return-void
.end method
