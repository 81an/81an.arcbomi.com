package com.google.firebase.database.tubesock;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
class MessageBuilderFactory {

    /* loaded from: classes.dex */
    interface Builder {
        boolean appendBytes(byte[] bArr);

        WebSocketMessage toMessage();
    }

    MessageBuilderFactory() {
    }

    /* loaded from: classes.dex */
    static class BinaryBuilder implements Builder {
        private int pendingByteCount = 0;
        private List<byte[]> pendingBytes = new ArrayList();

        BinaryBuilder() {
        }

        @Override // com.google.firebase.database.tubesock.MessageBuilderFactory.Builder
        public boolean appendBytes(byte[] bArr) {
            this.pendingBytes.add(bArr);
            this.pendingByteCount += bArr.length;
            return true;
        }

        @Override // com.google.firebase.database.tubesock.MessageBuilderFactory.Builder
        public WebSocketMessage toMessage() {
            byte[] bArr = new byte[this.pendingByteCount];
            int i = 0;
            for (int i2 = 0; i2 < this.pendingBytes.size(); i2++) {
                byte[] bArr2 = this.pendingBytes.get(i2);
                System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
                i += bArr2.length;
            }
            return new WebSocketMessage(bArr);
        }
    }

    /* loaded from: classes.dex */
    static class TextBuilder implements Builder {
        private static ThreadLocal<CharsetDecoder> localDecoder = new ThreadLocal<CharsetDecoder>() { // from class: com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public CharsetDecoder initialValue() {
                CharsetDecoder newDecoder = Charset.forName("UTF8").newDecoder();
                newDecoder.onMalformedInput(CodingErrorAction.REPORT);
                newDecoder.onUnmappableCharacter(CodingErrorAction.REPORT);
                return newDecoder;
            }
        };
        private static ThreadLocal<CharsetEncoder> localEncoder = new ThreadLocal<CharsetEncoder>() { // from class: com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public CharsetEncoder initialValue() {
                CharsetEncoder newEncoder = Charset.forName("UTF8").newEncoder();
                newEncoder.onMalformedInput(CodingErrorAction.REPORT);
                newEncoder.onUnmappableCharacter(CodingErrorAction.REPORT);
                return newEncoder;
            }
        };
        private StringBuilder builder = new StringBuilder();
        private ByteBuffer carryOver;

        TextBuilder() {
        }

        @Override // com.google.firebase.database.tubesock.MessageBuilderFactory.Builder
        public boolean appendBytes(byte[] bArr) {
            String decodeString = decodeString(bArr);
            if (decodeString == null) {
                return false;
            }
            this.builder.append(decodeString);
            return true;
        }

        @Override // com.google.firebase.database.tubesock.MessageBuilderFactory.Builder
        public WebSocketMessage toMessage() {
            if (this.carryOver != null) {
                return null;
            }
            return new WebSocketMessage(this.builder.toString());
        }

        private String decodeString(byte[] bArr) {
            try {
                return localDecoder.get().decode(ByteBuffer.wrap(bArr)).toString();
            } catch (CharacterCodingException unused) {
                return null;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:18:0x003b, code lost:
        
            if (r6.remaining() <= 0) goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x003d, code lost:
        
            r5.carryOver = r6;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x003f, code lost:
        
            com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.localEncoder.get().encode(java.nio.CharBuffer.wrap(r2));
            r2.flip();
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x0055, code lost:
        
            return r2.toString();
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private java.lang.String decodeStringStreaming(byte[] r6) {
            /*
                r5 = this;
                r0 = 0
                java.nio.ByteBuffer r6 = r5.getBuffer(r6)     // Catch: java.nio.charset.CharacterCodingException -> L6c
                int r1 = r6.remaining()     // Catch: java.nio.charset.CharacterCodingException -> L6c
                float r1 = (float) r1     // Catch: java.nio.charset.CharacterCodingException -> L6c
                java.lang.ThreadLocal<java.nio.charset.CharsetDecoder> r2 = com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.localDecoder     // Catch: java.nio.charset.CharacterCodingException -> L6c
                java.lang.Object r2 = r2.get()     // Catch: java.nio.charset.CharacterCodingException -> L6c
                java.nio.charset.CharsetDecoder r2 = (java.nio.charset.CharsetDecoder) r2     // Catch: java.nio.charset.CharacterCodingException -> L6c
                float r2 = r2.averageCharsPerByte()     // Catch: java.nio.charset.CharacterCodingException -> L6c
                float r1 = r1 * r2
                int r1 = (int) r1     // Catch: java.nio.charset.CharacterCodingException -> L6c
                java.nio.CharBuffer r2 = java.nio.CharBuffer.allocate(r1)     // Catch: java.nio.charset.CharacterCodingException -> L6c
            L1d:
                java.lang.ThreadLocal<java.nio.charset.CharsetDecoder> r3 = com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.localDecoder     // Catch: java.nio.charset.CharacterCodingException -> L6c
                java.lang.Object r3 = r3.get()     // Catch: java.nio.charset.CharacterCodingException -> L6c
                java.nio.charset.CharsetDecoder r3 = (java.nio.charset.CharsetDecoder) r3     // Catch: java.nio.charset.CharacterCodingException -> L6c
                r4 = 0
                java.nio.charset.CoderResult r3 = r3.decode(r6, r2, r4)     // Catch: java.nio.charset.CharacterCodingException -> L6c
                boolean r4 = r3.isError()     // Catch: java.nio.charset.CharacterCodingException -> L6c
                if (r4 == 0) goto L31
                return r0
            L31:
                boolean r4 = r3.isUnderflow()     // Catch: java.nio.charset.CharacterCodingException -> L6c
                if (r4 == 0) goto L56
                int r1 = r6.remaining()     // Catch: java.nio.charset.CharacterCodingException -> L6c
                if (r1 <= 0) goto L3f
                r5.carryOver = r6     // Catch: java.nio.charset.CharacterCodingException -> L6c
            L3f:
                java.nio.CharBuffer r6 = java.nio.CharBuffer.wrap(r2)     // Catch: java.nio.charset.CharacterCodingException -> L6c
                java.lang.ThreadLocal<java.nio.charset.CharsetEncoder> r1 = com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.localEncoder     // Catch: java.nio.charset.CharacterCodingException -> L6c
                java.lang.Object r1 = r1.get()     // Catch: java.nio.charset.CharacterCodingException -> L6c
                java.nio.charset.CharsetEncoder r1 = (java.nio.charset.CharsetEncoder) r1     // Catch: java.nio.charset.CharacterCodingException -> L6c
                r1.encode(r6)     // Catch: java.nio.charset.CharacterCodingException -> L6c
                r2.flip()     // Catch: java.nio.charset.CharacterCodingException -> L6c
                java.lang.String r6 = r2.toString()     // Catch: java.nio.charset.CharacterCodingException -> L6c
                return r6
            L56:
                boolean r3 = r3.isOverflow()     // Catch: java.nio.charset.CharacterCodingException -> L6c
                if (r3 == 0) goto L1d
                int r1 = r1 * 2
                int r1 = r1 + 1
                java.nio.CharBuffer r3 = java.nio.CharBuffer.allocate(r1)     // Catch: java.nio.charset.CharacterCodingException -> L6c
                r2.flip()     // Catch: java.nio.charset.CharacterCodingException -> L6c
                r3.put(r2)     // Catch: java.nio.charset.CharacterCodingException -> L6c
                r2 = r3
                goto L1d
            L6c:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.decodeStringStreaming(byte[]):java.lang.String");
        }

        private ByteBuffer getBuffer(byte[] bArr) {
            ByteBuffer byteBuffer = this.carryOver;
            if (byteBuffer != null) {
                ByteBuffer allocate = ByteBuffer.allocate(bArr.length + byteBuffer.remaining());
                allocate.put(this.carryOver);
                this.carryOver = null;
                allocate.put(bArr);
                allocate.flip();
                return allocate;
            }
            return ByteBuffer.wrap(bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Builder builder(byte b) {
        if (b == 2) {
            return new BinaryBuilder();
        }
        return new TextBuilder();
    }
}
