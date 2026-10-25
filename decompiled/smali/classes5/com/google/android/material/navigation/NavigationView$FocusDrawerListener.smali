.class Lcom/google/android/material/navigation/NavigationView$FocusDrawerListener;
.super Landroidx/drawerlayout/widget/DrawerLayout$SimpleDrawerListener;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/google/android/material/navigation/NavigationView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = "FocusDrawerListener"
.end annotation


# instance fields
.field private lastFocusedView:Ljava/lang/ref/WeakReference;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/WeakReference<",
            "Landroid/view/View;",
            ">;"
        }
    .end annotation
.end field

.field final synthetic this$0:Lcom/google/android/material/navigation/NavigationView;


# direct methods
.method private constructor <init>(Lcom/google/android/material/navigation/NavigationView;)V
    .locals 0

    .line 1184
    iput-object p1, p0, Lcom/google/android/material/navigation/NavigationView$FocusDrawerListener;->this$0:Lcom/google/android/material/navigation/NavigationView;

    invoke-direct {p0}, Landroidx/drawerlayout/widget/DrawerLayout$SimpleDrawerListener;-><init>()V

    const/4 p1, 0x0

    .line 1185
    iput-object p1, p0, Lcom/google/android/material/navigation/NavigationView$FocusDrawerListener;->lastFocusedView:Ljava/lang/ref/WeakReference;

    return-void
.end method

.method public synthetic constructor <init>(Lcom/google/android/material/navigation/NavigationView;Lcom/google/android/material/navigation/NavigationView$1;)V
    .locals 0

    .line 1184
    invoke-direct {p0, p1}, Lcom/google/android/material/navigation/NavigationView$FocusDrawerListener;-><init>(Lcom/google/android/material/navigation/NavigationView;)V

    return-void
.end method

.method private getCurrentFocus()Landroid/view/View;
    .locals 2

    .line 1195
    iget-object v0, p0, Lcom/google/android/material/navigation/NavigationView$FocusDrawerListener;->this$0:Lcom/google/android/material/navigation/NavigationView;

    invoke-virtual {v0}, Landroid/view/View;->hasFocus()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    return-object v1

    .line 1199
    :cond_0
    iget-object p0, p0, Lcom/google/android/material/navigation/NavigationView$FocusDrawerListener;->this$0:Lcom/google/android/material/navigation/NavigationView;

    invoke-virtual {p0}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object p0

    invoke-static {p0}, Lcom/google/android/material/internal/ContextUtils;->getActivity(Landroid/content/Context;)Landroid/app/Activity;

    move-result-object p0

    if-nez p0, :cond_1

    return-object v1

    .line 1204
    :cond_1
    invoke-virtual {p0}, Landroid/app/Activity;->getCurrentFocus()Landroid/view/View;

    move-result-object p0

    return-object p0
.end method

.method private saveLastFocusedView()V
    .locals 2

    .line 1188
    iget-object v0, p0, Lcom/google/android/material/navigation/NavigationView$FocusDrawerListener;->lastFocusedView:Ljava/lang/ref/WeakReference;

    if-nez v0, :cond_0

    .line 1189
    new-instance v0, Ljava/lang/ref/WeakReference;

    invoke-direct {p0}, Lcom/google/android/material/navigation/NavigationView$FocusDrawerListener;->getCurrentFocus()Landroid/view/View;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    iput-object v0, p0, Lcom/google/android/material/navigation/NavigationView$FocusDrawerListener;->lastFocusedView:Ljava/lang/ref/WeakReference;

    :cond_0
    return-void
.end method

.method private shouldAutoRequestFocus(Landroid/view/View;)Z
    .locals 0

    .line 1265
    invoke-virtual {p1}, Landroid/view/View;->isAttachedToWindow()Z

    move-result p0

    if-eqz p0, :cond_0

    invoke-virtual {p1}, Landroid/view/View;->isInTouchMode()Z

    move-result p0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method


# virtual methods
.method public clearLastFocusedView()V
    .locals 1

    const/4 v0, 0x0

    .line 1208
    iput-object v0, p0, Lcom/google/android/material/navigation/NavigationView$FocusDrawerListener;->lastFocusedView:Ljava/lang/ref/WeakReference;

    return-void
.end method

.method public onDrawerClosed(Landroid/view/View;)V
    .locals 1

    .line 1246
    iget-object v0, p0, Lcom/google/android/material/navigation/NavigationView$FocusDrawerListener;->this$0:Lcom/google/android/material/navigation/NavigationView;

    if-eq p1, v0, :cond_0

    goto :goto_0

    .line 1250
    :cond_0
    iget-object p1, p0, Lcom/google/android/material/navigation/NavigationView$FocusDrawerListener;->lastFocusedView:Ljava/lang/ref/WeakReference;

    if-nez p1, :cond_1

    :goto_0
    return-void

    .line 1254
    :cond_1
    invoke-virtual {p1}, Ljava/lang/ref/Reference;->get()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/view/View;

    if-eqz p1, :cond_3

    .line 1255
    invoke-direct {p0, p1}, Lcom/google/android/material/navigation/NavigationView$FocusDrawerListener;->shouldAutoRequestFocus(Landroid/view/View;)Z

    move-result v0

    if-nez v0, :cond_2

    goto :goto_1

    .line 1260
    :cond_2
    invoke-virtual {p1}, Landroid/view/View;->requestFocus()Z

    .line 1261
    invoke-virtual {p0}, Lcom/google/android/material/navigation/NavigationView$FocusDrawerListener;->clearLastFocusedView()V

    return-void

    .line 1256
    :cond_3
    :goto_1
    invoke-virtual {p0}, Lcom/google/android/material/navigation/NavigationView$FocusDrawerListener;->clearLastFocusedView()V

    return-void
.end method

.method public onDrawerOpened(Landroid/view/View;)V
    .locals 1

    .line 1231
    iget-object v0, p0, Lcom/google/android/material/navigation/NavigationView$FocusDrawerListener;->this$0:Lcom/google/android/material/navigation/NavigationView;

    if-eq p1, v0, :cond_0

    goto :goto_0

    .line 1237
    :cond_0
    invoke-direct {p0}, Lcom/google/android/material/navigation/NavigationView$FocusDrawerListener;->saveLastFocusedView()V

    .line 1239
    invoke-direct {p0, p1}, Lcom/google/android/material/navigation/NavigationView$FocusDrawerListener;->shouldAutoRequestFocus(Landroid/view/View;)Z

    move-result p0

    if-eqz p0, :cond_1

    .line 1240
    invoke-virtual {p1}, Landroid/view/View;->requestFocus()Z

    :cond_1
    :goto_0
    return-void
.end method

.method public onDrawerSlide(Landroid/view/View;F)V
    .locals 0

    .line 1213
    iget-object p2, p0, Lcom/google/android/material/navigation/NavigationView$FocusDrawerListener;->this$0:Lcom/google/android/material/navigation/NavigationView;

    if-eq p1, p2, :cond_0

    return-void

    .line 1217
    :cond_0
    invoke-virtual {p1}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    .line 1226
    invoke-direct {p0}, Lcom/google/android/material/navigation/NavigationView$FocusDrawerListener;->saveLastFocusedView()V

    return-void
.end method
