.class final Lorg/chromium/net/httpflags/BaseFeature$ParsedFlagName;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/chromium/net/httpflags/BaseFeature;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "ParsedFlagName"
.end annotation


# instance fields
.field public featureName:Ljava/lang/String;

.field public paramName:Ljava/lang/String;


# direct methods
.method private constructor <init>()V
    .locals 0

    .line 106
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public synthetic constructor <init>(Lorg/chromium/net/httpflags/BaseFeature-IA;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/chromium/net/httpflags/BaseFeature$ParsedFlagName;-><init>()V

    return-void
.end method
