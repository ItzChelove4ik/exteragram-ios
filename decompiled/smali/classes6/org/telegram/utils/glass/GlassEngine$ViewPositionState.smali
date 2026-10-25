.class Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/glass/GlassEngine;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "ViewPositionState"
.end annotation


# instance fields
.field private final drawable:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

.field private final drawableBoundsRelativeRoot:Landroid/graphics/RectF;

.field private final drawableBoundsRelativeRootWithOutset:Landroid/graphics/RectF;

.field private final drawableBoundsRelativeView:Landroid/graphics/RectF;

.field private invalidated:Z

.field private final view:Landroid/view/View;

.field private final viewPositionRelativeRoot:Landroid/graphics/RectF;

.field private visible:Z


# direct methods
.method public static bridge synthetic -$$Nest$fgetdrawable(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->drawable:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetdrawableBoundsRelativeRoot(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/graphics/RectF;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->drawableBoundsRelativeRoot:Landroid/graphics/RectF;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetdrawableBoundsRelativeRootWithOutset(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/graphics/RectF;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->drawableBoundsRelativeRootWithOutset:Landroid/graphics/RectF;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetdrawableBoundsRelativeView(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/graphics/RectF;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->drawableBoundsRelativeView:Landroid/graphics/RectF;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetinvalidated(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->invalidated:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fgetview(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/view/View;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->view:Landroid/view/View;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetviewPositionRelativeRoot(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Landroid/graphics/RectF;
    .locals 0

    .line 0
    iget-object p0, p0, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->viewPositionRelativeRoot:Landroid/graphics/RectF;

    return-object p0
.end method

.method public static bridge synthetic -$$Nest$fgetvisible(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;)Z
    .locals 0

    .line 0
    iget-boolean p0, p0, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->visible:Z

    return p0
.end method

.method public static bridge synthetic -$$Nest$fputinvalidated(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->invalidated:Z

    return-void
.end method

.method public static bridge synthetic -$$Nest$fputvisible(Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;Z)V
    .locals 0

    .line 0
    iput-boolean p1, p0, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->visible:Z

    return-void
.end method

.method private constructor <init>(Landroid/view/View;Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;)V
    .locals 1

    .line 296
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 289
    new-instance v0, Landroid/graphics/RectF;

    invoke-direct {v0}, Landroid/graphics/RectF;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->viewPositionRelativeRoot:Landroid/graphics/RectF;

    .line 290
    new-instance v0, Landroid/graphics/RectF;

    invoke-direct {v0}, Landroid/graphics/RectF;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->drawableBoundsRelativeView:Landroid/graphics/RectF;

    .line 291
    new-instance v0, Landroid/graphics/RectF;

    invoke-direct {v0}, Landroid/graphics/RectF;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->drawableBoundsRelativeRoot:Landroid/graphics/RectF;

    .line 292
    new-instance v0, Landroid/graphics/RectF;

    invoke-direct {v0}, Landroid/graphics/RectF;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->drawableBoundsRelativeRootWithOutset:Landroid/graphics/RectF;

    .line 297
    iput-object p1, p0, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->view:Landroid/view/View;

    .line 298
    iput-object p2, p0, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;->drawable:Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;

    return-void
.end method

.method public synthetic constructor <init>(Landroid/view/View;Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;Lorg/telegram/utils/glass/GlassEngine-IA;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/glass/GlassEngine$ViewPositionState;-><init>(Landroid/view/View;Lorg/telegram/ui/Components/blur3/drawable/BlurredBackgroundDrawable;)V

    return-void
.end method
