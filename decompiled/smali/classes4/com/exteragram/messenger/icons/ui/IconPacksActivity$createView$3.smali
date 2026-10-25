.class public final Lcom/exteragram/messenger/icons/ui/IconPacksActivity$createView$3;
.super Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->createView(Landroid/content/Context;)Landroid/view/View;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0002*\u0001\u0000\u0008\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0008\u001a\u00020\u0007H\u0016\u00a8\u0006\t"
    }
    d2 = {
        "com/exteragram/messenger/icons/ui/IconPacksActivity$createView$3",
        "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;",
        "onScrolled",
        "",
        "recyclerView",
        "Landroidx/recyclerview/widget/RecyclerView;",
        "dx",
        "",
        "dy",
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
.field final synthetic this$0:Lcom/exteragram/messenger/icons/ui/IconPacksActivity;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/icons/ui/IconPacksActivity;)V
    .locals 0

    iput-object p1, p0, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$createView$3;->this$0:Lcom/exteragram/messenger/icons/ui/IconPacksActivity;

    .line 77
    invoke-direct {p0}, Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;-><init>()V

    return-void
.end method


# virtual methods
.method public onScrolled(Landroidx/recyclerview/widget/RecyclerView;II)V
    .locals 0

    const/4 p1, 0x1

    if-eqz p3, :cond_2

    .line 79
    iget-object p2, p0, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$createView$3;->this$0:Lcom/exteragram/messenger/icons/ui/IconPacksActivity;

    invoke-static {p2}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->access$getScrollUpdated$p(Lcom/exteragram/messenger/icons/ui/IconPacksActivity;)Z

    move-result p2

    if-eqz p2, :cond_2

    .line 80
    iget-object p2, p0, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$createView$3;->this$0:Lcom/exteragram/messenger/icons/ui/IconPacksActivity;

    invoke-static {p2}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->access$getFloatingButton$p(Lcom/exteragram/messenger/icons/ui/IconPacksActivity;)Lorg/telegram/ui/Components/FragmentFloatingButton;

    move-result-object p2

    if-nez p2, :cond_0

    const/4 p2, 0x0

    :cond_0
    if-gez p3, :cond_1

    move p3, p1

    goto :goto_0

    :cond_1
    const/4 p3, 0x0

    :goto_0
    invoke-virtual {p2, p3, p1}, Lorg/telegram/ui/Components/FragmentFloatingButton;->setButtonVisible(ZZ)V

    .line 82
    :cond_2
    iget-object p0, p0, Lcom/exteragram/messenger/icons/ui/IconPacksActivity$createView$3;->this$0:Lcom/exteragram/messenger/icons/ui/IconPacksActivity;

    invoke-static {p0, p1}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;->access$setScrollUpdated$p(Lcom/exteragram/messenger/icons/ui/IconPacksActivity;Z)V

    return-void
.end method
