.class Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;)V
    .locals 0

    .line 54
    iput-object p1, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem$1;->this$0:Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 2

    .line 57
    iget-object v0, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem$1;->this$0:Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;

    invoke-static {v0}, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->-$$Nest$fgettoggled(Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 58
    iget-object v0, p0, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem$1;->this$0:Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;

    invoke-static {v0}, Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;->-$$Nest$mupdate(Lcom/exteragram/messenger/adblock/ui/AdBlockMenuItem;)V

    const-wide/16 v0, 0x1f4

    .line 59
    invoke-static {p0, v0, v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    :cond_0
    return-void
.end method
