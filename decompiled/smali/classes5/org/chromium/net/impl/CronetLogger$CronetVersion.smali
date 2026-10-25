.class public Lorg/chromium/net/impl/CronetLogger$CronetVersion;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/chromium/net/impl/CronetLogger;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "CronetVersion"
.end annotation


# instance fields
.field private final mBuildVersion:I

.field private final mMajorVersion:I

.field private final mMinorVersion:I

.field private final mPatchVersion:I


# direct methods
.method public constructor <init>(Ljava/lang/String;)V
    .locals 1

    .line 442
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 443
    const-string v0, "\\."

    invoke-virtual {p1, v0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p1

    const/4 v0, 0x0

    .line 444
    aget-object v0, p1, v0

    invoke-static {v0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v0

    iput v0, p0, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->mMajorVersion:I

    const/4 v0, 0x1

    .line 445
    aget-object v0, p1, v0

    invoke-static {v0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v0

    iput v0, p0, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->mMinorVersion:I

    const/4 v0, 0x2

    .line 446
    aget-object v0, p1, v0

    invoke-static {v0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v0

    iput v0, p0, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->mBuildVersion:I

    const/4 v0, 0x3

    .line 447
    aget-object p1, p1, v0

    invoke-static {p1}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result p1

    iput p1, p0, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->mPatchVersion:I

    return-void
.end method


# virtual methods
.method public getBuildVersion()I
    .locals 0

    .line 462
    iget p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->mBuildVersion:I

    return p0
.end method

.method public getMajorVersion()I
    .locals 0

    .line 452
    iget p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->mMajorVersion:I

    return p0
.end method

.method public getMinorVersion()I
    .locals 0

    .line 457
    iget p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->mMinorVersion:I

    return p0
.end method

.method public getPatchVersion()I
    .locals 0

    .line 467
    iget p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->mPatchVersion:I

    return p0
.end method

.method public toString()Ljava/lang/String;
    .locals 4

    .line 472
    iget v0, p0, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->mMajorVersion:I

    iget v1, p0, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->mMinorVersion:I

    iget v2, p0, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->mBuildVersion:I

    iget p0, p0, Lorg/chromium/net/impl/CronetLogger$CronetVersion;->mPatchVersion:I

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, "."

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
