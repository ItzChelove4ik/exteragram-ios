.class public final Lcom/exteragram/messenger/export/ui/ExportActivity;
.super Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/export/ui/ExportActivity$Companion;,
        Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;,
        Lcom/exteragram/messenger/export/ui/ExportActivity$WhenMappings;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\u0008\u0002\n\u0002\u0010\u000e\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0007\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0006\u0018\u0000 72\u00020\u00012\u00020\u0002:\u000267B\u0013\u0012\n\u0008\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\u0008\u0005\u0010\u0006J\u0008\u0010\r\u001a\u00020\u000eH\u0016J\u0008\u0010\u000f\u001a\u00020\u0010H\u0016J5\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0016\u0010\u0015\u001a\u000c\u0012\u0008\u0008\u0001\u0012\u0004\u0018\u00010\u00170\u0016\"\u0004\u0018\u00010\u0017H\u0016\u00a2\u0006\u0002\u0010\u0018J\u0008\u0010\u0019\u001a\u00020\u001aH\u0016J\u001e\u0010\u001b\u001a\u00020\u00102\u000c\u0010\u001c\u001a\u0008\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 H\u0014J0\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\'H\u0014J\"\u0010)\u001a\u00020\u00102\u0006\u0010*\u001a\u00020\u00132\u0006\u0010+\u001a\u00020\u00132\u0008\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u0010\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u0013H\u0002J\u0010\u00101\u001a\u0002022\u0006\u00100\u001a\u00020\u0013H\u0002J\u0010\u00103\u001a\u00020/2\u0006\u00100\u001a\u00020\u0013H\u0002J\u0010\u00104\u001a\u0002022\u0006\u00100\u001a\u00020\u0013H\u0002J\u0008\u00105\u001a\u00020\u0013H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0008X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000c\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00068"
    }
    d2 = {
        "Lcom/exteragram/messenger/export/ui/ExportActivity;",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;",
        "peer",
        "Lorg/telegram/tgnet/TLRPC$InputPeer;",
        "<init>",
        "(Lorg/telegram/tgnet/TLRPC$InputPeer;)V",
        "settings",
        "Lcom/exteragram/messenger/export/ExportSettings;",
        "exportSettings",
        "Lcom/exteragram/messenger/preferences/SwitchGroup;",
        "chatsSettings",
        "mediaSettings",
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
        "getTitle",
        "",
        "fillItems",
        "items",
        "Ljava/util/ArrayList;",
        "Lorg/telegram/ui/Components/UItem;",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "onClick",
        "item",
        "view",
        "Landroid/view/View;",
        "position",
        "x",
        "",
        "y",
        "onActivityResultFragment",
        "requestCode",
        "resultCode",
        "data",
        "Landroid/content/Intent;",
        "type",
        "Ljava/util/function/BooleanSupplier;",
        "flag",
        "setType",
        "Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;",
        "media",
        "setMedia",
        "getIndexOfFormat",
        "ExportItem",
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
        "SMAP\nExportActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExportActivity.kt\ncom/exteragram/messenger/export/ui/ExportActivity\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,235:1\n1#2:236\n*E\n"
    }
.end annotation


# static fields
.field public static final Companion:Lcom/exteragram/messenger/export/ui/ExportActivity$Companion;

.field private static final formats:[Ljava/lang/CharSequence;


# instance fields
.field private final chatsSettings:Lcom/exteragram/messenger/preferences/SwitchGroup;

.field private final exportSettings:Lcom/exteragram/messenger/preferences/SwitchGroup;

.field private final mediaSettings:Lcom/exteragram/messenger/preferences/SwitchGroup;

.field private final peer:Lorg/telegram/tgnet/TLRPC$InputPeer;

.field private final settings:Lcom/exteragram/messenger/export/ExportSettings;


# direct methods
.method public static $r8$lambda$CSL7zr0qaOxhjff6yGhcKdv3rX4(Lcom/exteragram/messenger/export/ui/ExportActivity;IZ)V
    .locals 0

    .line 221
    iget-object p0, p0, Lcom/exteragram/messenger/export/ui/ExportActivity;->settings:Lcom/exteragram/messenger/export/ExportSettings;

    if-eqz p2, :cond_0

    iget p2, p0, Lcom/exteragram/messenger/export/ExportSettings;->types:I

    or-int/2addr p1, p2

    goto :goto_0

    :cond_0
    iget p2, p0, Lcom/exteragram/messenger/export/ExportSettings;->types:I

    not-int p1, p1

    and-int/2addr p1, p2

    :goto_0
    iput p1, p0, Lcom/exteragram/messenger/export/ExportSettings;->types:I

    return-void
.end method

.method public static $r8$lambda$anjpbMsWdHeNsPc8nKOvMM4OfoA(Lcom/exteragram/messenger/export/ui/ExportActivity;I)V
    .locals 1

    .line 177
    iget-object p0, p0, Lcom/exteragram/messenger/export/ui/ExportActivity;->settings:Lcom/exteragram/messenger/export/ExportSettings;

    if-eqz p1, :cond_1

    const/4 v0, 0x1

    if-eq p1, v0, :cond_0

    .line 180
    sget-object p1, Lcom/exteragram/messenger/export/output/AbstractWriter$Format;->HtmlAndJson:Lcom/exteragram/messenger/export/output/AbstractWriter$Format;

    goto :goto_0

    .line 179
    :cond_0
    sget-object p1, Lcom/exteragram/messenger/export/output/AbstractWriter$Format;->Json:Lcom/exteragram/messenger/export/output/AbstractWriter$Format;

    goto :goto_0

    .line 178
    :cond_1
    sget-object p1, Lcom/exteragram/messenger/export/output/AbstractWriter$Format;->Html:Lcom/exteragram/messenger/export/output/AbstractWriter$Format;

    .line 177
    :goto_0
    iput-object p1, p0, Lcom/exteragram/messenger/export/ExportSettings;->format:Lcom/exteragram/messenger/export/output/AbstractWriter$Format;

    return-void
.end method

.method public static $r8$lambda$lelC_Sfy9tZdhla-7ngXsAJQz2w(Lcom/exteragram/messenger/export/ui/ExportActivity;IZ)V
    .locals 0

    .line 227
    iget-object p0, p0, Lcom/exteragram/messenger/export/ui/ExportActivity;->settings:Lcom/exteragram/messenger/export/ExportSettings;

    iget-object p0, p0, Lcom/exteragram/messenger/export/ExportSettings;->media:Lcom/exteragram/messenger/export/ExportSettings$MediaSettings;

    if-eqz p2, :cond_0

    iget p2, p0, Lcom/exteragram/messenger/export/ExportSettings$MediaSettings;->type:I

    or-int/2addr p1, p2

    goto :goto_0

    :cond_0
    iget p2, p0, Lcom/exteragram/messenger/export/ExportSettings$MediaSettings;->type:I

    not-int p1, p1

    and-int/2addr p1, p2

    :goto_0
    iput p1, p0, Lcom/exteragram/messenger/export/ExportSettings$MediaSettings;->type:I

    return-void
.end method

.method public static $r8$lambda$lr3s_ljEfZ5R2DSqgwPmtSpoz_I(Lcom/exteragram/messenger/export/ui/ExportActivity;I)Z
    .locals 0

    .line 224
    iget-object p0, p0, Lcom/exteragram/messenger/export/ui/ExportActivity;->settings:Lcom/exteragram/messenger/export/ExportSettings;

    iget-object p0, p0, Lcom/exteragram/messenger/export/ExportSettings;->media:Lcom/exteragram/messenger/export/ExportSettings$MediaSettings;

    iget p0, p0, Lcom/exteragram/messenger/export/ExportSettings$MediaSettings;->type:I

    and-int/2addr p0, p1

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public static $r8$lambda$vbrcMRtE7h-rED62mVswu3a4yfo(Lcom/exteragram/messenger/export/ui/ExportActivity;I)Z
    .locals 0

    .line 218
    iget-object p0, p0, Lcom/exteragram/messenger/export/ui/ExportActivity;->settings:Lcom/exteragram/messenger/export/ExportSettings;

    iget p0, p0, Lcom/exteragram/messenger/export/ExportSettings;->types:I

    and-int/2addr p0, p1

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method static constructor <clinit>()V
    .locals 3

    new-instance v0, Lcom/exteragram/messenger/export/ui/ExportActivity$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/export/ui/ExportActivity$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/export/ui/ExportActivity;->Companion:Lcom/exteragram/messenger/export/ui/ExportActivity$Companion;

    const/4 v0, 0x3

    .line 91
    new-array v0, v0, [Ljava/lang/CharSequence;

    const-string v1, "HTML"

    const/4 v2, 0x0

    aput-object v1, v0, v2

    const-string v1, "JSON"

    const/4 v2, 0x1

    aput-object v1, v0, v2

    const-string v1, "HTML and JSON"

    const/4 v2, 0x2

    aput-object v1, v0, v2

    .line 88
    sput-object v0, Lcom/exteragram/messenger/export/ui/ExportActivity;->formats:[Ljava/lang/CharSequence;

    return-void
.end method

.method public constructor <init>()V
    .locals 2

    .line 0
    const/4 v0, 0x0

    const/4 v1, 0x1

    invoke-direct {p0, v0, v1, v0}, Lcom/exteragram/messenger/export/ui/ExportActivity;-><init>(Lorg/telegram/tgnet/TLRPC$InputPeer;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    return-void
.end method

.method public constructor <init>(Lorg/telegram/tgnet/TLRPC$InputPeer;)V
    .locals 14

    .line 27
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;-><init>()V

    move-object v0, p1

    iput-object v0, p0, Lcom/exteragram/messenger/export/ui/ExportActivity;->peer:Lorg/telegram/tgnet/TLRPC$InputPeer;

    .line 58
    new-instance v0, Lcom/exteragram/messenger/export/ExportSettings;

    invoke-direct {v0}, Lcom/exteragram/messenger/export/ExportSettings;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/export/ui/ExportActivity;->settings:Lcom/exteragram/messenger/export/ExportSettings;

    .line 60
    new-instance v0, Lcom/exteragram/messenger/preferences/SwitchGroup;

    sget-object v2, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->EXPORT_SETTINGS:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    const/16 v7, 0x10

    const/4 v8, 0x0

    const/4 v3, 0x0

    const-string v4, "Main settings"

    const/4 v5, 0x0

    const/4 v6, 0x0

    move-object v1, p0

    invoke-direct/range {v0 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;ILjava/lang/CharSequence;Ljava/lang/String;ZILkotlin/jvm/internal/DefaultConstructorMarker;)V

    move-object v1, v0

    .line 61
    sget-object v2, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->ACCOUNT_INFO:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    const/4 v9, 0x1

    invoke-direct {p0, v9}, Lcom/exteragram/messenger/export/ui/ExportActivity;->type(I)Ljava/util/function/BooleanSupplier;

    move-result-object v4

    invoke-direct {p0, v9}, Lcom/exteragram/messenger/export/ui/ExportActivity;->setType(I)Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;

    move-result-object v5

    const-string v6, "Account Info"

    invoke-virtual/range {v1 .. v6}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 62
    sget-object v2, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->CONTACTS_LIST:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    const/4 v10, 0x4

    invoke-direct {p0, v10}, Lcom/exteragram/messenger/export/ui/ExportActivity;->type(I)Ljava/util/function/BooleanSupplier;

    move-result-object v4

    invoke-direct {p0, v10}, Lcom/exteragram/messenger/export/ui/ExportActivity;->setType(I)Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;

    move-result-object v5

    const-string v6, "Contacts"

    invoke-virtual/range {v1 .. v6}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 63
    sget-object v2, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->STORY_ARCHIVE:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    const/16 v3, 0x800

    invoke-direct {p0, v3}, Lcom/exteragram/messenger/export/ui/ExportActivity;->type(I)Ljava/util/function/BooleanSupplier;

    move-result-object v4

    invoke-direct {p0, v3}, Lcom/exteragram/messenger/export/ui/ExportActivity;->setType(I)Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;

    move-result-object v5

    const-string v6, "Stories"

    const/4 v3, 0x0

    invoke-virtual/range {v1 .. v6}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 64
    sget-object v2, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->ACTIVE_SESSIONS:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    const/16 v11, 0x8

    invoke-direct {p0, v11}, Lcom/exteragram/messenger/export/ui/ExportActivity;->type(I)Ljava/util/function/BooleanSupplier;

    move-result-object v4

    invoke-direct {p0, v11}, Lcom/exteragram/messenger/export/ui/ExportActivity;->setType(I)Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;

    move-result-object v5

    const-string v6, "Sessions"

    invoke-virtual/range {v1 .. v6}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 60
    iput-object v1, p0, Lcom/exteragram/messenger/export/ui/ExportActivity;->exportSettings:Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 67
    new-instance v2, Lcom/exteragram/messenger/preferences/SwitchGroup;

    move-object v0, v2

    sget-object v2, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->CHATS_SETTINGS:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    const-string v4, "Chats settings"

    const/4 v5, 0x0

    const/4 v6, 0x0

    move-object v1, p0

    invoke-direct/range {v0 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;ILjava/lang/CharSequence;Ljava/lang/String;ZILkotlin/jvm/internal/DefaultConstructorMarker;)V

    move-object v2, v0

    .line 68
    sget-object v3, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->PERSONAL_CHATS:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    const/16 v12, 0x20

    invoke-direct {p0, v12}, Lcom/exteragram/messenger/export/ui/ExportActivity;->type(I)Ljava/util/function/BooleanSupplier;

    move-result-object v5

    invoke-direct {p0, v12}, Lcom/exteragram/messenger/export/ui/ExportActivity;->setType(I)Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;

    move-result-object v6

    const-string v7, "Personal chats"

    const/4 v4, 0x0

    invoke-virtual/range {v2 .. v7}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 69
    sget-object v3, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->BOT_CHATS:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    const/16 v13, 0x40

    invoke-direct {p0, v13}, Lcom/exteragram/messenger/export/ui/ExportActivity;->type(I)Ljava/util/function/BooleanSupplier;

    move-result-object v5

    invoke-direct {p0, v13}, Lcom/exteragram/messenger/export/ui/ExportActivity;->setType(I)Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;

    move-result-object v6

    const-string v7, "Bots"

    invoke-virtual/range {v2 .. v7}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 70
    sget-object v3, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->PRIVATE_GROUPS:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    const/16 v0, 0x80

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/export/ui/ExportActivity;->type(I)Ljava/util/function/BooleanSupplier;

    move-result-object v5

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/export/ui/ExportActivity;->setType(I)Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;

    move-result-object v6

    const-string v7, "Private groups"

    invoke-virtual/range {v2 .. v7}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 71
    sget-object v3, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->PRIVATE_CHANNELS:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    const/16 v0, 0x200

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/export/ui/ExportActivity;->type(I)Ljava/util/function/BooleanSupplier;

    move-result-object v5

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/export/ui/ExportActivity;->setType(I)Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;

    move-result-object v6

    const-string v7, "Private channels"

    invoke-virtual/range {v2 .. v7}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 72
    sget-object v3, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->PUBLIC_GROUPS:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    const/16 v0, 0x100

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/export/ui/ExportActivity;->type(I)Ljava/util/function/BooleanSupplier;

    move-result-object v5

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/export/ui/ExportActivity;->setType(I)Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;

    move-result-object v6

    const-string v7, "Public groups"

    invoke-virtual/range {v2 .. v7}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 73
    sget-object v3, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->PUBLIC_CHANNELS:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    const/16 v0, 0x400

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/export/ui/ExportActivity;->type(I)Ljava/util/function/BooleanSupplier;

    move-result-object v5

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/export/ui/ExportActivity;->setType(I)Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;

    move-result-object v6

    const-string v7, "Public channels"

    invoke-virtual/range {v2 .. v7}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 67
    iput-object v2, p0, Lcom/exteragram/messenger/export/ui/ExportActivity;->chatsSettings:Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 76
    new-instance v3, Lcom/exteragram/messenger/preferences/SwitchGroup;

    sget-object v2, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->MEDIA_SETTINGS:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    const/16 v7, 0x10

    move-object v0, v3

    const/4 v3, 0x0

    const-string v4, "Media settings"

    const/4 v5, 0x0

    const/4 v6, 0x0

    invoke-direct/range {v0 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;ILjava/lang/CharSequence;Ljava/lang/String;ZILkotlin/jvm/internal/DefaultConstructorMarker;)V

    move-object v3, v0

    .line 77
    sget-object v4, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->PHOTOS:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    invoke-direct {p0, v9}, Lcom/exteragram/messenger/export/ui/ExportActivity;->media(I)Ljava/util/function/BooleanSupplier;

    move-result-object v6

    invoke-direct {p0, v9}, Lcom/exteragram/messenger/export/ui/ExportActivity;->setMedia(I)Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;

    move-result-object v7

    const-string v8, "Photos"

    const/4 v5, 0x0

    invoke-virtual/range {v3 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 78
    sget-object v4, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->VIDEOS:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    const/4 v0, 0x2

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/export/ui/ExportActivity;->media(I)Ljava/util/function/BooleanSupplier;

    move-result-object v6

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/export/ui/ExportActivity;->setMedia(I)Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;

    move-result-object v7

    const-string v8, "Videos"

    invoke-virtual/range {v3 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 79
    sget-object v4, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->VOICE_MESSAGES:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    invoke-direct {p0, v10}, Lcom/exteragram/messenger/export/ui/ExportActivity;->media(I)Ljava/util/function/BooleanSupplier;

    move-result-object v6

    invoke-direct {p0, v10}, Lcom/exteragram/messenger/export/ui/ExportActivity;->setMedia(I)Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;

    move-result-object v7

    const-string v8, "Voice messages"

    invoke-virtual/range {v3 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 80
    sget-object v4, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->VIDEO_MESSAGES:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    invoke-direct {p0, v11}, Lcom/exteragram/messenger/export/ui/ExportActivity;->media(I)Ljava/util/function/BooleanSupplier;

    move-result-object v6

    invoke-direct {p0, v11}, Lcom/exteragram/messenger/export/ui/ExportActivity;->setMedia(I)Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;

    move-result-object v7

    const-string v8, "Video messages"

    invoke-virtual/range {v3 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 81
    sget-object v4, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->STICKERS:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    const/16 v0, 0x10

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/export/ui/ExportActivity;->media(I)Ljava/util/function/BooleanSupplier;

    move-result-object v6

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/export/ui/ExportActivity;->setMedia(I)Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;

    move-result-object v7

    const-string v8, "Stickers"

    invoke-virtual/range {v3 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 82
    sget-object v4, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->GIFS:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    invoke-direct {p0, v12}, Lcom/exteragram/messenger/export/ui/ExportActivity;->media(I)Ljava/util/function/BooleanSupplier;

    move-result-object v6

    invoke-direct {p0, v12}, Lcom/exteragram/messenger/export/ui/ExportActivity;->setMedia(I)Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;

    move-result-object v7

    const-string v8, "GIFs"

    invoke-virtual/range {v3 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 83
    sget-object v4, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->FILES:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    invoke-direct {p0, v13}, Lcom/exteragram/messenger/export/ui/ExportActivity;->media(I)Ljava/util/function/BooleanSupplier;

    move-result-object v6

    invoke-direct {p0, v13}, Lcom/exteragram/messenger/export/ui/ExportActivity;->setMedia(I)Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;

    move-result-object v7

    const-string v8, "Files"

    invoke-virtual/range {v3 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add(Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 76
    iput-object v3, p0, Lcom/exteragram/messenger/export/ui/ExportActivity;->mediaSettings:Lcom/exteragram/messenger/preferences/SwitchGroup;

    return-void
.end method

.method public synthetic constructor <init>(Lorg/telegram/tgnet/TLRPC$InputPeer;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    and-int/lit8 p2, p2, 0x1

    if-eqz p2, :cond_0

    const/4 p1, 0x0

    .line 27
    :cond_0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/export/ui/ExportActivity;-><init>(Lorg/telegram/tgnet/TLRPC$InputPeer;)V

    return-void
.end method

.method private final getIndexOfFormat()I
    .locals 1

    .line 230
    iget-object p0, p0, Lcom/exteragram/messenger/export/ui/ExportActivity;->settings:Lcom/exteragram/messenger/export/ExportSettings;

    iget-object p0, p0, Lcom/exteragram/messenger/export/ExportSettings;->format:Lcom/exteragram/messenger/export/output/AbstractWriter$Format;

    if-nez p0, :cond_0

    const/4 p0, -0x1

    goto :goto_0

    :cond_0
    sget-object v0, Lcom/exteragram/messenger/export/ui/ExportActivity$WhenMappings;->$EnumSwitchMapping$1:[I

    invoke-virtual {p0}, Ljava/lang/Enum;->ordinal()I

    move-result p0

    aget p0, v0, p0

    :goto_0
    const/4 v0, 0x1

    if-eq p0, v0, :cond_1

    const/4 v0, 0x2

    if-eq p0, v0, :cond_1

    const/4 p0, 0x0

    return p0

    :cond_1
    return v0
.end method

.method private final media(I)Ljava/util/function/BooleanSupplier;
    .locals 1

    .line 224
    new-instance v0, Lcom/exteragram/messenger/export/ui/ExportActivity$$ExternalSyntheticLambda2;

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/export/ui/ExportActivity$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/export/ui/ExportActivity;I)V

    return-object v0
.end method

.method private final setMedia(I)Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;
    .locals 1

    .line 226
    new-instance v0, Lcom/exteragram/messenger/export/ui/ExportActivity$$ExternalSyntheticLambda3;

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/export/ui/ExportActivity$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/export/ui/ExportActivity;I)V

    return-object v0
.end method

.method private final setType(I)Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;
    .locals 1

    .line 220
    new-instance v0, Lcom/exteragram/messenger/export/ui/ExportActivity$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/export/ui/ExportActivity$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/export/ui/ExportActivity;I)V

    return-object v0
.end method

.method private final type(I)Ljava/util/function/BooleanSupplier;
    .locals 1

    .line 218
    new-instance v0, Lcom/exteragram/messenger/export/ui/ExportActivity$$ExternalSyntheticLambda1;

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/export/ui/ExportActivity$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/export/ui/ExportActivity;I)V

    return-object v0
.end method


# virtual methods
.method public varargs didReceivedNotification(II[Ljava/lang/Object;)V
    .locals 3

    .line 124
    sget p2, Lcom/exteragram/messenger/export/controllers/ExportController;->FINISH_NOTIFICATION:I

    if-ne p1, p2, :cond_0

    .line 125
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget p1, Lorg/telegram/messenger/R$raw;->contact_check:I

    const-string p2, "Export complete!"

    invoke-virtual {p0, p1, p2}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void

    :cond_0
    const/4 p0, 0x0

    .line 127
    aget-object p0, p3, p0

    check-cast p0, Lcom/exteragram/messenger/export/controllers/ExportController$ProcessingState;

    .line 128
    iget p1, p0, Lcom/exteragram/messenger/export/controllers/ExportController$ProcessingState;->substepsPassed:I

    int-to-float p1, p1

    iget p2, p0, Lcom/exteragram/messenger/export/controllers/ExportController$ProcessingState;->substepsTotal:I

    int-to-float p2, p2

    div-float/2addr p1, p2

    .line 129
    iget-wide p2, p0, Lcom/exteragram/messenger/export/controllers/ExportController$ProcessingState;->bytesCount:J

    const-wide/16 v0, 0x0

    cmp-long p2, p2, v0

    if-lez p2, :cond_1

    .line 130
    sget-object p2, Lkotlin/jvm/internal/StringCompanionObject;->INSTANCE:Lkotlin/jvm/internal/StringCompanionObject;

    .line 131
    sget-object p2, Ljava/util/Locale;->US:Ljava/util/Locale;

    .line 132
    iget-object p3, p0, Lcom/exteragram/messenger/export/controllers/ExportController$ProcessingState;->bytesName:Ljava/lang/String;

    .line 133
    iget-wide v0, p0, Lcom/exteragram/messenger/export/controllers/ExportController$ProcessingState;->bytesLoaded:J

    invoke-static {v0, v1}, Lorg/telegram/messenger/AndroidUtilities;->formatFileSize(J)Ljava/lang/String;

    move-result-object v0

    .line 134
    iget-wide v1, p0, Lcom/exteragram/messenger/export/controllers/ExportController$ProcessingState;->bytesCount:J

    invoke-static {v1, v2}, Lorg/telegram/messenger/AndroidUtilities;->formatFileSize(J)Ljava/lang/String;

    move-result-object p0

    filled-new-array {p3, v0, p0}, [Ljava/lang/Object;

    move-result-object p0

    const/4 p3, 0x3

    .line 130
    invoke-static {p0, p3}, Ljava/util/Arrays;->copyOf([Ljava/lang/Object;I)[Ljava/lang/Object;

    move-result-object p0

    const-string p3, "Downloading %s\n%s / %s"

    invoke-static {p2, p3, p0}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    .line 136
    :cond_1
    iget p2, p0, Lcom/exteragram/messenger/export/controllers/ExportController$ProcessingState;->entityCount:I

    if-lez p2, :cond_2

    .line 137
    sget-object p2, Lkotlin/jvm/internal/StringCompanionObject;->INSTANCE:Lkotlin/jvm/internal/StringCompanionObject;

    .line 138
    sget-object p2, Ljava/util/Locale;->US:Ljava/util/Locale;

    .line 139
    iget-object p3, p0, Lcom/exteragram/messenger/export/controllers/ExportController$ProcessingState;->entityName:Ljava/lang/String;

    iget v0, p0, Lcom/exteragram/messenger/export/controllers/ExportController$ProcessingState;->entityIndex:I

    add-int/lit8 v0, v0, 0x1

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    iget v1, p0, Lcom/exteragram/messenger/export/controllers/ExportController$ProcessingState;->entityCount:I

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    .line 140
    iget v2, p0, Lcom/exteragram/messenger/export/controllers/ExportController$ProcessingState;->itemIndex:I

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    iget p0, p0, Lcom/exteragram/messenger/export/controllers/ExportController$ProcessingState;->itemCount:I

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    filled-new-array {p3, v0, v1, v2, p0}, [Ljava/lang/Object;

    move-result-object p0

    const/4 p3, 0x5

    .line 137
    invoke-static {p0, p3}, Ljava/util/Arrays;->copyOf([Ljava/lang/Object;I)[Ljava/lang/Object;

    move-result-object p0

    const-string p3, "Exporting %s (%d / %d)\nMessage %d / %d"

    invoke-static {p2, p3, p0}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    .line 143
    :cond_2
    iget-object p0, p0, Lcom/exteragram/messenger/export/controllers/ExportController$ProcessingState;->step:Lcom/exteragram/messenger/export/controllers/ExportController$ProcessingState$Step;

    invoke-virtual {p0}, Ljava/lang/Enum;->name()Ljava/lang/String;

    move-result-object p0

    new-instance p2, Ljava/lang/StringBuilder;

    const-string p3, "Exporting "

    invoke-direct {p2, p3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 145
    :goto_0
    new-instance p2, Ljava/lang/StringBuilder;

    const-string p3, "[EXPORT] "

    invoke-direct {p2, p3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ", "

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;)V

    return-void
.end method

.method public fillItems(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V
    .locals 15
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

    move-object/from16 v14, p1

    .line 152
    const-string v1, "Export settings"

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 154
    iget-object v1, p0, Lcom/exteragram/messenger/export/ui/ExportActivity;->exportSettings:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {v1, v14}, Lcom/exteragram/messenger/preferences/SwitchGroup;->fill(Ljava/util/ArrayList;)V

    .line 155
    iget-object v1, p0, Lcom/exteragram/messenger/export/ui/ExportActivity;->chatsSettings:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {v1, v14}, Lcom/exteragram/messenger/preferences/SwitchGroup;->fill(Ljava/util/ArrayList;)V

    .line 156
    iget-object v1, p0, Lcom/exteragram/messenger/export/ui/ExportActivity;->mediaSettings:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {v1, v14}, Lcom/exteragram/messenger/preferences/SwitchGroup;->fill(Ljava/util/ArrayList;)V

    .line 158
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 160
    sget-object v1, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->FORMAT:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    sget-object v2, Lcom/exteragram/messenger/export/ui/ExportActivity;->formats:[Ljava/lang/CharSequence;

    invoke-direct {p0}, Lcom/exteragram/messenger/export/ui/ExportActivity;->getIndexOfFormat()I

    move-result v3

    aget-object v4, v2, v3

    const/16 v12, 0x7b4

    const/4 v13, 0x0

    const-string v2, "Select export result type"

    const/4 v3, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    move-object v0, p0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 161
    sget-object v1, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->START_EXPORT:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    const/16 v12, 0x7bc

    const-string v2, "Start Export"

    const/4 v4, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 162
    sget-object v1, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->VIEW_JSON_EXPORT:Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    const-string v2, "Open Json Export"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 164
    const-string v0, "Here you can export your chats."

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public getTitle()Ljava/lang/String;
    .locals 0

    .line 149
    const-string p0, "Export Chats"

    return-object p0
.end method

.method public onActivityResultFragment(IILandroid/content/Intent;)V
    .locals 1

    .line 203
    invoke-super {p0, p1, p2, p3}, Lorg/telegram/ui/ActionBar/BaseFragment;->onActivityResultFragment(IILandroid/content/Intent;)V

    const/16 v0, 0x539

    if-ne p1, v0, :cond_1

    const/4 p1, -0x1

    if-ne p2, p1, :cond_1

    if-eqz p3, :cond_1

    .line 206
    :try_start_0
    invoke-virtual {p3}, Landroid/content/Intent;->getData()Landroid/net/Uri;

    move-result-object p1

    if-nez p1, :cond_0

    goto :goto_0

    .line 207
    :cond_0
    invoke-static {p1}, Landroid/provider/DocumentsContract;->getTreeDocumentId(Landroid/net/Uri;)Ljava/lang/String;

    move-result-object p2

    invoke-static {p1, p2}, Landroid/provider/DocumentsContract;->buildDocumentUriUsingTree(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;

    move-result-object p1

    .line 208
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p2

    invoke-static {p2, p1}, Lcom/exteragram/messenger/export/ui/AndroidPickerUtils;->getPath(Landroid/content/Context;Landroid/net/Uri;)Ljava/lang/String;

    move-result-object p1

    .line 209
    new-instance p2, Lcom/exteragram/messenger/export/ui/DialogsView;

    invoke-direct {p2, p1}, Lcom/exteragram/messenger/export/ui/DialogsView;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0, p2}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 211
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getUseGoogleCrashlytics()Z

    move-result p1

    if-eqz p1, :cond_1

    .line 212
    invoke-static {}, Lcom/google/firebase/crashlytics/FirebaseCrashlytics;->getInstance()Lcom/google/firebase/crashlytics/FirebaseCrashlytics;

    move-result-object p1

    invoke-virtual {p1, p0}, Lcom/google/firebase/crashlytics/FirebaseCrashlytics;->recordException(Ljava/lang/Throwable;)V

    :cond_1
    :goto_0
    return-void
.end method

.method public onClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 6

    .line 168
    iget p2, p1, Lorg/telegram/ui/Components/UItem;->id:I

    if-lez p2, :cond_2

    invoke-static {}, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p3

    invoke-interface {p3}, Ljava/util/List;->size()I

    move-result p3

    if-le p2, p3, :cond_0

    goto/16 :goto_2

    .line 169
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p2

    iget p3, p1, Lorg/telegram/ui/Components/UItem;->id:I

    const/4 p4, 0x1

    sub-int/2addr p3, p4

    invoke-interface {p2, p3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/exteragram/messenger/export/ui/ExportActivity$ExportItem;

    .line 170
    sget-object p3, Lcom/exteragram/messenger/export/ui/ExportActivity$WhenMappings;->$EnumSwitchMapping$0:[I

    invoke-virtual {p2}, Ljava/lang/Enum;->ordinal()I

    move-result p2

    aget p2, p3, p2

    packed-switch p2, :pswitch_data_0

    :goto_0
    move-object v0, p0

    goto/16 :goto_1

    .line 192
    :pswitch_0
    new-instance p1, Landroid/content/Intent;

    const-string p2, "android.intent.action.OPEN_DOCUMENT_TREE"

    invoke-direct {p1, p2}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 193
    const-string p2, "android.intent.category.DEFAULT"

    invoke-virtual {p1, p2}, Landroid/content/Intent;->addCategory(Ljava/lang/String;)Landroid/content/Intent;

    .line 194
    const-string p2, "Choose a directory"

    invoke-static {p1, p2}, Landroid/content/Intent;->createChooser(Landroid/content/Intent;Ljava/lang/CharSequence;)Landroid/content/Intent;

    move-result-object p1

    const/16 p2, 0x539

    invoke-virtual {p0, p1, p2}, Lorg/telegram/ui/ActionBar/BaseFragment;->startActivityForResult(Landroid/content/Intent;I)V

    goto :goto_0

    .line 185
    :pswitch_1
    iget-object p1, p0, Lcom/exteragram/messenger/export/ui/ExportActivity;->peer:Lorg/telegram/tgnet/TLRPC$InputPeer;

    if-eqz p1, :cond_1

    iget-object p2, p0, Lcom/exteragram/messenger/export/ui/ExportActivity;->settings:Lcom/exteragram/messenger/export/ExportSettings;

    iput-object p1, p2, Lcom/exteragram/messenger/export/ExportSettings;->singlePeer:Lorg/telegram/tgnet/TLRPC$InputPeer;

    .line 186
    :cond_1
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p1

    const-string p2, "Starting export..."

    invoke-virtual {p1, p2}, Lorg/telegram/ui/Components/BulletinFactory;->createErrorBulletin(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p1

    invoke-virtual {p1}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    .line 187
    iget-object p1, p0, Lcom/exteragram/messenger/export/ui/ExportActivity;->settings:Lcom/exteragram/messenger/export/ExportSettings;

    iget-object p1, p1, Lcom/exteragram/messenger/export/ExportSettings;->media:Lcom/exteragram/messenger/export/ExportSettings$MediaSettings;

    const-wide/32 p2, 0x7d000000

    iput-wide p2, p1, Lcom/exteragram/messenger/export/ExportSettings$MediaSettings;->sizeLimit:J

    .line 188
    sget p1, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {p1}, Lcom/exteragram/messenger/export/controllers/ExportController;->getInstance(I)Lcom/exteragram/messenger/export/controllers/ExportController;

    move-result-object p1

    iget-object p2, p0, Lcom/exteragram/messenger/export/ui/ExportActivity;->settings:Lcom/exteragram/messenger/export/ExportSettings;

    invoke-virtual {p1, p2}, Lcom/exteragram/messenger/export/controllers/ExportController;->startExport(Lcom/exteragram/messenger/export/ExportSettings;)V

    goto :goto_0

    .line 176
    :pswitch_2
    sget-object v2, Lcom/exteragram/messenger/export/ui/ExportActivity;->formats:[Ljava/lang/CharSequence;

    invoke-direct {p0}, Lcom/exteragram/messenger/export/ui/ExportActivity;->getIndexOfFormat()I

    move-result v4

    new-instance v5, Lcom/exteragram/messenger/export/ui/ExportActivity$$ExternalSyntheticLambda4;

    invoke-direct {v5, p0}, Lcom/exteragram/messenger/export/ui/ExportActivity$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/export/ui/ExportActivity;)V

    const-string v3, "Select export result type"

    move-object v0, p0

    move-object v1, p1

    invoke-virtual/range {v0 .. v5}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;Ljava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V

    goto :goto_1

    :pswitch_3
    move-object v0, p0

    move-object v1, p1

    .line 174
    iget-object p0, v0, Lcom/exteragram/messenger/export/ui/ExportActivity;->mediaSettings:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {p0, v1}, Lcom/exteragram/messenger/preferences/SwitchGroup;->onClick(Lorg/telegram/ui/Components/UItem;)V

    goto :goto_1

    :pswitch_4
    move-object v0, p0

    move-object v1, p1

    .line 173
    iget-object p0, v0, Lcom/exteragram/messenger/export/ui/ExportActivity;->chatsSettings:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {p0, v1}, Lcom/exteragram/messenger/preferences/SwitchGroup;->onClick(Lorg/telegram/ui/Components/UItem;)V

    goto :goto_1

    :pswitch_5
    move-object v0, p0

    move-object v1, p1

    .line 171
    iget-object p0, v0, Lcom/exteragram/messenger/export/ui/ExportActivity;->exportSettings:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {p0, v1}, Lcom/exteragram/messenger/preferences/SwitchGroup;->onClick(Lorg/telegram/ui/Components/UItem;)V

    .line 199
    :goto_1
    invoke-virtual {v0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_2

    invoke-virtual {p0, p4}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_2
    :goto_2
    return-void

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_5
        :pswitch_4
        :pswitch_4
        :pswitch_4
        :pswitch_4
        :pswitch_4
        :pswitch_4
        :pswitch_4
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public onFragmentCreate()Z
    .locals 2

    .line 96
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lcom/exteragram/messenger/export/controllers/ExportController;->INITIALIZATING_NOTIFICATION:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 97
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lcom/exteragram/messenger/export/controllers/ExportController;->DIALOGS_LIST_NOTIFICATION:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 98
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lcom/exteragram/messenger/export/controllers/ExportController;->PERSONAL_INFO_NOTIFICATION:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 99
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lcom/exteragram/messenger/export/controllers/ExportController;->USERPICS_NOTIFICATION:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 100
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lcom/exteragram/messenger/export/controllers/ExportController;->STORIES_NOTIFICATION:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 101
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lcom/exteragram/messenger/export/controllers/ExportController;->CONTACTS_NOTIFICATION:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 102
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lcom/exteragram/messenger/export/controllers/ExportController;->SESSIONS_NOTIFICATION:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 103
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lcom/exteragram/messenger/export/controllers/ExportController;->OTHER_DATA_NOTIFICATION:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 104
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lcom/exteragram/messenger/export/controllers/ExportController;->DIALOGS_NOTIFICATION:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 105
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lcom/exteragram/messenger/export/controllers/ExportController;->FINISH_NOTIFICATION:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 106
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentCreate()Z

    move-result p0

    return p0
.end method

.method public onFragmentDestroy()V
    .locals 2

    .line 110
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lcom/exteragram/messenger/export/controllers/ExportController;->INITIALIZATING_NOTIFICATION:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 111
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lcom/exteragram/messenger/export/controllers/ExportController;->DIALOGS_LIST_NOTIFICATION:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 112
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lcom/exteragram/messenger/export/controllers/ExportController;->PERSONAL_INFO_NOTIFICATION:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 113
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lcom/exteragram/messenger/export/controllers/ExportController;->USERPICS_NOTIFICATION:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 114
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lcom/exteragram/messenger/export/controllers/ExportController;->STORIES_NOTIFICATION:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 115
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lcom/exteragram/messenger/export/controllers/ExportController;->CONTACTS_NOTIFICATION:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 116
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lcom/exteragram/messenger/export/controllers/ExportController;->SESSIONS_NOTIFICATION:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 117
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lcom/exteragram/messenger/export/controllers/ExportController;->OTHER_DATA_NOTIFICATION:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 118
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lcom/exteragram/messenger/export/controllers/ExportController;->DIALOGS_NOTIFICATION:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 119
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lcom/exteragram/messenger/export/controllers/ExportController;->FINISH_NOTIFICATION:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 120
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentDestroy()V

    return-void
.end method
