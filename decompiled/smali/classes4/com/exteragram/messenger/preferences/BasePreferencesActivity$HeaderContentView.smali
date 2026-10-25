.class final Lcom/exteragram/messenger/preferences/BasePreferencesActivity$HeaderContentView;
.super Landroid/widget/FrameLayout;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/ui/ActionBar/Theme$Colorable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x11
    name = "HeaderContentView"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0003\n\u0002\u0010\u000b\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0002\u0008\u0002\u0008\u0082\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0004\u0008\u0005\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\u00082\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0014J0\u0010\u000c\u001a\u00020\u00082\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\nH\u0014J\u0010\u0010\u0013\u001a\u00020\u00082\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J\u0008\u0010\u0016\u001a\u00020\u0008H\u0016\u00a8\u0006\u0017"
    }
    d2 = {
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity$HeaderContentView;",
        "Landroid/widget/FrameLayout;",
        "Lorg/telegram/ui/ActionBar/Theme$Colorable;",
        "context",
        "Landroid/content/Context;",
        "<init>",
        "(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Landroid/content/Context;)V",
        "onMeasure",
        "",
        "widthMeasureSpec",
        "",
        "heightMeasureSpec",
        "onLayout",
        "changed",
        "",
        "left",
        "top",
        "right",
        "bottom",
        "dispatchDraw",
        "canvas",
        "Landroid/graphics/Canvas;",
        "updateColors",
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
.field final synthetic this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Landroid/content/Context;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            ")V"
        }
    .end annotation

    .line 185
    iput-object p1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$HeaderContentView;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-direct {p0, p2}, Landroid/widget/FrameLayout;-><init>(Landroid/content/Context;)V

    return-void
.end method


# virtual methods
.method public dispatchDraw(Landroid/graphics/Canvas;)V
    .locals 3

    .line 198
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x1f

    if-lt v0, v1, :cond_1

    iget-object v0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$HeaderContentView;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->access$getScrollableViewNoiseSuppressor$p(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;

    move-result-object v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$HeaderContentView;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->access$getIBlur3SourceGlass$p(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 199
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$HeaderContentView;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->access$blur3_InvalidateBlur(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V

    .line 201
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$HeaderContentView;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->access$getIBlur3SourceGlass$p(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;->inRecording()Z

    move-result v0

    if-nez v0, :cond_1

    .line 202
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$HeaderContentView;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->access$getIBlur3SourceGlass$p(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;

    move-result-object v0

    invoke-virtual {p0}, Landroid/view/View;->getMeasuredWidth()I

    move-result v1

    invoke-virtual {p0}, Landroid/view/View;->getMeasuredHeight()I

    move-result v2

    invoke-virtual {v0, v1, v2}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;->beginRecording(II)Landroid/graphics/RecordingCanvas;

    move-result-object v0

    .line 203
    iget-object v1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$HeaderContentView;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    sget v2, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundGray:I

    invoke-virtual {v1, v2}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/graphics/RecordingCanvas;->drawColor(I)V

    .line 204
    invoke-static {}, Lorg/telegram/messenger/SharedConfig;->chatBlurEnabled()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 205
    iget-object v1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$HeaderContentView;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-static {v1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->access$getScrollableViewNoiseSuppressor$p(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;

    move-result-object v1

    const/4 v2, -0x2

    invoke-virtual {v1, v0, v2}, Lorg/telegram/ui/Components/blur3/DownscaleScrollableNoiseSuppressor;->draw(Landroid/graphics/Canvas;I)V

    .line 206
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$HeaderContentView;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-static {v0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->access$getIBlur3SourceGlass$p(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceRenderNode;->endRecording()V

    .line 209
    :cond_1
    invoke-super {p0, p1}, Landroid/view/View;->dispatchDraw(Landroid/graphics/Canvas;)V

    return-void
.end method

.method public onLayout(ZIIII)V
    .locals 0

    .line 193
    invoke-super/range {p0 .. p5}, Landroid/widget/FrameLayout;->onLayout(ZIIII)V

    .line 194
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$HeaderContentView;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    const/4 p1, 0x0

    invoke-static {p0, p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->access$updateHeaderFade(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Z)V

    return-void
.end method

.method public onMeasure(II)V
    .locals 0

    .line 188
    invoke-super {p0, p1, p2}, Landroid/widget/FrameLayout;->onMeasure(II)V

    .line 189
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$HeaderContentView;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-static {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->access$updateTopFade(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V

    return-void
.end method

.method public updateColors()V
    .locals 2

    .line 213
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$HeaderContentView;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundGray:I

    invoke-virtual {v0, v1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getThemedColor(I)I

    move-result v0

    .line 214
    invoke-virtual {p0, v0}, Landroid/view/View;->setBackgroundColor(I)V

    .line 215
    iget-object v1, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$HeaderContentView;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-static {v1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->access$getIBlur3SourceColor$p(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;

    move-result-object v1

    invoke-virtual {v1, v0}, Lorg/telegram/ui/Components/blur3/source/BlurredBackgroundSourceColor;->setColor(I)V

    .line 216
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/BasePreferencesActivity$HeaderContentView;->this$0:Lcom/exteragram/messenger/preferences/BasePreferencesActivity;

    invoke-static {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->access$updateTopFade(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)V

    return-void
.end method
