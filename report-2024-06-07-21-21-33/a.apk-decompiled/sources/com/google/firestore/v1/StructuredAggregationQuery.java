package com.google.firestore.v1;

import com.google.firestore.v1.StructuredQuery;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Int64Value;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class StructuredAggregationQuery extends GeneratedMessageLite<StructuredAggregationQuery, Builder> implements StructuredAggregationQueryOrBuilder {
    public static final int AGGREGATIONS_FIELD_NUMBER = 3;
    private static final StructuredAggregationQuery DEFAULT_INSTANCE;
    private static volatile Parser<StructuredAggregationQuery> PARSER = null;
    public static final int STRUCTURED_QUERY_FIELD_NUMBER = 1;
    private Object queryType_;
    private int queryTypeCase_ = 0;
    private Internal.ProtobufList<Aggregation> aggregations_ = emptyProtobufList();

    /* loaded from: classes2.dex */
    public interface AggregationOrBuilder extends MessageLiteOrBuilder {
        String getAlias();

        ByteString getAliasBytes();

        Aggregation.Count getCount();

        Aggregation.OperatorCase getOperatorCase();

        boolean hasCount();
    }

    private StructuredAggregationQuery() {
    }

    /* loaded from: classes2.dex */
    public static final class Aggregation extends GeneratedMessageLite<Aggregation, Builder> implements AggregationOrBuilder {
        public static final int ALIAS_FIELD_NUMBER = 7;
        public static final int COUNT_FIELD_NUMBER = 1;
        private static final Aggregation DEFAULT_INSTANCE;
        private static volatile Parser<Aggregation> PARSER;
        private Object operator_;
        private int operatorCase_ = 0;
        private String alias_ = "";

        /* loaded from: classes2.dex */
        public interface CountOrBuilder extends MessageLiteOrBuilder {
            Int64Value getUpTo();

            boolean hasUpTo();
        }

        private Aggregation() {
        }

        /* loaded from: classes2.dex */
        public static final class Count extends GeneratedMessageLite<Count, Builder> implements CountOrBuilder {
            private static final Count DEFAULT_INSTANCE;
            private static volatile Parser<Count> PARSER = null;
            public static final int UP_TO_FIELD_NUMBER = 1;
            private Int64Value upTo_;

            private Count() {
            }

            @Override // com.google.firestore.v1.StructuredAggregationQuery.Aggregation.CountOrBuilder
            public boolean hasUpTo() {
                return this.upTo_ != null;
            }

            @Override // com.google.firestore.v1.StructuredAggregationQuery.Aggregation.CountOrBuilder
            public Int64Value getUpTo() {
                Int64Value int64Value = this.upTo_;
                return int64Value == null ? Int64Value.getDefaultInstance() : int64Value;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setUpTo(Int64Value int64Value) {
                int64Value.getClass();
                this.upTo_ = int64Value;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void mergeUpTo(Int64Value int64Value) {
                int64Value.getClass();
                Int64Value int64Value2 = this.upTo_;
                if (int64Value2 != null && int64Value2 != Int64Value.getDefaultInstance()) {
                    this.upTo_ = Int64Value.newBuilder(this.upTo_).mergeFrom((Int64Value.Builder) int64Value).buildPartial();
                } else {
                    this.upTo_ = int64Value;
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearUpTo() {
                this.upTo_ = null;
            }

            public static Count parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (Count) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static Count parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Count) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
            }

            public static Count parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (Count) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static Count parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Count) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
            }

            public static Count parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (Count) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static Count parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Count) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
            }

            public static Count parseFrom(InputStream inputStream) throws IOException {
                return (Count) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Count parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Count) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Count parseDelimitedFrom(InputStream inputStream) throws IOException {
                return (Count) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static Count parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Count) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
            }

            public static Count parseFrom(CodedInputStream codedInputStream) throws IOException {
                return (Count) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
            }

            public static Count parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return (Count) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(Count count) {
                return DEFAULT_INSTANCE.createBuilder(count);
            }

            /* loaded from: classes2.dex */
            public static final class Builder extends GeneratedMessageLite.Builder<Count, Builder> implements CountOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    this();
                }

                private Builder() {
                    super(Count.DEFAULT_INSTANCE);
                }

                @Override // com.google.firestore.v1.StructuredAggregationQuery.Aggregation.CountOrBuilder
                public boolean hasUpTo() {
                    return ((Count) this.instance).hasUpTo();
                }

                @Override // com.google.firestore.v1.StructuredAggregationQuery.Aggregation.CountOrBuilder
                public Int64Value getUpTo() {
                    return ((Count) this.instance).getUpTo();
                }

                public Builder setUpTo(Int64Value int64Value) {
                    copyOnWrite();
                    ((Count) this.instance).setUpTo(int64Value);
                    return this;
                }

                public Builder setUpTo(Int64Value.Builder builder) {
                    copyOnWrite();
                    ((Count) this.instance).setUpTo(builder.build());
                    return this;
                }

                public Builder mergeUpTo(Int64Value int64Value) {
                    copyOnWrite();
                    ((Count) this.instance).mergeUpTo(int64Value);
                    return this;
                }

                public Builder clearUpTo() {
                    copyOnWrite();
                    ((Count) this.instance).clearUpTo();
                    return this;
                }
            }

            @Override // com.google.protobuf.GeneratedMessageLite
            protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                AnonymousClass1 anonymousClass1 = null;
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                    case 1:
                        return new Count();
                    case 2:
                        return new Builder(anonymousClass1);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"upTo_"});
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<Count> parser = PARSER;
                        if (parser == null) {
                            synchronized (Count.class) {
                                parser = PARSER;
                                if (parser == null) {
                                    parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                    PARSER = parser;
                                }
                            }
                        }
                        return parser;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            static {
                Count count = new Count();
                DEFAULT_INSTANCE = count;
                GeneratedMessageLite.registerDefaultInstance(Count.class, count);
            }

            public static Count getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Count> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        /* loaded from: classes2.dex */
        public enum OperatorCase {
            COUNT(1),
            OPERATOR_NOT_SET(0);

            private final int value;

            OperatorCase(int i) {
                this.value = i;
            }

            @Deprecated
            public static OperatorCase valueOf(int i) {
                return forNumber(i);
            }

            public static OperatorCase forNumber(int i) {
                if (i == 0) {
                    return OPERATOR_NOT_SET;
                }
                if (i != 1) {
                    return null;
                }
                return COUNT;
            }

            public int getNumber() {
                return this.value;
            }
        }

        @Override // com.google.firestore.v1.StructuredAggregationQuery.AggregationOrBuilder
        public OperatorCase getOperatorCase() {
            return OperatorCase.forNumber(this.operatorCase_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOperator() {
            this.operatorCase_ = 0;
            this.operator_ = null;
        }

        @Override // com.google.firestore.v1.StructuredAggregationQuery.AggregationOrBuilder
        public boolean hasCount() {
            return this.operatorCase_ == 1;
        }

        @Override // com.google.firestore.v1.StructuredAggregationQuery.AggregationOrBuilder
        public Count getCount() {
            if (this.operatorCase_ == 1) {
                return (Count) this.operator_;
            }
            return Count.getDefaultInstance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCount(Count count) {
            count.getClass();
            this.operator_ = count;
            this.operatorCase_ = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCount(Count count) {
            count.getClass();
            if (this.operatorCase_ == 1 && this.operator_ != Count.getDefaultInstance()) {
                this.operator_ = Count.newBuilder((Count) this.operator_).mergeFrom((Count.Builder) count).buildPartial();
            } else {
                this.operator_ = count;
            }
            this.operatorCase_ = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCount() {
            if (this.operatorCase_ == 1) {
                this.operatorCase_ = 0;
                this.operator_ = null;
            }
        }

        @Override // com.google.firestore.v1.StructuredAggregationQuery.AggregationOrBuilder
        public String getAlias() {
            return this.alias_;
        }

        @Override // com.google.firestore.v1.StructuredAggregationQuery.AggregationOrBuilder
        public ByteString getAliasBytes() {
            return ByteString.copyFromUtf8(this.alias_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAlias(String str) {
            str.getClass();
            this.alias_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAlias() {
            this.alias_ = getDefaultInstance().getAlias();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAliasBytes(ByteString byteString) {
            checkByteStringIsUtf8(byteString);
            this.alias_ = byteString.toStringUtf8();
        }

        public static Aggregation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Aggregation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Aggregation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Aggregation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Aggregation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Aggregation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Aggregation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Aggregation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Aggregation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Aggregation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Aggregation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Aggregation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Aggregation parseFrom(InputStream inputStream) throws IOException {
            return (Aggregation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Aggregation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Aggregation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Aggregation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Aggregation) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Aggregation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Aggregation) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Aggregation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Aggregation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Aggregation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Aggregation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Aggregation aggregation) {
            return DEFAULT_INSTANCE.createBuilder(aggregation);
        }

        /* loaded from: classes2.dex */
        public static final class Builder extends GeneratedMessageLite.Builder<Aggregation, Builder> implements AggregationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(Aggregation.DEFAULT_INSTANCE);
            }

            @Override // com.google.firestore.v1.StructuredAggregationQuery.AggregationOrBuilder
            public OperatorCase getOperatorCase() {
                return ((Aggregation) this.instance).getOperatorCase();
            }

            public Builder clearOperator() {
                copyOnWrite();
                ((Aggregation) this.instance).clearOperator();
                return this;
            }

            @Override // com.google.firestore.v1.StructuredAggregationQuery.AggregationOrBuilder
            public boolean hasCount() {
                return ((Aggregation) this.instance).hasCount();
            }

            @Override // com.google.firestore.v1.StructuredAggregationQuery.AggregationOrBuilder
            public Count getCount() {
                return ((Aggregation) this.instance).getCount();
            }

            public Builder setCount(Count count) {
                copyOnWrite();
                ((Aggregation) this.instance).setCount(count);
                return this;
            }

            public Builder setCount(Count.Builder builder) {
                copyOnWrite();
                ((Aggregation) this.instance).setCount(builder.build());
                return this;
            }

            public Builder mergeCount(Count count) {
                copyOnWrite();
                ((Aggregation) this.instance).mergeCount(count);
                return this;
            }

            public Builder clearCount() {
                copyOnWrite();
                ((Aggregation) this.instance).clearCount();
                return this;
            }

            @Override // com.google.firestore.v1.StructuredAggregationQuery.AggregationOrBuilder
            public String getAlias() {
                return ((Aggregation) this.instance).getAlias();
            }

            @Override // com.google.firestore.v1.StructuredAggregationQuery.AggregationOrBuilder
            public ByteString getAliasBytes() {
                return ((Aggregation) this.instance).getAliasBytes();
            }

            public Builder setAlias(String str) {
                copyOnWrite();
                ((Aggregation) this.instance).setAlias(str);
                return this;
            }

            public Builder clearAlias() {
                copyOnWrite();
                ((Aggregation) this.instance).clearAlias();
                return this;
            }

            public Builder setAliasBytes(ByteString byteString) {
                copyOnWrite();
                ((Aggregation) this.instance).setAliasBytes(byteString);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new Aggregation();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0007\u0002\u0000\u0000\u0000\u0001<\u0000\u0007Ȉ", new Object[]{"operator_", "operatorCase_", Count.class, "alias_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Aggregation> parser = PARSER;
                    if (parser == null) {
                        synchronized (Aggregation.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            Aggregation aggregation = new Aggregation();
            DEFAULT_INSTANCE = aggregation;
            GeneratedMessageLite.registerDefaultInstance(Aggregation.class, aggregation);
        }

        public static Aggregation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Aggregation> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredAggregationQuery$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public enum QueryTypeCase {
        STRUCTURED_QUERY(1),
        QUERYTYPE_NOT_SET(0);

        private final int value;

        QueryTypeCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static QueryTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public static QueryTypeCase forNumber(int i) {
            if (i == 0) {
                return QUERYTYPE_NOT_SET;
            }
            if (i != 1) {
                return null;
            }
            return STRUCTURED_QUERY;
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.v1.StructuredAggregationQueryOrBuilder
    public QueryTypeCase getQueryTypeCase() {
        return QueryTypeCase.forNumber(this.queryTypeCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearQueryType() {
        this.queryTypeCase_ = 0;
        this.queryType_ = null;
    }

    @Override // com.google.firestore.v1.StructuredAggregationQueryOrBuilder
    public boolean hasStructuredQuery() {
        return this.queryTypeCase_ == 1;
    }

    @Override // com.google.firestore.v1.StructuredAggregationQueryOrBuilder
    public StructuredQuery getStructuredQuery() {
        if (this.queryTypeCase_ == 1) {
            return (StructuredQuery) this.queryType_;
        }
        return StructuredQuery.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStructuredQuery(StructuredQuery structuredQuery) {
        structuredQuery.getClass();
        this.queryType_ = structuredQuery;
        this.queryTypeCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeStructuredQuery(StructuredQuery structuredQuery) {
        structuredQuery.getClass();
        if (this.queryTypeCase_ == 1 && this.queryType_ != StructuredQuery.getDefaultInstance()) {
            this.queryType_ = StructuredQuery.newBuilder((StructuredQuery) this.queryType_).mergeFrom((StructuredQuery.Builder) structuredQuery).buildPartial();
        } else {
            this.queryType_ = structuredQuery;
        }
        this.queryTypeCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStructuredQuery() {
        if (this.queryTypeCase_ == 1) {
            this.queryTypeCase_ = 0;
            this.queryType_ = null;
        }
    }

    @Override // com.google.firestore.v1.StructuredAggregationQueryOrBuilder
    public List<Aggregation> getAggregationsList() {
        return this.aggregations_;
    }

    public List<? extends AggregationOrBuilder> getAggregationsOrBuilderList() {
        return this.aggregations_;
    }

    @Override // com.google.firestore.v1.StructuredAggregationQueryOrBuilder
    public int getAggregationsCount() {
        return this.aggregations_.size();
    }

    @Override // com.google.firestore.v1.StructuredAggregationQueryOrBuilder
    public Aggregation getAggregations(int i) {
        return this.aggregations_.get(i);
    }

    public AggregationOrBuilder getAggregationsOrBuilder(int i) {
        return this.aggregations_.get(i);
    }

    private void ensureAggregationsIsMutable() {
        Internal.ProtobufList<Aggregation> protobufList = this.aggregations_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.aggregations_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAggregations(int i, Aggregation aggregation) {
        aggregation.getClass();
        ensureAggregationsIsMutable();
        this.aggregations_.set(i, aggregation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAggregations(Aggregation aggregation) {
        aggregation.getClass();
        ensureAggregationsIsMutable();
        this.aggregations_.add(aggregation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAggregations(int i, Aggregation aggregation) {
        aggregation.getClass();
        ensureAggregationsIsMutable();
        this.aggregations_.add(i, aggregation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllAggregations(Iterable<? extends Aggregation> iterable) {
        ensureAggregationsIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.aggregations_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAggregations() {
        this.aggregations_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeAggregations(int i) {
        ensureAggregationsIsMutable();
        this.aggregations_.remove(i);
    }

    public static StructuredAggregationQuery parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (StructuredAggregationQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static StructuredAggregationQuery parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StructuredAggregationQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static StructuredAggregationQuery parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (StructuredAggregationQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static StructuredAggregationQuery parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StructuredAggregationQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static StructuredAggregationQuery parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (StructuredAggregationQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static StructuredAggregationQuery parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StructuredAggregationQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static StructuredAggregationQuery parseFrom(InputStream inputStream) throws IOException {
        return (StructuredAggregationQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static StructuredAggregationQuery parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StructuredAggregationQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static StructuredAggregationQuery parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (StructuredAggregationQuery) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static StructuredAggregationQuery parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StructuredAggregationQuery) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static StructuredAggregationQuery parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (StructuredAggregationQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static StructuredAggregationQuery parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StructuredAggregationQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(StructuredAggregationQuery structuredAggregationQuery) {
        return DEFAULT_INSTANCE.createBuilder(structuredAggregationQuery);
    }

    /* loaded from: classes2.dex */
    public static final class Builder extends GeneratedMessageLite.Builder<StructuredAggregationQuery, Builder> implements StructuredAggregationQueryOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(StructuredAggregationQuery.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.StructuredAggregationQueryOrBuilder
        public QueryTypeCase getQueryTypeCase() {
            return ((StructuredAggregationQuery) this.instance).getQueryTypeCase();
        }

        public Builder clearQueryType() {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).clearQueryType();
            return this;
        }

        @Override // com.google.firestore.v1.StructuredAggregationQueryOrBuilder
        public boolean hasStructuredQuery() {
            return ((StructuredAggregationQuery) this.instance).hasStructuredQuery();
        }

        @Override // com.google.firestore.v1.StructuredAggregationQueryOrBuilder
        public StructuredQuery getStructuredQuery() {
            return ((StructuredAggregationQuery) this.instance).getStructuredQuery();
        }

        public Builder setStructuredQuery(StructuredQuery structuredQuery) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).setStructuredQuery(structuredQuery);
            return this;
        }

        public Builder setStructuredQuery(StructuredQuery.Builder builder) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).setStructuredQuery(builder.build());
            return this;
        }

        public Builder mergeStructuredQuery(StructuredQuery structuredQuery) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).mergeStructuredQuery(structuredQuery);
            return this;
        }

        public Builder clearStructuredQuery() {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).clearStructuredQuery();
            return this;
        }

        @Override // com.google.firestore.v1.StructuredAggregationQueryOrBuilder
        public List<Aggregation> getAggregationsList() {
            return Collections.unmodifiableList(((StructuredAggregationQuery) this.instance).getAggregationsList());
        }

        @Override // com.google.firestore.v1.StructuredAggregationQueryOrBuilder
        public int getAggregationsCount() {
            return ((StructuredAggregationQuery) this.instance).getAggregationsCount();
        }

        @Override // com.google.firestore.v1.StructuredAggregationQueryOrBuilder
        public Aggregation getAggregations(int i) {
            return ((StructuredAggregationQuery) this.instance).getAggregations(i);
        }

        public Builder setAggregations(int i, Aggregation aggregation) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).setAggregations(i, aggregation);
            return this;
        }

        public Builder setAggregations(int i, Aggregation.Builder builder) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).setAggregations(i, builder.build());
            return this;
        }

        public Builder addAggregations(Aggregation aggregation) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).addAggregations(aggregation);
            return this;
        }

        public Builder addAggregations(int i, Aggregation aggregation) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).addAggregations(i, aggregation);
            return this;
        }

        public Builder addAggregations(Aggregation.Builder builder) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).addAggregations(builder.build());
            return this;
        }

        public Builder addAggregations(int i, Aggregation.Builder builder) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).addAggregations(i, builder.build());
            return this;
        }

        public Builder addAllAggregations(Iterable<? extends Aggregation> iterable) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).addAllAggregations(iterable);
            return this;
        }

        public Builder clearAggregations() {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).clearAggregations();
            return this;
        }

        public Builder removeAggregations(int i) {
            copyOnWrite();
            ((StructuredAggregationQuery) this.instance).removeAggregations(i);
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new StructuredAggregationQuery();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0003\u0002\u0000\u0001\u0000\u0001<\u0000\u0003\u001b", new Object[]{"queryType_", "queryTypeCase_", StructuredQuery.class, "aggregations_", Aggregation.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<StructuredAggregationQuery> parser = PARSER;
                if (parser == null) {
                    synchronized (StructuredAggregationQuery.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        StructuredAggregationQuery structuredAggregationQuery = new StructuredAggregationQuery();
        DEFAULT_INSTANCE = structuredAggregationQuery;
        GeneratedMessageLite.registerDefaultInstance(StructuredAggregationQuery.class, structuredAggregationQuery);
    }

    public static StructuredAggregationQuery getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<StructuredAggregationQuery> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
