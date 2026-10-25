.class public final Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$searchItem$1;
.super Lorg/telegram/ui/ActionBar/ActionBarMenuItem$ActionBarMenuItemSearchListener;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->createView(Landroid/content/Context;)Landroid/view/View;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\u0008\n\u0018\u00002\u00020\u0001J\u0008\u0010\u0002\u001a\u00020\u0003H\u0016J\u0008\u0010\u0004\u001a\u00020\u0003H\u0016J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016\u00a8\u0006\u0008"
    }
    d2 = {
        "com/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$searchItem$1",
        "Lorg/telegram/ui/ActionBar/ActionBarMenuItem$ActionBarMenuItemSearchListener;",
        "onSearchExpand",
        "",
        "onSearchCollapse",
        "onTextChanged",
        "editText",
        "Landroid/widget/EditText;",
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

.annotation build Lkotlin/jvm/internal/SourceDebugExtension;
    value = {
        "SMAP\nIconPacksEditorActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconPacksEditorActivity.kt\ncom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$searchItem$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,388:1\n1#2:389\n*E\n"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;


# direct methods
.method public static $r8$lambda$UHgwOIcW448g3rraVlWtzY1_HCY(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;Landroid/widget/EditText;)V
    .locals 0

    .line 98
    invoke-virtual {p1}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p0, p1}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->access$setQuery$p(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;Ljava/lang/String;)V

    .line 99
    invoke-static {p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->access$updateAdapter(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;)V

    return-void
.end method

.method public constructor <init>(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;)V
    .locals 0

    iput-object p1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$searchItem$1;->this$0:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;

    .line 82
    invoke-direct {p0}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem$ActionBarMenuItemSearchListener;-><init>()V

    return-void
.end method


# virtual methods
.method public onSearchCollapse()V
    .locals 3

    .line 89
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$searchItem$1;->this$0:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->access$setSearching$p(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;Z)V

    .line 90
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$searchItem$1;->this$0:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;

    const/4 v2, 0x0

    invoke-static {v0, v2}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->access$setQuery$p(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;Ljava/lang/String;)V

    .line 91
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$searchItem$1;->this$0:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->access$getOtherItem$p(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    .line 92
    :cond_0
    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$searchItem$1;->this$0:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;

    invoke-static {p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->access$updateAdapter(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;)V

    return-void
.end method

.method public onSearchExpand()V
    .locals 2

    .line 84
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$searchItem$1;->this$0:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;

    const/4 v1, 0x1

    invoke-static {v0, v1}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->access$setSearching$p(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;Z)V

    .line 85
    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$searchItem$1;->this$0:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;

    invoke-static {p0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->access$getOtherItem$p(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    move-result-object p0

    if-eqz p0, :cond_0

    const/16 v0, 0x8

    invoke-virtual {p0, v0}, Landroid/view/View;->setVisibility(I)V

    :cond_0
    return-void
.end method

.method public onTextChanged(Landroid/widget/EditText;)V
    .locals 2

    .line 96
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$searchItem$1;->this$0:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->access$getSearchRunnable$p(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;)Ljava/lang/Runnable;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    .line 97
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$searchItem$1;->this$0:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;

    new-instance v1, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$searchItem$1$$ExternalSyntheticLambda0;

    invoke-direct {v1, v0, p1}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$searchItem$1$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;Landroid/widget/EditText;)V

    .line 101
    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$searchItem$1;->this$0:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;

    invoke-static {p0, v1}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->access$setSearchRunnable$p(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;Ljava/lang/Runnable;)V

    const-wide/16 p0, 0xc8

    .line 102
    invoke-static {v1, p0, p1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    return-void
.end method
