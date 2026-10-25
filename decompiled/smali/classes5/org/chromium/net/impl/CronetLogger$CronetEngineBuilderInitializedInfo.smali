.class public final Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/chromium/net/impl/CronetLogger;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "CronetEngineBuilderInitializedInfo"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;
    }
.end annotation


# instance fields
.field public apiVersion:Lorg/chromium/net/impl/CronetLogger$CronetVersion;

.field public author:Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo$Author;

.field public creationSuccessful:Ljava/lang/Boolean;

.field public cronetInitializationRef:J

.field public engineBuilderCreatedLatencyMillis:I

.field public implVersion:Lorg/chromium/net/impl/CronetLogger$CronetVersion;

.field public source:Lorg/chromium/net/impl/CronetLogger$CronetSource;

.field public uid:I


# direct methods
.method public constructor <init>()V
    .locals 1

    .line 62
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, -0x1

    .line 71
    iput v0, p0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;->engineBuilderCreatedLatencyMillis:I

    .line 72
    sget-object v0, Lorg/chromium/net/impl/CronetLogger$CronetSource;->CRONET_SOURCE_UNSPECIFIED:Lorg/chromium/net/impl/CronetLogger$CronetSource;

    iput-object v0, p0, Lorg/chromium/net/impl/CronetLogger$CronetEngineBuilderInitializedInfo;->source:Lorg/chromium/net/impl/CronetLogger$CronetSource;

    return-void
.end method
