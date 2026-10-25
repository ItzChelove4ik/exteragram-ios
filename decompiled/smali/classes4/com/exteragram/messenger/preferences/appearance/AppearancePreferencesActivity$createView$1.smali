.class public final Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$createView$1;
.super Lcom/exteragram/messenger/preferences/appearance/components/FabShapeCell;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->createView(Landroid/content/Context;)Landroid/view/View;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\u0008\n\u0018\u00002\u00020\u0001J\u0008\u0010\u0002\u001a\u00020\u0003H\u0014\u00a8\u0006\u0004"
    }
    d2 = {
        "com/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$createView$1",
        "Lcom/exteragram/messenger/preferences/appearance/components/FabShapeCell;",
        "rebuildFragments",
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
.field final synthetic this$0:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;


# direct methods
.method public constructor <init>(Landroid/content/Context;Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;)V
    .locals 0

    iput-object p2, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$createView$1;->this$0:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;

    .line 158
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/preferences/appearance/components/FabShapeCell;-><init>(Landroid/content/Context;)V

    return-void
.end method


# virtual methods
.method public rebuildFragments()V
    .locals 1

    .line 159
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$createView$1;->this$0:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;

    invoke-static {p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->access$getParentLayout$p$s1161066243(Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;)Lorg/telegram/ui/ActionBar/INavigationLayout;

    move-result-object p0

    const/4 v0, 0x0

    invoke-interface {p0, v0}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void
.end method
