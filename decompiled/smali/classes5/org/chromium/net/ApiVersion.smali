.class public abstract Lorg/chromium/net/ApiVersion;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static getApiLevel()I
    .locals 1

    const/4 v0, 0x3

    return v0
.end method

.method public static getCronetVersion()Ljava/lang/String;
    .locals 1

    .line 72
    const-string v0, "143.0.7445.0"

    return-object v0
.end method

.method public static getMaximumAvailableApiLevel()I
    .locals 1

    const/16 v0, 0x28

    return v0
.end method
