.class public final Lcom/exteragram/messenger/preferences/BasePreferencesActivity$createView$1$1;
.super Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->createView(Landroid/content/Context;)Landroid/view/View;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0008\n\u0000*\u0001\u0000\u0008\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016\u00a8\u0006\u0006"
    }
    d2 = {
        "com/exteragram/messenger/preferences/BasePreferencesActivity$createView$1$1",
        "Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;",
        "onItemClick",
        "",
        "id",
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
.field final synthetic this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V
    .locals 0

    iput-object p1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$createView$1$1;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    .line 99
    invoke-direct {p0}, Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;-><init>()V

    return-void
.end method


# virtual methods
.method public onItemClick(I)V
    .locals 1

    const/4 v0, -0x1

    if-ne p1, v0, :cond_0

    .line 101
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$createView$1$1;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->finishFragment()V

    :cond_0
    return-void
.end method
