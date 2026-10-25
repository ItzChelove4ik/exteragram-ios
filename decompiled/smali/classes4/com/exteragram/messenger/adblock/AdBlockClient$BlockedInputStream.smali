.class Lcom/exteragram/messenger/adblock/AdBlockClient$BlockedInputStream;
.super Ljava/io/InputStream;
.source "SourceFile"

# interfaces
.implements Lj$/io/InputStreamRetargetInterface;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/adblock/AdBlockClient;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "BlockedInputStream"
.end annotation


# direct methods
.method private constructor <init>()V
    .locals 0

    .line 228
    invoke-direct {p0}, Ljava/io/InputStream;-><init>()V

    return-void
.end method

.method public synthetic constructor <init>(Lcom/exteragram/messenger/adblock/AdBlockClient-IA;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/adblock/AdBlockClient$BlockedInputStream;-><init>()V

    return-void
.end method


# virtual methods
.method public available()I
    .locals 0

    .line 231
    invoke-static {}, Lcom/exteragram/messenger/adblock/AdBlockClient;->-$$Nest$sfgetBLOCKED_EXCEPTION()Ljava/io/IOException;

    move-result-object p0

    throw p0
.end method

.method public read()I
    .locals 0

    .line 236
    invoke-static {}, Lcom/exteragram/messenger/adblock/AdBlockClient;->-$$Nest$sfgetBLOCKED_EXCEPTION()Ljava/io/IOException;

    move-result-object p0

    throw p0
.end method

.method public synthetic transferTo(Ljava/io/OutputStream;)J
    .locals 0

    .line 0
    invoke-static {p0, p1}, Lj$/io/DesugarInputStream;->transferTo(Ljava/io/InputStream;Ljava/io/OutputStream;)J

    move-result-wide p0

    return-wide p0
.end method
