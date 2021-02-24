package com.coizioc.dasarcoin.mock;

import com.coizioc.dasarcoin.blockchain.Block;
import com.coizioc.dasarcoin.blockchain.ProofOfWork;

public class ProofOfWorkMock implements ProofOfWork {
    public static final long NONCE = 3289784;
    public static final byte[] HASH = new byte[32];

    public ProofOfWorkMock() {}

    @Override
    public Result run(Block block) {
        return new Result(NONCE, HASH);
    }

    @Override
    public boolean validate(Block block) {
        return false;
    }
}
