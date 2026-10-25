.class public final Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$1;
.super Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;
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
        "\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0008\n\u0000*\u0001\u0000\u0008\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016\u00a8\u0006\u0006"
    }
    d2 = {
        "com/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$1",
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

.annotation build Lkotlin/jvm/internal/SourceDebugExtension;
    value = {
        "SMAP\nIconPacksEditorActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconPacksEditorActivity.kt\ncom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,388:1\n1#2:389\n*E\n"
    }
.end annotation


# instance fields
.field final synthetic $context:Landroid/content/Context;

.field final synthetic this$0:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;Landroid/content/Context;)V
    .locals 0

    iput-object p1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$1;->this$0:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;

    iput-object p2, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$1;->$context:Landroid/content/Context;

    .line 116
    invoke-direct {p0}, Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;-><init>()V

    return-void
.end method


# virtual methods
.method public onItemClick(I)V
    .locals 2

    const/4 v0, -0x1

    if-eq p1, v0, :cond_4

    const/4 v0, 0x1

    if-eq p1, v0, :cond_3

    const/4 v0, 0x2

    if-eq p1, v0, :cond_0

    return-void

    :cond_0
    const/4 p1, 0x0

    .line 121
    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setEditingIconPackId(Ljava/lang/String;)V

    .line 122
    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$1;->this$0:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;

    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    instance-of v1, v0, Lorg/telegram/ui/LaunchActivity;

    if-eqz v1, :cond_1

    move-object p1, v0

    check-cast p1, Lorg/telegram/ui/LaunchActivity;

    :cond_1
    if-eqz p1, :cond_2

    const/4 v0, 0x0

    invoke-static {p1, v0}, Lcom/exteragram/messenger/icons/ui/picker/IconPickerController;->setActive(Lorg/telegram/ui/LaunchActivity;Z)V

    .line 123
    :cond_2
    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$1;->this$0:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->finishFragment()V

    return-void

    .line 125
    :cond_3
    new-instance p1, Lcom/exteragram/messenger/icons/ui/components/NewIconPackBottomSheet;

    iget-object v0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$1;->this$0:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;

    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$1;->$context:Landroid/content/Context;

    invoke-static {v0}, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;->access$getIconPack$p(Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;)Lcom/exteragram/messenger/icons/IconPack;

    move-result-object v1

    invoke-direct {p1, v0, p0, v1}, Lcom/exteragram/messenger/icons/ui/components/NewIconPackBottomSheet;-><init>(Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/content/Context;Lcom/exteragram/messenger/icons/IconPack;)V

    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/BottomSheet;->show()V

    return-void

    .line 119
    :cond_4
    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity$createView$1;->this$0:Lcom/exteragram/messenger/icons/ui/IconPacksEditorActivity;

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->finishFragment()V

    return-void
.end method
