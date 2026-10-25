.class public final Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$createView$2;
.super Lcom/exteragram/messenger/preferences/chats/components/StickerShapeCell;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->createView(Landroid/content/Context;)Landroid/view/View;
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
        "com/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$createView$2",
        "Lcom/exteragram/messenger/preferences/chats/components/StickerShapeCell;",
        "updateStickerPreview",
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
.field final synthetic this$0:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;


# direct methods
.method public constructor <init>(Landroid/content/Context;Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)V
    .locals 0

    iput-object p2, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$createView$2;->this$0:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;

    .line 284
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/preferences/chats/components/StickerShapeCell;-><init>(Landroid/content/Context;)V

    return-void
.end method


# virtual methods
.method public updateStickerPreview()V
    .locals 2

    .line 286
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$createView$2;->this$0:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->access$getParentLayout$p$s-146837972(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)Lorg/telegram/ui/ActionBar/INavigationLayout;

    move-result-object v0

    const/4 v1, 0x0

    invoke-interface {v0, v1}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    .line 287
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity$createView$2;->this$0:Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;

    invoke-static {p0}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;->access$getStickerSizeCell$p(Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;)Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;

    move-result-object p0

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/chats/components/SliderPreviewCell;->invalidate()V

    :cond_0
    return-void
.end method
