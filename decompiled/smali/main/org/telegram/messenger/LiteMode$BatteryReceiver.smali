.class public Lorg/telegram/messenger/LiteMode$BatteryReceiver;
.super Landroid/content/BroadcastReceiver;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/messenger/LiteMode;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "BatteryReceiver"
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 388
    invoke-direct {p0}, Landroid/content/BroadcastReceiver;-><init>()V

    return-void
.end method


# virtual methods
.method public onReceive(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 1

    .line 391
    const-string/jumbo p0, "level"

    const/4 p1, -0x1

    invoke-virtual {p2, p0, p1}, Landroid/content/Intent;->getIntExtra(Ljava/lang/String;I)I

    move-result p0

    .line 392
    const-string/jumbo v0, "scale"

    invoke-virtual {p2, v0, p1}, Landroid/content/Intent;->getIntExtra(Ljava/lang/String;I)I

    move-result p1

    if-ltz p0, :cond_0

    if-lez p1, :cond_0

    mul-int/lit8 p0, p0, 0x64

    .line 394
    div-int/2addr p0, p1

    invoke-static {p0}, Lorg/telegram/messenger/LiteMode;->-$$Nest$sfputlastBatteryLevelCached(I)V

    .line 395
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p0

    invoke-static {p0, p1}, Lorg/telegram/messenger/LiteMode;->-$$Nest$sfputlastBatteryLevelChecked(J)V

    goto :goto_0

    :cond_0
    const-wide/16 p0, 0x0

    .line 397
    invoke-static {p0, p1}, Lorg/telegram/messenger/LiteMode;->-$$Nest$sfputlastBatteryLevelChecked(J)V

    .line 399
    :goto_0
    invoke-static {}, Lorg/telegram/messenger/LiteMode;->getValue()I

    return-void
.end method
