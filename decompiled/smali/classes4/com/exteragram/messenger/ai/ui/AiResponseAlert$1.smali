.class Lcom/exteragram/messenger/ai/ui/AiResponseAlert$1;
.super Landroid/widget/LinearLayout;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/ai/ui/AiResponseAlert;-><init>(Landroid/content/Context;Lcom/exteragram/messenger/ai/network/Client;Ljava/lang/String;Ljava/lang/String;ZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/ai/ui/AiResponseAlert;Landroid/content/Context;)V
    .locals 0

    .line 175
    iput-object p1, p0, Lcom/exteragram/messenger/ai/ui/AiResponseAlert$1;->this$0:Lcom/exteragram/messenger/ai/ui/AiResponseAlert;

    invoke-direct {p0, p2}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    return-void
.end method


# virtual methods
.method public hasOverlappingRendering()Z
    .locals 0

    const/4 p0, 0x0

    return p0
.end method
