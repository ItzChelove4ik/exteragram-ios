.class public abstract Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final MATERIAL3_ICON_CHECK:[F

.field private static final MATERIAL3_ICON_CROSS:[F

.field private static final MATERIAL3_THUMB_INTERPOLATOR:Landroid/view/animation/Interpolator;

.field private static final colorLab:[D


# direct methods
.method static constructor <clinit>()V
    .locals 2

    const/16 v0, 0x8

    .line 52
    new-array v1, v0, [F

    fill-array-data v1, :array_0

    sput-object v1, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->MATERIAL3_ICON_CROSS:[F

    .line 53
    new-array v0, v0, [F

    fill-array-data v0, :array_1

    sput-object v0, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->MATERIAL3_ICON_CHECK:[F

    .line 73
    new-instance v0, Landroid/view/animation/AccelerateDecelerateInterpolator;

    invoke-direct {v0}, Landroid/view/animation/AccelerateDecelerateInterpolator;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->MATERIAL3_THUMB_INTERPOLATOR:Landroid/view/animation/Interpolator;

    const/4 v0, 0x3

    .line 82
    new-array v0, v0, [D

    sput-object v0, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->colorLab:[D

    return-void

    nop

    :array_0
    .array-data 4
        0x40c00000    # 6.0f
        0x40c00000    # 6.0f
        0x41900000    # 18.0f
        0x41900000    # 18.0f
        0x40c00000    # 6.0f
        0x41900000    # 18.0f
        0x41900000    # 18.0f
        0x40c00000    # 6.0f
    .end array-data

    :array_1
    .array-data 4
        0x40900000    # 4.5f
        0x4151999a    # 13.1f
        0x41100000    # 9.0f
        0x418ccccd    # 17.6f
        0x41100000    # 9.0f
        0x418ccccd    # 17.6f
        0x419e6666    # 19.8f
        0x40d9999a    # 6.8f
    .end array-data
.end method

.method public static createThumbPressedAnimator(Landroid/view/View;)Lme/vkryl/android/animator/BoolAnimator;
    .locals 4

    .line 112
    new-instance v0, Lme/vkryl/android/animator/BoolAnimator;

    sget-object v1, Lorg/telegram/ui/Components/CubicBezierInterpolator;->Standard:Landroid/view/animation/Interpolator;

    const-wide/16 v2, 0x64

    invoke-direct {v0, p0, v1, v2, v3}, Lme/vkryl/android/animator/BoolAnimator;-><init>(Landroid/view/View;Landroid/view/animation/Interpolator;J)V

    return-object v0
.end method

.method public static createThumbSpring(Ljava/lang/Object;Landroidx/dynamicanimation/animation/FloatPropertyCompat;)Landroidx/dynamicanimation/animation/SpringAnimation;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<K:",
            "Ljava/lang/Object;",
            ">(TK;",
            "Landroidx/dynamicanimation/animation/FloatPropertyCompat<",
            "TK;>;)",
            "Landroidx/dynamicanimation/animation/SpringAnimation;"
        }
    .end annotation

    .line 104
    new-instance v0, Landroidx/dynamicanimation/animation/SpringAnimation;

    invoke-direct {v0, p0, p1}, Landroidx/dynamicanimation/animation/SpringAnimation;-><init>(Ljava/lang/Object;Landroidx/dynamicanimation/animation/FloatPropertyCompat;)V

    const p0, 0x3a83126f    # 0.001f

    .line 105
    invoke-virtual {v0, p0}, Landroidx/dynamicanimation/animation/DynamicAnimation;->setMinimumVisibleChange(F)Landroidx/dynamicanimation/animation/DynamicAnimation;

    move-result-object p0

    check-cast p0, Landroidx/dynamicanimation/animation/SpringAnimation;

    new-instance p1, Landroidx/dynamicanimation/animation/SpringForce;

    invoke-direct {p1}, Landroidx/dynamicanimation/animation/SpringForce;-><init>()V

    const v0, 0x3f266666    # 0.65f

    .line 107
    invoke-virtual {p1, v0}, Landroidx/dynamicanimation/animation/SpringForce;->setDampingRatio(F)Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object p1

    const/high16 v0, 0x43ff0000    # 510.0f

    .line 108
    invoke-virtual {p1, v0}, Landroidx/dynamicanimation/animation/SpringForce;->setStiffness(F)Landroidx/dynamicanimation/animation/SpringForce;

    move-result-object p1

    .line 106
    invoke-virtual {p0, p1}, Landroidx/dynamicanimation/animation/SpringAnimation;->setSpring(Landroidx/dynamicanimation/animation/SpringForce;)Landroidx/dynamicanimation/animation/SpringAnimation;

    move-result-object p0

    return-object p0
.end method

.method public static getClockHandLength(Z)F
    .locals 0

    if-eqz p0, :cond_0

    const/high16 p0, 0x41080000    # 8.5f

    goto :goto_0

    :cond_0
    const p0, 0x40d9999a    # 6.8f

    .line 222
    :goto_0
    invoke-static {p0}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->iconGrid(F)F

    move-result p0

    return p0
.end method

.method public static getIconStrokeWidth()F
    .locals 1

    const/high16 v0, 0x40000000    # 2.0f

    .line 210
    invoke-static {v0}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->iconGrid(F)F

    move-result v0

    return v0
.end method

.method public static getOverlayPadding()I
    .locals 1

    .line 96
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->isMaterial3SwitchStyle()Z

    move-result v0

    if-eqz v0, :cond_0

    const/high16 v0, 0x40a00000    # 5.0f

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    return v0

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method public static getSelectedThumbColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I
    .locals 2

    .line 145
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->isMaterial3SwitchStyle()Z

    move-result v0

    if-eqz v0, :cond_0

    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x1f

    if-lt v0, v1, :cond_0

    invoke-static {p1}, Lorg/telegram/ui/ActionBar/Theme;->isCurrentThemeMonet(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 146
    sget p0, Lorg/telegram/ui/ActionBar/Theme;->key_switchTrackBlueThumbChecked:I

    invoke-static {p0, p1}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    return p0

    .line 148
    :cond_0
    invoke-static {p0, p1}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    return p0
.end method

.method public static getStateLayerRadius()I
    .locals 1

    .line 100
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->isMaterial3SwitchStyle()Z

    move-result v0

    if-eqz v0, :cond_0

    const/high16 v0, 0x41a00000    # 20.0f

    invoke-static {v0}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->scaled(F)F

    move-result v0

    float-to-int v0, v0

    return v0

    :cond_0
    const/high16 v0, 0x41900000    # 18.0f

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v0

    return v0
.end method

.method public static getThumbCenterX(IF)F
    .locals 1

    int-to-float p0, p0

    const/high16 v0, 0x40000000    # 2.0f

    div-float/2addr p0, v0

    mul-float/2addr p1, v0

    const/high16 v0, 0x3f800000    # 1.0f

    sub-float/2addr p1, v0

    const/high16 v0, 0x41200000    # 10.0f

    .line 169
    invoke-static {v0}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->scaled(F)F

    move-result v0

    mul-float/2addr p1, v0

    add-float/2addr p0, p1

    return p0
.end method

.method public static getThumbIconScale(II)F
    .locals 1

    const v0, 0x41555555

    .line 202
    invoke-static {v0}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->scaled(F)F

    move-result v0

    invoke-static {p0, p1}, Ljava/lang/Math;->max(II)I

    move-result p0

    int-to-float p0, p0

    div-float/2addr v0, p0

    return v0
.end method

.method public static getTrackOutlineWidth()F
    .locals 1

    const/high16 v0, 0x40000000    # 2.0f

    .line 122
    invoke-static {v0}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->scaled(F)F

    move-result v0

    return v0
.end method

.method public static getUnselectedThumbColor(IILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I
    .locals 2

    .line 133
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->isMaterial3SwitchStyle()Z

    move-result v0

    if-nez v0, :cond_0

    .line 134
    invoke-static {p1, p2}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    return p0

    :cond_0
    const/high16 p1, 0x42480000    # 50.0f

    const/high16 v0, 0x42700000    # 60.0f

    .line 136
    invoke-static {p0, p2, p1, v0}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->trackAtTone(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;FF)I

    move-result p1

    .line 137
    invoke-static {p2}, Lorg/telegram/ui/ActionBar/Theme;->isCurrentThemeMonet(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Z

    move-result v0

    if-eqz v0, :cond_1

    return p1

    :cond_1
    const/high16 v0, 0x42b40000    # 90.0f

    const/high16 v1, 0x41b00000    # 22.0f

    .line 140
    invoke-static {p0, p2, v0, v1}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->trackAtTone(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;FF)I

    move-result p0

    .line 141
    invoke-static {p2}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->isLightSurface(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Z

    move-result p2

    if-eqz p2, :cond_2

    const p2, 0x3f0ccccd    # 0.55f

    goto :goto_0

    :cond_2
    const p2, 0x3f4ccccd    # 0.8f

    :goto_0
    invoke-static {p0, p1, p2}, Landroidx/core/graphics/ColorUtils;->blendARGB(IIF)I

    move-result p0

    return p0
.end method

.method public static getUnselectedTrackColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I
    .locals 2

    .line 126
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->isMaterial3SwitchStyle()Z

    move-result v0

    if-nez v0, :cond_0

    .line 127
    invoke-static {p0, p1}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    return p0

    :cond_0
    const/high16 v0, 0x42b40000    # 90.0f

    const/high16 v1, 0x41b00000    # 22.0f

    .line 129
    invoke-static {p0, p1, v0, v1}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->trackAtTone(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;FF)I

    move-result p0

    return p0
.end method

.method private static iconGrid(F)F
    .locals 1

    const/high16 v0, 0x41800000    # 16.0f

    mul-float/2addr p0, v0

    const/high16 v0, 0x41c00000    # 24.0f

    div-float/2addr p0, v0

    .line 206
    invoke-static {p0}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->scaled(F)F

    move-result p0

    return p0
.end method

.method private static isLightSurface(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Z
    .locals 5

    .line 152
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-static {v0, p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    sget-object v0, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->colorLab:[D

    invoke-static {p0, v0}, Landroidx/core/graphics/ColorUtils;->colorToLAB(I[D)V

    const/4 p0, 0x0

    .line 153
    aget-wide v1, v0, p0

    const-wide/high16 v3, 0x4049000000000000L    # 50.0

    cmpl-double v0, v1, v3

    if-lez v0, :cond_0

    const/4 p0, 0x1

    :cond_0
    return p0
.end method

.method public static isMaterial3SwitchStyle()Z
    .locals 1

    .line 88
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getNewSwitchStyle()Z

    move-result v0

    return v0
.end method

.method private static scaled(F)F
    .locals 1

    const/high16 v0, 0x3f500000    # 0.8125f

    mul-float/2addr p0, v0

    .line 92
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result p0

    return p0
.end method

.method public static setCheckIconLines([FFFF)V
    .locals 3

    .line 214
    invoke-static {p3}, Lorg/telegram/messenger/Utilities;->clamp01(F)F

    move-result p3

    const/4 v0, 0x0

    .line 215
    :goto_0
    array-length v1, p0

    if-ge v0, v1, :cond_1

    .line 216
    sget-object v1, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->MATERIAL3_ICON_CROSS:[F

    aget v1, v1, v0

    sget-object v2, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->MATERIAL3_ICON_CHECK:[F

    aget v2, v2, v0

    invoke-static {v1, v2, p3}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result v1

    const/high16 v2, 0x41400000    # 12.0f

    sub-float/2addr v1, v2

    invoke-static {v1}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->iconGrid(F)F

    move-result v1

    .line 217
    rem-int/lit8 v2, v0, 0x2

    if-nez v2, :cond_0

    move v2, p1

    goto :goto_1

    :cond_0
    move v2, p2

    :goto_1
    add-float/2addr v1, v2

    aput v1, p0, v0

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_1
    return-void
.end method

.method public static setThumbBounds(Landroid/graphics/RectF;FFFZFF)V
    .locals 6

    const/high16 v0, 0x41000000    # 8.0f

    const/high16 v1, 0x41400000    # 12.0f

    .line 173
    invoke-static {v0, v1, p5}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result p5

    const/high16 v0, 0x40000000    # 2.0f

    .line 174
    invoke-static {p3}, Lorg/telegram/messenger/Utilities;->clamp01(F)F

    move-result p3

    mul-float/2addr p3, v0

    const/high16 v0, 0x3f800000    # 1.0f

    sub-float p3, v0, p3

    float-to-double v2, p3

    invoke-static {v2, v3}, Ljava/lang/Math;->acos(D)D

    move-result-wide v2

    const-wide v4, 0x400921fb54442d18L    # Math.PI

    div-double/2addr v2, v4

    double-to-float p3, v2

    if-eqz p4, :cond_0

    goto :goto_0

    :cond_0
    sub-float p3, v0, p3

    :goto_0
    if-eqz p4, :cond_1

    const v2, 0x3f19999a    # 0.6f

    goto :goto_1

    :cond_1
    const v2, 0x3ecccccd    # 0.4f

    :goto_1
    if-eqz p4, :cond_2

    move v3, p5

    goto :goto_2

    :cond_2
    move v3, v1

    :goto_2
    if-eqz p4, :cond_3

    goto :goto_3

    :cond_3
    move v1, p5

    :goto_3
    cmpg-float p4, p3, v2

    const/high16 p5, 0x40a00000    # 5.0f

    const/high16 v4, 0x41300000    # 11.0f

    if-gez p4, :cond_4

    .line 183
    sget-object p4, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->MATERIAL3_THUMB_INTERPOLATOR:Landroid/view/animation/Interpolator;

    div-float/2addr p3, v2

    invoke-interface {p4, p3}, Landroid/animation/TimeInterpolator;->getInterpolation(F)F

    move-result p3

    .line 184
    invoke-static {v3, v4, p3}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result p4

    :goto_4
    mul-float/2addr p3, p5

    goto :goto_5

    .line 187
    :cond_4
    sget-object p4, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->MATERIAL3_THUMB_INTERPOLATOR:Landroid/view/animation/Interpolator;

    sub-float/2addr p3, v2

    sub-float v2, v0, v2

    div-float/2addr p3, v2

    invoke-interface {p4, p3}, Landroid/animation/TimeInterpolator;->getInterpolation(F)F

    move-result p3

    .line 188
    invoke-static {v4, v1, p3}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result p4

    sub-float p3, v0, p3

    goto :goto_4

    :goto_5
    const/4 p5, 0x0

    cmpl-float p5, p6, p5

    if-lez p5, :cond_5

    const/high16 p5, 0x41600000    # 14.0f

    .line 192
    invoke-static {p4, p5, p6}, Lorg/telegram/messenger/AndroidUtilities;->lerp(FFF)F

    move-result p4

    sub-float/2addr v0, p6

    mul-float/2addr p3, v0

    .line 196
    :cond_5
    invoke-static {p4}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->scaled(F)F

    move-result p4

    .line 197
    invoke-static {p3}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->scaled(F)F

    move-result p3

    sub-float p5, p1, p3

    sub-float/2addr p5, p4

    sub-float p6, p2, p4

    add-float/2addr p1, p3

    add-float/2addr p1, p4

    add-float/2addr p2, p4

    .line 198
    invoke-virtual {p0, p5, p6, p1, p2}, Landroid/graphics/RectF;->set(FFFF)V

    return-void
.end method

.method public static setTrackBounds(Landroid/graphics/RectF;II)V
    .locals 4

    const/high16 v0, 0x41d00000    # 26.0f

    .line 116
    invoke-static {v0}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->scaled(F)F

    move-result v0

    const/high16 v1, 0x41800000    # 16.0f

    .line 117
    invoke-static {v1}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->scaled(F)F

    move-result v1

    int-to-float p1, p1

    const/high16 v2, 0x40000000    # 2.0f

    div-float/2addr p1, v2

    sub-float v3, p1, v0

    int-to-float p2, p2

    div-float/2addr p2, v2

    sub-float v2, p2, v1

    add-float/2addr p1, v0

    add-float/2addr p2, v1

    .line 118
    invoke-virtual {p0, v3, v2, p1, p2}, Landroid/graphics/RectF;->set(FFFF)V

    return-void
.end method

.method private static trackAtTone(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;FF)I
    .locals 8

    .line 157
    invoke-static {p1}, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->isLightSurface(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    move p2, p3

    .line 158
    :goto_0
    invoke-static {p0, p1}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    sget-object p1, Lcom/exteragram/messenger/utils/ui/SwitchUiHelper;->colorLab:[D

    invoke-static {p0, p1}, Landroidx/core/graphics/ColorUtils;->colorToLAB(I[D)V

    const/4 p0, 0x1

    .line 159
    aget-wide v0, p1, p0

    const/4 p3, 0x2

    aget-wide v2, p1, p3

    invoke-static {v0, v1, v2, v3}, Ljava/lang/Math;->hypot(DD)D

    move-result-wide v0

    const-wide/high16 v2, 0x4040000000000000L    # 32.0

    cmpl-double v4, v0, v2

    if-lez v4, :cond_1

    div-double/2addr v2, v0

    .line 162
    aget-wide v0, p1, p0

    mul-double/2addr v0, v2

    aput-wide v0, p1, p0

    .line 163
    aget-wide v0, p1, p3

    mul-double/2addr v0, v2

    aput-wide v0, p1, p3

    :cond_1
    float-to-double v2, p2

    .line 165
    aget-wide v4, p1, p0

    aget-wide v6, p1, p3

    invoke-static/range {v2 .. v7}, Landroidx/core/graphics/ColorUtils;->LABToColor(DDD)I

    move-result p0

    return p0
.end method
