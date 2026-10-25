.class public final Lcom/exteragram/messenger/utils/chats/StickerTime$Row;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/utils/chats/StickerTime;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Row"
.end annotation


# instance fields
.field public anchorY:F

.field public cellWidth:F

.field public hasName:Z

.field public message:Lorg/telegram/messenger/MessageObject;

.field public nameHeight:F

.field public nameLeft:F

.field public nameTop:F

.field public nameWidth:F

.field public overStickerOffsetX:F

.field public sideButton:Z

.field public stickerLeft:F

.field public stickerRight:F

.field public stickerWidth:F

.field public timeWidth:F


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 21
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method
