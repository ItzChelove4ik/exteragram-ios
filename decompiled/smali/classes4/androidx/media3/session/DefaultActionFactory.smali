.class abstract Landroidx/media3/session/DefaultActionFactory;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static getKeyEvent(Landroid/content/Intent;)Landroid/view/KeyEvent;
    .locals 2

    .line 63
    invoke-virtual {p0}, Landroid/content/Intent;->getExtras()Landroid/os/Bundle;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 64
    const-string v0, "android.intent.extra.KEY_EVENT"

    invoke-virtual {p0, v0}, Landroid/os/BaseBundle;->containsKey(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 65
    invoke-virtual {p0, v0}, Landroid/os/Bundle;->getParcelable(Ljava/lang/String;)Landroid/os/Parcelable;

    move-result-object p0

    check-cast p0, Landroid/view/KeyEvent;

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method
