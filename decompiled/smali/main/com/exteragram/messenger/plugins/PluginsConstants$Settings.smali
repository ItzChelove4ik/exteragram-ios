.class public final Lcom/exteragram/messenger/plugins/PluginsConstants$Settings;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/plugins/PluginsConstants;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Settings"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/plugins/PluginsConstants$Settings$Companion;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u000c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003\u00a8\u0006\u0005"
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/PluginsConstants$Settings;",
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
.field public static final ACCENT:Ljava/lang/String; = "accent"

.field public static final CREATE_SUB_FRAGMENT:Ljava/lang/String; = "create_sub_fragment"

.field public static final Companion:Lcom/exteragram/messenger/plugins/PluginsConstants$Settings$Companion;

.field public static final DEFAULT:Ljava/lang/String; = "default"

.field public static final FACTORY:Ljava/lang/String; = "factory"

.field public static final FACTORY_ARGS:Ljava/lang/String; = "factory_args"

.field public static final HINT:Ljava/lang/String; = "hint"

.field public static final ICON:Ljava/lang/String; = "icon"

.field public static final ITEM:Ljava/lang/String; = "item"

.field public static final ITEMS:Ljava/lang/String; = "items"

.field public static final KEY:Ljava/lang/String; = "key"

.field public static final LINK_ALIAS:Ljava/lang/String; = "link_alias"

.field public static final MASK:Ljava/lang/String; = "mask"

.field public static final MAX_LENGTH:Ljava/lang/String; = "max_length"

.field public static final MULTILINE:Ljava/lang/String; = "multiline"

.field public static final ON_CHANGE:Ljava/lang/String; = "on_change"

.field public static final ON_CLICK:Ljava/lang/String; = "on_click"

.field public static final ON_LONG_CLICK:Ljava/lang/String; = "on_long_click"

.field public static final RED:Ljava/lang/String; = "red"

.field public static final SUBTEXT:Ljava/lang/String; = "subtext"

.field public static final TEXT:Ljava/lang/String; = "text"

.field public static final TYPE:Ljava/lang/String; = "type"

.field public static final TYPE_CUSTOM:Ljava/lang/String; = "custom"

.field public static final TYPE_DIVIDER:Ljava/lang/String; = "divider"

.field public static final TYPE_EDIT_TEXT:Ljava/lang/String; = "edit_text"

.field public static final TYPE_HEADER:Ljava/lang/String; = "header"

.field public static final TYPE_INPUT:Ljava/lang/String; = "input"

.field public static final TYPE_SELECTOR:Ljava/lang/String; = "selector"

.field public static final TYPE_SWITCH:Ljava/lang/String; = "switch"

.field public static final TYPE_TEXT:Ljava/lang/String; = "text"

.field public static final VIEW:Ljava/lang/String; = "view"


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/plugins/PluginsConstants$Settings$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/plugins/PluginsConstants$Settings$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/plugins/PluginsConstants$Settings;->Companion:Lcom/exteragram/messenger/plugins/PluginsConstants$Settings$Companion;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 42
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method
