.class Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$1;
.super Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;->createView(Landroid/content/Context;)Landroid/view/View;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;)V
    .locals 0

    .line 116
    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$1;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-direct {p0}, Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;-><init>()V

    return-void
.end method


# virtual methods
.method public onItemClick(I)V
    .locals 1

    const/4 v0, -0x1

    if-ne p1, v0, :cond_0

    .line 119
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity$1;->this$0:Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->finishFragment()V

    :cond_0
    return-void
.end method
