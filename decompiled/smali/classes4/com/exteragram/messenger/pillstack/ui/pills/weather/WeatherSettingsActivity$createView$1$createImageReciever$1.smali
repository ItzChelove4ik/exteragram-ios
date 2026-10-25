.class public final Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$1$createImageReciever$1;
.super Lorg/telegram/messenger/ImageReceiver;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$1;->createImageReciever()Lorg/telegram/messenger/ImageReceiver;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0003*\u0001\u0000\u0008\n\u0018\u00002\u00020\u0001J4\u0010\u0002\u001a\u00020\u00032\u0008\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0008\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0008\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\tH\u0014\u00a8\u0006\u000c"
    }
    d2 = {
        "com/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$1$createImageReciever$1",
        "Lorg/telegram/messenger/ImageReceiver;",
        "setImageBitmapByKey",
        "",
        "drawable",
        "Landroid/graphics/drawable/Drawable;",
        "key",
        "",
        "type",
        "",
        "memCache",
        "guid",
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
.field final synthetic this$0:Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$1;Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;)V
    .locals 0

    iput-object p2, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$1$createImageReciever$1;->this$0:Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;

    .line 91
    invoke-direct {p0, p1}, Lorg/telegram/messenger/ImageReceiver;-><init>(Landroid/view/View;)V

    return-void
.end method


# virtual methods
.method public setImageBitmapByKey(Landroid/graphics/drawable/Drawable;Ljava/lang/String;IZI)Z
    .locals 3

    if-eqz p1, :cond_1

    const/4 v0, 0x1

    if-eq p3, v0, :cond_1

    .line 94
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$1$createImageReciever$1;->this$0:Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->access$getMapMarker$p(Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;)Landroid/view/View;

    move-result-object v0

    if-nez v0, :cond_0

    const/4 v0, 0x0

    :cond_0
    invoke-virtual {v0}, Landroid/view/View;->animate()Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    const/high16 v1, 0x3f800000    # 1.0f

    invoke-virtual {v0, v1}, Landroid/view/ViewPropertyAnimator;->alpha(F)Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/ViewPropertyAnimator;->translationY(F)Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    sget-object v1, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_BACK:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    invoke-virtual {v0, v1}, Landroid/view/ViewPropertyAnimator;->setInterpolator(Landroid/animation/TimeInterpolator;)Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    const-wide/16 v1, 0xfa

    invoke-virtual {v0, v1, v2}, Landroid/view/ViewPropertyAnimator;->setDuration(J)Landroid/view/ViewPropertyAnimator;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/ViewPropertyAnimator;->start()V

    .line 96
    :cond_1
    invoke-super/range {p0 .. p5}, Lorg/telegram/messenger/ImageReceiver;->setImageBitmapByKey(Landroid/graphics/drawable/Drawable;Ljava/lang/String;IZI)Z

    move-result p0

    return p0
.end method
