.class public Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;
.super Landroid/graphics/drawable/Drawable;
.source "SourceFile"

# interfaces
.implements Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$Callback;


# instance fields
.field private bitmap:Landroid/graphics/Bitmap;

.field private final icon:Lcom/exteragram/messenger/appicons/AppIcon;

.field private final matrix:Landroid/graphics/Matrix;

.field private final paint:Landroid/graphics/Paint;

.field private final path:Landroid/graphics/Path;

.field private shader:Landroid/graphics/BitmapShader;

.field private final size:I


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/appicons/AppIcon;I)V
    .locals 4

    .line 44
    invoke-direct {p0}, Landroid/graphics/drawable/Drawable;-><init>()V

    .line 34
    new-instance v0, Landroid/graphics/Paint;

    const/4 v1, 0x3

    invoke-direct {v0, v1}, Landroid/graphics/Paint;-><init>(I)V

    iput-object v0, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->paint:Landroid/graphics/Paint;

    .line 35
    new-instance v0, Landroid/graphics/Matrix;

    invoke-direct {v0}, Landroid/graphics/Matrix;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->matrix:Landroid/graphics/Matrix;

    .line 36
    new-instance v0, Landroid/graphics/Path;

    invoke-direct {v0}, Landroid/graphics/Path;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->path:Landroid/graphics/Path;

    .line 45
    iput-object p1, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->icon:Lcom/exteragram/messenger/appicons/AppIcon;

    .line 46
    iput p2, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->size:I

    int-to-float v1, p2

    .line 48
    sget v2, Lorg/telegram/messenger/AndroidUtilities;->density:F

    div-float/2addr v1, v2

    .line 49
    sget-object v2, Lcom/exteragram/messenger/preferences/utils/IconShapeHelper;->INSTANCE:Lcom/exteragram/messenger/preferences/utils/IconShapeHelper;

    const v3, 0x3e8f5c29    # 0.28f

    mul-float/2addr v3, v1

    invoke-virtual {v2, v1, v1, v3}, Lcom/exteragram/messenger/preferences/utils/IconShapeHelper;->getFinalIconShapePath(FFF)Landroid/graphics/Path;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/graphics/Path;->set(Landroid/graphics/Path;)V

    .line 51
    invoke-static {p1, p2}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->getCached(Lcom/exteragram/messenger/appicons/AppIcon;I)Landroid/graphics/Bitmap;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 53
    iput-object v0, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->bitmap:Landroid/graphics/Bitmap;

    return-void

    .line 55
    :cond_0
    invoke-static {p1, p2, p0}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->load(Lcom/exteragram/messenger/appicons/AppIcon;ILcom/exteragram/messenger/appicons/AppIconPreviewLoader$Callback;)V

    return-void
.end method


# virtual methods
.method public draw(Landroid/graphics/Canvas;)V
    .locals 4

    .line 71
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->bitmap:Landroid/graphics/Bitmap;

    if-nez v0, :cond_0

    return-void

    .line 74
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->shader:Landroid/graphics/BitmapShader;

    if-nez v0, :cond_1

    .line 75
    new-instance v0, Landroid/graphics/BitmapShader;

    iget-object v1, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->bitmap:Landroid/graphics/Bitmap;

    sget-object v2, Landroid/graphics/Shader$TileMode;->CLAMP:Landroid/graphics/Shader$TileMode;

    invoke-direct {v0, v1, v2, v2}, Landroid/graphics/BitmapShader;-><init>(Landroid/graphics/Bitmap;Landroid/graphics/Shader$TileMode;Landroid/graphics/Shader$TileMode;)V

    iput-object v0, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->shader:Landroid/graphics/BitmapShader;

    .line 76
    iget-object v1, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->paint:Landroid/graphics/Paint;

    invoke-virtual {v1, v0}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    .line 79
    :cond_1
    invoke-virtual {p0}, Landroid/graphics/drawable/Drawable;->getBounds()Landroid/graphics/Rect;

    move-result-object v0

    .line 80
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    .line 81
    iget v1, v0, Landroid/graphics/Rect;->left:I

    int-to-float v1, v1

    iget v0, v0, Landroid/graphics/Rect;->top:I

    int-to-float v0, v0

    invoke-virtual {p1, v1, v0}, Landroid/graphics/Canvas;->translate(FF)V

    .line 82
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->matrix:Landroid/graphics/Matrix;

    iget v1, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->size:I

    int-to-float v1, v1

    iget-object v2, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->bitmap:Landroid/graphics/Bitmap;

    invoke-virtual {v2}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v2

    int-to-float v2, v2

    div-float/2addr v1, v2

    iget v2, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->size:I

    int-to-float v2, v2

    iget-object v3, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->bitmap:Landroid/graphics/Bitmap;

    invoke-virtual {v3}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v3

    int-to-float v3, v3

    div-float/2addr v2, v3

    invoke-virtual {v0, v1, v2}, Landroid/graphics/Matrix;->setScale(FF)V

    .line 83
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->shader:Landroid/graphics/BitmapShader;

    iget-object v1, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->matrix:Landroid/graphics/Matrix;

    invoke-virtual {v0, v1}, Landroid/graphics/Shader;->setLocalMatrix(Landroid/graphics/Matrix;)V

    .line 84
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->path:Landroid/graphics/Path;

    iget-object p0, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->paint:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, p0}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    .line 85
    invoke-virtual {p1}, Landroid/graphics/Canvas;->restore()V

    return-void
.end method

.method public getIntrinsicHeight()I
    .locals 0

    .line 95
    iget p0, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->size:I

    return p0
.end method

.method public getIntrinsicWidth()I
    .locals 0

    .line 90
    iget p0, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->size:I

    return p0
.end method

.method public getOpacity()I
    .locals 0

    const/4 p0, -0x3

    return p0
.end method

.method public onPreviewReady(Lcom/exteragram/messenger/appicons/AppIcon;ILandroid/graphics/Bitmap;)V
    .locals 1

    .line 61
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->icon:Lcom/exteragram/messenger/appicons/AppIcon;

    if-ne v0, p1, :cond_1

    iget p1, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->size:I

    if-eq p1, p2, :cond_0

    goto :goto_0

    .line 64
    :cond_0
    iput-object p3, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->bitmap:Landroid/graphics/Bitmap;

    const/4 p1, 0x0

    .line 65
    iput-object p1, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->shader:Landroid/graphics/BitmapShader;

    .line 66
    invoke-virtual {p0}, Landroid/graphics/drawable/Drawable;->invalidateSelf()V

    :cond_1
    :goto_0
    return-void
.end method

.method public setAlpha(I)V
    .locals 1

    .line 100
    iget-object v0, p0, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;->paint:Landroid/graphics/Paint;

    invoke-virtual {v0, p1}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 101
    invoke-virtual {p0}, Landroid/graphics/drawable/Drawable;->invalidateSelf()V

    return-void
.end method

.method public setColorFilter(Landroid/graphics/ColorFilter;)V
    .locals 0

    return-void
.end method
