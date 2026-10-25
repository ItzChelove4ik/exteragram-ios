.class public abstract Lorg/telegram/tgnet/TLRPC$ReportReason;
.super Lorg/telegram/tgnet/TLObject;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/tgnet/TLRPC;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x409
    name = "ReportReason"
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 26119
    invoke-direct {p0}, Lorg/telegram/tgnet/TLObject;-><init>()V

    return-void
.end method

.method public static TLdeserialize(Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLRPC$ReportReason;
    .locals 2

    sparse-switch p1, :sswitch_data_0

    const/4 v0, 0x0

    goto :goto_0

    .line 26137
    :sswitch_0
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputReportReasonSpam;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputReportReasonSpam;-><init>()V

    goto :goto_0

    .line 26146
    :sswitch_1
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputReportReasonPornography;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputReportReasonPornography;-><init>()V

    goto :goto_0

    .line 26140
    :sswitch_2
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputReportReasonViolence;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputReportReasonViolence;-><init>()V

    goto :goto_0

    .line 26152
    :sswitch_3
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputReportReasonIllegalDrugs;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputReportReasonIllegalDrugs;-><init>()V

    goto :goto_0

    .line 26131
    :sswitch_4
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputReportReasonFake;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputReportReasonFake;-><init>()V

    goto :goto_0

    .line 26125
    :sswitch_5
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputReportReasonGeoIrrelevant;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputReportReasonGeoIrrelevant;-><init>()V

    goto :goto_0

    .line 26128
    :sswitch_6
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputReportReasonOther;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputReportReasonOther;-><init>()V

    goto :goto_0

    .line 26143
    :sswitch_7
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputReportReasonChildAbuse;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputReportReasonChildAbuse;-><init>()V

    goto :goto_0

    .line 26149
    :sswitch_8
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputReportReasonPersonalDetails;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputReportReasonPersonalDetails;-><init>()V

    goto :goto_0

    .line 26134
    :sswitch_9
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_inputReportReasonCopyright;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_inputReportReasonCopyright;-><init>()V

    .line 26155
    :goto_0
    const-class v1, Lorg/telegram/tgnet/TLRPC$ReportReason;

    invoke-static {v1, v0, p0, p1, p2}, Lorg/telegram/tgnet/TLObject;->TLdeserialize(Ljava/lang/Class;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/InputSerializedData;IZ)Lorg/telegram/tgnet/TLObject;

    move-result-object p0

    check-cast p0, Lorg/telegram/tgnet/TLRPC$ReportReason;

    return-object p0

    nop

    :sswitch_data_0
    .sparse-switch
        -0x647606c6 -> :sswitch_9
        -0x613879c3 -> :sswitch_8
        -0x520bb11d -> :sswitch_7
        -0x3e1b5d4f -> :sswitch_6
        -0x242b0113 -> :sswitch_5
        -0xa222919 -> :sswitch_4
        0xa8eb2be -> :sswitch_3
        0x1e22c78d -> :sswitch_2
        0x2e59d922 -> :sswitch_1
        0x58dbcab8 -> :sswitch_0
    .end sparse-switch
.end method
