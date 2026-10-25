.class public final Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;
.super Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$Companion;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u0007\n\u0002\u0008\u0006\u0018\u0000 +2\u00020\u0001:\u0001+B\u0007\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0008\u0010\u0011\u001a\u00020\u0012H\u0016J\u0008\u0010\u0013\u001a\u00020\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0017H\u0014J\u0008\u0010\u0018\u001a\u00020\u0019H\u0002J\u001e\u0010\u001a\u001a\u00020\u00192\u000c\u0010\u001b\u001a\u0008\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u001fH\u0014J0\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020&H\u0014J\u0010\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\u0014H\u0002J\u0008\u0010*\u001a\u00020\u0019H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0008\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000c\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"
    }
    d2 = {
        "Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "<init>",
        "()V",
        "mapPreviewContainer",
        "Landroid/widget/FrameLayout;",
        "mapMarker",
        "Landroid/view/View;",
        "mapLoadingDrawable",
        "Lorg/telegram/ui/Components/ClipRoundedDrawable;",
        "mapPreview",
        "Lorg/telegram/ui/Components/BackupImageView;",
        "currentGeo",
        "Lorg/telegram/tgnet/TLRPC$GeoPoint;",
        "addressContainer",
        "addressText",
        "Landroid/widget/TextView;",
        "getTitle",
        "",
        "needHideTitle",
        "",
        "createView",
        "context",
        "Landroid/content/Context;",
        "updateMapPreview",
        "",
        "fillItems",
        "items",
        "Ljava/util/ArrayList;",
        "Lorg/telegram/ui/Components/UItem;",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "onClick",
        "item",
        "view",
        "position",
        "",
        "x",
        "",
        "y",
        "updateLocationSetting",
        "useCurrent",
        "openMapPicker",
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
.field public static final Companion:Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$Companion;


# instance fields
.field private addressContainer:Landroid/widget/FrameLayout;

.field private addressText:Landroid/widget/TextView;

.field private currentGeo:Lorg/telegram/tgnet/TLRPC$GeoPoint;

.field private mapLoadingDrawable:Lorg/telegram/ui/Components/ClipRoundedDrawable;

.field private mapMarker:Landroid/view/View;

.field private mapPreview:Lorg/telegram/ui/Components/BackupImageView;

.field private mapPreviewContainer:Landroid/widget/FrameLayout;


# direct methods
.method public static $r8$lambda$K18aKbEB02VwdgSk6OgSRlmQkY4(Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;Landroid/location/Location;)V
    .locals 0

    if-eqz p1, :cond_0

    const/4 p1, 0x1

    .line 208
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->updateLocationSetting(Z)V

    :cond_0
    return-void
.end method

.method public static $r8$lambda$dyTW6MAr64ZRqyJ5JhTAxzD0hK4(Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;Landroid/location/Location;)V
    .locals 0

    if-eqz p1, :cond_0

    .line 214
    iget-object p1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    if-eqz p1, :cond_0

    .line 215
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_0

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_0
    return-void
.end method

.method public static $r8$lambda$kM8epmpckYHOpxod_f3EFFqG-34(Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;Landroid/view/View;)V
    .locals 0

    .line 142
    invoke-direct {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->openMapPicker()V

    return-void
.end method

.method public static $r8$lambda$r1F3JTTSwGevkR3rdkI74dTACBQ(Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;Lorg/telegram/ui/LocationActivity;Lorg/telegram/tgnet/TLRPC$MessageMedia;IZIJ)V
    .locals 0

    .line 253
    iget-object p2, p2, Lorg/telegram/tgnet/TLRPC$MessageMedia;->geo:Lorg/telegram/tgnet/TLRPC$GeoPoint;

    iput-object p2, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->currentGeo:Lorg/telegram/tgnet/TLRPC$GeoPoint;

    .line 254
    invoke-virtual {p1}, Lorg/telegram/ui/LocationActivity;->getAddressName()Ljava/lang/String;

    move-result-object p1

    if-nez p1, :cond_0

    const-string p1, ""

    .line 256
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getGSON()Lcom/google/gson/Gson;

    move-result-object p2

    iget-object p3, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->currentGeo:Lorg/telegram/tgnet/TLRPC$GeoPoint;

    invoke-virtual {p2, p3}, Lcom/google/gson/Gson;->toJson(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    invoke-static {p2}, Lcom/exteragram/messenger/pillstack/core/PillStackConfig;->setCustomWeatherLocation(Ljava/lang/String;)V

    .line 257
    invoke-static {p1}, Lcom/exteragram/messenger/pillstack/core/PillStackConfig;->setCustomWeatherAddress(Ljava/lang/String;)V

    const/4 p1, 0x0

    .line 259
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->updateLocationSetting(Z)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->Companion:Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$Companion;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .line 56
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;-><init>()V

    return-void
.end method

.method public static final synthetic access$getMapLoadingDrawable$p(Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;)Lorg/telegram/ui/Components/ClipRoundedDrawable;
    .locals 0

    .line 56
    iget-object p0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapLoadingDrawable:Lorg/telegram/ui/Components/ClipRoundedDrawable;

    return-object p0
.end method

.method public static final synthetic access$getMapMarker$p(Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;)Landroid/view/View;
    .locals 0

    .line 56
    iget-object p0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapMarker:Landroid/view/View;

    return-object p0
.end method

.method private final openMapPicker()V
    .locals 3

    .line 245
    new-instance v0, Lorg/telegram/ui/LocationActivity;

    const/16 v1, 0x8

    invoke-direct {v0, v1}, Lorg/telegram/ui/LocationActivity;-><init>(I)V

    .line 246
    iget-object v1, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->currentGeo:Lorg/telegram/tgnet/TLRPC$GeoPoint;

    if-eqz v1, :cond_0

    .line 247
    new-instance v2, Lorg/telegram/tgnet/TLRPC$TL_channelLocation;

    invoke-direct {v2}, Lorg/telegram/tgnet/TLRPC$TL_channelLocation;-><init>()V

    .line 248
    iput-object v1, v2, Lorg/telegram/tgnet/TLRPC$TL_channelLocation;->geo_point:Lorg/telegram/tgnet/TLRPC$GeoPoint;

    .line 249
    invoke-static {}, Lcom/exteragram/messenger/pillstack/core/PillStackConfig;->getCustomWeatherAddress()Ljava/lang/String;

    move-result-object v1

    iput-object v1, v2, Lorg/telegram/tgnet/TLRPC$TL_channelLocation;->address:Ljava/lang/String;

    .line 250
    invoke-virtual {v0, v2}, Lorg/telegram/ui/LocationActivity;->setInitialLocation(Lorg/telegram/tgnet/TLRPC$TL_channelLocation;)V

    .line 252
    :cond_0
    new-instance v1, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$$ExternalSyntheticLambda1;

    invoke-direct {v1, p0, v0}, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;Lorg/telegram/ui/LocationActivity;)V

    invoke-virtual {v0, v1}, Lorg/telegram/ui/LocationActivity;->setDelegate(Lorg/telegram/ui/LocationActivity$LocationActivityDelegate;)V

    .line 261
    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void
.end method

.method private final updateLocationSetting(Z)V
    .locals 0

    .line 236
    invoke-static {p1}, Lcom/exteragram/messenger/pillstack/core/PillStackConfig;->setUseCurrentLocation(Z)V

    .line 237
    sget-object p1, Lcom/exteragram/messenger/pillstack/core/PillType;->WEATHER:Lcom/exteragram/messenger/pillstack/core/PillType;

    invoke-virtual {p1}, Lcom/exteragram/messenger/pillstack/core/PillType;->getId()I

    move-result p1

    filled-new-array {p1}, [I

    move-result-object p1

    invoke-static {p1}, Lcom/exteragram/messenger/pillstack/core/PillStackConfig;->notifySettingsChanged([I)V

    .line 238
    iget-object p1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->fragmentView:Landroid/view/View;

    if-eqz p1, :cond_0

    .line 239
    invoke-direct {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->updateMapPreview()V

    .line 240
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_0

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_0
    return-void
.end method

.method private final updateMapPreview()V
    .locals 15

    .line 156
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->currentGeo:Lorg/telegram/tgnet/TLRPC$GeoPoint;

    const/4 v1, 0x0

    if-nez v0, :cond_1

    .line 158
    iget-object p0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapPreview:Lorg/telegram/ui/Components/BackupImageView;

    if-nez p0, :cond_0

    move-object p0, v1

    :cond_0
    invoke-virtual {p0, v1}, Lorg/telegram/ui/Components/BackupImageView;->setImageBitmap(Landroid/graphics/Bitmap;)V

    return-void

    .line 162
    :cond_1
    iget-object v2, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapMarker:Landroid/view/View;

    if-nez v2, :cond_2

    move-object v2, v1

    :cond_2
    const/4 v3, 0x0

    invoke-virtual {v2, v3}, Landroid/view/View;->setAlpha(F)V

    .line 163
    iget-object v2, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapMarker:Landroid/view/View;

    if-nez v2, :cond_3

    move-object v2, v1

    :cond_3
    const/high16 v3, 0x41400000    # 12.0f

    invoke-static {v3}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    int-to-float v3, v3

    neg-float v3, v3

    invoke-virtual {v2, v3}, Landroid/view/View;->setTranslationY(F)V

    .line 164
    iget-object v2, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapPreview:Lorg/telegram/ui/Components/BackupImageView;

    if-nez v2, :cond_4

    move-object v2, v1

    :cond_4
    invoke-virtual {v2}, Landroid/view/View;->getMeasuredWidth()I

    move-result v2

    if-gtz v2, :cond_5

    sget-object v2, Lorg/telegram/messenger/AndroidUtilities;->displaySize:Landroid/graphics/Point;

    iget v2, v2, Landroid/graphics/Point;->x:I

    goto :goto_0

    :cond_5
    iget-object v2, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapPreview:Lorg/telegram/ui/Components/BackupImageView;

    if-nez v2, :cond_6

    move-object v2, v1

    :cond_6
    invoke-virtual {v2}, Landroid/view/View;->getMeasuredWidth()I

    move-result v2

    :goto_0
    int-to-float v2, v2

    sget v3, Lorg/telegram/messenger/AndroidUtilities;->density:F

    div-float/2addr v2, v3

    float-to-int v2, v2

    float-to-double v3, v3

    .line 166
    invoke-static {v3, v4}, Ljava/lang/Math;->ceil(D)D

    move-result-wide v3

    double-to-float v3, v3

    float-to-int v3, v3

    const/4 v4, 0x2

    invoke-static {v4, v3}, Ljava/lang/Math;->min(II)I

    move-result v14

    .line 167
    iget-object v3, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapPreview:Lorg/telegram/ui/Components/BackupImageView;

    if-nez v3, :cond_7

    move-object v4, v1

    goto :goto_1

    :cond_7
    move-object v4, v3

    :goto_1
    iget-wide v5, v0, Lorg/telegram/tgnet/TLRPC$GeoPoint;->lat:D

    iget-wide v7, v0, Lorg/telegram/tgnet/TLRPC$GeoPoint;->_long:D

    mul-int v11, v14, v2

    mul-int/lit16 v12, v14, 0xf0

    const/16 v13, 0xf

    const-wide/16 v9, 0x0

    invoke-static/range {v5 .. v14}, Lorg/telegram/messenger/WebFile;->createWithGeoPoint(DDJIIII)Lorg/telegram/messenger/WebFile;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/messenger/ImageLocation;->getForWebFile(Lorg/telegram/messenger/WebFile;)Lorg/telegram/messenger/ImageLocation;

    move-result-object v5

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, "_240"

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    iget-object v7, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapLoadingDrawable:Lorg/telegram/ui/Components/ClipRoundedDrawable;

    const/4 v8, 0x0

    const/4 v9, 0x0

    invoke-virtual/range {v4 .. v9}, Lorg/telegram/ui/Components/BackupImageView;->setImage(Lorg/telegram/messenger/ImageLocation;Ljava/lang/String;Landroid/graphics/drawable/Drawable;ILjava/lang/Object;)V

    .line 168
    iget-object p0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->addressText:Landroid/widget/TextView;

    if-nez p0, :cond_8

    goto :goto_2

    :cond_8
    move-object v1, p0

    :goto_2
    invoke-static {}, Lcom/exteragram/messenger/pillstack/core/PillStackConfig;->getCustomWeatherAddress()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v1, p0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    return-void
.end method


# virtual methods
.method public createView(Landroid/content/Context;)Landroid/view/View;
    .locals 11

    .line 80
    new-instance v0, Landroid/widget/TextView;

    invoke-direct {v0, p1}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->addressText:Landroid/widget/TextView;

    const/high16 v1, 0x41600000    # 14.0f

    const/4 v2, 0x1

    .line 81
    invoke-virtual {v0, v2, v1}, Landroid/widget/TextView;->setTextSize(IF)V

    .line 82
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->addressText:Landroid/widget/TextView;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    move-object v0, v1

    :cond_0
    sget v3, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteGrayText:I

    invoke-static {v3}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result v3

    invoke-virtual {v0, v3}, Landroid/widget/TextView;->setTextColor(I)V

    .line 83
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->addressText:Landroid/widget/TextView;

    if-nez v0, :cond_1

    move-object v0, v1

    :cond_1
    const/16 v3, 0x11

    invoke-virtual {v0, v3}, Landroid/widget/TextView;->setGravity(I)V

    .line 84
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->addressText:Landroid/widget/TextView;

    if-nez v0, :cond_2

    move-object v0, v1

    :cond_2
    const/4 v3, 0x0

    invoke-virtual {v0, v3, v3, v3, v3}, Landroid/widget/TextView;->setPadding(IIII)V

    .line 86
    new-instance v0, Landroid/widget/FrameLayout;

    invoke-direct {v0, p1}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->addressContainer:Landroid/widget/FrameLayout;

    .line 87
    iget-object v3, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->addressText:Landroid/widget/TextView;

    if-nez v3, :cond_3

    move-object v3, v1

    :cond_3
    const/high16 v9, 0x41a80000    # 21.0f

    const/high16 v10, 0x41700000    # 15.0f

    const/4 v4, -0x1

    const/high16 v5, -0x40000000    # -2.0f

    const/16 v6, 0x30

    const/high16 v7, 0x41a80000    # 21.0f

    const/high16 v8, 0x41700000    # 15.0f

    invoke-static/range {v4 .. v10}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v4

    invoke-virtual {v0, v3, v4}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 88
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->addressContainer:Landroid/widget/FrameLayout;

    if-nez v0, :cond_4

    move-object v0, v1

    :cond_4
    sget v3, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-virtual {p0, v3}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v4

    invoke-virtual {v0, v4}, Landroid/view/View;->setBackgroundColor(I)V

    .line 90
    new-instance v0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$1;

    invoke-direct {v0, p1, p0}, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$1;-><init>(Landroid/content/Context;Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;)V

    iput-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapPreview:Lorg/telegram/ui/Components/BackupImageView;

    .line 107
    sget v0, Lorg/telegram/messenger/R$raw;->map_placeholder:I

    sget v4, Lorg/telegram/ui/ActionBar/Theme;->key_chat_outLocationIcon:I

    const v5, 0x3e4ccccd    # 0.2f

    invoke-static {v0, v4, v5}, Lorg/telegram/messenger/DocumentObject;->getSvgThumb(IIF)Lorg/telegram/messenger/SvgHelper$SvgDrawable;

    move-result-object v0

    .line 108
    sget v4, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhiteBlackText:I

    iget-object v5, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-virtual {v0, v4, v5}, Lorg/telegram/messenger/SvgHelper$SvgDrawable;->setColorKey(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 109
    invoke-virtual {v0, v2}, Lorg/telegram/messenger/SvgHelper$SvgDrawable;->setAspectCenter(Z)V

    .line 110
    iget-object v2, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapPreview:Lorg/telegram/ui/Components/BackupImageView;

    if-nez v2, :cond_5

    move-object v2, v1

    :cond_5
    invoke-virtual {v2}, Lorg/telegram/ui/Components/BackupImageView;->getImageReceiver()Lorg/telegram/messenger/ImageReceiver;

    move-result-object v2

    invoke-virtual {v0, v2}, Lorg/telegram/messenger/SvgHelper$SvgDrawable;->setParent(Lorg/telegram/messenger/ImageReceiver;)V

    .line 111
    new-instance v2, Lorg/telegram/ui/Components/ClipRoundedDrawable;

    invoke-direct {v2, v0}, Lorg/telegram/ui/Components/ClipRoundedDrawable;-><init>(Landroid/graphics/drawable/Drawable;)V

    .line 112
    iput-object v2, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapLoadingDrawable:Lorg/telegram/ui/Components/ClipRoundedDrawable;

    .line 113
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapPreview:Lorg/telegram/ui/Components/BackupImageView;

    if-nez v0, :cond_6

    move-object v0, v1

    :cond_6
    invoke-virtual {v2, v0}, Landroid/graphics/drawable/Drawable;->setCallback(Landroid/graphics/drawable/Drawable$Callback;)V

    .line 114
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapPreview:Lorg/telegram/ui/Components/BackupImageView;

    if-nez v0, :cond_7

    move-object v0, v1

    :cond_7
    invoke-virtual {p0, v3}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v2

    invoke-virtual {v0, v2}, Landroid/view/View;->setBackgroundColor(I)V

    .line 116
    new-instance v0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$2;

    invoke-direct {v0, p1, p0}, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$createView$2;-><init>(Landroid/content/Context;Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;)V

    iput-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapMarker:Landroid/view/View;

    .line 139
    new-instance v0, Landroid/widget/FrameLayout;

    invoke-direct {v0, p1}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapPreviewContainer:Landroid/widget/FrameLayout;

    .line 140
    iget-object v2, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapPreview:Lorg/telegram/ui/Components/BackupImageView;

    if-nez v2, :cond_8

    move-object v2, v1

    :cond_8
    const/4 v3, -0x1

    const/high16 v4, -0x40800000    # -1.0f

    invoke-static {v3, v4}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v3

    invoke-virtual {v0, v2, v3}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 141
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapPreviewContainer:Landroid/widget/FrameLayout;

    if-nez v0, :cond_9

    move-object v0, v1

    :cond_9
    iget-object v2, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapMarker:Landroid/view/View;

    if-nez v2, :cond_a

    move-object v2, v1

    :cond_a
    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v3, -0x2

    const/high16 v4, -0x40000000    # -2.0f

    const/16 v5, 0x11

    const/4 v6, 0x0

    const/high16 v7, -0x3e080000    # -31.0f

    invoke-static/range {v3 .. v9}, Lorg/telegram/ui/Components/LayoutHelper;->createFrame(IFIFFFF)Landroid/widget/FrameLayout$LayoutParams;

    move-result-object v3

    invoke-virtual {v0, v2, v3}, Landroid/view/ViewGroup;->addView(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V

    .line 142
    iget-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapPreviewContainer:Landroid/widget/FrameLayout;

    if-nez v0, :cond_b

    goto :goto_0

    :cond_b
    move-object v1, v0

    :goto_0
    new-instance v0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;)V

    invoke-virtual {v1, v0}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 144
    invoke-static {}, Lcom/exteragram/messenger/pillstack/core/PillStackConfig;->getCustomWeatherLocation()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_c

    .line 146
    :try_start_0
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getGSON()Lcom/google/gson/Gson;

    move-result-object v1

    const-class v2, Lorg/telegram/tgnet/TLRPC$TL_geoPoint;

    invoke-virtual {v1, v0, v2}, Lcom/google/gson/Gson;->fromJson(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/tgnet/TLRPC$GeoPoint;

    iput-object v0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->currentGeo:Lorg/telegram/tgnet/TLRPC$GeoPoint;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 150
    :catch_0
    :cond_c
    invoke-direct {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->updateMapPreview()V

    .line 152
    invoke-super {p0, p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->createView(Landroid/content/Context;)Landroid/view/View;

    move-result-object p0

    return-object p0
.end method

.method public fillItems(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/UItem;",
            ">;",
            "Lorg/telegram/ui/Components/UniversalAdapter;",
            ")V"
        }
    .end annotation

    .line 172
    invoke-virtual {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->getTitle()Ljava/lang/String;

    move-result-object p2

    sget v0, Lorg/telegram/messenger/R$string;->WeatherPillTopInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    const-string v1, "RestrictedEmoji"

    const-string v2, "\ud83c\udf24"

    invoke-static {p2, v0, v1, v2}, Lorg/telegram/ui/Components/UItem;->asTopView(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/String;Ljava/lang/String;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 174
    sget p2, Lorg/telegram/messenger/R$drawable;->msg_settings_old:I

    sget v0, Lorg/telegram/messenger/R$string;->PillStackPills:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x1

    invoke-static {v1, p2, v0}, Lorg/telegram/ui/Components/UItem;->asButton(IILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 175
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 177
    invoke-static {}, Lcom/exteragram/messenger/pillstack/core/PillStackConfig;->getActivePills()Ljava/util/List;

    move-result-object p2

    sget-object v0, Lcom/exteragram/messenger/pillstack/core/PillType;->WEATHER:Lcom/exteragram/messenger/pillstack/core/PillType;

    invoke-virtual {v0}, Lcom/exteragram/messenger/pillstack/core/PillType;->getId()I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-interface {p2, v0}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_0

    goto/16 :goto_0

    .line 179
    :cond_0
    sget p2, Lorg/telegram/messenger/R$string;->WeatherLocation:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    invoke-static {p2}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 180
    sget p2, Lorg/telegram/messenger/R$string;->CurrentLocation:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    const/4 v0, 0x2

    invoke-static {v0, p2}, Lorg/telegram/ui/Components/UItem;->asRadio(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-static {}, Lcom/exteragram/messenger/pillstack/core/PillStackConfig;->getUseCurrentLocation()Z

    move-result v2

    invoke-virtual {p2, v2}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 181
    sget p2, Lorg/telegram/messenger/R$string;->SelectLocation:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    const/4 v2, 0x3

    invoke-static {v2, p2}, Lorg/telegram/ui/Components/UItem;->asRadio(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-static {}, Lcom/exteragram/messenger/pillstack/core/PillStackConfig;->getUseCurrentLocation()Z

    move-result v2

    xor-int/2addr v1, v2

    invoke-virtual {p2, v1}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 183
    invoke-static {}, Lcom/exteragram/messenger/pillstack/core/PillStackConfig;->getUseCurrentLocation()Z

    move-result p2

    if-eqz p2, :cond_3

    .line 184
    sget p0, Lorg/telegram/messenger/R$string;->WeatherSettingsInfo:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 185
    invoke-static {}, Lorg/telegram/ui/Stories/recorder/Weather;->isLocationPermissionGranted()Z

    move-result p0

    if-nez p0, :cond_1

    .line 186
    sget p0, Lorg/telegram/messenger/R$drawable;->report:I

    sget p2, Lorg/telegram/messenger/R$string;->WeatherLocationPermissionGrant:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    invoke-static {v0, p0, p2}, Lorg/telegram/ui/Components/UItem;->asButton(IILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/UItem;->red()Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 187
    sget p0, Lorg/telegram/messenger/R$string;->WeatherLocationPermissionNo:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void

    .line 188
    :cond_1
    invoke-static {}, Lorg/telegram/ui/Stories/recorder/Weather;->isLocationEnabled()Z

    move-result p0

    if-nez p0, :cond_2

    .line 189
    sget p0, Lorg/telegram/messenger/R$drawable;->filled_location:I

    sget p2, Lorg/telegram/messenger/R$string;->WeatherLocationServicesEnable:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    const/4 v0, 0x4

    invoke-static {v0, p0, p2}, Lorg/telegram/ui/Components/UItem;->asButton(IILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/UItem;->accent()Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 190
    sget p0, Lorg/telegram/messenger/R$string;->GpsDisabledAlertText:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_2
    :goto_0
    return-void

    .line 193
    :cond_3
    iget-object p2, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->mapPreviewContainer:Landroid/widget/FrameLayout;

    const/4 v0, 0x0

    if-nez p2, :cond_4

    move-object p2, v0

    :cond_4
    const/4 v1, 0x5

    invoke-static {v1, p2}, Lorg/telegram/ui/Components/UItem;->asCustom(ILandroid/view/View;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 194
    invoke-static {}, Lcom/exteragram/messenger/pillstack/core/PillStackConfig;->getCustomWeatherAddress()Ljava/lang/String;

    move-result-object p2

    if-eqz p2, :cond_7

    invoke-interface {p2}, Ljava/lang/CharSequence;->length()I

    move-result p2

    if-nez p2, :cond_5

    goto :goto_2

    .line 195
    :cond_5
    iget-object p0, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->addressContainer:Landroid/widget/FrameLayout;

    if-nez p0, :cond_6

    goto :goto_1

    :cond_6
    move-object v0, p0

    :goto_1
    const/4 p0, 0x6

    invoke-static {p0, v0}, Lorg/telegram/ui/Components/UItem;->asCustom(ILandroid/view/View;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 197
    :cond_7
    :goto_2
    sget p0, Lorg/telegram/messenger/R$string;->WeatherSettingsInfo:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public getTitle()Ljava/lang/String;
    .locals 0

    .line 75
    sget p0, Lorg/telegram/messenger/R$string;->WeatherPill:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public needHideTitle()Z
    .locals 0

    const/4 p0, 0x1

    return p0
.end method

.method public onClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 0

    .line 202
    iget p1, p1, Lorg/telegram/ui/Components/UItem;->id:I

    const/4 p2, 0x1

    packed-switch p1, :pswitch_data_0

    goto :goto_0

    .line 231
    :pswitch_0
    invoke-direct {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->openMapPicker()V

    return-void

    .line 213
    :pswitch_1
    new-instance p1, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$$ExternalSyntheticLambda3;

    invoke-direct {p1, p0}, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;)V

    invoke-static {p2, p1}, Lorg/telegram/ui/Stories/recorder/Weather;->getUserLocation(ZLorg/telegram/messenger/Utilities$Callback;)V

    return-void

    .line 219
    :pswitch_2
    invoke-static {}, Lcom/exteragram/messenger/pillstack/core/PillStackConfig;->getUseCurrentLocation()Z

    move-result p1

    if-eqz p1, :cond_1

    .line 220
    iget-object p1, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->currentGeo:Lorg/telegram/tgnet/TLRPC$GeoPoint;

    if-nez p1, :cond_0

    .line 221
    new-instance p1, Lorg/telegram/tgnet/TLRPC$TL_geoPoint;

    invoke-direct {p1}, Lorg/telegram/tgnet/TLRPC$TL_geoPoint;-><init>()V

    const-wide p2, 0x404be0be0ded288dL    # 55.7558

    .line 222
    iput-wide p2, p1, Lorg/telegram/tgnet/TLRPC$GeoPoint;->lat:D

    const-wide p2, 0x4042cf03afb7e910L    # 37.6173

    .line 223
    iput-wide p2, p1, Lorg/telegram/tgnet/TLRPC$GeoPoint;->_long:D

    .line 221
    iput-object p1, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->currentGeo:Lorg/telegram/tgnet/TLRPC$GeoPoint;

    .line 225
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getGSON()Lcom/google/gson/Gson;

    move-result-object p1

    iget-object p2, p0, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->currentGeo:Lorg/telegram/tgnet/TLRPC$GeoPoint;

    invoke-virtual {p1, p2}, Lcom/google/gson/Gson;->toJson(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lcom/exteragram/messenger/pillstack/core/PillStackConfig;->setCustomWeatherLocation(Ljava/lang/String;)V

    :cond_0
    const/4 p1, 0x0

    .line 227
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->updateLocationSetting(Z)V

    .line 229
    :cond_1
    invoke-direct {p0}, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;->openMapPicker()V

    return-void

    .line 205
    :pswitch_3
    invoke-static {}, Lcom/exteragram/messenger/pillstack/core/PillStackConfig;->getUseCurrentLocation()Z

    move-result p1

    if-eqz p1, :cond_3

    invoke-static {}, Lorg/telegram/ui/Stories/recorder/Weather;->isLocationPermissionGranted()Z

    move-result p1

    if-nez p1, :cond_2

    goto :goto_1

    :cond_2
    :goto_0
    return-void

    .line 206
    :cond_3
    :goto_1
    new-instance p1, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$$ExternalSyntheticLambda2;

    invoke-direct {p1, p0}, Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/pillstack/ui/pills/weather/WeatherSettingsActivity;)V

    invoke-static {p2, p1}, Lorg/telegram/ui/Stories/recorder/Weather;->getUserLocation(ZLorg/telegram/messenger/Utilities$Callback;)V

    return-void

    .line 203
    :pswitch_4
    new-instance p1, Lcom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity;

    invoke-direct {p1}, Lcom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity;-><init>()V

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
        :pswitch_0
    .end packed-switch
.end method
