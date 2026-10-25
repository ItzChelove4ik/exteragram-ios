.class public Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;
.super Landroid/view/View;
.source "SourceFile"

# interfaces
.implements Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$Callback;


# static fields
.field private static final shapeCache:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Landroid/graphics/Path;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private bitmap:Landroid/graphics/Bitmap;

.field private bitmapAlpha:F

.field private bitmapIsExact:Z

.field private final bitmapPaint:Landroid/graphics/Paint;

.field private bitmapShader:Landroid/graphics/BitmapShader;

.field private crossfade:Z

.field private icon:Lcom/exteragram/messenger/appicons/AppIcon;

.field private final iconBounds:Landroid/graphics/Rect;

.field private final iconPath:Landroid/graphics/Path;

.field private final placeholderPaint:Landroid/graphics/Paint;

.field private previewReadyListener:Ljava/lang/Runnable;

.field private requestedSize:I

.field private final resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

.field private scale:F

.field private final shaderMatrix:Landroid/graphics/Matrix;

.field private underlayBitmap:Landroid/graphics/Bitmap;

.field private final underlayPaint:Landroid/graphics/Paint;

.field private underlayShader:Landroid/graphics/BitmapShader;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 44
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->shapeCache:Ljava/util/Map;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V
    .locals 1

    .line 72
    invoke-direct {p0, p1}, Landroid/view/View;-><init>(Landroid/content/Context;)V

    .line 49
    new-instance p1, Landroid/graphics/Paint;

    const/4 v0, 0x3

    invoke-direct {p1, v0}, Landroid/graphics/Paint;-><init>(I)V

    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapPaint:Landroid/graphics/Paint;

    .line 50
    new-instance p1, Landroid/graphics/Paint;

    invoke-direct {p1, v0}, Landroid/graphics/Paint;-><init>(I)V

    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->underlayPaint:Landroid/graphics/Paint;

    .line 51
    new-instance p1, Landroid/graphics/Paint;

    const/4 v0, 0x1

    invoke-direct {p1, v0}, Landroid/graphics/Paint;-><init>(I)V

    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->placeholderPaint:Landroid/graphics/Paint;

    .line 52
    new-instance p1, Landroid/graphics/Path;

    invoke-direct {p1}, Landroid/graphics/Path;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->iconPath:Landroid/graphics/Path;

    .line 53
    new-instance p1, Landroid/graphics/Rect;

    invoke-direct {p1}, Landroid/graphics/Rect;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->iconBounds:Landroid/graphics/Rect;

    .line 54
    new-instance p1, Landroid/graphics/Matrix;

    invoke-direct {p1}, Landroid/graphics/Matrix;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->shaderMatrix:Landroid/graphics/Matrix;

    const/high16 p1, 0x3f800000    # 1.0f

    .line 67
    iput p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->scale:F

    .line 73
    iput-object p2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    return-void
.end method

.method private drawBitmap(Landroid/graphics/Canvas;Landroid/graphics/Bitmap;Landroid/graphics/BitmapShader;Landroid/graphics/Paint;I)V
    .locals 3

    .line 196
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->shaderMatrix:Landroid/graphics/Matrix;

    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->iconBounds:Landroid/graphics/Rect;

    iget v2, v1, Landroid/graphics/Rect;->left:I

    int-to-float v2, v2

    iget v1, v1, Landroid/graphics/Rect;->top:I

    int-to-float v1, v1

    invoke-virtual {v0, v2, v1}, Landroid/graphics/Matrix;->setTranslate(FF)V

    .line 197
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->shaderMatrix:Landroid/graphics/Matrix;

    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->iconBounds:Landroid/graphics/Rect;

    invoke-virtual {v1}, Landroid/graphics/Rect;->width()I

    move-result v1

    int-to-float v1, v1

    invoke-virtual {p2}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v2

    int-to-float v2, v2

    div-float/2addr v1, v2

    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->iconBounds:Landroid/graphics/Rect;

    invoke-virtual {v2}, Landroid/graphics/Rect;->height()I

    move-result v2

    int-to-float v2, v2

    invoke-virtual {p2}, Landroid/graphics/Bitmap;->getHeight()I

    move-result p2

    int-to-float p2, p2

    div-float/2addr v2, p2

    invoke-virtual {v0, v1, v2}, Landroid/graphics/Matrix;->preScale(FF)Z

    .line 198
    iget-object p2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->shaderMatrix:Landroid/graphics/Matrix;

    invoke-virtual {p3, p2}, Landroid/graphics/Shader;->setLocalMatrix(Landroid/graphics/Matrix;)V

    .line 199
    invoke-virtual {p4, p5}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 200
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->iconPath:Landroid/graphics/Path;

    invoke-virtual {p1, p0, p4}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    return-void
.end method

.method private requestPreview()V
    .locals 3

    .line 110
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->icon:Lcom/exteragram/messenger/appicons/AppIcon;

    if-eqz v0, :cond_4

    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v0

    if-gtz v0, :cond_0

    goto :goto_0

    .line 113
    :cond_0
    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v0

    const/high16 v1, 0x40a00000    # 5.0f

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    mul-int/lit8 v1, v1, 0x2

    sub-int/2addr v0, v1

    if-gtz v0, :cond_1

    goto :goto_0

    .line 117
    :cond_1
    iput v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->requestedSize:I

    .line 118
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->icon:Lcom/exteragram/messenger/appicons/AppIcon;

    invoke-static {v1, v0}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->getCached(Lcom/exteragram/messenger/appicons/AppIcon;I)Landroid/graphics/Bitmap;

    move-result-object v1

    if-eqz v1, :cond_2

    const/4 v0, 0x1

    .line 120
    invoke-direct {p0, v1, v0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->setContent(Landroid/graphics/Bitmap;Z)V

    .line 121
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->previewReadyListener:Ljava/lang/Runnable;

    if-eqz p0, :cond_4

    .line 122
    invoke-interface {p0}, Ljava/lang/Runnable;->run()V

    return-void

    .line 126
    :cond_2
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->icon:Lcom/exteragram/messenger/appicons/AppIcon;

    invoke-static {v1}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->getAnyCached(Lcom/exteragram/messenger/appicons/AppIcon;)Landroid/graphics/Bitmap;

    move-result-object v1

    if-eqz v1, :cond_3

    const/4 v2, 0x0

    .line 128
    invoke-direct {p0, v1, v2}, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->setContent(Landroid/graphics/Bitmap;Z)V

    .line 130
    :cond_3
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->icon:Lcom/exteragram/messenger/appicons/AppIcon;

    invoke-static {v1, v0, p0}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->load(Lcom/exteragram/messenger/appicons/AppIcon;ILcom/exteragram/messenger/appicons/AppIconPreviewLoader$Callback;)V

    :cond_4
    :goto_0
    return-void
.end method

.method private setContent(Landroid/graphics/Bitmap;Z)V
    .locals 1

    .line 134
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmap:Landroid/graphics/Bitmap;

    if-ne v0, p1, :cond_0

    .line 135
    iget-boolean p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapIsExact:Z

    or-int/2addr p1, p2

    iput-boolean p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapIsExact:Z

    return-void

    :cond_0
    if-eqz v0, :cond_1

    .line 138
    iget-boolean v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapIsExact:Z

    if-nez v0, :cond_1

    if-eqz p2, :cond_1

    const/4 v0, 0x1

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    .line 139
    :goto_0
    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmap:Landroid/graphics/Bitmap;

    const/4 p1, 0x0

    .line 140
    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapShader:Landroid/graphics/BitmapShader;

    .line 141
    iput-boolean p2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapIsExact:Z

    if-nez v0, :cond_3

    .line 143
    iget-boolean p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->crossfade:Z

    if-nez p1, :cond_2

    if-eqz p2, :cond_2

    iget-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->underlayBitmap:Landroid/graphics/Bitmap;

    if-nez p1, :cond_2

    const/high16 p1, 0x3f800000    # 1.0f

    goto :goto_1

    :cond_2
    const/4 p1, 0x0

    :goto_1
    iput p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapAlpha:F

    :cond_3
    return-void
.end method

.method private shape(F)Landroid/graphics/Path;
    .locals 3

    .line 186
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v0, "_"

    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget v1, Lorg/telegram/messenger/AndroidUtilities;->density:F

    invoke-virtual {p0, v1}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getUseSystemIconShape()Z

    move-result v0

    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 187
    sget-object v0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->shapeCache:Ljava/util/Map;

    invoke-interface {v0, p0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/graphics/Path;

    if-nez v1, :cond_0

    .line 189
    sget-object v1, Lcom/exteragram/messenger/preferences/utils/IconShapeHelper;->INSTANCE:Lcom/exteragram/messenger/preferences/utils/IconShapeHelper;

    const v2, 0x3e8f5c29    # 0.28f

    mul-float/2addr v2, p1

    invoke-virtual {v1, p1, p1, v2}, Lcom/exteragram/messenger/preferences/utils/IconShapeHelper;->getFinalIconShapePath(FFF)Landroid/graphics/Path;

    move-result-object p1

    .line 190
    invoke-interface {v0, p0, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-object p1

    :cond_0
    return-object v1
.end method

.method private updatePaths()V
    .locals 6

    .line 167
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->iconPath:Landroid/graphics/Path;

    invoke-virtual {v0}, Landroid/graphics/Path;->rewind()V

    .line 168
    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v0

    if-gtz v0, :cond_0

    goto :goto_0

    .line 172
    :cond_0
    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v0

    int-to-float v0, v0

    sget v1, Lorg/telegram/messenger/AndroidUtilities;->density:F

    div-float/2addr v0, v1

    const/high16 v1, 0x41200000    # 10.0f

    sub-float/2addr v0, v1

    const/4 v1, 0x0

    cmpg-float v1, v0, v1

    if-gtz v1, :cond_1

    :goto_0
    return-void

    .line 178
    :cond_1
    new-instance v1, Landroid/graphics/Matrix;

    invoke-direct {v1}, Landroid/graphics/Matrix;-><init>()V

    const/high16 v2, 0x40a00000    # 5.0f

    .line 179
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    int-to-float v3, v3

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v4

    int-to-float v4, v4

    invoke-virtual {v1, v3, v4}, Landroid/graphics/Matrix;->setTranslate(FF)V

    .line 180
    invoke-direct {p0, v0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->shape(F)Landroid/graphics/Path;

    move-result-object v0

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->iconPath:Landroid/graphics/Path;

    invoke-virtual {v0, v1, v3}, Landroid/graphics/Path;->transform(Landroid/graphics/Matrix;Landroid/graphics/Path;)V

    .line 182
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->iconBounds:Landroid/graphics/Rect;

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v3

    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v4

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v5

    sub-int/2addr v4, v5

    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result p0

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    sub-int/2addr p0, v2

    invoke-virtual {v0, v1, v3, v4, p0}, Landroid/graphics/Rect;->set(IIII)V

    return-void
.end method


# virtual methods
.method public onDraw(Landroid/graphics/Canvas;)V
    .locals 10

    .line 205
    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->iconPath:Landroid/graphics/Path;

    invoke-virtual {v2}, Landroid/graphics/Path;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_0

    goto/16 :goto_3

    .line 209
    :cond_0
    iget v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->scale:F

    const/high16 v6, 0x3f800000    # 1.0f

    cmpl-float v2, v2, v6

    if-eqz v2, :cond_1

    const/4 v2, 0x1

    :goto_0
    move v7, v2

    goto :goto_1

    :cond_1
    const/4 v2, 0x0

    goto :goto_0

    :goto_1
    if-eqz v7, :cond_2

    .line 211
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    .line 212
    iget v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->scale:F

    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v3

    int-to-float v3, v3

    const/high16 v4, 0x40000000    # 2.0f

    div-float/2addr v3, v4

    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result v5

    int-to-float v5, v5

    div-float/2addr v5, v4

    invoke-virtual {p1, v2, v2, v3, v5}, Landroid/graphics/Canvas;->scale(FFFF)V

    .line 215
    :cond_2
    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmap:Landroid/graphics/Bitmap;

    if-eqz v2, :cond_3

    iget v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapAlpha:F

    cmpg-float v3, v2, v6

    if-gez v3, :cond_3

    const v3, 0x3d94f209

    add-float/2addr v2, v3

    .line 216
    invoke-static {v6, v2}, Ljava/lang/Math;->min(FF)F

    move-result v2

    iput v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapAlpha:F

    .line 217
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    .line 220
    :cond_3
    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->underlayBitmap:Landroid/graphics/Bitmap;

    if-eqz v2, :cond_5

    iget v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapAlpha:F

    cmpg-float v2, v2, v6

    if-gez v2, :cond_5

    .line 221
    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->underlayShader:Landroid/graphics/BitmapShader;

    if-nez v2, :cond_4

    .line 222
    new-instance v2, Landroid/graphics/BitmapShader;

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->underlayBitmap:Landroid/graphics/Bitmap;

    sget-object v4, Landroid/graphics/Shader$TileMode;->CLAMP:Landroid/graphics/Shader$TileMode;

    invoke-direct {v2, v3, v4, v4}, Landroid/graphics/BitmapShader;-><init>(Landroid/graphics/Bitmap;Landroid/graphics/Shader$TileMode;Landroid/graphics/Shader$TileMode;)V

    iput-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->underlayShader:Landroid/graphics/BitmapShader;

    .line 223
    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->underlayPaint:Landroid/graphics/Paint;

    invoke-virtual {v3, v2}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    .line 225
    :cond_4
    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->underlayBitmap:Landroid/graphics/Bitmap;

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->underlayShader:Landroid/graphics/BitmapShader;

    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->underlayPaint:Landroid/graphics/Paint;

    const/16 v5, 0xff

    move-object v0, p0

    move-object v1, p1

    invoke-direct/range {v0 .. v5}, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->drawBitmap(Landroid/graphics/Canvas;Landroid/graphics/Bitmap;Landroid/graphics/BitmapShader;Landroid/graphics/Paint;I)V

    .line 228
    :cond_5
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmap:Landroid/graphics/Bitmap;

    if-eqz v1, :cond_7

    .line 229
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapShader:Landroid/graphics/BitmapShader;

    if-nez v1, :cond_6

    .line 230
    new-instance v1, Landroid/graphics/BitmapShader;

    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmap:Landroid/graphics/Bitmap;

    sget-object v3, Landroid/graphics/Shader$TileMode;->CLAMP:Landroid/graphics/Shader$TileMode;

    invoke-direct {v1, v2, v3, v3}, Landroid/graphics/BitmapShader;-><init>(Landroid/graphics/Bitmap;Landroid/graphics/Shader$TileMode;Landroid/graphics/Shader$TileMode;)V

    iput-object v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapShader:Landroid/graphics/BitmapShader;

    .line 231
    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapPaint:Landroid/graphics/Paint;

    invoke-virtual {v2, v1}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    .line 233
    :cond_6
    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmap:Landroid/graphics/Bitmap;

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapShader:Landroid/graphics/BitmapShader;

    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapPaint:Landroid/graphics/Paint;

    const/high16 v1, 0x437f0000    # 255.0f

    iget v5, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapAlpha:F

    mul-float/2addr v5, v1

    float-to-int v5, v5

    move-object v0, p0

    move-object v1, p1

    invoke-direct/range {v0 .. v5}, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->drawBitmap(Landroid/graphics/Canvas;Landroid/graphics/Bitmap;Landroid/graphics/BitmapShader;Landroid/graphics/Paint;I)V

    .line 234
    iget v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapAlpha:F

    cmpl-float v2, v2, v6

    if-ltz v2, :cond_8

    const/4 v2, 0x0

    .line 235
    iput-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->underlayBitmap:Landroid/graphics/Bitmap;

    .line 236
    iput-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->underlayShader:Landroid/graphics/BitmapShader;

    goto :goto_2

    .line 238
    :cond_7
    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->underlayBitmap:Landroid/graphics/Bitmap;

    if-nez v2, :cond_8

    .line 239
    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_switchTrack:I

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v2, v3}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v3

    const/16 v4, 0x38

    invoke-static {v3, v4}, Landroidx/core/graphics/ColorUtils;->setAlphaComponent(II)I

    move-result v3

    .line 240
    iget-object v4, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->resourcesProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    invoke-static {v2, v4}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v2

    const/16 v4, 0x72

    invoke-static {v2, v4}, Landroidx/core/graphics/ColorUtils;->setAlphaComponent(II)I

    move-result v2

    .line 241
    invoke-static {}, Landroid/os/SystemClock;->uptimeMillis()J

    move-result-wide v4

    const-wide/16 v8, 0x384

    rem-long/2addr v4, v8

    long-to-float v4, v4

    const/high16 v5, 0x44610000    # 900.0f

    div-float/2addr v4, v5

    float-to-double v4, v4

    const-wide v8, 0x400921fb54442d18L    # Math.PI

    mul-double/2addr v4, v8

    const-wide/high16 v8, 0x4000000000000000L    # 2.0

    mul-double/2addr v4, v8

    .line 242
    invoke-static {v4, v5}, Ljava/lang/Math;->sin(D)D

    move-result-wide v4

    double-to-float v4, v4

    const/high16 v5, 0x3f000000    # 0.5f

    mul-float/2addr v4, v5

    add-float/2addr v4, v5

    .line 243
    iget-object v5, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->placeholderPaint:Landroid/graphics/Paint;

    invoke-static {v3, v2, v4}, Landroidx/core/graphics/ColorUtils;->blendARGB(IIF)I

    move-result v2

    invoke-virtual {v5, v2}, Landroid/graphics/Paint;->setColor(I)V

    .line 244
    iget-object v2, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->iconPath:Landroid/graphics/Path;

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->placeholderPaint:Landroid/graphics/Paint;

    invoke-virtual {p1, v2, v3}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    .line 245
    invoke-virtual {p0}, Landroid/view/View;->postInvalidateOnAnimation()V

    :cond_8
    :goto_2
    if-eqz v7, :cond_9

    .line 249
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    :cond_9
    :goto_3
    return-void
.end method

.method public onPreviewReady(Lcom/exteragram/messenger/appicons/AppIcon;ILandroid/graphics/Bitmap;)V
    .locals 1

    .line 149
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->icon:Lcom/exteragram/messenger/appicons/AppIcon;

    if-ne v0, p1, :cond_1

    iget p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->requestedSize:I

    if-eq p1, p2, :cond_0

    goto :goto_0

    :cond_0
    const/4 p1, 0x1

    .line 152
    invoke-direct {p0, p3, p1}, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->setContent(Landroid/graphics/Bitmap;Z)V

    .line 153
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    .line 154
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->previewReadyListener:Ljava/lang/Runnable;

    if-eqz p0, :cond_1

    .line 155
    invoke-interface {p0}, Ljava/lang/Runnable;->run()V

    :cond_1
    :goto_0
    return-void
.end method

.method public onSizeChanged(IIII)V
    .locals 0

    .line 161
    invoke-super {p0, p1, p2, p3, p4}, Landroid/view/View;->onSizeChanged(IIII)V

    .line 162
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->updatePaths()V

    .line 163
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->requestPreview()V

    return-void
.end method

.method public setCrossfade(Z)V
    .locals 0

    .line 81
    iput-boolean p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->crossfade:Z

    return-void
.end method

.method public setIcon(Lcom/exteragram/messenger/appicons/AppIcon;)V
    .locals 3

    .line 85
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->icon:Lcom/exteragram/messenger/appicons/AppIcon;

    if-ne v0, p1, :cond_0

    return-void

    .line 88
    :cond_0
    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->icon:Lcom/exteragram/messenger/appicons/AppIcon;

    .line 89
    iget-boolean p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->crossfade:Z

    const/4 v0, 0x0

    if-eqz p1, :cond_1

    iget-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmap:Landroid/graphics/Bitmap;

    if-eqz p1, :cond_1

    iget v1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapAlpha:F

    const/high16 v2, 0x3f800000    # 1.0f

    cmpl-float v1, v1, v2

    if-ltz v1, :cond_1

    .line 90
    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->underlayBitmap:Landroid/graphics/Bitmap;

    .line 91
    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->underlayShader:Landroid/graphics/BitmapShader;

    .line 93
    :cond_1
    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmap:Landroid/graphics/Bitmap;

    .line 94
    iput-object v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapShader:Landroid/graphics/BitmapShader;

    const/4 p1, 0x0

    .line 95
    iput-boolean p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapIsExact:Z

    const/4 p1, 0x0

    .line 96
    iput p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->bitmapAlpha:F

    .line 97
    invoke-direct {p0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->requestPreview()V

    .line 98
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method

.method public setOnPreviewReady(Ljava/lang/Runnable;)V
    .locals 0

    .line 77
    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->previewReadyListener:Ljava/lang/Runnable;

    return-void
.end method

.method public setPreviewScale(F)V
    .locals 1

    .line 102
    iget v0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->scale:F

    cmpl-float v0, v0, p1

    if-nez v0, :cond_0

    return-void

    .line 105
    :cond_0
    iput p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconPreviewView;->scale:F

    .line 106
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method
