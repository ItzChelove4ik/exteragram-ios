.class public abstract Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;
.super Landroid/graphics/drawable/Drawable;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;
    }
.end annotation


# static fields
.field private static tmpPath:Landroid/graphics/Path;

.field private static final tmpRadii:[F


# instance fields
.field protected alpha:I

.field private final backgroundBitmapFill:Landroid/graphics/Paint;

.field private final backgroundBitmapPaint:Landroid/graphics/Paint;

.field protected backgroundColor:I

.field private final backgroundColorPaint:Landroid/graphics/Paint;

.field private final bitmapInShader:Ljava/lang/ref/WeakReference;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/WeakReference<",
            "Landroid/graphics/Bitmap;",
            ">;"
        }
    .end annotation
.end field

.field private bitmapShader:Landroid/graphics/BitmapShader;

.field private final bitmapShaderMatrix:Landroid/graphics/Matrix;

.field protected final boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

.field private final cmpRectF1:Landroid/graphics/RectF;

.field private final cmpRectF2:Landroid/graphics/RectF;

.field protected colorProvider:Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;

.field protected inAppKeyboardOptimization:Z

.field private ninePatchDrawable:Landroid/graphics/drawable/NinePatchDrawable;

.field private ninePatchDrawableHash:J

.field private final ninePatchDrawablePadding:Landroid/graphics/Rect;

.field private final ninePatchHashBuilder:Lorg/telegram/ui/Components/blur3/Blur3HashImpl;

.field private ninePatchRef:[Landroid/graphics/Bitmap;

.field private outsetX:I

.field private outsetY:I

.field private final paintStrokeFill:Landroid/graphics/Paint;

.field protected shadowAlpha:F

.field protected shadowColor:I

.field protected shadowLayerDx:F

.field protected shadowLayerDy:F

.field protected shadowLayerRadius:F

.field private final shadowPaint:Landroid/graphics/Paint;

.field protected sourceOffsetX:F

.field protected sourceOffsetY:F

.field protected strokeColorBottom:I

.field protected strokeColorFull:I

.field protected strokeColorTop:I

.field private viewOutlineProvider:Landroid/view/ViewOutlineProvider;


# direct methods
.method public static synthetic $r8$lambda$OMxc8cd5reC5PQ5QLW0SW0wdMOs(Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;IZLandroid/graphics/Canvas;Landroid/graphics/RectF;[F)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->lambda$checkNinePatchDrawable$0(IZLandroid/graphics/Canvas;Landroid/graphics/RectF;[F)V

    return-void
.end method

.method public static bridge synthetic -$$Nest$sfgettmpRadii()[F
    .locals 1

    .line 0
    sget-object v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->tmpRadii:[F

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 1

    const/16 v0, 0x8

    .line 265
    new-array v0, v0, [F

    sput-object v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->tmpRadii:[F

    .line 423
    new-instance v0, Landroid/graphics/Path;

    invoke-direct {v0}, Landroid/graphics/Path;-><init>()V

    sput-object v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->tmpPath:Landroid/graphics/Path;

    return-void
.end method

.method public constructor <init>()V
    .locals 7

    .line 53
    invoke-direct {p0}, Landroid/graphics/drawable/Drawable;-><init>()V

    .line 266
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    invoke-direct {v0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;-><init>()V

    iput-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    const/16 v1, 0xff

    .line 444
    iput v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->alpha:I

    const/high16 v1, 0x3f800000    # 1.0f

    .line 612
    iput v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowAlpha:F

    .line 659
    new-instance v2, Landroid/graphics/Paint;

    const/4 v3, 0x1

    invoke-direct {v2, v3}, Landroid/graphics/Paint;-><init>(I)V

    iput-object v2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->backgroundColorPaint:Landroid/graphics/Paint;

    .line 660
    new-instance v2, Landroid/graphics/Paint;

    invoke-direct {v2, v3}, Landroid/graphics/Paint;-><init>(I)V

    iput-object v2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->paintStrokeFill:Landroid/graphics/Paint;

    .line 662
    new-instance v2, Landroid/graphics/Paint;

    invoke-direct {v2, v3}, Landroid/graphics/Paint;-><init>(I)V

    iput-object v2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->backgroundBitmapPaint:Landroid/graphics/Paint;

    .line 663
    new-instance v4, Landroid/graphics/Paint;

    invoke-direct {v4, v3}, Landroid/graphics/Paint;-><init>(I)V

    iput-object v4, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->backgroundBitmapFill:Landroid/graphics/Paint;

    .line 664
    new-instance v4, Landroid/graphics/Paint;

    invoke-direct {v4, v3}, Landroid/graphics/Paint;-><init>(I)V

    iput-object v4, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowPaint:Landroid/graphics/Paint;

    .line 665
    new-instance v5, Landroid/graphics/Matrix;

    invoke-direct {v5}, Landroid/graphics/Matrix;-><init>()V

    iput-object v5, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->bitmapShaderMatrix:Landroid/graphics/Matrix;

    .line 666
    new-instance v5, Ljava/lang/ref/WeakReference;

    const/4 v6, 0x0

    invoke-direct {v5, v6}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    iput-object v5, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->bitmapInShader:Ljava/lang/ref/WeakReference;

    const/4 v5, 0x0

    .line 670
    invoke-virtual {v4, v5}, Landroid/graphics/Paint;->setColor(I)V

    .line 671
    invoke-virtual {v2, v3}, Landroid/graphics/Paint;->setFilterBitmap(Z)V

    .line 818
    new-instance v2, Landroid/graphics/RectF;

    invoke-direct {v2}, Landroid/graphics/RectF;-><init>()V

    iput-object v2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->cmpRectF1:Landroid/graphics/RectF;

    .line 819
    new-instance v2, Landroid/graphics/RectF;

    invoke-direct {v2}, Landroid/graphics/RectF;-><init>()V

    iput-object v2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->cmpRectF2:Landroid/graphics/RectF;

    .line 847
    new-instance v2, Lorg/telegram/ui/Components/blur3/Blur3HashImpl;

    invoke-direct {v2}, Lorg/telegram/ui/Components/blur3/Blur3HashImpl;-><init>()V

    iput-object v2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchHashBuilder:Lorg/telegram/ui/Components/blur3/Blur3HashImpl;

    .line 848
    new-instance v2, Landroid/graphics/Rect;

    invoke-direct {v2}, Landroid/graphics/Rect;-><init>()V

    iput-object v2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchDrawablePadding:Landroid/graphics/Rect;

    .line 54
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v2

    iput v2, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->strokeWidthTop:F

    const v2, 0x3f2aaaab

    .line 55
    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v2

    iput v2, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->strokeWidthBottom:F

    .line 57
    iput v1, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->strokeWidthFull:F

    .line 59
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    iput v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerRadius:F

    const/4 v0, 0x0

    .line 60
    iput v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerDx:F

    const v0, 0x3eaaaaab

    .line 61
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    iput v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerDy:F

    return-void
.end method

.method private checkNinePatchDrawable(IZ)Landroid/graphics/drawable/NinePatchDrawable;
    .locals 8

    .line 855
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchHashBuilder:Lorg/telegram/ui/Components/blur3/Blur3HashImpl;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/blur3/Blur3HashImpl;->start()V

    .line 856
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchHashBuilder:Lorg/telegram/ui/Components/blur3/Blur3HashImpl;

    int-to-long v1, p1

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/Components/blur3/Blur3HashImpl;->add(J)V

    .line 857
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchHashBuilder:Lorg/telegram/ui/Components/blur3/Blur3HashImpl;

    iget v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowColor:I

    int-to-long v1, v1

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/Components/blur3/Blur3HashImpl;->add(J)V

    .line 858
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchHashBuilder:Lorg/telegram/ui/Components/blur3/Blur3HashImpl;

    iget-object v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v1, v1, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->radii:[F

    invoke-interface {v0, v1}, Lorg/telegram/ui/Components/blur3/capture/IBlur3Hash;->add([F)V

    .line 859
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchHashBuilder:Lorg/telegram/ui/Components/blur3/Blur3HashImpl;

    iget v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowAlpha:F

    invoke-interface {v0, v1}, Lorg/telegram/ui/Components/blur3/capture/IBlur3Hash;->addF(F)V

    .line 860
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchHashBuilder:Lorg/telegram/ui/Components/blur3/Blur3HashImpl;

    iget v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerRadius:F

    invoke-interface {v0, v1}, Lorg/telegram/ui/Components/blur3/capture/IBlur3Hash;->addF(F)V

    .line 861
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchHashBuilder:Lorg/telegram/ui/Components/blur3/Blur3HashImpl;

    iget v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerDx:F

    invoke-interface {v0, v1}, Lorg/telegram/ui/Components/blur3/capture/IBlur3Hash;->addF(F)V

    .line 862
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchHashBuilder:Lorg/telegram/ui/Components/blur3/Blur3HashImpl;

    iget v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerDy:F

    invoke-interface {v0, v1}, Lorg/telegram/ui/Components/blur3/capture/IBlur3Hash;->addF(F)V

    .line 863
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchHashBuilder:Lorg/telegram/ui/Components/blur3/Blur3HashImpl;

    invoke-interface {v0, p2}, Lorg/telegram/ui/Components/blur3/capture/IBlur3Hash;->add(Z)V

    if-eqz p2, :cond_1

    .line 865
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchHashBuilder:Lorg/telegram/ui/Components/blur3/Blur3HashImpl;

    iget-object v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-boolean v1, v1, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->useFullStroke:Z

    invoke-interface {v0, v1}, Lorg/telegram/ui/Components/blur3/capture/IBlur3Hash;->add(Z)V

    .line 866
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-boolean v0, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->useFullStroke:Z

    .line 870
    iget-object v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchHashBuilder:Lorg/telegram/ui/Components/blur3/Blur3HashImpl;

    if-eqz v0, :cond_0

    .line 867
    iget v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->strokeColorFull:I

    int-to-long v2, v0

    invoke-virtual {v1, v2, v3}, Lorg/telegram/ui/Components/blur3/Blur3HashImpl;->add(J)V

    .line 868
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchHashBuilder:Lorg/telegram/ui/Components/blur3/Blur3HashImpl;

    iget-object v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget v1, v1, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->strokeWidthFull:F

    invoke-interface {v0, v1}, Lorg/telegram/ui/Components/blur3/capture/IBlur3Hash;->addF(F)V

    goto :goto_0

    .line 870
    :cond_0
    iget v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->strokeColorTop:I

    int-to-long v2, v0

    invoke-virtual {v1, v2, v3}, Lorg/telegram/ui/Components/blur3/Blur3HashImpl;->add(J)V

    .line 871
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchHashBuilder:Lorg/telegram/ui/Components/blur3/Blur3HashImpl;

    iget v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->strokeColorBottom:I

    int-to-long v1, v1

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/Components/blur3/Blur3HashImpl;->add(J)V

    .line 872
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchHashBuilder:Lorg/telegram/ui/Components/blur3/Blur3HashImpl;

    iget-object v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget v1, v1, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->strokeWidthTop:F

    invoke-interface {v0, v1}, Lorg/telegram/ui/Components/blur3/capture/IBlur3Hash;->addF(F)V

    .line 873
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchHashBuilder:Lorg/telegram/ui/Components/blur3/Blur3HashImpl;

    iget-object v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget v1, v1, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->strokeWidthBottom:F

    invoke-interface {v0, v1}, Lorg/telegram/ui/Components/blur3/capture/IBlur3Hash;->addF(F)V

    .line 877
    :cond_1
    :goto_0
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchHashBuilder:Lorg/telegram/ui/Components/blur3/Blur3HashImpl;

    invoke-virtual {v0}, Lorg/telegram/ui/Components/blur3/Blur3HashImpl;->get()J

    move-result-wide v0

    .line 879
    iget-object v2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchDrawable:Landroid/graphics/drawable/NinePatchDrawable;

    if-eqz v2, :cond_2

    iget-wide v2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchDrawableHash:J

    cmp-long v2, v2, v0

    if-eqz v2, :cond_4

    .line 880
    :cond_2
    iput-wide v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchDrawableHash:J

    .line 882
    invoke-static {p1}, Landroid/graphics/Color;->alpha(I)I

    move-result v0

    const/16 v1, 0xff

    if-ne v0, v1, :cond_3

    move v6, p1

    goto :goto_1

    :cond_3
    const/4 v0, 0x1

    move v6, v0

    .line 884
    :goto_1
    iget-object v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchRef:[Landroid/graphics/Bitmap;

    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v2, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->radii:[F

    iget v3, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerRadius:F

    iget v4, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerDx:F

    iget v5, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerDy:F

    new-instance v7, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$$ExternalSyntheticLambda0;

    invoke-direct {v7, p0, p1, p2}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;IZ)V

    invoke-static/range {v1 .. v7}, Lorg/telegram/ui/Components/blur3/utils/NinePatchBuilder;->createNinePatch([Landroid/graphics/Bitmap;[FFFFILorg/telegram/ui/Components/blur3/utils/NinePatchBuilder$NinePathRenderer;)Landroid/graphics/drawable/NinePatchDrawable;

    move-result-object p1

    iput-object p1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchDrawable:Landroid/graphics/drawable/NinePatchDrawable;

    .line 970
    iget-object p2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchDrawablePadding:Landroid/graphics/Rect;

    invoke-virtual {p1, p2}, Landroid/graphics/drawable/NinePatchDrawable;->getPadding(Landroid/graphics/Rect;)Z

    .line 973
    :cond_4
    iget-object p0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchDrawable:Landroid/graphics/drawable/NinePatchDrawable;

    return-object p0
.end method

.method private dispatchSourceRelativePositionChange()V
    .locals 2

    .line 821
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->cmpRectF1:Landroid/graphics/RectF;

    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->getPositionRelativeSource(Landroid/graphics/RectF;)V

    .line 822
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->cmpRectF1:Landroid/graphics/RectF;

    iget-object v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->cmpRectF2:Landroid/graphics/RectF;

    invoke-virtual {v0, v1}, Landroid/graphics/RectF;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 823
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->cmpRectF2:Landroid/graphics/RectF;

    iget-object v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->cmpRectF1:Landroid/graphics/RectF;

    invoke-virtual {v0, v1}, Landroid/graphics/RectF;->set(Landroid/graphics/RectF;)V

    .line 824
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->cmpRectF1:Landroid/graphics/RectF;

    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->onSourceRelativePositionChanged(Landroid/graphics/RectF;)V

    :cond_0
    return-void
.end method

.method private drawSourceAny(Landroid/graphics/Canvas;Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;)V
    .locals 16

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    .line 676
    iget v2, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->alpha:I

    if-nez v2, :cond_0

    goto/16 :goto_2

    .line 680
    :cond_0
    iget v3, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->backgroundColor:I

    int-to-float v2, v2

    const/high16 v4, 0x437f0000    # 255.0f

    div-float/2addr v2, v4

    invoke-static {v3, v2}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v7

    .line 681
    iget v2, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowColor:I

    invoke-static {v2}, Landroid/graphics/Color;->alpha(I)I

    move-result v2

    const/16 v3, 0xff

    if-lez v2, :cond_1

    iget v2, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->alpha:I

    if-ne v2, v3, :cond_1

    iget v2, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowAlpha:F

    const/4 v4, 0x0

    cmpl-float v4, v2, v4

    if-lez v4, :cond_1

    .line 682
    iget-object v4, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowPaint:Landroid/graphics/Paint;

    iget v5, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerRadius:F

    iget v6, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerDx:F

    iget v8, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerDy:F

    iget v9, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowColor:I

    invoke-static {v9, v2}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v2

    invoke-virtual {v4, v5, v6, v8, v2}, Landroid/graphics/Paint;->setShadowLayer(FFFI)V

    .line 683
    iget-object v2, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v4, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowPaint:Landroid/graphics/Paint;

    iget-boolean v5, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->inAppKeyboardOptimization:Z

    invoke-virtual {v2, v1, v4, v5}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->drawShadows(Landroid/graphics/Canvas;Landroid/graphics/Paint;Z)V

    .line 686
    :cond_1
    iget v2, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->sourceOffsetX:F

    .line 687
    iget v4, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->sourceOffsetY:F

    .line 688
    iget-object v5, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v5, v5, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->boundsWithPadding:Landroid/graphics/Rect;

    iget v6, v5, Landroid/graphics/Rect;->left:I

    int-to-float v8, v6

    add-float/2addr v8, v2

    .line 689
    iget v9, v5, Landroid/graphics/Rect;->top:I

    int-to-float v10, v9

    add-float/2addr v10, v4

    .line 690
    iget v11, v5, Landroid/graphics/Rect;->right:I

    int-to-float v12, v11

    add-float/2addr v12, v2

    .line 691
    iget v2, v5, Landroid/graphics/Rect;->bottom:I

    int-to-float v5, v2

    add-float v13, v5, v4

    .line 693
    iget v4, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->alpha:I

    if-eq v4, v3, :cond_2

    const/4 v3, 0x1

    :goto_0
    move v14, v3

    goto :goto_1

    :cond_2
    const/4 v3, 0x0

    goto :goto_0

    :goto_1
    if-eqz v14, :cond_3

    int-to-float v3, v6

    int-to-float v5, v9

    int-to-float v6, v11

    int-to-float v2, v2

    move v15, v5

    move v5, v2

    move v2, v3

    move v3, v15

    move v15, v6

    move v6, v4

    move v4, v15

    .line 695
    invoke-virtual/range {v1 .. v6}, Landroid/graphics/Canvas;->saveLayerAlpha(FFFFI)I

    .line 699
    :cond_3
    invoke-virtual {v1}, Landroid/graphics/Canvas;->save()I

    .line 700
    iget-object v2, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v2, v2, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->path:Landroid/graphics/Path;

    invoke-virtual {v1, v2}, Landroid/graphics/Canvas;->clipPath(Landroid/graphics/Path;)Z

    .line 701
    iget-object v2, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v2, v2, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->boundsWithPadding:Landroid/graphics/Rect;

    iget v3, v2, Landroid/graphics/Rect;->left:I

    int-to-float v3, v3

    iget v2, v2, Landroid/graphics/Rect;->top:I

    int-to-float v2, v2

    invoke-virtual {v1, v3, v2}, Landroid/graphics/Canvas;->translate(FF)V

    neg-float v2, v8

    neg-float v3, v10

    .line 705
    invoke-virtual {v1, v2, v3}, Landroid/graphics/Canvas;->translate(FF)V

    move-object v2, v1

    move v3, v8

    move v4, v10

    move v5, v12

    move v6, v13

    move-object/from16 v1, p2

    .line 706
    invoke-interface/range {v1 .. v6}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;->draw(Landroid/graphics/Canvas;FFFF)V

    move-object v1, v2

    .line 707
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    .line 709
    invoke-static {v7}, Landroid/graphics/Color;->alpha(I)I

    move-result v2

    if-lez v2, :cond_4

    .line 710
    iget-object v2, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->backgroundColorPaint:Landroid/graphics/Paint;

    invoke-virtual {v2, v7}, Landroid/graphics/Paint;->setColor(I)V

    .line 711
    iget-object v2, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v3, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->backgroundColorPaint:Landroid/graphics/Paint;

    invoke-virtual {v2, v1, v3}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->draw(Landroid/graphics/Canvas;Landroid/graphics/Paint;)V

    .line 714
    :cond_4
    invoke-direct/range {p0 .. p1}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->drawStrokeInternalIfNeeded(Landroid/graphics/Canvas;)V

    if-eqz v14, :cond_5

    .line 717
    invoke-virtual {v1}, Landroid/graphics/Canvas;->restore()V

    :cond_5
    :goto_2
    return-void
.end method

.method private drawSourceBitmap(Landroid/graphics/Canvas;Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceBitmap;)V
    .locals 8

    .line 743
    invoke-virtual {p2}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceBitmap;->getBitmap()Landroid/graphics/Bitmap;

    move-result-object v0

    .line 744
    iget-object v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->bitmapInShader:Ljava/lang/ref/WeakReference;

    invoke-virtual {v1}, Ljava/lang/ref/Reference;->get()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/graphics/Bitmap;

    if-eq v0, v1, :cond_1

    if-eqz v0, :cond_0

    .line 747
    invoke-virtual {v0}, Landroid/graphics/Bitmap;->isRecycled()Z

    move-result v1

    if-nez v1, :cond_0

    .line 748
    new-instance v1, Landroid/graphics/BitmapShader;

    sget-object v2, Landroid/graphics/Shader$TileMode;->CLAMP:Landroid/graphics/Shader$TileMode;

    invoke-direct {v1, v0, v2, v2}, Landroid/graphics/BitmapShader;-><init>(Landroid/graphics/Bitmap;Landroid/graphics/Shader$TileMode;Landroid/graphics/Shader$TileMode;)V

    iput-object v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->bitmapShader:Landroid/graphics/BitmapShader;

    .line 749
    iget-object v2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->backgroundBitmapPaint:Landroid/graphics/Paint;

    invoke-virtual {v2, v1}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    goto :goto_0

    :cond_0
    const/4 v1, 0x0

    .line 751
    iput-object v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->bitmapShader:Landroid/graphics/BitmapShader;

    .line 752
    iget-object v2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->backgroundBitmapPaint:Landroid/graphics/Paint;

    invoke-virtual {v2, v1}, Landroid/graphics/Paint;->setShader(Landroid/graphics/Shader;)Landroid/graphics/Shader;

    .line 756
    :cond_1
    :goto_0
    iget v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerRadius:F

    const/4 v2, 0x0

    cmpl-float v1, v1, v2

    if-lez v1, :cond_2

    iget v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowColor:I

    invoke-static {v1}, Landroid/graphics/Color;->alpha(I)I

    move-result v1

    if-lez v1, :cond_2

    iget v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowAlpha:F

    cmpl-float v1, v1, v2

    if-lez v1, :cond_2

    const/4 v1, 0x0

    .line 757
    invoke-direct {p0, v1, v1}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->checkNinePatchDrawable(IZ)Landroid/graphics/drawable/NinePatchDrawable;

    move-result-object v1

    .line 758
    iget-object v2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v2, v2, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->boundsWithPadding:Landroid/graphics/Rect;

    iget v3, v2, Landroid/graphics/Rect;->left:I

    iget-object v4, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchDrawablePadding:Landroid/graphics/Rect;

    iget v5, v4, Landroid/graphics/Rect;->left:I

    sub-int/2addr v3, v5

    iget v5, v2, Landroid/graphics/Rect;->top:I

    iget v6, v4, Landroid/graphics/Rect;->top:I

    sub-int/2addr v5, v6

    iget v6, v2, Landroid/graphics/Rect;->right:I

    iget v7, v4, Landroid/graphics/Rect;->right:I

    add-int/2addr v6, v7

    iget v2, v2, Landroid/graphics/Rect;->bottom:I

    iget v4, v4, Landroid/graphics/Rect;->bottom:I

    add-int/2addr v2, v4

    invoke-virtual {v1, v3, v5, v6, v2}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 764
    iget v2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->alpha:I

    invoke-virtual {v1, v2}, Landroid/graphics/drawable/NinePatchDrawable;->setAlpha(I)V

    .line 765
    invoke-virtual {v1, p1}, Landroid/graphics/drawable/NinePatchDrawable;->draw(Landroid/graphics/Canvas;)V

    .line 768
    :cond_2
    iget-object v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->bitmapShader:Landroid/graphics/BitmapShader;

    if-eqz v1, :cond_3

    if-eqz v0, :cond_3

    invoke-virtual {v0}, Landroid/graphics/Bitmap;->isRecycled()Z

    move-result v0

    if-nez v0, :cond_3

    iget v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->alpha:I

    if-lez v0, :cond_3

    .line 769
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->bitmapShaderMatrix:Landroid/graphics/Matrix;

    invoke-virtual {p2}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceBitmap;->getMatrix()Landroid/graphics/Matrix;

    move-result-object p2

    invoke-virtual {v0, p2}, Landroid/graphics/Matrix;->set(Landroid/graphics/Matrix;)V

    .line 770
    iget-object p2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->bitmapShaderMatrix:Landroid/graphics/Matrix;

    iget v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->sourceOffsetX:F

    neg-float v0, v0

    iget v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->sourceOffsetY:F

    neg-float v1, v1

    invoke-virtual {p2, v0, v1}, Landroid/graphics/Matrix;->postTranslate(FF)Z

    .line 771
    iget-object p2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->bitmapShader:Landroid/graphics/BitmapShader;

    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->bitmapShaderMatrix:Landroid/graphics/Matrix;

    invoke-virtual {p2, v0}, Landroid/graphics/Shader;->setLocalMatrix(Landroid/graphics/Matrix;)V

    .line 772
    iget-object p2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->backgroundBitmapPaint:Landroid/graphics/Paint;

    iget v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->alpha:I

    invoke-virtual {p2, v0}, Landroid/graphics/Paint;->setAlpha(I)V

    .line 773
    iget-object p2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->backgroundBitmapPaint:Landroid/graphics/Paint;

    invoke-virtual {p2, p1, v0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->draw(Landroid/graphics/Canvas;Landroid/graphics/Paint;)V

    .line 776
    :cond_3
    iget p2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->backgroundColor:I

    iget v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->alpha:I

    int-to-float v0, v0

    const/high16 v1, 0x437f0000    # 255.0f

    div-float/2addr v0, v1

    invoke-static {p2, v0}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result p2

    .line 777
    invoke-static {p2}, Landroid/graphics/Color;->alpha(I)I

    move-result v0

    if-lez v0, :cond_4

    .line 778
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->backgroundBitmapFill:Landroid/graphics/Paint;

    invoke-virtual {v0, p2}, Landroid/graphics/Paint;->setColor(I)V

    .line 779
    iget-object p2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->backgroundBitmapFill:Landroid/graphics/Paint;

    invoke-virtual {p2, p1, v0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->draw(Landroid/graphics/Canvas;Landroid/graphics/Paint;)V

    .line 782
    :cond_4
    invoke-direct {p0, p1}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->drawStrokeInternalIfNeeded(Landroid/graphics/Canvas;)V

    return-void
.end method

.method private drawSourceColor(Landroid/graphics/Canvas;Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;)V
    .locals 0

    .line 722
    invoke-virtual {p2}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;->getColor()I

    move-result p2

    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->drawSourceColorImpl(Landroid/graphics/Canvas;I)V

    return-void
.end method

.method private drawSourceColorImpl(Landroid/graphics/Canvas;I)V
    .locals 6

    .line 726
    iget v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->backgroundColor:I

    invoke-static {v0, p2}, Landroidx/core/graphics/ColorUtils;->compositeColors(II)I

    move-result p2

    .line 727
    invoke-static {p2}, Landroid/graphics/Color;->alpha(I)I

    move-result v0

    if-nez v0, :cond_0

    iget v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowColor:I

    invoke-static {v0}, Landroid/graphics/Color;->alpha(I)I

    move-result v0

    if-nez v0, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x1

    .line 731
    invoke-direct {p0, p2, v0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->checkNinePatchDrawable(IZ)Landroid/graphics/drawable/NinePatchDrawable;

    move-result-object p2

    .line 732
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v0, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->boundsWithPadding:Landroid/graphics/Rect;

    iget v1, v0, Landroid/graphics/Rect;->left:I

    iget-object v2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->ninePatchDrawablePadding:Landroid/graphics/Rect;

    iget v3, v2, Landroid/graphics/Rect;->left:I

    sub-int/2addr v1, v3

    iget v3, v0, Landroid/graphics/Rect;->top:I

    iget v4, v2, Landroid/graphics/Rect;->top:I

    sub-int/2addr v3, v4

    iget v4, v0, Landroid/graphics/Rect;->right:I

    iget v5, v2, Landroid/graphics/Rect;->right:I

    add-int/2addr v4, v5

    iget v0, v0, Landroid/graphics/Rect;->bottom:I

    iget v2, v2, Landroid/graphics/Rect;->bottom:I

    add-int/2addr v0, v2

    invoke-virtual {p2, v1, v3, v4, v0}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    .line 738
    iget p0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->alpha:I

    invoke-virtual {p2, p0}, Landroid/graphics/drawable/NinePatchDrawable;->setAlpha(I)V

    .line 739
    invoke-virtual {p2, p1}, Landroid/graphics/drawable/NinePatchDrawable;->draw(Landroid/graphics/Canvas;)V

    return-void
.end method

.method private drawSourceRenderNode(Landroid/graphics/Canvas;Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;)V
    .locals 1

    .line 810
    invoke-virtual {p1}, Landroid/graphics/Canvas;->isHardwareAccelerated()Z

    move-result v0

    if-nez v0, :cond_0

    .line 811
    invoke-virtual {p2}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;->getFallbackSource()Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;

    move-result-object p2

    invoke-virtual {p0, p1, p2}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->drawSource(Landroid/graphics/Canvas;Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;)V

    :cond_0
    return-void
.end method

.method public static drawStroke(Landroid/graphics/Canvas;FFFFFFZLandroid/graphics/Paint;)V
    .locals 8

    const/high16 v3, 0x40000000    # 2.0f

    div-float v4, p6, v3

    .line 576
    invoke-virtual {p0}, Landroid/graphics/Canvas;->save()I

    if-eqz p7, :cond_0

    sub-float v5, p1, v4

    move v6, v3

    add-float v3, p3, v4

    mul-float/2addr v6, p5

    add-float/2addr v6, p2

    .line 578
    invoke-static {v6, p2, p4}, Landroidx/core/math/MathUtils;->clamp(FFF)F

    move-result v6

    invoke-virtual {p0, v5, p2, v3, v6}, Landroid/graphics/Canvas;->clipRect(FFFF)Z

    move-result v6

    if-eqz v6, :cond_1

    add-float v2, p2, v4

    add-float/2addr v4, p4

    move v6, p5

    move-object v0, p0

    move-object/from16 v7, p8

    move v1, v5

    move v5, p5

    .line 579
    invoke-virtual/range {v0 .. v7}, Landroid/graphics/Canvas;->drawRoundRect(FFFFFFLandroid/graphics/Paint;)V

    goto :goto_0

    :cond_0
    move v6, v3

    sub-float v2, p1, v4

    mul-float v3, p5, v6

    sub-float v3, p4, v3

    .line 589
    invoke-static {v3, p2, p4}, Landroidx/core/math/MathUtils;->clamp(FFF)F

    move-result v3

    add-float v5, p3, v4

    invoke-virtual {p0, v2, v3, v5, p4}, Landroid/graphics/Canvas;->clipRect(FFFF)Z

    move-result v3

    if-eqz v3, :cond_1

    sub-float v1, p2, v4

    sub-float v4, p4, v4

    move v6, p5

    move v0, v2

    move v2, v1

    move v1, v0

    move-object v0, p0

    move-object/from16 v7, p8

    move v3, v5

    move v5, p5

    .line 590
    invoke-virtual/range {v0 .. v7}, Landroid/graphics/Canvas;->drawRoundRect(FFFFFFLandroid/graphics/Paint;)V

    .line 600
    :cond_1
    :goto_0
    invoke-virtual {p0}, Landroid/graphics/Canvas;->restore()V

    return-void
.end method

.method public static drawStroke(Landroid/graphics/Canvas;FFFF[FFZLandroid/graphics/Paint;)V
    .locals 19

    move-object/from16 v0, p0

    move/from16 v8, p1

    move/from16 v9, p2

    move/from16 v10, p3

    move/from16 v11, p4

    const/4 v1, 0x7

    const/4 v2, 0x6

    const/4 v12, 0x5

    const/4 v13, 0x3

    const/4 v14, 0x2

    const/4 v3, 0x1

    const/4 v15, 0x4

    const/16 v16, 0x0

    if-eqz p7, :cond_1

    .line 476
    aget v4, p5, v16

    aget v5, p5, v3

    cmpl-float v4, v4, v5

    if-nez v4, :cond_0

    aget v4, p5, v14

    cmpl-float v5, v5, v4

    if-nez v5, :cond_0

    aget v5, p5, v13

    cmpl-float v4, v4, v5

    if-nez v4, :cond_0

    :goto_0
    move v4, v3

    goto :goto_1

    :cond_0
    move/from16 v4, v16

    goto :goto_1

    .line 477
    :cond_1
    aget v4, p5, v15

    aget v5, p5, v12

    cmpl-float v4, v4, v5

    if-nez v4, :cond_0

    aget v4, p5, v2

    cmpl-float v5, v5, v4

    if-nez v5, :cond_0

    aget v5, p5, v1

    cmpl-float v4, v4, v5

    if-nez v4, :cond_0

    goto :goto_0

    :goto_1
    const/high16 v17, 0x40000000    # 2.0f

    div-float v18, p6, v17

    if-eqz p7, :cond_6

    if-eqz v4, :cond_3

    .line 485
    invoke-virtual {v0}, Landroid/graphics/Canvas;->save()I

    .line 486
    aget v1, p5, v16

    mul-float v1, v1, v17

    add-float/2addr v1, v9

    invoke-static {v1, v9, v11}, Landroidx/core/math/MathUtils;->clamp(FFF)F

    move-result v1

    invoke-virtual {v0, v8, v9, v10, v1}, Landroid/graphics/Canvas;->clipRect(FFFF)Z

    move-result v1

    if-eqz v1, :cond_2

    sub-float v1, v8, v18

    add-float v2, v9, v18

    add-float v3, v10, v18

    add-float v4, v11, v18

    .line 487
    aget v5, p5, v16

    move v6, v5

    move-object/from16 v7, p8

    invoke-virtual/range {v0 .. v7}, Landroid/graphics/Canvas;->drawRoundRect(FFFFFFLandroid/graphics/Paint;)V

    .line 496
    :cond_2
    invoke-virtual {v0}, Landroid/graphics/Canvas;->restore()V

    return-void

    :cond_3
    add-float v1, v8, v10

    div-float v12, v1, v17

    .line 499
    invoke-virtual {v0}, Landroid/graphics/Canvas;->save()I

    .line 500
    aget v1, p5, v16

    mul-float v1, v1, v17

    add-float/2addr v1, v9

    invoke-static {v1, v9, v11}, Landroidx/core/math/MathUtils;->clamp(FFF)F

    move-result v1

    invoke-virtual {v0, v8, v9, v12, v1}, Landroid/graphics/Canvas;->clipRect(FFFF)Z

    move-result v1

    if-eqz v1, :cond_4

    sub-float v1, v8, v18

    add-float v2, v9, v18

    move v4, v3

    add-float v3, v10, v18

    move v5, v4

    add-float v4, v11, v18

    move v6, v5

    .line 501
    aget v5, p5, v16

    aget v6, p5, v6

    move-object/from16 v7, p8

    invoke-virtual/range {v0 .. v7}, Landroid/graphics/Canvas;->drawRoundRect(FFFFFFLandroid/graphics/Paint;)V

    .line 510
    :cond_4
    invoke-virtual {v0}, Landroid/graphics/Canvas;->restore()V

    .line 511
    invoke-virtual {v0}, Landroid/graphics/Canvas;->save()I

    .line 512
    aget v1, p5, v16

    mul-float v1, v1, v17

    add-float/2addr v1, v9

    invoke-static {v1, v9, v11}, Landroidx/core/math/MathUtils;->clamp(FFF)F

    move-result v1

    invoke-virtual {v0, v12, v9, v10, v1}, Landroid/graphics/Canvas;->clipRect(FFFF)Z

    move-result v1

    if-eqz v1, :cond_5

    sub-float v1, v8, v18

    add-float v2, v9, v18

    add-float v3, v10, v18

    add-float v4, v11, v18

    .line 513
    aget v5, p5, v14

    aget v6, p5, v13

    move-object/from16 v7, p8

    invoke-virtual/range {v0 .. v7}, Landroid/graphics/Canvas;->drawRoundRect(FFFFFFLandroid/graphics/Paint;)V

    .line 522
    :cond_5
    invoke-virtual {v0}, Landroid/graphics/Canvas;->restore()V

    return-void

    :cond_6
    if-eqz v4, :cond_8

    .line 526
    invoke-virtual {v0}, Landroid/graphics/Canvas;->save()I

    .line 527
    aget v1, p5, v15

    mul-float v1, v1, v17

    sub-float v1, v11, v1

    invoke-static {v1, v9, v11}, Landroidx/core/math/MathUtils;->clamp(FFF)F

    move-result v1

    invoke-virtual {v0, v8, v1, v10, v11}, Landroid/graphics/Canvas;->clipRect(FFFF)Z

    move-result v1

    if-eqz v1, :cond_7

    sub-float v1, v8, v18

    sub-float v2, v9, v18

    add-float v3, v10, v18

    sub-float v4, v11, v18

    .line 528
    aget v5, p5, v15

    move v6, v5

    move-object/from16 v7, p8

    invoke-virtual/range {v0 .. v7}, Landroid/graphics/Canvas;->drawRoundRect(FFFFFFLandroid/graphics/Paint;)V

    .line 537
    :cond_7
    invoke-virtual {v0}, Landroid/graphics/Canvas;->restore()V

    return-void

    :cond_8
    add-float v3, v8, v10

    div-float v13, v3, v17

    .line 540
    invoke-virtual {v0}, Landroid/graphics/Canvas;->save()I

    .line 541
    aget v3, p5, v15

    mul-float v3, v3, v17

    sub-float v3, v11, v3

    invoke-static {v3, v9, v11}, Landroidx/core/math/MathUtils;->clamp(FFF)F

    move-result v3

    invoke-virtual {v0, v8, v3, v13, v11}, Landroid/graphics/Canvas;->clipRect(FFFF)Z

    move-result v3

    if-eqz v3, :cond_9

    move v3, v1

    sub-float v1, v8, v18

    move v4, v2

    sub-float v2, v9, v18

    move v5, v3

    add-float v3, v10, v18

    move v6, v4

    sub-float v4, v11, v18

    .line 542
    aget v6, p5, v6

    aget v5, p5, v5

    move v7, v6

    move v6, v5

    move v5, v7

    move-object/from16 v7, p8

    invoke-virtual/range {v0 .. v7}, Landroid/graphics/Canvas;->drawRoundRect(FFFFFFLandroid/graphics/Paint;)V

    .line 551
    :cond_9
    invoke-virtual {v0}, Landroid/graphics/Canvas;->restore()V

    .line 552
    invoke-virtual {v0}, Landroid/graphics/Canvas;->save()I

    .line 553
    aget v1, p5, v15

    mul-float v1, v1, v17

    sub-float v1, v11, v1

    invoke-static {v1, v9, v11}, Landroidx/core/math/MathUtils;->clamp(FFF)F

    move-result v1

    invoke-virtual {v0, v13, v1, v10, v11}, Landroid/graphics/Canvas;->clipRect(FFFF)Z

    move-result v1

    if-eqz v1, :cond_a

    sub-float v1, v8, v18

    sub-float v2, v9, v18

    add-float v3, v10, v18

    sub-float v4, v11, v18

    .line 554
    aget v5, p5, v15

    aget v6, p5, v12

    move-object/from16 v7, p8

    invoke-virtual/range {v0 .. v7}, Landroid/graphics/Canvas;->drawRoundRect(FFFFFFLandroid/graphics/Paint;)V

    .line 563
    :cond_a
    invoke-virtual/range {p0 .. p0}, Landroid/graphics/Canvas;->restore()V

    return-void
.end method

.method public static drawStroke(Landroid/graphics/Canvas;Landroid/graphics/RectF;FFZLandroid/graphics/Paint;)V
    .locals 9

    .line 570
    iget v1, p1, Landroid/graphics/RectF;->left:F

    iget v2, p1, Landroid/graphics/RectF;->top:F

    iget v3, p1, Landroid/graphics/RectF;->right:F

    iget v4, p1, Landroid/graphics/RectF;->bottom:F

    move-object v0, p0

    move v5, p2

    move v6, p3

    move v7, p4

    move-object v8, p5

    invoke-static/range {v0 .. v8}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->drawStroke(Landroid/graphics/Canvas;FFFFFFZLandroid/graphics/Paint;)V

    return-void
.end method

.method private drawStrokeInternalIfNeeded(Landroid/graphics/Canvas;)V
    .locals 4

    .line 786
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-boolean v0, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->useFullStroke:Z

    const/high16 v1, 0x437f0000    # 255.0f

    if-eqz v0, :cond_0

    .line 787
    iget v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->strokeColorFull:I

    iget v2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->alpha:I

    int-to-float v2, v2

    div-float/2addr v2, v1

    invoke-static {v0, v2}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v0

    .line 789
    invoke-static {v0}, Landroid/graphics/Color;->alpha(I)I

    move-result v1

    if-lez v1, :cond_2

    .line 790
    iget-object v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->paintStrokeFill:Landroid/graphics/Paint;

    invoke-virtual {v1, v0}, Landroid/graphics/Paint;->setColor(I)V

    .line 791
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v0, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->strokePathTop:Landroid/graphics/Path;

    iget-object p0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->paintStrokeFill:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, p0}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    return-void

    .line 794
    :cond_0
    iget v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->strokeColorTop:I

    iget v2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->alpha:I

    int-to-float v2, v2

    div-float/2addr v2, v1

    invoke-static {v0, v2}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v0

    .line 795
    iget v2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->strokeColorBottom:I

    iget v3, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->alpha:I

    int-to-float v3, v3

    div-float/2addr v3, v1

    invoke-static {v2, v3}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v1

    .line 797
    invoke-static {v0}, Landroid/graphics/Color;->alpha(I)I

    move-result v2

    if-lez v2, :cond_1

    .line 798
    iget-object v2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->paintStrokeFill:Landroid/graphics/Paint;

    invoke-virtual {v2, v0}, Landroid/graphics/Paint;->setColor(I)V

    .line 799
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v0, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->strokePathTop:Landroid/graphics/Path;

    iget-object v2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->paintStrokeFill:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, v2}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    .line 801
    :cond_1
    invoke-static {v1}, Landroid/graphics/Color;->alpha(I)I

    move-result v0

    if-lez v0, :cond_2

    .line 802
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->paintStrokeFill:Landroid/graphics/Paint;

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->setColor(I)V

    .line 803
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v0, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->strokePathBottom:Landroid/graphics/Path;

    iget-object p0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->paintStrokeFill:Landroid/graphics/Paint;

    invoke-virtual {p1, v0, p0}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    :cond_2
    return-void
.end method

.method public static getOutline(Landroid/graphics/Outline;Landroid/graphics/Rect;[F)V
    .locals 8

    .line 425
    invoke-static {p2}, Lorg/telegram/messenger/utils/RadiiUtils;->radiiAreSame([F)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x0

    .line 428
    aget p2, p2, v0

    invoke-virtual {p1}, Landroid/graphics/Rect;->width()I

    move-result v0

    invoke-virtual {p1}, Landroid/graphics/Rect;->height()I

    move-result v1

    invoke-static {v0, v1}, Ljava/lang/Math;->min(II)I

    move-result v0

    int-to-float v0, v0

    const/high16 v1, 0x40000000    # 2.0f

    div-float/2addr v0, v1

    invoke-static {p2, v0}, Ljava/lang/Math;->min(FF)F

    move-result p2

    invoke-virtual {p0, p1, p2}, Landroid/graphics/Outline;->setRoundRect(Landroid/graphics/Rect;F)V

    return-void

    .line 430
    :cond_0
    sget-object v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->tmpPath:Landroid/graphics/Path;

    if-nez v0, :cond_1

    .line 431
    new-instance v0, Landroid/graphics/Path;

    invoke-direct {v0}, Landroid/graphics/Path;-><init>()V

    sput-object v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->tmpPath:Landroid/graphics/Path;

    goto :goto_0

    .line 433
    :cond_1
    invoke-virtual {v0}, Landroid/graphics/Path;->rewind()V

    .line 435
    :goto_0
    sget-object v1, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->tmpPath:Landroid/graphics/Path;

    iget v0, p1, Landroid/graphics/Rect;->left:I

    int-to-float v2, v0

    iget v0, p1, Landroid/graphics/Rect;->top:I

    int-to-float v3, v0

    iget v0, p1, Landroid/graphics/Rect;->right:I

    int-to-float v4, v0

    iget p1, p1, Landroid/graphics/Rect;->bottom:I

    int-to-float v5, p1

    sget-object v7, Landroid/graphics/Path$Direction;->CW:Landroid/graphics/Path$Direction;

    move-object v6, p2

    invoke-virtual/range {v1 .. v7}, Landroid/graphics/Path;->addRoundRect(FFFF[FLandroid/graphics/Path$Direction;)V

    .line 440
    sget-object p1, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->tmpPath:Landroid/graphics/Path;

    invoke-virtual {p0, p1}, Landroid/graphics/Outline;->setConvexPath(Landroid/graphics/Path;)V

    return-void
.end method

.method private synthetic lambda$checkNinePatchDrawable$0(IZLandroid/graphics/Canvas;Landroid/graphics/RectF;[F)V
    .locals 25

    move-object/from16 v0, p0

    move-object/from16 v1, p3

    move-object/from16 v2, p4

    move-object/from16 v3, p5

    .line 889
    new-instance v4, Landroid/graphics/Path;

    invoke-direct {v4}, Landroid/graphics/Path;-><init>()V

    .line 890
    sget-object v11, Landroid/graphics/Path$Direction;->CW:Landroid/graphics/Path$Direction;

    invoke-virtual {v4, v2, v3, v11}, Landroid/graphics/Path;->addRoundRect(Landroid/graphics/RectF;[FLandroid/graphics/Path$Direction;)V

    .line 891
    new-instance v5, Landroid/graphics/Paint;

    const/4 v6, 0x1

    invoke-direct {v5, v6}, Landroid/graphics/Paint;-><init>(I)V

    .line 892
    sget-object v7, Landroid/graphics/Paint$Style;->FILL:Landroid/graphics/Paint$Style;

    invoke-virtual {v5, v7}, Landroid/graphics/Paint;->setStyle(Landroid/graphics/Paint$Style;)V

    move/from16 v7, p1

    .line 893
    invoke-virtual {v5, v7}, Landroid/graphics/Paint;->setColor(I)V

    .line 894
    iget v7, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerRadius:F

    const/4 v12, 0x0

    cmpl-float v7, v7, v12

    if-lez v7, :cond_0

    .line 895
    iget v7, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowColor:I

    iget v8, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowAlpha:F

    invoke-static {v7, v8}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result v7

    .line 896
    iget v8, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerRadius:F

    iget v9, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerDx:F

    iget v10, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerDy:F

    invoke-virtual {v5, v8, v9, v10, v7}, Landroid/graphics/Paint;->setShadowLayer(FFFI)V

    .line 898
    :cond_0
    invoke-virtual {v1, v4, v5}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    .line 899
    iget v7, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerRadius:F

    cmpl-float v7, v7, v12

    if-lez v7, :cond_1

    .line 900
    invoke-virtual {v5}, Landroid/graphics/Paint;->clearShadowLayer()V

    .line 901
    invoke-virtual {v1, v4, v5}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    :cond_1
    if-eqz p2, :cond_9

    .line 906
    new-instance v4, Landroid/graphics/Paint;

    invoke-direct {v4, v6}, Landroid/graphics/Paint;-><init>(I)V

    const/16 v5, 0x8

    .line 907
    invoke-static {v3, v5}, Ljava/util/Arrays;->copyOf([FI)[F

    move-result-object v3

    .line 908
    invoke-static {v3}, Lorg/telegram/messenger/utils/RadiiUtils;->radiiAreSame([F)Z

    move-result v13

    .line 909
    invoke-virtual {v2}, Landroid/graphics/RectF;->width()F

    move-result v7

    invoke-virtual {v2}, Landroid/graphics/RectF;->height()F

    move-result v8

    invoke-static {v7, v8}, Ljava/lang/Math;->min(FF)F

    move-result v7

    const/high16 v8, 0x40000000    # 2.0f

    div-float v14, v7, v8

    .line 911
    iget-object v7, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-boolean v7, v7, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->useFullStroke:Z

    const/4 v15, 0x0

    if-eqz v7, :cond_3

    .line 912
    new-instance v16, Landroid/graphics/Path;

    invoke-direct/range {v16 .. v16}, Landroid/graphics/Path;-><init>()V

    .line 913
    sget-object v10, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->tmpRadii:[F

    invoke-static {v3, v15, v10, v15, v5}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    if-eqz v13, :cond_2

    .line 914
    aget v3, v3, v15

    cmpl-float v3, v3, v14

    if-lez v3, :cond_2

    .line 915
    invoke-static {v10, v14}, Ljava/util/Arrays;->fill([FF)V

    .line 917
    :cond_2
    iget v6, v2, Landroid/graphics/RectF;->left:F

    iget v7, v2, Landroid/graphics/RectF;->top:F

    iget v8, v2, Landroid/graphics/RectF;->right:F

    iget v9, v2, Landroid/graphics/RectF;->bottom:F

    move-object/from16 v5, v16

    invoke-virtual/range {v5 .. v11}, Landroid/graphics/Path;->addRoundRect(FFFF[FLandroid/graphics/Path$Direction;)V

    .line 920
    iget v3, v2, Landroid/graphics/RectF;->left:F

    iget-object v5, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget v5, v5, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->strokeWidthFull:F

    add-float v17, v3, v5

    iget v3, v2, Landroid/graphics/RectF;->top:F

    add-float v18, v3, v5

    iget v3, v2, Landroid/graphics/RectF;->right:F

    sub-float v19, v3, v5

    iget v2, v2, Landroid/graphics/RectF;->bottom:F

    sub-float v20, v2, v5

    sget-object v22, Landroid/graphics/Path$Direction;->CCW:Landroid/graphics/Path$Direction;

    move-object/from16 v21, v10

    invoke-virtual/range {v16 .. v22}, Landroid/graphics/Path;->addRoundRect(FFFF[FLandroid/graphics/Path$Direction;)V

    move-object/from16 v5, v16

    .line 926
    invoke-virtual {v5}, Landroid/graphics/Path;->close()V

    .line 927
    iget v0, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->strokeColorFull:I

    invoke-virtual {v4, v0}, Landroid/graphics/Paint;->setColor(I)V

    .line 928
    invoke-virtual {v1, v5, v4}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    return-void

    .line 930
    :cond_3
    iget v5, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->strokeColorTop:I

    invoke-static {v5}, Landroid/graphics/Color;->alpha(I)I

    move-result v5

    if-lez v5, :cond_6

    aget v5, v3, v15

    cmpl-float v5, v5, v12

    const/16 v16, 0x2

    if-gtz v5, :cond_4

    aget v5, v3, v16

    cmpl-float v5, v5, v12

    if-lez v5, :cond_6

    .line 931
    :cond_4
    sget-object v10, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->tmpRadii:[F

    invoke-static {v10, v12}, Ljava/util/Arrays;->fill([FF)V

    .line 932
    aget v5, v3, v15

    aput v5, v10, v15

    aget v5, v3, v6

    aput v5, v10, v6

    aget v5, v3, v16

    aput v5, v10, v16

    const/4 v5, 0x3

    aget v7, v3, v5

    aput v7, v10, v5

    if-eqz v13, :cond_5

    .line 933
    aget v7, v3, v15

    cmpl-float v7, v7, v14

    if-lez v7, :cond_5

    .line 934
    aput v14, v10, v5

    aput v14, v10, v16

    aput v14, v10, v6

    aput v14, v10, v15

    .line 937
    :cond_5
    new-instance v17, Landroid/graphics/Path;

    invoke-direct/range {v17 .. v17}, Landroid/graphics/Path;-><init>()V

    .line 938
    iget v6, v2, Landroid/graphics/RectF;->left:F

    iget v7, v2, Landroid/graphics/RectF;->top:F

    iget v8, v2, Landroid/graphics/RectF;->right:F

    aget v5, v3, v15

    aget v9, v3, v16

    .line 940
    invoke-static {v5, v9}, Ljava/lang/Math;->max(FF)F

    move-result v5

    add-float/2addr v5, v7

    iget v9, v2, Landroid/graphics/RectF;->bottom:F

    invoke-static {v5, v9}, Ljava/lang/Math;->min(FF)F

    move-result v9

    move-object/from16 v5, v17

    .line 938
    invoke-virtual/range {v5 .. v11}, Landroid/graphics/Path;->addRoundRect(FFFF[FLandroid/graphics/Path$Direction;)V

    .line 941
    iget v5, v2, Landroid/graphics/RectF;->left:F

    iget v6, v2, Landroid/graphics/RectF;->top:F

    iget-object v7, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget v7, v7, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->strokeWidthTop:F

    add-float v19, v6, v7

    iget v7, v2, Landroid/graphics/RectF;->right:F

    aget v8, v3, v15

    aget v9, v3, v16

    .line 943
    invoke-static {v8, v9}, Ljava/lang/Math;->max(FF)F

    move-result v8

    add-float/2addr v6, v8

    iget v8, v2, Landroid/graphics/RectF;->bottom:F

    invoke-static {v6, v8}, Ljava/lang/Math;->min(FF)F

    move-result v21

    sget-object v23, Landroid/graphics/Path$Direction;->CCW:Landroid/graphics/Path$Direction;

    move/from16 v18, v5

    move/from16 v20, v7

    move-object/from16 v22, v10

    .line 941
    invoke-virtual/range {v17 .. v23}, Landroid/graphics/Path;->addRoundRect(FFFF[FLandroid/graphics/Path$Direction;)V

    move-object/from16 v5, v17

    .line 944
    iget v6, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->strokeColorTop:I

    invoke-virtual {v4, v6}, Landroid/graphics/Paint;->setColor(I)V

    .line 945
    invoke-virtual {v1, v5, v4}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    .line 948
    :cond_6
    iget v5, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->strokeColorBottom:I

    invoke-static {v5}, Landroid/graphics/Color;->alpha(I)I

    move-result v5

    if-lez v5, :cond_9

    const/16 v16, 0x4

    aget v5, v3, v16

    cmpl-float v5, v5, v12

    const/16 v17, 0x6

    if-gtz v5, :cond_7

    aget v5, v3, v17

    cmpl-float v5, v5, v12

    if-lez v5, :cond_9

    .line 949
    :cond_7
    sget-object v10, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->tmpRadii:[F

    invoke-static {v10, v12}, Ljava/util/Arrays;->fill([FF)V

    .line 950
    aget v5, v3, v16

    aput v5, v10, v16

    const/4 v5, 0x5

    aget v6, v3, v5

    aput v6, v10, v5

    aget v6, v3, v17

    aput v6, v10, v17

    const/4 v6, 0x7

    aget v7, v3, v6

    aput v7, v10, v6

    if-eqz v13, :cond_8

    .line 951
    aget v7, v3, v15

    cmpl-float v7, v7, v14

    if-lez v7, :cond_8

    .line 952
    aput v14, v10, v6

    aput v14, v10, v17

    aput v14, v10, v5

    aput v14, v10, v16

    .line 955
    :cond_8
    new-instance v18, Landroid/graphics/Path;

    invoke-direct/range {v18 .. v18}, Landroid/graphics/Path;-><init>()V

    .line 956
    iget v6, v2, Landroid/graphics/RectF;->left:F

    iget v5, v2, Landroid/graphics/RectF;->bottom:F

    aget v7, v3, v16

    aget v8, v3, v17

    .line 957
    invoke-static {v7, v8}, Ljava/lang/Math;->max(FF)F

    move-result v7

    sub-float/2addr v5, v7

    iget v7, v2, Landroid/graphics/RectF;->top:F

    invoke-static {v5, v7}, Ljava/lang/Math;->max(FF)F

    move-result v7

    iget v8, v2, Landroid/graphics/RectF;->right:F

    iget v9, v2, Landroid/graphics/RectF;->bottom:F

    move-object/from16 v5, v18

    .line 956
    invoke-virtual/range {v5 .. v11}, Landroid/graphics/Path;->addRoundRect(FFFF[FLandroid/graphics/Path$Direction;)V

    .line 959
    iget v5, v2, Landroid/graphics/RectF;->left:F

    iget v6, v2, Landroid/graphics/RectF;->bottom:F

    aget v7, v3, v16

    aget v3, v3, v17

    .line 960
    invoke-static {v7, v3}, Ljava/lang/Math;->max(FF)F

    move-result v3

    sub-float/2addr v6, v3

    iget v3, v2, Landroid/graphics/RectF;->top:F

    invoke-static {v6, v3}, Ljava/lang/Math;->max(FF)F

    move-result v20

    iget v3, v2, Landroid/graphics/RectF;->right:F

    iget v2, v2, Landroid/graphics/RectF;->bottom:F

    iget-object v6, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget v6, v6, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->strokeWidthBottom:F

    sub-float v22, v2, v6

    sget-object v24, Landroid/graphics/Path$Direction;->CCW:Landroid/graphics/Path$Direction;

    move/from16 v21, v3

    move/from16 v19, v5

    move-object/from16 v23, v10

    .line 959
    invoke-virtual/range {v18 .. v24}, Landroid/graphics/Path;->addRoundRect(FFFF[FLandroid/graphics/Path$Direction;)V

    move-object/from16 v5, v18

    .line 962
    iget v0, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->strokeColorBottom:I

    invoke-virtual {v4, v0}, Landroid/graphics/Paint;->setColor(I)V

    .line 963
    invoke-virtual {v1, v5, v4}, Landroid/graphics/Canvas;->drawPath(Landroid/graphics/Path;Landroid/graphics/Paint;)V

    :cond_9
    return-void
.end method


# virtual methods
.method public drawSource(Landroid/graphics/Canvas;Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;)V
    .locals 2

    .line 637
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v0, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->boundsWithPadding:Landroid/graphics/Rect;

    invoke-virtual {v0}, Landroid/graphics/Rect;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 641
    :cond_0
    iget v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->backgroundColor:I

    invoke-static {v0}, Landroid/graphics/Color;->alpha(I)I

    move-result v0

    const/16 v1, 0xff

    if-ne v0, v1, :cond_1

    const/4 p2, 0x0

    .line 642
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->drawSourceColorImpl(Landroid/graphics/Canvas;I)V

    return-void

    .line 646
    :cond_1
    instance-of v0, p2, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;

    if-eqz v0, :cond_2

    .line 647
    check-cast p2, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;

    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->drawSourceColor(Landroid/graphics/Canvas;Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;)V

    return-void

    .line 648
    :cond_2
    instance-of v0, p2, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceBitmap;

    if-eqz v0, :cond_3

    .line 649
    check-cast p2, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceBitmap;

    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->drawSourceBitmap(Landroid/graphics/Canvas;Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceBitmap;)V

    return-void

    .line 650
    :cond_3
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x1d

    if-lt v0, v1, :cond_4

    instance-of v0, p2, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;

    if-eqz v0, :cond_4

    .line 651
    check-cast p2, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;

    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->drawSourceRenderNode(Landroid/graphics/Canvas;Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;)V

    return-void

    .line 652
    :cond_4
    instance-of v0, p2, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceWrapped;

    if-eqz v0, :cond_5

    .line 653
    check-cast p2, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceWrapped;

    invoke-virtual {p2}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceWrapped;->getSource()Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;

    move-result-object p2

    invoke-virtual {p0, p1, p2}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->drawSource(Landroid/graphics/Canvas;Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;)V

    return-void

    :cond_5
    if-eqz p2, :cond_6

    .line 655
    invoke-direct {p0, p1, p2}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->drawSourceAny(Landroid/graphics/Canvas;Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;)V

    :cond_6
    :goto_0
    return-void
.end method

.method public enableInAppKeyboardOptimization()V
    .locals 1

    const/4 v0, 0x1

    .line 605
    iput-boolean v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->inAppKeyboardOptimization:Z

    return-void
.end method

.method public getAlpha()I
    .locals 0

    .line 453
    iget p0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->alpha:I

    return p0
.end method

.method public getOpacity()I
    .locals 0

    const/4 p0, -0x3

    return p0
.end method

.method public getOutline(Landroid/graphics/Outline;)V
    .locals 1

    .line 412
    iget-object p0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->boundsWithPadding:Landroid/graphics/Rect;

    iget-object p0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->radii:[F

    invoke-static {p1, v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->getOutline(Landroid/graphics/Outline;Landroid/graphics/Rect;[F)V

    return-void
.end method

.method public getOutsetX()I
    .locals 0

    .line 222
    iget p0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->outsetX:I

    return p0
.end method

.method public getOutsetY()I
    .locals 0

    .line 226
    iget p0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->outsetY:I

    return p0
.end method

.method public getPaddedBounds()Landroid/graphics/Rect;
    .locals 0

    .line 158
    iget-object p0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object p0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->boundsWithPadding:Landroid/graphics/Rect;

    return-object p0
.end method

.method public getPadding(Landroid/graphics/Rect;)Z
    .locals 1

    .line 104
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget v0, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->padding:I

    invoke-virtual {p1, v0, v0, v0, v0}, Landroid/graphics/Rect;->set(IIII)V

    .line 105
    iget-object p0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-boolean p0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->hasPadding:Z

    return p0
.end method

.method public getPath()Landroid/graphics/Path;
    .locals 0

    .line 162
    iget-object p0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object p0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->path:Landroid/graphics/Path;

    return-object p0
.end method

.method public getPositionRelativeSource(Landroid/graphics/RectF;)V
    .locals 1

    .line 834
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v0, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->boundsWithPadding:Landroid/graphics/Rect;

    invoke-virtual {p1, v0}, Landroid/graphics/RectF;->set(Landroid/graphics/Rect;)V

    .line 835
    iget v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->sourceOffsetX:F

    iget p0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->sourceOffsetY:F

    invoke-virtual {p1, v0, p0}, Landroid/graphics/RectF;->offset(FF)V

    return-void
.end method

.method public abstract getSource()Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;
.end method

.method public getSourceOffsetX()F
    .locals 0

    .line 76
    iget p0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->sourceOffsetX:F

    return p0
.end method

.method public getSourceOffsetY()F
    .locals 0

    .line 80
    iget p0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->sourceOffsetY:F

    return p0
.end method

.method public getUnwrappedSource()Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;
    .locals 1

    .line 187
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->getSource()Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;

    move-result-object p0

    .line 188
    :goto_0
    instance-of v0, p0, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceWrapped;

    if-eqz v0, :cond_0

    .line 189
    check-cast p0, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceWrapped;

    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceWrapped;->getSource()Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSource;

    move-result-object p0

    goto :goto_0

    :cond_0
    return-object p0
.end method

.method public getViewOutlineProvider()Landroid/view/ViewOutlineProvider;
    .locals 1

    .line 398
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->viewOutlineProvider:Landroid/view/ViewOutlineProvider;

    if-nez v0, :cond_0

    .line 399
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$1;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$1;-><init>(Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;)V

    iput-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->viewOutlineProvider:Landroid/view/ViewOutlineProvider;

    .line 407
    :cond_0
    iget-object p0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->viewOutlineProvider:Landroid/view/ViewOutlineProvider;

    return-object p0
.end method

.method public hasDisplayList()Z
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method public onBoundPropsChanged()V
    .locals 0

    .line 176
    invoke-direct {p0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->dispatchSourceRelativePositionChange()V

    return-void
.end method

.method public final onBoundsChange(Landroid/graphics/Rect;)V
    .locals 1

    .line 167
    invoke-super {p0, p1}, Landroid/graphics/drawable/Drawable;->onBoundsChange(Landroid/graphics/Rect;)V

    .line 168
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v0, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->bounds:Landroid/graphics/Rect;

    invoke-virtual {v0, p1}, Landroid/graphics/Rect;->set(Landroid/graphics/Rect;)V

    .line 169
    iget-object p1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    invoke-virtual {p1}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->build()V

    .line 171
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->onBoundPropsChanged()V

    return-void
.end method

.method public onSourceOffsetChange(FF)V
    .locals 0

    .line 181
    invoke-direct {p0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->dispatchSourceRelativePositionChange()V

    return-void
.end method

.method public onSourceRelativePositionChanged(Landroid/graphics/RectF;)V
    .locals 0

    return-void
.end method

.method public setAlpha(I)V
    .locals 0

    .line 448
    iput p1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->alpha:I

    return-void
.end method

.method public setClipToOutline(Z)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;
    .locals 0

    return-object p0
.end method

.method public setColorFilter(Landroid/graphics/ColorFilter;)V
    .locals 0

    return-void
.end method

.method public setColorProvider(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;
    .locals 1

    .line 203
    iput-object p1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->colorProvider:Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;

    .line 204
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->updateColors()V

    .line 206
    instance-of v0, p1, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    if-eqz v0, :cond_0

    check-cast p1, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    .line 207
    invoke-interface {p1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;->getStrokeWidthTop()F

    move-result v0

    invoke-interface {p1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;->getStrokeWidthBottom()F

    move-result p1

    invoke-virtual {p0, v0, p1}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->setStrokeWidth(FF)V

    :cond_0
    return-object p0
.end method

.method public setHasPadding(Z)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;
    .locals 1

    .line 98
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iput-boolean p1, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->hasPadding:Z

    return-object p0
.end method

.method public setIntensity(F)V
    .locals 1

    .line 153
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iput p1, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->liquidIntensity:F

    .line 154
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->onBoundPropsChanged()V

    return-void
.end method

.method public setOutset(II)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;
    .locals 0

    .line 215
    iput p1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->outsetX:I

    .line 216
    iput p2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->outsetY:I

    return-object p0
.end method

.method public setPadding(I)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;
    .locals 2

    .line 88
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget v1, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->padding:I

    if-eq v1, p1, :cond_0

    .line 89
    iput p1, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->padding:I

    .line 90
    invoke-virtual {v0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->build()V

    .line 92
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->onBoundPropsChanged()V

    :cond_0
    return-object p0
.end method

.method public setRadius(F)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;
    .locals 1

    .line 109
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v0, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->radii:[F

    invoke-static {v0, p1}, Ljava/util/Arrays;->fill([FF)V

    .line 110
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v0, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->shaderRadii:[F

    invoke-static {v0, p1}, Ljava/util/Arrays;->fill([FF)V

    .line 111
    iget-object p1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    invoke-virtual {p1}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->build()V

    .line 113
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->onBoundPropsChanged()V

    return-object p0
.end method

.method public setRadius(FFFF)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;
    .locals 10

    .line 118
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v1, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->radii:[F

    const/4 v2, 0x1

    aput p1, v1, v2

    const/4 v3, 0x0

    aput p1, v1, v3

    const/4 v4, 0x3

    .line 119
    aput p2, v1, v4

    const/4 v5, 0x2

    aput p2, v1, v5

    const/4 v6, 0x5

    .line 120
    aput p3, v1, v6

    const/4 v7, 0x4

    aput p3, v1, v7

    const/4 v8, 0x7

    .line 121
    aput p4, v1, v8

    const/4 v9, 0x6

    aput p4, v1, v9

    .line 122
    iget-object v1, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->shaderRadii:[F

    aput p1, v1, v2

    aput p1, v1, v3

    .line 123
    aput p2, v1, v4

    aput p2, v1, v5

    .line 124
    aput p3, v1, v6

    aput p3, v1, v7

    .line 125
    aput p4, v1, v8

    aput p4, v1, v9

    .line 126
    invoke-virtual {v0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->build()V

    .line 128
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->onBoundPropsChanged()V

    return-object p0
.end method

.method public setRadius(FFFFZ)V
    .locals 10

    .line 133
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-object v1, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->radii:[F

    const/4 v2, 0x1

    aput p1, v1, v2

    const/4 v3, 0x0

    aput p1, v1, v3

    const/4 v4, 0x3

    .line 134
    aput p2, v1, v4

    const/4 v5, 0x2

    aput p2, v1, v5

    const/4 v6, 0x0

    if-eqz p5, :cond_0

    move v7, v6

    goto :goto_0

    :cond_0
    move v7, p3

    :goto_0
    const/4 v8, 0x5

    .line 135
    aput v7, v1, v8

    const/4 v9, 0x4

    aput v7, v1, v9

    if-eqz p5, :cond_1

    goto :goto_1

    :cond_1
    move v6, p4

    :goto_1
    const/4 p5, 0x7

    .line 136
    aput v6, v1, p5

    const/4 v7, 0x6

    aput v6, v1, v7

    .line 137
    iget-object v1, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->shaderRadii:[F

    aput p1, v1, v2

    aput p1, v1, v3

    .line 138
    aput p2, v1, v4

    aput p2, v1, v5

    .line 139
    aput p3, v1, v8

    aput p3, v1, v9

    .line 140
    aput p4, v1, p5

    aput p4, v1, v7

    .line 141
    invoke-virtual {v0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->build()V

    .line 143
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->onBoundPropsChanged()V

    return-void
.end method

.method public setShadowAlpha(F)V
    .locals 1

    .line 621
    iget v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowAlpha:F

    cmpl-float v0, v0, p1

    if-eqz v0, :cond_0

    .line 622
    iput p1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowAlpha:F

    .line 623
    invoke-virtual {p0}, Landroid/graphics/drawable/Drawable;->invalidateSelf()V

    :cond_0
    return-void
.end method

.method public setShadowParams(FFF)V
    .locals 0

    .line 615
    iput p1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerRadius:F

    .line 616
    iput p2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerDx:F

    .line 617
    iput p3, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowLayerDy:F

    return-void
.end method

.method public setSourceOffset(FF)V
    .locals 1

    .line 68
    iget v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->sourceOffsetX:F

    cmpl-float v0, v0, p1

    if-nez v0, :cond_1

    iget v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->sourceOffsetY:F

    cmpl-float v0, v0, p2

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    return-void

    .line 69
    :cond_1
    :goto_0
    iput p1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->sourceOffsetX:F

    .line 70
    iput p2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->sourceOffsetY:F

    .line 71
    invoke-virtual {p0, p1, p2}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->onSourceOffsetChange(FF)V

    return-void
.end method

.method public setStrokeWidth(FF)V
    .locals 0

    .line 628
    iget-object p0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iput p1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->strokeWidthTop:F

    .line 629
    iput p2, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->strokeWidthBottom:F

    return-void
.end method

.method public setThickness(I)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;
    .locals 1

    .line 147
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iput p1, v0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->liquidThickness:I

    .line 148
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->onBoundPropsChanged()V

    return-object p0
.end method

.method public updateColors()V
    .locals 6

    .line 231
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->colorProvider:Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;

    if-nez v0, :cond_0

    return-void

    .line 233
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getGlassOutlineStyle()Lcom/exteragram/messenger/GlassOutlineStyle;

    move-result-object v0

    .line 234
    sget-object v1, Lcom/exteragram/messenger/GlassOutlineStyle;->GLARE:Lcom/exteragram/messenger/GlassOutlineStyle;

    const/4 v2, 0x1

    const/4 v3, 0x0

    if-ne v0, v1, :cond_1

    move v1, v2

    goto :goto_0

    :cond_1
    move v1, v3

    .line 235
    :goto_0
    sget-object v4, Lcom/exteragram/messenger/GlassOutlineStyle;->SOLID:Lcom/exteragram/messenger/GlassOutlineStyle;

    if-ne v0, v4, :cond_2

    move v0, v2

    goto :goto_1

    :cond_2
    move v0, v3

    .line 237
    :goto_1
    iget-object v4, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->boundProps:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;

    iget-boolean v5, v4, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->useFullStroke:Z

    if-eq v5, v0, :cond_3

    .line 238
    iput-boolean v0, v4, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->useFullStroke:Z

    .line 239
    invoke-virtual {v4}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable$Props;->build()V

    :cond_3
    if-nez v1, :cond_5

    .line 243
    iget-object v4, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->colorProvider:Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;

    instance-of v5, v4, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    if-eqz v5, :cond_4

    check-cast v4, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    invoke-interface {v4}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;->isShadowAlwaysVisible()Z

    move-result v4

    if-eqz v4, :cond_4

    goto :goto_2

    :cond_4
    move v2, v3

    .line 245
    :cond_5
    :goto_2
    iget-object v4, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->colorProvider:Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;

    invoke-interface {v4}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;->getBackgroundColor()I

    move-result v4

    iput v4, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->backgroundColor:I

    if-eqz v2, :cond_6

    .line 246
    iget-object v4, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->colorProvider:Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;

    invoke-interface {v4}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;->getShadowColor()I

    move-result v4

    goto :goto_3

    :cond_6
    move v4, v3

    :goto_3
    iput v4, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->shadowColor:I

    if-eqz v1, :cond_7

    .line 247
    iget-object v4, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->colorProvider:Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;

    invoke-interface {v4}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;->getStrokeColorTop()I

    move-result v4

    goto :goto_4

    :cond_7
    move v4, v3

    :goto_4
    iput v4, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->strokeColorTop:I

    if-eqz v1, :cond_8

    .line 248
    iget-object v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->colorProvider:Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;

    invoke-interface {v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;->getStrokeColorBottom()I

    move-result v1

    goto :goto_5

    :cond_8
    move v1, v3

    :goto_5
    iput v1, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->strokeColorBottom:I

    if-eqz v0, :cond_9

    .line 249
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->colorProvider:Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;

    invoke-interface {v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;->getStrokeColorFull()I

    move-result v3

    :cond_9
    iput v3, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->strokeColorFull:I

    .line 251
    iget-object v0, p0, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->colorProvider:Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundColorProvider;

    instance-of v1, v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    if-eqz v1, :cond_b

    check-cast v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    if-eqz v2, :cond_a

    .line 253
    invoke-interface {v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;->getShadowRadius()F

    move-result v1

    invoke-interface {v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;->getShadowDx()F

    move-result v2

    invoke-interface {v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;->getShadowDy()F

    move-result v0

    invoke-virtual {p0, v1, v2, v0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->setShadowParams(FFF)V

    goto :goto_6

    :cond_a
    const/4 v0, 0x0

    .line 255
    invoke-virtual {p0, v0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;->setShadowParams(FFF)V

    .line 259
    :cond_b
    :goto_6
    invoke-virtual {p0}, Landroid/graphics/drawable/Drawable;->invalidateSelf()V

    return-void
.end method

.method public updateDisplayList()V
    .locals 0

    return-void
.end method
