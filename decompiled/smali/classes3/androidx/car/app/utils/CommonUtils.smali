.class public abstract Landroidx/car/app/utils/CommonUtils;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static isAutomotiveOS(Landroid/content/Context;)Z
    .locals 1

    .line 43
    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-virtual {p0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object p0

    const-string v0, "android.hardware.type.automotive"

    invoke-virtual {p0, v0}, Landroid/content/pm/PackageManager;->hasSystemFeature(Ljava/lang/String;)Z

    move-result p0

    return p0
.end method
