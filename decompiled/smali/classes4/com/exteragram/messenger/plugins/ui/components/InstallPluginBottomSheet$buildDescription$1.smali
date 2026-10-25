.class public final Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$buildDescription$1;
.super Lorg/telegram/ui/Components/EffectsTextView;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->buildDescription(Landroid/app/Activity;)Landroid/view/View;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\u0008\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0014J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0008\u001a\u00020\tH\u0014\u00a8\u0006\n"
    }
    d2 = {
        "com/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$buildDescription$1",
        "Lorg/telegram/ui/Components/EffectsTextView;",
        "onMeasure",
        "",
        "widthMeasureSpec",
        "",
        "heightMeasureSpec",
        "onDraw",
        "canvas",
        "Landroid/graphics/Canvas;",
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


# instance fields
.field final synthetic this$0:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;


# direct methods
.method public constructor <init>(Landroid/app/Activity;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)V
    .locals 0

    iput-object p2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$buildDescription$1;->this$0:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;

    .line 751
    invoke-direct {p0, p1}, Lorg/telegram/ui/Components/EffectsTextView;-><init>(Landroid/content/Context;)V

    return-void
.end method


# virtual methods
.method public onDraw(Landroid/graphics/Canvas;)V
    .locals 1

    .line 758
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$buildDescription$1;->this$0:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;

    invoke-static {v0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->access$getDescriptionText$p(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->beforeDraw(Landroid/graphics/Canvas;)I

    move-result v0

    .line 759
    invoke-super {p0, p1}, Lorg/telegram/ui/Components/EffectsTextView;->onDraw(Landroid/graphics/Canvas;)V

    .line 760
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$buildDescription$1;->this$0:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;

    invoke-static {p0}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->access$getDescriptionText$p(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    move-result-object p0

    invoke-virtual {p0, p1, v0}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->afterDraw(Landroid/graphics/Canvas;I)V

    return-void
.end method

.method public onMeasure(II)V
    .locals 1

    .line 753
    invoke-super {p0, p1, p2}, Landroid/view/View;->onMeasure(II)V

    .line 754
    invoke-virtual {p0}, Landroid/view/View;->getMeasuredWidth()I

    move-result p1

    iget-object p2, p0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$buildDescription$1;->this$0:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;

    invoke-static {p2}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;->access$getDescriptionText$p(Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;)Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;

    move-result-object p2

    invoke-virtual {p0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v0

    invoke-virtual {p2, v0}, Lcom/exteragram/messenger/plugins/ui/components/TranslatableText;->getHeight(I)I

    move-result p2

    invoke-virtual {p0, p1, p2}, Landroid/view/View;->setMeasuredDimension(II)V

    return-void
.end method
