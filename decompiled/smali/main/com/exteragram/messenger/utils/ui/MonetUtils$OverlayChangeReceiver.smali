.class final Lcom/exteragram/messenger/utils/ui/MonetUtils$OverlayChangeReceiver;
.super Landroid/content/BroadcastReceiver;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/utils/ui/MonetUtils;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "OverlayChangeReceiver"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\u0008\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0008\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\u0008\u001a\u00020\tJ\u0018\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0008\u001a\u00020\t2\u0006\u0010\u000c\u001a\u00020\rH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"
    }
    d2 = {
        "Lcom/exteragram/messenger/utils/ui/MonetUtils$OverlayChangeReceiver;",
        "Landroid/content/BroadcastReceiver;",
        "<init>",
        "()V",
        "isRegistered",
        "",
        "register",
        "",
        "context",
        "Landroid/content/Context;",
        "unregister",
        "onReceive",
        "intent",
        "Landroid/content/Intent;",
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
.field private isRegistered:Z


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 187
    invoke-direct {p0}, Landroid/content/BroadcastReceiver;-><init>()V

    return-void
.end method


# virtual methods
.method public onReceive(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 0

    .line 206
    const-string p0, "android.intent.action.OVERLAY_CHANGED"

    invoke-virtual {p2}, Landroid/content/Intent;->getAction()Ljava/lang/String;

    move-result-object p1

    invoke-static {p0, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_1

    const/4 p0, 0x0

    .line 207
    invoke-static {p0}, Lcom/exteragram/messenger/utils/ui/MonetUtils;->access$setHarmonizeContextColor$p(I)V

    .line 208
    invoke-static {}, Lorg/telegram/ui/ActionBar/Theme;->refreshMonetColors()V

    .line 209
    invoke-static {}, Lorg/telegram/ui/ActionBar/Theme;->isCurrentThemeMonet()Z

    move-result p0

    if-nez p0, :cond_0

    invoke-static {}, Lorg/telegram/ui/ActionBar/Theme;->isCurrentAccentMonet()Z

    move-result p0

    if-eqz p0, :cond_1

    .line 210
    :cond_0
    invoke-static {}, Lorg/telegram/ui/ActionBar/Theme;->getActiveTheme()Lorg/telegram/ui/ActionBar/Theme$ThemeInfo;

    move-result-object p0

    invoke-static {}, Lorg/telegram/ui/ActionBar/Theme;->isCurrentThemeNight()Z

    move-result p1

    invoke-static {p0, p1}, Lorg/telegram/ui/ActionBar/Theme;->applyTheme(Lorg/telegram/ui/ActionBar/Theme$ThemeInfo;Z)V

    :cond_1
    return-void
.end method

.method public final register(Landroid/content/Context;)V
    .locals 3

    .line 191
    iget-boolean v0, p0, Lcom/exteragram/messenger/utils/ui/MonetUtils$OverlayChangeReceiver;->isRegistered:Z

    if-eqz v0, :cond_0

    return-void

    .line 192
    :cond_0
    new-instance v0, Landroid/content/IntentFilter;

    const-string v1, "android.intent.action.OVERLAY_CHANGED"

    invoke-direct {v0, v1}, Landroid/content/IntentFilter;-><init>(Ljava/lang/String;)V

    .line 193
    const-string/jumbo v1, "package"

    invoke-virtual {v0, v1}, Landroid/content/IntentFilter;->addDataScheme(Ljava/lang/String;)V

    .line 194
    const-string v1, "android"

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/content/IntentFilter;->addDataSchemeSpecificPart(Ljava/lang/String;I)V

    .line 195
    invoke-virtual {p1, p0, v0}, Landroid/content/Context;->registerReceiver(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;

    const/4 p1, 0x1

    .line 196
    iput-boolean p1, p0, Lcom/exteragram/messenger/utils/ui/MonetUtils$OverlayChangeReceiver;->isRegistered:Z

    return-void
.end method

.method public final unregister(Landroid/content/Context;)V
    .locals 1

    .line 200
    iget-boolean v0, p0, Lcom/exteragram/messenger/utils/ui/MonetUtils$OverlayChangeReceiver;->isRegistered:Z

    if-nez v0, :cond_0

    return-void

    .line 201
    :cond_0
    invoke-virtual {p1, p0}, Landroid/content/Context;->unregisterReceiver(Landroid/content/BroadcastReceiver;)V

    const/4 p1, 0x0

    .line 202
    iput-boolean p1, p0, Lcom/exteragram/messenger/utils/ui/MonetUtils$OverlayChangeReceiver;->isRegistered:Z

    return-void
.end method
