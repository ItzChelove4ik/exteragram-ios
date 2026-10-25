.class public final Lcom/exteragram/messenger/plugins/PluginsConstants;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/plugins/PluginsConstants$Companion;,
        Lcom/exteragram/messenger/plugins/PluginsConstants$DevServer;,
        Lcom/exteragram/messenger/plugins/PluginsConstants$HookFilterTypes;,
        Lcom/exteragram/messenger/plugins/PluginsConstants$MenuItemProperties;,
        Lcom/exteragram/messenger/plugins/PluginsConstants$MenuItemTypes;,
        Lcom/exteragram/messenger/plugins/PluginsConstants$Settings;,
        Lcom/exteragram/messenger/plugins/PluginsConstants$Strategy;,
        Lcom/exteragram/messenger/plugins/PluginsConstants$Xposed;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u000c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u000b\u0018\u0000 \u00042\u00020\u0001:\u0008\u0004\u0005\u0006\u0007\u0008\t\n\u000bB\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003\u00a8\u0006\u000c"
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/PluginsConstants;",
        "",
        "<init>",
        "()V",
        "Companion",
        "Settings",
        "Strategy",
        "Xposed",
        "DevServer",
        "MenuItemTypes",
        "MenuItemProperties",
        "HookFilterTypes",
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
.field public static final APP_PAUSE:Ljava/lang/String; = "app_pause"

.field public static final APP_RESUME:Ljava/lang/String; = "app_resume"

.field public static final APP_START:Ljava/lang/String; = "app_start"

.field public static final APP_STOP:Ljava/lang/String; = "app_stop"

.field public static final CREATE_SETTINGS:Ljava/lang/String; = "create_settings"

.field public static final Companion:Lcom/exteragram/messenger/plugins/PluginsConstants$Companion;

.field public static final ERROR:Ljava/lang/String; = "error"

.field public static final ON_APP_EVENT:Ljava/lang/String; = "on_app_event"

.field public static final ON_PLUGIN_LOAD:Ljava/lang/String; = "on_plugin_load"

.field public static final ON_PLUGIN_UNLOAD:Ljava/lang/String; = "on_plugin_unload"

.field public static final PARAMS:Ljava/lang/String; = "params"

.field public static final PLUGINS:Ljava/lang/String; = "plugins"

.field public static final PLUGINS_EXT:Ljava/lang/String; = ".plugin"

.field public static final PLUGINS_SDK:Ljava/lang/String; = "plugins-sdk"

.field public static final PYTHON:Ljava/lang/String; = "python"

.field public static final REQUEST:Ljava/lang/String; = "request"

.field public static final RESPONSE:Ljava/lang/String; = "response"

.field public static final SEND_MESSAGE_HOOK:Ljava/lang/String; = "send_message_hook"

.field public static final STRATEGY:Ljava/lang/String; = "strategy"

.field public static final UPDATE:Ljava/lang/String; = "update"

.field public static final UPDATES:Ljava/lang/String; = "updates"


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/plugins/PluginsConstants$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/plugins/PluginsConstants$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/plugins/PluginsConstants;->Companion:Lcom/exteragram/messenger/plugins/PluginsConstants$Companion;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 15
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method
