package com.coizioc.dasarcoin.blockchain;

import lombok.Value;

public interface ProofOfWork {
    Result run(Block block);

    boolean validate(Block block);

    @Value
    class Result {
        long nonce;
        byte[] hash;
    }
}
