.class Landroidx/media3/session/legacy/MediaSessionCompat$1;
.super Landroidx/media3/session/legacy/MediaSessionCompat$Callback;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Landroidx/media3/session/legacy/MediaSessionCompat;-><init>(Landroid/content/Context;Ljava/lang/String;Landroid/content/ComponentName;Landroid/app/PendingIntent;Landroid/os/Bundle;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Landroidx/media3/session/legacy/MediaSessionCompat;


# direct methods
.method public constructor <init>(Landroidx/media3/session/legacy/MediaSessionCompat;)V
    .locals 0

    .line 366
    iput-object p1, p0, Landroidx/media3/session/legacy/MediaSessionCompat$1;->this$0:Landroidx/media3/session/legacy/MediaSessionCompat;

    invoke-direct {p0}, Landroidx/media3/session/legacy/MediaSessionCompat$Callback;-><init>()V

    return-void
.end method
