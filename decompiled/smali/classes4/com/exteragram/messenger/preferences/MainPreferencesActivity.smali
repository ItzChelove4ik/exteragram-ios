.class public final Lcom/exteragram/messenger/preferences/MainPreferencesActivity;
.super Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;,
        Lcom/exteragram/messenger/preferences/MainPreferencesActivity$WhenMappings;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u0007\n\u0002\u0008\u0006\u0018\u00002\u00020\u0001:\u0001 B\u0007\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0008\u0010\u0006\u001a\u00020\u0007H\u0016J\u0008\u0010\u0008\u001a\u00020\tH\u0014J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000c\u001a\u00020\rH\u0014J\u001e\u0010\u000e\u001a\u00020\u000f2\u000c\u0010\u0010\u001a\u0008\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J0\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0014J0\u0010\u001d\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0014J\u0010\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\u0019H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"
    }
    d2 = {
        "Lcom/exteragram/messenger/preferences/MainPreferencesActivity;",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "<init>",
        "()V",
        "headerSettingsCell",
        "Lcom/exteragram/messenger/preferences/components/HeaderSettingsCell;",
        "getTitle",
        "",
        "needHideTitle",
        "",
        "createView",
        "Landroid/view/View;",
        "context",
        "Landroid/content/Context;",
        "fillItems",
        "",
        "items",
        "Ljava/util/ArrayList;",
        "Lorg/telegram/ui/Components/UItem;",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "onClick",
        "item",
        "view",
        "position",
        "",
        "x",
        "",
        "y",
        "onLongClick",
        "getListTopPadding",
        "topInset",
        "PreferenceItem",
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
.field private headerSettingsCell:Lcom/exteragram/messenger/preferences/components/HeaderSettingsCell;


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 35
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;-><init>()V

    return-void
.end method


# virtual methods
.method public createView(Landroid/content/Context;)Landroid/view/View;
    .locals 1

    .line 58
    new-instance v0, Lcom/exteragram/messenger/preferences/components/HeaderSettingsCell;

    invoke-direct {v0, p1}, Lcom/exteragram/messenger/preferences/components/HeaderSettingsCell;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity;->headerSettingsCell:Lcom/exteragram/messenger/preferences/components/HeaderSettingsCell;

    .line 59
    invoke-super {p0, p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->createView(Landroid/content/Context;)Landroid/view/View;

    move-result-object p1

    iput-object p1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    return-object p1
.end method

.method public fillItems(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V
    .locals 16
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/UItem;",
            ">;",
            "Lorg/telegram/ui/Components/UniversalAdapter;",
            ")V"
        }
    .end annotation

    move-object/from16 v0, p1

    move-object/from16 v1, p0

    .line 64
    iget-object v2, v1, Lcom/exteragram/messenger/preferences/MainPreferencesActivity;->headerSettingsCell:Lcom/exteragram/messenger/preferences/components/HeaderSettingsCell;

    if-nez v2, :cond_0

    const/4 v2, 0x0

    :cond_0
    const/16 v3, 0xc6

    invoke-static {v2, v3}, Lorg/telegram/ui/Components/UItem;->asCustomShadow(Landroid/view/View;I)Lorg/telegram/ui/Components/UItem;

    move-result-object v2

    sget-object v3, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->HEADER_CELL:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    invoke-virtual {v3}, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->getId()I

    move-result v3

    invoke-virtual {v2, v3}, Lorg/telegram/ui/Components/UItem;->setId(I)Lorg/telegram/ui/Components/UItem;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 66
    sget v2, Lorg/telegram/messenger/R$string;->Categories:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 67
    sget-object v2, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->GENERAL_CATEGORY:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    sget v3, Lorg/telegram/messenger/R$string;->General:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->msg_media:I

    const/16 v13, 0x778

    const/4 v14, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v12, 0x0

    const-string v9, "general"

    invoke-static/range {v1 .. v14}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 68
    sget-object v2, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->APPEARANCE_CATEGORY:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    sget v1, Lorg/telegram/messenger/R$string;->Appearance:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->msg_theme:I

    const-string v9, "appearance"

    move-object/from16 v1, p0

    invoke-static/range {v1 .. v14}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 69
    sget-object v2, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->CHATS_CATEGORY:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    sget v1, Lorg/telegram/messenger/R$string;->SearchAllChatsShort:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->msg_discussion:I

    const-string v9, "chats"

    move-object/from16 v1, p0

    invoke-static/range {v1 .. v14}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 70
    sget-object v1, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->isPluginEngineSupported()Z

    move-result v15

    sget-object v2, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->PLUGINS_CATEGORY:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    sget v1, Lorg/telegram/messenger/R$string;->Plugins:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->msg_plugins:I

    const-string v9, "plugins"

    move-object/from16 v1, p0

    invoke-static/range {v1 .. v14}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v2

    invoke-static {v0, v15, v2}, Lcom/exteragram/messenger/utils/ExtensionsKt;->addIf(Ljava/util/List;ZLjava/lang/Object;)V

    .line 71
    sget-object v2, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->OTHER_CATEGORY:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    sget v1, Lorg/telegram/messenger/R$string;->LocalOther:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->msg_fave:I

    const-string v9, "other"

    move-object/from16 v1, p0

    invoke-static/range {v1 .. v14}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 72
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 74
    sget v1, Lorg/telegram/messenger/R$string;->Links:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 75
    sget-object v2, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->CHANNEL:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    sget v1, Lorg/telegram/messenger/R$string;->ProfileChannel:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->msg_channel:I

    const/16 v13, 0x770

    const-string v5, "@exteraGram"

    const-string v9, "channel"

    move-object/from16 v1, p0

    invoke-static/range {v1 .. v14}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 76
    sget-object v2, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->FORUM:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    sget v1, Lorg/telegram/messenger/R$string;->SearchAllChatsShort:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->msg_groups:I

    const-string v5, "@exteraForum"

    const-string v9, "chat"

    move-object/from16 v1, p0

    invoke-static/range {v1 .. v14}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 77
    sget-object v2, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->CROWDIN:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    sget v1, Lorg/telegram/messenger/R$string;->Crowdin:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->msg_translate:I

    const-string v5, "Crowdin"

    const-string v9, "crowdin"

    move-object/from16 v1, p0

    invoke-static/range {v1 .. v14}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 78
    sget-object v2, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->WEBSITE:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    sget v1, Lorg/telegram/messenger/R$string;->Website:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->msg_language:I

    sget-object v7, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    const/16 v13, 0x750

    const-string v5, "exteraGram.app"

    const-string v9, "website"

    move-object/from16 v1, p0

    invoke-static/range {v1 .. v14}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 79
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public getListTopPadding(I)I
    .locals 0

    const/high16 p0, 0x41400000    # 12.0f

    .line 112
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result p0

    add-int/2addr p1, p0

    return p1
.end method

.method public getTitle()Ljava/lang/String;
    .locals 0

    .line 54
    sget p0, Lorg/telegram/messenger/R$string;->Preferences:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public needHideTitle()Z
    .locals 0

    const/4 p0, 0x1

    return p0
.end method

.method public onClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 0

    .line 83
    iget p2, p1, Lorg/telegram/ui/Components/UItem;->id:I

    if-lez p2, :cond_2

    invoke-static {}, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p3

    invoke-interface {p3}, Ljava/util/List;->size()I

    move-result p3

    if-le p2, p3, :cond_0

    goto/16 :goto_1

    .line 84
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p2

    iget p1, p1, Lorg/telegram/ui/Components/UItem;->id:I

    const/4 p3, 0x1

    sub-int/2addr p1, p3

    invoke-interface {p2, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    .line 85
    sget-object p2, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$WhenMappings;->$EnumSwitchMapping$0:[I

    invoke-virtual {p1}, Ljava/lang/Enum;->ordinal()I

    move-result p1

    aget p1, p2, p1

    packed-switch p1, :pswitch_data_0

    invoke-static {}, Lkotlin/LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;->m()V

    return-void

    .line 95
    :pswitch_0
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p0

    const-string p1, "https://exteraGram.app"

    invoke-static {p0, p1}, Lorg/telegram/messenger/browser/Browser;->openUrl(Landroid/content/Context;Ljava/lang/String;)V

    return-void

    .line 94
    :pswitch_1
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p0

    const-string p1, "https://crowdin.com/project/exteralocales"

    invoke-static {p0, p1}, Lorg/telegram/messenger/browser/Browser;->openUrl(Landroid/content/Context;Ljava/lang/String;)V

    return-void

    .line 93
    :pswitch_2
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object p1

    const-string p2, "exteraForum"

    invoke-virtual {p1, p2, p0, p3}, Lorg/telegram/messenger/MessagesController;->openByUserName(Ljava/lang/String;Lorg/telegram/ui/ActionBar/BaseFragment;I)V

    return-void

    .line 92
    :pswitch_3
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object p1

    const-string p2, "exteraGram"

    invoke-virtual {p1, p2, p0, p3}, Lorg/telegram/messenger/MessagesController;->openByUserName(Ljava/lang/String;Lorg/telegram/ui/ActionBar/BaseFragment;I)V

    return-void

    .line 91
    :pswitch_4
    new-instance p1, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;

    invoke-direct {p1}, Lcom/exteragram/messenger/preferences/OtherPreferencesActivity;-><init>()V

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void

    .line 90
    :pswitch_5
    new-instance p1, Lcom/exteragram/messenger/plugins/ui/PluginsActivity;

    invoke-direct {p1}, Lcom/exteragram/messenger/plugins/ui/PluginsActivity;-><init>()V

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void

    .line 89
    :pswitch_6
    new-instance p1, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;

    invoke-direct {p1}, Lcom/exteragram/messenger/preferences/chats/ChatsPreferencesActivity;-><init>()V

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void

    .line 88
    :pswitch_7
    new-instance p1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;

    invoke-direct {p1}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;-><init>()V

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void

    .line 87
    :pswitch_8
    new-instance p1, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;

    invoke-direct {p1}, Lcom/exteragram/messenger/preferences/GeneralPreferencesActivity;-><init>()V

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void

    .line 86
    :pswitch_9
    sget-boolean p1, Lorg/telegram/messenger/BuildVars;->PM_BUILD:Z

    if-nez p1, :cond_2

    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p0

    instance-of p1, p0, Lorg/telegram/ui/LaunchActivity;

    if-eqz p1, :cond_1

    check-cast p0, Lorg/telegram/ui/LaunchActivity;

    goto :goto_0

    :cond_1
    const/4 p0, 0x0

    :goto_0
    if-eqz p0, :cond_2

    invoke-virtual {p0, p3}, Lorg/telegram/ui/LaunchActivity;->checkAppUpdate(Z)V

    :cond_2
    :goto_1
    return-void

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public onLongClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)Z
    .locals 9

    .line 100
    iget v0, p1, Lorg/telegram/ui/Components/UItem;->id:I

    if-lez v0, :cond_2

    invoke-static {}, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v1

    if-le v0, v1, :cond_0

    goto :goto_0

    .line 101
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object v0

    iget v1, p1, Lorg/telegram/ui/Components/UItem;->id:I

    const/4 v2, 0x1

    sub-int/2addr v1, v2

    invoke-interface {v0, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    .line 102
    sget-object v1, Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;->HEADER_CELL:Lcom/exteragram/messenger/preferences/MainPreferencesActivity$PreferenceItem;

    if-ne v0, v1, :cond_1

    .line 103
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getUseSystemIconShape()Z

    move-result p0

    xor-int/2addr p0, v2

    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setUseSystemIconShape(Z)V

    const/4 p0, 0x3

    .line 104
    invoke-static {p0}, Lcom/exteragram/messenger/utils/system/VibratorUtils;->getType(I)I

    move-result p0

    invoke-virtual {p2, p0, v2}, Landroid/view/View;->performHapticFeedback(II)Z

    .line 105
    invoke-virtual {p2}, Landroid/view/View;->invalidate()V

    return v2

    :cond_1
    move-object v3, p0

    move-object v4, p1

    move-object v5, p2

    move v6, p3

    move v7, p4

    move v8, p5

    .line 108
    invoke-super/range {v3 .. v8}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->onLongClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)Z

    move-result p0

    return p0

    :cond_2
    :goto_0
    const/4 p0, 0x0

    return p0
.end method
