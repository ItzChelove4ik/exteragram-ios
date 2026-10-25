.class public abstract Landroidx/media3/datasource/DataSourceUtil;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static closeQuietly(Landroidx/media3/datasource/DataSource;)V
    .locals 0

    if-eqz p0, :cond_0

    .line 87
    :try_start_0
    invoke-interface {p0}, Landroidx/media3/datasource/DataSource;->close()V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    :catch_0
    :cond_0
    return-void
.end method

.method public static readToEnd(Landroidx/media3/datasource/DataSource;)[B
    .locals 4

    const/16 v0, 0x400

    .line 39
    new-array v0, v0, [B

    const/4 v1, 0x0

    move v2, v1

    :cond_0
    :goto_0
    const/4 v3, -0x1

    if-eq v1, v3, :cond_2

    .line 43
    array-length v1, v0

    if-ne v2, v1, :cond_1

    .line 44
    array-length v1, v0

    mul-int/lit8 v1, v1, 0x2

    invoke-static {v0, v1}, Ljava/util/Arrays;->copyOf([BI)[B

    move-result-object v0

    .line 46
    :cond_1
    array-length v1, v0

    sub-int/2addr v1, v2

    invoke-interface {p0, v0, v2, v1}, Landroidx/media3/common/DataReader;->read([BII)I

    move-result v1

    if-eq v1, v3, :cond_0

    add-int/2addr v2, v1

    goto :goto_0

    .line 51
    :cond_2
    invoke-static {v0, v2}, Ljava/util/Arrays;->copyOf([BI)[B

    move-result-object p0

    return-object p0
.end method
