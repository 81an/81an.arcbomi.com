package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* loaded from: classes.dex */
public interface OAuthRequirementsOrBuilder extends MessageLiteOrBuilder {
    String getCanonicalScopes();

    ByteString getCanonicalScopesBytes();
}
