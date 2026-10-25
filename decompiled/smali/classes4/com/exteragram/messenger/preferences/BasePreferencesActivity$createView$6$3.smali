.class public final Lcom/exteragram/messenger/preferences/BasePreferencesActivity$createView$6$3;
.super Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;
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
        "\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0002*\u0001\u0000\u0008\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0008\u001a\u00020\u0007H\u0016\u00a8\u0006\t"
    }
    d2 = {
        "com/exteragram/messenger/preferences/BasePreferencesActivity$createView$6$3",
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
.field final synthetic this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V
    .locals 0

    iput-object p1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$createView$6$3;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    .line 149
    invoke-direct {p0}, Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;-><init>()V

    return-void
.end method


# virtual methods
.method public onScrolled(Landroidx/recyclerview/widget/RecyclerView;II)V
    .locals 1

    .line 151
    iget-object p1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$createView$6$3;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    const/4 v0, 0x1

    invoke-static {p1, v0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->access$updateHeaderFade(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Z)V

    .line 152
    sget p1, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v0, 0x1f

    if-lt p1, v0, :cond_0

    iget-object p1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$createView$6$3;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-static {p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->access$getScrollableViewNoiseSuppressor$p(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 153
    iget-object p1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$createView$6$3;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-static {p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->access$getScrollableViewNoiseSuppressor$p(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;

    move-result-object p1

    int-to-float p2, p2

    int-to-float p3, p3

    invoke-virtual {p1, p2, p3}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;->onScrolled(FF)V

    .line 154
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$createView$6$3;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-static {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->access$blur3_InvalidateBlur(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V

    :cond_0
    return-void
.end method
