.class public abstract Lorg/chromium/net/impl/CronetLogger;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/chromium/net/impl/CronetLogger$CronetVersion;,
        Lorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;,
        Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInfo;,
        Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;,
        Lorg/chromium/net/impl/CronetLogger$CronetSource;
    }
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 13
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public abstract generateId()J
.end method

.method public abstract logCronetEngineBuilderInitializedInfo(Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;)V
.end method

.method public abstract logCronetEngineCreation(JLorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInfo;Lorg/chromium/net/impl/CronetLogger$CronetVersion;Lorg/chromium/net/impl/CronetLogger$CronetSource;)V
.end method

.method public abstract logCronetTrafficInfo(JLorg/chromium/net/impl/CronetLogger$CronetTrafficInfo;)V
.end method
