.class public final Lcom/exteragram/messenger/plugins/PluginsConstants$MenuItemProperties;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/plugins/PluginsConstants;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "MenuItemProperties"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/plugins/PluginsConstants$MenuItemProperties$Companion;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u000c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003\u00a8\u0006\u0005"
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/PluginsConstants$MenuItemProperties;",
        "",
        "<init>",
        "()V",
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


# static fields
.field public static final CONDITION:Ljava/lang/String; = "condition"

.field public static final Companion:Lcom/exteragram/messenger/plugins/PluginsConstants$MenuItemProperties$Companion;

.field public static final ICON:Ljava/lang/String; = "icon"

.field public static final ITEM_ID:Ljava/lang/String; = "item_id"

.field public static final MENU_TYPE:Ljava/lang/String; = "menu_type"

.field public static final ON_CLICK:Ljava/lang/String; = "on_click"

.field public static final PRIORITY:Ljava/lang/String; = "priority"

.field public static final SUBTEXT:Ljava/lang/String; = "subtext"

.field public static final TEXT:Ljava/lang/String; = "text"


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/plugins/PluginsConstants$MenuItemProperties$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/plugins/PluginsConstants$MenuItemProperties$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/plugins/PluginsConstants$MenuItemProperties;->Companion:Lcom/exteragram/messenger/plugins/PluginsConstants$MenuItemProperties$Companion;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 114
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method
