.class public final Lorg/telegram/messenger/IMapsProvider$CameraPosition;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/messenger/IMapsProvider;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "CameraPosition"
.end annotation


# instance fields
.field public final target:Lorg/telegram/messenger/IMapsProvider$LatLng;

.field public final zoom:F


# direct methods
.method public constructor <init>(Lorg/telegram/messenger/IMapsProvider$LatLng;F)V
    .locals 0

    .line 184
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 185
    iput-object p1, p0, Lorg/telegram/messenger/IMapsProvider$CameraPosition;->target:Lorg/telegram/messenger/IMapsProvider$LatLng;

    .line 186
    iput p2, p0, Lorg/telegram/messenger/IMapsProvider$CameraPosition;->zoom:F

    return-void
.end method
