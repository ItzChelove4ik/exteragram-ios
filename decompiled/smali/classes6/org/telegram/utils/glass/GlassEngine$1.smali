.class Lorg/telegram/utils/glass/GlassEngine$1;
.super Landroidx/viewpager/widget/ViewPager$SimpleOnPageChangeListener;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/telegram/utils/glass/GlassEngine;->addScrolledView(Landroidx/viewpager/widget/ViewPager;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/telegram/utils/glass/GlassEngine;


# direct methods
.method public constructor <init>(Lorg/telegram/utils/glass/GlassEngine;)V
    .locals 0

    .line 91
    iput-object p1, p0, Lorg/telegram/utils/glass/GlassEngine$1;->this$0:Lorg/telegram/utils/glass/GlassEngine;

    invoke-direct {p0}, Landroidx/viewpager/widget/ViewPager$SimpleOnPageChangeListener;-><init>()V

    return-void
.end method


# virtual methods
.method public onPageScrolled(IFI)V
    .locals 2

    .line 94
    iget-object p0, p0, Lorg/telegram/utils/glass/GlassEngine$1;->this$0:Lorg/telegram/utils/glass/GlassEngine;

    invoke-static {p0}, Lorg/telegram/utils/glass/GlassEngine;->-$$Nest$fgetscrollGenerationId(Lorg/telegram/utils/glass/GlassEngine;)J

    move-result-wide p1

    const-wide/16 v0, 0x1

    add-long/2addr p1, v0

    invoke-static {p0, p1, p2}, Lorg/telegram/utils/glass/GlassEngine;->-$$Nest$fputscrollGenerationId(Lorg/telegram/utils/glass/GlassEngine;J)V

    return-void
.end method
