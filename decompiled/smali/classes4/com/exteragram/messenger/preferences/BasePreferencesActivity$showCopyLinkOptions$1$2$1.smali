.class public final Lcom/exteragram/messenger/preferences/BasePreferencesActivity$showCopyLinkOptions$1$2$1;
.super Lorg/telegram/ui/Components/ShareAlert;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showCopyLinkOptions(Landroid/view/View;Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\u0008\n\u0018\u00002\u00020\u0001J2\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\u00082\u0008\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u000cH\u0014\u00a8\u0006\r"
    }
    d2 = {
        "com/exteragram/messenger/preferences/BasePreferencesActivity$showCopyLinkOptions$1$2$1",
        "Lorg/telegram/ui/Components/ShareAlert;",
        "onSend",
        "",
        "dialogIds",
        "Landroidx/collection/LongSparseArray;",
        "Lorg/telegram/tgnet/TLRPC$Dialog;",
        "count",
        "",
        "topic",
        "Lorg/telegram/tgnet/TLRPC$TL_forumTopic;",
        "showToast",
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
.method public constructor <init>(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V
    .locals 8

    iput-object p2, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$showCopyLinkOptions$1$2$1;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    const/4 v4, 0x0

    const/4 v6, 0x0

    const/4 v2, 0x0

    move-object v5, p1

    move-object v0, p0

    move-object v3, p1

    move-object v1, p3

    move-object v7, p4

    .line 383
    invoke-direct/range {v0 .. v7}, Lorg/telegram/ui/Components/ShareAlert;-><init>(Landroid/content/Context;Ljava/util/ArrayList;Ljava/lang/String;ZLjava/lang/String;ZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    return-void
.end method


# virtual methods
.method public onSend(Landroidx/collection/LongSparseArray;ILorg/telegram/tgnet/TLRPC$TL_forumTopic;Z)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroidx/collection/LongSparseArray<",
            "Lorg/telegram/tgnet/TLRPC$Dialog;",
            ">;I",
            "Lorg/telegram/tgnet/TLRPC$TL_forumTopic;",
            "Z)V"
        }
    .end annotation

    if-nez p4, :cond_0

    return-void

    :cond_0
    const/4 p3, 0x0

    const/4 p4, 0x1

    if-eqz p1, :cond_3

    .line 388
    invoke-virtual {p1}, Landroidx/collection/LongSparseArray;->size()I

    move-result v0

    if-ne v0, p4, :cond_3

    .line 389
    invoke-virtual {p1, p3}, Landroidx/collection/LongSparseArray;->valueAt(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lorg/telegram/tgnet/TLRPC$Dialog;

    iget-wide p1, p1, Lorg/telegram/tgnet/TLRPC$Dialog;->id:J

    const-wide/16 v0, 0x0

    cmp-long v0, p1, v0

    if-eqz v0, :cond_2

    .line 390
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$showCopyLinkOptions$1$2$1;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getUserConfig()Lorg/telegram/messenger/UserConfig;

    move-result-object v0

    iget-wide v0, v0, Lorg/telegram/messenger/UserConfig;->clientUserId:J

    cmp-long v0, p1, v0

    if-nez v0, :cond_1

    goto :goto_0

    .line 393
    :cond_1
    sget v0, Lorg/telegram/messenger/R$string;->SettingLinkToUser:I

    iget-object v1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$showCopyLinkOptions$1$2$1;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-virtual {v1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object v1

    invoke-virtual {v1, p1, p2, p4}, Lorg/telegram/messenger/MessagesController;->getPeerName(JZ)Ljava/lang/String;

    move-result-object p1

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object p1

    invoke-static {v0, p1}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    goto :goto_1

    .line 391
    :cond_2
    :goto_0
    sget p1, Lorg/telegram/messenger/R$string;->SettingLinkToSavedMessages:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    goto :goto_1

    .line 395
    :cond_3
    sget p1, Lorg/telegram/messenger/R$string;->SettingLinkToChats:I

    const-string v0, "Chats"

    new-array v1, p3, [Ljava/lang/Object;

    invoke-static {v0, p2, v1}, Lorg/telegram/messenger/LocaleController;->formatPluralString(Ljava/lang/String;I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    filled-new-array {p2}, [Ljava/lang/Object;

    move-result-object p2

    invoke-static {p1, p2}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    .line 398
    :goto_1
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$showCopyLinkOptions$1$2$1;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget p2, Lorg/telegram/messenger/R$raw;->forward:I

    invoke-virtual {p0, p2, p1}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 399
    iput-boolean p3, p0, Lorg/telegram/ui/Components/Bulletin;->hideAfterBottomSheet:Z

    .line 400
    invoke-virtual {p0, p4}, Lorg/telegram/ui/Components/Bulletin;->show(Z)Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method
