.class public final Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$1;
.super Lorg/telegram/ui/Components/BackupImageView;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->createView(Landroid/content/Context;)Landroid/view/View;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\u0008\n\u0018\u00002\u00020\u0001J\u0008\u0010\u0002\u001a\u00020\u0003H\u0014J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0008\u001a\u00020\u0007H\u0014J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u000cH\u0014\u00a8\u0006\r"
    }
    d2 = {
        "com/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$1",
        "Lorg/telegram/ui/Components/BackupImageView;",
        "createImageReciever",
        "Lorg/telegram/messenger/ImageReceiver;",
        "onMeasure",
        "",
        "widthMeasureSpec",
        "",
        "heightMeasureSpec",
        "verifyDrawable",
        "",
        "who",
        "Landroid/graphics/drawable/Drawable;",
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
.method public constructor <init>(Landroid/content/Context;Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;)V
    .locals 0

    iput-object p2, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$1;->this$0:Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;

    .line 90
    invoke-direct {p0, p1}, Lorg/telegram/ui/Components/BackupImageView;-><init>(Landroid/content/Context;)V

    return-void
.end method


# virtual methods
.method public createImageReciever()Lorg/telegram/messenger/ImageReceiver;
    .locals 2

    .line 91
    new-instance v0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$1$createImageReciever$1;

    iget-object v1, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$1;->this$0:Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;

    invoke-direct {v0, p0, v1}, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$1$createImageReciever$1;-><init>(Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$1;Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;)V

    return-object v0
.end method

.method public onMeasure(II)V
    .locals 1

    .line 101
    invoke-static {p1}, Landroid/view/View$MeasureSpec;->getSize(I)I

    move-result p1

    const/high16 p2, 0x40000000    # 2.0f

    invoke-static {p1, p2}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result p1

    const/high16 v0, 0x43700000    # 240.0f

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    invoke-static {v0, p2}, Landroid/view/View$MeasureSpec;->makeMeasureSpec(II)I

    move-result p2

    invoke-super {p0, p1, p2}, Landroid/view/View;->onMeasure(II)V

    return-void
.end method

.method public verifyDrawable(Landroid/graphics/drawable/Drawable;)Z
    .locals 1

    .line 104
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$1;->this$0:Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->access$getMapLoadingDrawable$p(Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;)Lorg/telegram/ui/Components/ClipRoundedDrawable;

    move-result-object v0

    if-eq p1, v0, :cond_1

    invoke-super {p0, p1}, Lorg/telegram/ui/Components/BackupImageView;->verifyDrawable(Landroid/graphics/drawable/Drawable;)Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x1

    return p0
.end method
