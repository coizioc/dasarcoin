package com.coizioc.dasarcoin.blockchain;

import com.coizioc.dasarcoin.util.Timestamp;
import lombok.Value;

import java.io.Serializable;

@Value
public class Block implements Serializable {
    long timestamp;
    long nonce;
    byte[] data;
    byte[] prevBlockHash;
    byte[] hash;

    public Block(ProofOfWork pow, String data, byte[] prevBlockHash) {
        timestamp = Timestamp.now();
        this.data = data.getBytes();
        this.prevBlockHash = prevBlockHash;

        ProofOfWork.Result result = pow.run(this);

        this.hash = result.getHash();
        this.nonce = result.getNonce();
    }
}
