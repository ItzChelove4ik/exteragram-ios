.class public abstract Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper$WindowEdgeExtension;
    }
.end annotation


# direct methods
.method public static apply(Landroid/view/View;Landroid/view/View;ZF)V
    .locals 4

    .line 59
    invoke-static {p3}, Lorg/telegram/messenger/Utilities;->clamp01(F)F

    move-result p3

    const/high16 v0, 0x42c00000    # 96.0f

    .line 60
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    .line 61
    sget-object v1, Lorg/telegram/ui/Components/CubicBezierInterpolator;->Emphasized:Landroid/view/animation/Interpolator;

    invoke-interface {v1, p3}, Landroid/animation/TimeInterpolator;->getInterpolation(F)F

    move-result v1

    mul-float/2addr v1, v0

    invoke-static {v1}, Ljava/lang/Math;->round(F)I

    move-result v1

    int-to-float v1, v1

    const/high16 v2, 0x42a60000    # 83.0f

    const/high16 v3, 0x3f800000    # 1.0f

    if-eqz p0, :cond_2

    if-eqz p2, :cond_0

    sub-float/2addr v0, v1

    goto :goto_0

    :cond_0
    sub-float v0, v1, v0

    .line 63
    :goto_0
    invoke-virtual {p0, v0}, Landroid/view/View;->setTranslationX(F)V

    if-eqz p2, :cond_1

    const/high16 v0, 0x42480000    # 50.0f

    .line 64
    invoke-static {p3, v0, v2}, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper;->fade(FFF)F

    move-result v0

    goto :goto_1

    :cond_1
    move v0, v3

    :goto_1
    invoke-virtual {p0, v0}, Landroid/view/View;->setAlpha(F)V

    :cond_2
    if-eqz p1, :cond_5

    if-eqz p2, :cond_3

    neg-float v1, v1

    .line 67
    :cond_3
    invoke-virtual {p1, v1}, Landroid/view/View;->setTranslationX(F)V

    if-eqz p2, :cond_4

    goto :goto_2

    :cond_4
    const/high16 p0, 0x420c0000    # 35.0f

    .line 68
    invoke-static {p3, p0, v2}, Lcom/exteragram/messenger/utils/ui/WindowTransitionAnimationHelper;->fade(FFF)F

    move-result p0

    sub-float/2addr v3, p0

    :goto_2
    invoke-virtual {p1, v3}, Landroid/view/View;->setAlpha(F)V

    :cond_5
    return-void
.end method

.method private static fade(FFF)F
    .locals 1

    const/high16 v0, 0x43e10000    # 450.0f

    mul-float/2addr p0, v0

    sub-float/2addr p0, p1

    div-float/2addr p0, p2

    .line 73
    invoke-static {p0}, Lorg/telegram/messenger/Utilities;->clamp01(F)F

    move-result p0

    return p0
.end method

.method public static getDuration()F
    .locals 3

    const/high16 v0, 0x3f800000    # 1.0f

    .line 43
    :try_start_0
    sget-object v1, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    .line 44
    invoke-virtual {v1}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v1

    const-string v2, "transition_animation_scale"

    .line 43
    invoke-static {v1, v2, v0}, Landroid/provider/Settings$Global;->getFloat(Landroid/content/ContentResolver;Ljava/lang/String;F)F

    move-result v1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move v1, v0

    :goto_0
    const/high16 v2, 0x43e10000    # 450.0f

    mul-float/2addr v1, v2

    .line 51
    invoke-static {v0, v1}, Ljava/lang/Math;->max(FF)F

    move-result v0

    return v0
.end method

.method public static getSlideDistance()F
    .locals 1

    const/high16 v0, 0x42c00000    # 96.0f

    .line 55
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    int-to-float v0, v0

    return v0
.end method
