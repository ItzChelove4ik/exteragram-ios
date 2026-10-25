.class public final Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$createView$searchItem$1;
.super Lorg/telegram/ui/ActionBar/ActionBarMenuItem$ActionBarMenuItemSearchListener;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->createView(Landroid/content/Context;)Landroid/view/View;
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
        "com/exteragram/messenger/feed/ui/FeedChannelsActivity$createView$searchItem$1",
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


# instance fields
.field final synthetic this$0:Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;)V
    .locals 0

    iput-object p1, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$createView$searchItem$1;->this$0:Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;

    .line 95
    invoke-direct {p0}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem$ActionBarMenuItemSearchListener;-><init>()V

    return-void
.end method


# virtual methods
.method public onSearchCollapse()V
    .locals 3

    .line 102
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$createView$searchItem$1;->this$0:Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->access$setSearching$p(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;Z)V

    .line 103
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$createView$searchItem$1;->this$0:Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;

    const/4 v2, 0x0

    invoke-static {v0, v2}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->access$setQuery$p(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;Ljava/lang/String;)V

    .line 104
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$createView$searchItem$1;->this$0:Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->access$getOtherItem$p(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    .line 105
    :cond_0
    iget-object p0, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$createView$searchItem$1;->this$0:Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;

    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_1

    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_1
    return-void
.end method

.method public onSearchExpand()V
    .locals 2

    .line 97
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$createView$searchItem$1;->this$0:Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;

    const/4 v1, 0x1

    invoke-static {v0, v1}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->access$setSearching$p(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;Z)V

    .line 98
    iget-object p0, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$createView$searchItem$1;->this$0:Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;

    invoke-static {p0}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->access$getOtherItem$p(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    move-result-object p0

    if-eqz p0, :cond_0

    const/16 v0, 0x8

    invoke-virtual {p0, v0}, Landroid/view/View;->setVisibility(I)V

    :cond_0
    return-void
.end method

.method public onTextChanged(Landroid/widget/EditText;)V
    .locals 2

    .line 109
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$createView$searchItem$1;->this$0:Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;

    invoke-virtual {p1}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v1

    invoke-virtual {p1, v1}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p1

    invoke-static {v0, p1}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->access$setQuery$p(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;Ljava/lang/String;)V

    .line 110
    iget-object p0, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$createView$searchItem$1;->this$0:Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;

    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_0

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_0
    return-void
.end method
