.class public abstract Landroidx/car/app/SessionInfoIntentEncoder;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static containsSessionInfo(Landroid/content/Intent;)Z
    .locals 1

    .line 82
    invoke-virtual {p0}, Landroid/content/Intent;->getExtras()Landroid/os/Bundle;

    move-result-object p0

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return p0

    .line 87
    :cond_0
    const-string v0, "androidx.car.app.extra.SESSION_INFO_BUNDLE"

    invoke-virtual {p0, v0}, Landroid/os/BaseBundle;->containsKey(Ljava/lang/String;)Z

    move-result p0

    return p0
.end method

.method public static decode(Landroid/content/Intent;)Landroidx/car/app/SessionInfo;
    .locals 2

    .line 67
    invoke-virtual {p0}, Landroid/content/Intent;->getExtras()Landroid/os/Bundle;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 74
    const-string v0, "androidx.car.app.extra.SESSION_INFO_BUNDLE"

    invoke-virtual {p0, v0}, Landroid/os/Bundle;->getBundle(Ljava/lang/String;)Landroid/os/Bundle;

    move-result-object p0

    .line 75
    const-string v0, "display-type"

    invoke-virtual {p0, v0}, Landroid/os/BaseBundle;->getInt(Ljava/lang/String;)I

    move-result v0

    .line 76
    const-string/jumbo v1, "session-id"

    invoke-virtual {p0, v1}, Landroid/os/BaseBundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 77
    new-instance v1, Landroidx/car/app/SessionInfo;

    invoke-direct {v1, v0, p0}, Landroidx/car/app/SessionInfo;-><init>(ILjava/lang/String;)V

    return-object v1

    .line 69
    :cond_0
    const-string p0, "Expected the SessionInfo to be encoded in the bind intent extras, but the extras were null."

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method
