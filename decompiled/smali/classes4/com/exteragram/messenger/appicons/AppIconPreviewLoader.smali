.class public abstract Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$Callback;
    }
.end annotation


# static fields
.field private static final accents:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private static cache:Landroid/util/LruCache;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/LruCache<",
            "Ljava/lang/String;",
            "Landroid/graphics/Bitmap;",
            ">;"
        }
    .end annotation
.end field

.field private static final knownSizes:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private static final paint:Landroid/graphics/Paint;

.field private static final pending:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$Callback;",
            ">;>;"
        }
    .end annotation
.end field


# direct methods
.method public static synthetic $r8$lambda$5pAaOXpJhOh8Ffl7ErwtmWPqaac(Landroid/graphics/Bitmap;Ljava/lang/String;ILcom/exteragram/messenger/appicons/AppIcon;I)V
    .locals 2

    if-eqz p0, :cond_0

    .line 224
    invoke-static {}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->getCache()Landroid/util/LruCache;

    move-result-object v0

    invoke-virtual {v0, p1, p0}, Landroid/util/LruCache;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_0
    if-eqz p2, :cond_1

    .line 226
    sget-object v0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->accents:Ljava/util/Map;

    iget-object v1, p3, Lcom/exteragram/messenger/appicons/AppIcon;->id:Ljava/lang/String;

    invoke-interface {v0, v1}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1

    .line 227
    iget-object v1, p3, Lcom/exteragram/messenger/appicons/AppIcon;->id:Ljava/lang/String;

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-interface {v0, v1, p2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 229
    :cond_1
    sget-object p2, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->pending:Ljava/util/Map;

    invoke-interface {p2, p1}, Ljava/util/Map;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/util/List;

    if-eqz p1, :cond_3

    if-nez p0, :cond_2

    goto :goto_1

    .line 233
    :cond_2
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result p2

    if-eqz p2, :cond_3

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$Callback;

    .line 234
    invoke-interface {p2, p3, p4, p0}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$Callback;->onPreviewReady(Lcom/exteragram/messenger/appicons/AppIcon;ILandroid/graphics/Bitmap;)V

    goto :goto_0

    :cond_3
    :goto_1
    return-void
.end method

.method public static synthetic $r8$lambda$xCNjtrhQGHVyqdjOo4Qfmhh5oYI(Lcom/exteragram/messenger/appicons/AppIcon;IZLjava/lang/String;)V
    .locals 6

    .line 219
    invoke-static {p0, p1}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->render(Lcom/exteragram/messenger/appicons/AppIcon;I)Landroid/graphics/Bitmap;

    move-result-object v1

    const/4 v0, 0x0

    if-eqz v1, :cond_2

    if-nez p2, :cond_0

    goto :goto_0

    .line 221
    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/appicons/AppIcon;->isBackgroundColor()Z

    move-result p2

    if-eqz p2, :cond_1

    invoke-static {p0}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->getBackgroundColor(Lcom/exteragram/messenger/appicons/AppIcon;)I

    move-result v0

    :cond_1
    invoke-static {v1, v0}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->extractAccent(Landroid/graphics/Bitmap;I)I

    move-result v0

    :cond_2
    :goto_0
    move v3, v0

    .line 222
    new-instance v0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda1;

    move-object v4, p0

    move v5, p1

    move-object v2, p3

    invoke-direct/range {v0 .. v5}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda1;-><init>(Landroid/graphics/Bitmap;Ljava/lang/String;ILcom/exteragram/messenger/appicons/AppIcon;I)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 2

    .line 61
    new-instance v0, Landroid/graphics/Paint;

    const/4 v1, 0x3

    invoke-direct {v0, v1}, Landroid/graphics/Paint;-><init>(I)V

    sput-object v0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->paint:Landroid/graphics/Paint;

    .line 62
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->pending:Ljava/util/Map;

    .line 63
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->knownSizes:Ljava/util/List;

    .line 64
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->accents:Ljava/util/Map;

    return-void
.end method

.method private static clampTone([FFF)I
    .locals 9

    const/4 v0, 0x3

    .line 174
    new-array v0, v0, [D

    .line 175
    invoke-static {p0}, Landroidx/core/graphics/ColorUtils;->HSLToColor([F)I

    move-result v1

    invoke-static {v1, v0}, Landroidx/core/graphics/ColorUtils;->colorToLAB(I[D)V

    const/4 v1, 0x0

    .line 176
    aget-wide v2, v0, v1

    float-to-double v4, p1

    cmpl-double v6, v2, v4

    if-ltz v6, :cond_0

    float-to-double v6, p2

    cmpg-double v6, v2, v6

    if-gtz v6, :cond_0

    .line 177
    invoke-static {p0}, Landroidx/core/graphics/ColorUtils;->HSLToColor([F)I

    move-result p0

    return p0

    :cond_0
    cmpg-double v2, v2, v4

    if-gez v2, :cond_1

    goto :goto_0

    :cond_1
    move p1, p2

    :goto_0
    const/4 p2, 0x0

    const/high16 v2, 0x3f800000    # 1.0f

    move v3, v1

    :goto_1
    const/16 v4, 0x10

    const/high16 v5, 0x40000000    # 2.0f

    const/4 v6, 0x2

    if-ge v3, v4, :cond_3

    add-float v4, p2, v2

    div-float/2addr v4, v5

    .line 182
    aput v4, p0, v6

    .line 183
    invoke-static {p0}, Landroidx/core/graphics/ColorUtils;->HSLToColor([F)I

    move-result v4

    invoke-static {v4, v0}, Landroidx/core/graphics/ColorUtils;->colorToLAB(I[D)V

    .line 184
    aget-wide v4, v0, v1

    float-to-double v7, p1

    cmpg-double v4, v4, v7

    if-gez v4, :cond_2

    .line 185
    aget p2, p0, v6

    goto :goto_2

    .line 187
    :cond_2
    aget v2, p0, v6

    :goto_2
    add-int/lit8 v3, v3, 0x1

    goto :goto_1

    :cond_3
    add-float/2addr p2, v2

    div-float/2addr p2, v5

    .line 190
    aput p2, p0, v6

    .line 191
    invoke-static {p0}, Landroidx/core/graphics/ColorUtils;->HSLToColor([F)I

    move-result p0

    return p0
.end method

.method private static draw(Landroid/graphics/Canvas;ILandroid/graphics/Rect;)V
    .locals 9

    if-nez p1, :cond_0

    goto :goto_1

    .line 322
    :cond_0
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    .line 323
    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    .line 325
    new-instance v2, Landroid/graphics/BitmapFactory$Options;

    invoke-direct {v2}, Landroid/graphics/BitmapFactory$Options;-><init>()V

    const/4 v3, 0x1

    .line 326
    iput-boolean v3, v2, Landroid/graphics/BitmapFactory$Options;->inJustDecodeBounds:Z

    const/4 v4, 0x0

    .line 327
    iput-boolean v4, v2, Landroid/graphics/BitmapFactory$Options;->inScaled:Z

    .line 328
    invoke-static {v1, p1, v2}, Landroid/graphics/BitmapFactory;->decodeResource(Landroid/content/res/Resources;ILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;

    .line 330
    iget v5, v2, Landroid/graphics/BitmapFactory$Options;->outWidth:I

    const/4 v6, 0x0

    if-lez v5, :cond_2

    iget v5, v2, Landroid/graphics/BitmapFactory$Options;->outHeight:I

    if-lez v5, :cond_2

    .line 332
    :goto_0
    iget v5, v2, Landroid/graphics/BitmapFactory$Options;->outWidth:I

    mul-int/lit8 v7, v3, 0x2

    div-int/2addr v5, v7

    invoke-virtual {p2}, Landroid/graphics/Rect;->width()I

    move-result v8

    if-lt v5, v8, :cond_1

    move v3, v7

    goto :goto_0

    .line 335
    :cond_1
    iput-boolean v4, v2, Landroid/graphics/BitmapFactory$Options;->inJustDecodeBounds:Z

    .line 336
    iput v3, v2, Landroid/graphics/BitmapFactory$Options;->inSampleSize:I

    .line 337
    sget-object v3, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    iput-object v3, v2, Landroid/graphics/BitmapFactory$Options;->inPreferredConfig:Landroid/graphics/Bitmap$Config;

    .line 338
    invoke-static {v1, p1, v2}, Landroid/graphics/BitmapFactory;->decodeResource(Landroid/content/res/Resources;ILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;

    move-result-object v1

    if-eqz v1, :cond_2

    .line 340
    sget-object p1, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->paint:Landroid/graphics/Paint;

    invoke-virtual {p0, v1, v6, p2, p1}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Paint;)V

    return-void

    .line 345
    :cond_2
    invoke-static {v0, p1}, Landroidx/core/content/ContextCompat;->getDrawable(Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;

    move-result-object p1

    .line 346
    instance-of v0, p1, Landroid/graphics/drawable/BitmapDrawable;

    if-eqz v0, :cond_3

    move-object v0, p1

    check-cast v0, Landroid/graphics/drawable/BitmapDrawable;

    invoke-virtual {v0}, Landroid/graphics/drawable/BitmapDrawable;->getBitmap()Landroid/graphics/Bitmap;

    move-result-object v1

    if-eqz v1, :cond_3

    .line 347
    invoke-virtual {v0}, Landroid/graphics/drawable/BitmapDrawable;->getBitmap()Landroid/graphics/Bitmap;

    move-result-object p1

    sget-object v0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->paint:Landroid/graphics/Paint;

    invoke-virtual {p0, p1, v6, p2, v0}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Paint;)V

    return-void

    :cond_3
    if-eqz p1, :cond_4

    .line 349
    invoke-virtual {p1, p2}, Landroid/graphics/drawable/Drawable;->setBounds(Landroid/graphics/Rect;)V

    .line 350
    invoke-virtual {p1, p0}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V

    :cond_4
    :goto_1
    return-void
.end method

.method private static extractAccent(Landroid/graphics/Bitmap;I)I
    .locals 23

    .line 265
    invoke-virtual/range {p0 .. p0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v0

    const/4 v1, 0x1

    sub-int/2addr v0, v1

    int-to-float v0, v0

    const/high16 v2, 0x41b80000    # 23.0f

    div-float/2addr v0, v2

    .line 266
    invoke-virtual/range {p0 .. p0}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v3

    sub-int/2addr v3, v1

    int-to-float v3, v3

    div-float/2addr v3, v2

    const/4 v2, 0x3

    .line 267
    new-array v2, v2, [F

    const/16 v4, 0xc

    .line 268
    new-array v5, v4, [F

    .line 269
    new-array v6, v4, [F

    .line 270
    new-array v7, v4, [F

    .line 271
    new-array v8, v4, [F

    const/4 v11, 0x0

    const/4 v12, 0x0

    const/4 v13, 0x0

    const/4 v14, 0x0

    :goto_0
    const/16 v15, 0x18

    const/16 v16, 0x2

    if-ge v11, v15, :cond_4

    move/from16 v17, v1

    const/4 v1, 0x0

    :goto_1
    if-ge v1, v15, :cond_3

    const/16 v18, 0x0

    int-to-float v9, v11

    mul-float/2addr v9, v0

    float-to-int v9, v9

    const/16 v19, 0x0

    int-to-float v10, v1

    mul-float/2addr v10, v3

    float-to-int v10, v10

    move-object/from16 v15, p0

    .line 276
    invoke-virtual {v15, v9, v10}, Landroid/graphics/Bitmap;->getPixel(II)I

    move-result v9

    .line 277
    invoke-static {v9}, Landroid/graphics/Color;->alpha(I)I

    move-result v10

    const/16 v4, 0xc8

    if-lt v10, v4, :cond_2

    move/from16 v4, p1

    if-ne v9, v4, :cond_0

    goto :goto_2

    .line 280
    :cond_0
    invoke-static {v9, v2}, Landroidx/core/graphics/ColorUtils;->colorToHSL(I[F)V

    .line 281
    invoke-static {v2}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->isNeutral([F)Z

    move-result v9

    const/high16 v10, 0x3f800000    # 1.0f

    if-eqz v9, :cond_1

    add-float/2addr v12, v10

    .line 283
    aget v9, v2, v16

    add-float/2addr v14, v9

    goto :goto_2

    .line 286
    :cond_1
    aget v9, v2, v19

    const/high16 v20, 0x43b40000    # 360.0f

    div-float v9, v9, v20

    const/high16 v20, 0x41400000    # 12.0f

    mul-float v9, v9, v20

    float-to-int v9, v9

    move/from16 v20, v10

    const/16 v10, 0xb

    invoke-static {v10, v9}, Ljava/lang/Math;->min(II)I

    move-result v9

    .line 287
    aget v10, v2, v17

    const/high16 v21, 0x40000000    # 2.0f

    aget v22, v2, v16

    mul-float v22, v22, v21

    sub-float v22, v22, v20

    invoke-static/range {v22 .. v22}, Ljava/lang/Math;->abs(F)F

    move-result v21

    sub-float v21, v20, v21

    mul-float v10, v10, v21

    .line 288
    aget v21, v5, v9

    add-float v21, v21, v10

    aput v21, v5, v9

    .line 289
    aget v21, v6, v9

    aget v22, v2, v19

    mul-float v22, v22, v10

    add-float v21, v21, v22

    aput v21, v6, v9

    .line 290
    aget v21, v7, v9

    aget v22, v2, v17

    mul-float v22, v22, v10

    add-float v21, v21, v22

    aput v21, v7, v9

    .line 291
    aget v21, v8, v9

    aget v22, v2, v16

    mul-float v22, v22, v10

    add-float v21, v21, v22

    aput v21, v8, v9

    add-float v13, v13, v20

    goto :goto_2

    :cond_2
    move/from16 v4, p1

    :goto_2
    add-int/lit8 v1, v1, 0x1

    const/16 v4, 0xc

    const/16 v15, 0x18

    goto/16 :goto_1

    :cond_3
    move-object/from16 v15, p0

    move/from16 v4, p1

    const/16 v18, 0x0

    const/16 v19, 0x0

    add-int/lit8 v11, v11, 0x1

    move/from16 v1, v17

    const/16 v4, 0xc

    goto/16 :goto_0

    :cond_4
    move/from16 v17, v1

    const/16 v18, 0x0

    const/16 v19, 0x0

    const/4 v0, -0x1

    move v3, v0

    move/from16 v1, v19

    const/16 v4, 0xc

    :goto_3
    if-ge v1, v4, :cond_7

    if-eq v3, v0, :cond_5

    .line 298
    aget v9, v5, v1

    aget v10, v5, v3

    cmpl-float v9, v9, v10

    if-lez v9, :cond_6

    :cond_5
    move v3, v1

    :cond_6
    add-int/lit8 v1, v1, 0x1

    goto :goto_3

    :cond_7
    if-eq v3, v0, :cond_9

    .line 302
    aget v0, v5, v3

    cmpg-float v1, v0, v18

    if-lez v1, :cond_9

    const v1, 0x3e19999a    # 0.15f

    mul-float/2addr v1, v12

    cmpg-float v1, v13, v1

    if-gez v1, :cond_8

    goto :goto_4

    .line 312
    :cond_8
    aget v1, v6, v3

    div-float/2addr v1, v0

    aput v1, v2, v19

    .line 313
    aget v0, v7, v3

    aget v1, v5, v3

    div-float/2addr v0, v1

    aput v0, v2, v17

    .line 314
    aget v0, v8, v3

    aget v1, v5, v3

    div-float/2addr v0, v1

    aput v0, v2, v16

    .line 315
    invoke-static {v2}, Landroidx/core/graphics/ColorUtils;->HSLToColor([F)I

    move-result v0

    return v0

    :cond_9
    :goto_4
    cmpg-float v0, v12, v18

    if-gtz v0, :cond_a

    return v19

    .line 306
    :cond_a
    aput v18, v2, v19

    .line 307
    aput v18, v2, v17

    div-float/2addr v14, v12

    .line 308
    aput v14, v2, v16

    .line 309
    invoke-static {v2}, Landroidx/core/graphics/ColorUtils;->HSLToColor([F)I

    move-result v0

    return v0
.end method

.method public static getAccent(Lcom/exteragram/messenger/appicons/AppIcon;)I
    .locals 5

    .line 111
    iget v0, p0, Lcom/exteragram/messenger/appicons/AppIcon;->color:I

    if-eqz v0, :cond_0

    return v0

    .line 114
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->accents:Ljava/util/Map;

    iget-object v1, p0, Lcom/exteragram/messenger/appicons/AppIcon;->id:Ljava/lang/String;

    invoke-interface {v0, v1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Integer;

    if-eqz v1, :cond_1

    .line 116
    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result p0

    return p0

    .line 118
    :cond_1
    invoke-virtual {p0}, Lcom/exteragram/messenger/appicons/AppIcon;->isBackgroundColor()Z

    move-result v1

    const/4 v2, 0x0

    if-eqz v1, :cond_3

    .line 119
    invoke-static {p0}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->getBackgroundColor(Lcom/exteragram/messenger/appicons/AppIcon;)I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    const/4 v4, 0x3

    .line 120
    new-array v4, v4, [F

    .line 121
    invoke-static {v1, v4}, Landroidx/core/graphics/ColorUtils;->colorToHSL(I[F)V

    .line 122
    invoke-static {v4}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->isNeutral([F)Z

    move-result v4

    if-eqz v4, :cond_2

    return v2

    .line 125
    :cond_2
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/AppIcon;->id:Ljava/lang/String;

    invoke-interface {v0, p0, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return v1

    :cond_3
    return v2
.end method

.method public static getAccentTextColor(Lcom/exteragram/messenger/appicons/AppIcon;ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I
    .locals 2

    if-nez p0, :cond_0

    const/4 p0, 0x0

    goto :goto_0

    .line 141
    :cond_0
    invoke-static {p0}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->getAccent(Lcom/exteragram/messenger/appicons/AppIcon;)I

    move-result p0

    :goto_0
    if-eqz p0, :cond_2

    const/4 v0, 0x3

    .line 143
    new-array v0, v0, [F

    .line 144
    invoke-static {p0, v0}, Landroidx/core/graphics/ColorUtils;->colorToHSL(I[F)V

    const/4 p0, 0x1

    .line 145
    aget p0, v0, p0

    const v1, 0x3e19999a    # 0.15f

    cmpl-float p0, p0, v1

    if-ltz p0, :cond_2

    .line 146
    invoke-static {}, Lorg/telegram/ui/ActionBar/Theme;->isCurrentThemeDark()Z

    move-result p0

    const/4 p1, 0x2

    if-eqz p0, :cond_1

    .line 147
    aget p0, v0, p1

    const p2, 0x3f3851ec    # 0.72f

    invoke-static {p0, p2}, Ljava/lang/Math;->max(FF)F

    move-result p0

    aput p0, v0, p1

    const/high16 p0, 0x428c0000    # 70.0f

    const/high16 p1, 0x42c80000    # 100.0f

    .line 148
    invoke-static {v0, p0, p1}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->clampTone([FFF)I

    move-result p0

    return p0

    .line 150
    :cond_1
    aget p0, v0, p1

    const p2, 0x3ed70a3d    # 0.42f

    invoke-static {p0, p2}, Ljava/lang/Math;->min(FF)F

    move-result p0

    aput p0, v0, p1

    const/4 p0, 0x0

    const/high16 p1, 0x42280000    # 42.0f

    .line 151
    invoke-static {v0, p0, p1}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->clampTone([FFF)I

    move-result p0

    return p0

    .line 154
    :cond_2
    invoke-static {p1, p2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    return p0
.end method

.method public static getAccentTint(Lcom/exteragram/messenger/appicons/AppIcon;)I
    .locals 3

    const/4 v0, 0x0

    if-nez p0, :cond_0

    move p0, v0

    goto :goto_0

    .line 158
    :cond_0
    invoke-static {p0}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->getAccent(Lcom/exteragram/messenger/appicons/AppIcon;)I

    move-result p0

    :goto_0
    if-nez p0, :cond_1

    return v0

    :cond_1
    const/4 v0, 0x3

    .line 162
    new-array v0, v0, [F

    .line 163
    invoke-static {p0, v0}, Landroidx/core/graphics/ColorUtils;->colorToHSL(I[F)V

    const/4 p0, 0x1

    .line 164
    aget v1, v0, p0

    const v2, 0x3f19999a    # 0.6f

    invoke-static {v1, v2}, Ljava/lang/Math;->min(FF)F

    move-result v1

    aput v1, v0, p0

    .line 165
    invoke-static {}, Lorg/telegram/ui/ActionBar/Theme;->isCurrentThemeDark()Z

    move-result p0

    const/4 v1, 0x2

    if-eqz p0, :cond_2

    const p0, 0x3e6147ae    # 0.22f

    .line 166
    aput p0, v0, v1

    const/high16 p0, 0x41a00000    # 20.0f

    const/high16 v1, 0x42c80000    # 100.0f

    .line 167
    invoke-static {v0, p0, v1}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->clampTone([FFF)I

    move-result p0

    return p0

    :cond_2
    const p0, 0x3f5c28f6    # 0.86f

    .line 169
    aput p0, v0, v1

    const/4 p0, 0x0

    const/high16 v1, 0x42b00000    # 88.0f

    .line 170
    invoke-static {v0, p0, v1}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->clampTone([FFF)I

    move-result p0

    return p0
.end method

.method public static getAnyCached(Lcom/exteragram/messenger/appicons/AppIcon;)Landroid/graphics/Bitmap;
    .locals 5

    const/4 v0, 0x0

    const/4 v1, 0x0

    .line 93
    :goto_0
    sget-object v2, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->knownSizes:Ljava/util/List;

    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v3

    if-ge v1, v3, :cond_2

    .line 94
    invoke-static {}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->getCache()Landroid/util/LruCache;

    move-result-object v3

    invoke-interface {v2, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Integer;

    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v2

    invoke-static {p0, v2}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->key(Lcom/exteragram/messenger/appicons/AppIcon;I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v3, v2}, Landroid/util/LruCache;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/graphics/Bitmap;

    if-eqz v2, :cond_1

    if-eqz v0, :cond_0

    .line 95
    invoke-virtual {v2}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v3

    invoke-virtual {v0}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v4

    if-le v3, v4, :cond_1

    :cond_0
    move-object v0, v2

    :cond_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_2
    return-object v0
.end method

.method private static getBackgroundColor(Lcom/exteragram/messenger/appicons/AppIcon;)I
    .locals 1

    .line 133
    :try_start_0
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {p0}, Lcom/exteragram/messenger/appicons/AppIcon;->getBackground()I

    move-result p0

    invoke-static {v0, p0}, Landroidx/core/content/ContextCompat;->getColor(Landroid/content/Context;I)I

    move-result p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return p0

    :catchall_0
    move-exception p0

    .line 135
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    const/4 p0, 0x0

    return p0
.end method

.method private static getCache()Landroid/util/LruCache;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Landroid/util/LruCache<",
            "Ljava/lang/String;",
            "Landroid/graphics/Bitmap;",
            ">;"
        }
    .end annotation

    .line 71
    sget-object v0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->cache:Landroid/util/LruCache;

    if-nez v0, :cond_0

    .line 72
    invoke-static {}, Ljava/lang/Runtime;->getRuntime()Ljava/lang/Runtime;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Runtime;->maxMemory()J

    move-result-wide v0

    const-wide/16 v2, 0x10

    div-long/2addr v0, v2

    const-wide/32 v2, 0xc00000

    invoke-static {v2, v3, v0, v1}, Ljava/lang/Math;->min(JJ)J

    move-result-wide v0

    long-to-int v0, v0

    .line 73
    new-instance v1, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$1;

    invoke-direct {v1, v0}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$1;-><init>(I)V

    sput-object v1, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->cache:Landroid/util/LruCache;

    .line 80
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->cache:Landroid/util/LruCache;

    return-object v0
.end method

.method public static getCached(Lcom/exteragram/messenger/appicons/AppIcon;I)Landroid/graphics/Bitmap;
    .locals 1

    .line 88
    invoke-static {}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->getCache()Landroid/util/LruCache;

    move-result-object v0

    invoke-static {p0, p1}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->key(Lcom/exteragram/messenger/appicons/AppIcon;I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Landroid/util/LruCache;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroid/graphics/Bitmap;

    return-object p0
.end method

.method private static isNeutral([F)Z
    .locals 3

    const/4 v0, 0x1

    .line 261
    aget v1, p0, v0

    const v2, 0x3e19999a    # 0.15f

    cmpg-float v1, v1, v2

    if-ltz v1, :cond_1

    const/4 v1, 0x2

    aget p0, p0, v1

    const v1, 0x3d75c28f    # 0.06f

    cmpg-float v1, p0, v1

    if-ltz v1, :cond_1

    const v1, 0x3f70a3d7    # 0.94f

    cmpl-float p0, p0, v1

    if-lez p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    return v0
.end method

.method private static key(Lcom/exteragram/messenger/appicons/AppIcon;I)Ljava/lang/String;
    .locals 1

    .line 84
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    iget-object p0, p0, Lcom/exteragram/messenger/appicons/AppIcon;->id:Ljava/lang/String;

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "@"

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static load(Lcom/exteragram/messenger/appicons/AppIcon;ILcom/exteragram/messenger/appicons/AppIconPreviewLoader$Callback;)V
    .locals 3

    if-gtz p1, :cond_0

    return-void

    .line 198
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->knownSizes:Ljava/util/List;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1

    .line 199
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 201
    :cond_1
    invoke-static {p0, p1}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->key(Lcom/exteragram/messenger/appicons/AppIcon;I)Ljava/lang/String;

    move-result-object v0

    .line 202
    invoke-static {}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->getCache()Landroid/util/LruCache;

    move-result-object v1

    invoke-virtual {v1, v0}, Landroid/util/LruCache;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/graphics/Bitmap;

    if-eqz v1, :cond_2

    .line 204
    invoke-interface {p2, p0, p1, v1}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$Callback;->onPreviewReady(Lcom/exteragram/messenger/appicons/AppIcon;ILandroid/graphics/Bitmap;)V

    return-void

    .line 208
    :cond_2
    sget-object v1, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->pending:Ljava/util/Map;

    invoke-interface {v1, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/List;

    if-eqz v2, :cond_3

    .line 210
    invoke-interface {v2, p2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-void

    .line 213
    :cond_3
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    .line 214
    invoke-interface {v2, p2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 215
    invoke-interface {v1, v0, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 217
    sget-object p2, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->accents:Ljava/util/Map;

    iget-object v1, p0, Lcom/exteragram/messenger/appicons/AppIcon;->id:Ljava/lang/String;

    invoke-interface {p2, v1}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_4

    invoke-static {p0}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->getAccent(Lcom/exteragram/messenger/appicons/AppIcon;)I

    move-result p2

    if-nez p2, :cond_4

    const/4 p2, 0x1

    goto :goto_0

    :cond_4
    const/4 p2, 0x0

    .line 218
    :goto_0
    sget-object v1, Lorg/telegram/messenger/Utilities;->globalQueue:Lorg/telegram/messenger/DispatchQueue;

    new-instance v2, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda0;

    invoke-direct {v2, p0, p1, p2, v0}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/appicons/AppIcon;IZLjava/lang/String;)V

    invoke-virtual {v1, v2}, Lorg/telegram/messenger/DispatchQueue;->postRunnable(Ljava/lang/Runnable;)Z

    return-void
.end method

.method private static render(Lcom/exteragram/messenger/appicons/AppIcon;I)Landroid/graphics/Bitmap;
    .locals 5

    .line 242
    :try_start_0
    sget-object v0, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {p1, p1, v0}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 243
    new-instance v1, Landroid/graphics/Canvas;

    invoke-direct {v1, v0}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    .line 244
    div-int/lit8 v2, p1, 0x4

    .line 245
    new-instance v3, Landroid/graphics/Rect;

    neg-int v4, v2

    add-int/2addr p1, v2

    invoke-direct {v3, v4, v4, p1, p1}, Landroid/graphics/Rect;-><init>(IIII)V

    .line 247
    invoke-virtual {p0}, Lcom/exteragram/messenger/appicons/AppIcon;->isBackgroundColor()Z

    move-result p1

    if-eqz p1, :cond_0

    .line 248
    sget-object p1, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {p0}, Lcom/exteragram/messenger/appicons/AppIcon;->getBackground()I

    move-result v2

    invoke-static {p1, v2}, Landroidx/core/content/ContextCompat;->getColor(Landroid/content/Context;I)I

    move-result p1

    invoke-virtual {v1, p1}, Landroid/graphics/Canvas;->drawColor(I)V

    goto :goto_0

    .line 250
    :cond_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/appicons/AppIcon;->getBackground()I

    move-result p1

    invoke-static {v1, p1, v3}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->draw(Landroid/graphics/Canvas;ILandroid/graphics/Rect;)V

    .line 252
    :goto_0
    invoke-virtual {p0}, Lcom/exteragram/messenger/appicons/AppIcon;->getForeground()I

    move-result p0

    invoke-static {v1, p0, v3}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->draw(Landroid/graphics/Canvas;ILandroid/graphics/Rect;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-object v0

    :catchall_0
    move-exception p0

    .line 255
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public static trimAbove(I)V
    .locals 3

    .line 103
    invoke-static {}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->getCache()Landroid/util/LruCache;

    move-result-object v0

    invoke-virtual {v0}, Landroid/util/LruCache;->snapshot()Ljava/util/Map;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    .line 104
    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/graphics/Bitmap;

    invoke-virtual {v2}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v2

    if-lt v2, p0, :cond_0

    .line 105
    invoke-static {}, Lcom/exteragram/messenger/appicons/AppIconPreviewLoader;->getCache()Landroid/util/LruCache;

    move-result-object v2

    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    invoke-virtual {v2, v1}, Landroid/util/LruCache;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_0

    :cond_1
    return-void
.end method
