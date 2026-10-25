.class public final Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;
.super Landroid/view/View;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton$Companion;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0008\n\u0002\u0010\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u0007\n\u0002\u0008\u0003\u0018\u0000 !2\u00020\u0001:\u0001!B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\u0008\u0006\u0010\u0007J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u0005J\u0008\u0010\u0018\u001a\u00020\u0017H\u0002J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001bH\u0014J(\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0002R\u000e\u0010\u0008\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u000cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005@BX\u0086\u000e\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0014\u0010\u0015\u00a8\u0006\""
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;",
        "Landroid/view/View;",
        "context",
        "Landroid/content/Context;",
        "color",
        "",
        "<init>",
        "(Landroid/content/Context;I)V",
        "translateIcon",
        "Landroid/graphics/drawable/Drawable;",
        "resetIcon",
        "progressDrawable",
        "Lorg/telegram/ui/Components/CircularProgressDrawable;",
        "loadingProgress",
        "Lorg/telegram/ui/Components/AnimatedFloat;",
        "switchProgress",
        "icon",
        "previousIcon",
        "value",
        "state",
        "getState",
        "()I",
        "setState",
        "",
        "updateContentDescription",
        "onDraw",
        "canvas",
        "Landroid/graphics/Canvas;",
        "drawIcon",
        "drawable",
        "alpha",
        "",
        "scale",
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

.annotation build Lkotlin/jvm/internal/SourceDebugExtension;
    value = {
        "SMAP\nPluginTranslateButton.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginTranslateButton.kt\ncom/exteragram/messenger/plugins/ui/components/PluginTranslateButton\n+ 2 Canvas.kt\nandroidx/core/graphics/CanvasKt\n*L\n1#1,134:1\n44#2,8:135\n81#2,8:143\n*S KotlinDebug\n*F\n+ 1 PluginTranslateButton.kt\ncom/exteragram/messenger/plugins/ui/components/PluginTranslateButton\n*L\n84#1:135,8\n123#1:143,8\n*E\n"
    }
.end annotation


# static fields
.field public static final Companion:Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton$Companion;

.field public static final STATE_LOADING:I = 0x1

.field public static final STATE_RESET:I = 0x2

.field public static final STATE_TRANSLATE:I


# instance fields
.field private icon:Landroid/graphics/drawable/Drawable;

.field private final loadingProgress:Lorg/telegram/ui/Components/AnimatedFloat;

.field private previousIcon:Landroid/graphics/drawable/Drawable;

.field private final progressDrawable:Lorg/telegram/ui/Components/CircularProgressDrawable;

.field private final resetIcon:Landroid/graphics/drawable/Drawable;

.field private state:I

.field private final switchProgress:Lorg/telegram/ui/Components/AnimatedFloat;

.field private final translateIcon:Landroid/graphics/drawable/Drawable;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->Companion:Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton$Companion;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;I)V
    .locals 11

    .line 31
    invoke-direct {p0, p1}, Landroid/view/View;-><init>(Landroid/content/Context;)V

    .line 33
    sget v0, Lorg/telegram/messenger/R$drawable;->msg_translate:I

    invoke-static {p1, v0}, Landroidx/core/content/ContextCompat;->getDrawable(Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    const/4 v1, 0x0

    const-string v2, "Required value was null."

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->translateIcon:Landroid/graphics/drawable/Drawable;

    .line 34
    sget v3, Lorg/telegram/messenger/R$drawable;->photo_undo2:I

    invoke-static {p1, v3}, Landroidx/core/content/ContextCompat;->getDrawable(Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;

    move-result-object p1

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Landroid/graphics/drawable/Drawable;->mutate()Landroid/graphics/drawable/Drawable;

    move-result-object p1

    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->resetIcon:Landroid/graphics/drawable/Drawable;

    .line 35
    new-instance v1, Lorg/telegram/ui/Components/CircularProgressDrawable;

    invoke-direct {v1, p2}, Lorg/telegram/ui/Components/CircularProgressDrawable;-><init>(I)V

    iput-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->progressDrawable:Lorg/telegram/ui/Components/CircularProgressDrawable;

    .line 37
    new-instance v2, Lorg/telegram/ui/Components/AnimatedFloat;

    sget-object v8, Lorg/telegram/ui/Components/CubicBezierInterpolator;->EASE_OUT_QUINT:Lorg/telegram/ui/Components/CubicBezierInterpolator;

    const-wide/16 v4, 0x0

    const-wide/16 v6, 0x140

    move-object v3, p0

    invoke-direct/range {v2 .. v8}, Lorg/telegram/ui/Components/AnimatedFloat;-><init>(Landroid/view/View;JJLandroid/animation/TimeInterpolator;)V

    iput-object v2, v3, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->loadingProgress:Lorg/telegram/ui/Components/AnimatedFloat;

    move-object v5, v3

    .line 38
    new-instance v3, Lorg/telegram/ui/Components/AnimatedFloat;

    const-wide/16 v6, 0x0

    move-object v10, v8

    const-wide/16 v8, 0xfa

    const/high16 v4, 0x3f800000    # 1.0f

    invoke-direct/range {v3 .. v10}, Lorg/telegram/ui/Components/AnimatedFloat;-><init>(FLandroid/view/View;JJLandroid/animation/TimeInterpolator;)V

    move-object p0, v3

    move-object v3, v5

    iput-object p0, v3, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->switchProgress:Lorg/telegram/ui/Components/AnimatedFloat;

    .line 40
    iput-object v0, v3, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->icon:Landroid/graphics/drawable/Drawable;

    .line 47
    new-instance p0, Landroid/graphics/PorterDuffColorFilter;

    sget-object v1, Landroid/graphics/PorterDuff$Mode;->SRC_IN:Landroid/graphics/PorterDuff$Mode;

    invoke-direct {p0, p2, v1}, Landroid/graphics/PorterDuffColorFilter;-><init>(ILandroid/graphics/PorterDuff$Mode;)V

    .line 48
    invoke-virtual {v0, p0}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 49
    invoke-virtual {p1, p0}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    .line 50
    invoke-direct {v3}, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->updateContentDescription()V

    return-void

    .line 34
    :cond_0
    invoke-static {v2}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw v1

    .line 33
    :cond_1
    invoke-static {v2}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw v1
.end method

.method private final drawIcon(Landroid/graphics/Canvas;Landroid/graphics/drawable/Drawable;FF)V
    .locals 4

    const/4 v0, 0x0

    cmpg-float v0, p3, v0

    if-gtz v0, :cond_0

    return-void

    .line 119
    :cond_0
    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v0

    invoke-virtual {p2}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v1

    sub-int/2addr v0, v1

    div-int/lit8 v0, v0, 0x2

    .line 120
    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result v1

    invoke-virtual {p2}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v2

    sub-int/2addr v1, v2

    div-int/lit8 v1, v1, 0x2

    .line 121
    invoke-virtual {p2}, Landroid/graphics/drawable/Drawable;->getIntrinsicWidth()I

    move-result v2

    add-int/2addr v2, v0

    invoke-virtual {p2}, Landroid/graphics/drawable/Drawable;->getIntrinsicHeight()I

    move-result v3

    add-int/2addr v3, v1

    invoke-virtual {p2, v0, v1, v2, v3}, Landroid/graphics/drawable/Drawable;->setBounds(IIII)V

    const/high16 v0, 0x437f0000    # 255.0f

    mul-float/2addr v0, p3

    float-to-int p3, v0

    .line 122
    invoke-virtual {p2, p3}, Landroid/graphics/drawable/Drawable;->setAlpha(I)V

    .line 123
    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result p3

    int-to-float p3, p3

    const/high16 v0, 0x40000000    # 2.0f

    div-float/2addr p3, v0

    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result p0

    int-to-float p0, p0

    div-float/2addr p0, v0

    .line 81
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    move-result v0

    .line 82
    invoke-virtual {p1, p4, p4, p3, p0}, Landroid/graphics/Canvas;->scale(FFFF)V

    .line 124
    :try_start_0
    invoke-virtual {p2, p1}, Landroid/graphics/drawable/Drawable;->draw(Landroid/graphics/Canvas;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 86
    invoke-virtual {p1, v0}, Landroid/graphics/Canvas;->restoreToCount(I)V

    return-void

    :catchall_0
    move-exception p0

    invoke-virtual {p1, v0}, Landroid/graphics/Canvas;->restoreToCount(I)V

    throw p0
.end method

.method private final updateContentDescription()V
    .locals 2

    .line 72
    iget v0, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->state:I

    const/4 v1, 0x2

    if-ne v0, v1, :cond_0

    sget v0, Lorg/telegram/messenger/R$string;->ShowOriginalButton:I

    goto :goto_0

    :cond_0
    sget v0, Lorg/telegram/messenger/R$string;->TranslateMessage:I

    .line 71
    :goto_0
    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Landroid/view/View;->setContentDescription(Ljava/lang/CharSequence;)V

    return-void
.end method


# virtual methods
.method public final getState()I
    .locals 0

    .line 43
    iget p0, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->state:I

    return p0
.end method

.method public onDraw(Landroid/graphics/Canvas;)V
    .locals 12

    .line 77
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->loadingProgress:Lorg/telegram/ui/Components/AnimatedFloat;

    iget v1, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->state:I

    const/4 v2, 0x1

    const/4 v3, 0x0

    const/high16 v4, 0x3f800000    # 1.0f

    if-ne v1, v2, :cond_0

    move v1, v4

    goto :goto_0

    :cond_0
    move v1, v3

    :goto_0
    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/AnimatedFloat;->set(F)F

    move-result v0

    const/high16 v1, 0x41200000    # 10.0f

    .line 78
    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v1

    cmpg-float v2, v0, v4

    if-gez v2, :cond_3

    .line 81
    iget-object v2, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->switchProgress:Lorg/telegram/ui/Components/AnimatedFloat;

    invoke-virtual {v2, v4}, Lorg/telegram/ui/Components/AnimatedFloat;->set(F)F

    move-result v2

    sub-float v5, v4, v0

    const v6, 0x3f19999a    # 0.6f

    .line 83
    invoke-static {v4, v6, v0}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v6

    neg-int v7, v1

    int-to-float v7, v7

    mul-float/2addr v7, v0

    .line 44
    invoke-virtual {p1}, Landroid/graphics/Canvas;->save()I

    move-result v8

    .line 45
    invoke-virtual {p1, v3, v7}, Landroid/graphics/Canvas;->translate(FF)V

    .line 85
    :try_start_0
    iget-object v7, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->previousIcon:Landroid/graphics/drawable/Drawable;

    const v9, 0x3ecccccd    # 0.4f

    if-eqz v7, :cond_2

    cmpg-float v10, v2, v4

    if-gez v10, :cond_1

    sub-float v10, v4, v2

    mul-float/2addr v10, v5

    .line 91
    invoke-static {v4, v9, v2}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v11

    mul-float/2addr v11, v6

    .line 87
    invoke-direct {p0, p1, v7, v10, v11}, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->drawIcon(Landroid/graphics/Canvas;Landroid/graphics/drawable/Drawable;FF)V

    goto :goto_1

    :catchall_0
    move-exception p0

    goto :goto_2

    :cond_1
    const/4 v7, 0x0

    .line 94
    iput-object v7, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->previousIcon:Landroid/graphics/drawable/Drawable;

    .line 99
    :cond_2
    :goto_1
    iget-object v7, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->icon:Landroid/graphics/drawable/Drawable;

    mul-float/2addr v5, v2

    .line 101
    invoke-static {v9, v4, v2}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v2

    mul-float/2addr v6, v2

    .line 97
    invoke-direct {p0, p1, v7, v5, v6}, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->drawIcon(Landroid/graphics/Canvas;Landroid/graphics/drawable/Drawable;FF)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 49
    invoke-virtual {p1, v8}, Landroid/graphics/Canvas;->restoreToCount(I)V

    goto :goto_3

    :goto_2
    invoke-virtual {p1, v8}, Landroid/graphics/Canvas;->restoreToCount(I)V

    throw p0

    :cond_3
    :goto_3
    cmpl-float v2, v0, v3

    if-lez v2, :cond_4

    sub-float/2addr v4, v0

    int-to-float v1, v1

    mul-float/2addr v4, v1

    float-to-int v1, v4

    .line 108
    iget-object v2, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->progressDrawable:Lorg/telegram/ui/Components/CircularProgressDrawable;

    invoke-virtual {p0}, Landroid/view/View;->getWidth()I

    move-result v3

    invoke-virtual {p0}, Landroid/view/View;->getHeight()I

    move-result v4

    add-int/2addr v4, v1

    const/4 v5, 0x0

    invoke-virtual {v2, v5, v1, v3, v4}, Lorg/telegram/ui/Components/CircularProgressDrawable;->setBounds(IIII)V

    .line 109
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->progressDrawable:Lorg/telegram/ui/Components/CircularProgressDrawable;

    const/high16 v2, 0x437f0000    # 255.0f

    mul-float/2addr v2, v0

    float-to-int v0, v2

    invoke-virtual {v1, v0}, Lorg/telegram/ui/Components/CircularProgressDrawable;->setAlpha(I)V

    .line 110
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->progressDrawable:Lorg/telegram/ui/Components/CircularProgressDrawable;

    invoke-virtual {v0, p1}, Lorg/telegram/ui/Components/CircularProgressDrawable;->draw(Landroid/graphics/Canvas;)V

    .line 111
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    :cond_4
    return-void
.end method

.method public final setState(I)V
    .locals 2

    .line 54
    iget v0, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->state:I

    if-ne v0, p1, :cond_0

    return-void

    .line 57
    :cond_0
    iput p1, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->state:I

    const/4 v0, 0x1

    if-eq p1, v0, :cond_2

    const/4 v1, 0x2

    if-ne p1, v1, :cond_1

    .line 59
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->resetIcon:Landroid/graphics/drawable/Drawable;

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->translateIcon:Landroid/graphics/drawable/Drawable;

    .line 60
    :goto_0
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->icon:Landroid/graphics/drawable/Drawable;

    if-eq p1, v1, :cond_2

    .line 61
    iput-object v1, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->previousIcon:Landroid/graphics/drawable/Drawable;

    .line 62
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->icon:Landroid/graphics/drawable/Drawable;

    .line 63
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->switchProgress:Lorg/telegram/ui/Components/AnimatedFloat;

    const/4 v1, 0x0

    invoke-virtual {p1, v1, v0}, Lorg/telegram/ui/Components/AnimatedFloat;->set(FZ)F

    .line 66
    :cond_2
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/ui/components/PluginTranslateButton;->updateContentDescription()V

    .line 67
    invoke-virtual {p0}, Landroid/view/View;->invalidate()V

    return-void
.end method
