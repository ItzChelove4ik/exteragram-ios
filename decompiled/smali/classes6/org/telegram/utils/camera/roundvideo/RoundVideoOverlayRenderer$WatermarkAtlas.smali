.class final Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "WatermarkAtlas"
.end annotation


# instance fields
.field final frameSize:I

.field final height:I

.field final logoAtlasHeight:I

.field final pixels:Ljava/nio/ByteBuffer;

.field final textSize:I

.field final width:I


# direct methods
.method public constructor <init>(IIIIILjava/nio/ByteBuffer;)V
    .locals 0

    .line 1243
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 1244
    iput p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;->width:I

    .line 1245
    iput p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;->height:I

    .line 1246
    iput p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;->textSize:I

    .line 1247
    iput p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;->frameSize:I

    .line 1248
    iput p5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;->logoAtlasHeight:I

    .line 1249
    iput-object p6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;->pixels:Ljava/nio/ByteBuffer;

    return-void
.end method
