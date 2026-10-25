.class public abstract Lcom/exteragram/messenger/components/BlendedReplySpansKt;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0016\n\u0000\n\u0002\u0010\u0014\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u0007\n\u0000\u001a\u0012\u0010\u0003\u001a\u00020\u00042\u0008\u0008\u0002\u0010\u0005\u001a\u00020\u0006H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"
    }
    d2 = {
        "baseHsv",
        "",
        "nameHsv",
        "blendedReplyColor",
        "",
        "strength",
        "",
        "TMessagesProj"
    }
    k = 0x2
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation


# static fields
.field private static final baseHsv:[F

.field private static final nameHsv:[F


# direct methods
.method static constructor <clinit>()V
    .locals 2

    const/4 v0, 0x3

    .line 40
    new-array v1, v0, [F

    sput-object v1, Lcom/exteragram/messenger/components/BlendedReplySpansKt;->baseHsv:[F

    .line 41
    new-array v0, v0, [F

    sput-object v0, Lcom/exteragram/messenger/components/BlendedReplySpansKt;->nameHsv:[F

    return-void
.end method

.method private static final blendedReplyColor(F)I
    .locals 6

    .line 44
    sget-object v0, Lorg/telegram/ui/ActionBar/Theme;->chat_replyTextPaint:Lcom/exteragram/messenger/utils/ui/TextPaint;

    invoke-virtual {v0}, Landroid/graphics/Paint;->getColor()I

    move-result v0

    .line 45
    sget-object v1, Lorg/telegram/ui/ActionBar/Theme;->chat_replyNamePaint:Lcom/exteragram/messenger/utils/ui/TextPaint;

    invoke-virtual {v1}, Landroid/graphics/Paint;->getColor()I

    move-result v1

    .line 47
    sget-object v2, Lcom/exteragram/messenger/components/BlendedReplySpansKt;->baseHsv:[F

    invoke-static {v0, v2}, Landroid/graphics/Color;->colorToHSV(I[F)V

    .line 48
    sget-object v3, Lcom/exteragram/messenger/components/BlendedReplySpansKt;->nameHsv:[F

    invoke-static {v1, v3}, Landroid/graphics/Color;->colorToHSV(I[F)V

    const/4 v1, 0x2

    .line 50
    aget v4, v2, v1

    const v5, 0x3eb33333    # 0.35f

    cmpg-float v4, v4, v5

    if-gez v4, :cond_0

    const/high16 v4, 0x3f000000    # 0.5f

    aput v4, v2, v1

    :cond_0
    const/4 v1, 0x0

    .line 52
    aget v4, v3, v1

    aput v4, v2, v1

    const/4 v1, 0x1

    .line 53
    aget v3, v3, v1

    mul-float/2addr v3, p0

    const/4 p0, 0x0

    const/high16 v4, 0x3f800000    # 1.0f

    invoke-static {v3, p0, v4}, Lkotlin/ranges/RangesKt;->coerceIn(FFF)F

    move-result p0

    aput p0, v2, v1

    .line 55
    invoke-static {v0}, Landroid/graphics/Color;->alpha(I)I

    move-result p0

    invoke-static {p0, v2}, Landroid/graphics/Color;->HSVToColor(I[F)I

    move-result p0

    return p0
.end method

.method public static synthetic blendedReplyColor$default(FILjava/lang/Object;)I
    .locals 0

    and-int/lit8 p1, p1, 0x1

    if-eqz p1, :cond_0

    const/high16 p0, 0x3f400000    # 0.75f

    .line 43
    :cond_0
    invoke-static {p0}, Lcom/exteragram/messenger/components/BlendedReplySpansKt;->blendedReplyColor(F)I

    move-result p0

    return p0
.end method
