.class Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView$1;
.super Landroid/text/style/CharacterStyle;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->colorizeLinks(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;)V
    .locals 0

    .line 199
    iput-object p1, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView$1;->this$0:Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    invoke-direct {p0}, Landroid/text/style/CharacterStyle;-><init>()V

    return-void
.end method


# virtual methods
.method public updateDrawState(Landroid/text/TextPaint;)V
    .locals 2

    .line 202
    iget-object p0, p0, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView$1;->this$0:Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;

    invoke-static {p0}, Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;->-$$Nest$fgetlinkColor(Lcom/exteragram/messenger/appicons/ui/components/AppIconHeroView;)I

    move-result p0

    invoke-virtual {p1}, Landroid/graphics/Paint;->getAlpha()I

    move-result v0

    int-to-float v0, v0

    const/high16 v1, 0x437f0000    # 255.0f

    div-float/2addr v0, v1

    invoke-static {p0, v0}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result p0

    invoke-virtual {p1, p0}, Landroid/graphics/Paint;->setColor(I)V

    return-void
.end method
