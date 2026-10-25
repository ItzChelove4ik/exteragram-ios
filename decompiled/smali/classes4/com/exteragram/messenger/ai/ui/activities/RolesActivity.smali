.class public final Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;
.super Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$Companion;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0010\u0007\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\u0006\u0018\u0000 -2\u00020\u00012\u00020\u0002:\u0001-B\u0007\u00a2\u0006\u0004\u0008\u0003\u0010\u0004J\u0008\u0010\u0005\u001a\u00020\u0006H\u0016J\u0008\u0010\u0007\u001a\u00020\u0008H\u0016J5\u0010\t\u001a\u00020\u00082\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000c\u001a\u00020\u000b2\u0016\u0010\r\u001a\u000c\u0012\u0008\u0008\u0001\u0012\u0004\u0018\u00010\u000f0\u000e\"\u0004\u0018\u00010\u000fH\u0016\u00a2\u0006\u0002\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\u0008\u0010\u0015\u001a\u00020\u0016H\u0016J\u0008\u0010\u0017\u001a\u00020\u0006H\u0014J\u001e\u0010\u0018\u001a\u00020\u00082\u000c\u0010\u0019\u001a\u0008\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u001dH\u0014J0\u0010\u001e\u001a\u00020\u00082\u0006\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#H\u0014J0\u0010%\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#H\u0014J\u0010\u0010&\u001a\u00020\u00082\u0006\u0010\'\u001a\u00020(H\u0002J\u0010\u0010)\u001a\u00020\u00082\u0006\u0010\'\u001a\u00020(H\u0002J\u0010\u0010*\u001a\u00020\u00082\u0006\u0010\'\u001a\u00020(H\u0002J\u0012\u0010+\u001a\u00020\u00082\u0008\u0010\'\u001a\u0004\u0018\u00010(H\u0002J\u0010\u0010,\u001a\u00020\u00082\u0006\u0010\'\u001a\u00020(H\u0002\u00a8\u0006."
    }
    d2 = {
        "Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;",
        "<init>",
        "()V",
        "onFragmentCreate",
        "",
        "onFragmentDestroy",
        "",
        "didReceivedNotification",
        "id",
        "",
        "account",
        "args",
        "",
        "",
        "(II[Ljava/lang/Object;)V",
        "createView",
        "Landroid/view/View;",
        "context",
        "Landroid/content/Context;",
        "getTitle",
        "",
        "needHideTitle",
        "fillItems",
        "items",
        "Ljava/util/ArrayList;",
        "Lorg/telegram/ui/Components/UItem;",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "onClick",
        "item",
        "view",
        "position",
        "x",
        "",
        "y",
        "onLongClick",
        "confirmDeleteRole",
        "role",
        "Lcom/exteragram/messenger/ai/data/Role;",
        "deleteRole",
        "selectRole",
        "showRoleAlert",
        "showRolePreview",
        "Companion",
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
        "SMAP\nRolesActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RolesActivity.kt\ncom/exteragram/messenger/ai/ui/activities/RolesActivity\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,190:1\n1915#2,2:191\n1915#2,2:193\n*S KotlinDebug\n*F\n+ 1 RolesActivity.kt\ncom/exteragram/messenger/ai/ui/activities/RolesActivity\n*L\n84#1:191,2\n92#1:193,2\n*E\n"
    }
.end annotation


# static fields
.field public static final Companion:Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$Companion;


# direct methods
.method public static $r8$lambda$0cBzozEYAxC9_T7XloEOX8p4yJI(Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;Lcom/exteragram/messenger/ai/data/Role;)V
    .locals 0

    .line 117
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;->confirmDeleteRole(Lcom/exteragram/messenger/ai/data/Role;)V

    return-void
.end method

.method public static $r8$lambda$CeDw4zHLMWfIne7QSJRetR1ZxwU(Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;Lcom/exteragram/messenger/ai/data/Role;Lorg/telegram/ui/ActionBar/AlertDialog;I)V
    .locals 0

    .line 130
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;->deleteRole(Lcom/exteragram/messenger/ai/data/Role;)V

    return-void
.end method

.method public static $r8$lambda$M65RxkksXxqX5qSjSDo1RBn0BTI(Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;Lcom/exteragram/messenger/ai/data/Role;)V
    .locals 0

    .line 112
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;->showRoleAlert(Lcom/exteragram/messenger/ai/data/Role;)V

    return-void
.end method

.method public static $r8$lambda$T3SxBP3lIsmBLz88Ugcp0k58Xoo(Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;Lcom/exteragram/messenger/ai/data/Role;Landroid/view/View;)V
    .locals 0

    .line 85
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;->selectRole(Lcom/exteragram/messenger/ai/data/Role;)V

    return-void
.end method

.method public static $r8$lambda$bmawYMqP3qLjVbtTI8yheOmR9xA(Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;Lcom/exteragram/messenger/ai/data/Role;Landroid/view/View;)V
    .locals 0

    .line 93
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;->selectRole(Lcom/exteragram/messenger/ai/data/Role;)V

    return-void
.end method

.method public static $r8$lambda$srPp-9OP6XUqRCfRYkVYSV_y27Y(Lcom/exteragram/messenger/ai/data/Role;Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)Ljava/lang/Boolean;
    .locals 1

    .line 165
    new-instance v0, Lcom/exteragram/messenger/ai/data/Role;

    invoke-direct {v0, p2, p3}, Lcom/exteragram/messenger/ai/data/Role;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    if-eqz p4, :cond_0

    invoke-virtual {p4}, Ljava/lang/Long;->longValue()J

    move-result-wide p2

    goto :goto_0

    :cond_0
    const-wide/16 p2, 0x0

    :goto_0
    invoke-virtual {v0, p2, p3}, Lcom/exteragram/messenger/ai/data/Role;->setEmojiId(J)Lcom/exteragram/messenger/ai/data/Role;

    move-result-object p2

    const/4 p3, 0x0

    if-eqz p0, :cond_1

    .line 166
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/data/Role;->isSelected()Z

    move-result p4

    if-eqz p4, :cond_1

    const/4 p4, 0x1

    goto :goto_1

    :cond_1
    move p4, p3

    :goto_1
    if-eqz p0, :cond_2

    .line 167
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/data/Role;->isSuggestion()Z

    move-result v0

    if-nez v0, :cond_2

    .line 168
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object v0

    invoke-virtual {v0, p0, p2}, Lcom/exteragram/messenger/ai/AiController;->updateRole(Lcom/exteragram/messenger/ai/data/Role;Lcom/exteragram/messenger/ai/data/Role;)Z

    move-result p0

    goto :goto_2

    .line 170
    :cond_2
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object p0

    invoke-virtual {p0, p2}, Lcom/exteragram/messenger/ai/AiController;->addRole(Lcom/exteragram/messenger/ai/data/Role;)Z

    move-result p0

    :goto_2
    if-eqz p0, :cond_4

    if-eqz p4, :cond_3

    .line 174
    invoke-static {p2}, Lcom/exteragram/messenger/ai/AiConfig;->setSelectedAiRole(Lcom/exteragram/messenger/ai/data/Role;)V

    .line 175
    :cond_3
    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget p2, Lorg/telegram/messenger/NotificationCenter;->rolesUpdated:I

    new-array p3, p3, [Ljava/lang/Object;

    invoke-virtual {p1, p2, p3}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 177
    :cond_4
    invoke-static {p0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$y0aGRp8REUtt26t0n_9jHeDKEwQ(Lcom/exteragram/messenger/ai/data/Role;Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;)V
    .locals 2

    .line 114
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/data/Role;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/data/Role;->getPrompt()Ljava/lang/String;

    move-result-object p0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, "\n"

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->addToClipboard(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_0

    .line 115
    invoke-static {p1}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget p1, Lorg/telegram/messenger/R$string;->TextCopied:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/BulletinFactory;->createCopyBulletin(Ljava/lang/String;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    :cond_0
    return-void
.end method

.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;->Companion:Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$Companion;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .line 40
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;-><init>()V

    return-void
.end method

.method public static final synthetic access$showRoleAlert(Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;Lcom/exteragram/messenger/ai/data/Role;)V
    .locals 0

    .line 40
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;->showRoleAlert(Lcom/exteragram/messenger/ai/data/Role;)V

    return-void
.end method

.method private final confirmDeleteRole(Lcom/exteragram/messenger/ai/data/Role;)V
    .locals 3

    .line 126
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 127
    :cond_0
    new-instance v1, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    invoke-direct {v1, v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 128
    sget v0, Lorg/telegram/messenger/R$string;->Delete:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v0}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object v0

    .line 129
    sget v1, Lorg/telegram/messenger/R$string;->DeleteRoleInfo:I

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Role;->getName()Ljava/lang/String;

    move-result-object v2

    filled-new-array {v2}, [Ljava/lang/Object;

    move-result-object v2

    invoke-static {v1, v2}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->replaceTags(Ljava/lang/String;)Landroid/text/SpannableStringBuilder;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object v0

    .line 130
    sget v1, Lorg/telegram/messenger/R$string;->Delete:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$$ExternalSyntheticLambda6;

    invoke-direct {v2, p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$$ExternalSyntheticLambda6;-><init>(Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;Lcom/exteragram/messenger/ai/data/Role;)V

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object p1

    .line 131
    sget v0, Lorg/telegram/messenger/R$string;->Cancel:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {p1, v0, v1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Lorg/telegram/ui/ActionBar/AlertDialog$OnButtonClickListener;)Lorg/telegram/ui/ActionBar/AlertDialog$Builder;

    move-result-object p1

    .line 132
    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/AlertDialog$Builder;->create()Lorg/telegram/ui/ActionBar/AlertDialog;

    move-result-object p1

    .line 133
    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->showDialog(Landroid/app/Dialog;)Landroid/app/Dialog;

    const/4 p0, -0x1

    .line 134
    invoke-virtual {p1, p0}, Lorg/telegram/ui/ActionBar/AlertDialog;->getButton(I)Landroid/view/View;

    move-result-object p0

    instance-of p1, p0, Landroid/widget/TextView;

    if-eqz p1, :cond_1

    move-object v1, p0

    check-cast v1, Landroid/widget/TextView;

    :cond_1
    if-eqz v1, :cond_2

    sget p0, Lorg/telegram/ui/ActionBar/Theme;->key_text_RedBold:I

    invoke-static {p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result p0

    invoke-virtual {v1, p0}, Landroid/widget/TextView;->setTextColor(I)V

    :cond_2
    :goto_0
    return-void
.end method

.method private final deleteRole(Lcom/exteragram/messenger/ai/data/Role;)V
    .locals 2

    .line 138
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Role;->isSelected()Z

    move-result v0

    .line 139
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object v1

    invoke-virtual {v1, p1}, Lcom/exteragram/messenger/ai/AiController;->removeRole(Lcom/exteragram/messenger/ai/data/Role;)Z

    move-result p1

    if-nez p1, :cond_0

    goto :goto_0

    :cond_0
    if-eqz v0, :cond_1

    .line 142
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object p1

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/AiController;->getSuggestedRoles()Ljava/util/List;

    move-result-object p1

    const/4 v0, 0x0

    invoke-interface {p1, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/exteragram/messenger/ai/data/Role;

    invoke-static {p1}, Lcom/exteragram/messenger/ai/AiConfig;->setSelectedAiRole(Lcom/exteragram/messenger/ai/data/Role;)V

    .line 144
    :cond_1
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_2

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_2
    :goto_0
    return-void
.end method

.method private final selectRole(Lcom/exteragram/messenger/ai/data/Role;)V
    .locals 1

    .line 148
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Role;->isSelected()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 149
    :cond_0
    invoke-static {p1}, Lcom/exteragram/messenger/ai/AiConfig;->setSelectedAiRole(Lcom/exteragram/messenger/ai/data/Role;)V

    .line 150
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_1

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_1
    :goto_0
    return-void
.end method

.method private final showRoleAlert(Lcom/exteragram/messenger/ai/data/Role;)V
    .locals 10

    .line 154
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_0

    return-void

    .line 156
    :cond_0
    new-instance v1, Lorg/telegram/ui/Components/AIEditorAlert$CreateAiStyleAlert;

    iget-object v2, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-direct {v1, v0, v2}, Lorg/telegram/ui/Components/AIEditorAlert$CreateAiStyleAlert;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 158
    const-string v0, ""

    if-eqz p1, :cond_1

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Role;->getName()Ljava/lang/String;

    move-result-object v2

    if-nez v2, :cond_2

    :cond_1
    move-object v2, v0

    :cond_2
    if-eqz p1, :cond_3

    .line 159
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Role;->getPrompt()Ljava/lang/String;

    move-result-object v3

    if-nez v3, :cond_4

    :cond_3
    move-object v3, v0

    :cond_4
    if-eqz p1, :cond_5

    .line 160
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Role;->getEmojiId()J

    move-result-wide v4

    goto :goto_0

    :cond_5
    const-wide/16 v4, 0x0

    :goto_0
    if-eqz p1, :cond_6

    .line 161
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Role;->isSuggestion()Z

    move-result v0

    if-nez v0, :cond_6

    const/4 v0, 0x1

    :goto_1
    move v6, v0

    goto :goto_2

    :cond_6
    const/4 v0, 0x0

    goto :goto_1

    .line 157
    :goto_2
    new-instance v9, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$$ExternalSyntheticLambda3;

    invoke-direct {v9, p1, p0}, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/ai/data/Role;Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;)V

    const/16 v7, 0x40

    const/16 v8, 0x400

    invoke-virtual/range {v1 .. v9}, Lorg/telegram/ui/Components/AIEditorAlert$CreateAiStyleAlert;->setLocalStyle(Ljava/lang/String;Ljava/lang/String;JZIILorg/telegram/messenger/Utilities$Callback3Return;)Lorg/telegram/ui/Components/AIEditorAlert$CreateAiStyleAlert;

    move-result-object p0

    .line 179
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BottomSheet;->show()V

    return-void
.end method

.method private final showRolePreview(Lcom/exteragram/messenger/ai/data/Role;)V
    .locals 8

    .line 183
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_0

    return-void

    .line 185
    :cond_0
    new-instance v1, Lorg/telegram/ui/Components/AIEditorAlert$CreateAiStyleAlert;

    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-direct {v1, v0, p0}, Lorg/telegram/ui/Components/AIEditorAlert$CreateAiStyleAlert;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 186
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Role;->getName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Role;->getPrompt()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Role;->getEmojiId()J

    move-result-wide v4

    const/16 v6, 0x40

    const/16 v7, 0x400

    invoke-virtual/range {v1 .. v7}, Lorg/telegram/ui/Components/AIEditorAlert$CreateAiStyleAlert;->setLocalStylePreview(Ljava/lang/String;Ljava/lang/String;JII)Lorg/telegram/ui/Components/AIEditorAlert$CreateAiStyleAlert;

    move-result-object p0

    .line 187
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BottomSheet;->show()V

    return-void
.end method


# virtual methods
.method public createView(Landroid/content/Context;)Landroid/view/View;
    .locals 3

    .line 64
    invoke-super {p0, p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->createView(Landroid/content/Context;)Landroid/view/View;

    move-result-object p1

    .line 65
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/ActionBar;->createMenu()Lorg/telegram/ui/ActionBar/ActionBarMenu;

    move-result-object v0

    const/4 v1, 0x0

    sget v2, Lorg/telegram/messenger/R$drawable;->msg_add:I

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/ActionBar/ActionBarMenu;->addItem(II)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    .line 66
    iget-object v0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    new-instance v1, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$createView$1;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$createView$1;-><init>(Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;)V

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/ActionBar;->setActionBarMenuOnItemClick(Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;)V

    return-object p1
.end method

.method public varargs didReceivedNotification(II[Ljava/lang/Object;)V
    .locals 0

    .line 58
    sget p2, Lorg/telegram/messenger/NotificationCenter;->rolesUpdated:I

    if-ne p1, p2, :cond_0

    .line 59
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_0

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_0
    return-void
.end method

.method public fillItems(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V
    .locals 3
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

    .line 82
    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;->getTitle()Ljava/lang/String;

    move-result-object p2

    sget v0, Lorg/telegram/messenger/R$string;->RolesInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    const-string v1, "exteraGramPlaceholders"

    const-string v2, "\ud83c\udfad"

    invoke-static {p2, v0, v1, v2}, Lorg/telegram/ui/Components/UItem;->asTopView(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/String;Ljava/lang/String;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 83
    sget p2, Lorg/telegram/messenger/R$string;->Suggestions:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    invoke-static {p2}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 84
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object p2

    invoke-virtual {p2}, Lcom/exteragram/messenger/ai/AiController;->getSuggestedRoles()Ljava/util/List;

    move-result-object p2

    check-cast p2, Ljava/lang/Iterable;

    .line 1915
    invoke-interface {p2}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p2

    :goto_0
    invoke-interface {p2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {p2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/ai/data/Role;

    .line 85
    new-instance v1, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$$ExternalSyntheticLambda4;

    invoke-direct {v1, p0, v0}, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;Lcom/exteragram/messenger/ai/data/Role;)V

    invoke-static {v0, v1}, Lcom/exteragram/messenger/ai/ui/components/RoleCell$Factory;->asRoleCell(Lcom/exteragram/messenger/ai/data/Role;Landroid/view/View$OnClickListener;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_0
    const/4 p2, 0x0

    .line 87
    invoke-static {p2}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 89
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object p2

    invoke-virtual {p2}, Lcom/exteragram/messenger/ai/AiController;->getRoles()Ljava/util/List;

    move-result-object p2

    .line 90
    move-object v0, p2

    check-cast v0, Ljava/util/Collection;

    invoke-interface {v0}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_1

    .line 91
    sget v0, Lorg/telegram/messenger/R$string;->Roles:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 92
    check-cast p2, Ljava/lang/Iterable;

    .line 1915
    invoke-interface {p2}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p2

    :goto_1
    invoke-interface {p2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {p2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/ai/data/Role;

    .line 93
    new-instance v1, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$$ExternalSyntheticLambda5;

    invoke-direct {v1, p0, v0}, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;Lcom/exteragram/messenger/ai/data/Role;)V

    invoke-static {v0, v1}, Lcom/exteragram/messenger/ai/ui/components/RoleCell$Factory;->asRoleCell(Lcom/exteragram/messenger/ai/data/Role;Landroid/view/View$OnClickListener;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_1

    :cond_1
    return-void
.end method

.method public getTitle()Ljava/lang/String;
    .locals 0

    .line 77
    sget p0, Lorg/telegram/messenger/R$string;->Roles:I

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

    .line 99
    iget-object p1, p1, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    instance-of p2, p1, Lcom/exteragram/messenger/ai/data/Role;

    if-eqz p2, :cond_0

    check-cast p1, Lcom/exteragram/messenger/ai/data/Role;

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    if-nez p1, :cond_1

    return-void

    .line 100
    :cond_1
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Role;->isSuggestion()Z

    move-result p2

    if-eqz p2, :cond_2

    .line 101
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;->showRolePreview(Lcom/exteragram/messenger/ai/data/Role;)V

    return-void

    .line 103
    :cond_2
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;->showRoleAlert(Lcom/exteragram/messenger/ai/data/Role;)V

    return-void
.end method

.method public onFragmentCreate()Z
    .locals 2

    .line 48
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->rolesUpdated:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 49
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentCreate()Z

    move-result p0

    return p0
.end method

.method public onFragmentDestroy()V
    .locals 2

    .line 53
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->rolesUpdated:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 54
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentDestroy()V

    return-void
.end method

.method public onLongClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)Z
    .locals 1

    .line 108
    iget-object p1, p1, Lorg/telegram/ui/Components/UItem;->object:Ljava/lang/Object;

    instance-of p3, p1, Lcom/exteragram/messenger/ai/data/Role;

    if-eqz p3, :cond_0

    check-cast p1, Lcom/exteragram/messenger/ai/data/Role;

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    if-eqz p1, :cond_3

    .line 109
    invoke-virtual {p1}, Lcom/exteragram/messenger/ai/data/Role;->isSuggestion()Z

    move-result p3

    if-eqz p3, :cond_1

    goto :goto_2

    .line 111
    :cond_1
    invoke-static {p0, p2}, Lorg/telegram/ui/Components/ItemOptions;->makeOptions(Lorg/telegram/ui/ActionBar/BaseFragment;Landroid/view/View;)Lorg/telegram/ui/Components/ItemOptions;

    move-result-object p3

    .line 112
    sget p4, Lorg/telegram/messenger/R$drawable;->msg_edit:I

    sget p5, Lorg/telegram/messenger/R$string;->Edit:I

    invoke-static {p5}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p5

    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;Lcom/exteragram/messenger/ai/data/Role;)V

    invoke-virtual {p3, p4, p5, v0}, Lorg/telegram/ui/Components/ItemOptions;->add(ILjava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 113
    sget p4, Lorg/telegram/messenger/R$drawable;->msg_copy:I

    sget p5, Lorg/telegram/messenger/R$string;->Copy:I

    invoke-static {p5}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p5

    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$$ExternalSyntheticLambda1;

    invoke-direct {v0, p1, p0}, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/ai/data/Role;Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;)V

    invoke-virtual {p3, p4, p5, v0}, Lorg/telegram/ui/Components/ItemOptions;->add(ILjava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 117
    sget p4, Lorg/telegram/messenger/R$drawable;->msg_delete:I

    sget p5, Lorg/telegram/messenger/R$string;->Delete:I

    invoke-static {p5}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p5

    new-instance v0, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$$ExternalSyntheticLambda2;

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;Lcom/exteragram/messenger/ai/data/Role;)V

    const/4 p1, 0x1

    invoke-virtual {p3, p4, p5, p1, v0}, Lorg/telegram/ui/Components/ItemOptions;->add(ILjava/lang/CharSequence;ZLjava/lang/Runnable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 118
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    invoke-virtual {p0, p2}, Lorg/telegram/ui/Components/RecyclerListView;->getClipBackground(Landroid/view/View;)Landroid/graphics/drawable/Drawable;

    move-result-object p0

    invoke-virtual {p3, p0}, Lorg/telegram/ui/Components/ItemOptions;->setScrimViewBackground(Landroid/graphics/drawable/Drawable;)Lorg/telegram/ui/Components/ItemOptions;

    .line 119
    sget-boolean p0, Lorg/telegram/messenger/LocaleController;->isRTL:Z

    if-eqz p0, :cond_2

    const/4 p0, 0x3

    goto :goto_1

    :cond_2
    const/4 p0, 0x5

    :goto_1
    invoke-virtual {p3, p0}, Lorg/telegram/ui/Components/ItemOptions;->setGravity(I)Lorg/telegram/ui/Components/ItemOptions;

    .line 120
    invoke-virtual {p3}, Lorg/telegram/ui/Components/ItemOptions;->show()Lorg/telegram/ui/Components/ItemOptions;

    return p1

    :cond_3
    :goto_2
    const/4 p0, 0x0

    return p0
.end method
